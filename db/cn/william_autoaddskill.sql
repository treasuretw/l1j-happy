/*
MySQL Data Transfer
Source Host: localhost
Source Database: l1jdb_cn
Target Host: localhost
Target Database: l1jdb_cn
Date: 2011-7-14 …œŒÁ 10:00:00
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for william_autoaddskill
-- ----------------------------
CREATE TABLE `william_autoaddskill` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Note` varchar(1000) DEFAULT NULL,
  `Level` int(2) NOT NULL DEFAULT '1',
  `SkillId` varchar(1000) DEFAULT NULL,
  `Class` int(1) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `william_autoaddskill` VALUES ('1', '‰øùÊä§ÁΩ©', '50', '3', '1');
