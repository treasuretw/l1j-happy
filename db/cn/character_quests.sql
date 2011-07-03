/*
MySQL Data Transfer
Source Host: localhost
Source Database: l1jdb_cn
Target Host: localhost
Target Database: l1jdb_cn
Date: 2011-6-25 ÏÂÎç 12:51:01
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for character_quests
-- ----------------------------
CREATE TABLE `character_quests` (
  `char_id` int(10) unsigned NOT NULL,
  `quest_id` int(10) unsigned NOT NULL DEFAULT '0',
  `quest_step` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`char_id`,`quest_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `character_quests` VALUES ('268471291', '300', '255');
INSERT INTO `character_quests` VALUES ('268507325', '300', '1');
INSERT INTO `character_quests` VALUES ('268471291', '5033', '1');
INSERT INTO `character_quests` VALUES ('268471291', '5034', '1');
INSERT INTO `character_quests` VALUES ('268471291', '5035', '1');
INSERT INTO `character_quests` VALUES ('268471291', '5036', '1');
INSERT INTO `character_quests` VALUES ('268471291', '5037', '1');
INSERT INTO `character_quests` VALUES ('268471291', '5038', '1');
INSERT INTO `character_quests` VALUES ('269927405', '20001', '255');
INSERT INTO `character_quests` VALUES ('268471291', '20001', '255');
INSERT INTO `character_quests` VALUES ('268471291', '20002', '255');
INSERT INTO `character_quests` VALUES ('269492081', '304', '2');
INSERT INTO `character_quests` VALUES ('269492081', '20001', '255');
INSERT INTO `character_quests` VALUES ('269492081', '20002', '255');
INSERT INTO `character_quests` VALUES ('269529101', '20001', '255');
INSERT INTO `character_quests` VALUES ('269529101', '20002', '255');
INSERT INTO `character_quests` VALUES ('269529101', '300', '255');
INSERT INTO `character_quests` VALUES ('269890414', '300', '255');
INSERT INTO `character_quests` VALUES ('269890414', '20001', '255');
INSERT INTO `character_quests` VALUES ('269890414', '20002', '255');
INSERT INTO `character_quests` VALUES ('269927405', '20002', '255');
INSERT INTO `character_quests` VALUES ('269927405', '300', '255');
INSERT INTO `character_quests` VALUES ('269963955', '20001', '255');
INSERT INTO `character_quests` VALUES ('269963955', '20002', '255');
INSERT INTO `character_quests` VALUES ('269963955', '300', '255');
INSERT INTO `character_quests` VALUES ('270000390', '20001', '255');
INSERT INTO `character_quests` VALUES ('270000390', '20002', '255');
INSERT INTO `character_quests` VALUES ('270000390', '300', '255');
INSERT INTO `character_quests` VALUES ('270000371', '20001', '255');
INSERT INTO `character_quests` VALUES ('270000371', '20002', '255');
INSERT INTO `character_quests` VALUES ('271428649', '20001', '255');
INSERT INTO `character_quests` VALUES ('271428649', '20002', '255');
INSERT INTO `character_quests` VALUES ('271428649', '300', '255');
INSERT INTO `character_quests` VALUES ('268471291', '3', '4');
