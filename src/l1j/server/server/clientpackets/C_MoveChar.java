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

import static l1j.server.server.model.Instance.L1PcInstance.REGENSTATE_MOVE;
import static l1j.server.server.model.skill.L1SkillId.ABSOLUTE_BARRIER;
import static l1j.server.server.model.skill.L1SkillId.MEDITATION;
//import static l1j.server.server.model.skill.L1SkillId.IMMUNE_TO_HARM;
import static l1j.server.server.model.skill.L1SkillId.FOG_OF_SLEEPING;
import static l1j.server.server.model.skill.L1SkillId.ICE_LANCE;
import static l1j.server.server.model.skill.L1SkillId.FREEZING_BLIZZARD;
import static l1j.server.server.model.skill.L1SkillId.FREEZING_BREATH;
import static l1j.server.server.model.skill.L1SkillId.EARTH_BIND;
import static l1j.server.server.model.skill.L1SkillId.ICE_LANCE_COCKATRICE;
import static l1j.server.server.model.skill.L1SkillId.ICE_LANCE_BASILISK;
import static l1j.server.server.model.skill.L1SkillId.SHOCK_STUN;
import static l1j.server.server.model.skill.L1SkillId.STATUS_CURSE_PARALYZED;
import l1j.server.Config;
import l1j.server.server.ClientThread;
import l1j.server.server.model.AcceleratorChecker;
import l1j.server.server.model.Dungeon;
import l1j.server.server.model.DungeonRandom;
import l1j.server.server.model.L1Location;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.trap.L1WorldTraps;
import l1j.server.server.serverpackets.S_MoveCharPacket;
import l1j.server.server.serverpackets.S_SystemMessage;
import l1j.william.External_water;

// Referenced classes of package l1j.server.server.clientpackets:
// ClientBasePacket

/**
 * 处理收到由客户端传来移动角色的封包
 */
public class C_MoveChar extends ClientBasePacket {

	private static final byte HEADING_TABLE_X[] =
	{ 0, 1, 1, 1, 0, -1, -1, -1 };

	private static final byte HEADING_TABLE_Y[] =
	{ -1, -1, 0, 1, 1, 1, 0, -1 };

	private static final int CLIENT_LANGUAGE = Config.CLIENT_LANGUAGE;

	// 地图编号的研究
	@SuppressWarnings("unused")
	private void sendMapTileLog(L1PcInstance pc) {
		pc.sendPackets(new S_SystemMessage(pc.getMap().toString(pc.getLocation())));
	}

	// 移动
	public C_MoveChar(byte decrypt[], ClientThread client) throws Exception {
		super(decrypt);
		int locx = readH();
		int locy = readH();
		int heading = readC();

		L1PcInstance pc = client.getActiveChar();

		if (pc.isTeleport()) { // 传送中
			return;
		}

		// 检查移动的时间间隔
		if (Config.CHECK_MOVE_INTERVAL) {
			int result;
			result = pc.getAcceleratorChecker().checkInterval(AcceleratorChecker.ACT_TYPE.MOVE);
			if (result == AcceleratorChecker.R_DISPOSED) {
				return;
			}
		}

		// 防止外挂在不能移动的状态下移动 add
		if (Config.PlugMove) {
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
				External_water.water(decrypt, client);
			}
		}
/*
					Thread.sleep(1000);				// 暂停一秒
					pc.getNetConnection().kick();	// 踢下线
					pc.getMap().setPassable(pc.getLocation(), false);
					L1Teleport.teleport(pc, pc.getLocation(), heading, true);
					return;
*/
		// 防止外挂在不能移动的状态下移动 end

		if (pc.hasSkillEffect(MEDITATION)) { // 取消冥想效果
			pc.removeSkillEffect(MEDITATION);
		}
		pc.setCallClanId(0); // コールクランを唱えた后に移动すると召唤无效

		if (!pc.hasSkillEffect(ABSOLUTE_BARRIER)) { // 绝对屏障
			pc.setRegenState(REGENSTATE_MOVE);
		}
		pc.getMap().setPassable(pc.getLocation(), true);

		// 判断伺服器国家代码 (China Only)
		if (CLIENT_LANGUAGE == 5) {
			// heading ^= 0x49;	// 取得真实面向
			// 取得真实座标
			locx = pc.getX();	// X轴座标
			locy = pc.getY();	// Y轴座标
		}

		locx += HEADING_TABLE_X[heading]; // 4.26 Start
		locy += HEADING_TABLE_Y[heading]; // 4.26 End

		if (Dungeon.getInstance().dg(locx, locy, pc.getMap().getId(), pc)) { // 传点
			return;
		}
		if (DungeonRandom.getInstance().dg(locx, locy, pc.getMap().getId(), pc)) { // 取得随机传送地点
			return;
		}

		// Esc键的bug修复。
		L1Location oldLoc = pc.getLocation();
		if ((oldLoc.getX() + 10 < locx)
				|| (oldLoc.getX() - 10 > locx)
				|| (oldLoc.getY() + 10 < locy)
				|| (oldLoc.getX() - 10 > locx)) {
			return;
		}

		pc.getLocation().set(locx, locy);
		pc.setHeading(heading);
		if (pc.isGmInvis() || pc.isGhost()) {}
		else if (pc.isInvisble()) {
			pc.broadcastPacketForFindInvis(new S_MoveCharPacket(pc), true);
		}
		else {
			pc.broadcastPacket(new S_MoveCharPacket(pc));
		}

		// sendMapTileLog(pc); //发送信息的目的地瓦（为调查地图））
		// 宠物竞速-判断圈数
		l1j.server.server.model.game.L1PolyRace.getInstance().checkLapFinish(pc);
		L1WorldTraps.getInstance().onPlayerMoved(pc);

		pc.getMap().setPassable(pc.getLocation(), false);
		// user.UpdateObject(); // 可视范围内の全オブジェクト更新
	}
}