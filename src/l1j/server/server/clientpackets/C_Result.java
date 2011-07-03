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

import l1j.william.L1CheckPcItem; // 防止复制道具
import l1j.server.server.ClientThread;
import l1j.server.server.datatables.NpcTable;
import l1j.server.server.datatables.PetTable;
import l1j.server.server.datatables.ShopTable;
import l1j.server.server.model.L1Clan;
import l1j.server.server.model.L1Inventory;
import l1j.server.server.model.L1Object;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1DollInstance;
import l1j.server.server.model.Instance.L1ItemInstance;
import l1j.server.server.model.Instance.L1NpcInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.Instance.L1PetInstance;
import l1j.server.server.model.identity.L1ItemId;
import l1j.server.server.model.shop.L1Shop;
import l1j.server.server.model.shop.L1ShopBuyOrderList;
import l1j.server.server.model.shop.L1ShopSellOrderList;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.templates.L1Npc;
import l1j.server.server.templates.L1Pet;
import l1j.server.server.templates.L1PrivateShopBuyList;
import l1j.server.server.templates.L1PrivateShopSellList;

/**
 * TODO 翻译，好多 处理收到由客户端传来取得结果的封包
 */
public class C_Result extends ClientBasePacket {

	private static final String C_RESULT = "[C] C_Result";

	public C_Result(byte abyte0[], ClientThread clientthread) throws Exception {
		super(abyte0);
		int npcObjectId = readD();
		int resultType = readC();
		int size = readH();

		L1PcInstance pc = clientthread.getActiveChar();
		int level = pc.getLevel();

		int npcId = 0;
		String npcImpl = "";
		boolean isPrivateShop = false;
		boolean tradable = true;
		L1Object findObject = L1World.getInstance().findObject(npcObjectId);
		if (findObject != null) {
			int diffLocX = Math.abs(pc.getX() - findObject.getX());
			int diffLocY = Math.abs(pc.getY() - findObject.getY());
			// 3格以上的距离视为无效要求
			if ((diffLocX > 3) || (diffLocY > 3)) {
				return;
			}
			if (findObject instanceof L1NpcInstance) {
				L1NpcInstance targetNpc = (L1NpcInstance) findObject;
				npcId = targetNpc.getNpcTemplate().get_npcId();
				npcImpl = targetNpc.getNpcTemplate().getImpl();
			} else if (findObject instanceof L1PcInstance) {
				isPrivateShop = true;
			}
		}

		if ((resultType == 0) && (size != 0)
				&& npcImpl.equalsIgnoreCase("L1Merchant")) { // 买道具
			L1Shop shop = ShopTable.getInstance().get(npcId);
			L1ShopBuyOrderList orderList = shop.newBuyOrderList();
			for (int i = 0; i < size; i++) {
				orderList.add(readD(), readD());
			}
			shop.sellItems(pc, orderList);
		} else if ((resultType == 1) && (size != 0)
				&& npcImpl.equalsIgnoreCase("L1Merchant")) { // 卖道具

			// 防止复制道具 add
			@SuppressWarnings("unused")
			int objectId, count;
			L1ItemInstance item;
			@SuppressWarnings("unused")
			long totalPrice = 0;
			// 防止复制道具 end

			L1Shop shop = ShopTable.getInstance().get(npcId);
			L1ShopSellOrderList orderList = shop.newSellOrderList(pc);
			for (int i = 0; i < size; i++) {
				orderList.add(readD(), readD());

				// 防止复制道具 add
				objectId = readD();
				count = readD();
				item = pc.getInventory().getItem(objectId);
				if (item == null) {
					continue;
				}
				if (item.isEquipped()) {
					continue;
				}

				L1CheckPcItem checkPcItem = new L1CheckPcItem();
				boolean isCheat = checkPcItem.checkPcItem(item, pc);
				if (isCheat) {
					L1World.getInstance().broadcastServerMessage(
							"\\fZ【" + pc.getName()
			    		   				+ "】贩卖违法道具！(删除违法道具)");
					return;
				}
			   // 防止复制道具  end
			}
			shop.buyItems(orderList);
		} else if ((resultType == 2) && (size != 0)
				&& npcImpl.equalsIgnoreCase("L1Dwarf") && (level >= 5)) { // 自己的仓库
			int objectId, count;
			for (int i = 0; i < size; i++) {
				tradable = true;
				objectId = readD();
				count = readD();
				L1Object object = pc.getInventory().getItem(objectId);
				L1ItemInstance item = (L1ItemInstance) object;

				// 防止复制道具 add
				L1CheckPcItem checkPcItem = new L1CheckPcItem();
				boolean isCheat = checkPcItem.checkPcItem(item, pc);
				if (isCheat) {
					L1World.getInstance().broadcastServerMessage(
							"\\fZ【" + pc.getName()
										+ "】存违法道具到仓库！(刪除违法道具)");
					return;
				}
			    // 防止复制道具 end

				if (!item.getItem().isTradable()) {
					tradable = false;
					pc.sendPackets(new S_ServerMessage(210, item.getItem()
							.getName())); // \f1%0は舍てたりまたは他人に让ることができません。
				}
				for (L1NpcInstance petNpc : pc.getPetList().values()) {
					if (petNpc instanceof L1PetInstance) {
						L1PetInstance pet = (L1PetInstance) petNpc;
						if (item.getId() == pet.getItemObjId()) {
							tradable = false;
							// \f1%0は舍てたりまたは他人に让ることができません。
							pc.sendPackets(new S_ServerMessage(210, item
									.getItem().getName()));
							break;
						}
					}
				}
				for (L1DollInstance doll : pc.getDollList().values()) {
					if (item.getId() == doll.getItemObjId()) {
						tradable = false;
						pc.sendPackets(new S_ServerMessage(1181)); // 该当のマジックドールは现在使用中です。
						break;
					}
				}
				if (pc.getDwarfInventory().checkAddItemToWarehouse(item, count,
						L1Inventory.WAREHOUSE_TYPE_PERSONAL) == L1Inventory.SIZE_OVER) {
					pc.sendPackets(new S_ServerMessage(75)); // \f1これ以上ものを置く场所がありません。
					break;
				}
				if (tradable) {
					pc.getInventory().tradeItem(objectId, count,
							pc.getDwarfInventory());
					pc.turnOnOffLight();
				}
			}

			// 强制储存一次身上道具, 避免角色背包内的物品未正常写入导致物品复制的问题
			pc.saveInventory();
		} else if ((resultType == 3) && (size != 0)
				&& npcImpl.equalsIgnoreCase("L1Dwarf") && (level >= 5)) { // 从仓库取出东西
			int objectId, count;
			L1ItemInstance item;
			for (int i = 0; i < size; i++) {
				objectId = readD();
				count = readD();
				item = pc.getDwarfInventory().getItem(objectId);

				// 防止复制道具 add
				L1CheckPcItem checkPcItem = new L1CheckPcItem();
				boolean isCheat = checkPcItem.checkPcItem(item, pc);
				if (isCheat) {
					L1World.getInstance().broadcastServerMessage(
							"\\fZ【" + pc.getName()
										+ "】从仓库取出违法道具！(删除违法道具)");
					return;
				}
			   // 防止复制道具 end

				if (pc.getInventory().checkAddItem(item, count) == L1Inventory.OK) // 检查重量与容量
				{
					if (pc.getInventory().consumeItem(L1ItemId.ADENA, 30)) {
						pc.getDwarfInventory().tradeItem(item, count,
								pc.getInventory());
					} else {
						pc.sendPackets(new S_ServerMessage(189)); // \f1アデナが不足しています。
						break;
					}
				} else {
					pc.sendPackets(new S_ServerMessage(270)); // \f1持っているものが重くて取引できません。
					break;
				}
			}
		} else if ((resultType == 4) && (size != 0)
				&& npcImpl.equalsIgnoreCase("L1Dwarf") && (level >= 5)) { // 储存道具到仓库
			int objectId, count;
			if (pc.getClanid() != 0) { // 有血盟
				for (int i = 0; i < size; i++) {
					tradable = true;
					objectId = readD();
					count = readD();
					L1Clan clan = L1World.getInstance().getClan(
							pc.getClanname());
					L1Object object = pc.getInventory().getItem(objectId);
					L1ItemInstance item = (L1ItemInstance) object;

					// 防止复制道具 add
					L1CheckPcItem checkPcItem = new L1CheckPcItem();
					boolean isCheat = checkPcItem.checkPcItem(item, pc);
					if (isCheat) {
						L1World.getInstance().broadcastServerMessage(
								"\\fZ【" + pc.getName()
											+ "】存违法道具到盟仓！(删除违法道具)");
						return;
					}
				   // 防止复制道具 end

					if (clan != null) {
						if (!item.getItem().isTradable()) {
							tradable = false;
							pc.sendPackets(new S_ServerMessage(210, item
									.getItem().getName())); // \f1%0は舍てたりまたは他人に让ることができません。
						}
						if (item.getBless() >= 128) { // 被封印的装备
							tradable = false;
							pc.sendPackets(new S_ServerMessage(210, item
									.getItem().getName())); // \f1%0は舍てたりまたは他人に让ることができません。
						}
						for (L1NpcInstance petNpc : pc.getPetList().values()) {
							if (petNpc instanceof L1PetInstance) {
								L1PetInstance pet = (L1PetInstance) petNpc;
								if (item.getId() == pet.getItemObjId()) {
									tradable = false;
									// \f1%0は舍てたりまたは他人に让ることができません。
									pc.sendPackets(new S_ServerMessage(210,
											item.getItem().getName()));
									break;
								}
							}
						}
						for (L1DollInstance doll : pc.getDollList().values()) {
							if (item.getId() == doll.getItemObjId()) {
								tradable = false;
								pc.sendPackets(new S_ServerMessage(1181)); // 该当のマジックドールは现在使用中です。
								break;

							}
						}
						if (clan.getDwarfForClanInventory()
								.checkAddItemToWarehouse(item, count,
										L1Inventory.WAREHOUSE_TYPE_CLAN) == L1Inventory.SIZE_OVER) {
							pc.sendPackets(new S_ServerMessage(75)); // \f1これ以上ものを置く场所がありません。
							break;
						}
						if (tradable) {
							pc.getInventory().tradeItem(objectId, count,
									clan.getDwarfForClanInventory());
							pc.turnOnOffLight();
						}
					}
				}

				// 强制储存一次身上道具, 避免角色背包内的物品未正常写入导致物品复制的问题
				pc.saveInventory();
			} else {
				pc.sendPackets(new S_ServerMessage(208)); // \f1血盟仓库を使用するには血盟に加入していなくてはなりません。
			}
		} else if ((resultType == 5) && (size != 0)
				&& npcImpl.equalsIgnoreCase("L1Dwarf") && (level >= 5)) { // 从克莱因仓库中取出道具
			int objectId, count;
			L1ItemInstance item;

			L1Clan clan = L1World.getInstance().getClan(pc.getClanname());
			if (clan != null) {
				for (int i = 0; i < size; i++) {
					objectId = readD();
					count = readD();
					item = clan.getDwarfForClanInventory().getItem(objectId);

					// 防止复制道具 add
					L1CheckPcItem checkPcItem = new L1CheckPcItem();
					boolean isCheat = checkPcItem.checkPcItem(item, pc);
					if (isCheat) {
						L1World.getInstance().broadcastServerMessage(
								"\\fZ【" + pc.getName()
											+ "】从盟仓取出违法道具！(删除违法道具)");
						return;
					}
				   // 防止复制道具 end

					if (pc.getInventory().checkAddItem(item, count) == L1Inventory.OK) { // 容量重量确认及びメッセージ送信
						if (pc.getInventory().consumeItem(L1ItemId.ADENA, 30)) {
							clan.getDwarfForClanInventory().tradeItem(item,
									count, pc.getInventory());
						} else {
							pc.sendPackets(new S_ServerMessage(189)); // \f1アデナが不足しています。
							break;
						}
					} else {
						pc.sendPackets(new S_ServerMessage(270)); // \f1持っているものが重くて取引できません。
						break;
					}
				}
				clan.setWarehouseUsingChar(0); // クラン仓库のロックを解除
			}
		} else if ((resultType == 5) && (size == 0)
				&& npcImpl.equalsIgnoreCase("L1Dwarf")) { // クラン仓库から取り出し中にCancel、または、ESCキー
			L1Clan clan = L1World.getInstance().getClan(pc.getClanname());
			if (clan != null) {
				clan.setWarehouseUsingChar(0); // クラン仓库のロックを解除
			}
		} else if ((resultType == 8) && (size != 0)
				&& npcImpl.equalsIgnoreCase("L1Dwarf") && (level >= 5)
				&& pc.isElf()) { // 自分のエルフ仓库に格纳
			int objectId, count;
			for (int i = 0; i < size; i++) {
				tradable = true;
				objectId = readD();
				count = readD();
				L1Object object = pc.getInventory().getItem(objectId);
				L1ItemInstance item = (L1ItemInstance) object;
				if (!item.getItem().isTradable()) {
					tradable = false;
					pc.sendPackets(new S_ServerMessage(210, item.getItem()
							.getName())); // \f1%0は舍てたりまたは他人に让ることができません。
				}
				for (L1NpcInstance petNpc : pc.getPetList().values()) {
					if (petNpc instanceof L1PetInstance) {
						L1PetInstance pet = (L1PetInstance) petNpc;
						if (item.getId() == pet.getItemObjId()) {
							tradable = false;
							// \f1%0は舍てたりまたは他人に让ることができません。
							pc.sendPackets(new S_ServerMessage(210, item
									.getItem().getName()));
							break;
						}
					}
				}
				for (L1DollInstance doll : pc.getDollList().values()) {
					if (item.getId() == doll.getItemObjId()) {
						tradable = false;
						pc.sendPackets(new S_ServerMessage(1181)); // 该当のマジックドールは现在使用中です。
						break;
					}
				}
				if (pc.getDwarfForElfInventory().checkAddItemToWarehouse(item,
						count, L1Inventory.WAREHOUSE_TYPE_PERSONAL) == L1Inventory.SIZE_OVER) {
					pc.sendPackets(new S_ServerMessage(75)); // \f1これ以上ものを置く场所がありません。
					break;
				}
				if (tradable) {
					pc.getInventory().tradeItem(objectId, count,
							pc.getDwarfForElfInventory());
					pc.turnOnOffLight();
				}
			}

			// 强制储存一次身上道具, 避免角色背包内的物品未正常写入导致物品复制的问题
			pc.saveInventory();
		} else if ((resultType == 9) && (size != 0)
				&& npcImpl.equalsIgnoreCase("L1Dwarf") && (level >= 5)
				&& pc.isElf()) { // 自分のエルフ仓库から取り出し
			int objectId, count;
			L1ItemInstance item;
			for (int i = 0; i < size; i++) {
				objectId = readD();
				count = readD();
				item = pc.getDwarfForElfInventory().getItem(objectId);
				if (pc.getInventory().checkAddItem(item, count) == L1Inventory.OK) { // 容量重量确认及びメッセージ送信
					if (pc.getInventory().consumeItem(40494, 2)) { // ミスリル
						pc.getDwarfForElfInventory().tradeItem(item, count,
								pc.getInventory());
					} else {
						pc.sendPackets(new S_ServerMessage(337, "$767")); // \f1%0が不足しています。
						break;
					}
				} else {
					pc.sendPackets(new S_ServerMessage(270)); // \f1持っているものが重くて取引できません。
					break;
				}
			}
		} else if ((resultType == 0) && (size != 0) && isPrivateShop) { // 个人商店からアイテム购入
			if (findObject == null) {
				return;
			}
			if (!(findObject instanceof L1PcInstance)) {
				return;
			}
			L1PcInstance targetPc = (L1PcInstance) findObject;

			int order;
			int count;
			int price;
			List<L1PrivateShopSellList> sellList;
			L1PrivateShopSellList pssl;
			int itemObjectId;
			int sellPrice;
			int sellTotalCount;
			int sellCount;
			L1ItemInstance item;
			boolean[] isRemoveFromList = new boolean[8];

			if (targetPc.isTradingInPrivateShop()) {
				return;
			}
			sellList = targetPc.getSellList();
			synchronized (sellList) {
				// 卖り切れが発生し、阅览中のアイテム数とリスト数が异なる
				if (pc.getPartnersPrivateShopItemCount() != sellList.size()) {
					return;
				}
				targetPc.setTradingInPrivateShop(true);

				for (int i = 0; i < size; i++) { // 购入予定の商品
					order = readD();
					count = readD();
					pssl = sellList.get(order);
					itemObjectId = pssl.getItemObjectId();
					sellPrice = pssl.getSellPrice();
					sellTotalCount = pssl.getSellTotalCount(); // 卖る予定の个数
					sellCount = pssl.getSellCount(); // 卖った累计
					item = targetPc.getInventory().getItem(itemObjectId);
					if (item == null) {
						continue;
					}
					if (count > sellTotalCount - sellCount) {
						count = sellTotalCount - sellCount;
					}
					if (count == 0) {
						continue;
					}

					if (pc.getInventory().checkAddItem(item, count) == L1Inventory.OK) { // 容量重量确认及びメッセージ送信
						for (int j = 0; j < count; j++) { // オーバーフローをチェック
							if (sellPrice * j > 2000000000) {
								pc.sendPackets(new S_ServerMessage(904, // 总贩卖価格は%dアデナを超过できません。
										"2000000000"));
								targetPc.setTradingInPrivateShop(false);
								return;
							}
						}
						price = count * sellPrice;
						if (pc.getInventory().checkItem(L1ItemId.ADENA, price)) {
							L1ItemInstance adena = pc.getInventory()
									.findItemId(L1ItemId.ADENA);
							if ((targetPc != null) && (adena != null)) {
								if (targetPc.getInventory().tradeItem(item,
										count, pc.getInventory()) == null) {
									targetPc.setTradingInPrivateShop(false);
									return;
								}
								pc.getInventory().tradeItem(adena, price,
										targetPc.getInventory());
								String message = item.getItem().getName()
										+ " (" + String.valueOf(count) + ")";
								targetPc.sendPackets(new S_ServerMessage(877, // %1%o
										// %0に贩卖しました。
										pc.getName(), message));
								pssl.setSellCount(count + sellCount);
								sellList.set(order, pssl);
								if (pssl.getSellCount() == pssl
										.getSellTotalCount()) { // 卖る予定の个数を卖った
									isRemoveFromList[order] = true;
								}
							}
						} else {
							pc.sendPackets(new S_ServerMessage(189)); // \f1アデナが不足しています。
							break;
						}
					} else {
						pc.sendPackets(new S_ServerMessage(270)); // \f1持っているものが重くて取引できません。
						break;
					}
				}
				// 卖り切れたアイテムをリストの末尾から削除
				for (int i = 7; i >= 0; i--) {
					if (isRemoveFromList[i]) {
						sellList.remove(i);
					}
				}
				targetPc.setTradingInPrivateShop(false);
			}
		} else if ((resultType == 1) && (size != 0) && isPrivateShop) { // 个人商店にアイテム卖却
			int count;
			int order;
			List<L1PrivateShopBuyList> buyList;
			L1PrivateShopBuyList psbl;
			int itemObjectId;
			L1ItemInstance item;
			int buyPrice;
			int buyTotalCount;
			int buyCount;
			boolean[] isRemoveFromList = new boolean[8];

			L1PcInstance targetPc = null;
			if (findObject instanceof L1PcInstance) {
				targetPc = (L1PcInstance) findObject;
			}
			if (targetPc.isTradingInPrivateShop()) {
				return;
			}
			targetPc.setTradingInPrivateShop(true);
			buyList = targetPc.getBuyList();

			for (int i = 0; i < size; i++) {
				itemObjectId = readD();
				count = readCH();
				order = readC();
				item = pc.getInventory().getItem(itemObjectId);
				if (item == null) {
					continue;
				}
				psbl = buyList.get(order);
				buyPrice = psbl.getBuyPrice();
				buyTotalCount = psbl.getBuyTotalCount(); // 买う予定の个数
				buyCount = psbl.getBuyCount(); // 买った累计
				if (count > buyTotalCount - buyCount) {
					count = buyTotalCount - buyCount;
				}
				if (item.isEquipped()) {
					// pc.sendPackets(new S_ServerMessage(905)); // 无法贩卖装备中的道具。
					continue;
				}
				if (item.getBless() >= 128) { // 被封印的装备
					// pc.sendPackets(new S_ServerMessage(210, item.getItem().getName())); // \f1%0%d是不可转移的…
					continue;
				}

				if (targetPc.getInventory().checkAddItem(item, count) == L1Inventory.OK) { // 容量重量确认及びメッセージ送信
					for (int j = 0; j < count; j++) { // オーバーフローをチェック
						if (buyPrice * j > 2000000000) {
							targetPc.sendPackets(new S_ServerMessage(904, // 总贩卖価格は%dアデナを超过できません。
									"2000000000"));
							return;
						}
					}
					if (targetPc.getInventory().checkItem(L1ItemId.ADENA,
							count * buyPrice)) {
						L1ItemInstance adena = targetPc.getInventory()
								.findItemId(L1ItemId.ADENA);
						if (adena != null) {
							targetPc.getInventory().tradeItem(adena,
									count * buyPrice, pc.getInventory());
							pc.getInventory().tradeItem(item, count,
									targetPc.getInventory());
							psbl.setBuyCount(count + buyCount);
							buyList.set(order, psbl);
							if (psbl.getBuyCount() == psbl.getBuyTotalCount()) { // 买う予定の个数を买った
								isRemoveFromList[order] = true;
							}
						}
					} else {
						targetPc.sendPackets(new S_ServerMessage(189)); // \f1アデナが不足しています。
						break;
					}
				} else {
					pc.sendPackets(new S_ServerMessage(271)); // \f1相手が物を持ちすぎていて取引できません。
					break;
				}
			}
			// 买い切ったアイテムをリストの末尾から削除
			for (int i = 7; i >= 0; i--) {
				if (isRemoveFromList[i]) {
					buyList.remove(i);
				}
			}
			targetPc.setTradingInPrivateShop(false);
		} else if ((resultType == 12) && (size != 0)
				&& npcImpl.equalsIgnoreCase("L1Merchant")) { // 领取宠物
			int petCost, petCount, divisor, itemObjectId, itemCount = 0;
			boolean chackAdena = true;

			for (int i = 0; i < size; i++) {
				petCost = 0;
				petCount = 0;
				divisor = 6;
				itemObjectId = readD();
				itemCount = readD();

				if (itemCount == 0) {
					continue;
				}
				for (L1NpcInstance petNpc : pc.getPetList().values()) 
					petCost += petNpc.getPetcost();
				
				int charisma = pc.getCha();
				if (pc.isCrown()) { // 王族
					charisma += 6;
				} else if (pc.isElf()) { // 妖精
					charisma += 12;
				} else if (pc.isWizard()) { // 法师
					charisma += 6;
				} else if (pc.isDarkelf()) { // 黑暗妖精
					charisma += 6;
				} else if (pc.isDragonKnight()) { // 龙骑士
					charisma += 6;
				} else if (pc.isIllusionist()) { // 幻术师
					charisma += 6;
				}

				if (!pc.getInventory().consumeItem(L1ItemId.ADENA, 115)) {
					chackAdena = false;
				}
				L1Pet l1pet = PetTable.getInstance().getTemplate(itemObjectId);
				if (l1pet != null && chackAdena) {
					npcId = l1pet.get_npcid();
					charisma -= petCost;
					if ((npcId == 45313) || (npcId == 45710 // タイガー、バトルタイガー
							) || (npcId == 45711) || (npcId == 45712)) { // 纪州犬の子犬、纪州犬
						divisor = 12;
					} else {
						divisor = 6;
					}
					petCount = charisma / divisor;
					if (petCount <= 0) {
						pc.sendPackets(new S_ServerMessage(489)); // 你无法一次控制那么多宠物。
						return;
					}
					L1Npc npcTemp = NpcTable.getInstance().getTemplate(npcId);
					L1PetInstance pet = new L1PetInstance(npcTemp, pc, l1pet);
					pet.setPetcost(divisor);
				}
			}
			if (!chackAdena) {
				pc.sendPackets(new S_ServerMessage(189)); // \f1金币不足。
			}
		}
	}

	@Override
	public String getType() {
		return C_RESULT;
	}

}
