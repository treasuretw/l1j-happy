/**
 *                            License
 * THE WORK (AS DEFINED BELOW) IS PROVIDED UNDER THE TERMS OF THIS  
 * CREATIVE COMMONS PUBLIC LICENSE ("CCPL" OR "LICENSE"). 
 * THE WORK IS PROTECTED BY COPYRIGHT AND/OR OTHER APPLICABLE LAW.  
 * ANY USE OF THE WORK OTHER THAN AS AUTHORIZED UNDER THIS LICENSE OR  
 * COPYRIGHT LAW IS PROHIBITED.
 * 
 * BY EXERCISING ANY RIGHTS TO THE WORK PROVIDED HERE, YOU ACCEPT AND  
 * AGREE TO BE BOUND BY THE TERMS OF THIS LICENSE. TO THE EXTENT THIS LICENSE  
 * MAY BE CONSIDERED TO BE A CONTRACT, THE LICENSOR GRANTS YOU THE RIGHTS CONTAINED 
 * HERE IN CONSIDERATION OF YOUR ACCEPTANCE OF SUCH TERMS AND CONDITIONS.
 * 
 */
package l1j.server.server;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import l1j.server.Config;
import l1j.server.server.Opcodes;
import l1j.server.server.datatables.CharBuffTable;
import l1j.server.server.model.L1DragonSlayer;
import l1j.server.server.model.Getback;
import l1j.server.server.model.L1Trade;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1DollInstance;
import l1j.server.server.model.Instance.L1FollowerInstance;
import l1j.server.server.model.Instance.L1NpcInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.Instance.L1PetInstance;
import l1j.server.server.model.Instance.L1SummonInstance;
import l1j.server.server.serverpackets.S_Disconnect;
import l1j.server.server.serverpackets.S_PacketBox;
import l1j.server.server.serverpackets.S_SummonPack;
import l1j.server.server.serverpackets.ServerBasePacket;
import l1j.server.server.utils.StreamUtil;
import l1j.server.server.utils.SystemUtil;

// Referenced classes of package l1j.server.server:
// PacketHandler, Logins, IpTable, LoginController,
// ClanTable, IdFactory
//
public class ClientThread implements Runnable, PacketOutput {

	private static Logger _log = Logger.getLogger(ClientThread.class.getName());

	private InputStream _in;

	private OutputStream _out;

	private PacketHandler _handler;

	private Account _account;

	private L1PcInstance _activeChar;

	private String _ip;

	private String _hostname;

	private Socket _csocket;

	private int _loginStatus = 0;

	/*
	 * private static final byte[] FIRST_PACKET = { // 3.0 (byte) 0xec, (byte)
	 * 0x64, (byte) 0x3e, (byte) 0x0d, (byte) 0xc0, (byte) 0x82, (byte) 0x00,
	 * (byte) 0x00, (byte) 0x02, (byte) 0x08, (byte) 0x00 };
	 */
	private static final byte[] FIRST_PACKET = { // 3.3C Taiwan Server
	(byte) 0x65, (byte) 0xb6, (byte) 0xbd, (byte) 0x65, (byte) 0xcc,
			(byte) 0xd0, (byte) 0x7e, (byte) 0x53, (byte) 0x2e, (byte) 0xfa,
			(byte) 0xc1 };

	/**
	 * for Test
	 */
	protected ClientThread() {
	}

	public ClientThread(Socket socket) throws IOException {
		_csocket = socket;
		_ip = socket.getInetAddress().getHostAddress();
		if (Config.HOSTNAME_LOOKUPS) {
			_hostname = socket.getInetAddress().getHostName();
		} else {
			_hostname = _ip;
		}
		_in = socket.getInputStream();
		_out = new BufferedOutputStream(socket.getOutputStream());

		// PacketHandler 初始化
		_handler = new PacketHandler(this);
	}

	public String getIp() {
		return _ip;
	}

	public String getHostname() {
		return _hostname;
	}

	// TODO: 翻译
	// ClientThreadによる一定间隔自动セーブを制限する为のフラグ（true:制限 false:制限无し）
	// 现在はC_LoginToServerが实行された际にfalseとなり、
	// C_NewCharSelectが实行された际にtrueとなる
	private boolean _charRestart = true;

	public void CharReStart(boolean flag) {
		_charRestart = flag;
	}

	private byte[] readPacket() throws Exception {
		try {
			int hiByte = _in.read();
			int loByte = _in.read();
			if (loByte < 0) {
				throw new RuntimeException();
			}
			int dataLength = (loByte * 256 + hiByte) - 2;

			byte data[] = new byte[dataLength];

			int readSize = 0;

			for (int i = 0; i != -1 && readSize < dataLength; readSize += i) {
				i = _in.read(data, readSize, dataLength - readSize);
			}

			if (readSize != dataLength) {
				_log.warning("Incomplete Packet is sent to the server, closing connection.");
				throw new RuntimeException();
			}

			return _cipher.decrypt(data);
		} catch (IOException e) {
			throw e;
		}
	}

	private long _lastSavedTime = System.currentTimeMillis();

	private long _lastSavedTime_inventory = System.currentTimeMillis();

	private Cipher _cipher;

	private void doAutoSave() throws Exception {
		if (_activeChar == null || _charRestart) {
			return;
		}
		try {
			// 自动储存角色资料
			if (Config.AUTOSAVE_INTERVAL * 1000 < System.currentTimeMillis()
					- _lastSavedTime) {
				_activeChar.save();
				_lastSavedTime = System.currentTimeMillis();
				_log.info("-----自动储存角色资料-----");
			}

			// 自动储存身上道具资料
			if (Config.AUTOSAVE_INTERVAL_INVENTORY * 1000 < System
					.currentTimeMillis() - _lastSavedTime_inventory) {
				_activeChar.saveInventory();
				_lastSavedTime_inventory = System.currentTimeMillis();
				_log.info("-----自动储存身上道具资料-----");
			}
		} catch (Exception e) {
			_log.warning("Client autosave failure.");
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw e;
		}
	}

	@Override
	public void run() {
		_log.info("(" + _hostname + ") 开始连线...。");
		System.out.println("使用内存:  " + SystemUtil.getUsedMemoryMB() + "MB");
		System.out.println("等待客户端连线中...");

		/*
		 * TODO: 翻译 クライアントからのパケットをある程度制限する。 理由：不正の误检出が多発する恐れがあるため
		 * ex1.サーバに过负荷が挂かっている场合、负荷が落ちたときにクライアントパケットを一气に处理し、结果的に不正扱いとなる。
		 * ex2.サーバ侧のネットワーク（下り）にラグがある场合、クライアントパケットが一气に流れ迂み、结果的に不正扱いとなる。
		 * ex3.クライアント侧のネットワーク（上り）にラグがある场合、以下同样。
		 * 
		 * 无制限にする前に不正检出方法を见直す必要がある。
		 */
		HcPacket movePacket = new HcPacket(M_CAPACITY);
		HcPacket hcPacket = new HcPacket(H_CAPACITY);
		GeneralThreadPool.getInstance().execute(movePacket);
		GeneralThreadPool.getInstance().execute(hcPacket);

		ClientThreadObserver observer = new ClientThreadObserver(
				Config.AUTOMATIC_KICK * 60 * 1000); // 自动断线的时间（单位:毫秒）

		// 是否启用自动踢人
		if (Config.AUTOMATIC_KICK > 0) {
			observer.start();
		}

		try {
			// long seed = 0x7c98bdfa; // 3.0
			int key = 0x1a986541;// 3.3C Taiwan Server
			byte Bogus = (byte) (FIRST_PACKET.length + 7);
			_out.write(Bogus & 0xFF);
			_out.write(Bogus >> 8 & 0xFF);
			_out.write(Opcodes.S_OPCODE_INITPACKET);// 3.3C Taiwan Server
			_out.write((byte) (key & 0xFF));
			_out.write((byte) (key >> 8 & 0xFF));
			_out.write((byte) (key >> 16 & 0xFF));
			_out.write((byte) (key >> 24 & 0xFF));

			_out.write(FIRST_PACKET);
			_out.flush();

			_cipher = new Cipher(key);

			while (true) {
				doAutoSave();

				byte data[] = null;
				try {
					data = readPacket();
				} catch (Exception e) {
					break;
				}
				// _log.finest("[C]\n" + new
				// ByteArrayUtil(data).dumpToString());

				int opcode = data[0] & 0xFF;

				// 处理多重登入
				if (opcode == Opcodes.C_OPCODE_COMMONCLICK
						|| opcode == Opcodes.C_OPCODE_CHANGECHAR) {
					_loginStatus = 1;
				}
				if (opcode == Opcodes.C_OPCODE_LOGINTOSERVER) {
					if (_loginStatus != 1) {
						continue;
					}
				}
				if (opcode == Opcodes.C_OPCODE_LOGINTOSERVEROK
						|| opcode == Opcodes.C_OPCODE_RETURNTOLOGIN) {
					_loginStatus = 0;
				}

				if (opcode != Opcodes.C_OPCODE_KEEPALIVE) {
					// C_OPCODE_KEEPALIVE以外の何かしらのパケットを受け取ったらObserverへ通知
					observer.packetReceived();
				}
				// TODO: 翻译
				// 如果目前角色为 null はキャラクター选択前なのでOpcodeの取舍选択はせず全て实行
				if (_activeChar == null) {
					_handler.handlePacket(data, _activeChar);
					continue;
				}

				// TODO: 翻译
				// 以降、PacketHandlerの处理状况がClientThreadに影响を与えないようにする为の处理
				// 目的はOpcodeの取舍选択とClientThreadとPacketHandlerの切り离し

				// 要处理的 OPCODE
				// 切换角色、丢道具到地上、删除身上道具
				if (opcode == Opcodes.C_OPCODE_CHANGECHAR
						|| opcode == Opcodes.C_OPCODE_DROPITEM
						|| opcode == Opcodes.C_OPCODE_DELETEINVENTORYITEM) {
					_handler.handlePacket(data, _activeChar);
				} else if (opcode == Opcodes.C_OPCODE_MOVECHAR) {
					// 为了确保即时的移动，将移动的封包独立出来处理
					movePacket.requestWork(data);
				} else {
					// 处理其他数据的传递
					hcPacket.requestWork(data);
				}
			}
		} catch (Throwable e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			try {
				if (_activeChar != null) {
					quitGame(_activeChar);

					synchronized (_activeChar) {
						// 从线上中登出角色
						_activeChar.logout();
						setActiveChar(null);
					}
				}
				// 玩家离线时, online=0
				if (getAccount() != null) {
					Account.online(getAccount(), false);
				}

				// 送出断线的封包
				sendPacket(new S_Disconnect());

				StreamUtil.close(_out, _in);
			} catch (Exception e) {
				_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			} finally {
				LoginController.getInstance().logout(this);
			}
		}
		_csocket = null;
		_log.fine("Server thread[C] stopped");
		if (_kick < 1) {
			System.out.println("=================================================");

			_log.info("帐号：(" + getAccountName() + ":" + _hostname + ")连线结束。");
			System.out.println("使用内存" + SystemUtil.getUsedMemoryMB()
					+ "MB");
			System.out.println("等待客户端连线中...");
			if (getAccount() != null) {
				Account.online(getAccount(), false);
			}
		}
		return;
	}

	private int _kick = 0;

	public void kick() {
		Account.online(getAccount(), false);
		sendPacket(new S_Disconnect());
		_kick = 1;
		StreamUtil.close(_out, _in);
	}

	private static final int M_CAPACITY = 3; // 一边移动的最大封包量

	private static final int H_CAPACITY = 2;// 一方接受的最高限额所需的行动

	// 帐号处理的程序
	class HcPacket implements Runnable {
		private final Queue<byte[]> _queue;

		private PacketHandler _handler;

		public HcPacket() {
			_queue = new ConcurrentLinkedQueue<byte[]>();
			_handler = new PacketHandler(ClientThread.this);
		}

		public HcPacket(int capacity) {
			_queue = new LinkedBlockingQueue<byte[]>(capacity);
			_handler = new PacketHandler(ClientThread.this);
		}

		public void requestWork(byte data[]) {
			_queue.offer(data);
		}

		@Override
		public void run() {
			byte[] data;
			while (_csocket != null) {
				data = _queue.poll();
				if (data != null) {
					try {
						_handler.handlePacket(data, _activeChar);
					} catch (Exception e) {
					}
				} else {
					try {
						Thread.sleep(10);
					} catch (Exception e) {
					}
				}
			}
			return;
		}
	}

	private static Timer _observerTimer = new Timer();

	// 定时监控客户端
	class ClientThreadObserver extends TimerTask {
		private int _checkct = 1;

		private final int _disconnectTimeMillis;

		public ClientThreadObserver(int disconnectTimeMillis) {
			_disconnectTimeMillis = disconnectTimeMillis;
		}

		public void start() {
			_observerTimer.scheduleAtFixedRate(ClientThreadObserver.this, 0,
					_disconnectTimeMillis);
		}

		@Override
		public void run() {
			try {
				if (_csocket == null) {
					cancel();
					return;
				}

				if (_checkct > 0) {
					_checkct = 0;
					return;
				}

				if (_activeChar == null // 选角色之前
						|| _activeChar != null && !_activeChar.isPrivateShop()) { // 正在个人商店
					kick();
					_log.warning("一定时间没有收到封包回应，所以强制切断 (" + _hostname + ") 的连线。");
					Account.online(getAccount(), false);
					cancel();
					return;
				}
			} catch (Exception e) {
				_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
				cancel();
			}
		}

		public void packetReceived() {
			_checkct++;
		}
	}

	@Override
	public void sendPacket(ServerBasePacket packet) {
		synchronized (this) {
			try {
				byte content[] = packet.getContent();
				byte data[] = Arrays.copyOf(content, content.length);
				_cipher.encrypt(data);
				int length = data.length + 2;

				_out.write(length & 0xff);
				_out.write(length >> 8 & 0xff);
				_out.write(data);
				_out.flush();
			} catch (Exception e) {
			}
		}
	}

	public void close() throws IOException {
		_csocket.close();
	}

	public void setActiveChar(L1PcInstance pc) {
		_activeChar = pc;
	}

	public L1PcInstance getActiveChar() {
		return _activeChar;
	}

	public void setAccount(Account account) {
		_account = account;
	}

	public Account getAccount() {
		return _account;
	}

	public String getAccountName() {
		if (_account == null) {
			return null;
		}
		return _account.getName();
	}

	public static void quitGame(L1PcInstance pc) {
		// 如果死掉回到城中，设定饱食度
		if (pc.isDead()) {
			try {
				Thread.sleep(2000);// 暂停该执行续，优先权让给expmonitor
				int[] loc = Getback.GetBack_Location(pc, true);
				pc.setX(loc[0]);
				pc.setY(loc[1]);
				pc.setMap((short) loc[2]);
				pc.setCurrentHp(pc.getLevel());
				pc.set_food(40);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 终止交易
		if (pc.getTradeID() != 0) { // トレード中
			L1Trade trade = new L1Trade();
			trade.TradeCancel(pc);
		}

		// 终止决斗
		if (pc.getFightId() != 0) {
			L1PcInstance fightPc = (L1PcInstance) L1World.getInstance()
					.findObject(pc.getFightId());
			pc.setFightId(0);
			if (fightPc != null) {
				fightPc.setFightId(0);
				fightPc.sendPackets(new S_PacketBox(S_PacketBox.MSG_DUEL, 0, 0));
			}
		}

		// 离开组队
		if (pc.isInParty()) { // 如果有组队
			pc.getParty().leaveMember(pc);
		}

		// TODO: 离开聊天组队(?)
		if (pc.isInChatParty()) { // 如果在聊天组队中(?)
			pc.getChatParty().leaveMember(pc);
		}

		// 移除世界地图上的宠物
		// 变更召唤怪物的名称
		for (L1NpcInstance petNpc : pc.getPetList().values()) {
			if (petNpc instanceof L1PetInstance) {
				L1PetInstance pet = (L1PetInstance) petNpc;
				// 停止饱食度计时
				pet.stopFoodTimer(pet);
				pet.dropItem();
				pc.getPetList().remove(pet.getId());
				pet.deleteMe();
			}
			else if (petNpc instanceof L1SummonInstance) {
				L1SummonInstance summon = (L1SummonInstance) petNpc;
				for (L1PcInstance visiblePc : L1World.getInstance()
						.getVisiblePlayer(summon)) {
					visiblePc.sendPackets(new S_SummonPack(summon, visiblePc,
							false));
				}
			}
		}

		// 移除世界地图上的魔法娃娃
		for (L1DollInstance doll : pc.getDollList().values())
			doll.deleteDoll();

		// 重新建立跟随者
		for ( L1FollowerInstance follower : pc.getFollowerList().values()) {
			follower.setParalyzed(true);
			follower.spawn(follower.getNpcTemplate().get_npcId(),
					follower.getX(), follower.getY(), follower.getHeading(),
					follower.getMapId());
			follower.deleteMe();
		}

		// 删除屠龙副本此玩家纪录
		if (pc.getPortalNumber() != -1) {
			L1DragonSlayer.getInstance().removePlayer(pc, pc.getPortalNumber());
		}

		// 储存魔法状态
		CharBuffTable.DeleteBuff(pc);
		CharBuffTable.SaveBuff(pc);
		pc.clearSkillEffectTimer();
		l1j.server.server.model.game.L1PolyRace.getInstance()
				.checkLeaveGame(pc);

		// 停止玩家的侦测
		pc.stopEtcMonitor();
	
		// 设定线上状态为下线
		pc.setOnlineStatus(0);

		// TODO 管理者介面byeric1300460
		if (Config.GUI) {
			l1j.eric.gui.J_Main.getInstance().delPlayerTable(pc.getName());
		}

		// 设定帐号为下线
		Account account = Account.load(pc.getAccountName());
		Account.online(account, false);

		try {
			pc.save();
			pc.saveInventory();
		} catch (Exception e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
		_log.info("玩家：" + pc.getName()+" 退出游戏");
	}
}
