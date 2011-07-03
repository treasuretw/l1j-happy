package l1j.server.server;

import java.util.concurrent.ConcurrentHashMap;

import l1j.server.server.serverpackets.S_CurseBlind;
import l1j.server.server.serverpackets.S_DeleteCharOK;
import l1j.server.server.serverpackets.S_Disconnect;
import l1j.server.server.serverpackets.S_LoginResult;
import l1j.server.server.serverpackets.S_Message_YN;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.serverpackets.S_Sound;
import l1j.server.server.serverpackets.S_SystemMessage;
import l1j.server.server.serverpackets.ServerBasePacket;

public class PacketCreate {

	/** 此类用于公用封包内容的生成*/
	private static PacketCreate _instance;

	private static ConcurrentHashMap<String, ServerBasePacket> PacketMap;

	static {
		PacketMap = new ConcurrentHashMap<String, ServerBasePacket>();

		PacketMap.put("S_Disconnect", new S_Disconnect());
		PacketMap.put("S_CurseBlind1", new S_CurseBlind(1));
		PacketMap.put("S_CurseBlind2", new S_CurseBlind(2));
		PacketMap.put("S_Sound145", new S_Sound(145));
		PacketMap.put("S_Message_YN322", new S_Message_YN(322, ""));
		PacketMap.put("S_Message_YN729", new S_Message_YN(729, ""));
		PacketMap.put("S_ServerMessage79", new S_ServerMessage(79));
		PacketMap.put("S_ServerMessage276", new S_ServerMessage(276));
		PacketMap.put("S_ServerMessage278", new S_ServerMessage(278));
		PacketMap.put("S_ServerMessage279", new S_ServerMessage(279));
		PacketMap.put("S_ServerMessage280", new S_ServerMessage(280));
		PacketMap.put("S_ServerMessage285", new S_ServerMessage(285));
		PacketMap.put("S_ServerMessage297", new S_ServerMessage(297));
		PacketMap.put("S_ServerMessage299", new S_ServerMessage(299));
		PacketMap.put("S_ServerMessage312", new S_ServerMessage(312));
		PacketMap.put("S_ServerMessage316", new S_ServerMessage(316));
		PacketMap.put("S_ServerMessage319", new S_ServerMessage(319));
		PacketMap.put("S_ServerMessage547", new S_ServerMessage(547));
		PacketMap.put("S_ServerMessage592", new S_ServerMessage(592));
		PacketMap.put("S_ServerMessage647", new S_ServerMessage(647));
		PacketMap.put("S_ServerMessage698", new S_ServerMessage(698));
		PacketMap.put("S_ServerMessage1007", new S_ServerMessage(1007));
		PacketMap.put("S_ServerMessage1101", new S_ServerMessage(1101));
		PacketMap.put("S_ServerMessage1102", new S_ServerMessage(1102));
		PacketMap.put("S_ServerMessage1103", new S_ServerMessage(1103));
		PacketMap.put("S_ServerMessage1137", new S_ServerMessage(1137));
		PacketMap.put("S_ServerMessage1138", new S_ServerMessage(1138));
		PacketMap.put("S_ServerMessage1160", new S_ServerMessage(1160));
		PacketMap.put("S_ServerMessage1162", new S_ServerMessage(1162));
		PacketMap.put("S_ServerMessage1312", new S_ServerMessage(1312));
		PacketMap.put("S_ServerMessage1385", new S_ServerMessage(1385));
		PacketMap.put("S_ServerMessage1412", new S_ServerMessage(1412));
		PacketMap.put("S_ServerMessage1436", new S_ServerMessage(1436));
		PacketMap.put("S_ServerMessage1437", new S_ServerMessage(1437));
		PacketMap.put("S_ServerMessage1438", new S_ServerMessage(1438));
		PacketMap.put("S_ServerMessage1439", new S_ServerMessage(1439));
		PacketMap.put("S_ServerMessage1440", new S_ServerMessage(1440));
		PacketMap.put("S_ServerMessage1441", new S_ServerMessage(1441));
		PacketMap.put("S_ServerMessage1442", new S_ServerMessage(1442));
		PacketMap.put("S_ServerMessage1443", new S_ServerMessage(1443));
		PacketMap.put("S_ServerMessage1444", new S_ServerMessage(1444));
		PacketMap.put("S_ServerMessage1445", new S_ServerMessage(1445));
		PacketMap.put("S_ServerMessage1491", new S_ServerMessage(1491));
		PacketMap.put("S_ServerMessage1542", new S_ServerMessage(1542));
		PacketMap.put("S_ServerMessage1552", new S_ServerMessage(1552));
		PacketMap.put("S_ServerMessage1553", new S_ServerMessage(1553));
		PacketMap.put("S_ServerMessage1554", new S_ServerMessage(1554));
		PacketMap.put("S_ServerMessage1555", new S_ServerMessage(1555));
		PacketMap.put("S_ServerMessage1556", new S_ServerMessage(1556));
//      PacketMap.put("S_LoginResultNameWrong", new S_LoginResult(S_LoginResult.REASON_ACCOUNTNAME_WRONG));
//      PacketMap.put("S_LoginResultAccWrong", new S_LoginResult(S_LoginResult.REASON_ACCOUNT_WRONG));
//      PacketMap.put("S_LoginResultAccBan", new S_LoginResult(S_LoginResult.REASON_ACCOUNT_BAN));
		PacketMap.put("S_LoginResultReasonLoginOk", new S_LoginResult(S_LoginResult.REASON_LOGIN_OK));
		PacketMap.put("S_LoginResultReasonAccountInUse", new S_LoginResult(S_LoginResult.REASON_ACCOUNT_IN_USE));
		PacketMap.put("S_LoginResultReasonAccountAlreadyExists", new S_LoginResult(S_LoginResult.REASON_ACCOUNT_ALREADY_EXISTS));
		PacketMap.put("S_LoginResultReasonAccessFailed", new S_LoginResult(S_LoginResult.REASON_ACCESS_FAILED));
		PacketMap.put("S_LoginResultReasonUserOrPassWrong", new S_LoginResult(S_LoginResult.REASON_USER_OR_PASS_WRONG));
		PacketMap.put("S_LoginResultReasonPassWrong", new S_LoginResult(S_LoginResult.REASON_PASS_WRONG));
//      PacketMap.put("S_LoginResult2Pc", new S_LoginResult(S_LoginResult.REASON_ALLOW_2PC));
//      PacketMap.put("S_LoginResultMaxPlayer", new S_LoginResult(S_LoginResult.REASON_MAX_PLAYER));
		PacketMap.put("S_DeleteCharOK7DAYS", new S_DeleteCharOK(S_DeleteCharOK.DELETE_CHAR_AFTER_7DAYS));
		PacketMap.put("S_DeleteCharOKNOW", new S_DeleteCharOK(S_DeleteCharOK.DELETE_CHAR_NOW));
		PacketMap.put("大脑一片混乱", new S_SystemMessage("大脑一片混乱。"));
		PacketMap.put("你的手骨折了", new S_SystemMessage("你的手骨折了。"));
		PacketMap.put("身体充满了火焰的力量", new S_SystemMessage("身体充满了火焰的力量。"));

		PacketMap.put("使用野狼的嗅觉，找寻目标......", new S_SystemMessage("使用野狼的嗅觉，找寻目标......"));
		PacketMap.put("这里已经没有BOSS的气味，好像晚了一步...", new S_SystemMessage("这里已经没有BOSS的气味，好像晚了一步..."));
		PacketMap.put("除了你，其他角色已回到奇岩村。", new S_SystemMessage("除了你，其他角色已回到奇岩村。"));
		PacketMap.put("您的声望值提高了1点。", new S_SystemMessage("您的声望值提高了1点。"));
		PacketMap.put("您的声望值提高了3点。", new S_SystemMessage("您的声望值提高了3点。"));
		PacketMap.put("您的声望值提高了5点。", new S_SystemMessage("您的声望值提高了5点。"));
		PacketMap.put("您的声望值提高了10点。", new S_SystemMessage("您的声望值提高了10点。"));
		PacketMap.put("您的声望值已到了极限", new S_SystemMessage("您的声望值已到了极限"));

	}

	public static PacketCreate getInstance() {
		if (_instance == null) {
			_instance = new PacketCreate();
		}
		return _instance;
	}

	private PacketCreate() {
	}

	public ServerBasePacket getPacket(String PacketName) {
		return PacketMap.get(PacketName);
	}

}
