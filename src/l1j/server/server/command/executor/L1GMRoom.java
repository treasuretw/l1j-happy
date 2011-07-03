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

import l1j.server.server.GMCommandsConfig;
import l1j.server.server.model.L1Location;
import l1j.server.server.model.L1Teleport;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_SystemMessage;

public class L1GMRoom implements L1CommandExecutor {
	private L1GMRoom() {
	}

	public static L1CommandExecutor getInstance() {
		return new L1GMRoom();
	}

	@Override
	public void execute(L1PcInstance pc, String cmdName, String arg) {
		try {
			int i = 0;
			try {
				i = Integer.parseInt(arg);
			}
			catch (NumberFormatException e) {}

			if (i == 1) {
				L1Teleport.teleport(pc, 32737, 32796, (short) 99, 5, false);
			}
			// 修改为 奇岩城
			else if (i == 2) {
				L1Teleport.teleport(pc, 33445, 32814, (short) 4, 5, false); // 17100!?
			}
			else if (i == 3) {
				L1Teleport.teleport(pc, 32644, 32955, (short) 0, 5, false);
			}
			else if (i == 4) {
				L1Teleport.teleport(pc, 33429, 32814, (short) 4, 5, false);
			}
			else if (i == 5) {
				L1Teleport.teleport(pc, 32894, 32535, (short) 300, 5, false);
			}
			/** 
			 *●●●●新增GM指令●●●●
			 */
			/**●●●●梦幻岛●●●●*/
			else if (i == 3030) {
				L1Teleport.teleport(pc, 32636, 32817, (short) 303, 5, false);
			}
			/**风区平台*/
			else if (i == 3031) {
				L1Teleport.teleport(pc, 32646, 32714, (short) 303, 5, false);
			}
			/**土区平台*/
			else if (i == 3032) {
				L1Teleport.teleport(pc, 32748, 32739, (short) 303, 5, false);
			}
			/**水区平台*/
			else if (i == 3033) {
				L1Teleport.teleport(pc, 32667, 32637, (short) 303, 5, false);
			}
			/**冰区平台*/
			else if (i == 3034) {
				L1Teleport.teleport(pc, 32779, 32627, (short) 303, 5, false);
			}
			/**火区平台*/
			else if (i == 3035) {
				L1Teleport.teleport(pc, 32835, 32760, (short) 303, 5, false);
			}
			/**火区平台下大空地*/
			else if (i == 3036) {
				L1Teleport.teleport(pc, 32792, 32850, (short) 303, 5, false);
			}
			else {
				L1Location loc = GMCommandsConfig.ROOMS.get(arg.toLowerCase());
				if (loc == null) {
					pc.sendPackets(new S_SystemMessage(arg + " 未定义的Room～"));
					return;
				}
				L1Teleport.teleport(pc, loc.getX(), loc.getY(), (short) loc.getMapId(), 5, false);
			}
		}
		catch (Exception exception) {
			pc.sendPackets(new S_SystemMessage("请输入 .gmroom1～.gmroom5 or .gmroom name 。"));
		}
	}
}
