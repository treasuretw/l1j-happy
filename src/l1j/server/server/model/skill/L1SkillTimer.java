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
package l1j.server.server.model.skill;

import static l1j.server.server.model.skill.L1SkillId.*;

import java.util.concurrent.ScheduledFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

import l1j.server.Config;
import l1j.server.server.GeneralThreadPool;
import l1j.server.server.datatables.SkillsTable;
import l1j.server.server.model.L1Character;
import l1j.server.server.model.L1EffectSpawn;
import l1j.server.server.model.L1PolyMorph;
import l1j.server.server.model.L1Teleport;
import l1j.server.server.model.Instance.L1MonsterInstance;
import l1j.server.server.model.Instance.L1NpcInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.Instance.L1PetInstance;
import l1j.server.server.model.Instance.L1SummonInstance;
import l1j.server.server.serverpackets.S_CurseBlind;
import l1j.server.server.serverpackets.S_Dexup;
import l1j.server.server.serverpackets.S_HPUpdate;
import l1j.server.server.serverpackets.S_Liquor;
import l1j.server.server.serverpackets.S_MPUpdate;
import l1j.server.server.serverpackets.S_OwnCharAttrDef;
import l1j.server.server.serverpackets.S_OwnCharStatus;
import l1j.server.server.serverpackets.S_PacketBox;
import l1j.server.server.serverpackets.S_Paralysis;
import l1j.server.server.serverpackets.S_Poison;
import l1j.server.server.serverpackets.S_SPMR;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.serverpackets.S_SkillBrave;
import l1j.server.server.serverpackets.S_SkillHaste;
import l1j.server.server.serverpackets.S_SkillIconAura;
import l1j.server.server.serverpackets.S_SkillIconBlessOfEva;
import l1j.server.server.serverpackets.S_SkillIconBloodstain;
import l1j.server.server.serverpackets.S_SkillIconShield;
import l1j.server.server.serverpackets.S_SkillIconWindShackle;
import l1j.server.server.serverpackets.S_SkillIconWisdomPotion;
import l1j.server.server.serverpackets.S_Strup;
import l1j.server.server.serverpackets.S_SystemMessage;
import l1j.server.server.templates.L1Skills;
import l1j.server.server.utils.Random;

//import l1j.server.Config;//TODO 新增在线一段时间给物品
import l1j.server.server.model.L1World;//TODO 新增在线一段时间给物品
import l1j.server.server.model.L1Inventory;//TODO 新增在线一段时间给物品
import l1j.server.server.model.Instance.L1ItemInstance;//TODO 新增在线一段时间给物品
import l1j.server.server.datatables.ItemTable;//TODO 新增在线一段时间给物品

public interface L1SkillTimer {
	public int getRemainingTime();

	public void begin();

	public void end();

	public void kill();
}

/*
 * XXX 2008/02/13 vala 本来、このクラスはあるべきではないが暂定处置。
 */
class L1SkillStop {
	public static void stopSkill(L1Character cha, int skillId) {
		if (skillId == LIGHT) { // ライト
			if (cha instanceof L1PcInstance) {
				if (!cha.isInvisble()) {
					L1PcInstance pc = (L1PcInstance) cha;
					pc.turnOnOffLight();
				}
			}
		}
		else if (skillId == GLOWING_AURA) { // 王族魔法 (激励士气)
			cha.addHitup(-5);
			cha.addBowHitup(-5);
			cha.addMr(-20);
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_SPMR(pc));
				pc.sendPackets(new S_SkillIconAura(113, 0));
			}
		}
		else if (skillId == SHINING_AURA) { // 王族魔法 (钢铁士气)
			cha.addAc(8);
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_SkillIconAura(114, 0));
			}
		}
		else if (skillId == BRAVE_AURA) {	// 王族魔法 (冲击士气)
			cha.addDmgup(-5);
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_SkillIconAura(116, 0));
			}
		}
		else if (skillId == SHIELD) {		// 法师魔法 (防护罩)
			cha.addAc(2);
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_SkillIconShield(5, 0));
			}
		}
		else if (skillId == BLIND_HIDING) {		// 黑暗妖精魔法 (暗隐术)
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.delBlindHiding();
			}
		}
		else if (skillId == SHADOW_ARMOR) {		// 黑暗妖精魔法 (影之防护)
			cha.addAc(3);
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_SkillIconShield(3, 0));
			}
		}
		else if (skillId == DRESS_DEXTERITY) {	// 黑暗妖精魔法 (敏捷提升)
			cha.addDex((byte) -2);
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_Dexup(pc, 2, 0));
			}
		}
		else if (skillId == DRESS_MIGHTY) {		// 黑暗妖精魔法 (力量提升)
			cha.addStr((byte) -2);
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_Strup(pc, 2, 0));
			}
		}
		else if (skillId == SHADOW_FANG) {		// 黑暗妖精魔法 (暗影之牙)
			cha.addDmgup(-5);
		}
		else if (skillId == ENCHANT_WEAPON) {	// 法师魔法 (拟似魔法武器)
			cha.addDmgup(-2);
		}
		else if (skillId == BLESSED_ARMOR) {	// 法师魔法 (铠甲护持)
			cha.addAc(3);
		}
		else if (skillId == EARTH_BLESS) {		// 妖精魔法 (大地的祝福)
			cha.addAc(7);
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_SkillIconShield(7, 0));
			}
		}
		else if (skillId == RESIST_MAGIC) {		// 妖精魔法 (魔法防御)
			cha.addMr(-10);
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_SPMR(pc));
			}
		}
		else if (skillId == CLEAR_MIND) {		// 妖精魔法 (精化精神)
			cha.addWis((byte) -3);
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.resetBaseMr();
			}
		}
		else if (skillId == RESIST_ELEMENTAL) {	// 妖精魔法 (属性防御)
			cha.addWind(-10);
			cha.addWater(-10);
			cha.addFire(-10);
			cha.addEarth(-10);
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_OwnCharAttrDef(pc));
			}
		}
		else if (skillId == ELEMENTAL_PROTECTION) { // 妖精魔法 (单属性防御)
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				int attr = pc.getElfAttr();
				if (attr == 1) {
					cha.addEarth(-50);
				} else if (attr == 2) {
					cha.addFire(-50);
				} else if (attr == 4) {
					cha.addWater(-50);
				} else if (attr == 8) {
					cha.addWind(-50);
				}
				pc.sendPackets(new S_OwnCharAttrDef(pc));
			}
		}
		else if (skillId == ELEMENTAL_FALL_DOWN) { // 妖精魔法 (弱化属性)
			int attr = cha.getAddAttrKind();
			int i = 50;
			switch (attr) {
				case 1:
					cha.addEarth(i);
					break;
				case 2:
					cha.addFire(i);
					break;
				case 4:
					cha.addWater(i);
					break;
				case 8:
					cha.addWind(i);
					break;
				default:
					break;
			}
			cha.setAddAttrKind(0);
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_OwnCharAttrDef(pc));
			}
		}
		else if (skillId == IRON_SKIN) { // 妖精魔法 (钢铁防护)
			cha.addAc(10);
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_SkillIconShield(10, 0));
			}
		}
		else if (skillId == EARTH_SKIN) { // 妖精魔法 (大地防护)
			cha.addAc(6);
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_SkillIconShield(6, 0));
			}
		}
		else if (skillId == PHYSICAL_ENCHANT_STR) { // 法师魔法 (体魄强健术)：STR
			cha.addStr((byte) -5);
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_Strup(pc, 5, 0));
			}
		}
		else if (skillId == PHYSICAL_ENCHANT_DEX) { // 法师魔法 (通畅气脉术)：DEX
			cha.addDex((byte) -5);
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_Dexup(pc, 5, 0));
			}
		}
		else if (skillId == FIRE_WEAPON) { // 妖精魔法 (火焰武器)
			cha.addDmgup(-4);
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_SkillIconAura(147, 0));
			}
		}
		else if (skillId == FIRE_BLESS) { // 妖精魔法 (烈炎气息)
			cha.addDmgup(-4);
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_SkillIconAura(154, 0));
			}
		}
		else if (skillId == BURNING_WEAPON) { // 妖精魔法 (烈炎武器)
			cha.addDmgup(-6);
			cha.addHitup(-3);
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_SkillIconAura(162, 0));
			}
		}
		else if (skillId == BLESS_WEAPON) { // 法师魔法 (祝福魔法武器)
			cha.addDmgup(-2);
			cha.addHitup(-2);
			cha.addBowHitup(-2);
		}
		else if (skillId == WIND_SHOT) { // 妖精魔法 (风之神射)
			cha.addBowHitup(-6);
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_SkillIconAura(148, 0));
			}
		}
		else if (skillId == STORM_EYE) { // 妖精魔法 (暴风之眼)
			cha.addBowHitup(-2);
			cha.addBowDmgup(-3);
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_SkillIconAura(155, 0));
			}
		}
		else if (skillId == STORM_SHOT) { // 妖精魔法 (暴风神射)
			cha.addBowDmgup(-5);
			cha.addBowHitup(1);
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_SkillIconAura(165, 0));
			}
		}
		else if (skillId == BERSERKERS) { // 法师魔法 (狂暴术)
			cha.addAc(-10);
			cha.addDmgup(-5);
			cha.addHitup(-2);
		}
		else if (skillId == SHAPE_CHANGE) { // 法师魔法 (变形术)
			L1PolyMorph.undoPoly(cha);
		}
		else if (skillId == ADVANCE_SPIRIT) { // 法师魔法 (灵魂升华)
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxHp(-pc.getAdvenHp());
				pc.addMaxMp(-pc.getAdvenMp());
				pc.setAdvenHp(0);
				pc.setAdvenMp(0);
				pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc.getMaxHp()));
				if (pc.isInParty()) { // 组队中
					pc.getParty().updateMiniHP(pc);
				}
				pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc.getMaxMp()));
			}
		}
		else if ((skillId == HASTE) || (skillId == GREATER_HASTE)) { // 法师魔法 (加速术)(强力加速术)
			cha.setMoveSpeed(0);
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_SkillHaste(pc.getId(), 0, 0));
				pc.broadcastPacket(new S_SkillHaste(pc.getId(), 0, 0));
			}
		}
		else if ((skillId == HOLY_WALK) || (skillId == MOVING_ACCELERATION) || (skillId == WIND_WALK) || (skillId == BLOODLUST)) { // 神圣疾走、行走加速、风之疾走、血之渴望
			cha.setBraveSpeed(0);
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_SkillBrave(pc.getId(), 0, 0));
				pc.broadcastPacket(new S_SkillBrave(pc.getId(), 0, 0));
			}
		}
		else if (skillId == ILLUSION_OGRE) { // 幻觉：欧吉
			cha.addDmgup(-4);
			cha.addHitup(-4);
			cha.addBowDmgup(-4);
			cha.addBowHitup(-4);
		}
		else if (skillId == ILLUSION_LICH) { // 幻觉：巫妖
			cha.addSp(-2);
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_SPMR(pc));
			}
		}
		else if (skillId == ILLUSION_DIA_GOLEM) { // 幻觉：钻石高仑
			cha.addAc(20);
		}
		else if (skillId == ILLUSION_AVATAR) { // 幻觉：化身
			cha.addDmgup(-10);
			cha.addBowDmgup(-10);
		}
		else if (skillId == INSIGHT) { // 洞察
			cha.addStr((byte) -1);
			cha.addCon((byte) -1);
			cha.addDex((byte) -1);
			cha.addWis((byte) -1);
			cha.addInt((byte) -1);
		}
		else if (skillId == PANIC) { // 恐慌
			cha.addStr((byte) 1);
			cha.addCon((byte) 1);
			cha.addDex((byte) 1);
			cha.addWis((byte) 1);
			cha.addInt((byte) 1);
		}

		// ****** 状态变化が解けた场合
		else if ((skillId == CURSE_BLIND) || (skillId == DARKNESS)) { //暗盲咒术,黑暗之影
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_CurseBlind(0));
			}
		}
		else if (skillId == CURSE_PARALYZE) { // 木乃伊的诅咒
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_Poison(pc.getId(), 0));
				pc.broadcastPacket(new S_Poison(pc.getId(), 0));
				pc.sendPackets(new S_Paralysis(S_Paralysis.TYPE_PARALYSIS, false));
			}
		}
		else if (skillId == WEAKNESS) { // 弱化术
			cha.addDmgup(5);
			cha.addHitup(1);
		}
		else if (skillId == DISEASE) { // 疾病术
			cha.addDmgup(6);
			cha.addAc(-12);
		}
		else if ((skillId == ICE_LANCE // 冰矛围篱
				)
				|| (skillId == FREEZING_BLIZZARD // 冰雪飓风
				) || (skillId == FREEZING_BREATH) // 寒冰喷吐
				|| (skillId == ICE_LANCE_COCKATRICE) // 亚力安冰矛围篱
				|| (skillId == ICE_LANCE_BASILISK)) { // 邪恶蜥蜴冰矛围篱
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_Poison(pc.getId(), 0));
				pc.broadcastPacket(new S_Poison(pc.getId(), 0));
				pc.sendPackets(new S_Paralysis(S_Paralysis.TYPE_FREEZE, false));
			}
			else if ((cha instanceof L1MonsterInstance) || (cha instanceof L1SummonInstance) || (cha instanceof L1PetInstance)) {
				L1NpcInstance npc = (L1NpcInstance) cha;
				npc.broadcastPacket(new S_Poison(npc.getId(), 0));
				npc.setParalyzed(false);
			}
		}
		else if (skillId == EARTH_BIND) { // 大地屏障
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_Poison(pc.getId(), 0));
				pc.broadcastPacket(new S_Poison(pc.getId(), 0));
				pc.sendPackets(new S_Paralysis(S_Paralysis.TYPE_FREEZE, false));
			}
			else if ((cha instanceof L1MonsterInstance) || (cha instanceof L1SummonInstance) || (cha instanceof L1PetInstance)) {
				L1NpcInstance npc = (L1NpcInstance) cha;
				npc.broadcastPacket(new S_Poison(npc.getId(), 0));
				npc.setParalyzed(false);
			}
		}
		else if (skillId == SHOCK_STUN) { // 冲击之晕
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_Paralysis(S_Paralysis.TYPE_STUN, false));
			} else if ((cha instanceof L1MonsterInstance) || (cha instanceof L1SummonInstance) || (cha instanceof L1PetInstance)) {
				L1NpcInstance npc = (L1NpcInstance) cha;
				npc.setParalyzed(false);
			}
		}
		else if (skillId == BONE_BREAK_START) { // 骷髅毁坏 (发动)
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_Paralysis(S_Paralysis.TYPE_STUN, true));
				pc.setSkillEffect(BONE_BREAK_END, 1 * 1000);
			} else if ((cha instanceof L1MonsterInstance) || (cha instanceof L1SummonInstance) || (cha instanceof L1PetInstance)) {
				L1NpcInstance npc = (L1NpcInstance) cha;
				npc.setParalyzed(true);
				npc.setSkillEffect(BONE_BREAK_END, 1 * 1000);
			}
		}
		else if (skillId == BONE_BREAK_END) { // 骷髅毁坏 (结束)
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_Paralysis(S_Paralysis.TYPE_STUN, false));
			} else if ((cha instanceof L1MonsterInstance) || (cha instanceof L1SummonInstance) || (cha instanceof L1PetInstance)) {
				L1NpcInstance npc = (L1NpcInstance) cha;
				npc.setParalyzed(false);
			}
		}
		else if (skillId == FOG_OF_SLEEPING) { // 沉睡之雾
			cha.setSleeped(false);
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_Paralysis(S_Paralysis.TYPE_SLEEP, false));
				pc.sendPackets(new S_OwnCharStatus(pc));
			}
		}
		else if (skillId == ABSOLUTE_BARRIER) { // 绝对屏障
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.startHpRegeneration();
				pc.startMpRegeneration();
				pc.startHpRegenerationByDoll();
				pc.startMpRegenerationByDoll();
			}
		}
		else if (skillId == MEDITATION) { // 冥想术
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMpr(-5);
			}
		}
		else if (skillId == CONCENTRATION) { // 专注
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMpr(-2);
			}
		}
		else if (skillId == WIND_SHACKLE) { // 风之枷锁
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_SkillIconWindShackle(pc.getId(), 0));
				pc.broadcastPacket(new S_SkillIconWindShackle(pc.getId(), 0));
			}
		}
		else if ((skillId == SLOW) || (skillId == ENTANGLE) || (skillId == MASS_SLOW)) { // 缓速、地面障碍、集体缓速术
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_SkillHaste(pc.getId(), 0, 0));
				pc.broadcastPacket(new S_SkillHaste(pc.getId(), 0, 0));
			}
			cha.setMoveSpeed(0);
		}
		else if (skillId == STATUS_FREEZE) { // 束缚术
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_Paralysis(S_Paralysis.TYPE_BIND, false));
			}
			else if ((cha instanceof L1MonsterInstance) || (cha instanceof L1SummonInstance) || (cha instanceof L1PetInstance)) {
				L1NpcInstance npc = (L1NpcInstance) cha;
				npc.setParalyzed(false);
			}
		}
		else if (skillId == THUNDER_GRAB_START) { //夺命之雷(发动)
			L1Skills _skill = SkillsTable.getInstance().getTemplate(THUNDER_GRAB); // 夺命之雷
			int _fetterDuration = _skill.getBuffDuration() * 1000;
			cha.setSkillEffect(STATUS_FREEZE, _fetterDuration); //束缚术
			L1EffectSpawn.getInstance().spawnEffect(81182, _fetterDuration, cha.getX(), cha.getY(), cha.getMapId());
		}
		else if (skillId == GUARD_BRAKE) { // 护卫毁灭
			cha.addAc(-15);
		}
		else if (skillId == HORROR_OF_DEATH) { // 惊悚死神
			cha.addStr(5);
			cha.addInt(5);
		}
		else if (skillId == STATUS_CUBE_IGNITION_TO_ALLY) { // 立方:燃烧 (友方)
			cha.addFire(-30);
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_OwnCharAttrDef(pc));
			}
		}
		else if (skillId == STATUS_CUBE_QUAKE_TO_ALLY) { // 立方:地裂 (友方)
			cha.addEarth(-30);
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_OwnCharAttrDef(pc));
			}
		}
		else if (skillId == STATUS_CUBE_SHOCK_TO_ALLY) { // 立方:冲击 (友方)
			cha.addWind(-30);
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_OwnCharAttrDef(pc));
			}
		}
		else if (skillId == STATUS_CUBE_IGNITION_TO_ENEMY) { // 立方:燃烧 (敌方)
		}
		else if (skillId == STATUS_CUBE_QUAKE_TO_ENEMY) { // 立方:地裂 (敌方)
		}
		else if (skillId == STATUS_CUBE_SHOCK_TO_ENEMY) { // 立方:冲击 (敌方)
		}
		else if (skillId == STATUS_MR_REDUCTION_BY_CUBE_SHOCK) { // キューブ[ショック]によるMR减少

		}

		// 活动卷轴 (活动设定限制时间)
		else if (skillId == 1800) {
			L1PcInstance pc = (L1PcInstance)cha;
			if((cha instanceof L1PcInstance)) {
				pc.sendPackets(new S_SystemMessage("你已经进入活动地图。"));
				pc.setSkillEffect(1801, 125 * 60000); // 倒计时120分钟 (2小时)
			}
		}

		else if(skillId == 1801) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
					L1Teleport.teleport(pc, 33080, 33392, (short)4, 5, true);
					pc.sendPackets(new S_SystemMessage("你参加活动的时间已到，传送至银骑士村。"));
			}
		}
		// 活动卷轴 (活动设定限制时间)
		
		// TODO 猜数字 by aloha777
		else if (skillId==7005) {
			L1PcInstance pc = (L1PcInstance)cha;
			if((cha instanceof L1PcInstance)) {
				pc.sendPackets(new S_Paralysis(5, true));
				int rndo = Random.nextInt(99) + 1;
				pc.setGuessmath(rndo);
				pc.sendPackets(new S_SystemMessage("猜数字大小游戏开始，请输入1~99的数字..."));
				pc.setSkillEffect(7006, 5 * 60000); // 倒计时五分钟
			}
		}
		if(skillId == 7006) {
			L1PcInstance pc = (L1PcInstance)cha;
			if(cha instanceof L1PcInstance)
				pc.sendPackets(new S_SystemMessage("时间到了...很遗憾，下次再接再励吧!!"));
			pc.sendPackets(new S_Paralysis(5, false));
		}

		// TODO 在线一段时间给物品 by 阿杰 add
		else if (skillId == 1920) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				L1ItemInstance item = ItemTable.getInstance().createItem(Config.GI);
				item.setCount(Config.GIC);
				if (item != null) {
					if (pc.getInventory().checkAddItem(item, 1) == L1Inventory.OK) {
						pc.getInventory().storeItem(item);
					} else { // 持てない场合は地面に落とす 处理のキャンセルはしない（不正防止）
						L1World.getInstance()
						.getInventory(pc.getX(), pc.getY(),
								pc.getMapId()).storeItem(item);
					}
					pc.sendPackets(new S_ServerMessage(403, item.getLogName())); // %0を手に入
				}
			}
			cha.setSkillEffect(1920, Config.GIT * 60000);// 3分钟
			// TODO 在线一段时间给物品 by 阿杰 end
			// cha.addMr(10);
			// if (cha instanceof L1PcInstance) {
			// L1PcInstance pc = (L1PcInstance) cha;
			// pc.sendPackets(new S_SPMR(pc));
			// }
		}
		else if (skillId == STATUS_CUBE_BALANCE) { // 立方:和谐
		}

		// ****** アイテム关系
		else if ((skillId == STATUS_BRAVE)
				|| (skillId == STATUS_ELFBRAVE)
				|| (skillId == STATUS_BRAVE2)) { // 二段加速
			cha.setBraveSpeed(0);
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_SkillBrave(pc.getId(), 0, 0));
				pc.broadcastPacket(new S_SkillBrave(pc.getId(), 0, 0));
			}
		}
		else if (skillId == STATUS_THIRD_SPEED) { // 三段加速
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_Liquor(pc.getId(), 0)); // 人物 * 1.15
				pc.broadcastPacket(new S_Liquor(pc.getId(), 0)); // 人物 * 1.15
			}
		}
		/** 生命之树果实 */
		/*else if (skillId == STATUS_RIBRAVE) { // ユグドラの实
			// XXX ユグドラの实のアイコンを消す方法が不明
			cha.setBraveSpeed(0);
		}*/
		else if (skillId == STATUS_HASTE) { // 一段加速
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_SkillHaste(pc.getId(), 0, 0));
				pc.broadcastPacket(new S_SkillHaste(pc.getId(), 0, 0));
			}
			cha.setMoveSpeed(0);
		}
		else if (skillId == STATUS_BLUE_POTION) { // 蓝色药水
		}
		else if (skillId == STATUS_UNDERWATER_BREATH) { // 人鱼之鳞与受祝福的伊娃之鳞
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_SkillIconBlessOfEva(pc.getId(), 0));
			}
		}
		else if (skillId == STATUS_WISDOM_POTION) { // 智慧药水
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				cha.addSp(-2);
				pc.sendPackets(new S_SkillIconWisdomPotion(0));
			}
		}
		else if (skillId == STATUS_CHAT_PROHIBITED) { // 禁言
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_ServerMessage(288)); // 你现在已经可以交谈了。
			}
		}

		// ****** 毒关系
		else if (skillId == STATUS_POISON) { // 毒伤害
			cha.curePoison();
		}

		// TODO ****** 商城状态 ******
		// 生命升华药水
		else if (skillId == BUFF_D) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxHp(-120);
				pc.sendPackets(new S_OwnCharStatus(pc));
			}
		}
		// 魔法升华药水
		else if (skillId == BUFF_E) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxMp(-120);
				pc.sendPackets(new S_OwnCharStatus(pc));
			}
		}
		// 魔法抵抗药水
		else if (skillId == BUFF_F) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMr(-10);
				pc.sendPackets(new S_SPMR(pc));
			}
		}
		// 力量药水
		else if (skillId == BUFF_G) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addHitup(-2);
				pc.addDmgup(-2);
			}
		}
		// 坚韧药水
		else if (skillId == BUFF_H) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxHp(-100);
				pc.sendPackets(new S_OwnCharStatus(pc));
			}
		}
		// 暴怒药水
		else if (skillId == BUFF_I) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addHitup(-2);
				pc.addDmgup(-2);	
				pc.addMaxHp(-100);
				pc.sendPackets(new S_OwnCharStatus(pc));
			}
		}
		// 智力药水
		else if (skillId == BUFF_J) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addSp(-3);
				pc.sendPackets(new S_SPMR(pc));
			}
		}
		// 活化药水
		else if (skillId == BUFF_K) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxMp(-80);
				pc.sendPackets(new S_OwnCharStatus(pc));
			}
		}
		// 巫师药水
		else if (skillId == BUFF_L) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addSp(-3);
				pc.addMaxMp(-80);
				pc.sendPackets(new S_SPMR(pc));
				pc.sendPackets(new S_OwnCharStatus(pc));
			}
		}
		// 突袭药水
		else if (skillId == BUFF_M) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addSp(-1);
				pc.addDmgup(-1);
				pc.sendPackets(new S_SPMR(pc));
				pc.sendPackets(new S_OwnCharStatus(pc));
			}
		}
		// 强力恢复药水
		else if (skillId == BUFF_N) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxMp(-30);
				pc.addMaxHp(-30);
				pc.sendPackets(new S_OwnCharStatus(pc));
			}
		}
		// 疯狂力量药水
		else if (skillId == BUFF_O) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addSp(-1);
				pc.addMaxMp(-30);
				pc.addMaxHp(-30);
				pc.sendPackets(new S_SPMR(pc));
				pc.sendPackets(new S_OwnCharStatus(pc));
			}
		}

		// 在线一段时间送经验
		else if (skillId == ONLINE_EXP) {
			L1PcInstance pc = (L1PcInstance) cha;
			if (pc.getMapId() == Config.OnlineExpMapId // 判断地图与坐标位置
					&& pc.getX() >= Config.OnlineExpStartX && pc.getX() <= Config.OnlineExpEndX
					&& pc.getY() >= Config.OnlineExpStartY && pc.getY() <= Config.OnlineExpEndY
					&& pc.getInventory().checkItem(Config.OnlineExpItem) // 需要的道具编号
					&& cha instanceof L1PcInstance
					&& (!pc.isDead()) // 角色没有死亡
					&& pc.getLevel() < 99) { // 多少级以后不增加
				int i = Config.OnlineExpValue; // 一次给予的经验值
				int time = Config.OnlineExpTime; // 间隔时间
				int item = Config.OnlineExpItem; // 需要的道具
				int count = Config.OnlineExpItemCount; // 道具的数量
				pc.addExp(i);
				pc.sendPackets(new S_OwnCharStatus(pc));
				pc.sendPackets(new S_SystemMessage("您每 " + time + " 秒消耗 " + count + " 元宝,获得 " + i + " 经验。"));
				pc.setSkillEffect(ONLINE_EXP, time * 1000); // 单位：秒 
				pc.getInventory().consumeItem(item, count);	// 判断一次消耗的道具与数量
			}
		}

		// ****** 料理关系
		else if ((skillId == COOKING_1_0_N) || (skillId == COOKING_1_0_S)) { // 漂浮之眼肉排
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addWind(-10);
				pc.addWater(-10);
				pc.addFire(-10);
				pc.addEarth(-10);
				pc.sendPackets(new S_OwnCharAttrDef(pc));
				pc.sendPackets(new S_PacketBox(53, 0, 0));
				pc.setCookingId(0);
			}
		}
		else if ((skillId == COOKING_1_1_N) || (skillId == COOKING_1_1_S)) { // 烤熊肉
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxHp(-30);
				pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc.getMaxHp()));
				if (pc.isInParty()) { // 组队中
					pc.getParty().updateMiniHP(pc);
				}
				pc.sendPackets(new S_PacketBox(53, 1, 0));
				pc.setCookingId(0);
			}
		}
		else if ((skillId == COOKING_1_2_N) || (skillId == COOKING_1_2_S)) { // 煎饼
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_PacketBox(53, 2, 0));
				pc.setCookingId(0);
			}
		}
		else if ((skillId == COOKING_1_3_N) || (skillId == COOKING_1_3_S)) { // 蚂蚁腿起司
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addAc(1);
				pc.sendPackets(new S_PacketBox(53, 3, 0));
				pc.setCookingId(0);
			}
		}
		else if ((skillId == COOKING_1_4_N) || (skillId == COOKING_1_4_S)) { // 水果沙拉
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxMp(-20);
				pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc.getMaxMp()));
				pc.sendPackets(new S_PacketBox(53, 4, 0));
				pc.setCookingId(0);
			}
		}
		else if ((skillId == COOKING_1_5_N) || (skillId == COOKING_1_5_S)) { // 水果糖醋肉
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_PacketBox(53, 5, 0));
				pc.setCookingId(0);
			}
		}
		else if ((skillId == COOKING_1_6_N) || (skillId == COOKING_1_6_S)) { // 烤山猪肉串
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMr(-5);
				pc.sendPackets(new S_SPMR(pc));
				pc.sendPackets(new S_PacketBox(53, 6, 0));
				pc.setCookingId(0);
			}
		}
		else if ((skillId == COOKING_1_7_N) || (skillId == COOKING_1_7_S)) { // 蘑菇汤
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_PacketBox(53, 7, 0));
				pc.setDessertId(0);
			}
		}
		else if ((skillId == COOKING_2_0_N) || (skillId == COOKING_2_0_S)) { // 鱼子酱
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_PacketBox(53, 8, 0));
				pc.setCookingId(0);
			}
		}
		else if ((skillId == COOKING_2_1_N) || (skillId == COOKING_2_1_S)) { // 鳄鱼肉排
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxHp(-30);
				pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc.getMaxHp()));
				if (pc.isInParty()) { // 组队中
					pc.getParty().updateMiniHP(pc);
				}
				pc.addMaxMp(-30);
				pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc.getMaxMp()));
				pc.sendPackets(new S_PacketBox(53, 9, 0));
				pc.setCookingId(0);
			}
		}
		else if ((skillId == COOKING_2_2_N) || (skillId == COOKING_2_2_S)) { // 龙龟蛋饼干
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addAc(2);
				pc.sendPackets(new S_PacketBox(53, 10, 0));
				pc.setCookingId(0);
			}
		}
		else if ((skillId == COOKING_2_3_N) || (skillId == COOKING_2_3_S)) { // 烤奇异鹦鹉
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_PacketBox(53, 11, 0));
				pc.setCookingId(0);
			}
		}
		else if ((skillId == COOKING_2_4_N) || (skillId == COOKING_2_4_S)) { // 毒蝎串烧
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_PacketBox(53, 12, 0));
				pc.setCookingId(0);
			}
		}
		else if ((skillId == COOKING_2_5_N) || (skillId == COOKING_2_5_S)) { // 烤伊莱克顿
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMr(-10);
				pc.sendPackets(new S_SPMR(pc));
				pc.sendPackets(new S_PacketBox(53, 13, 0));
				pc.setCookingId(0);
			}
		}
		else if ((skillId == COOKING_2_6_N) || (skillId == COOKING_2_6_S)) { // 蜘蛛腿串烧
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addSp(-1);
				pc.sendPackets(new S_SPMR(pc));
				pc.sendPackets(new S_PacketBox(53, 14, 0));
				pc.setCookingId(0);
			}
		}
		else if ((skillId == COOKING_2_7_N) || (skillId == COOKING_2_7_S)) { // 蟹肉汤
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_PacketBox(53, 15, 0));
				pc.setDessertId(0);
			}
		}
		else if ((skillId == COOKING_3_0_N) || (skillId == COOKING_3_0_S)) { // 烤奎斯坦修的鳌
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_PacketBox(53, 16, 0));
				pc.setCookingId(0);
			}
		}
		else if ((skillId == COOKING_3_1_N) || (skillId == COOKING_3_1_S)) { // 烤格利芬肉
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxHp(-50);
				pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc.getMaxHp()));
				if (pc.isInParty()) { // 组队中
					pc.getParty().updateMiniHP(pc);
				}
				pc.addMaxMp(-50);
				pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc.getMaxMp()));
				pc.sendPackets(new S_PacketBox(53, 17, 0));
				pc.setCookingId(0);
			}
		}
		else if ((skillId == COOKING_3_2_N) || (skillId == COOKING_3_2_S)) { // 亚力安的尾巴肉排
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_PacketBox(53, 18, 0));
				pc.setCookingId(0);
			}
		}
		else if ((skillId == COOKING_3_3_N) || (skillId == COOKING_3_3_S)) { // 烤巨王龟肉
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addAc(3);
				pc.sendPackets(new S_PacketBox(53, 19, 0));
				pc.setCookingId(0);
			}
		}
		else if ((skillId == COOKING_3_4_N) || (skillId == COOKING_3_4_S)) { // 幼龙翅膀串烧
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMr(-15);
				pc.sendPackets(new S_SPMR(pc));
				pc.addWind(-10);
				pc.addWater(-10);
				pc.addFire(-10);
				pc.addEarth(-10);
				pc.sendPackets(new S_OwnCharAttrDef(pc));
				pc.sendPackets(new S_PacketBox(53, 20, 0));
				pc.setCookingId(0);
			}
		}
		else if ((skillId == COOKING_3_5_N) || (skillId == COOKING_3_5_S)) { // 烤飞龙肉
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addSp(-2);
				pc.sendPackets(new S_SPMR(pc));
				pc.sendPackets(new S_PacketBox(53, 21, 0));
				pc.setCookingId(0);
			}
		}
		else if ((skillId == COOKING_3_6_N) || (skillId == COOKING_3_6_S)) { // 炖深海鱼肉
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxHp(-30);
				pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc.getMaxHp()));
				if (pc.isInParty()) { // 组队中
					pc.getParty().updateMiniHP(pc);
				}
				pc.sendPackets(new S_PacketBox(53, 22, 0));
				pc.setCookingId(0);
			}
		}
		else if ((skillId == COOKING_3_7_N) || (skillId == COOKING_3_7_S)) { // 邪恶蜥蜴蛋汤
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_PacketBox(53, 23, 0));
				pc.setDessertId(0);
			}
		}
		else if (skillId == COOKING_WONDER_DRUG) { // 象牙塔妙药
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addHpr(-10);
				pc.addMpr(-2);
			}
		}
		// ****** 
		else if (skillId == EFFECT_BLESS_OF_MAZU) { // 妈祖的祝福
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addHitup(-3);
				pc.addDmgup(-3);
				pc.addMpr(-2);
			}
		}
		else if (skillId == EFFECT_STRENGTHENING_HP) { // 体力增强卷轴
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxHp(-50);
				pc.addHpr(-4);
				pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc.getMaxHp()));
				if (pc.isInParty()) { // 组队中
					pc.getParty().updateMiniHP(pc);
				}
			}
		}
		else if (skillId == EFFECT_STRENGTHENING_MP) { // 魔力增强卷轴
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxMp(-40);
				pc.addMpr(-4);
				pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc.getMaxMp()));
			}
		}
		else if (skillId == EFFECT_ENCHANTING_BATTLE) { // 强化战斗卷轴
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addHitup(-3);
				pc.addDmgup(-3);
				pc.addBowHitup(-3);
				pc.addBowDmgup(-3);
				pc.addSp(-3);
				pc.sendPackets(new S_SPMR(pc));
			}
		}
		else if (skillId == MIRROR_IMAGE || skillId == UNCANNY_DODGE) { // 镜像、暗影闪避
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addDodge((byte) -5); // 闪避率 - 50%
				// 更新闪避率显示
				pc.sendPackets(new S_PacketBox(88, pc.getDodge()));
			}
		}
		else if (skillId == RESIST_FEAR) { // 恐惧无助
			cha.addNdodge((byte) -5); // 闪避率 + 50%
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				// 更新闪避率显示
				pc.sendPackets(new S_PacketBox(101, pc.getNdodge()));
			}
		}
		else if (skillId == EFFECT_BLOODSTAIN_OF_ANTHARAS) { // 安塔瑞斯的血痕
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addAc(2);
				pc.addWater(-50);
				pc.sendPackets(new S_SkillIconBloodstain(82, 0));
			}
		}
		else if (skillId == EFFECT_BLOODSTAIN_OF_FAFURION) { // 法利昂的血痕
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addWind(-50);
				pc.sendPackets(new S_SkillIconBloodstain(85, 0));
			}
		}
		else if (skillId == EFFECT_MAGIC_STONE_A_1) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxHp(-10);
				pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc.getMaxHp()));
				if (pc.isInParty()) { // 组队中
					pc.getParty().updateMiniHP(pc);
				}
				pc.setMagicStoneLevel((byte) 0);
			}
		}
		else if (skillId == EFFECT_MAGIC_STONE_A_2) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxHp(-20);
				pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc.getMaxHp()));
				if (pc.isInParty()) { // 组队中
					pc.getParty().updateMiniHP(pc);
				}
				pc.setMagicStoneLevel((byte) 0);
			}
		}
		else if (skillId == EFFECT_MAGIC_STONE_A_3) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxHp(-30);
				pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc.getMaxHp()));
				if (pc.isInParty()) { // 组队中
					pc.getParty().updateMiniHP(pc);
				}
				pc.setMagicStoneLevel((byte) 0);
			}
		}
		else if (skillId == EFFECT_MAGIC_STONE_A_4) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxHp(-40);
				pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc.getMaxHp()));
				if (pc.isInParty()) { // 组队中
					pc.getParty().updateMiniHP(pc);
				}
				pc.setMagicStoneLevel((byte) 0);
			}
		}
		else if (skillId == EFFECT_MAGIC_STONE_A_5) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxHp(-50);
				pc.addHpr(-1);
				pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc.getMaxHp()));
				if (pc.isInParty()) { // 组队中
					pc.getParty().updateMiniHP(pc);
				}
				pc.setMagicStoneLevel((byte) 0);
			}
		}
		else if (skillId == EFFECT_MAGIC_STONE_A_6) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxHp(-60);
				pc.addHpr(-2);
				pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc.getMaxHp()));
				if (pc.isInParty()) { // 组队中
					pc.getParty().updateMiniHP(pc);
				}
				pc.setMagicStoneLevel((byte) 0);
			}
		}
		else if (skillId == EFFECT_MAGIC_STONE_A_7) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxHp(-70);
				pc.addHpr(-3);
				pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc.getMaxHp()));
				if (pc.isInParty()) { // 组队中
					pc.getParty().updateMiniHP(pc);
				}
				pc.setMagicStoneLevel((byte) 0);
			}
		}
		else if (skillId == EFFECT_MAGIC_STONE_A_8) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxHp(-80);
				pc.addHpr(-4);
				pc.addHitup(-1);
				pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc.getMaxHp()));
				if (pc.isInParty()) { // 组队中
					pc.getParty().updateMiniHP(pc);
				}
				pc.setMagicStoneLevel((byte) 0);
			}
		}
		else if (skillId == EFFECT_MAGIC_STONE_A_9) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxHp(-100);
				pc.addHpr(-5);
				pc.addHitup(-2);
				pc.addDmgup(-2);
				pc.addStr((byte) -1);
				pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc.getMaxHp()));
				if (pc.isInParty()) { // 组队中
					pc.getParty().updateMiniHP(pc);
				}
				pc.setMagicStoneLevel((byte) 0);
			}
		}
		else if (skillId == EFFECT_MAGIC_STONE_B_1) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxHp(-5);
				pc.addMaxMp(-3);
				pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc.getMaxHp()));
				pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc.getMaxMp()));
				if (pc.isInParty()) { // 组队中
					pc.getParty().updateMiniHP(pc);
				}
				pc.setMagicStoneLevel((byte) 0);
			}
		}
		else if (skillId == EFFECT_MAGIC_STONE_B_2) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxHp(-10);
				pc.addMaxMp(-6);
				pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc.getMaxHp()));
				pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc.getMaxMp()));
				if (pc.isInParty()) { // 组队中
					pc.getParty().updateMiniHP(pc);
				}
				pc.setMagicStoneLevel((byte) 0);
			}
		}
		else if (skillId == EFFECT_MAGIC_STONE_B_3) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxHp(-15);
				pc.addMaxMp(-10);
				pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc.getMaxHp()));
				pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc.getMaxMp()));
				if (pc.isInParty()) { // 组队中
					pc.getParty().updateMiniHP(pc);
				}
				pc.setMagicStoneLevel((byte) 0);
			}
		}
		else if (skillId == EFFECT_MAGIC_STONE_B_4) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxHp(-20);
				pc.addMaxMp(-15);
				pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc.getMaxHp()));
				pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc.getMaxMp()));
				if (pc.isInParty()) { // 组队中
					pc.getParty().updateMiniHP(pc);
				}
				pc.setMagicStoneLevel((byte) 0);
			}
		}
		else if (skillId == EFFECT_MAGIC_STONE_B_5) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxHp(-25);
				pc.addMaxMp(-20);
				pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc.getMaxHp()));
				pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc.getMaxMp()));
				if (pc.isInParty()) { // 组队中
					pc.getParty().updateMiniHP(pc);
				}
				pc.setMagicStoneLevel((byte) 0);
			}
		}
		else if (skillId == EFFECT_MAGIC_STONE_B_6) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxHp(-30);
				pc.addMaxMp(-20);
				pc.addHpr(-1);
				pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc.getMaxHp()));
				pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc.getMaxMp()));
				if (pc.isInParty()) { // 组队中
					pc.getParty().updateMiniHP(pc);
				}
				pc.setMagicStoneLevel((byte) 0);
			}
		}
		else if (skillId == EFFECT_MAGIC_STONE_B_7) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxHp(-35);
				pc.addMaxMp(-20);
				pc.addHpr(-1);
				pc.addMpr(-1);
				pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc.getMaxHp()));
				pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc.getMaxMp()));
				if (pc.isInParty()) { // 组队中
					pc.getParty().updateMiniHP(pc);
				}
				pc.setMagicStoneLevel((byte) 0);
			}
		}
		else if (skillId == EFFECT_MAGIC_STONE_B_8) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxHp(-40);
				pc.addMaxMp(-25);
				pc.addHpr(-2);
				pc.addMpr(-1);
				pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc.getMaxHp()));
				pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc.getMaxMp()));
				if (pc.isInParty()) { // 组队中
					pc.getParty().updateMiniHP(pc);
				}
				pc.setMagicStoneLevel((byte) 0);
			}
		}
		else if (skillId == EFFECT_MAGIC_STONE_B_9) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxHp(-50);
				pc.addMaxMp(-30);
				pc.addHpr(-2);
				pc.addMpr(-2);
				pc.addBowDmgup(-2);
				pc.addBowHitup(-2);
				pc.addDex((byte) -1);
				pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc.getMaxHp()));
				pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc.getMaxMp()));
				if (pc.isInParty()) { // 组队中
					pc.getParty().updateMiniHP(pc);
				}
				pc.setMagicStoneLevel((byte) 0);
			}
		}
		else if (skillId == EFFECT_MAGIC_STONE_C_1) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxMp(-5);
				pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc.getMaxMp()));
				pc.setMagicStoneLevel((byte) 0);
			}
		}
		else if (skillId == EFFECT_MAGIC_STONE_C_2) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxMp(-10);
				pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc.getMaxMp()));
				pc.setMagicStoneLevel((byte) 0);
			}
		}
		else if (skillId == EFFECT_MAGIC_STONE_C_3) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxMp(-15);
				pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc.getMaxMp()));
				pc.setMagicStoneLevel((byte) 0);
			}
		}
		else if (skillId == EFFECT_MAGIC_STONE_C_4) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxMp(-20);
				pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc.getMaxMp()));
				pc.setMagicStoneLevel((byte) 0);
			}
		}
		else if (skillId == EFFECT_MAGIC_STONE_C_5) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxMp(-25);
				pc.addMpr(-1);
				pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc.getMaxMp()));
				pc.setMagicStoneLevel((byte) 0);
			}
		}
		else if (skillId == EFFECT_MAGIC_STONE_C_6) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxMp(-30);
				pc.addMpr(-2);
				pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc.getMaxMp()));
				pc.setMagicStoneLevel((byte) 0);
			}
		}
		else if (skillId == EFFECT_MAGIC_STONE_C_7) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxMp(-35);
				pc.addMpr(-3);
				pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc.getMaxMp()));
				pc.setMagicStoneLevel((byte) 0);
			}
		}
		else if (skillId == EFFECT_MAGIC_STONE_C_8) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxMp(-40);
				pc.addMpr(-4);
				pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc.getMaxMp()));
				pc.setMagicStoneLevel((byte) 0);
			}
		}
		else if (skillId == EFFECT_MAGIC_STONE_C_9) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxMp(-50);
				pc.addMpr(-5);
				pc.addInt((byte)-1);
				pc.addSp(-1);
				pc.sendPackets(new S_SPMR(pc));
				pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc.getMaxMp()));
				pc.setMagicStoneLevel((byte) 0);
			}
		}
		else if (skillId == EFFECT_MAGIC_STONE_D_1) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMr(-2);
				pc.sendPackets(new S_SPMR(pc));
				pc.setMagicStoneLevel((byte) 0);
			}
		}
		else if (skillId == EFFECT_MAGIC_STONE_D_2) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMr(-4);
				pc.sendPackets(new S_SPMR(pc));
				pc.setMagicStoneLevel((byte) 0);
			}
		}
		else if (skillId == EFFECT_MAGIC_STONE_D_3) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMr(-6);
				pc.sendPackets(new S_SPMR(pc));
				pc.setMagicStoneLevel((byte) 0);
			}
		}
		else if (skillId == EFFECT_MAGIC_STONE_D_4) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMr(-8);
				pc.sendPackets(new S_SPMR(pc));
				pc.setMagicStoneLevel((byte) 0);
			}
		}
		else if (skillId == EFFECT_MAGIC_STONE_D_5) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMr(-10);
				pc.addAc(1);
				pc.sendPackets(new S_SPMR(pc));
				pc.setMagicStoneLevel((byte) 0);
			}
		}
		else if (skillId == EFFECT_MAGIC_STONE_D_6) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMr(-10);
				pc.addAc(2);
				pc.sendPackets(new S_SPMR(pc));
				pc.setMagicStoneLevel((byte) 0);
			}
		}
		else if (skillId == EFFECT_MAGIC_STONE_D_7) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMr(-10);
				pc.addAc(3);
				pc.sendPackets(new S_SPMR(pc));
				pc.setMagicStoneLevel((byte) 0);
			}
		}
		else if (skillId == EFFECT_MAGIC_STONE_D_8) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMr(-15);
				pc.addAc(4);
				pc.addDamageReductionByArmor(-1);
				pc.sendPackets(new S_SPMR(pc));
				pc.setMagicStoneLevel((byte) 0);
			}
		}
		else if (skillId == EFFECT_MAGIC_STONE_D_9) {
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMr(-20);
				pc.addAc(5);
				pc.addCon((byte) -1);
				pc.addDamageReductionByArmor(-3);
				pc.sendPackets(new S_SPMR(pc));
				pc.setMagicStoneLevel((byte) 0);
			}
		}
		else if (skillId == EFFECT_MAGIC_EYE_OF_AHTHARTS) { // 地龙之魔眼
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addRegistStone(-3); // 石化耐性

				pc.addDodge((byte) -1); // 闪避率 - 10%
				// 更新闪避率显示
				pc.sendPackets(new S_PacketBox(88, pc.getDodge()));
			}
		}
		else if (skillId == EFFECT_MAGIC_EYE_OF_FAFURION) { // 水龙之魔眼
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.add_regist_freeze(-3); // 寒冰耐性
				// 魔法伤害减免
			}
		}
		else if (skillId == EFFECT_MAGIC_EYE_OF_LINDVIOR) { // 风龙之魔眼
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addRegistSleep(-3); // 睡眠耐性
				// 魔法暴击率
			}
		}
		else if (skillId == EFFECT_MAGIC_EYE_OF_VALAKAS) { // 火龙之魔眼
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addRegistStun(-3); // 昏迷耐性
				pc.addDmgup(-2); // 额外攻击点数
			}
		}
		else if (skillId == EFFECT_MAGIC_EYE_OF_BIRTH) { // 诞生之魔眼
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addRegistBlind(-3); // 闇黑耐性
				// 魔法伤害减免

				pc.addDodge((byte) -1); // 闪避率 - 10%
				// 更新闪避率显示
				pc.sendPackets(new S_PacketBox(88, pc.getDodge()));
			}
		}
		else if (skillId == EFFECT_MAGIC_EYE_OF_FIGURE) { // 形象之魔眼
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addRegistSustain(-3); // 支撑耐性
				// 魔法伤害减免
				// 魔法暴击率

				pc.addDodge((byte) -1); // 闪避率 - 10%
				// 更新闪避率显示
				pc.sendPackets(new S_PacketBox(88, pc.getDodge()));
			}
		}
		else if (skillId == EFFECT_MAGIC_EYE_OF_LIFE) { // 生命之魔眼
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addDmgup(2); // 额外攻击点数
				// 魔法伤害减免
				// 魔法暴击率
				// 防护中毒状态

				pc.addDodge((byte) -1); // 闪避率 - 10%
				// 更新闪避率显示
				pc.sendPackets(new S_PacketBox(88, pc.getDodge()));
			}
		}
		else if (skillId == EFFECT_BLESS_OF_CRAY) { // 卡瑞的祝福
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxHp(-100);
				pc.addMaxMp(-50);
				pc.addHpr(-3);
				pc.addMpr(-3);
				pc.addEarth(-30);
				pc.addDmgup(-1);
				pc.addHitup(-5);
				pc.addWeightReduction(-40);
				pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc.getMaxHp()));
				if (pc.isInParty()) {
					pc.getParty().updateMiniHP(pc);
				}
				pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc.getMaxMp()));
			}
		}
		else if (skillId == EFFECT_BLESS_OF_SAELL) { // 莎尔的祝福
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.addMaxHp(-80);
				pc.addMaxMp(-10);
				pc.addWater(-30);
				pc.addAc(8);
				pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc.getMaxHp()));
				if (pc.isInParty()) {
					pc.getParty().updateMiniHP(pc);
				}
				pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc.getMaxMp()));
			}
		}
		else if (skillId == ERASE_MAGIC) { // 魔法消除
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_SkillIconAura(152, 0));
			}
		}
		else if (skillId == STATUS_CURSE_YAHEE) { // 炎魔的烙印
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_SkillIconAura(221, 0, 1));
			}
		}
		else if (skillId == STATUS_CURSE_BARLOG) { // 火焰之影的烙印
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_SkillIconAura(221, 0, 2));
			}
		}

		if (cha instanceof L1PcInstance) {
			L1PcInstance pc = (L1PcInstance) cha;
			sendStopMessage(pc, skillId);
			pc.sendPackets(new S_OwnCharStatus(pc));
		}
	}

	// メッセージの表示（终了するとき）
	private static void sendStopMessage(L1PcInstance charaPc, int skillid) {
		L1Skills l1skills = SkillsTable.getInstance().getTemplate(skillid);
		if ((l1skills == null) || (charaPc == null)) {
			return;
		}

		int msgID = l1skills.getSysmsgIdStop();
		if (msgID > 0) {
			charaPc.sendPackets(new S_ServerMessage(msgID));
		}
	}
}

class L1SkillTimerThreadImpl extends Thread implements L1SkillTimer {
	public L1SkillTimerThreadImpl(L1Character cha, int skillId, int timeMillis) {
		_cha = cha;
		_skillId = skillId;
		_timeMillis = timeMillis;
	}

	@Override
	public void run() {
		for (int timeCount = _timeMillis / 1000; timeCount > 0; timeCount--) {
			try {
				Thread.sleep(1000);
				_remainingTime = timeCount;
			}
			catch (InterruptedException e) {
				return;
			}
		}
		_cha.removeSkillEffect(_skillId);
	}

	@Override
	public int getRemainingTime() {
		return _remainingTime;
	}

	@Override
	public void begin() {
		GeneralThreadPool.getInstance().execute(this);
	}

	@Override
	public void end() {
		super.interrupt();
		L1SkillStop.stopSkill(_cha, _skillId);
	}

	@Override
	public void kill() {
		if (Thread.currentThread().getId() == super.getId()) {
			return; // 呼び出し元スレッドが自分であれば止めない
		}
		super.interrupt();
	}

	private final L1Character _cha;

	private final int _timeMillis;

	private final int _skillId;

	private int _remainingTime;
}

class L1SkillTimerTimerImpl implements L1SkillTimer, Runnable {
	private static Logger _log = Logger.getLogger(L1SkillTimerTimerImpl.class.getName());

	private ScheduledFuture<?> _future = null;

	public L1SkillTimerTimerImpl(L1Character cha, int skillId, int timeMillis) {
		_cha = cha;
		_skillId = skillId;
		_timeMillis = timeMillis;

		_remainingTime = _timeMillis / 1000;
	}

	@Override
	public void run() {
		_remainingTime--;
		if (_remainingTime <= 0) {
			_cha.removeSkillEffect(_skillId);
		}
	}

	@Override
	public void begin() {
		_future = GeneralThreadPool.getInstance().scheduleAtFixedRate(this, 1000, 1000);
	}

	@Override
	public void end() {
		kill();
		try {
			L1SkillStop.stopSkill(_cha, _skillId);
		}
		catch (Throwable e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
	}

	@Override
	public void kill() {
		if (_future != null) {
			_future.cancel(false);
		}
	}

	@Override
	public int getRemainingTime() {
		return _remainingTime;
	}

	private final L1Character _cha;

	private final int _timeMillis;

	private final int _skillId;

	private int _remainingTime;
}
