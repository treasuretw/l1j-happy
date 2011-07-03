/*
MySQL Data Transfer
Source Host: localhost
Source Database: l1jdb_cn
Target Host: localhost
Target Database: l1jdb_cn
Date: 2011-6-25 ÏÂÎç 12:54:48
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for monster_enhance
-- ----------------------------
CREATE TABLE `monster_enhance` (
  `npcid` int(11) NOT NULL,
  `current_dc` int(11) NOT NULL DEFAULT '0',
  `dc_enhance` int(11) NOT NULL DEFAULT '0',
  `level` int(11) NOT NULL DEFAULT '0',
  `hp` int(11) NOT NULL DEFAULT '0',
  `mp` int(11) NOT NULL DEFAULT '0',
  `ac` int(11) NOT NULL DEFAULT '0',
  `str` int(11) NOT NULL DEFAULT '0',
  `dex` int(11) NOT NULL DEFAULT '0',
  `con` int(11) NOT NULL DEFAULT '0',
  `wis` int(11) NOT NULL DEFAULT '0',
  `int` int(11) NOT NULL DEFAULT '0',
  `mr` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`npcid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `monster_enhance` VALUES ('45009', '5', '2', '1', '10', '10', '-5', '1', '1', '1', '1', '1', '1');
