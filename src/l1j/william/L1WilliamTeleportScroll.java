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
package l1j.william;

import l1j.server.server.model.L1Teleport;
import l1j.server.server.model.Instance.L1ItemInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.william.TeleportScroll;

public class L1WilliamTeleportScroll {

	private int _itemId;

	private int _tpLocX;

	private int _tpLocY;

	private int _tpMapId;

	private int _check_minLocX;

	private int _check_minLocY;

	private int _check_maxLocX;

	private int _check_maxLocY;

	private short _check_MapId;

	private int _removeItem;

	public L1WilliamTeleportScroll(int itemId, int tpLocX, int tpLocY,
			short tpMapId, int check_minLocX, int check_minLocY,
			int check_maxLocX, int check_maxLocY, short check_MapId,
			int removeItem) {

		_itemId = itemId;
		_tpLocX = tpLocX;
		_tpLocY = tpLocY;
		_tpMapId = tpMapId;
		_check_minLocX = check_minLocX;
		_check_minLocY = check_minLocY;
		_check_maxLocX = check_maxLocX;
		_check_maxLocY = check_maxLocY;
		_check_MapId = check_MapId;
		_removeItem = removeItem;
	}

	public int getItemId() {
		return _itemId;
	}

	public int getTpLocX() {
		return _tpLocX;
	}

	public int getTpLocY() {
		return _tpLocY;
	}

	public int getTpMapId() {
		return _tpMapId;
	}

	public int getCheckMinLocX() {
		return _check_minLocX;
	}

	public int getCheckMinLocY() {
		return _check_minLocY;
	}

	public int getCheckMaxLocX() {
		return _check_maxLocX;
	}

	public int getCheckMaxLocY() {
		return _check_maxLocY;
	}

	public int getCheckMapId() {
		return _check_MapId;
	}

	public int getRemoveItem() {
		return _removeItem;
	}

	public static int checkItemId(int itemId) {
		L1WilliamTeleportScroll teleport_scroll = TeleportScroll.getInstance()
				.getTemplate(itemId);

		if (teleport_scroll == null) {
			return 0;
		}

		int item_id = teleport_scroll.getItemId();
		return item_id;
	}

	public static void getTeleportScroll(L1PcInstance pc,
			L1ItemInstance l1iteminstance, int itemId) {
		L1WilliamTeleportScroll teleport_scroll = TeleportScroll.getInstance()
				.getTemplate(itemId);

		if (teleport_scroll == null) {
			return;
		}

		if (teleport_scroll.getCheckMinLocX() != 0
				&& teleport_scroll.getCheckMinLocY() != 0
				&& teleport_scroll.getCheckMaxLocX() != 0
				&& teleport_scroll.getCheckMaxLocY() != 0
				&& pc.getX() >= teleport_scroll.getCheckMinLocX()
				&& pc.getX() <= teleport_scroll.getCheckMaxLocX()
				&& pc.getY() >= teleport_scroll.getCheckMinLocY()
				&& pc.getY() <= teleport_scroll.getCheckMaxLocY()
				&& pc.getMapId() == teleport_scroll.getCheckMapId()) {
			if (pc.getMap().isEscapable() || pc.isGm()) {
				L1Teleport.teleport(pc, teleport_scroll.getTpLocX(),
						teleport_scroll.getTpLocY(),
						(short) teleport_scroll.getTpMapId(), pc.getHeading(), true);
				if (teleport_scroll.getRemoveItem() != 0) {
					pc.getInventory().removeItem(l1iteminstance, 1);
				}
			} else {
				pc.sendPackets(new S_ServerMessage(647));
			}
		} else if (teleport_scroll.getCheckMinLocX() == 0
				&& teleport_scroll.getCheckMinLocY() == 0
				&& teleport_scroll.getCheckMaxLocX() == 0
				&& teleport_scroll.getCheckMaxLocY() == 0
				&& teleport_scroll.getTpLocX() != 0
				&& teleport_scroll.getTpLocY() != 0) {
			if (pc.getMap().isEscapable() || pc.isGm()) {
				L1Teleport.teleport(pc, teleport_scroll.getTpLocX(),
						teleport_scroll.getTpLocY(),
						(short) teleport_scroll.getTpMapId(), pc.getHeading(), true);
				if (teleport_scroll.getRemoveItem() != 0) {
					pc.getInventory().removeItem(l1iteminstance, 1);
				}
			} else {
				pc.sendPackets(new S_ServerMessage(647));
			}
		} else {
			pc.sendPackets(new S_ServerMessage(79));
		}
	}
}
