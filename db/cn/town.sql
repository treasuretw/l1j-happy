/*
MySQL Data Transfer
Source Host: localhost
Source Database: l1jdb_cn
Target Host: localhost
Target Database: l1jdb_cn
Date: 2011-6-25 ���� 12:59:01
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for town
-- ----------------------------
CREATE TABLE `town` (
  `town_id` int(10) unsigned NOT NULL DEFAULT '0',
  `name` varchar(45) NOT NULL DEFAULT '',
  `leader_id` int(10) unsigned NOT NULL DEFAULT '0',
  `leader_name` varchar(45) DEFAULT NULL,
  `tax_rate` int(10) unsigned NOT NULL DEFAULT '0',
  `tax_rate_reserved` int(10) unsigned NOT NULL DEFAULT '0',
  `sales_money` int(10) unsigned NOT NULL DEFAULT '0',
  `sales_money_yesterday` int(10) unsigned NOT NULL DEFAULT '0',
  `town_tax` int(10) unsigned NOT NULL DEFAULT '0',
  `town_fix_tax` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`town_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `town` VALUES ('1', '说话之岛', '0', null, '0', '0', '0', '0', '0', '0');
INSERT INTO `town` VALUES ('2', '银骑士村庄', '0', null, '0', '0', '0', '0', '0', '0');
INSERT INTO `town` VALUES ('3', '古鲁丁村庄', '0', null, '0', '0', '0', '0', '0', '0');
INSERT INTO `town` VALUES ('4', '燃柳村庄', '0', null, '0', '0', '0', '0', '0', '0');
INSERT INTO `town` VALUES ('5', '风木村庄', '0', null, '0', '0', '0', '0', '0', '0');
INSERT INTO `town` VALUES ('6', '肯特村庄', '0', null, '0', '0', '0', '0', '0', '0');
INSERT INTO `town` VALUES ('7', '奇岩村庄', '0', null, '0', '0', '0', '0', '0', '200');
INSERT INTO `town` VALUES ('8', '海音村庄', '0', null, '0', '0', '0', '0', '0', '0');
INSERT INTO `town` VALUES ('9', '威顿村庄', '0', null, '0', '0', '0', '0', '0', '0');
INSERT INTO `town` VALUES ('10', '欧瑞村庄', '0', null, '0', '0', '1119010', '0', '0', '99192');
