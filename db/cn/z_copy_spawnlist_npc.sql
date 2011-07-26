/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50150
Source Host           : localhost:3306
Source Database       : l1jdb_cn

Target Server Type    : MYSQL
Target Server Version : 50150
File Encoding         : 65001

Date: 2011-07-26 19:29:18
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
) ENGINE=MyISAM AUTO_INCREMENT=99067 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of z_copy_spawnlist_npc
-- ----------------------------
INSERT INTO `z_copy_spawnlist_npc` VALUES ('99038', '天神祝福A', '1', '99990', '33436', '32806', '0', '0', '1', '0', '4', '0');
INSERT INTO `z_copy_spawnlist_npc` VALUES ('99039', '天宝商人(武器)', '1', '99000', '33448', '32816', '0', '0', '6', '0', '4', '0');
INSERT INTO `z_copy_spawnlist_npc` VALUES ('99040', '传送卷商人', '1', '99013', '33448', '32814', '0', '0', '6', '0', '4', '0');
INSERT INTO `z_copy_spawnlist_npc` VALUES ('99041', '消耗品商人', '1', '99010', '33448', '32812', '0', '0', '6', '0', '4', '0');
