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

import java.util.List;

import l1j.server.server.ActionCodes;
import l1j.server.server.ClientThread;
import l1j.server.server.model.L1World; // 防止复制道具
import l1j.server.server.model.Instance.L1DollInstance;
import l1j.server.server.model.Instance.L1ItemInstance;
import l1j.server.server.model.Instance.L1NpcInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.Instance.L1PetInstance;
import l1j.server.server.serverpackets.S_DoActionGFX;
import l1j.server.server.serverpackets.S_DoActionShop;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.serverpackets.S_SystemMessage;
import l1j.server.server.templates.L1PrivateShopBuyList;
import l1j.server.server.templates.L1PrivateShopSellList;
import l1j.william.L1CheckPcItem; // 防止复制道具

// Referenced classes of package l1j.server.server.clientpackets:
// ClientBasePacket

/**
 * 处理收到由客户端传来商店的封包
 */
public class C_Shop extends ClientBasePacket {

	private static final String C_SHOP = "[C] C_Shop";

	public C_Shop(byte abyte0[], ClientThread clientthread) {
		super(abyte0);

		L1PcInstance pc = clientthread.getActiveChar();
		if (pc.isGhost()) {
			return;
		}

		int mapId = pc.getMapId();
		if ((mapId != 340) && (mapId != 350) && (mapId != 360) && (mapId != 4) // 大陆
				&& (mapId != 370)) {
			pc.sendPackets(new S_ServerMessage(876)); // この场所では个人商店を开けません。
			return;
		}

		List<L1PrivateShopSellList> sellList = pc.getSellList();
		List<L1PrivateShopBuyList> buyList = pc.getBuyList();
		L1ItemInstance checkItem;
		boolean tradable = true;

		int type = readC();
		if (type == 0) { // 开始
			int sellTotalCount = readH();
			int sellObjectId;
			int sellPrice;
			int sellCount;
			for (int i = 0; i < sellTotalCount; i++) {
				sellObjectId = readD();

				// 防止复制道具 add
				L1ItemInstance item = pc.getInventory().getItem(sellObjectId);
				L1CheckPcItem checkPcItem = new L1CheckPcItem();
				boolean isCheat = checkPcItem.checkPcItem(item, pc);
				if (isCheat) {
					L1World.getInstance().broadcastServerMessage(
							"\\fZ【" + pc.getName()
										+ "】个人商店贩卖违法道具！(删除违法道具)");
					return;
				}
			   // 防止复制道具  end

				sellPrice = readD();
				sellCount = readD();
				// 检查交易项目
				checkItem = pc.getInventory().getItem(sellObjectId);
				if (!checkItem.getItem().isTradable()) {
					tradable = false;
					pc.sendPackets(new S_ServerMessage(166, // \f1%0が%4%1%3%2
							checkItem.getItem().getName(), "这是不可能处理。"));
				}
				for (L1NpcInstance petNpc : pc.getPetList().values()) {
					if (petNpc instanceof L1PetInstance) {
						L1PetInstance pet = (L1PetInstance) petNpc;
						if (checkItem.getId() == pet.getItemObjId()) {
							tradable = false;
							pc.sendPackets(new S_ServerMessage(166, // \f1%0が%4%1%3%2
									checkItem.getItem().getName(), "这是不可能处理。"));
							break;
						}
					}
				}
				L1PrivateShopSellList pssl = new L1PrivateShopSellList();
				pssl.setItemObjectId(sellObjectId);
				pssl.setSellPrice(sellPrice);
				pssl.setSellTotalCount(sellCount);
				sellList.add(pssl);
			}
			int buyTotalCount = readH();
			int buyObjectId;
			int buyPrice;
			int buyCount;
			for (int i = 0; i < buyTotalCount; i++) {
				buyObjectId = readD();
				buyPrice = readD();
				buyCount = readD();
				// 检查交易项目
				checkItem = pc.getInventory().getItem(buyObjectId);
				if (!checkItem.getItem().isTradable()) {
					tradable = false;
					pc.sendPackets(new S_ServerMessage(166, // \f1%0が%4%1%3%2
							checkItem.getItem().getName(), "这是不可能处理。"));
				}
				if (checkItem.getBless() >= 128) { // 封印的装备
					// \f1%0は舍てたりまたは他人に让ることができません。
					pc.sendPackets(new S_ServerMessage(210, checkItem.getItem()
							.getName()));
					return;
				}

				// 道具天数删除系统(无法放商店) add
				if (checkItem.getDeleteDate() != null) {
					pc.sendPackets(new S_ServerMessage(166, checkItem.getName(), " 无法交易"));
					return;
				}
				// 道具天数删除系统(无法放商店) end

				// 防止异常堆叠交易
				if ((checkItem.getCount() > 1)
						&& (!checkItem.getItem().isStackable())) {
					pc.sendPackets(new S_SystemMessage("此物品非堆叠，但异常堆叠无法交易。"));
					return;
				}

				// 使用中的宠物项链 - 无法贩卖
				for (L1NpcInstance petNpc : pc.getPetList().values()) {
					if (petNpc instanceof L1PetInstance) {
						L1PetInstance pet = (L1PetInstance) petNpc;
						if (checkItem.getId() == pet.getItemObjId()) {
							tradable = false;
							pc.sendPackets(new S_ServerMessage(1187)); // 宠物项链正在使用中。
							break;
						}
					}
				}
				// 使用中的魔法娃娃 - 无法贩卖
				for (L1DollInstance doll : pc.getDollList().values()) {
					if (doll.getItemObjId() == checkItem.getId()) {
						tradable = false;
						pc.sendPackets(new S_ServerMessage(1181));
						break;
					}
				}
				L1PrivateShopBuyList psbl = new L1PrivateShopBuyList();
				psbl.setItemObjectId(buyObjectId);
				psbl.setBuyPrice(buyPrice);
				psbl.setBuyTotalCount(buyCount);
				buyList.add(psbl);
			}
			if (!tradable) { // 如果项目不包括在交易结束零售商
				sellList.clear();
				buyList.clear();
				pc.setPrivateShop(false);
				pc.sendPackets(new S_DoActionGFX(pc.getId(),
						ActionCodes.ACTION_Idle));
				pc.broadcastPacket(new S_DoActionGFX(pc.getId(),
						ActionCodes.ACTION_Idle));
				return;
			}
			byte[] chat = readByte();
			pc.setShopChat(chat);
			pc.setPrivateShop(true);
			pc.sendPackets(new S_DoActionShop(pc.getId(),
					ActionCodes.ACTION_Shop, chat));
			pc.broadcastPacket(new S_DoActionShop(pc.getId(),
					ActionCodes.ACTION_Shop, chat));
		} else if (type == 1) { // 终了
			sellList.clear();
			buyList.clear();
			pc.setPrivateShop(false);
			pc.sendPackets(new S_DoActionGFX(pc.getId(),
					ActionCodes.ACTION_Idle));
			pc.broadcastPacket(new S_DoActionGFX(pc.getId(),
					ActionCodes.ACTION_Idle));
		}
	}

	@Override
	public String getType() {
		return C_SHOP;
	}

}
