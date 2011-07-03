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

public class Opcodes {

	public Opcodes() {
	}
    /**
     * @3.3C Taiwan Server
     * <b>2010.12.22 Lin.bin
     */
	/**3.3C ClientPacket*/
	public static final int C_OPCODE_EXIT_GHOST = 0;//要求退出观看模式
	public static final int C_OPCODE_RETURNTOLOGIN = 1;//要求回到选人画面
	public static final int C_OPCODE_LOGINTOSERVER = 5;//要求进入游戏
	public static final int C_OPCODE_HIRESOLDIER = 7;//要求雇佣佣兵列表(购买)
	public static final int C_OPCODE_BOOKMARKDELETE = 8;//要求删除记忆座标
	public static final int C_OPCODE_DROPITEM = 10;//要求丢弃物品
	public static final int C_OPCODE_RESULT = 11;//要求列表物品取得 /3.3C领取宠物
	public static final int C_OPCODE_SELECTTARGET = 13;//要求攻击指定物件(宠物&召唤)
	public static final int C_OPCODE_COMMONCLICK = 14 ;//要求下一步 ( 公告资讯 )
	public static final int C_OPCODE_SETCASTLESECURITY = 15;//要求设置治安管理
	public static final int C_OPCODE_CLAN = 16;////要求血盟数据(例如盟标)
	public static final int C_OPCODE_FIX_WEAPON_LIST = 18;//要求维修物品清单
	public static final int C_OPCODE_USESKILL = 19;//要求使用技能
	public static final int C_OPCODE_TRADEADDCANCEL = 21;//要求取消交易(个人商店)
	public static final int C_OPCODE_CHANGEPASS = 22;//要求变更账号密码(登陆界面)
	public static final int C_OPCODE_DEPOSIT = 24;//要求存入资金
	public static final int C_OPCODE_TRADE = 25;//要求交易(个人)
	public static final int C_OPCODE_MOVE_CHECK = 26;//人物移动回硕检测 <= 3.3C added
	public static final int C_OPCODE_ENTERPORTAL = 27;//要求传送 (进入地监)
	public static final int C_OPCODE_DRAWAL = 28;//要求领出资金
	public static final int C_OPCODE_SECOND_PLEDGE = 31;//第二次要求查询血盟成员
	public static final int C_OPCODE_RANK = 31;//要求给予角色血盟阶级
	public static final int C_OPCODE_TRADEADDOK = 32;//要求完成交易(个人)
	public static final int C_OPCODE_PLEDGE = 33;//要求查询血盟成员
	public static final int C_OPCODE_QUITGAME = 35;//要求离开游戏
	public static final int C_OPCODE_BANCLAN = 36;//要求驱逐人物离开血盟
	public static final int C_OPCODE_WAREHOUSELOCK = 37;//要求变更仓库密码 && 送出仓库密码
	public static final int C_OPCODE_TITLE = 39;//要求赋予封号
	public static final int C_OPCODE_PICKUPITEM = 41;//要求捡取物品
	public static final int C_OPCODE_CHARRESET = 42;//要求重置人物点数
	public static final int C_OPCODE_NEWCHAR = 43;//要求创造角色 
	public static final int C_OPCODE_DOOR = 44;//要求开关门
	public static final int C_OPCODE_PETMENU = 45;//要求宠物回报选单
	public static final int C_OPCODE_CLIENTVERSION = 46; //要求登入测试 (接收伺服器版本)
	public static final int C_OPCODE_CREATECLAN = 48;//要求创立血盟
	public static final int C_OPCODE_CHANGECHAR = 50;//要求切换角色
	public static final int C_OPCODE_USEITEM = 51;//要求使用物品
	public static final int C_OPCODE_SKILLBUYOK = 52;//要求学习魔法 完成
	public static final int C_OPCODE_UNKOWN1 = 53;//用户端自动请求在线公告
	public static final int C_OPCODE_NPCTALK = 55;//要求物件对话视窗
	public static final int C_OPCODE_TELEPORT = 56;//要求传送 更新周围物件 (无动画传送后)
	public static final int C_OPCODE_SHIP = 58;//要求下船
	public static final int C_OPCODE_CHANGEWARTIME = 102;//修正城堡总管全部功能
	public static final int C_OPCODE_USEPETITEM = 60;//要求使用宠物装备
	public static final int C_OPCODE_SKILLBUY = 63;//要求学习魔法(金币)
	public static final int C_OPCODE_ADDBUDDY = 64;//要求新增好友
	public static final int C_OPCODE_BOARDWRITE = 65;//要求写入公布栏讯息
	public static final int C_OPCODE_BOARDNEXT = 66;//要求下一页 (公布栏)
	public static final int C_OPCODE_FISHCLICK = 67;//要求钓鱼收竿
	public static final int C_OPCODE_LEAVECLANE = 69;//要求脱离血盟
	public static final int C_OPCODE_LOGINTOSERVEROK = 70;//登入伺服器OK
	public static final int C_OPCODE_BUDDYLIST = 71;//要求查询朋友名单
	public static final int C_OPCODE_MOVECHAR = 73;//要求角色移动
	public static final int C_OPCODE_ATTR = 74;//要求点选项目的结果
	public static final int C_OPCODE_BOARDDELETE = 75;//要求删除公布栏内容
	public static final int C_OPCODE_DELEXCLUDE = 76;//要求使用开启名单(拒绝指定人物讯息)
	public static final int C_OPCODE_EXCLUDE = 76;//要求使用拒绝名单(开启指定人物讯息)
	public static final int C_OPCODE_CHATGLOBAL = 77;//要求使用广播聊天频道
	public static final int C_OPCODE_PROPOSE = 78;//要求结婚
	public static final int C_OPCODE_TRADEADDITEM = 79;//要求交易(添加物品)
	public static final int C_OPCODE_CASTLESECURITY = 81;//要求治安管理  OK
	public static final int C_OPCODE_SHOP = 82;//要求开个人商店
	public static final int C_OPCODE_CHAT = 83;//要求使用一般聊天频道
	public static final int C_OPCODE_PUTSOLDIER = 84;//要求配置已雇用士兵
	public static final int C_OPCODE_LEAVEPARTY = 85;//要求脱离队伍
	public static final int C_OPCODE_PARTYLIST = 86;//要求查看队伍
	public static final int C_OPCODE_SENDLOCATION = 87; // 要求传送位置(3.3C 新增)
	public static final int C_OPCODE_BOARDREAD = 88;//要求阅读布告单个栏讯息
	public static final int C_OPCODE_CALL = 89;//要求召唤到身边(gm)
	public static final int C_OPCODE_WAR = 91;//要求宣战
	public static final int C_OPCODE_CHECKPK = 92;//要求查询PK次数
	public static final int C_OPCODE_CHANGEHEADING = 93;//要求改变角色面向
	public static final int C_OPCODE_AMOUNT = 94;//要求确定数量选取
	public static final int C_OPCODE_WHO = 95;//要求查询游戏人数
	public static final int C_OPCODE_FIGHT = 96;//要求决斗
	public static final int C_OPCODE_NPCACTION = 97;//要求物件对话视窗结果
	public static final int C_OPCODE_CHARACTERCONFIG = 100;//要求纪录快速键
	public static final int C_OPCODE_ATTACK = 101;//要求角色攻击
	public static final int C_OPCODE_SELECTWARTIME = 102;//要求选择 变更攻城时间(but3.3C无使用)
	public static final int C_OPCODE_BOARD = 103;//要求读取公布栏
	public static final int C_OPCODE_PRIVATESHOPLIST = 104;//要求个人商店 （物品列表）
	public static final int C_OPCODE_LOGINPACKET = 105;//要求登入伺服器
	public static final int C_OPCODE_SELECTLIST = 106;//要求物品维修
	public static final int C_OPCODE_MAIL = 107;//要求打开邮箱
	public static final int C_OPCODE_EXTCOMMAND = 108;//要求角色表情动作
	public static final int C_OPCODE_DELETECHAR = 110;//要求删除角色
	public static final int C_OPCODE_DELBUDDY = 112;//要求删除好友
	public static final int C_OPCODE_ARROWATTACK = 113;//要求使用远距武器
	public static final int C_OPCODE_EMBLEM = 114;//要求上传盟标
	public static final int C_OPCODE_BANPARTY = 115;//要求踢出队伍
	public static final int C_OPCODE_CHATWHISPER = 116;//要求使用密语聊天频道
	public static final int C_OPCODE_SMS = 117;//要求寄送简讯
	public static final int C_OPCODE_PUTHIRESOLDIER = 118;//要求配置已雇佣佣兵 OK
	public static final int C_OPCODE_BOOKMARK = 119;//要求增加记忆座标
	public static final int C_OPCODE_PUTBOWSOLDIER = 120;//要求配置城墙上弓手
	public static final int C_OPCODE_KEEPALIVE = 121;//要求更新时间
	public static final int C_OPCODE_TAXRATE = 122;//要求税收设定封包
	public static final int C_OPCODE_GIVEITEM = 124;//要求给予物品
	public static final int C_OPCODE_JOINCLAN = 125;//要求加入血盟
	public static final int C_OPCODE_DELETEINVENTORYITEM = 126;//要求删除物品
	public static final int C_OPCODE_RESTART = 127;//要求死亡后重新开始
	public static final int C_OPCODE_CREATEPARTY = 130;//要求邀请加入队伍(要求创立队伍)
	public static final int C_OPCODE_CAHTPARTY = 131;//要求人物移出队伍

	/**3.2C ServerPacket*/
	public static final int S_OPCODE_COMMONNEWS2 = 0;
	public static final int S_OPCODE_USEMAP = 71;
	public static final int S_LETTER = 90;

	/**3.3C ServerPacket*/
	public static final int S_OPCODE_BLUEMESSAGE = 0;//蓝色讯息 (使用String-h.tbl)
	public static final int S_OPCODE_BLESSOFEVA = 1;//效果图示 (水底呼吸)
	public static final int S_OPCODE_NPCSHOUT = 3;//非玩家聊天频道 (一般 & 大喊 )NPC
	public static final int S_OPCODE_RESURRECTION = 4;//物件复活
	public static final int S_OPCODE_BOARDREAD = 5;//布告栏(讯息阅读)
	public static final int S_OPCODE_CASTLEMASTER = 6;//角色皇冠 3.3C
	public static final int S_OPCODE_FIX_WEAPON_MENU = 7;//修理武器清单 (3.3C新增)
	public static final int S_OPCODE_SELECTLIST = 7;//损坏武器名单
	public static final int S_OPCODE_ADDSKILL = 8;//增加魔法进魔法名单
	public static final int S_OPCODE_CHARVISUALUPDATE = 9;//物件动作种类 (长时间)
	public static final int S_OPCODE_COMMONNEWS = 10;//公告视窗
	public static final int S_OPCODE_CHARAMOUNT = 11;//角色列表
	public static final int S_OPCODE_PARALYSIS = 12;//魔法效果 : 诅咒类 {编号 }麻痹,瘫痪
	public static final int S_OPCODE_REDMESSAGE = 13; //画面正中出现红色/新增未使用
	public static final int S_OPCODE_INPUTAMOUNT = 14;//拍卖公告栏选取金币数量 (选取物品数量)
	public static final int S_OPCODE_SKILLSOUNDGFX = 15;//产生动画 [物件]
	public static final int S_OPCODE_IDENTIFYDESC = 16;//物品资讯讯息 {使用String-h.tbl}
	public static final int S_OPCODE_EFFECTLOCATION = 18;//产生动画 [地点]
	public static final int S_OPCODE_MAIL = 19;//邮件封包
	public static final int S_OPCODE_SHOWRETRIEVELIST = 21;//仓库物品名单
	public static final int S_OPCODE_HOUSELIST = 22;//血盟小屋名单
	public static final int S_OPCODE_SKILLBUY = 23;//魔法购买 (金币)
	public static final int S_OPCODE_GLOBALCHAT = 24;//广播聊天频道
	public static final int S_OPCODE_SYSMSG = 24;//广播聊天频道/伺服器讯息(字串)
	public static final int S_OPCODE_CURSEBLIND = 25;//魔法效果 - 暗盲咒术 {编号}
	public static final int S_OPCODE_INVLIST = 26;//道具栏全物品
	public static final int S_OPCODE_CHARPACK = 27;//物件封包
	public static final int S_OPCODE_DROPITEM = 27;//丢弃物品封包
	public static final int S_OPCODE_SERVERMSG = 29;//伺服器讯息 (行数)/(行数, 附加字串 )
	public static final int S_OPCODE_NEWCHARPACK = 31;//创造角色封包
	public static final int S_OPCODE_DELSKILL = 34;//移除魔法出魔法名单
	public static final int S_OPCODE_LOGINTOGAME = 35;//进入游戏
	public static final int S_OPCODE_WHISPERCHAT = 36;//要求使用密语聊天频道
	public static final int S_OPCODE_DRAWAL = 37;//取出城堡宝库金币
	public static final int S_OPCODE_CHARLIST = 38;//角色资讯
	public static final int S_OPCODE_EMBLEM = 39;//角色盟徽
	public static final int S_OPCODE_ATTACKPACKET = 40;//物件攻击
	public static final int S_OPCODE_SPMR = 42;//魔法攻击力与魔法防御力
	public static final int S_OPCODE_OWNCHARSTATUS = 43;//角色属性与能力值
	public static final int S_OPCODE_RANGESKILLS = 44;//范围魔法
	public static final int S_OPCODE_SHOWSHOPSELLLIST = 45;//NPC物品贩卖
	public static final int S_OPCODE_INVIS = 47;//物件隐形 & 现形
	public static final int S_OPCODE_NORMALCHAT = 48;//一般聊天频道
	public static final int S_OPCODE_SKILLHASTE = 49;//魔法|物品效果 {加速颣}
	public static final int S_OPCODE_TAXRATE = 50;//税收设定封包
	public static final int S_OPCODE_WEATHER = 51;//游戏天气
	public static final int S_OPCODE_HIRESOLDIER = 52;//雇佣佣兵 佣兵名单
	public static final int S_OPCODE_WAR = 53;//血盟战争讯息 {编号,血盟名称,目标血盟名称}
	public static final int S_OPCODE_TELEPORTLOCK = 54;//人物回硕检测  OR 传送锁定 (无动画)
	public static final int S_OPCODE_PINKNAME = 55;//角色名称变紫色
	public static final int S_OPCODE_ITEMSTATUS = 56;//物品状态更新
	public static final int S_OPCODE_ITEMAMOUNT = 56;//物品可用次数
	public static final int S_OPCODE_PRIVATESHOPLIST = 57;//角色个人商店 {购买}
	public static final int S_OPCODE_DETELECHAROK = 58;//角色移除 [非立即]/7天后
	public static final int S_OPCODE_BOOKMARKS = 59;//角色座标名单
	public static final int S_OPCODE_INITPACKET = 60;//初始化OpCodes
	public static final int S_OPCODE_MOVEOBJECT = 62;//物件移动
	public static final int S_OPCODE_PUTSOLDIER = 63;//配置已雇用士兵
	public static final int S_OPCODE_TELEPORT = 64;//要求传送 (有动画)
	public static final int S_OPCODE_STRUP = 65;//力量提升封包
	public static final int S_OPCODE_LAWFUL = 66;//正义值更新
	public static final int S_OPCODE_SELECTTARGET = 67;//选择一个目标
	public static final int S_OPCODE_ABILITY = 68;//戒指
	public static final int S_OPCODE_HPMETER = 69;//物件血条
	public static final int S_OPCODE_ATTRIBUTE = 70;//物件属性
	public static final int S_OPCODE_SERVERVERSION = 72;//伺服器版本
	public static final int S_OPCODE_EXP = 73;//经验值更新封包
	public static final int S_OPCODE_MPUPDATE = 74;//魔力更新
	public static final int S_OPCODE_CHANGENAME = 75;//改变物件名称
	public static final int S_OPCODE_POLY = 76;//改变外型
	public static final int S_OPCODE_MAPID = 77;//更新角色所在的地图
	public static final int S_OPCODE_ITEMCOLOR = 79;//物品状态
	public static final int S_OPCODE_OWNCHARATTRDEF = 80;//角色防御 & 属性 更新
	public static final int S_OPCODE_PACKETBOX = 82;//角色选择视窗/开启拒绝名单(封包盒子)
	public static final int S_OPCODE_ACTIVESPELLS = 82;
	public static final int S_OPCODE_SKILLICONGFX = 82;
	public static final int S_OPCODE_UNKNOWN2 = 82;
	public static final int S_OPCODE_DELETEINVENTORYITEM = 83;//物品删除
	public static final int S_OPCODE_RESTART = 84;//角色重新选择 返回选人画面 功能未知
	public static final int S_OPCODE_PINGTIME = 85;//Ping Time
	public static final int S_OPCODE_DEPOSIT = 86;//存入资金城堡宝库 (2)
	public static final int S_OPCODE_TRUETARGET = 88;//魔法动画 {精准目标}
	public static final int S_OPCODE_HOUSEMAP = 89;//血盟小屋地图 [地点]
	public static final int S_OPCODE_CHARTITLE = 90;//角色封号
	public static final int S_OPCODE_DEXUP = 92;//敏捷提升封包
	public static final int S_OPCODE_CHANGEHEADING = 94;//物件面向
	public static final int S_OPCODE_BOARD = 96;//布告栏 (讯息列表)
	public static final int S_OPCODE_LIQUOR = 97;//海底波纹
	public static final int S_OPCODE_TRADESTATUS = 99;//交易状态
	public static final int S_OPCODE_SPOLY = 100;//特别变身封包
	public static final int S_OPCODE_UNDERWATER = 101;//更新角色所在的地图 （水下）
	public static final int S_OPCODE_SKILLBRAVE = 102;//魔法|物品效果图示 {勇敢药水类}
	public static final int S_OPCODE_PUTHIRESOLDIER = 103;//配置佣兵
	public static final int S_OPCODE_POISON = 104 ;//魔法效果:中毒 { 编号 }
	public static final int S_OPCODE_DISCONNECT = 105;//立即中断连线
	public static final int S_OPCODE_NEWCHARWRONG = 106;//角色创造失败
	public static final int S_OPCODE_REMOVE_OBJECT = 107;//物件删除
	public static final int S_OPCODE_NPC_ATTACKPACKET = 108;//NPC攻击 用于特殊攻击?!
	public static final int S_OPCODE_ADDITEM = 110;//物品增加封包
	public static final int S_OPCODE_TRADE = 111;//交易封包
	public static final int S_OPCODE_OWNCHARSTATUS2 = 112;//角色状态 (2)
	public static final int S_OPCODE_SHOWHTML = 113;//产生对话视窗
	public static final int S_OPCODE_SKILLICONSHIELD = 114;//魔法效果 : 防御颣
	public static final int S_OPCODE_DOACTIONGFX = 115;//物件动作种类 (短时间)
	public static final int S_OPCODE_TRADEADDITEM = 116;//增加交易物品封包
	public static final int S_OPCODE_YES_NO = 117;//选项封包 {Yes || No}
	public static final int S_OPCODE_HPUPDATE = 118;//体力更新
	public static final int S_OPCODE_SHOWSHOPBUYLIST = 119;//物品购买
	public static final int S_OPCODE_GAMETIME = 120;//更新目前游戏时间 ( 游戏时间 )
	public static final int S_OPCODE_PETCTRL = 121;//宠物控制介面移除
	public static final int S_OPCODE_CHARRESET = 121; //角色重置
	public static final int S_OPCODE_SOUND = 122;//拨放音效
	public static final int S_OPCODE_LIGHT = 123;//物件亮度
	public static final int S_OPCODE_LOGINRESULT = 124;//登入状态
	public static final int S_OPCODE_PUTBOWSOLDIERLIST = 125;//配置墙上弓手
	public static final int S_OPCODE_WARTIME = 126;//攻城时间设定
	public static final int S_OPCODE_ITEMNAME = 127;//物品显示名称


	/**额外区块(不确定||未使用)*/
    //public static final int S_OPCODE_xxxx = 33; // 似乎是显示被驯服宠物的名称 // ok
    //public static final int S_OPCODE_UNKNOWN1= 35;//0000: 01 03 xx xx xx..

}
