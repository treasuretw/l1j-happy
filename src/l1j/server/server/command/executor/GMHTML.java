/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 *
 * http://www.gnu.org/copyleft/gpl.html
 */
package l1j.server.server.command.executor;

import java.util.logging.Level;
import java.util.logging.Logger;

//import l1j.server.server.model.Instance.L1PcInstance;
//import net.l1j.server.serverpackets.S_GMHtml;
import l1j.server.server.serverpackets.S_NPCTalkReturn;
import l1j.server.server.serverpackets.S_SystemMessage;

public class GMHTML implements L1CommandExecutor {
	private static Logger _log = Logger.getLogger(GMHTML.class.getName());

	private GMHTML() {
	}

	public static L1CommandExecutor getInstance() {
		return new GMHTML();
	}

	@Override
	public void execute(l1j.server.server.model.Instance.L1PcInstance pc, String cmdName, String arg) {
		try {
			pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "gmadmin"));
			//pc.sendPackets(new S_GMHtml(pc.getId(),"test"));
		} catch (Exception exception) {
			pc.sendPackets(new S_SystemMessage(cmdName + "  ←请输入。 "));
			_log.log(Level.SEVERE, exception.getLocalizedMessage(), exception);
		}
	}
}
