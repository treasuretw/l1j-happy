/*
MySQL Data Transfer
Source Host: localhost
Source Database: l1jdb_cn
Target Host: localhost
Target Database: l1jdb_cn
Date: 2011-7-3 ÏÂÎç 12:55:52
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for william_pettypes
-- ----------------------------
CREATE TABLE `william_pettypes` (
  `BaseNpcId` int(10) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `ItemIdForTaming` int(10) NOT NULL,
  `HpUpMin` int(10) NOT NULL,
  `HpUpMax` int(10) NOT NULL,
  `MpUpMin` int(10) NOT NULL,
  `MpUpMax` int(10) NOT NULL,
  `EvolvItemId` int(10) NOT NULL DEFAULT '40070',
  `NpcIdForEvolving` int(10) NOT NULL,
  `MessageId1` int(10) NOT NULL,
  `MessageId2` int(10) NOT NULL,
  `MessageId3` int(10) NOT NULL,
  `MessageId4` int(10) NOT NULL,
  `MessageId5` int(10) NOT NULL,
  `DefyMessageId` int(10) NOT NULL,
  `canUseEquipment` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`BaseNpcId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
