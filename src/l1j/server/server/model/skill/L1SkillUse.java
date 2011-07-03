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
import static l1j.server.server.model.skill.L1SkillId.BLKS_FIRE_WALL;//巴拉卡斯火牢

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import l1j.server.server.ActionCodes;
import l1j.server.server.datatables.PolyTable;
import l1j.server.server.datatables.SkillsTable;
import l1j.server.server.model.L1CastleLocation;
import l1j.server.server.model.L1Character;
import l1j.server.server.model.L1EffectSpawn;
import l1j.server.server.model.L1Location;
import l1j.server.server.model.L1Magic;
import l1j.server.server.model.L1Object;
import l1j.server.server.model.L1PcInventory;
import l1j.server.server.model.L1PolyMorph;
import l1j.server.server.model.L1Teleport;
import l1j.server.server.model.L1War;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1AuctionBoardInstance;
import l1j.server.server.model.Instance.L1BoardInstance;
import l1j.server.server.model.Instance.L1CrownInstance;
import l1j.server.server.model.Instance.L1DollInstance;
import l1j.server.server.model.Instance.L1DoorInstance;
import l1j.server.server.model.Instance.L1DwarfInstance;
import l1j.server.server.model.Instance.L1EffectInstance;
import l1j.server.server.model.Instance.L1FieldObjectInstance;
import l1j.server.server.model.Instance.L1FurnitureInstance;
import l1j.server.server.model.Instance.L1GuardInstance;
import l1j.server.server.model.Instance.L1HousekeeperInstance;
import l1j.server.server.model.Instance.L1ItemInstance;
import l1j.server.server.model.Instance.L1MerchantInstance;
import l1j.server.server.model.Instance.L1MonsterInstance;
import l1j.server.server.model.Instance.L1NpcInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.Instance.L1PetInstance;
import l1j.server.server.model.Instance.L1SummonInstance;
import l1j.server.server.model.Instance.L1TeleporterInstance;
import l1j.server.server.model.Instance.L1TowerInstance;
import l1j.server.server.model.trap.L1WorldTraps;
import l1j.server.server.serverpackets.S_AttackPacket;
import l1j.server.server.serverpackets.S_ChangeHeading;
import l1j.server.server.serverpackets.S_Dexup;
import l1j.server.server.serverpackets.S_DoActionGFX;
import l1j.server.server.serverpackets.S_EffectLocation;
import l1j.server.server.serverpackets.S_Message_YN;
import l1j.server.server.serverpackets.S_OwnCharAttrDef;
import l1j.server.server.serverpackets.S_OwnCharStatus;
import l1j.server.server.serverpackets.S_Paralysis;
import l1j.server.server.serverpackets.S_Poison;
import l1j.server.server.serverpackets.S_RangeSkill;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.serverpackets.S_SkillBrave;
import l1j.server.server.serverpackets.S_SkillHaste;
import l1j.server.server.serverpackets.S_SkillIconAura;
import l1j.server.server.serverpackets.S_SkillIconGFX;
import l1j.server.server.serverpackets.S_SkillIconShield;
import l1j.server.server.serverpackets.S_SkillIconWaterLife;
import l1j.server.server.serverpackets.S_SkillIconWindShackle;
import l1j.server.server.serverpackets.S_SkillSound;
import l1j.server.server.serverpackets.S_Sound;
import l1j.server.server.serverpackets.S_Strup;
import l1j.server.server.serverpackets.S_SystemMessage;
import l1j.server.server.serverpackets.S_TrueTarget;
import l1j.server.server.serverpackets.S_UseAttackSkill;
import l1j.server.server.templates.L1BookMark;

import l1j.server.server.templates.L1Skills;
import l1j.server.server.utils.Random;
import l1j.server.server.utils.collections.Lists;
import l1j.william.L1WilliamSystemMessage;

public class L1SkillUse {
	public static final int TYPE_NORMAL = 0;

	public static final int TYPE_LOGIN = 1;

	public static final int TYPE_SPELLSC = 2;

	public static final int TYPE_NPCBUFF = 3;

	public static final int TYPE_GMBUFF = 4;

	private L1Skills _skill;

	private int _skillId;

	private int _dmg;

	private int _getBuffDuration;

	private int _shockStunDuration;

	private int _getBuffIconDuration;

	private int _targetID;

	private int _mpConsume = 0;

	private int _hpConsume = 0;

	private int _targetX = 0;

	private int _targetY = 0;

	private String _message = null;

	private int _skillTime = 0;

	private int _type = 0;

	private boolean _isPK = false;

	private int _bookmarkId = 0;

	private int _itemobjid = 0;

	private boolean _checkedUseSkill = false; // 事前チェック济みか

	private int _leverage = 10; // 1/10倍なので10で1倍

	private int _skillRanged = 0;

	private int _skillArea = 0;

	private boolean _isFreeze = false;

	private boolean _isCounterMagic = true;

	private boolean _isGlanceCheckFail = false;

	private L1Character _user = null;

	private L1Character _target = null;

	private L1PcInstance _player = null;

	private L1NpcInstance _npc = null;

	private int _calcType;

	private static final int PC_PC = 1;

	private static final int PC_NPC = 2;

	private static final int NPC_PC = 3;

	private static final int NPC_NPC = 4;

	private List<TargetStatus> _targetList;

	private int _actid = 0;

	private int _gfxid = 0;

	private static Logger _log = Logger.getLogger(L1SkillUse.class.getName());

	private static final int[] CAST_WITH_INVIS =
	{ 1, 2, 3, 5, 8, 9, 12, 13, 14, 19, 21, 26, 31, 32, 35, 37, 42, 43, 44, 48, 49, 52, 54, 55, 57, 60, 61, 63, 67, 68, 69, 72, 73, 75, 78, 79,
			REDUCTION_ARMOR, BOUNCE_ATTACK, SOLID_CARRIAGE, COUNTER_BARRIER, 97, 98, 99, 100, 101, 102, 104, 105, 106, 107, 109, 110, 111, 113, 114,
			115, 116, 117, 118, 129, 130, 131, 133, 134, 137, 138, 146, 147, 148, 149, 150, 151, 155, 156, 158, 159, 163, 164, 165, 166, 168, 169,
			170, 171, SOUL_OF_FLAME, ADDITIONAL_FIRE, DRAGON_SKIN, AWAKEN_ANTHARAS, AWAKEN_FAFURION, AWAKEN_VALAKAS, MIRROR_IMAGE, ILLUSION_OGRE,
			ILLUSION_LICH, PATIENCE, ILLUSION_DIA_GOLEM, INSIGHT, ILLUSION_AVATAR };

	// 设定魔法屏障不可抵挡的魔法
	private static final int[] EXCEPT_COUNTER_MAGIC =
	{ 1, 2, 3, 5, 8, 9, 12, 13, 14, 19, 21, 26, 31, 32, 35, 37, 42, 43, 44, 48, 49, 52, 54, 55, 57, 60, 61, 63, 67, 68, 69, 72, 73, 75, 78, 79,
			SHOCK_STUN, REDUCTION_ARMOR, BOUNCE_ATTACK, SOLID_CARRIAGE, COUNTER_BARRIER, 97, 98, 99, 100, 101, 102, 104, 105, 106, 107, 109, 110,
			111, 113, 114, 115, 116, 117, 118, 129, 130, 131, 132, 134, 137, 138, 146, 147, 148, 149, 150, 151, 155, 156, 158, 159, 161, 163, 164,
			165, 166, 168, 169, 170, 171, SOUL_OF_FLAME, ADDITIONAL_FIRE, DRAGON_SKIN, AWAKEN_ANTHARAS, AWAKEN_FAFURION, AWAKEN_VALAKAS,
			MIRROR_IMAGE, ILLUSION_OGRE, ILLUSION_LICH, PATIENCE, 10026, 10027, ILLUSION_DIA_GOLEM, INSIGHT, ILLUSION_AVATAR, 10028, 10029 };

	public L1SkillUse() {
	}

	private static class TargetStatus {
		private L1Character _target = null;

		private boolean _isCalc = true; // ダメージや确率魔法の计算をする必要があるか？

		public TargetStatus(L1Character _cha) {
			_target = _cha;
		}

		public TargetStatus(L1Character _cha, boolean _flg) {
			_isCalc = _flg;
		}

		public L1Character getTarget() {
			return _target;
		}

		public boolean isCalc() {
			return _isCalc;
		}
	}

	/*
	 * 攻击距离变更。
	 */
	public void setSkillRanged(int i) {
		_skillRanged = i;
	}

	public int getSkillRanged() {
		if (_skillRanged == 0) {
			return _skill.getRanged();
		}
		return _skillRanged;
	}

	/*
	 * 攻击范围变更。
	 */
	public void setSkillArea(int i) {
		_skillArea = i;
	}

	public int getSkillArea() {
		if (_skillArea == 0) {
			return _skill.getArea();
		}
		return _skillArea;
	} 

	/*
	 * 1/10倍で表现する。
	 */
	public void setLeverage(int i) {
		_leverage = i;
	}

	public int getLeverage() {
		return _leverage;
	}

	private boolean isCheckedUseSkill() {
		return _checkedUseSkill;
	}

	private void setCheckedUseSkill(boolean flg) {
		_checkedUseSkill = flg;
	}

	public boolean checkUseSkill(L1PcInstance player, int skillid, int target_id, int x, int y, String message, int time, int type,
			L1Character attacker) {
		return checkUseSkill(player, skillid, target_id, x, y, message, time, type, attacker, 0, 0, 0);
	}

	public boolean checkUseSkill(L1PcInstance player, int skillid, int target_id, int x, int y, String message, int time, int type,
			L1Character attacker, int actid, int gfxid, int mpConsume) {
		// 初期设定ここから
		setCheckedUseSkill(true);
		_targetList = Lists.newList(); // ターゲットリストの初期化

		_skill = SkillsTable.getInstance().getTemplate(skillid);
		_skillId = skillid;
		_targetX = x;
		_targetY = y;
		_message = message;
		_skillTime = time;
		_type = type;
		_actid = actid;
		_gfxid = gfxid;
		_mpConsume = mpConsume;
		boolean checkedResult = true;

		if (attacker == null) {
			// pc
			_player = player;
			_user = _player;
		}
		else {
			// npc
			_npc = (L1NpcInstance) attacker;
			_user = _npc;
		}

		if (_skill.getTarget().equals("none")) {
			_targetID = _user.getId();
			_targetX = _user.getX();
			_targetY = _user.getY();
		}
		else {
			_targetID = target_id;
		}

		if (type == TYPE_NORMAL) { // 通常の魔法使用时
			checkedResult = isNormalSkillUsable();
		}
		else if (type == TYPE_SPELLSC) { // スペルスクロール使用时
			checkedResult = isSpellScrollUsable();
		}
		else if (type == TYPE_NPCBUFF) {
			checkedResult = true;
		}
		if (!checkedResult) {
			return false;
		}

		// ファイアーウォール、ライフストリームは咏唱对象が座标
		// キューブは咏唱者の座标に配置されるため例外
		if ((_skillId == FIRE_WALL) || (_skillId == LIFE_STREAM) || (_skillId == TRUE_TARGET)) {
			return true;
		}

		L1Object l1object = L1World.getInstance().findObject(_targetID);
		if (l1object instanceof L1ItemInstance) {
			_log.fine("skill target item name: " + ((L1ItemInstance) l1object).getViewName());
			// スキルターゲットが精灵の石になることがある。
			// Linux环境で确认（Windowsでは未确认）
			// 2008.5.4追记：地面のアイテムに魔法を使うとなる。继续してもエラーになるだけなのでreturn
			return false;
		}
		if (_user instanceof L1PcInstance) {
			if (l1object instanceof L1PcInstance) {
				_calcType = PC_PC;
			}
			else {
				_calcType = PC_NPC;
			}
		}
		else if (_user instanceof L1NpcInstance) {
			if (l1object instanceof L1PcInstance) {
				_calcType = NPC_PC;
			}
			else if (_skill.getTarget().equals("none")) {
				_calcType = NPC_PC;
			}
			else {
				_calcType = NPC_NPC;
			}
		}

		// テレポート、マステレポートは对象がブックマークID
		if ((_skillId == TELEPORT) || (_skillId == MASS_TELEPORT)) {
			_bookmarkId = target_id;
		}
		// 对象がアイテムのスキル
		if ((_skillId == CREATE_MAGICAL_WEAPON) || (_skillId == BRING_STONE) || (_skillId == BLESSED_ARMOR) || (_skillId == ENCHANT_WEAPON)
				|| (_skillId == SHADOW_FANG)) {
			_itemobjid = target_id;
		}
		_target = (L1Character) l1object;

		if (!(_target instanceof L1MonsterInstance) && _skill.getTarget().equals("attack") && (_user.getId() != target_id)) {
			_isPK = true; // ターゲットがモンスター以外で攻击系スキルで、自分以外の场合PKモードとする。
		}

		// 初期设定ここまで

		// 事前チェック
		if (!(l1object instanceof L1Character)) { // ターゲットがキャラクター以外の场合何もしない。
			checkedResult = false;
		}
		makeTargetList(); // ターゲットの一览を作成
		if (_targetList.isEmpty() && (_user instanceof L1NpcInstance)) {
			checkedResult = false;
		}
		// 事前チェックここまで
		return checkedResult;
	}

	/**
	 * 通常のスキル使用时に使用者の状态からスキルが使用可能であるか判断する
	 * 
	 * @return false スキルが使用不可能な状态である场合
	 */
	private boolean isNormalSkillUsable() {
		// スキル使用者がPCの场合のチェック
		if (_user instanceof L1PcInstance) {
			L1PcInstance pc = (L1PcInstance) _user;

			if (pc.isParalyzed()) { // 麻痹・冻结状态か
				return false;
			}
			if ((pc.isInvisble() || pc.isInvisDelay()) && !isInvisUsableSkill()) { // インビジ中に使用不可のスキル
				return false;
			}
			if (pc.getInventory().getWeight242() >= 197) { // \f1你携带太多物品，因此无法使用法术。
				pc.sendPackets(new S_ServerMessage(316));
				return false;
			}
			int polyId = pc.getTempCharGfx();
			L1PolyMorph poly = PolyTable.getInstance().getTemplate(polyId);
			// 魔法が使えない变身
			if ((poly != null) && !poly.canUseSkill()) {
				pc.sendPackets(new S_ServerMessage(285)); // \f1在此状态下无法使用魔法。
				return false;
			}

			if (!isAttrAgrees()) { // 精灵魔法で、属性が一致しなければ何もしない。
				return false;
			}

			if ((_skillId == ELEMENTAL_PROTECTION) && (pc.getElfAttr() == 0)) {
				pc.sendPackets(new S_ServerMessage(280)); // \f1施咒失败。
				return false;
			}

			/* 水中无法使用火属性魔法 */
			if (pc.getMap().isUnderwater() && _skill.getAttr() == 2) {
				pc.sendPackets(new S_ServerMessage(280)); // \f1施咒失败。
				return false;
			}

			// スキルディレイ中使用不可
			if (pc.isSkillDelay()) {
				return false;
			}

			// 魔法封印、封印禁地、卡毒、幻想
			if (pc.hasSkillEffect(SILENCE) || pc.hasSkillEffect(AREA_OF_SILENCE)
					|| pc.hasSkillEffect(STATUS_POISON_SILENCE) || pc.hasSkillEffect(CONFUSION_ING)) {
				pc.sendPackets(new S_ServerMessage(285)); // \f1在此状态下无法使用魔法。
				return false;
			}

			// DIGはロウフルでのみ使用可
			if ((_skillId == DISINTEGRATE) && (pc.getLawful() < 500)) {
				// このメッセージであってるか未确认
				pc.sendPackets(new S_ServerMessage(352, "$967")); // 若要使用这个法术，属性必须成为 (正义)。
				return false;
			}

			// 同じキューブは效果范围外であれば配置可能
			if ((_skillId == CUBE_IGNITION) || (_skillId == CUBE_QUAKE) || (_skillId == CUBE_SHOCK) || (_skillId == CUBE_BALANCE)) {
				boolean isNearSameCube = false;
				int gfxId = 0;
				for (L1Object obj : L1World.getInstance().getVisibleObjects(pc, 3)) {
					if (obj instanceof L1EffectInstance) {
						L1EffectInstance effect = (L1EffectInstance) obj;
						gfxId = effect.getGfxId();
						if (((_skillId == CUBE_IGNITION) && (gfxId == 6706)) || ((_skillId == CUBE_QUAKE) && (gfxId == 6712))
								|| ((_skillId == CUBE_SHOCK) && (gfxId == 6718)) || ((_skillId == CUBE_BALANCE) && (gfxId == 6724))) {
							isNearSameCube = true;
							break;
						}
					}
				}
				if (isNearSameCube) {
					pc.sendPackets(new S_ServerMessage(1412)); // 已在地板上召唤了魔法立方块。
					return false;
				}
			}

			// 觉醒状态 - 非觉醒技能无法使用
			if (((pc.getAwakeSkillId() == AWAKEN_ANTHARAS) && (_skillId != AWAKEN_ANTHARAS))
					|| ((pc.getAwakeSkillId() == AWAKEN_FAFURION) && (_skillId != AWAKEN_FAFURION))
					|| ((pc.getAwakeSkillId() == AWAKEN_VALAKAS) && (_skillId != AWAKEN_VALAKAS))
					&& (_skillId != MAGMA_BREATH) && (_skillId != SHOCK_SKIN) && (_skillId != FREEZING_BREATH)) {
				pc.sendPackets(new S_ServerMessage(1385)); // 目前状态中无法使用觉醒魔法。
				return false;
			}

			if ((isItemConsume() == false) && !_player.isGm()) { // 法术消耗道具判断。
				_player.sendPackets(new S_ServerMessage(299)); // \f1施放魔法所需材料不足。
				return false;
			}
		}
		// スキル使用者がNPCの场合のチェック
		else if (_user instanceof L1NpcInstance) {

			// サイレンス状态では使用不可
			if (_user.hasSkillEffect(SILENCE)) {
				// NPCにサイレンスが挂かっている场合は1回だけ使用をキャンセルさせる效果。
				_user.removeSkillEffect(SILENCE);
				return false;
			}
		}

		// PC、NPC共通检查HP、MP是否足够
		if (!isHPMPConsume()) { // 花费的HP、MP计算 
			return false;
		}
		return true;
	}

	/**
	 * スペルスクロール使用时に使用者の状态からスキルが使用可能であるか判断する
	 * 
	 * @return false スキルが使用不可能な状态である场合
	 */
	private boolean isSpellScrollUsable() {
		// スペルスクロールを使用するのはPCのみ
		L1PcInstance pc = (L1PcInstance) _user;

		if (pc.isParalyzed()) { // 麻痹・冻结状态か
			return false;
		}

		// インビジ中に使用不可のスキル
		if ((pc.isInvisble() || pc.isInvisDelay()) && !isInvisUsableSkill()) {
			return false;
		}

		return true;
	}

	// インビジ中に使用可能なスキルかを返す
	private boolean isInvisUsableSkill() {
		for (int skillId : CAST_WITH_INVIS) {
			if (skillId == _skillId) {
				return true;
			}
		}
		return false;
	}

	public void handleCommands(L1PcInstance player, int skillId, int targetId, int x, int y, String message, int timeSecs, int type) {
		L1Character attacker = null;
		handleCommands(player, skillId, targetId, x, y, message, timeSecs, type, attacker);
	}

	public void handleCommands(L1PcInstance player, int skillId, int targetId, int x, int y, String message, int timeSecs, int type,
			L1Character attacker) {

		try {
			// 事前チェックをしているか？
			if (!isCheckedUseSkill()) {
				boolean isUseSkill = checkUseSkill(player, skillId, targetId, x, y, message, timeSecs, type, attacker);

				if (!isUseSkill) {
					failSkill();
					return;
				}
			}

			if (type == TYPE_NORMAL) { // 魔法咏唱时
				if (!_isGlanceCheckFail || (getSkillArea() > 0) || _skill.getTarget().equals("none")) {
					runSkill();
					useConsume();
					sendGrfx(true);
					sendFailMessageHandle();
					setDelay();
				}
			}
			else if (type == TYPE_LOGIN) { // ログイン时（HPMP材料消费なし、グラフィックなし）
				runSkill();
			}
			else if (type == TYPE_SPELLSC) { // スペルスクロール使用时（HPMP材料消费なし）
				runSkill();
				sendGrfx(true);
			}
			else if (type == TYPE_GMBUFF) { // GMBUFF使用时（HPMP材料消费なし、魔法モーションなし）
				runSkill();
				sendGrfx(false);
			}
			else if (type == TYPE_NPCBUFF) { // NPCBUFF使用时（HPMP材料消费なし）
				runSkill();
				sendGrfx(true);
			}
			setCheckedUseSkill(false);
		}
		catch (Exception e) {
			_log.log(Level.SEVERE, "", e);
		}
	}

	/**
	 * スキルの失败处理(PCのみ）
	 */
	private void failSkill() {
		// HPが足りなくてスキルが使用できない场合のみ、MPのみ消费したいが未实装（必要ない？）
		// その他の场合は何も消费されない。
		// useConsume(); // HP、MPは减らす
		setCheckedUseSkill(false);
		// テレポートスキル
		if ((_skillId == TELEPORT) || (_skillId == MASS_TELEPORT) || (_skillId == TELEPORT_TO_MATHER)) {
			// テレポートできない场合でも、クライアント侧は应答を待っている
			// テレポート待ち状态の解除（第2引数に意味はない）
			_player.sendPackets(new S_Paralysis(S_Paralysis.TYPE_TELEPORT_UNLOCK, false));
		}
	}

	// ターゲットか？
	private boolean isTarget(L1Character cha) throws Exception {
		boolean _flg = false;

		if (cha instanceof L1PcInstance) {
			L1PcInstance pc = (L1PcInstance) cha;
			if (pc.isGhost() || pc.isGmInvis()) {
				return false;
			}
		}
		if ((_calcType == NPC_PC) && ((cha instanceof L1PcInstance) || (cha instanceof L1PetInstance) || (cha instanceof L1SummonInstance))) {
			_flg = true;
		}

		// 破坏不可能なドアは对象外
		if (cha instanceof L1DoorInstance) {
			if ((cha.getMaxHp() == 0) || (cha.getMaxHp() == 1)) {
				return false;
			}
		}

		// マジックドールは对象外
		if ((cha instanceof L1DollInstance) && (_skillId != HASTE)) {
			return false;
		}

		// TODO 修正召唤怪的范围魔法 
		if (((_user instanceof L1PetInstance) || (_user instanceof L1SummonInstance)) && (_skill.getArea() > 0 || _skillId == 17)) {
			if (_user.glanceCheck(cha.getX(), cha.getY()) == true ||  _skill.isThrough() == true) {
				return (cha instanceof L1MonsterInstance);
			}
		}
		// 修正召唤怪的范围魔法  end

		// 元のターゲットがPet、Summon以外のNPCの场合、PC、Pet、Summonは对象外
		if ((_calcType == PC_NPC) && (_target instanceof L1NpcInstance) && !(_target instanceof L1PetInstance)
				&& !(_target instanceof L1SummonInstance)
				&& ((cha instanceof L1PetInstance) || (cha instanceof L1SummonInstance) || (cha instanceof L1PcInstance))) {
			return false;
		}

		// 元のターゲットがガード以外のNPCの场合、ガードは对象外
		if ((_calcType == PC_NPC) && (_target instanceof L1NpcInstance) && !(_target instanceof L1GuardInstance) && (cha instanceof L1GuardInstance)) {
			return false;
		}

		// NPC对PCでターゲットがモンスターの场合ターゲットではない。
		if ((_skill.getTarget().equals("attack") || (_skill.getType() == L1Skills.TYPE_ATTACK)) && (_calcType == NPC_PC)
				&& !(cha instanceof L1PetInstance) && !(cha instanceof L1SummonInstance) && !(cha instanceof L1PcInstance)) {
			return false;
		}

		// NPC对NPCで使用者がMOBで、ターゲットがMOBの场合ターゲットではない。
		// 怪对怪放魔法 (单体攻击)
		if ((_skill.getTarget().equals("attack") || (_skill.getType() == L1Skills.TYPE_ATTACK)) && (_calcType == NPC_NPC)
				&& (_user instanceof L1MonsterInstance) && (cha instanceof L1MonsterInstance)) {
			return (L1WilliamSystemMessage.ShowMessage(1001).equals("true")); // 开关 (true) (false)
		}

		// 无方向范围攻击魔法で攻击できないNPCは对象外
		if (_skill.getTarget().equals("none")
				&& (_skill.getType() == L1Skills.TYPE_ATTACK)
				&& ((cha instanceof L1AuctionBoardInstance) || (cha instanceof L1BoardInstance) || (cha instanceof L1CrownInstance)
						|| (cha instanceof L1DwarfInstance) || (cha instanceof L1EffectInstance) || (cha instanceof L1FieldObjectInstance)
						|| (cha instanceof L1FurnitureInstance) || (cha instanceof L1HousekeeperInstance) || (cha instanceof L1MerchantInstance) || (cha instanceof L1TeleporterInstance))) {
			return false;
		}

		// 攻击型魔法无法攻击自己
		if ((_skill.getType() == L1Skills.TYPE_ATTACK) && (cha.getId() == _user.getId())) {
			return false;
		}
		//修正怪物的范围负面魔法不会放到自己
		if ((_user instanceof L1MonsterInstance) && (cha instanceof L1MonsterInstance)
				&& _skill.getType() == L1Skills.TYPE_PROBABILITY) {
			return false;
		}
		// 体力回复术判断施法者不补血
		if ((cha.getId() == _user.getId()) && (_skillId == HEAL_ALL)) {
			return false;
		}

		if ((((_skill.getTargetTo() & L1Skills.TARGET_TO_PC) == L1Skills.TARGET_TO_PC)
				|| ((_skill.getTargetTo() & L1Skills.TARGET_TO_CLAN) == L1Skills.TARGET_TO_CLAN) || ((_skill.getTargetTo() & L1Skills.TARGET_TO_PARTY) == L1Skills.TARGET_TO_PARTY))
				&& (cha.getId() == _user.getId()) && (_skillId != HEAL_ALL)) {
			return true; // ターゲットがパーティーかクラン员のものは自分に效果がある。（ただし、ヒールオールは除外）
		}

		// スキル使用者がPCで、PKモードではない场合、自分のサモン・ペットは对象外
		if ((_user instanceof L1PcInstance) && (_skill.getTarget().equals("attack") || (_skill.getType() == L1Skills.TYPE_ATTACK))
				&& (_isPK == false)) {
			if (cha instanceof L1SummonInstance) {
				L1SummonInstance summon = (L1SummonInstance) cha;
				if (_player.getId() == summon.getMaster().getId()) {
					return false;
				}
			}
			else if (cha instanceof L1PetInstance) {
				L1PetInstance pet = (L1PetInstance) cha;
				if (_player.getId() == pet.getMaster().getId()) {
					return false;
				}
			}
		}

		if ((_skill.getTarget().equals("attack") || (_skill.getType() == L1Skills.TYPE_ATTACK)) && !(cha instanceof L1MonsterInstance)
				&& (_isPK == false) && (_target instanceof L1PcInstance)) {
			L1PcInstance enemy = (L1PcInstance) cha;
			// カウンターディテクション
			if ((_skillId == COUNTER_DETECTION) && (enemy.getZoneType() != 1)
					&& (cha.hasSkillEffect(INVISIBILITY) || cha.hasSkillEffect(BLIND_HIDING))) {
				return true; // インビジかブラインドハイディング中
			}
			if ((_player.getClanid() != 0) && (enemy.getClanid() != 0)) { // クラン所属中
				// 全战争リストを取得
				for (L1War war : L1World.getInstance().getWarList()) {
					if (war.CheckClanInWar(_player.getClanname())) { // 自クランが战争に参加中
						if (war.CheckClanInSameWar( // 同じ战争に参加中
								_player.getClanname(), enemy.getClanname())) {
							if (L1CastleLocation.checkInAllWarArea(enemy.getX(), enemy.getY(), enemy.getMapId())) {
								return true;
							}
						}
					}
				}
			}
			return false; // 攻击スキルでPKモードじゃない场合
		}

		if ((_user.glanceCheck(cha.getX(), cha.getY()) == false) && (_skill.isThrough() == false)) {
			// エンチャント、复活スキルは障害物の判定をしない
			if (!((_skill.getType() == L1Skills.TYPE_CHANGE) || (_skill.getType() == L1Skills.TYPE_RESTORE))) {
				_isGlanceCheckFail = true;
				return false; // 直线上に障害物がある
			}
		}

		if (cha.hasSkillEffect(ICE_LANCE) || cha.hasSkillEffect(FREEZING_BLIZZARD) || cha.hasSkillEffect(FREEZING_BREATH) 
			|| cha.hasSkillEffect(ICE_LANCE_COCKATRICE) || cha.hasSkillEffect(ICE_LANCE_BASILISK)) {
			if (_skillId == ICE_LANCE || _skillId == FREEZING_BLIZZARD
				|| _skillId == FREEZING_BREATH || _skillId == ICE_LANCE_COCKATRICE || _skillId == ICE_LANCE_BASILISK) {
				return false;
			}
		}
/*
		if (cha.hasSkillEffect(ICE_LANCE) && ((_skillId == ICE_LANCE) || (_skillId == FREEZING_BLIZZARD) || (_skillId == FREEZING_BREATH))) {
			return false; // アイスランス中にアイスランス、フリージングブリザード、フリージングブレス
		}

		if (cha.hasSkillEffect(FREEZING_BLIZZARD) && ((_skillId == ICE_LANCE) || (_skillId == FREEZING_BLIZZARD) || (_skillId == FREEZING_BREATH))) {
			return false; // フリージングブリザード中にアイスランス、フリージングブリザード、フリージングブレス
		}

		if (cha.hasSkillEffect(FREEZING_BREATH) && ((_skillId == ICE_LANCE) || (_skillId == FREEZING_BLIZZARD) || (_skillId == FREEZING_BREATH))) {
			return false; // フリージングブレス中にアイスランス、フリージングブリザード、フリージングブレス
		}
*/
		if (cha.hasSkillEffect(EARTH_BIND) && (_skillId == EARTH_BIND)) {
			return false; // アース バインド中にアース バインド
		}

		if (!(cha instanceof L1MonsterInstance) && ((_skillId == TAMING_MONSTER) || (_skillId == CREATE_ZOMBIE))) {
			return false; // ターゲットがモンスターじゃない（テイミングモンスター）
		}
		if (cha.isDead()
				&& ((_skillId != CREATE_ZOMBIE) && (_skillId != RESURRECTION) && (_skillId != GREATER_RESURRECTION) && (_skillId != CALL_OF_NATURE))) {
			return false; // 目标已死亡 法术非复活类
		}

		if ((cha.isDead() == false)
				&& ((_skillId == CREATE_ZOMBIE) || (_skillId == RESURRECTION) || (_skillId == GREATER_RESURRECTION) || (_skillId == CALL_OF_NATURE))) {
			return false; // 目标未死亡 法术复活类
		}

		if (((cha instanceof L1TowerInstance) || (cha instanceof L1DoorInstance))
				&& ((_skillId == CREATE_ZOMBIE) || (_skillId == RESURRECTION) || (_skillId == GREATER_RESURRECTION) || (_skillId == CALL_OF_NATURE))) {
			return false; // 塔跟门不可放复活法术
		}

		if (cha instanceof L1PcInstance) {
			L1PcInstance pc = (L1PcInstance) cha;
			if (pc.hasSkillEffect(ABSOLUTE_BARRIER)) { // 绝对屏障状态中
				if ((_skillId == CURSE_BLIND) || (_skillId == WEAPON_BREAK) || (_skillId == DARKNESS) || (_skillId == WEAKNESS)
						|| (_skillId == DISEASE) || (_skillId == FOG_OF_SLEEPING) || (_skillId == MASS_SLOW) || (_skillId == SLOW)
						|| (_skillId == CANCELLATION) || (_skillId == SILENCE) || (_skillId == DECAY_POTION) || (_skillId == MASS_TELEPORT)
						|| (_skillId == DETECTION) || (_skillId == COUNTER_DETECTION) || (_skillId == ERASE_MAGIC) || (_skillId == ENTANGLE)
						|| (_skillId == PHYSICAL_ENCHANT_DEX) || (_skillId == PHYSICAL_ENCHANT_STR) || (_skillId == BLESS_WEAPON)
						|| (_skillId == EARTH_SKIN) || (_skillId == IMMUNE_TO_HARM) || (_skillId == REMOVE_CURSE)) {
					return true;
				}
				else {
					return false;
				}
			}
		}

		if (cha instanceof L1NpcInstance) {
			int hiddenStatus = ((L1NpcInstance) cha).getHiddenStatus();
			if (hiddenStatus == L1NpcInstance.HIDDEN_STATUS_SINK) {
				if ((_skillId == DETECTION) || (_skillId == COUNTER_DETECTION)) { // ディテク、Cディテク
					return true;
				}
				else {
					return false;
				}
			}
			else if (hiddenStatus == L1NpcInstance.HIDDEN_STATUS_FLY) {
				return false;
			}
		}

		if (((_skill.getTargetTo() & L1Skills.TARGET_TO_PC) == L1Skills.TARGET_TO_PC // PC目标
				)
				&& (cha instanceof L1PcInstance)) {
			_flg = true;
		}
		else if (((_skill.getTargetTo() & L1Skills.TARGET_TO_NPC) == L1Skills.TARGET_TO_NPC // 目标NPC
				)
				&& ((cha instanceof L1MonsterInstance) || (cha instanceof L1NpcInstance) || (cha instanceof L1SummonInstance) || (cha instanceof L1PetInstance))) {
			_flg = true;
		}
		else if (((_skill.getTargetTo() & L1Skills.TARGET_TO_PET) == L1Skills.TARGET_TO_PET) && (_user instanceof L1PcInstance)) { // 目标 召唤怪与宠物
			if (cha instanceof L1SummonInstance) {
				L1SummonInstance summon = (L1SummonInstance) cha;
				if (summon.getMaster() != null) {
					if (_player.getId() == summon.getMaster().getId()) {
						_flg = true;
					}
				}
			}
			else if (cha instanceof L1PetInstance) {
				L1PetInstance pet = (L1PetInstance) cha;
				if (pet.getMaster() != null) {
					if (_player.getId() == pet.getMaster().getId()) {
						_flg = true;
					}
				}
			}
		}

		if ((_calcType == PC_PC) && (cha instanceof L1PcInstance)) {
			if (((_skill.getTargetTo() & L1Skills.TARGET_TO_CLAN) == L1Skills.TARGET_TO_CLAN) && (((_player.getClanid() != 0 // 血盟目标
					) && (_player.getClanid() == ((L1PcInstance) cha).getClanid())) || _player.isGm())) {
				return true;
			}
			if (((_skill.getTargetTo() & L1Skills.TARGET_TO_PARTY) == L1Skills.TARGET_TO_PARTY) && (_player.getParty() // 组队目标
					.isMember((L1PcInstance) cha) || _player.isGm())) {
				return true;
			}
		}

		return _flg;
	}

	// ターゲットの一览を作成
	private void makeTargetList() {
		try {
			if (_type == TYPE_LOGIN) { // ログイン时(死亡时、お化け屋敷のキャンセレーション含む)は使用者のみ
				_targetList.add(new TargetStatus(_user));
				return;
			}
			if ((_skill.getTargetTo() == L1Skills.TARGET_TO_ME) && ((_skill.getType() & L1Skills.TYPE_ATTACK) != L1Skills.TYPE_ATTACK)) {
				_targetList.add(new TargetStatus(_user)); // ターゲットは使用者のみ
				return;
			}

			// 射程距离-1の场合は画面内のオブジェクトが对象
			if (getSkillRanged() != -1) {
				if (_user.getLocation().getTileLineDistance(_target.getLocation()) > getSkillRanged()) {
					return; // 射程范围外
				}
			}
			else {
				if (!_user.getLocation().isInScreen(_target.getLocation())) {
					return; // 射程范围外
				}
			}

			if ((isTarget(_target) == false) && !(_skill.getTarget().equals("none"))) {
				// 对象が违うのでスキルが発动しない。
				return;
			}

			if ((_skillId == LIGHTNING) || (_skillId == FREEZING_BREATH)) { // ライトニング、フリージングブレス直线的に范围を决める
				List<L1Object> al1object = L1World.getInstance().getVisibleLineObjects(_user, _target);

				for (L1Object tgobj : al1object) {
					if (tgobj == null) {
						continue;
					}
					if (!(tgobj instanceof L1Character)) { // ターゲットがキャラクター以外の场合何もしない。
						continue;
					}
					L1Character cha = (L1Character) tgobj;
					if (isTarget(cha) == false) {
						continue;
					}
					_targetList.add(new TargetStatus(cha));
				}
				return;
			}

			if (getSkillArea() == 0) { // 单体の场合
				if (!_user.glanceCheck(_target.getX(), _target.getY())) { // 直线上に障害物があるか
					if (((_skill.getType() & L1Skills.TYPE_ATTACK) == L1Skills.TYPE_ATTACK) && (_skillId != 10026) && (_skillId != 10027)
							&& (_skillId != 10028) && (_skillId != 10029)) { // 安息攻击以外の攻击スキル
						_targetList.add(new TargetStatus(_target, false)); // ダメージも発生しないし、ダメージモーションも発生しないが、スキルは発动
						return;
					}
				}
				_targetList.add(new TargetStatus(_target));
			}
			else { // 范围の场合
				if (!_skill.getTarget().equals("none")) {
					_targetList.add(new TargetStatus(_target));
				}

				if ((_skillId != 49) && !(_skill.getTarget().equals("attack") || (_skill.getType() == L1Skills.TYPE_ATTACK))) {
					// 攻击系以外のスキルとH-A以外はターゲット自身を含める
					_targetList.add(new TargetStatus(_user));
				}

				List<L1Object> objects;
				if (getSkillArea() == -1) {
					objects = L1World.getInstance().getVisibleObjects(_user);
				}
				else {
					objects = L1World.getInstance().getVisibleObjects(_target, getSkillArea());
				}
				for (L1Object tgobj : objects) {
					if (tgobj == null) {
						continue;
					}
					if (!(tgobj instanceof L1Character)) { // ターゲットがキャラクター以外の场合何もしない。
						continue;
					}
					L1Character cha = (L1Character) tgobj;
					if (!isTarget(cha)) {
						continue;
					}

					_targetList.add(new TargetStatus(cha));
				}
				return;
			}

		}
		catch (Exception e) {
			_log.log(Level.FINEST, "exception in L1Skilluse makeTargetList{0}", e);
		}
	}

	// メッセージの表示（何か起こったとき）
	private void sendHappenMessage(L1PcInstance pc) {
		int msgID = _skill.getSysmsgIdHappen();
		if (msgID > 0) {
			// 效果讯息排除施法者本身。
			if (_skillId == AREA_OF_SILENCE && _user.getId() == pc.getId()) {// 封印禁地
				return;
			}
			pc.sendPackets(new S_ServerMessage(msgID));
		}
	}

	// 失败メッセージ表示のハンドル
	private void sendFailMessageHandle() {
		// 攻击スキル以外で对象を指定するスキルが失败した场合は失败したメッセージをクライアントに送信
		// ※攻击スキルは障害物があっても成功时と同じアクションであるべき。
		if ((_skill.getType() != L1Skills.TYPE_ATTACK) && !_skill.getTarget().equals("none") && _targetList.isEmpty()) {
			sendFailMessage();
		}
	}

	// メッセージの表示（失败したとき）
	private void sendFailMessage() {
		int msgID = _skill.getSysmsgIdFail();
		if ((msgID > 0) && (_user instanceof L1PcInstance)) {
			_player.sendPackets(new S_ServerMessage(msgID));
		}
	}

	// 精灵魔法の属性と使用者の属性は一致するか？（とりあえずの对处なので、对应できたら消去して下さい)
	private boolean isAttrAgrees() {
		int magicattr = _skill.getAttr();
		if (_user instanceof L1NpcInstance) { // NPCが使った场合なんでもOK
			return true;
		}

		if ((_skill.getSkillLevel() >= 17) && (_skill.getSkillLevel() <= 22)
				&& (magicattr != 0 // 精灵魔法で、无属性魔法ではなく、
				) && (magicattr != _player.getElfAttr() && magicattr != _player.getElfAttr2() // 使用者と魔法の属性が一致しない。
				) && !_player.isGm()) { // ただしGMは例外
			return false;
		}
		return true;
	}

	@SuppressWarnings("unused")
	private boolean isAttrAgrees1() {
		int magicattr = _skill.getAttr();
		boolean isAttr = false;

		if (_user instanceof L1NpcInstance) { // NPCが使った场合なんでもOK
			return true;
		}

        if (magicattr == _player.getElfAttr()){
        	_player.sendPackets(new S_SystemMessage("这是第一属性魔法！")); 
        	isAttr = true;
        }
        if (magicattr == _player.getElfAttr2()){
        	_player.sendPackets(new S_SystemMessage("这是第二属性魔法！")); 
        	isAttr = true;
        }
        if (_skill.getSkillLevel() >= 17 && _skill.getSkillLevel() <= 22
        		&& magicattr != 0 // 精灵魔法で、无属性魔法ではなく、
        		&& !isAttr  // 使用者と魔法の属性が一致しない。
        		&& !_player.isGm()) { // ただしGMは例外
        	_player.sendPackets(new S_SystemMessage("施法失败！")); 
        	return false;
        }
        return true;
	}

	// 必要ＨＰ、ＭＰがあるか？
	private boolean isHPMPConsume() {
		if (_mpConsume == 0) {
			_mpConsume = _skill.getMpConsume();
		}
		_hpConsume = _skill.getHpConsume();
		int currentMp = 0;
		int currentHp = 0;

		if (_user instanceof L1NpcInstance) {
			currentMp = _npc.getCurrentMp();
			currentHp = _npc.getCurrentHp();
		}
		else {
			currentMp = _player.getCurrentMp();
			currentHp = _player.getCurrentHp();

			// MPのINT轻减
			if ((_player.getInt() > 12) && (_skillId > HOLY_WEAPON) && (_skillId <= FREEZING_BLIZZARD)) { // LV2以上
				_mpConsume--;
			}
			if ((_player.getInt() > 13) && (_skillId > STALAC) && (_skillId <= FREEZING_BLIZZARD)) { // LV3以上
				_mpConsume--;
			}
			if ((_player.getInt() > 14) && (_skillId > WEAK_ELEMENTAL) && (_skillId <= FREEZING_BLIZZARD)) { // LV4以上
				_mpConsume--;
			}
			if ((_player.getInt() > 15) && (_skillId > MEDITATION) && (_skillId <= FREEZING_BLIZZARD)) { // LV5以上
				_mpConsume--;
			}
			if ((_player.getInt() > 16) && (_skillId > DARKNESS) && (_skillId <= FREEZING_BLIZZARD)) { // LV6以上
				_mpConsume--;
			}
			if ((_player.getInt() > 17) && (_skillId > BLESS_WEAPON) && (_skillId <= FREEZING_BLIZZARD)) { // LV7以上
				_mpConsume--;
			}
			if ((_player.getInt() > 18) && (_skillId > DISEASE) && (_skillId <= FREEZING_BLIZZARD)) { // LV8以上
				_mpConsume--;
			}

			// 骑士智力减免
			if ((_player.getInt() > 12) && (_skillId >= SHOCK_STUN) && (_skillId <= COUNTER_BARRIER)) {
				if ( _player.getInt() <= 17 )
					_mpConsume -= (_player.getInt() - 12);
				else {
					_mpConsume -= 5 ; // int > 18
					if ( _mpConsume > 1 ) { // 法术还可以减免
						byte extraInt = (byte) (_player.getInt() - 17) ;
						// 减免公式
						for ( int first= 1 ,range = 2 ; first <= extraInt; first += range, range ++  )  
							_mpConsume -- ;
					}
				}
			}

			// 装备MP减免 一次只需判断一个
			if ((_skillId == PHYSICAL_ENCHANT_DEX) && _player.getInventory().checkEquipped(20013)) { // 敏捷魔法头盔使用通畅气脉术
				_mpConsume /= 2;
			}
			else if ((_skillId == HASTE) && _player.getInventory().checkEquipped(20013)) { // 敏捷魔法头盔使用加速术
				_mpConsume /= 2;
			}
			else if ((_skillId == HEAL) && _player.getInventory().checkEquipped(20014)) { // 治愈魔法头盔使用初级治愈术
				_mpConsume /= 2;
			}
			else if ((_skillId == EXTRA_HEAL) && _player.getInventory().checkEquipped(20014)) { // 治愈魔法头盔使用中级治愈术
				_mpConsume /= 2;
			}
			else if ((_skillId == ENCHANT_WEAPON) && _player.getInventory().checkEquipped(20015)) { // 力量魔法头盔使用拟似魔法武器
				_mpConsume /= 2;
			}
			else if ((_skillId == DETECTION) && _player.getInventory().checkEquipped(20015)) { // 力量魔法头盔使用无所遁形术
				_mpConsume /= 2;
			}
			else if ((_skillId == PHYSICAL_ENCHANT_STR) && _player.getInventory().checkEquipped(20015)) { // 力量魔法头盔使用体魄强健术
				_mpConsume /= 2;
			}
			else if ((_skillId == HASTE) && _player.getInventory().checkEquipped(20008)) { // 小型风之头盔使用加速术
				_mpConsume /= 2;
			}
			else if ((_skillId == HASTE) && _player.getInventory().checkEquipped(20023)) { // 风之头盔使用加速术
				_mpConsume = 25;
			}
			else if ((_skillId == GREATER_HASTE) && _player.getInventory().checkEquipped(20023)) { // 风之头盔使用强力加速术
				_mpConsume /= 2;
			}

			// 初始能力减免
			if (_player.getOriginalMagicConsumeReduction() > 0) {
				_mpConsume -= _player.getOriginalMagicConsumeReduction();
			}

			if (0 < _skill.getMpConsume()) {
				_mpConsume = Math.max(_mpConsume, 1); // 最小值 1
			}
		}

		if (currentHp < _hpConsume + 1) {
			if (_user instanceof L1PcInstance) {
				_player.sendPackets(new S_ServerMessage(279)); // \f1因体力不足而无法使用魔法。
			}
			return false;
		}
		else if (currentMp < _mpConsume) {
			if (_user instanceof L1PcInstance) {
				_player.sendPackets(new S_ServerMessage(278)); // \f1因魔力不足而无法使用魔法。
			}
			return false;
		}

		return true;
	}

	// 必要材料があるか？
	private boolean isItemConsume() {

		int itemConsume = _skill.getItemConsumeId();
		int itemConsumeCount = _skill.getItemConsumeCount();

		if (itemConsume == 0) {
			return true; // 材料を必要としない魔法
		}

		if (!_player.getInventory().checkItem(itemConsume, itemConsumeCount)) {
			return false; // 必要材料が足りなかった。
		}

		return true;
	}

	// 使用材料、HP・MP、Lawfulをマイナスする。
	private void useConsume() {
		if (_user instanceof L1NpcInstance) {
			// NPCの场合、HP、MPのみマイナス
			int current_hp = _npc.getCurrentHp() - _hpConsume;
			_npc.setCurrentHp(current_hp);

			int current_mp = _npc.getCurrentMp() - _mpConsume;
			_npc.setCurrentMp(current_mp);
			return;
		}

		// HP・MP花费 已经计算使用量
		if (_skillId == FINAL_BURN) { // 会心一击

				_player.setCurrentHp(1);
				_player.setCurrentMp(0);
		}
		else {
			int current_hp = _player.getCurrentHp() - _hpConsume;
			_player.setCurrentHp(current_hp);

			int current_mp = _player.getCurrentMp() - _mpConsume;
			_player.setCurrentMp(current_mp);

		}

		// Lawful(正义值)
		int lawful = _player.getLawful() + _skill.getLawful();
		if (lawful > 32767) {
			lawful = 32767;
		}
		else if (lawful < -32767) {
			lawful = -32767;
		}
		_player.setLawful(lawful);

		int itemConsume = _skill.getItemConsumeId();
		int itemConsumeCount = _skill.getItemConsumeCount();

		if (itemConsume == 0) {
			return; // 材料を必要としない魔法
		}

		// 使用材料をマイナス
		_player.getInventory().consumeItem(itemConsume, itemConsumeCount);
	}

	// 追加魔法列表。
	private void addMagicList(L1Character cha, boolean repetition) {
		if (_skillTime == 0) {
			_getBuffDuration = _skill.getBuffDuration() * 1000; // 效果时间
			if (_skill.getBuffDuration() == 0) {
				if (_skillId == INVISIBILITY) { // 隐身术
					cha.setSkillEffect(INVISIBILITY, 0);
				}
				return;
			}
		}
		else {
			_getBuffDuration = _skillTime * 1000; // パラメータのtimeが0以外なら、效果时间として设定する
		}

		if (_skillId == SHOCK_STUN) {
			_getBuffDuration = _shockStunDuration;
		}

		if (_skillId == CURSE_POISON) {  // 毒咒持续时间移至 L1Poison 处理。
			return;
		}
		if ((_skillId == CURSE_PARALYZE) || (_skillId == CURSE_PARALYZE2)) { // 木乃伊的咀咒、石化持续时间移至 L1CurseParalysis 处理。
			return;
		}
		if (_skillId == SHAPE_CHANGE) { // 变形术持续时间移至 L1PolyMorph 处理。
			return;
		}
		if ((_skillId == BLESSED_ARMOR) || (_skillId == HOLY_WEAPON // 武器・防具に效果がある处理はL1ItemInstanceに移让。
				) || (_skillId == ENCHANT_WEAPON) || (_skillId == BLESS_WEAPON) || (_skillId == SHADOW_FANG)) {
			return;
		}
		if (((_skillId == ICE_LANCE) || (_skillId == FREEZING_BLIZZARD) || (_skillId == FREEZING_BREATH) 
			|| (_skillId == ICE_LANCE_COCKATRICE) || (_skillId == ICE_LANCE_BASILISK)) && !_isFreeze) { // 冻结失败
			return;
		}
		if ((_skillId == AWAKEN_ANTHARAS) || (_skillId == AWAKEN_FAFURION) || (_skillId == AWAKEN_VALAKAS)) { // 觉醒の效果处理はL1Awakeに移让。
			return;
		}
		// 骷髅毁坏持续时间另外处理
		if (_skillId == BONE_BREAK || _skillId == CONFUSION) {
			return;
		}
		cha.setSkillEffect(_skillId, _getBuffDuration);

		if (_skillId == ELEMENTAL_FALL_DOWN && repetition) { // 弱化属性重复施放
			if (_skillTime == 0) {
				_getBuffIconDuration = _skill.getBuffDuration(); // 效果时间
			} else {
				_getBuffIconDuration = _skillTime;
			}
			_target.removeSkillEffect(ELEMENTAL_FALL_DOWN);
			runSkill();
			return;
		}
		if ((cha instanceof L1PcInstance) && repetition) { // 对象がPCで既にスキルが重复している场合
			L1PcInstance pc = (L1PcInstance) cha;
			sendIcon(pc);
		}
	}

	// アイコンの送信
	private void sendIcon(L1PcInstance pc) {
		if (_skillTime == 0) {
			_getBuffIconDuration = _skill.getBuffDuration(); // 效果时间
		}
		else {
			_getBuffIconDuration = _skillTime; // パラメータのtimeが0以外なら、效果时间として设定する
		}

		if (_skillId == SHIELD) { // シールド
			pc.sendPackets(new S_SkillIconShield(5, _getBuffIconDuration));
		}
		else if (_skillId == SHADOW_ARMOR) { // シャドウ アーマー
			pc.sendPackets(new S_SkillIconShield(3, _getBuffIconDuration));
		}
		else if (_skillId == DRESS_DEXTERITY) { // ドレス デクスタリティー
			pc.sendPackets(new S_Dexup(pc, 2, _getBuffIconDuration));
		}
		else if (_skillId == DRESS_MIGHTY) { // ドレス マイティー
			pc.sendPackets(new S_Strup(pc, 2, _getBuffIconDuration));
		}
		else if (_skillId == GLOWING_AURA) { // グローウィング オーラ
			pc.sendPackets(new S_SkillIconAura(113, _getBuffIconDuration));
		}
		else if (_skillId == SHINING_AURA) { // シャイニング オーラ
			pc.sendPackets(new S_SkillIconAura(114, _getBuffIconDuration));
		}
		else if (_skillId == BRAVE_AURA) { // ブレイブ オーラ
			pc.sendPackets(new S_SkillIconAura(116, _getBuffIconDuration));
		}
		else if (_skillId == FIRE_WEAPON) { // ファイアー ウェポン
			pc.sendPackets(new S_SkillIconAura(147, _getBuffIconDuration));
		}
		else if (_skillId == WIND_SHOT) { // ウィンド ショット
			pc.sendPackets(new S_SkillIconAura(148, _getBuffIconDuration));
		}
		else if (_skillId == FIRE_BLESS) { // ファイアー ブレス
			pc.sendPackets(new S_SkillIconAura(154, _getBuffIconDuration));
		}
		else if (_skillId == STORM_EYE) { // ストーム アイ
			pc.sendPackets(new S_SkillIconAura(155, _getBuffIconDuration));
		}
		else if (_skillId == EARTH_BLESS) { // アース ブレス
			pc.sendPackets(new S_SkillIconShield(7, _getBuffIconDuration));
		}
		else if (_skillId == BURNING_WEAPON) { // バーニング ウェポン
			pc.sendPackets(new S_SkillIconAura(162, _getBuffIconDuration));
		}
		else if (_skillId == STORM_SHOT) { // ストーム ショット
			pc.sendPackets(new S_SkillIconAura(165, _getBuffIconDuration));
		}
		else if (_skillId == IRON_SKIN) { // アイアン スキン
			pc.sendPackets(new S_SkillIconShield(10, _getBuffIconDuration));
		}
		else if (_skillId == EARTH_SKIN) { // アース スキン
			pc.sendPackets(new S_SkillIconShield(6, _getBuffIconDuration));
		}
		else if (_skillId == PHYSICAL_ENCHANT_STR) { // フィジカル エンチャント：STR
			pc.sendPackets(new S_Strup(pc, 5, _getBuffIconDuration));
		}
		else if (_skillId == PHYSICAL_ENCHANT_DEX) { // フィジカル エンチャント：DEX
			pc.sendPackets(new S_Dexup(pc, 5, _getBuffIconDuration));
		}
		else if ((_skillId == HASTE) || (_skillId == GREATER_HASTE)) { // グレーターヘイスト
			pc.sendPackets(new S_SkillHaste(pc.getId(), 1, _getBuffIconDuration));
			pc.broadcastPacket(new S_SkillHaste(pc.getId(), 1, 0));
		}
		else if ((_skillId == HOLY_WALK) || (_skillId == MOVING_ACCELERATION) || (_skillId == WIND_WALK)) { // ホーリーウォーク、ムービングアクセレーション、ウィンドウォーク
			pc.sendPackets(new S_SkillBrave(pc.getId(), 4, _getBuffIconDuration));
			pc.broadcastPacket(new S_SkillBrave(pc.getId(), 4, 0));
		}
		else if (_skillId == BLOODLUST) { // ブラッドラスト
			pc.sendPackets(new S_SkillBrave(pc.getId(), 6, _getBuffIconDuration));
			pc.broadcastPacket(new S_SkillBrave(pc.getId(), 6, 0));
		}
		else if ((_skillId == SLOW) || (_skillId == MASS_SLOW) || (_skillId == ENTANGLE)) { // スロー、エンタングル、マススロー
			pc.sendPackets(new S_SkillHaste(pc.getId(), 2, _getBuffIconDuration));
			pc.broadcastPacket(new S_SkillHaste(pc.getId(), 2, 0));
		}
		else if (_skillId == IMMUNE_TO_HARM) {
			pc.sendPackets(new S_SkillIconGFX(40, _getBuffIconDuration));
		}
		else if (_skillId == WIND_SHACKLE) { // 风之枷锁
			pc.sendPackets(new S_SkillIconWindShackle(pc.getId(), _getBuffIconDuration));
			pc.broadcastPacket(new S_SkillIconWindShackle(pc.getId(), _getBuffIconDuration));
		}
		pc.sendPackets(new S_OwnCharStatus(pc));
	}

	// グラフィックの送信
	private void sendGrfx(boolean isSkillAction) {
		if (_actid == 0) {
			_actid = _skill.getActionId();
		}
		if (_gfxid == 0) {
			_gfxid = _skill.getCastGfx();
		}
		if (_gfxid == 0) {
			return; // 表示するグラフィックが无い
		}
		int[] data = null;

		if (_user instanceof L1PcInstance) {

			int targetid = 0;
			if (_skillId != FIRE_WALL) {
				targetid = _target.getId();
			}
			L1PcInstance pc = (L1PcInstance) _user;

			switch(_skillId) {
				case FIRE_WALL: // 火牢
				case LIFE_STREAM: // 治愈能量风暴
				case ELEMENTAL_FALL_DOWN: // 弱化属性
					if (_skillId == FIRE_WALL) {
						pc.setHeading(pc.targetDirection(_targetX, _targetY));
						pc.sendPackets(new S_ChangeHeading(pc));
						pc.broadcastPacket(new S_ChangeHeading(pc));
					}
					S_DoActionGFX gfx = new S_DoActionGFX(pc.getId(), _actid);
					pc.sendPackets(gfx);
					pc.broadcastPacket(gfx);
					return;
				case SHOCK_STUN: // 冲击之晕
					if (_targetList.isEmpty()) { // 失败
						return;
					} else {
						if (_target instanceof L1PcInstance) {
							L1PcInstance targetPc = (L1PcInstance) _target;
							targetPc.sendPackets(new S_SkillSound(targetid, 4434));
							targetPc.broadcastPacket(new S_SkillSound(targetid, 4434));
						} else if (_target instanceof L1NpcInstance) {
							_target.broadcastPacket(new S_SkillSound(targetid, 4434));
						}
						return;
					}
				case LIGHT: // 日光术
					pc.sendPackets(new S_Sound(145));
					break;
				case MIND_BREAK: // 心灵破坏
				case JOY_OF_PAIN: // 疼痛的欢愉
					data = new int[] {_actid, _dmg, 0}; // data = {actid, dmg, effect}
					pc.sendPackets(new S_AttackPacket(pc, targetid, data));
					pc.broadcastPacket(new S_AttackPacket(pc, targetid, data));
					pc.sendPackets(new S_SkillSound(targetid, _gfxid));
					pc.broadcastPacket(new S_SkillSound(targetid, _gfxid));
					return;
				case CONFUSION: // 混乱
					data = new int[] {_actid, _dmg, 0}; // data = {actid, dmg, effect}
					pc.sendPackets(new S_AttackPacket(pc, targetid, data));
					pc.broadcastPacket(new S_AttackPacket(pc, targetid, data));
					return;
				case SMASH: // 暴击
					pc.sendPackets(new S_SkillSound(targetid, _gfxid));
					pc.broadcastPacket(new S_SkillSound(targetid, _gfxid));
					return;
				case TAMING_MONSTER: // 迷魅
					pc.sendPackets(new S_EffectLocation(_targetX, _targetY, _gfxid));
					pc.broadcastPacket(new S_EffectLocation(_targetX, _targetY, _gfxid));
					return;
				default:
					break;
			}

			if (_targetList.isEmpty() && !(_skill.getTarget().equals("none"))) {
				// ターゲット数が０で对象を指定するスキルの场合、魔法使用エフェクトだけ表示して终了
				int tempchargfx = _player.getTempCharGfx();
				if ((tempchargfx == 5727) || (tempchargfx == 5730)) { // シャドウ系变身のモーション对应
					_actid = ActionCodes.ACTION_SkillBuff;
				}
				else if ((tempchargfx == 5733) || (tempchargfx == 5736)) {
					_actid = ActionCodes.ACTION_Attack;
				}
				if (isSkillAction) {
					S_DoActionGFX gfx = new S_DoActionGFX(_player.getId(), _actid);
					_player.sendPackets(gfx);
					_player.broadcastPacket(gfx);
				}
				return;
			}

			if (_skill.getTarget().equals("attack") && (_skillId != 18)) {
				if (isPcSummonPet(_target)) { // 目标玩家、宠物、召唤兽
					if ((_player.getZoneType() == 1) || (_target.getZoneType() == 1)
							|| _player.checkNonPvP(_player, _target)) { // Non-PvP设定
						data = new int[] {_actid, 0, _gfxid, 6};
						_player.sendPackets(new S_UseAttackSkill(_player, _target.getId(), _targetX, _targetY, data));
						_player.broadcastPacket(new S_UseAttackSkill(_player, _target.getId(), _targetX, _targetY, data));
						return;
					}
				}

				if (getSkillArea() == 0) {  // 单体攻击魔法
					data = new int[] {_actid, _dmg, _gfxid, 6};
					_player.sendPackets(new S_UseAttackSkill(_player, targetid, _targetX, _targetY, data));
					_player.broadcastPacket(new S_UseAttackSkill(_player, targetid, _targetX, _targetY, data));
					_target.broadcastPacketExceptTargetSight(new S_DoActionGFX(targetid, ActionCodes.ACTION_Damage), _player);
				}
				else { // 有方向范围攻击魔法
					L1Character[] cha = new L1Character[_targetList.size()];
					int i = 0;
					for (TargetStatus ts : _targetList) {
						cha[i] = ts.getTarget();
						i++;
					}
					_player.sendPackets(new S_RangeSkill(_player, cha, _gfxid, _actid, S_RangeSkill.TYPE_DIR));
					_player.broadcastPacket(new S_RangeSkill(_player, cha, _gfxid, _actid, S_RangeSkill.TYPE_DIR));
				}
			}
			else if (_skill.getTarget().equals("none") && (_skill.getType() == L1Skills.TYPE_ATTACK)) { // 无方向范围攻击魔法
				L1Character[] cha = new L1Character[_targetList.size()];
				int i = 0;
				for (TargetStatus ts : _targetList) {
					cha[i] = ts.getTarget();
					cha[i].broadcastPacketExceptTargetSight(new S_DoActionGFX(cha[i].getId(), ActionCodes.ACTION_Damage), _player);
					i++;
				}
				_player.sendPackets(new S_RangeSkill(_player, cha, _gfxid, _actid, S_RangeSkill.TYPE_NODIR));
				_player.broadcastPacket(new S_RangeSkill(_player, cha, _gfxid, _actid, S_RangeSkill.TYPE_NODIR));
			}
			else { // 补助魔法
				// 指定传送、集体传送术、世界树的呼唤以外
				if ((_skillId != TELEPORT) && (_skillId != MASS_TELEPORT) && (_skillId != TELEPORT_TO_MATHER)) {
					// 施法动作
					if (isSkillAction) {
						S_DoActionGFX gfx = new S_DoActionGFX(_player.getId(), _skill.getActionId());
						_player.sendPackets(gfx);
						_player.broadcastPacket(gfx);
					}
					// 魔法屏障、反击屏障、镜反射 魔法效果只有自身显示
					if ((_skillId == COUNTER_MAGIC) || (_skillId == COUNTER_BARRIER) || (_skillId == COUNTER_MIRROR)) {
						_player.sendPackets(new S_SkillSound(targetid, _gfxid));
					}
					else if ((_skillId == AWAKEN_ANTHARAS // 觉醒：アンタラス
							)
							|| (_skillId == AWAKEN_FAFURION // 觉醒：パプリオン
							) || (_skillId == AWAKEN_VALAKAS)) { // 觉醒：ヴァラカス
						if (_skillId == _player.getAwakeSkillId()) { // 再咏唱なら解除でエフェクトなし
							_player.sendPackets(new S_SkillSound(targetid, _gfxid));
							_player.broadcastPacket(new S_SkillSound(targetid, _gfxid));
						}
						else {
							return;
						}
					}
					else {
						_player.sendPackets(new S_SkillSound(targetid, _gfxid));
						_player.broadcastPacket(new S_SkillSound(targetid, _gfxid));
					}
				}

				// スキルのエフェクト表示はターゲット全员だが、あまり必要性がないので、ステータスのみ送信
				for (TargetStatus ts : _targetList) {
					L1Character cha = ts.getTarget();
					if (cha instanceof L1PcInstance) {
						L1PcInstance chaPc = (L1PcInstance) cha;
						chaPc.sendPackets(new S_OwnCharStatus(chaPc));
					}
				}
			}
		}
		else if (_user instanceof L1NpcInstance) { // NPCがスキルを使った场合
			int targetid = _target.getId();

			if (_user instanceof L1MerchantInstance) {
				_user.broadcastPacket(new S_SkillSound(targetid, _gfxid));
				return;
			}

			if (_skillId == CURSE_PARALYZE || _skillId == WEAKNESS || _skillId == DISEASE) { // 木乃伊的诅咒、弱化术、疾病术
				_user.setHeading(_user.targetDirection(_targetX, _targetY)); // 改变面向
				_user.broadcastPacket(new S_ChangeHeading(_user));
			}

			if (_targetList.isEmpty() && !(_skill.getTarget().equals("none"))) {
				// ターゲット数が０で对象を指定するスキルの场合、魔法使用エフェクトだけ表示して终了
				S_DoActionGFX gfx = new S_DoActionGFX(_user.getId(), _actid);
				_user.broadcastPacket(gfx);
				return;
			}

			if (_skill.getTarget().equals("attack") && (_skillId != 18)) {
				if (getSkillArea() == 0) { // 单体攻击魔法
					data = new int[] {_actid, _dmg, _gfxid, 6};
					_user.broadcastPacket(new S_UseAttackSkill(_user, targetid, _targetX, _targetY, data));
					_target.broadcastPacketExceptTargetSight(new S_DoActionGFX(targetid, ActionCodes.ACTION_Damage), _user);
				}
				else { // 有方向范围攻击魔法
					L1Character[] cha = new L1Character[_targetList.size()];
					int i = 0;
					for (TargetStatus ts : _targetList) {
						cha[i] = ts.getTarget();
						cha[i].broadcastPacketExceptTargetSight(new S_DoActionGFX(cha[i].getId(), ActionCodes.ACTION_Damage), _user);
						i++;
					}
					_user.broadcastPacket(new S_RangeSkill(_user, cha, _gfxid, _actid, S_RangeSkill.TYPE_DIR));
				}
			}
			else if (_skill.getTarget().equals("none") && (_skill.getType() == L1Skills.TYPE_ATTACK)) { // 无方向范围攻击魔法
				L1Character[] cha = new L1Character[_targetList.size()];
				int i = 0;
				for (TargetStatus ts : _targetList) {
					cha[i] = ts.getTarget();
					i++;
				}
				_user.broadcastPacket(new S_RangeSkill(_user, cha, _gfxid, _actid, S_RangeSkill.TYPE_NODIR));
			}
			else { // 补助魔法
					// テレポート、マステレ、テレポートトゥマザー以外
				if ((_skillId != 5) && (_skillId != 69) && (_skillId != 131)) {
					// 魔法を使う动作のエフェクトは使用者だけ
					S_DoActionGFX gfx = new S_DoActionGFX(_user.getId(), _actid);
					_user.broadcastPacket(gfx);
					_user.broadcastPacket(new S_SkillSound(targetid, _gfxid));
				}
			}
		}
	}

	/** 删除重复的魔法状态 */
	private void deleteRepeatedSkills(L1Character cha) {
		final int[][] repeatedSkills =
		{

				// 火焰武器、风之神射、烈炎气息、暴风之眼、烈炎武器、暴风神射、妈祖的祝福
				{ FIRE_WEAPON, WIND_SHOT, FIRE_BLESS, STORM_EYE, BURNING_WEAPON, STORM_SHOT, EFFECT_BLESS_OF_MAZU },
				// 防护罩、影之防护、大地防护、大地的祝福、钢铁防护
				{ SHIELD, SHADOW_ARMOR, EARTH_SKIN, EARTH_BLESS, IRON_SKIN },
				// 勇敢药水、精灵饼干、(神圣疾走、行走加速、风之疾走)、超级加速、血之渴望
				{ STATUS_BRAVE, STATUS_ELFBRAVE, HOLY_WALK, MOVING_ACCELERATION, WIND_WALK, STATUS_BRAVE2, BLOODLUST },
				// 加速术、强力加速术、自我加速药水
				{ HASTE, GREATER_HASTE, STATUS_HASTE },
				// 缓速、集体缓术、地面障碍
				{ SLOW , MASS_SLOW , ENTANGLE },
				// 通畅气脉术、敏捷提升
				{ PHYSICAL_ENCHANT_DEX, DRESS_DEXTERITY },
				// 体魄强健术、力量提升
				{ PHYSICAL_ENCHANT_STR, DRESS_MIGHTY },
				// 激励士气、钢铁士气
				{ GLOWING_AURA, SHINING_AURA },
				// 镜像、暗影闪避
				{ MIRROR_IMAGE, UNCANNY_DODGE } };
				

		for (int[] skills : repeatedSkills) {
			for (int id : skills) {
				if (id == _skillId) {
					stopSkillList(cha, skills);
				}
			}
		}
	}

	// 重复しているスキルを一旦すべて削除
	private void stopSkillList(L1Character cha, int[] repeat_skill) {
		for (int skillId : repeat_skill) {
			if (skillId != _skillId) {
				cha.removeSkillEffect(skillId);
			}
		}
	}

	// ディレイの设定
	private void setDelay() {
		if (_skill.getReuseDelay() > 0) {
			L1SkillDelay.onSkillUse(_user, _skill.getReuseDelay());
		}
	}

	private void runSkill() {

		switch(_skillId) {
			case LIFE_STREAM:
				L1EffectSpawn.getInstance().spawnEffect(81169, _skill.getBuffDuration() * 1000, _targetX, _targetY, _user.getMapId());
				return;
			case CUBE_IGNITION:
				L1EffectSpawn.getInstance().spawnEffect(80149, _skill.getBuffDuration() * 1000, _targetX, _targetY, _user.getMapId(),
					(L1PcInstance) _user, _skillId);
				return;
			case CUBE_QUAKE:
				L1EffectSpawn.getInstance().spawnEffect(80150, _skill.getBuffDuration() * 1000, _targetX, _targetY, _user.getMapId(),
						(L1PcInstance) _user, _skillId);
				return;
			case CUBE_SHOCK:
				L1EffectSpawn.getInstance().spawnEffect(80151, _skill.getBuffDuration() * 1000, _targetX, _targetY, _user.getMapId(),
						(L1PcInstance) _user, _skillId);
				return;
			case CUBE_BALANCE:
				L1EffectSpawn.getInstance().spawnEffect(80152, _skill.getBuffDuration() * 1000, _targetX, _targetY, _user.getMapId(),
						(L1PcInstance) _user, _skillId);
				return;
			case FIRE_WALL: // 火牢
				L1EffectSpawn.getInstance().doSpawnFireWall(_user, _targetX, _targetY);
				return;
				/**巴拉卡斯火牢*/
			case BLKS_FIRE_WALL:
				_user.setSkillEffect(_skillId, 11 * 1000);
				L1EffectSpawn.getInstance().doSpawnFireWallforNpc(_user, _target);
				return;
			case TRUE_TARGET: // 精准目标
				if (_user instanceof L1PcInstance) {
					L1PcInstance pri = (L1PcInstance) _user;
					L1EffectInstance effect = L1EffectSpawn.getInstance().spawnEffect(80153, 5 * 1000, _targetX + 2, _targetY - 1, _user.getMapId());
					if (_targetID != 0) {
						pri.sendPackets(new S_TrueTarget(_targetID, pri.getId(), _message));
						if (pri.getClanid() != 0) {
							L1PcInstance players[] = L1World.getInstance().getClan(pri.getClanname()).getOnlineClanMember();
							for (L1PcInstance pc : players) {
								pc.sendPackets(new S_TrueTarget(_targetID, pc.getId(), _message));
							}
						}
					} else if (effect != null) {
						pri.sendPackets(new S_TrueTarget(effect.getId(), pri.getId(), _message));
						if (pri.getClanid() != 0) {
							L1PcInstance players[] = L1World.getInstance().getClan(pri.getClanname()).getOnlineClanMember();
							for (L1PcInstance pc : players) {
								pc.sendPackets(new S_TrueTarget(effect.getId(), pc.getId(), _message));
							}
						}
					}
				}
				return;
			default:
				break;
		}

		// 魔法屏障不可抵挡的魔法
		for (int skillId : EXCEPT_COUNTER_MAGIC) {
			if (_skillId == skillId) {
				_isCounterMagic = false;
				break;
			}
		}

		// NPCにショックスタンを使用させるとonActionでNullPointerExceptionが発生するため
		// とりあえずPCが使用した时のみ
		if ((_skillId == SHOCK_STUN) && (_user instanceof L1PcInstance)) {
			_target.onAction(_player);
		}

		if (!isTargetCalc(_target)) {
			return;
		}

		try {
			TargetStatus ts = null;
			L1Character cha = null;
			int dmg = 0;
			int drainMana = 0;
			int heal = 0;
			boolean isSuccess = false;
			int undeadType = 0;

			for (Iterator<TargetStatus> iter = _targetList.iterator(); iter.hasNext();) {
				ts = null;
				cha = null;
				dmg = 0;
				heal = 0;
				isSuccess = false;
				undeadType = 0;

				ts = iter.next();
				cha = ts.getTarget();

				if (!ts.isCalc() || !isTargetCalc(cha)) {
					continue; // 计算する必要がない。
				}

				L1Magic _magic = new L1Magic(_user, cha);
				_magic.setLeverage(getLeverage());

				if (cha instanceof L1MonsterInstance) { // 不死系判断
					undeadType = ((L1MonsterInstance) cha).getNpcTemplate().get_undead();
				}

				// 确率系スキルで失败が确定している场合
				if (((_skill.getType() == L1Skills.TYPE_CURSE) || (_skill.getType() == L1Skills.TYPE_PROBABILITY)) && isTargetFailure(cha)) {
					iter.remove();
					continue;
				}

				if (cha instanceof L1PcInstance) { // ターゲットがPCの场合のみアイコンは送信する。
					if (_skillTime == 0) {
						_getBuffIconDuration = _skill.getBuffDuration(); // 效果时间
					}
					else {
						_getBuffIconDuration = _skillTime; // パラメータのtimeが0以外なら、效果时间として设定する
					}
				}

				deleteRepeatedSkills(cha); // 删除无法共同存在的魔法状态

				if ((_skill.getType() == L1Skills.TYPE_ATTACK) && (_user.getId() != cha.getId())) { // 攻击系スキル＆ターゲットが使用者以外であること。
					if (isUseCounterMagic(cha)) { // カウンターマジックが発动した场合、リストから削除
						iter.remove();
						continue;
					}
					dmg = _magic.calcMagicDamage(_skillId);
					_dmg = dmg;
					cha.removeSkillEffect(ERASE_MAGIC); // イレースマジック中なら、攻击魔法で解除
				}
				else if ((_skill.getType() == L1Skills.TYPE_CURSE) || (_skill.getType() == L1Skills.TYPE_PROBABILITY)) { // 确率系スキル
					isSuccess = _magic.calcProbabilityMagic(_skillId);
					if (_skillId != ERASE_MAGIC) {
						cha.removeSkillEffect(ERASE_MAGIC); // イレースマジック中なら、确率魔法で解除
					}
					if (_skillId != FOG_OF_SLEEPING) {
						cha.removeSkillEffect(FOG_OF_SLEEPING); // フォグオブスリーピング中なら、确率魔法で解除
					}
					if (isSuccess) { // 成功したがカウンターマジックが発动した场合、リストから削除
						if (isUseCounterMagic(cha)) { // カウンターマジックが発动したか
							iter.remove();
							continue;
						}
					}
					else { // 失败した场合、リストから削除
						if ((_skillId == FOG_OF_SLEEPING) && (cha instanceof L1PcInstance)) {
							L1PcInstance pc = (L1PcInstance) cha;
							pc.sendPackets(new S_ServerMessage(297)); // 你感觉些微地晕眩。
						}
						iter.remove();
						continue;
					}
				}
				// 治愈性魔法
				else if (_skill.getType() == L1Skills.TYPE_HEAL) {
					// 回复量
					dmg = -1 * _magic.calcHealing(_skillId);
					if (cha.hasSkillEffect(WATER_LIFE)) { // 水之元气-效果 2倍
						dmg *= 2;
						cha.killSkillEffectTimer(WATER_LIFE); // 效果只有一次
						if (cha instanceof L1PcInstance) {
							L1PcInstance pc = (L1PcInstance) cha;
							pc.sendPackets(new S_SkillIconWaterLife());
						}
					}
					if (cha.hasSkillEffect(POLLUTE_WATER)) { // 污浊之水-效果减半
						dmg /= 2;
					}
				}

				// ★★★ 圣洁之光 ★★★
				else if (_skillId == REMOVE_CURSE) {
					//防止玩家使用外挂在木乃尹,冰茅,冲晕,沉雾状态底下的施法补血跟圣光 by thettt999
					if (cha.hasSkillEffect(STATUS_CURSE_PARALYZED)		//木乃尹状态
						||cha.hasSkillEffect(SHOCK_STUN)				//冲晕
						||cha.hasSkillEffect(FOG_OF_SLEEPING)			//沉睡之雾
						||cha.hasSkillEffect(ICE_LANCE)){				//冰茅
						_player.sendPackets(new S_ServerMessage(285));//285 : \f1在此状态下无法使用魔法。
						return;
					}
					//~防止玩家使用外挂在木乃尹,冰茅,冲晕,沉雾状态底下的施法补血跟圣光 by thettt999
					cha.curePoison();
					if (cha.hasSkillEffect(STATUS_CURSE_PARALYZING)
							|| cha.hasSkillEffect(STATUS_CURSE_PARALYZED)) {
						cha.cureParalaysis();
					}
				}

				// ★★★ 回复系魔法 ★★★
				else if ((_skillId == HEAL || _skillId == EXTRA_HEAL
						|| _skillId == GREATER_HEAL || _skillId == FULL_HEAL
						|| _skillId == HEAL_ALL || _skillId == NATURES_TOUCH
						|| _skillId == NATURES_BLESSING)
						&& (_user instanceof L1PcInstance)) {
						// 防止玩家使用外挂在木乃尹,冰茅,冲晕,沉雾状态底下的施法补血跟圣光 by thettt999
					if (cha.hasSkillEffect(STATUS_CURSE_PARALYZED)		// 木乃尹状态
							||cha.hasSkillEffect(SHOCK_STUN)			// 冲晕
							||cha.hasSkillEffect(FOG_OF_SLEEPING)		// 沉睡之雾
							||cha.hasSkillEffect(ICE_LANCE)){			// 冰茅

						_player.sendPackets(new S_ServerMessage(285));//285 : \f1在此状态下无法使用魔法。
						return;
					}
					else if (_user instanceof L1PcInstance) {
						cha.removeSkillEffect(WATER_LIFE);
					}
						//cha.removeSkillEffect(WATER_LIFE);
						//~防止玩家使用外挂在木乃尹,冰茅,冲晕,沉雾状态底下的施法补血跟圣光 by thettt999
				}

				// 显示团体魔法效果在队友或盟友
				else if ((_skillId == FIRE_BLESS || _skillId == STORM_EYE // 烈炎气息、暴风之眼
						|| _skillId == EARTH_BLESS // 大地的祝福
						|| _skillId == GLOWING_AURA // 激励士气
						|| _skillId == SHINING_AURA || _skillId == BRAVE_AURA) // 钢铁士气、冲击士气 
							&& _user.getId() != cha.getId()) {
					if (cha instanceof L1PcInstance) {
						L1PcInstance _targetPc = (L1PcInstance) cha;
						_targetPc.sendPackets(new S_SkillSound(_targetPc.getId(), _skill.getCastGfx()));
						_targetPc.broadcastPacket(new S_SkillSound(_targetPc.getId(), _skill.getCastGfx()));
					}
				}

				// ■■■■ 个别处理のあるスキルのみ书いてください。 ■■■■

				// 除了冲晕、骷髅毁坏之外魔法效果存在时，只更新效果时间跟图示。
				if (cha.hasSkillEffect(_skillId) && (_skillId != SHOCK_STUN && _skillId != BONE_BREAK && _skillId != CONFUSION && _skillId != THUNDER_GRAB)) {
					addMagicList(cha, true); // 魔法效果已存在时
					if (_skillId != SHAPE_CHANGE) { // 除了变形术之外
						continue;
					}
				}

				switch(_skillId) {
					// 加速术
					case HASTE:
						if (cha.getMoveSpeed() != 2) { // スロー中以外
							if (cha instanceof L1PcInstance) {
								L1PcInstance pc = (L1PcInstance) cha;
								if (pc.getHasteItemEquipped() > 0) {
									continue;
								}
								pc.setDrink(false);
								pc.sendPackets(new S_SkillHaste(pc.getId(), 1, _getBuffIconDuration));
							}
							cha.broadcastPacket(new S_SkillHaste(cha.getId(), 1, 0));
							cha.setMoveSpeed(1);
						}
						else { // スロー中
							int skillNum = 0;
							if (cha.hasSkillEffect(SLOW)) {
								skillNum = SLOW;
							}
							else if (cha.hasSkillEffect(MASS_SLOW)) {
								skillNum = MASS_SLOW;
							}
							else if (cha.hasSkillEffect(ENTANGLE)) {
								skillNum = ENTANGLE;
							}
							if (skillNum != 0) {
								cha.removeSkillEffect(skillNum);
								cha.removeSkillEffect(HASTE);
								cha.setMoveSpeed(0);
								continue;
							}
						}
						break;
					// 强力加速术
					case GREATER_HASTE:
						if (cha instanceof L1PcInstance) {
							L1PcInstance pc = (L1PcInstance) cha;
							if (pc.getHasteItemEquipped() > 0) {
								continue;
							}
							if (pc.getMoveSpeed() != 2) { // スロー中以外
								pc.setDrink(false);
								pc.setMoveSpeed(1);
								pc.sendPackets(new S_SkillHaste(pc.getId(), 1, _getBuffIconDuration));
								pc.broadcastPacket(new S_SkillHaste(pc.getId(), 1, 0));
							}
							else { // スロー中
								int skillNum = 0;
								if (pc.hasSkillEffect(SLOW)) {
									skillNum = SLOW;
								}
								else if (pc.hasSkillEffect(MASS_SLOW)) {
									skillNum = MASS_SLOW;
								}
								else if (pc.hasSkillEffect(ENTANGLE)) {
									skillNum = ENTANGLE;
								}
								if (skillNum != 0) {
									pc.removeSkillEffect(skillNum);
									pc.removeSkillEffect(GREATER_HASTE);
									pc.setMoveSpeed(0);
									continue;
								}
							}
						}
						break;
					// 缓速术、集体缓速术、地面障碍
					case SLOW:
					case MASS_SLOW:
					case ENTANGLE:
						if (cha instanceof L1PcInstance) {
							L1PcInstance pc = (L1PcInstance) cha;
							if (pc.getHasteItemEquipped() > 0) {
								continue;
							}
						}
						if (cha.getMoveSpeed() == 0) {
							if (cha instanceof L1PcInstance) {
								L1PcInstance pc = (L1PcInstance) cha;
								pc.sendPackets(new S_SkillHaste(pc.getId(), 2, _getBuffIconDuration));
							}
							cha.broadcastPacket(new S_SkillHaste(cha.getId(), 2, _getBuffIconDuration));
							cha.setMoveSpeed(2);
						}
						else if (cha.getMoveSpeed() == 1) {
							int skillNum = 0;
							if (cha.hasSkillEffect(HASTE)) {
								skillNum = HASTE;
							}
							else if (cha.hasSkillEffect(GREATER_HASTE)) {
								skillNum = GREATER_HASTE;
							}
							else if (cha.hasSkillEffect(STATUS_HASTE)) {
								skillNum = STATUS_HASTE;
							}
							if (skillNum != 0) {
								cha.removeSkillEffect(skillNum);
								cha.removeSkillEffect(_skillId);
								cha.setMoveSpeed(0);
								continue;
							}
						}
						break;
					// 寒冷战栗、吸血鬼之吻
					case CHILL_TOUCH:
					case VAMPIRIC_TOUCH:
						heal = dmg;
						break;
					// 亚力安冰矛围篱
					case ICE_LANCE_COCKATRICE:
					// 邪恶蜥蜴冰矛围篱
					case ICE_LANCE_BASILISK:
					// 冰毛围篱、冰雪飓风、寒冰喷吐
					case ICE_LANCE:
					case FREEZING_BLIZZARD:
					case FREEZING_BREATH:
						_isFreeze = _magic.calcProbabilityMagic(_skillId);
						if (_isFreeze) {
							int time = _skill.getBuffDuration() * 1000;
							L1EffectSpawn.getInstance().spawnEffect(81168, time, cha.getX(), cha.getY(), cha.getMapId());
							if (cha instanceof L1PcInstance) {
								L1PcInstance pc = (L1PcInstance) cha;
								pc.sendPackets(new S_Poison(pc.getId(), 2));
								pc.broadcastPacket(new S_Poison(pc.getId(), 2));
								pc.sendPackets(new S_Paralysis(S_Paralysis.TYPE_FREEZE, true));
							}
							else if ((cha instanceof L1MonsterInstance) || (cha instanceof L1SummonInstance) || (cha instanceof L1PetInstance)) {
								L1NpcInstance npc = (L1NpcInstance) cha;
								npc.broadcastPacket(new S_Poison(npc.getId(), 2));
								npc.setParalyzed(true);
								npc.setParalysisTime(time);
							}
						}
						break;
					// 大地屏障
					case EARTH_BIND:
						if (cha instanceof L1PcInstance) {
							L1PcInstance pc = (L1PcInstance) cha;
							pc.sendPackets(new S_Poison(pc.getId(), 2));
							pc.broadcastPacket(new S_Poison(pc.getId(), 2));
							pc.sendPackets(new S_Paralysis(S_Paralysis.TYPE_FREEZE, true));
						}
						else if ((cha instanceof L1MonsterInstance) || (cha instanceof L1SummonInstance) || (cha instanceof L1PetInstance)) {
							L1NpcInstance npc = (L1NpcInstance) cha;
							npc.broadcastPacket(new S_Poison(npc.getId(), 2));
							npc.setParalyzed(true);
							npc.setParalysisTime(_skill.getBuffDuration() * 1000);
						}
						break;
					case 20011: // 毒雾-前方 3X3
						_user.setHeading(_user.targetDirection(_targetX, _targetY)); // 改变面向
						int locX = 0;
						int locY = 0;
						for (int i = 0; i < 3; i++) {
							for (int j = 0; j < 3; j++) {
								switch (_user.getHeading()) {
									case 0:
										locX = (-1 + j);
										locY = -1 * (-3 + i);
										break;
									case 1:
										locX = -1 * (2 + j - i);
										locY = -1 * (-4 + j + i);
										break;
									case 2:
										locX = -1 * (3 - i);
										locY = (-1 + j);
										break;
									case 3:
										locX = -1 * (4 - j - i);
										locY = -1 * (2 + j - i);
										break;
									case 4:
										locX = (1 - j);
										locY = -1 * (3 - i);
										break;
									case 5:
										locX = -1 * (-2 - j + i);
										locY = -1 * (4 - j - i);
										break;
									case 6:
										locX = -1 * (-3 + i);
										locY = (1 - j);
										break;
									case 7:
										locX = -1 * (-4 + j + i);
										locY = -1 * (-2 - j + i);
										break;
								}
								L1EffectSpawn.getInstance().spawnEffect(93002, 10000, _user.getX() - locX, _user.getY() - locY, _user.getMapId());
							}
						}
						break;
					// 冲击之晕
					case SHOCK_STUN:
						//int stun = Random.nextInt(10) + 5;
						//drainMana = stun + (_user.getInt() / 2);
						int[] stunTimeArray =
						{ 500, 1000, 1500, 2000, 2500, 3000 };
						int rnd = Random.nextInt(stunTimeArray.length);
						_shockStunDuration = stunTimeArray[rnd];
						if ((cha instanceof L1PcInstance) && cha.hasSkillEffect(SHOCK_STUN)) {
							_shockStunDuration += cha.getSkillEffectTimeSec(SHOCK_STUN) * 1000;
						}

						L1EffectSpawn.getInstance().spawnEffect(81162, _shockStunDuration, cha.getX(), cha.getY(), cha.getMapId());
						if (cha instanceof L1PcInstance) {
							L1PcInstance pc = (L1PcInstance) cha;
							pc.sendPackets(new S_Paralysis(S_Paralysis.TYPE_STUN, true));
						}
						else if ((cha instanceof L1MonsterInstance) || (cha instanceof L1SummonInstance) || (cha instanceof L1PetInstance)) {
							L1NpcInstance npc = (L1NpcInstance) cha;
							npc.setParalyzed(true);
							npc.setParalysisTime(_shockStunDuration);
						}
						break;
					// 夺命之雷
					case THUNDER_GRAB:
						isSuccess = _magic.calcProbabilityMagic(_skillId);
						if (isSuccess) {
							if (!cha.hasSkillEffect(THUNDER_GRAB_START) && !cha.hasSkillEffect(STATUS_FREEZE) ) {
								if (cha instanceof L1PcInstance) {
									L1PcInstance pc = (L1PcInstance) cha;
									pc.sendPackets(new S_Paralysis(S_Paralysis.TYPE_BIND, true));
									pc.sendPackets(new S_SkillSound(pc.getId(), 4184));
									pc.broadcastPacket(new S_SkillSound(pc.getId(), 4184));
								} else if (cha instanceof L1NpcInstance) {
									L1NpcInstance npc = (L1NpcInstance) cha;
									npc.setParalyzed(true);
									npc.broadcastPacket(new S_SkillSound(npc.getId(), 4184));
								}
								cha.setSkillEffect(THUNDER_GRAB_START, 500);
							}
						}
						break;
					// TODO 安息攻击
					case 12131:	// 火龙
					case 12123:	// 地龙
					case 12128:	// 水龙
					case 12120:	// 风龙
						if (_user instanceof L1NpcInstance) {
							_user.broadcastPacket(new l1j.server.server.serverpackets.S_NpcChatPacket(_npc,
									(cha.getName())+("! ")+("$3717"), (byte) 2)); // 龙的安息字串
						}
						else {
							_player.broadcastPacket(new l1j.server.server.serverpackets.S_ChatPacket(_player,
									(cha.getName())+("! ")+("$3717"), 0, (byte) 0)); // 龙的安息字串
						}
						break;
					// 起死回生术
					case TURN_UNDEAD:
						if (undeadType == 1 || undeadType == 3){
							dmg = cha.getCurrentHp();
						}
						break;
					// 魔力夺取
					case MANA_DRAIN:
						int chance = Random.nextInt(10) + 5;
						drainMana = chance + (_user.getInt() / 2);
						if (cha.getCurrentMp() < drainMana) {
							drainMana = cha.getCurrentMp();
						}
						break;
					// 指定传送、集体传送术
					case TELEPORT:
					case MASS_TELEPORT:
						if (cha instanceof L1PcInstance) {
							L1PcInstance pc = (L1PcInstance) cha;
							L1BookMark bookm = pc.getBookMark(_bookmarkId);
							if (bookm != null) { // ブックマークを取得出来たらテレポート
								if (pc.getMap().isEscapable() || pc.isGm()) {
									int newX = bookm.getLocX();
									int newY = bookm.getLocY();
									short mapId = bookm.getMapId();

									if (_skillId == MASS_TELEPORT) { // マステレポート
										List<L1PcInstance> clanMember = L1World.getInstance().getVisiblePlayer(pc);
										for (L1PcInstance member : clanMember) {
											if ((pc.getLocation().getTileLineDistance(member.getLocation()) <= 3)
													&& (member.getClanid() == pc.getClanid()) && (pc.getClanid() != 0) && (member.getId() != pc.getId())) {
												L1Teleport.teleport(member, newX, newY, mapId, 5, true);
											}
										}
									}
									L1Teleport.teleport(pc, newX, newY, mapId, 5, true);
								}
								else {
									pc.sendPackets(new S_ServerMessage(79));
									pc.sendPackets(new S_Paralysis(S_Paralysis.TYPE_TELEPORT_UNLOCK, true));
								}
							}
							else { // ブックマークが取得出来なかった、あるいは“任意の场所”を选択した场合の处理
								if (pc.getMap().isTeleportable() || pc.isGm()) {
									L1Location newLocation = pc.getLocation().randomLocation(200, true);
									int newX = newLocation.getX();
									int newY = newLocation.getY();
									short mapId = (short) newLocation.getMapId();

									if (_skillId == MASS_TELEPORT) {
										List<L1PcInstance> clanMember = L1World.getInstance().getVisiblePlayer(pc);
										for (L1PcInstance member : clanMember) {
											if ((pc.getLocation().getTileLineDistance(member.getLocation()) <= 3)
													&& (member.getClanid() == pc.getClanid()) && (pc.getClanid() != 0) && (member.getId() != pc.getId())) {
												L1Teleport.teleport(member, newX, newY, mapId, 5, true);
											}
										}
									}
									L1Teleport.teleport(pc, newX, newY, mapId, 5, true);
								}
								else {
									pc.sendPackets(new S_ServerMessage(276)); // \f1在此无法使用传送。
									pc.sendPackets(new S_Paralysis(S_Paralysis.TYPE_TELEPORT_UNLOCK, true));
								}
							}
						}
						break;
					// 呼唤盟友
					case CALL_CLAN:
						if (cha instanceof L1PcInstance) {
							L1PcInstance pc = (L1PcInstance) cha;
							L1PcInstance clanPc = (L1PcInstance) L1World.getInstance().findObject(_targetID);
							if (clanPc != null) {
								clanPc.setTempID(pc.getId());
								clanPc.sendPackets(new S_Message_YN(729, "")); // 盟主正在呼唤你，你要接受他的呼唤吗？(Y/N)
							}
						}
						break;
					// 援护盟友
					case RUN_CLAN:
						if (cha instanceof L1PcInstance) {
							L1PcInstance pc = (L1PcInstance) cha;
							L1PcInstance clanPc = (L1PcInstance) L1World.getInstance().findObject(_targetID);
							if (clanPc != null) {
								if (pc.getMap().isEscapable() || pc.isGm()) {
									boolean castle_area = L1CastleLocation.checkInAllWarArea(
												clanPc.getX(), clanPc.getY(), clanPc.getMapId());
									if (((clanPc.getMapId() == 0) || (clanPc.getMapId() == 4) || (clanPc.getMapId() == 304)) && (castle_area == false)) {
										L1Teleport.teleport(pc, clanPc.getX(), clanPc.getY(), clanPc.getMapId(), 5, true);
									}
									else {
										pc.sendPackets(new S_ServerMessage(79));
									}
								}
								else {
									// 这附近的能量影响到瞬间移动。在此地无法使用瞬间移动。
									pc.sendPackets(new S_ServerMessage(647));
									pc.sendPackets(new S_Paralysis(S_Paralysis.TYPE_TELEPORT_UNLOCK, true));
								}
							}
						}
						break;
					// 强力无所遁形
					case COUNTER_DETECTION:
						if (cha instanceof L1PcInstance) {
							dmg = _magic.calcMagicDamage(_skillId);
						}
						else if (cha instanceof L1NpcInstance) {
							L1NpcInstance npc = (L1NpcInstance) cha;
							int hiddenStatus = npc.getHiddenStatus();
							if (hiddenStatus == L1NpcInstance.HIDDEN_STATUS_SINK) {
								npc.appearOnGround(_player);
							} else {
								dmg = 0;
							}
						} else {
							dmg = 0;
						}
						break;
					// 创造魔法武器
					case CREATE_MAGICAL_WEAPON:
						if (cha instanceof L1PcInstance) {
							L1PcInstance pc = (L1PcInstance) cha;
							L1ItemInstance item = pc.getInventory().getItem(_itemobjid);
							if ((item != null) && (item.getItem().getType2() == 1)) {
								int item_type = item.getItem().getType2();
								int safe_enchant = item.getItem().get_safeenchant();
								int enchant_level = item.getEnchantLevel();
								String item_name = item.getName();
								if (safe_enchant < 0) { // 强化不可
									pc.sendPackets( // \f1何も起きませんでした。
									new S_ServerMessage(79));
								}
								else if (safe_enchant == 0) { // 安全圏+0
									pc.sendPackets( // \f1何も起きませんでした。
									new S_ServerMessage(79));
								}
								else if ((item_type == 1) && (enchant_level == 0)) {
									if (!item.isIdentified()) {// 未鉴定
										pc.sendPackets( // \f1%0が%2%1光ります。
										new S_ServerMessage(161, item_name, "$245", "$247"));
									}
									else {
										item_name = "+0 " + item_name;
										pc.sendPackets( // \f1%0が%2%1光ります。
										new S_ServerMessage(161, "+0 " + item_name, "$245", "$247"));
									}
									item.setEnchantLevel(1);
									pc.getInventory().updateItem(item, L1PcInventory.COL_ENCHANTLVL);
								}
								else {
									pc.sendPackets( // \f1何も起きませんでした。
									new S_ServerMessage(79));
								}
							}
							else {
								pc.sendPackets( // \f1何も起きませんでした。
								new S_ServerMessage(79));
							}
						}
						break;
					// 提炼魔石
					case BRING_STONE:
						if (cha instanceof L1PcInstance) {
							L1PcInstance pc = (L1PcInstance) cha;

							L1ItemInstance item = pc.getInventory().getItem(_itemobjid);
							if (item != null) {
								int dark = (int) (10 + (pc.getLevel() * 0.8) + (pc.getWis() - 6) * 1.2);
								int brave = (int) (dark / 2.1);
								int wise = (int) (brave / 2.0);
								int kayser = (int) (wise / 1.9);
								int run = Random.nextInt(100) + 1;
								if (item.getItem().getItemId() == 40320) {
									pc.getInventory().removeItem(item, 1);
									if (dark >= run) {
										pc.getInventory().storeItem(40321, 1);
										pc.sendPackets(new S_ServerMessage(403, "$2475")); // 获得%0%o 。
									} else {
										pc.sendPackets(new S_ServerMessage(280)); // \f1施咒失败。
									}
								} else if (item.getItem().getItemId() == 40321) {
									pc.getInventory().removeItem(item, 1);
									if (brave >= run) {
										pc.getInventory().storeItem(40322, 1);
										pc.sendPackets(new S_ServerMessage(403, "$2476")); // 获得%0%o 。
									} else {
										pc.sendPackets(new S_ServerMessage(280));// \f1施咒失败。
									}
								} else if (item.getItem().getItemId() == 40322) {
									pc.getInventory().removeItem(item, 1);
									if (wise >= run) {
										pc.getInventory().storeItem(40323, 1);
										pc.sendPackets(new S_ServerMessage(403, "$2477")); // 获得%0%o 。
									} else {
										pc.sendPackets(new S_ServerMessage(280));// \f1施咒失败。
									}
								} else if (item.getItem().getItemId() == 40323) {
									pc.getInventory().removeItem(item, 1);
									if (kayser >= run) {
										pc.getInventory().storeItem(40324, 1);
										pc.sendPackets(new S_ServerMessage(403, "$2478")); // 获得%0%o 。
									} else {
										pc.sendPackets(new S_ServerMessage(280));// \f1施咒失败。
									}
								}
							}
						}
						break;
					// 日光术
					case LIGHT:
						if (cha instanceof L1PcInstance) {
						}
						break;
					// 暗影之牙
					case SHADOW_FANG:
						if (cha instanceof L1PcInstance) {
							L1PcInstance pc = (L1PcInstance) cha;
							L1ItemInstance item = pc.getInventory().getItem(_itemobjid);
							if ((item != null) && (item.getItem().getType2() == 1)) {
								item.setSkillWeaponEnchant(pc, _skillId, _skill.getBuffDuration() * 1000);
							}
							else {
								pc.sendPackets(new S_ServerMessage(79));
							}
						}
						break;
					// 拟似魔法武器
					case ENCHANT_WEAPON:
						if (cha instanceof L1PcInstance) {
							L1PcInstance pc = (L1PcInstance) cha;
							L1ItemInstance item = pc.getInventory().getItem(_itemobjid);
							if ((item != null) && (item.getItem().getType2() == 1)) {
								pc.sendPackets(new S_ServerMessage(161, item.getLogName(), "$245", "$247"));
								item.setSkillWeaponEnchant(pc, _skillId, _skill.getBuffDuration() * 1000);
							}
							else {
								pc.sendPackets(new S_ServerMessage(79));
							}
						}
						break;
					// 神圣武器、祝福魔法武器
					case HOLY_WEAPON:
					case BLESS_WEAPON:
						if (cha instanceof L1PcInstance) {
							if (!(cha instanceof L1PcInstance)) {
								return;
							}
							L1PcInstance pc = (L1PcInstance) cha;
							if (pc.getWeapon() == null) {
								pc.sendPackets(new S_ServerMessage(79));
								return;
							}
							for (L1ItemInstance item : pc.getInventory().getItems()) {
								if (pc.getWeapon().equals(item)) {
									pc.sendPackets(new S_ServerMessage(161, item.getLogName(), "$245", "$247"));
									item.setSkillWeaponEnchant(pc, _skillId, _skill.getBuffDuration() * 1000);
									return;
								}
							}
						}
						break;
					// 铠甲护持
					case BLESSED_ARMOR:
						if (cha instanceof L1PcInstance) {
							L1PcInstance pc = (L1PcInstance) cha;
							L1ItemInstance item = pc.getInventory().getItem(_itemobjid);
							if ((item != null) && (item.getItem().getType2() == 2) && (item.getItem().getType() == 2)) {
								pc.sendPackets(new S_ServerMessage(161, item.getLogName(), "$245", "$247"));
								item.setSkillArmorEnchant(pc, _skillId, _skill.getBuffDuration() * 1000);
							}
							else {
								pc.sendPackets(new S_ServerMessage(79));
							}
						}
						break;
					default:
						L1BuffUtil.skillEffect(_user, cha, _target, _skillId, _getBuffIconDuration, dmg);
						break;
				}

				// ■■■■ 个别处理ここまで ■■■■

				 // 治愈性魔法攻击不死系的怪物。
				if ((_skill.getType() == L1Skills.TYPE_HEAL) && (_calcType == PC_NPC) && (undeadType == 1)) {
					dmg *= -1;
				}
				// 治愈性魔法无法对此不死系起作用
				if ((_skill.getType() == L1Skills.TYPE_HEAL) && (_calcType == PC_NPC) && (undeadType == 3)) {
					dmg = 0;
				}
				// 无法对城门、守护塔补血
				if (((cha instanceof L1TowerInstance) || (cha instanceof L1DoorInstance)) && (dmg < 0)) {
					dmg = 0;
				}
				 // 吸取魔力。
				if ((dmg > 0) || (drainMana != 0)) {
					_magic.commit(dmg, drainMana);
				}
				// 补血判断
				if ((_skill.getType() == L1Skills.TYPE_HEAL) && (dmg < 0)) {
					cha.setCurrentHp((dmg * -1) + cha.getCurrentHp());
				}
				// 非治愈性魔法补血判断(寒战、吸吻等)
				if (heal > 0) {
					_user.setCurrentHp(heal + _user.getCurrentHp());
				}

				if (cha instanceof L1PcInstance) { // 更新自身状态
					L1PcInstance pc = (L1PcInstance) cha;
					pc.turnOnOffLight();
					pc.sendPackets(new S_OwnCharAttrDef(pc));
					pc.sendPackets(new S_OwnCharStatus(pc));
					sendHappenMessage(pc); // ターゲットにメッセージを送信
				}

				addMagicList(cha, false); // ターゲットに魔法の效果时间を设定

				if (cha instanceof L1PcInstance) { // ターゲットがPCならば、ライト状态を更新
					L1PcInstance pc = (L1PcInstance) cha;
					pc.turnOnOffLight();
				}
			}

			// 解除隐身
			if ((_skillId == DETECTION) || (_skillId == COUNTER_DETECTION)) { // 无所遁形、强力无所遁形
				detection(_player);
			}

		}
		catch (Exception e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
	}

	private void detection(L1PcInstance pc) {
		if (!pc.isGmInvis() && pc.isInvisble()) { // 自己隐身中
			pc.delInvis();
			pc.beginInvisTimer();
		}

		for (L1PcInstance tgt : L1World.getInstance().getVisiblePlayer(pc)) { // 画面内其他隐身者
			if (!tgt.isGmInvis() && tgt.isInvisble()) {
				tgt.delInvis();
			}
		}
		L1WorldTraps.getInstance().onDetection(pc);
	}

	// ターゲットについて计算する必要があるか返す
	private boolean isTargetCalc(L1Character cha) {
		// 三重矢、屠宰者、暴击、骷髅毁坏
		if ((_user instanceof L1PcInstance)
				&& (_skillId == TRIPLE_ARROW || _skillId == FOE_SLAYER
						|| _skillId == SMASH || _skillId == BONE_BREAK)) {
			return true;
		}
		// 攻击魔法のNon－PvP判定
		if (_skill.getTarget().equals("attack") && (_skillId != 18)) { // 攻击魔法
			if (isPcSummonPet(cha)) { // 对象がPC、サモン、ペット
				if ((_player.getZoneType() == 1) || (cha.getZoneType() == 1 // 攻击する侧または攻击される侧がセーフティーゾーン
						) || _player.checkNonPvP(_player, cha)) { // Non-PvP设定
					return false;
				}
			}
		}

		// フォグオブスリーピングは自分自身は对象外
		if ((_skillId == FOG_OF_SLEEPING) && (_user.getId() == cha.getId())) {
			return false;
		}

		// マススローは自分自身と自分のペットは对象外
		if (_skillId == MASS_SLOW) {
			if (_user.getId() == cha.getId()) {
				return false;
			}
			if (cha instanceof L1SummonInstance) {
				L1SummonInstance summon = (L1SummonInstance) cha;
				if (_user.getId() == summon.getMaster().getId()) {
					return false;
				}
			}
			else if (cha instanceof L1PetInstance) {
				L1PetInstance pet = (L1PetInstance) cha;
				if (_user.getId() == pet.getMaster().getId()) {
					return false;
				}
			}
		}

		// マステレポートは自分自身のみ对象（同时にクラン员もテレポートさせる）
		if (_skillId == MASS_TELEPORT) {
			if (_user.getId() != cha.getId()) {
				return false;
			}
		}

		return true;
	}

	// 对象がPC、サモン、ペットかを返す
	private boolean isPcSummonPet(L1Character cha) {
		if (_calcType == PC_PC) { // 对象がPC
			return true;
		}

		if (_calcType == PC_NPC) {
			if (cha instanceof L1SummonInstance) { // 对象がサモン
				L1SummonInstance summon = (L1SummonInstance) cha;
				if (summon.isExsistMaster()) { // マスターが居る
					return true;
				}
			}
			if (cha instanceof L1PetInstance) { // 对象がペット
				return true;
			}
		}
		return false;
	}

	// ターゲットに对して必ず失败になるか返す
	private boolean isTargetFailure(L1Character cha) {
		boolean isTU = false;
		boolean isErase = false;
		boolean isManaDrain = false;
		int undeadType = 0;

		if ((cha instanceof L1TowerInstance) || (cha instanceof L1DoorInstance)) { // ガーディアンタワー、ドアには确率系スキル无效
			return true;
		}

		if (cha instanceof L1PcInstance) { // 对PCの场合
			if ((_calcType == PC_PC) && _player.checkNonPvP(_player, cha)) { // Non-PvP设定
				L1PcInstance pc = (L1PcInstance) cha;
				if ((_player.getId() == pc.getId()) || ((pc.getClanid() != 0) && (_player.getClanid() == pc.getClanid()))) {
					return false;
				}
				return true;
			}
			return false;
		}

		if (cha instanceof L1MonsterInstance) { // ターンアンデット可能か判定
			isTU = ((L1MonsterInstance) cha).getNpcTemplate().get_IsTU();
		}

		if (cha instanceof L1MonsterInstance) { // イレースマジック可能か判定
			isErase = ((L1MonsterInstance) cha).getNpcTemplate().get_IsErase();
		}

		if (cha instanceof L1MonsterInstance) { // アンデットの判定
			undeadType = ((L1MonsterInstance) cha).getNpcTemplate().get_undead();
		}

		// マナドレインが可能か？
		if (cha instanceof L1MonsterInstance) {
			isManaDrain = true;
		}
		/*
		 * 成功除外条件１：T-Uが成功したが、对象がアンデットではない。 成功除外条件２：T-Uが成功したが、对象にはターンアンデット无效。
		 * 成功除外条件３：スロー、マススロー、マナドレイン、エンタングル、イレースマジック、ウィンドシャックル无效
		 * 成功除外条件４：マナドレインが成功したが、モンスター以外の场合
		 */
		if (((_skillId == TURN_UNDEAD) && ((undeadType == 0) || (undeadType == 2)))
				|| ((_skillId == TURN_UNDEAD) && (isTU == false))
				|| (((_skillId == ERASE_MAGIC) || (_skillId == SLOW) || (_skillId == MANA_DRAIN) || (_skillId == MASS_SLOW) || (_skillId == ENTANGLE) || (_skillId == WIND_SHACKLE)) && (isErase == false))
				|| ((_skillId == MANA_DRAIN) && (isManaDrain == false))) {
			return true;
		}
		return false;
	}

	// 魔法屏障发动判断
	private boolean isUseCounterMagic(L1Character cha) {
		if (_isCounterMagic && cha.hasSkillEffect(COUNTER_MAGIC)) {
			cha.removeSkillEffect(COUNTER_MAGIC);
			int castgfx = SkillsTable.getInstance().getTemplate(COUNTER_MAGIC).getCastGfx();
			cha.broadcastPacket(new S_SkillSound(cha.getId(), castgfx));
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_SkillSound(pc.getId(), castgfx));
			}
			return true;
		}
		return false;
	}

}
