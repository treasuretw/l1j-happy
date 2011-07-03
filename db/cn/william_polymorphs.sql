/*
MySQL Data Transfer
Source Host: localhost
Source Database: l1jdb_cn
Target Host: localhost
Target Database: l1jdb_cn
Date: 2011-7-3 ÏÂÎç 12:55:58
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for william_polymorphs
-- ----------------------------
CREATE TABLE `william_polymorphs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `polyid` int(11) DEFAULT NULL,
  `minlevel` int(11) DEFAULT NULL,
  `weaponequip` int(11) DEFAULT NULL,
  `armorequip` int(11) DEFAULT NULL,
  `isSkillUse` int(11) DEFAULT NULL,
  `cause` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8720 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
