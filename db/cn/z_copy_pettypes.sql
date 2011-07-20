/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50150
Source Host           : localhost:3306
Source Database       : happy-weixiugai

Target Server Type    : MYSQL
Target Server Version : 50150
File Encoding         : 65001

Date: 2011-07-20 20:24:25
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `z_copy_pettypes`
-- ----------------------------
DROP TABLE IF EXISTS `z_copy_pettypes`;
CREATE TABLE `z_copy_pettypes` (
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
-- Records of z_copy_pettypes
-- ----------------------------
