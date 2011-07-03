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
package l1j.server.server.clientpackets;

import java.util.Timer;
import java.util.TimerTask;

import l1j.server.server.ActionCodes;
import l1j.server.server.ClientThread;
import l1j.server.server.datatables.HouseTable;
import l1j.server.server.model.L1Clan;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1DoorInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.templates.L1House;

// Referenced classes of package l1j.server.server.clientpackets:
// ClientBasePacket, C_Door

/**
 * 處理收到由客戶端傳來開關門的封包
 */
public class C_Door extends ClientBasePacket {

	private static final String C_DOOR = "[C] C_Door";

	public C_Door(byte abyte0[], ClientThread client) throws Exception {
		super(abyte0);
		readH();
		readH();
		int objectId = readD();

		L1PcInstance pc = client.getActiveChar();
		L1DoorInstance door = (L1DoorInstance) L1World.getInstance().findObject(objectId);
		if (door == null) {
			return;
		}

		if (((door.getDoorId() >= 5001) && (door.getDoorId() <= 5010))) { // 水晶洞窟
			return;
		}

		//副本  (测试) add
		else if (door.getDoorId() == 6033) { //副本-门 A
			if (door.getOpenStatus() == ActionCodes.ACTION_Open) {
				return;
			}
			if (pc.getInventory().consumeItem(5072,1)) { // 钥匙(A)
				door.open();
				LongCloseTimer closetimer = new LongCloseTimer(door);
				closetimer.begin();
			}
		}
		else if (door.getDoorId() == 6034) { //副本-门 B
			if (door.getOpenStatus() == ActionCodes.ACTION_Open) {
				return;
			}
			if (pc.getInventory().consumeItem(5073,1)) { // 钥匙(B)
				door.open();
				LongCloseTimer closetimer = new LongCloseTimer(door);
				closetimer.begin();
			}
		}
		else if (door.getDoorId() == 6035) { //副本-门 C
			if (door.getOpenStatus() == ActionCodes.ACTION_Open) {
				return;
			}
			if (pc.getInventory().consumeItem(5074,1)) { // 钥匙(C)
				door.open();
				LongCloseTimer closetimer = new LongCloseTimer(door);
				closetimer.begin();
			}
		}
		//副本  (测试) end

		else if (door.getDoorId() == 6006) { // 冒險洞穴二樓
			if (door.getOpenStatus() == ActionCodes.ACTION_Open) {
				return;
			}
			if (pc.getInventory().consumeItem(40163, 1)) { // 黃金鑰匙
				door.open();
				CloseTimer closetimer = new CloseTimer(door);
				closetimer.begin();
			}
		}
		else if (door.getDoorId() == 6007) { // 冒險洞穴二樓
			if (door.getOpenStatus() == ActionCodes.ACTION_Open) {
				return;
			}
			if (pc.getInventory().consumeItem(40313, 1)) { // 銀鑰匙
				door.open();
				CloseTimer closetimer = new CloseTimer(door);
				closetimer.begin();
			}
		}
		else if (!isExistKeeper(pc, door.getKeeperId())) {
			if (door.getOpenStatus() == ActionCodes.ACTION_Open) {
				door.close();
			}
			else if (door.getOpenStatus() == ActionCodes.ACTION_Close) {
				door.open();
			}
		}
	}

	private boolean isExistKeeper(L1PcInstance pc, int keeperId) {
		if (keeperId == 0) {
			return false;
		}

		L1Clan clan = L1World.getInstance().getClan(pc.getClanname());
		if (clan != null) {
			int houseId = clan.getHouseId();
			if (houseId != 0) {
				L1House house = HouseTable.getInstance().getHouseTable(houseId);
				if (keeperId == house.getKeeperId()) {
					return false;
				}
			}
		}
		return true;
	}

	//副本  (测试)(测试) add
	public class LongCloseTimer extends TimerTask {

		private L1DoorInstance _door;

		public LongCloseTimer(L1DoorInstance door) {
			_door = door;
		}

		@Override
		public void run() {
			if (_door.getOpenStatus() == ActionCodes.ACTION_Open) {
				_door.close();
			}
		}

		public void begin() {
			Timer timer = new Timer();
			timer.schedule(this, 60 * 1000);
		}
	}
	//副本  (测试)(测试) end

	public class CloseTimer extends TimerTask {

		private L1DoorInstance _door;

		public CloseTimer(L1DoorInstance door) {
			_door = door;
		}

		@Override
		public void run() {
			if (_door.getOpenStatus() == ActionCodes.ACTION_Open) {
				_door.close();
			}
		}

		public void begin() {
			Timer timer = new Timer();
			timer.schedule(this, 5 * 1000);
		}
	}

	@Override
	public String getType() {
		return C_DOOR;
	}
}
