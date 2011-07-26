/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50150
Source Host           : localhost:3306
Source Database       : l1j-cn

Target Server Type    : MYSQL
Target Server Version : 50150
File Encoding         : 65001

Date: 2011-07-26 12:20:54
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `william_item_summon`
-- ----------------------------
DROP TABLE IF EXISTS `william_item_summon`;
CREATE TABLE `william_item_summon` (
  `item_id` int(10) unsigned NOT NULL,
  `注解` varchar(45) DEFAULT NULL,
  `checkLevel` int(3) unsigned NOT NULL DEFAULT '0',
  `checkClass` int(2) unsigned NOT NULL DEFAULT '0',
  `checkItem` int(10) unsigned NOT NULL DEFAULT '0',
  `hpConsume` int(10) unsigned NOT NULL DEFAULT '0',
  `mpConsume` int(10) unsigned NOT NULL DEFAULT '0',
  `material` int(10) unsigned NOT NULL DEFAULT '0',
  `material_count` int(10) unsigned NOT NULL DEFAULT '0',
  `summon_id` int(10) unsigned NOT NULL,
  `summonCost` int(10) unsigned NOT NULL DEFAULT '0',
  `onlyOne` int(1) unsigned NOT NULL DEFAULT '0',
  `removeItem` int(1) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`item_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of william_item_summon
-- ----------------------------
INSERT INTO `william_item_summon` VALUES ('5083', '召唤死亡骑士', '0', '0', '0', '15', '15', '40308', '10000', '45601', '1', '0', '0');
