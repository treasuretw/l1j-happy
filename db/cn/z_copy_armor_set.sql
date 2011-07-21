/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50150
Source Host           : localhost:3306
Source Database       : l1j-cn

Target Server Type    : MYSQL
Target Server Version : 50150
File Encoding         : 65001

Date: 2011-07-21 22:11:46
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `z_copy_armor_set`
-- ----------------------------
DROP TABLE IF EXISTS `z_copy_armor_set`;
CREATE TABLE `z_copy_armor_set` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `note` varchar(45) DEFAULT NULL,
  `sets` varchar(1000) NOT NULL,
  `polyid` int(10) NOT NULL DEFAULT '0',
  `ac` int(2) NOT NULL DEFAULT '0',
  `hp` int(5) NOT NULL DEFAULT '0',
  `mp` int(5) NOT NULL DEFAULT '0',
  `hpr` int(5) NOT NULL DEFAULT '0',
  `mpr` int(5) NOT NULL DEFAULT '0',
  `mr` int(5) NOT NULL DEFAULT '0',
  `str` int(2) NOT NULL DEFAULT '0',
  `dex` int(2) NOT NULL DEFAULT '0',
  `con` int(2) NOT NULL DEFAULT '0',
  `wis` int(2) NOT NULL DEFAULT '0',
  `cha` int(2) NOT NULL DEFAULT '0',
  `intl` int(2) NOT NULL DEFAULT '0',
  `hit_modifier` int(2) NOT NULL DEFAULT '0',
  `dmg_modifier` int(2) NOT NULL DEFAULT '0',
  `bow_hit_modifier` int(2) NOT NULL DEFAULT '0',
  `bow_dmg_modifier` int(2) NOT NULL DEFAULT '0',
  `sp` int(2) NOT NULL,
  `defense_water` int(2) NOT NULL DEFAULT '0',
  `defense_wind` int(2) NOT NULL DEFAULT '0',
  `defense_fire` int(2) NOT NULL DEFAULT '0',
  `defense_earth` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=69 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of z_copy_armor_set
-- ----------------------------
INSERT INTO `z_copy_armor_set` VALUES ('64', '守护团戒指', '20286', '1080', '-128', '0', '0', '0', '0', '0', '127', '127', '127', '127', '127', '127', '100', '100', '100', '100', '127', '100', '100', '100', '100');
INSERT INTO `z_copy_armor_set` VALUES ('65', '新手套装', '10000,10001,10002,10003,10004,10005,10006,10007,10008,10009,10010', '240', '-40', '100', '50', '10', '10', '10', '1', '1', '1', '1', '1', '1', '0', '0', '0', '0', '1', '0', '0', '0', '0');
INSERT INTO `z_copy_armor_set` VALUES ('66', '11周年白色套装', '10073,10074', '-1', '0', '0', '33', '0', '2', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0');
INSERT INTO `z_copy_armor_set` VALUES ('67', '11周年黑色套装', '10073,10075', '-1', '0', '55', '0', '5', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_armor_set` VALUES ('68', '11周年红色套装', '10073,10076', '-1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '2', '2', '2', '2', '0', '0', '0', '0', '0');
