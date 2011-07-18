/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50150
Source Host           : localhost:3306
Source Database       : happy-weixiugai

Target Server Type    : MYSQL
Target Server Version : 50150
File Encoding         : 65001

Date: 2011-07-18 22:23:11
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `william_labar_game`
-- ----------------------------
DROP TABLE IF EXISTS `william_labar_game`;
CREATE TABLE `william_labar_game` (
  `npcid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `action` varchar(45) NOT NULL,
  `GiveItem` varchar(1000) DEFAULT NULL,
  `GiveItemCount` varchar(1000) DEFAULT NULL,
  `GiveItemEnchantlvl` int(10) unsigned NOT NULL DEFAULT '0',
  `checkMoney` int(10) unsigned NOT NULL DEFAULT '0',
  `checkMoneyCount` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`npcid`)
) ENGINE=MyISAM AUTO_INCREMENT=99026 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of william_labar_game
-- ----------------------------
INSERT INTO `william_labar_game` VALUES ('99022', 'labar', '40307,40306,40305,57,58,59,60,61', '100,200,300,1,1,1,1,1', '1', '40308', '1000');
