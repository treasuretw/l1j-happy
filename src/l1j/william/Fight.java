package l1j.william;

import java.util.List;
import java.util.Iterator;
import l1j.server.Config;
import l1j.server.server.GeneralThreadPool;
import l1j.server.server.datatables.DoorSpawnTable;
import l1j.server.server.model.Instance.L1DoorInstance;
import l1j.server.server.model.Instance.L1ItemInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.Getback;
import l1j.server.server.model.L1Inventory;
import l1j.server.server.model.L1Teleport;
import l1j.server.server.model.L1World;
import l1j.server.server.serverpackets.S_BlueMessage;
import l1j.server.server.serverpackets.S_Paralysis;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.serverpackets.S_SystemMessage;
import l1j.server.server.utils.collections.Lists;

/**
 *	死斗竞技场
 */
public class Fight {

	private List<L1PcInstance> playerListA = Lists.newList();
	private List<L1PcInstance> playerListB = Lists.newList();
	private List<L1PcInstance> playerListC = Lists.newList();
	private List<L1PcInstance> playerListD = Lists.newList();
	private static Fight _instance;
	public static final int STATUS_NONE = 0;
	public static final int STATUS_READY = 1;
	public static final int STATUS_PLAYING = 2;
	public static final int STATUS_CLEANING = 4;
	private static final int Level = 51;
	private static final int Map = 998;
	private static final int maxPlayerA = Config.FightmaxPlayer;
	private static final int maxPlayerB = Config.FightmaxPlayer;
	private static final int minPlayerA = Config.FightminPlayer;
	private static final int minPlayerB = Config.FightminPlayer;
	private static final int readytime = 90*1000;	// 90秒
	private static final int gameTime = 3*60*1000;	// 3分
	private static final int cleartime = 30*60*1000;// 30分
	private static final int _adena = Config.FightMoney;

	private int _FightStatus = 0;

	public static Fight getInstance() {
		if (_instance == null) {
			_instance = new Fight();
		}
		return _instance;
	}

	public void addPlayerList(L1PcInstance pc) {
		if (!this.playerListA.contains(pc)) {
			this.playerListA.add(pc);
		}
		if (!this.playerListB.contains(pc))
			this.playerListB.add(pc);
	}

	public void addPlayerListA(L1PcInstance pc) {
		if (!this.playerListA.contains(pc)) {
			this.playerListA.add(pc);
			pc.getInventory().consumeItem(40308, _adena);
			Fight.this.sendMessage("蓝队人数 " + playerListA.size() +" 人，红队人数 " + playerListB.size() +" 人");
		}
		if ((getMembersCountA() == 1) && (getFightStatus() == 0))
			GeneralThreadPool.getInstance().execute(new runFight());
	}

	public void addPlayerListB(L1PcInstance pc) {
		if (!this.playerListB.contains(pc)) {
			this.playerListB.add(pc);
			Fight.this.sendMessage("蓝队人数 " + playerListA.size() +" 人，红队人数 " + playerListB.size() +" 人");
			pc.getInventory().consumeItem(40308, _adena);
		}
		if ((getMembersCountB() == 1) && (getFightStatus() == 0))
			GeneralThreadPool.getInstance().execute(new runFight());
	}

	public void addPlayerListC(L1PcInstance pc) {
		if (!this.playerListC.contains(pc))
			this.playerListC.add(pc);
	}

	public void addPlayerListD(L1PcInstance pc) {
		if (!this.playerListD.contains(pc))
			this.playerListD.add(pc);
	}

	public void removePlayerList(L1PcInstance pc) {
		if (this.playerListA.contains(pc)) {
			this.playerListA.remove(pc);
			pc.setATeam(false);
		}
		if (this.playerListB.contains(pc)) {
			this.playerListB.remove(pc);
			pc.setBTeam(false);
		}
	}

	public String enterA(L1PcInstance pc) {
		if (pc.getLevel() <= Level) {
			pc.sendPackets(new S_SystemMessage("等级必须"+ Level +"级以上，才可参加死斗竞技。"));
			return "";
		}

		if (getInstance().getFightStatus() == 4) {
			pc.sendPackets(new S_SystemMessage("目前死斗竞技场地正在清洁中，请稍候。"));
			return "";
		}
		if (getInstance().getFightStatus() == 2) {
			pc.sendPackets(new S_SystemMessage("目前死斗竞技已经开始，请等待下一场。"));
			return "";
		}
		if (this.playerListA.size() >= maxPlayerA) {
			pc.sendPackets(new S_SystemMessage("死斗竞技蓝队人数已经额满，请等待下一场。"));
			return "";
		}
		if (!pc.getInventory().checkItem(40308, _adena)) {
			pc.sendPackets(new S_ServerMessage(189));
			return "";
		}
		//L1Teleport.teleport(pc, 32733, 32792, (short) 509, pc.getHeading(), true);
		L1Teleport.teleport(pc, 32744, 32808, (short) Map, 5, true);
		addPlayerListA(pc);
		pc.sendPackets(new S_Paralysis(6, true));// 1:麻痹 5:昏迷 6:双脚受困
		pc.setATeam(true);
		pc.sendPackets(new S_SystemMessage("参加死斗竞技蓝队扣除报名费:金币(" + _adena + ")。"));
		L1ItemInstance item1 = pc.getInventory().storeItem(Config.FightItem, Config.FightCount);
		pc.sendPackets(new S_SystemMessage("死斗竞技优胜可获得"+item1.getName()+"("+Config.FightCount+")。"));
		return "";
	}

	public String enterB(L1PcInstance pc) {
		if (pc.getLevel() <= 52) {
			pc.sendPackets(new S_SystemMessage("等级必须52级以上，才可参加死斗竞技。"));
			return "";
		}

		if (getInstance().getFightStatus() == 4) {
			pc.sendPackets(new S_SystemMessage("目前死斗竞技场地正在清洁中，请稍候。"));
			return "";
		}
		if (getInstance().getFightStatus() == 2) {
			pc.sendPackets(new S_SystemMessage("目前死斗竞技已经开始，请等待下一场。"));
			return "";
		}
		if (this.playerListB.size() >= maxPlayerB) {
			pc.sendPackets(new S_SystemMessage("死斗竞技红队人数已经额满，请等待下一场。"));
			return "";
		}
		if (!pc.getInventory().checkItem(40308, _adena)) {
			pc.sendPackets(new S_ServerMessage(189));
			return "";
		}
		//L1Teleport.teleport(pc, 32743, 32802, (short) 509, pc.getHeading(), true);
		L1Teleport.teleport(pc, 32727, 32791, (short) Map, 5, true);
		addPlayerListB(pc);
		pc.sendPackets(new S_Paralysis(6, true));
		pc.setBTeam(true);
		pc.sendPackets(new S_SystemMessage("参加死斗竞技红队扣除报名费:金币(" + _adena + ")。"));
		L1ItemInstance item1 = pc.getInventory().storeItem(Config.FightItem, Config.FightCount);
		pc.sendPackets(new S_SystemMessage("死斗竞技优胜可获得"+item1.getName()+"("+Config.FightCount+")。"));
		return "";
	}

	// 查看
	public String LookATeam(L1PcInstance pc) {
		for (L1PcInstance tg : this.playerListA) {
			String name = String.valueOf(tg.getName());
			String level = String.valueOf(tg.getLevel());
			//String[] info = new String[] { " 蓝队玩家:" + name ," 等级:" + level };
			//pc.sendPackets(new S_SystemMessage(""+ info + ""));
			pc.sendPackets(new S_SystemMessage("【蓝队玩家】: "+ name + " 【等级】: "+ level +""));
		}
		return "";
	}
	public String LookBTeam(L1PcInstance pc) {
		for (L1PcInstance tg : this.playerListB) {
			String name = String.valueOf(tg.getName());
			String level = String.valueOf(tg.getLevel());
			//String[] info = { " 红队玩家:" + name ," 等级:" + level };
			//pc.sendPackets(new S_SystemMessage(""+ info + ""));
			pc.sendPackets(new S_SystemMessage("【红队玩家】: "+ name + " 【等级】: "+ level +""));
		}
		return "";
	}

	private void Paralysis() {
		for (L1PcInstance pc : this.playerListA) {
			pc.sendPackets(new S_Paralysis(6, false));
		}
		for (L1PcInstance pc : this.playerListB)
			pc.sendPackets(new S_Paralysis(6, false));
	}

	private boolean checkPlayerCount() {
		if (getMembersCountA() != getMembersCountB() || getMembersCountA() < minPlayerA  || getMembersCountB() < minPlayerB  ) {
			setFightStatus(4);
			sendMessage("因一方队伍人数不足，为公平起见，死斗竞技强制结束");

			for (L1PcInstance pc : this.playerListA) {
				pc.setATeam(false);
				pc.getInventory().storeItem(40308, _adena);
				pc.sendPackets(new S_SystemMessage("死斗竞技退还报名费:金币(" + _adena + ")。"));
				pc.sendPackets(new S_Paralysis(6, false));
				int[] loc = Getback.GetBack_Location(pc, true);
				L1Teleport.teleport(pc, loc[0], loc[1], (short) loc[2],5, true);
				//L1Teleport.teleport(pc, 33442, 32797, (short) 4, pc.getHeading(), true);
			}
			for (L1PcInstance pc : this.playerListB) {
				pc.setBTeam(false);
				pc.getInventory().storeItem(40308, _adena);
				pc.sendPackets(new S_SystemMessage("死斗竞技退还报名费:金币(" + _adena + ")。"));
				pc.sendPackets(new S_Paralysis(6, false));
				int[] loc = Getback.GetBack_Location(pc, true);
				L1Teleport.teleport(pc, loc[0], loc[1], (short) loc[2],5, true);
				//L1Teleport.teleport(pc, 33442, 32797, (short) 4, pc.getHeading(), true);
			}
			clearMembers();
			return false;
		}
		return true;
	}

	private void WinTeam() {
		for (L1PcInstance pc : this.playerListA) {
			if ((pc != null) && (!pc.isDead()) && (!pc.isGhost()) && (pc.getMapId() == Map)) {
				addPlayerListC(pc);
			}
		}
		for (L1PcInstance pc : this.playerListB) {
			if ((pc != null) && (!pc.isDead()) && (!pc.isGhost()) && (pc.getMapId() == Map)) {
				addPlayerListD(pc);
			}
		}
		if (this.playerListC.size() == this.playerListD.size()) {
			sendMessage("双方平手");
			for (L1PcInstance pc : this.playerListA) {
				L1ItemInstance item1 = pc.getInventory().storeItem(Config.FightItem, Config.FightCount);
				pc.sendPackets(new S_SystemMessage("双方平手，无人获得"+item1.getName()+"("+Config.FightCount+")。"));
			}
		}
		else if (this.playerListC.size() > this.playerListD.size()) {
			for (L1PcInstance pc : this.playerListA) {
				L1ItemInstance item1 = pc.getInventory().storeItem(Config.FightItem, Config.FightCount);
				sendMessage("恭喜【蓝队】获得死斗竞技优胜，获得"+item1.getName()+"("+Config.FightCount+")");
				pc.sendPackets(new S_SystemMessage("恭喜【蓝队】获得死斗竞技优胜，获得"+item1.getName()+"("+Config.FightCount+")。"));
				//pc.sendPackets(new S_ServerMessage(403, item1.getLogName()));
			}
		}
		else if (this.playerListD.size() > this.playerListC.size()) {
			for (L1PcInstance pc : this.playerListB) {
				L1ItemInstance item1 = pc.getInventory().storeItem(Config.FightItem, Config.FightCount);
				sendMessage("恭喜【红队】获得死斗竞技优胜，获得"+item1.getName()+"("+Config.FightCount+")");
				pc.sendPackets(new S_SystemMessage("恭喜【红队】获得死斗竞技优胜，获得"+item1.getName()+"("+Config.FightCount+")。"));
				//pc.sendPackets(new S_ServerMessage(403, item1.getLogName()));
			}
		}
	}

	private void endFight() {
		setFightStatus(4);
		sendMessage("死斗竞技结束， 下一场将在"+cleartime/60/1000+"分钟后开始");
		for (L1PcInstance pc : this.playerListA) {
			pc.setATeam(false);
     /* if ((pc.getCurrentHp() == 0) && (pc.isDead())) {//复活
          pc.broadcastPacket(new S_SkillSound(pc.getId(), 3944));
          pc.sendPackets(new S_SkillSound(pc.getId(), 3944));
          pc.setTempID(pc.getId());
          pc.sendPackets(new S_Message_YN(322, ""));
        }*/
			int[] loc = Getback.GetBack_Location(pc, true);
			L1Teleport.teleport(pc, loc[0], loc[1], (short) loc[2],5, true);
			//L1Teleport.teleport(pc, 33442, 32797, (short) 4, pc.getHeading(), true);
		}
		for (L1PcInstance pc : this.playerListB) {
			pc.setBTeam(false);
      /*if ((pc.getCurrentHp() == 0) && (pc.isDead())) {//复活
          pc.broadcastPacket(new S_SkillSound(pc.getId(), 3944));
          pc.sendPackets(new S_SkillSound(pc.getId(), 3944));
          pc.setTempID(pc.getId());
          pc.sendPackets(new S_Message_YN(322, ""));
        }*/
			int[] loc = Getback.GetBack_Location(pc, true);
			L1Teleport.teleport(pc, loc[0], loc[1], (short) loc[2],5, true);
			//L1Teleport.teleport(pc, 33442, 32797, (short) 4, pc.getHeading(), true);     
		}
		clearMembers();
		clearColosseum();
	}

	@SuppressWarnings("rawtypes")
	private void clearColosseum() {
		for (Iterator localIterator = L1World.getInstance().getVisibleObjects(4941).values().iterator(); localIterator.hasNext(); ) {
			Object obj = localIterator.next();
			if ((obj instanceof L1Inventory)) {
				L1Inventory inventory = (L1Inventory)obj;
				inventory.clearItems();
			}
		}
	}

	private void sendMessage(String msg) {
		for (L1PcInstance pc : this.playerListA) {
			if (pc.getMapId() == Map) {
				pc.sendPackets(new S_BlueMessage(166, "\\f3" + msg));
			}
		}
		for (L1PcInstance pc : this.playerListB)
			if (pc.getMapId() == Map)
				pc.sendPackets(new S_BlueMessage(166, "\\f3" + msg));
	}

	public void checkFightGame(L1PcInstance pc) {
		if (pc.getMapId() == Map)
			removePlayerList(pc);
	}

	@SuppressWarnings("unused")
	private void setDoorClose(boolean isClose) {
		L1DoorInstance[] list = DoorSpawnTable.getInstance().getDoorList();
		for (L1DoorInstance door : list)
			if (door.getMapId() == Map)
				if (isClose)
					door.close();
				else
					door.open();
	}

	private void setFightStatus(int i) {
		this._FightStatus = i;
	}

	private int getFightStatus() {
		return this._FightStatus;
	}

	private void clearMembers() {
		this.playerListA.clear();
		this.playerListB.clear();
		this.playerListC.clear();
		this.playerListD.clear();
	}

	private int getMembersCountA() {
		return this.playerListA.size();
	}

	private int getMembersCountB() {
		return this.playerListB.size();
	}

	private class runFight
	implements Runnable
	{
		private runFight()
		{
		}

		public void run()
		{
			try
			{
				Fight.this.setFightStatus(1);
				Fight.this.sendMessage("死斗竞技即将在"+ readytime/1000 +"秒后开始");
				Thread.sleep(readytime);
				Fight.this.sendMessage("死斗竞技即将在10秒后开始，目前参加人数 蓝队: " + playerListA.size() +"人 红队: " + playerListB.size() +"人");
				Thread.sleep(5000L);
				Fight.this.sendMessage("死斗竞技即将在5秒后开始，目前参加人数 蓝队: " + playerListA.size() +"人 红队: " + playerListB.size() +"人");
				Thread.sleep(1000L);
				Fight.this.sendMessage("死斗竞技即将在4秒后开始，目前参加人数 蓝队: " + playerListA.size() +"人 红队: " + playerListB.size() +"人");
				Thread.sleep(1000L);
				Fight.this.sendMessage("死斗竞技即将在3秒后开始，目前参加人数 蓝队: " + playerListA.size() +"人 红队: " + playerListB.size() +"人");
				Thread.sleep(1000L);
				Fight.this.sendMessage("死斗竞技即将在2秒后开始，目前参加人数 蓝队: " + playerListA.size() +"人 红队: " + playerListB.size() +"人");
				Thread.sleep(1000L);
				Fight.this.sendMessage("死斗竞技即将在1秒后开始，目前参加人数 蓝队: " + playerListA.size() +"人 红队: " + playerListB.size() +"人");
				Thread.sleep(1000L);

				if (Fight.this.checkPlayerCount()) {
					Fight.this.Paralysis();
					Fight.this.setFightStatus(2);
					Fight.this.sendMessage("死斗竞技开始，请在"+ gameTime/1000/60 +"分钟内将对手打败");
					Thread.sleep(gameTime/3);
					Fight.this.sendMessage("死斗竞技将在"+ gameTime/3*2/1000/60 +"分钟后结束");
					Thread.sleep(gameTime/3);
					Fight.this.sendMessage("死斗竞技将在"+ gameTime/3/1000/60 +"分钟后结束");
					Thread.sleep(gameTime/3);
					Fight.this.sendMessage("死斗竞技即将结束与公布优胜队伍");
					Thread.sleep(5000);
					Fight.this.WinTeam();
					Thread.sleep(5000L);
					Fight.this.sendMessage("死斗竞技将在10秒后关闭");
					Thread.sleep(5000L);
					Fight.this.sendMessage("死斗竞技将在5秒后关闭");
					Thread.sleep(1000L);
					Fight.this.sendMessage("死斗竞技将在4秒后关闭");
					Thread.sleep(1000L);
					Fight.this.sendMessage("死斗竞技将在3秒后关闭");
					Thread.sleep(1000L);
					Fight.this.sendMessage("死斗竞技将在2秒后关闭");
					Thread.sleep(1000L);
					Fight.this.sendMessage("死斗竞技将在1秒后关闭");
					Thread.sleep(1000L);
					Fight.this.endFight();
				}
				Thread.sleep(cleartime);
				Fight.this.setFightStatus(0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
