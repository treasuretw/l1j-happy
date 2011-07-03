/*
MySQL Data Transfer
Source Host: localhost
Source Database: l1jdb_cn
Target Host: localhost
Target Database: l1jdb_cn
Date: 2011-7-3 ÏÂÎç 12:55:38
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for william_npcaction
-- ----------------------------
CREATE TABLE `william_npcaction` (
  `npcid` int(10) unsigned NOT NULL DEFAULT '0',
  `normal_action` varchar(45) NOT NULL DEFAULT '',
  `caotic_action` varchar(45) NOT NULL DEFAULT '',
  `teleport_url` varchar(45) NOT NULL DEFAULT '',
  `teleport_urla` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`npcid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `william_npcaction` VALUES ('99000', 'cold1', 'cold1', '', '');
INSERT INTO `william_npcaction` VALUES ('99001', 'cold1', 'cold1', '', '');
INSERT INTO `william_npcaction` VALUES ('99993', 'bossroom1', 'bossroom1', '', '');
INSERT INTO `william_npcaction` VALUES ('99002', 'shuangbei', 'shuangbei', '', '');
INSERT INTO `william_npcaction` VALUES ('99003', 'love1', 'love1', '', '');
INSERT INTO `william_npcaction` VALUES ('99004', 'jlssx8', 'jlssx8', '', '');
INSERT INTO `william_npcaction` VALUES ('99005', 'xycj', 'xycj', '', '');
INSERT INTO `william_npcaction` VALUES ('99006', 'guessmath', 'guessmath', '', '');
INSERT INTO `william_npcaction` VALUES ('99007', 'fuben', 'fuben', '', '');
INSERT INTO `william_npcaction` VALUES ('99010', 'cold1', 'cold1', '', '');
INSERT INTO `william_npcaction` VALUES ('99011', 'cold1', 'cold1', '', '');
INSERT INTO `william_npcaction` VALUES ('99012', 'cold1', 'cold1', '', '');
INSERT INTO `william_npcaction` VALUES ('99013', 'cold1', 'cold1', '', '');
INSERT INTO `william_npcaction` VALUES ('99014', 'cold1', 'cold1', '', '');
INSERT INTO `william_npcaction` VALUES ('99015', 'cold1', 'cold1', '', '');
INSERT INTO `william_npcaction` VALUES ('99016', 'cold1', 'cold1', '', '');
INSERT INTO `william_npcaction` VALUES ('99017', 'cold1', 'cold1', '', '');
INSERT INTO `william_npcaction` VALUES ('99018', 'cold1', 'cold1', '', '');
INSERT INTO `william_npcaction` VALUES ('99019', 'cold1', 'cold1', '', '');
