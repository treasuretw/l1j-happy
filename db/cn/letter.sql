/*
MySQL Data Transfer
Source Host: localhost
Source Database: l1jdb_cn
Target Host: localhost
Target Database: l1jdb_cn
Date: 2011-6-25 ÏÂÎç 12:53:34
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for letter
-- ----------------------------
CREATE TABLE `letter` (
  `item_object_id` int(10) unsigned NOT NULL DEFAULT '0',
  `code` int(10) unsigned NOT NULL DEFAULT '0',
  `sender` varchar(16) DEFAULT NULL,
  `receiver` varchar(16) DEFAULT NULL,
  `date` varchar(16) DEFAULT NULL,
  `template_id` int(5) unsigned NOT NULL DEFAULT '0',
  `subject` blob,
  `content` blob,
  PRIMARY KEY (`item_object_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
