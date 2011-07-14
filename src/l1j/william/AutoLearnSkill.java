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
package l1j.william;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.sql.*;

import l1j.server.L1DatabaseFactory;
import l1j.server.Server;
import l1j.server.server.datatables.SkillsTable;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_AddSkill;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.serverpackets.S_SkillSound;
import l1j.server.server.templates.L1Skills;

 /**
  *	自动学习技能
  */
public class AutoLearnSkill {
	private static ArrayList<ArrayList<Object>> aData = new ArrayList<ArrayList<Object>>();
	private static boolean NO_MORE_GET_DATA = false;
	public static final String TOKEN = ",";

	public static void main(String a[]) {
		while (true) {
			try {
				Server.main(null);
			} catch (Exception ex) {
			}
		}
	}

	private AutoLearnSkill() {
	}

	public static void forAutoLearnSkill(L1PcInstance pc) {
		ArrayList<?> aTempData = null;
		if (!NO_MORE_GET_DATA) {
			NO_MORE_GET_DATA = true;
			getData();
		}
		for (int i = 0; i < aData.size(); i++) {
			aTempData = (ArrayList<?>) aData.get(i);
			if (pc.getLevel() >= ((Integer) aTempData.get(0)).intValue()) {
				int[] id = (int[]) aTempData.get(1);
				// 王族
				if (((Integer) aTempData.get(2)).intValue() == 0
						&& pc.isCrown()) {
					for (int l = 0; l < id.length; l++) {
						int skillid = id[l];
						Skill(pc, skillid);
					}
				}
				// 骑士
				else if (((Integer) aTempData.get(2)).intValue() == 1
						&& pc.isKnight()) {
					for (int l = 0; l < id.length; l++) {
						int skillid = id[l];
						Skill(pc, skillid);
					}
				}
				// 法师
				else if (((Integer) aTempData.get(2)).intValue() == 2
						&& pc.isWizard()) {
					for (int l = 0; l < id.length; l++) {
						int skillid = id[l];
						Skill(pc, skillid);
					}
				}
				// 妖精
				else if (((Integer) aTempData.get(2)).intValue() == 3
						&& pc.isElf()) {
					for (int l = 0; l < id.length; l++) {
						int skillid = id[l];
						Skill(pc, skillid);
					}
				}
				// 黑暗妖精
				else if (((Integer) aTempData.get(2)).intValue() == 4
						&& pc.isDarkelf()) {
					for (int l = 0; l < id.length; l++) {
						int skillid = id[l];
						Skill(pc, skillid);
					}
				}
				// 龙骑士
				else if (((Integer) aTempData.get(2)).intValue() == 5
						&& pc.isDragonKnight()) {
					for (int l = 0; l < id.length; l++) {
						int skillid = id[l];
						Skill(pc, skillid);
					}
				}
				// 幻术士
				else if (((Integer) aTempData.get(2)).intValue() == 6
						&& pc.isIllusionist()) {
					for (int l = 0; l < id.length; l++) {
						int skillid = id[l];
						Skill(pc, skillid);
					}
				}
			}
		}
	}

	private static void getData() {
		java.sql.Connection con = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			Statement stat = con.createStatement();
			ResultSet rset = stat
					.executeQuery("SELECT * FROM william_autoaddskill");
			ArrayList<Object> aReturn = null;
			if (rset != null)
				while (rset.next()) {
					aReturn = new ArrayList<Object>();
					aReturn.add(0, new Integer(rset.getInt("Level")));
					if (rset.getString("SkillId") != null
							&& !rset.getString("SkillId").equals("")
							&& !rset.getString("SkillId").equals("0"))
						aReturn.add(1,
								getArray(rset.getString("SkillId"), TOKEN, 1));
					else
						aReturn.add(1, null);
					aReturn.add(2, new Integer(rset.getInt("Class")));
					aData.add(aReturn);
				}
			if (con != null && !con.isClosed())
				con.close();
		} catch (Exception ex) {
		}
	}

	private static void Skill(L1PcInstance pc, int skillid) {
		if (pc.isSkillMastery(skillid)) {
			return;
		}
		String s = "";
		int i = 0;
		int j = 0;
		int k = 0;
		int l = 0;
		int i1 = 0;
		int j1 = 0;
		int k1 = 0;
		int l1 = 0;
		int i2 = 0;
		int j2 = 0;
		int k2 = 0;
		int l2 = 0;
		int i3 = 0;
		int j3 = 0;
		int k3 = 0;
		int l3 = 0;
		int i4 = 0;
		int j4 = 0;
		int k4 = 0;
		int l4 = 0;
		int i5 = 0;
		int j5 = 0;
		int k5 = 0;
		int l5 = 0;
		int i6 = 0;
		int i8 = 0;
		int j8 = 0;
		int k8 = 0;
		int l8 = 0;
		L1Skills l1skills = SkillsTable.getInstance().getTemplate(skillid);
		int l6 = l1skills.getSkillLevel();
		int i7 = l1skills.getId();
		s = l1skills.getName();
		i = l1skills.getSkillId();
		switch (l6) {
		case 1: // '\001'
			j = i7;
			break;
		case 2: // '\002'
			k = i7;
			break;
		case 3: // '\003'
			l = i7;
			break;
		case 4: // '\004'
			i1 = i7;
			break;
		case 5: // '\005'
			j1 = i7;
			break;
		case 6: // '\006'
			k1 = i7;
			break;
		case 7: // '\007'
			l1 = i7;
			break;
		case 8: // '\b'
			i2 = i7;
			break;
		case 9: // '\t'
			j2 = i7;
			break;
		case 10: // '\n'
			k2 = i7;
			break;
		case 11: // '\013'
			l2 = i7;
			break;
		case 12: // '\f'
			i3 = i7;
			break;
		case 13: // '\r'
			j3 = i7;
			break;
		case 14: // '\016'
			k3 = i7;
			break;
		case 15: // '\017'
			l3 = i7;
			break;
		case 16: // '\020'
			i4 = i7;
			break;
		case 17: // '\021'
			j4 = i7;
			break;
		case 18: // '\022'
			k4 = i7;
			break;
		case 19: // '\023'
			l4 = i7;
			break;
		case 20: // '\024'
			i5 = i7;
			break;
		case 21: // '\025'
			j5 = i7;
			break;
		case 22: // '\026'
			k5 = i7;
			break;
		case 23: // '\027'
			l5 = i7;
			break;
		case 24: // '\030'
			i6 = i7;
			break;
		case 25: // '\031'
			j8 = i7;
			break;
		case 26: // '\032'
			k8 = i7;
			break;
		case 27: // '\033'
			l8 = i7;
			break;
		case 28: // '\034'
			i8 = i7;
			break;
		}
		int k6 = pc.getId();
		pc.sendPackets(new S_AddSkill(j, k, l, i1, j1, k1, l1, i2, j2, k2, l2,
				i3, j3, k3, l3, i4, j4, k4, l4, i5, j5, k5, l5, i6, j8, k8, l8,
				i8));
		S_SkillSound s_skillSound = new S_SkillSound(k6, 224);
		pc.sendPackets(s_skillSound);
		pc.broadcastPacket(s_skillSound);
		SkillsTable.getInstance().spellMastery(k6, i, s, 0, 0);
		pc.sendPackets(new S_ServerMessage(166, "学习了技能(" + s + ")"));
	}

	// 复数文字
	private static Object getArray(String s, String sToken, int iType) {
		StringTokenizer st = new StringTokenizer(s, sToken);
		int iSize = st.countTokens();
		String sTemp = null;
		if (iType == 1) { // int
			int[] iReturn = new int[iSize];
			for (int i = 0; i < iSize; i++) {
				sTemp = st.nextToken();
				iReturn[i] = Integer.parseInt(sTemp);
			}
			return iReturn;
		}
		if (iType == 2) { // String
			String[] sReturn = new String[iSize];
			for (int i = 0; i < iSize; i++) {
				sTemp = st.nextToken();
				sReturn[i] = sTemp;
			}
			return sReturn;
		}
		if (iType == 3) { // String
			String sReturn = null;
			for (int i = 0; i < iSize; i++) {
				sTemp = st.nextToken();
				sReturn = sTemp;
			}
			return sReturn;
		}
		return null;
	}
	// 复数文字 end

}
