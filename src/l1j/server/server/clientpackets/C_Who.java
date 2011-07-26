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

//import l1j.william.L1GameReStart;
import l1j.server.Config;
import l1j.server.server.ClientThread;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_BlueMessage;
import l1j.server.server.serverpackets.S_Message_YN;
import l1j.server.server.serverpackets.S_PacketBox;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.serverpackets.S_SkillSound;
import l1j.server.server.serverpackets.S_SystemMessage;
import l1j.server.server.serverpackets.S_WhoAmount;
import l1j.server.server.serverpackets.S_WhoCharinfo;

// Referenced classes of package l1j.server.server.clientpackets:
// ClientBasePacket

/**
 * 处理收到由客戶端传来查询线上人数的封包
 */
public class C_Who extends ClientBasePacket {

	private static final String C_WHO = "[C] C_Who";

	public C_Who(byte[] decrypt, ClientThread client) {
		super(decrypt);
		String s = readS();
		L1PcInstance find = L1World.getInstance().getPlayer(s);
		L1PcInstance pc = client.getActiveChar();

		// TODO 春哥证 add
		if (pc.isDead()) {
			if (pc.getInventory().consumeItem(5055, 1)) { // 复活消耗的道具编号与数量
				int objid = pc.getId();
				pc.sendPackets(new S_SkillSound(objid, 759));	// 动画
				pc.broadcastPacket(new S_SkillSound(objid, 759));
				if (pc.getCurrentHp() == 0 && pc.isDead()) {
					pc.sendPackets(new S_SystemMessage("信春哥 得永生。"));
					L1World.getInstance().broadcastPacketToAll(new S_BlueMessage(166, "\\f2【复活公告】：玩家【" + pc.getName() + "】 使用 【春哥证】获得重生！信春哥，得永生！"));
					pc.sendPackets(new S_SkillSound(pc.getId(), 3944));
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 3944));
					pc.setTempID(objid);
					pc.sendPackets(new S_Message_YN(322, ""));
				}
				else {
					pc.sendPackets(new S_SystemMessage("信春哥 得治疗。"));
					pc.sendPackets(new S_SkillSound(pc.getId(), 832));
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 832));
					pc.setCurrentHp(pc.getMaxHp());
					pc.setCurrentMp(pc.getMaxMp());
				}
			}
		}
		else // 加上这个玩家输入复活指令后 不会查询到系统人数那块
		//春哥证 end

			if (find != null) {
				S_WhoCharinfo s_whocharinfo = new S_WhoCharinfo(find);
				pc.sendPackets(s_whocharinfo);
			} else {
				if (Config.ALT_WHO_COMMAND) {
	                String amount = String.valueOf(L1World.getInstance()
	                        .getAllPlayers().size());
					//将线上人数*N倍 add
					if(Config.PeopleUp){
					int Iamount = Integer.parseInt(amount);
					Iamount = Iamount*Config.Rate;
					amount = String.valueOf(Iamount);
					}
					//将线上人数*N倍 end
	                // 线上资讯 
	                S_WhoAmount s_whoamount = new S_WhoAmount(amount);
					if (pc.isGm() == true) {
						int i=1;
						for (L1PcInstance pc1 : L1World.getInstance().getAllPlayers()) {
	                        pc.sendPackets(new S_SystemMessage(i + ". 玩家:{ " + pc1.getName() + " }、血盟:{ " + pc1.getClanname() + " }、等级:{ " + pc1.getLevel() + " }、PK:{" + pc1.get_PKcount() + "}"));
	                        i++;
						}
	                }
//	                pc.sendPackets(new S_SystemMessage("---------------------------------------------------"));
//	                pc.sendPackets(new S_SystemMessage("经验值: " + Config.RATE_XP + " 倍"));
//			        pc.sendPackets(new S_SystemMessage("正义值: " + Config.RATE_LA + " 倍"));
//					pc.sendPackets(new S_SystemMessage("友好度: " + Config.RATE_KARMA + " 倍"));
//					pc.sendPackets(new S_SystemMessage("负重率: " + Config.RATE_WEIGHT_LIMIT + " 倍"));
//					pc.sendPackets(new S_SystemMessage("掉宝率: " + Config.RATE_DROP_ITEMS + " 倍"));
//					pc.sendPackets(new S_SystemMessage("掉钱率: " + Config.RATE_DROP_ADENA + " 倍"));
					//pc.sendPackets(new S_SystemMessage("宠物经验值: " + Config.RATE_XP_PET + " 倍"));
					//pc.sendPackets(new S_SystemMessage("宠物等级上限: " + Config.Pet_Max_LV + " 级"));
//					pc.sendPackets(new S_SystemMessage("冲装率: 武器 " + Config.ENCHANT_CHANCE_WEAPON + "%  /  防具 " + Config.ENCHANT_CHANCE_ARMOR + "%"));
//					int second = L1GameReStart.getWillRestartTime();
//					pc.sendPackets(new S_SystemMessage("伺服器重启时间还有 " + ( second / 60 ) / 60 + " 小时 " + ( second / 60 ) % 60+ " 分 " + second % 60 + " 秒"));
					pc.sendPackets(new S_ServerMessage(1434,"" + (amount) + "")); // \f1目前线上的使用者: %0 (原81)

					pc.sendPackets(s_whoamount);
//					pc.sendPackets(new S_SystemMessage("---------------------------------------------------"));
	            } else {
	                String amount = String.valueOf(L1World.getInstance().getAllPlayers().size());
	                S_WhoAmount s_whoamount = new S_WhoAmount(amount);
	                pc.sendPackets(s_whoamount);
	                pc.sendPackets(new S_SystemMessage("在线查询服务已关闭。"));
				}

				if (pc.isGm()) { // GM点选玩家清单可瞬移到玩家身边
					pc.sendPackets(new S_PacketBox(S_PacketBox.CALL_SOMETHING));
				}
			}
		}

	@Override
	public String getType() {
		return C_WHO;
	}
}
