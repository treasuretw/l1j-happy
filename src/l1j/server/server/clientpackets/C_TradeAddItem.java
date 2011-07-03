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
package l1j.server.server.clientpackets;

import l1j.server.server.ClientThread;
import l1j.server.server.model.L1Inventory;
import l1j.server.server.model.L1Trade;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1DollInstance;
import l1j.server.server.model.Instance.L1ItemInstance;
import l1j.server.server.model.Instance.L1NpcInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.Instance.L1PetInstance;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.william.L1CheckPcItem; // 防止复制道具

// Referenced classes of package l1j.server.server.clientpackets:
// ClientBasePacket

/**
 * 处理收到由客户端传来增加交易物品的封包
 */
public class C_TradeAddItem extends ClientBasePacket {
	private static final String C_TRADE_ADD_ITEM = "[C] C_TradeAddItem";

	public C_TradeAddItem(byte abyte0[], ClientThread client) throws Exception {
		super(abyte0);

		int itemid = readD();
		int itemcount = readD();
		L1PcInstance pc = client.getActiveChar();
		L1Trade trade = new L1Trade();
		L1ItemInstance item = pc.getInventory().getItem(itemid);

		// 防止复制道具 add
		L1CheckPcItem checkPcItem = new L1CheckPcItem();
		boolean isCheat = checkPcItem.checkPcItem(item, pc);
		if (isCheat) {
			L1World.getInstance().broadcastServerMessage(
					"\\fZ【" + pc.getName()
								+ "】交易违法道具！(删除违法道具)");
			return;
		}
	   // 防止复制道具 end 

		if (!item.getItem().isTradable()) {
			pc.sendPackets(new S_ServerMessage(210, item.getItem().getName())); // \f1%0は舍てたりまたは他人に让ることができません。
			return;
		}
		if (item.getBless() >= 128) { // 封印的装备
			// \f1%0は舍てたりまたは他人に让ることができません。
			pc.sendPackets(new S_ServerMessage(210, item.getItem().getName()));
			return;
		}
		// 使用中的宠物项链 - 无法交易
		for (L1NpcInstance petNpc : pc.getPetList().values()) {
			if (petNpc instanceof L1PetInstance) {
				L1PetInstance pet = (L1PetInstance) petNpc;
				if (item.getId() == pet.getItemObjId()) {
					pc.sendPackets(new S_ServerMessage(1187)); // 宠物项链正在使用中。
					return;
				}
			}
		}
		// 使用中的魔法娃娃 - 无法交易
		for (L1DollInstance doll : pc.getDollList().values()) {
			if (doll.getItemObjId() == item.getId()) {
				pc.sendPackets(new S_ServerMessage(1181)); // 这个魔法娃娃目前正在使用中。
				return;
			}
		}

		L1PcInstance tradingPartner = (L1PcInstance) L1World.getInstance()
				.findObject(pc.getTradeID());
		if (tradingPartner == null) {
			return;
		}
		if (pc.getTradeOk()) {
			return;
		}
		if (tradingPartner.getInventory().checkAddItem(item, itemcount) != L1Inventory.OK) { // 检查容量与重量
			tradingPartner.sendPackets(new S_ServerMessage(270)); // \f1持っているものが重くて取引できません。
			pc.sendPackets(new S_ServerMessage(271)); // \f1相手が物を持ちすぎていて取引できません。
			return;
		}

		trade.TradeAddItem(pc, itemid, itemcount);
	}

	@Override
	public String getType() {
		return C_TRADE_ADD_ITEM;
	}
}
