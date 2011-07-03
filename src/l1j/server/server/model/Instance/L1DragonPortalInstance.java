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
package l1j.server.server.model.Instance;

import static l1j.server.server.model.skill.L1SkillId.EFFECT_BLOODSTAIN_OF_ANTHARAS;
import static l1j.server.server.model.skill.L1SkillId.EFFECT_BLOODSTAIN_OF_FAFURION;

import l1j.server.server.datatables.NPCTalkDataTable;
import l1j.server.server.model.L1DragonSlayer;
import l1j.server.server.model.L1NpcTalkData;
import l1j.server.server.model.L1Teleport;
import l1j.server.server.serverpackets.S_NPCTalkReturn;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.templates.L1Npc;

public class L1DragonPortalInstance extends L1NpcInstance {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @param template
	 */
	public L1DragonPortalInstance(L1Npc template) {
		super(template);
	}

	@Override
	public void onTalkAction(L1PcInstance player) {
		int npcid = getNpcTemplate().get_npcId();
		int portalNumber = getPortalNumber(); // 龙门编号
		int X = 32599;
		int Y = 32742;
		short mapId = 1005;
		int objid = getId();
		L1NpcTalkData talking = NPCTalkDataTable.getInstance().getTemplate(npcid);
		String htmlid = null;
		String[] htmldata = null;
		if ((npcid >= 81273 && npcid <= 81276)) { // 龙之门扉
			if (portalNumber == -1) {
				return;
			}
			mapId = (short) (1005 + portalNumber); // 地图判断
			if (L1DragonSlayer.getInstance().getPlayersCount(portalNumber) >= 32) {
				player.sendPackets(new S_ServerMessage(1536)); // 参与人员已额满，目前无法再入场。
			} else if (L1DragonSlayer.getInstance().getDragonSlayerStatus()[portalNumber] >= 5) {
				player.sendPackets(new S_ServerMessage(1537)); // 攻略已经开始，目前无法入场。
			} else {
				if (portalNumber >= 0 && portalNumber <= 5) { // 安塔瑞斯副本
					if (player.hasSkillEffect(EFFECT_BLOODSTAIN_OF_ANTHARAS)) {
						player.sendPackets(new S_ServerMessage(1626)); // 龙之血痕已穿透全身，在血痕的气味消失之前，无法再进入龙之门扉。
						return;
					}
				} else if (portalNumber >= 6 && portalNumber <= 11) { // 法利昂副本
					if (player.hasSkillEffect(EFFECT_BLOODSTAIN_OF_FAFURION)) {
						player.sendPackets(new S_ServerMessage(1626)); // 龙之血痕已穿透全身，在血痕的气味消失之前，无法再进入龙之门扉。
						return;
					}
					X = 32927;
					Y = 32741;
				}
				player.setPortalNumber(portalNumber);
				L1DragonSlayer.getInstance().addPlayerList(player, portalNumber);
				L1Teleport.teleport(player, X, Y, mapId, 2, true);
			}
		}
		else if (npcid == 81301) { // 传送进入安塔瑞斯栖息地
			L1DragonSlayer.getInstance().startDragonSlayer(player.getPortalNumber());
			L1Teleport.teleport(player, 32795, 32665, player.getMapId(), 4, true);
		}
		else if (npcid == 81302) { // 传送出去安塔瑞斯栖息地
			L1Teleport.teleport(player, 32700, 32671, player.getMapId(), 6, true);
		}
		else if (npcid == 81303) { // 传送进入法利昂栖息地
			L1DragonSlayer.getInstance().startDragonSlayer(player.getPortalNumber());
			L1Teleport.teleport(player, 32988, 32843, player.getMapId(), 6, true);
		}
		else if (npcid == 81304) { // 传送出去法利昂栖息地
			L1Teleport.teleport(player, 32937, 32672, player.getMapId(), 6, true);
		}
		else if (npcid == 81305) { // 传送进入安塔瑞斯洞穴
		}
		else if (npcid == 81306) { // 传送到安塔瑞斯 洞穴入口(阶段型)
			L1Teleport.teleport(player, 32677, 32746, player.getMapId(), 6, true);
		}
		else if (npcid == 81277) { // 隐匿的巨龙谷入口
			int playerLv = player.getLevel();//角色等级
			if (playerLv >= 30 && playerLv <= 51) {
				htmlid = "dsecret1";
			} else if (playerLv >= 52) {
				htmlid = "dsecret2";
			} else {
				htmlid = "dsecret3";
			}
		}

		if (htmlid != null) {
			player.sendPackets(new S_NPCTalkReturn(objid, htmlid, htmldata));
		} else {
			player.sendPackets(new S_NPCTalkReturn(talking, objid, 1));
		}
	}
}
