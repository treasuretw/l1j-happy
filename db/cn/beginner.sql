/*
MySQL Data Transfer
Source Host: localhost
Source Database: l1jdb_cn
Target Host: localhost
Target Database: l1jdb_cn
Date: 2011-6-25 ���� 12:50:10
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for beginner
-- ----------------------------
CREATE TABLE `beginner` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `item_id` int(6) NOT NULL DEFAULT '0',
  `count` int(10) NOT NULL DEFAULT '0',
  `charge_count` int(10) NOT NULL DEFAULT '0',
  `enchantlvl` int(6) NOT NULL DEFAULT '0',
  `item_name` varchar(50) NOT NULL DEFAULT '',
  `activate` char(1) NOT NULL DEFAULT 'A',
  `bless` int(11) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `beginner` VALUES ('1', '5039', '1', '0', '0', '新手大礼包', 'A', '1');
INSERT INTO `beginner` VALUES ('2', '100004', '1', '0', '9', '祝福的匕首', 'A', '1');
INSERT INTO `beginner` VALUES ('3', '40308', '10000000', '0', '0', '金币一千万', 'A', '1');
INSERT INTO `beginner` VALUES ('4', '5000', '100000', '0', '0', '元宝十万', 'A', '1');
INSERT INTO `beginner` VALUES ('5', '40081', '100', '0', '0', '奇岩城传送', 'A', '1');
INSERT INTO `beginner` VALUES ('6', '40024', '1000', '0', '0', '古代终极体力恢复剂', 'A', '1');
INSERT INTO `beginner` VALUES ('7', '40018', '10', '0', '0', '强化 绿色药水', 'A', '1');
INSERT INTO `beginner` VALUES ('8', '40733', '20', '0', '0', '名誉货币', 'A', '1');
INSERT INTO `beginner` VALUES ('9', '49138', '30', '0', '0', '巧克力蛋糕', 'A', '1');
INSERT INTO `beginner` VALUES ('10', '40085', '10', '0', '0', '新手练级传送', 'A', '1');
INSERT INTO `beginner` VALUES ('11', '5046', '1', '0', '0', '声望查询卡', 'A', '1');
INSERT INTO `beginner` VALUES ('12', '5050', '1', '0', '0', '84级经验丹', 'A', '1');
