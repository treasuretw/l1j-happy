/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50150
Source Host           : localhost:3306
Source Database       : l1j-cn

Target Server Type    : MYSQL
Target Server Version : 50150
File Encoding         : 65001

Date: 2011-07-04 00:37:19
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `william_droplist`
-- ----------------------------
DROP TABLE IF EXISTS `william_droplist`;
CREATE TABLE `william_droplist` (
  `mobId` int(6) unsigned NOT NULL DEFAULT '0',
  `itemId` int(6) unsigned NOT NULL DEFAULT '0',
  `min` int(4) unsigned NOT NULL DEFAULT '0',
  `max` int(4) unsigned NOT NULL DEFAULT '0',
  `chance` int(8) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`mobId`,`itemId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of william_droplist
-- ----------------------------
INSERT INTO `william_droplist` VALUES ('99021', '40308', '30000', '12000', '1000000');
INSERT INTO `william_droplist` VALUES ('99021', '40074', '1', '4', '30000');
INSERT INTO `william_droplist` VALUES ('99021', '40087', '1', '4', '30000');
INSERT INTO `william_droplist` VALUES ('99021', '140074', '1', '2', '20000');
INSERT INTO `william_droplist` VALUES ('99021', '140087', '1', '2', '20000');
INSERT INTO `william_droplist` VALUES ('99021', '240074', '1', '3', '30000');
INSERT INTO `william_droplist` VALUES ('99021', '240087', '1', '3', '30000');
INSERT INTO `william_droplist` VALUES ('99021', '40052', '1', '3', '500000');
INSERT INTO `william_droplist` VALUES ('99021', '40053', '1', '3', '500000');
INSERT INTO `william_droplist` VALUES ('99021', '40054', '1', '3', '500000');
INSERT INTO `william_droplist` VALUES ('99021', '40055', '1', '3', '500000');
INSERT INTO `william_droplist` VALUES ('99021', '40253', '1', '1', '400000');
INSERT INTO `william_droplist` VALUES ('99021', '40254', '1', '1', '400000');
INSERT INTO `william_droplist` VALUES ('99021', '40202', '1', '1', '600000');
INSERT INTO `william_droplist` VALUES ('99021', '40223', '1', '1', '3000');
INSERT INTO `william_droplist` VALUES ('99021', '40216', '1', '1', '600000');
INSERT INTO `william_droplist` VALUES ('99021', '41151', '1', '1', '30000');
INSERT INTO `william_droplist` VALUES ('99021', '20288', '1', '1', '10000');
INSERT INTO `william_droplist` VALUES ('99021', '20284', '1', '1', '20000');
INSERT INTO `william_droplist` VALUES ('99021', '20281', '1', '1', '10000');
INSERT INTO `william_droplist` VALUES ('99021', '20303', '1', '2', '100000');
INSERT INTO `william_droplist` VALUES ('99021', '120280', '1', '1', '30000');
INSERT INTO `william_droplist` VALUES ('99021', '20207', '1', '1', '100000');
INSERT INTO `william_droplist` VALUES ('99021', '57', '1', '1', '5000');
INSERT INTO `william_droplist` VALUES ('99021', '100057', '1', '1', '4000');
