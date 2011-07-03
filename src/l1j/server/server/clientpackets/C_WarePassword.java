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

import l1j.server.server.Account;
import l1j.server.server.ClientThread;
import l1j.server.server.model.L1Clan;
import l1j.server.server.model.L1Object;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1NpcInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.identity.L1SystemMessageId;
import l1j.server.server.serverpackets.S_RetrieveElfList;
import l1j.server.server.serverpackets.S_RetrieveList;
import l1j.server.server.serverpackets.S_RetrievePledgeList;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.serverpackets.S_SystemMessage;

public class C_WarePassword extends ClientBasePacket {
	public C_WarePassword(byte abyte0[], ClientThread client) {
		super(abyte0);
		// 类型(0: 密码变更, 1: 一般仓库, 2: 血盟仓库)
		int type = readC();

		// 取得第一组数值(旧密码, 或待验证的密码)
		int pass1 = readD();

		// 取得第二组数值(新密码, 或仓库 NPC 的 objId)
		int pass2 = readD();

		// 不明的2个位元组
		readH();

		// 取得角色物件
		L1PcInstance pc = client.getActiveChar();
		Account account = client.getAccount();

		// 变更密码
		if (type == 0) {
			// 两次皆直接跳过密码输入
			if ((pass1 < 0) && (pass2 < 0)) {
				pc.sendPackets(new S_ServerMessage(L1SystemMessageId.$79));
			}

			// 进行新密码的设定
			else if ((pass1 < 0) && (account.getWarePassword() == 0)) {
				// 进行密码变更
				account.changeWarePassword(pass2);
				pc.sendPackets(new S_SystemMessage("仓库密码设定完成，请牢记您的新密码。"));
			}

			// 进行密码变更
			else if ((pass1 > 0) && (pass1 == account.getWarePassword())) {
				// 进行密码变更
				if (pass1 == pass2) {
					// [342::你不能使用旧的密码当作新的密码。请再次输入密码。]
					pc.sendPackets(new S_ServerMessage(L1SystemMessageId.$342));
					return;
				} else if (pass2 > 0) {
					account.changeWarePassword(pass2);
					pc.sendPackets(new S_SystemMessage("仓库密码变更完成，请牢记您的新密码。"));
				} else {
					account.changeWarePassword(0);
					pc.sendPackets(new S_SystemMessage("仓库密码取消完成。"));
				}
			} else {
				// 送出密码错误的提示讯息[835:密码错误。]
				pc.sendPackets(new S_ServerMessage(L1SystemMessageId.$835));
			}
		}

		// 密码验证
		else {
			if (account.getWarePassword() == pass1) {
				int objid = pass2;
				L1Object obj = L1World.getInstance().findObject(objid);
				if (pc.getLevel() >= 5) {// 判断玩家等级
					if (type == 1) {
						if (obj != null) {
							if (obj instanceof L1NpcInstance) {
								L1NpcInstance npc = (L1NpcInstance) obj;
								// 判断npc所属仓库类别
								switch (npc.getNpcId()) {
								case 60028:// 仓库-艾尔(妖森)
									// 密码吻合 输出仓库视窗
									if (pc.isElf())// 判断是否为妖精
										pc.sendPackets(new S_RetrieveElfList(
												objid, pc));
									break;
								default:
									// 密码吻合 输出仓库视窗
									pc.sendPackets(new S_RetrieveList(objid, pc));
									break;
								}
							}
						}
					} else if (type == 2) {
						if (pc.getClanid() == 0) {
							// \f1若想使用血盟仓库，必须加入血盟。
							pc.sendPackets(new S_ServerMessage(L1SystemMessageId.$208));
							return;
						}
						int rank = pc.getClanRank();
						if (rank == L1Clan.CLAN_RANK_PROBATION) {
							// 只有收到称谓的人才能使用血盟仓库。
							pc.sendPackets(new S_ServerMessage(
									L1SystemMessageId.$728));
							return;
						}
						if ((rank != L1Clan.CLAN_RANK_PRINCE)
								&& pc.getTitle().equalsIgnoreCase("")) {
							// 只有收到称谓的人才能使用血盟仓库。
							pc.sendPackets(new S_ServerMessage(
									L1SystemMessageId.$728));
							return;
						}
						pc.sendPackets(new S_RetrievePledgeList(objid, pc));

					}
				}
			} else {
				// 送出密码错误的提示讯息
				pc.sendPackets(new S_ServerMessage(835));
			}
		}
	}
}
