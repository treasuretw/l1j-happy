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
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import l1j.server.Config;
import l1j.server.L1DatabaseFactory;
import l1j.server.server.model.shop.L1Shop;
import l1j.server.server.templates.L1ShopItem;
import l1j.server.server.utils.SQLUtil;
import l1j.server.server.utils.collections.Lists;
import l1j.server.server.utils.collections.Maps;

public class ShopTable {

	private static final long serialVersionUID = 1L;

	private static Logger _log = Logger.getLogger(ShopTable.class.getName());

	private static ShopTable _instance;

	private final Map<Integer, L1Shop> _allShops = Maps.newMap();

	private final Map<Integer, Integer> _allItemSells = Maps.newMap();// 元宝商城 

	public static ShopTable getInstance() {
		if (_instance == null) {
			_instance = new ShopTable();
		}
		return _instance;
	}

	private ShopTable() {
		loadShopAllSell();// 修正新所有东西贩卖
		loadShops();
	}

	private List<Integer> enumNpcIds() {
		List<Integer> ids = Lists.newList();

		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement("SELECT DISTINCT npc_id FROM shop");
			rs = pstm.executeQuery();
			while (rs.next()) {
				ids.add(rs.getInt("npc_id"));
			}
		}
		catch (SQLException e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
		finally {
			SQLUtil.close(rs, pstm, con);
		}
		return ids;
	}

	// 修正新所有东西贩卖 add
	private void loadShopAllSell() {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con
					.prepareStatement("SELECT * FROM shop_sell_price ORDER BY item_id");
			rs = pstm.executeQuery();
			while (rs.next()) {
				int itemId = rs.getInt("item_id");
				// String name = rs.getString("name");
				int price = rs.getInt("sell_price");

				// 修正贩卖价钱错误问题 add
				if (Config.AllSell1) {
				if (price >= 1) {
					Connection conI = null;
					PreparedStatement pstmI = null;
					ResultSet rsI = null;
					try {
						conI = L1DatabaseFactory.getInstance().getConnection();
						pstmI = conI.prepareStatement("SELECT * FROM shop WHERE item_id=&#39;"+ itemId+"&#39;");
						rsI = pstmI.executeQuery();
						while (rsI.next()) {
							if (rsI.getInt("selling_price") >= 1 && price > rsI.getInt("selling_price")) {
								System.out.println("NpcId="+rsI.getInt("npc_id")+", ItemID="+itemId+", PriceError!!!");
								price = -1;
							}
						}
						rsI.close();
					} catch (SQLException e) {
						_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
					} finally {
						SQLUtil.close(rsI, pstmI, conI);
					}
				}
				}
				// 修正贩卖价钱错误问题 end

				if (price >= 1) {
					_allItemSells.put(itemId, price);
				}
			}
		} catch (SQLException e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			SQLUtil.close(rs, pstm, con);
		}
	}
	// 修正新所有东西贩卖 end

	private L1Shop loadShop(int npcId, ResultSet rs) throws SQLException {
		List<L1ShopItem> sellingList = Lists.newList();
		List<L1ShopItem> purchasingList = Lists.newList();
		while (rs.next()) {
			int itemId = rs.getInt("item_id");
			int sellingPrice = rs.getInt("selling_price");
			int purchasingPrice = rs.getInt("purchasing_price");
			int packCount = rs.getInt("pack_count");
			packCount = packCount == 0 ? 1 : packCount;
			int EnchantLevel = rs.getInt("EnchantLevel");//商店直接卖+几武器
			//元宝商城 add
			int YB = rs.getInt("YB");
			switch(npcId){
			case 70068:case 70020:
			case 70024:case 70032:
			case 80075:
				continue;
			default:
				break;
			}
			if(0 <= sellingPrice) {
				if(_allItemSells.get(itemId)==null) {
					_allItemSells.put(itemId, sellingPrice/2);
				} else if(_allItemSells.get(itemId)<sellingPrice/2) {
					_allItemSells.put(itemId, sellingPrice/2);
				}
			} else {
				if(0 <= purchasingPrice) {
					if(_allItemSells.get(itemId)==null) {
						_allItemSells.put(itemId, purchasingPrice);
					} else if(_allItemSells.get(itemId)<purchasingPrice) {
						_allItemSells.put(itemId, purchasingPrice);
					}
				}
			}
			if (0 <= sellingPrice && 0 >= YB ){
				L1ShopItem item = new L1ShopItem(itemId, sellingPrice, packCount, EnchantLevel);//商店直接卖+几武器
				sellingList.add(item);
			}
			if (0 <= purchasingPrice && 0 >= YB ) {
				L1ShopItem item = new L1ShopItem(itemId, purchasingPrice, packCount, EnchantLevel);//商店直接卖+几武器
				purchasingList.add(item);
			}
			if (0 < YB ) {
				L1ShopItem item = new L1ShopItem(itemId, YB,packCount, EnchantLevel);//商店直接卖+几武器
				sellingList.add(item);
			}//元宝商城 end
		}
		return new L1Shop(npcId, sellingList, purchasingList);
	}

	private void loadShops() {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement("SELECT * FROM shop WHERE npc_id=? ORDER BY order_id");
			for (int npcId : enumNpcIds()) {
				pstm.setInt(1, npcId);
				rs = pstm.executeQuery();
				L1Shop shop = loadShop(npcId, rs);
				_allShops.put(npcId, shop);
				rs.close();
			}
		}
		catch (SQLException e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
		finally {
			SQLUtil.close(rs, pstm, con);
		}
	}

	// 新所有物品贩卖 by eric1300460 add
	public int getItemPrice(int id) {
		if (_allItemSells.get(id) == null) {
			return 0;
		} else {
			return _allItemSells.get(id);
		}
	}
	// 新所有物品贩卖 by eric1300460 end

	public L1Shop get(int npcId) {
		return _allShops.get(npcId);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
