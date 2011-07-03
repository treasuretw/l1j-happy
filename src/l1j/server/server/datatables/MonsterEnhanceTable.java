package l1j.server.server.datatables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import l1j.server.L1DatabaseFactory;
import l1j.server.server.model.Instance.L1MonsterEnhanceInstance;
import l1j.server.server.utils.SQLUtil;
import l1j.server.server.utils.collections.Maps;

public class MonsterEnhanceTable {

	private static Logger _log = Logger.getLogger(MonsterEnhanceTable.class
			.getName());

	private final Map<Integer, L1MonsterEnhanceInstance> _meis = Maps.newMap();

	private final boolean _initialized;

	private static MonsterEnhanceTable _instance;

	public static MonsterEnhanceTable getInstance() {
		if (_instance == null) {
			_instance = new MonsterEnhanceTable();
		}
		return _instance;
	}

	public boolean isInitialized() {
		return _initialized;
	}

	private MonsterEnhanceTable() {
		load();
		_initialized = true;
	}

	private void load() {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {

			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement("SELECT * FROM monster_enhance");
			rs = pstm.executeQuery();
			while (rs.next()) {
				L1MonsterEnhanceInstance mei = new L1MonsterEnhanceInstance();
				int npcid = rs.getInt("npcid");

				mei.setNpcId(npcid);
				mei.setCurrentDc(rs.getInt("current_dc"));
//				mei.setCurrentDc(rs.getInt(0));		// 修改强化伺服器重开后重新计算
				mei.setDcEnhance(rs.getInt("dc_enhance"));
				mei.setLevel(rs.getInt("level"));	// 等级
				mei.setHp(rs.getInt("hp"));			// 血
				mei.setMp(rs.getInt("mp"));			// 魔
				mei.setAc(rs.getInt("ac"));			// 防御
				mei.setStr(rs.getInt("str"));		// 力量
				mei.setDex(rs.getInt("dex"));		// 敏捷
				mei.setCon(rs.getInt("con"));		// 体质
				mei.setWis(rs.getInt("wis"));		// 精神
				mei.setInt(rs.getInt("int"));		// 智力
				mei.setMr(rs.getInt("mr"));			// 魔防

				_meis.put(npcid, mei);

			}
		} catch (SQLException e) {
			_log.log(Level.SEVERE,
					"error while creating monster_enhance table", e);
		} finally {
			SQLUtil.close(rs);
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
	}

	public void save(L1MonsterEnhanceInstance mei) {
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement("UPDATE monster_enhance SET current_dc= ? WHERE npcid=?");
			pstm.setInt(1, mei.getCurrentDc());
			pstm.setInt(2, mei.getNpcId());
			pstm.execute();
		} catch (Exception e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
	}

	public L1MonsterEnhanceInstance getTemplate(int npcId) {
		return _meis.get(npcId);
	}
}
