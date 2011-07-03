/*
MySQL Data Transfer
Source Host: localhost
Source Database: l1jdb_cn
Target Host: localhost
Target Database: l1jdb_cn
Date: 2011-7-3 ���� 12:55:45
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for william_npcchat
-- ----------------------------
CREATE TABLE `william_npcchat` (
  `npc_id` int(10) unsigned NOT NULL DEFAULT '0',
  `chat_timing` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `note` varchar(45) NOT NULL DEFAULT '',
  `start_delay_time` int(10) NOT NULL DEFAULT '0',
  `chat_id1` varchar(45) NOT NULL DEFAULT '',
  `chat_id2` varchar(45) NOT NULL DEFAULT '',
  `chat_id3` varchar(45) NOT NULL DEFAULT '',
  `chat_id4` varchar(45) NOT NULL DEFAULT '',
  `chat_id5` varchar(45) NOT NULL DEFAULT '',
  `chat_interval` int(10) unsigned NOT NULL DEFAULT '0',
  `is_shout` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `is_world_chat` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `is_repeat` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `repeat_interval` int(10) unsigned NOT NULL DEFAULT '0',
  `game_time` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`npc_id`,`chat_timing`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `william_npcchat` VALUES ('45601', '1', '死亡骑士-死亡時)', '0', '$8866', '', '', '', '', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_npcchat` VALUES ('45601', '0', '死亡骑士-(出现時)', '0', '$8617', '', '', '', '', '5000', '0', '0', '0', '0', '0');
