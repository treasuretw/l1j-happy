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

import java.util.List;

import l1j.server.server.ClientThread;
import l1j.server.server.WarTimeController;
import l1j.server.server.model.L1CastleLocation;
import l1j.server.server.model.L1Clan;
import l1j.server.server.model.L1War;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_Message_YN;
import l1j.server.server.serverpackets.S_ServerMessage;

// Referenced classes of package l1j.server.server.clientpackets:
// ClientBasePacket

/**
 * TODO 翻译，好多 处理收到由客户端传来盟战的封包
 */
public class C_War extends ClientBasePacket {

	private static final String C_WAR = "[C] C_War";

	public C_War(byte abyte0[], ClientThread clientthread) throws Exception {
		super(abyte0);
		int type = readC();
		String s = readS();

		L1PcInstance player = clientthread.getActiveChar();
		String playerName = player.getName();
		String clanName = player.getClanname();
		int clanId = player.getClanid();

		if (!player.isCrown()) { // 不是王族
			player.sendPackets(new S_ServerMessage(478)); // \f1プリンスとプリンセスのみ战争を布告できます。
			return;
		}
		if (clanId == 0) { // 没有血盟
			player.sendPackets(new S_ServerMessage(272)); // \f1战争するためにはまず血盟を创设しなければなりません。
			return;
		}
		L1Clan clan = L1World.getInstance().getClan(clanName);
		if (clan == null) { // 找不到血盟
			return;
		}

		if (player.getId() != clan.getLeaderId()) { // 血盟主
			player.sendPackets(new S_ServerMessage(478)); // \f1プリンスとプリンセスのみ战争を布告できます。
			return;
		}

		if (clanName.toLowerCase().equals(s.toLowerCase())) { // 自クランを指定
			return;
		}

		L1Clan enemyClan = null;
		String enemyClanName = null;
		for (L1Clan checkClan : L1World.getInstance().getAllClans()) { // 取得所有的血盟
			if (checkClan.getClanName().toLowerCase().equals(s.toLowerCase())) {
				enemyClan = checkClan;
				enemyClanName = checkClan.getClanName();
				break;
			}
		}
		if (enemyClan == null) { // 相手のクランが见つからなかった
			return;
		}

		boolean inWar = false;
		List<L1War> warList = L1World.getInstance().getWarList(); // 取得所有的盟战
		for (L1War war : warList) {
			if (war.CheckClanInWar(clanName)) { // 检查是否在盟战中
				if (type == 0) { // 宣战公告
					player.sendPackets(new S_ServerMessage(234)); // \f1あなたの血盟はすでに战争中です。
					return;
				}
				inWar = true;
				break;
			}
		}
		if (!inWar && ((type == 2) || (type == 3))) { // 自クランが战争中以外で、降伏または终结
			return;
		}

		if (clan.getCastleId() != 0) { // 有城堡
			if (type == 0) { // 宣战公告
				player.sendPackets(new S_ServerMessage(474)); // あなたはすでに城を所有しているので、他の城を取ることは出来ません。
				return;
			}
			else if ((type == 2) || (type == 3)) { // 投降、或是结束
				return;
			}
		}

		if ((enemyClan.getCastleId() == 0) && // 对方王族等级低于15
				(player.getLevel() <= 15)) {
			player.sendPackets(new S_ServerMessage(232)); // \f1レベル15以下の君主は宣战布告できません。
			return;
		}

		if ((enemyClan.getCastleId() != 0) && // 相手クランが城主で、自キャラがLv25未满
				(player.getLevel() < 25)) {
			player.sendPackets(new S_ServerMessage(475)); // 攻城战を宣言するにはレベル25に达していなければなりません。
			return;
		}

		if (enemyClan.getCastleId() != 0) { // 相手クランが城主
			int castle_id = enemyClan.getCastleId();
			if (WarTimeController.getInstance().isNowWar(castle_id)) { // 战争时间内
				L1PcInstance clanMember[] = clan.getOnlineClanMember();
				for (L1PcInstance element : clanMember) {
					if (L1CastleLocation.checkInWarArea(castle_id, element)) {
						player.sendPackets(new S_ServerMessage(477)); // あなたを含む全ての血盟员が城の外に出なければ攻城战は宣言できません。
						return;
					}
				}
				boolean enemyInWar = false;
				for (L1War war : warList) {
					if (war.CheckClanInWar(enemyClanName)) { // 相手クランが既に战争中
						if (type == 0) { // 宣战布告
							war.DeclareWar(clanName, enemyClanName);
							war.AddAttackClan(clanName);
						}
						else if ((type == 2) || (type == 3)) {
							if (!war.CheckClanInSameWar(clanName, enemyClanName)) { // 自クランと相手クランが别の战争
								return;
							}
							if (type == 2) { // 降伏
								war.SurrenderWar(clanName, enemyClanName);
							}
							else if (type == 3) { // 终结
								war.CeaseWar(clanName, enemyClanName);
							}
						}
						enemyInWar = true;
						break;
					}
				}
				if (!enemyInWar && (type == 0)) { // 相手クランが战争中以外で、宣战布告
					L1War war = new L1War();
					war.handleCommands(1, clanName, enemyClanName); // 攻城战开始
				}
			}
			else { // 战争时间外
				if (type == 0) { // 宣战布告
					player.sendPackets(new S_ServerMessage(476)); // まだ攻城战の时间ではありません。
				}
			}
		}
		else { // 相手クランが城主ではない
			boolean enemyInWar = false;
			for (L1War war : warList) {
				if (war.CheckClanInWar(enemyClanName)) { // 相手クランが既に战争中
					if (type == 0) { // 宣战布告
						player.sendPackets(new S_ServerMessage(236, enemyClanName)); // %0血盟があなたの血盟との战争を拒绝しました。
						return;
					}
					else if ((type == 2) || (type == 3)) { // 降伏または终结
						if (!war.CheckClanInSameWar(clanName, enemyClanName)) { // 自クランと相手クランが别の战争
							return;
						}
					}
					enemyInWar = true;
					break;
				}
			}
			if (!enemyInWar && ((type == 2) || (type == 3))) { // 相手クランが战争中以外で、降伏または终结
				return;
			}

			// 攻城战ではない场合、相手の血盟主の承认が必要
			L1PcInstance enemyLeader = L1World.getInstance().getPlayer(enemyClan.getLeaderName());

			if (enemyLeader == null) { // 相手の血盟主が见つからなかった
				player.sendPackets(new S_ServerMessage(218, enemyClanName)); // \f1%0血盟の君主は现在ワールドに居ません。
				return;
			}

			if (type == 0) { // 宣战布告
				enemyLeader.setTempID(player.getId()); // 相手のオブジェクトIDを保存しておく
				enemyLeader.sendPackets(new S_Message_YN(217, clanName, playerName)); // %0血盟の%1があなたの血盟との战争を望んでいます。战争に应じますか？（Y/N）
			}
			else if (type == 2) { // 降伏
				enemyLeader.setTempID(player.getId()); // 相手のオブジェクトIDを保存しておく
				enemyLeader.sendPackets(new S_Message_YN(221, clanName)); // %0血盟が降伏を望んでいます。受け入れますか？（Y/N）
			}
			else if (type == 3) { // 终结
				enemyLeader.setTempID(player.getId()); // 相手のオブジェクトIDを保存しておく
				enemyLeader.sendPackets(new S_Message_YN(222, clanName)); // %0血盟が战争の终结を望んでいます。终结しますか？（Y/N）
			}
		}
	}

	@Override
	public String getType() {
		return C_WAR;
	}

}
