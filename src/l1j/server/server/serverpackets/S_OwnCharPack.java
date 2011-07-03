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
package l1j.server.server.serverpackets;

import static l1j.server.server.model.skill.L1SkillId.STATUS_THIRD_SPEED;

import l1j.server.server.Opcodes;
import l1j.server.server.model.Instance.L1PcInstance;
//import l1j.william.L1WilliamSystemMessage;

// Referenced classes of package l1j.server.server.serverpackets:
// ServerBasePacket

public class S_OwnCharPack extends ServerBasePacket {

	private static final String S_OWN_CHAR_PACK = "[S] S_OwnCharPack";

	private static final int STATUS_INVISIBLE = 2;

	private static final int STATUS_PC = 4;

	private static final int STATUS_GHOST = 128;

	private byte[] _byte = null;

	public S_OwnCharPack(L1PcInstance pc) {
		buildPacket(pc);
	}

	private void buildPacket(L1PcInstance pc) {
		int status = STATUS_PC;
/*
		// sosodemon add 称号系统 0-200 BY.SosoDEmoN Start 
		String famename = "";
		if (pc.getAccessLevel() == 200 && pc.getFameLevel()<= 20000){//fM【总统】	 					  
			famename = L1WilliamSystemMessage.ShowMessage(1156);
		}
		if (pc.getAccessLevel() == 100 && pc.getFameLevel()<= 20000){//fM【总统】助手
			famename = L1WilliamSystemMessage.ShowMessage(1175);
		}
		if (pc.getAccessLevel() == 11 && pc.getFameLevel()<= 20000){//fM【VIP1】
			famename = L1WilliamSystemMessage.ShowMessage(1157);
		}
		if (pc.getAccessLevel() == 12 && pc.getFameLevel()<= 20000){//fV【VIP2】
			famename = L1WilliamSystemMessage.ShowMessage(1158);
		}
		if (pc.getAccessLevel() == 13 && pc.getFameLevel()<= 20000){//fD【VIP3】
			famename = L1WilliamSystemMessage.ShowMessage(1159);
		}
		if (pc.getFameLevel() >= 1 && pc.getFameLevel() <= 2 
				&& pc.getAccessLevel() <= 10) { //fM【新兵】
			famename = L1WilliamSystemMessage.ShowMessage(1160); 
		} 
		if (pc.getFameLevel() >= 3 && pc.getFameLevel() <= 6
				&& pc.getAccessLevel() <= 10) { //fM【下等兵】
			famename = L1WilliamSystemMessage.ShowMessage(1161); 
		}  
		if (pc.getFameLevel() >= 7 && pc.getFameLevel() <= 14
				&& pc.getAccessLevel() <= 10) { //fM【中等兵】
			famename = L1WilliamSystemMessage.ShowMessage(1162); 
		}  
		if (pc.getFameLevel() >= 15 && pc.getFameLevel() <= 28
				&& pc.getAccessLevel() <= 10) { //fM【上等兵】
			famename = L1WilliamSystemMessage.ShowMessage(1163); 
		}  
		if (pc.getFameLevel() >= 29 && pc.getFameLevel() <= 56 
				&& pc.getAccessLevel() <= 10) { //fM【少尉】
			famename = L1WilliamSystemMessage.ShowMessage(1164); 
		}  
		if (pc.getFameLevel() >= 57 && pc.getFameLevel() <= 111
				&& pc.getAccessLevel() <= 10) { //fM【中尉】
			famename = L1WilliamSystemMessage.ShowMessage(1165); 
		}  
		if (pc.getFameLevel() >= 112 && pc.getFameLevel() <= 223
				&& pc.getAccessLevel() <= 10) { //fM【上尉】
			famename = L1WilliamSystemMessage.ShowMessage(1166); 
		}  
		if (pc.getFameLevel() >= 224 && pc.getFameLevel() <= 447
				&& pc.getAccessLevel() <= 10) { //fM【少校】
			famename = L1WilliamSystemMessage.ShowMessage(1167); 
		}  
		if (pc.getFameLevel() >= 448 && pc.getFameLevel() <= 859
				&& pc.getAccessLevel() <= 10) { //fM【中校】
			famename = L1WilliamSystemMessage.ShowMessage(1168); 
		}  
		if (pc.getFameLevel() >= 860 && pc.getFameLevel() <= 1791 
				&& pc.getAccessLevel() <= 10) { //fM【上校】
			famename = L1WilliamSystemMessage.ShowMessage(1169); 
		} 
		if (pc.getFameLevel() >= 1792 && pc.getFameLevel() <= 3583
				&& pc.getAccessLevel() <= 10) { //fM【大校】
			famename = L1WilliamSystemMessage.ShowMessage(1170); 
			}
		if (pc.getFameLevel() >= 3584 && pc.getFameLevel() <= 7167
				&& pc.getAccessLevel() <= 10) { //fM【三星少将】
			famename = L1WilliamSystemMessage.ShowMessage(1171); 
			}
		if (pc.getFameLevel() >= 7168 && pc.getFameLevel() <= 14334
				&& pc.getAccessLevel() <= 10) { //fM【四星中将】
			famename = L1WilliamSystemMessage.ShowMessage(1172); 
			}
		if (pc.getFameLevel() >= 14335 && pc.getFameLevel() <= 15000
				&& pc.getAccessLevel() <= 10) { //fM【五星上将】
			famename = L1WilliamSystemMessage.ShowMessage(1173); 
			}
		if (pc.getFameLevel() >= 15001  && pc.getAccessLevel() <= 10) { //fM【三军统帅】
			famename = L1WilliamSystemMessage.ShowMessage(1174); 
			}

		// sosodemon add 称号系统 0-200 BY.SosoDEmoN End
*/
		// グール毒みたいな緑の毒
		// if (pc.isPoison()) {
		// status |= STATUS_POISON;
		// }

		if (pc.isInvisble() || pc.isGmInvis()) {
			status |= STATUS_INVISIBLE;
		}
		if (pc.getBraveSpeed() != 0) { // 2段加速效果
			status |= pc.getBraveSpeed() * 16;
		}

		if (pc.isGhost()) {
			status |= STATUS_GHOST;
		}

		// int addbyte = 0;
		writeC(Opcodes.S_OPCODE_CHARPACK);
		writeH(pc.getX());
		writeH(pc.getY());
		writeD(pc.getId());
		if (pc.isDead()) {
			writeH(pc.getTempCharGfxAtDead());
		} else {
			writeH(pc.getTempCharGfx());
		}
		if (pc.isDead()) {
			writeC(pc.getStatus());
		} else {
			writeC(pc.getCurrentWeapon());
		}
		writeC(pc.getHeading());
		// writeC(addbyte);
		writeC(pc.getOwnLightSize());
		writeC(pc.getMoveSpeed());
		writeD(pc.getExp());
		writeH(pc.getLawful());
		writeS(pc.getName());// + famename);// sosodemon add 称号系统 0-150 BY.SosoDEmoN
		writeS(pc.getTitle());
		writeC(status);
		writeD(pc.getClanid());
		writeS(pc.getClanname()); // クラン名
		writeS(null); // ペッホチング？
		writeC(0); // ？
		if (pc.isInParty()) // パーティー中
		{
			writeC(100 * pc.getCurrentHp() / pc.getMaxHp());
		} else {
			writeC(0xFF);
		}
		if (pc.hasSkillEffect(STATUS_THIRD_SPEED)) {
			writeC(0x08); // 3段加速
		} else {
			writeC(0);
		}
		writeC(0); // PC = 0, Mon = Lv
		writeC(0); // ？
		writeC(0xFF);
		writeC(0xFF);
	}

	@Override
	public byte[] getContent() {
		if (_byte == null) {
			_byte = _bao.toByteArray();
		}
		return _byte;
	}

	@Override
	public String getType() {
		return S_OWN_CHAR_PACK;
	}

}