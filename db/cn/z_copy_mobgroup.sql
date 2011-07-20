/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50150
Source Host           : localhost:3306
Source Database       : happy-weixiugai

Target Server Type    : MYSQL
Target Server Version : 50150
File Encoding         : 65001

Date: 2011-07-20 20:24:11
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `z_copy_mobgroup`
-- ----------------------------
DROP TABLE IF EXISTS `z_copy_mobgroup`;
CREATE TABLE `z_copy_mobgroup` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `note` varchar(255) NOT NULL DEFAULT '',
  `remove_group_if_leader_die` int(10) unsigned NOT NULL DEFAULT '0',
  `leader_id` int(10) unsigned NOT NULL DEFAULT '0',
  `minion1_id` int(10) unsigned NOT NULL DEFAULT '0',
  `minion1_count` int(10) unsigned NOT NULL DEFAULT '0',
  `minion2_id` int(10) unsigned NOT NULL DEFAULT '0',
  `minion2_count` int(10) unsigned NOT NULL DEFAULT '0',
  `minion3_id` int(10) unsigned NOT NULL DEFAULT '0',
  `minion3_count` int(10) unsigned NOT NULL DEFAULT '0',
  `minion4_id` int(10) unsigned NOT NULL DEFAULT '0',
  `minion4_count` int(10) unsigned NOT NULL DEFAULT '0',
  `minion5_id` int(10) unsigned NOT NULL DEFAULT '0',
  `minion5_count` int(10) unsigned NOT NULL DEFAULT '0',
  `minion6_id` int(10) unsigned NOT NULL DEFAULT '0',
  `minion6_count` int(10) unsigned NOT NULL DEFAULT '0',
  `minion7_id` int(10) unsigned NOT NULL DEFAULT '0',
  `minion7_count` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4038 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of z_copy_mobgroup
-- ----------------------------
INSERT INTO `z_copy_mobgroup` VALUES ('77', '殘暴的骷髏斧兵(3)', '0', '45402', '45402', '2', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('78', '恐怖的鋼鐵高崙(3)', '0', '45479', '45479', '2', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('79', '火燄之靈魂(3)', '0', '45519', '45519', '2', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('80', '幼龍(3)', '0', '45496', '45496', '2', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('81', '骨龍(3)', '0', '45522', '45522', '2', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('82', '火燄之魔法師(3)', '0', '45480', '45480', '2', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('4001', '妖精森林-蘑菇(2)', '0', '45029', '45029', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('4002', '妖精森林-白兔子(2)', '0', '45010', '45011', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('4003', '妖精森林-茶色兔子(2)', '0', '45012', '45013', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('4004', '妖精森林-黑色兔子(2)', '0', '48000', '48001', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('4005', '妖魔森林-妖魔密使+妖魔密使護衛兵(2)', '1', '46157', '46160', '2', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('4006', '妖魔森林-妖魔巡守', '0', '45138', '45138', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('4007', '妖魔森林-卡司特王+卡司特', '0', '45346', '45213', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('4008', '古魯丁-狼人+萊肯', '0', '45024', '45173', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('4009', '古魯丁-妖魔密使+妖魔密使護衛兵(2)', '1', '46158', '46160', '2', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('4010', '古魯丁-哥布林(2)', '0', '45008', '45008', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('4011', '豬(3)', '0', '70983', '70983', '2', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('4012', '母雞(5)', '0', '70981', '70981', '4', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('4013', '鴨子(3)', '0', '45015', '45015', '2', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('4014', '古魯丁-妖魔+妖魔鬥士', '0', '45009', '45082', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('4015', '古魯丁-人形殭屍(8)', '0', '45065', '45065', '7', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('4016', '古魯丁-黑騎士搜索隊(2)', '0', '81066', '81066', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('4017', '風木-妖魔密使+妖魔密使護衛兵(2)', '1', '46159', '46160', '2', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('4018', '風木沙漠-巨大兵蟻(3)', '0', '45190', '45190', '2', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('4019', '風木沙漠-巨大兵蟻(1)+巨蟻(4)', '0', '45190', '45115', '4', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('4020', '銀騎士村-哥布林+哈柏哥布林', '0', '45008', '45140', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('4021', '銀騎士村-黑騎士(2)', '0', '45165', '45165', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('4022', '肯特-哈柏哥布林(2)', '0', '45140', '45140', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('4023', '肯特-哥布林(2)', '0', '45008', '45008', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('4024', '威頓-強盜(錘)+強盜(弓)', '0', '45329', '45276', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('4025', '威頓-強盜(斧)+強盜(弓)', '0', '81080', '45276', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('4026', '威頓-強盜頭目+強盜(刀)+強盜(斧)', '0', '45370', '45324', '1', '81080', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('4027', '歐瑞-侏儒+侏儒戰士', '0', '45041', '45092', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('4028', '龍之谷-哈維(2)', '0', '45067', '45264', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('4029', '火龍窟-火炎蛋(2)', '0', '45206', '45206', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('4030', '亞丁-黑兔子(2)+小黑兔(3)', '0', '48000', '48000', '1', '48001', '3', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('4031', '亞丁-蜥蜴人(2)', '0', '45144', '45144', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('4032', '亞丁-冰原狼人(2)', '0', '45079', '45079', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('4033', '亞丁-白巨人(2)', '0', '45351', '45351', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('4034', '亞丁-白巨人(1)+黃巨人(1)', '0', '45351', '45337', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('4035', '亞丁-黃巨人(2)', '0', '45337', '45337', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('4036', '亞丁-黃巨人(1)+黑巨人(1)', '0', '45337', '45295', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `z_copy_mobgroup` VALUES ('4037', '歐瑞-艾爾摩將軍+士兵+法師', '0', '45248', '45216', '1', '45224', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
