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

import java.util.EnumMap;
import java.util.logging.Logger;

import l1j.server.Config;
import l1j.server.server.datatables.SprTable;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_Disconnect;
import l1j.server.server.serverpackets.S_Paralysis;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.serverpackets.S_SystemMessage;
import l1j.server.server.utils.LogRecorder;

// TODO 检测到加速冰冻
import static l1j.server.server.model.skill.L1SkillId.MOVE_BACK;
import static l1j.server.server.model.skill.L1SkillId.FREEZING_BLIZZARD;
import l1j.server.server.serverpackets.S_SkillSound;
import l1j.server.server.serverpackets.S_SkillIconGFX;

/**
 *	检查加速器使用的类别。
 */

public class AcceleratorChecker {

	private static final Logger _log = Logger.getLogger(AcceleratorChecker.class.getName());

	private final L1PcInstance _pc;

	private int _injusticeCount;

	private int _justiceCount;

	private static final int INJUSTICE_COUNT_LIMIT = Config.INJUSTICE_COUNT;

	private static final int JUSTICE_COUNT_LIMIT = Config.JUSTICE_COUNT;

	// 实际には移动と攻击のパケット间隔はsprの理论值より5%ほど迟い。
	// それを考虑して-5としている。
	private static final double CHECK_STRICTNESS = (Config.CHECK_STRICTNESS - 5) / 100D;

	private static final double HASTE_RATE = 0.75; // 速度 * 1.33

	private static final double WAFFLE_RATE = 0.87; // 速度 * 1.15

	private static final double DOUBLE_HASTE_RATE = 0.375; // 速度 * 2.66

	private final EnumMap<ACT_TYPE, Long> _actTimers = new EnumMap<ACT_TYPE, Long>(
			ACT_TYPE.class);

	private final EnumMap<ACT_TYPE, Long> _checkTimers = new EnumMap<ACT_TYPE, Long>(
			ACT_TYPE.class);

	public static enum ACT_TYPE {
		MOVE, ATTACK, SPELL_DIR, SPELL_NODIR
	}

	// 检查结果
	public static final int R_OK = 0;

	public static final int R_DETECTED = 1;

	public static final int R_DISPOSED = 2;

	public AcceleratorChecker(L1PcInstance pc) {
		_pc = pc;
		_injusticeCount = 0;
		_justiceCount = 0;
		long now = System.currentTimeMillis();
		for (ACT_TYPE each : ACT_TYPE.values()) {
			_actTimers.put(each, now);
			_checkTimers.put(each, now);
		}
	}

	/**
	 * アクションの间隔が不正でないかチェックし、适宜处理を行う。
	 * 
	 * @param type
	 *            - チェックするアクションのタイプ
	 * @return 问题がなかった场合は0、不正であった场合は1、不正动作が一定回数に达した ためプレイヤーを切断した场合は2を返す。
	 */
	public int checkInterval(ACT_TYPE type) {
		int result = R_OK;
		long now = System.currentTimeMillis();
		long interval = now - _actTimers.get(type);
		int rightInterval = getRightInterval(type);

		interval *= CHECK_STRICTNESS;

		if (0 < interval && interval < rightInterval) {
			_injusticeCount++;
			_justiceCount = 0;
			if (_injusticeCount >= INJUSTICE_COUNT_LIMIT) {
				doPunishment(Config.ILLEGAL_SPEEDUP_PUNISHMENT);
				return R_DISPOSED;
			}
			result = R_DETECTED;
		} else if (interval >= rightInterval) {
			_justiceCount++;
			if (_justiceCount >= JUSTICE_COUNT_LIMIT) {
				_injusticeCount = 0;
				_justiceCount = 0;
			}
		}

		// 检证用
		// double rate = (double) interval / rightInterval;
		// System.out.println(String.format("%s: %d / %d = %.2f (o-%d x-%d)",
		// type.toString(), interval, rightInterval, rate,
		// _justiceCount, _injusticeCount));

		_actTimers.put(type, now);
		return result;
	}

	/**
	 * 加速检测处罚
	 * @param punishmaent 处罚模式
	 */

	private void doPunishment(int punishmaent) {

		if (!_pc.isGm()) {// 如果不是GM才执行处罚
			int x = _pc.getX() ,y = _pc.getY() ,mapid = _pc.getMapId();// 记录坐标
			switch (punishmaent) {

			case 0:// 冰冻效果
				int fettersTime = 8000;
				L1EffectSpawn.getInstance().spawnEffect(81168, fettersTime, _pc.getX(), _pc.getY(), _pc.getMapId());
				L1PcInstance targetPc = (L1PcInstance) _pc;
				targetPc.setSkillEffect(FREEZING_BLIZZARD, fettersTime);
				targetPc.sendPackets(new S_SkillSound(targetPc.getId(), 2));
				targetPc.broadcastPacket(new S_SkillSound(targetPc.getId(),2));
				targetPc.sendPackets(new S_Paralysis(S_Paralysis.TYPE_FREEZE,true));
				_pc.setSkillEffect(MOVE_BACK, 1000 * 8);
				_pc.sendPackets(new S_SkillIconGFX(36, 8));
				_pc.sendPackets(new S_SystemMessage("系统检测到您的速度异常,请您停留8秒。"));
				_log.info(String.format("检测到玩家: [ %s ] 正在使用加速器，处罚方式：冻结8秒。",_pc.getName()));
				break;

			case 1:// 锁定人物10秒
				_pc.sendPackets(new S_Paralysis(S_Paralysis.TYPE_BIND, true));
				_pc.sendPackets(new S_SystemMessage("系统检测到您的速度异常,请您停留10秒。"));
				_log.info(String.format("检测到玩家: [ %s ] 正在使用加速器，处罚方式：束缚10秒。",_pc.getName()));
				try {
					Thread.sleep(10000);// 暂停十秒
				} catch (Exception e) {
					System.out.println(e.getLocalizedMessage());
				}
				_pc.sendPackets(new S_Paralysis(S_Paralysis.TYPE_BIND, false));
				break;

			case 2:// 死
				_pc.sendPackets(new S_SystemMessage("竟敢使用外挂!去死吧!")); // 去死吧!
				_log.info(String.format("检测到玩家: [ %s ] 正在使用加速器，处罚方式：死。",_pc.getName()));
				_pc.setCurrentHp(0);
				_pc.death(null);
				break;

			case 3:// 传到GM房，30秒后传回
				L1Teleport.teleport(_pc, 32737, 32796, (short) 99, 5, false);
				_pc.sendPackets(new S_SystemMessage("因为你使用加速器，被传送到了GM房，30秒后传回。"));
				_log.info(String.format("检测到玩家: [ %s ] 正在使用加速器，处罚方式：传送到GM房30秒。",_pc.getName()));
				try {
					Thread.sleep(30000);// 暂停30秒
				} catch (Exception e) {
					System.out.println(e.getLocalizedMessage());
				}
				L1Teleport.teleport(_pc, x, y, (short) mapid, 5, false);
				break;

 			case 4:// 剔除
				_pc.sendPackets(new S_ServerMessage(945));
				_pc.sendPackets(new S_Disconnect());
				_log.info(String.format("检测到玩家: [ %s ] 正在使用加速器，处罚方式：强制切断其连线。",_pc.getName()));
				break;
			}
		} else {
			// GM不需要断线
			_pc.sendPackets(new S_SystemMessage("防加速功能检测有效。"));
			_injusticeCount = 0;
		}
		if (Config.writeRobotsLog) {
			LogRecorder.writeRobotsLog(_pc);// 加速器纪录
		}
	}

	/**
	 * PCの状态から指定された种类のアクションの正しいインターバル(ms)を计算し、返す。
	 * 
	 * @param type
	 *            - アクションの种类
	 * @param _pc
	 *            - 调べるPC
	 * @return 正しいインターバル(ms)
	 */
	private int getRightInterval(ACT_TYPE type) {
		int interval;

		// 动作判断
		switch (type) {
		case ATTACK:
			interval = SprTable.getInstance().getAttackSpeed(
					_pc.getTempCharGfx(), _pc.getCurrentWeapon() + 1);
			break;
		case MOVE:
			interval = SprTable.getInstance().getMoveSpeed(
					_pc.getTempCharGfx(), _pc.getCurrentWeapon());
			break;
		case SPELL_DIR:
			interval = SprTable.getInstance().getDirSpellSpeed(
					_pc.getTempCharGfx());
			break;
		case SPELL_NODIR:
			interval = SprTable.getInstance().getNodirSpellSpeed(
					_pc.getTempCharGfx());
			break;
		default:
			return 0;
		}

		// 一段加速
		switch (_pc.getMoveSpeed()) {
		case 1: // 加速术
			interval *= HASTE_RATE;
			break;
		case 2: // 缓速术
			interval /= HASTE_RATE;
			break;
		default:
			break;
		}

		// 二段加速
		switch (_pc.getBraveSpeed()) {
		case 1: // 勇水
			interval *= HASTE_RATE; // 攻速、移速 * 1.33倍
			break;
		case 3: // 精饼
			interval *= WAFFLE_RATE; // 攻速、移速 * 1.15倍
			break;
		case 4: // 神疾、风走、行走
			if (type.equals(ACT_TYPE.MOVE)) {
				interval *= HASTE_RATE; // 移速 * 1.33倍
			}
			break;
		case 5: // 超级加速
			interval *= DOUBLE_HASTE_RATE; // 攻速、移速 * 2.66倍
			break;
		case 6: // 血之渴望
			if (type.equals(ACT_TYPE.ATTACK)) {
				interval *= HASTE_RATE; // 攻速 * 1.33倍
			}
			break;
		default:
			break;
		}

		// 生命之树果实
		if (_pc.isRibrave() && type.equals(ACT_TYPE.MOVE)) { // 移速 * 1.15倍
			interval *= WAFFLE_RATE;
		}
		// 三段加速
		if (_pc.isThirdSpeed()) { // 攻速、移速 * 1.15倍
			interval *= WAFFLE_RATE;
		}
		// 风之枷锁
		if (_pc.isWindShackle()) { // 攻速 / 2倍
			interval /= 2;
		}
		if (_pc.getMapId() == 5143) { // 宠物竞速例外
			interval *= 0.1;
		}
		return interval;
	}
}
