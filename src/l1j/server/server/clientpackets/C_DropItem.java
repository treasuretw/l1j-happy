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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

import l1j.server.Config;
import l1j.server.server.ClientThread;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1DollInstance;
import l1j.server.server.model.Instance.L1ItemInstance;
import l1j.server.server.model.Instance.L1NpcInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.Instance.L1PetInstance;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.serverpackets.S_SystemMessage;
import l1j.server.server.utils.LogRecorder;
import l1j.william.L1CheckPcItem; // 防止复制道具

/**
 * 處理收到由客戶端傳來丟道具到地上的封包
 */
public class C_DropItem extends ClientBasePacket {
	private static final String C_DROP_ITEM = "[C] C_DropItem";

	public C_DropItem(byte[] decrypt, ClientThread client) throws Exception {
		super(decrypt);
		int x = readH();
		int y = readH();
		int objectId = readD();
		int count = readD();

		L1PcInstance pc = client.getActiveChar();
		if (pc.isGhost()) {
			return;
		} else if (pc.getMapId() >= 16384 && pc.getMapId() <= 25088) { // 旅馆内判断
			pc.sendPackets(new S_ServerMessage(539)); // \f1你無法將它放在這。
			return;
		}

		L1ItemInstance item = pc.getInventory().getItem(objectId);
		if (item != null) {
			if (!item.getItem().isTradable()) {
				// \f1%0%d是不可轉移的…
				pc.sendPackets(new S_ServerMessage(210, item.getItem()
						.getName()));
				return;
			}

			// 丢弃物品记录 by bill00148
			dropitem("IP"
					+ "(" + pc.getNetConnection().getIp() + ")"
					+"玩家"
					+ ":【" + pc.getName() + "】 "
					+ "的" 
					+ "【+" + item.getEnchantLevel()
					+ " " + item.getName() + 
					"(" + count + ")" + "】"
					+ " 丢弃到地上,"
					+ "时间:" + "(" + new Timestamp(System.currentTimeMillis()) + ")。");

			// 使用中的寵物項鍊 - 無法丟棄
			for (L1NpcInstance petNpc : pc.getPetList().values()) {
				if (petNpc instanceof L1PetInstance) {
					L1PetInstance pet = (L1PetInstance) petNpc;
					if (item.getId() == pet.getItemObjId()) {
						pc.sendPackets(new S_ServerMessage(1187)); // 寵物項鍊正在使用中。
						return;
					}
				}
			}
			// 使用中的魔法娃娃 - 無法丟棄
			for (L1DollInstance doll : pc.getDollList().values()) {
					if (doll.getItemObjId() == item.getId()) {
						pc.sendPackets(new S_ServerMessage(1181)); // 這個魔法娃娃目前正在使用中。
						return;
				}
			}

			// 防止复制道具 add
			L1CheckPcItem checkPcItem = new L1CheckPcItem();
			boolean isCheat = checkPcItem.checkPcItem(item, pc);
			if (isCheat) {
				L1World.getInstance().broadcastServerMessage(
						"\\fZ【" + pc.getName()
									+ "】丢弃违法道具！(删除违法道具)");
				return;
			}
			// 防止复制道具 end

			if (item.isEquipped()) {
				// \f1你不能夠放棄此樣物品。
				pc.sendPackets(new S_ServerMessage(125));
				return;
			}
			if (item.getBless() >= 128) { // 封印的裝備
				// \f1%0%d是不可轉移的…
				pc.sendPackets(new S_ServerMessage(210, item.getItem()
						.getName()));
				return;
			}

			// 物品丟地上系统会自动删除 by bill00148
			if (Config.Lostdeleteditems) {
				if (!pc.isGm()) {
					pc.getInventory().removeItem(objectId, count);
					pc.sendPackets(new S_SystemMessage("请爱护地球，垃圾不乱丢世界更美丽。"));
					return;
				}
			}
			// 交易紀錄
			if (Config.writeDropLog)
				LogRecorder.writeDropLog(pc, item);

			pc.getInventory().tradeItem(item, count,
					L1World.getInstance().getInventory(x, y, pc.getMapId()));
			pc.turnOnOffLight();
		}
	}

	// 记录文件档 by 阿杰 
	public static void dropitem(String info) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("log/丢弃物品记录.txt", true));
			out.write(info + "\r\n");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getType() {
		return C_DROP_ITEM;
	}
}
