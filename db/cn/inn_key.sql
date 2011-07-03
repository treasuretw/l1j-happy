/*
MySQL Data Transfer
Source Host: localhost
Source Database: l1jdb_cn
Target Host: localhost
Target Database: l1jdb_cn
Date: 2011-6-25 обнГ 12:53:27
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for inn_key
-- ----------------------------
CREATE TABLE `inn_key` (
  `item_obj_id` int(11) NOT NULL,
  `key_id` int(11) NOT NULL,
  `npc_id` int(10) DEFAULT NULL,
  `hall` tinyint(2) DEFAULT NULL,
  `due_time` datetime DEFAULT NULL,
  PRIMARY KEY (`item_obj_id`,`key_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
