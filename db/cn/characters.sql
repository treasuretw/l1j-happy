/*
MySQL Data Transfer
Source Host: localhost
Source Database: l1jdb_cn
Target Host: localhost
Target Database: l1jdb_cn
Date: 2011-6-25 ���� 12:51:29
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for characters
-- ----------------------------
CREATE TABLE `characters` (
  `account_name` varchar(13) NOT NULL DEFAULT '0',
  `objid` int(11) unsigned NOT NULL DEFAULT '0',
  `char_name` varchar(45) NOT NULL DEFAULT '',
  `Levelnamemet` int(10) DEFAULT '0',
  `FamenameLevel` int(10) DEFAULT '0',
  `level` int(11) unsigned NOT NULL DEFAULT '0',
  `HighLevel` int(11) unsigned NOT NULL DEFAULT '0',
  `Exp` int(10) unsigned NOT NULL DEFAULT '0',
  `MaxHp` int(10) unsigned NOT NULL DEFAULT '0',
  `CurHp` int(10) unsigned NOT NULL DEFAULT '0',
  `MaxMp` int(10) NOT NULL DEFAULT '0',
  `CurMp` int(10) NOT NULL DEFAULT '0',
  `Ac` int(10) NOT NULL DEFAULT '0',
  `Str` int(3) NOT NULL DEFAULT '0',
  `Con` int(3) NOT NULL DEFAULT '0',
  `Dex` int(3) NOT NULL DEFAULT '0',
  `Cha` int(3) NOT NULL DEFAULT '0',
  `Intel` int(3) NOT NULL DEFAULT '0',
  `Wis` int(3) NOT NULL DEFAULT '0',
  `Status` int(10) unsigned NOT NULL DEFAULT '0',
  `Class` int(10) unsigned NOT NULL DEFAULT '0',
  `Sex` int(10) unsigned NOT NULL DEFAULT '0',
  `Type` int(10) unsigned NOT NULL DEFAULT '0',
  `Heading` int(10) unsigned NOT NULL DEFAULT '0',
  `LocX` int(11) unsigned NOT NULL DEFAULT '0',
  `LocY` int(11) unsigned NOT NULL DEFAULT '0',
  `MapID` int(10) unsigned NOT NULL DEFAULT '0',
  `Food` int(10) unsigned NOT NULL DEFAULT '0',
  `Lawful` int(10) NOT NULL DEFAULT '0',
  `Title` varchar(35) NOT NULL DEFAULT '',
  `ClanID` int(10) unsigned NOT NULL DEFAULT '0',
  `Clanname` varchar(45) NOT NULL DEFAULT '',
  `ClanRank` int(3) NOT NULL DEFAULT '0',
  `BonusStatus` int(10) NOT NULL DEFAULT '0',
  `ElixirStatus` int(10) NOT NULL DEFAULT '0',
  `ElfAttr` int(10) NOT NULL DEFAULT '0',
  `ElfAttr2` int(10) NOT NULL DEFAULT '0',
  `PKcount` int(10) NOT NULL DEFAULT '0',
  `PkCountForElf` int(10) NOT NULL DEFAULT '0',
  `ExpRes` int(10) NOT NULL DEFAULT '0',
  `PartnerID` int(10) NOT NULL DEFAULT '0',
  `AccessLevel` int(10) unsigned NOT NULL DEFAULT '0',
  `OnlineStatus` int(10) unsigned NOT NULL DEFAULT '0',
  `HomeTownID` int(10) NOT NULL DEFAULT '0',
  `Contribution` int(10) NOT NULL DEFAULT '0',
  `Pay` int(10) NOT NULL DEFAULT '0',
  `HellTime` int(10) unsigned NOT NULL DEFAULT '0',
  `Banned` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `Karma` int(10) NOT NULL DEFAULT '0',
  `LastPk` datetime DEFAULT NULL,
  `LastPkForElf` datetime DEFAULT NULL,
  `DeleteTime` datetime DEFAULT NULL,
  `OriginalStr` int(3) NOT NULL DEFAULT '0',
  `OriginalCon` int(3) NOT NULL DEFAULT '0',
  `OriginalDex` int(3) NOT NULL DEFAULT '0',
  `OriginalCha` int(3) NOT NULL DEFAULT '0',
  `OriginalInt` int(3) NOT NULL DEFAULT '0',
  `OriginalWis` int(3) NOT NULL DEFAULT '0',
  `LastActive` datetime DEFAULT NULL,
  `AinZone` int(10) DEFAULT NULL,
  `AinPoint` int(10) DEFAULT NULL,
  PRIMARY KEY (`objid`),
  KEY `key_id` (`account_name`,`char_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `characters` VALUES ('1230123', '268471291', '测试', '18', '3054', '80', '99', '1137770949', '1300', '443', '49', '49', '-10', '50', '16', '50', '50', '1', '1', '46', '61', '0', '1', '4', '33646', '32298', '4', '110', '-4063', '\\f= 超级无敌小霸', '0', '', '0', '0', '150', '0', '0', '0', '0', '0', '0', '200', '0', '0', '0', '0', '0', '0', '-1', null, null, null, '20', '14', '12', '12', '8', '9', '2011-06-25 02:53:08', '0', '108');
INSERT INTO `characters` VALUES ('123123', '268507325', 'Dasd', '0', '1', '5', '5', '925', '87', '87', '5', '5', '10', '20', '14', '12', '12', '8', '9', '4', '61', '0', '1', '5', '33442', '32797', '4', '40', '0', '', '0', '', '0', '0', '0', '0', '0', '0', '0', '0', '0', '200', '0', '0', '0', '0', '0', '0', '0', null, null, null, '20', '14', '12', '12', '8', '9', null, null, null);
INSERT INTO `characters` VALUES ('1230123', '269927405', '256252', '0', '0', '84', '84', '1282024090', '889', '889', '352', '352', '-11', '50', '50', '50', '9', '50', '16', '20', '37', '1', '2', '6', '33442', '32797', '4', '40', '0', '', '0', '', '0', '0', '150', '8', '1', '0', '0', '0', '0', '200', '0', '0', '0', '0', '0', '0', '0', null, null, null, '18', '12', '12', '9', '12', '12', '2011-06-24 15:18:09', '1', '0');
INSERT INTO `characters` VALUES ('asda', '269274385', 'Asda', '0', '8', '1', '0', '27', '16', '66', '1', '1', '-5', '16', '18', '12', '12', '8', '9', '46', '61', '0', '1', '6', '33445', '32813', '4', '40', '0', '', '0', '', '0', '0', '0', '0', '0', '0', '0', '0', '0', '200', '0', '0', '0', '0', '0', '0', '0', null, null, null, '16', '18', '12', '12', '8', '9', null, null, null);
INSERT INTO `characters` VALUES ('1230123', '269492081', '剑刃舞者', '0', '0', '83', '84', '1280222182', '1615', '1615', '140', '140', '-4', '17', '18', '13', '8', '11', '12', '50', '6658', '0', '5', '6', '32586', '32930', '0', '40', '0', '', '0', '', '0', '4', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', null, null, null, '13', '18', '13', '8', '11', '12', '2011-06-24 10:48:51', '1', '200');
INSERT INTO `characters` VALUES ('123123', '269456262', '寒武纪的假身', '0', '1', '1', '0', '0', '16', '16', '1', '1', '10', '20', '14', '12', '12', '8', '9', '0', '61', '0', '1', '4', '33445', '32814', '4', '40', '0', '', '0', '', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', null, null, null, '20', '14', '12', '12', '8', '9', null, null, null);
INSERT INTO `characters` VALUES ('123123', '269529101', '5415', '0', '0', '84', '84', '1282024090', '1469', '1469', '85', '85', '-9', '16', '14', '16', '12', '8', '9', '0', '61', '0', '1', '4', '33448', '32822', '4', '40', '0', '', '0', '', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', null, null, null, '16', '14', '16', '12', '8', '9', null, null, null);
INSERT INTO `characters` VALUES ('1230123', '269890414', 'Gho', '0', '0', '84', '84', '1282024218', '884', '866', '357', '65', '-2', '18', '12', '12', '9', '12', '12', '20', '37', '1', '2', '5', '33665', '32375', '4', '40', '70', '', '0', '', '0', '0', '0', '2', '8', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', null, null, null, '18', '12', '12', '9', '12', '12', '2011-06-23 10:53:47', '0', '0');
INSERT INTO `characters` VALUES ('1230123', '269963955', '15615', '0', '0', '84', '84', '1282024090', '896', '896', '380', '380', '-2', '18', '12', '12', '9', '12', '12', '0', '138', '0', '2', '4', '33442', '32797', '4', '40', '0', '', '0', '', '0', '0', '0', '2', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', null, null, null, '18', '12', '12', '9', '12', '12', null, null, null);
INSERT INTO `characters` VALUES ('123123', '270000390', 'Fdfdf', '0', '10', '84', '84', '1282024090', '1463', '1463', '75', '75', '-2', '20', '14', '12', '12', '8', '9', '4', '61', '0', '1', '4', '33444', '32812', '4', '40', '0', '', '0', '', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', null, null, null, '20', '14', '12', '12', '8', '9', null, null, null);
INSERT INTO `characters` VALUES ('asda', '270000371', 'Qweq', '0', '5', '84', '84', '1282024090', '1961', '1961', '91', '91', '-2', '17', '18', '12', '12', '8', '9', '0', '61', '0', '1', '6', '33445', '32814', '4', '40', '0', '', '0', '', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', null, null, null, '16', '18', '12', '12', '8', '9', null, null, null);
INSERT INTO `characters` VALUES ('1548', '271099265', '1234', '0', '0', '1', '0', '0', '16', '16', '1', '1', '10', '20', '14', '12', '12', '8', '9', '0', '61', '0', '1', '6', '32691', '32864', '2005', '40', '0', '', '0', '', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', null, null, null, '20', '14', '12', '12', '8', '9', null, null, null);
INSERT INTO `characters` VALUES ('1230123', '270769672', '12321', '0', '0', '50', '50', '56521762', '774', '462', '174', '174', '-1', '12', '18', '15', '9', '11', '10', '46', '2786', '0', '4', '6', '32735', '32794', '305', '40', '237', '', '0', '', '0', '0', '0', '0', '0', '0', '0', '0', '0', '200', '0', '0', '0', '0', '0', '0', '0', null, null, null, '12', '18', '15', '9', '11', '10', null, null, null);
INSERT INTO `characters` VALUES ('1234', '271282470', '5555', '0', '0', '1', '0', '0', '16', '16', '1', '1', '10', '20', '14', '12', '12', '8', '9', '0', '61', '0', '1', '3', '34052', '32286', '4', '40', '0', '', '0', '', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', null, null, null, '20', '14', '12', '12', '8', '9', null, null, null);
INSERT INTO `characters` VALUES ('12301233', '271355559', '56456', '0', '0', '1', '0', '0', '16', '16', '1', '1', '10', '20', '14', '12', '12', '8', '9', '0', '61', '0', '1', '0', '32691', '32864', '2005', '40', '0', '', '0', '', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', null, null, null, '20', '14', '12', '12', '8', '9', null, null, null);
INSERT INTO `characters` VALUES ('123123', '271391977', '1414', '0', '0', '1', '0', '0', '16', '16', '1', '1', '10', '20', '14', '12', '12', '8', '9', '0', '61', '0', '1', '0', '32691', '32864', '2005', '40', '0', '', '0', '', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', null, null, null, '20', '14', '12', '12', '8', '9', null, null, null);
INSERT INTO `characters` VALUES ('123456', '271428649', '45545', '0', '0', '84', '84', '1282024090', '1472', '1627', '83', '133', '-62', '20', '14', '12', '12', '8', '9', '4', '61', '0', '1', '4', '33437', '32813', '4', '40', '0', '', '0', '', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', null, null, null, '20', '14', '12', '12', '8', '9', '2011-06-16 06:48:16', '1', '17');
