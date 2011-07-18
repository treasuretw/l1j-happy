/*
MySQL Data Transfer
Source Host: localhost
Source Database: l1jdb_cn
Target Host: localhost
Target Database: l1jdb_cn
Date: 2011-7-15 œ¬ŒÁ 06:38:12
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for william_item_blend
-- ----------------------------
CREATE TABLE `william_item_blend` (
  `item_id` int(10) NOT NULL,
  `name` varchar(45) NOT NULL,
  `checkClass` int(1) NOT NULL DEFAULT '0',
  `checkLevel` int(2) NOT NULL DEFAULT '0',
  `rnd` int(10) NOT NULL DEFAULT '100',
  `checkItem` int(10) NOT NULL,
  `hpConsume` int(10) unsigned NOT NULL DEFAULT '0',
  `mpConsume` int(10) unsigned NOT NULL DEFAULT '0',
  `material` int(10) NOT NULL,
  `material_count` int(10) NOT NULL,
  `material_2` int(10) NOT NULL,
  `material_2_count` int(10) NOT NULL,
  `material_3` int(10) NOT NULL,
  `material_3_count` int(10) NOT NULL,
  `new_item` int(10) NOT NULL,
  `new_item_counts` int(10) NOT NULL,
  `new_Enchantlvl_SW` int(1) NOT NULL,
  `new_item_Enchantlvl` int(10) NOT NULL DEFAULT '0',
  `removeItem` int(1) NOT NULL DEFAULT '1',
  `message` varchar(1000) DEFAULT NULL,
  `item_Html` int(1) NOT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `william_item_blend` VALUES ('5079', 'ÂêàÊàêÊú´Êó•ÂàÄ', '2', '50', '100', '5079', '0', '0', '61', '1', '40308', '100000000', '5000', '1000000', '60', '1', '0', '10', '1', null, '0');
