package l1j.william;

import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import l1j.server.Config;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_SkillSound;

/**
 *	魔法特效(人物周围出现)
 *	@author 狼人香
 */

public class SkillSound extends TimerTask {

	private static Logger _log = Logger.getLogger(SkillSound.class.getName());

	private final L1PcInstance _pc;

	public SkillSound(L1PcInstance pc) { // 建構子
		_pc = pc;
	}

	@Override
	public void run() {
		try {
			if (_pc.isDead()) {
				return;
			}

			// 以声望等级加特效
			else if ((_pc.getFameLevel() >= 0) && (_pc.getFameLevel() <= 223)) {// 判断范围
				_pc.sendPackets(new S_SkillSound(_pc.getId(), Config.Phase1));	// 特效编号
				_pc.broadcastPacket(new S_SkillSound(_pc.getId(), Config.Phase1));
			}

			else if ((_pc.getFameLevel() >= 224) && (_pc.getFameLevel() <= 3583)) {
				_pc.sendPackets(new S_SkillSound(_pc.getId(), Config.Phase2));
				_pc.broadcastPacket(new S_SkillSound(_pc.getId(), Config.Phase2)); 
			}

			else if ((_pc.getFameLevel() >= 3584) && (_pc.getFameLevel() <= 7167)) {
				_pc.sendPackets(new S_SkillSound(_pc.getId(), Config.Phase3));
				_pc.broadcastPacket(new S_SkillSound(_pc.getId(), Config.Phase3));
			}

			else if ((_pc.getFameLevel() >= 7168) && (_pc.getFameLevel() <= 14334)) {
				_pc.sendPackets(new S_SkillSound(_pc.getId(), Config.Phase4));
				_pc.broadcastPacket(new S_SkillSound(_pc.getId(), Config.Phase4));
			}

			else if ((_pc.getFameLevel() >= 14335) && (_pc.getFameLevel() <= 15000)) {
				_pc.sendPackets(new S_SkillSound(_pc.getId(), Config.Phase5));
				_pc.broadcastPacket(new S_SkillSound(_pc.getId(), Config.Phase5));
			}

			else if (_pc.getFameLevel() >= 15001) {
				_pc.sendPackets(new S_SkillSound(_pc.getId(), Config.Phase6));
				_pc.broadcastPacket(new S_SkillSound(_pc.getId(), Config.Phase6));
			}

			else {
				return;	// 自动停止
			}
			_pc.sendPackets(new S_SkillSound(_pc.getId(), 4856)); // 基底效果
		} catch (Throwable e) {
			_log.log(Level.WARNING, e.getLocalizedMessage(), e);
		}
	}

}
