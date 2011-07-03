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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import l1j.server.L1DatabaseFactory;
import l1j.server.server.utils.SQLUtil;
import l1j.william.L1WilliamSystemMessage;

public class SystemMessage {

	private static Logger _log = Logger.getLogger(SystemMessage.class.getName());

	private static SystemMessage _instance;

	private final HashMap<Integer, L1WilliamSystemMessage> _itemIdIndex
			= new HashMap<Integer, L1WilliamSystemMessage>();

	public static SystemMessage getInstance() {

		if (_instance == null) {
			_instance = new SystemMessage();
		}
		return _instance;
	}

	private SystemMessage() {
		loadSystemMessage();
	}

	private void loadSystemMessage() {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {

			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement("SELECT * FROM william_system_message");
			rs = pstm.executeQuery();
			fillSystemMessage(rs);
		} catch (SQLException e) {
			_log.log(Level.SEVERE, "error while creating william_system_message table",
					e);
		} finally {
			SQLUtil.close(rs);
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
	}

	private void fillSystemMessage(ResultSet rs) throws SQLException {
		while (rs.next()) {
			int Id = rs.getInt("id");
			String Message = rs.getString("message");
			L1WilliamSystemMessage System_Message = new L1WilliamSystemMessage(Id, Message);
			_itemIdIndex.put(Id, System_Message);
		}
	}

	public L1WilliamSystemMessage getTemplate(int Id) {
		return _itemIdIndex.get(Id);
	}

}
