/*
MySQL Data Transfer
Source Host: localhost
Source Database: l1jdb_cn
Target Host: localhost
Target Database: l1jdb_cn
Date: 2011-6-25 ÏÂÎç 12:59:53
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for william_item_update
-- ----------------------------
CREATE TABLE `william_item_update` (
  `item_id` int(11) NOT NULL DEFAULT '0',
  `count` int(11) DEFAULT NULL,
  `add_dmg` int(11) DEFAULT NULL,
  `add_dmgmodifier` int(11) DEFAULT NULL,
  `add_hitmodifier` int(11) DEFAULT NULL,
  `add_str` int(11) DEFAULT NULL,
  `add_dex` int(11) DEFAULT NULL,
  `add_int` int(11) DEFAULT NULL,
  `add_hp` int(11) DEFAULT NULL,
  `add_mp` int(11) DEFAULT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `william_item_update` VALUES ('268544552', '0', '2', '6', '2', '1', '1', '1', '0', '0');
INSERT INTO `william_item_update` VALUES ('268802537', '7', '0', '0', '0', '0', '0', '0', '10', '0');
INSERT INTO `william_item_update` VALUES ('269165152', '7', '0', '0', '0', '0', '0', '0', '0', '10');
INSERT INTO `william_item_update` VALUES ('269274655', '6', '0', '0', '0', '0', '0', '1', '0', '0');
INSERT INTO `william_item_update` VALUES ('270439317', '0', '4', '9', '3', '1', '1', '1', '0', '0');
INSERT INTO `william_item_update` VALUES ('270439320', '7', '0', '0', '0', '0', '0', '0', '10', '10');
