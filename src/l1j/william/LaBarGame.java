package l1j.william;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;
import l1j.server.L1DatabaseFactory;
import l1j.server.Server;
import l1j.server.server.datatables.ItemTable;
import l1j.server.server.model.Instance.L1ItemInstance;
import l1j.server.server.model.Instance.L1NpcInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.L1World;
import l1j.server.server.serverpackets.S_BlueMessage;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.serverpackets.S_SystemMessage;
import l1j.server.server.templates.L1Item;
import l1j.server.server.utils.Random;

public class LaBarGame {

	private static ArrayList<ArrayList<Object>> aData = new ArrayList<ArrayList<Object>>();
	//private static List<String> aData = Lists.newList();
	private static boolean NO_GET_DATA = false;
	public static final String TOKEN = ",";

	public static void main(String[] a) {

		while (true)
			try
		{
				Server.main(null); continue;
		}
		catch (Exception localException) {
		}
	}

	public static boolean forLaBarGame(String s, L1PcInstance pc, L1NpcInstance npc, int npcid, int oid) {
		ArrayList<Object> aTempData = null;
		if (!NO_GET_DATA) {
			NO_GET_DATA = true;
			getData();
		}

		for (int i = 0; i < aData.size(); i++) {
			aTempData = aData.get(i);
			if ((aTempData.get(0) == null) || (((Integer)aTempData.get(0)).intValue() != npcid) || 
					(!((String)aTempData.get(1)).equals(s)))
				continue;
			if (pc.hasSkillEffect(123456)) {
				int time = pc.getSkillEffectTimeSec(123456);
				pc.sendPackets(new S_SystemMessage("【拉霸机】进行保养中， 下一场将于" + time + " 秒后开始。"));
				return false;
			}

			boolean isCreate = true;

			if ((((Integer)aTempData.get(5)).intValue() != 0) && (((Integer)aTempData.get(6)).intValue() != 0) && 
					(!pc.getInventory().checkItem(((Integer)aTempData.get(5)).intValue(), ((Integer)aTempData.get(6)).intValue()))) {
				L1Item temp = ItemTable.getInstance().getTemplate(((Integer)aTempData.get(5)).intValue());
				pc.sendPackets(new S_ServerMessage(337, temp.getName() + "(" + (((Integer)aTempData.get(6)).intValue() - pc.getInventory().countItems(temp.getItemId())) + ")"));
				isCreate = false;
			}

			if (isCreate) {
				pc.getInventory().consumeItem(((Integer)aTempData.get(5)).intValue(), ((Integer)aTempData.get(6)).intValue());
				L1Item temp = ItemTable.getInstance().getTemplate(((Integer)aTempData.get(5)).intValue());
				pc.sendPackets(new S_SystemMessage("参加【拉霸游戏】 扣除" + temp.getName() + "(" + ((Integer)aTempData.get(6)).intValue() + ")，" +
						"您还剩下"+ temp.getName() + "(" + pc.getInventory().countItems(temp.getItemId())+ ")。"));
			}

			if (((int[])aTempData.get(2) == null) || ((int[])aTempData.get(3) == null) || (!isCreate))
				continue;
			int[] giveItem = (int[])aTempData.get(2);

			for (int j = 0; j < giveItem.length; j++) {
				try {
					L1ItemInstance item1 = ItemTable.getInstance().createItem(giveItem[j]);
					pc.sendPackets(new S_BlueMessage(166, "\\f3 【\\f=" + item1.getName()+"\\f3】\\f9"));
					Thread.sleep(300L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			int[] giveCount = (int[])aTempData.get(3);
			int[] giveEnchantlvl = (int[])aTempData.get(4);
			int rndItem = Random.getInt(giveItem.length);
			int rndCount = Random.getInt(giveCount.length);
			int rndEnchantlvl = Random.getInt(giveEnchantlvl.length);
			int giveItemGet = giveItem[rndItem];
			int giveCountGet = giveCount[rndCount];
			int giveEnchantlvlGet = giveEnchantlvl[rndEnchantlvl];

			L1ItemInstance item = ItemTable.getInstance().createItem(giveItemGet);

			if (item.isStackable())
				item.setCount(giveCountGet);
			else {
				item.setCount(1);
			}
			if ((item.getItem().getType2() == 1)
					|| (item.getItem().getType2() == 2))
				item.setEnchantLevel(giveEnchantlvlGet);
			else {
				item.setEnchantLevel(0);
			}
			if (item != null) {
				if(giveCountGet != 0){ // 抽到物品数量
					if (pc.getInventory().checkAddItem(item, giveCountGet) == 0)
						pc.getInventory().storeItem(item); // 给物品到背包
					else {
						L1World.getInstance().getInventory(pc.getX(), pc.getY(), 
								pc.getMapId()).storeItem(item); // 给物品到地上人物座标
					}
					if ((item.getItem().getType2() == 1) ||  (item.getItem().getType2() == 2)){ // 武器或装备
						pc.sendPackets(new S_BlueMessage(166, "\\f3您抽中了 +" + giveEnchantlvlGet +" " + item.getName() + ""));
						pc.sendPackets(new S_SystemMessage("恭喜您抽中了 +" + giveEnchantlvlGet +" " + item.getName() + ""));
					} else {
						pc.sendPackets(new S_BlueMessage(166, "\\f3您抽中了  " + item.getName() + " " + giveCountGet + " 个"));
						pc.sendPackets(new S_SystemMessage("恭喜您抽中了 " + item.getName() + " " + giveCountGet + " 个"));
					}   	
					if (item.getItem().DropBoard()==1) { // 如果物品 (设置掉落会公告) 则
						L1World.getInstance().broadcastPacketToAll(new S_SystemMessage("\\fX恭喜玩家【 \\fR" + pc.getName() + " \\fX】拉霸游戏抽到【 \\fR" + item.getName() + "\\fX 】。"));
					}
				} else {
					pc.sendPackets(new S_BlueMessage(166, "\\f3很抱歉您没中奖"));
					pc.sendPackets(new S_SystemMessage("很抱歉您没中奖"));
				}
				pc.setSkillEffect(123456, 60000);
			}

		}

		return false;
	}

	private static void getData() {
		Connection con = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			Statement stat = con.createStatement();
			ResultSet rset = stat.executeQuery("SELECT * FROM william_labar_game");
			ArrayList<Object> aReturn = null;
			String sTemp = null;
			if (rset != null)
				while (rset.next()) {
					aReturn = new ArrayList<Object>();
					aReturn.add(0, new Integer(rset.getInt("npcid"))); // NPC 编号
					sTemp = rset.getString("action"); // 对话 action "" (不是对话档名)
					aReturn.add(1, sTemp);

					if ((rset.getString("GiveItem") != null) && (!rset.getString("GiveItem").equals("")) && (!rset.getString("GiveItem").equals("0")))
						aReturn.add(2, getArray(rset.getString("GiveItem"), ",", 1)); // 给予的物品 (可用 , 隔开)
					else {
						aReturn.add(2, null);
					}
					if ((rset.getString("GiveItemCount") != null) && (!rset.getString("GiveItemCount").equals("")) && (!rset.getString("GiveItemCount").equals("0")))
						aReturn.add(3, getArray(rset.getString("GiveItemCount"), ",", 1)); // 给予的物品数量 (可用 , 隔开)
					else {
						aReturn.add(3, null);
					}
					if ((rset.getString("GiveItemEnchantlvl") != null) && (!rset.getString("GiveItemEnchantlvl").equals("")) && (!rset.getString("GiveItemEnchantlvl").equals("0")))
						aReturn.add(4, getArray(rset.getString("GiveItemEnchantlvl"), ",", 1)); // 给予的物品等级 (最少填1,道具不会增加等级)
					else {
						aReturn.add(4, null);
					}
					aReturn.add(5, new Integer(rset.getInt("checkMoney"))); // 检查身上金币
					aReturn.add(6, new Integer(rset.getInt("checkMoneyCount"))); // 检查身上金币数量

					aData.add(aReturn);
				}
			if ((con != null) && (!con.isClosed()))
				con.close();
		} catch (Exception localException) {
		}
	}

	private static Object getArray(String s, String sToken, int iType) {
		StringTokenizer st = new StringTokenizer(s, sToken);
		int iSize = st.countTokens();
		String sTemp = null;

		if (iType == 1) {
			int[] iReturn = new int[iSize];
			for (int i = 0; i < iSize; i++) {
				sTemp = st.nextToken();
				iReturn[i] = Integer.parseInt(sTemp);
			}
			return iReturn;
		}

		if (iType == 2) {
			String[] sReturn = new String[iSize];
			for (int i = 0; i < iSize; i++) {
				sTemp = st.nextToken();
				sReturn[i] = sTemp;
			}
			return sReturn;
		}

		if (iType == 3) {
			String sReturn = null;
			for (int i = 0; i < iSize; i++) {
				sTemp = st.nextToken();
				sReturn = sTemp;
			}
			return sReturn;
		}
		return null;
	}
}
