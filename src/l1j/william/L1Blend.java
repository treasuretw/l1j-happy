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
package l1j.william;

import java.util.Random;

import l1j.server.server.datatables.BlendTable;
import l1j.server.server.datatables.ItemTable;
import l1j.server.server.model.L1Inventory;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1ItemInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_NPCTalkReturn;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.serverpackets.S_SystemMessage;
import l1j.server.server.templates.L1Item;
import l1j.william.L1Blend;

public class L1Blend {

	private int _item_id;

	private int _checkLevel;

	private int _checkClass;

	private int _rnd;

	private int _checkItem;

	private int _hpConsume;

	private int _mpConsume;

	private int _material;

	private int _material_count;

	private int _material_2;

	private int _material_2_count;

	private int _material_3;

	private int _material_3_count;

	private int _new_item;

	private int _new_item_counts;

	private int _new_Enchantlvl_SW;

	private int _new_item_Enchantlvl;

	private int _removeItem;

	private String _message;

	private int _item_Html;

	public L1Blend(int item_id, int checkLevel, int checkClass, int rnd,
			int checkItem, int hpConsume, int mpConsume, int material,
			int material_count, int material_2, int material_2_count,
			int material_3, int material_3_count, int new_item,
			int new_item_counts, int new_Enchantlvl_SW,
			int new_item_Enchantlvl, int removeItem, String message,
			int item_Html) {

		_item_id = item_id;
		_checkLevel = checkLevel;
		_checkClass = checkClass;
		_rnd = rnd;
		_checkItem = checkItem;
		_hpConsume = hpConsume;
		_mpConsume = mpConsume;
		_material = material;
		_material_count = material_count;
		_material_2 = material_2;
		_material_2_count = material_2_count;
		_material_3 = material_3;
		_material_3_count = material_3_count;
		_new_item = new_item;
		_new_item_counts = new_item_counts;
		_new_Enchantlvl_SW = new_Enchantlvl_SW;
		_new_item_Enchantlvl = new_item_Enchantlvl;
		_removeItem = removeItem;
		_message = message;
		_item_Html = item_Html;
	}

	public int getItemId() {
		return _item_id;
	}

	public int getCheckLevel() {
		return _checkLevel;
	}

	public int getCheckClass() {
		return _checkClass;
	}

	public int getRandom() {
		return _rnd;
	}

	public int getCheckItem() {
		return _checkItem;
	}

	public int getHpConsume() {
		return _hpConsume;
	}

	public int getMpConsume() {
		return _mpConsume;
	}

	public int getMaterial() {
		return _material;
	}

	public int getMaterial_count() {
		return _material_count;
	}

	public int getMaterial_2() {
		return _material_2;
	}

	public int getMaterial_2_count() {
		return _material_2_count;
	}

	public int getMaterial_3() {
		return _material_3;
	}

	public int getMaterial_3_count() {
		return _material_3_count;
	}

	public int getNew_item() {
		return _new_item;
	}

	public int getNew_item_counts() {
		return _new_item_counts;
	}

	public int getNew_Enchantlvl_SW() {
		return _new_Enchantlvl_SW;
	}

	public int getNew_item_Enchantlvl() {
		return _new_item_Enchantlvl;
	}

	public int getRemoveItem() {
		return _removeItem;
	}

	public String getMessage() {
		return _message;
	}

	public int getitem_Html() {
		return _item_Html;
	}

	public static int checkItemId(int itemId) { // 判断有没有这个编号的道具
		L1Blend Item_Blend = BlendTable.getInstance().getTemplate(itemId);

		if (Item_Blend == null) {
			return 0;
		}

		int item_id = Item_Blend.getItemId();
		return item_id;

	}

	@SuppressWarnings("unused")
	public static void getItemBlend(L1PcInstance pc,
			L1ItemInstance l1iteminstance, int itemId) {
		L1Blend Item_Blend = BlendTable.getInstance().getTemplate(itemId);

		boolean isBlend = false;
		boolean isConsumeItem = false;
		boolean isConsumeItem_2 = false;
		boolean isConsumeItem_3 = false;
		boolean isOK = false;
		boolean OpenHtml = false;

		int I_B_ma = Item_Blend.getMaterial();			// 媒介1
		int I_B_ma2 = Item_Blend.getMaterial_2();		// 媒介2
		int I_B_ma3 = Item_Blend.getMaterial_3();		// 媒介3
		int ma_C = Item_Blend.getMaterial_count();		// 媒介1数量
		int ma_C2 = Item_Blend.getMaterial_2_count();	// 媒介2数量
		int ma_C3 = Item_Blend.getMaterial_3_count();	// 媒介3数量

		L1Item temp = ItemTable.getInstance().getTemplate(
				Item_Blend.getItemId());
		L1Item CheckItemtemp = ItemTable.getInstance().getTemplate(
				Item_Blend.getCheckItem());
		L1Item maNeme = ItemTable.getInstance().getTemplate(I_B_ma);
		L1Item maNeme2 = ItemTable.getInstance().getTemplate(I_B_ma2);
		L1Item maNeme3 = ItemTable.getInstance().getTemplate(I_B_ma3);
		L1Item NewItemNeme = ItemTable.getInstance().getTemplate(
				Item_Blend.getNew_item());

		if (!isOK) {

			if (Item_Blend == null) {
				return;
			}

			if (Item_Blend.getCheckLevel() != 0) { // 等级判断
				if (pc.getLevel() < Item_Blend.getCheckLevel()) { // 等级不符
					OpenHtml = true;
					pc.sendPackets(new S_ServerMessage(318, ""
							+ Item_Blend.getCheckLevel())); // 等级 %0以上才可使用此道具。
				}
			}

			if (Item_Blend.getCheckClass() != 0) { // 职业判断
				byte class_id = (byte) 0;
				String Classmsg = "";

				if (pc.isCrown()) { // 王族
					class_id = 1;
				} else if (pc.isKnight()) { // 骑士
					class_id = 2;
				} else if (pc.isWizard()) { // 法师
					class_id = 3;
				} else if (pc.isElf()) { // 精灵
					class_id = 4;
				} else if (pc.isDarkelf()) { // 黑暗精灵
					class_id = 5;
				} else if (pc.isDragonKnight()) { // 龙骑士 sosodemon
					class_id = 6;
				} else if (pc.isIllusionist()) { // 幻术师 sosodemon
					class_id = 7;
				}
				switch (Item_Blend.getCheckClass()) {
				case 1:
					Classmsg = "王族";
					break;
				case 2:
					Classmsg = "骑士";
					break;
				case 3:
					Classmsg = "法师";
					break;
				case 4:
					Classmsg = "精灵";
					break;
				case 5:
					Classmsg = "黑暗精灵";
					break;
				case 6:
					Classmsg = "龙骑士"; // 龙骑士 sosodemon
					break;
				case 7:
					Classmsg = "幻术师"; // 幻术师 sosodemon
					break;
				}

				if (Item_Blend.getCheckClass() != class_id) { // 职业不符
					if (!OpenHtml) {
						OpenHtml = true;
					}
					pc.sendPackets(new S_ServerMessage(166, "职业必须是", Classmsg,
							"才能使用此道具"));
				}
			}

			if (Item_Blend.getCheckItem() != 0) { // 携带物品判断
				if (!pc.getInventory().checkItem(Item_Blend.getCheckItem())) {
					if (!OpenHtml) {
						OpenHtml = true;
					}
					pc.sendPackets(new S_ServerMessage(166, "必须有", "【"
							+ CheckItemtemp.getName() + "】", "才能使用此道具"));
				}
			}

			if (Item_Blend.getHpConsume() != 0 // 体力
					|| Item_Blend.getMpConsume() != 0) { // 魔力
				if (pc.getCurrentHp() < Item_Blend.getHpConsume()) {
					if (!OpenHtml) {
						OpenHtml = true;
					}
					pc.sendPackets(new S_ServerMessage(166, "$1083", "必须有"
							+ " (" + Item_Blend.getHpConsume() + ") ",
							"才能使用此道具", "以上"));
				}
				if (pc.getCurrentMp() < Item_Blend.getMpConsume()) {
					if (!OpenHtml) {
						OpenHtml = true;
					}
					pc.sendPackets(new S_ServerMessage(166, "$1084", "必须有"
							+ " (" + Item_Blend.getMpConsume() + ") ",
							"才能使用此道具", "以上"));
				}
			}

			if (I_B_ma != 0 && ma_C != 0) { // 媒介判断
				if (!pc.getInventory().checkItem(I_B_ma, ma_C)) {
					if (!OpenHtml) {
						OpenHtml = true;
					}
					pc.sendPackets(new S_ServerMessage(337, maNeme.getName()
							+ "("
							+ (ma_C - pc.getInventory().countItems(
									maNeme.getItemId())) + ")"));
				} else {
					isConsumeItem = true;
				}
			}

			if (I_B_ma2 != 0 && ma_C2 != 0) { // 媒介判断 2
				if (!pc.getInventory().checkItem(I_B_ma2, ma_C2)) {
					if (!OpenHtml) {
						OpenHtml = true;
					}
					pc.sendPackets(new S_ServerMessage(337, maNeme2.getName()
							+ "("
							+ (ma_C2 - pc.getInventory().countItems(
									maNeme2.getItemId())) + ")"));
				} else {
					isConsumeItem_2 = true;
				}
			}

			if (I_B_ma3 != 0 && ma_C3 != 0) { // 媒介判断 3
				if (!pc.getInventory().checkItem(I_B_ma3, ma_C3)) {
					if (!OpenHtml) {
						OpenHtml = true;
					}
					pc.sendPackets(new S_ServerMessage(337, maNeme3.getName()
							+ "("
							+ (ma_C3 - pc.getInventory().countItems(
									maNeme3.getItemId())) + ")"));
				} else {
					isConsumeItem_3 = true;
				}
			}

			if (OpenHtml) {
				if (Item_Blend.getitem_Html() == 1) {
					String msg0 = "";
					String msg1 = "";
					String msg2 = "";
					String msg3 = "";
					String msg4 = "";
					String msg5 = "";
					String msg6 = "";
					String msg7 = "";
					String msg8 = "";
					String msg9 = "";

					msg0 = temp.getName();
					if (Item_Blend.getCheckLevel() != 0) {
						msg1 = "" + Item_Blend.getCheckLevel() + " 级 ";
					} else {
						msg1 = " 无限制 ";
					}

					if (Item_Blend.getCheckClass() == 1) { // 王族
						msg2 = " 王族";
					} else if (Item_Blend.getCheckClass() == 2) { // 骑士
						msg2 = " 骑士";
					} else if (Item_Blend.getCheckClass() == 3) { // 法师
						msg2 = " 法师";
					} else if (Item_Blend.getCheckClass() == 4) { // 精灵
						msg2 = " 精灵";
					} else if (Item_Blend.getCheckClass() == 5) { // 黑暗精灵
						msg2 = " 黑暗精灵";
					} else if (Item_Blend.getCheckClass() == 6) { // 龙骑士
						msg2 = " 龙骑士";
					} else if (Item_Blend.getCheckClass() == 7) { // 幻术师
						msg2 = " 幻术师";
					} else if (Item_Blend.getCheckClass() == 0) { // 所有职业
						msg2 = " 所有职业";
					}

					if (Item_Blend.getCheckItem() != 0) {
						msg3 = "" + CheckItemtemp.getName();
					} else {
						msg3 = " 无";
					}

					if (Item_Blend.getHpConsume() != 0) {
						msg4 = "" + Item_Blend.getHpConsume() + " 滴 ";
					} else {
						msg4 = " 无限制 ";
					}

					if (Item_Blend.getMpConsume() != 0) {
						msg5 = "" + Item_Blend.getMpConsume() + " 滴 ";
					} else {
						msg5 = " 无限制 ";
					}

					if (I_B_ma != 0) {
						msg6 = "" + maNeme.getName() + "(" + ma_C + ")";
					} else {
						msg6 = " 无";
					}
					if (I_B_ma2 != 0) {
						msg7 = "" + maNeme2.getName() + "(" + ma_C2 + ")";
					} else {
						msg7 = " 无";
					}
					if (I_B_ma3 != 0) {
						msg8 = "" + maNeme3.getName() + "(" + ma_C3 + ")";
					} else {
						msg8 = " 无";
					}
					msg9 = "" + NewItemNeme.getName();
					String msg[] = { msg0, msg1, msg2, msg3, msg4, msg5, msg6,
							msg7, msg8, msg9 };
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "ItemBlend",
							msg));
					return;
				} else {
					return;
				}
			}
			isOK = true;
		}

		Random _random = new Random();
		int DBrnd = Item_Blend.getRandom();		// DB设定随机值
		int Rnd100 = _random.nextInt(100) + 1;	// 100%随机值
		int New_item = Item_Blend.getNew_item();// 融合后 新道具
		int New_item_counts = Item_Blend.getNew_item_counts();	// 融合物的数量
		int ItemLV_SW = Item_Blend.getNew_Enchantlvl_SW();		// 随机等级 开关
		int ItemLV = Item_Blend.getNew_item_Enchantlvl();		// 固定-强化值
		int ItemLV_rnd = _random.nextInt(ItemLV + 1);			// 随机-强化值(0~设定值)
		if (isOK) {
			if (Rnd100 <= DBrnd) { // 随机100% <= 总随机(= DB随机值 + 副业LV)
				int type2 = NewItemNeme.getType2();

				if (Item_Blend.getMessage() != null) {			// 判断系统讯息有无输入值
					pc.sendPackets(new S_ServerMessage(166, Item_Blend
							.getMessage()));
				} else {
					pc.sendPackets(new S_SystemMessage("\\fW道具融合启动，开始进行融合之术。")); // ItemBlend-融合
				}
				if (type2 == 1 || type2 == 2) { // 道具种类判别 1:武器 2:防具
					if (ItemLV == 0) { // 无安定值
						createNewItem(pc, New_item, 1);
					} else {
						if (ItemLV_SW == 0) { // 随机LV:1 ; 固定LV:0
							createNewItem_LV(pc, New_item, 1, ItemLV); // 固定值LV
						} else {
							createNewItem_LV(pc, New_item, 1, ItemLV_rnd); // 隋机LV
						}
					}
				} else { // 道具种类判别 0:道具
					createNewItem(pc, New_item, New_item_counts);
				}
			} else {
				pc.sendPackets(new S_SystemMessage("\\fU道具融合失败，开始发出破裂寸芒。"));// ItemBlend-融合失败
			}
			isBlend = true;
		} else {
			return;
		}

		if (isBlend) {
			if (Item_Blend.getRemoveItem() == 1) {
				pc.getInventory().removeItem(l1iteminstance, 1); // 删除融合道具
			}
			if (isConsumeItem) { // 删除融合媒介
				pc.getInventory().consumeItem(I_B_ma, ma_C);
			}
			if (isConsumeItem_2) {
				pc.getInventory().consumeItem(I_B_ma2, ma_C2);
			}
			if (isConsumeItem_3) {
				pc.getInventory().consumeItem(I_B_ma3, ma_C3);
			}
			pc.setCurrentHp(pc.getCurrentHp() - Item_Blend.getHpConsume());
			pc.setCurrentMp(pc.getCurrentMp() - Item_Blend.getMpConsume());
		}
	}

	@SuppressWarnings("unused")
	public static boolean createNewItem(L1PcInstance pc, int item_id, int count) {
		L1ItemInstance item = ItemTable.getInstance().createItem(item_id);
		item.setCount(count);
		if (item != null) {
			if (pc.getInventory().checkAddItem(item, count) == L1Inventory.OK) {
				pc.getInventory().storeItem(item);
			} else { // 持てない场合は地面に落とす 处理のキャンセルはしない（不正防止）
				L1World.getInstance()
						.getInventory(pc.getX(), pc.getY(), pc.getMapId())
						.storeItem(item);
			}
			pc.sendPackets(new S_ServerMessage(403, item.getLogName())); // 获得%0%o 。
			return true;
		} else {
			return false;
		}
	}

	// 狼人香-武器进化 增加强化值
	@SuppressWarnings("unused")
	public static boolean createNewItem_LV(L1PcInstance pc, int item_id,
			int count, int EnchantLevel) {
		L1ItemInstance item = ItemTable.getInstance().createItem(item_id);
		item.setCount(count);
		item.setEnchantLevel(EnchantLevel);
		if (item != null) {
			if (pc.getInventory().checkAddItem_LV(item, count, EnchantLevel) == L1Inventory.OK) {
				pc.getInventory().storeItem(item);
			} else {
				L1World.getInstance()
						.getInventory(pc.getX(), pc.getY(), pc.getMapId())
						.storeItem(item);
			}
			pc.sendPackets(new S_ServerMessage(403, item.getLogName()));
			return true;
		} else {
			return false;
		}
	}
}
