/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 *
 * http://www.gnu.org/copyleft/gpl.html
 */
package l1j.william;

import java.util.logging.Logger;

import l1j.server.server.datatables.ItemTable;
import l1j.server.server.datatables.NpcTable;
import l1j.server.server.model.Instance.L1ItemInstance;
import l1j.server.server.model.Instance.L1NpcInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.Instance.L1PetInstance;
import l1j.server.server.model.Instance.L1SummonInstance;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.templates.L1Item;
import l1j.server.server.templates.L1Npc;

import l1j.william.ItemSummon;

// Referenced classes of package l1j.server.server.model:
// L1PcInstance

public class L1WilliamItemSummon {

	@SuppressWarnings("unused")
	private static Logger _log = Logger.getLogger(L1WilliamItemSummon.class.getName());

	private int _item_id;

	private int _checkLevel;

	private int _checkClass;

	private int _checkItem;

	private int _hpConsume;

	private int _mpConsume;

	private int _material;

	private int _material_count;

	private int _summon_id;

	private int _summonCost;

	private int _onlyOne;

	private int _removeItem;

	public L1WilliamItemSummon(int item_id, int checkLevel, int checkClass , int checkItem, int hpConsume, int mpConsume
		, int material, int material_count, int summon_id, int summonCost, int onlyOne, int removeItem) {

		_item_id = item_id;
		_checkLevel = checkLevel;
		_checkClass = checkClass;
		_checkItem = checkItem;
		_hpConsume = hpConsume;
		_mpConsume = mpConsume;
		_material = material;
		_material_count = material_count;
		_summon_id = summon_id;
		_summonCost = summonCost;
		_onlyOne = onlyOne;
		_removeItem = removeItem;
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

	public int getMaterialCount() {
		return _material_count;
	}

	public int getSummonId() {
		return _summon_id;
	}

	public int getSummonCost() {
		return _summonCost;
	}

	public int getOnlyOne() {
		return _onlyOne;
	}

	public int getRemoveItem() {
		return _removeItem;
	}

	public static int checkItemId(int itemId) { // 判断有沒有这个编号的道具
		L1WilliamItemSummon Item_Summon = ItemSummon.getInstance().getTemplate(itemId);

		if (Item_Summon == null) {
			return 0;
		}

		int item_id = Item_Summon.getItemId();
		return item_id;
	}

	public static void getItemSummon(L1PcInstance pc, L1ItemInstance l1iteminstance, int itemId) {
		L1WilliamItemSummon Item_Summon = ItemSummon.getInstance().getTemplate(itemId);

		boolean isSummon = false;
		boolean isConsumeItem = false;

		if (Item_Summon == null) {
			return;
		}

		if (Item_Summon.getCheckLevel() != 0) { // 等级判断
			if (pc.getLevel() < Item_Summon.getCheckLevel()) { // 等级不符
				pc.sendPackets(new S_ServerMessage(318, "" + Item_Summon.getCheckLevel())); // 等级 %0以上才可使用此道具。
				return;
			}
		}

		if (Item_Summon.getCheckClass() != 0) { // 职业判断
			byte class_id = (byte) 0;
			String msg = "";

			if (pc.isCrown()) {					// 王族
				class_id = 1;
			} else if (pc.isKnight()) {			// 骑士
				class_id = 2;
			} else if (pc.isWizard()) {			// 法师
				class_id = 3;
			} else if (pc.isElf()) {			// 精灵
				class_id = 4;
			} else if (pc.isDarkelf()) {		// 黑暗精灵
				class_id = 5;
			} else if (pc.isDragonKnight()) {	// 龙骑士
				class_id = 6;
			} else if (pc.isIllusionist()) {	// 幻术师
				class_id = 7;
			}

			switch(Item_Summon.getCheckClass())
			{
			case 1:
				msg = "王族";
				break;
			case 2:
				msg = "骑士";
				break;
			case 3:
				msg = "法师";
				break;
			case 4:
				msg = "精灵";
				break;
			case 5:
				msg = "黑暗精灵";
				break;
			case 6:
				msg = "龙骑士";
				break;
			case 7:
				msg = "幻术师";
				break;
			}

			if (Item_Summon.getCheckClass() != class_id) { // 职业不符
				pc.sendPackets(new S_ServerMessage(166, "职业必须是" + msg+ "才能使用此道具"));
				return;
			}
		}

		if (pc.getCha() < Item_Summon.getSummonCost()) { // 魅力不足
			pc.sendPackets(new S_ServerMessage(166, "魅力最低需要" + " (" + Item_Summon.getSummonCost() + ")"));
			return;
		}

		if (Item_Summon.getCheckItem() != 0) { // 携带物品判断
			if (!pc.getInventory().checkItem(Item_Summon.getCheckItem())) {
				L1Item temp = ItemTable.getInstance().getTemplate(Item_Summon.getCheckItem());
				pc.sendPackets(new S_ServerMessage(166, "必须有 (" + temp.getName() + ") 才能使用此道具"));
				return;
			}
		}

		if (Item_Summon.getHpConsume() != 0 || Item_Summon.getMpConsume() != 0) { // 体力 && 魔力判断
			if (pc.getCurrentHp() < Item_Summon.getHpConsume()) {
				pc.sendPackets(new S_ServerMessage(166, "必须有 (" + Item_Summon.getHpConsume() + ") HP 才能使用此道具"));
				return;
			}
			if (pc.getCurrentMp() < Item_Summon.getMpConsume()) {
				pc.sendPackets(new S_ServerMessage(166, "必须有 (" + Item_Summon.getMpConsume() + ") MP 才能使用此道具"));
				return;
			}
		}

		if (Item_Summon.getMaterial() != 0 && Item_Summon.getMaterialCount() != 0) { // 媒介判断
			if (!pc.getInventory().checkItem(Item_Summon.getMaterial(), Item_Summon.getMaterialCount())) {
				L1Item temp = ItemTable.getInstance().getTemplate(Item_Summon.getMaterial());
				pc.sendPackets(new S_ServerMessage(337, temp.getName() + "(" + 
					((Item_Summon.getMaterialCount()) - pc.getInventory().countItems(temp.getItemId())) + ")"));
				return;
			} else {
				isConsumeItem = true;
			}
		}

		if (pc.getMap().isRecallPets()) {
			int summonCost = Item_Summon.getSummonCost(); // 召唤所需魅力
			if (summonCost < 6) {
				summonCost = 6;
			}

			int petcost = 0;
			for (Object pet : pc.getPetList().values().toArray()) {
				// 现在
				petcost += ((L1NpcInstance) pet).getPetcost(); // 取得已使用的魅力
			}

			int charisma = pc.getCha();

			if (pc.isCrown()) {					// 王族
				charisma += 6;
			} else if (pc.isElf()) {			// 精灵
				charisma += 12;
			} else if (pc.isWizard()) {			// 法师
				charisma += 6;
			} else if (pc.isDarkelf()) {		// 黑暗精灵
				charisma += 6;
			} else if (pc.isDragonKnight()) {	// 龙骑士
				charisma += 6;
			} else if (pc.isIllusionist()) {	// 幻术师
				charisma += 6;
			}

			charisma -= petcost;
			int summonCount = charisma / summonCost;
			if (summonCount == 0) {
				if (petcost > 0) { // 已有召唤怪
					pc.sendPackets(new S_ServerMessage(489)); // 你无法一次控制那么多宠物。
					return;
				} else {
					pc.sendPackets(new S_ServerMessage(166, "魅力最低需要" + " (" + summonCost + ")"));
				}
			}

			if (Item_Summon.getOnlyOne() != 0) { // 召唤只数限制为 1
				for (Object petNpc : pc.getPetList().values().toArray()) {
					if (petNpc instanceof L1SummonInstance) {
						L1SummonInstance _summon = (L1SummonInstance) petNpc;
						if (_summon != null) { // 已有其他召唤怪
							pc.sendPackets(new S_ServerMessage(489)); // 你无法一次控制那么多宠物。
							return;
						}
					} else if (petNpc instanceof L1PetInstance) {
						L1PetInstance _pet = (L1PetInstance) petNpc;
						if (_pet != null) { // 已有其他宠物
							pc.sendPackets(new S_ServerMessage(489)); // 你无法一次控制那么多宠物。
							return;
						}
					}
				}
				summonCount = 1;
			}

			L1Npc npcTemp = NpcTable.getInstance().getTemplate(Item_Summon.getSummonId());
			for (int i = 0; i < summonCount; i++) {
				L1SummonInstance summon = new L1SummonInstance(npcTemp, pc);
				if (Item_Summon.getOnlyOne() != 0) {
					summon.setPetcost(pc.getCha() + 7);
				} else {
					summon.setPetcost(summonCost);
				}
				isSummon = true;
			}
		} else {
			pc.sendPackets(new S_ServerMessage(353)); // 在这附近无法召唤怪物。
		}

		if (isSummon) { // 召唤成功
			if (isConsumeItem) { // 刪除召唤媒介
				pc.getInventory().consumeItem(Item_Summon.getMaterial(), Item_Summon.getMaterialCount());
			}
			if (Item_Summon.getRemoveItem() != 0) { // 刪除召唤道具
				pc.getInventory().removeItem(l1iteminstance, 1);
			}
			pc.setCurrentHp(pc.getCurrentHp() - Item_Summon.getHpConsume()); // 扣血
			pc.setCurrentMp(pc.getCurrentMp() - Item_Summon.getMpConsume()); // 扣魔
		}
	}
}
