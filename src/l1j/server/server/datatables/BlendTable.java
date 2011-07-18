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
package l1j.server.server.datatables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import l1j.server.L1DatabaseFactory;
import l1j.server.server.utils.SQLUtil;
import l1j.william.L1Blend;

public class BlendTable {
	private static Logger _log = Logger.getLogger(BlendTable.class.getName());

	private static BlendTable _instance;

	private final HashMap<Integer, L1Blend> _itemIdIndex = new HashMap<Integer, L1Blend>();

	public static BlendTable getInstance() {
		if (_instance == null) {
			_instance = new BlendTable();
		}
		return _instance;
	}

	private BlendTable() {
		loadBlendTable();
	}

	private void loadBlendTable() {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {

			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement("SELECT * FROM william_item_blend");
			rs = pstm.executeQuery();
			fillBlendTable(rs);
		} catch (SQLException e) {
			_log.log(Level.SEVERE,
					"error while creating william_item_blend table", e);
		} finally {
			SQLUtil.close(rs);
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
	}

	private void fillBlendTable(ResultSet rs) throws SQLException {
		while (rs.next()) {
			int item_id = rs.getInt("item_id");			// 融合道具
			int checkLevel = rs.getInt("checkLevel");	// 判断等级
			int checkClass = rs.getInt("checkClass");	// 判断职业
			int rnd = rs.getInt("rnd");					// DB设定合成机率
			int checkItem = rs.getInt("checkItem");		// 身上要有何种道具才可执行
			int hpConsume = rs.getInt("hpConsume");		// 判断所需血量
			int mpConsume = rs.getInt("mpConsume");		// 判断所需魔力
			int material = rs.getInt("material");		// 判断合成媒介
			int material_count = rs.getInt("material_count");		// 判断合成媒介 数量
			int material_2 = rs.getInt("material_2");	// 判断合成媒介2
			int material_2_count = rs.getInt("material_2_count");	// 判断合成媒介2 数量
			int material_3 = rs.getInt("material_3");	// 判断合成媒介3
			int material_3_count = rs.getInt("material_3_count");	// 判断合成媒介3 数量
			int new_item = rs.getInt("new_item");		// 合成后的新道具
			int new_item_counts = rs.getInt("new_item_counts");// 新道具的数量
			int new_Enchantlvl_SW = rs.getInt("new_Enchantlvl_SW");	// 新道具(武器或防具)强化值是否随机的开关
																	// 随机:1 固定:0
			int new_item_Enchantlvl = rs.getInt("new_item_Enchantlvl");// 新道具的强化值
			int removeItem = rs.getInt("removeItem");	// 是否删除融合道具
			String message = rs.getString("message");	// 设定显示对话
			int item_Html = rs.getInt("item_Html");		// 设定显示融合所须物品清单

			L1Blend Item_Blend = new L1Blend(item_id, checkLevel, checkClass,
					rnd, checkItem, hpConsume, mpConsume, material,
					material_count, material_2, material_2_count, material_3,
					material_3_count, new_item, new_item_counts,
					new_Enchantlvl_SW, new_item_Enchantlvl, removeItem,
					message, item_Html);
			_itemIdIndex.put(item_id, Item_Blend);
		}
	}

	public L1Blend getTemplate(int itemId) {
		return _itemIdIndex.get(itemId);
	}

}
