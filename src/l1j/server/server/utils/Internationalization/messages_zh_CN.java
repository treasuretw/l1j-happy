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
package l1j.server.server.utils.Internationalization;

import java.util.ListResourceBundle;

/**
 * @category 中国-简体中文<br>
 *           国际化的英文是Internationalization 因为单字中总共有18个字母，简称I18N，
 *           目的是让应用程式可以应地区不同而显示不同的讯息。
 */
public class messages_zh_CN extends ListResourceBundle {
	static final Object[][] contents = { 
		{ "l1j.server.memoryUse", "使用内存: " },
		{ "l1j.server.memory", "MB" },
		{ "l1j.server.server.model.onGroundItem", "地上的物品" },
		{ "l1j.server.server.model.seconds", "10秒后将被清除" },
		{ "l1j.server.server.model.deleted", "已经被清除了" },
		{ "l1j.server.server.GameServer.ver","版本: Lineage 3.3C  开发 By L1J-TW For All User" },
		{ "l1j.server.server.GameServer.settingslist","●●●●〈伺服器设置清单〉●●●●"},
		{ "l1j.server.server.GameServer.exp","「经验值」"},
		{ "l1j.server.server.GameServer.x","【倍】"},
		{ "l1j.server.server.GameServer.level","【级】"},
		{ "l1j.server.server.GameServer.justice","「正义值」"},
		{ "l1j.server.server.GameServer.karma","「友好度」"},
		{ "l1j.server.server.GameServer.dropitems","「物品掉落」"},
		{ "l1j.server.server.GameServer.dropadena","「金币掉落」"},
		{ "l1j.server.server.GameServer.enchantweapon","「冲武」"},
		{ "l1j.server.server.GameServer.enchantarmor","「冲防」"},
		{ "l1j.server.server.GameServer.chatlevel","「广播频道可用等级」"},
		{ "l1j.server.server.GameServer.nonpvp1","「杀人设定」: 【无效 (PvP可以)】"},
		{ "l1j.server.server.GameServer.nonpvp2","「杀人设定」: 【有效 (PvP不可)】"},
		{ "l1j.server.server.GameServer.maxplayer","连线人数上限为 "},
		{ "l1j.server.server.GameServer.player"," 人 "},
		{ "l1j.server.server.GameServer.waitingforuser","等待客户端连接中..."},
		{ "l1j.server.server.GameServer.from","尝试连线的IP： "},
		{ "l1j.server.server.GameServer.attempt",""},
		{ "l1j.server.server.GameServer.setporton","伺服器成功建立 端口："},
		{ "l1j.server.server.GameServer.initialfinished","服务器启动完成!!"}};

	@Override
	protected Object[][] getContents() {
		return contents;
	}

}
