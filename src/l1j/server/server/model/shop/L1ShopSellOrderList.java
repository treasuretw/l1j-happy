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
package l1j.server.server.model.shop;

import java.util.List;

import l1j.server.Config;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.utils.collections.Lists;

class L1ShopSellOrder {
	private final L1AssessedItem _item;

	private final int _count;

	public L1ShopSellOrder(L1AssessedItem item, int count) {
		_item = item;
		_count = count;
	}

	public L1AssessedItem getItem() {
		return _item;
	}

	public int getCount() {
		return _count;
	}

}

public class L1ShopSellOrderList {
	private final L1Shop _shop;

	private final L1PcInstance _pc;

	private final List<L1ShopSellOrder> _list = Lists.newList();

	L1ShopSellOrderList(L1Shop shop, L1PcInstance pc) {
		_shop = shop;
		_pc = pc;
	}

	public void add(int itemObjectId, int count) {

		// 新所有物品贩卖 by eric1300460
		L1AssessedItem assessedItem;
		if (Config.AllSell) {
			assessedItem = _shop.allAssessItem(_pc.getInventory().getItem(
					itemObjectId));
		} else {
			assessedItem = _shop.assessItem(_pc.getInventory().getItem(
					itemObjectId));
		}
		// 新所有物品贩卖 by eric1300460

		if (assessedItem == null) {
			/*
			 * 買取リストに無いアイテムが指定された。 不正パケの可能性。
			 */
			throw new IllegalArgumentException();
		}

		// TODO 修正新所有东西贩卖
		if (assessedItem.getAssessedPrice() > 0) {
			_list.add(new L1ShopSellOrder(assessedItem, count));
		}
		// TODO 修正新所有东西贩卖
	}

	L1PcInstance getPc() {
		return _pc;
	}

	List<L1ShopSellOrder> getList() {
		return _list;
	}
}
