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

import static l1j.server.server.model.skill.L1SkillId.AWAKEN_ANTHARAS;
import static l1j.server.server.model.skill.L1SkillId.AWAKEN_FAFURION;
import static l1j.server.server.model.skill.L1SkillId.AWAKEN_VALAKAS;
import static l1j.server.server.model.skill.L1SkillId.CURSE_PARALYZE;
import static l1j.server.server.model.skill.L1SkillId.SHOCK_STUN;
import static l1j.server.server.model.skill.L1SkillId.EARTH_BIND;

import static l1j.server.server.model.skill.L1SkillId.ABSOLUTE_BARRIER;
import static l1j.server.server.model.skill.L1SkillId.COOKING_NOW;
import static l1j.server.server.model.skill.L1SkillId.DECAY_POTION;
import static l1j.server.server.model.skill.L1SkillId.SHAPE_CHANGE;
import static l1j.server.server.model.skill.L1SkillId.SOLID_CARRIAGE;
import static l1j.server.server.model.skill.L1SkillId.STATUS_FLOATING_EYE;
import static l1j.server.server.model.skill.L1SkillId.STATUS_HOLY_MITHRIL_POWDER;
import static l1j.server.server.model.skill.L1SkillId.STATUS_HOLY_WATER;
import static l1j.server.server.model.skill.L1SkillId.STATUS_HOLY_WATER_OF_EVA;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import l1j.william.DragonGate;		// 副本  (测试)
import l1j.william.L1Blend;
import l1j.william.L1CheckPcItem;	// 防止复制道具
import l1j.william.L1WilliamTeleportScroll;
import l1j.server.Config;
import l1j.server.L1DatabaseFactory;
import l1j.server.server.Account;
import l1j.server.server.ActionCodes;
import l1j.server.server.ClientThread;
import l1j.server.server.FishingTimeController;
import l1j.william.GetNowTime;
import l1j.server.server.IdFactory;
import l1j.server.server.datatables.CharacterTable;
import l1j.server.server.datatables.FurnitureSpawnTable;
import l1j.server.server.datatables.ItemTable;
import l1j.server.server.datatables.LetterTable;
import l1j.server.server.datatables.NpcTable;
import l1j.server.server.datatables.PetTable;
import l1j.server.server.datatables.PolyTable;
import l1j.server.server.datatables.ResolventTable;
import l1j.server.server.datatables.SkillsTable;
import l1j.server.server.model.Getback;
import l1j.server.server.model.L1CastleLocation;
import l1j.server.server.model.L1Character;
import l1j.server.server.model.L1Clan;
import l1j.server.server.model.L1Cooking;
import l1j.server.server.model.L1DragonSlayer;
import l1j.server.server.model.L1EffectSpawn;
import l1j.server.server.model.L1HouseLocation;
import l1j.server.server.model.L1Inventory;
import l1j.server.server.model.L1ItemDelay;
import l1j.server.server.model.L1Location;
import l1j.server.server.model.L1Object;
import l1j.server.server.model.L1PcInventory;
import l1j.server.server.model.L1PolyMorph;
import l1j.server.server.model.L1Quest;
import l1j.server.server.model.L1Teleport;
import l1j.server.server.model.L1TownLocation;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1EffectInstance;
import l1j.server.server.model.Instance.L1FurnitureInstance;
import l1j.server.server.model.Instance.L1GuardianInstance;
import l1j.server.server.model.Instance.L1ItemInstance;
import l1j.server.server.model.Instance.L1MonsterInstance;
import l1j.server.server.model.Instance.L1NpcInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.Instance.L1PetInstance;
import l1j.server.server.model.Instance.L1TowerInstance;
import l1j.server.server.model.identity.L1ItemId;
import l1j.server.server.model.item.L1TreasureBox;
import l1j.server.server.model.item.action.Effect;
import l1j.server.server.model.item.action.Enchant;
import l1j.server.server.model.item.action.MagicDoll;
import l1j.server.server.model.item.action.Potion;
import l1j.server.server.model.poison.L1DamagePoison;
import l1j.server.server.model.skill.L1SkillUse;
import l1j.server.server.serverpackets.S_AddSkill;
import l1j.server.server.serverpackets.S_AttackPacket;
import l1j.server.server.serverpackets.S_BlueMessage;
import l1j.server.server.serverpackets.S_DragonGate;
import l1j.server.server.serverpackets.S_Fishing;
import l1j.server.server.serverpackets.S_IdentifyDesc;
import l1j.server.server.serverpackets.S_ItemName;
import l1j.server.server.serverpackets.S_ItemStatus;
import l1j.server.server.serverpackets.S_Letter;
import l1j.server.server.serverpackets.S_Liquor;
import l1j.server.server.serverpackets.S_Message_YN;
import l1j.server.server.serverpackets.S_NPCTalkReturn;
import l1j.server.server.serverpackets.S_OtherCharPacks;
import l1j.server.server.serverpackets.S_OwnCharAttrDef;
import l1j.server.server.serverpackets.S_OwnCharPack;
import l1j.server.server.serverpackets.S_OwnCharStatus;
import l1j.server.server.serverpackets.S_OwnCharStatus2;
import l1j.server.server.serverpackets.S_PacketBox;
import l1j.server.server.serverpackets.S_Paralysis;
import l1j.server.server.serverpackets.S_SPMR;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.serverpackets.S_ShowPolyList;
import l1j.server.server.serverpackets.S_SkillSound;
import l1j.server.server.serverpackets.S_Sound;
import l1j.server.server.serverpackets.S_SystemMessage;
import l1j.server.server.serverpackets.S_UseAttackSkill;
import l1j.server.server.serverpackets.S_UseMap;
import l1j.server.server.storage.CharactersItemStorage;
import l1j.server.server.templates.L1Armor;
import l1j.server.server.templates.L1BookMark;
import l1j.server.server.templates.L1EtcItem;
import l1j.server.server.templates.L1Item;
import l1j.server.server.templates.L1Npc;
import l1j.server.server.templates.L1Pet;
import l1j.server.server.templates.L1Skills;
import l1j.server.server.types.Point;
import l1j.server.server.utils.L1SpawnUtil;
import l1j.server.server.utils.Random;
import l1j.william.ItemUpdate;

// Referenced classes of package l1j.server.server.clientpackets:
// ClientBasePacket
//

/**
 * TODO: 翻译，太多了= = 处理收到由客户端传来使用道具的封包
 */
public class C_ItemUSe extends ClientBasePacket {

	private static final String C_ITEM_USE = "[C] C_ItemUSe";

	private static Logger _log = Logger.getLogger(C_ItemUSe.class.getName());

	public C_ItemUSe(byte abyte0[], ClientThread client) throws Exception {
		super(abyte0);
		int itemObjid = readD();

		L1PcInstance pc = client.getActiveChar();
		if (pc.isGhost()) {
			return;
		}

		// 防外挂自动喝水
		if (pc.hasSkillEffect(CURSE_PARALYZE)		//木乃伊
				|| pc.hasSkillEffect(SHOCK_STUN)	//冲晕
				|| pc.hasSkillEffect(EARTH_BIND)) {	//大地屏障
			pc.checkUseItemInterval();
		}
		//end

		L1ItemInstance l1iteminstance = pc.getInventory().getItem(itemObjid);

		if ((l1iteminstance == null) || (pc.isDead())) {
			return;
		}

		if (l1iteminstance.getItem().getUseType() == -1) { // none:不能使用的道具
			pc.sendPackets(new S_ServerMessage(74, l1iteminstance.getLogName())); // \f1%0%o 无法使用。
			return;
		}

		// 防止复制道具 add
		L1CheckPcItem checkPcItem = new L1CheckPcItem();
		boolean isCheat = checkPcItem.checkPcItem(l1iteminstance, pc);
		if (isCheat) {
			L1World.getInstance().broadcastServerMessage(
					"\\fZ【" + pc.getName()
								+ "】使用违法道具！(删除违法道具)");
			return;
		}
		// 防止复制道具 end

		int pcObjid = pc.getId();
		if (pc.isTeleport()) { // 传送中
			return;
		}
		if (!pc.getMap().isUsableItem()) {
			pc.sendPackets(new S_ServerMessage(563)); // \f1你无法在这个地方使用。
			return;
		}
		int itemId;
		try {
			itemId = l1iteminstance.getItem().getItemId();
		}
		catch (Exception e) {
			return;
		}
		int l = 0;

		String s = "";
		@SuppressWarnings("unused")
		int bmapid = 0;
		int btele = 0;
		int blanksc_skillid = 0;
		int spellsc_objid = 0;
		int spellsc_x = 0;
		int spellsc_y = 0;
		int resid = 0;
		int letterCode = 0;
		String letterReceiver = "";
		byte[] letterText = null;
		int cookStatus = 0;
		int cookNo = 0;
		int fishX = 0;
		int fishY = 0;

		int use_type = l1iteminstance.getItem().getUseType();
		switch (itemId) {
		case 40088: case 40096: case 49308: case 140088: // 变形卷轴
			s = readS();
			break;
		case L1ItemId.SCROLL_OF_ENCHANT_ARMOR: case L1ItemId.SCROLL_OF_ENCHANT_WEAPON:
		case L1ItemId.SCROLL_OF_ENCHANT_QUEST_WEAPON:
		case 40077: case 40078: case 40126: case 40098: case 40129: case 40130: case 140129: case 140130: 
		case L1ItemId.B_SCROLL_OF_ENCHANT_ARMOR: case L1ItemId.B_SCROLL_OF_ENCHANT_WEAPON:
		case L1ItemId.C_SCROLL_OF_ENCHANT_ARMOR: case L1ItemId.C_SCROLL_OF_ENCHANT_WEAPON:
		case 41029: case 40317: case 49188: case 41036: case 41245: case 40127: case 40128: case 41048:
		case 41049: case 41050: case 41051: case 41052: case 41053: case 41054: case 41055: case 41056:
		case 41057: case 40925: case 40926: case 40927: case 40928: case 40929: case 40931: case 40932:
		case 40933: case 40934: case 40935: case 40936: case 40937: case 40938: case 40939: case 40940:
		case 40941: case 40942: case 40943: case 40944: case 40945: case 40946: case 40947: case 40948:
		case 40949: case 40950: case 40951: case 40952: case 40953: case 40954: case 40955: case 40956:
		case 40957: case 40958: case 40964: case 49092: case 49094: case 49098: case 49317: case 49321:
		case 41426: case 41427: case 40075: case 49311: case 49312: case 49148: case 41429: case 41430:
		case 41431: case 41432: case 47041: case 47042: case 47043: case 47044: case 47045: case 47046:
		case 47048: case 47049: case 47050: case 47051: case 47052: case 49198: case 49199:
		case 5001:	// 10%武器攻击宝石
		case 5002:	// 60%武器攻击宝石
		case 5003:	// 100%武器攻击宝石
		case 5004:	// 武器力量卷轴
		case 5005:	// 武器敏捷卷轴
		case 5006:	// 武器智力卷轴
		case 5007:	// 盔甲体力卷轴
		case 5008:	// 盔甲魔力卷轴
		case 5053:	// 装备保护卷轴
		case 5056:	// 百分百武卷
		case 5057:	// 百分百防卷
			l = readD();
			break;
		case 140100: case 40100: case 40099: case 40086: case 40863: // 瞬间移动卷轴
			bmapid = readH();
			btele = readD();
		//	pc.sendPackets(new S_Paralysis(S_Paralysis.TYPE_TELEPORT_UNLOCK, false));
			break;
		case 40090: case 40091: case 40092: case 40093: case 40094: // 空的魔法卷轴(Lv1)～(Lv5)
			blanksc_skillid = readC();
			break;
		case 40870: case 40879: // spell_buff
			spellsc_objid = readD();
			break;
		case 40089: case 140089: // 复活卷轴
			resid = readD();
			break;
		case 40310: case 40311: case 40730: case 40731: case 40732: // 信纸
			letterCode = readH();
			letterReceiver = readS();
			letterText = readByte();
			break;
		case 41293: case 41294: // 钓竿
			fishX = readH();
			fishY = readH();
			break;
		default:
			if ((use_type == 30)) { // spell_buff
				spellsc_objid = readD();
			}
			else if ((use_type == 5) || (use_type == 17)) { // spell_long、spell_short
				spellsc_objid = readD();
				spellsc_x = readH();
				spellsc_y = readH();
			}
			else if ((itemId >= 41255) && (itemId <= 41259)) { // 料理书
				cookStatus = readC();
				cookNo = readC();
			}
			else {
				l = readC();
			}
			break;
		}

		if (pc.getCurrentHp() > 0) {
			int delay_id = 0;
			if (l1iteminstance.getItem().getType2() == 0) { // 种别：その他のアイテム
				delay_id = ((L1EtcItem) l1iteminstance.getItem()).get_delayid();
			}
			if (delay_id != 0) { // ディレイ设定あり
				if (pc.hasItemDelay(delay_id) == true) {
					return;
				}
			}

			// 再使用チェック
			boolean isDelayEffect = false;
			if (l1iteminstance.getItem().getType2() == 0) { // etcitem
				int delayEffect = ((L1EtcItem) l1iteminstance.getItem()).get_delayEffect();
				if (delayEffect > 0) {
					isDelayEffect = true;
					Timestamp lastUsed = l1iteminstance.getLastUsed();
					if (lastUsed != null) {
						Calendar cal = Calendar.getInstance();
						if ((cal.getTimeInMillis() - lastUsed.getTime()) / 1000 <= delayEffect) {
							// \f1没有任何事情发生。
							pc.sendPackets(new S_ServerMessage(79));
							return;
						}
					}
				}
			}

			L1ItemInstance l1iteminstance1 = pc.getInventory().getItem(l);
			_log.finest("request item use (obj) = " + itemObjid + " action = " + l + " value = " + s);
			if ((itemId == 40077) || (itemId == L1ItemId.SCROLL_OF_ENCHANT_WEAPON) || (itemId == L1ItemId.SCROLL_OF_ENCHANT_QUEST_WEAPON)
					|| (itemId == 40130) || (itemId == 140130) || (itemId == L1ItemId.B_SCROLL_OF_ENCHANT_WEAPON)
					|| (itemId == L1ItemId.C_SCROLL_OF_ENCHANT_WEAPON) || (itemId == 5056) // 百分百武卷
					|| (itemId == 40128)) { // 对武器施法的卷轴
				Enchant.scrollOfEnchantWeapon(pc, l1iteminstance, l1iteminstance1, client);
			}
			else if (itemId == 49312) { // 象牙塔对武器施法的卷轴
				Enchant.scrollOfEnchantWeaponIvoryTower(pc, l1iteminstance, l1iteminstance1, client);
			}
			else if ((itemId == 41429) || (itemId == 41430) || (itemId == 41431) || (itemId == 41432)) { // 武器属性强化卷轴
				Enchant.scrollOfEnchantWeaponAttr(pc, l1iteminstance, l1iteminstance1, client);
			}
			else if ((itemId == 40078) || (itemId == L1ItemId.SCROLL_OF_ENCHANT_ARMOR) || (itemId == 40129) || (itemId == 140129) || (itemId == 5057)	// 百分百防卷
					|| (itemId == L1ItemId.B_SCROLL_OF_ENCHANT_ARMOR) || (itemId == L1ItemId.C_SCROLL_OF_ENCHANT_ARMOR) || (itemId == 40127)) {			// 对盔甲施法的卷轴
				Enchant.scrollOfEnchantArmor(pc, l1iteminstance, l1iteminstance1, client);
			}
			else if (itemId == 49311) { // 象牙塔对盔甲施法的卷轴
				Enchant.scrollOfEnchantArmorIvoryTower(pc, l1iteminstance, l1iteminstance1, client);
			}
			else if (l1iteminstance.getItem().getType2() == 0) { // 道具类
				int item_minlvl = ((L1EtcItem) l1iteminstance.getItem()).getMinLevel();
				int item_maxlvl = ((L1EtcItem) l1iteminstance.getItem()).getMaxLevel();
				int fameLV = l1iteminstance.getItem().getCheckFameLevel(); // sosodemon 物品声望控制
				if ((item_minlvl != 0) && (item_minlvl > pc.getLevel()) && !pc.isGm()) {
					pc.sendPackets(new S_ServerMessage(318, String.valueOf(item_minlvl))); // 等级 %0以上才可使用此道具。
					return;
				}
				else if ((item_maxlvl != 0) && (item_maxlvl < pc.getLevel()) && !pc.isGm()) {
					pc.sendPackets(new S_PacketBox(S_PacketBox.MSG_LEVEL_OVER, item_maxlvl)); // 等级%d以下才能使用此道具。
					return;
				}

				// sosodemon add 物品声望控制
				else if (fameLV > pc.getFameLevel()) {
					pc.sendPackets(new S_SystemMessage(
							"您的声望值不足。 需要声望：【"+ fameLV +"】"));
					return;
				}
				// sosodemon end 物品声望控制
				
				if (((itemId == 40576) && !pc.isElf())				// 灵魂水晶（白）
						|| ((itemId == 40577) && !pc.isWizard())	// 灵魂水晶（黑）
						|| ((itemId == 40578) && !pc.isKnight())) {	// 灵魂水晶（赤）
					pc.sendPackets(new S_ServerMessage(264));		// \f1你的职业无法使用此道具。
					return;
				}

				if (l1iteminstance.getItem().getType() == 0) { // アロー
					pc.getInventory().setArrow(l1iteminstance.getItem().getItemId());
					pc.sendPackets(new S_ServerMessage(452, l1iteminstance.getLogName())); // %0%s 被选择了。
				}
				else if (l1iteminstance.getItem().getType() == 15) { // スティング
					pc.getInventory().setSting(l1iteminstance.getItem().getItemId());
					pc.sendPackets(new S_ServerMessage(452, // %0%s 被选择了。
							l1iteminstance.getLogName()));
				}
				else if (l1iteminstance.getItem().getType() == 16) { // treasure_box
					L1TreasureBox box = L1TreasureBox.get(itemId);

					if (box != null) {
						if (box.open(pc)) {
							L1EtcItem temp = (L1EtcItem) l1iteminstance.getItem();
							if (temp.get_delayEffect() > 0) {
								// 有限制再次使用的时间且可堆叠的道具
								if (l1iteminstance.isStackable()) {
									if (l1iteminstance.getCount() > 1) {
										isDelayEffect = true;
									}
									pc.getInventory().removeItem(l1iteminstance.getId(), 1);
								}
								else {
									isDelayEffect = true;
								}
							}
							else {
								pc.getInventory().removeItem(l1iteminstance.getId(), 1);
							}
						}
					}
				}
				else if (l1iteminstance.getItem().getType() == 2) { // light系アイテム
					if ((l1iteminstance.getRemainingTime() <= 0) && (itemId != 40004)) { // 魔法灯笼
						return;
					}
					if (l1iteminstance.isNowLighting()) {
						l1iteminstance.setNowLighting(false);
						pc.turnOnOffLight();
					}
					else {
						l1iteminstance.setNowLighting(true);
						pc.turnOnOffLight();
					}
					pc.sendPackets(new S_ItemName(l1iteminstance));
				}
				else if (l1iteminstance.getItem().getType() == 17) { // 魔法娃娃类
					MagicDoll.useMagicDoll(pc, itemId, itemObjid);
				}

				else if (l1iteminstance.getItem().getType() == 18) { // 道具融合系统类
					if (L1Blend.checkItemId(itemId) != 0) {
						L1Blend.getItemBlend(pc, l1iteminstance, itemId);
					} else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
				}

				else if (l1iteminstance.getItem().getType() == 19) { // 传送卷轴类 (DB化) by 丫杰
					if (L1WilliamTeleportScroll.checkItemId(itemId) != 0) {
						L1WilliamTeleportScroll.getTeleportScroll(pc,l1iteminstance, itemId);
					} else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
				}

				else if (itemId == 47103) { // 新鲜的饵
					pc.sendPackets(new S_ServerMessage(452, l1iteminstance.getLogName())); // %0%s 被选择了。
				}
				else if (itemId == 40003) { // 灯油
					for (L1ItemInstance lightItem : pc.getInventory().getItems()) {
						if (lightItem.getItem().getItemId() == 40002) {	// 灯笼
							lightItem.setRemainingTime(l1iteminstance.getItem().getLightFuel());
							pc.sendPackets(new S_ItemName(lightItem));
							pc.sendPackets(new S_ServerMessage(230));	// 你在灯笼里加满了新的灯油。
							break;
						}
					}
					pc.getInventory().removeItem(l1iteminstance, 1);
				}

				// TODO 新增 ////////////////////////////////////////////
				// 10%武器攻击卷轴
				else if (itemId == 5001) {
					L1ItemInstance check_item = pc.getInventory().getItem(l1iteminstance1.getId());
					if (check_item != null && check_item.getItem().getType2() == 1) { // 武器类
						if (l1iteminstance1.getUpdateCount() <= 0) { // 次数为0
							pc.sendPackets(new S_ServerMessage(166, l1iteminstance1.getLogName() + " 不可再镶嵌！"));
							return;
						}
						if (Random.nextInt(100) <= 10) {
							l1iteminstance1.setUpdateDmg(l1iteminstance1.getUpdateDmg() + 3); // 攻击力 +3
							l1iteminstance1.setUpdateDmgModifier(l1iteminstance1.getUpdateDmgModifier() + 5); // 额外攻击力 +5 
							l1iteminstance1.setUpdateHitModifier(l1iteminstance1.getUpdateHitModifier() + 2); // 攻击成功 +2
							l1iteminstance1.setUpdateCount(l1iteminstance1.getUpdateCount() - 1); // 次数减少1
							pc.sendPackets(new S_ServerMessage(166, l1iteminstance1.getLogName() + " 强化成功。"));
						} else {
							pc.sendPackets(new S_ServerMessage(166, l1iteminstance1.getLogName() + " 强化失败。"));
						}
						if (ItemUpdate.getInstance().checkItem(l1iteminstance1.getId()) == 0) { 
							ItemUpdate.getInstance().storeItem(l1iteminstance1);
						} else {
							ItemUpdate.getInstance().updateItem(l1iteminstance1);
						}
						pc.sendPackets(new S_ItemStatus(l1iteminstance1));
						pc.getInventory().removeItem(l1iteminstance, 1);
					} else {
						pc.sendPackets(new S_ServerMessage(79)); // 没有任何事情发生
					}
				}
				// 60%武器攻击卷轴
				else if (itemId == 5002) {
					L1ItemInstance check_item = pc.getInventory().getItem(l1iteminstance1.getId());
					if (check_item != null && check_item.getItem().getType2() == 1) { // 武器类
						if (l1iteminstance1.getUpdateCount() <= 0) { // 次数为0
							pc.sendPackets(new S_ServerMessage(166, l1iteminstance1.getLogName() + " 不可再镶嵌！"));
							return;
						}
						if (Random.nextInt(100) <= 60) {
							l1iteminstance1.setUpdateDmgModifier(l1iteminstance1.getUpdateDmgModifier() + 2); // 额外攻击力 +2
							l1iteminstance1.setUpdateHitModifier(l1iteminstance1.getUpdateHitModifier() + 1); // 攻击成功 +1
							l1iteminstance1.setUpdateCount(l1iteminstance1.getUpdateCount() - 1); // 次数减少1
							pc.sendPackets(new S_ServerMessage(166, l1iteminstance1.getLogName() + " 强化成功。"));
						} else {
							pc.sendPackets(new S_ServerMessage(166, l1iteminstance1.getLogName() + " 强化失败。"));
						}
						if (ItemUpdate.getInstance().checkItem(l1iteminstance1.getId()) == 0) {
							ItemUpdate.getInstance().storeItem(l1iteminstance1);
						}
						else {
							ItemUpdate.getInstance().updateItem(l1iteminstance1);
						}
						pc.sendPackets(new S_ItemStatus(l1iteminstance1));
						pc.getInventory().removeItem(l1iteminstance, 1);
					} else {
						pc.sendPackets(new S_ServerMessage(79)); // 没有任何事情发生
					}
				}
				// 100%武器攻击卷轴
				else if (itemId == 5003) {
					L1ItemInstance check_item = pc.getInventory().getItem(l1iteminstance1.getId());
					if (check_item != null && check_item.getItem().getType2() == 1) { // 武器类
						if (l1iteminstance1.getUpdateCount() <= 0) { // 次数为0
							pc.sendPackets(new S_ServerMessage(79));
							return;
						}
						l1iteminstance1.setUpdateCount(l1iteminstance1.getUpdateCount() - 1); // 次数减少1
						if (Random.nextInt(100) <= 50) {
							l1iteminstance1.setUpdateDmgModifier(l1iteminstance1.getUpdateDmgModifier() + 1); // 额外攻击力 +1  
							pc.sendPackets(new S_ServerMessage(166, l1iteminstance1.getLogName() + " 强化成功。"));
						} else {
							l1iteminstance1.setUpdateDmg(l1iteminstance1.getUpdateDmg() + 1); // 攻击力 +1
							l1iteminstance1.setUpdateDmgModifier(l1iteminstance1.getUpdateDmgModifier() + 1); // 额外攻击力 +1
							pc.sendPackets(new S_ServerMessage(166, l1iteminstance1.getLogName() + " 强化武器属性成功。"));
						}
						//pc.sendPackets(new S_ServerMessage(166, l1iteminstance1.getLogName() + " 强化失败"));   
						//}
						if (ItemUpdate.getInstance().checkItem(l1iteminstance1.getId()) == 0) {
							ItemUpdate.getInstance().storeItem(l1iteminstance1);
						} else {
							ItemUpdate.getInstance().updateItem(l1iteminstance1);
						}
						pc.sendPackets(new S_ItemStatus(l1iteminstance1));
						pc.getInventory().removeItem(l1iteminstance, 1);
					} else {
						pc.sendPackets(new S_ServerMessage(79)); // 没有任何事情发生
					}
				}
				// 武器力量卷轴
				else if (itemId == 5004) {
					L1ItemInstance check_item = pc.getInventory().getItem(l1iteminstance1.getId());
					if (check_item != null && check_item.getItem().getType2() == 1) { // 武器类
						if (l1iteminstance1.getUpdateCount() <= 0) { // 次数为0
							pc.sendPackets(new S_ServerMessage(166, l1iteminstance1.getLogName() + " 不可再镶嵌！"));
							return;
						}
						if (Random.nextInt(100) <= 10) {
						l1iteminstance1.setUpdateStr(l1iteminstance1.getUpdateStr() + 1); // 力量 +1
						if(l1iteminstance1.isEquipped()) {
							pc.addStr(1);
							pc.sendPackets(new S_OwnCharStatus(pc));
						}
						l1iteminstance1.setUpdateCount(l1iteminstance1.getUpdateCount() - 1); // 次数减少1
						pc.sendPackets(new S_ServerMessage(166, l1iteminstance1.getLogName() + " 强化成功。"));
						} else {
							pc.sendPackets(new S_ServerMessage(166, l1iteminstance1.getLogName() + " 强化失败"));
						}
						if (ItemUpdate.getInstance().checkItem(l1iteminstance1.getId()) == 0) {
							ItemUpdate.getInstance().storeItem(l1iteminstance1);
						} else {
							ItemUpdate.getInstance().updateItem(l1iteminstance1);
						}
						pc.sendPackets(new S_ItemStatus(l1iteminstance1));
						pc.getInventory().removeItem(l1iteminstance, 1);
					} else {
						pc.sendPackets(new S_ServerMessage(79)); // 没有任何事情发生
					}
				}
				// 武器敏捷卷轴
				else if (itemId == 5005) {
					L1ItemInstance check_item = pc.getInventory().getItem(l1iteminstance1.getId());
					if (check_item != null && check_item.getItem().getType2() == 1) { // 武器类
						if (l1iteminstance1.getUpdateCount() <= 0) { // 次数为0
							pc.sendPackets(new S_ServerMessage(166, l1iteminstance1.getLogName() + " 不可再镶嵌！"));
							return;
						}
						l1iteminstance1.setUpdateCount(l1iteminstance1.getUpdateCount() - 1); // 次数减少1
						if (Random.nextInt(100) <= 10) {
						l1iteminstance1.setUpdateDex(l1iteminstance1.getUpdateDex() + 1); // 敏捷 +1
						if(l1iteminstance1.isEquipped()) {
							pc.addDex(1);
							pc.sendPackets(new S_OwnCharStatus(pc));
						}
						pc.sendPackets(new S_ServerMessage(166, l1iteminstance1.getLogName() + " 强化成功。"));
						} else {
							pc.sendPackets(new S_ServerMessage(166, l1iteminstance1.getLogName() + " 强化失败"));
						}
						if (ItemUpdate.getInstance().checkItem(l1iteminstance1.getId()) == 0) {
							ItemUpdate.getInstance().storeItem(l1iteminstance1);
						} else {
							ItemUpdate.getInstance().updateItem(l1iteminstance1);
						}
						pc.sendPackets(new S_ItemStatus(l1iteminstance1));
						pc.getInventory().removeItem(l1iteminstance, 1);
					} else {
						pc.sendPackets(new S_ServerMessage(79)); // 没有任何事情发生
					}
				}
				// 武器智力卷轴
				else if (itemId == 5006) {
					L1ItemInstance check_item = pc.getInventory().getItem(l1iteminstance1.getId());
					if (check_item != null && check_item.getItem().getType2() == 1) { // 武器类
						if (l1iteminstance1.getUpdateCount() <= 0) { // 次数为0
							pc.sendPackets(new S_ServerMessage(166, l1iteminstance1.getLogName() + " 不可再镶嵌！"));
							return;
						}
						l1iteminstance1.setUpdateCount(l1iteminstance1.getUpdateCount() - 1); // 次数减少1
						if (Random.nextInt(100) <= 10) { 
						l1iteminstance1.setUpdateInt(l1iteminstance1.getUpdateInt() + 1); // 智力 +1
						if(l1iteminstance1.isEquipped()) {
							pc.addInt(1);
							pc.sendPackets(new S_OwnCharStatus(pc));
						}
						pc.sendPackets(new S_ServerMessage(166, l1iteminstance1.getLogName() + " 强化成功。"));
						} else {
							pc.sendPackets(new S_ServerMessage(166, l1iteminstance1.getLogName() + " 强化失败"));   
						}
						if (ItemUpdate.getInstance().checkItem(l1iteminstance1.getId()) == 0) {
							ItemUpdate.getInstance().storeItem(l1iteminstance1);
						} else {
							ItemUpdate.getInstance().updateItem(l1iteminstance1);
						}
						pc.sendPackets(new S_ItemStatus(l1iteminstance1));
						pc.getInventory().removeItem(l1iteminstance, 1);
					} else {
						pc.sendPackets(new S_ServerMessage(79)); // 没有任何事情发生
					}
				}
				// 盔甲体力卷轴
				else if (itemId == 5007) {
					L1ItemInstance check_item = pc.getInventory().getItem(l1iteminstance1.getId());
					if (check_item != null && check_item.getItem().getType2() == 2) { // 盔甲类
						if (Random.nextInt(100) <= 10) {
						l1iteminstance1.setUpdateHp(l1iteminstance1.getUpdateHp() + 10); // 体力 +10
						if(l1iteminstance1.isEquipped()) {
							pc.addMaxHp(10);
							pc.sendPackets(new S_OwnCharStatus(pc));
						}
						pc.sendPackets(new S_ServerMessage(166, l1iteminstance1.getLogName() + " 强化成功。"));
						} else {
							pc.sendPackets(new S_ServerMessage(166, l1iteminstance1.getLogName() + " 强化失败"));
						}
						if (ItemUpdate.getInstance().checkItem(l1iteminstance1.getId()) == 0) {
							ItemUpdate.getInstance().storeItem(l1iteminstance1);
						} else {
							ItemUpdate.getInstance().updateItem(l1iteminstance1);
						}
						pc.sendPackets(new S_ItemStatus(l1iteminstance1));
						pc.getInventory().removeItem(l1iteminstance, 1);
					} else {
						pc.sendPackets(new S_ServerMessage(79)); // 没有任何事情发生
					}
				}
				// 盔甲魔力卷轴 
				else if (itemId == 5008) {
					L1ItemInstance check_item = pc.getInventory().getItem(l1iteminstance1.getId());
					if (check_item != null && check_item.getItem().getType2() == 2) { // 盔甲类
						if (Random.nextInt(100) <= 10) { 
						l1iteminstance1.setUpdateMp(l1iteminstance1.getUpdateMp() + 10); // 魔力 +10
						if(l1iteminstance1.isEquipped()) {
							pc.addMaxMp(10);
							pc.sendPackets(new S_OwnCharStatus(pc));
						}
						pc.sendPackets(new S_ServerMessage(166, l1iteminstance1.getLogName() + " 强化成功。"));
						} else {
							pc.sendPackets(new S_ServerMessage(166, l1iteminstance1.getLogName() + " 强化失败"));   
						}
						if (ItemUpdate.getInstance().checkItem(l1iteminstance1.getId()) == 0) {
							ItemUpdate.getInstance().storeItem(l1iteminstance1); 
						} else {
							ItemUpdate.getInstance().updateItem(l1iteminstance1);
						}
						pc.sendPackets(new S_ItemStatus(l1iteminstance1));
						pc.getInventory().removeItem(l1iteminstance, 1);
					} else {
						pc.sendPackets(new S_ServerMessage(79)); // 没有任何事情发生
					}
				}

				// 商城状态
				else if (itemId >= 5009 && itemId <= 5023) {
					Potion.MallStatus(pc, l1iteminstance, itemId);
				}
				// 经验书 , 彩票
				else if ((itemId >= 5024 && itemId <= 5026)
						|| (itemId >= 5050 && itemId <= 5052)
						|| (itemId == 5054)) {
					Potion.ExperienceBook(pc, l1iteminstance, itemId);
				}
				// 特殊状态 暴击药水
				else if (itemId >= 5027 && itemId <= 5029) {
					Potion.PotionBuff(pc, itemId);
				}
				// 经验药水
				else if (itemId >= 5030 && itemId <= 5032) {
					Potion.PotionExpBuff(pc, itemId,l1iteminstance);
				}
				// 特殊药水 永久提升 HP MP
				else if (itemId >= 5033 && itemId <= 5038) {
					Potion.SpecialHP(pc, itemId,l1iteminstance);
				}
				// 声望相关
				else if (itemId >= 5041 && itemId <= 5049) {
					Potion.Prestige(pc, l1iteminstance, itemId);
				}
				// 装备保护卷轴
				else if (itemId == 5053) {
					if (l1iteminstance1 != null) {
						if (l1iteminstance1.getItem().get_safeenchant() <= -1) {
							pc.sendPackets(new S_ServerMessage(1309)); // 此装备无法使用装备保护卷轴。
							return;
						}
						if (l1iteminstance1.getproctect() == true) {
							pc.sendPackets(new S_ServerMessage(1300)); // 神秘力量的影响正在发生。
							return;
						}
						if (l1iteminstance1.getItem().getType2() == 0) {
							pc.sendPackets(new S_ServerMessage(79)); // 没有任何事情发生
							return;
						} else {
							l1iteminstance1.setproctect(true);
							pc.sendPackets(new S_ServerMessage(1308, l1iteminstance1.getLogName())); // %0受到魔法力量的保护。
							pc.getInventory().removeItem(l1iteminstance, 1);
						}
					}
				}
				// 解卡点卷轴　by xpatax
				else if (itemId == 5058) {
					Connection connection = null;
					connection = L1DatabaseFactory.getInstance().getConnection();
					PreparedStatement preparedstatement = connection.prepareStatement("UPDATE characters SET LocX=33442,LocY=32797,MapID=4 WHERE account_name=?");
					preparedstatement.setString(1, client.getAccountName());
					preparedstatement.execute();
					preparedstatement.close();
					connection.close();
					pc.sendPackets(new S_SystemMessage("除了你，其他角色已回到奇岩村。"));
					pc.getInventory().removeItem(l1iteminstance, 1);
				}
				// 变性药水
				else if (itemId == 5059) {
					pc.getInventory().takeoffEquip(945);
					if (pc.getPartnerId() != 0)
						pc.sendPackets(new S_SystemMessage("结婚中无法变性，本伺服器不支持断背山。"));
					if (pc.get_sex() == 0) {
						pc.set_sex(1);
						L1World.getInstance().broadcastPacketToAll(new S_BlueMessage(166, "\\fY【" + pc.getName() + "】变成性感美女萝！"));
						pc.sendPackets(new S_SystemMessage("下体似乎慢慢消肿了，胸膛似乎快要裂开了(.)人(.)。"));
					} else {
						pc.set_sex(0);
						L1World.getInstance().broadcastPacketToAll(new S_BlueMessage(166, "\\fX【" + pc.getName() + "】变成英俊帅哥萝！"));
						pc.sendPackets(new S_SystemMessage("你的全身发烫，下体突然肿大，似乎有东西要跑出来了oUo。"));
					}
					if (pc.getClassId() == 61)
						pc.setClassId(48);
					else
						if (pc.getClassId() == 48)
							pc.setClassId(61);
					if (pc.getClassId() == 138)
						pc.setClassId(37);
					else
						if (pc.getClassId() == 37)
							pc.setClassId(138);
					if (pc.getClassId() == 734)
						pc.setClassId(1186);
					else
						if (pc.getClassId() == 1186)
							pc.setClassId(734);
					if (pc.getClassId() == 2786)
						pc.setClassId(2796);
					else
						if (pc.getClassId() == 2796)
							pc.setClassId(2786);
					if (pc.getClassId() == 0)
						pc.setClassId(1);
					else
						if (pc.getClassId() == 1)
							pc.setClassId(0);
					if (pc.getClassId() == 6658)
						pc.setClassId(6661);
					else
						if (pc.getClassId() == 6661)
							pc.setClassId(6658);
					if (pc.getClassId() == 6671)
						pc.setClassId(6650);
					else
						if (pc.getClassId() == 6650)
							pc.setClassId(6671);
					pc.sendPackets(new S_OwnCharStatus(pc));
					pc.sendPackets(new S_SkillSound(itemId, 1183));
					pc.broadcastPacket(new S_SkillSound(itemId, 1183));
					pc.getInventory().removeItem(l1iteminstance, 1);
					pc.save();
					pc.sendPackets(new S_OwnCharPack(pc));
					pc.broadcastPacket(new S_OtherCharPacks(pc));
					pc.removeAllKnownObjects();
					//break label1;
				}
				// 洗血药水
				else if (itemId == 5060) {
					if (pc.getLevel() <= 76) { // 多少级以上才可以使用
						pc.sendPackets(new S_ServerMessage(318,"76 ")); // 等级 %0以上才可使用此道具。
						return;
					}
						pc.sendPackets(new S_SkillSound(pcObjid, 6505));
						pc.broadcastPacket(new S_SkillSound(pcObjid, 6505));
						pc.getInventory().takeoffEquip(945); // 用来脱掉全身装备
						pc.setExp(10000);
						pc.resetLevel();
						pc.addBaseMaxHp((short)(-1 * (int)((double) pc.getBaseMaxHp() - 12)));
			        	pc.addBaseMaxMp((short)(-1 * (int)((double) pc.getBaseMaxMp() - 12)));
						pc.resetBaseAc();
						pc.resetBaseMr();
						pc.resetBaseHitup();
						pc.resetBaseDmgup();
						pc.sendPackets(new S_OwnCharStatus(pc));
						pc.sendPackets(new S_ServerMessage(822)); // 你感受到体内深处产生一股不明力量。
						L1World.getInstance().broadcastPacketToAll(new S_BlueMessage(1429,"" + pc.getName() + "")); // \fT【温馨提示】: 玩家【%0%s】想不开喝自杀药水了！！！
						pc.getInventory().removeItem(l1iteminstance, 1);
				}
				// 画面更新卷轴
			    else if (itemId == 5061) {
			    	L1Teleport.teleport(pc, pc.getX() + Random.nextInt(1) , pc.getY() + Random.nextInt(-1) , (short)pc.getMapId(), pc.getHeading(), true);
			    	pc.getInventory().removeItem(l1iteminstance, 1);
			    }
				// BOSS雷达
			    else if (itemId == 5062) {
			    	boolean hasboss = false;
			    	String bossname = "";
			    	int bossX = 0;
			    	int bossY = 0;
			    	int bossMAP = 0;
			    	for (L1Object object : L1World.getInstance().getObject()) {
			    		if (object instanceof L1MonsterInstance) {
			    			L1MonsterInstance boss = (L1MonsterInstance) object;
			    			if (boss.getMapId()==pc.getMapId()&&!boss.isDead()) {
			    				switch(boss.getNpcTemplate().get_npcId()) {
			    				case 45601: // 更改指定怪物请改这
			    				case 45600:
			    					//if (boss.getNpcTemplate().getBroad() == 1 ? true : false) {//参考怪死广播
			    					hasboss = true;
			    					bossname = boss.getName();
			    					bossX = boss.getX();
			    					bossY = boss.getY();
			    					bossMAP = pc.getMapId();
			    					break;
			    				}
			    			}
			    		}
			    	}
			    	pc.sendPackets(new S_SystemMessage("使用野狼的嗅觉，找寻目标......"));
			    	if(hasboss==true) {
			    		pc.sendPackets(new S_SystemMessage("从 " + bossX + "," + bossY + " 处，传来 " + bossname + " 的气味~~~")); // 提示讯息
			    		L1Teleport.teleport(pc, bossX, bossY, (short)bossMAP, pc.getHeading(), true); // 直接飞到BOSS旁
			    	} else {
			    		pc.sendPackets(new S_SystemMessage("这里已经没有BOSS的气味，好像晚了一步..."));
			    	}
			    }
				// TODO 殷海萨祝福药水
			    else if (itemId == 5075) {
			    	if (pc.getAinPoint() <= 75) {
			    		pc.setAinPoint(pc.getAinPoint() + 25); // 点数 + 1
		                pc.getInventory().removeItem(l1iteminstance, 1);
			            pc.save();
			    		pc.sendPackets(new S_SystemMessage("殷海萨祝福点数增加25。"));
			    		} else {
			    		pc.sendPackets(new S_SystemMessage("无法使用。"));
			    	}
			    }
			    else if (itemId == 5076) {
			    	if (pc.getAinPoint() <= 50) {
			    		pc.setAinPoint(pc.getAinPoint() + 50); // 点数 + 1
		                pc.getInventory().removeItem(l1iteminstance, 1);
			            pc.save();
			    		pc.sendPackets(new S_SystemMessage("殷海萨祝福点数增加50。"));
			    		} else {
			    		pc.sendPackets(new S_SystemMessage("无法使用。"));
			    	}
			    }
			    else if (itemId == 5077) {
			    	if (pc.getAinPoint() <= 0) {
			    		pc.setAinPoint(pc.getAinPoint() + 100); // 点数 + 1
		                pc.getInventory().removeItem(l1iteminstance, 1);
			            pc.save();
			    		pc.sendPackets(new S_SystemMessage("殷海萨祝福点数增加100。"));
			    		} else {
			    		pc.sendPackets(new S_SystemMessage("无法使用。"));
			    	}
			    }

				// 活动卷轴 (限制活动开始时间为(24h) 0点 4点 8点 12点 16点 20点 每天共六次, 每次2小时) by mca 20090113
			    else if (itemId == 5078) {
			    	if(pc.isGm()
			    			|| GetNowTime.GetNowHour() == 0 // 系统时间 (现实时间)
			    			|| GetNowTime.GetNowHour() == 4
			    			|| GetNowTime.GetNowHour() == 8
			    			|| GetNowTime.GetNowHour() == 12
			    			|| GetNowTime.GetNowHour() == 16
			    			|| GetNowTime.GetNowHour() == 20) {
			    		if (pc.getMap().isEscapable() || pc.isGm()) {
			    			L1Teleport.teleport(pc, ((L1EtcItem) l1iteminstance
			    					.getItem()).get_locx(),
			    					((L1EtcItem) l1iteminstance.getItem())
			    					.get_locy(),
			    					((L1EtcItem) l1iteminstance.getItem())
			    					.get_mapid(), 5, true);
			    			pc.setSkillEffect(1800, 1); // 启动计时器
			    			pc.getInventory().removeItem(l1iteminstance, 1);
			    		} else {
			    			pc.sendPackets(new S_ServerMessage(647)); // 这附近的能量影响到瞬间移动。在此地无法使用瞬间移动。
			    		}
			    		cancelAbsoluteBarrier(pc); // 解除绝对屏障状态
			    	} else {
			    		pc.sendPackets(new S_BlueMessage(166,"\\f3","现在不是活动时间,无法使用"));
			    	}
			    }
				// TODO 新增 end //////////////////////////////////////

				// 返生药水
				else if (itemId == 43000) {
					String name = pc.getName();
					if (pc.getLevel() <= 98) { // 98级以下无法使用
						pc.sendPackets(new S_ServerMessage(318,"98 ")); // 等级 %0以上才可使用此道具。
						return;
					}
						pc.setExp(1);
						pc.resetLevel();						// 等级重算
						pc.setLevelmet(pc.getLevelmet() + 1);	// 转生次数+1
						pc.getInventory().takeoffEquip(945);	// 用来脱掉全身装备
						pc.setBonusStats(0);					// 可以继续点属性？
						// add 转生可设定血魔保留多少 by eric1300460
						pc.setBaseMaxHp((short)(pc.getBaseMaxHp()*Config.EVOLUTION_HP/100));
						pc.setBaseMaxMp((short)(pc.getBaseMaxMp()*Config.EVOLUTION_MP/100));
						pc.setMaxHp(pc.getBaseMaxHp());
						pc.setMaxMp(pc.getBaseMaxMp());
						pc.setCurrentHp(pc.getMaxHp());
						pc.setCurrentMp(pc.getMaxMp());
						// end 转生可设定血魔保留多少 by eric1300460
						pc.sendPackets(new S_SkillSound(pcObjid, 2568)); // 风龙降临动画。
		                pc.broadcastPacket(new S_SkillSound(pcObjid, 2568));
						pc.sendPackets(new S_ServerMessage(822)); // 你感受到体内深处产生一股不明力量。
						L1World.getInstance().broadcastPacketToAll(new S_BlueMessage(1432,name,"" + pc.getLevelmet() + "")); // \f=【温馨提示】: 玩家【%0%s】爆肝修炼后第【%1】次转生。
						pc.sendPackets(new S_OwnCharStatus(pc));
						pc.getInventory().removeItem(l1iteminstance, 1);
						pc.save(); // 将数据存入DB资料库
						// 处理新手保护系统(遭遇的守护)状态资料的变动
						pc.checkNoviceType();
				}
				// 万能药:力量
				else if (itemId == 40033) {
					if (pc.getBaseStr() < Config.BONUS_STATS3 && pc.getElixirStats() < Config.BONUS_STATS2) { // 调整能力值上限
						pc.addBaseStr((byte) 1); // STR + 1
						pc.setElixirStats(pc.getElixirStats() + 1);
						pc.getInventory().removeItem(l1iteminstance, 1);
						pc.sendPackets(new S_OwnCharStatus2(pc));
						pc.save(); // 将数据存入DB资料库
					} else {
						if (pc.getBaseStr() >= Config.BONUS_STATS3)
							pc.sendPackets(new S_ServerMessage(1430,"Str " + Config.BONUS_STATS3 + "")); // \f1能力值 %0%s %1 以后不能喝万能药！
						if (pc.getElixirStats() >= Config.BONUS_STATS2)
							pc.sendPackets(new S_ServerMessage(1431,"" + Config.BONUS_STATS2 + "")); // \f1万能药只能喝 %0 瓶。
					}
				}
				// 万能药:体质
				else if (itemId == 40034) {
					if (pc.getBaseCon() < Config.BONUS_STATS3 && pc.getElixirStats() < Config.BONUS_STATS2) { // 调整能力值上限
						pc.addBaseCon((byte) 1); // CON + 1
						pc.setElixirStats(pc.getElixirStats() + 1);
						pc.getInventory().removeItem(l1iteminstance, 1);
						pc.sendPackets(new S_OwnCharStatus2(pc));
						pc.save(); // 将数据存入DB资料库
					} else {
						if (pc.getBaseCon() >= Config.BONUS_STATS3)
							pc.sendPackets(new S_ServerMessage(1430,"Con " + Config.BONUS_STATS3 + "")); // \f1能力值 %0%s %1 以后不能喝万能药！
						if (pc.getElixirStats() >= Config.BONUS_STATS2)
							pc.sendPackets(new S_ServerMessage(1431,"" + Config.BONUS_STATS2 + "")); // \f1万能药只能喝 %0 瓶。
					}
				}
				// 万能药:敏捷
				else if (itemId == 40035) {
					if (pc.getBaseDex() < Config.BONUS_STATS3 && pc.getElixirStats() < Config.BONUS_STATS2) { // 调整能力值上限
						pc.addBaseDex((byte) 1); // DEX + 1
						pc.resetBaseAc();
						pc.setElixirStats(pc.getElixirStats() + 1);
						pc.getInventory().removeItem(l1iteminstance, 1);
						pc.sendPackets(new S_OwnCharStatus2(pc));
						pc.save(); // 将数据存入DB资料库
					} else {
						if (pc.getBaseDex() >= Config.BONUS_STATS3)
							pc.sendPackets(new S_ServerMessage(1430,"Dex " + Config.BONUS_STATS3 + "")); // \f1能力值 %0%s %1 以后不能喝万能药！
						if (pc.getElixirStats() >= Config.BONUS_STATS2)
							pc.sendPackets(new S_ServerMessage(1431,"" + Config.BONUS_STATS2 + "")); // \f1万能药只能喝 %0 瓶。
					}
				}
				// 万能药:智力
				else if (itemId == 40036) {
					if (pc.getBaseInt() < Config.BONUS_STATS3 && pc.getElixirStats() < Config.BONUS_STATS2) { // 调整能力值上限
						pc.addBaseInt((byte) 1); // INT + 1
						pc.setElixirStats(pc.getElixirStats() + 1);
						pc.getInventory().removeItem(l1iteminstance, 1);
						pc.sendPackets(new S_OwnCharStatus2(pc));
						pc.save(); // 将数据存入DB资料库
					} else {
						if (pc.getBaseInt() >= Config.BONUS_STATS3)
							pc.sendPackets(new S_ServerMessage(1430,"Int " + Config.BONUS_STATS3 + "")); // \f1能力值 %0%s %1 以后不能喝万能药！
						if (pc.getElixirStats() >= Config.BONUS_STATS2)
							pc.sendPackets(new S_ServerMessage(1431,"" + Config.BONUS_STATS2 + "")); // \f1万能药只能喝 %0 瓶。
					}
				}
				// 万能药:精神
				else if (itemId == 40037) {
					if (pc.getBaseWis() < Config.BONUS_STATS3 && pc.getElixirStats() < Config.BONUS_STATS2) { // 调整能力值上限
						pc.addBaseWis((byte) 1); // WIS + 1
						pc.resetBaseMr();
						pc.setElixirStats(pc.getElixirStats() + 1);
						pc.getInventory().removeItem(l1iteminstance, 1);
						pc.sendPackets(new S_OwnCharStatus2(pc));
						pc.save(); // 将数据存入DB资料库
					} else {
						if (pc.getBaseWis() >= Config.BONUS_STATS3)
							pc.sendPackets(new S_ServerMessage(1430,"Wis " + Config.BONUS_STATS3 + "")); // \f1能力值 %0%s %1 以后不能喝万能药！
						if (pc.getElixirStats() >= Config.BONUS_STATS2)
							pc.sendPackets(new S_ServerMessage(1431,"" + Config.BONUS_STATS2 + "")); // \f1万能药只能喝 %0 瓶。
					}
				}
				// 万能药:魅力
				else if (itemId == 40038) {
					if (pc.getBaseCha() < Config.BONUS_STATS3 && pc.getElixirStats() < Config.BONUS_STATS2) { // 调整能力值上限
						pc.addBaseCha((byte) 1); // CHA + 1
						pc.setElixirStats(pc.getElixirStats() + 1);
						pc.getInventory().removeItem(l1iteminstance, 1);
						pc.sendPackets(new S_OwnCharStatus2(pc));
						pc.save(); // 将数据存入DB资料库
					} else {
						if (pc.getBaseCha() >= Config.BONUS_STATS3)
							pc.sendPackets(new S_ServerMessage(1430,"Cha " + Config.BONUS_STATS3 + "")); // \f1能力值 %0%s %1 以后不能喝万能药！
						if (pc.getElixirStats() >= Config.BONUS_STATS2)
							pc.sendPackets(new S_ServerMessage(1431,"" + Config.BONUS_STATS2 + "")); // \f1万能药只能喝 %0 瓶。
					}
				}
				// 治愈药水、浓缩体力恢复剂、象牙塔治愈药水
				else if ((itemId == L1ItemId.POTION_OF_HEALING) || (itemId == L1ItemId.CONDENSED_POTION_OF_HEALING) || (itemId == 40029)) {
					Potion.UseHeallingPotion(pc, l1iteminstance, 15, 189);
				}
				else if (itemId == 40022) { // 古代体力恢复剂
					Potion.UseHeallingPotion(pc, l1iteminstance, 20, 189);
				}
				else if ((itemId == L1ItemId.POTION_OF_EXTRA_HEALING) // 强力治愈药水、浓缩强力体力恢复剂
						|| (itemId == L1ItemId.CONDENSED_POTION_OF_EXTRA_HEALING)) {
					Potion.UseHeallingPotion(pc, l1iteminstance, 45, 194);
				}
				else if (itemId == 40023) { // 古代强力体力恢复剂
					Potion.UseHeallingPotion(pc, l1iteminstance, 30, 194);
				}
				else if ((itemId == L1ItemId.POTION_OF_GREATER_HEALING) // 终极治愈药水
						// 浓缩终极体力恢复剂、凝聚的化合物、鲜奶油蛋糕、神秘的体力药水
						|| (itemId == L1ItemId.CONDENSED_POTION_OF_GREATER_HEALING)
						|| (itemId == 47114) || (itemId == 49137) || (itemId == 41141)) {
					Potion.UseHeallingPotion(pc, l1iteminstance, 75, 197);
				}
				else if (itemId == 40024) { // 古代终极体力恢复剂
					Potion.UseHeallingPotion(pc, l1iteminstance, 55, 197);
				}
				else if (itemId == 40506) { // 安特的水果
					Potion.UseHeallingPotion(pc, l1iteminstance, 70, 197);
				}
				else if ((itemId == 40026) || (itemId == 40027) || (itemId == 40028)) { // 香蕉汁、橘子汁、苹果汁
					Potion.UseHeallingPotion(pc, l1iteminstance, 25, 189);
				}
				else if ((itemId == 40058) || (itemId == 49268) || (itemId == 49269)) { // 烟熏的面包屑、爱玛伊的画像、伊森之画像
					Potion.UseHeallingPotion(pc, l1iteminstance, 30, 189);
				}
				else if (itemId == 40071) { // 烤焦的面包屑
					Potion.UseHeallingPotion(pc, l1iteminstance, 70, 197);
				}
				else if (itemId == 40734) { // 信赖货币
					Potion.UseHeallingPotion(pc, l1iteminstance, 50, 189);
				}
				else if (itemId == L1ItemId.B_POTION_OF_HEALING) { // 受祝福的 治愈药水
					Potion.UseHeallingPotion(pc, l1iteminstance, 25, 189);
				}
				else if (itemId == L1ItemId.C_POTION_OF_HEALING) { // 受咀咒的 治愈药水
					Potion.UseHeallingPotion(pc, l1iteminstance, 10, 189);
				}
				else if (itemId == L1ItemId.B_POTION_OF_EXTRA_HEALING) { // 受祝福的 强力治愈药水
					Potion.UseHeallingPotion(pc, l1iteminstance, 55, 194);
				}
				else if (itemId == L1ItemId.B_POTION_OF_GREATER_HEALING) { // 受祝福的 终极治愈药水
					Potion.UseHeallingPotion(pc, l1iteminstance, 85, 197);
				}
				else if (itemId == 140506) { // 受祝福的 安特的水果
					Potion.UseHeallingPotion(pc, l1iteminstance, 80, 197);
				}
				else if (itemId == 40043) { // 兔子的肝
					Potion.UseHeallingPotion(pc, l1iteminstance, 600, 189);
				}
				else if (itemId == 41403) { // 库杰的粮食
					Potion.UseHeallingPotion(pc, l1iteminstance, 300, 189);
				}
				else if ((itemId >= 41417) && (itemId <= 41421)) { // 日本「亚丁的夏天」活动道具 - 刨冰
					Potion.UseHeallingPotion(pc, l1iteminstance, 90, 197);
					pc.getInventory().removeItem(l1iteminstance, 1);
				}
				else if (itemId == 41337) { // 受祝福的五谷面包
					Potion.UseHeallingPotion(pc, l1iteminstance, 85, 197);
				}
				else if (itemId == 40858) { // liquor（酒）
					pc.setDrink(true);
					pc.sendPackets(new S_Liquor(pc.getId(), 1));
					pc.getInventory().removeItem(l1iteminstance, 1);
				}
				else if ((itemId == L1ItemId.POTION_OF_CURE_POISON) || (itemId == 40507)) { // 翡翠药水
					if (pc.hasSkillEffect(DECAY_POTION)) { // 药水霜化术状态
						pc.sendPackets(new S_ServerMessage(698)); // 喉咙灼热，无法喝东西。
						return;
					} else {
						pc.sendPackets(new S_SkillSound(pc.getId(), 192));
						pc.broadcastPacket(new S_SkillSound(pc.getId(), 192));
						if (itemId == L1ItemId.POTION_OF_CURE_POISON) {
							pc.getInventory().removeItem(l1iteminstance, 1);
						} else if (itemId == 40507) {
							pc.getInventory().removeItem(l1iteminstance, 1);
						}

						pc.curePoison();
					}
				}
				else if ((itemId == L1ItemId.POTION_OF_HASTE_SELF) || (itemId == L1ItemId.B_POTION_OF_HASTE_SELF
						// 自我加速药水、受祝福的 自我加速药水
						) || (itemId == 40018 // 强力 自我加速药水
						) || (itemId == 140018 // 受祝福的 强力 自我加速药水
						) || (itemId == 40039 // 红酒
						) || (itemId == 40040 // 威士忌
						) || (itemId == 40030 // 象牙塔加速药水
						) || (itemId == 41338 // 受祝福的葡萄酒
						) || (itemId == 41261 // 饭团
						) || (itemId == 41262 // 鸡肉串烧
						) || (itemId == 41268 // 小比萨
						) || (itemId == 41269 // 烤玉米
						) || (itemId == 41271 // 爆米花
						) || (itemId == 41272 // 甜不辣
						) || (itemId == 41273 // 松饼
						) || (itemId == 41342 // 梅杜莎之血
						) || (itemId == 49302 // 福利加速药水
						) || (itemId == 49140 // 绿茶蛋糕卷
						)) {
					Potion.useGreenPotion(pc, l1iteminstance, itemId);
				}
				else if ((itemId == L1ItemId.POTION_OF_EMOTION_BRAVERY) // 勇敢药水
						|| (itemId == L1ItemId.B_POTION_OF_EMOTION_BRAVERY) // 受祝福的 勇敢药水
						|| (itemId == L1ItemId.POTION_OF_REINFORCED_CASE) // 强化勇气的药水
						|| (itemId == L1ItemId.W_POTION_OF_EMOTION_BRAVERY)) { // 福利勇敢药水
					if (pc.isKnight()) { // 骑士
						Potion.Brave(pc, l1iteminstance, itemId);
					} else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
						pc.getInventory().removeItem(l1iteminstance, 1);
					}
				}
				// 修改 生命之树果实 只有GM可使用 (堵天神祝福漏洞)
				else if (itemId == L1ItemId.FORBIDDEN_FRUIT) { // 生命之树果实
					//if (pc.isDragonKnight() || pc.isIllusionist()) { // 龙骑士、幻术师
					if (pc.isGm()) {
						Potion.Brave(pc, l1iteminstance, itemId);
					} else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
						pc.getInventory().removeItem(l1iteminstance, 1);
					}
				}
				else if ((itemId == L1ItemId.ELVEN_WAFER) // 精灵饼干
						|| (itemId == L1ItemId.B_ELVEN_WAFER) // 受祝福的 精灵饼干
						|| (itemId == L1ItemId.W_POTION_OF_FOREST)) { // 福利森林药水
					if (pc.isElf()) { // 妖精
						Potion.Brave(pc, l1iteminstance, itemId);
					} else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
						pc.getInventory().removeItem(l1iteminstance, 1);
					}
				}
				else if (itemId == L1ItemId.DEVILS_BLOOD) { // 恶魔之血
					if (pc.isCrown()) { // 王族
						Potion.Brave(pc, l1iteminstance, itemId);
					} else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
						pc.getInventory().removeItem(l1iteminstance, 1);
					}
				}
				// 修改 名誉货币 可全职使用 (堵天神祝福漏洞)
				else if (itemId == L1ItemId.COIN_OF_REPUTATION) { // 名誉货币
					/*if (pc.isDragonKnight() && pc.isIllusionist()) { // 龙骑士与幻术师无法使用
						Potion.Brave(pc, l1iteminstance, itemId);
					} else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
						pc.getInventory().removeItem(l1iteminstance, 1);
					}*/
					Potion.Brave(pc, l1iteminstance, itemId);
				}
				else if (itemId == L1ItemId.CHOCOLATE_CAKE) { // 巧克力蛋糕
					Potion.ThirdSpeed(pc, l1iteminstance, 600);
				}
				else if ((itemId >= L1ItemId.POTION_OF_EXP_150)
						&& (itemId <= L1ItemId.SCROLL_FOR_ENCHANTING_BATTLE)) { // 150%神力药水 ~ 强化战斗卷轴
					Effect.useEffectItem(pc, l1iteminstance);
				}
				else if ((itemId == 40066) || (itemId == 41413)) { // 年糕、月饼
					Potion.UseMpPotion(pc, l1iteminstance, 7, 6);
				}
				else if ((itemId == 40067) || (itemId == 41414)) { // 艾草年糕、福月饼
					Potion.UseMpPotion(pc, l1iteminstance, 15, 16);
				}
				else if (itemId == 40735) { // 勇气货币
					Potion.UseMpPotion(pc, l1iteminstance, 60, 0);
				}
				else if ((itemId == 40042) || (itemId == 41142)) { // 精神药水、神秘的魔力药水
					Potion.UseMpPotion(pc, l1iteminstance, 50, 0);
				}
				else if (itemId == 41404) { // 库杰的灵药
					Potion.UseMpPotion(pc, l1iteminstance, 80, 21);
				}
				else if (itemId == 41412) { // 金粽子
					Potion.UseMpPotion(pc, l1iteminstance, 5, 16);
				}
				else if ((itemId == 40032) || (itemId == 40041) // 伊娃的祝福、人鱼之鳞
						|| (itemId == 41344) || (itemId == 49303)) { // 水中的水、福利呼吸药水
					Potion.useBlessOfEva(pc, l1iteminstance, itemId);
				}
				else if ((itemId == L1ItemId.POTION_OF_MANA) // 蓝色药水
						|| (itemId == L1ItemId.B_POTION_OF_MANA // 受祝福的 蓝色药水
						|| (itemId == 40736) || (itemId == 49306))) { // 智慧货币、福利蓝色药水
					Potion.useBluePotion(pc, l1iteminstance, itemId);
				}
				else if ((itemId == L1ItemId.POTION_OF_EMOTION_WISDOM) // 慎重药水
						|| (itemId == L1ItemId.B_POTION_OF_EMOTION_WISDOM) // 受祝福的 慎重药水
						|| (itemId == 49307)) { // 福利慎重药水
					if (pc.isWizard()) { // 法师
						Potion.useWisdomPotion(pc, l1iteminstance, itemId);
					} else {
						pc.sendPackets(new S_ServerMessage(79));
						pc.getInventory().removeItem(l1iteminstance, 1);
					}
				}
				else if (itemId == L1ItemId.POTION_OF_BLINDNESS) { // 黑色药水
					Potion.useBlindPotion(pc, l1iteminstance);
				}
				else if ((itemId == 40088) || (itemId == 40096)
						|| (itemId == 49308) || (itemId == 140088)) { // 变形卷轴、福利变形药水
					if (usePolyScroll(pc, itemId, s)) {
						pc.getInventory().removeItem(l1iteminstance, 1);
					} else {
						pc.sendPackets(new S_ServerMessage(181)); // \f1无法变成你指定的怪物。
					}
				}
				else if ((itemId == 41154 // 暗之鳞
						) || (itemId == 41155 // 火之鳞
						) || (itemId == 41156 // 叛之鳞
						) || (itemId == 41157 // 恨之鳞
						) || (itemId == 49220)) { // 妖魔密使变形卷轴
					usePolyScale(pc, itemId);
					pc.getInventory().removeItem(l1iteminstance, 1);
				}
				else if ((itemId == 41143 // 海贼骷髅首领变身药水
						) || (itemId == 41144 // 海贼骷髅士兵变身药水
						) || (itemId == 41145 // 海贼骷髅刀手变身药水
						) || (itemId == 49149 // 夏纳的变身卷轴(等级30)
						) || (itemId == 49150 // 夏纳的变身卷轴(等级40)
						) || (itemId == 49151 // 夏纳的变身卷轴(等级52)
						) || (itemId == 49152 // 夏纳的变身卷轴(等级55)
						) || (itemId == 49153 // 夏纳的变身卷轴(等级60)
						) || (itemId == 49154 // 夏纳的变身卷轴(等级65)
						) || (itemId == 49155 // 夏纳的变身卷轴(等级70)
						) || (itemId == 49139 // 起司蛋糕
						)) {
					usePolyPotion(pc, itemId);
					pc.getInventory().removeItem(l1iteminstance, 1);
				}
				else if (itemId == 40317) { // 磨刀石
					// 武器か防具の场合のみ
					if ((l1iteminstance1.getItem().getType2() != 0) && (l1iteminstance1.get_durability() > 0)) {
						String msg0;
						pc.getInventory().recoveryDamage(l1iteminstance1);
						msg0 = l1iteminstance1.getLogName();
						if (l1iteminstance1.get_durability() == 0) {
							pc.sendPackets(new S_ServerMessage(464, msg0)); // %0 现在变成像个新的一样。
						}
						else {
							pc.sendPackets(new S_ServerMessage(463, msg0)); // %0 变好多了。
						}
					}
					else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
					pc.getInventory().removeItem(l1iteminstance, 1);
				}
				else if (itemId >= 47017 && itemId <= 47023) { // 龙之魔眼
					Effect.useEffectItem(pc, l1iteminstance);
				}
				else if (itemId == 47041) { // 地龙之精致魔眼、水龙之精致魔眼
					if (l1iteminstance1.getItem().getItemId() == 47042) { // 水龙之精致魔眼
						pc.getInventory().consumeItem(47041, 1);
						pc.getInventory().consumeItem(47042, 1);
						createNewItem(pc, 47021, 1); // 诞生之魔眼
					} else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
				}
				else if (itemId == 47042) { // 水龙之精致魔眼
					if (l1iteminstance1.getItem().getItemId() == 47041) { // 地龙之精致魔眼、水龙之精致魔眼
						pc.getInventory().consumeItem(47041, 1);
						pc.getInventory().consumeItem(47042, 1);
						createNewItem(pc, 47021, 1); // 诞生之魔眼
					} else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
				}
				else if (itemId == 47043) { // 风龙之精致魔眼
					if (l1iteminstance1.getItem().getItemId() == 47045) { // 诞生之精致魔眼
						pc.getInventory().consumeItem(47043, 1);
						pc.getInventory().consumeItem(47045, 1);
						createNewItem(pc, 47022, 1); // 形象之魔眼
					} else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
				}
				else if (itemId == 47045) { // 诞生之精致魔眼
					if (l1iteminstance1.getItem().getItemId() == 47043) { // 风龙之精致魔眼
						pc.getInventory().consumeItem(47043, 1);
						pc.getInventory().consumeItem(47045, 1);
						createNewItem(pc, 47022, 1); // 形象之魔眼
					} else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
				}
				else if (itemId == 47044) { // 火龙之精致魔眼
					if (l1iteminstance1.getItem().getItemId() == 47046) { // 形象之精致魔眼
						pc.getInventory().consumeItem(47044, 1);
						pc.getInventory().consumeItem(47046, 1);
						createNewItem(pc, 47023, 1); // 生命之魔眼
					} else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
				}
				else if (itemId == 47046) { // 形象之精致魔眼
					if (l1iteminstance1.getItem().getItemId() == 47044) { // 火龙之精致魔眼
						pc.getInventory().consumeItem(47044, 1);
						pc.getInventory().consumeItem(47046, 1);
						createNewItem(pc, 47023, 1); // 生命之魔眼
					} else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
				}
				else if (itemId >= 47049 && itemId <= 47052) { // XX附魔转换卷轴
					if (l1iteminstance1.getItemId() >= 47053
							&& l1iteminstance1.getItemId() <= 47062) { // 附魔石 ~ 9阶附魔石
						int type = (itemId - 47048) * 10; // type = 10,20,30,40
						int rnd = Random.nextInt(100) + 1;
						if (Config.MAGIC_STONE_TYPE < rnd) {
							int newItem = l1iteminstance1.getItemId() + type; // 附魔石(近战) ~ 9阶附魔石(近战)
							L1Item template = ItemTable.getInstance().getTemplate(newItem);
							if (template == null) {
								pc.sendPackets(new S_ServerMessage(79));
							}
							createNewItem(pc, newItem, 1); // 获得附魔石(XX)
						} else {
							pc.sendPackets(new S_ServerMessage(1411, l1iteminstance1.getName())); // 对\f1%0附加魔法失败。
						}
						pc.getInventory().removeItem(l1iteminstance1, 1); // 删除 - 附魔石
						pc.getInventory().removeItem(l1iteminstance, 1); // 删除 - 附魔转换卷轴
					} else {
						pc.sendPackets(new S_ServerMessage(79));
					}
				}
				else if (itemId == 47048) { // 附魔强化卷轴
					int item_id = l1iteminstance1.getItemId();
					if ((item_id < 47053) || (item_id > 47102)
							|| (item_id == 47062) || (item_id == 47072)
							|| (item_id == 47082) || (item_id == 47092)
							|| (item_id == 47102)) {
						pc.sendPackets(new S_ServerMessage(79));
						return;
					}

					int rnd = Random.nextInt(100) + 1;
					if (Config.MAGIC_STONE_LEVEL < rnd
							|| (item_id >= 47053 && item_id <= 47056)
							|| (item_id >= 47063 && item_id <= 47066)
							|| (item_id >= 47073 && item_id <= 47076)
							|| (item_id >= 47083 && item_id <= 47086)
							|| (item_id >= 47093 && item_id <= 47096)) {
						int newItem = l1iteminstance1.getItemId() + 1; // X 阶附魔石 -> X+1 阶附魔石
						L1Item template = ItemTable.getInstance().getTemplate(newItem);
						if (template == null) {
							pc.sendPackets(new S_ServerMessage(79)); // 没有任何事情发生。
							return;
						}
						pc.sendPackets(new S_ServerMessage(1410, l1iteminstance1.getName())); // 对\f1%0附加强大的魔法力量成功。

						l1iteminstance1.setItem(template);
						pc.getInventory().updateItem(l1iteminstance1, L1PcInventory.COL_ITEMID);
						pc.getInventory().saveItem(l1iteminstance1, L1PcInventory.COL_ITEMID);
					} else {
						pc.sendPackets(new S_ServerMessage(1411, l1iteminstance1.getName())); // 对\f1%0附加魔法失败。
						pc.getInventory().removeItem(l1iteminstance1, 1);
					}
					pc.getInventory().removeItem(l1iteminstance, 1);
				}
				else if ((itemId >= 47064 && itemId <= 47067) || (itemId >= 47074 && itemId <= 47077)
						|| (itemId >= 47084 && itemId <= 47087) || (itemId >= 47094 && itemId <= 47097)) { // 1 ~ 4 阶附魔石(近战)(远攻)(恢复)(防御)
					if (pc.getInventory().consumeItem(41246, 30)) {
						Effect.useEffectItem(pc, l1iteminstance);
					} else {
						isDelayEffect = false;
						pc.sendPackets(new S_ServerMessage(337, "$5240")); // \f1%0不足%s。
					}
				}
				else if ((itemId == 47068) || (itemId == 47069) || (itemId == 47078) || (itemId == 47079)
						 || (itemId == 47088) || (itemId == 47089) || (itemId == 47098) || (itemId == 47099)) { // 5 ~ 6阶附魔石(近战)(远攻)(恢复)(防御)
					if (pc.getInventory().consumeItem(41246, 60)) {
						Effect.useEffectItem(pc, l1iteminstance);
					} else {
						isDelayEffect = false;
						pc.sendPackets(new S_ServerMessage(337, "$5240")); // \f1%0不足%s。
					}
				}
				else if ((itemId == 47070) || (itemId == 47080) || (itemId == 47090) || (itemId == 47100)) { // 7阶附魔石(近战)(远攻)(恢复)(防御)
					if (pc.getInventory().consumeItem(41246, 100)) {
						Effect.useEffectItem(pc, l1iteminstance);
					} else {
						isDelayEffect = false;
						pc.sendPackets(new S_ServerMessage(337, "$5240")); // \f1%0不足%s。
					}
				}
				else if ((itemId == 47071) || (itemId == 47081) || (itemId == 47091) || (itemId == 47101)) { // 8阶附魔石(近战)(远攻)(恢复)(防御)
					if (pc.getInventory().consumeItem(41246, 200)) {
						Effect.useEffectItem(pc, l1iteminstance);
					} else {
						isDelayEffect = false;
						pc.sendPackets(new S_ServerMessage(337, "$5240")); // \f1%0不足%s。
					}
				}
				else if ((itemId == 47072) || (itemId == 47082) || (itemId == 47092) || (itemId == 47102)) { // 9阶附魔石(近战)(远攻)(恢复)(防御)
					if (pc.getInventory().consumeItem(41246, 300)) {
						Effect.useEffectItem(pc, l1iteminstance);
					} else {
						isDelayEffect = false;
						pc.sendPackets(new S_ServerMessage(337, "$5240")); // \f1%0不足%s。
					}
				}
				else if ((itemId == 40097) || (itemId == 40119)
						|| (itemId == 140119) || (itemId == 140329)) { // 解除咀咒的卷轴、原住民图腾
					for (L1ItemInstance eachItem : pc.getInventory().getItems()) {
						if ((eachItem.getItem().getBless() != 2)
								&& (eachItem.getItem().getBless() != 130)) {
							continue;
						}
						if (!eachItem.isEquipped()
								&& ((itemId == 40119) || (itemId == 40097))) {
							// 装备中才可解除咀咒
							continue;
						}
						int id_normal = eachItem.getItemId() - 200000;
						L1Item template = ItemTable.getInstance().getTemplate(id_normal);
						if (template == null) {
							continue;
						}
						if (eachItem.getBless() == 130) { // 封印中的咀咒装备
							eachItem.setBless(129);
						} else { // 未封印的咀咒装备
							eachItem.setBless(1);
						}
						if (pc.getInventory().checkItem(id_normal) && template.isStackable()) {
							pc.getInventory().storeItem(id_normal, eachItem.getCount());
							pc.getInventory().removeItem(eachItem, eachItem.getCount());
						}
						else {
							eachItem.setItem(template);
							pc.getInventory().updateItem(eachItem, L1PcInventory.COL_ITEMID);
							pc.getInventory().saveItem(eachItem, L1PcInventory.COL_ITEMID);
						}
					}
					pc.getInventory().removeItem(l1iteminstance, 1);
					pc.sendPackets(new S_ServerMessage(155)); // \f1你感觉到似乎有人正在帮助你。
				}
				else if ((itemId == 40126) || (itemId == 40098)) { // 鉴定卷轴
					if (!l1iteminstance1.isIdentified()) {
						l1iteminstance1.setIdentified(true);
						pc.getInventory().updateItem(l1iteminstance1, L1PcInventory.COL_IS_ID);
					}
					pc.sendPackets(new S_IdentifyDesc(l1iteminstance1));
					pc.getInventory().removeItem(l1iteminstance, 1);
				}
				else if (itemId == 41036) { // 胶水
					int diaryId = l1iteminstance1.getItem().getItemId();
					if ((diaryId >= 41038) && (41047 >= diaryId)) {
						if ((Random.nextInt(99) + 1) <= Config.CREATE_CHANCE_DIARY) {
							createNewItem(pc, diaryId + 10, 1);
						}
						else {
							pc.sendPackets(new S_ServerMessage(158, l1iteminstance1.getName())); // \f1%0%s 消失。
						}
						pc.getInventory().removeItem(l1iteminstance1, 1);
						pc.getInventory().removeItem(l1iteminstance, 1);
					}
					else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
				}
				else if ((itemId >= 41048) && (41055 >= itemId)) {
					// 涂着胶水的航海日志：１～８页
					int logbookId = l1iteminstance1.getItem().getItemId();
					if (logbookId == (itemId + 8034)) {
						createNewItem(pc, logbookId + 2, 1);
						pc.getInventory().removeItem(l1iteminstance1, 1);
						pc.getInventory().removeItem(l1iteminstance, 1);
					}
					else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
				}
				else if ((itemId == 41056) || (itemId == 41057)) {
					// 涂着胶水的航海日志：９～１０页
					int logbookId = l1iteminstance1.getItem().getItemId();
					if (logbookId == (itemId + 8034)) {
						createNewItem(pc, 41058, 1);
						pc.getInventory().removeItem(l1iteminstance1, 1);
						pc.getInventory().removeItem(l1iteminstance, 1);
					}
					else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
				}
				else if (itemId == 40925) { // 净化药水
					int earingId = l1iteminstance1.getItem().getItemId();
					if ((earingId >= 40987) && (40989 >= earingId)) { // 受诅咒的黑色耳环
						if (Random.nextInt(100) < Config.CREATE_CHANCE_RECOLLECTION) {
							createNewItem(pc, earingId + 186, 1);
						}
						else {
							pc.sendPackets(new S_ServerMessage(158, l1iteminstance1.getName())); // \f1%0%s 消失。
						}
						pc.getInventory().removeItem(l1iteminstance1, 1);
						pc.getInventory().removeItem(l1iteminstance, 1);
					}
					else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
				}
				else if ((itemId >= 40926) && (40929 >= itemId)) { // 一～四阶神秘药水
					int earing2Id = l1iteminstance1.getItem().getItemId();
					int potion1 = 0;
					int potion2 = 0;
					if ((earing2Id >= 41173) && (41184 >= earing2Id)) {
						if (itemId == 40926) {
							potion1 = 247;
							potion2 = 249;
						} else if (itemId == 40927) {
							potion1 = 249;
							potion2 = 251;
						} else if (itemId == 40928) {
							potion1 = 251;
							potion2 = 253;
						} else if (itemId == 40929) {
							potion1 = 253;
							potion2 = 255;
						}
						if ((earing2Id >= (itemId + potion1)) && ((itemId + potion2) >= earing2Id)) {
							if ((Random.nextInt(99) + 1) < Config.CREATE_CHANCE_MYSTERIOUS) {
								createNewItem(pc, (earing2Id - 12), 1);
								pc.getInventory().removeItem(l1iteminstance1, 1);
								pc.getInventory().removeItem(l1iteminstance, 1);
							} else {
								pc.sendPackets(new S_ServerMessage(160, l1iteminstance1.getName()));
								// \f1%0%s %2 产生激烈的 %1 光芒，但是没有任何事情发生。
								pc.getInventory().removeItem(l1iteminstance, 1);
							}
						} else {
							pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
						}
					} else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
				}
				else if ((itemId >= 40931) && (40942 >= itemId)) { // 精工的蓝、绿、红宝石
					int earing3Id = l1iteminstance1.getItem().getItemId();
					if ((earing3Id >= 41161) && (41172 >= earing3Id)) {
						// ミステリアスイアリング类
						if (earing3Id == (itemId + 230)) {
							if ((Random.nextInt(99) + 1) < Config.CREATE_CHANCE_PROCESSING) {
								int[] earingId = { 41161, 41162, 41163, 41164, 41165, 41166, 41167, 41168, 41169, 41170, 41171, 41172 };
								int[] earinglevel = { 21014, 21006, 21007, 21015, 21009, 21008, 21016, 21012, 21010, 21017, 21013, 21011 };
								for (int i = 0; i < earingId.length; i++) {
									if (earing3Id == earingId[i]) {
										createNewItem(pc, earinglevel[i], 1);
										break;
									}
								}
							} else {
								pc.sendPackets(new S_ServerMessage(158, l1iteminstance1.getName())); // \f1%0%s 消失。
							}
							pc.getInventory().removeItem(l1iteminstance1, 1);
							pc.getInventory().removeItem(l1iteminstance, 1);
						} else {
							pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
						}
					} else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
				}
				else if ((itemId >= 40943) && (40958 >= itemId)) { // 精工的土、水、火、风之钻
					int ringId = l1iteminstance1.getItem().getItemId();
					int ringlevel = 0;
					int gmas = 0;
					int gmam = 0;
					if ((ringId >= 41185) && (41200 >= ringId)) {
						// 细工されたリング类
						if ((itemId == 40943) || (itemId == 40947) || (itemId == 40951) || (itemId == 40955)) {
							gmas = 443;
							gmam = 447;
						} else if ((itemId == 40944) || (itemId == 40948) || (itemId == 40952) || (itemId == 40956)) {
							gmas = 442;
							gmam = 446;
						} else if ((itemId == 40945) || (itemId == 40949) || (itemId == 40953) || (itemId == 40957)) {
							gmas = 441;
							gmam = 445;
						} else if ((itemId == 40946) || (itemId == 40950) || (itemId == 40954) || (itemId == 40958)) {
							gmas = 444;
							gmam = 448;
						}
						if (ringId == (itemId + 242)) {
							if ((Random.nextInt(99) + 1) < Config.CREATE_CHANCE_PROCESSING_DIAMOND) {
								ringlevel = 20435 + (ringId - 41185);
								pc.sendPackets(new S_ServerMessage(gmas, l1iteminstance1.getName()));
								createNewItem(pc, ringlevel, 1);
								pc.getInventory().removeItem(l1iteminstance1, 1);
								pc.getInventory().removeItem(l1iteminstance, 1);
							} else {
								pc.sendPackets(new S_ServerMessage(gmam, l1iteminstance.getName()));
								pc.getInventory().removeItem(l1iteminstance, 1);
							}
						} else {
							pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
						}
					} else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
				}
				else if (itemId == 41029) { // 召唤球之核
					int dantesId = l1iteminstance1.getItem().getItemId();
					if ((dantesId >= 41030) && (41034 >= dantesId)) { // 召唤球のコア・各段阶
						if ((Random.nextInt(99) + 1) < Config.CREATE_CHANCE_DANTES) {
							createNewItem(pc, dantesId + 1, 1);
						}
						else {
							pc.sendPackets(new S_ServerMessage(158, l1iteminstance1.getName())); // \f1%0%s 消失。
						}
						pc.getInventory().removeItem(l1iteminstance1, 1);
						pc.getInventory().removeItem(l1iteminstance, 1);
					}
					else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
				}
				else if (itemId == 40964) { // 黑魔法粉
					int historybookId = l1iteminstance1.getItem().getItemId();
					if ((historybookId >= 41011) && (41018 >= historybookId)) {
						if ((Random.nextInt(99) + 1) <= Config.CREATE_CHANCE_HISTORY_BOOK) {
							createNewItem(pc, historybookId + 8, 1);
						}
						else {
							pc.sendPackets(new S_ServerMessage(158, l1iteminstance1.getName())); // \f1%0%s 消失。
						}
						pc.getInventory().removeItem(l1iteminstance1, 1);
						pc.getInventory().removeItem(l1iteminstance, 1);
					}
					else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
				}
				else if ((itemId == 40090) || (itemId == 40091) || (itemId == 40092) || (itemId == 40093) || (itemId == 40094)) { // ブランク
																																	// 空的魔法卷轴(Lv1)～(Lv5)
					// スクロール(Lv5)
					if (pc.isWizard()) { // 职业 (魔法师)
						if (((itemId == 40090) && (blanksc_skillid <= 7)) || // 空的魔法卷轴
								(// 空的魔法卷轴(等级1)可写1级以下的魔法
								(itemId == 40091) && (blanksc_skillid <= 15)) || // 空的魔法卷轴
								(// 空的魔法卷轴(等级2)可写2级以下的魔法
								(itemId == 40092) && (blanksc_skillid <= 22)) || // 空的魔法卷轴
								(// 空的魔法卷轴(等级3)可写3级以下的魔法
								(itemId == 40093) && (blanksc_skillid <= 31)) || // 空的魔法卷轴
								(// 空的魔法卷轴(等级4)可写4级以下的魔法
								(itemId == 40094) && (blanksc_skillid <= 39))) { // 空的魔法卷轴
							// 空的魔法卷轴(等级5)可写5级以下的魔法
							L1ItemInstance spellsc = ItemTable.getInstance().createItem(40859 + blanksc_skillid);
							if (spellsc != null) {
								if (pc.getInventory().checkAddItem(spellsc, 1) == L1Inventory.OK) {
									L1Skills l1skills = SkillsTable.getInstance().getTemplate(blanksc_skillid + 1); // blanksc_skillidは0始まり
									if (pc.getCurrentHp() + 1 < l1skills.getHpConsume() + 1) {
										pc.sendPackets(new S_ServerMessage(279)); // \f1因体力不足而无法使用魔法。
										return;
									}
									if (pc.getCurrentMp() < l1skills.getMpConsume()) {
										pc.sendPackets(new S_ServerMessage(278)); // \f1因魔力不足而无法使用魔法。
										return;
									}
									if (l1skills.getItemConsumeId() != 0) { // 材料が必要
										if (!pc.getInventory().checkItem(l1skills.getItemConsumeId(), l1skills.getItemConsumeCount())) { // 必要材料をチェック
											pc.sendPackets(new S_ServerMessage(299)); // \f1施放魔法所需材料不足。
											return;
										}
									}
									pc.setCurrentHp(pc.getCurrentHp() - l1skills.getHpConsume());
									pc.setCurrentMp(pc.getCurrentMp() - l1skills.getMpConsume());
									int lawful = pc.getLawful() + l1skills.getLawful();
									if (lawful > 32767) {
										lawful = 32767;
									}
									if (lawful < -32767) {
										lawful = -32767;
									}
									pc.setLawful(lawful);
									if (l1skills.getItemConsumeId() != 0) { // 材料が必要
										pc.getInventory().consumeItem(l1skills.getItemConsumeId(), l1skills.getItemConsumeCount());
									}
									pc.getInventory().removeItem(l1iteminstance, 1);
									pc.getInventory().storeItem(spellsc);
								}
							}
						}
						else {
							pc.sendPackets(new S_ServerMessage(591)); // \f1这张卷轴太易碎所以无法纪录魔法。
						}
					}
					else {
						pc.sendPackets(new S_ServerMessage(264)); // \f1你的职业无法使用此道具。
					}

				}
				// 各类 魔法卷轴
				else if ((((itemId >= 40859) && (itemId <= 40898)) && (itemId != 40863)) || ((itemId >= 49281) && (itemId <= 49286))) { // 40863 魔法卷轴 (指定传送) 另行处理
					if ((spellsc_objid == pc.getId()) && (l1iteminstance.getItem().getUseType() != 30)) { // spell_buff
						pc.sendPackets(new S_ServerMessage(281)); // \f1施咒取消。
						return;
					}
					pc.getInventory().removeItem(l1iteminstance, 1);
					if ((spellsc_objid == 0) && (l1iteminstance.getItem().getUseType() != 0) && (l1iteminstance.getItem().getUseType() != 26)
							&& (l1iteminstance.getItem().getUseType() != 27)) {
						return;
						// ターゲットがいない场合にhandleCommandsを送るとぬるぽになるためここでreturn
						// handleCommandsのほうで判断＆处理すべき部分かもしれない
					}
					int skillid = itemId - 40858;
					if (itemId == 49281) { // 魔法卷轴 (体魄强健术)
						skillid = 42;
					}
					else if (itemId == 49282) { // 魔法卷轴 (祝福魔法武器)
						skillid = 48;
					}
					else if (itemId == 49283) { // 魔法卷轴 (体力回复术)
						skillid = 49;
					}
					else if (itemId == 49284) { // 魔法卷轴 (神圣疾走)
						skillid = 52;
					}
					else if (itemId == 49285) { // 魔法卷轴 (强力加速术)
						skillid = 54;
					}
					else if (itemId == 49286) { // 魔法卷轴 (全部治愈术)
						skillid = 57;
					}
					L1SkillUse l1skilluse = new L1SkillUse();
					l1skilluse.handleCommands(client.getActiveChar(), skillid, spellsc_objid, spellsc_x, spellsc_y, null, 0, L1SkillUse.TYPE_SPELLSC);

				}
				else if (((itemId >= 40373) && (itemId <= 40382 // 地图各种
						))
						|| ((itemId >= 40385) && (itemId <= 40390))) {
					pc.sendPackets(new S_UseMap(pc, l1iteminstance.getId(), l1iteminstance.getItem().getItemId()));
				}
				else if ((itemId == 40310) || (itemId == 40730) || (itemId == 40731) || (itemId == 40732)) { // 便笺(未使用)
					if (writeLetter(itemId, pc, letterCode, letterReceiver, letterText)) {
						pc.getInventory().removeItem(l1iteminstance, 1);
					}
				}
				else if (itemId == 40311) { // 血盟便笺(未使用)
					if (writeClanLetter(itemId, pc, letterCode, letterReceiver, letterText)) {
						pc.getInventory().removeItem(l1iteminstance, 1);
					}
				}
				else if ((itemId == 49016) || (itemId == 49018) || (itemId == 49020) || (itemId == 49022) || (itemId == 49024)) { // 便笺(未开封)
					pc.sendPackets(new S_Letter(l1iteminstance));
					l1iteminstance.setItemId(itemId + 1);
					pc.getInventory().updateItem(l1iteminstance, L1PcInventory.COL_ITEMID);
					pc.getInventory().saveItem(l1iteminstance, L1PcInventory.COL_ITEMID);
				}
				else if ((itemId == 49017) || (itemId == 49019) || (itemId == 49021) || (itemId == 49023) || (itemId == 49025)) { // 便笺(开封济み)
					pc.sendPackets(new S_Letter(l1iteminstance));
				}
				else if ((itemId == 40314) || (itemId == 40316)) { // 项圈
					if (pc.getInventory().checkItem(41160)) { // 宠物召唤笛
						if (withdrawPet(pc, itemObjid)) {
							pc.getInventory().consumeItem(41160, 1);
						}
					}
					else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
				}
				else if (itemId == 40315) { // 哨子
					pc.sendPackets(new S_Sound(437));
					pc.broadcastPacket(new S_Sound(437));
					for (L1NpcInstance petNpc : pc.getPetList().values()) {
						if (petNpc instanceof L1PetInstance) { // 宠物
							L1PetInstance pet = (L1PetInstance) petNpc;
							pet.call();
						}
					}
				}
				else if (itemId == 40493) { // 魔法笛子
					pc.sendPackets(new S_Sound(165));
					pc.broadcastPacket(new S_Sound(165));
					for (L1Object visible : pc.getKnownObjects()) {
						if (visible instanceof L1GuardianInstance) {
							L1GuardianInstance guardian = (L1GuardianInstance) visible;
							if (guardian.getNpcTemplate().get_npcId() == 70850) { // 潘
								if (createNewItem(pc, 88, 1)) {
									pc.getInventory().removeItem(l1iteminstance, 1);
								}
							}
						}
					}
				}
				// 二~六段式魔法骰子 by 0919162173
				else if (itemId == 40325) { // 二段式魔法骰子
					int A = 0;
					if(pc.getInventory().consumeItem(40318, 1)) {
						int SK = Random.nextInt(2) + 1;
						{
							if (SK >= 1 && SK <= 2) {
								A = SK;
								switch(A) {
								case 1:
									pc.sendPackets(new S_SystemMessage("(正面)"));
									break;
								case 2:
									pc.sendPackets(new S_SystemMessage("(反面)"));
									break;
								}
								pc.sendPackets(new S_SkillSound(pc.getId(),3236 + A)); // 骰子效果显示
								pc.broadcastPacket(new S_SkillSound(pc.getId(),3236 + A));
							}
						}
					}
					else {
						// \f1没有任何事情发生。
						pc.sendPackets(new S_ServerMessage(79));
					}
				}
				else if (itemId == 40326) { // 三段式魔法骰子
					int A = 0;
					if(pc.getInventory().consumeItem(40318, 1)) {
						int SK = Random.nextInt(3) + 1;
						{
							if (SK >= 1 && SK <= 3) {
								A = SK;
								switch(A) {
								case 1:
									pc.sendPackets(new S_SystemMessage("(剪刀)"));
									break;
								case 2:
									pc.sendPackets(new S_SystemMessage("(石头)"));
									break;
								case 3:
									pc.sendPackets(new S_SystemMessage("(布)"));
									break;
								}
								pc.sendPackets(new S_SkillSound(pc.getId(),3228 + A)); // 骰子效果显示
								pc.broadcastPacket(new S_SkillSound(pc.getId(),3228 + A));
							}
						}
					}
					else {
						// \f1没有任何事情发生。
						pc.sendPackets(new S_ServerMessage(79));
					}
				}
				else if (itemId == 40327) { //4段式魔法骰子
					int A = 0;
					if(pc.getInventory().consumeItem(40318, 1)) {
						int SK = Random.nextInt(4) + 1;
						{

							if (SK >= 1 && SK <= 4) {

								A = SK;
								switch(A) {
								case 1:
									pc.sendPackets(new S_SystemMessage("(右上)"));
									break;
								case 2:
									pc.sendPackets(new S_SystemMessage("(左上)"));
									break;
								case 3:
									pc.sendPackets(new S_SystemMessage("(右下)"));
									break;
								case 4:
									pc.sendPackets(new S_SystemMessage("(左下)"));
									break;
								}
								pc.sendPackets(new S_SkillSound(pc.getId(),3240 + A)); // 骰子效果显示
								pc.broadcastPacket(new S_SkillSound(pc.getId(),3240 + A));
							}
						}
					}
					else {
						// \f1没有任何事情发生。
						pc.sendPackets(new S_ServerMessage(79));
					}
				}
				else if (itemId == 40328) { //6段式魔法骰子
					int A = 0;
					if(pc.getInventory().consumeItem(40318, 1)) {
						int SK = Random.nextInt(5) + 1;
						{

							if (SK >= 1 && SK <= 6) {

								A = SK;
								pc.sendPackets(new S_SystemMessage("你骰出的点数是(" + (A) + ")"));
								pc.sendPackets(new S_SkillSound(pc.getId(),3203 + A)); // 骰子效果显示
								pc.broadcastPacket(new S_SkillSound(pc.getId(),3203 + A));
							}
						}
					}
					else {
						// \f1没有任何事情发生。
						pc.sendPackets(new S_ServerMessage(79));
					}
				}
				else if ((itemId == 40089) || (itemId == 140089)) { // 复活卷轴、祝福复活卷轴
					L1Character resobject = (L1Character) L1World.getInstance().findObject(resid);
					if (resobject != null) {
						if (resobject instanceof L1PcInstance) {
							L1PcInstance target = (L1PcInstance) resobject;
							if (pc.getId() == target.getId()) {
								return;
							}
							if (L1World.getInstance().getVisiblePlayer(target, 0).size() > 0) {
								for (L1PcInstance visiblePc : L1World.getInstance().getVisiblePlayer(target, 0)) {
									if (!visiblePc.isDead()) {
										// \f1复活失败，因为这个位置已被占据
										pc.sendPackets(new S_ServerMessage(592));
										return;
									}
								}
							}
							if ((target.getCurrentHp() == 0) && (target.isDead() == true)) {
								if (pc.getMap().isUseResurrection()) {
									target.setTempID(pc.getId());
									if (itemId == 40089) {
										// 是否要复活？ (Y/N)
										target.sendPackets(new S_Message_YN(321, ""));
									}
									else if (itemId == 140089) {
										// 是否要复活？ (Y/N)
										target.sendPackets(new S_Message_YN(322, ""));
									}
								}
								else {
									return;
								}
							}
						}
						else if (resobject instanceof L1NpcInstance) {
							if (!(resobject instanceof L1TowerInstance)) {
								L1NpcInstance npc = (L1NpcInstance) resobject;
								if (npc.getNpcTemplate().isCantResurrect() && !(npc instanceof L1PetInstance)) {
									pc.getInventory().removeItem(l1iteminstance, 1);
									return;
								}
								if ((npc instanceof L1PetInstance) && (L1World.getInstance().getVisiblePlayer(npc, 0).size() > 0)) {
									for (L1PcInstance visiblePc : L1World.getInstance().getVisiblePlayer(npc, 0)) {
										if (!visiblePc.isDead()) {
											// \f1复活失败，因为这个位置已被占据
											pc.sendPackets(new S_ServerMessage(592));
											return;
										}
									}
								}
								if ((npc.getCurrentHp() == 0) && npc.isDead()) {
									npc.resurrect(npc.getMaxHp() / 4);
									npc.setResurrect(true);
									if ((npc instanceof L1PetInstance)) {
										L1PetInstance pet = (L1PetInstance) npc;
										// 开始饱食度计时
										pet.startFoodTimer(pet);
									}
								}
							}
						}
					}
					pc.getInventory().removeItem(l1iteminstance, 1);
				}
				else if (((itemId > 40169) && (itemId < 40226)) || ((itemId >= 45000) && (itemId <= 45022))) { // 魔法书
					useSpellBook(pc, l1iteminstance, itemId);
				}
				else if ((itemId > 40225) && (itemId < 40232)) {
					if (pc.isCrown() || pc.isGm()) {
						if ((itemId == 40226) && (pc.getLevel() >= 15)) {
							SpellBook4(pc, l1iteminstance, client);
						}
						else if ((itemId == 40228) && (pc.getLevel() >= 30)) {
							SpellBook4(pc, l1iteminstance, client);
						}
						else if ((itemId == 40227) && (pc.getLevel() >= 40)) {
							SpellBook4(pc, l1iteminstance, client);
						}
						else if (((itemId == 40231) || (itemId == 40232)) && (pc.getLevel() >= 45)) {
							SpellBook4(pc, l1iteminstance, client);
						}
						else if ((itemId == 40230) && (pc.getLevel() >= 50)) {
							SpellBook4(pc, l1iteminstance, client);
						}
						else if ((itemId == 40229) && (pc.getLevel() >= 55)) {
							SpellBook4(pc, l1iteminstance, client);
						}
						else {
							pc.sendPackets(new S_ServerMessage(312)); // 你还不能学习法术。
						}
					}
					else {
						pc.sendPackets(new S_ServerMessage(79));
					}
				}
				else if (((itemId >= 40232) && (itemId <= 40264 // 精灵の水晶
						))
						|| ((itemId >= 41149) && (itemId <= 41153))) {
					useElfSpellBook(pc, l1iteminstance, itemId);
				}
				else if ((itemId > 40264) && (itemId < 40280)) { // 闇精灵の水晶
					if (pc.isDarkelf() || pc.isGm()) {
						if ((itemId >= 40265) && (itemId <= 40269) && (pc.getLevel() >= 15)) {
							SpellBook1(pc, l1iteminstance, client);
						}
						else if ((itemId >= 40270) && (itemId <= 40274) && (pc.getLevel() >= 30)) {
							SpellBook1(pc, l1iteminstance, client);
						}
						else if ((itemId >= 40275) && (itemId <= 40279) && (pc.getLevel() >= 45)) {
							SpellBook1(pc, l1iteminstance, client);
						}
						else {
							pc.sendPackets(new S_ServerMessage(312)); // 你还不能学习法术。
						}
					}
					else {
						pc.sendPackets(new S_ServerMessage(79)); // (原文:闇精灵の水晶はダークエルフのみが习得できます。)
					}
				}
				else if (((itemId >= 40164) && (itemId <= 40166 // 技术书
						))
						|| ((itemId >= 41147) && (itemId <= 41148))) {
					if (pc.isKnight() || pc.isGm()) {
						if ((itemId >= 40164) && (itemId <= 40165 // 技术书(冲击之晕)、技术书(增幅防御)
								) && (pc.getLevel() >= 50)) {
							SpellBook3(pc, l1iteminstance, client);
						}
						else if ((itemId >= 41147) && (itemId <= 41148 // 技术书(坚固防护)、技术书(反击屏障)
								) && (pc.getLevel() >= 50)) {
							SpellBook3(pc, l1iteminstance, client);
						}
						else if ((itemId == 40166) && (pc.getLevel() >= 60)) { // 技术书(尖刺盔甲)
							SpellBook3(pc, l1iteminstance, client);
						}
						else {
							pc.sendPackets(new S_ServerMessage(312)); // 你还不能学习法术。
						}
					}
					else {
						pc.sendPackets(new S_ServerMessage(79)); // 没有任何事情发生。
					}
				}
				else if ((itemId >= 49102) && (itemId <= 49116)) { // 龙骑士书板
					if (pc.isDragonKnight() || pc.isGm()) {
						if ((itemId >= 49102) && (itemId <= 49106 // 龙骑士秘技 LV1
								) && (pc.getLevel() >= 15)) {
							SpellBook5(pc, l1iteminstance, client);
						}
						else if ((itemId >= 49107) && (itemId <= 49111 // 龙骑士秘技 LV2
								) && (pc.getLevel() >= 30)) {
							SpellBook5(pc, l1iteminstance, client);
						}
						else if ((itemId >= 49112) && (itemId <= 49116 // 龙骑士秘技 LV3
								) && (pc.getLevel() >= 45)) {
							SpellBook5(pc, l1iteminstance, client);
						}
						else {
							pc.sendPackets(new S_ServerMessage(312)); // 你还不能学习法术。
						}
					}
					else {
						pc.sendPackets(new S_ServerMessage(79)); // 没有任何事情发生。
					}
				}
				else if ((itemId >= 49117) && (itemId <= 49136)) { // 记忆水晶 
					if (pc.isIllusionist() || pc.isGm()) {
						if ((itemId >= 49117) && (itemId <= 49121 // 幻术师 魔法 LV1
								) && (pc.getLevel() >= 10)) {
							SpellBook6(pc, l1iteminstance, client);
						}
						else if ((itemId >= 49122) && (itemId <= 49126 // 幻术师 魔法 LV2
								) && (pc.getLevel() >= 20)) {
							SpellBook6(pc, l1iteminstance, client);
						}
						else if ((itemId >= 49127) && (itemId <= 49131 // 幻术师 魔法 LV3
								) && (pc.getLevel() >= 30)) {
							SpellBook6(pc, l1iteminstance, client);
						}
						else if ((itemId >= 49132) && (itemId <= 49136 // 幻术师 魔法 LV4
								) && (pc.getLevel() >= 40)) {
							SpellBook6(pc, l1iteminstance, client);
						}
						else {
							pc.sendPackets(new S_ServerMessage(312)); // 你还不能学习法术。
						}
					}
					else {
						pc.sendPackets(new S_ServerMessage(79)); // 没有任何事情发生。
					}
				}
				else if ((itemId == 40079) || (itemId == 40095) || (itemId == 40521)) { // 传送回家的卷轴、象牙塔传送回家的卷轴、精灵羽翼
					if (pc.getMap().isEscapable() || pc.isGm()) {
						int[] loc = Getback.GetBack_Location(pc, true);
						L1Teleport.teleport(pc, loc[0], loc[1], (short) loc[2], 5, true);
						pc.getInventory().removeItem(l1iteminstance, 1);
					}
					else {
						pc.sendPackets(new S_ServerMessage(276)); // \f1在此无法使用传送。
					}
				}
				else if (itemId == 40124) { // 血盟传送卷轴
					if (pc.getMap().isEscapable() || pc.isGm()) {
						int castle_id = 0;
						int house_id = 0;
						if (pc.getClanid() != 0) { // クラン所属
							L1Clan clan = L1World.getInstance().getClan(pc.getClanname());
							if (clan != null) {
								castle_id = clan.getCastleId();
								house_id = clan.getHouseId();
							}
						}
						if (castle_id != 0) { // 城主クラン员
							if (pc.getMap().isEscapable() || pc.isGm()) {
								int[] loc = new int[3];
								loc = L1CastleLocation.getCastleLoc(castle_id);
								int locx = loc[0];
								int locy = loc[1];
								short mapid = (short) (loc[2]);
								L1Teleport.teleport(pc, locx, locy, mapid, 5, true);
								pc.getInventory().removeItem(l1iteminstance, 1);
							}
							else {
								pc.sendPackets(new S_ServerMessage(647));
							}
						}
						else if (house_id != 0) { // アジト所有クラン员
							if (pc.getMap().isEscapable() || pc.isGm()) {
								int[] loc = new int[3];
								loc = L1HouseLocation.getHouseLoc(house_id);
								int locx = loc[0];
								int locy = loc[1];
								short mapid = (short) (loc[2]);
								L1Teleport.teleport(pc, locx, locy, mapid, 5, true);
								pc.getInventory().removeItem(l1iteminstance, 1);
							}
							else {
								pc.sendPackets(new S_ServerMessage(647));
							}
						}
						else {
							if (pc.getHomeTownId() > 0) {
								int[] loc = L1TownLocation.getGetBackLoc(pc.getHomeTownId());
								int locx = loc[0];
								int locy = loc[1];
								short mapid = (short) (loc[2]);
								L1Teleport.teleport(pc, locx, locy, mapid, 5, true);
								pc.getInventory().removeItem(l1iteminstance, 1);
							}
							else {
								int[] loc = Getback.GetBack_Location(pc, true);
								L1Teleport.teleport(pc, loc[0], loc[1], (short) loc[2], 5, true);
								pc.getInventory().removeItem(l1iteminstance, 1);
							}
						}
					}
					else {
						pc.sendPackets(new S_ServerMessage(647)); // 这附近的能量影响到瞬间移动。在此地无法使用瞬间移动。
					}
				}
				else if ((itemId == 140100) || (itemId == 40100) || (itemId == 40099 // 祝福的 (瞬间移动卷轴), 瞬间移动卷轴, 象牙塔瞬间移动卷轴
						) || (itemId == 40086) || (itemId == 40863)) { // 全体传送术的卷轴, 魔法卷轴 (指定传送)
					L1BookMark bookm = pc.getBookMark(btele);
					if (bookm != null) { // ブックマークを取得出来たらテレポート
						if (pc.getMap().isEscapable() || pc.isGm()) {
							int newX = bookm.getLocX();
							int newY = bookm.getLocY();
							short mapId = bookm.getMapId();

							if (itemId == 40086) { // 全体传送术的卷轴
								for (L1PcInstance member : L1World.getInstance().getVisiblePlayer(pc)) {
									if ((pc.getLocation().getTileLineDistance(member.getLocation()) <= 3) && (member.getClanid() == pc.getClanid())
											&& (pc.getClanid() != 0) && (member.getId() != pc.getId())) {
										L1Teleport.teleport(member, newX, newY, mapId, 5, true);
									}
								}
							}

							L1Teleport.teleport(pc, newX, newY, mapId, 5, true);
							// 卷轴传送后 使用物品延迟完才解开停止状态
							L1ItemDelay.teleportUnlock(pc, l1iteminstance);
							pc.getInventory().removeItem(l1iteminstance, 1);
						}
						else {
							pc.sendPackets(new S_ServerMessage(79));
							pc.sendPackets(new S_Paralysis(S_Paralysis.TYPE_TELEPORT_UNLOCK, true));
						}
					}
					else {
						if (pc.getMap().isTeleportable() || pc.isGm()) {
							L1Location newLocation = pc.getLocation().randomLocation(200, true);
							int newX = newLocation.getX();
							int newY = newLocation.getY();
							short mapId = (short) newLocation.getMapId();

							if (itemId == 40086) { // 全体传送术的卷轴
								for (L1PcInstance member : L1World.getInstance().getVisiblePlayer(pc)) {
									if ((pc.getLocation().getTileLineDistance(member.getLocation()) <= 3) && (member.getClanid() == pc.getClanid())
											&& (pc.getClanid() != 0) && (member.getId() != pc.getId())) {
										L1Teleport.teleport(member, newX, newY, mapId, 5, true);
									}
								}
							}
							L1Teleport.teleport(pc, newX, newY, mapId, 5, true);
							// 卷轴传送后 使用物品延迟完才解开停止状态
							L1ItemDelay.teleportUnlock(pc, l1iteminstance);
							pc.getInventory().removeItem(l1iteminstance, 1);
						}
						else {
							pc.sendPackets(new S_ServerMessage(276));
							pc.sendPackets(new S_Paralysis(S_Paralysis.TYPE_TELEPORT_UNLOCK, true));
						}
					}
				}
				else if (itemId == 240100) { // 受诅咒的 (瞬间移动卷轴)
					L1Teleport.teleport(pc, pc.getX(), pc.getY(), pc.getMapId(), pc.getHeading(), true);
					pc.getInventory().removeItem(l1iteminstance, 1);
				}
				else if ((itemId >= 40901) && (itemId <= 40908)) { // 各种传送戒指 (结婚后可以使用的)
					L1PcInstance partner = null;
					boolean partner_stat = false;
					if (pc.getPartnerId() != 0) { // 结婚中
						partner = (L1PcInstance) L1World.getInstance().findObject(pc.getPartnerId());
						if ((partner != null) && (partner.getPartnerId() != 0) && (pc.getPartnerId() == partner.getId())
								&& (partner.getPartnerId() == pc.getId())) {
							partner_stat = true;
						}
					}
					else {
						pc.sendPackets(new S_ServerMessage(662)); // \f1你(你)目前未婚。
						return;
					}

					if (partner_stat) {
						boolean castle_area = L1CastleLocation.checkInAllWarArea(
						// いずれかの城エリア
								partner.getX(), partner.getY(), partner.getMapId());
						if (((partner.getMapId() == 0) || (partner.getMapId() == 4) || (partner.getMapId() == 304)) && (castle_area == false)) {
							L1Teleport.teleport(pc, partner.getX(), partner.getY(), partner.getMapId(), 5, true);
						}
						else {
							pc.sendPackets(new S_ServerMessage(547)); // \f1あなたのパートナーは今あなたが行けない所でプレイ中です。
						}
					}
					else {
						pc.sendPackets(new S_ServerMessage(546)); // \f1あなたのパートナーは今プレイをしていません。
					}
				}
				else if (itemId == 40555) { // 密室钥匙
					if (pc.isKnight() && ((pc.getX() >= 32806) && // オリム部屋
							(pc.getX() <= 32814)) && ((pc.getY() >= 32798) && (pc.getY() <= 32807)) && (pc.getMapId() == 13)) {
						short mapid = 13;
						L1Teleport.teleport(pc, 32815, 32810, mapid, 5, false);
					}
					else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
				}
				else if (itemId == 40417) { // 精灵结晶
					if (((pc.getX() >= 32665) && // 海贼岛
							(pc.getX() <= 32674))
							&& ((pc.getY() >= 32976) && (pc.getY() <= 32985)) && (pc.getMapId() == 440)) {
						short mapid = 430;
						L1Teleport.teleport(pc, 32922, 32812, mapid, 5, true);
					}
					else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
				}
				else if (itemId == 49202) { // 时空裂痕邪念碎片
					if ((pc.getMapId() != 2004) && (pc.getQuest().get_step(L1Quest.QUEST_LEVEL50) > 1 )) {
						short mapid = 2004;
						L1Teleport.teleport(pc, 32723, 32834, mapid, 5, true);
						pc.getInventory().removeItem(l1iteminstance, 1);
					}
					else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。。
					}
				}			
				else if (itemId == 49178) { // 希蓮恩的护身符
					if ((pc.isIllusionist()) && (pc.getMapId() == 2004) && (pc.getQuest().get_step(L1Quest.QUEST_LEVEL50) > 1 )) {
						short mapid = 1000;
						L1Teleport.teleport(pc, 32772, 32812, mapid, 5, true);
						pc.getInventory().removeItem(l1iteminstance, 1);
					}
					else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
				}			
				else if (itemId == 49216) { // 普洛凯尔的护身符
					if ((pc.isDragonKnight()) && (pc.getMapId() == 2004) && (pc.getQuest().get_step(L1Quest.QUEST_LEVEL50) > 1 )) {
						short mapid = 1001;
						L1Teleport.teleport(pc, 32817, 32832, mapid, 5, true);
						pc.getInventory().removeItem(l1iteminstance, 1);
					}
					else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
				}		
				else if (itemId == 40566) { // 神秘贝壳
					if (pc.isElf()
							&& ((pc.getX() >= 33971) && // 象牙の塔の村の南にある魔方阵の座标
							(pc.getX() <= 33975)) && ((pc.getY() >= 32324) && (pc.getY() <= 32328)) && (pc.getMapId() == 4)
							&& !pc.getInventory().checkItem(40548)) { // 古代亡灵之袋
						boolean found = false;
						for (L1Object obj : L1World.getInstance().getObject()) {
							if (obj instanceof L1MonsterInstance) {
								L1MonsterInstance mob = (L1MonsterInstance) obj;
								if (mob != null) {
									if (mob.getNpcTemplate().get_npcId() == 45300) {
										found = true;
										break;
									}
								}
							}
						}
						if (found) {
							pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
						}
						else {
							L1SpawnUtil.spawn(pc, 45300, 0, 0); // 古代人亡灵 (试炼怪物)
						}
					}
					else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
				}
				else if (itemId == 40557) { // 暗杀名单(古鲁丁村)
					if ((pc.getX() == 32620) && (pc.getY() == 32641) && (pc.getMapId() == 4)) {
						for (L1Object object : L1World.getInstance().getObject()) {
							if (object instanceof L1NpcInstance) {
								L1NpcInstance npc = (L1NpcInstance) object;
								if (npc.getNpcTemplate().get_npcId() == 45883) {
									pc.sendPackets(new S_ServerMessage(79));
									return;
								}
							}
						}
						L1SpawnUtil.spawn(pc, 45883, 0, 300000);
					}
					else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
				}
				else if (itemId == 40563) { // 暗杀名单(燃柳村)
					if ((pc.getX() == 32730) && (pc.getY() == 32426) && (pc.getMapId() == 4)) {
						for (L1Object object : L1World.getInstance().getObject()) {
							if (object instanceof L1NpcInstance) {
								L1NpcInstance npc = (L1NpcInstance) object;
								if (npc.getNpcTemplate().get_npcId() == 45884) {
									pc.sendPackets(new S_ServerMessage(79));
									return;
								}
							}
						}
						L1SpawnUtil.spawn(pc, 45884, 0, 300000);
					}
					else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
				}
				else if (itemId == 40561) { // 暗杀名单(肯特村)
					if ((pc.getX() == 33046) && (pc.getY() == 32806) && (pc.getMapId() == 4)) {
						for (L1Object object : L1World.getInstance().getObject()) {
							if (object instanceof L1NpcInstance) {
								L1NpcInstance npc = (L1NpcInstance) object;
								if (npc.getNpcTemplate().get_npcId() == 45885) {
									pc.sendPackets(new S_ServerMessage(79));
									return;
								}
							}
						}
						L1SpawnUtil.spawn(pc, 45885, 0, 300000);
					}
					else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
				}
				else if (itemId == 40560) { // 暗杀名单(风木村)
					if ((pc.getX() == 32580) && (pc.getY() == 33260) && (pc.getMapId() == 4)) {
						for (L1Object object : L1World.getInstance().getObject()) {
							if (object instanceof L1NpcInstance) {
								L1NpcInstance npc = (L1NpcInstance) object;
								if (npc.getNpcTemplate().get_npcId() == 45886) {
									pc.sendPackets(new S_ServerMessage(79));
									return;
								}
							}
						}
						L1SpawnUtil.spawn(pc, 45886, 0, 300000);
					}
					else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
				}
				else if (itemId == 40562) { // 暗杀名单(海音村)
					if ((pc.getX() == 33447) && (pc.getY() == 33476) && (pc.getMapId() == 4)) {
						for (L1Object object : L1World.getInstance().getObject()) {
							if (object instanceof L1NpcInstance) {
								L1NpcInstance npc = (L1NpcInstance) object;
								if (npc.getNpcTemplate().get_npcId() == 45887) {
									pc.sendPackets(new S_ServerMessage(79));
									return;
								}
							}
						}
						L1SpawnUtil.spawn(pc, 45887, 0, 300000);
					}
					else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
				}
				else if (itemId == 40559) { // 暗杀名单(亚丁城镇)
					if ((pc.getX() == 34215) && (pc.getY() == 33195) && (pc.getMapId() == 4)) {
						for (L1Object object : L1World.getInstance().getObject()) {
							if (object instanceof L1NpcInstance) {
								L1NpcInstance npc = (L1NpcInstance) object;
								if (npc.getNpcTemplate().get_npcId() == 45888) {
									pc.sendPackets(new S_ServerMessage(79));
									return;
								}
							}
						}
						L1SpawnUtil.spawn(pc, 45888, 0, 300000);
					}
					else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
				}
				else if (itemId == 40558) { // 暗杀名单(奇岩村)
					if ((pc.getX() == 33513) && (pc.getY() == 32890) && (pc.getMapId() == 4)) {
						for (L1Object object : L1World.getInstance().getObject()) {
							if (object instanceof L1NpcInstance) {
								L1NpcInstance npc = (L1NpcInstance) object;
								if (npc.getNpcTemplate().get_npcId() == 45889) {
									pc.sendPackets(new S_ServerMessage(79));
									return;
								}
							}
						}
						L1SpawnUtil.spawn(pc, 45889, 0, 300000);
					}
					else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
				}
				else if (itemId == 40572) { // 刺客之证
					if ((pc.getX() == 32778) && (pc.getY() == 32738) && (pc.getMapId() == 21)) {
						L1Teleport.teleport(pc, 32781, 32728, (short) 21, 5, true);
					}
					else if ((pc.getX() == 32781) && (pc.getY() == 32728) && (pc.getMapId() == 21)) {
						L1Teleport.teleport(pc, 32778, 32738, (short) 21, 5, true);
					}
					else {
						pc.sendPackets(new S_ServerMessage(79));
					}
				}
				else if ((itemId == 40006) || (itemId == 40412) || (itemId == 140006)) { // 松木魔杖、黑暗安特的树枝
					if (pc.getMap().isUsePainwand()) {
						S_AttackPacket s_attackPacket = new S_AttackPacket(pc, 0, ActionCodes.ACTION_Wand);
						pc.sendPackets(s_attackPacket);
						pc.broadcastPacket(s_attackPacket);
						int[] mobArray =
						{ 45008, 45140, 45016, 45021, 45025, 45033, 45099, 45147, 45123, 45130, 45046, 45092, 45138, 45098, 45127, 45143, 45149,
								45171, 45040, 45155, 45192, 45173, 45213, 45079, 45144 };
						// ゴブリン・ホブコブリン・コボルト・鹿・グレムリン
						// インプ・インプエルダー・オウルベア・スケルトンアーチャー・スケルトンアックス
						// ビーグル・ドワーフウォーリアー・オークスカウト・ガンジオーク・ロバオーク
						// ドゥダーマラオーク・アトゥバオーク・ネルガオーク・ベアー・トロッグ
						// ラットマン・ライカンスロープ・ガースト・ノール・リザードマン
						/*
						 * 45005, 45008, 45009, 45016, 45019, 45043, 45060,
						 * 45066, 45068, 45082, 45093, 45101, 45107, 45126,
						 * 45129, 45136, 45144, 45157, 45161, 45173, 45184,
						 * 45223 }; // カエル、ゴブリン、オーク、コボルド、 // オーク
						 * アーチャー、ウルフ、スライム、ゾンビ、 // フローティングアイ、オーク ファイター、 // ウェア
						 * ウルフ、アリゲーター、スケルトン、 // ストーン ゴーレム、スケルトン アーチャー、 // ジャイアント
						 * スパイダー、リザードマン、グール、 // スパルトイ、ライカンスロープ、ドレッド スパイダー、 //
						 * バグベアー
						 */
						int rnd = Random.nextInt(mobArray.length);
						L1SpawnUtil.spawn(pc, mobArray[rnd], 0, 300000);
						if ((itemId == 40006) || (itemId == 140006)) {
							l1iteminstance.setChargeCount(l1iteminstance.getChargeCount() - 1);
							pc.getInventory().updateItem(l1iteminstance, L1PcInventory.COL_CHARGE_COUNT);
							if (l1iteminstance.getChargeCount() <= 0) { // 次数为 0时删除
								pc.getInventory().removeItem(l1iteminstance, 1);
							}
						}
						else {
							pc.getInventory().removeItem(l1iteminstance, 1);
						}
					}
					else {
						// \f1没有任何事情发生。
						pc.sendPackets(new S_ServerMessage(79));
					}
				}
				else if (itemId == 40007) { // 闪电魔杖
					int dmg = 0;
					int[] data = null;
					L1Object target = L1World.getInstance().findObject(spellsc_objid);
					if (target != null) {
						dmg = doWandAction(pc, target);
					}
					data = new int[] {ActionCodes.ACTION_Wand, dmg, 10, 6}; // data = {actid, dmg, spellgfx, use_type}
					pc.sendPackets(new S_UseAttackSkill(pc, spellsc_objid, spellsc_x, spellsc_y, data));
					pc.broadcastPacket(new S_UseAttackSkill(pc, spellsc_objid, spellsc_x, spellsc_y, data));
					l1iteminstance.setChargeCount(l1iteminstance.getChargeCount() - 1);
					pc.getInventory().updateItem(l1iteminstance, L1PcInventory.COL_CHARGE_COUNT);
					if (l1iteminstance.getChargeCount() <= 0) { // 次数为 0时删除
						pc.getInventory().removeItem(l1iteminstance, 1);
					}
				}
				else if ((itemId == 40008) || (itemId == 40410) || (itemId == 140008)) { // 枫木魔杖、黑暗安特的树皮
					if ((pc.getMapId() == 63) || (pc.getMapId() == 552) || (pc.getMapId() == 555) || (pc.getMapId() == 557) || (pc.getMapId() == 558)
							|| (pc.getMapId() == 779)) { // 水中无法使用
						pc.sendPackets(new S_ServerMessage(563)); // \f1你无法在这个地方使用。
					}
					else {
						S_AttackPacket s_attackPacket = new S_AttackPacket(pc, 0, ActionCodes.ACTION_Wand);
						pc.sendPackets(s_attackPacket);
						pc.broadcastPacket(s_attackPacket);
						L1Object target = L1World.getInstance().findObject(spellsc_objid);
						if (target != null) {
							L1Character cha = (L1Character) target;
							polyAction(pc, cha);
							if ((itemId == 40008) || (itemId == 140008)) {
								l1iteminstance.setChargeCount(l1iteminstance.getChargeCount() - 1);
								pc.getInventory().updateItem(l1iteminstance, L1PcInventory.COL_CHARGE_COUNT);
								if (l1iteminstance.getChargeCount() <= 0) { // 次数为 0时删除
									pc.getInventory().removeItem(l1iteminstance, 1);
								}
							}
							else {
								pc.getInventory().removeItem(l1iteminstance, 1);
							}
						}
						else {
							pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
						}
					}
				}
				else if ((itemId >= 40289) && (itemId <= 40297)) { // 傲慢之塔传送符 (11F-91F)
					useToiTeleportAmulet(pc, itemId, l1iteminstance);
				}
				else if ((itemId >= 40280) && (itemId <= 40288)) { // 封印的傲慢之塔传送符 (11F-91F)
					pc.getInventory().removeItem(l1iteminstance, 1);
					L1ItemInstance item = pc.getInventory().storeItem(itemId + 9, 1);
					if (item != null) {
						pc.sendPackets(new S_ServerMessage(403, item.getLogName())); // 获得%0%o 。
					}
				}
				// 肉类 (增加饱食度)
				else if ((itemId == 40056) || (itemId == 40057) || (itemId == 40059) || (itemId == 40060) || (itemId == 40061) || (itemId == 40062)
						|| (itemId == 40063) || (itemId == 40064) || (itemId == 40065) || (itemId == 40069) || (itemId == 40072) || (itemId == 40073)
						|| (itemId == 140061) || (itemId == 140062) || (itemId == 140065) || (itemId == 140069) || (itemId == 140072)
						|| (itemId == 41296) || (itemId == 41297) || (itemId == 41266) || (itemId == 41267) || (itemId == 41274) || (itemId == 41275)
						|| (itemId == 41276) || (itemId == 41252) || (itemId == 49040) || (itemId == 49041) || (itemId == 49042) || (itemId == 49043)
						|| (itemId == 49044) || (itemId == 49045) || (itemId == 49046) || (itemId == 49047)) {
					pc.getInventory().removeItem(l1iteminstance, 1);
					// XXX 食べ物每の满腹度(100单位で变动)
					short foodvolume1 = (short) (l1iteminstance.getItem().getFoodVolume() / 10);
					short foodvolume2 = 0;
					if (foodvolume1 <= 0) {
						foodvolume1 = 5;
					}
					if (pc.get_food() >= 225) {
						pc.sendPackets(new S_PacketBox(S_PacketBox.FOOD, (short) pc.get_food()));
					}
					else {
						foodvolume2 = (short) (pc.get_food() + foodvolume1);
						if (foodvolume2 <= 225) {
							pc.set_food(foodvolume2);
							pc.sendPackets(new S_PacketBox(S_PacketBox.FOOD, (short) pc.get_food()));
						}
						else {
							pc.set_food((short) 225);
							pc.sendPackets(new S_PacketBox(S_PacketBox.FOOD, (short) pc.get_food()));
						}
					}
					if (itemId == 40057) { // 漂浮之眼肉
						pc.setSkillEffect(STATUS_FLOATING_EYE, 0);
					}
					pc.sendPackets(new S_ServerMessage(76, l1iteminstance.getItem().getIdentifiedNameId()));
				}
				else if (itemId == 40070) { // 进化果实
					pc.sendPackets(new S_ServerMessage(76, l1iteminstance.getLogName()));
					pc.getInventory().removeItem(l1iteminstance, 1);
				}
				else if (itemId == 41298) { // 鳕鱼
					Potion.UseHeallingPotion(pc, l1iteminstance, 4, 189);
				}
				else if (itemId == 41299) { // 虎斑带鱼
					Potion.UseHeallingPotion(pc, l1iteminstance, 15, 194);
				}
				else if (itemId == 41300) { // 鲔鱼
					Potion.UseHeallingPotion(pc, l1iteminstance, 35, 197);
				}
				else if ((itemId >= 40136) && (itemId <= 40161)) { // 烟火
					int soundid = 3198;
					if (itemId == 40154) { // 圣诞烟火
						soundid = 3198;
					}
					else if (itemId == 40152) { // 绿色烟火
						soundid = 2031;
					}
					else if (itemId == 40141) { // 蓝色烟火
						soundid = 2028;
					}
					else if (itemId == 40160) { // 淡黄色烟火
						soundid = 2030;
					}
					else if (itemId == 40145) { // 红色烟火
						soundid = 2029;
					}
					else if (itemId == 40159) { // 黄色烟火
						soundid = 2033;
					}
					else if (itemId == 40151) { // 淡绿色烟火
						soundid = 2032;
					}
					else if (itemId == 40161) { // 黄色心型烟火
						soundid = 2037;
					}
					else if (itemId == 40142) { // 蓝色心型烟火
						soundid = 2036;
					}
					else if (itemId == 40146) { // 红色心型烟火
						soundid = 2039;
					}
					else if (itemId == 40148) { // 绿色两段烟火
						soundid = 2043;
					}
					else if (itemId == 40143) { // 红色两段烟火
						soundid = 2041;
					}
					else if (itemId == 40156) { // 黄色两段烟火
						soundid = 2042;
					}
					else if (itemId == 40139) { // 蓝色两段烟火
						soundid = 2040;
					}
					else if (itemId == 40137) { // 六连发烟火
						soundid = 2047;
					}
					else if (itemId == 40136) { // 三连发烟火
						soundid = 2046;
					}
					else if (itemId == 40138) { // 高级六连发烟火
						soundid = 2048;
					}
					else if (itemId == 40140) { // 蓝色仙女棒
						soundid = 2051;
					}
					else if (itemId == 40144) { // 红色仙女棒
						soundid = 2053;
					}
					else if (itemId == 40147) { // 绿色两段圆形烟火
						soundid = 2045;
					}
					else if (itemId == 40149) { // 蓝色圆形烟火
						soundid = 2034;
					}
					else if (itemId == 40150) { // 绿色仙女棒
						soundid = 2055;
					}
					else if (itemId == 40153) { // 绿色心型烟火
						soundid = 2038;
					}
					else if (itemId == 40155) { // 黄色两段圆形烟火
						soundid = 2044;
					}
					else if (itemId == 40157) { // 黄色圆形烟火
						soundid = 2035;
					}
					else if (itemId == 40158) { // 黄色仙女棒
						soundid = 2049;
					}
					else {
						soundid = 3198;
					}

					S_SkillSound s_skillsound = new S_SkillSound(pc.getId(), soundid);
					pc.sendPackets(s_skillsound);
					pc.broadcastPacket(s_skillsound);
					pc.getInventory().removeItem(l1iteminstance, 1);
				}
				else if ((itemId >= 41357) && (itemId <= 41382)) { // 字母鞭炮 (A-Z)
					int soundid = itemId - 34946;
					S_SkillSound s_skillsound = new S_SkillSound(pc.getId(), soundid);
					pc.sendPackets(s_skillsound);
					pc.broadcastPacket(s_skillsound);
					pc.getInventory().removeItem(l1iteminstance, 1);
				}
				else if (itemId == 40615) { // 暗影神殿2楼钥匙
					if (((pc.getX() >= 32701) && (pc.getX() <= 32705)) && ((pc.getY() >= 32894) && (pc.getY() <= 32898)) && (pc.getMapId() == 522)) { // 影の神殿1F
						L1Teleport.teleport(pc, ((L1EtcItem) l1iteminstance.getItem()).get_locx(), ((L1EtcItem) l1iteminstance.getItem()).get_locy(),
								((L1EtcItem) l1iteminstance.getItem()).get_mapid(), 5, true);
					}
					else {
						// \f1没有任何事情发生。
						pc.sendPackets(new S_ServerMessage(79));
					}
				}
				else if ((itemId == 40616) || (itemId == 40782) || (itemId == 40783)) { // 暗影神殿3楼钥匙
					if (((pc.getX() >= 32698) && (pc.getX() <= 32702)) && ((pc.getY() >= 32894) && (pc.getY() <= 32898)) && (pc.getMapId() == 523)) { // 影の神殿2阶
						L1Teleport.teleport(pc, ((L1EtcItem) l1iteminstance.getItem()).get_locx(), ((L1EtcItem) l1iteminstance.getItem()).get_locy(),
								((L1EtcItem) l1iteminstance.getItem()).get_mapid(), 5, true);
					}
					else {
						// \f1没有任何事情发生。
						pc.sendPackets(new S_ServerMessage(79));
					}
				}
				else if (itemId == 40692) { // 完成的藏宝图
					if (pc.getInventory().checkItem(40621)) {
						// \f1没有任何事情发生。
						pc.sendPackets(new S_ServerMessage(79));
					}
					else if (((pc.getX() >= 32856) && (pc.getX() <= 32858)) && ((pc.getY() >= 32857) && (pc.getY() <= 32858))
							&& (pc.getMapId() == 443)) { // 海贼岛地监３楼
						L1Teleport.teleport(pc, ((L1EtcItem) l1iteminstance.getItem()).get_locx(), ((L1EtcItem) l1iteminstance.getItem()).get_locy(),
								((L1EtcItem) l1iteminstance.getItem()).get_mapid(), 5, true);
					}
					else {
						// \f1没有任何事情发生。
						pc.sendPackets(new S_ServerMessage(79));
					}
				}
				else if (itemId == 41146) { // [jp]ドロモンドの招待状
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "ei001"));
				}
				else if (itemId == 40641) { // 说话卷轴
					if (Config.ALT_TALKINGSCROLLQUEST == true) {
						if (pc.getQuest().get_step(L1Quest.QUEST_TOSCROLL) == 0) {
							pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "tscrolla"));
						}
						else if (pc.getQuest().get_step(L1Quest.QUEST_TOSCROLL) == 1) {
							pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "tscrollb"));
						}
						else if (pc.getQuest().get_step(L1Quest.QUEST_TOSCROLL) == 2) {
							pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "tscrollc"));
						}
						else if (pc.getQuest().get_step(L1Quest.QUEST_TOSCROLL) == 3) {
							pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "tscrolld"));
						}
						else if (pc.getQuest().get_step(L1Quest.QUEST_TOSCROLL) == 4) {
							pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "tscrolle"));
						}
						else if (pc.getQuest().get_step(L1Quest.QUEST_TOSCROLL) == 5) {
							pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "tscrollf"));
						}
						else if (pc.getQuest().get_step(L1Quest.QUEST_TOSCROLL) == 6) {
							pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "tscrollg"));
						}
						else if (pc.getQuest().get_step(L1Quest.QUEST_TOSCROLL) == 7) {
							pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "tscrollh"));
						}
						else if (pc.getQuest().get_step(L1Quest.QUEST_TOSCROLL) == 8) {
							pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "tscrolli"));
						}
						else if (pc.getQuest().get_step(L1Quest.QUEST_TOSCROLL) == 9) {
							pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "tscrollj"));
						}
						else if (pc.getQuest().get_step(L1Quest.QUEST_TOSCROLL) == 10) {
							pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "tscrollk"));
						}
						else if (pc.getQuest().get_step(L1Quest.QUEST_TOSCROLL) == 11) {
							pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "tscrolll"));
						}
						else if (pc.getQuest().get_step(L1Quest.QUEST_TOSCROLL) == 12) {
							pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "tscrollm"));
						}
						else if (pc.getQuest().get_step(L1Quest.QUEST_TOSCROLL) == 13) {
							pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "tscrolln"));
						}
						else if (pc.getQuest().get_step(L1Quest.QUEST_TOSCROLL) == 255) {
							pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "tscrollo"));
						}
					}
					else {
						pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "tscrollp"));
					}
				}
				else if (itemId == 49172) { // 希蓮恩的第一次信件
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "silrein1lt"));
				}
				else if (itemId == 49173) { // 希蓮恩的第二次信件
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "silrein2lt"));
				}
				else if (itemId == 49174) { // 希蓮恩的第三次信件
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "silrein3lt"));
				}
				else if (itemId == 49175) { // 希蓮恩的第四次信件
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "silrein4lt"));
				}
				else if (itemId == 49176) { // 希蓮恩的第五次信件
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "silrein5lt"));
				}
				else if (itemId == 49177) { // 希蓮恩的第六次信件
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "silrein6lt"));
				}
				else if (itemId == 49206) { // 塞維斯邪念碎片
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "bluesoul_p"));
				}
				else if (itemId == 49210) { // 普洛凯尔的第一次指令书
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "first_p"));
				}
				else if (itemId == 49211) { // 普洛凯尔的第二次指令书
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "second_p"));
				}
				else if (itemId == 49212) { // 普洛凯尔的第三次指令书
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "third_p"));
				}
				else if (itemId == 49287) { // 普洛凯尔的第四次指令书
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "fourth_p"));
				}
				else if (itemId == 49288) { // 普洛凯尔的第五次指令书
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "fifth_p"));
				}
				else if (itemId == 49231) { // 路西尔斯邪念碎片
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "redsoul_p"));
				}
				else if (itemId == 40383) { // 地图:歌唱之岛
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "ei035"));
				}
				else if (itemId == 40384) { // 地图:隐藏之谷
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "ei036"));
				}
				else if (itemId == 40101) { // 指定传送卷轴(隐藏之谷)
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "ei037"));
				}
				else if (itemId == 41209) { // [jp]ポピレアの依赖书
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "ei002"));
				}
				else if (itemId == 41210) { // 研磨材
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "ei003"));
				}
				else if (itemId == 41211) { // 香菜
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "ei004"));
				}
				else if (itemId == 41212) { // [jp]特制糖果
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "ei005"));
				}
				else if (itemId == 41213) { // [jp]歌唱岛的人们
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "ei006"));
				}
				else if (itemId == 41214) { // [jp]运の证
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "ei012"));
				}
				else if (itemId == 41215) { // [jp]知の证
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "ei010"));
				}
				else if (itemId == 41216) { // [jp]力の证
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "ei011"));
				}
				else if (itemId == 41222) { // 蘑菇汁
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "ei008"));
				}
				else if (itemId == 41223) { // 武器的碎片
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "ei007"));
				}
				else if (itemId == 41224) { // 徽章
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "ei009"));
				}
				else if (itemId == 41225) { // 遗失钥匙商人的请求
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "ei013"));
				}
				else if (itemId == 41226) { // [jp]帕古的药
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "ei014"));
				}
				else if (itemId == 41227) { // [jp]亚历斯的介绍书
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "ei033"));
				}
				else if (itemId == 41228) { // [jp]ラビのお守り
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "ei034"));
				}
				else if (itemId == 41229) { // [jp]骷髅头
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "ei025"));
				}
				else if (itemId == 41230) { // [jp]ジーナンへの手纸
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "ei020"));
				}
				else if (itemId == 41231) { // [jp]マッティへの手纸
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "ei021"));
				}
				else if (itemId == 41233) { // [jp]ケーイへの手纸
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "ei019"));
				}
				else if (itemId == 41234) { // [jp]骨の入った袋
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "ei023"));
				}
				else if (itemId == 41235) { // [jp]材料表
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "ei024"));
				}
				else if (itemId == 41236) { // [jp]ボーンアーチャーの骨
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "ei026"));
				}
				else if (itemId == 41237) { // [jp]スケルトンスパイクの骨
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "ei027"));
				}
				else if (itemId == 41239) { // [jp]ヴートへの手纸
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "ei018"));
				}
				else if (itemId == 41240) { // [jp]フェーダへの手纸
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "ei022"));
				}
				else if (itemId == 41060) { // 诺曼阿吐巴的信
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "nonames"));
				}
				else if (itemId == 41061) { // 妖精调查书：卡麦都达玛拉
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "kames"));
				}
				else if (itemId == 41062) { // 人类调查书：巴库摩那鲁加
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "bakumos"));
				}
				else if (itemId == 41063) { // 精灵调查书：可普都达玛拉
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "bukas"));
				}
				else if (itemId == 41064) { // 妖魔调查书：弧邬牟那鲁加
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "huwoomos"));
				}
				else if (itemId == 41065) { // 死亡之树调查书：诺亚阿吐巴
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "noas"));
				}
				else if (itemId == 41356) { // 波伦的资源清单
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "rparum3"));
				}
				else if (itemId == 40701) { // 小藏宝图
					if (pc.getQuest().get_step(L1Quest.QUEST_LUKEIN1) == 1) {
						pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "firsttmap"));
					}
					else if (pc.getQuest().get_step(L1Quest.QUEST_LUKEIN1) == 2) {
						pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "secondtmapa"));
					}
					else if (pc.getQuest().get_step(L1Quest.QUEST_LUKEIN1) == 3) {
						pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "secondtmapb"));
					}
					else if (pc.getQuest().get_step(L1Quest.QUEST_LUKEIN1) == 4) {
						pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "secondtmapc"));
					}
					else if (pc.getQuest().get_step(L1Quest.QUEST_LUKEIN1) == 5) {
						pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "thirdtmapd"));
					}
					else if (pc.getQuest().get_step(L1Quest.QUEST_LUKEIN1) == 6) {
						pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "thirdtmape"));
					}
					else if (pc.getQuest().get_step(L1Quest.QUEST_LUKEIN1) == 7) {
						pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "thirdtmapf"));
					}
					else if (pc.getQuest().get_step(L1Quest.QUEST_LUKEIN1) == 8) {
						pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "thirdtmapg"));
					}
					else if (pc.getQuest().get_step(L1Quest.QUEST_LUKEIN1) == 9) {
						pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "thirdtmaph"));
					}
					else if (pc.getQuest().get_step(L1Quest.QUEST_LUKEIN1) == 10) {
						pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "thirdtmapi"));
					}
				}
				else if (itemId == 40663) { // 儿子的信
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "sonsletter"));
				}
				else if (itemId == 40630) { // 迪哥的旧日记
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "diegodiary"));
				}
				else if (itemId == 41340) { // 佣兵团长多文的推荐书
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "tion"));
				}
				else if (itemId == 41317) { // 拉罗森的推荐书
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "rarson"));
				}
				else if (itemId == 41318) { // 可恩的便条纸
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "kuen"));
				}
				else if (itemId == 41329) { // 标本制作委托书
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "anirequest"));
				}
				else if (itemId == 41346) { // 罗宾孙的便条纸
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "robinscroll"));
				}
				else if (itemId == 41347) { // 罗宾孙的便条纸
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "robinscroll2"));
				}
				else if (itemId == 41348) { // 罗宾孙的推荐书
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "robinhood"));
				}
				else if (itemId == 41007) { // 伊莉丝的命令书：灵魂之安息
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "erisscroll"));
				}
				else if (itemId == 41009) { // 伊莉丝的命令书：同盟之意志
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "erisscroll2"));
				}
				else if (itemId == 41019) { // 拉斯塔巴德历史书第1页
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "lashistory1"));
				}
				else if (itemId == 41020) { // 拉斯塔巴德历史书第2页
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "lashistory2"));
				}
				else if (itemId == 41021) { // 拉斯塔巴德历史书第3页
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "lashistory3"));
				}
				else if (itemId == 41022) { // 拉斯塔巴德历史书第4页
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "lashistory4"));
				}
				else if (itemId == 41023) { // 拉斯塔巴德历史书第5页
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "lashistory5"));
				}
				else if (itemId == 41024) { // 拉斯塔巴德历史书第6页
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "lashistory6"));
				}
				else if (itemId == 41025) { // 拉斯塔巴德历史书第7页
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "lashistory7"));
				}
				else if (itemId == 41026) { // 拉斯塔巴德历史书第8页
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "lashistory8"));
				}
				else if (itemId == 41208) { // 微弱的灵魂
					if (((pc.getX() >= 32844) && (pc.getX() <= 32845)) && ((pc.getY() >= 32693) && (pc.getY() <= 32694)) && (pc.getMapId() == 550)) { // 船の墓场:地上层
						L1Teleport.teleport(pc, ((L1EtcItem) l1iteminstance.getItem()).get_locx(), ((L1EtcItem) l1iteminstance.getItem()).get_locy(),
								((L1EtcItem) l1iteminstance.getItem()).get_mapid(), 5, true);
					}
					else {
						// \f1没有任何事情发生。
						pc.sendPackets(new S_ServerMessage(79));
					}
				}
				else if (itemId == 40700) { // 银笛
					pc.sendPackets(new S_Sound(10));
					pc.broadcastPacket(new S_Sound(10));
					if (((pc.getX() >= 32619) && (pc.getX() <= 32623)) && ((pc.getY() >= 33120) && (pc.getY() <= 33124)) && (pc.getMapId() == 440)) { // 海贼岛前半魔方阵座标
						boolean found = false;
						for (L1Object obj : L1World.getInstance().getObject()) {
							if (obj instanceof L1MonsterInstance) {
								L1MonsterInstance mob = (L1MonsterInstance) obj;
								if (mob != null) {
									if (mob.getNpcTemplate().get_npcId() == 45875) {
										found = true;
										break;
									}
								}
							}
						}
						if (found) {}
						else {
							L1SpawnUtil.spawn(pc, 45875, 0, 0); // 海贼骷髅首领
						}
					}
				}
				else if (itemId == 41121) { // 火焰之影的契约书
					if ((pc.getQuest().get_step(L1Quest.QUEST_SHADOWS) == L1Quest.QUEST_END) || pc.getInventory().checkItem(41122, 1)) {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
					else {
						createNewItem(pc, 41122, 1);
					}
				}
				else if (itemId == 41130) { // 炎魔的契约书
					if ((pc.getQuest().get_step(L1Quest.QUEST_DESIRE) == L1Quest.QUEST_END) || pc.getInventory().checkItem(41131, 1)) {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
					else {
						createNewItem(pc, 41131, 1);
					}
				}
				else if (itemId == 42501) { // ストームウォーク
					if (pc.getCurrentMp() < 10) {
						pc.sendPackets(new S_ServerMessage(278)); // \f1因魔力不足而无法使用魔法。
						return;
					}
					pc.setCurrentMp(pc.getCurrentMp() - 10);
					// pc.sendPackets(new S_CantMove()); // テレポート后に移动不可能になる场合がある
					L1Teleport.teleport(pc, spellsc_x, spellsc_y, pc.getMapId(), pc.getHeading(), true, L1Teleport.CHANGE_POSITION);
				}
				else if ((itemId == 41293)) { // 魔法钓竿
					startFishing(pc, itemId, fishX, fishY);
				}
				else if (itemId == 41245) { // 溶解剂
					useResolvent(pc, l1iteminstance1, l1iteminstance);
				}
				else if ((itemId >= 41255) && (itemId <= 41259)) { // 料理书
					if (cookStatus == 0) {
						pc.sendPackets(new S_PacketBox(S_PacketBox.COOK_WINDOW, (itemId - 41255)));
					}
					else {
						makeCooking(pc, cookNo);
					}
				}
				else if (itemId == 41260) { // 柴火
					for (L1Object object : L1World.getInstance().getVisibleObjects(pc, 3)) {
						if (object instanceof L1EffectInstance) {
							if (((L1NpcInstance) object).getNpcTemplate().get_npcId() == 81170) { // 料理用火堆
								// 附近已经有柴火了。
								pc.sendPackets(new S_ServerMessage(1162));
								return;
							}
						}
					}
					int[] loc = new int[2];
					loc = pc.getFrontLoc();
					L1EffectSpawn.getInstance().spawnEffect(81170, 600000, loc[0], loc[1], pc.getMapId());
					pc.getInventory().removeItem(l1iteminstance, 1);
				}
				else if (((itemId >= 41277) && (itemId <= 41292)) || ((itemId >= 49049) && (itemId <= 49064))
						|| ((itemId >= 49244) && (itemId <= 49259))
						|| itemId == L1ItemId.POTION_OF_WONDER_DRUG) { // 魔法料理、象牙塔妙药
					L1Cooking.useCookingItem(pc, l1iteminstance);
				}
				else if ((itemId >= 41383) && (itemId <= 41400)) { // 家具
					useFurnitureItem(pc, itemId, itemObjid);
				}
				else if (itemId == 41401) { // 移除家具魔杖
					useFurnitureRemovalWand(pc, spellsc_objid, l1iteminstance);
				}

				// TODO 副本(测试) add
				else if (itemId == 5071) { // 副本 钥匙
					if(l1j.william.DragonGate.getStatus() == 0) {
						DragonGate.getStart().startDragonGate(pc);
						pc.getInventory().removeItem(l1iteminstance, 1);
					}
					else if(DragonGate.getStatus() == 2) {
						pc.sendPackets(new S_SystemMessage("\\fY目前副本任务正在进行中。"));
						return;
					}
					else if(DragonGate.getStatus() == 4){
						pc.sendPackets(new S_SystemMessage("\\fY目前副本任务正在维护中。"));
						return;
					}
				}
				else if (itemId == 5072) { // 小副本 A 钥匙
					L1SpawnUtil.spawn(pc, 6033, 0, 10000);
					pc.getInventory().removeItem(l1iteminstance, 1);
				}
				else if (itemId == 5073) { // 小副本 B 钥匙
					L1SpawnUtil.spawn(pc, 6034, 0, 10000);
					pc.getInventory().removeItem(l1iteminstance, 1);
				}
				//副本(测试) end

				else if (itemId == 41411) { // 练金术之石
					Potion.UseHeallingPotion(pc, l1iteminstance, 10, 189);
				}
				else if (itemId == 41345) { // 酸性液体
					L1DamagePoison.doInfection(pc, pc, 3000, 5);
					pc.getInventory().removeItem(l1iteminstance, 1);
				}
				else if (itemId == 41315) { // 圣水
					if (pc.hasSkillEffect(STATUS_HOLY_WATER_OF_EVA)) {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
						return;
					}
					if (pc.hasSkillEffect(STATUS_HOLY_MITHRIL_POWDER)) {
						pc.removeSkillEffect(STATUS_HOLY_MITHRIL_POWDER);
					}
					pc.setSkillEffect(STATUS_HOLY_WATER, 900 * 1000);
					pc.sendPackets(new S_SkillSound(pc.getId(), 190));
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 190));
					pc.sendPackets(new S_ServerMessage(1141));
					pc.getInventory().removeItem(l1iteminstance, 1);
				}
				else if (itemId == 41316) { // 神圣的米索莉粉
					if (pc.hasSkillEffect(STATUS_HOLY_WATER_OF_EVA)) {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
						return;
					}
					if (pc.hasSkillEffect(STATUS_HOLY_WATER)) {
						pc.removeSkillEffect(STATUS_HOLY_WATER);
					}
					pc.setSkillEffect(STATUS_HOLY_MITHRIL_POWDER, 900 * 1000);
					pc.sendPackets(new S_SkillSound(pc.getId(), 190));
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 190));
					pc.sendPackets(new S_ServerMessage(1142)); // 你得到可以攻击哈蒙将军的力量。
					pc.getInventory().removeItem(l1iteminstance, 1);
				}
				else if (itemId == 41354) { // 伊娃的圣水
					if (pc.hasSkillEffect(STATUS_HOLY_WATER) || pc.hasSkillEffect(STATUS_HOLY_MITHRIL_POWDER)) {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
						return;
					}
					pc.setSkillEffect(STATUS_HOLY_WATER_OF_EVA, 900 * 1000);
					pc.sendPackets(new S_SkillSound(pc.getId(), 190));
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 190));
					pc.sendPackets(new S_ServerMessage(1140)); // 你得到可以攻击受诅咒的巫女莎尔的力量。
					pc.getInventory().removeItem(l1iteminstance, 1);
				}
				else if (itemId == 49092) { // 龟裂之核
					int targetItemId = l1iteminstance1.getItem().getItemId();
					// 上锁的欧西里斯初级宝箱、上锁的欧西里斯高级宝箱
					if ((targetItemId == 49095) || (targetItemId == 49099)
							// 上锁的库库尔坎初级宝箱、上锁的库库尔坎高级宝箱
							|| (targetItemId == 49318) || (targetItemId == 49322)) {
						createNewItem(pc, targetItemId + 1, 1);
						pc.getInventory().consumeItem(targetItemId, 1);
						pc.getInventory().consumeItem(49092, 1);
					} else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
				}
				else if (itemId == 49094) { // 欧西里斯初级宝箱碎片(下)
					if (l1iteminstance1.getItem().getItemId() == 49093) {
						pc.getInventory().consumeItem(49093, 1);
						pc.getInventory().consumeItem(49094, 1);
						createNewItem(pc, 49095, 1);
					} else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
				}
				else if (itemId == 49098) { // 欧西里斯高级宝箱碎片(下)
					if (l1iteminstance1.getItem().getItemId() == 49097) {
						pc.getInventory().consumeItem(49097, 1);
						pc.getInventory().consumeItem(49098, 1);
						createNewItem(pc, 49099, 1);
					} else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
				}
				else if (itemId == 49317) { // 库库尔坎初级宝箱碎片：下
					if (l1iteminstance1.getItem().getItemId() == 49316) {
						pc.getInventory().consumeItem(49316, 1);
						pc.getInventory().consumeItem(49317, 1);
						createNewItem(pc, 49318, 1);
					} else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
				}
				else if (itemId == 49321) { // 库库尔坎高级宝箱碎片(下)
					if (l1iteminstance1.getItem().getItemId() == 49320) {
						pc.getInventory().consumeItem(49320, 1);
						pc.getInventory().consumeItem(49321, 1);
						createNewItem(pc, 49322, 1);
					} else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
				}
				else if (itemId == 49198) { // 第二次邪念碎片
					if (l1iteminstance1.getItem().getItemId() == 49197) {
						pc.getInventory().consumeItem(49197, 1);
						pc.getInventory().consumeItem(49198, 1);
						createNewItem(pc, 49200, 1);
					} else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
				}
				else if (itemId == 49199) { // 第三次邪念碎片
					if (l1iteminstance1.getItem().getItemId() == 49200) {
						pc.getInventory().consumeItem(49199, 1);
						pc.getInventory().consumeItem(49200, 1);
						createNewItem(pc, 49201, 1);
					} else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
				}
				else if (itemId == 49148) { // 饰品强化卷轴
					Enchant.scrollOfEnchantAccessory(pc, l1iteminstance, l1iteminstance1, client);
				}
				else if (itemId == 41426) { // 封印卷轴
					L1ItemInstance lockItem = pc.getInventory().getItem(l);
					if (((lockItem != null) && (lockItem.getItem().getType2() == 1)) || (lockItem.getItem().getType2() == 2)
							|| ((lockItem.getItem().getType2() == 0) && lockItem.getItem().isCanSeal())) {
						if ((lockItem.getBless() == 0) || (lockItem.getBless() == 1) || (lockItem.getBless() == 2) || (lockItem.getBless() == 3)) {
							int bless = 1;
							switch (lockItem.getBless()) {
								case 0:
									bless = 128;
									break;
								case 1:
									bless = 129;
									break;
								case 2:
									bless = 130;
									break;
								case 3:
									bless = 131;
									break;
							}
							lockItem.setBless(bless);
							pc.getInventory().updateItem(lockItem, L1PcInventory.COL_BLESS);
							pc.getInventory().saveItem(lockItem, L1PcInventory.COL_BLESS);
							pc.getInventory().removeItem(l1iteminstance, 1);
						}
						else {
							pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
						}
					}
					else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
				}
				else if (itemId == 41427) { // 解除封印卷轴
					L1ItemInstance lockItem = pc.getInventory().getItem(l);
					if (((lockItem != null) && (lockItem.getItem().getType2() == 1)) || (lockItem.getItem().getType2() == 2)
							|| ((lockItem.getItem().getType2() == 0) && lockItem.getItem().isCanSeal())) {
						if ((lockItem.getBless() == 128) || (lockItem.getBless() == 129) || (lockItem.getBless() == 130)
								|| (lockItem.getBless() == 131)) {
							int bless = 1;
							switch (lockItem.getBless()) {
								case 128:
									bless = 0;
									break;
								case 129:
									bless = 1;
									break;
								case 130:
									bless = 2;
									break;
								case 131:
									bless = 3;
									break;
							}
							lockItem.setBless(bless);
							pc.getInventory().updateItem(lockItem, L1PcInventory.COL_BLESS);
							pc.getInventory().saveItem(lockItem, L1PcInventory.COL_BLESS);
							pc.getInventory().removeItem(l1iteminstance, 1);
						}
						else {
							pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
						}
					}
					else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
				}
				else if (itemId == 41428) { // 太古的玉玺
					if ((pc != null) && (l1iteminstance != null)) {
						Account account = Account.load(pc.getAccountName());
						if (account == null) {
							pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
							return;
						}
						int characterSlot = account.getCharacterSlot();
						int maxAmount = Config.DEFAULT_CHARACTER_SLOT + characterSlot;
						if (maxAmount >= 8) {
							pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
							return;
						}
						if (characterSlot < 0) {
							characterSlot = 0;
						}
						else {
							characterSlot += 1;
						}
						account.setCharacterSlot(characterSlot);
						Account.updateCharacterSlot(account);
						pc.getInventory().removeItem(l1iteminstance, 1);
					}
					else {
						pc.sendPackets(new S_ServerMessage(79));
					}
				}
				else if (itemId == 40075) { // 毁灭盔甲的卷轴
					if (l1iteminstance1.getItem().getType2() == 2) {
						int msg = 0;
						switch (l1iteminstance1.getItem().getType()) {
							case 1: // helm
								msg = 171; // \f1你的钢盔如爆炸般地破碎了。
								break;
							case 2: // armor
								msg = 169; // \f1你的盔甲变成尘埃落地。
								break;
							case 3: // T
								msg = 170; // \f1你的T恤破碎成线四散。
								break;
							case 4: // cloak
								msg = 168; // \f1你的斗蓬破碎化为尘埃。
								break;
							case 5: // glove
								msg = 172; // \f1你的手套消失。
								break;
							case 6: // boots
								msg = 173; // \f1你的长靴分解。
								break;
							case 7: // shield
								msg = 174; // \f1你的盾崩溃分散。
								break;
							default:
								msg = 167; // \f1你的皮肤痒。
								break;
						}
						pc.sendPackets(new S_ServerMessage(msg));
						pc.getInventory().removeItem(l1iteminstance1, 1);
					}
					else {
						pc.sendPackets(new S_ServerMessage(154)); // \f1这个卷轴散开了。
					}
					pc.getInventory().removeItem(l1iteminstance, 1);
				}
				else if (itemId == 49210) { // 普洛凯尔的第一次指令书
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "first_p"));
				}
				else if (itemId == 49211) { // 普洛凯尔的第二次指令书
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "second_p"));
				}
				else if (itemId == 49212) { // 普洛凯尔的第三次指令书
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "third_p"));
				}
				else if (itemId == 49287) { // 普洛凯尔的第四次指令书
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "fourth_p"));
				}
				else if (itemId == 49288) { // 普洛凯尔的第五次指令书
					pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "fifth_p"));
				}
				else if (itemId == 49222) { // 妖魔密使之笛子
					if (pc.isDragonKnight() && (pc.getMapId() == 61)) { // HC3F
						boolean found = false;
						for (L1Object obj : L1World.getInstance().getObject()) {
							if (obj instanceof L1MonsterInstance) {
								L1MonsterInstance mob = (L1MonsterInstance) obj;
								if (mob != null) {
									if (mob.getNpcTemplate().get_npcId() == 46161) {
										found = true;
										break;
									}
								}
							}
						}
						if (found) {
							pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
						}
						else {
							L1SpawnUtil.spawn(pc, 46161, 0, 0); // オーク
																// 妖魔密使首领
						}
						pc.getInventory().consumeItem(49222, 1);// 妖魔密使之笛子
					}
				}
				else if (itemId == 49188) { // 索夏依卡灵魂之石
					if (l1iteminstance1.getItem().getItemId() == 49186) {
						L1ItemInstance item1 = ItemTable.getInstance().createItem(49189);
						item1.setCount(1);
						if (pc.getInventory().checkAddItem(item1, 1) == L1Inventory.OK) {
							pc.getInventory().storeItem(item1);
							pc.sendPackets(new S_ServerMessage(403, item1.getLogName()));
							pc.getInventory().removeItem(l1iteminstance, 1);
							pc.getInventory().removeItem(l1iteminstance1, 1);
						}
					}
					else {
						pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
					}
				}
				else if (itemId == 49189) { // 索夏依卡灵魂之笛
					if (pc.isIllusionist() && (pc.getMapId() == 4)) { // 古鲁丁祭坛
						boolean found = false;
						for (L1Object obj : L1World.getInstance().getObject()) {
							if (obj instanceof L1MonsterInstance) {
								L1MonsterInstance mob = (L1MonsterInstance) obj;
								if (mob != null) {
									if (mob.getNpcTemplate().get_npcId() == 46163) {// 艾尔摩索夏依卡将军的冤魂
										found = true;
										break;
									}
								}
							}
						}
						if (found) {
							pc.sendPackets(new S_ServerMessage(79));
						}
						else {
							L1SpawnUtil.spawn(pc, 46163, 0, 0);
						}
						pc.getInventory().consumeItem(49189, 1);
					}
				}
				else if (itemId == 49201) { // 完成的时间水晶球
					if (pc.isIllusionist() && (pc.getMapId() == 4)) { // 火龙窟
						boolean found = false;
						for (L1Object obj : L1World.getInstance().getObject()) {
							if (obj instanceof L1MonsterInstance) {
								L1MonsterInstance mob = (L1MonsterInstance) obj;
								if (mob != null) {
									if (mob.getNpcTemplate().get_npcId() == 81254) {// 时空裂痕
										found = true;
										break;
									}
								}
							}
						}
						if (found) {
							pc.sendPackets(new S_ServerMessage(79));
						}
						else {
							L1SpawnUtil.spawn(pc, 81254, 0, 0);
						}
						pc.getInventory().consumeItem(49201, 1);
					}
				}
				else if (itemId == 49208) { // 藍色之火碎片
					if (pc.isIllusionist() && (pc.getMapId() == 2004)) { // 異界 奎斯特
						boolean found = false;
						for (L1Object obj : L1World.getInstance().getObject()) {
							if (obj instanceof L1MonsterInstance) {
								L1MonsterInstance mob = (L1MonsterInstance) obj;
								if (mob != null) {
									if (mob.getNpcTemplate().get_npcId() == 81313) {// 塞維斯
										found = true;
										break;
									}
								}
							}
						}
						if (found) {
							pc.sendPackets(new S_ServerMessage(79));
						}
						else {
							L1SpawnUtil.spawn(pc, 81313, 0, 0);
						}
						pc.getInventory().consumeItem(49208, 1);
					}
				}
				else if (itemId == 49208) { // 藍色之火碎片
					if (pc.isIllusionist() && (pc.getMapId() == 2004)) { // 異界 奎斯特
						boolean found = false;
						for (L1Object obj : L1World.getInstance().getObject()) {
							if (obj instanceof L1MonsterInstance) {
								L1MonsterInstance mob = (L1MonsterInstance) obj;
								if (mob != null) {
									if (mob.getNpcTemplate().get_npcId() == 81313) {// 塞維斯
										found = true;
										break;
									}
								}
							}
						}
						if (found) {
							pc.sendPackets(new S_ServerMessage(79));
						}
						else {
							L1SpawnUtil.spawn(pc, 81313, 0, 0);
						}
						pc.getInventory().consumeItem(49208, 1);
					}
				}
				else if (itemId == 49227) { // 紅色之火碎片
					if (pc.isDragonKnight() && (pc.getMapId() == 2004)) { // 異界 奎斯特
						boolean found = false;
						for (L1Object obj : L1World.getInstance().getObject()) {
							if (obj instanceof L1MonsterInstance) {
								L1MonsterInstance mob = (L1MonsterInstance) obj;
								if (mob != null) {
									if (mob.getNpcTemplate().get_npcId() == 81312) {// 路西爾斯
										found = true;
										break;
									}
								}
							}
						}
						if (found) {
							pc.sendPackets(new S_ServerMessage(79));
						}
						else {
							L1SpawnUtil.spawn(pc, 81312, 0, 0);
						}
						pc.getInventory().consumeItem(49227, 1);
					}
				}
				else if (itemId == 47010) { // 龙之钥匙
					if (!L1CastleLocation.checkInAllWarArea(pc.getLocation())) { // 检查是否在城堡区域内
						pc.sendPackets(new S_DragonGate(pc ,L1DragonSlayer.getInstance().checkDragonPortal()));
					} else {
						pc.sendPackets(new S_ServerMessage(79)); // 没有任何事情发生
					}
				}
				else {
					int locX = ((L1EtcItem) l1iteminstance.getItem()).get_locx();
					int locY = ((L1EtcItem) l1iteminstance.getItem()).get_locy();
					short mapId = ((L1EtcItem) l1iteminstance.getItem()).get_mapid();
					if ((locX != 0) && (locY != 0)) { // 各种テレポートスクロール
						if (pc.getMap().isEscapable() || pc.isGm()) {
							L1Teleport.teleport(pc, locX, locY, mapId, pc.getHeading(), true);
							pc.getInventory().removeItem(l1iteminstance, 1);
						}
						else {
							pc.sendPackets(new S_ServerMessage(647)); // 这附近的能量影响到瞬间移动。在此地无法使用瞬间移动。
						}
					}
					else {
						if (l1iteminstance.getCount() < 1) { // あり得ない？
							pc.sendPackets(new S_ServerMessage(329, l1iteminstance.getLogName())); // \f1没有具有 %0%o。
						}
						else {
							pc.sendPackets(new S_ServerMessage(74, l1iteminstance.getLogName())); // \f1%0%o 无法使用。
						}
					}
				}
			}
			else if (l1iteminstance.getItem().getType2() == 1) {
				// 种别：武器
				int min = l1iteminstance.getItem().getMinLevel();
				int max = l1iteminstance.getItem().getMaxLevel();
				int fameLV = l1iteminstance.getItem().getCheckFameLevel(); // sosodemon 物品声望控制
				if ((min != 0) && (min > pc.getLevel())) {
					// 等级 %0以上才可使用此道具。
					pc.sendPackets(new S_ServerMessage(318, String.valueOf(min)));
				}
				else if ((max != 0) && (max < pc.getLevel())) {
					pc.sendPackets(new S_PacketBox(S_PacketBox.MSG_LEVEL_OVER, max)); // 等级%d以下才能使用此道具。
				}
				

				// sosodemon add 武器声望控制
				else if (fameLV > pc.getFameLevel()) {
					pc.sendPackets(new S_SystemMessage(
							"您的声望值不足。 需要声望：【"+ fameLV +"】"));
                }
				// sosodemon end 武器声望控制
				
				else {
					if ((pc.isCrown() && l1iteminstance.getItem().isUseRoyal()) || (pc.isKnight() && l1iteminstance.getItem().isUseKnight())
							|| (pc.isElf() && l1iteminstance.getItem().isUseElf()) || (pc.isWizard() && l1iteminstance.getItem().isUseMage())
							|| (pc.isDarkelf() && l1iteminstance.getItem().isUseDarkelf())
							|| (pc.isDragonKnight() && l1iteminstance.getItem().isUseDragonknight())
							|| (pc.isIllusionist() && l1iteminstance.getItem().isUseIllusionist())) {
						UseWeapon(pc, l1iteminstance);
					}
					else {
						// \f1你的职业无法使用此道具。
						pc.sendPackets(new S_ServerMessage(264));
					}
				}
			}
			else if (l1iteminstance.getItem().getType2() == 2) { // 种别：防具
				if ((pc.isCrown() && l1iteminstance.getItem().isUseRoyal()) || (pc.isKnight() && l1iteminstance.getItem().isUseKnight())
						|| (pc.isElf() && l1iteminstance.getItem().isUseElf()) || (pc.isWizard() && l1iteminstance.getItem().isUseMage())
						|| (pc.isDarkelf() && l1iteminstance.getItem().isUseDarkelf())
						|| (pc.isDragonKnight() && l1iteminstance.getItem().isUseDragonknight())
						|| (pc.isIllusionist() && l1iteminstance.getItem().isUseIllusionist())) {

					int min = ((L1Armor) l1iteminstance.getItem()).getMinLevel();
					int max = ((L1Armor) l1iteminstance.getItem()).getMaxLevel();
					int fameLV = l1iteminstance.getItem().getCheckFameLevel(); // sosodemon 物品声望控制
					if ((min != 0) && (min > pc.getLevel())) {
						// 等级 %0以上才可使用此道具。
						pc.sendPackets(new S_ServerMessage(318, String.valueOf(min)));
					}
					else if ((max != 0) && (max < pc.getLevel())) {
						pc.sendPackets(new S_PacketBox(S_PacketBox.MSG_LEVEL_OVER, max)); // 等级%d以下才能使用此道具。
					}

					// sosodemon add 防具声望控制
					else if (fameLV > pc.getFameLevel()) {
						pc.sendPackets(new S_SystemMessage(
								"您的声望值不足。 需要声望：【"+ fameLV +"】"));
					} else { // sosodemon end 防具声望控制
						UseArmor(pc, l1iteminstance);
					}
				} else {
				
					// \f1你的职业无法使用此道具。
					pc.sendPackets(new S_ServerMessage(264));
				}
			}

			// 效果ディレイがある场合は现在时间をセット
			if (isDelayEffect) {
				Timestamp ts = new Timestamp(System.currentTimeMillis());
				l1iteminstance.setLastUsed(ts);
				pc.getInventory().updateItem(l1iteminstance, L1PcInventory.COL_DELAY_EFFECT);
				pc.getInventory().saveItem(l1iteminstance, L1PcInventory.COL_DELAY_EFFECT);
			}

			L1ItemDelay.onItemUse(client, l1iteminstance); // アイテムディレイ开始
		}
	}

	// 各种变形卷轴
	private boolean usePolyScroll(L1PcInstance pc, int item_id, String s) {
		int awakeSkillId = pc.getAwakeSkillId();
		if ((awakeSkillId == AWAKEN_ANTHARAS) || (awakeSkillId == AWAKEN_FAFURION) || (awakeSkillId == AWAKEN_VALAKAS)) {
			pc.sendPackets(new S_ServerMessage(1384)); // 目前状态中无法变身。
			return false;
		}

		int time = 0;
		if ((item_id == 40088) || (item_id == 40096)) { // 变形卷轴、象牙塔变形卷轴
			time = 1800;
		}
		else if (item_id == 49308) { // 福利变形药水
			time = Random.nextInt(2401, 4800);
		}
		else if (item_id == 140088) { // 受祝福的 变形卷轴
			time = 2100;
		}

		L1PolyMorph poly = PolyTable.getInstance().getTemplate(s);
		if ((poly != null) || s.equals("")) {
			if (s.equals("")) {
				if ((pc.getTempCharGfx() == 6034) || (pc.getTempCharGfx() == 6035)) {
					return true;
				} else {
					pc.removeSkillEffect(SHAPE_CHANGE);
					return true;
				}
			} else if ((poly.getMinLevel() <= pc.getLevel()) || pc.isGm()) {
				L1PolyMorph.doPoly(pc, poly.getPolyId(), time, L1PolyMorph.MORPH_BY_ITEMMAGIC);
				return true;
			}
		}
		return false;
	}

	// 鳞片类 变身
	private void usePolyScale(L1PcInstance pc, int itemId) {
		int time = 900;
		int awakeSkillId = pc.getAwakeSkillId();
		if ((awakeSkillId == AWAKEN_ANTHARAS) || (awakeSkillId == AWAKEN_FAFURION) || (awakeSkillId == AWAKEN_VALAKAS)) {
			pc.sendPackets(new S_ServerMessage(1384)); // 目前状态中无法变身。
			return;
		}

		int polyId = 0;
		if (itemId == 41154) { // 暗之鳞
			polyId = 3101;
		} else if (itemId == 41155) { // 火之鳞
			polyId = 3126;
		} else if (itemId == 41156) { // 叛之鳞
			polyId = 3888;
		} else if (itemId == 41157) { // 恨之鳞
			polyId = 3784;
		} else if (itemId == 49220) { // 妖魔密使变形卷轴
			polyId = 6984;
			time = 1200;
		}
		L1PolyMorph.doPoly(pc, polyId, time, L1PolyMorph.MORPH_BY_ITEMMAGIC);
	}

	// 卷轴类 变身
	private void usePolyPotion(L1PcInstance pc, int itemId) {
		int time = 1800;
		int awakeSkillId = pc.getAwakeSkillId();
		if ((awakeSkillId == AWAKEN_ANTHARAS) || (awakeSkillId == AWAKEN_FAFURION) || (awakeSkillId == AWAKEN_VALAKAS)) {
			pc.sendPackets(new S_ServerMessage(1384)); // 目前状态中无法变身。
			return;
		}

		int polyId = 0;
		if (itemId == 41143) { // 海贼骷髅首领变身药水
			polyId = 6086;
			time = 900;
		}
		else if (itemId == 41144) { // 海贼骷髅士兵变身药水
			polyId = 6087;
			time = 900;
		}
		else if (itemId == 41145) { // 海贼骷髅刀手变身药水
			polyId = 6088;
			time = 900;
		}
		else if ((itemId == 49149) && (pc.get_sex() == 0) && pc.isCrown()) { // 夏纳的变身卷轴(等级30)
			polyId = 6822;
		}
		else if ((itemId == 49149) && (pc.get_sex() == 1) && pc.isCrown()) {
			polyId = 6823;
		}
		else if ((itemId == 49149) && (pc.get_sex() == 0) && pc.isKnight()) {
			polyId = 6824;
		}
		else if ((itemId == 49149) && (pc.get_sex() == 1) && pc.isKnight()) {
			polyId = 6825;
		}
		else if ((itemId == 49149) && (pc.get_sex() == 0) && pc.isElf()) {
			polyId = 6826;
		}
		else if ((itemId == 49149) && (pc.get_sex() == 1) && pc.isElf()) {
			polyId = 6827;
		}
		else if ((itemId == 49149) && (pc.get_sex() == 0) && pc.isWizard()) {
			polyId = 6828;
		}
		else if ((itemId == 49149) && (pc.get_sex() == 1) && pc.isWizard()) {
			polyId = 6829;
		}
		else if ((itemId == 49149) && (pc.get_sex() == 0) && pc.isDarkelf()) {
			polyId = 6830;
		}
		else if ((itemId == 49149) && (pc.get_sex() == 1) && pc.isDarkelf()) {
			polyId = 6831;
		}
		else if ((itemId == 49149) && (pc.get_sex() == 0) && pc.isDragonKnight()) {
			polyId = 7139;
		}
		else if ((itemId == 49149) && (pc.get_sex() == 1) && pc.isDragonKnight()) {
			polyId = 7140;
		}
		else if ((itemId == 49149) && (pc.get_sex() == 0) && pc.isIllusionist()) {
			polyId = 7141;
		}
		else if ((itemId == 49149) && (pc.get_sex() == 1) && pc.isIllusionist()) {
			polyId = 7142;
		}
		else if ((itemId == 49150) && (pc.get_sex() == 0) && pc.isCrown()) { // 夏纳的变身卷轴(等级40)
			polyId = 6832;
		}
		else if ((itemId == 49150) && (pc.get_sex() == 1) && pc.isCrown()) {
			polyId = 6833;
		}
		else if ((itemId == 49150) && (pc.get_sex() == 0) && pc.isKnight()) {
			polyId = 6834;
		}
		else if ((itemId == 49150) && (pc.get_sex() == 1) && pc.isKnight()) {
			polyId = 6835;
		}
		else if ((itemId == 49150) && (pc.get_sex() == 0) && pc.isElf()) {
			polyId = 6836;
		}
		else if ((itemId == 49150) && (pc.get_sex() == 1) && pc.isElf()) {
			polyId = 6837;
		}
		else if ((itemId == 49150) && (pc.get_sex() == 0) && pc.isWizard()) {
			polyId = 6838;
		}
		else if ((itemId == 49150) && (pc.get_sex() == 1) && pc.isWizard()) {
			polyId = 6839;
		}
		else if ((itemId == 49150) && (pc.get_sex() == 0) && pc.isDarkelf()) {
			polyId = 6840;
		}
		else if ((itemId == 49150) && (pc.get_sex() == 1) && pc.isDarkelf()) {
			polyId = 6841;
		}
		else if ((itemId == 49150) && (pc.get_sex() == 0) && pc.isDragonKnight()) {
			polyId = 7143;
		}
		else if ((itemId == 49150) && (pc.get_sex() == 1) && pc.isDragonKnight()) {
			polyId = 7144;
		}
		else if ((itemId == 49150) && (pc.get_sex() == 0) && pc.isIllusionist()) {
			polyId = 7145;
		}
		else if ((itemId == 49150) && (pc.get_sex() == 1) && pc.isIllusionist()) {
			polyId = 7146;
		}
		else if ((itemId == 49151) && (pc.get_sex() == 0) && pc.isCrown()) { // 夏纳的变身卷轴(等级52)
			polyId = 6842;
		}
		else if ((itemId == 49151) && (pc.get_sex() == 1) && pc.isCrown()) {
			polyId = 6843;
		}
		else if ((itemId == 49151) && (pc.get_sex() == 0) && pc.isKnight()) {
			polyId = 6844;
		}
		else if ((itemId == 49151) && (pc.get_sex() == 1) && pc.isKnight()) {
			polyId = 6845;
		}
		else if ((itemId == 49151) && (pc.get_sex() == 0) && pc.isElf()) {
			polyId = 6846;
		}
		else if ((itemId == 49151) && (pc.get_sex() == 1) && pc.isElf()) {
			polyId = 6847;
		}
		else if ((itemId == 49151) && (pc.get_sex() == 0) && pc.isWizard()) {
			polyId = 6848;
		}
		else if ((itemId == 49151) && (pc.get_sex() == 1) && pc.isWizard()) {
			polyId = 6849;
		}
		else if ((itemId == 49151) && (pc.get_sex() == 0) && pc.isDarkelf()) {
			polyId = 6850;
		}
		else if ((itemId == 49151) && (pc.get_sex() == 1) && pc.isDarkelf()) {
			polyId = 6851;
		}
		else if ((itemId == 49151) && (pc.get_sex() == 0) && pc.isDragonKnight()) {
			polyId = 7147;
		}
		else if ((itemId == 49151) && (pc.get_sex() == 1) && pc.isDragonKnight()) {
			polyId = 7148;
		}
		else if ((itemId == 49151) && (pc.get_sex() == 0) && pc.isIllusionist()) {
			polyId = 7149;
		}
		else if ((itemId == 49151) && (pc.get_sex() == 1) && pc.isIllusionist()) {
			polyId = 7150;
		}
		else if ((itemId == 49152) && (pc.get_sex() == 0) && pc.isCrown()) { // 夏纳的变身卷轴(等级55)
			polyId = 6852;
		}
		else if ((itemId == 49152) && (pc.get_sex() == 1) && pc.isCrown()) {
			polyId = 6853;
		}
		else if ((itemId == 49152) && (pc.get_sex() == 0) && pc.isKnight()) {
			polyId = 6854;
		}
		else if ((itemId == 49152) && (pc.get_sex() == 1) && pc.isKnight()) {
			polyId = 6855;
		}
		else if ((itemId == 49152) && (pc.get_sex() == 0) && pc.isElf()) {
			polyId = 6856;
		}
		else if ((itemId == 49152) && (pc.get_sex() == 1) && pc.isElf()) {
			polyId = 6857;
		}
		else if ((itemId == 49152) && (pc.get_sex() == 0) && pc.isWizard()) {
			polyId = 6858;
		}
		else if ((itemId == 49152) && (pc.get_sex() == 1) && pc.isWizard()) {
			polyId = 6859;
		}
		else if ((itemId == 49152) && (pc.get_sex() == 0) && pc.isDarkelf()) {
			polyId = 6860;
		}
		else if ((itemId == 49152) && (pc.get_sex() == 1) && pc.isDarkelf()) {
			polyId = 6861;
		}
		else if ((itemId == 49152) && (pc.get_sex() == 0) && pc.isDragonKnight()) {
			polyId = 7151;
		}
		else if ((itemId == 49152) && (pc.get_sex() == 1) && pc.isDragonKnight()) {
			polyId = 7152;
		}
		else if ((itemId == 49152) && (pc.get_sex() == 0) && pc.isIllusionist()) {
			polyId = 7153;
		}
		else if ((itemId == 49152) && (pc.get_sex() == 1) && pc.isIllusionist()) {
			polyId = 7154;
		}
		else if ((itemId == 49153) && (pc.get_sex() == 0) && pc.isCrown()) { // 夏纳的变身卷轴(等级60)
			polyId = 6862;
		}
		else if ((itemId == 49153) && (pc.get_sex() == 1) && pc.isCrown()) {
			polyId = 6863;
		}
		else if ((itemId == 49153) && (pc.get_sex() == 0) && pc.isKnight()) {
			polyId = 6864;
		}
		else if ((itemId == 49153) && (pc.get_sex() == 1) && pc.isKnight()) {
			polyId = 6865;
		}
		else if ((itemId == 49153) && (pc.get_sex() == 0) && pc.isElf()) {
			polyId = 6866;
		}
		else if ((itemId == 49153) && (pc.get_sex() == 1) && pc.isElf()) {
			polyId = 6867;
		}
		else if ((itemId == 49153) && (pc.get_sex() == 0) && pc.isWizard()) {
			polyId = 6868;
		}
		else if ((itemId == 49153) && (pc.get_sex() == 1) && pc.isWizard()) {
			polyId = 6869;
		}
		else if ((itemId == 49153) && (pc.get_sex() == 0) && pc.isDarkelf()) {
			polyId = 6870;
		}
		else if ((itemId == 49153) && (pc.get_sex() == 1) && pc.isDarkelf()) {
			polyId = 6871;
		}
		else if ((itemId == 49153) && (pc.get_sex() == 0) && pc.isDragonKnight()) {
			polyId = 7155;
		}
		else if ((itemId == 49153) && (pc.get_sex() == 1) && pc.isDragonKnight()) {
			polyId = 7156;
		}
		else if ((itemId == 49153) && (pc.get_sex() == 0) && pc.isIllusionist()) {
			polyId = 7157;
		}
		else if ((itemId == 49153) && (pc.get_sex() == 1) && pc.isIllusionist()) {
			polyId = 7158;
		}
		else if ((itemId == 49154) && (pc.get_sex() == 0) && pc.isCrown()) { // 夏纳的变身卷轴(等级65)
			polyId = 6872;
		}
		else if ((itemId == 49154) && (pc.get_sex() == 1) && pc.isCrown()) {
			polyId = 6873;
		}
		else if ((itemId == 49154) && (pc.get_sex() == 0) && pc.isKnight()) {
			polyId = 6874;
		}
		else if ((itemId == 49154) && (pc.get_sex() == 1) && pc.isKnight()) {
			polyId = 6875;
		}
		else if ((itemId == 49154) && (pc.get_sex() == 0) && pc.isElf()) {
			polyId = 6876;
		}
		else if ((itemId == 49154) && (pc.get_sex() == 1) && pc.isElf()) {
			polyId = 6877;
		}
		else if ((itemId == 49154) && (pc.get_sex() == 0) && pc.isWizard()) {
			polyId = 6878;
		}
		else if ((itemId == 49154) && (pc.get_sex() == 1) && pc.isWizard()) {
			polyId = 6879;
		}
		else if ((itemId == 49154) && (pc.get_sex() == 0) && pc.isDarkelf()) {
			polyId = 6880;
		}
		else if ((itemId == 49154) && (pc.get_sex() == 1) && pc.isDarkelf()) {
			polyId = 6881;
		}
		else if ((itemId == 49154) && (pc.get_sex() == 0) && pc.isDragonKnight()) {
			polyId = 7159;
		}
		else if ((itemId == 49154) && (pc.get_sex() == 1) && pc.isDragonKnight()) {
			polyId = 7160;
		}
		else if ((itemId == 49154) && (pc.get_sex() == 0) && pc.isIllusionist()) {
			polyId = 7161;
		}
		else if ((itemId == 49154) && (pc.get_sex() == 1) && pc.isIllusionist()) {
			polyId = 7162;
		}
		else if ((itemId == 49155) && (pc.get_sex() == 0) && pc.isCrown()) { // 夏纳的变身卷轴(等级70)
			polyId = 6882;
		}
		else if ((itemId == 49155) && (pc.get_sex() == 1) && pc.isCrown()) {
			polyId = 6883;
		}
		else if ((itemId == 49155) && (pc.get_sex() == 0) && pc.isKnight()) {
			polyId = 6884;
		}
		else if ((itemId == 49155) && (pc.get_sex() == 1) && pc.isKnight()) {
			polyId = 6885;
		}
		else if ((itemId == 49155) && (pc.get_sex() == 0) && pc.isElf()) {
			polyId = 6886;
		}
		else if ((itemId == 49155) && (pc.get_sex() == 1) && pc.isElf()) {
			polyId = 6887;
		}
		else if ((itemId == 49155) && (pc.get_sex() == 0) && pc.isWizard()) {
			polyId = 6888;
		}
		else if ((itemId == 49155) && (pc.get_sex() == 1) && pc.isWizard()) {
			polyId = 6889;
		}
		else if ((itemId == 49155) && (pc.get_sex() == 0) && pc.isDarkelf()) {
			polyId = 6890;
		}
		else if ((itemId == 49155) && (pc.get_sex() == 1) && pc.isDarkelf()) {
			polyId = 6891;
		}
		else if ((itemId == 49155) && (pc.get_sex() == 0) && pc.isDragonKnight()) {
			polyId = 7163;
		}
		else if ((itemId == 49155) && (pc.get_sex() == 1) && pc.isDragonKnight()) {
			polyId = 7164;
		}
		else if ((itemId == 49155) && (pc.get_sex() == 0) && pc.isIllusionist()) {
			polyId = 7165;
		}
		else if ((itemId == 49155) && (pc.get_sex() == 1) && pc.isIllusionist()) {
			polyId = 7166;
		}
		else if ((itemId == 49139)) { // 起司蛋糕
			polyId = 6137; // 52级死亡骑士
			time = 900;
		}
		L1PolyMorph.doPoly(pc, polyId, time, L1PolyMorph.MORPH_BY_ITEMMAGIC);
	}

	private void UseArmor(L1PcInstance activeChar, L1ItemInstance armor) {
		int type = armor.getItem().getType();
		L1PcInventory pcInventory = activeChar.getInventory();
		boolean equipeSpace; // 装备する个所が空いているか
		if (type == 9) { // リングの场合
			equipeSpace = pcInventory.getTypeEquipped(2, 9) <= 1;
		}
		else {
			equipeSpace = pcInventory.getTypeEquipped(2, type) <= 0;
		}

		if (equipeSpace && !armor.isEquipped()) { // 使用した防具を装备していなくて、その装备个所が空いている场合（装着を试みる）
			int polyid = activeChar.getTempCharGfx();

			if (!L1PolyMorph.isEquipableArmor(polyid, type)) { // その变身では装备不可
				return;
			}

			if (((type == 13) && (pcInventory.getTypeEquipped(2, 7) >= 1)) || ((type == 7) && (pcInventory.getTypeEquipped(2, 13) >= 1))) { // シールド、ガーダー同时装备不可
				activeChar.sendPackets(new S_ServerMessage(124)); // \f1已经装备其他东西。
				return;
			}
			if ((type == 7) && (activeChar.getWeapon() != null)) { // シールドの场合、武器を装备していたら两手武器チェック
				if (activeChar.getWeapon().getItem().isTwohandedWeapon()) { // 两手武器
					activeChar.sendPackets(new S_ServerMessage(129)); // \f1当你使用双手武器时，无法装备盾牌。
					return;
				}
			}

			if ((type == 3) && (pcInventory.getTypeEquipped(2, 4) >= 1)) { // シャツの场合、マントを着てないか确认
				activeChar.sendPackets(new S_ServerMessage(126, "$224", "$225")); // \f1穿着%1 无法装备 %0%o 。
				return;
			}
			else if ((type == 3) && (pcInventory.getTypeEquipped(2, 2) >= 1)) { // シャツの场合、メイルを着てないか确认
				activeChar.sendPackets(new S_ServerMessage(126, "$224", "$226")); // \f1穿着%1 无法装备 %0%o 。
				return;
			}
			else if ((type == 2) && (pcInventory.getTypeEquipped(2, 4) >= 1)) { // メイルの场合、マントを着てないか确认
				activeChar.sendPackets(new S_ServerMessage(126, "$226", "$225")); // \f1穿着%1 无法装备 %0%o 。
				return;
			}

			pcInventory.setEquipped(armor, true);
		}
		else if (armor.isEquipped()) { // 使用した防具を装备していた场合（脱着を试みる）
			if (armor.getItem().getBless() == 2) { // 咒われていた场合
				activeChar.sendPackets(new S_ServerMessage(150)); // \f1你无法这样做。这个物品已经被诅咒了。
				return;
			}
			if ((type == 3) && (pcInventory.getTypeEquipped(2, 2) >= 1)) { // シャツの场合、メイルを着てないか确认
				activeChar.sendPackets(new S_ServerMessage(127)); // \f1你不能够脱掉那个。
				return;
			}
			else if (((type == 2) || (type == 3)) && (pcInventory.getTypeEquipped(2, 4) >= 1)) { // シャツとメイルの场合、マントを着てないか确认
				activeChar.sendPackets(new S_ServerMessage(127)); // \f1你不能够脱掉那个。
				return;
			}
			if (type == 7) { // シールドの场合、ソリッドキャリッジの效果消失
				if (activeChar.hasSkillEffect(SOLID_CARRIAGE)) {
					activeChar.removeSkillEffect(SOLID_CARRIAGE);
				}
			}
			pcInventory.setEquipped(armor, false);
		}
		else {
			activeChar.sendPackets(new S_ServerMessage(124)); // \f1已经装备其他东西。
		}
		// セット装备用HP、MP、MR更新
		activeChar.setCurrentHp(activeChar.getCurrentHp());
		activeChar.setCurrentMp(activeChar.getCurrentMp());
		activeChar.sendPackets(new S_OwnCharAttrDef(activeChar));
		activeChar.sendPackets(new S_OwnCharStatus(activeChar));
		activeChar.sendPackets(new S_SPMR(activeChar));
	}

	private void UseWeapon(L1PcInstance activeChar, L1ItemInstance weapon) {
		L1PcInventory pcInventory = activeChar.getInventory();
		if ((activeChar.getWeapon() == null) || !activeChar.getWeapon().equals(weapon)) { // 指定された武器が装备している武器と违う场合、装备できるか确认
			int weapon_type = weapon.getItem().getType();
			int polyid = activeChar.getTempCharGfx();

			if (!L1PolyMorph.isEquipableWeapon(polyid, weapon_type)) { // その变身では装备不可
				return;
			}
			if (weapon.getItem().isTwohandedWeapon() && (pcInventory.getTypeEquipped(2, 7) >= 1)) { // 两手武器の场合、シールド装备の确认
				activeChar.sendPackets(new S_ServerMessage(128)); // \f1当你拿着一个盾时，你无法使用双手武器。
				return;
			}
		}

		if (activeChar.getWeapon() != null) { // 既に何かを装备している场合、前の装备をはずす
			if (activeChar.getWeapon().getItem().getBless() == 2) { // 咒われていた场合
				activeChar.sendPackets(new S_ServerMessage(150)); // \f1你无法这样做。这个物品已经被诅咒了。
				return;
			}
			if (activeChar.getWeapon().equals(weapon)) {
				// 装备交换ではなく外すだけ
				pcInventory.setEquipped(activeChar.getWeapon(), false, false, false);
				return;
			}
			else {
				pcInventory.setEquipped(activeChar.getWeapon(), false, false, true);
			}
		}

		if (weapon.getItemId() == 200002) { // 受诅咒的 骰子匕首
			activeChar.sendPackets(new S_ServerMessage(149, weapon.getLogName())); // \f1%0%s 主动固定在你的手上！
		}
		pcInventory.setEquipped(weapon, true, false, false);
	}

	// 魔法书 (技能书)
	private void useSpellBook(L1PcInstance pc, L1ItemInstance item, int itemId) {
		int itemAttr = 0;
		int locAttr = 0; // 0:other 1:law 2:chaos
		boolean isLawful = true;
		int pcX = pc.getX();
		int pcY = pc.getY();
		int mapId = pc.getMapId();
		int level = pc.getLevel();
		if ((itemId == 45000) || (itemId == 45008) || (itemId == 45018) || (itemId == 45021) || (itemId == 40171) || (itemId == 40179)
				|| (itemId == 40180) || (itemId == 40182) || (itemId == 40194) || (itemId == 40197) || (itemId == 40202) || (itemId == 40206)
				|| (itemId == 40213) || (itemId == 40220) || (itemId == 40222)) {
			itemAttr = 1;
		}
		if ((itemId == 45009) || (itemId == 45010) || (itemId == 45019) || (itemId == 40172) || (itemId == 40173) || (itemId == 40178)
				|| (itemId == 40185) || (itemId == 40186) || (itemId == 40192) || (itemId == 40196) || (itemId == 40201) || (itemId == 40204)
				|| (itemId == 40211) || (itemId == 40221) || (itemId == 40225)) {
			itemAttr = 2;
		}
		// ロウフルテンプル
		if (((pcX > 33116) && (pcX < 33128) && (pcY > 32930) && (pcY < 32942) && (mapId == 4))
				|| ((pcX > 33135) && (pcX < 33147) && (pcY > 32235) && (pcY < 32247) && (mapId == 4))
				|| ((pcX >= 32783) && (pcX <= 32803) && (pcY >= 32831) && (pcY <= 32851) && (mapId == 77))) {
			locAttr = 1;
			isLawful = true;
		}
		// カオティックテンプル
		if (((pcX > 32880) && (pcX < 32892) && (pcY > 32646) && (pcY < 32658) && (mapId == 4))
				|| ((pcX > 32662) && (pcX < 32674) && (pcY > 32297) && (pcY < 32309) && (mapId == 4))) {
			locAttr = 2;
			isLawful = false;
		}
		if (pc.isGm()) {
			SpellBook(pc, item, isLawful);
		}
		else if (((itemAttr == locAttr) || (itemAttr == 0)) && (locAttr != 0)) {
			if (pc.isKnight()) {
				if ((itemId >= 45000) && (itemId <= 45007) && (level >= 50)) {
					SpellBook(pc, item, isLawful);
				}
				else if ((itemId >= 45000) && (itemId <= 45007)) {
					pc.sendPackets(new S_ServerMessage(312));
				}
				else {
					pc.sendPackets(new S_ServerMessage(79));
				}
			}
			else if (pc.isCrown() || pc.isDarkelf()) {
				if ((itemId >= 45000) && (itemId <= 45007) && (level >= 10)) {
					SpellBook(pc, item, isLawful);
				}
				else if ((itemId >= 45008) && (itemId <= 45015) && (level >= 20)) {
					SpellBook(pc, item, isLawful);
				}
				else if (((itemId >= 45008) && (itemId <= 45015)) || ((itemId >= 45000) && (itemId <= 45007))) {
					pc.sendPackets(new S_ServerMessage(312)); // 你还不能学习法术。
				}
				else {
					pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
				}
			}
			else if (pc.isElf()) {
				if ((itemId >= 45000) && (itemId <= 45007) && (level >= 8)) {
					SpellBook(pc, item, isLawful);
				}
				else if ((itemId >= 45008) && (itemId <= 45015) && (level >= 16)) {
					SpellBook(pc, item, isLawful);
				}
				else if ((itemId >= 45016) && (itemId <= 45022) && (level >= 24)) {
					SpellBook(pc, item, isLawful);
				}
				else if ((itemId >= 40170) && (itemId <= 40177) && (level >= 32)) {
					SpellBook(pc, item, isLawful);
				}
				else if ((itemId >= 40178) && (itemId <= 40185) && (level >= 40)) {
					SpellBook(pc, item, isLawful);
				}
				else if ((itemId >= 40186) && (itemId <= 40193) && (level >= 48)) {
					SpellBook(pc, item, isLawful);
				}
				else if (((itemId >= 45000) && (itemId <= 45022)) || ((itemId >= 40170) && (itemId <= 40193))) {
					pc.sendPackets(new S_ServerMessage(312)); // 你还不能学习法术。
				}
				else {
					pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
				}
			}
			else if (pc.isWizard()) {
				if ((itemId >= 45000) && (itemId <= 45007) && (level >= 4)) {
					SpellBook(pc, item, isLawful);
				}
				else if ((itemId >= 45008) && (itemId <= 45015) && (level >= 8)) {
					SpellBook(pc, item, isLawful);
				}
				else if ((itemId >= 45016) && (itemId <= 45022) && (level >= 12)) {
					SpellBook(pc, item, isLawful);
				}
				else if ((itemId >= 40170) && (itemId <= 40177) && (level >= 16)) {
					SpellBook(pc, item, isLawful);
				}
				else if ((itemId >= 40178) && (itemId <= 40185) && (level >= 20)) {
					SpellBook(pc, item, isLawful);
				}
				else if ((itemId >= 40186) && (itemId <= 40193) && (level >= 24)) {
					SpellBook(pc, item, isLawful);
				}
				else if ((itemId >= 40194) && (itemId <= 40201) && (level >= 28)) {
					SpellBook(pc, item, isLawful);
				}
				else if ((itemId >= 40202) && (itemId <= 40209) && (level >= 32)) {
					SpellBook(pc, item, isLawful);
				}
				else if ((itemId >= 40210) && (itemId <= 40217) && (level >= 36)) {
					SpellBook(pc, item, isLawful);
				}
				else if ((itemId >= 40218) && (itemId <= 40225) && (level >= 40)) {
					SpellBook(pc, item, isLawful);
				}
				else {
					pc.sendPackets(new S_ServerMessage(312)); // 你还不能学习法术。
				}
			}
		}
		else if ((itemAttr != locAttr) && (itemAttr != 0) && (locAttr != 0)) {
			// 间违ったテンプルで读んだ场合雷が落ちる
			pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
			S_SkillSound effect = new S_SkillSound(pc.getId(), 10);
			pc.sendPackets(effect);
			pc.broadcastPacket(effect);
			// ダメージは适当
			pc.setCurrentHp(Math.max(pc.getCurrentHp() - 45, 0));
			if (pc.getCurrentHp() <= 0) {
				pc.death(null);
			}
			pc.getInventory().removeItem(item, 1);
		}
		else {
			pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
		}
	}

	// 精灵水晶 (技能书)
	private void useElfSpellBook(L1PcInstance pc, L1ItemInstance item, int itemId) {
		int level = pc.getLevel();
		if ((pc.isElf() || pc.isGm()) && isLearnElfMagic(pc)) {
			if ((itemId >= 40232) && (itemId <= 40234) && (level >= 10)) {
				SpellBook2(pc, item);
			}
			else if ((itemId >= 40235) && (itemId <= 40236) && (level >= 20)) {
				SpellBook2(pc, item);
			}
			if ((itemId >= 40237) && (itemId <= 40240) && (level >= 30)) {
				SpellBook2(pc, item);
			}
			else if ((itemId >= 40241) && (itemId <= 40243) && (level >= 40)) {
				SpellBook2(pc, item);
			}
			else if ((itemId >= 40244) && (itemId <= 40246) && (level >= 50)) {
				SpellBook2(pc, item);
			}
			else if ((itemId >= 40247) && (itemId <= 40248) && (level >= 30)) {
				SpellBook2(pc, item);
			}
			else if ((itemId >= 40249) && (itemId <= 40250) && (level >= 40)) {
				SpellBook2(pc, item);
			}
			else if ((itemId >= 40251) && (itemId <= 40252) && (level >= 50)) {
				SpellBook2(pc, item);
			}
			else if ((itemId == 40253) && (level >= 30)) {
				SpellBook2(pc, item);
			}
			else if ((itemId == 40254) && (level >= 40)) {
				SpellBook2(pc, item);
			}
			else if ((itemId == 40255) && (level >= 50)) {
				SpellBook2(pc, item);
			}
			else if ((itemId == 40256) && (level >= 30)) {
				SpellBook2(pc, item);
			}
			else if ((itemId == 40257) && (level >= 40)) {
				SpellBook2(pc, item);
			}
			else if ((itemId >= 40258) && (itemId <= 40259) && (level >= 50)) {
				SpellBook2(pc, item);
			}
			else if ((itemId >= 40260) && (itemId <= 40261) && (level >= 30)) {
				SpellBook2(pc, item);
			}
			else if ((itemId == 40262) && (level >= 40)) {
				SpellBook2(pc, item);
			}
			else if ((itemId >= 40263) && (itemId <= 40264) && (level >= 50)) {
				SpellBook2(pc, item);
			}
			else if ((itemId >= 41149) && (itemId <= 41150) && (level >= 50)) {
				SpellBook2(pc, item);
			}
			else if ((itemId == 41151) && (level >= 40)) {
				SpellBook2(pc, item);
			}
			else if ((itemId >= 41152) && (itemId <= 41153) && (level >= 50)) {
				SpellBook2(pc, item);
			}
		}
		else {
			pc.sendPackets(new S_ServerMessage(79)); // (原文:精灵の水晶はエルフのみが习得できます。)
		}
	}

	// 魔法学习地点
	private boolean isLearnElfMagic(L1PcInstance pc) {
		int pcX = pc.getX();
		int pcY = pc.getY();
		int pcMapId = pc.getMapId();
		if (((pcX >= 32786) && (pcX <= 32797) && (pcY >= 32842) && (pcY <= 32859) && (pcMapId == 75 // 象牙塔
				))
				|| (pc.getLocation().isInScreen(new Point(33055, 32336)) && (pcMapId == 4))) { // 邪恶神殿
			return true;
		}
		return false;
	}

	// 魔法书
	private void SpellBook(L1PcInstance pc, L1ItemInstance item, boolean isLawful) {
		String s = "";
		int i = 0;
		int level1 = 0;
		int level2 = 0;
		int l = 0;
		int i1 = 0;
		int j1 = 0;
		int k1 = 0;
		int l1 = 0;
		int i2 = 0;
		int j2 = 0;
		int k2 = 0;
		int l2 = 0;
		int i3 = 0;
		int j3 = 0;
		int k3 = 0;
		int l3 = 0;
		int i4 = 0;
		int j4 = 0;
		int k4 = 0;
		int l4 = 0;
		int i5 = 0;
		int j5 = 0;
		int k5 = 0;
		int l5 = 0;
		int i6 = 0;
		for (int skillId = 1; skillId < 81; skillId++) {
			L1Skills l1skills = SkillsTable.getInstance().getTemplate(skillId);
			String s1 = null;
			if (Config.CLIENT_LANGUAGE == 3) {
				s1 = (new StringBuilder()).append("魔法书(").append(l1skills.getName()).append(")").toString();
			}
			else if (Config.CLIENT_LANGUAGE == 5) {
				s1 = (new StringBuilder()).append("魔法书(").append(l1skills.getName()).append(")").toString();
			}
			else {
				s1 = (new StringBuilder()).append("魔法书(").append(l1skills.getName()).append(")").toString();
			}
			if (item.getItem().getName().equalsIgnoreCase(s1)) {
				int skillLevel = l1skills.getSkillLevel();
				int i7 = l1skills.getId();
				s = l1skills.getName();
				i = l1skills.getSkillId();
				switch (skillLevel) {
					case 1: // '\001'
						level1 = i7;
						break;

					case 2: // '\002'
						level2 = i7;
						break;

					case 3: // '\003'
						l = i7;
						break;

					case 4: // '\004'
						i1 = i7;
						break;

					case 5: // '\005'
						j1 = i7;
						break;

					case 6: // '\006'
						k1 = i7;
						break;

					case 7: // '\007'
						l1 = i7;
						break;

					case 8: // '\b'
						i2 = i7;
						break;

					case 9: // '\t'
						j2 = i7;
						break;

					case 10: // '\n'
						k2 = i7;
						break;

					case 11: // '\013'
						l2 = i7;
						break;

					case 12: // '\f'
						i3 = i7;
						break;

					case 13: // '\r'
						j3 = i7;
						break;

					case 14: // '\016'
						k3 = i7;
						break;

					case 15: // '\017'
						l3 = i7;
						break;

					case 16: // '\020'
						i4 = i7;
						break;

					case 17: // '\021'
						j4 = i7;
						break;

					case 18: // '\022'
						k4 = i7;
						break;

					case 19: // '\023'
						l4 = i7;
						break;

					case 20: // '\024'
						i5 = i7;
						break;

					case 21: // '\025'
						j5 = i7;
						break;

					case 22: // '\026'
						k5 = i7;
						break;

					case 23: // '\027'
						l5 = i7;
						break;

					case 24: // '\030'
						i6 = i7;
						break;
				}
			}
		}

		int objid = pc.getId();
		pc.sendPackets(new S_AddSkill(level1, level2, l, i1, j1, k1, l1, i2, j2, k2, l2, i3, j3, k3, l3, i4, j4, k4, l4, i5, j5, k5, l5, i6, 0, 0, 0,
				0));
		S_SkillSound s_skillSound = new S_SkillSound(objid, isLawful ? 224 : 231);
		pc.sendPackets(s_skillSound);
		pc.broadcastPacket(s_skillSound);
		SkillsTable.getInstance().spellMastery(objid, i, s, 0, 0);
		pc.getInventory().removeItem(item, 1);
	}

	private void SpellBook1(L1PcInstance pc, L1ItemInstance l1iteminstance, ClientThread clientthread) {
		String s = "";
		int i = 0;
		int j = 0;
		int k = 0;
		int l = 0;
		int i1 = 0;
		int j1 = 0;
		int k1 = 0;
		int l1 = 0;
		int i2 = 0;
		int j2 = 0;
		int k2 = 0;
		int l2 = 0;
		int i3 = 0;
		int j3 = 0;
		int k3 = 0;
		int l3 = 0;
		int i4 = 0;
		int j4 = 0;
		int k4 = 0;
		int l4 = 0;
		int i5 = 0;
		int j5 = 0;
		int k5 = 0;
		int l5 = 0;
		int i6 = 0;
		for (int j6 = 97; j6 < 112; j6++) {
			L1Skills l1skills = SkillsTable.getInstance().getTemplate(j6);
			String s1 = null;
			if (Config.CLIENT_LANGUAGE == 3) {
				s1 = (new StringBuilder()).append("黑暗精灵水晶(").append(l1skills.getName()).append(")").toString();
			}
			else if (Config.CLIENT_LANGUAGE == 5) {
				s1 = (new StringBuilder()).append("黑暗精灵水晶(").append(l1skills.getName()).append(")").toString();
			}
			else {
				s1 = (new StringBuilder()).append("闇精灵の水晶(").append(l1skills.getName()).append(")").toString();
			}
			if (l1iteminstance.getItem().getName().equalsIgnoreCase(s1)) {
				int l6 = l1skills.getSkillLevel();
				int i7 = l1skills.getId();
				s = l1skills.getName();
				i = l1skills.getSkillId();
				switch (l6) {
					case 1: // '\001'
						j = i7;
						break;

					case 2: // '\002'
						k = i7;
						break;

					case 3: // '\003'
						l = i7;
						break;

					case 4: // '\004'
						i1 = i7;
						break;

					case 5: // '\005'
						j1 = i7;
						break;

					case 6: // '\006'
						k1 = i7;
						break;

					case 7: // '\007'
						l1 = i7;
						break;

					case 8: // '\b'
						i2 = i7;
						break;

					case 9: // '\t'
						j2 = i7;
						break;

					case 10: // '\n'
						k2 = i7;
						break;

					case 11: // '\013'
						l2 = i7;
						break;

					case 12: // '\f'
						i3 = i7;
						break;

					case 13: // '\r'
						j3 = i7;
						break;

					case 14: // '\016'
						k3 = i7;
						break;

					case 15: // '\017'
						l3 = i7;
						break;

					case 16: // '\020'
						i4 = i7;
						break;

					case 17: // '\021'
						j4 = i7;
						break;

					case 18: // '\022'
						k4 = i7;
						break;

					case 19: // '\023'
						l4 = i7;
						break;

					case 20: // '\024'
						i5 = i7;
						break;

					case 21: // '\025'
						j5 = i7;
						break;

					case 22: // '\026'
						k5 = i7;
						break;

					case 23: // '\027'
						l5 = i7;
						break;

					case 24: // '\030'
						i6 = i7;
						break;
				}
			}
		}

		int k6 = pc.getId();
		pc.sendPackets(new S_AddSkill(j, k, l, i1, j1, k1, l1, i2, j2, k2, l2, i3, j3, k3, l3, i4, j4, k4, l4, i5, j5, k5, l5, i6, 0, 0, 0, 0));
		S_SkillSound s_skillSound = new S_SkillSound(k6, 231);
		pc.sendPackets(s_skillSound);
		pc.broadcastPacket(s_skillSound);
		SkillsTable.getInstance().spellMastery(k6, i, s, 0, 0);
		pc.getInventory().removeItem(l1iteminstance, 1);
	}

	private void SpellBook2(L1PcInstance pc, L1ItemInstance l1iteminstance) {
		String s = "";
		int i = 0;
		int j = 0;
		int k = 0;
		int l = 0;
		int i1 = 0;
		int j1 = 0;
		int k1 = 0;
		int l1 = 0;
		int i2 = 0;
		int j2 = 0;
		int k2 = 0;
		int l2 = 0;
		int i3 = 0;
		int j3 = 0;
		int k3 = 0;
		int l3 = 0;
		int i4 = 0;
		int j4 = 0;
		int k4 = 0;
		int l4 = 0;
		int i5 = 0;
		int j5 = 0;
		int k5 = 0;
		int l5 = 0;
		int i6 = 0;
		for (int j6 = 129; j6 <= 176; j6++) {
			L1Skills l1skills = SkillsTable.getInstance().getTemplate(j6);
			String s1 = null;
			if (Config.CLIENT_LANGUAGE == 3) {
				s1 = (new StringBuilder()).append("精灵水晶(").append(l1skills.getName()).append(")").toString();
			}
			else if (Config.CLIENT_LANGUAGE == 5) {
				s1 = (new StringBuilder()).append("精灵水晶(").append(l1skills.getName()).append(")").toString();
			}
			else {
				s1 = (new StringBuilder()).append("精灵の水晶(").append(l1skills.getName()).append(")").toString();
			}
			if (l1iteminstance.getItem().getName().equalsIgnoreCase(s1)) {
				if (!pc.isGm() && (l1skills.getAttr() != 0)
						&& (pc.getElfAttr() != l1skills.getAttr())
						&& pc.getElfAttr2() != l1skills.getAttr()) { // TODO 精灵双属性
					if ((pc.getElfAttr() == 0) || (pc.getElfAttr() == 1)
							|| (pc.getElfAttr() == 2) || (pc.getElfAttr() == 4)
							|| (pc.getElfAttr() == 8) || (pc.getElfAttr2() == 0)
							|| (pc.getElfAttr2() == 1) || (pc.getElfAttr2() == 2)
							|| (pc.getElfAttr2() == 4) || (pc.getElfAttr2() == 8)) { // 属性值异常
						pc.sendPackets(new S_ServerMessage(79));
						return;
					}
				}
				int l6 = l1skills.getSkillLevel();
				int i7 = l1skills.getId();
				s = l1skills.getName();
				i = l1skills.getSkillId();
				switch (l6) {
					case 1: // '\001'
						j = i7;
						break;

					case 2: // '\002'
						k = i7;
						break;

					case 3: // '\003'
						l = i7;
						break;

					case 4: // '\004'
						i1 = i7;
						break;

					case 5: // '\005'
						j1 = i7;
						break;

					case 6: // '\006'
						k1 = i7;
						break;

					case 7: // '\007'
						l1 = i7;
						break;

					case 8: // '\b'
						i2 = i7;
						break;

					case 9: // '\t'
						j2 = i7;
						break;

					case 10: // '\n'
						k2 = i7;
						break;

					case 11: // '\013'
						l2 = i7;
						break;

					case 12: // '\f'
						i3 = i7;
						break;

					case 13: // '\r'
						j3 = i7;
						break;

					case 14: // '\016'
						k3 = i7;
						break;

					case 15: // '\017'
						l3 = i7;
						break;

					case 16: // '\020'
						i4 = i7;
						break;

					case 17: // '\021'
						j4 = i7;
						break;

					case 18: // '\022'
						k4 = i7;
						break;

					case 19: // '\023'
						l4 = i7;
						break;

					case 20: // '\024'
						i5 = i7;
						break;

					case 21: // '\025'
						j5 = i7;
						break;

					case 22: // '\026'
						k5 = i7;
						break;

					case 23: // '\027'
						l5 = i7;
						break;

					case 24: // '\030'
						i6 = i7;
						break;
				}
			}
		}

		int k6 = pc.getId();
		pc.sendPackets(new S_AddSkill(j, k, l, i1, j1, k1, l1, i2, j2, k2, l2, i3, j3, k3, l3, i4, j4, k4, l4, i5, j5, k5, l5, i6, 0, 0, 0, 0));
		S_SkillSound s_skillSound = new S_SkillSound(k6, 224);
		pc.sendPackets(s_skillSound);
		pc.broadcastPacket(s_skillSound);
		SkillsTable.getInstance().spellMastery(k6, i, s, 0, 0);
		pc.getInventory().removeItem(l1iteminstance, 1);
	}

	private void SpellBook3(L1PcInstance pc, L1ItemInstance l1iteminstance, ClientThread clientthread) {
		String s = "";
		int i = 0;
		int j = 0;
		int k = 0;
		int l = 0;
		int i1 = 0;
		int j1 = 0;
		int k1 = 0;
		int l1 = 0;
		int i2 = 0;
		int j2 = 0;
		int k2 = 0;
		int l2 = 0;
		int i3 = 0;
		int j3 = 0;
		int k3 = 0;
		int l3 = 0;
		int i4 = 0;
		int j4 = 0;
		int k4 = 0;
		int l4 = 0;
		int i5 = 0;
		int j5 = 0;
		int k5 = 0;
		int l5 = 0;
		int i6 = 0;
		for (int j6 = 87; j6 <= 91; j6++) {
			L1Skills l1skills = SkillsTable.getInstance().getTemplate(j6);

			String s1 = null;
			if (Config.CLIENT_LANGUAGE == 3) {
				s1 = (new StringBuilder()).append("技术书(").append(l1skills.getName()).append(")").toString();
			}
			else if (Config.CLIENT_LANGUAGE == 5) {
				s1 = (new StringBuilder()).append("技术书(").append(l1skills.getName()).append(")").toString();
			}
			else {
				s1 = (new StringBuilder()).append("技术书(").append(l1skills.getName()).append(")").toString();
			}
			if (l1iteminstance.getItem().getName().equalsIgnoreCase(s1)) {
				int l6 = l1skills.getSkillLevel();
				int i7 = l1skills.getId();
				s = l1skills.getName();
				i = l1skills.getSkillId();
				switch (l6) {
					case 1: // '\001'
						j = i7;
						break;

					case 2: // '\002'
						k = i7;
						break;

					case 3: // '\003'
						l = i7;
						break;

					case 4: // '\004'
						i1 = i7;
						break;

					case 5: // '\005'
						j1 = i7;
						break;

					case 6: // '\006'
						k1 = i7;
						break;

					case 7: // '\007'
						l1 = i7;
						break;

					case 8: // '\b'
						i2 = i7;
						break;

					case 9: // '\t'
						j2 = i7;
						break;

					case 10: // '\n'
						k2 = i7;
						break;

					case 11: // '\013'
						l2 = i7;
						break;

					case 12: // '\f'
						i3 = i7;
						break;

					case 13: // '\r'
						j3 = i7;
						break;

					case 14: // '\016'
						k3 = i7;
						break;

					case 15: // '\017'
						l3 = i7;
						break;

					case 16: // '\020'
						i4 = i7;
						break;

					case 17: // '\021'
						j4 = i7;
						break;

					case 18: // '\022'
						k4 = i7;
						break;

					case 19: // '\023'
						l4 = i7;
						break;

					case 20: // '\024'
						i5 = i7;
						break;

					case 21: // '\025'
						j5 = i7;
						break;

					case 22: // '\026'
						k5 = i7;
						break;

					case 23: // '\027'
						l5 = i7;
						break;

					case 24: // '\030'
						i6 = i7;
						break;
				}
			}
		}

		int k6 = pc.getId();
		pc.sendPackets(new S_AddSkill(j, k, l, i1, j1, k1, l1, i2, j2, k2, l2, i3, j3, k3, l3, i4, j4, k4, l4, i5, j5, k5, l5, i6, 0, 0, 0, 0));
		S_SkillSound s_skillSound = new S_SkillSound(k6, 224);
		pc.sendPackets(s_skillSound);
		pc.broadcastPacket(s_skillSound);
		SkillsTable.getInstance().spellMastery(k6, i, s, 0, 0);
		pc.getInventory().removeItem(l1iteminstance, 1);
	}

	private void SpellBook4(L1PcInstance pc, L1ItemInstance l1iteminstance, ClientThread clientthread) {
		String s = "";
		int i = 0;
		int j = 0;
		int k = 0;
		int l = 0;
		int i1 = 0;
		int j1 = 0;
		int k1 = 0;
		int l1 = 0;
		int i2 = 0;
		int j2 = 0;
		int k2 = 0;
		int l2 = 0;
		int i3 = 0;
		int j3 = 0;
		int k3 = 0;
		int l3 = 0;
		int i4 = 0;
		int j4 = 0;
		int k4 = 0;
		int l4 = 0;
		int i5 = 0;
		int j5 = 0;
		int k5 = 0;
		int l5 = 0;
		int i6 = 0;
		for (int j6 = 113; j6 < 121; j6++) {
			L1Skills l1skills = SkillsTable.getInstance().getTemplate(j6);
			String s1 = null;
			if (Config.CLIENT_LANGUAGE == 3) {
				s1 = (new StringBuilder()).append("魔法书(").append(l1skills.getName()).append(")").toString();
			}
			else if (Config.CLIENT_LANGUAGE == 5) {
				s1 = (new StringBuilder()).append("魔法书(").append(l1skills.getName()).append(")").toString();
			}
			else {
				s1 = (new StringBuilder()).append("魔法书(").append(l1skills.getName()).append(")").toString();
			}
			if (l1iteminstance.getItem().getName().equalsIgnoreCase(s1)) {
				int l6 = l1skills.getSkillLevel();
				int i7 = l1skills.getId();
				s = l1skills.getName();
				i = l1skills.getSkillId();
				switch (l6) {
					case 1: // '\001'
						j = i7;
						break;

					case 2: // '\002'
						k = i7;
						break;

					case 3: // '\003'
						l = i7;
						break;

					case 4: // '\004'
						i1 = i7;
						break;

					case 5: // '\005'
						j1 = i7;
						break;

					case 6: // '\006'
						k1 = i7;
						break;

					case 7: // '\007'
						l1 = i7;
						break;

					case 8: // '\b'
						i2 = i7;
						break;

					case 9: // '\t'
						j2 = i7;
						break;

					case 10: // '\n'
						k2 = i7;
						break;

					case 11: // '\013'
						l2 = i7;
						break;

					case 12: // '\f'
						i3 = i7;
						break;

					case 13: // '\r'
						j3 = i7;
						break;

					case 14: // '\016'
						k3 = i7;
						break;

					case 15: // '\017'
						l3 = i7;
						break;

					case 16: // '\020'
						i4 = i7;
						break;

					case 17: // '\021'
						j4 = i7;
						break;

					case 18: // '\022'
						k4 = i7;
						break;

					case 19: // '\023'
						l4 = i7;
						break;

					case 20: // '\024'
						i5 = i7;
						break;

					case 21: // '\025'
						j5 = i7;
						break;

					case 22: // '\026'
						k5 = i7;
						break;

					case 23: // '\027'
						l5 = i7;
						break;

					case 24: // '\030'
						i6 = i7;
						break;
				}
			}
		}

		int k6 = pc.getId();
		pc.sendPackets(new S_AddSkill(j, k, l, i1, j1, k1, l1, i2, j2, k2, l2, i3, j3, k3, l3, i4, j4, k4, l4, i5, j5, k5, l5, i6, 0, 0, 0, 0));
		S_SkillSound s_skillSound = new S_SkillSound(k6, 224);
		pc.sendPackets(s_skillSound);
		pc.broadcastPacket(s_skillSound);
		SkillsTable.getInstance().spellMastery(k6, i, s, 0, 0);
		pc.getInventory().removeItem(l1iteminstance, 1);
	}

	private void SpellBook5(L1PcInstance pc, L1ItemInstance l1iteminstance, ClientThread clientthread) {
		String s = "";
		int i = 0;
		int j = 0;
		int k = 0;
		int l = 0;
		int i1 = 0;
		int j1 = 0;
		int k1 = 0;
		int l1 = 0;
		int i2 = 0;
		int j2 = 0;
		int k2 = 0;
		int l2 = 0;
		int i3 = 0;
		int j3 = 0;
		int k3 = 0;
		int l3 = 0;
		int i4 = 0;
		int j4 = 0;
		int k4 = 0;
		int l4 = 0;
		int i5 = 0;
		int j5 = 0;
		int k5 = 0;
		int l5 = 0;
		int i6 = 0;
		int i8 = 0;
		int j8 = 0;
		int k8 = 0;
		int l8 = 0;
		for (int j6 = 181; j6 <= 195; j6++) {
			L1Skills l1skills = SkillsTable.getInstance().getTemplate(j6);
			String s1 = null;
			if (Config.CLIENT_LANGUAGE == 3) {
				s1 = (new StringBuilder()).append("龙骑士书板(").append(l1skills.getName()).append(")").toString();
			}
			else if (Config.CLIENT_LANGUAGE == 5) {
				s1 = (new StringBuilder()).append("龙骑士书板(").append(l1skills.getName()).append(")").toString();
			}
			else {
				s1 = (new StringBuilder()).append("ドラゴンナイトの书板（").append(l1skills.getName()).append("）").toString();
			}
			if (l1iteminstance.getItem().getName().equalsIgnoreCase(s1)) {
				int l6 = l1skills.getSkillLevel();
				int i7 = l1skills.getId();
				s = l1skills.getName();
				i = l1skills.getSkillId();
				switch (l6) {
					case 1: // '\001'
						j = i7;
						break;

					case 2: // '\002'
						k = i7;
						break;

					case 3: // '\003'
						l = i7;
						break;

					case 4: // '\004'
						i1 = i7;
						break;

					case 5: // '\005'
						j1 = i7;
						break;

					case 6: // '\006'
						k1 = i7;
						break;

					case 7: // '\007'
						l1 = i7;
						break;

					case 8: // '\b'
						i2 = i7;
						break;

					case 9: // '\t'
						j2 = i7;
						break;

					case 10: // '\n'
						k2 = i7;
						break;

					case 11: // '\013'
						l2 = i7;
						break;

					case 12: // '\f'
						i3 = i7;
						break;

					case 13: // '\r'
						j3 = i7;
						break;

					case 14: // '\016'
						k3 = i7;
						break;

					case 15: // '\017'
						l3 = i7;
						break;

					case 16: // '\020'
						i4 = i7;
						break;

					case 17: // '\021'
						j4 = i7;
						break;

					case 18: // '\022'
						k4 = i7;
						break;

					case 19: // '\023'
						l4 = i7;
						break;

					case 20: // '\024'
						i5 = i7;
						break;

					case 21: // '\025'
						j5 = i7;
						break;

					case 22: // '\026'
						k5 = i7;
						break;

					case 23: // '\027'
						l5 = i7;
						break;

					case 24: // '\030'
						i6 = i7;
						break;

					case 25: // '\031'
						j8 = i7;
						break;

					case 26: // '\032'
						k8 = i7;
						break;

					case 27: // '\033'
						l8 = i7;
						break;
					case 28: // '\034'
						i8 = i7;
						break;
				}
			}
		}

		int k6 = pc.getId();
		pc.sendPackets(new S_AddSkill(j, k, l, i1, j1, k1, l1, i2, j2, k2, l2, i3, j3, k3, l3, i4, j4, k4, l4, i5, j5, k5, l5, i6, j8, k8, l8, i8));
		S_SkillSound s_skillSound = new S_SkillSound(k6, 224);
		pc.sendPackets(s_skillSound);
		pc.broadcastPacket(s_skillSound);
		SkillsTable.getInstance().spellMastery(k6, i, s, 0, 0);
		pc.getInventory().removeItem(l1iteminstance, 1);
	}

	private void SpellBook6(L1PcInstance pc, L1ItemInstance l1iteminstance, ClientThread clientthread) {
		String s = "";
		int i = 0;
		int j = 0;
		int k = 0;
		int l = 0;
		int i1 = 0;
		int j1 = 0;
		int k1 = 0;
		int l1 = 0;
		int i2 = 0;
		int j2 = 0;
		int k2 = 0;
		int l2 = 0;
		int i3 = 0;
		int j3 = 0;
		int k3 = 0;
		int l3 = 0;
		int i4 = 0;
		int j4 = 0;
		int k4 = 0;
		int l4 = 0;
		int i5 = 0;
		int j5 = 0;
		int k5 = 0;
		int l5 = 0;
		int i6 = 0;
		int i8 = 0;
		int j8 = 0;
		int k8 = 0;
		int l8 = 0;
		for (int j6 = 201; j6 <= 220; j6++) {
			L1Skills l1skills = SkillsTable.getInstance().getTemplate(j6);
			String s1 = null;
			if (Config.CLIENT_LANGUAGE == 3) {
				s1 = (new StringBuilder()).append("记忆水晶(").append(l1skills.getName()).append(")").toString();
			}
			else if (Config.CLIENT_LANGUAGE == 5) {
				s1 = (new StringBuilder()).append("记忆水晶(").append(l1skills.getName()).append(")").toString();
			}
			else {
				s1 = (new StringBuilder()).append("记忆の水晶(").append(l1skills.getName()).append("）").toString();
			}
			if (l1iteminstance.getItem().getName().equalsIgnoreCase(s1)) {
				int l6 = l1skills.getSkillLevel();
				int i7 = l1skills.getId();
				s = l1skills.getName();
				i = l1skills.getSkillId();
				switch (l6) {
					case 1: // '\001'
						j = i7;
						break;

					case 2: // '\002'
						k = i7;
						break;

					case 3: // '\003'
						l = i7;
						break;

					case 4: // '\004'
						i1 = i7;
						break;

					case 5: // '\005'
						j1 = i7;
						break;

					case 6: // '\006'
						k1 = i7;
						break;

					case 7: // '\007'
						l1 = i7;
						break;

					case 8: // '\b'
						i2 = i7;
						break;

					case 9: // '\t'
						j2 = i7;
						break;

					case 10: // '\n'
						k2 = i7;
						break;

					case 11: // '\013'
						l2 = i7;
						break;

					case 12: // '\f'
						i3 = i7;
						break;

					case 13: // '\r'
						j3 = i7;
						break;

					case 14: // '\016'
						k3 = i7;
						break;

					case 15: // '\017'
						l3 = i7;
						break;

					case 16: // '\020'
						i4 = i7;
						break;

					case 17: // '\021'
						j4 = i7;
						break;

					case 18: // '\022'
						k4 = i7;
						break;

					case 19: // '\023'
						l4 = i7;
						break;

					case 20: // '\024'
						i5 = i7;
						break;

					case 21: // '\025'
						j5 = i7;
						break;

					case 22: // '\026'
						k5 = i7;
						break;

					case 23: // '\027'
						l5 = i7;
						break;

					case 24: // '\030'
						i6 = i7;
						break;

					case 25: // '\031'
						j8 = i7;
						break;

					case 26: // '\032'
						k8 = i7;
						break;

					case 27: // '\033'
						l8 = i7;
						break;
					case 28: // '\034'
						i8 = i7;
						break;
				}
			}
		}

		int k6 = pc.getId();
		pc.sendPackets(new S_AddSkill(j, k, l, i1, j1, k1, l1, i2, j2, k2, l2, i3, j3, k3, l3, i4, j4, k4, l4, i5, j5, k5, l5, i6, j8, k8, l8, i8));
		S_SkillSound s_skillSound = new S_SkillSound(k6, 224);
		pc.sendPackets(s_skillSound);
		pc.broadcastPacket(s_skillSound);
		SkillsTable.getInstance().spellMastery(k6, i, s, 0, 0);
		pc.getInventory().removeItem(l1iteminstance, 1);
	}

	private int doWandAction(L1PcInstance user, L1Object target) {
		if (user.getId() == target.getId()) {
			return 0; // 目标为自身
		}
		if (user.glanceCheck(target.getX(), target.getY()) == false) {
			return 0; // 有障碍物
		}

		// XXX 适当なダメージ计算、要修正
		int dmg = (Random.nextInt(11) - 5) + user.getStr();
		dmg = Math.max(1, dmg);

		if (target instanceof L1PcInstance) {
			L1PcInstance pc = (L1PcInstance) target;
			if (pc.getMap().isSafetyZone(pc.getLocation()) || user.checkNonPvP(user, pc)) {
				return 0;
			}
			if ((pc.hasSkillEffect(50) == true) || (pc.hasSkillEffect(78) == true) || (pc.hasSkillEffect(157) == true)) {
				return 0;
			}

			int newHp = pc.getCurrentHp() - dmg;
			if (newHp > 0) {
				pc.setCurrentHp(newHp);
			}
			else if ((newHp <= 0) && pc.isGm()) {
				pc.setCurrentHp(pc.getMaxHp());
			}
			else if ((newHp <= 0) && !pc.isGm()) {
				pc.death(user);
			}
			return dmg;
		}
		else if (target instanceof L1MonsterInstance) {
			L1MonsterInstance mob = (L1MonsterInstance) target;
			mob.receiveDamage(user, dmg);
			return dmg;
		}
		return 0;
	}

	// 变身
	private void polyAction(L1PcInstance attacker, L1Character cha) {
		boolean isSameClan = false;
		if (cha instanceof L1PcInstance) {
			L1PcInstance pc = (L1PcInstance) cha;
			if ((pc.getClanid() != 0) && (attacker.getClanid() == pc.getClanid())) { // 目标为盟友
				isSameClan = true;
			}
		}
		if ((attacker.getId() != cha.getId()) && !isSameClan) { // 非自身及盟友
			int probability = 3 * (attacker.getLevel() - cha.getLevel()) + 100 - cha.getMr();
			int rnd = Random.nextInt(100) + 1;
			if (rnd > probability) {
				attacker.sendPackets(new S_ServerMessage(79));
				return;
			}
		}

		int[] polyArray = {
				29, 945, 947, 979, 1037, 1039, 3860, 3861, 3862, 3863, 3864,
				3865, 3904, 3906, 95, 146, 2374, 2376, 2377, 2378, 3866, 3867,
				3868, 3869, 3870, 3871, 3872, 3873, 3874, 3875, 3876
		};

		int pid = Random.nextInt(polyArray.length);
		int polyId = polyArray[pid];

		if (cha instanceof L1PcInstance) {
			L1PcInstance pc = (L1PcInstance) cha;
			int awakeSkillId = pc.getAwakeSkillId();
			if ((awakeSkillId == AWAKEN_ANTHARAS) || (awakeSkillId == AWAKEN_FAFURION) || (awakeSkillId == AWAKEN_VALAKAS)) {
				if (attacker.getId() == pc.getId()) {
					attacker.sendPackets(new S_ServerMessage(1384)); // 目前状态中无法变身。
				} else {
					attacker.sendPackets(new S_ServerMessage(79));
				}
				return;
			}

			if (pc.getInventory().checkEquipped(20281)) { // 装备变形控制戒指
				pc.sendPackets(new S_ShowPolyList(pc.getId()));
				if (!pc.isShapeChange()) {
					pc.setShapeChange(true);
				}
			}
			else {
				if (attacker.getId() != pc.getId()) {
					pc.sendPackets(new S_ServerMessage(241, attacker.getName())); // %0%s 把你变身。
				}
				L1Skills skillTemp = SkillsTable.getInstance().getTemplate(SHAPE_CHANGE);
				L1PolyMorph.doPoly(pc, polyId, skillTemp.getBuffDuration(), L1PolyMorph.MORPH_BY_ITEMMAGIC, false);
			}
		}
		else if (cha instanceof L1MonsterInstance) {
			L1MonsterInstance mob = (L1MonsterInstance) cha;
			if (mob.getLevel() < 50) {
				int npcId = mob.getNpcTemplate().get_npcId();
				if ((npcId != 45338) && (npcId != 45370) && (npcId != 45456 // 巨大鳄鱼、强盗头目、魔法师
						) && (npcId != 45464) && (npcId != 45473) && (npcId != 45488 // 西玛、巴土瑟、卡士伯
						) && (npcId != 45497) && (npcId != 45516) && (npcId != 45529 // 马库尔、伊弗利特、飞龙 (DV)
						) && (npcId != 45458)) { // 德雷克 (船长)
					L1Skills skillTemp = SkillsTable.getInstance().getTemplate(SHAPE_CHANGE);
					L1PolyMorph.doPoly(mob, polyId, skillTemp.getBuffDuration(), L1PolyMorph.MORPH_BY_ITEMMAGIC);
				}
			}
		}
	}

	private void cancelAbsoluteBarrier(L1PcInstance pc) { // 解除绝对屏障状态
		if (pc.hasSkillEffect(ABSOLUTE_BARRIER)) {
			pc.killSkillEffectTimer(ABSOLUTE_BARRIER);
			pc.startHpRegeneration();
			pc.startMpRegeneration();
			pc.startMpRegenerationByDoll();
		}
	}
	private boolean createNewItem(L1PcInstance pc, int item_id, int count) {
		L1ItemInstance item = ItemTable.getInstance().createItem(item_id);
		if (item != null) {
			item.setCount(count);
			if (pc.getInventory().checkAddItem(item, count) == L1Inventory.OK) {
				pc.getInventory().storeItem(item);
			}
			else { // 持てない场合は地面に落とす 处理のキャンセルはしない（不正防止）
				L1World.getInstance().getInventory(pc.getX(), pc.getY(), pc.getMapId()).storeItem(item);
			}
			pc.sendPackets(new S_ServerMessage(403, item.getLogName())); // 获得%0%o 。
			return true;
		}
		else {
			return false;
		}
	}

	// 傲慢之塔传送符
	private void useToiTeleportAmulet(L1PcInstance pc, int itemId, L1ItemInstance item) {
		boolean isTeleport = false;
		if ((itemId == 40289) || (itemId == 40293)) { // 傲慢之塔传送符(11F),傲慢之塔传送符(51F)
			if ((pc.getX() >= 32816) && (pc.getX() <= 32821) && (pc.getY() >= 32778) && (pc.getY() <= 32783) && (pc.getMapId() == 101)) {
				isTeleport = true;
			}
		}
		else if ((itemId == 40290) || (itemId == 40294)) { // 傲慢之塔传送符(21F),傲慢之塔传送符(61F)
			if ((pc.getX() >= 32815) && (pc.getX() <= 32820) && (pc.getY() >= 32815) && (pc.getY() <= 32820) && (pc.getMapId() == 101)) {
				isTeleport = true;
			}
		}
		else if ((itemId == 40291) || (itemId == 40295)) { // 傲慢之塔传送符(31F),傲慢之塔传送符(71F)
			if ((pc.getX() >= 32779) && (pc.getX() <= 32784) && (pc.getY() >= 32778) && (pc.getY() <= 32783) && (pc.getMapId() == 101)) {
				isTeleport = true;
			}
		}
		else if ((itemId == 40292) || (itemId == 40296)) { // 傲慢之塔传送符(41F),傲慢之塔传送符(81F)
			if ((pc.getX() >= 32779) && (pc.getX() <= 32784) && (pc.getY() >= 32815) && (pc.getY() <= 32820) && (pc.getMapId() == 101)) {
				isTeleport = true;
			}
		}
		else if (itemId == 40297) { // 傲慢之塔传送符(91F)
			if ((pc.getX() >= 32706) && (pc.getX() <= 32710) && (pc.getY() >= 32909) && (pc.getY() <= 32913) && (pc.getMapId() == 190)) {
				isTeleport = true;
			}
		}

		if (isTeleport) {
			L1Teleport.teleport(pc, item.getItem().get_locx(), item.getItem().get_locy(), item.getItem().get_mapid(), 5, true);
		}
		else {
			pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
		}
	}

	// 信纸
	private boolean writeLetter(int itemId, L1PcInstance pc, int letterCode, String letterReceiver, byte[] letterText) {

		int newItemId = 0;
		if (itemId == 40310) { // 信纸
			newItemId = 49016; // 信纸
		}
		else if (itemId == 40730) { // 圣诞卡片
			newItemId = 49020; // 圣诞卡片
		}
		else if (itemId == 40731) { // 情人节 卡片
			newItemId = 49022; // 情人节 卡片
		}
		else if (itemId == 40732) { // 白色情人节 卡片
			newItemId = 49024; // 白色情人节 卡片
		}
		L1ItemInstance item = ItemTable.getInstance().createItem(newItemId);
		if (item == null) {
			return false;
		}
		item.setCount(1);

		if (sendLetter(pc, letterReceiver, item, true)) {
			saveLetter(item.getId(), letterCode, pc.getName(), letterReceiver, letterText);
		}
		else {
			return false;
		}
		return true;
	}

	// 信件 (写信)
	private boolean writeClanLetter(int itemId, L1PcInstance pc, int letterCode, String letterReceiver, byte[] letterText) {
		L1Clan targetClan = null;
		for (L1Clan clan : L1World.getInstance().getAllClans()) {
			if (clan.getClanName().toLowerCase().equals(letterReceiver.toLowerCase())) {
				targetClan = clan;
				break;
			}
		}
		if (targetClan == null) {
			pc.sendPackets(new S_ServerMessage(434)); // 没有收信者。
			return false;
		}

		String memberName[] = targetClan.getAllMembers();
		for (String element : memberName) {
			L1ItemInstance item = ItemTable.getInstance().createItem(49016);
			if (item == null) {
				return false;
			}
			item.setCount(1);
			if (sendLetter(pc, element, item, false)) {
				saveLetter(item.getId(), letterCode, pc.getName(), element, letterText);
			}
		}
		return true;
	}

	// 信件 (收信)
	private boolean sendLetter(L1PcInstance pc, String name, L1ItemInstance item, boolean isFailureMessage) {
		L1PcInstance target = L1World.getInstance().getPlayer(name);
		if (target != null) {
			if (target.getInventory().checkAddItem(item, 1) == L1Inventory.OK) {
				target.getInventory().storeItem(item);
				target.sendPackets(new S_SkillSound(target.getId(), 1091));
				target.sendPackets(new S_ServerMessage(428)); // 您收到鸽子信差给你的信件。
			}
			else {
				if (isFailureMessage) {
					// 对方的负重太重，无法再给予。
					pc.sendPackets(new S_ServerMessage(942));
				}
				return false;
			}
		}
		else {
			if (CharacterTable.doesCharNameExist(name)) {
				try {
					int targetId = CharacterTable.getInstance().restoreCharacter(name).getId();
					CharactersItemStorage storage = CharactersItemStorage.create();
					if (storage.getItemCount(targetId) < 180) {
						storage.storeItem(targetId, item);
					}
					else {
						if (isFailureMessage) {
							// 对方的负重太重，无法再给予。
							pc.sendPackets(new S_ServerMessage(942));
						}
						return false;
					}
				}
				catch (Exception e) {
					_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
				}
			}
			else {
				if (isFailureMessage) {
					pc.sendPackets(new S_ServerMessage(109, name)); // 没有叫%0的人。
				}
				return false;
			}
		}
		return true;
	}

	private void saveLetter(int itemObjectId, int code, String sender, String receiver, byte[] text) {
		// 日付を取得する
		SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
		TimeZone tz = TimeZone.getTimeZone(Config.TIME_ZONE);
		String date = sdf.format(Calendar.getInstance(tz).getTime());

		// subjectとcontentの区切り(0x00 0x00)位置を见つける
		int spacePosition1 = 0;
		int spacePosition2 = 0;
		for (int i = 0; i < text.length; i += 2) {
			if ((text[i] == 0) && (text[i + 1] == 0)) {
				if (spacePosition1 == 0) {
					spacePosition1 = i;
				}
				else if ((spacePosition1 != 0) && (spacePosition2 == 0)) {
					spacePosition2 = i;
					break;
				}
			}
		}

		// letterテーブルに书き迂む
		int subjectLength = spacePosition1 + 2;
		int contentLength = spacePosition2 - spacePosition1;
		if (contentLength <= 0) {
			contentLength = 1;
		}
		byte[] subject = new byte[subjectLength];
		byte[] content = new byte[contentLength];
		System.arraycopy(text, 0, subject, 0, subjectLength);
		System.arraycopy(text, subjectLength, content, 0, contentLength);
		LetterTable.getInstance().writeLetter(itemObjectId, code, sender, receiver, date, 0, subject, content);
	}

	private boolean withdrawPet(L1PcInstance pc, int itemObjectId) {
		if (!pc.getMap().isTakePets()) {
			pc.sendPackets(new S_ServerMessage(563)); // \f1你无法在这个地方使用。
			return false;
		}

		int petCost = 0;
		for (L1NpcInstance petNpc : pc.getPetList().values()) {
			if (petNpc instanceof L1PetInstance) {
				if (((L1PetInstance) petNpc).getItemObjId() == itemObjectId) { // 既に引き出しているペット 
					return false;
				}
			}
			petCost += petNpc.getPetcost();
		}
		int charisma = pc.getCha();
		if (pc.isCrown()) { // 王族
			charisma += 6;
		}
		else if (pc.isElf()) { // 妖精
			charisma += 12;
		}
		else if (pc.isWizard()) { // 法师
			charisma += 6;
		}
		else if (pc.isDarkelf()) { // 黑暗妖精
			charisma += 6;
		}
		else if (pc.isDragonKnight()) { // 龙骑士
			charisma += 6;
		}
		else if (pc.isIllusionist()) { // 幻术师
			charisma += 6;
		}
		charisma -= petCost;
		int petCount = charisma / 6;
		if (petCount <= 0) {
			pc.sendPackets(new S_ServerMessage(489)); // 你无法一次控制那么多宠物。
			return false;
		}

		L1Pet l1pet = PetTable.getInstance().getTemplate(itemObjectId);
		if (l1pet != null) {
			L1Npc npcTemp = NpcTable.getInstance().getTemplate(l1pet.get_npcid());
			L1PetInstance pet = new L1PetInstance(npcTemp, pc, l1pet);
			pet.setPetcost(6);
		}
		return true;
	}

	// 钓鱼
	private void startFishing(L1PcInstance pc, int itemId, int fishX, int fishY) {
		if ((pc.getMapId() != 5300) && (pc.getMapId() != 5301)) {
			// 无法在这个地区使用钓竿。
			pc.sendPackets(new S_ServerMessage(1138));
			return;
		}
		if (pc.getTempCharGfx() != pc.getClassId()) {
			// 这里不可以变身。
			pc.sendPackets(new S_ServerMessage(1170));
			return;
		}

		int rodLength = 6;

		if (pc.getMap().isFishingZone(fishX, fishY)) {
			if (pc.getMap().isFishingZone(fishX + 1, fishY) && pc.getMap().isFishingZone(fishX - 1, fishY)
					&& pc.getMap().isFishingZone(fishX, fishY + 1) && pc.getMap().isFishingZone(fishX, fishY - 1)) {
				if ((fishX > pc.getX() + rodLength) || (fishX < pc.getX() - rodLength)) {
					pc.sendPackets(new S_ServerMessage(1138));
				}
				else if ((fishY > pc.getY() + rodLength) || (fishY < pc.getY() - rodLength)) {
					pc.sendPackets(new S_ServerMessage(1138));
				}
				else if (pc.getInventory().consumeItem(47103, 1)) { // 新鲜的饵
					pc.setFishX(fishX);
					pc.setFishY(fishY);
					pc.sendPackets(new S_Fishing(pc.getId(), ActionCodes.ACTION_Fishing, fishX, fishY));
					pc.broadcastPacket(new S_Fishing(pc.getId(), ActionCodes.ACTION_Fishing, fishX, fishY));
					pc.setFishing(true);
					long time = System.currentTimeMillis() + 10000 + Random.nextInt(5) * 1000;
					pc.setFishingTime(time);
					FishingTimeController.getInstance().addMember(pc);
				}
				else {
					// 钓鱼需要有饵。
					pc.sendPackets(new S_ServerMessage(1137));
				}
			}
			else {
				pc.sendPackets(new S_ServerMessage(1138)); // 无法在这个地区使用钓竿。
			}
		}
		else {
			pc.sendPackets(new S_ServerMessage(1138)); // 无法在这个地区使用钓竿。
		}
	}

	// 溶解
	private void useResolvent(L1PcInstance pc, L1ItemInstance item, L1ItemInstance resolvent) {
		if ((item == null) || (resolvent == null)) {
			pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
			return;
		}
		if ((item.getItem().getType2() == 1) || (item.getItem().getType2() == 2)) { // 武器・防具
			if (item.getEnchantLevel() != 0) { // 强化济み
				pc.sendPackets(new S_ServerMessage(1161)); // 无法溶解。
				return;
			}
			if (item.isEquipped()) { // 装备中
				pc.sendPackets(new S_ServerMessage(1161)); // 无法溶解。
				return;
			}
		}
		int crystalCount = ResolventTable.getInstance().getCrystalCount(item.getItem().getItemId());
		if (crystalCount == 0) {
			pc.sendPackets(new S_ServerMessage(1161)); // 无法溶解。
			return;
		}

		int rnd = Random.nextInt(100) + 1;
		if ((rnd >= 1) && (rnd <= 50)) {
			crystalCount = 0;
			pc.sendPackets(new S_ServerMessage(158, item.getName())); // \f1%0%s 消失。
		}
		else if ((rnd >= 51) && (rnd <= 90)) {
			crystalCount *= 1;
		}
		else if ((rnd >= 91) && (rnd <= 100)) {
			crystalCount *= 1.5;
			pc.getInventory().storeItem(41246, (int) (crystalCount * 1.5));
		}
		if (crystalCount != 0) {
			L1ItemInstance crystal = ItemTable.getInstance().createItem(41246);
			crystal.setCount(crystalCount);
			if (pc.getInventory().checkAddItem(crystal, 1) == L1Inventory.OK) {
				pc.getInventory().storeItem(crystal);
				pc.sendPackets(new S_ServerMessage(403, crystal.getLogName())); // 获得%0%o 。
			}
			else { // 持てない场合は地面に落とす 处理のキャンセルはしない（不正防止）
				L1World.getInstance().getInventory(pc.getX(), pc.getY(), pc.getMapId()).storeItem(crystal);
			}
		}
		pc.getInventory().removeItem(item, 1);
		pc.getInventory().removeItem(resolvent, 1);
	}

	// 料理
	private void makeCooking(L1PcInstance pc, int cookNo) {
		boolean isNearFire = false;
		for (L1Object obj : L1World.getInstance().getVisibleObjects(pc, 3)) {
			if (obj instanceof L1EffectInstance) {
				L1EffectInstance effect = (L1EffectInstance) obj;
				if (effect.getGfxId() == 5943) {
					isNearFire = true;
					break;
				}
			}
		}
		if (!isNearFire) {
			pc.sendPackets(new S_ServerMessage(1160)); // 为了料理所以需要柴火。
			return;
		}
		if (pc.getMaxWeight() <= pc.getInventory().getWeight()) {
			pc.sendPackets(new S_ServerMessage(1103)); // 身上无法再携带烹饪的物品
			return;
		}
		if (pc.hasSkillEffect(COOKING_NOW)) {
			return;
		}
		pc.setSkillEffect(COOKING_NOW, 3 * 1000); // 料理制作中

		int chance = Random.nextInt(100) + 1;

		if (cookNo == 0) { // 漂浮之眼肉排
			if (pc.getInventory().checkItem(40057, 1)) {
				pc.getInventory().consumeItem(40057, 1);
				if ((chance >= 1) && (chance <= 90)) {
					createNewItem(pc, 41277, 1);
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
				}
				else if ((chance >= 91) && (chance <= 95)) {
					createNewItem(pc, 41285, 1);
					pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
				}
				else if ((chance >= 96) && (chance <= 100)) {
					pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
				}
			}
			else {
				pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
			}
		}
		else if (cookNo == 1) { // 烤熊肉
			if (pc.getInventory().checkItem(41275, 1)) {
				pc.getInventory().consumeItem(41275, 1);
				if ((chance >= 1) && (chance <= 90)) {
					createNewItem(pc, 41278, 1);
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
				}
				else if ((chance >= 91) && (chance <= 95)) {
					createNewItem(pc, 41286, 1);
					pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
				}
				else if ((chance >= 96) && (chance <= 100)) {
					pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
				}
			}
			else {
				pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
			}
		}
		else if (cookNo == 2) { // 煎饼
			if (pc.getInventory().checkItem(41263, 1) && pc.getInventory().checkItem(41265, 1)) {
				pc.getInventory().consumeItem(41263, 1);
				pc.getInventory().consumeItem(41265, 1);
				if ((chance >= 1) && (chance <= 90)) {
					createNewItem(pc, 41279, 1);
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
				}
				else if ((chance >= 91) && (chance <= 95)) {
					createNewItem(pc, 41287, 1);
					pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
				}
				else if ((chance >= 96) && (chance <= 100)) {
					pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
				}
			}
			else {
				pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
			}
		}
		else if (cookNo == 3) { // 烤蚂蚁腿起司
			if (pc.getInventory().checkItem(41274, 1) && pc.getInventory().checkItem(41267, 1)) {
				pc.getInventory().consumeItem(41274, 1);
				pc.getInventory().consumeItem(41267, 1);
				if ((chance >= 1) && (chance <= 90)) {
					createNewItem(pc, 41280, 1);
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
				}
				else if ((chance >= 91) && (chance <= 95)) {
					createNewItem(pc, 41288, 1);
					pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
				}
				else if ((chance >= 96) && (chance <= 100)) {
					pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
				}
			}
			else {
				pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
			}
		}
		else if (cookNo == 4) { // 水果沙拉
			if (pc.getInventory().checkItem(40062, 1) && pc.getInventory().checkItem(40069, 1) && pc.getInventory().checkItem(40064, 1)) {
				pc.getInventory().consumeItem(40062, 1);
				pc.getInventory().consumeItem(40069, 1);
				pc.getInventory().consumeItem(40064, 1);
				if ((chance >= 1) && (chance <= 90)) {
					createNewItem(pc, 41281, 1);
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
				}
				else if ((chance >= 91) && (chance <= 95)) {
					createNewItem(pc, 41289, 1);
					pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
				}
				else if ((chance >= 96) && (chance <= 100)) {
					pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
				}
			}
			else {
				pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
			}
		}
		else if (cookNo == 5) { // 水果糖醋肉
			if (pc.getInventory().checkItem(40056, 1) && pc.getInventory().checkItem(40060, 1) && pc.getInventory().checkItem(40061, 1)) {
				pc.getInventory().consumeItem(40056, 1);
				pc.getInventory().consumeItem(40060, 1);
				pc.getInventory().consumeItem(40061, 1);
				if ((chance >= 1) && (chance <= 90)) {
					createNewItem(pc, 41282, 1);
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
				}
				else if ((chance >= 91) && (chance <= 95)) {
					createNewItem(pc, 41290, 1);
					pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
				}
				else if ((chance >= 96) && (chance <= 100)) {
					pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
				}
			}
			else {
				pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
			}
		}
		else if (cookNo == 6) { // 烤山猪肉串
			if (pc.getInventory().checkItem(41276, 1)) {
				pc.getInventory().consumeItem(41276, 1);
				if ((chance >= 1) && (chance <= 90)) {
					createNewItem(pc, 41283, 1);
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
				}
				else if ((chance >= 91) && (chance <= 95)) {
					createNewItem(pc, 41291, 1);
					pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
				}
				else if ((chance >= 96) && (chance <= 100)) {
					pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
				}
			}
			else {
				pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
			}
		}
		else if (cookNo == 7) { // 蘑菇汤
			if (pc.getInventory().checkItem(40499, 1) && pc.getInventory().checkItem(40060, 1)) {
				pc.getInventory().consumeItem(40499, 1);
				pc.getInventory().consumeItem(40060, 1);
				if ((chance >= 1) && (chance <= 90)) {
					createNewItem(pc, 41284, 1);
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
				}
				else if ((chance >= 91) && (chance <= 95)) {
					createNewItem(pc, 41292, 1);
					pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
				}
				else if ((chance >= 96) && (chance <= 100)) {
					pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
				}
			}
			else {
				pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
			}
		}
		else if (cookNo == 8) { // 鱼子酱
			if (pc.getInventory().checkItem(49040, 1) && pc.getInventory().checkItem(49048, 1)) {
				pc.getInventory().consumeItem(49040, 1);
				pc.getInventory().consumeItem(49048, 1);
				if ((chance >= 1) && (chance <= 90)) {
					createNewItem(pc, 49049, 1);
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
				}
				else if ((chance >= 91) && (chance <= 95)) {
					createNewItem(pc, 49057, 1);
					pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
				}
				else if ((chance >= 96) && (chance <= 100)) {
					pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
				}
			}
			else {
				pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
			}
		}
		else if (cookNo == 9) { // 鳄鱼肉排
			if (pc.getInventory().checkItem(49041, 1) && pc.getInventory().checkItem(49048, 1)) {
				pc.getInventory().consumeItem(49041, 1);
				pc.getInventory().consumeItem(49048, 1);
				if ((chance >= 1) && (chance <= 90)) {
					createNewItem(pc, 49050, 1);
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
				}
				else if ((chance >= 91) && (chance <= 95)) {
					createNewItem(pc, 49058, 1);
					pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
				}
				else if ((chance >= 96) && (chance <= 100)) {
					pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
				}
			}
			else {
				pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
			}
		}
		else if (cookNo == 10) { // 龙龟蛋饼干
			if (pc.getInventory().checkItem(49042, 1) && pc.getInventory().checkItem(41265, 1) && pc.getInventory().checkItem(49048, 1)) {
				pc.getInventory().consumeItem(49042, 1);
				pc.getInventory().consumeItem(41265, 1);
				pc.getInventory().consumeItem(49048, 1);
				if ((chance >= 1) && (chance <= 90)) {
					createNewItem(pc, 49051, 1);
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
				}
				else if ((chance >= 91) && (chance <= 95)) {
					createNewItem(pc, 49059, 1);
					pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
				}
				else if ((chance >= 96) && (chance <= 100)) {
					pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
				}
			}
			else {
				pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
			}
		}
		else if (cookNo == 11) { // 烤奇异鹦鹉
			if (pc.getInventory().checkItem(49043, 1) && pc.getInventory().checkItem(49048, 1)) {
				pc.getInventory().consumeItem(49043, 1);
				pc.getInventory().consumeItem(49048, 1);
				if ((chance >= 1) && (chance <= 90)) {
					createNewItem(pc, 49052, 1);
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
				}
				else if ((chance >= 91) && (chance <= 95)) {
					createNewItem(pc, 49060, 1);
					pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
				}
				else if ((chance >= 96) && (chance <= 100)) {
					pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
				}
			}
			else {
				pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
			}
		}
		else if (cookNo == 12) { // 毒蝎串烧
			if (pc.getInventory().checkItem(49044, 1) && pc.getInventory().checkItem(49048, 1)) {
				pc.getInventory().consumeItem(49044, 1);
				pc.getInventory().consumeItem(49048, 1);
				if ((chance >= 1) && (chance <= 90)) {
					createNewItem(pc, 49053, 1);
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
				}
				else if ((chance >= 91) && (chance <= 95)) {
					createNewItem(pc, 49061, 1);
					pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
				}
				else if ((chance >= 96) && (chance <= 100)) {
					pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
				}
			}
			else {
				pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
			}
		}
		else if (cookNo == 13) { // 炖伊莱克顿
			if (pc.getInventory().checkItem(49045, 1) && pc.getInventory().checkItem(49048, 1)) {
				pc.getInventory().consumeItem(49045, 1);
				pc.getInventory().consumeItem(49048, 1);
				if ((chance >= 1) && (chance <= 90)) {
					createNewItem(pc, 49054, 1);
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
				}
				else if ((chance >= 91) && (chance <= 95)) {
					createNewItem(pc, 49062, 1);
					pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
				}
				else if ((chance >= 96) && (chance <= 100)) {
					pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
				}
			}
			else {
				pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
			}
		}
		else if (cookNo == 14) { // 蜘蛛腿串烧
			if (pc.getInventory().checkItem(49046, 1) && pc.getInventory().checkItem(49048, 1)) {
				pc.getInventory().consumeItem(49046, 1);
				pc.getInventory().consumeItem(49048, 1);
				if ((chance >= 1) && (chance <= 90)) {
					createNewItem(pc, 49055, 1);
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
				}
				else if ((chance >= 91) && (chance <= 95)) {
					createNewItem(pc, 49063, 1);
					pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
				}
				else if ((chance >= 96) && (chance <= 100)) {
					pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
				}
			}
			else {
				pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
			}
		}
		else if (cookNo == 15) { // 蟹肉汤
			if (pc.getInventory().checkItem(49047, 1) && pc.getInventory().checkItem(40499, 1) && pc.getInventory().checkItem(49048, 1)) {
				pc.getInventory().consumeItem(49047, 1); // 蟹肉
				pc.getInventory().consumeItem(40499, 1); // 蘑菇汁
				pc.getInventory().consumeItem(49048, 1); // 综合烤肉酱
				if ((chance >= 1) && (chance <= 90)) {
					createNewItem(pc, 49056, 1);
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
				}
				else if ((chance >= 91) && (chance <= 95)) {
					createNewItem(pc, 49064, 1);
					pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
				}
				else if ((chance >= 96) && (chance <= 100)) {
					pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
				}
			}
			else {
				pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
			}
		}
		else if (cookNo == 16) { // 烤奎斯坦修的螯
			if (pc.getInventory().checkItem(49048, 1) && pc.getInventory().checkItem(49243, 1) && pc.getInventory().checkItem(49260, 1)) {
				pc.getInventory().consumeItem(49048, 1);
				pc.getInventory().consumeItem(49243, 1);
				pc.getInventory().consumeItem(49260, 1);
				if ((chance >= 1) && (chance <= 90)) {
					createNewItem(pc, 49244, 1);
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
				}
				else if ((chance >= 91) && (chance <= 95)) {
					createNewItem(pc, 49252, 1);
					pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
				}
				else if ((chance >= 96) && (chance <= 100)) {
					pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
				}
			}
			else {
				pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
			}
		}
		else if (cookNo == 17) { // 烤格利芬肉
			if (pc.getInventory().checkItem(49048, 1) && pc.getInventory().checkItem(49243, 1) && pc.getInventory().checkItem(49261, 1)) {
				pc.getInventory().consumeItem(49048, 1);
				pc.getInventory().consumeItem(49243, 1);
				pc.getInventory().consumeItem(49261, 1);
				if ((chance >= 1) && (chance <= 90)) {
					createNewItem(pc, 49245, 1);
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
				}
				else if ((chance >= 91) && (chance <= 95)) {
					createNewItem(pc, 49253, 1);
					pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
				}
				else if ((chance >= 96) && (chance <= 100)) {
					pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
				}
			}
			else {
				pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
			}
		}
		else if (cookNo == 18) { // 亚力安的尾巴肉排
			if (pc.getInventory().checkItem(49048, 1) && pc.getInventory().checkItem(49243, 1) && pc.getInventory().checkItem(49262, 1)) {
				pc.getInventory().consumeItem(49048, 1);
				pc.getInventory().consumeItem(49243, 1);
				pc.getInventory().consumeItem(49262, 1);
				if ((chance >= 1) && (chance <= 90)) {
					createNewItem(pc, 49246, 1);
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
				}
				else if ((chance >= 91) && (chance <= 95)) {
					createNewItem(pc, 49254, 1);
					pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
				}
				else if ((chance >= 96) && (chance <= 100)) {
					pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
				}
			}
			else {
				pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
			}
		}
		else if (cookNo == 19) { // 烤巨王龟肉
			if (pc.getInventory().checkItem(49048, 1) && pc.getInventory().checkItem(49243, 1) && pc.getInventory().checkItem(49263, 1)) {
				pc.getInventory().consumeItem(49048, 1);
				pc.getInventory().consumeItem(49243, 1);
				pc.getInventory().consumeItem(49263, 1);
				if ((chance >= 1) && (chance <= 90)) {
					createNewItem(pc, 49247, 1);
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
				}
				else if ((chance >= 91) && (chance <= 95)) {
					createNewItem(pc, 49255, 1);
					pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
				}
				else if ((chance >= 96) && (chance <= 100)) {
					pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
				}
			}
			else {
				pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
			}
		}
		else if (cookNo == 20) { // 幼龙翅膀串烧
			if (pc.getInventory().checkItem(49048, 1) && pc.getInventory().checkItem(49243, 1) && pc.getInventory().checkItem(49264, 1)) {
				pc.getInventory().consumeItem(49048, 1);
				pc.getInventory().consumeItem(49243, 1);
				pc.getInventory().consumeItem(49264, 1);
				if ((chance >= 1) && (chance <= 90)) {
					createNewItem(pc, 49248, 1);
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
				}
				else if ((chance >= 91) && (chance <= 95)) {
					createNewItem(pc, 49256, 1);
					pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
				}
				else if ((chance >= 96) && (chance <= 100)) {
					pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
				}
			}
			else {
				pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
			}
		}
		else if (cookNo == 21) { // 烤飞龙肉
			if (pc.getInventory().checkItem(49048, 1) && pc.getInventory().checkItem(49243, 1) && pc.getInventory().checkItem(49265, 1)) {
				pc.getInventory().consumeItem(49048, 1);
				pc.getInventory().consumeItem(49243, 1);
				pc.getInventory().consumeItem(49265, 1);
				if ((chance >= 1) && (chance <= 90)) {
					createNewItem(pc, 49249, 1);
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
				}
				else if ((chance >= 91) && (chance <= 95)) {
					createNewItem(pc, 49257, 1);
					pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
				}
				else if ((chance >= 96) && (chance <= 100)) {
					pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
				}
			}
			else {
				pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
			}
		}
		else if (cookNo == 22) { // 炖深海鱼肉
			if (pc.getInventory().checkItem(49048, 1) && pc.getInventory().checkItem(49243, 1) && pc.getInventory().checkItem(49266, 1)) {
				pc.getInventory().consumeItem(49048, 1);
				pc.getInventory().consumeItem(49243, 1);
				pc.getInventory().consumeItem(49266, 1);
				if ((chance >= 1) && (chance <= 90)) {
					createNewItem(pc, 49250, 1);
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
				}
				else if ((chance >= 91) && (chance <= 95)) {
					createNewItem(pc, 49258, 1);
					pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
				}
				else if ((chance >= 96) && (chance <= 100)) {
					pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
				}
			}
			else {
				pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
			}
		}
		else if (cookNo == 23) { // 邪恶蜥蜴蛋汤
			if (pc.getInventory().checkItem(49048, 1) && pc.getInventory().checkItem(49243, 1)
					&& pc.getInventory().checkItem(49267, 1) && pc.getInventory().checkItem(40499, 1)) {
				pc.getInventory().consumeItem(49048, 1); // 综合烤肉酱
				pc.getInventory().consumeItem(49243, 1); // 香菜
				pc.getInventory().consumeItem(49267, 1); // 邪恶蜥蜴蛋
				pc.getInventory().consumeItem(40499, 1); // 蘑菇汁
				if ((chance >= 1) && (chance <= 90)) {
					createNewItem(pc, 49251, 1);
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
				}
				else if ((chance >= 91) && (chance <= 95)) {
					createNewItem(pc, 49259, 1);
					pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
				}
				else if ((chance >= 96) && (chance <= 100)) {
					pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
					pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
				}
			}
			else {
				pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
			}
		}
	}

	private void useFurnitureItem(L1PcInstance pc, int itemId, int itemObjectId) {
		if (!L1HouseLocation.isInHouse(pc.getX(), pc.getY(), pc.getMapId())) {
			pc.sendPackets(new S_ServerMessage(563)); // \f1你无法在这个地方使用。
			return;
		}

		boolean isAppear = true;
		L1FurnitureInstance furniture = null;
		for (L1Object l1object : L1World.getInstance().getObject()) {
			if (l1object instanceof L1FurnitureInstance) {
				furniture = (L1FurnitureInstance) l1object;
				if (furniture.getItemObjId() == itemObjectId) { // 既に引き出している家具
					isAppear = false;
					break;
				}
			}
		}

		if (isAppear) {
			if ((pc.getHeading() != 0) && (pc.getHeading() != 2)) {
				return;
			}
			int npcId = 0;
			if (itemId == 41383) { // 巨大兵蚁标本
				npcId = 80109;
			}
			else if (itemId == 41384) { // 熊标本
				npcId = 80110;
			}
			else if (itemId == 41385) { // 蛇女标本
				npcId = 80113;
			}
			else if (itemId == 41386) { // 黑虎标本
				npcId = 80114;
			}
			else if (itemId == 41387) { // 鹿标本
				npcId = 80115;
			}
			else if (itemId == 41388) { // 哈维标本
				npcId = 80124;
			}
			else if (itemId == 41389) { // 青铜骑士
				npcId = 80118;
			}
			else if (itemId == 41390) { // 青铜马
				npcId = 80119;
			}
			else if (itemId == 41391) { // 烛台
				npcId = 80120;
			}
			else if (itemId == 41392) { // 茶几
				npcId = 80121;
			}
			else if (itemId == 41393) { // 火炉
				npcId = 80126;
			}
			else if (itemId == 41394) { // 火把
				npcId = 80125;
			}
			else if (itemId == 41395) { // 君主用讲台
				npcId = 80111;
			}
			else if (itemId == 41396) { // 旗帜
				npcId = 80112;
			}
			else if (itemId == 41397) { // 茶几椅子(右)
				npcId = 80116;
			}
			else if (itemId == 41398) { // 茶几椅子(左)
				npcId = 80117;
			}
			else if (itemId == 41399) { // 屏风(右)
				npcId = 80122;
			}
			else if (itemId == 41400) { // 屏风(左)
				npcId = 80123;
			}

			try {
				L1Npc l1npc = NpcTable.getInstance().getTemplate(npcId);
				if (l1npc != null) {
					try {
						String s = l1npc.getImpl();
						Constructor<?> constructor = Class.forName("l1j.server.server.model.Instance." + s + "Instance").getConstructors()[0];
						Object aobj[] =
						{ l1npc };
						furniture = (L1FurnitureInstance) constructor.newInstance(aobj);
						furniture.setId(IdFactory.getInstance().nextId());
						furniture.setMap(pc.getMapId());
						if (pc.getHeading() == 0) {
							furniture.setX(pc.getX());
							furniture.setY(pc.getY() - 1);
						}
						else if (pc.getHeading() == 2) {
							furniture.setX(pc.getX() + 1);
							furniture.setY(pc.getY());
						}
						furniture.setHomeX(furniture.getX());
						furniture.setHomeY(furniture.getY());
						furniture.setHeading(0);
						furniture.setItemObjId(itemObjectId);

						L1World.getInstance().storeObject(furniture);
						L1World.getInstance().addVisibleObject(furniture);
						FurnitureSpawnTable.getInstance().insertFurniture(furniture);
					}
					catch (Exception e) {
						_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
					}
				}
			}
			catch (Exception exception) {}
		}
		else {
			furniture.deleteMe();
			FurnitureSpawnTable.getInstance().deleteFurniture(furniture);
		}
	}

	// 家具移除魔杖
	private void useFurnitureRemovalWand(L1PcInstance pc, int targetId, L1ItemInstance item) {
		S_AttackPacket s_attackPacket = new S_AttackPacket(pc, 0, ActionCodes.ACTION_Wand);
		pc.sendPackets(s_attackPacket);
		pc.broadcastPacket(s_attackPacket);
		int chargeCount = item.getChargeCount();
		if (chargeCount <= 0) {
			return;
		}

		L1Object target = L1World.getInstance().findObject(targetId);
		if ((target != null) && (target instanceof L1FurnitureInstance)) {
			L1FurnitureInstance furniture = (L1FurnitureInstance) target;
			furniture.deleteMe();
			FurnitureSpawnTable.getInstance().deleteFurniture(furniture);
			item.setChargeCount(item.getChargeCount() - 1);
			pc.getInventory().updateItem(item, L1PcInventory.COL_CHARGE_COUNT);
		}
	}

	@Override
	public String getType() {
		return C_ITEM_USE;
	}
}
