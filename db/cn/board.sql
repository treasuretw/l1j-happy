/*
MySQL Data Transfer
Source Host: localhost
Source Database: l1jdb_cn
Target Host: localhost
Target Database: l1jdb_cn
Date: 2011-6-25 обнГ 12:50:15
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for board
-- ----------------------------
CREATE TABLE `board` (
  `id` int(10) NOT NULL DEFAULT '0',
  `name` varchar(16) DEFAULT NULL,
  `date` varchar(16) DEFAULT NULL,
  `title` varchar(16) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
