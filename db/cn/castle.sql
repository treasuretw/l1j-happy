/*
MySQL Data Transfer
Source Host: localhost
Source Database: l1jdb_cn
Target Host: localhost
Target Database: l1jdb_cn
Date: 2011-6-25 ���� 12:50:26
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for castle
-- ----------------------------
CREATE TABLE `castle` (
  `castle_id` int(11) NOT NULL DEFAULT '0',
  `name` varchar(45) NOT NULL DEFAULT '',
  `war_time` datetime DEFAULT NULL,
  `tax_rate` int(11) NOT NULL DEFAULT '0',
  `public_money` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`castle_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `castle` VALUES ('1', '肯特城', '2011-06-28 07:32:15', '10', '0');
INSERT INTO `castle` VALUES ('2', '妖魔城', '2011-06-28 07:32:15', '10', '0');
INSERT INTO `castle` VALUES ('3', '風木城', '2011-06-28 07:32:15', '10', '0');
INSERT INTO `castle` VALUES ('4', '奇岩城', '2011-06-28 07:32:15', '10', '0');
INSERT INTO `castle` VALUES ('5', '海音城', '2011-06-28 07:32:15', '10', '0');
INSERT INTO `castle` VALUES ('6', '侏儒城', '2011-06-28 07:32:15', '10', '100711');
INSERT INTO `castle` VALUES ('7', '亞丁城', '2011-06-28 07:32:15', '10', '11190');
INSERT INTO `castle` VALUES ('8', '狄亞得要塞', '2011-06-28 07:32:15', '10', '16785');
