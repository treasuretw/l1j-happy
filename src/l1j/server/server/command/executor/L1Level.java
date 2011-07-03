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

import java.util.StringTokenizer;

import l1j.server.server.datatables.ExpTable;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_SystemMessage;
import l1j.server.server.utils.IntRange;

public class L1Level implements L1CommandExecutor {
	private L1Level() {
	}

	public static L1CommandExecutor getInstance() {
		return new L1Level();
	}

	// 在线调整玩家等级 by LovieAlice
	@Override
	public void execute(L1PcInstance pc, String cmdName, String arg) {

		try {
			StringTokenizer tok = new StringTokenizer(arg);
			String char_name = tok.nextToken();
            int level = 50; //预设是50级，可自改
            if (tok.hasMoreTokens()) {
            	level = Integer.parseInt(tok.nextToken());
            }

            L1PcInstance target = null;
            if (char_name.equalsIgnoreCase("me")) {
            	target = pc;
            }
            else {
                    target = L1World.getInstance().getPlayer(char_name);
            }

            if (level == target.getLevel()) {
                    return;
            }

            if (!IntRange.includes(level, 1, 99)) {
                    pc.sendPackets(new S_SystemMessage("请在1-99范围内指定"));
                    return;
            }
            target.setExp(ExpTable.getExpByLevel(level));
		}
		catch (Exception e) {
			pc.sendPackets(new S_SystemMessage("请输入 : " + cmdName + " 玩家名字  等级 "));
		}
	}

}
