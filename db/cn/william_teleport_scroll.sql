/*
MySQL Data Transfer
Source Host: localhost
Source Database: l1jdb_cn
Target Host: localhost
Target Database: l1jdb_cn
Date: 2011-7-16 ���� 08:49:28
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for william_teleport_scroll
-- ----------------------------
CREATE TABLE `william_teleport_scroll` (
  `item_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `注解` varchar(45) DEFAULT NULL,
  `tpLocX` int(10) unsigned NOT NULL DEFAULT '0',
  `tpLocY` int(10) unsigned NOT NULL DEFAULT '0',
  `tpMapId` int(10) unsigned NOT NULL DEFAULT '0',
  `check_minLocX` int(10) unsigned NOT NULL DEFAULT '0',
  `check_minLocY` int(10) unsigned NOT NULL DEFAULT '0',
  `check_maxLocX` int(10) unsigned NOT NULL DEFAULT '0',
  `check_maxLocY` int(10) unsigned NOT NULL DEFAULT '0',
  `check_MapId` int(10) unsigned NOT NULL DEFAULT '0',
  `removeItem` int(1) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`item_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5081 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `william_teleport_scroll` VALUES ('5080', '天空之城传送卷轴', '32758', '32818', '630', '32512', '32064', '34303', '33535', '4', '1');
