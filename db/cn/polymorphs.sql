/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50150
Source Host           : localhost:3306
Source Database       : my-l1jserver

Target Server Type    : MYSQL
Target Server Version : 50150
File Encoding         : 65001

Date: 2011-07-10 23:39:29
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `polymorphs`
-- ----------------------------
DROP TABLE IF EXISTS `polymorphs`;
CREATE TABLE `polymorphs` (
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
) ENGINE=MyISAM AUTO_INCREMENT=8720 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of polymorphs
-- ----------------------------
INSERT INTO `polymorphs` VALUES ('29', 'floating eye', null, '29', '1', '0', '0', '1', '7');
INSERT INTO `polymorphs` VALUES ('95', 'shelob', null, '95', '10', '0', '0', '0', '7');
INSERT INTO `polymorphs` VALUES ('146', 'ungoliant', null, '146', '10', '0', '0', '0', '7');
INSERT INTO `polymorphs` VALUES ('938', 'beagle', null, '938', '1', '0', '2', '1', '7');
INSERT INTO `polymorphs` VALUES ('945', 'milkcow', null, '945', '1', '0', '0', '0', '7');
INSERT INTO `polymorphs` VALUES ('947', 'deer', null, '947', '1', '0', '0', '0', '7');
INSERT INTO `polymorphs` VALUES ('951', 'cerberus', null, '951', '15', '0', '0', '0', '7');
INSERT INTO `polymorphs` VALUES ('979', 'wild boar', null, '979', '1', '0', '0', '1', '7');
INSERT INTO `polymorphs` VALUES ('1037', 'giant ant', null, '1037', '1', '0', '136', '0', '7');
INSERT INTO `polymorphs` VALUES ('1039', 'giant ant soldier', null, '1039', '1', '0', '136', '0', '7');
INSERT INTO `polymorphs` VALUES ('1047', 'scorpion', null, '1047', '15', '0', '0', '0', '7');
INSERT INTO `polymorphs` VALUES ('2064', 'snowman', null, '2064', '1', '0', '1027', '1', '7');
INSERT INTO `polymorphs` VALUES ('2284', 'dark elf polymorph', null, '2284', '52', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('2323', 'orc scout polymorph', null, '2323', '15', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('2374', 'skeleton polymorph', null, '2374', '10', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('2376', 'skeleton axeman polymorph', null, '2376', '10', '1791', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('2377', 'skeleton pike polymorph', null, '2377', '10', '1791', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('2378', 'spartoi polymorph', null, '2378', '10', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('2384', 'succubus morph', null, '2384', '40', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('2385', 'yeti morph', null, '2385', '15', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('2386', 'minotaur i morph', null, '2386', '15', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('2387', 'giant a morph', null, '2387', '15', '1791', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('2388', 'death', null, '2388', '1', '0', '32', '0', '7');
INSERT INTO `polymorphs` VALUES ('2501', 'jack o lantern', null, '2501', '1', '751', '417', '0', '7');
INSERT INTO `polymorphs` VALUES ('3101', 'black knight chief morph', null, '3101', '51', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3102', 'great minotaur morph', null, '3102', '50', '1791', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3103', 'barlog morph', null, '3103', '52', '1791', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3126', 'fire bowman morph', null, '3126', '51', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3630', 'cyclops', null, '3630', '40', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3631', 'griffon', null, '3631', '40', '0', '32', '1', '7');
INSERT INTO `polymorphs` VALUES ('3632', 'cockatrice', null, '3632', '40', '0', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3633', 'ettin', null, '3633', '40', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3634', 'assassin', null, '3634', '45', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3664', 'baranka', null, '3664', '1', '704', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3750', 'haregi', null, '3750', '1', '0', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3784', 'death knight', null, '3784', '52', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3860', 'bow orc', null, '3860', '1', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3861', 'goblin', null, '3861', '1', '751', '913', '0', '7');
INSERT INTO `polymorphs` VALUES ('3862', 'kobolds', null, '3862', '1', '751', '913', '1', '7');
INSERT INTO `polymorphs` VALUES ('3863', 'dwarf', null, '3863', '1', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3864', 'orc fighter', null, '3864', '1', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3865', 'werewolf', null, '3865', '1', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3866', 'gandi orc', null, '3866', '10', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3867', 'rova orc', null, '3867', '10', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3868', 'atuba orc', null, '3868', '10', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3869', 'dudamara orc', null, '3869', '10', '1791', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3870', 'neruga orc', null, '3870', '10', '1791', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3871', 'skeleton archer polymorph', null, '3871', '10', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3872', 'zombie', null, '3872', '10', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3873', 'ghoul', null, '3873', '10', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3874', 'lycanthrope', null, '3874', '10', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3875', 'ghast', null, '3875', '10', '751', '945', '1', '7');
INSERT INTO `polymorphs` VALUES ('3876', 'bugbear', null, '3876', '10', '751', '945', '1', '7');
INSERT INTO `polymorphs` VALUES ('3877', 'ogre', null, '3877', '15', '1791', '913', '1', '7');
INSERT INTO `polymorphs` VALUES ('3878', 'troll', null, '3878', '15', '751', '545', '1', '7');
INSERT INTO `polymorphs` VALUES ('3879', 'elder', null, '3879', '15', '751', '0', '1', '7');
INSERT INTO `polymorphs` VALUES ('3880', 'king bugbear', null, '3880', '15', '751', '945', '1', '7');
INSERT INTO `polymorphs` VALUES ('3881', 'dark elder', null, '3881', '45', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3882', 'necromancer1', null, '3882', '45', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3883', 'necromancer2', null, '3883', '45', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3884', 'necromancer3', null, '3884', '45', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3885', 'necromancer4', null, '3885', '45', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3886', 'lesser demon', null, '3886', '45', '751', '4095', '0', '7');
INSERT INTO `polymorphs` VALUES ('3887', 'darkelf carrier', null, '3887', '45', '1791', '4095', '0', '7');
INSERT INTO `polymorphs` VALUES ('3888', 'baphomet', null, '3888', '50', '751', '954', '1', '7');
INSERT INTO `polymorphs` VALUES ('3889', 'demon', null, '3889', '51', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3890', 'ancient black knight morph', null, '3890', '55', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3891', 'ancient black mage morph', null, '3891', '55', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3892', 'ancient black scouter morph', null, '3892', '55', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3893', 'ancient silver knight morph', null, '3893', '60', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3894', 'ancient silver mage morph', null, '3894', '60', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3895', 'ancient silver scouter morph', null, '3895', '60', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3896', 'ancient gold knight morph', null, '3896', '65', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3897', 'ancient gold mage morph', null, '3897', '65', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3898', 'ancient gold scouter morph', null, '3898', '65', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3899', 'ancient platinum knight morph', null, '3899', '70', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3900', 'ancient platinum mage morph', null, '3900', '70', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3901', 'ancient platinum scouter morph', null, '3901', '70', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3902', 'Kelenis Morph', null, '3902', '1', '43', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3903', 'Ken Lauhel Morph', null, '3903', '1', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3904', 'stone golem', null, '3904', '1', '751', '145', '1', '7');
INSERT INTO `polymorphs` VALUES ('3905', 'beleth', null, '3905', '50', '751', '954', '1', '7');
INSERT INTO `polymorphs` VALUES ('3906', 'orc', null, '3906', '1', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3945', 'gelatincube', null, '3945', '15', '751', '0', '1', '7');
INSERT INTO `polymorphs` VALUES ('3950', 'middle oum', null, '3950', '15', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('3952', 'vampire', null, '3952', '1', '0', '32', '0', '7');
INSERT INTO `polymorphs` VALUES ('4000', 'knight vald morph', null, '4000', '1', '1791', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('4001', 'iris morph', null, '4001', '1', '1791', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('4002', 'paperman morph', null, '4002', '1', '1791', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('4004', 'succubus queen morph', null, '4004', '1', '1791', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('4186', 'robber bone', null, '4186', '1', '751', '4095', '0', '7');
INSERT INTO `polymorphs` VALUES ('4188', 'robber bone head', null, '4188', '1', '751', '4095', '0', '7');
INSERT INTO `polymorphs` VALUES ('4190', 'robber bone soldier', null, '4190', '1', '256', '4095', '0', '7');
INSERT INTO `polymorphs` VALUES ('4227', 'hakama', null, '4227', '1', '0', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('4767', 'rabbit', null, '4767', '1', '0', '4095', '0', '7');
INSERT INTO `polymorphs` VALUES ('4769', 'carrot rabbit', null, '4769', '1', '0', '4095', '0', '7');
INSERT INTO `polymorphs` VALUES ('4917', 'darkelf ranger morph', null, '4917', '45', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('4918', 'bandit bow morph', null, '4918', '40', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('4919', 'darkelf guard morph', null, '4919', '50', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('4920', 'elmor general morph', null, '4920', '45', '1791', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('4921', 'darkelf general morph', null, '4921', '52', '1791', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('4922', 'guardian armor morph', null, '4922', '50', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('4923', 'black knight morph', null, '4923', '51', '1080', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('4924', 'darkelf spear morph', null, '4924', '50', '1080', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('4925', 'elmor soldier morph', null, '4925', '40', '1080', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('4926', 'darkelf wizard morph', null, '4926', '50', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('4928', 'high collie', null, '4928', '1', '0', '2', '1', '7');
INSERT INTO `polymorphs` VALUES ('4929', 'high raccoon', null, '4929', '1', '0', '2', '1', '7');
INSERT INTO `polymorphs` VALUES ('4932', 'assassin master morph', null, '4932', '52', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('5184', 'red uniform', null, '5184', '1', '0', '8', '1', '7');
INSERT INTO `polymorphs` VALUES ('5186', 'blue uniform', null, '5186', '1', '0', '8', '1', '7');
INSERT INTO `polymorphs` VALUES ('5645', 'Halloween Pumpkin', null, '5645', '1', '2047', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('5719', 'manekineko', null, '5719', '1', '2047', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('5727', 'ancient black assassin morph', null, '5727', '55', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('5730', 'ancient silver assassin morph', null, '5730', '60', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('5733', 'ancient gold assassin morph', null, '5733', '65', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('5736', 'ancient platinum assassin morph', null, '5736', '70', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('5976', 'high bear', null, '5976', '1', '751', '2', '0', '7');
INSERT INTO `polymorphs` VALUES ('6002', 'spirit of earth boss', null, '6002', '1', '8', '0', '1', '7');
INSERT INTO `polymorphs` VALUES ('6010', 'red orc', null, '6010', '1', '0', '1', '0', '7');
INSERT INTO `polymorphs` VALUES ('6034', 'priest of corruption', null, '6034', '45', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6035', 'quest lesser demon', null, '6035', '45', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6080', 'princess horse', null, '6080', '1', '16', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6086', 'Rabor Born Head', null, '6086', '1', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6087', 'Rabor Born archer', null, '6087', '1', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6088', 'Rabor Born knife', null, '6088', '1', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6089', 'drake morph', null, '6089', '1', '1791', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6094', 'prince horse', null, '6094', '1', '16', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6136', 'barlog 52', null, '6136', '52', '1791', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6137', 'death 52', null, '6137', '52', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6138', 'assassin 52', null, '6138', '52', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6139', 'general 52', null, '6139', '52', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6140', 'darkelf 52', null, '6140', '52', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6141', 'barlog 55', null, '6141', '55', '1791', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6142', 'death 55', null, '6142', '55', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6143', 'assassin 55', null, '6143', '55', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6144', 'general 55', null, '6144', '55', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6145', 'darkelf 55', null, '6145', '55', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6146', 'barlog 60', null, '6146', '60', '1791', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6147', 'death 60', null, '6147', '60', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6148', 'assassin 60', null, '6148', '60', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6149', 'general 60', null, '6149', '60', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6150', 'darkelf 60', null, '6150', '60', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6151', 'barlog 65', null, '6151', '65', '1791', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6152', 'death 65', null, '6152', '65', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6153', 'assassin 65', null, '6153', '65', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6154', 'general 65', null, '6154', '65', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6155', 'darkelf 65', null, '6155', '65', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6156', 'barlog 70', null, '6156', '70', '1791', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6157', 'death 70', null, '6157', '70', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6158', 'assassin 70', null, '6158', '70', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6159', 'general 70', null, '6159', '70', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6160', 'darkelf 70', null, '6160', '70', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6180', 'unicorn A', null, '6180', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6181', 'unicorn B', null, '6181', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6182', 'unicorn C', null, '6182', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6183', 'unicorn D', null, '6183', '0', '0', '0', '0', '10');
INSERT INTO `polymorphs` VALUES ('6184', 'bear A', null, '6184', '0', '749', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6185', 'bear B', null, '6185', '0', '749', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6186', 'bear C', null, '6186', '0', '749', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6187', 'bear D', null, '6187', '0', '0', '0', '0', '10');
INSERT INTO `polymorphs` VALUES ('6188', 'mini white dog A', null, '6188', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6189', 'mini white dog B', null, '6189', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6190', 'mini white dog C', null, '6190', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6191', 'mini white dog D', null, '6191', '0', '0', '0', '0', '10');
INSERT INTO `polymorphs` VALUES ('6192', 'ratman A', null, '6192', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6193', 'ratman B', null, '6193', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6194', 'ratman C', null, '6194', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6195', 'ratman D', null, '6195', '0', '0', '0', '0', '10');
INSERT INTO `polymorphs` VALUES ('6196', 'pet tiger A', null, '6196', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6197', 'pet tiger B', null, '6197', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6198', 'pet tiger C', null, '6198', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6199', 'pet tiger D', null, '6199', '0', '0', '0', '0', '10');
INSERT INTO `polymorphs` VALUES ('6200', 'dillo A', null, '6200', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6201', 'dillo B', null, '6201', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6202', 'dillo C', null, '6202', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6203', 'dillo D', null, '6203', '0', '0', '0', '0', '10');
INSERT INTO `polymorphs` VALUES ('6204', 'mole A', null, '6204', '0', '256', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6205', 'mole B', null, '6205', '0', '256', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6206', 'mole C', null, '6206', '0', '256', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6207', 'mole D', null, '6207', '0', '0', '0', '0', '10');
INSERT INTO `polymorphs` VALUES ('6208', 'darkelf thief A', null, '6208', '0', '1007', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6209', 'darkelf thief B', null, '6209', '0', '1007', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6210', 'darkelf thief C', null, '6210', '0', '1007', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6211', 'darkelf thief D', null, '6211', '0', '0', '0', '0', '10');
INSERT INTO `polymorphs` VALUES ('6212', 'ken lauhel A', null, '6212', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6213', 'ken lauhel B', null, '6213', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6214', 'ken lauhel C', null, '6214', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6215', 'ken lauhel D', null, '6215', '0', '0', '0', '0', '10');
INSERT INTO `polymorphs` VALUES ('6216', 'kelenis A', null, '6216', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6217', 'kelenis B', null, '6217', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6218', 'kelenis C', null, '6218', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6219', 'kelenis D', null, '6219', '0', '0', '0', '0', '10');
INSERT INTO `polymorphs` VALUES ('6220', 'slave A', null, '6220', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6221', 'slave B', null, '6221', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6222', 'slave C', null, '6222', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6223', 'slave D', null, '6223', '0', '0', '0', '0', '10');
INSERT INTO `polymorphs` VALUES ('6224', 'dofleganger boss A', null, '6224', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6225', 'dofleganger boss B', null, '6225', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6226', 'dofleganger boss C', null, '6226', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6227', 'dofleganger boss D', null, '6227', '0', '0', '0', '0', '10');
INSERT INTO `polymorphs` VALUES ('6228', 'lich A', null, '6228', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6229', 'lich B', null, '6229', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6230', 'lich C', null, '6230', '0', '751', '4095', '1', '10');
INSERT INTO `polymorphs` VALUES ('6231', 'lich D', null, '6231', '0', '0', '0', '0', '10');
INSERT INTO `polymorphs` VALUES ('6232', 'woman1 A', null, '6232', '0', '0', '4095', '0', '10');
INSERT INTO `polymorphs` VALUES ('6233', 'woman1 B', null, '6233', '0', '0', '0', '0', '10');
INSERT INTO `polymorphs` VALUES ('6234', 'woman2 A', null, '6234', '0', '0', '4095', '0', '10');
INSERT INTO `polymorphs` VALUES ('6235', 'woman2 B', null, '6235', '0', '0', '0', '0', '10');
INSERT INTO `polymorphs` VALUES ('6236', 'woman3 A', null, '6236', '0', '0', '4095', '0', '10');
INSERT INTO `polymorphs` VALUES ('6237', 'woman3 B', null, '6237', '0', '0', '0', '0', '10');
INSERT INTO `polymorphs` VALUES ('6238', 'woman4 A', null, '6238', '0', '0', '4095', '0', '10');
INSERT INTO `polymorphs` VALUES ('6239', 'woman4 B', null, '6239', '0', '0', '0', '0', '10');
INSERT INTO `polymorphs` VALUES ('6240', 'woman5 A', null, '6240', '0', '0', '4095', '0', '10');
INSERT INTO `polymorphs` VALUES ('6241', 'woman5 B', null, '6241', '0', '0', '0', '0', '10');
INSERT INTO `polymorphs` VALUES ('6242', 'noblewoman A', null, '6242', '0', '0', '4095', '0', '10');
INSERT INTO `polymorphs` VALUES ('6243', 'noblewoman B', null, '6243', '0', '0', '0', '0', '10');
INSERT INTO `polymorphs` VALUES ('6267', 'neo black knight', null, '6267', '55', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6268', 'neo black mage', null, '6268', '55', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6269', 'neo black scouter', null, '6269', '55', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6270', 'neo silver knight', null, '6270', '60', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6271', 'neo silver mage', null, '6271', '60', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6272', 'neo silver scouter', null, '6272', '60', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6273', 'neo gold knight', null, '6273', '65', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6274', 'neo gold mage', null, '6274', '65', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6275', 'neo gold scouter', null, '6275', '65', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6276', 'neo platinum knight', null, '6276', '70', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6277', 'neo platinum mage', null, '6277', '70', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6278', 'neo platinum scouter', null, '6278', '70', '256', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6279', 'neo black assassin', null, '6279', '55', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6280', 'neo silver assassin', null, '6280', '60', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6281', 'neo gold assassin', null, '6281', '65', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6282', 'neo platinum assassin', null, '6282', '70', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6284', 'Haunted House jack o lantern', null, '6284', '1', '0', '0', '0', '7');
INSERT INTO `polymorphs` VALUES ('6400', 'Halloween jack o lantern', null, '6400', '1', '2047', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('6894', 'awake', null, '6894', '1', '751', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('7332', 'spearm 52', null, '7332', '52', '1080', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('7338', 'spearm 55', null, '7338', '55', '1080', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('7339', 'spearm 60', null, '7339', '60', '1080', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('7340', 'spearm 65', null, '7340', '65', '1080', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('7341', 'spearm 70', null, '7341', '70', '1080', '4095', '1', '7');
INSERT INTO `polymorphs` VALUES ('8719', '柑橘', null, '8719', '1', '2047', '4095', '1', '7');
