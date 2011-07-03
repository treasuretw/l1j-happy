/*
MySQL Data Transfer
Source Host: localhost
Source Database: l1jdb_cn
Target Host: localhost
Target Database: l1jdb_cn
Date: 2011-6-25 ���� 12:52:12
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for drop_item
-- ----------------------------
CREATE TABLE `drop_item` (
  `item_id` int(10) NOT NULL DEFAULT '0',
  `drop_rate` float unsigned NOT NULL DEFAULT '0',
  `drop_amount` float unsigned NOT NULL DEFAULT '0',
  `note` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`item_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `drop_item` VALUES ('1', '1', '1', '欧西斯匕首');
INSERT INTO `drop_item` VALUES ('21', '1', '1', '欧西斯短剑');
INSERT INTO `drop_item` VALUES ('23', '1', '1', '阔剑');
INSERT INTO `drop_item` VALUES ('25', '1', '1', '银剑');
INSERT INTO `drop_item` VALUES ('26', '1', '1', '小侏儒短剑');
INSERT INTO `drop_item` VALUES ('27', '1', '1', '弯刀');
INSERT INTO `drop_item` VALUES ('31', '1', '1', '长剑');
INSERT INTO `drop_item` VALUES ('91', '1', '1', '欧西斯之矛');
INSERT INTO `drop_item` VALUES ('93', '1', '1', '三叉戟');
INSERT INTO `drop_item` VALUES ('94', '1', '1', '帕提森');
INSERT INTO `drop_item` VALUES ('96', '1', '1', '矛');
INSERT INTO `drop_item` VALUES ('98', '1', '1', '阔矛');
INSERT INTO `drop_item` VALUES ('102', '1', '1', '露西锤');
INSERT INTO `drop_item` VALUES ('103', '1', '1', '戟');
INSERT INTO `drop_item` VALUES ('136', '1', '1', '斧');
INSERT INTO `drop_item` VALUES ('137', '1', '1', '亚连');
INSERT INTO `drop_item` VALUES ('138', '1', '1', '木棒');
INSERT INTO `drop_item` VALUES ('139', '1', '1', '弗莱尔');
INSERT INTO `drop_item` VALUES ('140', '1', '1', '钉锤');
INSERT INTO `drop_item` VALUES ('143', '1', '1', '战斧');
INSERT INTO `drop_item` VALUES ('144', '1', '1', '侏儒铁斧');
INSERT INTO `drop_item` VALUES ('171', '1', '1', '欧西斯弓');
INSERT INTO `drop_item` VALUES ('172', '1', '1', '弓');
INSERT INTO `drop_item` VALUES ('173', '1', '1', '短弓');
INSERT INTO `drop_item` VALUES ('20007', '1', '1', '侏儒铁盔');
INSERT INTO `drop_item` VALUES ('20034', '1', '1', '欧西斯头盔');
INSERT INTO `drop_item` VALUES ('20043', '1', '1', '钢盔');
INSERT INTO `drop_item` VALUES ('20052', '1', '1', '侏儒斗篷');
INSERT INTO `drop_item` VALUES ('20072', '1', '1', '欧西斯斗篷');
INSERT INTO `drop_item` VALUES ('20089', '1', '1', '小藤甲');
INSERT INTO `drop_item` VALUES ('20096', '1', '1', '环甲');
INSERT INTO `drop_item` VALUES ('20101', '1', '1', '皮甲');
INSERT INTO `drop_item` VALUES ('20115', '1', '1', '藤甲');
INSERT INTO `drop_item` VALUES ('20122', '1', '1', '鳞甲');
INSERT INTO `drop_item` VALUES ('20125', '1', '1', '链甲');
INSERT INTO `drop_item` VALUES ('20135', '1', '1', '欧西斯环甲');
INSERT INTO `drop_item` VALUES ('20136', '1', '1', '欧西斯链甲');
INSERT INTO `drop_item` VALUES ('20147', '1', '1', '银钉皮甲');
INSERT INTO `drop_item` VALUES ('20149', '1', '1', '青铜盔甲');
INSERT INTO `drop_item` VALUES ('20162', '1', '1', '皮手套');
INSERT INTO `drop_item` VALUES ('20182', '1', '1', '手套');
INSERT INTO `drop_item` VALUES ('20205', '1', '1', '长靴');
INSERT INTO `drop_item` VALUES ('20213', '1', '1', '短统靴');
INSERT INTO `drop_item` VALUES ('20223', '1', '1', '侏儒圆盾');
INSERT INTO `drop_item` VALUES ('20237', '1', '1', '阿克海盾牌');
INSERT INTO `drop_item` VALUES ('20239', '1', '1', '小盾牌');
INSERT INTO `drop_item` VALUES ('20242', '1', '1', '大盾牌');
INSERT INTO `drop_item` VALUES ('40001', '1', '1', '灯');
INSERT INTO `drop_item` VALUES ('40002', '1', '1', '灯笼');
INSERT INTO `drop_item` VALUES ('40005', '1', '1', '蜡烛');
INSERT INTO `drop_item` VALUES ('100025', '1', '1', '银剑');
INSERT INTO `drop_item` VALUES ('100027', '1', '1', '弯刀');
INSERT INTO `drop_item` VALUES ('100098', '1', '1', '阔矛');
INSERT INTO `drop_item` VALUES ('100102', '1', '1', '露西锤');
INSERT INTO `drop_item` VALUES ('100103', '1', '1', '戟');
INSERT INTO `drop_item` VALUES ('100143', '1', '1', '战斧');
INSERT INTO `drop_item` VALUES ('100172', '1', '1', '弓');
INSERT INTO `drop_item` VALUES ('120043', '1', '1', '钢盔');
INSERT INTO `drop_item` VALUES ('120101', '1', '1', '皮甲');
INSERT INTO `drop_item` VALUES ('120149', '1', '1', '青铜盔甲');
INSERT INTO `drop_item` VALUES ('120182', '1', '1', '手套');
INSERT INTO `drop_item` VALUES ('120242', '1', '1', '大盾牌');
INSERT INTO `drop_item` VALUES ('200001', '1', '1', '欧西斯匕首');
INSERT INTO `drop_item` VALUES ('200027', '1', '1', '弯刀');
INSERT INTO `drop_item` VALUES ('200171', '1', '1', '欧西斯弓');
INSERT INTO `drop_item` VALUES ('220034', '1', '1', '欧西斯头盔');
INSERT INTO `drop_item` VALUES ('220043', '1', '1', '钢盔');
INSERT INTO `drop_item` VALUES ('220101', '1', '1', '皮甲');
INSERT INTO `drop_item` VALUES ('220115', '1', '1', '藤甲');
INSERT INTO `drop_item` VALUES ('220122', '1', '1', '鳞甲');
INSERT INTO `drop_item` VALUES ('220125', '1', '1', '链甲');
INSERT INTO `drop_item` VALUES ('220135', '1', '1', '欧西斯环甲');
INSERT INTO `drop_item` VALUES ('220136', '1', '1', '欧西斯链甲');
INSERT INTO `drop_item` VALUES ('220147', '1', '1', '银钉皮甲');
INSERT INTO `drop_item` VALUES ('220213', '1', '1', '短统靴');
INSERT INTO `drop_item` VALUES ('220237', '1', '1', '阿克海盾牌');
