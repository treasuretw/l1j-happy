/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50150
Source Host           : localhost:3306
Source Database       : l1jdb_cn

Target Server Type    : MYSQL
Target Server Version : 50150
File Encoding         : 65001

Date: 2011-07-26 18:56:12
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `z_backup_shop`
-- ----------------------------
DROP TABLE IF EXISTS `z_backup_shop`;
CREATE TABLE `z_backup_shop` (
  `npc_id` int(10) unsigned NOT NULL DEFAULT '0',
  `item_id` int(10) unsigned NOT NULL DEFAULT '0',
  `order_id` int(10) unsigned NOT NULL DEFAULT '0',
  `selling_price` int(10) NOT NULL DEFAULT '-1',
  `pack_count` int(10) unsigned NOT NULL DEFAULT '0',
  `purchasing_price` int(10) NOT NULL DEFAULT '-1',
  `YB` int(10) NOT NULL DEFAULT '-1',
  `EnchantLevel` int(10) NOT NULL DEFAULT '0',
  `delete_day` int(10) NOT NULL DEFAULT '0',
  `delete_date` datetime DEFAULT NULL,
  PRIMARY KEY (`npc_id`,`item_id`,`order_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of z_backup_shop
-- ----------------------------
