package l1j.william;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;
import java.util.logging.Level;

import l1j.server.Config;
//import l1j.server.server.ActionCodes;
import l1j.server.server.GeneralThreadPool;
import l1j.server.server.IdFactory;
import l1j.server.server.datatables.NpcTable;
//import l1j.server.server.model.L1CastleLocation;
//import l1j.server.server.model.L1Inventory;
import l1j.server.server.model.L1World;
import l1j.server.server.model.L1Location;
import l1j.server.server.model.L1Object;
import l1j.server.server.model.L1Teleport;
//import l1j.server.server.model.Instance.L1MonsterInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.Instance.L1NpcInstance;
import l1j.server.server.serverpackets.S_CharVisualUpdate;
import l1j.server.server.serverpackets.S_MapID;
import l1j.server.server.serverpackets.S_OtherCharPacks;
import l1j.server.server.serverpackets.S_OwnCharPack;
import l1j.server.server.serverpackets.S_RemoveObject;
import l1j.server.server.serverpackets.S_Weather;
import l1j.server.server.templates.L1Npc;
import l1j.william.L1WilliamSystemMessage;

/**
 * 时空裂痕 控制项与时间轴
 *
 */
public class CrackTime extends TimerTask {

	private static Logger _log = Logger.getLogger(CrackTime.class.getName());

	private Timer _timeHandler = new Timer(true);

	private static Random _random = new Random();

	@SuppressWarnings("unused")
	private ArrayList<L1PcInstance> playerList = new ArrayList<L1PcInstance>();

	private boolean _isOver = false;

	private static int WaitTime = Config.CrackStartTime;

	// 时空裂痕开始时间(1/2秒)
	private int _startTime = 0;

	private static final int[][] _crack = {
		{ 32639, 32876, 780 },	//底比斯
		{ 32794, 32751, 783 }	//提卡尔
	};

	private static final int[][] _crackLoc = {
		{ 32728, 32709, 4 }, { 32848, 32639, 4 }, { 32852, 32705, 4 },	// 邪恶神殿
		{ 32913, 33168, 4 }, { 32957, 33247, 4 }, { 32913, 33425, 4 },	// 沙漠绿洲
		{ 34255, 33203, 4 }, { 34232, 33312, 4 }, { 34276, 33359, 4 }	// 黄昏山脉
	};

	private static CrackTime _instance;

	public static CrackTime getStart() {
		if (_instance == null) {
			_instance = new CrackTime();
		}
		return _instance;
	}

	public void startCrackTime() {
		CrackTime.getStart();
	}

	private CrackTime() {
		// 开始执行时间轴
		_timeHandler.schedule(this, 0, 1000);
		// 交给线程处理
		GeneralThreadPool.getInstance().execute(this);
	}

	@Override
	public void run() {
		// 时空裂痕结束
		if(!_isOver) {
			_startTime ++;
			switch (_startTime) {
			case 60:// 时间轴开始1分钟
				String name = "";
				name = L1WilliamSystemMessage.ShowMessage(1557);
				L1World.getInstance().broadcastServerMessage(""+name+"");//fY时空裂痕即将开启.....
				break;
			case 120:// 2分钟后开启时空裂痕
				spawnCrack();
				break;
			}
			//时空裂痕开启后多久结束(单位分钟60) 
			if (_startTime >= Config.CrackOpenTime*60) {//时间到后 关闭
				clear();
				startWaitTime();
			}
		}
	}
	/**
	 * 清空时空裂痕资讯(时空裂痕结束)
	 */
	private void clear() {
		_startTime = 0;
		String over = "";
		over = L1WilliamSystemMessage.ShowMessage(1560);
		L1World.getInstance().broadcastServerMessage(""+over+"");//时空裂痕关闭了。
		for (L1Object obj : L1World.getInstance().getAllNpcs()) {
			if (obj instanceof L1NpcInstance) {
				L1NpcInstance Cracknpc = (L1NpcInstance) obj;
				if (Cracknpc.getNpcId()== 71254) {
					Cracknpc.deleteMe();
				}
			}
		}
		for(L1PcInstance pc : L1World.getInstance().getAllPlayers()) {
			if (pc.getMapId()==780 || pc.getMapId()==783) {
				if (pc.isDead()) {
					restartPlayer(pc,32616,32782,(short)4);
				}
				else {
					L1Teleport.teleport(pc,33442,32797,(short)4,5,true);//结束后传送玩家到指定地图(预设奇岩城)
				}
			}
		}
	}

	private void spawnCrack() {
		L1Location crack = null;
		L1Location crack_loc = null;
		int rnd1 = _random.nextInt(2);
		int rnd2 = _random.nextInt(9);
		crack = new L1Location(_crack[rnd1][0], _crack[rnd1][1], _crack[rnd1][2]);
		crack_loc = new L1Location(_crackLoc[rnd2][0], _crackLoc[rnd2][1], _crackLoc[rnd2][2]);
		String msg = "";
		String name = "";
		String name1 = "";
		name = L1WilliamSystemMessage.ShowMessage(1558);	//时空裂痕在
		name1 = L1WilliamSystemMessage.ShowMessage(1559);	// 开启!!!异界侵略即将开始...
		if (crack_loc.getX() == 32728 && crack_loc.getY() == 32709 ||
				crack_loc.getX() == 32848 && crack_loc.getY() == 32639 ||
				crack_loc.getX() == 32852 && crack_loc.getY() == 32705)	//远古战场
			msg = "远古战场";
		else if (crack_loc.getX() == 32913 && crack_loc.getY() == 33168 ||
				crack_loc.getX() == 32957 && crack_loc.getY() == 33247 ||
				crack_loc.getX() == 32913 && crack_loc.getY() == 33425)	//沙漠绿洲
			msg = "沙漠绿洲";
		else if (crack_loc.getX() == 34255 && crack_loc.getY() == 33203 ||
				crack_loc.getX() == 34232 && crack_loc.getY() == 33312 ||
				crack_loc.getX() == 34276 && crack_loc.getY() == 33359)	//黄昏山脉
			msg = "黄昏山脉";

		L1World.getInstance().broadcastServerMessage(""+name+"【"+msg+"】"+name1+"");
		createCrack(crack.getX(), crack.getY(), (short) crack.getMapId(), crack_loc.getX(), crack_loc.getY(), (short) crack_loc.getMapId());
		createCrack(crack_loc.getX(), crack_loc.getY(), (short) crack_loc.getMapId(), crack.getX(), crack.getY(), (short) crack.getMapId());
	}

	private void createCrack(int x, int y, short mapId, int to_x, int to_y, short to_mapId) {
		try {
			L1Npc l1npc = NpcTable.getInstance().getTemplate(71254);

			if (l1npc == null) {
				return;
			}

			String s = l1npc.getImpl();
			Constructor<?> constructor = Class.forName("l1j.server.server.model.Instance." + s + "Instance").getConstructors()[0];
			Object aobj[] = { l1npc };
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
		} catch (Exception e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
	}

	public void restartPlayer(L1PcInstance pc, int locx, int locy, short mapid){ //移除玩家
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

	private void setIsOver() {
		if(_isOver){
			_isOver = false;
		}
		else {
			_isOver =true;
		}
	}

	private void startWaitTime() {
		setIsOver();
		//_isOver = true;
		new WaitTime().begin();
	}

	private class WaitTime extends TimerTask {
		//@Override
		public void run() {
			setIsOver();
			//_isOver = false;
			this.cancel();
		}
		public void begin() {
			Timer timer = new Timer();
			timer.schedule(this,WaitTime*1000*60);	//维护的时间(单位分钟)
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
							L1Location tmp_loc = new L1Location(_to_x, _to_y, _to_mapId);
							L1Location rnd_loc = tmp_loc.randomLocation(1, 5, false);
							L1Teleport.teleport(target, rnd_loc.getX(), rnd_loc.getY(), (short) rnd_loc.getMapId(), target.getHeading(), true);
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
}
