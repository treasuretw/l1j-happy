/*
MySQL Data Transfer
Source Host: localhost
Source Database: l1jdb_cn
Target Host: localhost
Target Database: l1jdb_cn
Date: 2011-6-25 ���� 12:59:21
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for ub_settings
-- ----------------------------
CREATE TABLE `ub_settings` (
  `ub_id` int(10) unsigned NOT NULL DEFAULT '0',
  `ub_name` varchar(45) NOT NULL DEFAULT '',
  `ub_mapid` int(10) unsigned NOT NULL DEFAULT '0',
  `ub_area_x1` int(10) unsigned NOT NULL DEFAULT '0',
  `ub_area_y1` int(10) unsigned NOT NULL DEFAULT '0',
  `ub_area_x2` int(10) unsigned NOT NULL DEFAULT '0',
  `ub_area_y2` int(10) unsigned NOT NULL DEFAULT '0',
  `min_lvl` int(10) unsigned NOT NULL DEFAULT '0',
  `max_lvl` int(10) unsigned NOT NULL DEFAULT '0',
  `max_player` int(10) unsigned NOT NULL DEFAULT '0',
  `enter_royal` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `enter_knight` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `enter_mage` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `enter_elf` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `enter_darkelf` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `enter_dragonknight` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `enter_illusionist` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `enter_male` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `enter_female` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `use_pot` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `hpr_bonus` int(10) NOT NULL DEFAULT '0',
  `mpr_bonus` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ub_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `ub_settings` VALUES ('1', '奇岩竞技场', '88', '33494', '32724', '33516', '32746', '52', '99', '20', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0');
INSERT INTO `ub_settings` VALUES ('2', '威顿竞技场', '98', '32682', '32878', '32717', '32913', '45', '60', '20', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0');
INSERT INTO `ub_settings` VALUES ('3', '古鲁丁竞技场', '92', '32682', '32878', '32717', '32913', '31', '51', '20', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0');
INSERT INTO `ub_settings` VALUES ('4', '说话之岛竞技场', '91', '32682', '32878', '32717', '32913', '25', '44', '20', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0');
INSERT INTO `ub_settings` VALUES ('5', '银骑士竞技场', '95', '32682', '32878', '32717', '32913', '1', '30', '20', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0');
