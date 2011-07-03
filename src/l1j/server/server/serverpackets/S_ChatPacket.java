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

import l1j.server.Config;
import l1j.server.server.model.L1Clan;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1PcInstance;

// Referenced classes of package l1j.server.server.serverpackets:
// ServerBasePacket

public class S_ChatPacket extends ServerBasePacket {

	private static final String _S__1F_NORMALCHATPACK = "[S] S_ChatPacket";

	private byte[] _byte = null;

	public S_ChatPacket(L1PcInstance pc, String chat, int opcode, int type) {

		if (type == 0) { // 通常チャット
			writeC(opcode);
			writeC(type);
			if (pc.isInvisble()) {
				writeD(0);
			}
			else {
				writeD(pc.getId());
			}
			writeS(pc.getName() + ": " + chat);
		}
		else if (type == 2) { // 叫び
			writeC(opcode);
			writeC(type);
			if (pc.isInvisble()) {
				writeD(0);
			}
			else {
				writeD(pc.getId());
			}
			writeS("<" + pc.getName() + "> " + chat);
			writeH(pc.getX());
			writeH(pc.getY());
		}
		else if (type == 3) { // 全体聊天
			String clan_name = pc.getClanname();
			L1Clan clan = L1World.getInstance().getClan(clan_name);
			writeC(opcode);
			writeC(type);
			if (pc.isGm() == true) {
				// TODO change GM使用公频(&)显示方式设定
				if (Config.GMTalkShowName) {
					writeS("[*" + pc.getName() + "*] " + chat);
				} else {
					writeS("[******] " + chat);
				}
				writeS("\\fY" + "[******] " + "\\fW" + chat);
			}
			else if (pc.getClanid() != 0) { // 有血盟
				if (pc.getId() == clan.getLeaderId() && clan.getCastleId() == 1) { // TODO 肯特城
					writeS("\\fR" + "肯特城主[" + pc.getName() + "] " + chat);
				} else if (pc.getId() == clan.getLeaderId()
						&& clan.getCastleId() == 2) { // TODO 妖堡
					writeS("\\fB" + "妖魔城主[" + pc.getName() + "] " + chat);
				} else if (pc.getId() == clan.getLeaderId()
						&& clan.getCastleId() == 3) { // TODO 风木
					writeS("\\fU" + "风木城主[" + pc.getName() + "] " + chat);
				} else if (pc.getId() == clan.getLeaderId()
						&& clan.getCastleId() == 4) { // TODO 奇岩
					writeS("\\fT" + "奇岩城主[" + pc.getName() + "] " + chat);
				} else if (pc.getId() == clan.getLeaderId()
						&& clan.getCastleId() == 5) { // TODO 海音
					writeS("\\fW" + "海音城主[" + pc.getName() + "] " + chat);
				} else if (pc.getId() == clan.getLeaderId()
						&& clan.getCastleId() == 6) { // TODO 侏儒
					writeS("\\fJ" + "侏儒城主[" + pc.getName() + "] " + chat);
				} else if (pc.getId() == clan.getLeaderId()
						&& clan.getCastleId() == 7) { // TODO 亚丁
					writeS("\\fG" + "亚丁城主[" + pc.getName() + "] " + chat);
				} else if (pc.getId() == clan.getLeaderId()
						&& clan.getCastleId() == 8) { // TODO 狄亚得要塞
					writeS("\\fO" + "狄亚得要塞城主[" + pc.getName() + "] " + chat);
				} else if (pc.getId() == clan.getLeaderId()
						&& clan.getCastleId() == 0) { // TODO 沒城堡,有血盟
					writeS("\\fS" + pc.getClanname() + "盟主[" + pc.getName()
							+ "] " + chat);
				} else { // TODO 血盟成員
					writeS(pc.getClanname() + "盟[" + pc.getName() + "] " + chat);
				}
			} else { // TODO 不是GM,沒有血盟
				writeS("[" + pc.getName() + "] " + chat);
			}
		}
		else if (type == 4) { // 血盟聊天
			writeC(opcode);
			writeC(type);
			writeS("{" + pc.getName() + "} " + chat);
		}
		else if (type == 9) { // 密语
			writeC(opcode);
			writeC(type);
			writeS("-> (" + pc.getName() + ") " + chat);
		}
		else if (type == 11) { // パーティーチャット
			writeC(opcode);
			writeC(type);
			writeS("(" + pc.getName() + ") " + chat);
		}
		else if (type == 12) { // トレードチャット
			writeC(opcode);
			writeC(type);
			writeS("[" + pc.getName() + "] " + chat);
		}
		else if (type == 13) { // 联盟聊天
			writeC(opcode);
			writeC(type);
			writeS("{{" + pc.getName() + "}} " + chat);
		}
		else if (type == 14) { // チャットパーティー
			writeC(opcode);
			writeC(type);
			if (pc.isInvisble()) {
				writeD(0);
			}
			else {
				writeD(pc.getId());
			}
			writeS("(" + pc.getName() + ") " + chat);
		}
		else if (type == 16) { // ウィスパー
			writeC(opcode);
			writeS(pc.getName());
			writeS(chat);
		}
	}

	@Override
	public byte[] getContent() {
		if (null == _byte) {
			_byte = _bao.toByteArray();
		}
		return _byte;
	}

	@Override
	public String getType() {
		return _S__1F_NORMALCHATPACK;
	}

}