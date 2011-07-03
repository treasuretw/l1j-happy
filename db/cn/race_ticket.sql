/*
MySQL Data Transfer
Source Host: localhost
Source Database: l1jdb_cn
Target Host: localhost
Target Database: l1jdb_cn
Date: 2011-6-25 ÏÂÎç 12:56:32
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for race_ticket
-- ----------------------------
CREATE TABLE `race_ticket` (
  `item_obj_id` int(11) NOT NULL,
  `round` int(7) NOT NULL,
  `allotment_percentage` double NOT NULL,
  `victory` int(1) NOT NULL,
  `runner_num` int(2) NOT NULL,
  PRIMARY KEY (`item_obj_id`,`round`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `race_ticket` VALUES ('0', '781', '0', '0', '0');
