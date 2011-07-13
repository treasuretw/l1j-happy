
package l1j.server.server.model.skill;

import static l1j.server.server.model.skill.L1SkillId.ICE_LANCE;
import static l1j.server.server.model.skill.L1SkillId.ABSOLUTE_BARRIER;
import static l1j.server.server.model.skill.L1SkillId.FREEZING_BLIZZARD;
import static l1j.server.server.model.skill.L1SkillId.EARTH_BIND;

import l1j.server.server.ActionCodes;
import l1j.server.server.GeneralThreadPool;
import l1j.server.server.model.L1Character;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1EffectInstance;
import l1j.server.server.model.Instance.L1NpcInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.Instance.L1PetInstance;
import l1j.server.server.model.Instance.L1SummonInstance;
import l1j.server.server.serverpackets.S_DoActionGFX;
import l1j.server.server.model.L1Object;

 /**
  *	NPC 火牢伤害
  */
public class NpcFireDamage {

	/** 使用者 */
	private L1Character user = null;

	/** 火牢 */
	private L1EffectInstance fire = null;

	public NpcFireDamage(L1Character cha,L1NpcInstance firewall) {
		user = (L1Character) cha;
		fire = (L1EffectInstance) firewall;
	}

	class Damage implements Runnable {

		private Damage() {
		}

		@Override
		public void run() {
			for (int findObjecCounts = 0; findObjecCounts < 8; findObjecCounts ++) {
				try {
					for (L1Object objects : L1World.getInstance().
							getVisibleObjects(user, 15)) { // 玩家视线范围15格
						if (objects instanceof L1PcInstance) { // 对玩家

							L1PcInstance pc = (L1PcInstance) objects;
							if (pc.getLocation().equals(fire.getLocation())) {

								if (pc.isDead()
										|| DamageRegeneration(pc)
										|| pc.hasSkillEffect(ICE_LANCE)			// 冰矛围篱
										|| pc.hasSkillEffect(ABSOLUTE_BARRIER)	// 绝对屏障
										|| pc.hasSkillEffect(FREEZING_BLIZZARD)	// 冰雪飓风
										|| pc.hasSkillEffect(EARTH_BIND)		// 大地屏障
								)
									continue;

								pc.sendPackets(new S_DoActionGFX(pc.getId(),
										ActionCodes.ACTION_Damage));
								pc.broadcastPacket(new S_DoActionGFX(pc.getId(),
										ActionCodes.ACTION_Damage));
								pc.receiveDamage(user, 25);
								pc.removeSkillEffect(66);
							}
						} else if (objects instanceof L1NpcInstance) { // 对 Npc
							if ((objects instanceof L1PetInstance)
									|| (objects instanceof L1SummonInstance)) { // 对宠物或召唤怪
								L1NpcInstance npc = (L1NpcInstance) objects;
								if (npc.getLocation().equals(fire.getLocation())) {

									if (npc.isDead()
											|| npc.getHiddenStatus() != 0
											|| DamageRegeneration(npc)
											|| npc.hasSkillEffect(ICE_LANCE)		// 冰矛围篱
											|| npc.hasSkillEffect(ABSOLUTE_BARRIER)	// 绝对屏障
											|| npc.hasSkillEffect(FREEZING_BLIZZARD)// 冰雪飓风
											|| npc.hasSkillEffect(EARTH_BIND)		// 大地屏障
									)
										continue;

									npc.broadcastPacket(new S_DoActionGFX(npc.getId(),
											ActionCodes.ACTION_Damage));
									npc.receiveDamage(user, 25);
									npc.removeSkillEffect(66);
								}
							}
						}
					}
					Thread.sleep(12 * 100); // 即时伤害 by a8889888
				} catch(Exception ex){}
			}
		}
	}

	public void onDamageAction() {
		Damage damage_run = new Damage();
		GeneralThreadPool.getInstance().execute(damage_run);
	}

	private boolean DamageRegeneration(L1Character cha) {
		if (cha.hasSkillEffect(99999))
			return true;
		cha.setSkillEffect(99999, 1000);
		return false;
	}
}
