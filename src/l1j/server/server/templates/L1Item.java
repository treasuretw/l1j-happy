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
package l1j.server.server.templates;

import java.io.Serializable;
import java.sql.Timestamp;

public abstract class L1Item implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;

	public L1Item() {
	}

	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// throw (new InternalError(e.getMessage()));
			return null;
		}
	}

	// ■■■■■■ L1EtcItem,L1Weapon,L1Armor に共通する项目 ■■■■■■

	private int _type2; // ● 0=L1EtcItem, 1=L1Weapon, 2=L1Armor

	/**
	 * @return 0 if L1EtcItem, 1 if L1Weapon, 2 if L1Armor
	 */
	public int getType2() {
		return _type2;
	}

	public void setType2(int type) {
		_type2 = type;
	}

	private int _itemId; // ● 项目ＩＤ

	public int getItemId() {
		return _itemId;
	}

	public void setItemId(int itemId) {
		_itemId = itemId;
	}

	private String _name; // ● 项目名称

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	private String _unidentifiedNameId; // ● 未鉴定アイテムのネームＩＤ

	public String getUnidentifiedNameId() {
		return _unidentifiedNameId;
	}

	public void setUnidentifiedNameId(String unidentifiedNameId) {
		_unidentifiedNameId = unidentifiedNameId;
	}

	private String _identifiedNameId; // ● 鉴定济みアイテムのネームＩＤ

	public String getIdentifiedNameId() {
		return _identifiedNameId;
	}

	public void setIdentifiedNameId(String identifiedNameId) {
		_identifiedNameId = identifiedNameId;
	}

	private int _type; // ● 详细类型

	/**
	 * アイテムの种类を返す。<br>
	 * 
	 * @return <p>
	 *         [etcitem]<br>
	 *         0:arrow, 1:wand, 2:light, 3:gem, 4:totem, 5:firecracker,
	 *         6:potion, 7:food, 8:scroll, 9:questitem, 10:spellbook,
	 *         11:petitem, 12:other, 13:material, 14:event, 15:sting
	 *         </p>
	 *         <p>
	 *         [weapon]<br>
	 *         1:sword, 2:dagger, 3:tohandsword, 4:bow, 5:spear, 6:blunt,
	 *         7:staff, 8:throwingknife, 9:arrow, 10:gauntlet, 11:claw,
	 *         12:edoryu, 13:singlebow, 14:singlespear, 15:tohandblunt,
	 *         16:tohandstaff, 17:kiringku 18chainsword
	 *         </p>
	 *         <p>
	 *         [armor]<br>
	 *         1:helm, 2:armor, 3:T, 4:cloak, 5:glove, 6:boots, 7:shield,
	 *         8:amulet, 9:ring, 10:belt, 11:ring2, 12:earring
	 */
	/**
	 * 返回该项目的种类。<br>
	 * 
	 * @return <p>
	 *         [道具]<br>
	 *         0:箭头, 1:魔杖, 2:光线 (灯), 3:宝物 (金币), 4:图腾, 5:烟火,
	 *         6:货币 (名誉货币), 7:肉, 8:卷轴, 9:任务物品, 10:魔法书,
	 *         11:宠物装备, 12:其他, 13:材料, 14:活动物品, 15:飞刀
	 *         </p>
	 *         <p>
	 *         [武器]<br>
	 *         1:长剑, 2:短剑, 3:巨剑, 4:弓, 5:长矛, 6:斧,
	 *         7:魔杖, 8:飞刀, 9:箭头, 10:铁手甲, 11:钢爪,
	 *         12:双刀, 13:单手弓, 14:单手矛, 15:双手斧,
	 *         16:双手魔杖, 17:奇古兽 18:锁链剑
	 *         </p>
	 *         <p>
	 *         [装备]<br>
	 *         1:头盔, 2:盔甲, 3:T恤, 4:斗篷, 5:手套, 6:长靴, 7:盾牌,
	 *         8:项链, 9:戒指, 10:腰带, 11:戒指2, 12:耳环
	 */
	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	private int _type1; // ● 类型

	/**
	 * 返回该项目的种类。<br>
	 * 
	 * @return <p>
	 *         [weapon]<br>
	 *         sword:4, dagger:46, tohandsword:50, bow:20, blunt:11, spear:24,
	 *         staff:40, throwingknife:2922, arrow:66, gauntlet :62, claw:58,
	 *         edoryu:54, singlebow:20, singlespear:24, tohandblunt:11,
	 *         tohandstaff:40, kiringku:58, chainsword:24
	 *         </p>
	 */
	/**
	 * 返回该项目的种类。<br>
	 * 
	 * @return <p>
	 *         [武器]<br>
	 *         长剑:4, 短剑:46, 巨剑:50, 弓:20, 斧:11, 长矛:24,
	 *         魔杖:40, 飞刀:2922, 箭头:66, 铁手甲 :62, 钢爪:58,
	 *         双刀:54, 单手弓:20, 单手矛:24, 双手斧:11,
	 *         双手魔杖:40, 奇古兽:58, 锁链剑:24
	 *         </p>
	 */
	public int getType1() {
		return _type1;
	}

	public void setType1(int type1) {
		_type1 = type1;
	}

	private int _material; // ● 素材

	/**
	 * アイテムの素材を返す
	 * 
	 * @return 0:none 1:液体 2:web 3:植物性 4:动物性 5:纸 6:布 7:皮 8:木 9:骨 10:龙の鳞 11:铁
	 *         12:钢铁 13:铜 14:银 15:金 16:プラチナ 17:ミスリル 18:ブラックミスリル 19:ガラス 20:宝石
	 *         21:矿物 22:オリハルコン
	 */
	/**
	 * 返回材料项目
	 * 
	 * @return 0:无 1:液体 2:蛛丝 3:植物性 4:动物性 5:纸 6:布 7:皮 8:木 9:骨 10:龙鳞 11:铁
	 *         12:钢铁 13:铜 14:银 15:金 16:白金 17:米索莉 18:黑色米索莉 19:玻璃20:宝石
	 *         21:矿物 22:オリハルコン
	 */
	public int getMaterial() {
		return _material;
	}

	public void setMaterial(int material) {
		_material = material;
	}

	private int _weight; // ● 重量

	public int getWeight() {
		return _weight;
	}

	public void setWeight(int weight) {
		_weight = weight;
	}

	private int _gfxId; // ● 图像ＩＤ

	public int getGfxId() {
		return _gfxId;
	}

	public void setGfxId(int gfxId) {
		_gfxId = gfxId;
	}

	private int _groundGfxId; // ● 地面上的图像ＩＤ

	public int getGroundGfxId() {
		return _groundGfxId;
	}

	public void setGroundGfxId(int groundGfxId) {
		_groundGfxId = groundGfxId;
	}

	private int _minLevel; // ● 使用、装备可能最小ＬＶ

	private int _itemDescId;

	/**
	 * 鉴定时に表示されるItemDesc.tblのメッセージIDを返す。
	 */
	public int getItemDescId() {
		return _itemDescId;
	}

	public void setItemDescId(int descId) {
		_itemDescId = descId;
	}

	public int getMinLevel() {
		return _minLevel;
	}

	public void setMinLevel(int level) {
		_minLevel = level;
	}

	private int _maxLevel; // ● 使用、装备可能最大ＬＶ

	public int getMaxLevel() {
		return _maxLevel;
	}

	public void setMaxLevel(int maxlvl) {
		_maxLevel = maxlvl;
	}

	private int _bless; // ● 祝福状态

	public int getBless() {
		return _bless;
	}

	public void setBless(int i) {
		_bless = i;
	}

	private boolean _tradable; // ● トレード可／不可

	public boolean isTradable() {
		return _tradable;
	}

	public void setTradable(boolean flag) {
		_tradable = flag;
	}

	private boolean _cantDelete; // ● 削除不可

	public boolean isCantDelete() {
		return _cantDelete;
	}

	public void setCantDelete(boolean flag) {
		_cantDelete = flag;
	}

	private boolean _save_at_once;

	/**
	 * アイテムの个数が变化した际にすぐにDBに书き迂むべきかを返す。
	 */
	public boolean isToBeSavedAtOnce() {
		return _save_at_once;
	}

	public void setToBeSavedAtOnce(boolean flag) {
		_save_at_once = flag;
	}

	// ■■■■■■ L1EtcItem,L1Weapon に共通する项目 ■■■■■■

	private int _dmgSmall = 0; // ● 最小ダメージ

	public int getDmgSmall() {
		return _dmgSmall;
	}

	public void setDmgSmall(int dmgSmall) {
		_dmgSmall = dmgSmall;
	}

	private int _dmgLarge = 0; // ● 最大ダメージ

	public int getDmgLarge() {
		return _dmgLarge;
	}

	public void setDmgLarge(int dmgLarge) {
		_dmgLarge = dmgLarge;
	}

	// ■■■■■■ L1EtcItem,L1Armor に共通する项目 ■■■■■■

	// ■■■■■■ L1Weapon,L1Armor に共通する项目 ■■■■■■

	private int _safeEnchant = 0; // ● ＯＥ安全圏

	public int get_safeenchant() {
		return _safeEnchant;
	}

	public void set_safeenchant(int safeenchant) {
		_safeEnchant = safeenchant;
	}

	private boolean _useRoyal = false; // ● ロイヤルクラスが装备できるか

	public boolean isUseRoyal() {
		return _useRoyal;
	}

	public void setUseRoyal(boolean flag) {
		_useRoyal = flag;
	}

	private boolean _useKnight = false; // ● ナイトクラスが装备できるか

	public boolean isUseKnight() {
		return _useKnight;
	}

	public void setUseKnight(boolean flag) {
		_useKnight = flag;
	}

	private boolean _useElf = false; // ● エルフクラスが装备できるか

	public boolean isUseElf() {
		return _useElf;
	}

	public void setUseElf(boolean flag) {
		_useElf = flag;
	}

	private boolean _useMage = false; // ● メイジクラスが装备できるか

	public boolean isUseMage() {
		return _useMage;
	}

	public void setUseMage(boolean flag) {
		_useMage = flag;
	}

	private boolean _useDarkelf = false; // ● ダークエルフクラスが装备できるか

	public boolean isUseDarkelf() {
		return _useDarkelf;
	}

	public void setUseDarkelf(boolean flag) {
		_useDarkelf = flag;
	}

	private boolean _useDragonknight = false; // ● ドラゴンナイト装备できるか

	public boolean isUseDragonknight() {
		return _useDragonknight;
	}

	public void setUseDragonknight(boolean flag) {
		_useDragonknight = flag;
	}

	private boolean _useIllusionist = false; // ● イリュージョニスト装备できるか

	public boolean isUseIllusionist() {
		return _useIllusionist;
	}

	public void setUseIllusionist(boolean flag) {
		_useIllusionist = flag;
	}

	private byte _addstr = 0; // ● ＳＴＲ补正

	public byte get_addstr() {
		return _addstr;
	}

	public void set_addstr(byte addstr) {
		_addstr = addstr;
	}

	private byte _adddex = 0; // ● ＤＥＸ补正

	public byte get_adddex() {
		return _adddex;
	}

	public void set_adddex(byte adddex) {
		_adddex = adddex;
	}

	private byte _addcon = 0; // ● ＣＯＮ补正

	public byte get_addcon() {
		return _addcon;
	}

	public void set_addcon(byte addcon) {
		_addcon = addcon;
	}

	private byte _addint = 0; // ● ＩＮＴ补正

	public byte get_addint() {
		return _addint;
	}

	public void set_addint(byte addint) {
		_addint = addint;
	}

	private byte _addwis = 0; // ● ＷＩＳ补正

	public byte get_addwis() {
		return _addwis;
	}

	public void set_addwis(byte addwis) {
		_addwis = addwis;
	}

	private byte _addcha = 0; // ● ＣＨＡ补正

	public byte get_addcha() {
		return _addcha;
	}

	public void set_addcha(byte addcha) {
		_addcha = addcha;
	}

	private int _addhp = 0; // ● ＨＰ补正

	public int get_addhp() {
		return _addhp;
	}

	public void set_addhp(int addhp) {
		_addhp = addhp;
	}

	private int _addmp = 0; // ● ＭＰ补正

	public int get_addmp() {
		return _addmp;
	}

	public void set_addmp(int addmp) {
		_addmp = addmp;
	}

	private int _addhpr = 0; // ● ＨＰＲ补正

	public int get_addhpr() {
		return _addhpr;
	}

	public void set_addhpr(int addhpr) {
		_addhpr = addhpr;
	}

	private int _addmpr = 0; // ● ＭＰＲ补正

	public int get_addmpr() {
		return _addmpr;
	}

	public void set_addmpr(int addmpr) {
		_addmpr = addmpr;
	}

	private int _addsp = 0; // ● ＳＰ补正

	public int get_addsp() {
		return _addsp;
	}

	public void set_addsp(int addsp) {
		_addsp = addsp;
	}

	private int _mdef = 0; // ● ＭＲ

	public int get_mdef() {
		return _mdef;
	}

	public void set_mdef(int i) {
		this._mdef = i;
	}

	private boolean _isHasteItem = false; // ● ヘイスト效果の有无

	public boolean isHasteItem() {
		return _isHasteItem;
	}

	public void setHasteItem(boolean flag) {
		_isHasteItem = flag;
	}

	private int _maxUseTime = 0; // ● 使用可能な时间

	public int getMaxUseTime() {
		return _maxUseTime;
	}

	public void setMaxUseTime(int i) {
		_maxUseTime = i;
	}

	private int _useType;

	/**
	 * 使用したときのリアクションを决定するタイプを返す。
	 */
	public int getUseType() {
		return _useType;
	}

	public void setUseType(int useType) {
		_useType = useType;
	}

	private int _foodVolume;

	/**
	 * 肉などのアイテムに设定されている满腹度を返す。
	 */
	public int getFoodVolume() {
		return _foodVolume;
	}

	public void setFoodVolume(int volume) {
		_foodVolume = volume;
	}

	/**
	 * ランプなどのアイテムに设定されている明るさを返す。
	 */
	public int getLightRange() {
		if (_itemId == 40001) { // 灯
			return 11;
		} else if (_itemId == 40002) { // 灯笼
			return 14;
		} else if (_itemId == 40004) { // 魔法灯笼
			return 14;
		} else if (_itemId == 40005) { // 蜡烛
			return 8;
		} else {
			return 0;
		}
	}
	
	/**
	 * 道具天数删除系统
	 */
	private int _delete_day = 0; // ● 指定天数

	public int getDelete_Day() {
		return _delete_day;
	}

	public void setDelete_Day(int i) {
		_delete_day = i;
	}

	private Timestamp _delete_date; // ● 指定日期

	public Timestamp getDelete_Date() {
		return _delete_date;
	}

	public void setDelete_Date(Timestamp time) {
		_delete_date = time;
	}

	/**
	 * ランプなどの燃料の量を返す。
	 */
	public int getLightFuel() {
		if (_itemId == 40001) { // 灯
			return 6000;
		} else if (_itemId == 40002) { // 灯笼
			return 12000;
		} else if (_itemId == 40003) { // 灯油
			return 12000;
		} else if (_itemId == 40004) { // 魔法灯笼
			return 0;
		} else if (_itemId == 40005) { // 蜡烛
			return 600;
		} else {
			return 0;
		}
	}

	// ■■■■■■ L1EtcItem でオーバーライドする项目 ■■■■■■
	public boolean isStackable() {
		return false;
	}

	public int get_locx() {
		return 0;
	}

	public int get_locy() {
		return 0;
	}

	public short get_mapid() {
		return 0;
	}

	public int get_delayid() {
		return 0;
	}

	public int get_delaytime() {
		return 0;
	}

	public int getMaxChargeCount() {
		return 0;
	}

	public boolean isCanSeal() {
		return false;
	}

	// ■■■■■■ L1Weapon でオーバーライドする项目 ■■■■■■
	public int getRange() {
		return 0;
	}

	public int getHitModifier() {
		return 0;
	}

	public int getDmgModifier() {
		return 0;
	}

	public int getDoubleDmgChance() {
		return 0;
	}

	public int getMagicDmgModifier() {
		return 0;
	}

	public int get_canbedmg() {
		return 0;
	}

	public boolean isTwohandedWeapon() {
		return false;
	}

	// ■■■■■■ L1Armor でオーバーライドする项目 ■■■■■■
	public int get_ac() {
		return 0;
	}

	public int getDamageReduction() {
		return 0;
	}

	public int getWeightReduction() {
		return 0;
	}

	public int getHitModifierByArmor() {
		return 0;
	}

	public int getDmgModifierByArmor() {
		return 0;
	}

	public int getBowHitModifierByArmor() {
		return 0;
	}

	public int getBowDmgModifierByArmor() {
		return 0;
	}

	public int get_defense_water() {
		return 0;
	}

	public int get_defense_fire() {
		return 0;
	}

	public int get_defense_earth() {
		return 0;
	}

	public int get_defense_wind() {
		return 0;
	}

	public int get_regist_stun() {
		return 0;
	}

	public int get_regist_stone() {
		return 0;
	}

	public int get_regist_sleep() {
		return 0;
	}

	public int get_regist_freeze() {
		return 0;
	}

	public int get_regist_sustain() {
		return 0;
	}

	public int get_regist_blind() {
		return 0;
	}

	public int getGrade() {
		return 0;
	}

	// 装备武器变身 (开关) add
	private int _polySW;

	public void setpolySW(int i) {
		_polySW = i;
	}

	public int getpolySW() {
		return _polySW;
	}
	// 装备武器变身 (开关) end

	// 装备武器变身 (变身编号) add
	private int _POLYin;

	public void setPOLYin(int i) {
		_POLYin = i;
	}

	public int getPOLYin() {
		return _POLYin;
	}
	// 装备武器变身 (变身编号) end

	// sosodemon add 声望控制 BY SosoDEmoN
	private int _checkfamenameLevel;

	public int getCheckFameLevel() {
		return _checkfamenameLevel;
    }

	public void setCheckFameLevel(int i) {
		_checkfamenameLevel = i;
    }
	// sosodemon end 声望控制 BY SosoDEmoN

	// 打到特定物品广播 (DB化) add
	private int _DropBoard;

	public int DropBoard() {
		return _DropBoard;
	}

	public void setDropBoard(int i) {
		_DropBoard = i;
	}

	public int getDropBoard() {
		return _DropBoard;
	}
	// 打到特定物品广播 (DB化) end
}
