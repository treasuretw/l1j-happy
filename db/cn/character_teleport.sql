/*
MySQL Data Transfer
Source Host: localhost
Source Database: l1jdb_cn
Target Host: localhost
Target Database: l1jdb_cn
Date: 2011-6-25 ÏÂÎç 12:51:16
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for character_teleport
-- ----------------------------
CREATE TABLE `character_teleport` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `char_id` int(10) unsigned NOT NULL DEFAULT '0',
  `name` varchar(45) NOT NULL DEFAULT '',
  `locx` int(10) unsigned NOT NULL DEFAULT '0',
  `locy` int(10) unsigned NOT NULL DEFAULT '0',
  `mapid` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `key_id` (`char_id`)
) ENGINE=MyISAM AUTO_INCREMENT=271649192 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `character_teleport` VALUES ('271649191', '268471291', 'è±¡ç‰™å¡”', '34041', '32163', '4');
