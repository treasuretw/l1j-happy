/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50150
Source Host           : localhost:3306
Source Database       : happy-weixiugai

Target Server Type    : MYSQL
Target Server Version : 50150
File Encoding         : 65001

Date: 2011-07-20 20:24:28
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `z_copy_polymorphs`
-- ----------------------------
DROP TABLE IF EXISTS `z_copy_polymorphs`;
CREATE TABLE `z_copy_polymorphs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `注解` varchar(45) DEFAULT NULL,
  `polyid` int(11) DEFAULT NULL,
  `minlevel` int(11) DEFAULT NULL,
  `weaponequip` int(11) DEFAULT NULL,
  `armorequip` int(11) DEFAULT NULL,
  `isSkillUse` int(11) DEFAULT NULL,
  `cause` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9227 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of z_copy_polymorphs
-- ----------------------------
INSERT INTO `z_copy_polymorphs` VALUES ('8817', 'Ken Rauhel', '肯恩罗亨', '8817', '75', '751', '4095', '1', '7');
INSERT INTO `z_copy_polymorphs` VALUES ('8774', 'Cerenis', '赛尼斯', '8774', '75', '751', '4095', '1', '7');
INSERT INTO `z_copy_polymorphs` VALUES ('8900', 'Hellvine', '海露拜', '8900', '75', '256', '4095', '1', '7');
INSERT INTO `z_copy_polymorphs` VALUES ('8851', 'Dantes', '丹特斯', '8851', '75', '751', '4095', '1', '7');
INSERT INTO `z_copy_polymorphs` VALUES ('8913', 'Zillian', '朱里安', '8913', '80', '256', '4095', '1', '7');
INSERT INTO `z_copy_polymorphs` VALUES ('9003', 'Joe', '乔', '9003', '80', '751', '4095', '1', '7');
INSERT INTO `z_copy_polymorphs` VALUES ('8812', 'Gantt', '甘特', '8812', '80', '751', '4095', '1', '7');
INSERT INTO `z_copy_polymorphs` VALUES ('8978', 'Bluedica', '布鲁迪卡', '8978', '80', '751', '4095', '1', '7');
INSERT INTO `z_copy_polymorphs` VALUES ('9225', 'darkelf 75', '黑暗精灵', '9225', '75', '256', '4095', '1', '7');
INSERT INTO `z_copy_polymorphs` VALUES ('9205', 'death 75', '死亡骑士', '9205', '75', '751', '4095', '1', '7');
INSERT INTO `z_copy_polymorphs` VALUES ('9011', 'spearm 75', '狂暴将军', '9011', '75', '1080', '4095', '1', '7');
INSERT INTO `z_copy_polymorphs` VALUES ('9206', 'death 80', '死亡骑士', '9206', '80', '751', '4095', '1', '7');
INSERT INTO `z_copy_polymorphs` VALUES ('9226', 'darkelf 80', '黑暗精灵', '9226', '80', '256', '4095', '1', '7');
INSERT INTO `z_copy_polymorphs` VALUES ('9012', 'spearm 80', '狂暴将军', '9012', '80', '1080', '4095', '1', '7');
INSERT INTO `z_copy_polymorphs` VALUES ('363', 'tgg neo Death knight', '光圈死骑', '363', '90', '751', '4095', '1', '7');
INSERT INTO `z_copy_polymorphs` VALUES ('365', 'tgg neo platinum knight', '光圈骑士', '365', '90', '751', '4095', '1', '7');
INSERT INTO `z_copy_polymorphs` VALUES ('367', 'tgg neo platinum mage', '光圈法师', '367', '90', '751', '4095', '1', '7');
INSERT INTO `z_copy_polymorphs` VALUES ('369', 'tgg neo platinum assassin', '光圈刺客', '369', '90', '751', '4095', '1', '7');
INSERT INTO `z_copy_polymorphs` VALUES ('371', 'tgg neo platinum scouter', '光圈巡狩', '371', '90', '256', '4095', '1', '7');
