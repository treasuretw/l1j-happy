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
package l1j.server.server;

import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.TimeZone;

import l1j.server.Config;
import l1j.server.server.datatables.AuctionBoardTable;
import l1j.server.server.datatables.ClanTable;
import l1j.server.server.datatables.HouseTable;
import l1j.server.server.datatables.ItemTable;
import l1j.server.server.model.L1Clan;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1ItemInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.identity.L1ItemId;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.storage.CharactersItemStorage;
import l1j.server.server.templates.L1AuctionBoard;
import l1j.server.server.templates.L1House;

public class AuctionTimeController implements Runnable {
	private static Logger _log = Logger.getLogger(AuctionTimeController.class
			.getName());

	private static AuctionTimeController _instance;

	public static AuctionTimeController getInstance() {
		if (_instance == null) {
			_instance = new AuctionTimeController();
		}
		return _instance;
	}

	@Override
	public void run() {
		try {
			while (true) {
				checkAuctionDeadline();
				Thread.sleep(60000);
			}
		} catch (Exception e1) {
		}
	}

	public Calendar getRealTime() {
		TimeZone tz = TimeZone.getTimeZone(Config.TIME_ZONE);
		Calendar cal = Calendar.getInstance(tz);
		return cal;
	}

	private void checkAuctionDeadline() {
		AuctionBoardTable boardTable = new AuctionBoardTable();
		for (L1AuctionBoard board : boardTable.getAuctionBoardTableList()) {
			if (board.getDeadline().before(getRealTime())) {
				endAuction(board);
			}
		}
	}

	private void endAuction(L1AuctionBoard board) {
		int houseId = board.getHouseId();
		int price = board.getPrice();
		int oldOwnerId = board.getOldOwnerId();
		String bidder = board.getBidder();
		int bidderId = board.getBidderId();

		if (oldOwnerId != 0 && bidderId != 0) { // 在前主人与得标者都存在的情况下
			L1PcInstance oldOwnerPc = (L1PcInstance) L1World.getInstance()
					.findObject(oldOwnerId);
			int payPrice = (int) (price * 0.9);
			if (oldOwnerPc != null) { // 如果有前主人
				oldOwnerPc.getInventory().storeItem(L1ItemId.ADENA, payPrice);
				// あなたが所有していた家が最终価格%1アデナで落札されました。%n
				// 手数料10%%を除いた残りの金额%0アデナを差し上げます。%nありがとうございました。%n%n
				oldOwnerPc.sendPackets(new S_ServerMessage(527, String
						.valueOf(payPrice)));
			} else { // 没有前主人
				L1ItemInstance item = ItemTable.getInstance().createItem(
						L1ItemId.ADENA);
				item.setCount(payPrice);
				try {
					CharactersItemStorage storage = CharactersItemStorage
							.create();
					storage.storeItem(oldOwnerId, item);
				} catch (Exception e) {
					_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
				}
			}

			L1PcInstance bidderPc = (L1PcInstance) L1World.getInstance()
					.findObject(bidderId);
			if (bidderPc != null) { // 如果有得标者
				// おめでとうございます。%nあなたが参加された竞卖は最终価格%0アデナの価格で落札されました。%n
				// 样がご购入された家はすぐにご利用できます。%nありがとうございました。%n%n
				bidderPc.sendPackets(new S_ServerMessage(524, String
						.valueOf(price), bidder));
			}
			deleteHouseInfo(houseId);
			setHouseInfo(houseId, bidderId);
			deleteNote(houseId);
		} else if (oldOwnerId == 0 && bidderId != 0) { // 在先前的拥有者没有中标
			L1PcInstance bidderPc = (L1PcInstance) L1World.getInstance()
					.findObject(bidderId);
			if (bidderPc != null) { // 落札者がオンライン中
				// おめでとうございます。%nあなたが参加された竞卖は最终価格%0アデナの価格で落札されました。%n
				// 样がご购入された家はすぐにご利用できます。%nありがとうございました。%n%n
				bidderPc.sendPackets(new S_ServerMessage(524, String
						.valueOf(price), bidder));
			}
			
			setHouseInfo(houseId, bidderId);
			deleteNote(houseId);
		} else if (oldOwnerId != 0 && bidderId == 0) { // 以前没有人成功竞投无
			L1PcInstance oldOwnerPc = (L1PcInstance) L1World.getInstance()
					.findObject(oldOwnerId);
			if (oldOwnerPc != null) { // 以前の所有者がオンライン中
				// あなたが申请なさった竞卖は、竞卖期间内に提示した金额以上での支払いを表明した方が现れなかったため、结局取り消されました。%n
				// 从って、所有权があなたに戻されたことをお知らせします。%nありがとうございました。%n%n
				oldOwnerPc.sendPackets(new S_ServerMessage(528));
			}
			deleteNote(houseId);
		} else if (oldOwnerId == 0 && bidderId == 0) { // 在先前的拥有者没有中标
			// 设定五天之后再次竞标
			Calendar cal = getRealTime();
			cal.add(Calendar.DATE, 5); // 5天后
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			board.setDeadline(cal);
			AuctionBoardTable boardTable = new AuctionBoardTable();
			boardTable.updateAuctionBoard(board);
		}
	}

	/**
	 * 取消拥有者的血盟小屋
	 * 
	 * @param houseId
	 *            血盟小屋的编号
	 * @return
	 */
	private void deleteHouseInfo(int houseId) {
		for (L1Clan clan : L1World.getInstance().getAllClans()) {
			if (clan.getHouseId() == houseId) {
				clan.setHouseId(0);
				ClanTable.getInstance().updateClan(clan);
			}
		}
	}

	/**
	 * 设定得标者血盟小屋的编号
	 * 
	 * @param houseId
	 *            血盟小屋的编号
	 * @param bidderId
	 *            得标者的编号
	 * @return
	 */
	private void setHouseInfo(int houseId, int bidderId) {
		for (L1Clan clan : L1World.getInstance().getAllClans()) {
			if (clan.getLeaderId() == bidderId) {
				clan.setHouseId(houseId);
				ClanTable.getInstance().updateClan(clan);
				break;
			}
		}
	}

	/**
	 * 将血盟小屋拍卖的告示取消、设定血盟小屋为不拍卖状态
	 * 
	 * @param houseId
	 *            血盟小屋的编号
	 * @return
	 */
	private void deleteNote(int houseId) {
		// 将血盟小屋的状态设定为不拍卖
		L1House house = HouseTable.getInstance().getHouseTable(houseId);
		house.setOnSale(false);
		Calendar cal = getRealTime();
		cal.add(Calendar.DATE, Config.HOUSE_TAX_INTERVAL);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		house.setTaxDeadline(cal);
		HouseTable.getInstance().updateHouse(house);

		// 取消拍卖告示
		AuctionBoardTable boardTable = new AuctionBoardTable();
		boardTable.deleteAuctionBoard(houseId);
	}

}
