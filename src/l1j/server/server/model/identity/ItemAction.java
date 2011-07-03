/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 *
 * http://www.gnu.org/copyleft/gpl.html
 */
package l1j.server.server.model.identity;

import static l1j.server.server.model.skill.L1SkillId.ABSOLUTE_BARRIER;

import java.util.concurrent.ConcurrentHashMap;

import l1j.server.server.datatables.NpcTable;
import l1j.server.server.datatables.PetTable;
import l1j.server.server.datatables.SkillsTable;
import l1j.server.server.model.Instance.L1ItemInstance;
import l1j.server.server.model.Instance.L1NpcInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.Instance.L1PetInstance;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.serverpackets.S_SkillList;
import l1j.server.server.templates.L1Npc;
import l1j.server.server.templates.L1Pet;
import l1j.server.server.templates.L1Skills;

public class ItemAction {
	private static ItemAction itemAct;

	public static ItemAction getAct() {
		if (itemAct == null) {
			itemAct = new ItemAction();
		}

		return itemAct;
	}

	private static ConcurrentHashMap<Integer, Byte> SpellBook;

	static {
		SpellBook = new ConcurrentHashMap<Integer, Byte>();

		/* 道具编号,				技能编号 */
		SpellBook.put(45000, (byte) 1);  /* 魔法书 (初级治愈术),	初级治愈术 */
		SpellBook.put(45001, (byte) 2);  /* 魔法书 (日光术),		日光术 */
		SpellBook.put(45002, (byte) 3);  /* 魔法书 (保护罩),		保护罩 */
		SpellBook.put(45003, (byte) 4);  /* 魔法书 (光箭),		光箭 */
		SpellBook.put(45004, (byte) 5);  /* 魔法书 (指定传送),		指定传送 */
		SpellBook.put(45005, (byte) 6);  /* 魔法书 (冰箭),		冰箭 */
		SpellBook.put(45006, (byte) 7);  /* 魔法书 (风刃),		风刃 */
		SpellBook.put(45007, (byte) 8);  /* 魔法书 (神圣武器),		神圣武器 */
		SpellBook.put(45008, (byte) 9);  /* 魔法书 (解毒术),		解毒术 */
		SpellBook.put(45009, (byte) 10); /* 魔法书 (寒冷战栗),		寒冷战栗 */
		SpellBook.put(45010, (byte) 11); /* 魔法书 (毒咒),		毒咒 */
		SpellBook.put(45011, (byte) 12); /* 魔法书 (拟似魔法武器),	拟似魔法武器 */
		SpellBook.put(45012, (byte) 13); /* 魔法书 (无所遁形术),	无所遁形术 */
		SpellBook.put(45013, (byte) 14); /* 魔法书 (负重强化),		负重强化 */
		SpellBook.put(45014, (byte) 15); /* 魔法书 (地狱之牙),		地狱之牙 */
		SpellBook.put(45015, (byte) 16); /* 魔法书 (火箭),		火箭 */
		SpellBook.put(45016, (byte) 17); /* 魔法书 (极光雷电),		极光雷电 */
		SpellBook.put(45021, (byte) 18); /* 魔法书 (起死回生术),	起死回生术 */
		SpellBook.put(45018, (byte) 19); /* 魔法书 (中级治愈术),	中级治愈术 */
		SpellBook.put(45019, (byte) 20); /* 魔法书 (闇盲咒术),		闇盲咒术 */
		SpellBook.put(45020, (byte) 21); /* 魔法书 (铠甲护持),		铠甲护持 */
		SpellBook.put(45017, (byte) 22); /* 魔法书 (寒冰气息),		寒冰气息 */
		SpellBook.put(45022, (byte) 23); /* 魔法书 (能量感测),		能量感测 */
		SpellBook.put(40170, (byte) 25); /* 魔法书 (燃烧的火球),	燃烧的火球 */
		SpellBook.put(40171, (byte) 26); /* 魔法书 (通畅气脉术),	通畅气脉术 */
		SpellBook.put(40172, (byte) 27); /* 魔法书 (坏物术),		坏物术 */
		SpellBook.put(40173, (byte) 28); /* 魔法书 (吸血鬼之吻),	吸血鬼之吻 */
		SpellBook.put(40174, (byte) 29); /* 魔法书 (缓速术),		缓速术 */
		SpellBook.put(40175, (byte) 31); /* 魔法书 (魔法屏障),		魔法屏障 */
		SpellBook.put(40176, (byte) 32); /* 魔法书 (冥想术),		冥想术 */
		SpellBook.put(40177, (byte) 30); /* 魔法书 (岩牢),		岩牢 */
		SpellBook.put(40178, (byte) 33); /* 魔法书 (木乃伊的诅咒),	木乃伊的诅咒 */
		SpellBook.put(40179, (byte) 34); /* 魔法书 (极道落雷),		极道落雷 */
		SpellBook.put(40180, (byte) 35); /* 魔法书 (高级治愈术),	高级治愈术 */
		SpellBook.put(40181, (byte) 36); /* 魔法书 (迷魅术),		迷魅术 */
		SpellBook.put(40182, (byte) 37); /* 魔法书 (圣洁之光),		圣洁之光 */
		SpellBook.put(40183, (byte) 38); /* 魔法书 (冰锥),		冰锥 */
		SpellBook.put(40184, (byte) 39); /* 魔法书 (魔力夺取),		魔力夺取 */
		SpellBook.put(40185, (byte) 40); /* 魔法书 (黑闇之影),		黑闇之影 */
		SpellBook.put(40186, (byte) 41); /* 魔法书 (造尸术),		造尸术 */
		SpellBook.put(40187, (byte) 42); /* 魔法书 (体魄强健术),	体魄强健术 */
		SpellBook.put(40188, (byte) 43); /* 魔法书 (加速术),		加速术 */
		SpellBook.put(40189, (byte) 44); /* 魔法书 (魔法相消术)),	魔法相消术 */
		SpellBook.put(40190, (byte) 45); /* 魔法书 (地裂术),		地裂术 */
		SpellBook.put(40191, (byte) 46); /* 魔法书 (烈炎术),		烈炎术 */
		SpellBook.put(40192, (byte) 47); /* 魔法书 (弱化术),		弱化术 */
		SpellBook.put(40193, (byte) 48); /* 魔法书 (祝福魔法武器),	祝福魔法武器 */
		SpellBook.put(40194, (byte) 49); /* 魔法书 (体力回覆术),	体力回覆术 */
		SpellBook.put(40195, (byte) 50); /* 魔法书 (冰矛围篱),		冰矛围篱 */
		SpellBook.put(40196, (byte) 51); /* 魔法书 (召唤术),		召唤术 */
		SpellBook.put(40197, (byte) 52); /* 魔法书 (神圣疾走),		神圣疾走 */
		SpellBook.put(40198, (byte) 53); /* 魔法书 (龙卷风),		龙卷风 */
		SpellBook.put(40199, (byte) 54); /* 魔法书 (强力加速术),	强力加速术 */
		SpellBook.put(40200, (byte) 55); /* 魔法书 (狂暴术),		狂暴术 */
		SpellBook.put(40201, (byte) 56); /* 魔法书 (疾病术),		疾病术 */
		SpellBook.put(40202, (byte) 57); /* 魔法书 (全部治愈术),	全部治愈术 */
		SpellBook.put(40203, (byte) 58); /* 魔法书 (火牢),		火牢 */
		SpellBook.put(40204, (byte) 59); /* 魔法书 (冰雪暴),		冰雪暴 */
		SpellBook.put(40205, (byte) 60); /* 魔法书 (隐身术),		隐身术 */
		SpellBook.put(40206, (byte) 61); /* 魔法书 (返生术),		返生术 */
		SpellBook.put(40207, (byte) 62); /* 魔法书 (震裂术),		震裂术 */
		SpellBook.put(40208, (byte) 63); /* 魔法书 (治愈能量风暴),	治愈能量风暴 */
		SpellBook.put(40209, (byte) 64); /* 魔法书 (魔法封印),		魔法封印 */
		SpellBook.put(40210, (byte) 65); /* 魔法书 (雷霆风暴),		雷霆风暴 */
		SpellBook.put(40211, (byte) 66); /* 魔法书 (沉睡之雾),		沉睡之雾 */
		SpellBook.put(40212, (byte) 67); /* 魔法书 (变形术),		变形术 */
		SpellBook.put(40213, (byte) 68); /* 魔法书 (圣结界),		圣结界 */
		SpellBook.put(40214, (byte) 69); /* 魔法书 (集体传送术),	集体传送术 */
		SpellBook.put(40215, (byte) 70); /* 魔法书 (火风暴),		火风暴 */
		SpellBook.put(40216, (byte) 71); /* 魔法书 (药水霜化术),	药水霜化术 */
		SpellBook.put(40217, (byte) 72); /* 魔法书 (强力无所遁形术),	强力无所遁形术 */
		SpellBook.put(40218, (byte) 73); /* 魔法书 (创造魔法武器),	创造魔法武器 */
		SpellBook.put(40219, (byte) 74); /* 魔法书 (流星雨),		流星雨 */
		SpellBook.put(40220, (byte) 75); /* 魔法书 (终极返生术),	终极返生术 */
		SpellBook.put(40221, (byte) 76); /* 魔法书 (集体缓速术),	集体缓速术 */
		SpellBook.put(40222, (byte) 77); /* 魔法书 (究极光裂术),	究极光裂术 */
		SpellBook.put(40223, (byte) 78); /* 魔法书 (绝对屏障),		绝对屏障 */
		SpellBook.put(40224, (byte) 79); /* 魔法书 (灵魂升华),		灵魂升华 */
		SpellBook.put(40225, (byte) 80); /* 魔法书 (冰雪飓风),		冰雪飓风 */

		SpellBook.put(40164, (byte) 87); /* 技术书(冲击之晕),		冲击之晕 */
		SpellBook.put(40165, (byte) 88); /* 技术书(增幅防御),		增幅防御 */
		SpellBook.put(40166, (byte) 89); /* 技术书(尖刺盔甲),		尖刺盔甲 */
		SpellBook.put(41147, (byte) 90); /* 技术书(坚固防护),		坚固防护 */
		SpellBook.put(41148, (byte) 91); /* 技术书(反击屏障),		反击屏障 */

		SpellBook.put(40265, (byte) 97);  /* 黑暗精灵水晶(暗隐术),		暗隐术 */
		SpellBook.put(40266, (byte) 98);  /* 黑暗精灵水晶(附加剧毒),	附加剧毒 */
		SpellBook.put(40267, (byte) 99);  /* 黑暗精灵水晶(影之防护),	影之防护 */
		SpellBook.put(40268, (byte) 100); /* 黑暗精灵水晶(提炼魔石),	提炼魔石 */
		SpellBook.put(40269, (byte) 109); /* 黑暗精灵水晶(力量提升),	力量提升 */
		SpellBook.put(40270, (byte) 101); /* 黑暗精灵水晶(行走加速),	行走加速 */
		SpellBook.put(40271, (byte) 102); /* 黑暗精灵水晶(燃烧斗志),	燃烧斗志 */
		SpellBook.put(40272, (byte) 103); /* 黑暗精灵水晶(暗黑盲咒),	暗黑盲咒 */
		SpellBook.put(40273, (byte) 104); /* 黑暗精灵水晶(毒性抵抗),	毒性抵抗 */
		SpellBook.put(40274, (byte) 110); /* 黑暗精灵水晶(敏捷提升),	敏捷提升 */
		SpellBook.put(40275, (byte) 105); /* 黑暗精灵水晶(双重破坏),	双重破坏 */
		SpellBook.put(40276, (byte) 106); /* 黑暗精灵水晶(暗影闪避),	暗影闪避 */
		SpellBook.put(40277, (byte) 107); /* 黑暗精灵水晶(暗影之牙),	暗影之牙 */
		SpellBook.put(40278, (byte) 108); /* 黑暗精灵水晶(会心一击),	会心一击 */
		SpellBook.put(40279, (byte) 111); /* 黑暗精灵水晶(闪避提升),	闪避提升 */

		SpellBook.put(40226, (byte) 113); /* 魔法书 (精准目标),	精准目标 */
		SpellBook.put(40227, (byte) 114); /* 魔法书 (激励士气),	激励士气 */
		SpellBook.put(40228, (byte) 116); /* 魔法书 (呼唤盟友),	呼唤盟友 */
		SpellBook.put(40229, (byte) 115); /* 魔法书(钢铁士气),	钢铁士气 */
		SpellBook.put(40230, (byte) 117); /* 魔法书(冲击士气),	冲击士气 */
		SpellBook.put(40231, (byte) 118); /* 魔法书(援护盟友),	援护盟友 */
		
		SpellBook.put(40232, (byte) 129); /* 精灵水晶(魔法防御),	魔法防御 */
		SpellBook.put(40233, (byte) 130); /* 精灵水晶(心灵转换),	心灵转换 */
		SpellBook.put(40234, (byte) 131); /* 精灵水晶(世界树的呼唤),	世界树的呼唤 */
		SpellBook.put(40235, (byte) 137); /* 精灵水晶(净化精神),	净化精神 */
		SpellBook.put(40236, (byte) 138); /* 精灵水晶(属性防御),	属性防御 */
		SpellBook.put(40237, (byte) 145); /* 精灵水晶(释放元素),	释放元素 */
		SpellBook.put(40238, (byte) 146); /* 精灵水晶(魂体转换),	魂体转换 */
		SpellBook.put(40239, (byte) 147); /* 精灵水晶(单属性防御),	单属性防御 */
		SpellBook.put(40240, (byte) 132); /* 精灵水晶(三重矢),	三重矢 */
		SpellBook.put(40241, (byte) 133); /* 精灵水晶(弱化属性),	弱化属性 */
		SpellBook.put(40242, (byte) 153); /* 精灵水晶(魔法消除),	魔法消除 */
		SpellBook.put(40243, (byte) 154); /* 精灵水晶(召唤属性精灵),	召唤属性精灵 */
		SpellBook.put(40244, (byte) 161); /* 精灵水晶(封印禁地),	封印禁地 */
		SpellBook.put(40245, (byte) 162); /* 精灵水晶(召唤强力属性精灵),	召唤强力属性精灵 */
		SpellBook.put(40246, (byte) 134); /* 精灵水晶(镜反射),	镜反射 */
		SpellBook.put(40247, (byte) 151); /* 精灵水晶(大地防护),	大地防护 */
		SpellBook.put(40248, (byte) 152); /* 精灵水晶(地面障碍),	地面障碍 */
		SpellBook.put(40249, (byte) 157); /* 精灵水晶(大地屏障),	大地屏障 */
		SpellBook.put(40250, (byte) 159); /* 精灵水晶(大地的祝福),	大地的祝福 */
		SpellBook.put(40251, (byte) 168); /* 精灵水晶(钢铁防护),	钢铁防护 */
		SpellBook.put(40252, (byte) 169); /* 精灵水晶(体能激发),	体能激发 */
		SpellBook.put(40253, (byte) 170); /* 精灵水晶(水之元气),	水之元气 */
		SpellBook.put(40254, (byte) 158); /* 精灵水晶(生命之泉),	生命之泉 */
		SpellBook.put(40255, (byte) 164); /* 精灵水晶(生命的祝福),	生命的祝福 */
		SpellBook.put(40256, (byte) 148); /* 精灵水晶(火焰武器),	火焰武器 */
		SpellBook.put(40257, (byte) 155); /* 精灵水晶(烈炎气息),	烈炎气息 */
		SpellBook.put(40258, (byte) 163); /* 精灵水晶(烈炎武器),	烈炎武器 */
		SpellBook.put(40259, (byte) 171); /* 精灵水晶(属性之火),	属性之火 */
		SpellBook.put(40260, (byte) 149); /* 精灵水晶(风之神射),	风之神射 */
		SpellBook.put(40261, (byte) 150); /* 精灵水晶(风之疾走),	风之疾走 */
		SpellBook.put(40262, (byte) 156); /* 精灵水晶(暴风之眼),	暴风之眼 */
		SpellBook.put(40263, (byte) 166); /* 精灵水晶(暴风神射),	暴风神射 */
		SpellBook.put(40264, (byte) 167); /* 精灵水晶(风之枷锁),	风之枷锁 */
		SpellBook.put(41149, (byte) 175); /* 精灵水晶(烈焰之魂),	烈焰之魂 */
		SpellBook.put(41150, (byte) 176); /* 精灵水晶(能量激发),	能量激发 */
		SpellBook.put(41151, (byte) 160); /* 精灵水晶(水之防护),	水之防护 */
		SpellBook.put(41152, (byte) 173); /* 精灵水晶(污浊之水),	污浊之水 */
		SpellBook.put(41153, (byte) 174); /* 精灵水晶(精准射击),	精准射击 */

		SpellBook.put(49102, (byte) 181); /* 龙骑士书板(龙之护铠),	龙之护铠 */
		SpellBook.put(49103, (byte) 182); /* 龙骑士书板(燃烧击砍),	燃烧击砍 */
		SpellBook.put(49104, (byte) 183); /* 龙骑士书板(护卫毁灭),	护卫毁灭 */
		SpellBook.put(49105, (byte) 184); /* 龙骑士书板(岩浆喷吐),	岩浆喷吐 */
		SpellBook.put(49106, (byte) 185); /* 龙骑士书板(觉醒：安塔瑞斯),	觉醒：安塔瑞斯 */
		SpellBook.put(49107, (byte) 186); /* 龙骑士书板(血之渴望),	血之渴望 */
		SpellBook.put(49108, (byte) 187); /* 龙骑士书板(屠宰者),	屠宰者 */
		SpellBook.put(49109, (byte) 188); /* 龙骑士书板(恐惧无助),	恐惧无助 */
		SpellBook.put(49110, (byte) 189); /* 龙骑士书板(冲击之肤),	冲击之肤 */
		SpellBook.put(49111, (byte) 190); /* 龙骑士书板(觉醒：法利昂),		觉醒：法利昂 */
		SpellBook.put(49112, (byte) 191); /* 龙骑士书板(致命身躯),	致命身躯 */
		SpellBook.put(49113, (byte) 192); /* 龙骑士书板(夺命之雷),	夺命之雷 */
		SpellBook.put(49114, (byte) 193); /* 龙骑士书板(惊悚死神),	惊悚死神 */
		SpellBook.put(49115, (byte) 194); /* 龙骑士书板(寒冰喷吐),	寒冰喷吐 */
		SpellBook.put(49116, (byte) 195); /* 龙骑士书板(觉醒：巴拉卡斯),	觉醒：巴拉卡斯术 */

		SpellBook.put(49117, (byte) 201); /* 记忆水晶(镜像),	镜像 */
		SpellBook.put(49118, (byte) 202); /* 记忆水晶(混乱),	混乱 */
		SpellBook.put(49119, (byte) 203); /* 记忆水晶(暴击),	暴击 */
		SpellBook.put(49120, (byte) 204); /* 记忆水晶(幻觉：欧吉),	幻觉：欧吉 */
		SpellBook.put(49121, (byte) 205); /* 记忆水晶(立方：燃烧),	立方：燃烧 */
		SpellBook.put(49122, (byte) 206); /* 记忆水晶(专注),		专注 */
		SpellBook.put(49123, (byte) 207); /* 记忆水晶(心灵破坏),	心灵破坏 */
		SpellBook.put(49124, (byte) 208); /* 记忆水晶(骷髅毁坏),	骷髅毁坏 */
		SpellBook.put(49125, (byte) 209); /* 记忆水晶(幻觉：巫妖),	幻觉：巫妖 */
		SpellBook.put(49126, (byte) 210); /* 记忆水晶(立方：地裂),	立方：地裂 */
		SpellBook.put(49127, (byte) 211); /* 记忆水晶(耐力),		耐力 */
		SpellBook.put(49128, (byte) 212); /* 记忆水晶(幻想),		幻想 */
		SpellBook.put(49129, (byte) 213); /* 记忆水晶(武器破坏者),	武器破坏者 */
		SpellBook.put(49130, (byte) 214); /* 记忆水晶(幻觉：钻石高仑),	幻觉：钻石高仑 */
		SpellBook.put(49131, (byte) 215); /* 记忆水晶(立方：冲击),	立方：冲击 */
		SpellBook.put(49132, (byte) 216); /* 记忆水晶(洞察),		洞察 */
		SpellBook.put(49133, (byte) 217); /* 记忆水晶(恐慌),		恐慌 */
		SpellBook.put(49134, (byte) 218); /* 记忆水晶(疼痛的欢愉),	疼痛的欢愉 */
		SpellBook.put(49135, (byte) 219); /* 记忆水晶(幻觉：化身),	幻觉：化身 */
		SpellBook.put(49136, (byte) 220); /* 记忆水晶(立方：和谐),	立方：和谐 */
	}

	public void SpellBook(L1PcInstance src, L1ItemInstance item) {
		Byte b = SpellBook.get(item.getItemId());

		// 判断数值是否为空
		if (b == null || item == null) {
			return; // 中断程序
		}

		int Skillid = b.intValue() & 0xFF;
		SkillsTable st = SkillsTable.getInstance();
		L1Skills skill = st.getTemplate(Skillid);

		// 判断魔法是否存在
		if (st.spellCheck(src.getId(), Skillid)) {
			return; // 中断程序
		}

		st.spellMastery(src.getId(), Skillid, skill.getName(), 0, 0); // 将魔法存入资料库
		src.sendPackets(new S_SkillList(true, skill)); // 将魔法插入魔法清单内
	}

	/** 解除绝对屏障效果 */
	public static void cancelAbsoluteBarrier(L1PcInstance pc) {
		if (pc.hasSkillEffect(ABSOLUTE_BARRIER)) {
			pc.killSkillEffectTimer(ABSOLUTE_BARRIER);
			pc.startHpRegeneration();
			pc.startHpRegenerationByDoll();
			pc.startMpRegeneration();
			pc.startMpRegenerationByDoll();
		}
	}

	public static boolean withDrawPet(L1PcInstance pc, int itemObjectId) {
		if (!pc.getMap().isTakePets()) {
			pc.sendPackets(new S_ServerMessage(563)); // \f1ここでは使えません。
			return false;
		}
		int petCost = 0;
		int petCount = 0;
		int divisor = 6;
		Object[] petList = pc.getPetList().values().toArray();
		for (Object pet : petList) {
			if (pet instanceof L1PetInstance) {
				if (((L1PetInstance) pet).getItemObjId() == itemObjectId) { // 既に引き出しているペット
					return false;
				}
			}
			petCost += ((L1NpcInstance) pet).getPetcost();
		}
		int charisma = pc.getCha();
		if (pc.isCrown()) { // 君主
			charisma += 6;
		} else if (pc.isElf()) { // エルフ
			charisma += 12;
		} else if (pc.isWizard()) { // WIZ
			charisma += 6;
		} else if (pc.isDarkelf()) { // DE
			charisma += 6;
		} else if (pc.isDragonKnight()) { // ドラゴンナイト
			charisma += 6;
		} else if (pc.isIllusionist()) { // イリュージョニスト
			charisma += 6;
		}
		//charisma -= petCost;
		/* 原始设定魅 / 6
		int petCount = charisma / 6;
		if (petCount <= 0) {
			pc.sendPackets(new S_ServerMessage(489)); // 引き取ろうとするペットが多すぎます。
			return false;
		}

		L1Pet l1pet = PetTable.getInstance().getTemplate(itemObjectId);
		if (l1pet != null) {
			L1Npc npcTemp = NpcTable.getInstance().getTemplate(
					l1pet.get_npcid());
			L1PetInstance pet = new L1PetInstance(npcTemp, pc, l1pet);
			pet.setPetcost(6);
		}
		*/
		L1Pet l1pet = PetTable.getInstance().getTemplate(itemObjectId);
		if (l1pet != null) {
			int npcId = l1pet.get_npcid();
			charisma -= petCost;
			if (npcId == 45313 || npcId == 45710 // タイガー、バトルタイガー
					|| npcId == 45711 || npcId == 45712) { // 纪州犬の子犬、纪州犬
				divisor = 12;
			} else {
				divisor = 6;
			}
			petCount = charisma / divisor;
			if (petCount <= 0) {
				pc.sendPackets(new S_ServerMessage(489)); // 引き取ろうとするペットが多すぎます。
				return false;
			}
			L1Npc npcTemp = NpcTable.getInstance().getTemplate(npcId);
			L1PetInstance pet = new L1PetInstance(npcTemp, pc, l1pet);
			pet.setPetcost(divisor);
		}

		return true;
	}

}
