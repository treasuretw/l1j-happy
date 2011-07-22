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

import static l1j.server.server.model.skill.L1SkillId.ABSOLUTE_BARRIER;
import static l1j.server.server.model.skill.L1SkillId.CALL_CLAN;
import static l1j.server.server.model.skill.L1SkillId.EARTH_BIND;
import static l1j.server.server.model.skill.L1SkillId.FIRE_WALL;
import static l1j.server.server.model.skill.L1SkillId.FOG_OF_SLEEPING;
import static l1j.server.server.model.skill.L1SkillId.FREEZING_BLIZZARD;
import static l1j.server.server.model.skill.L1SkillId.FREEZING_BREATH;
import static l1j.server.server.model.skill.L1SkillId.ICE_LANCE;
import static l1j.server.server.model.skill.L1SkillId.ICE_LANCE_BASILISK;
import static l1j.server.server.model.skill.L1SkillId.ICE_LANCE_COCKATRICE;
import static l1j.server.server.model.skill.L1SkillId.LIFE_STREAM;
import static l1j.server.server.model.skill.L1SkillId.MASS_TELEPORT;
import static l1j.server.server.model.skill.L1SkillId.MEDITATION;
import static l1j.server.server.model.skill.L1SkillId.RUN_CLAN;
import static l1j.server.server.model.skill.L1SkillId.STATUS_CURSE_PARALYZED;
import static l1j.server.server.model.skill.L1SkillId.TELEPORT;
import static l1j.server.server.model.skill.L1SkillId.TRUE_TARGET;
import static l1j.server.server.model.skill.L1SkillId.COUNTER_BARRIER;
import static l1j.server.server.model.skill.L1SkillId.SHOCK_STUN;
import static l1j.server.server.model.skill.L1SkillId.TRIPLE_ARROW;
//import static l1j.server.server.model.skill.L1SkillId.IMMUNE_TO_HARM;
import l1j.server.Config;
import l1j.server.server.ActionCodes;
import l1j.server.server.ClientThread;
import l1j.server.server.datatables.SkillsTable;
import l1j.server.server.model.AcceleratorChecker;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.skill.L1SkillUse;
import l1j.server.server.serverpackets.S_ServerMessage;

// Referenced classes of package l1j.server.server.clientpackets:
// ClientBasePacket

/**
 * 处理收到由客户端传来使用魔法的封包
 */
public class C_UseSkill extends ClientBasePacket {

	public C_UseSkill(byte abyte0[], ClientThread client) throws Exception {
		super(abyte0);
		int row = readC();
		int column = readC();
		int skillId = (row * 8) + column + 1;
		String charName = null;
		String message = null;
		int targetId = 0;
		int targetX = 0;
		int targetY = 0;
		L1PcInstance pc = client.getActiveChar();

		//不处理封包的条件
		if (pc.isTeleport() || pc.isDead() || pc.isSkillDelay() || pc.isSPELL_DIRdelay() 
			    || pc.isSPELL_NODIRdelay()) {
			return;
		}
		if (!pc.getMap().isUsableSkill()) {
			pc.sendPackets(new S_ServerMessage(563)); // \f1ここでは使えません。
			return;
		}
		if (!pc.isSkillMastery(skillId)) {
			return;
		}

		if(pc.isInCharReset()) {
			return;
		}

		// TODO 封锁 LinHelp无条件喝水功能
		if (pc.isParalyzed() || pc.isSleeped()
				 || pc.isFreeze() || pc.isStun()) {
			return;
		}

		if (!pc.isSkillMastery(skillId)) {
			return;
		}

		// 检查使用魔法的间隔
		if (Config.CHECK_SPELL_INTERVAL) {
			int result;
			// FIXME 判断有向及无向的魔法
			if (SkillsTable.getInstance().getTemplate(skillId).getActionId() == ActionCodes.ACTION_SkillAttack) {
				result = pc.getAcceleratorChecker().checkInterval(AcceleratorChecker.ACT_TYPE.SPELL_DIR);
			}
			else {
				result = pc.getAcceleratorChecker().checkInterval(AcceleratorChecker.ACT_TYPE.SPELL_NODIR);
			}
			if (result == AcceleratorChecker.R_DISPOSED) {
				return;
			}
		}

		// 增加三重矢武器判断
		if (skillId == TRIPLE_ARROW && pc.getWeapon().getItem().getType1() != 20) {
			//pc.sendPackets(new S_ServerMessage(1008));
			return;
		}

		if ((skillId == SHOCK_STUN ||skillId ==COUNTER_BARRIER)&& pc.getWeapon().getItem().getType1() != 50) {
			//pc.sendPackets(new S_ServerMessage(1008));
			return;
		}
		// 增加三重矢武器判断  end

		if (abyte0.length > 4) {
			try {
				if ((skillId == CALL_CLAN) || (skillId == RUN_CLAN)) { // コールクラン、ランクラン
					charName = readS();
				}
				else if (skillId == TRUE_TARGET) { // トゥルーターゲット
					targetId = readD();
					targetX = readH();
					targetY = readH();
					message = readS();
				}
				else if ((skillId == TELEPORT) || (skillId == MASS_TELEPORT)) { // テレポート、マステレポート
					readH(); // MapID
					targetId = readD(); // Bookmark ID
				}
				else if ((skillId == FIRE_WALL) || (skillId == LIFE_STREAM)) { // ファイアーウォール、ライフストリーム
					targetX = readH();
					targetY = readH();
				}
				else {
					targetId = readD();
					targetX = readH();
					targetY = readH();
				}
			}
			catch (Exception e) {
				// _log.log(Level.SEVERE, "", e);
			}
		}

		// 防止外挂非法魔法攻击 add
		if (pc.hasSkillEffect(ICE_LANCE)					// 冰矛围篱
		//		|| pc.hasSkillEffect(IMMUNE_TO_HARM)		// 圣结界 (测试用)
				|| pc.hasSkillEffect(FREEZING_BLIZZARD) 	// 冰雪飓风
				|| pc.hasSkillEffect(FREEZING_BREATH)		// 寒冰喷吐
				|| pc.hasSkillEffect(EARTH_BIND)			// 大地屏障
				|| pc.hasSkillEffect(ICE_LANCE_COCKATRICE)	// 亚力安冰矛围篱
				|| pc.hasSkillEffect(ICE_LANCE_BASILISK)	// 邪恶蜥蜴冰矛围篱
				|| pc.hasSkillEffect(SHOCK_STUN)			// 冲击之晕
				|| pc.hasSkillEffect(FOG_OF_SLEEPING)		// 沉睡之雾
				|| pc.hasSkillEffect(STATUS_CURSE_PARALYZED)) {	// 木乃尹状态
			return;
		}
		// 防止外挂非法魔法攻击 end

		if (pc.hasSkillEffect(ABSOLUTE_BARRIER)) { // 取消绝对屏障
			pc.removeSkillEffect(ABSOLUTE_BARRIER);
		}
		if (pc.hasSkillEffect(MEDITATION)) { // 取消冥想效果
			pc.removeSkillEffect(MEDITATION);
		}

		try {
			if ((skillId == CALL_CLAN) || (skillId == RUN_CLAN)) { // コールクラン、ランクラン
				if (charName.isEmpty()) {
					// 名前が空の场合クライアントで弹かれるはず
					return;
				}

				L1PcInstance target = L1World.getInstance().getPlayer(charName);

				if (target == null) {
					// メッセージが正确であるか未调查
					pc.sendPackets(new S_ServerMessage(73, charName)); // \f1%0はゲームをしていません。
					return;
				}
				if (pc.getClanid() != target.getClanid()) {
					pc.sendPackets(new S_ServerMessage(414)); // 同じ血盟员ではありません。
					return;
				}
				targetId = target.getId();
				if (skillId == CALL_CLAN) {
					// 移动せずに连续して同じクラン员にコールクランした场合、向きは前回の向きになる
					int callClanId = pc.getCallClanId();
					if ((callClanId == 0) || (callClanId != targetId)) {
						pc.setCallClanId(targetId);
						pc.setCallClanHeading(pc.getHeading());
					}
				}
			}
			L1SkillUse l1skilluse = new L1SkillUse();
			l1skilluse.handleCommands(pc, skillId, targetId, targetX, targetY, message, 0, L1SkillUse.TYPE_NORMAL);

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
