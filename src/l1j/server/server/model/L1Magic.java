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
package l1j.server.server.model;

import l1j.server.Config;
import l1j.server.server.ActionCodes;
import l1j.server.server.WarTimeController;
import l1j.server.server.datatables.SkillsTable;
import l1j.server.server.model.Instance.L1ItemInstance;
import l1j.server.server.model.Instance.L1NpcInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.Instance.L1PetInstance;
import l1j.server.server.model.Instance.L1SummonInstance;
import l1j.server.server.serverpackets.S_DoActionGFX;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.serverpackets.S_SkillSound;
import l1j.server.server.templates.L1MagicDoll;
import l1j.server.server.templates.L1Skills;
import l1j.server.server.utils.Random;

import static l1j.server.server.model.skill.L1SkillId.*;

public class L1Magic {

	/** 状态 (谁攻击谁) */
	private int _calcType;

	/** PC 对 PC */
	private final int PC_PC = 1;

	/** PC 对 NPC */
	private final int PC_NPC = 2;

	/** NPC 对 PC */
	private final int NPC_PC = 3;

	/** NPC 对 NPC */
	private final int NPC_NPC = 4;

	/** 目标 */
	private L1Character _target = null;

	/** 角色本身 */
	private L1PcInstance _pc = null;

	/** 目标为 PC */
	private L1PcInstance _targetPc = null;

	/** 怪物 */
	private L1NpcInstance _npc = null;

	/** 目标为 NPC */
	private L1NpcInstance _targetNpc = null;

	private int _leverage = 10; // 1/10倍で表现する。

	public void setLeverage(int i) {
		_leverage = i;
	}

	private int getLeverage() {
		return _leverage;
	}

	public L1Magic(L1Character attacker, L1Character target) {
		_target = target;

		if (attacker instanceof L1PcInstance) {
			if (target instanceof L1PcInstance) {
				_calcType = PC_PC;
				_pc = (L1PcInstance) attacker;
				_targetPc = (L1PcInstance) target;
			}
			else {
				_calcType = PC_NPC;
				_pc = (L1PcInstance) attacker;
				_targetNpc = (L1NpcInstance) target;
			}
		}
		else {
			if (target instanceof L1PcInstance) {
				_calcType = NPC_PC;
				_npc = (L1NpcInstance) attacker;
				_targetPc = (L1PcInstance) target;
			}
			else {
				_calcType = NPC_NPC;
				_npc = (L1NpcInstance) attacker;
				_targetNpc = (L1NpcInstance) target;
			}
		}
	}

	private int getMagicLevel() {
		int magicLevel = 0;
		if ((_calcType == PC_PC) || (_calcType == PC_NPC)) {
			magicLevel = _pc.getMagicLevel();
		}
		else if ((_calcType == NPC_PC) || (_calcType == NPC_NPC)) {
			magicLevel = _npc.getMagicLevel();
		}
		return magicLevel;
	}

	private int getMagicBonus() {
		int magicBonus = 0;
		if ((_calcType == PC_PC) || (_calcType == PC_NPC)) {
			magicBonus = _pc.getMagicBonus();
		}
		else if ((_calcType == NPC_PC) || (_calcType == NPC_NPC)) {
			magicBonus = _npc.getMagicBonus();
		}
		return magicBonus;
	}

	private int getLawful() {
		int lawful = 0;
		if ((_calcType == PC_PC) || (_calcType == PC_NPC)) {
			lawful = _pc.getLawful();
		}
		else if ((_calcType == NPC_PC) || (_calcType == NPC_NPC)) {
			lawful = _npc.getLawful();
		}
		return lawful;
	}

	private int getTargetMr() {
		int mr = 0;
		if ((_calcType == PC_PC) || (_calcType == NPC_PC)) {
			mr = _targetPc.getMr();
		}
		else {
			mr = _targetNpc.getMr();
		}
		return mr;
	}

	/* ■■■■■■■■■■■■■■ 成功判定 ■■■■■■■■■■■■■ */
	// ●●●● 确率系魔法的成功判定 ●●●●
	// 计算方法
	// 攻击侧ポイント：LV + ((MagicBonus * 3) * 魔法固有系数)
	// 防御侧ポイント：((LV / 2) + (MR * 3)) / 2
	// 攻击成功率：攻击侧ポイント - 防御侧ポイント
	public boolean calcProbabilityMagic(int skillId) {
		int probability = 0;
		boolean isSuccess = false;

		// 攻击者为GM权限100%成功
		if ((_pc != null) && _pc.isGm()) {
			return true;
		}

		// 判断特定状态下才可攻击 NPC
		if ((_calcType == PC_NPC) && (_targetNpc != null)) {
			if (_pc.isAttackMiss(_pc, _targetNpc.getNpcTemplate().get_npcId())) {
				return false;
			}
		}

		if (!checkZone(skillId)) {
			return false;
		}
		// 魔法相消术
		if (skillId == CANCELLATION) {
			if ((_calcType == PC_PC) && (_pc != null) && (_targetPc != null)) {
				// 自身100%成功
				if (_pc.getId() == _targetPc.getId()) {
					return true;
				}
				// 同盟100%成功
				if ((_pc.getClanid() > 0) && (_pc.getClanid() == _targetPc.getClanid())) {
					return true;
				}
				// 同队100%成功
				if (_pc.isInParty()) {
					if (_pc.getParty().isMember(_targetPc)) {
						return true;
					}
				}
				// 以外的场合、安全区内无效
				if ((_pc.getZoneType() == 1) || (_targetPc.getZoneType() == 1)) {
					return false;
				}
			}
			// 对象为NPC、使用者为NPC的场合100%成功
			if ((_calcType == PC_NPC) || (_calcType == NPC_PC) || (_calcType == NPC_NPC)) {
				return true;
			}
		}

		// 在大地屏障状态中坏物术、魔法相消术以外无效
		if ((_calcType == PC_PC) || (_calcType == NPC_PC)) {
			if (_targetPc.hasSkillEffect(EARTH_BIND)) {
				if ((skillId != WEAPON_BREAK) && (skillId != CANCELLATION)) {
					return false;
				}
			}
		}
		else {
			if (_targetNpc.hasSkillEffect(EARTH_BIND)) {
				if ((skillId != WEAPON_BREAK) && (skillId != CANCELLATION)) {
					return false;
				}
			}
		}

		probability = calcProbability(skillId);

		int rnd = Random.nextInt(100) + 1;
		if (probability > 90) {
			probability = 90; // 最高成功率90%。
		}

		if (probability >= rnd) {
			isSuccess = true;
		}
		else {
			isSuccess = false;
		}
		//修正怪物的范围负面魔法不会放到自己
		if (_calcType == NPC_NPC) {
			if (_targetNpc.getId() == _npc.getId()) {
				return false;
			}
		}
		// 确率系魔法信息
		if (!Config.ALT_ATKMSG) {
			return isSuccess;
		}
		if (Config.ALT_ATKMSG) {
			if (((_calcType == PC_PC) || (_calcType == PC_NPC)) && !_pc.isGm()) {
				return isSuccess;
			}
			if (((_calcType == PC_PC) || (_calcType == NPC_PC)) && !_targetPc.isGm()) {
				return isSuccess;
			}
		}

		String msg0 = "";
		String msg1 = " 施放魔法 ";
		String msg2 = "";
		String msg3 = "";
		String msg4 = "";

		if ((_calcType == PC_PC) || (_calcType == PC_NPC)) { // 攻击者为ＰＣ的场合
			msg0 = _pc.getName() + " 对";
		}
		else if (_calcType == NPC_PC) { // 攻击者为ＮＰＣ的场合
			msg0 = _npc.getName();
		}

		msg2 = "，几率：" + probability + "%";
		if ((_calcType == NPC_PC) || (_calcType == PC_PC)) { // 目标位ＰＣ的场合
			msg4 = _targetPc.getName();
		}
		else if (_calcType == PC_NPC) { // 目标位ＮＰＣ的场合
			msg4 = _targetNpc.getName();
		}
		if (isSuccess == true) {
			msg3 = "成功";
		}
		else {
			msg3 = "失败";
		}

		// 0 4 1 3 2 攻击者 对 目标 施放魔法 成功/失败，机率：X%。
		if ((_calcType == PC_PC) || (_calcType == PC_NPC)) {
			_pc.sendPackets(new S_ServerMessage(166, msg0, msg1, msg2, msg3, msg4));
		}
		// 攻击者 施放魔法 成功/失败，机率：X%。
		else if ((_calcType == NPC_PC)) {
			_targetPc.sendPackets(new S_ServerMessage(166, msg0, msg1, msg2, msg3, null));
		}

		return isSuccess;
	}

	// 在安全区内无效的负面魔法
	private boolean checkZone(int skillId) {
		if ((_pc != null) && (_targetPc != null)) {
			if ((_pc.getZoneType() == 1) || (_targetPc.getZoneType() == 1)) { // 安全区
				if ((skillId == WEAPON_BREAK)			// 坏物术
						|| (skillId == SLOW)			// 缓速术
						|| (skillId == CURSE_PARALYZE)	// 木乃伊的诅咒
						|| (skillId == MANA_DRAIN)		// 魔力夺取
						|| (skillId == DARKNESS)		// 黑暗之影
						|| (skillId == WEAKNESS)		// 弱化术
						|| (skillId == DISEASE)			// 疾病术
						|| (skillId == DECAY_POTION)	// 药水霜化术
						|| (skillId == MASS_SLOW)		// 集体缓速术
						|| (skillId == ENTANGLE)		// 地面障碍
						|| (skillId == ERASE_MAGIC)		// 魔法消除
						|| (skillId == EARTH_BIND)		// 大地屏障
						|| (skillId == AREA_OF_SILENCE) // 封印禁地
						|| (skillId == WIND_SHACKLE)	// 风之枷锁
						|| (skillId == STRIKER_GALE)	// 精准射击
						|| (skillId == SHOCK_STUN)		// 冲击之晕
						|| (skillId == FOG_OF_SLEEPING) // 沉睡之雾
						|| (skillId == ICE_LANCE)		// 冰矛围篱
						|| (skillId == FREEZING_BLIZZARD) // 冰雪飓风
						|| (skillId == FREEZING_BREATH) // 寒冰喷吐
						|| (skillId == POLLUTE_WATER)	// 污浊之水
						|| (skillId == ELEMENTAL_FALL_DOWN) // 弱化属性
						|| (skillId == RETURN_TO_NATURE) // 释放元素
						|| (skillId == ICE_LANCE_COCKATRICE) // 亚力安冰矛围篱
						|| (skillId == ICE_LANCE_BASILISK)) { // 邪恶蜥蜴冰矛围篱
					return false;
				}
			}
		}
		return true;
	}

	// 成功率
	private int calcProbability(int skillId) {
		L1Skills l1skills = SkillsTable.getInstance().getTemplate(skillId);
		int attackLevel = 0;
		int defenseLevel = 0;
		int probability = 0;

		if ((_calcType == PC_PC) || (_calcType == PC_NPC)) {
			attackLevel = _pc.getLevel();
		}
		else {
			attackLevel = _npc.getLevel();
		}

		if ((_calcType == PC_PC) || (_calcType == NPC_PC)) {
			defenseLevel = _targetPc.getLevel();
		}
		else {
			defenseLevel = _targetNpc.getLevel();
			if (skillId == RETURN_TO_NATURE) { // 释放元素
				if (_targetNpc instanceof L1SummonInstance) {
					L1SummonInstance summon = (L1SummonInstance) _targetNpc;
					defenseLevel = summon.getMaster().getLevel();
				}
			}
		}

		if ((skillId == ELEMENTAL_FALL_DOWN) || (skillId == RETURN_TO_NATURE) || (skillId == ENTANGLE) || (skillId == ERASE_MAGIC)
				|| (skillId == AREA_OF_SILENCE) || (skillId == WIND_SHACKLE) || (skillId == STRIKER_GALE) || (skillId == POLLUTE_WATER)
				|| (skillId == EARTH_BIND)) {
			// 成功确率は 魔法固有系数 × LV差 + 基本确率
			probability = (int) (((l1skills.getProbabilityDice()) / 10D) * (attackLevel - defenseLevel)) + l1skills.getProbabilityValue();

			// 原始 INT 的魔法命中
			if ((_calcType == PC_PC) || (_calcType == PC_NPC)) {
				probability += 2 * _pc.getOriginalMagicHit();
			}
		}
		else if (skillId == SHOCK_STUN) {
			// 成功确率は 基本确率 + LV差1每に+-2%
			probability = l1skills.getProbabilityValue() + (attackLevel - defenseLevel) * 2;

			// 原始 INT 的魔法命中
			if ((_calcType == PC_PC) || (_calcType == PC_NPC)) {
				probability += 2 * _pc.getOriginalMagicHit();
			}
		}
		else if (skillId == COUNTER_BARRIER) {
			// 成功确率は 基本确率 + LV差1每に+-1%
			probability = l1skills.getProbabilityValue() + attackLevel - defenseLevel;

			// 原始 INT 的魔法命中
			if ((_calcType == PC_PC) || (_calcType == PC_NPC)) {
				probability += 2 * _pc.getOriginalMagicHit();
			}
		}
		else if ((skillId == GUARD_BRAKE) || (skillId == RESIST_FEAR) || (skillId == HORROR_OF_DEATH)) {
			int dice = l1skills.getProbabilityDice();
			int value = l1skills.getProbabilityValue();
			int diceCount = 0;
			diceCount = getMagicBonus() + getMagicLevel();

			if (diceCount < 1) {
				diceCount = 1;
			}

			for (int i = 0; i < diceCount; i++) {
				probability += (Random.nextInt(dice) + 1 + value);
			}

			probability = probability * getLeverage() / 10;

			// 原始 INT 的魔法命中
			if ((_calcType == PC_PC) || (_calcType == PC_NPC)) {
				probability += 2 * _pc.getOriginalMagicHit();
			}

			if (probability >= getTargetMr()) {
				probability = 100;
			}
			else {
				probability = 0;
			}
		}
		else {
			int dice = l1skills.getProbabilityDice();
			int diceCount = 0;
			if ((_calcType == PC_PC) || (_calcType == PC_NPC)) {
				if (_pc.isWizard()) {
					diceCount = getMagicBonus() + getMagicLevel() + 1;
				}
				else if (_pc.isElf()) {
					diceCount = getMagicBonus() + getMagicLevel() - 1;
				}
				else {
					diceCount = getMagicBonus() + getMagicLevel() - 1;
				}
			}
			else {
				diceCount = getMagicBonus() + getMagicLevel();
			}
			if (diceCount < 1) {
				diceCount = 1;
			}

			for (int i = 0; i < diceCount; i++) {
				probability += (Random.nextInt(dice) + 1);
			}
			probability = probability * getLeverage() / 10;

			// 原始 INT 的魔法命中
			if ((_calcType == PC_PC) || (_calcType == PC_NPC)) {
				probability += 2 * _pc.getOriginalMagicHit();
			}

			probability -= getTargetMr();

			if (skillId == TAMING_MONSTER) {
				double probabilityRevision = 1;
				if ((_targetNpc.getMaxHp() * 1 / 4) > _targetNpc.getCurrentHp()) {
					probabilityRevision = 1.3;
				}
				else if ((_targetNpc.getMaxHp() * 2 / 4) > _targetNpc.getCurrentHp()) {
					probabilityRevision = 1.2;
				}
				else if ((_targetNpc.getMaxHp() * 3 / 4) > _targetNpc.getCurrentHp()) {
					probabilityRevision = 1.1;
				}
				probability *= probabilityRevision;
			}
		}

		// 状态异常に对する耐性
		if (skillId == EARTH_BIND) { // 大地屏障
			if ((_calcType == PC_PC) || (_calcType == NPC_PC)) {
				probability -= _targetPc.getRegistSustain();
			}
		}
		else if (skillId == SHOCK_STUN) { // 冲击之晕
			if ((_calcType == PC_PC) || (_calcType == NPC_PC)) {
				probability -= 2 * _targetPc.getRegistStun();
			}
		}
		else if (skillId == CURSE_PARALYZE) { // 木乃伊的诅咒
			if ((_calcType == PC_PC) || (_calcType == NPC_PC)) {
				probability -= _targetPc.getRegistStone();
			}
		}
		else if (skillId == FOG_OF_SLEEPING) { // 沉睡之雾
			if ((_calcType == PC_PC) || (_calcType == NPC_PC)) {
				probability -= _targetPc.getRegistSleep();
			}
		}
		else if ((skillId == ICE_LANCE) || (skillId == FREEZING_BLIZZARD) || (skillId == FREEZING_BREATH) 
			|| (skillId == ICE_LANCE_COCKATRICE) || (skillId == ICE_LANCE_BASILISK)) {
			if ((_calcType == PC_PC) || (_calcType == NPC_PC)) {
				probability -= _targetPc.getRegistFreeze();	
				// 检查无敌状态
				for (int skillid : INVINCIBLE) {
					if (_targetPc.hasSkillEffect(skillid)) {
						probability = 0;
						break;
					}
				}
			}
		}
		else if ((skillId == CURSE_BLIND) || (skillId == DARKNESS) || (skillId == DARK_BLIND)) { // 暗盲咒术,黑暗之影,暗黑盲咒
			if ((_calcType == PC_PC) || (_calcType == NPC_PC)) {
				probability -= _targetPc.getRegistBlind();
			}
		}

		return probability;
	}

	// 拥有这些状态的, 不会受到伤害(无敌)
	private static final int[] INVINCIBLE = {
		ABSOLUTE_BARRIER,		// 绝对屏障
		ICE_LANCE,				// 冰矛围篱
		FREEZING_BLIZZARD,		// 冰雪飓风
		FREEZING_BREATH,		// 寒冰喷吐
		EARTH_BIND,				// 大地屏障
		ICE_LANCE_COCKATRICE,	// 亚力安冰矛围篱
		ICE_LANCE_BASILISK		// 邪恶蜥蜴冰矛围篱
	};

	/* ■■■■■■■■■■■■■■ 魔法伤害算出 ■■■■■■■■■■■■■■ */

	public int calcMagicDamage(int skillId) {
		int damage = 0;
		
		// 检查无敌状态
		for (int skillid : INVINCIBLE) {
			if (_target.hasSkillEffect(skillid)) {
				return damage;
			}
		}

		if ((_calcType == PC_PC) || (_calcType == NPC_PC)) {
			damage = calcPcMagicDamage(skillId);
		}
		else if ((_calcType == PC_NPC) || (_calcType == NPC_NPC)) {
			damage = calcNpcMagicDamage(skillId);
		}

		if (skillId != JOY_OF_PAIN) { // 疼痛的欢愉无视魔免
			damage = calcMrDefense(damage);
		}

		return damage;
	}

	// ●●●● 对 PC 的火牢伤害算出 ●●●●
	public int calcPcFireWallDamage() {
		int dmg = 0;
		double attrDeffence = calcAttrResistance(L1Skills.ATTR_FIRE);
		L1Skills l1skills = SkillsTable.getInstance().getTemplate(FIRE_WALL); // 火牢
		dmg = (int) ((1.0 - attrDeffence) * l1skills.getDamageValue());

		if (_targetPc.hasSkillEffect(ABSOLUTE_BARRIER)) {
			dmg = 0;
		}
		if (_targetPc.hasSkillEffect(ICE_LANCE)) {
			dmg = 0;
		}
		if (_targetPc.hasSkillEffect(FREEZING_BLIZZARD)) {
			dmg = 0;
		}
		if (_targetPc.hasSkillEffect(FREEZING_BREATH)) {
			dmg = 0;
		}
		if (_targetPc.hasSkillEffect(EARTH_BIND)) {
			dmg = 0;
		}
		if (_targetPc.hasSkillEffect(ICE_LANCE_COCKATRICE)) {
			dmg = 0;
		}
		if (_targetPc.hasSkillEffect(ICE_LANCE_BASILISK)) {
			dmg = 0;
		}

		if (dmg < 0) {
			dmg = 0;
		}

		return dmg;
	}

	// ●●●● 对ＮＰＣ 的火牢伤害算出 ●●●●
	public int calcNpcFireWallDamage() {
		int dmg = 0;
		double attrDeffence = calcAttrResistance(L1Skills.ATTR_FIRE);
		L1Skills l1skills = SkillsTable.getInstance().getTemplate(FIRE_WALL);
		dmg = (int) ((1.0 - attrDeffence) * l1skills.getDamageValue());

		if (_targetNpc.hasSkillEffect(ICE_LANCE)) {
			dmg = 0;
		}
		if (_targetNpc.hasSkillEffect(FREEZING_BLIZZARD)) {
			dmg = 0;
		}
		if (_targetNpc.hasSkillEffect(FREEZING_BREATH)) {
			dmg = 0;
		}
		if (_targetNpc.hasSkillEffect(EARTH_BIND)) {
			dmg = 0;
		}
		if (_targetNpc.hasSkillEffect(ICE_LANCE_COCKATRICE)) {
			dmg = 0;
		}
		if (_targetNpc.hasSkillEffect(ICE_LANCE_BASILISK)) {
			dmg = 0;
		}

		if (dmg < 0) {
			dmg = 0;
		}

		return dmg;
	}

	// ●●●● 计算玩家的魔法伤害  ●●●●
	private int calcPcMagicDamage(int skillId) {
		int dmg = 0;
		if (skillId == FINAL_BURN) {
			if (_calcType == PC_PC) {
				dmg = _pc.getCurrentMp();
			} else {
				dmg = _npc.getCurrentMp();
			}
		} else {
			dmg = calcMagicDiceDamage(skillId);
			dmg = (dmg * getLeverage()) / 10;
		}

		// 心灵破坏消耗目标5点MP造成5倍精神伤害
		if (skillId == MIND_BREAK) {
			if (_targetPc.getCurrentMp() >= 5) {
				_targetPc.setCurrentMp(_targetPc.getCurrentMp() - 5);
				if (_calcType == PC_PC) {
					dmg += _pc.getWis() * 5;
				} else if (_calcType == NPC_PC) {
					dmg += _npc.getWis() * 5;
				}
			}
		}

		dmg -= _targetPc.getDamageReductionByArmor(); // 防具的伤害减免

		// 魔法娃娃效果 - 伤害减免
		dmg -= L1MagicDoll.getDamageReductionByDoll(_targetPc);

		if (_targetPc.hasSkillEffect(COOKING_1_0_S) // 料理的伤害减免
				|| _targetPc.hasSkillEffect(COOKING_1_1_S) || _targetPc.hasSkillEffect(COOKING_1_2_S)
				|| _targetPc.hasSkillEffect(COOKING_1_3_S)
				|| _targetPc.hasSkillEffect(COOKING_1_4_S) || _targetPc.hasSkillEffect(COOKING_1_5_S)
				|| _targetPc.hasSkillEffect(COOKING_1_6_S)
				|| _targetPc.hasSkillEffect(COOKING_2_0_S) || _targetPc.hasSkillEffect(COOKING_2_1_S)
				|| _targetPc.hasSkillEffect(COOKING_2_2_S)
				|| _targetPc.hasSkillEffect(COOKING_2_3_S) || _targetPc.hasSkillEffect(COOKING_2_4_S)
				|| _targetPc.hasSkillEffect(COOKING_2_5_S)
				|| _targetPc.hasSkillEffect(COOKING_2_6_S) || _targetPc.hasSkillEffect(COOKING_3_0_S)
				|| _targetPc.hasSkillEffect(COOKING_3_1_S)
				|| _targetPc.hasSkillEffect(COOKING_3_2_S) || _targetPc.hasSkillEffect(COOKING_3_3_S)
				|| _targetPc.hasSkillEffect(COOKING_3_4_S)
				|| _targetPc.hasSkillEffect(COOKING_3_5_S) || _targetPc.hasSkillEffect(COOKING_3_6_S)) {
			dmg -= 5;
		}
		if (_targetPc.hasSkillEffect(COOKING_1_7_S) // 料理的伤害减免
				|| _targetPc.hasSkillEffect(COOKING_2_7_S) || _targetPc.hasSkillEffect(COOKING_3_7_S)) {
			dmg -= 5;
		}

		if (_targetPc.hasSkillEffect(REDUCTION_ARMOR)) {
			int targetPcLvl = _targetPc.getLevel();
			if (targetPcLvl < 50) {
				targetPcLvl = 50;
			}
			dmg -= (targetPcLvl - 50) / 5 + 1;
		}
		if (_targetPc.hasSkillEffect(DRAGON_SKIN)) {
			dmg -= 2;
		}

		if (_targetPc.hasSkillEffect(PATIENCE)) {
			dmg -= 2;
		}

		if (_calcType == NPC_PC) { // 宠物、サモンからプレイヤーに攻击
			boolean isNowWar = false;
			int castleId = L1CastleLocation.getCastleIdByArea(_targetPc);
			if (castleId > 0) {
				isNowWar = WarTimeController.getInstance().isNowWar(castleId);
			}
			if (!isNowWar) {
				if (_npc instanceof L1PetInstance) {
					dmg /= 8;
				}
				if (_npc instanceof L1SummonInstance) {
					L1SummonInstance summon = (L1SummonInstance) _npc;
					if (summon.isExsistMaster()) {
						dmg /= 8;
					}
				}
			}
		}

		if (_targetPc.hasSkillEffect(IMMUNE_TO_HARM)) {
			dmg /= 2;
		}
		// 疼痛的欢愉伤害：(最大血量 - 目前血量 /5)
		if (skillId == JOY_OF_PAIN) {
			int nowDamage = 0;
			if (_calcType == PC_PC) {
				nowDamage = _pc.getMaxHp() - _pc.getCurrentHp();
				if (nowDamage > 0) {
					dmg = nowDamage / 5;
				}
			} else if (_calcType == NPC_PC) {
				nowDamage = _npc.getMaxHp() - _npc.getCurrentHp();
				if (nowDamage > 0) {
					dmg = nowDamage / 5;
				}
			}
		}

		if (_targetPc.hasSkillEffect(ABSOLUTE_BARRIER)) {
			dmg = 0;
		} else if (_targetPc.hasSkillEffect(ICE_LANCE)) {
			dmg = 0;
		} else if (_targetPc.hasSkillEffect(FREEZING_BLIZZARD)) {
			dmg = 0;
		} else if (_targetPc.hasSkillEffect(FREEZING_BREATH)) {
			dmg = 0;
		} else if (_targetPc.hasSkillEffect(EARTH_BIND)) {
			dmg = 0;
		}

		if (_calcType == NPC_PC) {
			if ((_npc instanceof L1PetInstance) || (_npc instanceof L1SummonInstance)) {
				// 目标在安区、攻击者在安区、NOPVP
				if ((_targetPc.getZoneType() == 1) || (_npc.getZoneType() == 1)
						|| (_targetPc.checkNonPvP(_targetPc, _npc))) {
					dmg = 0;
				}
			}
		}

		if (_targetPc.hasSkillEffect(COUNTER_MIRROR)) {
			if (_calcType == PC_PC) {
				if (_targetPc.getWis() >= Random.nextInt(100)) {
					_pc.sendPackets(new S_DoActionGFX(_pc.getId(), ActionCodes.ACTION_Damage));
					_pc.broadcastPacket(new S_DoActionGFX(_pc.getId(), ActionCodes.ACTION_Damage));
					_targetPc.sendPackets(new S_SkillSound(_targetPc.getId(), 4395));
					_targetPc.broadcastPacket(new S_SkillSound(_targetPc.getId(), 4395));
					_pc.receiveDamage(_targetPc, dmg, false);
					dmg = 0;
					_targetPc.killSkillEffectTimer(COUNTER_MIRROR);
				}
			}
			else if (_calcType == NPC_PC) {
				int npcId = _npc.getNpcTemplate().get_npcId();
				if ((npcId == 45681) || (npcId == 45682) || (npcId == 45683) || (npcId == 45684)) {}
				else if (!_npc.getNpcTemplate().get_IsErase()) {}
				else {
					if (_targetPc.getWis() >= Random.nextInt(100)) {
						_npc.broadcastPacket(new S_DoActionGFX(_npc.getId(), ActionCodes.ACTION_Damage));
						_targetPc.sendPackets(new S_SkillSound(_targetPc.getId(), 4395));
						_targetPc.broadcastPacket(new S_SkillSound(_targetPc.getId(), 4395));
						_npc.receiveDamage(_targetPc, dmg);
						dmg = 0;
						_targetPc.killSkillEffectTimer(COUNTER_MIRROR);
					}
				}
			}
		}

		if (dmg < 0) {
			dmg = 0;
		}

		return dmg;
	}

	// ●●●● 计算 NPC 的魔法伤害 ●●●●
	private int calcNpcMagicDamage(int skillId) {
		int dmg = 0;
		if (skillId == FINAL_BURN) {
			if (_calcType == PC_NPC) {
				dmg = _pc.getCurrentMp();
			} else {
				dmg = _npc.getCurrentMp();
			}
		}
		else {
			dmg = calcMagicDiceDamage(skillId);
			dmg = (dmg * getLeverage()) / 10;
		}

		// 心灵破坏消耗目标5点MP造成5倍精神伤害
		if (skillId == MIND_BREAK) {
			if (_targetNpc.getCurrentMp() >= 5) {
				_targetNpc.setCurrentMp(_targetNpc.getCurrentMp() - 5);
				if (_calcType == PC_NPC) {
					dmg += _pc.getWis() * 5;
				} else if (_calcType == NPC_NPC) {
					dmg += _npc.getWis() * 5;
				}
			}
		}

		// 疼痛的欢愉伤害：(最大血量 - 目前血量 /5)
		if (skillId == JOY_OF_PAIN) {
			int nowDamage = 0;
			if (_calcType == PC_NPC) {
				nowDamage = _pc.getMaxHp() - _pc.getCurrentHp();
				if (nowDamage > 0) {
					dmg = nowDamage / 5;
				}
			} else if (_calcType == NPC_NPC) {
				nowDamage = _npc.getMaxHp() - _npc.getCurrentHp();
				if (nowDamage > 0) {
					dmg = nowDamage / 5;
				}
			}
		}

		if (_calcType == PC_NPC) { // 玩家宠物、サモンに攻击
			boolean isNowWar = false;
			int castleId = L1CastleLocation.getCastleIdByArea(_targetNpc);
			if (castleId > 0) {
				isNowWar = WarTimeController.getInstance().isNowWar(castleId);
			}
			if (!isNowWar) {
				if (_targetNpc instanceof L1PetInstance) {
					dmg /= 8;
				}
				if (_targetNpc instanceof L1SummonInstance) {
					L1SummonInstance summon = (L1SummonInstance) _targetNpc;
					if (summon.isExsistMaster()) {
						dmg /= 8;
					}
				}
			}
		}

		if (_targetNpc.hasSkillEffect(ICE_LANCE)) {
			dmg = 0;
		} else if (_targetNpc.hasSkillEffect(FREEZING_BLIZZARD)) {
			dmg = 0;
		} else if (_targetNpc.hasSkillEffect(FREEZING_BREATH)) {
			dmg = 0;
		} else if (_targetNpc.hasSkillEffect(EARTH_BIND)) {
			dmg = 0;
		}

		// 判断特定状态下才可攻击 NPC
		if ((_calcType == PC_NPC) && (_targetNpc != null)) {
			if (_pc.isAttackMiss(_pc, _targetNpc.getNpcTemplate().get_npcId())) {
				dmg = 0;
			}
		}
		if (_calcType == NPC_NPC) {
			if (((_npc instanceof L1PetInstance) || (_npc instanceof L1SummonInstance))
					&& ((_targetNpc instanceof L1PetInstance) || (_targetNpc instanceof L1SummonInstance))) {
				// 目标在安区、攻击者在安区
				if ((_targetNpc.getZoneType() == 1) || (_npc.getZoneType() == 1)) {
					dmg = 0;
				}
			}
		}

		return dmg;
	}

	// ●●●● damage_dice、damage_dice_count、damage_value、SP 的魔法伤害算出 ●●●●
	private int calcMagicDiceDamage(int skillId) {
		L1Skills l1skills = SkillsTable.getInstance().getTemplate(skillId);
		int dice = l1skills.getDamageDice();
		int diceCount = l1skills.getDamageDiceCount();
		int value = l1skills.getDamageValue();
		int magicDamage = 0;
		int charaIntelligence = 0;

		for (int i = 0; i < diceCount; i++) {
			magicDamage += (Random.nextInt(dice) + 1);
		}
		magicDamage += value;

		// 装备项链获得特殊能力 add (PC NPC)
		if ((_calcType == PC_PC) || (_calcType == PC_NPC)) {

			if (_pc.getInventory().checkEquipped(10060)) { // 魔导士项链 by 丫杰
				magicDamage *= 2; // 魔法攻击力翻倍
			}
			if (_pc.getInventory().checkEquipped(10061)) { // 祭司项链 转换伤害补充魔力 by 丫杰
				int dmgMp = 0;
				dmgMp = (short) (_pc.getCurrentMp() + (magicDamage / 5));
				_pc.setCurrentMp(dmgMp);  // 魔法攻击力的1/5转化为自身魔力
			}
		}
		// 装备项链获得特殊能力 end (PC NPC)

		if ((_calcType == PC_PC) || (_calcType == PC_NPC)) {
			int weaponAddDmg = 0; // 武器による追加ダメージ
			L1ItemInstance weapon = _pc.getWeapon();
			if (weapon != null) {
				weaponAddDmg = weapon.getItem().getMagicDmgModifier();
			}
			magicDamage += weaponAddDmg;
		}

		if ((_calcType == PC_PC) || (_calcType == PC_NPC)) {
			int spByItem = _pc.getSp() - _pc.getTrueSp(); // アイテムによるSP变动
			charaIntelligence = _pc.getInt() + spByItem - 12;
		}
		else if ((_calcType == NPC_PC) || (_calcType == NPC_NPC)) {
			int spByItem = _npc.getSp() - _npc.getTrueSp(); // アイテムによるSP变动
			charaIntelligence = _npc.getInt() + spByItem - 12;
		}
		if (charaIntelligence < 1) {
			charaIntelligence = 1;
		}

		double attrDeffence = calcAttrResistance(l1skills.getAttr());

		double coefficient = (1.0 - attrDeffence + charaIntelligence * 3.0 / 32.0);
		if (coefficient < 0) {
			coefficient = 0;
		}

		magicDamage *= coefficient;

		double criticalCoefficient = 1.5; // 魔法クリティカル
		int rnd = Random.nextInt(100) + 1;
		if ((_calcType == PC_PC) || (_calcType == PC_NPC)) {
			if (l1skills.getSkillLevel() <= 6) {
				if (rnd <= (10 + _pc.getOriginalMagicCritical())) {
					magicDamage *= criticalCoefficient;
				}
			}
		}

		if ((_calcType == PC_PC) || (_calcType == PC_NPC)) { // オリジナルINTによる魔法ダメージ
			magicDamage += _pc.getOriginalMagicDamage();
		}
		if ((_calcType == PC_PC) || (_calcType == PC_NPC)) { // アバターによる追加ダメージ
			if (_pc.hasSkillEffect(ILLUSION_AVATAR)) {
				magicDamage += 10;
			}
		}

		return magicDamage;
	}

	// ●●●● ヒール回复量（对アンデッドにはダメージ）を算出 ●●●●
	public int calcHealing(int skillId) {
		L1Skills l1skills = SkillsTable.getInstance().getTemplate(skillId);
		int dice = l1skills.getDamageDice();
		int value = l1skills.getDamageValue();
		int magicDamage = 0;

		int magicBonus = getMagicBonus();
		if (magicBonus > 10) {
			magicBonus = 10;
		}

		int diceCount = value + magicBonus;
		for (int i = 0; i < diceCount; i++) {
			magicDamage += (Random.nextInt(dice) + 1);
		}

		double alignmentRevision = 1.0;
		if (getLawful() > 0) {
			alignmentRevision += (getLawful() / 32768.0);
		}

		magicDamage *= alignmentRevision;

		magicDamage = (magicDamage * getLeverage()) / 10;

		return magicDamage;
	}

	// ●●●● ＭＲ的伤害减免 ●●●●
	private int calcMrDefense(int dmg) {
		int mr = getTargetMr();

		double mrFloor = 0;
		if ((_calcType == PC_PC) || (_calcType == PC_NPC)) {
			if (mr <= 100) {
				mrFloor = Math.floor((mr - _pc.getOriginalMagicHit()) / 2);
			}
			else if (mr >= 100) {
				mrFloor = Math.floor((mr - _pc.getOriginalMagicHit()) / 10);
			}
			double mrCoefficient = 0;
			if (mr <= 100) {
				mrCoefficient = 1 - 0.01 * mrFloor;
			}
			else if (mr >= 100) {
				mrCoefficient = 0.6 - 0.01 * mrFloor;
			}
			dmg *= mrCoefficient;
		}
		else if ((_calcType == NPC_PC) || (_calcType == NPC_NPC)) {
			int rnd = Random.nextInt(100) + 1;
			if (mr >= rnd) {
				dmg /= 2;
			}
		}

		return dmg;
	}

	// ●●●● 属性的伤害减免 ●●●●
	// attr:0.无属性魔法,1.地魔法,2.火魔法,4.水魔法,8.风魔法(,16.光魔法)
	private double calcAttrResistance(int attr) {
		int resist = 0;
		if ((_calcType == PC_PC) || (_calcType == NPC_PC)) {
			if (attr == L1Skills.ATTR_EARTH) {
				resist = _targetPc.getEarth();
			}
			else if (attr == L1Skills.ATTR_FIRE) {
				resist = _targetPc.getFire();
			}
			else if (attr == L1Skills.ATTR_WATER) {
				resist = _targetPc.getWater();
			}
			else if (attr == L1Skills.ATTR_WIND) {
				resist = _targetPc.getWind();
			}
		}
		else if ((_calcType == PC_NPC) || (_calcType == NPC_NPC)) {}

		int resistFloor = (int) (0.32 * Math.abs(resist));
		if (resist >= 0) {
			resistFloor *= 1;
		}
		else {
			resistFloor *= -1;
		}

		double attrDeffence = resistFloor / 32.0;

		return attrDeffence;
	}

	/* ■■■■■■■■■■■■■■■ 计算结果反映 ■■■■■■■■■■■■■■■ */

	public void commit(int damage, int drainMana) {
		if ((_calcType == PC_PC) || (_calcType == NPC_PC)) {
			commitPc(damage, drainMana);
		}
		else if ((_calcType == PC_NPC) || (_calcType == NPC_NPC)) {
			commitNpc(damage, drainMana);
		}

		// 伤害值和命中率的确认消息
		if (!Config.ALT_ATKMSG) {
			return;
		}
		if (Config.ALT_ATKMSG) {
			if (((_calcType == PC_PC) || (_calcType == PC_NPC)) && !_pc.isGm()) {
				return;
			}
			if ((_calcType == NPC_PC) && !_targetPc.isGm()) {
				return;
			}
		}

		String msg0 = "";
		String msg1 = " 造成 ";
		String msg2 = "";
		String msg3 = "";
		String msg4 = "";

		if ((_calcType == PC_PC) || (_calcType == PC_NPC)) {// 攻击者为ＰＣ的场合
			msg0 = "魔攻 对";
		}
		else if (_calcType == NPC_PC) { // 攻击者为ＮＰＣ的场合
			msg0 = _npc.getName() + "(魔攻)：";
		}

		if ((_calcType == NPC_PC) || (_calcType == PC_PC)) { // 目标为ＰＣ的场合
			msg4 = _targetPc.getName();
			msg2 = "，剩余 " + _targetPc.getCurrentHp();
		}
		else if (_calcType == PC_NPC) { // 目标为ＮＰＣ的场合
			msg4 = _targetNpc.getName();
			msg2 = "，剩余 " + _targetNpc.getCurrentHp();
		}

		msg3 = damage  + " 伤害";

		// 魔攻 对 目标 造成 X 伤害，剩余 Y。
		if ((_calcType == PC_PC) || (_calcType == PC_NPC)) { // 攻击者为ＰＣ的场合
			_pc.sendPackets(new S_ServerMessage(166, msg0, msg1, msg2, msg3, msg4)); // \f1%0%s %4%1%3 %2。
		}
		// 攻击者(魔攻)： X伤害，剩余 Y。
		else if ((_calcType == NPC_PC)) { // 目标为ＰＣ的场合
			_targetPc.sendPackets(new S_ServerMessage(166, msg0, null, msg2, msg3, null)); // \f1%0%s %4%1%3 %2。
		}
	}

	// ●●●● 玩家计算结果反映 ●●●●
	private void commitPc(int damage, int drainMana) {
		if (_calcType == PC_PC) {
			if ((drainMana > 0) && (_targetPc.getCurrentMp() > 0)) {
				if (drainMana > _targetPc.getCurrentMp()) {
					drainMana = _targetPc.getCurrentMp();
				}
				int newMp = _pc.getCurrentMp() + drainMana;
				_pc.setCurrentMp(newMp);
			}
			_targetPc.receiveManaDamage(_pc, drainMana);
			_targetPc.receiveDamage(_pc, damage, true);
		}
		else if (_calcType == NPC_PC) {
			_targetPc.receiveDamage(_npc, damage, true);
		}
	}

	// ●●●● ＮＰＣ计算结果反映 ●●●●
	private void commitNpc(int damage, int drainMana) {
		if (_calcType == PC_NPC) {
			if (drainMana > 0) {
				int drainValue = _targetNpc.drainMana(drainMana);
				int newMp = _pc.getCurrentMp() + drainValue;
				_pc.setCurrentMp(newMp);
			}
			_targetNpc.ReceiveManaDamage(_pc, drainMana);
			_targetNpc.receiveDamage(_pc, damage);
		}
		else if (_calcType == NPC_NPC) {
			_targetNpc.receiveDamage(_npc, damage);
		}
	}
}
