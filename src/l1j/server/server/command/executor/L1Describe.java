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
package l1j.server.server.command.executor;

import l1j.server.server.model.Instance.L1ItemInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_SystemMessage;

/**
 * GM指令：描述
 */
public class L1Describe implements L1CommandExecutor {

private L1ItemInstance weapon = null;

	private int weaponHit = 0;

	private int weaponDmg = 0;

	private int weaponBowHit = 0;

	private int weaponBowDmg = 0;

	private int weaponEnchant = 0;

	private int BowweaponEnchant = 0;

	private int Hit = 0;

	private int Dmg = 0;

	private int BowHit = 0;

	private int BowDmg = 0;

	private L1Describe() {
	}

	public static L1CommandExecutor getInstance() {
		return new L1Describe();
	}

	@Override
	public void execute(L1PcInstance pc, String cmdName, String arg) {
		try {
			StringBuilder msg = new StringBuilder();
			pc.sendPackets(new S_SystemMessage("【角色名称】: " + pc.getName() + ""));
			int hpr = pc.getHpr() + pc.getInventory().hpRegenPerTick();
			int mpr = pc.getMpr() + pc.getInventory().mpRegenPerTick();
/*
			msg.append("Dmg: +" + pc.getDmgup() + " / ");
			msg.append("Hit: +" + pc.getHitup() + " / ");
			msg.append("MR: " + pc.getMr() + " / ");
			msg.append("HPR: " + hpr + " / ");
			msg.append("MPR: " + mpr + " / ");
			msg.append("Karma: " + pc.getKarma() + " / ");
			msg.append("Item: " + pc.getInventory().getSize() + " / ");
			pc.sendPackets(new S_SystemMessage(msg.toString()));
		}
*/
			// 人物攻击命中与额外攻击力
			pc.sendPackets(new S_SystemMessage("【人物攻击成功】: " + pc.getHitup() + " /【 人物额外攻击点数】: " + pc.getDmgup() +"。"));
			pc.sendPackets(new S_SystemMessage("【人物远攻命中率】: " + pc.getBowHitup() + " / 【人物远攻额外攻击点数】: " + pc.getBowDmgup() + "。"));

			// 武器攻击命中与额外攻击力
			weapon = pc.getWeapon();
			if (weapon != null) {
				weaponHit = weapon.getItem().getHitModifier() + weapon.getHitByMagic();
				weaponDmg = weapon.getItem().getDmgModifier() + weapon.getDmgByMagic();
				if (weapon.getItem().getType1() != 20 && weapon.getItem().getType1() != 62) {
					weaponEnchant = weapon.getEnchantLevel() - weapon.get_durability();
				} else {
					BowweaponEnchant = weapon.getEnchantLevel();
				}
				if (weaponHit > 0 || weaponDmg > 0) {
					pc.sendPackets(new S_SystemMessage("【武器攻击成功】: " + weaponHit + " / 【武器额外攻击点数】: " + weaponDmg + "。"));}
				if (weaponBowHit > 0 || weaponBowDmg > 0) {
					pc.sendPackets(new S_SystemMessage("【武器远攻命中率】: " + weaponBowHit + " / 【武器远攻额外攻击点数】: " + weaponBowDmg + "。"));}
				Hit = weaponHit + pc.getHitup() + pc.getOriginalHitup() + (weaponEnchant / 2) + pc.getHitModifierByArmor();
				Dmg = weaponDmg + pc.getDmgup() + pc.getOriginalDmgup() + pc.getDmgModifierByArmor();
				BowHit = weaponBowHit + pc.getBowHitup() + pc.getOriginalBowHitup() + (BowweaponEnchant / 2) + pc.getBowHitModifierByArmor();
				BowDmg = weaponBowDmg + pc.getBowDmgup() + pc.getOriginalBowDmgup() + pc.getBowDmgModifierByArmor();
			} else {
				Hit =  pc.getHitup() + pc.getOriginalHitup() + pc.getHitModifierByArmor();
				Dmg =  pc.getDmgup() + pc.getOriginalDmgup() + pc.getDmgModifierByArmor();
				BowHit = pc.getBowHitup() + pc.getOriginalBowHitup() + pc.getBowHitModifierByArmor();
				BowDmg = pc.getBowDmgup() + pc.getOriginalBowDmgup() + pc.getBowDmgModifierByArmor();
			}

			// 防具攻击命中与额外攻击力
			if (pc.getHitModifierByArmor() > 0 || pc.getDmgModifierByArmor() > 0) {
				pc.sendPackets(new S_SystemMessage("【防具攻击成功】: " + pc.getHitModifierByArmor() + " /【防具额外攻击点数】: " + pc.getDmgModifierByArmor() + "。"));}
			if (pc.getBowHitModifierByArmor() > 0 || pc.getBowDmgModifierByArmor() > 0) {
				pc.sendPackets(new S_SystemMessage("【防具远攻命中率】: " + pc.getBowHitModifierByArmor() + " / 【防具额外攻击点数】: " + pc.getBowDmgModifierByArmor() + "。"));}	

			// 整体攻击命中与额外攻击力
			pc.sendPackets(new S_SystemMessage("【加总攻击成功】: " + Hit + " / 【加总额外攻击点数】: " + Dmg + "。"));
			pc.sendPackets(new S_SystemMessage("【加总远攻命中率】: " + BowHit + " / 【加总远攻额外攻击点数】: " + BowDmg + "。"));
			// msg.append("魔法防御: " + pc.getMr() + " / ");
			pc.sendPackets(new S_SystemMessage("【体力回复量】: " + hpr + " / 【魔力回复量】: " + mpr + "。"));
			pc.sendPackets(new S_SystemMessage("【石化耐性】: " + pc.getRegistStone() + " / 【寒冰耐性】: " + pc.getRegistFreeze() + "。"));
			pc.sendPackets(new S_SystemMessage("【睡眠耐性】: " + pc.getRegistSleep() + " / 【昏迷耐性】: " + pc.getRegistStun() + "。"));
			pc.sendPackets(new S_SystemMessage("【闇黑耐性】: " + pc.getRegistBlind() +  " / 【支撑耐性】: " + pc.getRegistSustain() + "。"));
			pc.sendPackets(new S_SystemMessage("【PK值】: " + pc.get_PKcount() +   " / 【转生次数】: " + pc.getLevelmet() +  "。"));
			msg.append("【物品数量】: " + pc.getInventory().getSize() + "。");
			pc.sendPackets(new S_SystemMessage(msg.toString()));
		}
		catch (Exception e) {
			pc.sendPackets(new S_SystemMessage(cmdName + " 指令错误"));
		}
	}
}
