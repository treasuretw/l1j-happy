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

import static l1j.server.server.model.skill.L1SkillId.ABSOLUTE_BARRIER;
import static l1j.server.server.model.skill.L1SkillId.BLESS_WEAPON;
import static l1j.server.server.model.skill.L1SkillId.BRAVE_AURA;
import static l1j.server.server.model.skill.L1SkillId.CLEAR_MIND;
import static l1j.server.server.model.skill.L1SkillId.CONCENTRATION;
import static l1j.server.server.model.skill.L1SkillId.DECREASE_WEIGHT;
import static l1j.server.server.model.skill.L1SkillId.PHYSICAL_ENCHANT_DEX;
import static l1j.server.server.model.skill.L1SkillId.PHYSICAL_ENCHANT_STR;
import static l1j.server.server.model.skill.L1SkillId.ENTANGLE;
import static l1j.server.server.model.skill.L1SkillId.GLOWING_AURA;
import static l1j.server.server.model.skill.L1SkillId.GREATER_HASTE;
import static l1j.server.server.model.skill.L1SkillId.HASTE;
import static l1j.server.server.model.skill.L1SkillId.HOLY_WALK;
import static l1j.server.server.model.skill.L1SkillId.ILLUSION_LICH;
import static l1j.server.server.model.skill.L1SkillId.ILLUSION_OGRE;
import static l1j.server.server.model.skill.L1SkillId.IRON_SKIN;
import static l1j.server.server.model.skill.L1SkillId.MASS_SLOW;
import static l1j.server.server.model.skill.L1SkillId.MIRROR_IMAGE;
import static l1j.server.server.model.skill.L1SkillId.MOVING_ACCELERATION;
import static l1j.server.server.model.skill.L1SkillId.PATIENCE;
import static l1j.server.server.model.skill.L1SkillId.RESIST_MAGIC;
import static l1j.server.server.model.skill.L1SkillId.SLOW;
import static l1j.server.server.model.skill.L1SkillId.WIND_WALK;
import static l1j.server.server.model.skill.L1SkillId.STATUS_BRAVE;
import static l1j.server.server.model.skill.L1SkillId.STATUS_ELFBRAVE;
import static l1j.server.server.model.skill.L1SkillId.STATUS_HASTE;
import static l1j.server.server.model.skill.L1SkillId.STATUS_RIBRAVE;

import java.lang.reflect.Constructor;
import java.util.logging.Level;
import java.util.logging.Logger;

import l1j.server.server.IdFactory;
import l1j.server.server.datatables.NpcTable;
import l1j.server.server.model.L1NpcDeleteTimer;
import l1j.server.server.serverpackets.S_NPCPack;
import l1j.server.server.templates.L1Npc;

import l1j.server.server.datatables.ItemTable;
import l1j.server.server.model.identity.L1ItemId;
import l1j.server.server.model.L1Teleport;
import l1j.server.server.model.Instance.L1ItemInstance;
import l1j.server.server.model.Instance.L1NpcInstance;
import l1j.server.server.serverpackets.S_NpcChatPacket;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.serverpackets.S_SkillBrave;
import l1j.server.server.serverpackets.S_SkillHaste;
import l1j.server.server.serverpackets.S_SkillSound;
import l1j.server.server.serverpackets.S_SystemMessage;
import l1j.server.server.model.skill.L1SkillUse;
import l1j.william.Base;
import l1j.william.RandomArrayList;

import l1j.server.server.ClientThread;
import l1j.server.server.datatables.NpcActionTable;
import l1j.server.server.model.L1Object;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.npc.L1NpcHtml;
import l1j.server.server.model.npc.action.L1NpcAction;
import l1j.server.server.serverpackets.S_NPCTalkReturn;

// Referenced classes of package l1j.server.server.clientpackets:
// ClientBasePacket, C_NPCTalk

/**
 * 处理收到由客户端传来NPC讲话的封包
 */
public class C_NPCTalk extends ClientBasePacket {

	private static final String C_NPC_TALK = "[C] C_NPCTalk";
	private static Logger _log = Logger.getLogger(C_NPCTalk.class.getName());

	public C_NPCTalk(byte abyte0[], ClientThread client)
			throws Exception {
		super(abyte0);
		int objid = readD();
		L1Object obj = L1World.getInstance().findObject(objid);
		L1PcInstance pc = client.getActiveChar();
		if (obj != null && pc != null) {
			L1NpcAction action = NpcActionTable.getInstance().get(pc, obj);
			if (action != null) {
				L1NpcHtml html = action.execute("", pc, obj, new byte[0]);
				if (html != null) {
					pc.sendPackets(new S_NPCTalkReturn(obj.getId(), html));
				}
				return;
			}

			// TODO 加天神祝福NPC add
			if(obj instanceof L1NpcInstance) {
				//加天神祝福NPC by eric1300460
				if(((L1NpcInstance)obj).getNpcId()==99990) { // NPC 编号
					if (pc.hasSkillEffect(71) == true) { // ディケイポーションの状态
						pc.sendPackets(new S_ServerMessage(698)); // \f1魔力によって何も饮むことができません。
						return;
					}
					int time = 3600; // 1小时
					if(((L1NpcInstance)obj).getMapId()==350) { // 大陆NPC要收费
						L1ItemInstance adena = ItemTable.getInstance().createItem(5000);
						adena.setCount(pc.getInventory().countItems(5000));
						if(adena.getCount()<350){ //指定数量
							pc.sendPackets(new S_SystemMessage("元宝(350) 不足。"));
							return;
						}else {
							pc.getInventory().consumeItem(5000, 350);
							time = 900; // 15分钟
						}
					}
					if(((L1NpcInstance)obj).getMapId()==4) { // 大陆NPC要收费
						L1ItemInstance adena = ItemTable.getInstance().createItem(5000);
						adena.setCount(pc.getInventory().countItems(5000));
						if(adena.getCount()<350){ // 指定数量
							pc.sendPackets(new S_SystemMessage("元宝(350) 不足。"));
							return;
						}else {
							pc.getInventory().consumeItem(5000, 350);
							time = 900; // 15分钟
						}
					}

					// 解除绝对屏障
					if (pc.hasSkillEffect(ABSOLUTE_BARRIER)) {
						pc.killSkillEffectTimer(ABSOLUTE_BARRIER);
						pc.startHpRegeneration();
						pc.startMpRegeneration();
						//pc.startMpRegenerationByDoll();
					}
					//勇水
					if (pc.hasSkillEffect(STATUS_ELFBRAVE)) { // エルヴンワッフルとは重复しない
						pc.killSkillEffectTimer(STATUS_ELFBRAVE);
						pc.sendPackets(new S_SkillBrave(pc.getId(), 0, 0));
						pc.broadcastPacket(new S_SkillBrave(pc.getId(), 0, 0));
						pc.setBraveSpeed(0);
					}
					if (pc.hasSkillEffect(HOLY_WALK)) { // ホーリーウォークとは重复しない
						pc.killSkillEffectTimer(HOLY_WALK);
						pc.sendPackets(new S_SkillBrave(pc.getId(), 0, 0));
						pc.broadcastPacket(new S_SkillBrave(pc.getId(), 0, 0));
						pc.setBraveSpeed(0);
					}
					if (pc.hasSkillEffect(MOVING_ACCELERATION)) { // ムービングアクセレーションとは重复しない
						pc.killSkillEffectTimer(MOVING_ACCELERATION);
						pc.sendPackets(new S_SkillBrave(pc.getId(), 0, 0));
						pc.broadcastPacket(new S_SkillBrave(pc.getId(), 0, 0));
						pc.setBraveSpeed(0);
					}
					if (pc.hasSkillEffect(WIND_WALK)) { // ウィンドウォークとは重复しない
						pc.killSkillEffectTimer(WIND_WALK);
						pc.sendPackets(new S_SkillBrave(pc.getId(), 0, 0));
						pc.broadcastPacket(new S_SkillBrave(pc.getId(), 0, 0));
						pc.setBraveSpeed(0);
					}
					if (pc.hasSkillEffect(STATUS_RIBRAVE)) { // ユグドラの实とは重复しない
						pc.killSkillEffectTimer(STATUS_RIBRAVE);
						// XXX 不知道怎么关掉Yggdra稔图标
						pc.setBraveSpeed(0);
					}
					pc.sendPackets(new S_SkillBrave(pc.getId(), 1, time));
					pc.broadcastPacket(new S_SkillBrave(pc.getId(), 1, 0));
					pc.sendPackets(new S_SkillSound(pc.getId(), 751));
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 751));
					pc.setSkillEffect(STATUS_BRAVE, time * 1000);
					pc.setBraveSpeed(1);
					// 绿水
					// 解除醉酒状态
					pc.setDrink(false);

					// 删除重复的状态 貌似
					if (pc.hasSkillEffect(HASTE)) {
						pc.killSkillEffectTimer(HASTE);
						pc.sendPackets(new S_SkillHaste(pc.getId(), 0, 0));
						pc.broadcastPacket(new S_SkillHaste(pc.getId(), 0, 0));
						pc.setMoveSpeed(0);
					}
					else if (pc.hasSkillEffect(GREATER_HASTE)) {
						pc.killSkillEffectTimer(GREATER_HASTE);
						pc.sendPackets(new S_SkillHaste(pc.getId(), 0, 0));
						pc.broadcastPacket(new S_SkillHaste(pc.getId(), 0, 0));
						pc.setMoveSpeed(0);
					}
					else if (pc.hasSkillEffect(STATUS_HASTE)) {
						pc.killSkillEffectTimer(STATUS_HASTE);
						pc.sendPackets(new S_SkillHaste(pc.getId(), 0, 0));
						pc.broadcastPacket(new S_SkillHaste(pc.getId(), 0, 0));
						pc.setMoveSpeed(0);
					}

					// スロー、マス スロー、エンタングル中はスロー状态を解除するだけ
					if (pc.hasSkillEffect(SLOW)) { // スロー
						pc.killSkillEffectTimer(SLOW);
						pc.sendPackets(new S_SkillHaste(pc.getId(), 0, 0));
						pc.broadcastPacket(new S_SkillHaste(pc.getId(), 0, 0));
					}
					else if (pc.hasSkillEffect(MASS_SLOW)) { // マス スロー
						pc.killSkillEffectTimer(MASS_SLOW);
						pc.sendPackets(new S_SkillHaste(pc.getId(), 0, 0));
						pc.broadcastPacket(new S_SkillHaste(pc.getId(), 0, 0));
					}
					else if (pc.hasSkillEffect(ENTANGLE)) { // エンタングル
						pc.killSkillEffectTimer(ENTANGLE);
						pc.sendPackets(new S_SkillHaste(pc.getId(), 0, 0));
						pc.broadcastPacket(new S_SkillHaste(pc.getId(), 0, 0));
					}
					else {
						pc.sendPackets(new S_SkillHaste(pc.getId(), 1, time));
						pc.broadcastPacket(new S_SkillHaste(pc.getId(), 1, 0));
						pc.setMoveSpeed(1);
						pc.setSkillEffect(STATUS_HASTE, time * 1000);
					}

					// 幻术师魔法 (耐力)
					new L1SkillUse().handleCommands(pc, PATIENCE, pc.getId(), pc.getX(), pc.getY(), null, time, Base.SKILL_TYPE[4]);
					// 幻术师魔法 (幻觉：巫妖)
					new L1SkillUse().handleCommands(pc, ILLUSION_LICH, pc.getId(), pc.getX(), pc.getY(), null, time, Base.SKILL_TYPE[4]);
					// 幻术师魔法 (专注)
					new L1SkillUse().handleCommands(pc, CONCENTRATION, pc.getId(), pc.getX(), pc.getY(), null, time, Base.SKILL_TYPE[4]);
					// 幻术师魔法 (幻觉：欧吉
					new L1SkillUse().handleCommands(pc, ILLUSION_OGRE, pc.getId(), pc.getX(), pc.getY(), null, time, Base.SKILL_TYPE[4]);
					// 幻术师魔法 (镜像)
					new L1SkillUse().handleCommands(pc, MIRROR_IMAGE, pc.getId(), pc.getX(), pc.getY(), null, time, Base.SKILL_TYPE[4]);
					// 妖精魔法 (净化精神) 
					new L1SkillUse().handleCommands(pc, CLEAR_MIND, pc.getId(), pc.getX(), pc.getY(), null, time, Base.SKILL_TYPE[4]);
					// 妖精魔法 (魔法防御) 
					new L1SkillUse().handleCommands(pc, RESIST_MAGIC, pc.getId(), pc.getX(), pc.getY(), null, time, Base.SKILL_TYPE[4]);
					// 法师魔法 (负重强化) 
					new L1SkillUse().handleCommands(pc, DECREASE_WEIGHT, pc.getId(), pc.getX(), pc.getY(), null, time, Base.SKILL_TYPE[4]);
					// 法师魔法 (体魄强健术) 
					new L1SkillUse().handleCommands(pc, PHYSICAL_ENCHANT_STR, pc.getId(), pc.getX(), pc.getY(), null, time, Base.SKILL_TYPE[4]);
					// 法师魔法 (通畅气脉术)
					new L1SkillUse().handleCommands(pc, PHYSICAL_ENCHANT_DEX, pc.getId(), pc.getX(), pc.getY(), null, time, Base.SKILL_TYPE[4]);
					// 妖精魔法 (钢铁防护)
					new L1SkillUse().handleCommands(pc, IRON_SKIN, pc.getId(), pc.getX(), pc.getY(), null, time, Base.SKILL_TYPE[4]);
					// 法师魔法 (祝福魔法武器)
					new L1SkillUse().handleCommands(pc, BLESS_WEAPON, pc.getId(), pc.getX(), pc.getY(), null, time, Base.SKILL_TYPE[4]);
					// 王族魔法 (激励士气) 
					new L1SkillUse().handleCommands(pc, GLOWING_AURA, pc.getId(), pc.getX(), pc.getY(), null, time, Base.SKILL_TYPE[4]);
					// 王族魔法 (冲击士气)
					new L1SkillUse().handleCommands(pc, BRAVE_AURA, pc.getId(), pc.getX(), pc.getY(), null, time, Base.SKILL_TYPE[4]);
					// 法师魔法 (灵魂升华)
					new L1SkillUse().handleCommands(pc, 79, pc.getId(), pc.getX(), pc.getY(), null, time, Base.SKILL_TYPE[4]);
					pc.sendPackets(new S_SystemMessage("你的身体不断聚集伊娃的能量。"));
					pc.setCurrentHp(pc.getMaxHp()); // 补血
					pc.setCurrentMp(pc.getMaxMp()); // 补魔
					return;
				}

				//~加天神祝福NPC by eric1300460
				if(obj instanceof L1NpcInstance){
					// 加天神祝福NPC by eric1300460
					if(((L1NpcInstance)obj).getNpcId()==99991) { // NPC 编号
						if (pc.hasSkillEffect(71) == true) { // ディケイポーションの状态
							pc.sendPackets(new S_ServerMessage(698)); // \f1魔力によって何も饮むことができません。
							return;
						}
						int time = 3600; // 1小时
						if(((L1NpcInstance)obj).getMapId()==350) { // 大陆NPC要收费
							L1ItemInstance adena = ItemTable.getInstance().createItem(40308);
							adena.setCount(pc.getInventory().countItems(40308));
							if(adena.getCount()<200000) { // 小于20万
								pc.sendPackets(new S_SystemMessage("您的金币不足20万,我无法为您增加祝福!"));
								return;
							}
							else{
								pc.getInventory().consumeItem(40308, 200000);
								time = 900; // 15分钟
							}
						}
						if(((L1NpcInstance)obj).getMapId()==4) { // 大陆NPC要收费
							L1ItemInstance adena = ItemTable.getInstance().createItem(40308);
							adena.setCount(pc.getInventory().countItems(40308));
							if(adena.getCount()<200000) { // 小于20万
								pc.sendPackets(new S_SystemMessage("您的金币不足20万,我无法为您增加祝福!"));
								return;
							}
							else{
								pc.getInventory().consumeItem(40308, 200000);
								time = 900; // 15分钟
							}
						}

						//アブソルート バリアの解除
						if (pc.hasSkillEffect(ABSOLUTE_BARRIER)) {
							pc.killSkillEffectTimer(ABSOLUTE_BARRIER);
							pc.startHpRegeneration();
							pc.startMpRegeneration();
							//pc.startMpRegenerationByDoll();
						}
						// 勇水
						if (pc.hasSkillEffect(STATUS_ELFBRAVE)) { // エルヴンワッフルとは重复しない
							pc.killSkillEffectTimer(STATUS_ELFBRAVE);
							pc.sendPackets(new S_SkillBrave(pc.getId(), 0, 0));
							pc.broadcastPacket(new S_SkillBrave(pc.getId(), 0, 0));
							pc.setBraveSpeed(0);
						}
						if (pc.hasSkillEffect(HOLY_WALK)) { // ホーリーウォークとは重复しない
							pc.killSkillEffectTimer(HOLY_WALK);
							pc.sendPackets(new S_SkillBrave(pc.getId(), 0, 0));
							pc.broadcastPacket(new S_SkillBrave(pc.getId(), 0, 0));
							pc.setBraveSpeed(0);
						}
						if (pc.hasSkillEffect(MOVING_ACCELERATION)) { // ムービングアクセレーションとは重复しない
							pc.killSkillEffectTimer(MOVING_ACCELERATION);
							pc.sendPackets(new S_SkillBrave(pc.getId(), 0, 0));
							pc.broadcastPacket(new S_SkillBrave(pc.getId(), 0, 0));
							pc.setBraveSpeed(0);
						}
						if (pc.hasSkillEffect(WIND_WALK)) { // ウィンドウォークとは重复しない
							pc.killSkillEffectTimer(WIND_WALK);
							pc.sendPackets(new S_SkillBrave(pc.getId(), 0, 0));
							pc.broadcastPacket(new S_SkillBrave(pc.getId(), 0, 0));
							pc.setBraveSpeed(0);
						}
						if (pc.hasSkillEffect(STATUS_RIBRAVE)) { // ユグドラの实とは重复しない
							pc.killSkillEffectTimer(STATUS_RIBRAVE);
							// XXX ユグドラの实のアイコンを消す方法が不明
							pc.setBraveSpeed(0);
						}
						pc.sendPackets(new S_SkillBrave(pc.getId(), 1, time));
						pc.broadcastPacket(new S_SkillBrave(pc.getId(), 1, 0));
						pc.sendPackets(new S_SkillSound(pc.getId(), 751));
						pc.broadcastPacket(new S_SkillSound(pc.getId(), 751));
						pc.setSkillEffect(STATUS_BRAVE, time * 1000);
						pc.setBraveSpeed(1);
						// 绿水
						// 醉った状态を解除
						pc.setDrink(false);

						// 消除重复的状态
						if (pc.hasSkillEffect(HASTE)) {
							pc.killSkillEffectTimer(HASTE);
							pc.sendPackets(new S_SkillHaste(pc.getId(), 0, 0));
							pc.broadcastPacket(new S_SkillHaste(pc.getId(), 0, 0));
							pc.setMoveSpeed(0);
						}
						else if (pc.hasSkillEffect(GREATER_HASTE)) {
							pc.killSkillEffectTimer(GREATER_HASTE);
							pc.sendPackets(new S_SkillHaste(pc.getId(), 0, 0));
							pc.broadcastPacket(new S_SkillHaste(pc.getId(), 0, 0));
							pc.setMoveSpeed(0);
						}
						else if (pc.hasSkillEffect(STATUS_HASTE)) {
							pc.killSkillEffectTimer(STATUS_HASTE);
							pc.sendPackets(new S_SkillHaste(pc.getId(), 0, 0));
							pc.broadcastPacket(new S_SkillHaste(pc.getId(), 0, 0));
							pc.setMoveSpeed(0);
						}

						// 解除状态
						if (pc.hasSkillEffect(SLOW)) { // 缓速术
							pc.killSkillEffectTimer(SLOW);
							pc.sendPackets(new S_SkillHaste(pc.getId(), 0, 0));
							pc.broadcastPacket(new S_SkillHaste(pc.getId(), 0, 0));
						}
						else if (pc.hasSkillEffect(MASS_SLOW)) { // 集体缓速术
							pc.killSkillEffectTimer(MASS_SLOW);
							pc.sendPackets(new S_SkillHaste(pc.getId(), 0, 0));
							pc.broadcastPacket(new S_SkillHaste(pc.getId(), 0, 0));
						}
						else if (pc.hasSkillEffect(ENTANGLE)) { // 地面障碍
							pc.killSkillEffectTimer(ENTANGLE);
							pc.sendPackets(new S_SkillHaste(pc.getId(), 0, 0));
							pc.broadcastPacket(new S_SkillHaste(pc.getId(), 0, 0));
						}
						else {
							pc.sendPackets(new S_SkillHaste(pc.getId(), 1, time));
							pc.broadcastPacket(new S_SkillHaste(pc.getId(), 1, 0));
							pc.setMoveSpeed(1);
							pc.setSkillEffect(STATUS_HASTE, time * 1000);
						}

						// 法师魔法 (体魄强健术) 
						new L1SkillUse().handleCommands(pc, PHYSICAL_ENCHANT_STR, pc.getId(), pc.getX(), pc.getY(), null, time, Base.SKILL_TYPE[4]);
						// 法师魔法 (通畅气脉术)
						new L1SkillUse().handleCommands(pc, PHYSICAL_ENCHANT_DEX, pc.getId(), pc.getX(), pc.getY(), null, time, Base.SKILL_TYPE[4]);
						// 法师魔法 (负重强化) 
						new L1SkillUse().handleCommands(pc, DECREASE_WEIGHT, pc.getId(), pc.getX(), pc.getY(), null, time, Base.SKILL_TYPE[4]);
						// 妖精魔法 (钢铁防护)
						new L1SkillUse().handleCommands(pc, IRON_SKIN, pc.getId(), pc.getX(), pc.getY(), null, time, Base.SKILL_TYPE[4]);
						// 法师魔法 (祝福魔法武器)
						new L1SkillUse().handleCommands(pc, BLESS_WEAPON, pc.getId(), pc.getX(), pc.getY(), null, time, Base.SKILL_TYPE[4]);
						// 法师魔法  (灵魂升华)
						new L1SkillUse().handleCommands(pc, 79, pc.getId(), pc.getX(), pc.getY(), null, time, Base.SKILL_TYPE[4]);
						pc.sendPackets(new S_SystemMessage("恭喜你消耗20万成功的增加了天神祝福!"));
						pc.setCurrentHp(pc.getMaxHp()); // 补血
						pc.setCurrentMp(pc.getMaxMp()); // 补魔
						return;
					}
				}

				//~提卡尔祭坛传送 Npc
				if(((L1NpcInstance)obj).getNpcId() == 95020) { // 提卡尔 库库尔坎祭坛守门人
					L1ItemInstance adena = ItemTable.getInstance().createItem(49324);
					adena.setCount(pc.getInventory().countItems(49324)); // 提卡尔库库尔坎祭坛钥匙
					if(!pc.getInventory().consumeItem(49324, 1)) { //需要的物品与数量
						pc.sendPackets(new S_SystemMessage(adena.getLogName()+"  不足!"));
						return;
					}
					try {
						pc.sendPackets(new S_SkillSound(pc.getId(), 7300));
						pc.broadcastPacket(new S_SkillSound(pc.getId(), 7300));
						pc.sendPackets(new S_SkillSound(pc.getId(), 7282));
						pc.broadcastPacket(new S_SkillSound(pc.getId(), 7282));
						pc.sendPackets(new S_SystemMessage("大地开始抖动!"));
						Thread.sleep(2000); // 2秒后传送

						//end
						L1Teleport.teleport(pc, 32731, 32863, (short) 784, 4, true);
						pc.sendPackets(new S_SystemMessage("抖动停止后你发现祭坛就在眼前！"));
					}
					catch (Exception exception) {

					}
					return;
				}

				// 点击npc随机刷怪 (测试)
				if(((L1NpcInstance)obj).getNpcId()==88888) {
					if (pc.getInventory().consumeItem(L1ItemId.ADENA,50000000)) {
						L1NpcInstance npc = (L1NpcInstance) obj;
						int[] npcid = { 45005, 45006, 45007, 45008, 45009, 45010,45228,45487 };
						int rnd = RandomArrayList.getInt(8);
						try {
							npc.broadcastPacket(new S_NpcChatPacket(npc, "5秒后，怪物即将登场.", 0));
							Thread.sleep(5000);
						}
						catch (Exception exception) {

						}
						spawnObject(npcid[rnd],pc.getX(),pc.getY(),pc.getMapId(),0);
						int rnd1 = RandomArrayList.getInt(8);
						spawnObject(npcid[rnd1],pc.getX(),pc.getY(),pc.getMapId(),0);


						if (npc.hasSkillEffect(8985)){//8985
							npc.removeSkillEffect(8985);
						}
						npc.setSkillEffect(8985, 5*1000);

						return;
					}
					pc.sendPackets(new S_SystemMessage("需要金币5千万！"));

				}

			}

			obj.onTalkAction(pc);
		} else {
			_log.severe("不正确的NPC objid=" + objid);
		}
	}

	@SuppressWarnings("unused")
	private void spawnObject(int npcid, int locx, int locy, short mapid, int head/*, int timeMillisToDelete*/) {
		@SuppressWarnings("rawtypes")
		Constructor constructor;
		L1Npc l1npc = NpcTable.getInstance().getTemplate(npcid);
		try {
			if (l1npc != null) {
				Object obj = null;
				String s = l1npc.getImpl();
				constructor = Class.forName((new StringBuilder()).append(
				"l1j.server.server.model.Instance.").append(s)
				.append("Instance").toString()).getConstructors()[0];
				Object aobj[] = { l1npc };
				L1NpcInstance npc = (L1NpcInstance) constructor.newInstance(aobj);
				npc.setId(IdFactory.getInstance().nextId());
				npc.setX(locx);
				npc.setY(locy);
				npc.setHomeX(locx);
				npc.setHomeY(locy);
				npc.setHeading(head);
				npc.setMap(mapid);
				L1World.getInstance().storeObject(npc);
				L1World.getInstance().addVisibleObject(npc);			
				L1NpcDeleteTimer timer = new L1NpcDeleteTimer(npc, 300*1000);

				timer.begin();
				for (L1PcInstance pc : L1World.getInstance().getAllPlayers()) {
					npc.addKnownObject(pc);
					pc.addKnownObject(npc);
					pc.sendPackets(new S_NPCPack(npc));
					pc.broadcastPacket(new S_NPCPack(npc));
				}
			}
		}
		catch (Exception exception) { 
			_log.log(Level.SEVERE, exception.getLocalizedMessage(), exception);
		}
	}

	@Override
	public String getType() {
		return C_NPC_TALK;
	}
}
