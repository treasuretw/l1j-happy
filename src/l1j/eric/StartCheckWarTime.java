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
package l1j.eric;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import l1j.server.L1DatabaseFactory;
import l1j.server.server.utils.SQLUtil;

public final class StartCheckWarTime {

	private class Data {
		@SuppressWarnings("unused")
		public int _castle_id = 0;

		@SuppressWarnings("unused")
		public String _name = "";

		public boolean _isActive = true;
	}

	private static Logger _log = Logger.getLogger(StartCheckWarTime.class
			.getName());

	private static StartCheckWarTime _instance;

	private final Map<Integer, Data> _check = new HashMap<Integer, Data>();

	private StartCheckWarTime() {
		loadStartCheckWarTimeFromDatabase();
	}

	private void loadStartCheckWarTimeFromDatabase() {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement("SELECT * FROM eric_startcheckwartime");
			rs = pstm.executeQuery();
			while (rs.next()) {
				Data data = new Data();
				int id = rs.getInt("castle_id");
				data._castle_id = id;
				data._name = rs.getString("name");
				data._isActive = rs.getBoolean("isActive");
				_check.put(new Integer(id), data);
			}
			_log.config("StartCheckWarTime " + _check.size());
		} catch (SQLException e) {
			e.printStackTrace();
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			SQLUtil.close(rs);
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
	}

	public static StartCheckWarTime getInstance() {
		if (_instance == null) {
			_instance = new StartCheckWarTime();
		}
		return _instance;
	}

	public boolean isActive(int castleId) {
		Data data = _check.get(castleId);
		if (data == null) {
			return true;
		}
		return _check.get(castleId)._isActive;
	}
}
