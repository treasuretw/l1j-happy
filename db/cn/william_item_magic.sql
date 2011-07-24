/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50150
Source Host           : localhost:3306
Source Database       : l1j-cn

Target Server Type    : MYSQL
Target Server Version : 50150
File Encoding         : 65001

Date: 2011-07-24 22:35:34
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `william_item_magic`
-- ----------------------------
DROP TABLE IF EXISTS `william_item_magic`;
CREATE TABLE `william_item_magic` (
  `item_id` int(10) unsigned NOT NULL,
  `checkClass` int(1) unsigned NOT NULL DEFAULT '0',
  `checkItem` int(10) unsigned NOT NULL DEFAULT '0',
  `skill_id` int(10) unsigned NOT NULL,
  `removeItem` int(1) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of william_item_magic
-- ----------------------------
INSERT INTO `william_item_magic` VALUES ('5082', '2', '40308', '10057', '0');
