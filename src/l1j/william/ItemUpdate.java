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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import l1j.server.L1DatabaseFactory;
import l1j.server.server.model.Instance.L1ItemInstance;
import l1j.server.server.utils.SQLUtil;

// Referenced classes of package l1j.server.server:
// IdFactory

public class ItemUpdate {

	private static Logger _log = Logger.getLogger(ItemUpdate.class.getName());

	private static ItemUpdate _instance;

	private final Map<Integer, L1WilliamItemUpdate> _itemUpdate = new ConcurrentHashMap<Integer, L1WilliamItemUpdate>();

	public ItemUpdate() {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement("SELECT * FROM william_item_update ORDER BY item_id");
			rs = pstm.executeQuery();
			while (rs.next()) {
				L1WilliamItemUpdate itemUpdate = new L1WilliamItemUpdate();
				itemUpdate.setId(rs.getInt(1));
				itemUpdate.setCount(rs.getInt(2));
				itemUpdate.setAddDmg(rs.getInt(3));
				itemUpdate.setAddDmgModifier(rs.getInt(4));
				itemUpdate.setAddHitModifier(rs.getInt(5));
				itemUpdate.setAddStr(rs.getInt(6));
				itemUpdate.setAddDex(rs.getInt(7));
				itemUpdate.setAddInt(rs.getInt(8));
				itemUpdate.setAddHp(rs.getInt(9));
				itemUpdate.setAddMp(rs.getInt(10));

				_itemUpdate.put(itemUpdate.getId(), itemUpdate);
			}
		} catch (SQLException e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			SQLUtil.close(rs);
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
	}

	public static ItemUpdate getInstance() {
		if (_instance == null) {
			_instance = new ItemUpdate();
		}
		return _instance;
	}

	public void storeItem(L1ItemInstance item) {
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement("INSERT INTO william_item_update SET item_id=?, count=?, add_dmg=?, add_dmgmodifier=?, add_hitmodifier=?, add_str=?, add_dex=?, add_int=?, add_hp=?, add_mp=?");
			pstm.setInt(1, item.getId());
			pstm.setInt(2, item.getUpdateCount());
			pstm.setInt(3, item.getUpdateDmg());
			pstm.setInt(4, item.getUpdateDmgModifier());
			pstm.setInt(5, item.getUpdateHitModifier());
			pstm.setInt(6, item.getUpdateStr());
			pstm.setInt(7, item.getUpdateDex());
			pstm.setInt(8, item.getUpdateInt());
			pstm.setInt(9, item.getUpdateHp());
			pstm.setInt(10, item.getUpdateMp());

			pstm.execute();
		} catch (SQLException e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
	}

	public void updateItem(L1ItemInstance item) {
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement("UPDATE william_item_update SET count=?, add_dmg=?, add_dmgmodifier=?, add_hitmodifier=?, add_str=?, add_dex=?, add_int=?, add_hp=?, add_mp=? WHERE item_id=?");
			pstm.setInt(1, item.getUpdateCount());
			pstm.setInt(2, item.getUpdateDmg());
			pstm.setInt(3, item.getUpdateDmgModifier());
			pstm.setInt(4, item.getUpdateHitModifier());
			pstm.setInt(5, item.getUpdateStr());
			pstm.setInt(6, item.getUpdateDex());
			pstm.setInt(7, item.getUpdateInt());
			pstm.setInt(8, item.getUpdateHp());
			pstm.setInt(9, item.getUpdateMp());
			pstm.setInt(10, item.getId());

			pstm.execute();
		} catch (SQLException e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
	}

	public int checkItem(int objectId) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement("SELECT count(*) as cnt FROM william_item_update WHERE item_id=?");
			pstm.setInt(1, objectId);
			rs = pstm.executeQuery();
			if (rs.next()) {
				result = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			SQLUtil.close(rs);
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
		return result;
	}

	public L1WilliamItemUpdate[] getItemUpdateList() {
		return _itemUpdate.values().toArray(new L1WilliamItemUpdate[_itemUpdate.size()]);
	}

}
