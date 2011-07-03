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
package l1j.server.server.model.Instance;

import static l1j.server.server.model.identity.L1ItemId.B_POTION_OF_GREATER_HASTE_SELF;
import static l1j.server.server.model.identity.L1ItemId.B_POTION_OF_HASTE_SELF;
import static l1j.server.server.model.identity.L1ItemId.POTION_OF_EXTRA_HEALING;
import static l1j.server.server.model.identity.L1ItemId.POTION_OF_GREATER_HASTE_SELF;
import static l1j.server.server.model.identity.L1ItemId.POTION_OF_GREATER_HEALING;
import static l1j.server.server.model.identity.L1ItemId.POTION_OF_HASTE_SELF;
import static l1j.server.server.model.identity.L1ItemId.POTION_OF_HEALING;
import static l1j.server.server.model.skill.L1SkillId.CANCELLATION;
import static l1j.server.server.model.skill.L1SkillId.COUNTER_BARRIER;
import static l1j.server.server.model.skill.L1SkillId.POLLUTE_WATER;
import static l1j.server.server.model.skill.L1SkillId.STATUS_HASTE;
import static l1j.server.server.model.skill.L1SkillId.WIND_SHACKLE;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

import l1j.server.Config;
import l1j.server.server.ActionCodes;
import l1j.server.server.GeneralThreadPool;
import l1j.server.server.datatables.NpcChatTable;
import l1j.server.server.datatables.NpcTable;
import l1j.server.server.datatables.SprTable;
import l1j.server.server.model.L1Attack;
import l1j.server.server.model.L1Character;
import l1j.server.server.model.L1GroundInventory;
import l1j.server.server.model.L1HateList;
import l1j.server.server.model.L1Inventory;
import l1j.server.server.model.L1Magic;
import l1j.server.server.model.L1MobGroupInfo;
import l1j.server.server.model.L1MobSkillUse;
import l1j.server.server.model.L1NpcChatTimer;
import l1j.server.server.model.L1NpcRegenerationTimer;
import l1j.server.server.model.L1Object;
import l1j.server.server.model.L1Spawn;
import l1j.server.server.model.L1World;
import l1j.server.server.model.map.L1Map;
import l1j.server.server.model.map.L1WorldMap;
import l1j.server.server.model.npc.action.L1NpcDefaultAction;
import l1j.server.server.model.skill.L1SkillUse;
import l1j.server.server.serverpackets.S_CharVisualUpdate;
import l1j.server.server.serverpackets.S_DoActionGFX;
import l1j.server.server.serverpackets.S_MoveCharPacket;
import l1j.server.server.serverpackets.S_NPCPack;
import l1j.server.server.serverpackets.S_NpcChangeShape;
import l1j.server.server.serverpackets.S_RemoveObject;
import l1j.server.server.serverpackets.S_SkillHaste;
import l1j.server.server.serverpackets.S_SkillSound;
import l1j.server.server.templates.L1Npc;
import l1j.server.server.templates.L1NpcChat;
import l1j.server.server.types.Point;
import l1j.server.server.utils.Random;
import l1j.server.server.utils.TimerPool;
import l1j.server.server.utils.collections.Lists;
import l1j.server.server.utils.collections.Maps;

public class L1NpcInstance extends L1Character {
	private static final long serialVersionUID = 1L;

	public static final int MOVE_SPEED = 0;

	public static final int ATTACK_SPEED = 1;

	public static final int MAGIC_SPEED = 2;

	public static final int HIDDEN_STATUS_NONE = 0;

	public static final int HIDDEN_STATUS_SINK = 1;

	public static final int HIDDEN_STATUS_FLY = 2;

	public static final int HIDDEN_STATUS_ICE = 3;

	public static final int CHAT_TIMING_APPEARANCE = 0;

	public static final int CHAT_TIMING_DEAD = 1;

	public static final int CHAT_TIMING_HIDE = 2;

	public static final int CHAT_TIMING_GAME_TIME = 3;

	private static Logger _log = Logger
			.getLogger(L1NpcInstance.class.getName());

	private L1Npc _npcTemplate;

	private L1Spawn _spawn;

	private int _spawnNumber; // L1Spawnで管理されているナンバー

	private int _petcost; // ペットになったときのコスト

	public L1Inventory _inventory = new L1Inventory();

	private L1MobSkillUse mobSkill;

	// 对象を初めて発见したとき。（テレポート用）
	private boolean firstFound = true;

	// 经路探索范围（半径） ※上げすぎ注意！！
	public static int courceRange = 15;

	// 吸われたMP
	private int _drainedMana = 0;

	// 休憩
	private boolean _rest = false;

	// ランダム移动时の距离と方向
	private int _randomMoveDistance = 0;

	private int _randomMoveDirection = 0;

	// ■■■■■■■■■■■■■ ＡＩ关连 ■■■■■■■■■■■

	interface NpcAI {
		public void start();
	}

	protected void startAI() {
		if (Config.NPCAI_IMPLTYPE == 1) {
			new NpcAITimerImpl().start();
		} else if (Config.NPCAI_IMPLTYPE == 2) {
			new NpcAIThreadImpl().start();
		} else {
			new NpcAITimerImpl().start();
		}
	}

	/**
	 * マルチ(コア)プロセッサをサポートする为のタイマープール。 AIの实装タイプがタイマーの场合に使用される。
	 */
	private static final TimerPool _timerPool = new TimerPool(4);

	class NpcAITimerImpl extends TimerTask implements NpcAI {
		/**
		 * 死亡处理の终了を待つタイマー
		 */
		private class DeathSyncTimer extends TimerTask {
			private void schedule(int delay) {
				_timerPool.getTimer().schedule(new DeathSyncTimer(), delay);
			}

			@Override
			public void run() {
				if (isDeathProcessing()) {
					schedule(getSleepTime());
					return;
				}
				allTargetClear();
				setAiRunning(false);
			}
		}

		@Override
		public void start() {
			setAiRunning(true);
			_timerPool.getTimer().schedule(NpcAITimerImpl.this, 0);
		}

		private void stop() {
			mobSkill.resetAllSkillUseCount();
			_timerPool.getTimer().schedule(new DeathSyncTimer(), 0); // 死亡同期を开始
		}

		// 同じインスタンスをTimerへ登录できない为、苦肉の策。
		private void schedule(int delay) {
			_timerPool.getTimer().schedule(new NpcAITimerImpl(), delay);
		}

		@Override
		public void run() {
			try {
				if (notContinued()) {
					stop();
					return;
				}

				// XXX 同期がとても怪しげな麻痹判定
				if (0 < _paralysisTime) {
					schedule(_paralysisTime);
					_paralysisTime = 0;
					setParalyzed(false);
					return;
				} else if (isParalyzed() || isSleeped()) {
					schedule(200);
					return;
				}

				if (!AIProcess()) { // AIを续けるべきであれば、次の实行をスケジュールし、终了
					schedule(getSleepTime());
					return;
				}
				stop();
			} catch (Exception e) {
				_log.log(Level.WARNING, "NpcAI"+getNpcId()+"发生例外的错误。", e);
			}
		}

		private boolean notContinued() {
			return _destroyed || isDead() || (getCurrentHp() <= 0)
					|| (getHiddenStatus() != HIDDEN_STATUS_NONE);
		}
	}

	class NpcAIThreadImpl implements Runnable, NpcAI {
		@Override
		public void start() {
			GeneralThreadPool.getInstance().execute(NpcAIThreadImpl.this);
		}

		@Override
		public void run() {
			try {
				setAiRunning(true);
				while (!_destroyed && !isDead() && (getCurrentHp() > 0)
						&& (getHiddenStatus() == HIDDEN_STATUS_NONE)) {
					/*
					 * if (_paralysisTime > 0) { try {
					 * Thread.sleep(_paralysisTime); } catch (Exception
					 * exception) { break; } finally { setParalyzed(false);
					 * _paralysisTime = 0; } }
					 */
					while (isParalyzed() || isSleeped()) {
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							setParalyzed(false);
						}
					}

					if (AIProcess()) {
						break;
					}
					try {
						// 指定时间分スレッド停止
						Thread.sleep(getSleepTime());
					} catch (Exception e) {
						break;
					}
				}
				mobSkill.resetAllSkillUseCount();
				do {
					try {
						Thread.sleep(getSleepTime());
					} catch (Exception e) {
						break;
					}
				} while (isDeathProcessing());
				allTargetClear();
				setAiRunning(false);
			} catch (Exception e) {
				_log.log(Level.WARNING, "NpcAI"+getNpcId()+"发生例外的错误。", e);
			}
		}
	}

	// ＡＩの处理 (返り值はＡＩ处理を终了するかどうか)
	private boolean AIProcess() {
		setSleepTime(300);

		checkTarget();
		if ((_target == null) && (_master == null)) {
			// 空っぽの场合はターゲットを探してみる
			// （主人がいる场合は自分でターゲットを探さない）
			searchTarget();
		}

		onDoppel(true);
		onItemUse();

		if (_target == null) {
			// ターゲットがいない场合
			checkTargetItem();
			if (isPickupItem() && (_targetItem == null)) {
				// アイテム拾う子の场合はアイテムを探してみる
				searchTargetItem();
			}

			if (_targetItem == null) {
				if (noTarget()) {
					return true;
				}
			} else {
				// onTargetItem();
				L1Inventory groundInventory = L1World.getInstance()
						.getInventory(_targetItem.getX(), _targetItem.getY(),
								_targetItem.getMapId());
				if (groundInventory.checkItem(_targetItem.getItemId())) {
					onTargetItem();
				} else {
					_targetItemList.remove(_targetItem);
					_targetItem = null;
					setSleepTime(1000);
					return false;
				}
			}
		} else { // ターゲットがいる场合
			if (getHiddenStatus() == HIDDEN_STATUS_NONE) {
				onTarget();
			} else {
				return true;
			}
		}

		return false; // ＡＩ处理续行
	}

	// 变形怪变形
	public void onDoppel(boolean isChangeShape) {
	}

	// アイテム使用处理（Ｔｙｐｅによって结构违うのでオーバライドで实装）
	public void onItemUse() {
	}

	// ターゲットを探す（Ｔｙｐｅによって结构违うのでオーバライドで实装）
	public void searchTarget() {
		tagertClear();
	}

	// 有效なターゲットか确认及び次のターゲットを设定
	public void checkTarget() {
		if ((_target == null)
				|| (_target.getMapId() != getMapId())
				|| (_target.getCurrentHp() <= 0)
				|| _target.isDead()
				|| (_target.isInvisble() && !getNpcTemplate().is_agrocoi() && !_hateList
						.containsKey(_target))
						// 目标距离超过30以上
						|| _target.getTileLineDistance(this) > 30 ) {
			if (_target != null) {
				tagertClear();
			}
			if (!_hateList.isEmpty()) {
				_target = _hateList.getMaxHateCharacter();
				checkTarget();
			}
		}
	}

	// ヘイトの设定
	public void setHate(L1Character cha, int hate) {
		if ((cha != null) && (cha.getId() != getId())) {
			if (!isFirstAttack() && (hate != 0)) {
				// hate += 20; // ＦＡヘイト
				hate += getMaxHp() / 10; // ＦＡヘイト
				setFirstAttack(true);
			}

			_hateList.add(cha, hate);
			_dropHateList.add(cha, hate);
			_target = _hateList.getMaxHateCharacter();
			checkTarget();
		}
	}

	// リンクの设定
	public void setLink(L1Character cha) {
	}

	// 仲间意识によりアクティブになるＮＰＣの检索（攻击者がプレイヤーのみ有效）
	public void serchLink(L1PcInstance targetPlayer, int family) {
		List<L1Object> targetKnownObjects = targetPlayer.getKnownObjects();
		for (Object knownObject : targetKnownObjects) {
			if (knownObject instanceof L1NpcInstance) {
				L1NpcInstance npc = (L1NpcInstance) knownObject;
				if (npc.getNpcTemplate().get_agrofamily() > 0) {
					// 仲间に对してアクティブになる场合
					if (npc.getNpcTemplate().get_agrofamily() == 1) {
						// 同种族に对してのみ仲间意识
						if (npc.getNpcTemplate().get_family() == family) {
							npc.setLink(targetPlayer);
						}
					} else {
						// 全てのＮＰＣに对して仲间意识
						npc.setLink(targetPlayer);
					}
				}
				L1MobGroupInfo mobGroupInfo = getMobGroupInfo();
				if (mobGroupInfo != null) {
					if ((getMobGroupId() != 0)
							&& (getMobGroupId() == npc.getMobGroupId())) { // 同じグループ
						npc.setLink(targetPlayer);
					}
				}
			}
		}
	}

	// ターゲットがいる场合の处理
	public void onTarget() {
		setActived(true);
		_targetItemList.clear();
		_targetItem = null;
		L1Character target = _target; // ここから先は_targetが变わると影响出るので别领域に参照确保
		if (getAtkspeed() == 0) { // 逃げるキャラ
			if (getPassispeed() > 0) { // 移动できるキャラ
				int escapeDistance = 15;
				if (hasSkillEffect(40) == true) {
					escapeDistance = 1;
				}
				if (getLocation().getTileLineDistance(target.getLocation()) > escapeDistance) { // ターゲットから逃げるの终了
					tagertClear();
				} else { // ターゲットから逃げる
					int dir = targetReverseDirection(target.getX(),
							target.getY());
					dir = checkObject(getX(), getY(), getMapId(), dir);
					setDirectionMove(dir);
					setSleepTime(calcSleepTime(getPassispeed(), MOVE_SPEED));
				}
			}
		} else { // 逃げないキャラ
			if (isAttackPosition(target.getX(), target.getY(), getAtkRanged())) { // 攻击可能位置
				if (mobSkill.isSkillTrigger(target)) { // トリガの条件に合うスキルがある
					if (mobSkill.skillUse(target, true)) { // スキル使用(mobskill.sqlのTriRndに从う)
						setSleepTime(calcSleepTime(mobSkill.getSleepTime(),
								MAGIC_SPEED));
					} else { // スキル使用が失败したら物理攻击
						setHeading(targetDirection(target.getX(),
								target.getY()));
						attackTarget(target);
					}
				} else {
					setHeading(targetDirection(target.getX(), target.getY()));
					attackTarget(target);
				}
			} else { // 攻击不可能位置
				if (mobSkill.skillUse(target, false)) { // スキル使用(mobskill.sqlのTriRndに从わず、発动确率は100%。ただしサモン、强制变身は常にTriRndに从う。)
					setSleepTime(calcSleepTime(mobSkill.getSleepTime(),
							MAGIC_SPEED));
					return;
				}

				if (getPassispeed() > 0) {
					// 移动できるキャラ
					int distance = getLocation().getTileDistance(
							target.getLocation());
					if ((firstFound == true) && getNpcTemplate().is_teleport()
							&& (distance > 3) && (distance < 15)) {
						if (nearTeleport(target.getX(), target.getY()) == true) {
							firstFound = false;
							return;
						}
					}

					if (getNpcTemplate().is_teleport()
							&& (20 > Random.nextInt(100))
							&& (getCurrentMp() >= 10) && (distance > 6)
							&& (distance < 15)) { // テレポート移动
						if (nearTeleport(target.getX(), target.getY()) == true) {
							return;
						}
					}
					int dir = moveDirection(target.getX(), target.getY());
					if (dir == -1) {
						// 假如怪物走不过去   就找附近下一个玩家攻击
						searchTarget();
					} else {
						setDirectionMove(dir);
						setSleepTime(calcSleepTime(getPassispeed(), MOVE_SPEED));
					}
				} else {
					// 移动できないキャラ（ターゲットから排除、ＰＴのときドロップチャンスがリセットされるけどまぁ自业自得）
					tagertClear();
				}
			}
		}
	}

	// 目标を指定のスキルで攻击
	public void attackTarget(L1Character target) {
		if (target instanceof L1PcInstance) {
			L1PcInstance player = (L1PcInstance) target;
			if (player.isTeleport()) { // テレポート处理中
				return;
			}
		} else if (target instanceof L1PetInstance) {
			L1PetInstance pet = (L1PetInstance) target;
			L1Character cha = pet.getMaster();
			if (cha instanceof L1PcInstance) {
				L1PcInstance player = (L1PcInstance) cha;
				if (player.isTeleport()) { // テレポート处理中
					return;
				}
			}
		} else if (target instanceof L1SummonInstance) {
			L1SummonInstance summon = (L1SummonInstance) target;
			L1Character cha = summon.getMaster();
			if (cha instanceof L1PcInstance) {
				L1PcInstance player = (L1PcInstance) cha;
				if (player.isTeleport()) { // テレポート处理中
					return;
				}
			}
		}
		if (this instanceof L1PetInstance) {
			L1PetInstance pet = (L1PetInstance) this;
			L1Character cha = pet.getMaster();
			if (cha instanceof L1PcInstance) {
				L1PcInstance player = (L1PcInstance) cha;
				if (player.isTeleport()) { // テレポート处理中
					return;
				}
			}
		} else if (this instanceof L1SummonInstance) {
			L1SummonInstance summon = (L1SummonInstance) this;
			L1Character cha = summon.getMaster();
			if (cha instanceof L1PcInstance) {
				L1PcInstance player = (L1PcInstance) cha;
				if (player.isTeleport()) { // テレポート处理中
					return;
				}
			}
		}

		if (target instanceof L1NpcInstance) {
			L1NpcInstance npc = (L1NpcInstance) target;
			if (npc.getHiddenStatus() != HIDDEN_STATUS_NONE) { // 地中に潜っているか、飞んでいる
				allTargetClear();
				return;
			}
		}

		boolean isCounterBarrier = false;
		L1Attack attack = new L1Attack(this, target);
		if (attack.calcHit()) {
			if (target.hasSkillEffect(COUNTER_BARRIER)) {
				L1Magic magic = new L1Magic(target, this);
				boolean isProbability = magic
						.calcProbabilityMagic(COUNTER_BARRIER);
				boolean isShortDistance = attack.isShortDistance();
				if (isProbability && isShortDistance) {
					isCounterBarrier = true;
				}
			}
			if (!isCounterBarrier) {
				attack.calcDamage();
			}
		}
		if (isCounterBarrier) {
			attack.actionCounterBarrier();
			attack.commitCounterBarrier();
		} else {
			attack.action();
			attack.commit();
		}
		setSleepTime(calcSleepTime(getAtkspeed(), ATTACK_SPEED));
	}

	// ターゲットアイテムを探す
	public void searchTargetItem() {
		List<L1GroundInventory> gInventorys = Lists.newList();

		for (L1Object obj : L1World.getInstance().getVisibleObjects(this)) {
			if ((obj != null) && (obj instanceof L1GroundInventory)) {
				gInventorys.add((L1GroundInventory) obj);
			}
		}
		if (gInventorys.size() == 0) {
			return;
		}

		// 拾うアイテム(のインベントリ)をランダムで选定
		int pickupIndex = Random.nextInt(gInventorys.size());
		L1GroundInventory inventory = gInventorys.get(pickupIndex);
		for (L1ItemInstance item : inventory.getItems()) {
			if (getInventory().checkAddItem(item, item.getCount()) == L1Inventory.OK) { // 持てるならターゲットアイテムに加える
				_targetItem = item;
				_targetItemList.add(_targetItem);
			}
		}
	}

	public void searchItemFromAir() { // 怪物飞天中，发现特定道具时解除飞天捡拾道具
		List<L1GroundInventory> gInventorys = Lists.newList();

		for (L1Object obj : L1World.getInstance().getVisibleObjects(this)) {
			if ((obj != null) && (obj instanceof L1GroundInventory)) {
				gInventorys.add((L1GroundInventory) obj);
			}
		}
		if (gInventorys.isEmpty()) {
			return;
		}

		int pickupIndex = Random.nextInt(gInventorys.size());
		L1GroundInventory inventory = gInventorys.get(pickupIndex);
		for (L1ItemInstance item : inventory.getItems()) {
			if ((item.getItem().getType() == 6) // potion
					|| (item.getItem().getType() == 7)) { // food
				if (getInventory().checkAddItem(item, item.getCount()) == L1Inventory.OK) {
					if (getHiddenStatus() == HIDDEN_STATUS_FLY) {
						setHiddenStatus(HIDDEN_STATUS_NONE);
						setStatus(L1NpcDefaultAction.getInstance().getStatus(getTempCharGfx()));
						broadcastPacket(new S_DoActionGFX(getId(), ActionCodes.ACTION_Movedown));
						onNpcAI();
						startChat(CHAT_TIMING_HIDE);
						//追加不会移动的怪物不会去捡物品
						if (getPassispeed() > 0) { // 须曼 (测试)
						_targetItem = item;
						_targetItemList.add(_targetItem);
						}
						// 追加不会移动的怪物不会去捡物品
					}
				}
			}
			// 须曼特殊条件 (测试)
			if (_npcTemplate.get_npcId() == 99021) {
				int item_Id = item.getItem().getItemId();
				if ((item_Id == 40052) // 高品质钻石
						|| (item_Id == 40053) // 高品质红宝石
						|| (item_Id == 40054) // 高品质蓝宝石
						|| (item_Id == 40055) // 高品质绿宝石
						&& (item.getItem().getType() != 6 || item.getItem().getType() != 7)) {
					if (getHiddenStatus() == HIDDEN_STATUS_FLY) {
						L1Inventory groundInventory = L1World.getInstance()
						.getInventory(item.getX(), item.getY(), item.getMapId());
						setHiddenStatus(HIDDEN_STATUS_NONE);
						broadcastPacket(new S_DoActionGFX(getId(), 11));
						setStatus(0);
						broadcastPacket(new S_NPCPack(this));
						onNpcAI();
						startChat(CHAT_TIMING_HIDE);
						groundInventory.removeItem(item);
					}
				}
			}
			// 须曼特殊条件 (测试)
		}
	}

	public static void shuffle(L1Object[] arr) {
		for (int i = arr.length - 1; i > 0; i--) {
			int t = Random.nextInt(i);

			// 选ばれた值と交换する
			L1Object tmp = arr[i];
			arr[i] = arr[t];
			arr[t] = tmp;
		}
	}

	// 有效なターゲットアイテムか确认及び次のターゲットアイテムを设定
	public void checkTargetItem() {
		if ((_targetItem == null)
				|| (_targetItem.getMapId() != getMapId())
				|| (getLocation().getTileDistance(_targetItem.getLocation()) > 15)) {
			if (!_targetItemList.isEmpty()) {
				_targetItem = _targetItemList.get(0);
				_targetItemList.remove(0);
				checkTargetItem();
			} else {
				_targetItem = null;
			}
		}
	}

	// ターゲットアイテムがある场合の处理
	public void onTargetItem() {
		if (getLocation().getTileLineDistance(_targetItem.getLocation()) == 0) { // ピックアップ可能位置
			pickupTargetItem(_targetItem);
		} else { // ピックアップ不可能位置
			int dir = moveDirection(_targetItem.getX(), _targetItem.getY());
			if (dir == -1) { // 拾うの谛め
				_targetItemList.remove(_targetItem);
				_targetItem = null;
			} else { // ターゲットアイテムへ移动
				setDirectionMove(dir);
				setSleepTime(calcSleepTime(getPassispeed(), MOVE_SPEED));
			}
		}
	}

	// アイテムを拾う
	public void pickupTargetItem(L1ItemInstance targetItem) {
		L1Inventory groundInventory = L1World.getInstance().getInventory(
				targetItem.getX(), targetItem.getY(), targetItem.getMapId());
		L1ItemInstance item = groundInventory.tradeItem(targetItem,
				targetItem.getCount(), getInventory());
		turnOnOffLight();
		onGetItem(item);
		_targetItemList.remove(_targetItem);
		_targetItem = null;
		setSleepTime(1000);
	}

	// ターゲットがいない场合の处理 (返り值はＡＩ处理を终了するかどうか)
	public boolean noTarget() {
		if ((_master != null)
				&& (_master.getMapId() == getMapId())
				&& (getLocation().getTileLineDistance(_master.getLocation()) > 2)) { // 主人が同じマップにいて离れてる场合は追尾
			int dir = moveDirection(_master.getX(), _master.getY());
			if (dir != -1) {
				setDirectionMove(dir);
				setSleepTime(calcSleepTime(getPassispeed(), MOVE_SPEED));
			} else {
				return true;
			}
		} else {
			if (L1World.getInstance().getRecognizePlayer(this).isEmpty()) {
				return true; // 周りにプレイヤーがいなくなったらＡＩ处理终了
			}
			// 移动できるキャラはランダムに动いておく
			if ((_master == null) && (getPassispeed() > 0) && !isRest()) {
				// グループに属していないorグループに属していてリーダーの场合、ランダムに动いておく
				L1MobGroupInfo mobGroupInfo = getMobGroupInfo();
				if ((mobGroupInfo == null)
						|| ((mobGroupInfo != null) && mobGroupInfo
								.isLeader(this))) {
					// 移动する予定の距离を移动し终えたら、新たに距离と方向を决める
					// そうでないなら、移动する予定の距离をデクリメント
					if (_randomMoveDistance == 0) {
						_randomMoveDistance = Random.nextInt(5) + 1;
						_randomMoveDirection = Random.nextInt(20);
						// ホームポイントから离れすぎないように、一定の确率でホームポイントの方向に补正
						if ((getHomeX() != 0) && (getHomeY() != 0)
								&& (_randomMoveDirection < 8)
								&& (Random.nextInt(3) == 0)) {
							_randomMoveDirection = moveDirection(getHomeX(),
									getHomeY());
						}
					} else {
						_randomMoveDistance--;
					}
					int dir = checkObject(getX(), getY(), getMapId(),
							_randomMoveDirection);
					if (dir != -1) {
						setDirectionMove(dir);
						setSleepTime(calcSleepTime(getPassispeed(), MOVE_SPEED));
					}
				} else { // リーダーを追尾
					L1NpcInstance leader = mobGroupInfo.getLeader();
					if (getLocation().getTileLineDistance(leader.getLocation()) > 2) {
						int dir = moveDirection(leader.getX(), leader.getY());
						if (dir == -1) {
							return true;
						} else {
							setDirectionMove(dir);
							setSleepTime(calcSleepTime(getPassispeed(),
									MOVE_SPEED));
						}
					}
				}
			}
		}
		return false;
	}

	public void onFinalAction(L1PcInstance pc, String s) {
	}

	// 现在のターゲットを削除
	public void tagertClear() {
		if (_target == null) {
			return;
		}
		_hateList.remove(_target);
		_target = null;
	}

	// 指定されたターゲットを削除
	public void targetRemove(L1Character target) {
		_hateList.remove(target);
		if ((_target != null) && _target.equals(target)) {
			_target = null;
		}
	}

	// 全てのターゲットを削除
	public void allTargetClear() {
		_hateList.clear();
		_dropHateList.clear();
		_target = null;
		_targetItemList.clear();
		_targetItem = null;
	}

	// マスターの设定
	public void setMaster(L1Character cha) {
		_master = cha;
	}

	// マスターの取得
	public L1Character getMaster() {
		return _master;
	}

	// ＡＩトリガ
	public void onNpcAI() {
	}

	// アイテム精制
	public void refineItem() {

		int[] materials = null;
		int[] counts = null;
		int[] createitem = null;
		int[] createcount = null;

		if (_npcTemplate.get_npcId() == 45032) { // ブロッブ
			// オリハルコンソードの刀身
			if ((getExp() != 0) && !_inventory.checkItem(20)) {
				materials = new int[] { 40508, 40521, 40045 };
				counts = new int[] { 150, 3, 3 };
				createitem = new int[] { 20 };
				createcount = new int[] { 1 };
				if (_inventory.checkItem(materials, counts)) {
					for (int i = 0; i < materials.length; i++) {
						_inventory.consumeItem(materials[i], counts[i]);
					}
					for (int j = 0; j < createitem.length; j++) {
						_inventory.storeItem(createitem[j], createcount[j]);
					}
				}
			}
			// ロングソードの刀身
			if ((getExp() != 0) && !_inventory.checkItem(19)) {
				materials = new int[] { 40494, 40521 };
				counts = new int[] { 150, 3 };
				createitem = new int[] { 19 };
				createcount = new int[] { 1 };
				if (_inventory.checkItem(materials, counts)) {
					for (int i = 0; i < materials.length; i++) {
						_inventory.consumeItem(materials[i], counts[i]);
					}
					for (int j = 0; j < createitem.length; j++) {
						_inventory.storeItem(createitem[j], createcount[j]);
					}
				}
			}
			// ショートソードの刀身
			if ((getExp() != 0) && !_inventory.checkItem(3)) {
				materials = new int[] { 40494, 40521 };
				counts = new int[] { 50, 1 };
				createitem = new int[] { 3 };
				createcount = new int[] { 1 };
				if (_inventory.checkItem(materials, counts)) {
					for (int i = 0; i < materials.length; i++) {
						_inventory.consumeItem(materials[i], counts[i]);
					}
					for (int j = 0; j < createitem.length; j++) {
						_inventory.storeItem(createitem[j], createcount[j]);
					}
				}
			}
			// オリハルコンホーン
			if ((getExp() != 0) && !_inventory.checkItem(100)) {
				materials = new int[] { 88, 40508, 40045 };
				counts = new int[] { 4, 80, 3 };
				createitem = new int[] { 100 };
				createcount = new int[] { 1 };
				if (_inventory.checkItem(materials, counts)) {
					for (int i = 0; i < materials.length; i++) {
						_inventory.consumeItem(materials[i], counts[i]);
					}
					for (int j = 0; j < createitem.length; j++) {
						_inventory.storeItem(createitem[j], createcount[j]);
					}
				}
			}
			// ミスリルホーン
			if ((getExp() != 0) && !_inventory.checkItem(89)) {
				materials = new int[] { 88, 40494 };
				counts = new int[] { 2, 80 };
				createitem = new int[] { 89 };
				createcount = new int[] { 1 };
				if (_inventory.checkItem(materials, counts)) {
					for (int i = 0; i < materials.length; i++) {
						_inventory.consumeItem(materials[i], counts[i]);
					}
					for (int j = 0; j < createitem.length; j++) {
						L1ItemInstance item = _inventory.storeItem(
								createitem[j], createcount[j]);
						if (getNpcTemplate().get_digestitem() > 0) {
							setDigestItem(item);
						}
					}
				}
			}
		} else if (_npcTemplate.get_npcId() == 81069) { // ドッペルゲンガー（クエスト）
			// ドッペルゲンガーの体液
			if ((getExp() != 0) && !_inventory.checkItem(40542)) {
				materials = new int[] { 40032 };
				counts = new int[] { 1 };
				createitem = new int[] { 40542 };
				createcount = new int[] { 1 };
				if (_inventory.checkItem(materials, counts)) {
					for (int i = 0; i < materials.length; i++) {
						_inventory.consumeItem(materials[i], counts[i]);
					}
					for (int j = 0; j < createitem.length; j++) {
						_inventory.storeItem(createitem[j], createcount[j]);
					}
				}
			}
		} else if ((_npcTemplate.get_npcId() == 45166 // ジャックオーランタン
				)
				|| (_npcTemplate.get_npcId() == 45167)) {
			// パンプキンの种
			if ((getExp() != 0) && !_inventory.checkItem(40726)) {
				materials = new int[] { 40725 };
				counts = new int[] { 1 };
				createitem = new int[] { 40726 };
				createcount = new int[] { 1 };
				if (_inventory.checkItem(materials, counts)) {
					for (int i = 0; i < materials.length; i++) {
						_inventory.consumeItem(materials[i], counts[i]);
					}
					for (int j = 0; j < createitem.length; j++) {
						_inventory.storeItem(createitem[j], createcount[j]);
					}
				}
			}
		}
	}

	private boolean _aiRunning = false; // ＡＩが实行中か

	// ※ＡＩをスタートさせる时にすでに实行されてないか确认する时に使用
	private boolean _actived = false; // ＮＰＣがアクティブか

	// ※この值がfalseで_targetがいる场合、アクティブになって初行动とみなしヘイストポーション等を使わせる判定で使用
	private boolean _firstAttack = false; // ファーストアッタクされたか

	private int _sleep_time; // ＡＩを停止する时间(ms) ※行动を起こした场合に所要する时间をセット

	protected L1HateList _hateList = new L1HateList();

	protected L1HateList _dropHateList = new L1HateList();

	// ※攻击するターゲットの判定とＰＴ时のドロップ判定で使用
	protected List<L1ItemInstance> _targetItemList = Lists.newList(); // ダーゲットアイテム一览

	protected L1Character _target = null; // 现在のターゲット

	protected L1ItemInstance _targetItem = null; // 现在のターゲットアイテム

	protected L1Character _master = null; // 主人orグループリーダー

	private boolean _deathProcessing = false; // 死亡处理中か

	// EXP、Drop分配中はターゲットリスト、ヘイトリストをクリアしない

	private int _paralysisTime = 0; // Paralysis RestTime

	public void setParalysisTime(int ptime) {
		_paralysisTime = ptime;
	}

	public L1HateList getHateList() {
		return _hateList;
	}

	public int getParalysisTime() {
		return _paralysisTime;
	}

	// HP自然回复
	public final void startHpRegeneration() {
		int hprInterval = getNpcTemplate().get_hprinterval();
		int hpr = getNpcTemplate().get_hpr();
		if (!_hprRunning && (hprInterval > 0) && (hpr > 0)) {
			_hprTimer = new HprTimer(hpr);
			L1NpcRegenerationTimer.getInstance().scheduleAtFixedRate(_hprTimer,
					hprInterval, hprInterval);
			_hprRunning = true;
		}
	}

	public final void stopHpRegeneration() {
		if (_hprRunning) {
			_hprTimer.cancel();
			_hprRunning = false;
		}
	}

	// MP自然回复
	public final void startMpRegeneration() {
		int mprInterval = getNpcTemplate().get_mprinterval();
		int mpr = getNpcTemplate().get_mpr();
		if (!_mprRunning && (mprInterval > 0) && (mpr > 0)) {
			_mprTimer = new MprTimer(mpr);
			L1NpcRegenerationTimer.getInstance().scheduleAtFixedRate(_mprTimer,
					mprInterval, mprInterval);
			_mprRunning = true;
		}
	}

	public final void stopMpRegeneration() {
		if (_mprRunning) {
			_mprTimer.cancel();
			_mprRunning = false;
		}
	}

	// ■■■■■■■■■■■■ タイマー关连 ■■■■■■■■■■

	// ＨＰ自然回复
	private boolean _hprRunning = false;

	private HprTimer _hprTimer;

	class HprTimer extends TimerTask {
		@Override
		public void run() {
			try {
				if ((!_destroyed && !isDead())
						&& ((getCurrentHp() > 0) && (getCurrentHp() < getMaxHp()))) {
					setCurrentHp(getCurrentHp() + _point);
				} else {
					cancel();
					_hprRunning = false;
				}
			} catch (Exception e) {
				_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			}
		}

		public HprTimer(int point) {
			if (point < 1) {
				point = 1;
			}
			_point = point;
		}

		private final int _point;
	}

	// ＭＰ自然回复
	private boolean _mprRunning = false;

	private MprTimer _mprTimer;

	class MprTimer extends TimerTask {
		@Override
		public void run() {
			try {
				if ((!_destroyed && !isDead())
						&& ((getCurrentHp() > 0) && (getCurrentMp() < getMaxMp()))) {
					setCurrentMp(getCurrentMp() + _point);
				} else {
					cancel();
					_mprRunning = false;
				}
			} catch (Exception e) {
				_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			}
		}

		public MprTimer(int point) {
			if (point < 1) {
				point = 1;
			}
			_point = point;
		}

		private final int _point;
	}

	// アイテム消化
	private Map<Integer, Integer> _digestItems;

	public boolean _digestItemRunning = false;

	class DigestItemTimer implements Runnable {
		@Override
		public void run() {
			_digestItemRunning = true;
			while (!_destroyed && (_digestItems.size() > 0)) {
				try {
					Thread.sleep(1000);
				} catch (Exception exception) {
					break;
				}

				Object[] keys = _digestItems.keySet().toArray();
				for (Object key2 : keys) {
					Integer key = (Integer) key2;
					Integer digestCounter = _digestItems.get(key);
					digestCounter -= 1;
					if (digestCounter <= 0) {
						_digestItems.remove(key);
						L1ItemInstance digestItem = getInventory().getItem(key);
						if (digestItem != null) {
							getInventory().removeItem(digestItem,
									digestItem.getCount());
						}
					} else {
						_digestItems.put(key, digestCounter);
					}
				}
			}
			_digestItemRunning = false;
		}
	}

	// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

	public L1NpcInstance(L1Npc template) {
		setStatus(0);
		setMoveSpeed(0);
		setDead(false);
		setreSpawn(false);

		if (template != null) {
			setting_template(template);
		}
	}

	// 指定のテンプレートで各种值を初期化
	public void setting_template(L1Npc template) {
		_npcTemplate = template;
		int randomlevel = 0;
		double rate = 0;
		double diff = 0;
		setName(template.get_name());
		setNameId(template.get_nameid());
		if (template.get_randomlevel() == 0) { // ランダムLv指定なし
			setLevel(template.get_level());
		} else { // ランダムLv指定あり（最小值:get_level(),最大值:get_randomlevel()）
			randomlevel = Random.nextInt(template.get_randomlevel()
					- template.get_level() + 1);
			diff = template.get_randomlevel() - template.get_level();
			rate = randomlevel / diff;
			randomlevel += template.get_level();
			setLevel(randomlevel);
		}
		if (template.get_randomhp() == 0) {
			setMaxHp(template.get_hp());
			setCurrentHpDirect(template.get_hp());
		} else {
			double randomhp = rate
					* (template.get_randomhp() - template.get_hp());
			int hp = (int) (template.get_hp() + randomhp);
			setMaxHp(hp);
			setCurrentHpDirect(hp);
		}
		if (template.get_randommp() == 0) {
			setMaxMp(template.get_mp());
			setCurrentMpDirect(template.get_mp());
		} else {
			double randommp = rate
					* (template.get_randommp() - template.get_mp());
			int mp = (int) (template.get_mp() + randommp);
			setMaxMp(mp);
			setCurrentMpDirect(mp);
		}
		if (template.get_randomac() == 0) {
			setAc(template.get_ac());
		} else {
			double randomac = rate
					* (template.get_randomac() - template.get_ac());
			int ac = (int) (template.get_ac() + randomac);
			setAc(ac);
		}
		if (template.get_randomlevel() == 0) {
			setStr(template.get_str());
			setCon(template.get_con());
			setDex(template.get_dex());
			setInt(template.get_int());
			setWis(template.get_wis());
			setMr(template.get_mr());
		} else {
			setStr((byte) Math.min(template.get_str() + diff, 127));
			setCon((byte) Math.min(template.get_con() + diff, 127));
			setDex((byte) Math.min(template.get_dex() + diff, 127));
			setInt((byte) Math.min(template.get_int() + diff, 127));
			setWis((byte) Math.min(template.get_wis() + diff, 127));
			setMr((byte) Math.min(template.get_mr() + diff, 127));

			addHitup((int) diff * 2);
			addDmgup((int) diff * 2);
		}
		setAgro(template.is_agro());
		setAgrocoi(template.is_agrocoi());
		setAgrososc(template.is_agrososc());
		setTempCharGfx(template.get_gfxid());
		setGfxId(template.get_gfxid());
		setStatus(L1NpcDefaultAction.getInstance().getStatus(getTempCharGfx()));
		setPolyAtkRanged(template.get_ranged());
		setPolyArrowGfx(template.getBowActId());

		// 移动
		if (template.get_passispeed() != 0) {
			setPassispeed(SprTable.getInstance().getSprSpeed(getTempCharGfx(), getStatus()));
		} else {
			setPassispeed(0);
		}
		// 攻击
		if (template.get_atkspeed() != 0) {
			int actid = (getStatus() + 1);
			if (L1NpcDefaultAction.getInstance().getDefaultAttack(getTempCharGfx()) != actid) {
				actid = L1NpcDefaultAction.getInstance().getDefaultAttack(getTempCharGfx());
			}
			setAtkspeed(SprTable.getInstance().getSprSpeed(getTempCharGfx(), actid));
		} else {
			setAtkspeed(0);
		}

		if (template.get_randomexp() == 0) {
			setExp(template.get_exp());
		} else {
			int level = getLevel();
			int exp = level * level;
			exp += 1;
			setExp(exp);
		}
		if (template.get_randomlawful() == 0) {
			setLawful(template.get_lawful());
			setTempLawful(template.get_lawful());
		} else {
			double randomlawful = rate
					* (template.get_randomlawful() - template.get_lawful());
			int lawful = (int) (template.get_lawful() + randomlawful);
			setLawful(lawful);
			setTempLawful(lawful);
		}
		setPickupItem(template.is_picupitem());
		if (template.is_bravespeed()) {
			setBraveSpeed(1);
		} else {
			setBraveSpeed(0);
		}
		if (template.get_digestitem() > 0) {
			_digestItems = Maps.newMap();
		}
		setKarma(template.getKarma());
		setLightSize(template.getLightSize());

		mobSkill = new L1MobSkillUse(this);
	}

	// 延迟时间
	public void npcSleepTime(int i, int type) {
		setSleepTime(calcSleepTime(SprTable.getInstance()
				.getSprSpeed(getTempCharGfx(), i), type));
	}

	private int _passispeed;

	public int getPassispeed() {
		return _passispeed;
	}

	public void setPassispeed(int i) {
		_passispeed = i;
	}

	private int _atkspeed;

	public int getAtkspeed() {
		return _atkspeed;
	}

	public void setAtkspeed(int i) {
		_atkspeed = i;
	}

	private boolean _pickupItem;

	public boolean isPickupItem() {
		return _pickupItem;
	}

	public void setPickupItem(boolean flag) {
		_pickupItem = flag;
	}

	@Override
	public L1Inventory getInventory() {
		return _inventory;
	}

	public void setInventory(L1Inventory inventory) {
		_inventory = inventory;
	}

	public L1Npc getNpcTemplate() {
		return _npcTemplate;
	}

	public int getNpcId() {
		return _npcTemplate.get_npcId();
	}

	public void setPetcost(int i) {
		_petcost = i;
	}

	public int getPetcost() {
		return _petcost;
	}

	public void setSpawn(L1Spawn spawn) {
		_spawn = spawn;
	}

	public L1Spawn getSpawn() {
		return _spawn;
	}

	public void setSpawnNumber(int number) {
		_spawnNumber = number;
	}

	public int getSpawnNumber() {
		return _spawnNumber;
	}

	// オブジェクトIDをSpawnTaskに渡し再利用する
	// グループモンスターは复杂になるので再利用しない
	public void onDecay(boolean isReuseId) {
		int id = 0;
		if (isReuseId) {
			id = getId();
		} else {
			id = 0;
		}
		_spawn.executeSpawnTask(_spawnNumber, id);
	}

	@Override
	public void onPerceive(L1PcInstance perceivedFrom) {
		perceivedFrom.addKnownObject(this);
		perceivedFrom.sendPackets(new S_NPCPack(this));
		onNpcAI();
	}

	public void deleteMe() {
		_destroyed = true;
		if (getInventory() != null) {
			getInventory().clearItems();
		}
		allTargetClear();
		_master = null;
		L1World.getInstance().removeVisibleObject(this);
		L1World.getInstance().removeObject(this);
		List<L1PcInstance> players = L1World.getInstance().getRecognizePlayer(
				this);
		if (players.size() > 0) {
			S_RemoveObject s_deleteNewObject = new S_RemoveObject(this);
			for (L1PcInstance pc : players) {
				if (pc != null) {
					pc.removeKnownObject(this);
					// if(!L1Character.distancepc(user, this))
					pc.sendPackets(s_deleteNewObject);
				}
			}
		}
		removeAllKnownObjects();

		// リスパウン设定
		L1MobGroupInfo mobGroupInfo = getMobGroupInfo();
		if (mobGroupInfo == null) {
			if (isReSpawn()) {
				onDecay(true);
			}
		} else {
			if (mobGroupInfo.removeMember(this) == 0) { // グループメンバー全灭
				setMobGroupInfo(null);
				if (isReSpawn()) {
					onDecay(false);
				}
			}
		}
	}

	public void ReceiveManaDamage(L1Character attacker, int damageMp) {
	}

	public void receiveDamage(L1Character attacker, int damage) {
	}

	public void setDigestItem(L1ItemInstance item) {
		_digestItems.put(new Integer(item.getId()), new Integer(
				getNpcTemplate().get_digestitem()));
		if (!_digestItemRunning) {
			DigestItemTimer digestItemTimer = new DigestItemTimer();
			GeneralThreadPool.getInstance().execute(digestItemTimer);
		}
	}

	public void onGetItem(L1ItemInstance item) {
		refineItem();
		getInventory().shuffle();
		if (getNpcTemplate().get_digestitem() > 0) {
			setDigestItem(item);
		}
	}

	public void approachPlayer(L1PcInstance pc) {
		if (pc.hasSkillEffect(60) || pc.hasSkillEffect(97)) { // インビジビリティ、ブラインドハイディング中
			return;
		}
		if (getHiddenStatus() == HIDDEN_STATUS_SINK) {
			if (getCurrentHp() == getMaxHp()) {
				if (pc.getLocation().getTileLineDistance(getLocation()) <= 2) {
					appearOnGround(pc);
				}
			}
		} else if (getHiddenStatus() == HIDDEN_STATUS_FLY) {
			if (getCurrentHp() == getMaxHp()) {
				if (pc.getLocation().getTileLineDistance(getLocation()) <= 1) {
					appearOnGround(pc);
				}
			} else {
				// if (getNpcTemplate().get_npcId() != 45681) { // リンドビオル以外
				searchItemFromAir();
				// }
			}
		} else if (getHiddenStatus() == HIDDEN_STATUS_ICE) {
			if (getCurrentHp() < getMaxHp()) {
				appearOnGround(pc);
			}
		}
	}

	public void appearOnGround(L1PcInstance pc) { // 怪物解除遁地、飞天、冰冻
		if (getHiddenStatus() == HIDDEN_STATUS_SINK) {

			setHiddenStatus(HIDDEN_STATUS_NONE);
			setStatus(L1NpcDefaultAction.getInstance().getStatus(getTempCharGfx()));
			broadcastPacket(new S_DoActionGFX(getId(), ActionCodes.ACTION_Appear));
			broadcastPacket(new S_CharVisualUpdate(this, getStatus()));
			if (!pc.hasSkillEffect(60) && !pc.hasSkillEffect(97) // インビジビリティ、ブラインドハイディング中以外、GM以外
					&& !pc.isGm()) {
				_hateList.add(pc, 0);
				_target = pc;
			}
			onNpcAI(); // モンスターのＡＩを开始
			startChat(CHAT_TIMING_HIDE);
		} else if (getHiddenStatus() == HIDDEN_STATUS_FLY) {
			setHiddenStatus(HIDDEN_STATUS_NONE);
			setStatus(L1NpcDefaultAction.getInstance().getStatus(getTempCharGfx()));
			broadcastPacket(new S_DoActionGFX(getId(), ActionCodes.ACTION_Movedown));
			if (!pc.hasSkillEffect(60) && !pc.hasSkillEffect(97) // インビジビリティ、ブラインドハイディング中以外、GM以外
					&& !pc.isGm()) {
				_hateList.add(pc, 0);
				_target = pc;
			}
			onNpcAI(); // モンスターのＡＩを开始
			startChat(CHAT_TIMING_HIDE);
		} else if (getHiddenStatus() == HIDDEN_STATUS_ICE) {
			setHiddenStatus(HIDDEN_STATUS_NONE);
			setStatus(L1NpcDefaultAction.getInstance().getStatus(getTempCharGfx()));
			broadcastPacket(new S_DoActionGFX(getId(), ActionCodes.ACTION_AxeWalk));
			broadcastPacket(new S_CharVisualUpdate(this, getStatus()));
			if (!pc.hasSkillEffect(60) && !pc.hasSkillEffect(97) // インビジビリティ、ブラインドハイディング中以外、GM以外
					&& !pc.isGm()) {
				_hateList.add(pc, 0);
				_target = pc;
			}
			onNpcAI(); // モンスターのＡＩを开始
			startChat(CHAT_TIMING_HIDE);
		}
	}

	// ■■■■■■■■■■■■■ 移动关连 ■■■■■■■■■■■

	// 指定された方向に移动させる
	public void setDirectionMove(int dir) {
		if (dir >= 0) {
			int nx = 0;
			int ny = 0;

			switch (dir) {
			case 1:
				nx = 1;
				ny = -1;
				setHeading(1);
				break;

			case 2:
				nx = 1;
				ny = 0;
				setHeading(2);
				break;

			case 3:
				nx = 1;
				ny = 1;
				setHeading(3);
				break;

			case 4:
				nx = 0;
				ny = 1;
				setHeading(4);
				break;

			case 5:
				nx = -1;
				ny = 1;
				setHeading(5);
				break;

			case 6:
				nx = -1;
				ny = 0;
				setHeading(6);
				break;

			case 7:
				nx = -1;
				ny = -1;
				setHeading(7);
				break;

			case 0:
				nx = 0;
				ny = -1;
				setHeading(0);
				break;

			default:
				break;

			}

			getMap().setPassable(getLocation(), true);

			int nnx = getX() + nx;
			int nny = getY() + ny;
			setX(nnx);
			setY(nny);

			getMap().setPassable(getLocation(), false);

			broadcastPacket(new S_MoveCharPacket(this));

			// movement_distanceマス以上离れたらホームポイントへテレポート
			if (getMovementDistance() > 0) {
				if ((this instanceof L1GuardInstance)
						|| (this instanceof L1MerchantInstance)
						|| (this instanceof L1MonsterInstance)) {
					if (getLocation().getLineDistance(
							new Point(getHomeX(), getHomeY())) > getMovementDistance()) {
						teleport(getHomeX(), getHomeY(), getHeading());
					}
				}
			}
			// 判断士兵的怨灵、怨灵、哈蒙将军的怨灵离开墓园范围时传送回墓园！
			if ((getNpcTemplate().get_npcId() >= 45912)
					&& (getNpcTemplate().get_npcId() <= 45916)) {
				if (!((getX() >= 32591) && (getX() <= 32644)
						&& (getY() >= 32643) && (getY() <= 32688) && (getMapId() == 4))) {
					teleport(getHomeX(), getHomeY(), getHeading());
				}
			}
		}
	}

	public int moveDirection(int x, int y) { // 目标点Ｘ 目标点Ｙ
		return moveDirection(x, y,
				getLocation().getLineDistance(new Point(x, y)));
	}

	// 目标までの距离に应じて最适と思われるルーチンで进む方向を返す
	public int moveDirection(int x, int y, double d) { // 目标点Ｘ 目标点Ｙ 目标までの距离
		int dir = 0;
		if ((hasSkillEffect(40) == true) && (d >= 2D)) { // ダークネスが挂かっていて、距离が2以上の场合追迹终了
			return -1;
		} else if (d > 30D) { // 距离が激しく远い场合は追迹终了
			return -1;
		} else if (d > courceRange) { // 距离が远い场合は单纯计算
			dir = targetDirection(x, y);
			dir = checkObject(getX(), getY(), getMapId(), dir);
		} else { // 目标までの最短经路を探索
			dir = _serchCource(x, y);
			if (dir == -1) { // 目标までの经路がなっかた场合はとりあえず近づいておく
				dir = targetDirection(x, y);
				if (!isExsistCharacterBetweenTarget(dir)) {
					dir = checkObject(getX(), getY(), getMapId(), dir);
				}
			}
		}
		return dir;
	}

	private boolean isExsistCharacterBetweenTarget(int dir) {
		if (!(this instanceof L1MonsterInstance)) { // モンスター以外は对象外
			return false;
		}
		if (_target == null) { // ターゲットがいない场合
			return false;
		}

		int locX = getX();
		int locY = getY();
		int targetX = locX;
		int targetY = locY;

		if (dir == 1) {
			targetX = locX + 1;
			targetY = locY - 1;
		} else if (dir == 2) {
			targetX = locX + 1;
		} else if (dir == 3) {
			targetX = locX + 1;
			targetY = locY + 1;
		} else if (dir == 4) {
			targetY = locY + 1;
		} else if (dir == 5) {
			targetX = locX - 1;
			targetY = locY + 1;
		} else if (dir == 6) {
			targetX = locX - 1;
		} else if (dir == 7) {
			targetX = locX - 1;
			targetY = locY - 1;
		} else if (dir == 0) {
			targetY = locY - 1;
		}

		for (L1Object object : L1World.getInstance().getVisibleObjects(this, 1)) {
			// PC, Summon, Petがいる场合
			if ((object instanceof L1PcInstance)
					|| (object instanceof L1SummonInstance)
					|| (object instanceof L1PetInstance)) {
				L1Character cha = (L1Character) object;
				// 进行方向に立ちふさがっている场合、ターゲットリストに加える
				if ((cha.getX() == targetX) && (cha.getY() == targetY)
						&& (cha.getMapId() == getMapId())) {
					if (object instanceof L1PcInstance) {
						L1PcInstance pc = (L1PcInstance) object;
						if (pc.isGhost()) { // UB观战中のPCは除く
							continue;
						}
					}
					_hateList.add(cha, 0);
					_target = cha;
					return true;
				}
			}
		}
		return false;
	}

	// 目标の逆方向を返す
	public int targetReverseDirection(int tx, int ty) { // 目标点Ｘ 目标点Ｙ
		int dir = targetDirection(tx, ty);
		dir += 4;
		if (dir > 7) {
			dir -= 8;
		}
		return dir;
	}

	// 进みたい方向に障害物がないか确认、ある场合は前方斜め左右も确认后进める方向を返す
	// ※从来あった处理に、バックできない仕样を省いて、目标の反对（左右含む）には进まないようにしたもの
	public static int checkObject(int x, int y, short m, int d) { // 起点Ｘ 起点Ｙ
																	// マップＩＤ
		// 进行方向
		L1Map map = L1WorldMap.getInstance().getMap(m);
		if (d == 1) {
			if (map.isPassable(x, y, 1)) {
				return 1;
			} else if (map.isPassable(x, y, 0)) {
				return 0;
			} else if (map.isPassable(x, y, 2)) {
				return 2;
			}
		} else if (d == 2) {
			if (map.isPassable(x, y, 2)) {
				return 2;
			} else if (map.isPassable(x, y, 1)) {
				return 1;
			} else if (map.isPassable(x, y, 3)) {
				return 3;
			}
		} else if (d == 3) {
			if (map.isPassable(x, y, 3)) {
				return 3;
			} else if (map.isPassable(x, y, 2)) {
				return 2;
			} else if (map.isPassable(x, y, 4)) {
				return 4;
			}
		} else if (d == 4) {
			if (map.isPassable(x, y, 4)) {
				return 4;
			} else if (map.isPassable(x, y, 3)) {
				return 3;
			} else if (map.isPassable(x, y, 5)) {
				return 5;
			}
		} else if (d == 5) {
			if (map.isPassable(x, y, 5)) {
				return 5;
			} else if (map.isPassable(x, y, 4)) {
				return 4;
			} else if (map.isPassable(x, y, 6)) {
				return 6;
			}
		} else if (d == 6) {
			if (map.isPassable(x, y, 6)) {
				return 6;
			} else if (map.isPassable(x, y, 5)) {
				return 5;
			} else if (map.isPassable(x, y, 7)) {
				return 7;
			}
		} else if (d == 7) {
			if (map.isPassable(x, y, 7)) {
				return 7;
			} else if (map.isPassable(x, y, 6)) {
				return 6;
			} else if (map.isPassable(x, y, 0)) {
				return 0;
			}
		} else if (d == 0) {
			if (map.isPassable(x, y, 0)) {
				return 0;
			} else if (map.isPassable(x, y, 7)) {
				return 7;
			} else if (map.isPassable(x, y, 1)) {
				return 1;
			}
		}
		return -1;
	}

	// 目标までの最短经路の方向を返す
	// ※目标を中心とした探索范围のマップで探索
	private int _serchCource(int x, int y) // 目标点Ｘ 目标点Ｙ
	{
		int i;
		int locCenter = courceRange + 1;
		int diff_x = x - locCenter; // Ｘの实际のロケーションとの差
		int diff_y = y - locCenter; // Ｙの实际のロケーションとの差
		int[] locBace = { getX() - diff_x, getY() - diff_y, 0, 0 }; // Ｘ Ｙ
		// 方向
		// 初期方向
		int[] locNext = new int[4];
		int[] locCopy;
		int[] dirFront = new int[5];
		boolean serchMap[][] = new boolean[locCenter * 2 + 1][locCenter * 2 + 1];
		LinkedList<int[]> queueSerch = new LinkedList<int[]>();

		// 探索用マップの设定
		for (int j = courceRange * 2 + 1; j > 0; j--) {
			for (i = courceRange - Math.abs(locCenter - j); i >= 0; i--) {
				serchMap[j][locCenter + i] = true;
				serchMap[j][locCenter - i] = true;
			}
		}

		// 初期方向の设置
		int[] firstCource = { 2, 4, 6, 0, 1, 3, 5, 7 };
		for (i = 0; i < 8; i++) {
			System.arraycopy(locBace, 0, locNext, 0, 4);
			_moveLocation(locNext, firstCource[i]);
			if ((locNext[0] - locCenter == 0) && (locNext[1] - locCenter == 0)) {
				// 最短经路が见つかった场合:邻
				return firstCource[i];
			}
			if (serchMap[locNext[0]][locNext[1]]) {
				int tmpX = locNext[0] + diff_x;
				int tmpY = locNext[1] + diff_y;
				boolean found = false;
				if (i == 0) {
					found = getMap().isPassable(tmpX, tmpY + 1, i);
				} else if (i == 1) {
					found = getMap().isPassable(tmpX - 1, tmpY + 1, i);
				} else if (i == 2) {
					found = getMap().isPassable(tmpX - 1, tmpY, i);
				} else if (i == 3) {
					found = getMap().isPassable(tmpX - 1, tmpY - 1, i);
				} else if (i == 4) {
					found = getMap().isPassable(tmpX, tmpY - 1, i);
				} else if (i == 5) {
					found = getMap().isPassable(tmpX + 1, tmpY - 1, i);
				} else if (i == 6) {
					found = getMap().isPassable(tmpX + 1, tmpY, i);
				} else if (i == 7) {
					found = getMap().isPassable(tmpX + 1, tmpY + 1, i);
				}
				if (found)// 移动经路があった场合
				{
					locCopy = new int[4];
					System.arraycopy(locNext, 0, locCopy, 0, 4);
					locCopy[2] = firstCource[i];
					locCopy[3] = firstCource[i];
					queueSerch.add(locCopy);
				}
				serchMap[locNext[0]][locNext[1]] = false;
			}
		}
		locBace = null;

		// 最短经路を探索
		while (queueSerch.size() > 0) {
			locBace = queueSerch.removeFirst();
			_getFront(dirFront, locBace[2]);
			for (i = 4; i >= 0; i--) {
				System.arraycopy(locBace, 0, locNext, 0, 4);
				_moveLocation(locNext, dirFront[i]);
				if ((locNext[0] - locCenter == 0)
						&& (locNext[1] - locCenter == 0)) {
					return locNext[3];
				}
				if (serchMap[locNext[0]][locNext[1]]) {
					int tmpX = locNext[0] + diff_x;
					int tmpY = locNext[1] + diff_y;
					boolean found = false;
					if (i == 0) {
						found = getMap().isPassable(tmpX, tmpY + 1, i);
					} else if (i == 1) {
						found = getMap().isPassable(tmpX - 1, tmpY + 1, i);
					} else if (i == 2) {
						found = getMap().isPassable(tmpX - 1, tmpY, i);
					} else if (i == 3) {
						found = getMap().isPassable(tmpX - 1, tmpY - 1, i);
					} else if (i == 4) {
						found = getMap().isPassable(tmpX, tmpY - 1, i);
					}
					if (found) // 移动经路があった场合
					{
						locCopy = new int[4];
						System.arraycopy(locNext, 0, locCopy, 0, 4);
						locCopy[2] = dirFront[i];
						queueSerch.add(locCopy);
					}
					serchMap[locNext[0]][locNext[1]] = false;
				}
			}
			locBace = null;
		}
		return -1; // 目标までの经路がない场合
	}

	private void _moveLocation(int[] ary, int d) {
		if (d == 1) {
			ary[0] = ary[0] + 1;
			ary[1] = ary[1] - 1;
		} else if (d == 2) {
			ary[0] = ary[0] + 1;
		} else if (d == 3) {
			ary[0] = ary[0] + 1;
			ary[1] = ary[1] + 1;
		} else if (d == 4) {
			ary[1] = ary[1] + 1;
		} else if (d == 5) {
			ary[0] = ary[0] - 1;
			ary[1] = ary[1] + 1;
		} else if (d == 6) {
			ary[0] = ary[0] - 1;
		} else if (d == 7) {
			ary[0] = ary[0] - 1;
			ary[1] = ary[1] - 1;
		} else if (d == 0) {
			ary[1] = ary[1] - 1;
		}
		ary[2] = d;
	}

	private void _getFront(int[] ary, int d) {
		if (d == 1) {
			ary[4] = 2;
			ary[3] = 0;
			ary[2] = 1;
			ary[1] = 3;
			ary[0] = 7;
		} else if (d == 2) {
			ary[4] = 2;
			ary[3] = 4;
			ary[2] = 0;
			ary[1] = 1;
			ary[0] = 3;
		} else if (d == 3) {
			ary[4] = 2;
			ary[3] = 4;
			ary[2] = 1;
			ary[1] = 3;
			ary[0] = 5;
		} else if (d == 4) {
			ary[4] = 2;
			ary[3] = 4;
			ary[2] = 6;
			ary[1] = 3;
			ary[0] = 5;
		} else if (d == 5) {
			ary[4] = 4;
			ary[3] = 6;
			ary[2] = 3;
			ary[1] = 5;
			ary[0] = 7;
		} else if (d == 6) {
			ary[4] = 4;
			ary[3] = 6;
			ary[2] = 0;
			ary[1] = 5;
			ary[0] = 7;
		} else if (d == 7) {
			ary[4] = 6;
			ary[3] = 0;
			ary[2] = 1;
			ary[1] = 5;
			ary[0] = 7;
		} else if (d == 0) {
			ary[4] = 2;
			ary[3] = 6;
			ary[2] = 0;
			ary[1] = 1;
			ary[0] = 7;
		}
	}

	// ■■■■■■■■■■■■ アイテム关连 ■■■■■■■■■■

	private void useHealPotion(int healHp, int effectId) {
		broadcastPacket(new S_SkillSound(getId(), effectId));
		if (hasSkillEffect(POLLUTE_WATER)) { // ポルートウォーター中は回复量1/2倍
			healHp /= 2;
		}
		if (this instanceof L1PetInstance) {
			((L1PetInstance) this).setCurrentHp(getCurrentHp() + healHp);
		} else if (this instanceof L1SummonInstance) {
			((L1SummonInstance) this).setCurrentHp(getCurrentHp() + healHp);
		} else {
			setCurrentHpDirect(getCurrentHp() + healHp);
		}
	}

	private void useHastePotion(int time) {
		broadcastPacket(new S_SkillHaste(getId(), 1, time));
		broadcastPacket(new S_SkillSound(getId(), 191));
		setMoveSpeed(1);
		setSkillEffect(STATUS_HASTE, time * 1000);
	}

	// アイテムの使用判定及び使用
	public static final int USEITEM_HEAL = 0;

	public static final int USEITEM_HASTE = 1;

	public static int[] healPotions = { POTION_OF_GREATER_HEALING,
			POTION_OF_EXTRA_HEALING, POTION_OF_HEALING };

	public static int[] haestPotions = { B_POTION_OF_GREATER_HASTE_SELF,
			POTION_OF_GREATER_HASTE_SELF, B_POTION_OF_HASTE_SELF,
			POTION_OF_HASTE_SELF };

	public void useItem(int type, int chance) { // 使用する种类 使用する可能性(％)
		if (hasSkillEffect(71)) {
			return; // ディケイ ポーション状态かチェック
		}

		if (Random.nextInt(100) > chance) {
			return; // 使用する可能性
		}

		if (type == USEITEM_HEAL) { // 回复系ポーション
			// 回复量の大きい顺
			if (getInventory().consumeItem(POTION_OF_GREATER_HEALING, 1)) {
				useHealPotion(75, 197);
			} else if (getInventory().consumeItem(POTION_OF_EXTRA_HEALING, 1)) {
				useHealPotion(45, 194);
			} else if (getInventory().consumeItem(POTION_OF_HEALING, 1)) {
				useHealPotion(15, 189);
			}
		} else if (type == USEITEM_HASTE) { // ヘイスト系ポーション
			if (hasSkillEffect(1001)) {
				return; // ヘイスト状态チェック
			}

			// 效果の长い顺
			if (getInventory().consumeItem(B_POTION_OF_GREATER_HASTE_SELF, 1)) {
				useHastePotion(2100);
			} else if (getInventory().consumeItem(POTION_OF_GREATER_HASTE_SELF,
					1)) {
				useHastePotion(1800);
			} else if (getInventory().consumeItem(B_POTION_OF_HASTE_SELF, 1)) {
				useHastePotion(350);
			} else if (getInventory().consumeItem(POTION_OF_HASTE_SELF, 1)) {
				useHastePotion(300);
			}
		}
	}

	// ■■■■■■■■■■■■■ スキル关连(npcskillsテーブル实装されたら消すかも) ■■■■■■■■■■■

	// 目标の邻へテレポート
	public boolean nearTeleport(int nx, int ny) {
		int rdir = Random.nextInt(8);
		int dir;
		for (int i = 0; i < 8; i++) {
			dir = rdir + i;
			if (dir > 7) {
				dir -= 8;
			}
			if (dir == 1) {
				nx++;
				ny--;
			} else if (dir == 2) {
				nx++;
			} else if (dir == 3) {
				nx++;
				ny++;
			} else if (dir == 4) {
				ny++;
			} else if (dir == 5) {
				nx--;
				ny++;
			} else if (dir == 6) {
				nx--;
			} else if (dir == 7) {
				nx--;
				ny--;
			} else if (dir == 0) {
				ny--;
			}
			if (getMap().isPassable(nx, ny)) {
				dir += 4;
				if (dir > 7) {
					dir -= 8;
				}
				teleport(nx, ny, dir);
				setCurrentMp(getCurrentMp() - 10);
				return true;
			}
		}
		return false;
	}

	// 目标へテレポート
	public void teleport(int nx, int ny, int dir) {
		for (L1PcInstance pc : L1World.getInstance().getRecognizePlayer(this)) {
			pc.sendPackets(new S_SkillSound(getId(), 169));
			pc.sendPackets(new S_RemoveObject(this));
			pc.removeKnownObject(this);
		}
		setX(nx);
		setY(ny);
		setHeading(dir);
	}

	// ----------From L1Character-------------
	private String _nameId; // ● ネームＩＤ

	public String getNameId() {
		return _nameId;
	}

	public void setNameId(String s) {
		_nameId = s;
	}

	private boolean _Agro; // ● アクティブか

	public boolean isAgro() {
		return _Agro;
	}

	public void setAgro(boolean flag) {
		_Agro = flag;
	}

	private boolean _Agrocoi; // ● インビジアクティブか

	public boolean isAgrocoi() {
		return _Agrocoi;
	}

	public void setAgrocoi(boolean flag) {
		_Agrocoi = flag;
	}

	private boolean _Agrososc; // ● 变身アクティブか

	public boolean isAgrososc() {
		return _Agrososc;
	}

	public void setAgrososc(boolean flag) {
		_Agrososc = flag;
	}

	private int _homeX; // ● ホームポイントＸ（モンスターの戻る位置とかペットの警戒位置）

	public int getHomeX() {
		return _homeX;
	}

	public void setHomeX(int i) {
		_homeX = i;
	}

	private int _homeY; // ● ホームポイントＹ（モンスターの戻る位置とかペットの警戒位置）

	public int getHomeY() {
		return _homeY;
	}

	public void setHomeY(int i) {
		_homeY = i;
	}

	private boolean _reSpawn; // ● 再ポップするかどうか

	public boolean isReSpawn() {
		return _reSpawn;
	}

	public void setreSpawn(boolean flag) {
		_reSpawn = flag;
	}

	private int _lightSize; // ● ライト ０．なし １～１４．大きさ

	public int getLightSize() {
		return _lightSize;
	}

	public void setLightSize(int i) {
		_lightSize = i;
	}

	private boolean _weaponBreaked; // ● ウェポンブレイク中かどうか

	public boolean isWeaponBreaked() {
		return _weaponBreaked;
	}

	public void setWeaponBreaked(boolean flag) {
		_weaponBreaked = flag;
	}

	private int _hiddenStatus; // ● 地中に潜ったり、空を飞んでいる状态

	public int getHiddenStatus() {
		return _hiddenStatus;
	}

	public void setHiddenStatus(int i) {
		_hiddenStatus = i;
	}

	// 行动距离
	private int _movementDistance = 0;

	public int getMovementDistance() {
		return _movementDistance;
	}

	public void setMovementDistance(int i) {
		_movementDistance = i;
	}

	// 表示用ロウフル
	private int _tempLawful = 0;

	public int getTempLawful() {
		return _tempLawful;
	}

	public void setTempLawful(int i) {
		_tempLawful = i;
	}

	protected int calcSleepTime(int sleepTime, int type) {
		switch (getMoveSpeed()) {
		case 0: // 通常
			break;
		case 1: // ヘイスト
			sleepTime -= (sleepTime * 0.25);
			break;
		case 2: // スロー
			sleepTime *= 2;
			break;
		}
		if (getBraveSpeed() == 1) {
			sleepTime -= (sleepTime * 0.25);
		}
		if (hasSkillEffect(WIND_SHACKLE)) {
			if ((type == ATTACK_SPEED) || (type == MAGIC_SPEED)) {
				sleepTime += (sleepTime * 0.25);
			}
		}
		return sleepTime;
	}

	protected void setAiRunning(boolean aiRunning) {
		_aiRunning = aiRunning;
	}

	protected boolean isAiRunning() {
		return _aiRunning;
	}

	protected void setActived(boolean actived) {
		_actived = actived;
	}

	protected boolean isActived() {
		return _actived;
	}

	protected void setFirstAttack(boolean firstAttack) {
		_firstAttack = firstAttack;
	}

	protected boolean isFirstAttack() {
		return _firstAttack;
	}

	protected void setSleepTime(int sleep_time) {
		_sleep_time = sleep_time;
	}

	protected int getSleepTime() {
		return _sleep_time;
	}

	protected void setDeathProcessing(boolean deathProcessing) {
		_deathProcessing = deathProcessing;
	}

	protected boolean isDeathProcessing() {
		return _deathProcessing;
	}

	public int drainMana(int drain) {
		if (_drainedMana >= Config.MANA_DRAIN_LIMIT_PER_NPC) {
			return 0;
		}
		int result = Math.min(drain, getCurrentMp());
		if (_drainedMana + result > Config.MANA_DRAIN_LIMIT_PER_NPC) {
			result = Config.MANA_DRAIN_LIMIT_PER_NPC - _drainedMana;
		}
		_drainedMana += result;
		return result;
	}

	public boolean _destroyed = false; // このインスタンスが破弃されているか

	// ※破弃后に动かないよう强制的にＡＩ等のスレッド处理中止（念のため）

	// NPCが别のNPCに变わる场合の处理
	protected void transform(int transformId) {
		stopHpRegeneration();
		stopMpRegeneration();
		int transformGfxId = getNpcTemplate().getTransformGfxId();
		if (transformGfxId != 0) {
			broadcastPacket(new S_SkillSound(getId(), transformGfxId));
		}
		L1Npc npcTemplate = NpcTable.getInstance().getTemplate(transformId);
		setting_template(npcTemplate);

		broadcastPacket(new S_NpcChangeShape(getId(), getTempCharGfx(), getLawful(), getStatus()));
		for (L1PcInstance pc : L1World.getInstance().getRecognizePlayer(this)) {
			onPerceive(pc);
		}

	}

	public void setRest(boolean _rest) {
		this._rest = _rest;
	}

	public boolean isRest() {
		return _rest;
	}

	private boolean _isResurrect;

	public boolean isResurrect() {
		return _isResurrect;
	}

	public void setResurrect(boolean flag) {
		_isResurrect = flag;
	}

	/** 妖精森林 物品掉落*/
	private boolean _isDropitems = false;

	public boolean isDropitems() {
		return _isDropitems;
	}

	public void setDropItems(boolean i) {
		_isDropitems = i;
	}

	private boolean _forDropitems = false;

	public boolean forDropitems() {
		return _forDropitems;
	}

	public void giveDropItems(boolean i) {
		_forDropitems = i;
	}

	@Override
	public synchronized void resurrect(int hp) {
		if (_destroyed) {
			return;
		}
		if (_deleteTask != null) {
			if (!_future.cancel(false)) { // キャンセルできない
				return;
			}
			_deleteTask = null;
			_future = null;
		}
		super.resurrect(hp);

		// キャンセレーションをエフェクトなしでかける
		// 本来は死亡时に行うべきだが、负荷が大きくなるため复活时に行う
		L1SkillUse skill = new L1SkillUse();
		skill.handleCommands(null, CANCELLATION, getId(), getX(), getY(), null,
				0, L1SkillUse.TYPE_LOGIN, this);
	}

	// 死んでから消えるまでの时间计测用
	private DeleteTimer _deleteTask;

	private ScheduledFuture<?> _future = null;

	protected synchronized void startDeleteTimer() {
		if (_deleteTask != null) {
			return;
		}
		_deleteTask = new DeleteTimer(getId());
		_future = GeneralThreadPool.getInstance().schedule(_deleteTask,
				Config.NPC_DELETION_TIME * 1000);
	}

	protected static class DeleteTimer extends TimerTask {
		private int _id;

		protected DeleteTimer(int oId) {
			_id = oId;
			if (!(L1World.getInstance().findObject(_id) instanceof L1NpcInstance)) {
				throw new IllegalArgumentException("allowed only L1NpcInstance");
			}
		}

		@Override
		public void run() {
			L1NpcInstance npc = (L1NpcInstance) L1World.getInstance()
					.findObject(_id);
			if ((npc == null) || !npc.isDead() || npc._destroyed) {
				return; // 复活してるか、既に破弃济みだったら拔け
			}
			try {
				npc.deleteMe();
			} catch (Exception e) { // 绝对例外を投げないように
				e.printStackTrace();
			}
		}
	}

	private L1MobGroupInfo _mobGroupInfo = null;

	public boolean isInMobGroup() {
		return getMobGroupInfo() != null;
	}

	public L1MobGroupInfo getMobGroupInfo() {
		return _mobGroupInfo;
	}

	public void setMobGroupInfo(L1MobGroupInfo m) {
		_mobGroupInfo = m;
	}

	private int _mobGroupId = 0;

	public int getMobGroupId() {
		return _mobGroupId;
	}

	public void setMobGroupId(int i) {
		_mobGroupId = i;
	}

	public void startChat(int chatTiming) {
		// 出现时のチャットにも关わらず死亡中、死亡时のチャットにも关わらず生存中
		if ((chatTiming == CHAT_TIMING_APPEARANCE) && isDead()) {
			return;
		}
		if ((chatTiming == CHAT_TIMING_DEAD) && !isDead()) {
			return;
		}
		if ((chatTiming == CHAT_TIMING_HIDE) && isDead()) {
			return;
		}
		if ((chatTiming == CHAT_TIMING_GAME_TIME) && isDead()) {
			return;
		}

		int npcId = getNpcTemplate().get_npcId();
		L1NpcChat npcChat = null;
		if (chatTiming == CHAT_TIMING_APPEARANCE) {
			npcChat = NpcChatTable.getInstance().getTemplateAppearance(npcId);
		} else if (chatTiming == CHAT_TIMING_DEAD) {
			npcChat = NpcChatTable.getInstance().getTemplateDead(npcId);
		} else if (chatTiming == CHAT_TIMING_HIDE) {
			npcChat = NpcChatTable.getInstance().getTemplateHide(npcId);
		} else if (chatTiming == CHAT_TIMING_GAME_TIME) {
			npcChat = NpcChatTable.getInstance().getTemplateGameTime(npcId);
		}
		if (npcChat == null) {
			return;
		}

		Timer timer = new Timer(true);
		L1NpcChatTimer npcChatTimer = new L1NpcChatTimer(this, npcChat);
		if (!npcChat.isRepeat()) {
			timer.schedule(npcChatTimer, npcChat.getStartDelayTime());
		} else {
			timer.scheduleAtFixedRate(npcChatTimer,
					npcChat.getStartDelayTime(), npcChat.getRepeatInterval());
		}
	}

	public int getAtkRanged() {
		if (_polyAtkRanged == -1) {
			return getNpcTemplate().get_ranged();
		}
		return _polyAtkRanged;
	}

	private int _polyAtkRanged = -1;

	public int getPolyAtkRanged() {
		return _polyAtkRanged;
	}

	public void setPolyAtkRanged(int i) {
		_polyAtkRanged = i;
	}

	private int _polyArrowGfx = 0;

	public int getPolyArrowGfx() {
		return _polyArrowGfx;
	}

	public void setPolyArrowGfx(int i) {
		_polyArrowGfx = i;
	}
/*
    public void setLawful(int i) {
        _npcTemplate.set_lawful(i);
	}
*/
}
