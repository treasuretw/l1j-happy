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
package l1j.william;

import java.util.logging.Logger;

//import l1j.server.server.model.L1Teleport;
//import l1j.server.server.model.Instance.L1ItemInstance;
//import l1j.server.server.model.Instance.L1PcInstance;
//import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.william.SystemMessage;

// Referenced classes of package l1j.server.server.model:
// L1PcInstance

public class L1WilliamSystemMessage {

	@SuppressWarnings("unused")
	private static Logger _log = Logger
			.getLogger(L1WilliamSystemMessage.class.getName());

	private int _id;

	private String _message;

	public L1WilliamSystemMessage(int id, String message) {

		_id = id;
		_message = message;
	}

	public int getId() {
		return _id;
	}

	public String getMessage() {
		return _message;
	}

	public static String ShowMessage(int id) {
		L1WilliamSystemMessage System_Message = SystemMessage.getInstance().getTemplate(id);

		if (System_Message == null) {
			return "";
		}

		String Message = System_Message.getMessage();
		return Message;
	}

}
