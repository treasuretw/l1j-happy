/*
MySQL Data Transfer
Source Host: localhost
Source Database: l1jdb_cn
Target Host: localhost
Target Database: l1jdb_cn
Date: 2011-6-25 ���� 12:53:54
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for magic_doll
-- ----------------------------
CREATE TABLE `magic_doll` (
  `item_id` int(10) unsigned NOT NULL,
  `note` varchar(45) DEFAULT '',
  `doll_id` int(10) NOT NULL,
  `ac` tinyint(2) NOT NULL DEFAULT '0',
  `hpr` tinyint(2) NOT NULL DEFAULT '0',
  `hpr_time` tinyint(2) NOT NULL DEFAULT '0',
  `mpr` tinyint(2) NOT NULL DEFAULT '0',
  `mpr_time` tinyint(2) NOT NULL DEFAULT '0',
  `hit` tinyint(2) NOT NULL DEFAULT '0',
  `dmg` tinyint(2) NOT NULL DEFAULT '0',
  `dmg_chance` tinyint(2) NOT NULL DEFAULT '0',
  `bow_hit` tinyint(2) NOT NULL DEFAULT '0',
  `bow_dmg` tinyint(2) NOT NULL DEFAULT '0',
  `dmg_reduction` tinyint(2) NOT NULL DEFAULT '0',
  `dmg_reduction_chance` tinyint(2) NOT NULL DEFAULT '0',
  `dmg_evasion_chance` tinyint(2) NOT NULL DEFAULT '0',
  `weight_reduction` tinyint(2) NOT NULL DEFAULT '0',
  `regist_stun` tinyint(2) NOT NULL DEFAULT '0',
  `regist_stone` tinyint(2) NOT NULL DEFAULT '0',
  `regist_sleep` tinyint(2) NOT NULL DEFAULT '0',
  `regist_freeze` tinyint(2) NOT NULL DEFAULT '0',
  `regist_sustain` tinyint(2) NOT NULL DEFAULT '0',
  `regist_blind` tinyint(2) NOT NULL DEFAULT '0',
  `make_itemid` int(10) NOT NULL DEFAULT '0',
  `effect` tinyint(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`item_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `magic_doll` VALUES ('41248', '魔法娃娃：肥肥', '80106', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '20', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `magic_doll` VALUES ('41249', '魔法娃娃：小思克巴', '80107', '0', '0', '0', '15', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `magic_doll` VALUES ('41250', '魔法娃娃：野狼宝宝', '80108', '0', '0', '0', '0', '0', '0', '15', '3', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `magic_doll` VALUES ('47105', '魔法娃娃：希尔黛斯', '92109', '0', '40', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `magic_doll` VALUES ('47106', '魔法娃娃：蛇女', '92103', '0', '0', '0', '4', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `magic_doll` VALUES ('47107', '魔法娃娃：雪怪', '92102', '-3', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '7', '0', '0', '0', '0');
INSERT INTO `magic_doll` VALUES ('47108', '魔法娃娃：亚力安', '92104', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `magic_doll` VALUES ('47109', '魔法娃娃：史巴托', '92106', '0', '0', '0', '0', '0', '0', '2', '0', '0', '0', '0', '0', '0', '0', '10', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `magic_doll` VALUES ('47110', '神秘棱镜：淘气幼龙', '92110', '0', '0', '0', '5', '0', '0', '0', '0', '0', '0', '0', '0', '0', '20', '0', '0', '0', '0', '0', '0', '47114', '0');
INSERT INTO `magic_doll` VALUES ('47111', '神秘棱镜：顽皮幼龙', '92111', '0', '0', '0', '5', '0', '0', '0', '0', '0', '0', '0', '0', '0', '20', '0', '0', '0', '0', '0', '0', '47114', '0');
INSERT INTO `magic_doll` VALUES ('47112', '神秘棱镜：高等淘气幼龙', '92112', '0', '0', '0', '5', '0', '0', '0', '0', '0', '0', '0', '0', '0', '20', '0', '0', '0', '0', '0', '0', '47114', '0');
INSERT INTO `magic_doll` VALUES ('47113', '神秘棱镜：高等顽皮幼龙', '92113', '0', '0', '0', '5', '0', '0', '0', '0', '0', '0', '0', '0', '0', '20', '0', '0', '0', '0', '0', '0', '47114', '0');
INSERT INTO `magic_doll` VALUES ('49037', '魔法娃娃：长老', '80129', '0', '0', '0', '15', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `magic_doll` VALUES ('49038', '魔法娃娃：奎斯坦修', '80130', '0', '0', '0', '0', '0', '0', '15', '3', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `magic_doll` VALUES ('49039', '魔法娃娃：石头高仑', '80131', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '15', '4', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
