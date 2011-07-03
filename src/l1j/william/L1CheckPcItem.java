package l1j.william;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import l1j.server.L1DatabaseFactory;
import l1j.server.server.model.Instance.L1ItemInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.utils.SQLUtil;

/**
 *	利用外挂复制道具的处罚
 */

public class L1CheckPcItem {

	private int itemId;

	private boolean isStackable = false;

	public L1CheckPcItem() {
	}

	public boolean checkPcItem(L1ItemInstance item, L1PcInstance pc) {

		itemId = item.getItem().getItemId();
		int itemCount = item.getCount();
		boolean isCheat = false;

		if ((findWeapon() || findArmor()) && itemCount != 1) {
			isCheat = true;
		}
		else if (findEtcitem()) {

			// 不可堆叠的道具同一格不等于1时设定为作弊
			if (!isStackable && itemCount != 1) {
				isCheat = true;
			}

			// 金币大于20亿以及金币负值设定为作弊
			else if (itemId == 40308 && (itemCount > 2000000000 || itemCount < 0)) {
				isCheat = true;
			}

			// 可堆叠道具(金币与元宝除外)堆叠超过十万个以及堆叠负值设定为作弊
			else if (isStackable && itemId != 40308 && itemId != 5000 && (itemCount > 100000 || itemCount < 0)) {
				isCheat = true;
			}
		}
		if (isCheat) {

			// 作弊成立则删除道具
			pc.getInventory().removeItem(item, itemCount);
		}
		return isCheat;
	}

	private boolean findWeapon() {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		boolean inWeapon = false;

		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement("SELECT * FROM weapon WHERE item_id = ?");
			pstm.setInt(1, itemId);
			rs = pstm.executeQuery();
			if (rs != null) {
				if (rs.next()) {
					inWeapon = true;
				}
			}
		} catch (Exception e) {
		} finally {
			SQLUtil.close(rs);
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
		return inWeapon;
	}

	private boolean findArmor() {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		boolean inArmor = false;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement("SELECT * FROM armor WHERE item_id = ?");
			pstm.setInt(1, itemId);
			rs = pstm.executeQuery();
			if (rs != null) {
				if (rs.next()) {
					inArmor = true;
				}
			}
		} catch (Exception e) {
		} finally {
			SQLUtil.close(rs);
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
		return inArmor;
	}

	private boolean findEtcitem() {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		boolean inEtcitem = false;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement("SELECT * FROM etcitem WHERE item_id = ?");
			pstm.setInt(1, itemId);
			rs = pstm.executeQuery();
			if (rs != null) {
				if (rs.next()) {
					inEtcitem = true;
					isStackable = rs.getInt("stackable") == 1 ? true : false;
				}
			}
		} catch (Exception e) {
		} finally {
			SQLUtil.close(rs);
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
		return inEtcitem;
	}

}
