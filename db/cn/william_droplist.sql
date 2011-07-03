/*
MySQL Data Transfer
Source Host: localhost
Source Database: l1jdb_cn
Target Host: localhost
Target Database: l1jdb_cn
Date: 2011-7-3 ÏÂÎç 12:54:55
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for william_droplist
-- ----------------------------
CREATE TABLE `william_droplist` (
  `mobId` int(6) unsigned NOT NULL DEFAULT '0',
  `itemId` int(6) unsigned NOT NULL DEFAULT '0',
  `min` int(4) unsigned NOT NULL DEFAULT '0',
  `max` int(4) unsigned NOT NULL DEFAULT '0',
  `chance` int(8) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`mobId`,`itemId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
