/*
MySQL Data Transfer
Source Host: localhost
Source Database: l1jdb_cn
Target Host: localhost
Target Database: l1jdb_cn
Date: 2011-7-3 ���� 12:56:35
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for william_spawnlist_npc
-- ----------------------------
CREATE TABLE `william_spawnlist_npc` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `location` varchar(19) NOT NULL DEFAULT '',
  `count` int(10) unsigned NOT NULL DEFAULT '0',
  `npc_templateid` int(10) unsigned NOT NULL DEFAULT '0',
  `locx` int(10) unsigned NOT NULL DEFAULT '0',
  `locy` int(10) unsigned NOT NULL DEFAULT '0',
  `randomx` int(10) unsigned NOT NULL DEFAULT '0',
  `randomy` int(10) unsigned NOT NULL DEFAULT '0',
  `heading` int(10) unsigned NOT NULL DEFAULT '0',
  `respawn_delay` int(10) unsigned NOT NULL DEFAULT '0',
  `mapid` int(10) unsigned NOT NULL DEFAULT '0',
  `movement_distance` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=99057 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `william_spawnlist_npc` VALUES ('99011', '武器商人(天宝)', '1', '99000', '32778', '32873', '0', '0', '6', '0', '613', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99012', '吉利乌斯', '1', '50051', '32777', '32877', '0', '0', '6', '0', '613', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99013', '亚丁商团', '1', '70076', '32780', '32866', '0', '0', '5', '0', '613', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99014', '莱利的辅佐官', '1', '70652', '32759', '32865', '0', '0', '4', '0', '613', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99015', '雷奥纳', '1', '70674', '32761', '32865', '0', '0', '4', '0', '613', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99016', '库普', '1', '70904', '32763', '32865', '0', '0', '4', '0', '613', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99017', '研究员', '1', '80054', '32765', '32865', '0', '0', '4', '0', '613', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99018', '航海士的灵魂', '1', '80076', '32767', '32865', '0', '0', '4', '0', '613', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99019', '阿罗卡', '1', '80078', '32769', '32865', '0', '0', '4', '0', '613', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99020', '波伦', '1', '80084', '32771', '32865', '0', '0', '4', '0', '613', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99021', '拉库其', '1', '80090', '32773', '32865', '0', '0', '4', '0', '613', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99022', '拉庞斯', '1', '80091', '32775', '32865', '0', '0', '4', '0', '613', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99023', '亚丁王宫料理师', '1', '80127', '32770', '32859', '0', '0', '4', '0', '613', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99024', '徽章贩卖员', '1', '80128', '32772', '32860', '0', '0', '4', '0', '613', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99025', '铁匠 皮尔', '1', '80133', '32760', '32887', '0', '0', '0', '0', '613', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99026', '雷德', '1', '81112', '32762', '32887', '0', '0', '0', '0', '613', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99027', '哈古丁', '1', '81113', '32764', '32887', '0', '0', '0', '0', '613', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99028', '火焰之影的分身', '1', '81114', '32765', '32887', '0', '0', '0', '0', '613', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99029', '黑骑士副队长', '1', '81115', '32767', '32887', '0', '0', '0', '0', '613', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99030', '束缚的灵魂', '1', '81118', '32769', '32887', '0', '0', '0', '0', '613', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99031', '金侃的仆人', '1', '81120', '32771', '32887', '0', '0', '0', '0', '613', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99032', '强韧的海斯', '1', '81282', '32781', '32880', '0', '0', '6', '0', '613', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99033', '细心的修乐', '1', '81283', '32781', '32882', '0', '0', '6', '0', '613', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99034', '顽强的欧浩', '1', '81284', '32781', '32884', '0', '0', '6', '0', '613', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99035', '灿烂的艾咪', '1', '81285', '32781', '32886', '0', '0', '6', '0', '613', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99036', '米米', '1', '81286', '32781', '32888', '0', '0', '6', '0', '613', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99037', '宠物竞速管理人杜波', '1', '91002', '32775', '32859', '0', '0', '4', '0', '613', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99038', '天神祝福A', '1', '99990', '33454', '32796', '0', '0', '6', '0', '4', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99039', '龙幻魔法商人', '1', '99014', '33456', '32788', '0', '0', '4', '0', '4', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99040', '法师魔法商人', '1', '99015', '33454', '32786', '0', '0', '4', '0', '4', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99041', '精灵魔法商人', '1', '99016', '33452', '32784', '0', '0', '4', '0', '4', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99042', '王族魔法商人', '1', '99017', '33450', '32782', '0', '0', '4', '0', '4', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99043', '骑士魔法商人', '1', '99018', '33448', '32780', '0', '0', '4', '0', '4', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99044', '黑妖魔法商人', '1', '99019', '33446', '32778', '0', '0', '4', '0', '4', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99045', '传送魔法阵', '1', '99020', '33452', '32812', '0', '0', '2', '0', '4', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99046', 'BOSS馆', '1', '99993', '33426', '32803', '0', '0', '4', '0', '4', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99047', '\\f=双倍经验地图', '1', '99002', '33447', '32796', '0', '0', '0', '0', '4', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99048', '天堂点将官', '1', '99003', '33450', '32793', '0', '0', '4', '0', '4', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99049', '\\f2混沌精灵使者', '1', '99004', '33449', '32792', '0', '0', '6', '0', '4', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99050', '赌博商人', '1', '99005', '33440', '32810', '0', '0', '4', '0', '4', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99051', '\\f2猜数字', '1', '99006', '33437', '32810', '0', '0', '4', '0', '4', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99052', '鬼火', '1', '99007', '33435', '32820', '0', '0', '4', '0', '4', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99053', '消耗品商人', '1', '99010', '33449', '32817', '0', '0', '6', '0', '4', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99054', '传送卷商人', '1', '99013', '33449', '32819', '0', '0', '6', '0', '4', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99055', '武器商人', '1', '99011', '33449', '32825', '0', '0', '6', '0', '4', '0');
INSERT INTO `william_spawnlist_npc` VALUES ('99056', '装备商人', '1', '99012', '33449', '32827', '0', '0', '6', '0', '4', '0');
