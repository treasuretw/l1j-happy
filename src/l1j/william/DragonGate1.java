package l1j.william;

import java.lang.reflect.Constructor;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import javolution.util.FastTable;

import l1j.server.Config;
import l1j.server.server.ActionCodes;
import l1j.server.server.GeneralThreadPool;
import l1j.server.server.IdFactory;
import l1j.server.server.datatables.NpcTable;
import l1j.server.server.model.L1Inventory;
import l1j.server.server.model.L1Location;
import l1j.server.server.model.L1Object;
import l1j.server.server.model.L1Teleport;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1MonsterInstance;
import l1j.server.server.model.Instance.L1NpcInstance;
import l1j.server.server.model.Instance.L1PcInstance;
//import l1j.server.server.serverpackets.Expand_S_Race;
import l1j.server.server.serverpackets.S_BlueMessage;
import l1j.server.server.serverpackets.S_CharVisualUpdate;
import l1j.server.server.serverpackets.S_MapID;
import l1j.server.server.serverpackets.S_OtherCharPacks;
import l1j.server.server.serverpackets.S_OwnCharPack;
import l1j.server.server.serverpackets.S_Race;
import l1j.server.server.serverpackets.S_RemoveObject;
//import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.serverpackets.S_SystemMessage;
import l1j.server.server.serverpackets.S_Weather;
import l1j.server.server.templates.L1Npc;

/**
 * 副本1 控制项时间轴(测试)
 *
 */

public class DragonGate1 extends TimerTask {

	private static Logger _log = Logger.getLogger(DragonGate1.class.getName());

	private Timer _timeHandler = new Timer(true);

	@SuppressWarnings("unused")
	private static Random _random = new Random();

	// 副本 已开始时间(1/2秒)
	private int _startDragonGateTime = 0;

	public static final int STATUS_NONE = 0;//副本等待中

	public static final int STATUS_PLAYING = 2;//副本进行中 

	public static final int STATUS_CLEANING = 4;//副本维护中 

	private static int WaitTime = Config.GateWaitTime;//副本维护时间

	private static final int maxPlayer = Config.GateMaxPc;//副本进入的玩家上限

	private int _doormap = 0;//记忆副本地图位置

	private L1PcInstance _pc = null;//新定义PC

	private static final int maxLap = 4; //遊戲圈數 最小:1 最大:你高興

	private FastTable<L1PcInstance> playerList = new FastTable<L1PcInstance>();

	//***人数判断部分***	
	//private final ArrayList<L1PcInstance> GatePcList = new ArrayList<L1PcInstance>();
	private FastTable<L1PcInstance> GatePcList = new FastTable<L1PcInstance>();

	public void addPlayerList(L1PcInstance pc) {
		if (!GatePcList.contains(pc)) {
			GatePcList.add(pc);
		}
	}

	private void clearGatePcList() {
		GatePcList.clear();
	}
	//***人数判断部分***	

	//***副本 部分***
	int gateNpcid = 99009;//副本 传送门编号
	L1Npc gatenpc = NpcTable.getInstance().getTemplate(gateNpcid);

	private static DragonGate1 _instance;

	public static DragonGate1 getStart1() {
		if (_instance == null) {
			_instance = new DragonGate1();
		}
		return _instance;
	}

	public void startDragonGate1(L1PcInstance pc) {
		if(getDragonGateStatus() != STATUS_PLAYING || //副本 不是进行中
				getDragonGateStatus() != STATUS_CLEANING) { //副本 不是维护中
			_pc = pc;
			_doormap = _pc.getMapId();//记忆传送门位置
			DragonGate1.getStart1();
			setDragonGateStatus(STATUS_PLAYING);//设定副本进行中
			pc.sendPackets(new S_SystemMessage("\\fW即将开启传送门。"));
		}
		else {
			pc.sendPackets(new S_SystemMessage("\\fW传送门目前无法启动。"));
			return;
		}
	}

	DragonGate1() {
		// 开始执行 时间轴
		_timeHandler.schedule(this, 0, 1000);
		// 交由线程工厂 处理
		GeneralThreadPool.getInstance().execute(this);
	}

	//@Override
	public void run() {
		if(getDragonGateStatus() == STATUS_PLAYING) {//副本进行中
			_startDragonGateTime ++;

			switch (_startDragonGateTime) {
			case 10:// 10秒时
				L1World.getInstance().broadcastServerMessage("\\fW传送门将于30秒之后开启。");
				break;
			case 40:// 副本开启(时间轴开始第1分钟)
				spawnGate();
				break;
			case 50:// 副本开启110秒后，出怪广播
				sendMessage("\\f=怪物即将出现，请做好准备。");
				break;
			case 60:// 副本开启110秒后，出怪广播
				sendMessage("\\f=5秒");
				break;
			case 61:// 副本开启110秒后，出怪广播
				sendMessage("\\f=4秒");
				break;
			case 62:// 副本开启110秒后，出怪广播
				sendMessage("\\f=3秒");
				break;
			case 63:// 副本开启110秒后，出怪广播
				sendMessage("\\f=2秒");
				break;
			case 64:// 副本开启110秒后，出怪广播
				sendMessage("\\f=1秒");
				break;
			case 65:// 出怪广播后，10秒-出怪(时间轴开始第2分钟)

				try {
					spawn(45672);//创造 怪物
					Thread.sleep(5000);//延迟时间
					spawn(45673);
					Thread.sleep(5000);
					spawn(45664);
					Thread.sleep(5000);
					spawn(45653);
					Thread.sleep(10000);
					spawn(45652);
					Thread.sleep(10000);
					spawn(45651);
					Thread.sleep(10000);
					spawn(45650);
					Thread.sleep(10000);
					spawn(45649);
					Thread.sleep(20000);
					spawn(45654);
					Thread.sleep(20000);
					spawn(45646);
					Thread.sleep(30000);
					sendMessage("\\f=安塔瑞斯出现");
					_pc.sendPackets(new S_Race(S_Race.GameStart));//5.4.3.2.1.GO!
					_pc.sendPackets(new S_Race(maxLap, _pc.getLap()));//圈數
					_pc.sendPackets(new S_Race(playerList, _pc));//玩家名單
					spawn(97008);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			break;
			}

		//副本进行多久后关闭(单位分钟)
			if(_startDragonGateTime >= Config.CloseDragonTime*60) {
				sendMessage("\\fW副本任务开启时间已达到极限，即将关闭。");
				isGameOver();
			}
		}
	}

	/**
	 * 清空副本资讯(副本结束)
	 */
	public void DragonDead1() {//怪死亡后关闭
		sendMessage("\\fY副本任务已完成。");
		isGameOver();
	}

	private void closeGate() {//关闭传送门
		for (L1Object obj : L1World.getInstance().getVisibleObjects(_doormap).values()) {
			if (obj instanceof L1NpcInstance) {
				L1NpcInstance Cracknpc = (L1NpcInstance) obj;
				if (Cracknpc.getNpcId()== 99009 ) { //副本传送门
					Cracknpc.deleteMe();
				}
			}
		}
	}

	private void clearMap() {//清除地图中的:玩家、NPC、道具
		//将玩家传送回村庄、Map中所有npc删除
		for(L1PcInstance pc : GatePcList){
			if (pc.isDead()){
				restartPlayer(pc,32616,32782,(short)4);//玩家死亡传送的村庄
			}
			else {
		//		L1Teleport.teleport(pc,32724,32830,(short)208,5,true);//副本结束，传送的地图 预设奇岩
				L1Teleport.teleport(pc,33442,32797,(short)4,5,true);//副本结束，传送的地图 预设奇岩
			}
		}
		for (Object obj : L1World.getInstance().getVisibleObjects(208).values()) {
			if (obj instanceof L1MonsterInstance) {
				L1MonsterInstance mob = (L1MonsterInstance) obj;
				if (!mob.isDead()) {
					mob.setDead(true);
					mob.setStatus(ActionCodes.ACTION_Die);
					mob.setCurrentHpDirect(0);
					mob.deleteMe();
				}
			}
			else if (obj instanceof L1Inventory) {
				L1Inventory inventory = (L1Inventory) obj;
				inventory.clearItems();
			}
		}
	}

	//副本 传送
	private void spawnGate() {//创造传送门
		L1Location crack = null; //副本所在地传送坐标控制
		L1Location crack_loc = null; //指定村庄传送坐标控制
		//crack = new L1Location(32796, 32691, 1005); //点击门后传送到的坐标
		crack = new L1Location(32724, 32830, 208); //点击门后传送到的坐标
		crack_loc = new L1Location(_pc.getX()+2, _pc.getY()+2, _pc.getMapId());//取得人物座标
		L1World.getInstance().broadcastServerMessage("\\fT传送门已开启完毕。");		
		//村庄到副本
		createGate(crack_loc.getX(), crack_loc.getY(), (short) crack_loc.getMapId(), crack.getX(), crack.getY(), (short) crack.getMapId());
	}

	private void createGate(int x, int y, short mapId, int to_x, int to_y, short to_mapId) {
		try {
			//gatenpc 采用四门随机
			if (gatenpc == null) {
				return;
			}
			String s = gatenpc.getImpl();
			Constructor<?> constructor = Class.forName("l1j.server.server.model.Instance." + s + "Instance").getConstructors()[0];
			Object aobj[] = { gatenpc };
			L1NpcInstance npc = (L1NpcInstance) constructor.newInstance(aobj);

			npc.setId(IdFactory.getInstance().nextId());
			npc.setX(x);
			npc.setY(y);
			npc.setMap(mapId);
			npc.setHomeX(npc.getX());
			npc.setHomeY(npc.getY());
			npc.setHeading(0);

			L1World.getInstance().storeObject(npc);
			L1World.getInstance().addVisibleObject(npc);

			Teleport teleport = new Teleport(npc, to_x, to_y, to_mapId);
			GeneralThreadPool.getInstance().execute(teleport);			
		}
		catch (Exception e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
	}

	private void spawn(int npcid) {//创造怪物
		try {
			L1NpcInstance npc = NpcTable.getInstance().newNpcInstance(npcid);
			npc.setId(IdFactory.getInstance().nextId());

			//副本坐标锁定
			npc.setMap((short)208);
			npc.setX(32724);
			npc.setY(32830);

			Thread.sleep(1);
			npc.setHomeX(npc.getX());
			npc.setHomeY(npc.getY());
			npc.setHeading(4);
			L1World.getInstance().storeObject(npc);
			L1World.getInstance().addVisibleObject(npc);
			npc.turnOnOffLight();
			npc.startChat(L1NpcInstance.CHAT_TIMING_APPEARANCE);
		}
		catch (Exception e) {
		}
	}

	public void restartPlayer(L1PcInstance pc, int locx, int locy, short mapid) {//移除玩家
		pc.removeAllKnownObjects();
		pc.broadcastPacket(new S_RemoveObject(pc));
		pc.setCurrentHp(pc.getLevel());
		pc.set_food(40);
		pc.setDead(false);
		pc.setStatus(0);
		L1World.getInstance().moveVisibleObject(pc, mapid);
		pc.setX(locx);
		pc.setY(locy);
		pc.setMap((short) mapid);
		pc.sendPackets(new S_MapID(pc.getMapId(), pc.getMap().isUnderwater()));
		pc.broadcastPacket(new S_OtherCharPacks(pc));
		pc.sendPackets(new S_OwnCharPack(pc));
		pc.sendPackets(new S_CharVisualUpdate(pc));
		pc.startHpRegeneration();
		pc.startMpRegeneration();
		pc.sendPackets(new S_Weather(L1World.getInstance().getWeather()));
		pc.stopPcDeleteTimer();
		if (pc.getHellTime() > 0) {
			pc.beginHell(false);
		}
	}

	class Teleport implements Runnable {
		private L1NpcInstance _npc = null;
		private int _to_x = 0;
		private int _to_y = 0;
		private short _to_mapId = 0;

		public Teleport(L1NpcInstance npc, int to_x, int to_y, short to_mapId) {
			_npc = npc;
			_to_x = to_x;
			_to_y = to_y;
			_to_mapId = to_mapId;
		}

		public void run() {
			try {
				Thread.sleep(1000);
				for (;;) {
					if (_npc._destroyed) {
						return;
					}

					for (L1Object obj : L1World.getInstance().getVisiblePoint(_npc.getLocation(), 1)) {
						if (obj instanceof L1PcInstance) {
							L1PcInstance target = (L1PcInstance) obj;

							if (GatePcList.size() >= maxPlayer) { //判断已传送的人数
								target.sendPackets(new S_SystemMessage("副本人数已达上限。"));
								return;
							}
							//}else{
							L1Location tmp_loc = new L1Location(_to_x, _to_y, _to_mapId);
							L1Location rnd_loc = tmp_loc.randomLocation(1, 5, false);
							addPlayerList(target);
							L1Teleport.teleport(target, rnd_loc.getX(), rnd_loc.getY(), (short) rnd_loc.getMapId(), target.getHeading(), true);					
							//}
						}
					}
					Thread.sleep(1000);
				}
			}
			catch (Exception e) {
				_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			}
		}
	}

	//private int _DragonGateStatus = STATUS_NONE; 
	private static int _DragonGateStatus = STATUS_NONE;

	/*回传目前状态*/
	public static int getStatus1 = 0;
	public static int getStatus1() {
		getStatus1 = _DragonGateStatus;
		return getStatus1;
	}
	/*回传目前状态*/
	private void setDragonGateStatus(int i) {
		_DragonGateStatus = i;
	}

	private int getDragonGateStatus() {
		return _DragonGateStatus;
	}

	private void sendMessage(String msg) {
		for (L1PcInstance pc : GatePcList) {
			if (pc.getMapId() == 208) {
				pc.sendPackets(new S_BlueMessage(166, msg));
			}
		}
	}
	//副本维护
	private void startWaitTime() {
		setDragonGateStatus(STATUS_CLEANING);//副本设定为维护中
		_startDragonGateTime = 0;
		new WaitTime().begin();
	}

	public class WaitTime extends TimerTask {
		//@Override
		public void run() {
			setDragonGateStatus(STATUS_NONE);
			this.cancel();
		}
		public void begin() {
			Timer timer = new Timer();
			timer.schedule(this,WaitTime*1000*60);//维护的时间(单位分钟)
		}
	}

	//副本关闭
	private void isGameOver() {
		new isOver().begin();
	}

	public class isOver extends TimerTask {
		//@Override
		public void run() {
			if(_startDragonGateTime < 7200*2) {
				closeGate();//关闭传送门
			}
			clearMap();//清除地图中的:玩家、NPC、道具
			clearGatePcList();//清除地图人数
			startWaitTime();//副本维护时间开始		
			this.cancel();
		}
		public void begin() {
			Timer timer = new Timer();
			timer.schedule(this, 3000);//1秒
		}
	}

}
