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

import static l1j.server.server.model.skill.L1SkillId.BUFF_B;
import static l1j.server.server.model.skill.L1SkillId.BUFF_C;
import static l1j.server.server.model.skill.L1SkillId.BUFF_D;
import static l1j.server.server.model.skill.L1SkillId.BUFF_E;
import static l1j.server.server.model.skill.L1SkillId.BUFF_F;
import static l1j.server.server.model.skill.L1SkillId.BUFF_G;
import static l1j.server.server.model.skill.L1SkillId.BUFF_H;
import static l1j.server.server.model.skill.L1SkillId.BUFF_I;
import static l1j.server.server.model.skill.L1SkillId.BUFF_J;
import static l1j.server.server.model.skill.L1SkillId.BUFF_K;
import static l1j.server.server.model.skill.L1SkillId.BUFF_L;
import static l1j.server.server.model.skill.L1SkillId.BUFF_M;
import static l1j.server.server.model.skill.L1SkillId.BUFF_N;
import static l1j.server.server.model.skill.L1SkillId.BUFF_O;

import l1j.server.server.datatables.ExpTable;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.Instance.L1ItemInstance;
import l1j.server.server.model.identity.L1ItemId;
import l1j.server.server.model.skill.L1SkillUse;
import l1j.server.server.serverpackets.S_BlueMessage;
import l1j.server.server.serverpackets.S_CurseBlind;
import l1j.server.server.serverpackets.S_OwnCharStatus;
import l1j.server.server.serverpackets.S_PacketBox;
import l1j.server.server.serverpackets.S_SPMR;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.serverpackets.S_Liquor;
import l1j.server.server.serverpackets.S_SkillBrave;
import l1j.server.server.serverpackets.S_SkillHaste;
import l1j.server.server.serverpackets.S_SkillIconBlessOfEva;
import l1j.server.server.serverpackets.S_SkillIconGFX;
import l1j.server.server.serverpackets.S_SkillIconWisdomPotion;
import l1j.server.server.serverpackets.S_SkillSound;
import l1j.server.server.serverpackets.S_SystemMessage;
import l1j.server.server.utils.Random;
import l1j.william.Base;
//import l1j.william.L1WilliamSystemMessage;

import static l1j.server.server.model.skill.L1SkillId.*;

public class Potion {

	/** 2段加速效果 **/
	public static void Brave(L1PcInstance pc, L1ItemInstance item, int item_id) {

		if (pc.hasSkillEffect(DECAY_POTION)) {			// 药水霜化术状态
			pc.sendPackets(new S_ServerMessage(698));	// 喉咙灼热，无法喝东西。
			return;
		}

		int time = 0;

		// 判断持续时间 && 使用类型
		/** 勇敢药水类 */
		if (item_id == L1ItemId.POTION_OF_EMOTION_BRAVERY
				|| item_id == L1ItemId.B_POTION_OF_EMOTION_BRAVERY
				|| item_id == L1ItemId.POTION_OF_REINFORCED_CASE
				|| item_id == L1ItemId.W_POTION_OF_EMOTION_BRAVERY
				|| item_id == L1ItemId.DEVILS_BLOOD
				|| item_id == L1ItemId.COIN_OF_REPUTATION) {
			if (item_id == L1ItemId.POTION_OF_EMOTION_BRAVERY) {			// 勇敢药水
				time = 300;
			} else if (item_id == L1ItemId.B_POTION_OF_EMOTION_BRAVERY) {	// 受祝福的勇敢药水
				time = 350;
			} else if (item_id == L1ItemId.POTION_OF_REINFORCED_CASE) {		// 强化勇气的药水
				time = 1800;
			} else if (item_id == L1ItemId.DEVILS_BLOOD) {					// 恶魔之血
				time = 600;
			} else if (item_id == L1ItemId.COIN_OF_REPUTATION) {			// 名誉货币
				time = 600;
			} else if (item_id == L1ItemId.W_POTION_OF_EMOTION_BRAVERY) {	// 福利勇敢药水
				time = 1200;
			}
			buff_brave(pc, STATUS_BRAVE, (byte) 1, time);					// 给予勇敢药水效果
			pc.getInventory().removeItem(item, 1);

			/** 精灵饼干 & 祝福的精灵饼干 */
		} else if (item_id == L1ItemId.ELVEN_WAFER
				|| item_id == L1ItemId.B_ELVEN_WAFER
				|| item_id == L1ItemId.W_POTION_OF_FOREST) {
			if (item_id == L1ItemId.ELVEN_WAFER) {							// 精灵饼干
				time = 480;
			} else if (item_id == L1ItemId.B_ELVEN_WAFER) {					// 祝福的精灵饼干
				time = 700;
			} else if (item_id == L1ItemId.W_POTION_OF_FOREST) {			// 福利森林药水
				time = 1920;
			}
			buff_brave(pc, STATUS_ELFBRAVE, (byte) 3, time);				// 给予精灵饼干效果
			pc.getInventory().removeItem(item, 1);

			/** 生命之树果实 */
		} else if (item_id == L1ItemId.FORBIDDEN_FRUIT) {					// 生命之树果实
			time = 480;
			pc.setSkillEffect(STATUS_RIBRAVE, time * 1000);
			pc.sendPackets(new S_SkillSound(pc.getId(), 7110));
			pc.broadcastPacket(new S_SkillSound(pc.getId(), 7110));
			pc.getInventory().removeItem(item, 1);
		}
	}

	private static void buff_brave(L1PcInstance pc, int skillId, byte type,int timeMillis) {

		// 消除重复状态
		if (pc.hasSkillEffect(STATUS_BRAVE)) {				// 勇敢药水类 1.33倍
			pc.killSkillEffectTimer(STATUS_BRAVE);
		}
		if (pc.hasSkillEffect(STATUS_ELFBRAVE)) {			// 精灵饼干 1.15倍
			pc.killSkillEffectTimer(STATUS_ELFBRAVE);
		}
		if (pc.hasSkillEffect(HOLY_WALK)) {					// 神圣疾走 移速1.33倍
			pc.killSkillEffectTimer(HOLY_WALK);
		}
		if (pc.hasSkillEffect(MOVING_ACCELERATION)) {		// 行走加速 移速1.33倍
			pc.killSkillEffectTimer(MOVING_ACCELERATION);
		}
		if (pc.hasSkillEffect(WIND_WALK)) {					// 风之疾走 移速1.33倍
			pc.killSkillEffectTimer(WIND_WALK);
		}
		if (pc.hasSkillEffect(BLOODLUST)) {					// 血之渴望 攻速1.33倍
			pc.killSkillEffectTimer(BLOODLUST);
		}
		if (pc.hasSkillEffect(STATUS_BRAVE2)) {				// 超级加速 2.66倍
			pc.killSkillEffectTimer(STATUS_BRAVE2);
		}

		// 给予状态 && 效果
		pc.setSkillEffect(skillId, timeMillis * 1000);
		pc.sendPackets(new S_SkillSound(pc.getId(), 751));
		pc.broadcastPacket(new S_SkillSound(pc.getId(), 751));
		pc.sendPackets(new S_SkillBrave(pc.getId(), type, timeMillis));
		pc.broadcastPacket(new S_SkillBrave(pc.getId(), type, 0));
		pc.setBraveSpeed(type);
	}

	/** 3段加速效果 **/
	public static void ThirdSpeed(L1PcInstance pc, L1ItemInstance item, int time) {

		if (pc.hasSkillEffect(DECAY_POTION)) {				// 药水霜化术状态
			pc.sendPackets(new S_ServerMessage(698));		// 喉咙灼热，无法喝东西。
			return;
		}

		if (pc.hasSkillEffect(STATUS_THIRD_SPEED)) {
			pc.killSkillEffectTimer(STATUS_THIRD_SPEED);
		}

		pc.setSkillEffect(STATUS_THIRD_SPEED, time * 1000);
		pc.sendPackets(new S_SkillSound(pc.getId(), 8031));
		pc.broadcastPacket(new S_SkillSound(pc.getId(), 8031));
		pc.sendPackets(new S_Liquor(pc.getId(), 8));		// 人物 * 1.15
		pc.broadcastPacket(new S_Liquor(pc.getId(), 8));	// 人物 * 1.15
		pc.sendPackets(new S_ServerMessage(1065));			// 将发生神秘的奇迹力量。
		pc.getInventory().removeItem(item, 1);
	}

	public static void UseHeallingPotion(L1PcInstance pc, L1ItemInstance item, int healHp, int gfxid) {

		if (pc.hasSkillEffect(DECAY_POTION)) {				// 药水霜化术状态
			pc.sendPackets(new S_ServerMessage(698));		// 喉咙灼热，无法喝东西。
			return;
		}

		pc.sendPackets(new S_SkillSound(pc.getId(), gfxid));
		pc.broadcastPacket(new S_SkillSound(pc.getId(), gfxid));

		// 移除使用加血类药水的提示
//		if (L1WilliamSystemMessage.ShowMessage(1001).equals("true")) {
//			pc.sendPackets(new S_ServerMessage(77));			// \f1你觉得舒服多了。
//		}
		healHp *= ((new java.util.Random()).nextGaussian() / 5.0D) + 1.0D;
		if (pc.hasSkillEffect(POLLUTE_WATER)) {				// 污浊之水 - 效果减半
			healHp /= 2;
		}
		pc.setCurrentHp(pc.getCurrentHp() + healHp);
		pc.getInventory().removeItem(item, 1);
	}

	public static void UseMpPotion(L1PcInstance pc, L1ItemInstance item, int mp, int i) {

		if (pc.hasSkillEffect(DECAY_POTION)) {				// 药水霜化术状态
			pc.sendPackets(new S_ServerMessage(698));		// 喉咙灼热，无法喝东西。
			return;
		}

		pc.sendPackets(new S_SkillSound(pc.getId(), 190));
		pc.broadcastPacket(new S_SkillSound(pc.getId(), 190));
		pc.sendPackets(new S_ServerMessage(338, "$1084"));	// 你的 魔力 渐渐恢复。
		int newMp = 0;
		if (i > 0) {
			newMp = Random.nextInt(i, mp);
		} else {
			newMp = mp;
		}
		pc.setCurrentMp(pc.getCurrentMp() + newMp);
		pc.getInventory().removeItem(item, 1);
	}

	public static void useGreenPotion(L1PcInstance pc, L1ItemInstance item, int itemId) {

		if (pc.hasSkillEffect(DECAY_POTION)) {				// 药水霜化术状态
			pc.sendPackets(new S_ServerMessage(698));		// 喉咙灼热，无法喝东西。
			return;
		}

		int time = 0;
		if (itemId == L1ItemId.POTION_OF_HASTE_SELF || itemId == 40030) {	// 自我加速药水、象牙塔加速药水
			time = 300;
		} else if (itemId == L1ItemId.B_POTION_OF_HASTE_SELF) {				// 受祝福的 自我加速药水
			time = 350;
		} else if ((itemId == 40018) || (itemId == 41338)
				|| (itemId == 41342) || (itemId == 49140)) {				// 强化 自我加速药水、受祝福的葡萄酒、梅杜莎之血、绿茶蛋糕卷
			time = 1800;
		} else if (itemId == 140018) {	// 受祝福的 强化 自我加速药水
			time = 2100;
		} else if (itemId == 40039) {	// 红酒
			time = 600;
		} else if (itemId == 40040) {	// 威士忌
			time = 900;
		} else if (itemId == 49302) {	// 福利加速药水
			time = 1200;
		} else if ((itemId == 41261) || (itemId == 41262) || (itemId == 41268)
				|| (itemId == 41269) || (itemId == 41271) || (itemId == 41272)
				|| (itemId == 41273)) { // 商店料理
			time = 30;
		}

		pc.sendPackets(new S_SkillSound(pc.getId(), 191));
		pc.broadcastPacket(new S_SkillSound(pc.getId(), 191));
		// XXX:ヘイストアイテム装备时、醉った状态が解除されるのか不明
		if (pc.getHasteItemEquipped() > 0) {
			return;
		}
		// 醉った状态を解除
		pc.setDrink(false);

		// 消除重复状态
		if (pc.hasSkillEffect(HASTE)) {
			pc.killSkillEffectTimer(HASTE);
			pc.sendPackets(new S_SkillHaste(pc.getId(), 0, 0));
			pc.broadcastPacket(new S_SkillHaste(pc.getId(), 0, 0));
			pc.setMoveSpeed(0);
		}
		else if (pc.hasSkillEffect(GREATER_HASTE)) {
			pc.killSkillEffectTimer(GREATER_HASTE);
			pc.sendPackets(new S_SkillHaste(pc.getId(), 0, 0));
			pc.broadcastPacket(new S_SkillHaste(pc.getId(), 0, 0));
			pc.setMoveSpeed(0);
		}
		else if (pc.hasSkillEffect(STATUS_HASTE)) {
			pc.killSkillEffectTimer(STATUS_HASTE);
			pc.sendPackets(new S_SkillHaste(pc.getId(), 0, 0));
			pc.broadcastPacket(new S_SkillHaste(pc.getId(), 0, 0));
			pc.setMoveSpeed(0);
		}

		// 解除状态
		if (pc.hasSkillEffect(SLOW)) {				// 缓速术
			pc.killSkillEffectTimer(SLOW);
			pc.sendPackets(new S_SkillHaste(pc.getId(), 0, 0));
			pc.broadcastPacket(new S_SkillHaste(pc.getId(), 0, 0));
		}
		else if (pc.hasSkillEffect(MASS_SLOW)) {	// 集体缓速术
			pc.killSkillEffectTimer(MASS_SLOW);
			pc.sendPackets(new S_SkillHaste(pc.getId(), 0, 0));
			pc.broadcastPacket(new S_SkillHaste(pc.getId(), 0, 0));
		}
		else if (pc.hasSkillEffect(ENTANGLE)) {		// 地面障碍
			pc.killSkillEffectTimer(ENTANGLE);
			pc.sendPackets(new S_SkillHaste(pc.getId(), 0, 0));
			pc.broadcastPacket(new S_SkillHaste(pc.getId(), 0, 0));
		}
		else {
			pc.sendPackets(new S_SkillHaste(pc.getId(), 1, time));
			pc.broadcastPacket(new S_SkillHaste(pc.getId(), 1, 0));
			pc.setMoveSpeed(1);
			pc.setSkillEffect(STATUS_HASTE, time * 1000);
		}
		pc.getInventory().removeItem(item, 1);
	}

	public static void useBluePotion(L1PcInstance pc, L1ItemInstance item, int item_id) {

		if (pc.hasSkillEffect(DECAY_POTION)) {				// 药水霜化术状态
			pc.sendPackets(new S_ServerMessage(698));		// 喉咙灼热，无法喝东西。
			return;
		}

		int time = 0;
		if ((item_id == 40015) || (item_id == 40736)) {		// 蓝色药水、智慧货币
			time = 600;
		} else if (item_id == 140015) {						// 受祝福的 蓝色药水
			time = 700;
		} else if (item_id == 49306) {						// 福利蓝色药水
			time = 2400;
		}

		if (pc.hasSkillEffect(STATUS_BLUE_POTION)) {
			pc.killSkillEffectTimer(STATUS_BLUE_POTION);
		}
		pc.sendPackets(new S_SkillIconGFX(34, time));		// 状态图示
		pc.sendPackets(new S_SkillSound(pc.getId(), 190));
		pc.broadcastPacket(new S_SkillSound(pc.getId(), 190));
		pc.sendPackets(new S_ServerMessage(1007));			// 你感觉到魔力恢复速度加快。

		pc.setSkillEffect(STATUS_BLUE_POTION, time * 1000);
		pc.getInventory().removeItem(item, 1);
	}

	public static void useWisdomPotion(L1PcInstance pc, L1ItemInstance item, int item_id) {

		if (pc.hasSkillEffect(DECAY_POTION)) {				// 药水霜化术状态
			pc.sendPackets(new S_ServerMessage(698));		// 喉咙灼热，无法喝东西。
			return;
		}

		int time = 0;
		if (item_id == L1ItemId.POTION_OF_EMOTION_WISDOM) { // 慎重药水
			time = 300;
		} else if (item_id == L1ItemId.B_POTION_OF_EMOTION_WISDOM) { // 受祝福的 慎重药水
			time = 360;
		} else if (item_id == 49307) { // 福利慎重药水
			time = 1000;
		}

		if (!pc.hasSkillEffect(STATUS_WISDOM_POTION)) {
			pc.addSp(2);
		} else {
			pc.killSkillEffectTimer(STATUS_WISDOM_POTION);
		}

		pc.sendPackets(new S_SkillIconWisdomPotion((time / 4))); // 状态图示
		pc.sendPackets(new S_SkillSound(pc.getId(), 750));
		pc.broadcastPacket(new S_SkillSound(pc.getId(), 750));

		pc.setSkillEffect(STATUS_WISDOM_POTION, time * 1000);
		pc.getInventory().removeItem(item, 1);
	}

	public static void useBlessOfEva(L1PcInstance pc, L1ItemInstance item, int item_id) {

		if (pc.hasSkillEffect(DECAY_POTION)) {				// 药水霜化术状态
			pc.sendPackets(new S_ServerMessage(698));		// 喉咙灼热，无法喝东西。
			return;
		}

		int time = 0;
		if (item_id == 40032) {			// 伊娃的祝福
			time = 1800;
		} else if (item_id == 40041) {	// 人鱼之鳞
			time = 300;
		} else if (item_id == 41344) {	// 水中的水
			time = 2100;
		} else if (item_id == 49303) {	// 福利呼吸药水
			time = 7200;
		}

		if (pc.hasSkillEffect(STATUS_UNDERWATER_BREATH)) { // 持续时间可累加
			int timeSec = pc.getSkillEffectTimeSec(STATUS_UNDERWATER_BREATH);
			time += timeSec;
			if (time > 7200) {
				time = 7200;
			}
			pc.killSkillEffectTimer(STATUS_UNDERWATER_BREATH);
		}
		pc.sendPackets(new S_SkillIconBlessOfEva(pc.getId(), time)); // 状态图示
		pc.sendPackets(new S_SkillSound(pc.getId(), 190));
		pc.broadcastPacket(new S_SkillSound(pc.getId(), 190));
		pc.setSkillEffect(STATUS_UNDERWATER_BREATH, time * 1000);
		pc.getInventory().removeItem(item, 1);
	}

	public static void useBlindPotion(L1PcInstance pc, L1ItemInstance item) {

		if (pc.hasSkillEffect(DECAY_POTION)) {				// 药水霜化术状态
			pc.sendPackets(new S_ServerMessage(698));		// 喉咙灼热，无法喝东西。
			return;
		}

		int time = 16;
		if (pc.hasSkillEffect(CURSE_BLIND)) {
			pc.killSkillEffectTimer(CURSE_BLIND);
		} else if (pc.hasSkillEffect(DARKNESS)) {
			pc.killSkillEffectTimer(DARKNESS);
		}

		if (pc.hasSkillEffect(STATUS_FLOATING_EYE)) {
			pc.sendPackets(new S_CurseBlind(2));
		} else {
			pc.sendPackets(new S_CurseBlind(1));
		}

		pc.setSkillEffect(CURSE_BLIND, time * 1000);
		pc.getInventory().removeItem(item, 1);
	}

	/** 暴击药水动作 */
	public static void PotionBuff(L1PcInstance pc,int itemId) {

		if (pc.hasSkillEffect(DECAY_POTION)) {				// 药水霜化术状态
			pc.sendPackets(new S_ServerMessage(698));		// 喉咙灼热，无法喝东西。
			return;
		}
		if (pc.hasSkillEffect(1900)||pc.hasSkillEffect(1901)||pc.hasSkillEffect(1902)) {
			pc.sendPackets(new S_SystemMessage("已激活一种暴击状态"));
			return;
		}
		l1j.server.server.model.identity.ItemAction.cancelAbsoluteBarrier(pc);

		int buff = 0;
		String msg = "";
		switch (itemId) {
		case 5027:
			//if (Quest<50)
			if (pc.getInventory().consumeItem(L1ItemId.ADENA,300000)) {
				buff=1900;
				msg = "身体冒出神秘力量，攻击力增加1.1倍!";
			}
			else {
				msg = "悟空，你没有三十万金币。";
			}
			break;
		case 5028:
			//if (Quest<10)
			if (pc.getInventory().consumeItem(L1ItemId.ADENA,1000000)) {
				buff=1901;
				msg = "身体冒出神秘力量，攻击力增加1.2倍!";
			}
			else {
				msg = "悟空，你没有一百万金币。";
			}
			break;
		case 5029:
			//if (Quest<7)
			if (pc.getInventory().consumeItem(L1ItemId.YB,3000)) {
				buff=1902;
				msg = "身体冒出神秘力量，攻击力增加1.3倍!";
			}
			else {
				msg = "悟空，你没有三千元宝。";
			}
			break;
		}
		pc.sendPackets(new S_SystemMessage(msg));
		if (buff <= 0) {
			return;
		}
		if (pc.hasSkillEffect(buff)) {
			pc.killSkillEffectTimer(buff);
		}
		pc.setSkillEffect(buff, 1800 * 1000);
		pc.sendPackets(new S_SkillSound(pc.getId(), 4700));
	}

	/** 经验药水动作 */
	public static void PotionExpBuff(L1PcInstance pc,int itemId,L1ItemInstance item) {

		if (pc.hasSkillEffect(DECAY_POTION)) {				// 药水霜化术状态
			pc.sendPackets(new S_ServerMessage(698));		// 喉咙灼热，无法喝东西。
			return;
		}
		if (pc.get_food() != 225) {
			pc.sendPackets(new S_SystemMessage("你只能在吃饱的状态下喝下!"));
			return;
		}
		if (pc.hasSkillEffect(EXP_UP_A)||pc.hasSkillEffect(EXP_UP_B)||pc.hasSkillEffect(EXP_UP_C)) {
			int time = pc.getSkillEffectTimeSec(EXP_UP_A)+ pc.getSkillEffectTimeSec(EXP_UP_B)+
			pc.getSkillEffectTimeSec(EXP_UP_C);

			pc.sendPackets(new S_SystemMessage("双倍经验药水不可以累积喝!"));
			pc.sendPackets(new S_SystemMessage("经验加倍药水时间剩余 "+time+" 秒"));
			return;
		}
		if(item==null) {
			return;
		}
		l1j.server.server.model.identity.ItemAction.cancelAbsoluteBarrier(pc);

		int buff = 0;
		String msg="";
		switch (itemId) {
		case 5030:
			//if (Quest<50)
			buff=EXP_UP_A;
			msg="受到经验之神的祝福，狩猎后经验提升1.5倍！!";
			break;
		case 5031:
			//if (Quest<10)
			buff=EXP_UP_B;
			msg="受到经验之神的祝福，狩猎后经验提升2.0倍！!";
			break;
		case 5032:
			//if (Quest<7)
			buff=EXP_UP_C;
			msg="受到经验之神的祝福，狩猎后经验提升2.5倍！!";
			break;
		}
		if (pc.hasSkillEffect(buff)) {
			pc.killSkillEffectTimer(buff);
		}
		pc.getInventory().removeItem(item, 1);
		pc.sendPackets(new S_PacketBox(53, 23, 1800));
		pc.sendPackets(new S_OwnCharStatus(pc));
		pc.setSkillEffect(buff, 1800 * 1000);
		//pc.sendPackets(new S_SkillSound(pc.getId(), 4700));
		pc.sendPackets(new S_SystemMessage(msg));
	}

	/** HP MP 药水动作 */
	public static void SpecialHP(L1PcInstance pc, int itemId,L1ItemInstance item) {
		l1j.server.server.model.identity.ItemAction.cancelAbsoluteBarrier(pc);
		int Quest=pc.getQuest().get_step(itemId);
		int HP = 0;
		int MP = 0;
		switch (itemId) {
		case 5033:
			if (Quest<50)
		    HP=10;
		    break;
		case 5034:
			if (Quest<10)
			HP=50;
			break;
		case 5035:
			if (Quest<7)
			HP=70;
			break;
		case 5036:
			if (Quest<50)
			MP=10;
			break;
		case 5037:
			if (Quest<10)
			MP=50;
			break;
		case 5038:
			if (Quest<7)
			MP=70;
			break;
		}
		if (HP > 0) {
			try {
				pc.addBaseMaxHp((byte) HP);
				pc.sendPackets(new S_SystemMessage("神秘的血液将你的血量永久提升"+HP+"")); 
				pc.getQuest().set_step(itemId, Quest+1);
				pc.save();
			}
			catch (Exception e) {
			} finally {
				pc.getInventory().removeItem(item, 1);
				pc.sendPackets(new S_OwnCharStatus(pc));
			}
		}
		else if (MP > 0) {
			try {
				pc.addBaseMaxMp((byte) MP);
				pc.sendPackets(new S_SystemMessage("神秘的魔液将你的魔量永久提升"+MP+""));
				pc.getQuest().set_step(itemId, Quest+1);
				pc.save();
			}
			catch (Exception e) {
			} finally {
				pc.getInventory().removeItem(item, 1);
				pc.sendPackets(new S_OwnCharStatus(pc));
			}
		} else {
			pc.sendPackets(new S_SystemMessage("悟空，你吃的够多了，不可以再吃"+item.getName()+"了"));
		}
	}

	/** 
	 *	声望卡 
	 *	@throws Exception
	 */
	public static void Prestige(L1PcInstance pc, L1ItemInstance item, int itemId) throws Exception {

		// 管理员证 
		if(itemId == 5041) {
			if (pc.getAccessLevel() >= 0) {
				L1World.getInstance().broadcastPacketToAll(new S_BlueMessage(166,
						"\\f=【温馨提示】「" + pc.getName() + "」成为天堂新的管理员！！"));
			}
			pc.setAccessLevel((short)200); // 账号等级 + 200 (不会累积)
			pc.getInventory().removeItem(item, 1); // 删除物品
			pc.sendPackets(new S_SystemMessage("恭喜你成为天堂管理员，请小退重新登入.."));
			pc.save(); // 将数据存入DB资料库
		}
		// 声望卡 (+1 声望)
		else if (itemId == 5042) {
			if (pc.getFameLevel() <= 15002) {
				L1World.getInstance().broadcastPacketToAll(new S_BlueMessage(1425,
						"" + pc.getName() + "")); // \fX【温馨提示】: 玩家【%0%s】使用死神邀请函后声望增加1点！！
				pc.sendPackets(new S_ServerMessage(1421)); // 您的声望值提高了1点。
				pc.setFameLevel(pc.getFameLevel() + 1);
				pc.getInventory().removeItem(item, 1);
				pc.save();
			} else {
				pc.sendPackets(new S_ServerMessage(1420)); // 您的声望值已到了极限。
			}
		}
		// 声望卡 (+3 声望)
		else if (itemId == 5043) {
			if (pc.getFameLevel() <= 15002) {
				L1World.getInstance().broadcastPacketToAll(new S_BlueMessage(1426,
						"" + pc.getName() + "")); // \fX【温馨提示】: 玩家【%0%s】使用死神邀请函后声望增加3点！！
				pc.sendPackets(new S_ServerMessage(1422)); // 您的声望值提高了3点。
				pc.setFameLevel(pc.getFameLevel() + 3);
				pc.getInventory().removeItem(item, 1);
				pc.save();
			} else {
				pc.sendPackets(new S_ServerMessage(1420)); // 您的声望值已到了极限。
			}
		}
		// 声望卡 (+5 声望)
		else if (itemId == 5044) {
			if (pc.getFameLevel() <= 15002) {
				L1World.getInstance().broadcastPacketToAll(new S_BlueMessage(1427,
						"" + pc.getName() + "")); // \fX【温馨提示】: 玩家【%0%s】使用死神邀请函后声望增加5点！！
				pc.sendPackets(new S_ServerMessage(1423)); // 您的声望值提高了5点。
				pc.setFameLevel(pc.getFameLevel() + 5);
				pc.getInventory().removeItem(item, 1);
				pc.save();
			} else {
				pc.sendPackets(new S_ServerMessage(1420)); // 您的声望值已到了极限。
			}
		}
		// 声望卡 (+10 声望)
		else if (itemId == 5045) {
			if (pc.getFameLevel() <= 15002) {
				L1World.getInstance().broadcastPacketToAll(new S_BlueMessage(1428,
						"" + pc.getName() + "")); // \fX【温馨提示】: 玩家【%0%s】使用死神邀请函后声望增加10点！！
				pc.sendPackets(new S_ServerMessage(1424)); // 您的声望值提高了10点。
				pc.setFameLevel(pc.getFameLevel() + 10);
				pc.getInventory().removeItem(item, 1);
				pc.save();
			} else {
				pc.sendPackets(new S_ServerMessage(1420)); // 您的声望值已到了极限。
			}
		}
		// 声望查询卡
		else if(itemId == 5046) {
			pc.sendPackets(new S_SystemMessage("你好!【 "+pc.getName()+" 】你目前的声望值  " + pc.getFameLevel() + " 点."));
			if(pc.getFameLevel() >= 0);{
				String abcd = "";
				if (pc.getAccessLevel() == 200 && pc.getFameLevel()<= 20000)
					abcd = "【管理员】";
				if (pc.getAccessLevel() == 100 && pc.getFameLevel()<= 20000)
					abcd = "【管理员小蜜】";
				if (pc.getAccessLevel() == 11 && pc.getFameLevel()<= 20000)
					abcd = "【VIP1】";
				if (pc.getAccessLevel() == 12 && pc.getFameLevel()<= 20000)
					abcd = "【VIP2】";
				if (pc.getAccessLevel() == 13 && pc.getFameLevel()<= 20000)
					abcd = "【VIP3】";
				if (pc.getFameLevel() == 0 && pc.getAccessLevel() <= 10)
					abcd = "【木有】";
				if (pc.getFameLevel() >= 1 && pc.getFameLevel() <= 2&& pc.getAccessLevel() <= 10)
					abcd = "【新兵】";
				if (pc.getFameLevel() >= 3 && pc.getFameLevel() <= 6&& pc.getAccessLevel() <= 10)
					abcd = "【下等兵】";
				if (pc.getFameLevel() >= 7 && pc.getFameLevel() <= 14&& pc.getAccessLevel() <= 10)
					abcd = "【中等兵】";
				if (pc.getFameLevel() >= 15 && pc.getFameLevel() <= 28&& pc.getAccessLevel() <= 10)
					abcd = "【上等兵】";
				if (pc.getFameLevel() >= 29 && pc.getFameLevel() <= 56&& pc.getAccessLevel() <= 10)
					abcd = "【少尉】";
				if (pc.getFameLevel() >= 57 && pc.getFameLevel() <= 111&& pc.getAccessLevel() <= 10)
					abcd = "【中尉】";
				if (pc.getFameLevel() >= 112 && pc.getFameLevel() <= 223&& pc.getAccessLevel() <= 10)
					abcd = "【上尉】";
				if (pc.getFameLevel() >= 224 && pc.getFameLevel() <= 447&& pc.getAccessLevel() <= 10)
					abcd = "【少校】";
				if (pc.getFameLevel() >= 448 && pc.getFameLevel() <= 859&& pc.getAccessLevel() <= 10)
					abcd = "【中校】";
				if (pc.getFameLevel() >= 860 && pc.getFameLevel() <= 1791&& pc.getAccessLevel() <= 10)
					abcd = "【上校】";
				if (pc.getFameLevel() >= 1792 && pc.getFameLevel() <= 3583&& pc.getAccessLevel() <= 10)
					abcd = "【大校】";
				if (pc.getFameLevel() >= 3584 && pc.getFameLevel() <= 7167&& pc.getAccessLevel() <= 10)
					abcd = "【三星少将】";
				if (pc.getFameLevel() >= 7168 && pc.getFameLevel() <= 14334&& pc.getAccessLevel() <= 10)
					abcd = "【四星中将】";
				if (pc.getFameLevel() >= 14335 && pc.getFameLevel() <= 15000&& pc.getAccessLevel() <= 10)
					abcd = "【五星上将】";
				if (pc.getFameLevel() >= 15001 && pc.getAccessLevel() <= 10)
					abcd = "【三军统帅】";
				pc.sendPackets(new S_SystemMessage("你好!【 "+pc.getName()+" 】你目前的头衔为 "+abcd+"."));
				return;
			}
		}
		// VIP1
		else if(itemId == 5047) {
			if (pc.getAccessLevel() == 0) {
				L1World.getInstance().broadcastPacketToAll(new S_BlueMessage(166,
						"\\f=【温馨提示】玩家:「" + pc.getName() + "」成为天堂 VIP1 会员！！"));
			}
			pc.setAccessLevel((short)11); // 账号等级 + 11  (不会累积)
			pc.getInventory().removeItem(item, 1); // 删除物品
			pc.sendPackets(new S_SystemMessage("恭喜你成为天堂 VIP1 会员，请小退重新登入.."));
			pc.save(); // 将数据存入DB资料库
		}
		// VIP2
		else if(itemId == 5048) {
			if (pc.getAccessLevel() == 11) {
				L1World.getInstance().broadcastPacketToAll(new S_BlueMessage(166,
						"\\f=【温馨提示】玩家:「" + pc.getName() + "」成为天堂 VIP2 会员！！"));
			}
			pc.setAccessLevel((short)12); // 账号等级 + 12  (不会累积)
			pc.getInventory().removeItem(item, 1); // 删除物品
			pc.sendPackets(new S_SystemMessage("恭喜你成为天堂 VIP2 会员，请小退重新登入.."));
			pc.save(); // 将数据存入DB资料库
		}
		// VIP3
		else if(itemId == 5049) {
			if (pc.getAccessLevel() == 12) {
				L1World.getInstance().broadcastPacketToAll(new S_BlueMessage(166,
						"\\f=【温馨提示】玩家:「" + pc.getName() + "」成为天堂 VIP3 会员！！"));
			}
			pc.setAccessLevel((short)13); // 账号等级 + 13  (不会累积)
			pc.getInventory().removeItem(item, 1); // 删除物品
			pc.sendPackets(new S_SystemMessage("恭喜你成为天堂 VIP3 会员，请小退重新登入.."));
			pc.save(); // 将数据存入DB资料库
		}
	}

	/** 商城状态 */
	public static void MallStatus(L1PcInstance pc, L1ItemInstance item, int itemId) {

		// 自然恢复药水
		if (itemId == 5009) {
			if (!pc.hasSkillEffect(BUFF_B)) {
				pc.setSkillEffect(BUFF_B, 1800 * 1000);
			} else {
				pc.killSkillEffectTimer(BUFF_B);
				pc.setSkillEffect(BUFF_B, 1800 * 1000);
			}
			pc.getInventory().removeItem(item, 1);
		}
		// 冥想恢复药水
		else if (itemId == 5010) {
			if (!pc.hasSkillEffect(BUFF_C)) {
				pc.setSkillEffect(BUFF_C, 1800 * 1000);
			} else {
				pc.killSkillEffectTimer(BUFF_C);
				pc.setSkillEffect(BUFF_C, 1800 * 1000);
			}
			pc.getInventory().removeItem(item, 1);
		}
		// 生命升华药水
		else if (itemId == 5011) {
			if (!pc.hasSkillEffect(BUFF_D)) {
				pc.setSkillEffect(BUFF_D, 1800 * 1000);
				pc.addMaxHp(120);
				pc.sendPackets(new S_OwnCharStatus(pc));
			} else {
				pc.killSkillEffectTimer(BUFF_D);
				pc.setSkillEffect(BUFF_D, 1800 * 1000);
			}
			pc.getInventory().removeItem(item, 1);
		}
		// 魔法升华药水
		else if (itemId == 5012) {
			if (!pc.hasSkillEffect(BUFF_E)) {
				pc.setSkillEffect(BUFF_E, 1800 * 1000);
				pc.addMaxMp(120);
				pc.sendPackets(new S_OwnCharStatus(pc));
			} else {
				pc.killSkillEffectTimer(BUFF_E);
				pc.setSkillEffect(BUFF_E, 1800 * 1000);
			}
			pc.getInventory().removeItem(item, 1);
		}
		// 魔法抵抗药水
		else if (itemId == 5013) {
			if (!pc.hasSkillEffect(BUFF_F)) {
				pc.setSkillEffect(BUFF_F, 1800 * 1000);
				pc.addMr(10);
				pc.sendPackets(new S_SPMR(pc));
			} else {
				pc.killSkillEffectTimer(BUFF_F);
				pc.setSkillEffect(BUFF_F, 1800 * 1000);
			}
			pc.getInventory().removeItem(item, 1);
		}
		// 保护卷轴 (圣结界)
		else if (itemId == 5014) {
			new L1SkillUse().handleCommands(pc, 68, pc.getId(), pc.getX(), pc.getY(), null, 32, Base.SKILL_TYPE[4]);
			pc.getInventory().removeItem(item, 1);
		}
		// 力量药水
		else if (itemId == 5015) {
			if (!pc.hasSkillEffect(BUFF_G)) {
				pc.addHitup(2);
				pc.addDmgup(2);
				pc.setSkillEffect(BUFF_G, 3600 * 1000);
			} else {
				pc.killSkillEffectTimer(BUFF_G);
				pc.setSkillEffect(BUFF_G, 3600 * 1000);
			}
			pc.sendPackets(new S_SkillSound(pc.getId(), 7101));
			pc.broadcastPacket(new S_SkillSound(pc.getId(), 7101));
			pc.getInventory().removeItem(item, 1);
		}
		// 坚韧药水
		else if (itemId == 5016) {
			if (!pc.hasSkillEffect(BUFF_H)) {
				pc.addMaxHp(100); // 暂时
				pc.sendPackets(new S_OwnCharStatus(pc));
				pc.setSkillEffect(BUFF_H, 3600 * 1000);
			} else {
				pc.killSkillEffectTimer(BUFF_H);
				pc.setSkillEffect(BUFF_H, 3600 * 1000);
			}
			pc.sendPackets(new S_SkillSound(pc.getId(), 7102)); 
			pc.broadcastPacket(new S_SkillSound(pc.getId(), 7102));
			pc.getInventory().removeItem(item, 1);
		}
		// 暴怒药水
		else if (itemId == 5017) {
			if (!pc.hasSkillEffect(BUFF_I)) {
				pc.addHitup(2);
				pc.addDmgup(2);	
				pc.addMaxHp(100);
				pc.sendPackets(new S_OwnCharStatus(pc));
				pc.setSkillEffect(BUFF_I, 3600 * 1000);
			} else {
				pc.killSkillEffectTimer(BUFF_I);
				pc.setSkillEffect(BUFF_I, 3600 * 1000);
			}
			pc.sendPackets(new S_SkillSound(pc.getId(), 7103)); 
			pc.broadcastPacket(new S_SkillSound(pc.getId(), 7103));
			pc.getInventory().removeItem(item, 1);
		}
		// 智力药水
		else if (itemId == 5018) {
			if (!pc.hasSkillEffect(BUFF_J)) {
				pc.addSp(3);
				pc.sendPackets(new S_SPMR(pc));
				pc.setSkillEffect(BUFF_J, 3600 * 1000);
			} else {
				pc.killSkillEffectTimer(BUFF_J);
				pc.setSkillEffect(BUFF_J, 3600 * 1000);
			}
			pc.sendPackets(new S_SkillSound(pc.getId(), 7104)); 
			pc.broadcastPacket(new S_SkillSound(pc.getId(), 7104));
			pc.getInventory().removeItem(item, 1);
		}
		// 活化药水
		else if (itemId == 5019) {
			if (!pc.hasSkillEffect(BUFF_K)) {
				pc.addMaxMp(80);
				pc.sendPackets(new S_OwnCharStatus(pc));
				pc.setSkillEffect(BUFF_K, 3600 * 1000);
			} else {
				pc.killSkillEffectTimer(BUFF_K);
				pc.setSkillEffect(BUFF_K, 3600 * 1000);
			}
			pc.sendPackets(new S_SkillSound(pc.getId(), 7105)); 
			pc.broadcastPacket(new S_SkillSound(pc.getId(), 7105));
			pc.getInventory().removeItem(item, 1);
		}
		// 巫师药水
		else if (itemId == 5020) {
			if (!pc.hasSkillEffect(BUFF_L)) {
				pc.addSp(3);
				pc.addMaxMp(80);
				pc.sendPackets(new S_SPMR(pc));
				pc.sendPackets(new S_OwnCharStatus(pc));
				pc.setSkillEffect(BUFF_L, 3600 * 1000);
			} else {
				pc.killSkillEffectTimer(BUFF_L);
				pc.setSkillEffect(BUFF_L, 3600 * 1000);
			}
			pc.sendPackets(new S_SkillSound(pc.getId(), 7106)); 
			pc.broadcastPacket(new S_SkillSound(pc.getId(), 7106));
			pc.getInventory().removeItem(item, 1);
		}
		// 突袭药水
		else if (itemId == 5021) {
			if (!pc.hasSkillEffect(BUFF_M)) {
				pc.addSp(1);
				pc.addDmgup(1);
				pc.sendPackets(new S_SPMR(pc));
				pc.sendPackets(new S_OwnCharStatus(pc));
				pc.setSkillEffect(BUFF_M, 3600 * 1000);
			} else {
				pc.killSkillEffectTimer(BUFF_M);
				pc.setSkillEffect(BUFF_M, 3600 * 1000);
			}
			pc.sendPackets(new S_SkillSound(pc.getId(), 7107)); 
			pc.broadcastPacket(new S_SkillSound(pc.getId(), 7107));
			pc.getInventory().removeItem(item, 1);
		}
		// 强力恢复药水
		else if (itemId == 5022) {
			if (!pc.hasSkillEffect(BUFF_N)) {
				pc.addMaxMp(30);
				pc.addMaxHp(30);
				pc.sendPackets(new S_OwnCharStatus(pc));
				pc.setSkillEffect(BUFF_N, 3600 * 1000);
			} else {
				pc.killSkillEffectTimer(BUFF_N);
				pc.setSkillEffect(BUFF_N, 3600 * 1000);
			}
			pc.sendPackets(new S_SkillSound(pc.getId(), 7108)); 
			pc.broadcastPacket(new S_SkillSound(pc.getId(), 7108));
			pc.getInventory().removeItem(item, 1);
		}
		// 疯狂力量药水
		else if (itemId == 5023) {
			if (!pc.hasSkillEffect(BUFF_O)) {
				pc.addSp(1);
				pc.addMaxMp(30);
				pc.addMaxHp(30);
				pc.sendPackets(new S_SPMR(pc));
				pc.sendPackets(new S_OwnCharStatus(pc));
				pc.setSkillEffect(BUFF_O, 3600 * 1000);
			} else {
				pc.killSkillEffectTimer(BUFF_O);
				pc.setSkillEffect(BUFF_O, 3600 * 1000);
			}
			pc.sendPackets(new S_SkillSound(pc.getId(), 7109)); 
			pc.broadcastPacket(new S_SkillSound(pc.getId(), 7109));
			pc.getInventory().removeItem(item, 1);
		}
	}

	/**
	 *	经验书 彩票
	 *	@throws Exception
	 */
	public static void ExperienceBook(L1PcInstance pc, L1ItemInstance item, int itemId) throws Exception {

		// 经验书1%
		if (itemId == 5024) {
			int oldLevel = pc.getLevel();
			int needExp = ExpTable.getNeedExpNextLevel(oldLevel);
			int exp = 0;
			if (/*oldLevel >= 95 && */oldLevel <= 99) {
				exp = (int) (needExp * 0.01);
				pc.sendPackets(new S_SystemMessage("恭喜你!你的当前经验递增了1%"));
				pc.addExp(+exp);
				pc.getInventory().removeItem(item, 1);
			} else if (oldLevel < 99) {
				pc.sendPackets(new S_SystemMessage("你没有达到使用等级!"));
			} else {
				pc.sendPackets(new S_SystemMessage("你已经超过使用等级!"));
			}
		}
		// 经验书2%
		else if (itemId == 5025) {
			int oldLevel = pc.getLevel();
			int needExp = ExpTable.getNeedExpNextLevel(oldLevel);
			int exp = 0;
			if (/*oldLevel >= 95 && */oldLevel <= 99) {
				exp = (int) (needExp * 0.02);
				pc.sendPackets(new S_SystemMessage("恭喜你!你的当前经验递增了2%"));
				pc.addExp(+exp);
				pc.getInventory().removeItem(item, 1);
			} else if (oldLevel < 99) {
				pc.sendPackets(new S_SystemMessage("你没有达到使用等级!"));
			} else {
				pc.sendPackets(new S_SystemMessage("你已经超过使用等级!"));
			}
		}
		// 经验书3%
		else if (itemId == 5026) {
			int oldLevel = pc.getLevel();
			int needExp = ExpTable.getNeedExpNextLevel(oldLevel);
			int exp = 0;
			if (/*oldLevel >= 95 && */oldLevel <= 99) {
				exp = (int) (needExp * 0.03);
				pc.sendPackets(new S_SystemMessage("恭喜你!你的当前经验递增了3%"));
				pc.addExp(+exp);
				pc.getInventory().removeItem(item, 1);
			} else if (oldLevel < 99) {
				pc.sendPackets(new S_SystemMessage("你没有达到使用等级!"));
			} else {
				pc.sendPackets(new S_SystemMessage("你已经超过使用等级!"));
			}
		}
		// 经验丹 (直接升84级)
		else if (itemId == 5050) {
			if (pc.getLevel() <= 40) {
				pc.setExp(pc.getExp() + 1282024090); // 从1级至84级的总经验值
				pc.getInventory().removeItem(item, 1);
				//pc.sendPackets(new S_SkillSound(pcObjid, 5515));
				pc.sendPackets(new S_ServerMessage(1300)); // 神秘力量的影响正在发生。
				pc.save();
			} else {
				pc.sendPackets(new S_ServerMessage(673)); // 等级%d以下才能使用此道具。
			}
		}
		// 经验丹 (10万经验值)
		else if (itemId == 5051) {
			if (pc.getLevel() <= 98) {
				pc.setExp(pc.getExp() + 100000);
				pc.getInventory().removeItem(item, 1);
				//pc.sendPackets(new S_SkillSound(pcObjid, 5651));
				pc.sendPackets(new S_SystemMessage("你的经验值增加了10W！"));
				pc.save();
			} else {
				pc.sendPackets(new S_ServerMessage(673)); // 等级%d以下才能使用此道具。
			}
		}
		// 经验丹 (20万经验值)
		else if (itemId == 5052) {
			if (pc.getLevel() <= 98) {
				pc.setExp(pc.getExp() + 200000);
				pc.getInventory().removeItem(item, 1);
				//pc.sendPackets(new S_SkillSound(pcObjid, 5638));
				pc.sendPackets(new S_SystemMessage("你的经验值增加了20W！"));
				pc.save();
			} else {
				pc.sendPackets(new S_ServerMessage(673)); // 等级%d以下才能使用此道具。
			}
		}
		// 彩票 (大乐透)
		else if (itemId == 5054) {
			int itemId1 = 5000;  // 奖品 (元宝)
			String sNum = "", pNum = "";
		// 系统数字
			while(sNum.split(",").length < 6) {
				int sk  = 1 + (int) (Math.random() * 49);
				if(sNum.indexOf(sk + ",") < 0)
					sNum += String.valueOf(sk) + ",";
			}
		// 玩家数字
			while(pNum.split(",").length < 6) {
				int pk  = 1 + (int) (Math.random() * 49);
				if(pNum.indexOf(pk + ",") < 0)
					pNum += String.valueOf(pk) + ",";
			}
			int ch = 0;
			int iloop = sNum.split(",").length;
			String pk[] = pNum.split(",");
			for(int i=0; i<iloop; i++) {
				if((","+sNum).indexOf(","+pk[i]+",") >= 0)
					ch++;
			}

			pc.sendPackets(new S_SystemMessage(sNum));
			pc.sendPackets(new S_SystemMessage(pNum));
			pc.sendPackets(new S_SystemMessage("对中了" + ch + "个号码!"));
			pc.getInventory().removeItem(item,1);

			switch(ch)
			{

			case 3:
				pc.sendPackets(new S_ServerMessage(534)); // 你的彩卷中了 "再来一次" !!
				@SuppressWarnings("unused")
				L1ItemInstance item1 = pc.getInventory().storeItem(itemId1, 1000);		// 1000元宝
				pc.sendPackets(new S_SkillSound(pc.getId(), 2011));
				pc.broadcastPacket(new S_SkillSound(pc.getId(), 2011));
				break;
			case 4:
				pc.sendPackets(new S_ServerMessage(535)); // 你中了10,000元宝!!
				@SuppressWarnings("unused")
				L1ItemInstance item2 = pc.getInventory().storeItem(itemId1, 10000);		// 1万元宝
				pc.sendPackets(new S_SkillSound(pc.getId(), 2011));
				pc.broadcastPacket(new S_SkillSound(pc.getId(), 2011));
				break;
			case 5:
				pc.sendPackets(new S_ServerMessage(536)); // 你中了100,000元宝!!!
				@SuppressWarnings("unused")
				L1ItemInstance item3 = pc.getInventory().storeItem(itemId1, 100000);	// 10万元宝
				pc.sendPackets(new S_SkillSound(pc.getId(), 2020));
				pc.broadcastPacket(new S_SkillSound(pc.getId(), 2020));
				break;
			case 6:
				pc.sendPackets(new S_ServerMessage(537)); // 恭喜你!!!你中了500,000元宝!!!!!!!!
				@SuppressWarnings("unused")
				L1ItemInstance item4 = pc.getInventory().storeItem(itemId1, 500000);	// 50万元宝
				pc.sendPackets(new S_SkillSound(pc.getId(), 2047));
				pc.broadcastPacket(new S_SkillSound(pc.getId(), 2047));
				break;
			default:
				pc.sendPackets(new S_ServerMessage(533)); // 你的彩卷并没有中奖。
				break;
			}
		}
	}

}
