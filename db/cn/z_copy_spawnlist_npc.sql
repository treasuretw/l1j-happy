/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50150
Source Host           : localhost:3306
Source Database       : happy-weixiugai

Target Server Type    : MYSQL
Target Server Version : 50150
File Encoding         : 65001

Date: 2011-07-20 20:24:42
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `z_copy_spawnlist_npc`
-- ----------------------------
DROP TABLE IF EXISTS `z_copy_spawnlist_npc`;
CREATE TABLE `z_copy_spawnlist_npc` (
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
-- Records of z_copy_spawnlist_npc
-- ----------------------------
INSERT INTO `z_copy_spawnlist_npc` VALUES ('99011', '武器商人(天宝)', '1', '99000', '32778', '32873', '0', '0', '6', '0', '613', '0');
INSERT INTO `z_copy_spawnlist_npc` VALUES ('99012', '吉利乌斯', '1', '50051', '32777', '32877', '0', '0', '6', '0', '613', '0');
INSERT INTO `z_copy_spawnlist_npc` VALUES ('99013', '亚丁商团', '1', '70076', '32780', '32866', '0', '0', '5', '0', '613', '0');
INSERT INTO `z_copy_spawnlist_npc` VALUES ('99014', '莱利的辅佐官', '1', '70652', '32759', '32865', '0', '0', '4', '0', '613', '0');
INSERT INTO `z_copy_spawnlist_npc` VALUES ('99015', '雷奥纳', '1', '70674', '32761', '32865', '0', '0', '4', '0', '613', '0');
INSERT INTO `z_copy_spawnlist_npc` VALUES ('99016', '库普', '1', '70904', '32763', '32865', '0', '0', '4', '0', '613', '0');
INSERT INTO `z_copy_spawnlist_npc` VALUES ('99017', '研究员', '1', '80054', '32765', '32865', '0', '0', '4', '0', '613', '0');
INSERT INTO `z_copy_spawnlist_npc` VALUES ('99018', '航海士的灵魂', '1', '80076', '32767', '32865', '0', '0', '4', '0', '613', '0');
INSERT INTO `z_copy_spawnlist_npc` VALUES ('99019', '阿罗卡', '1', '80078', '32769', '32865', '0', '0', '4', '0', '613', '0');
INSERT INTO `z_copy_spawnlist_npc` VALUES ('99020', '波伦', '1', '80084', '32771', '32865', '0', '0', '4', '0', '613', '0');
INSERT INTO `z_copy_spawnlist_npc` VALUES ('99021', '拉库其', '1', '80090', '32773', '32865', '0', '0', '4', '0', '613', '0');
INSERT INTO `z_copy_spawnlist_npc` VALUES ('99022', '拉庞斯', '1', '80091', '32775', '32865', '0', '0', '4', '0', '613', '0');
INSERT INTO `z_copy_spawnlist_npc` VALUES ('99023', '亚丁王宫料理师', '1', '80127', '32770', '32859', '0', '0', '4', '0', '613', '0');
INSERT INTO `z_copy_spawnlist_npc` VALUES ('99024', '徽章贩卖员', '1', '80128', '32772', '32860', '0', '0', '4', '0', '613', '0');
INSERT INTO `z_copy_spawnlist_npc` VALUES ('99025', '铁匠 皮尔', '1', '80133', '32760', '32887', '0', '0', '0', '0', '613', '0');
INSERT INTO `z_copy_spawnlist_npc` VALUES ('99026', '雷德', '1', '81112', '32762', '32887', '0', '0', '0', '0', '613', '0');
INSERT INTO `z_copy_spawnlist_npc` VALUES ('99027', '哈古丁', '1', '81113', '32764', '32887', '0', '0', '0', '0', '613', '0');
INSERT INTO `z_copy_spawnlist_npc` VALUES ('99028', '火焰之影的分身', '1', '81114', '32765', '32887', '0', '0', '0', '0', '613', '0');
INSERT INTO `z_copy_spawnlist_npc` VALUES ('99029', '黑骑士副队长', '1', '81115', '32767', '32887', '0', '0', '0', '0', '613', '0');
INSERT INTO `z_copy_spawnlist_npc` VALUES ('99030', '束缚的灵魂', '1', '81118', '32769', '32887', '0', '0', '0', '0', '613', '0');
INSERT INTO `z_copy_spawnlist_npc` VALUES ('99031', '金侃的仆人', '1', '81120', '32771', '32887', '0', '0', '0', '0', '613', '0');
INSERT INTO `z_copy_spawnlist_npc` VALUES ('99032', '强韧的海斯', '1', '81282', '32781', '32880', '0', '0', '6', '0', '613', '0');
INSERT INTO `z_copy_spawnlist_npc` VALUES ('99033', '细心的修乐', '1', '81283', '32781', '32882', '0', '0', '6', '0', '613', '0');
INSERT INTO `z_copy_spawnlist_npc` VALUES ('99034', '顽强的欧浩', '1', '81284', '32781', '32884', '0', '0', '6', '0', '613', '0');
INSERT INTO `z_copy_spawnlist_npc` VALUES ('99035', '灿烂的艾咪', '1', '81285', '32781', '32886', '0', '0', '6', '0', '613', '0');
INSERT INTO `z_copy_spawnlist_npc` VALUES ('99036', '米米', '1', '81286', '32781', '32888', '0', '0', '6', '0', '613', '0');
INSERT INTO `z_copy_spawnlist_npc` VALUES ('99037', '宠物竞速管理人杜波', '1', '91002', '32775', '32859', '0', '0', '4', '0', '613', '0');
INSERT INTO `z_copy_spawnlist_npc` VALUES ('99038', '天神祝福A', '1', '99990', '33436', '32806', '0', '0', '1', '0', '4', '0');
INSERT INTO `z_copy_spawnlist_npc` VALUES ('99053', '消耗品商人', '1', '99010', '33449', '32817', '0', '0', '6', '0', '4', '0');
INSERT INTO `z_copy_spawnlist_npc` VALUES ('99054', '传送卷商人', '1', '99013', '33449', '32819', '0', '0', '6', '0', '4', '0');
INSERT INTO `z_copy_spawnlist_npc` VALUES ('99055', '武器商人', '1', '99011', '33449', '32825', '0', '0', '6', '0', '4', '0');
INSERT INTO `z_copy_spawnlist_npc` VALUES ('99056', '装备商人', '1', '99012', '33449', '32827', '0', '0', '6', '0', '4', '0');
