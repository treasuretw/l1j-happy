/*
MySQL Data Transfer
Source Host: localhost
Source Database: l1jdb_cn
Target Host: localhost
Target Database: l1jdb_cn
Date: 2011-6-25 ÏÂÎç 12:50:37
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for character_buff
-- ----------------------------
CREATE TABLE `character_buff` (
  `char_obj_id` int(10) NOT NULL DEFAULT '0',
  `skill_id` int(10) unsigned NOT NULL DEFAULT '0',
  `remaining_time` int(10) NOT NULL DEFAULT '0',
  `poly_id` int(10) DEFAULT '0',
  PRIMARY KEY (`char_obj_id`,`skill_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `character_buff` VALUES ('268471291', '1001', '2499', '0');
INSERT INTO `character_buff` VALUES ('268471291', '67', '5355', '240');
INSERT INTO `character_buff` VALUES ('268471291', '1000', '2499', '0');
