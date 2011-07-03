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
package l1j.server.server.model.item.action;

import l1j.server.Config;
import l1j.server.server.ClientThread;
import l1j.server.server.datatables.LogEnchantTable;
import l1j.server.server.model.L1PcInventory;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1ItemInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.identity.L1ItemId;
import l1j.server.server.serverpackets.S_ItemStatus;
import l1j.server.server.serverpackets.S_BlueMessage;
import l1j.server.server.serverpackets.S_OwnCharAttrDef;
import l1j.server.server.serverpackets.S_SPMR;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.serverpackets.S_SystemMessage;
import l1j.server.server.templates.L1Armor;
import l1j.server.server.utils.Random;
import l1j.william.L1WilliamSystemMessage;

public class Enchant {

	// 对武器施法的卷轴
	public static void scrollOfEnchantWeapon(L1PcInstance pc, L1ItemInstance l1iteminstance
			, L1ItemInstance l1iteminstance1, ClientThread client) {
		int itemId = l1iteminstance.getItem().getItemId();
		int safe_enchant = l1iteminstance1.getItem().get_safeenchant();
		int weaponId = l1iteminstance1.getItem().getItemId();
		if ((l1iteminstance1 == null) || (l1iteminstance1.getItem().getType2() != 1)
				|| (safe_enchant < 0) || (l1iteminstance1.getBless() >= 128)) {
			pc.sendPackets(new S_ServerMessage(79));
			return;
		}
		if ((weaponId == 7) || (weaponId == 35) || (weaponId == 48) || (weaponId == 73) || (weaponId == 105)
				|| (weaponId == 120) || (weaponId == 147) || (weaponId == 156)
				|| (weaponId == 174) || (weaponId == 175) || (weaponId == 224)) { // 象牙塔装备
			pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
			return;
		}
		if ((weaponId >= 246) && (weaponId <= 249)) { // 试炼之剑
			if (itemId != L1ItemId.SCROLL_OF_ENCHANT_QUEST_WEAPON) { // 非试炼卷轴
				pc.sendPackets(new S_ServerMessage(79));
				return;
			}
		}
		if (itemId == L1ItemId.SCROLL_OF_ENCHANT_QUEST_WEAPON) { // 试炼卷轴
			if ((weaponId < 246) || (weaponId > 249)) { // 非试炼之剑
				pc.sendPackets(new S_ServerMessage(79));
				return;
			}
		}
		if ((weaponId == 36) || (weaponId == 183) || ((weaponId >= 250) && (weaponId <= 255))) { // 幻象武器
			if (itemId != 40128) { // 非对武器施法的幻象卷轴
				pc.sendPackets(new S_ServerMessage(79));
				return;
			}
		}
		if (itemId == 40128) { // 对武器施法的幻象卷轴
			if ((weaponId != 36) && (weaponId != 183) && ((weaponId < 250) || (weaponId > 255))) { // 非幻象武器
				pc.sendPackets(new S_ServerMessage(79)); // \f1何も起きませんでした。
				return;
			}
		}

		int enchant_level = l1iteminstance1.getEnchantLevel();

		//百分百武卷 防爆卷
		if (itemId == 5056){
			if (l1iteminstance1 == null || l1iteminstance1.getItem().getType2() != 1){//判断防武类型
				pc.sendPackets(new S_BlueMessage(166,"你使用的卷轴是武器专用的!"));
				return;
			}
			if (l1iteminstance1.getEnchantLevel() < 50){ //判断提升范围
				//L1World.getInstance().broadcastPacketToAll(new S_BlueMessage(166, "\\f3【温馨提示】：恭喜 【" + pc.getName() + "】使用百分百武卷提升攻击力，爽啦！！！ " ));//提升后世界广播（可修改成自己想要的）
				pc.getInventory().removeItem(l1iteminstance, 1);
				SuccessEnchant(pc, l1iteminstance1, client, RandomELevel(l1iteminstance1, itemId));
			}
			else{
				pc.sendPackets(new S_SystemMessage(L1WilliamSystemMessage.ShowMessage(1561)));
			}
		}

		else if (itemId == L1ItemId.C_SCROLL_OF_ENCHANT_WEAPON) { // 受咀咒的 对武器施法的卷轴
			pc.getInventory().removeItem(l1iteminstance, 1);
			if (enchant_level < -6) {
				// -7以上失败。
				FailureEnchant(pc, l1iteminstance1);
			} else {
				SuccessEnchant(pc, l1iteminstance1, client, -1);
			}
		} else if (enchant_level < safe_enchant) { // 强化等级小于安定值
			pc.getInventory().removeItem(l1iteminstance, 1);
			SuccessEnchant(pc, l1iteminstance1, client, RandomELevel(l1iteminstance1, itemId));
		} else {
			pc.getInventory().removeItem(l1iteminstance, 1);

			int rnd = Random.nextInt(100) + 1;
			int enchant_chance_wepon;
			if (enchant_level >= 9) {
				enchant_chance_wepon = (100 + 3 * Config.ENCHANT_CHANCE_WEAPON) / 6;
			}
			else {
				enchant_chance_wepon = (100 + 3 * Config.ENCHANT_CHANCE_WEAPON) / 3;
			}

			if (rnd < enchant_chance_wepon) {
				int randomEnchantLevel = RandomELevel(l1iteminstance1, itemId);
				SuccessEnchant(pc, l1iteminstance1, client, randomEnchantLevel);
			} else if ((enchant_level >= 9) && (rnd < (enchant_chance_wepon * 2))) {
				// \f1%0%s 持续发出 产生激烈的 蓝色的 光芒，但是没有任何事情发生。
				pc.sendPackets(new S_ServerMessage(160, l1iteminstance1.getLogName(), "$245", "$248"));
			} else {
				FailureEnchant(pc, l1iteminstance1);
			}
		}
	}

	// 对盔甲施法的卷轴
	public static void scrollOfEnchantArmor(L1PcInstance pc, L1ItemInstance l1iteminstance
			, L1ItemInstance l1iteminstance1, ClientThread client) {
		int itemId = l1iteminstance.getItem().getItemId();
		int safe_enchant = ((L1Armor) l1iteminstance1.getItem()).get_safeenchant();
		int armorId = l1iteminstance1.getItem().getItemId();
		if ((l1iteminstance1 == null) || (l1iteminstance1.getItem().getType2() != 2)
				|| (safe_enchant < 0) || (l1iteminstance1.getBless() >= 128)) {
			pc.sendPackets(new S_ServerMessage(79)); // \f1何も起きませんでした。
			return;
		}
		if (armorId == 20028 || armorId == 20082 || armorId == 20126 || armorId == 20173
				|| armorId == 20206 || armorId == 20232 || armorId == 21138
				|| armorId == 21051 || armorId == 21052 || armorId== 21053
				|| armorId == 21054 || armorId == 21055 || armorId == 21056
				|| armorId == 21140 || armorId == 21141) { // 象牙塔装备
			pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
			return;
		}
		if ((armorId == 20161) || ((armorId >= 21035) && (armorId <= 21038))) { // 幻象装备
			if (itemId != 40127) { // 非对盔甲施法的幻象卷轴
				pc.sendPackets(new S_ServerMessage(79));
				return;
			}
		}
		if (itemId == 40127) { // 对盔甲施法的幻象卷轴
			if ((armorId != 20161) && ((armorId < 21035) || (armorId > 21038))) { // 非幻象装备
				pc.sendPackets(new S_ServerMessage(79)); // \f1何も起きませんでした。
				return;
			}
		}

		int enchant_level = l1iteminstance1.getEnchantLevel();

		//百分百防卷 防爆卷
		if (itemId == 5057){
			if (l1iteminstance1 == null || l1iteminstance1.getItem().getType2() != 2){
				pc.sendPackets(new S_BlueMessage(166,"你使用的卷轴是防具专用的"));
	 			return;
	 		}
	 		if (l1iteminstance1.getEnchantLevel() < 10){
	 			//L1World.getInstance().broadcastPacketToAll(new S_BlueMessage(166,"//f3【温馨提示】： 恭喜 【" + pc.getName() + "】使用百分百防卷提升防御力，爽啦！！！ " ));
	 			pc.getInventory().removeItem(l1iteminstance, 1);
	 			SuccessEnchant(pc, l1iteminstance1, client, RandomELevel(l1iteminstance1, itemId));
	 		}
	 		else{
	 			pc.sendPackets(new S_SystemMessage(L1WilliamSystemMessage.ShowMessage(1562)));
	 		}
	 	}

		else if (itemId == L1ItemId.C_SCROLL_OF_ENCHANT_ARMOR) { // 受咀咒的 对盔甲施法的卷轴
			pc.getInventory().removeItem(l1iteminstance, 1);
			if (enchant_level < -6) {
				// -7以上失败。
				FailureEnchant(pc, l1iteminstance1);
			} else {
				SuccessEnchant(pc, l1iteminstance1, client, -1);
			}
		} else if (enchant_level < safe_enchant) { // 强化等级小于安定值
			pc.getInventory().removeItem(l1iteminstance, 1);
			SuccessEnchant(pc, l1iteminstance1, client, RandomELevel(l1iteminstance1, itemId));
		} else {
			pc.getInventory().removeItem(l1iteminstance, 1);
			int rnd = Random.nextInt(100) + 1;
			int enchant_chance_armor;
			int enchant_level_tmp;
			if (safe_enchant == 0) { // 骨、ブラックミスリル用补正
				enchant_level_tmp = enchant_level + 2;
			} else {
				enchant_level_tmp = enchant_level;
			}
			if (enchant_level >= 9) {
				enchant_chance_armor = (100 + enchant_level_tmp * Config.ENCHANT_CHANCE_ARMOR) / (enchant_level_tmp * 2);
			} else {
				enchant_chance_armor = (100 + enchant_level_tmp * Config.ENCHANT_CHANCE_ARMOR) / enchant_level_tmp;
			}

			if (rnd < enchant_chance_armor) {
				int randomEnchantLevel = RandomELevel(l1iteminstance1, itemId);
				SuccessEnchant(pc, l1iteminstance1, client, randomEnchantLevel);
			}
			else if ((enchant_level >= 9) && (rnd < (enchant_chance_armor * 2))) {
				// \f1%0%s 持续发出 产生激烈的 银色的 光芒，但是没有任何事情发生。
				pc.sendPackets(new S_ServerMessage(160, l1iteminstance1.getLogName(), "$252", "$248"));
			} else {
				FailureEnchant(pc, l1iteminstance1);
			}
		}
	}

	// 饰品强化卷轴
	public static void scrollOfEnchantAccessory(L1PcInstance pc, L1ItemInstance l1iteminstance
			, L1ItemInstance l1iteminstance1, ClientThread client) {
		if ((l1iteminstance1 == null) || (l1iteminstance1.getBless() >= 128)
				|| (l1iteminstance1.getItem().getType2() != 2
				|| l1iteminstance1.getItem().getType() < 8
				|| l1iteminstance1.getItem().getType() > 12
				|| l1iteminstance1.getItem().getGrade() == -1)) { // 封印中
			pc.sendPackets(new S_ServerMessage(79));
			return;
		}
		int enchant_level = l1iteminstance1.getEnchantLevel();

		if (enchant_level < 0 || enchant_level >= 10) { // 强化上限 + 10
			pc.sendPackets(new S_ServerMessage(79));
			return;
		}

		int rnd = Random.nextInt(100) + 1;
		int enchant_chance_accessory;
		int enchant_level_tmp = enchant_level;
		int itemStatus = 0;
		// +6 时额外奖励效果判断
		boolean award = false;
		// 成功率： +0-85% ~ +9-40%
		enchant_chance_accessory = (50 + enchant_level_tmp) / (enchant_level_tmp + 1) + 35;

		if (rnd < enchant_chance_accessory) { // 成功
			if (l1iteminstance1.getEnchantLevel() == 5) {
				award = true;
			}
			switch (l1iteminstance1.getItem().getGrade()) {
				case 0: // 上等
					// 四属性 +1
					l1iteminstance1.setEarthMr(l1iteminstance1.getEarthMr() + 1);
					itemStatus += L1PcInventory.COL_EARTHMR;
					l1iteminstance1.setFireMr(l1iteminstance1.getFireMr() + 1);
					itemStatus += L1PcInventory.COL_FIREMR;
					l1iteminstance1.setWaterMr(l1iteminstance1.getWaterMr() + 1);
					itemStatus += L1PcInventory.COL_WATERMR;
					l1iteminstance1.setWindMr(l1iteminstance1.getWindMr() + 1);
					itemStatus += L1PcInventory.COL_WINDMR;
					// LV6 额外奖励：体力与魔力回复量 +1
					if (award) {
						l1iteminstance1.setHpr(l1iteminstance1.getHpr() + 1);
						itemStatus += L1PcInventory.COL_HPR;
						l1iteminstance1.setMpr(l1iteminstance1.getMpr() + 1);
						itemStatus += L1PcInventory.COL_MPR;
					}
					// 装备中
					if (l1iteminstance1.isEquipped()) {
						pc.addFire(1);
						pc.addWater(1);
						pc.addEarth(1);
						pc.addWind(1);
					}
					break;
				case 1: // 中等
					// HP +2
					l1iteminstance1.setaddHp(l1iteminstance1.getaddHp() + 2);
					itemStatus += L1PcInventory.COL_ADDHP;
					// LV6 额外奖励：魔防 +1
					if (award) {
						l1iteminstance1.setM_Def(l1iteminstance1.getM_Def() + 1);
						itemStatus += L1PcInventory.COL_M_DEF;
					}
					// 装备中
					if (l1iteminstance1.isEquipped()) {
						pc.addMaxHp(2);
						if (award) {
							pc.addMr(1);
							pc.sendPackets(new S_SPMR(pc));
						}
					}
					break;
				case 2: // 初等
					// MP +1
					l1iteminstance1.setaddMp(l1iteminstance1.getaddMp() + 1);
					itemStatus += L1PcInventory.COL_ADDMP;
					// LV6 额外奖励：魔攻 +1
					if (award) {
						l1iteminstance1.setaddSp(l1iteminstance1.getaddSp() + 1);
						itemStatus += L1PcInventory.COL_ADDSP;
					}
					// 装备中
					if (l1iteminstance1.isEquipped()) {
						pc.addMaxMp(1);
						if (award) {
							pc.addSp(1);
							pc.sendPackets(new S_SPMR(pc));
						}
					}
					break;
				case 3: // 特等
					// 功能台版未实装。
					break;
				default:
					pc.sendPackets(new S_ServerMessage(79));
					return;
			}
		} else { // 饰品强化失败
			FailureEnchant(pc, l1iteminstance1);
			pc.getInventory().removeItem(l1iteminstance, 1);
			return;
		}
		SuccessEnchant(pc, l1iteminstance1, client, 1);
		// 更新
		pc.sendPackets(new S_ItemStatus(l1iteminstance1));
		pc.getInventory().saveEnchantAccessory(l1iteminstance1, itemStatus);
		// 删除
		pc.getInventory().removeItem(l1iteminstance, 1);
	}

	public static void scrollOfEnchantWeaponAttr(L1PcInstance pc, L1ItemInstance l1iteminstance
			, L1ItemInstance l1iteminstance1, ClientThread client) {
		int itemId = l1iteminstance.getItem().getItemId();
		if ((l1iteminstance1 == null) || (l1iteminstance1.getItem().getType2() != 1)
				|| (l1iteminstance1.getBless() >= 128)) {
			pc.sendPackets(new S_ServerMessage(79));
			return;
		}
		if (l1iteminstance1.getItem().get_safeenchant() < 0) { // 强化不可
			pc.sendPackets(new S_ServerMessage(1453)); // 此装备无法使用强化。
			return;
		}

		// 0:无属性 1:地 2:火 4:水 8:风
		int oldAttrEnchantKind = l1iteminstance1.getAttrEnchantKind();
		int oldAttrEnchantLevel = l1iteminstance1.getAttrEnchantLevel();

		boolean isSameAttr = false;
		if (((itemId == 41429) && (oldAttrEnchantKind == 8)) || ((itemId == 41430) && (oldAttrEnchantKind == 1))
				|| ((itemId == 41431) && (oldAttrEnchantKind == 4)) || ((itemId == 41432) && (oldAttrEnchantKind == 2))) { // 同じ属性
			isSameAttr = true;
		}
		if (isSameAttr && (oldAttrEnchantLevel >= 3)) {
			pc.sendPackets(new S_ServerMessage(1453)); // 此装备无法使用强化。
			return;
		}

		int rnd = Random.nextInt(100) + 1;
		if (Config.ATTR_ENCHANT_CHANCE >= rnd) {
			pc.sendPackets(new S_ServerMessage(1410, l1iteminstance1.getLogName())); // 对\f1%0附加强大的魔法力量成功。
			int newAttrEnchantKind = 0;
			int newAttrEnchantLevel = 0;
			if (isSameAttr) { // 同じ属性なら+1
				newAttrEnchantLevel = oldAttrEnchantLevel + 1;
			}
			else { // 异なる属性なら1
				newAttrEnchantLevel = 1;
			}
			if (itemId == 41429) { // 风之武器强化卷轴
				newAttrEnchantKind = 8;
			}
			else if (itemId == 41430) { // 地之武器强化卷轴
				newAttrEnchantKind = 1;
			}
			else if (itemId == 41431) { // 水之武器强化卷轴
				newAttrEnchantKind = 4;
			}
			else if (itemId == 41432) { // 火之武器强化卷轴
				newAttrEnchantKind = 2;
			}
			l1iteminstance1.setAttrEnchantKind(newAttrEnchantKind);
			pc.getInventory().updateItem(l1iteminstance1, L1PcInventory.COL_ATTR_ENCHANT_KIND);
			pc.getInventory().saveItem(l1iteminstance1, L1PcInventory.COL_ATTR_ENCHANT_KIND);
			l1iteminstance1.setAttrEnchantLevel(newAttrEnchantLevel);
			pc.getInventory().updateItem(l1iteminstance1, L1PcInventory.COL_ATTR_ENCHANT_LEVEL);
			pc.getInventory().saveItem(l1iteminstance1, L1PcInventory.COL_ATTR_ENCHANT_LEVEL);
		} else {
			pc.sendPackets(new S_ServerMessage(1411, l1iteminstance1.getLogName())); // 对\f1%0附加魔法失败。
		}
		pc.getInventory().removeItem(l1iteminstance, 1);
	}

	// 象牙塔对武器施法的卷轴
	public static void scrollOfEnchantWeaponIvoryTower(L1PcInstance pc, L1ItemInstance l1iteminstance
			, L1ItemInstance l1iteminstance1, ClientThread client) {
		int weaponId = l1iteminstance1.getItem().getItemId();
		if ((l1iteminstance1 == null) || (l1iteminstance1.getItem().getType2() != 1)) {
			pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
			return;
		}
		if (l1iteminstance1.getBless() >= 128) { // 封印中强化不可
			pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
			return;
		}
		if (weaponId != 7 && weaponId != 35 && weaponId != 48 && weaponId != 73 && weaponId != 105
				&& weaponId != 120 && weaponId != 147 && weaponId != 156
				&& weaponId != 174 && weaponId != 175 && weaponId != 224) { // 非象牙塔装备
			pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
			return;
		}
		int safe_enchant = l1iteminstance1.getItem().get_safeenchant();
		if (l1iteminstance1.getEnchantLevel() < safe_enchant) {
			pc.getInventory().removeItem(l1iteminstance, 1);
			SuccessEnchant(pc, l1iteminstance1, client, 1);
		} else {
			pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
		}
	}

	// 象牙塔对盔甲施法的卷轴
	public static void scrollOfEnchantArmorIvoryTower(L1PcInstance pc, L1ItemInstance l1iteminstance
			, L1ItemInstance l1iteminstance1, ClientThread client) {
		int armorId = l1iteminstance1.getItem().getItemId();
		if ((l1iteminstance1 == null) || (l1iteminstance1.getItem().getType2() != 2)
				|| (l1iteminstance1.getBless() >= 128)) {
			pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
			return;
		}
		if (armorId != 20028 && armorId != 20082 && armorId != 20126 && armorId != 20173
				&& armorId != 20206 && armorId != 20232 && armorId != 21138
				&& armorId != 21051 && armorId != 21052 && armorId != 21053
				&& armorId != 21054 && armorId != 21055 && armorId != 21056
				&& armorId != 21140 && armorId != 21141) { // 非象牙塔、泡水装备
			pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
			return;
		}
		int safe_enchant = l1iteminstance1.getItem().get_safeenchant();
		if (l1iteminstance1.getEnchantLevel() < safe_enchant) {
			pc.getInventory().removeItem(l1iteminstance, 1);
			SuccessEnchant(pc, l1iteminstance1, client, 1);
		} else {
			pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
		}
	}

	// 强化成功
	private static void SuccessEnchant(L1PcInstance pc, L1ItemInstance item, ClientThread client, int i) {
		//装备保护卷轴 add
		item.setproctect(false);
		//装备保护卷轴 end
		int itemType2 = item.getItem().getType2();

		String[][] sa = { {"", "", "", "", ""}
						, {"$246", "", "$245", "$245", "$245"}
						, {"$246", "", "$252", "$252", "$252"}};
		String[][] sb = { {"", "", "", "", ""}
						, {"$247", "", "$247", "$248", "$248"}
						, {"$247", "", "$247", "$248", "$248"}};
		String sa_temp = sa[itemType2][i + 1];
		String sb_temp = sb[itemType2][i + 1];

		// 冲武防超过安定值多少广播 (最初冲武防广播原创 by 枫印铭心)
		if (Config.SuccessBoard) { // 开关
			if (item.getItem().getType2() == 1
					&& item.getEnchantLevel()
					>= item.getItem().get_safeenchant()
					+ Config.WeaponOverSafeBoard ) { // 武器 (多少以上才广播)
				L1World.getInstance().broadcastPacketToAll(new S_ServerMessage(
						166,"\\fX"+"*【" + pc.getName()
									+ "】的【+" + item.getEnchantLevel()
												+ " " + item.getName() + "】强化成功 *"));
			} else if (item.getItem().getType2() == 2
					&& item.getEnchantLevel()
					>= item.getItem().get_safeenchant()
					+ Config.ArmorOverSafeBoard ) { // 防具 (多少以上才广播)
				L1World.getInstance().broadcastPacketToAll(new S_ServerMessage(
						166,"\\fX"+"*【" + pc.getName()
									+ "】的【+" + item.getEnchantLevel()
												+ " " + item.getName() + "】强化成功 *"));
			}
		}
		//end

		pc.sendPackets(new S_ServerMessage(161, item.getLogName(), sa_temp, sb_temp));
		int oldEnchantLvl = item.getEnchantLevel();
		int newEnchantLvl = oldEnchantLvl + i;
		int safe_enchant = item.getItem().get_safeenchant();
		item.setEnchantLevel(newEnchantLvl);
		client.getActiveChar().getInventory().updateItem(item, L1PcInventory.COL_ENCHANTLVL);

		if (newEnchantLvl > safe_enchant) {
			client.getActiveChar().getInventory().saveItem(item, L1PcInventory.COL_ENCHANTLVL);
		}
		if ((item.getItem().getType2() == 1) && (Config.LOGGING_WEAPON_ENCHANT != 0)) {
			if ((safe_enchant == 0) || (newEnchantLvl >= Config.LOGGING_WEAPON_ENCHANT)) {
				LogEnchantTable logenchant = new LogEnchantTable();
				logenchant.storeLogEnchant(pc.getId(), item.getId(), oldEnchantLvl, newEnchantLvl);
			}
		} else if ((item.getItem().getType2() == 2) && (Config.LOGGING_ARMOR_ENCHANT != 0)) {
			if ((safe_enchant == 0) || (newEnchantLvl >= Config.LOGGING_ARMOR_ENCHANT)) {
				LogEnchantTable logenchant = new LogEnchantTable();
				logenchant.storeLogEnchant(pc.getId(), item.getId(), oldEnchantLvl, newEnchantLvl);
			}
		}

		if (item.getItem().getType2() == 2) { // 防具类
			if (item.isEquipped()) {
				if ((item.getItem().getType() < 8
						|| item.getItem().getType() > 12)) {
					pc.addAc(-i);
				}
				int armorId = item.getItem().getItemId();
				// 强化等级+1，魔防+1
				int[] i1 = { 20011, 20110, 21123, 21124, 21125, 21126, 120011 };
				// 抗魔法头盔、抗魔法链甲、林德拜尔的XX、受祝福的 抗魔法头盔
				for (int j = 0; j < i1.length; j ++) {
					if (armorId == i1[j]) {
						pc.addMr(i);
						pc.sendPackets(new S_SPMR(pc));
						break;
					}
				}
				// 强化等级+1，魔防+2
				int[] i2 = { 20056, 120056, 220056 };
				// 抗魔法斗篷
				for (int j = 0; j < i2.length; j ++) {
					if (armorId == i2[j]) {
						pc.addMr(i * 2);
						pc.sendPackets(new S_SPMR(pc));
						break;
					}
				}
			}
			pc.sendPackets(new S_OwnCharAttrDef(pc));
		}
	}

	// 强化失败
	private static void FailureEnchant(L1PcInstance pc, L1ItemInstance item) {

		//装备保护卷轴 add
		if (item.getproctect() == true) {
		//2009/04/25
			if(item.isEquipped()) {
				pc.addAc(+item.getEnchantLevel());
			}
		//2009/04/25
			item.setEnchantLevel(0);
			pc.sendPackets(new S_ItemStatus(item));
			pc.getInventory().saveItem(item, L1PcInventory.COL_ENCHANTLVL);
			item.setproctect(false);
			pc.sendPackets(new S_ServerMessage(1310));
			return;
		}
		//装备保护卷轴 end

		String[] sa = {"", "$245", "$252"}; // ""、蓝色的、银色的
		int itemType2 = item.getItem().getType2();

		if (item.getEnchantLevel() < 0) { // 强化等级为负值
			 sa[itemType2] = "$246"; // 黑色的
		}
		pc.sendPackets(new S_ServerMessage(164, item.getLogName(), sa[itemType2])); // \f1%0%s 强烈的发出%1光芒就消失了。
		pc.getInventory().removeItem(item, item.getCount());
	}

	// 随机强化等级
	private static int RandomELevel(L1ItemInstance item, int itemId) {
		if ((itemId == L1ItemId.B_SCROLL_OF_ENCHANT_ARMOR) || (itemId == L1ItemId.B_SCROLL_OF_ENCHANT_WEAPON)
				|| (itemId == 140129) || (itemId == 140130)) {
			if (item.getEnchantLevel() <= 2) {
				int j = Random.nextInt(100) + 1;
				if (j < 32) {
					return 1;
				} else if ((j >= 33) && (j <= 76)) {
					return 2;
				} else if ((j >= 77) && (j <= 100)) {
					return 3;
				}
			} else if ((item.getEnchantLevel() >= 3) && (item.getEnchantLevel() <= 5)) {
				int j = Random.nextInt(100) + 1;
				if (j < 50) {
					return 2;
				} else {
					return 1;
				}
			}
		}
		return 1;
	}

}
