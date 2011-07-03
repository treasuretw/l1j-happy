package l1j.william;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import l1j.server.Config;
import l1j.server.L1DatabaseFactory;
import l1j.server.server.ActionCodes;
import l1j.server.server.GeneralThreadPool;
import l1j.server.server.IdFactory;
import l1j.server.server.datatables.NpcTable;
import l1j.server.server.model.L1Inventory;
import l1j.server.server.model.L1Teleport;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1MonsterInstance;
import l1j.server.server.model.Instance.L1NpcInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_BlueMessage;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.serverpackets.S_SystemMessage;
import l1j.server.server.utils.SQLUtil;

/**
 *  boss馆 by 431200
 *
 *	游戏开始后 中途死亡离开boss馆还可以再找NPC进入
 *
 *	游戏开始后 还没参加过的玩家不可进入
 */

public class BossRoom {

	private final ArrayList<L1PcInstance> _members = new ArrayList<L1PcInstance>();

	private static BossRoom _instance;

	public static BossRoom getInstance() {
		if (_instance == null) {
			_instance = new BossRoom();
		}
		return _instance;
	}

	public static final int STATUS_NONE = 0; //闲置
	public static final int STATUS_READY = 1; //等待中
	public static final int STATUS_PLAYING = 2; //游戏开始 
	public static final int STATUS_CLEANING = 4; //清洁中

	private static final int _minPlayer = Config.minPlayer; //最少玩家数
	private static final int _maxPlayer = Config.maxPlayer; //最多玩家数

	private static final int _readytime = 80 * 1000; //等待时间 80秒 + 倒数 = 总共90秒
	private static final int _cleartime = Config.cleartime * 60000; //清洁时间 (单位分钟)

	// 怪物编号
	private static final int _bossId1 = Config.bossId1; //火焰领主 
	private static final int _bossId2 = Config.bossId2; //冰霜君主 
	private static final int _bossId3 = Config.bossId3; //噩梦君主
	private static final int _bossId4 = Config.bossId4; //蛮牛暴君 
	private static final int _bossId5 = Config.bossId5; //噬血暴君 
	private static final int _bossId6 = Config.bossId6; //独眼暴君
	private static final int _bossId7 = Config.bossId7; //不朽暴君
	private static final int _bossId8 = Config.bossId8; //幻影魔豹
	private static final int _bossId9 = Config.bossId9; //血族亲王 
	private static final int _bossId10 = Config.bossId10; //半神强者吉尔塔斯 

	//private static final int ADENA = 1000000; //扣除金币数量 

	public String enterBossRoom(L1PcInstance pc) {

		if (BossRoom.getInstance().getBossRoomStatus() == BossRoom.STATUS_CLEANING) {
			pc.sendPackets(new S_SystemMessage("目前BOSS馆正在清洁中。"));
			return "";
		}
		if (BossRoom.getInstance().getBossRoomStatus() == BossRoom.STATUS_PLAYING
				&& !isMember(pc)) {
			pc.sendPackets(new S_ServerMessage(1182)); //游戏已经开始了。 
			return "";
		}

		if (getMembersCount() >= _maxPlayer && !isMember(pc)) {
			pc.sendPackets(new S_SystemMessage("BOSS馆已经到达饱和的状态了。"));
			return "";
		}

		if (!pc.getInventory().checkItem(Config.itemId,Config.Quantity) && !isMember(pc)){
			pc.sendPackets(new S_SystemMessage("需要在线箱子"+Config.Quantity+"个.")); //需要的道具不足 
			return "";
		}

		// 传送到的坐标
		L1Teleport.teleport(pc, Config.mapIdx, Config.mapIdy, (short) Config.mapId, pc.getHeading(), true);
		addMember(pc);
		return "";
	}

	private void addMember(L1PcInstance pc) {
		if (!_members.contains(pc)) {
			_members.add(pc);
			pc.getInventory().consumeItem(Config.itemId,Config.Quantity);
		}
		if (getMembersCount() == 1 && getBossRoomStatus() == STATUS_NONE) {
			GeneralThreadPool.getInstance().execute(new runBossRoom());
		}
	}

	private class runBossRoom implements Runnable {

		public void run() {

			try {
				setBossRoomStatus(STATUS_READY);
				sendMessage("90秒后开始游戏");
				Thread.sleep(_readytime);

				sendMessage("倒数10秒");
				Thread.sleep(5 * 1000);

				sendMessage("倒数5秒");
				Thread.sleep(1000);

				sendMessage("4秒");
				Thread.sleep(1000);

				sendMessage("3秒");
				Thread.sleep(1000);

				sendMessage("2秒");
				Thread.sleep(1000);

				sendMessage("1秒");
				Thread.sleep(1000);

				if (checkPlayerCount()) {

					setBossRoomStatus(STATUS_PLAYING);
					spawnBoss(_bossId1,"1","2");
					Thread.sleep(2 * 60000);

					spawnBoss(_bossId2,"2","3");
					Thread.sleep(2 * 60000);

					spawnBoss(_bossId3,"3","4");
					Thread.sleep(2 * 60000);

					spawnBoss(_bossId4,"4","5");
					Thread.sleep(2 * 60000);

					spawnBoss(_bossId5,"5","6");
					Thread.sleep(2 * 60000);

					spawnBoss(_bossId6,"6","7");
					Thread.sleep(2 * 60000);

					spawnBoss(_bossId7,"7","8");
					Thread.sleep(2 * 60000);

					spawnBoss(_bossId8,"8","9");
					Thread.sleep(2 * 60000);

					spawnBoss(_bossId9,"9","10");
					Thread.sleep(5 * 60000); 

					spawnBoss(_bossId10,"10",null);
					Thread.sleep(10 * 60000);

					endBossRoom();
				}

				Thread.sleep(_cleartime);

				setBossRoomStatus(STATUS_NONE);

			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private boolean checkPlayerCount() {
		if (getMembersCount() < _minPlayer) {
			setBossRoomStatus(STATUS_CLEANING); 
			sendMessage("人数不足 "+_minPlayer+" 人，所以强制关闭游戏");
			for (L1PcInstance pc : getMembersArray()) {
				pc.getInventory().storeItem(Config.itemId,Config.Quantity);
				pc.sendPackets(new S_SystemMessage("BOSS馆退还箱子("+Config.Quantity+")个."));
				L1Teleport.teleport(pc, Config.mapIdx1, Config.mapIdy1, (short) Config.mapId1, pc.getHeading(), true);
			}
			clearMembers();
			return false;
		}
		return true;
	}

	private void endBossRoom() {
		setBossRoomStatus(STATUS_CLEANING);
		sendMessage("BOSS馆游戏结束，请下次再来");
		for (L1PcInstance pc : getMembersArray()) {
			L1Teleport.teleport(pc, Config.mapIdx1, Config.mapIdy1, (short) Config.mapId1, pc.getHeading(), true);
		}
		clearMembers();
		clearColosseum();
	}

	private void clearColosseum() {
		for (Object obj : L1World.getInstance().getVisibleObjects(Config.mapId).values()) {
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

	private void spawnBoss(int npcid, String msg1, String msg2) {
		if (msg1.equalsIgnoreCase("9")){
			sendMessage("第 "+msg1+" 关 ["+ getBossName(npcid) +"] 5分后开始第 "+msg2+" 关");
		}
		else if (msg1.equalsIgnoreCase("10")){
			sendMessage("最后一关 ["+ getBossName(npcid) +"] 请努力撑下去，20分钟后结束BOSS馆");
		}
		else {
			sendMessage("第 "+msg1+" 关 ["+ getBossName(npcid) +"] 2分后开始第 "+msg2+" 关");
		}
		spawn(npcid);
	}

	private void spawn(int npcid) {
		try {
			L1NpcInstance npc = NpcTable.getInstance().newNpcInstance(npcid);
			npc.setId(IdFactory.getInstance().nextId());
			npc.setMap((short)Config.mapId);
			npc.setX(Config.mapIdx);
			npc.setY(Config.mapIdy);
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

	private String getBossName(int npcId) {
		String BossName = null;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement("select name from npc where npcid = ?");
			pstm.setInt(1, npcId);
			rs = pstm.executeQuery();
			if (rs.next()) {
				BossName = rs.getString("name");
			}
		}
		catch (SQLException e) {
		} finally {
			SQLUtil.close(rs);
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
		return BossName;
	}

	private void sendMessage(String msg) {
		for (L1PcInstance pc : getMembersArray()) {
			if (pc.getMapId() == Config.mapId){
				pc.sendPackets(new S_BlueMessage(166,"\\f3" + msg)); // 夜小空 创意广播 
			}
		}
	}

	private int _BossRoomStatus = STATUS_NONE;

	private void setBossRoomStatus(int i) {
		_BossRoomStatus = i;
	}

	private int getBossRoomStatus() {
		return _BossRoomStatus;
	}

	private void clearMembers() {
		_members.clear();
	}

	private boolean isMember(L1PcInstance pc) {
		return _members.contains(pc);
	}

	private L1PcInstance[] getMembersArray() {
		return _members.toArray(new L1PcInstance[_members.size()]);
	}

	private int getMembersCount() {
		return _members.size();
	}

}
