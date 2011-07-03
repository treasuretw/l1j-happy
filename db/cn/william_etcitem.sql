/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50150
Source Host           : localhost:3306
Source Database       : l1j-cn

Target Server Type    : MYSQL
Target Server Version : 50150
File Encoding         : 65001

Date: 2011-07-03 19:05:16
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `william_etcitem`
-- ----------------------------
DROP TABLE IF EXISTS `william_etcitem`;
CREATE TABLE `william_etcitem` (
  `item_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL DEFAULT '',
  `unidentified_name_id` varchar(45) NOT NULL DEFAULT '',
  `identified_name_id` varchar(45) NOT NULL DEFAULT '',
  `item_type` varchar(40) NOT NULL DEFAULT '',
  `use_type` varchar(20) NOT NULL DEFAULT '',
  `material` varchar(45) NOT NULL DEFAULT '',
  `weight` int(10) unsigned NOT NULL DEFAULT '0',
  `invgfx` int(10) unsigned NOT NULL DEFAULT '0',
  `grdgfx` int(10) unsigned NOT NULL DEFAULT '0',
  `itemdesc_id` int(10) unsigned NOT NULL DEFAULT '0',
  `stackable` int(10) unsigned NOT NULL DEFAULT '0',
  `max_charge_count` int(10) unsigned NOT NULL DEFAULT '0',
  `dmg_small` int(10) unsigned NOT NULL DEFAULT '0',
  `dmg_large` int(10) unsigned NOT NULL DEFAULT '0',
  `min_lvl` int(10) unsigned NOT NULL DEFAULT '0',
  `max_lvl` int(10) unsigned NOT NULL DEFAULT '0',
  `locx` int(10) unsigned NOT NULL DEFAULT '0',
  `locy` int(10) unsigned NOT NULL DEFAULT '0',
  `mapid` int(10) unsigned NOT NULL DEFAULT '0',
  `bless` int(2) unsigned NOT NULL DEFAULT '1',
  `trade` int(2) unsigned NOT NULL DEFAULT '0',
  `cant_delete` int(2) unsigned NOT NULL DEFAULT '0',
  `can_seal` int(2) unsigned NOT NULL DEFAULT '0',
  `delay_id` int(10) unsigned NOT NULL DEFAULT '0',
  `delay_time` int(10) unsigned NOT NULL DEFAULT '0',
  `delay_effect` int(10) unsigned NOT NULL DEFAULT '0',
  `food_volume` int(10) unsigned NOT NULL DEFAULT '0',
  `save_at_once` tinyint(1) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`item_id`)
) ENGINE=MyISAM AUTO_INCREMENT=600008 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of william_etcitem
-- ----------------------------
INSERT INTO `william_etcitem` VALUES ('5000', '元宝', '$5967', '$5967', 'gem', 'none', 'gemstone', '0', '3212', '22', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '1500', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5001', '10%武器攻击强化石', '$9698', '$9698', 'scroll', 'dai', 'paper', '0', '2291', '6936', '3270', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5002', '60%武器攻击强化石', '$9697', '$9697', 'scroll', 'dai', 'paper', '0', '2293', '6936', '3270', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5003', '100%武器攻击强化石', '$9696', '$9696', 'scroll', 'dai', 'paper', '0', '2295', '6936', '3270', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5004', '武器属性强化石(力量)', '$9695', '$9695', 'scroll', 'dai', 'paper', '0', '1860', '6936', '3270', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5005', '武器属性强化石(敏捷)', '$9694', '$9694', 'scroll', 'dai', 'paper', '0', '1861', '6936', '3270', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5006', '武器属性强化石(智力)', '$9693', '$9693', 'scroll', 'dai', 'paper', '0', '1862', '6936', '3270', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5007', '盔甲强化石(HP)', '$9692', '$9692', 'scroll', 'dai', 'paper', '0', '1702', '6936', '3270', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5008', '盔甲强化石(MP)', '$9691', '$9691', 'scroll', 'dai', 'paper', '0', '1706', '6936', '3270', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5009', '自然恢复药水', '$8022', '$8022', 'potion', 'normal', 'glass', '7800', '3460', '20', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5010', '冥想恢复药水', '$8023', '$8023', 'potion', 'normal', 'glass', '7800', '3459', '20', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5011', '生命升华药水', '$8024', '$8024', 'potion', 'normal', 'glass', '7800', '3457', '20', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5012', '魔法升华药水', '$8025', '$8025', 'potion', 'normal', 'glass', '7800', '3458', '20', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5013', '魔法抵抗药水', '$8026', '$8026', 'potion', 'normal', 'glass', '7800', '3456', '20', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5014', '保护卷轴', '$5985', '$5985', 'potion', 'normal', 'vegetation', '0', '3447', '2019', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '1', '0', '0', '0', '0', '0', '1');
INSERT INTO `william_etcitem` VALUES ('5015', '力量药水', '$5976', '$5976', 'potion', 'normal', 'glass', '7800', '3450', '20', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5016', '坚韧药水', '$5977', '$5977', 'potion', 'normal', 'glass', '7800', '3450', '20', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5017', '暴怒药水', '$5978', '$5978', 'potion', 'normal', 'glass', '7800', '3450', '20', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5018', '智力药水', '$5979', '$5979', 'potion', 'normal', 'glass', '7800', '3451', '20', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5019', '活化药水', '$5980', '$5980', 'potion', 'normal', 'glass', '7800', '3451', '20', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5020', '巫师药水', '$5981', '$5981', 'potion', 'normal', 'glass', '7800', '3451', '20', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5021', '突袭药水', '$5982', '$5982', 'potion', 'normal', 'glass', '7800', '3452', '20', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5022', '强力恢复药水', '$5983', '$5983', 'potion', 'normal', 'glass', '7800', '3452', '20', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5023', '疯狂力量药水', '$5984', '$5984', 'potion', 'normal', 'glass', '7800', '3452', '20', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5024', '经验书1%', '$9690', '$9690', 'potion', 'normal', 'glass', '0', '2019', '22', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5025', '经验书2%', '$9689', '$9689', 'potion', 'normal', 'glass', '0', '2019', '22', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5026', '经验书3%', '$9688', '$9688', 'potion', 'normal', 'glass', '0', '2019', '22', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5027', '暴击药水lv1', '$9687', '$9687', 'potion', 'normal', 'glass', '7800', '3450', '20', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5028', '暴击药水lv2', '$9686', '$9686', 'potion', 'normal', 'glass', '7800', '3450', '20', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5029', '暴击药水lv3', '$9685', '$9685', 'potion', 'normal', 'glass', '7800', '3450', '20', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5030', '1.5倍经验药水', '$9684', '$9684', 'potion', 'normal', 'glass', '7800', '3450', '20', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5031', '2.0倍经验药水', '$9683', '$9683', 'potion', 'normal', 'glass', '7800', '3450', '20', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5032', '2.5倍经验药水', '$9682', '$9682', 'potion', 'normal', 'glass', '7800', '3450', '20', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5033', '+10hp', '$9681', '$9681', 'potion', 'normal', 'glass', '7800', '3450', '20', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5034', '+50hp', '$9680', '$9680', 'potion', 'normal', 'glass', '7800', '3450', '20', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5035', '+70hp', '$9679', '$9679', 'potion', 'normal', 'glass', '7800', '3450', '20', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5036', '+10mp', '$9678', '$9678', 'potion', 'normal', 'glass', '7800', '3450', '20', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5037', '+50mp', '$9677', '$9677', 'potion', 'normal', 'glass', '7800', '3450', '20', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5038', '+70mp', '$9676', '$9676', 'potion', 'normal', 'glass', '7800', '3450', '20', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5039', '新手礼包', '$8058', '$8058', 'treasure_box', 'normal', 'leather', '0', '2681', '3963', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('6666', '副本钥匙', '副本钥匙', '测试', 'potion', 'normal', 'glass', '0', '3559', '45', '1342', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '2', '500', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5040', '在线礼物箱', '$9699', '$9699', 'treasure_box', 'normal', 'gold', '0', '3562', '6928', '3262', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '0', '0', '1000', '0', '0', '1');
INSERT INTO `william_etcitem` VALUES ('5041', '管理员证', '$9675', '$9675', 'material', 'normal', 'vegetation', '8000', '2443', '79', '279', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '2', '1000', '0', '0', '1');
INSERT INTO `william_etcitem` VALUES ('5042', '死神邀请函(+1 声望 )', '$9674', '$9674', 'material', 'normal', 'vegetation', '8000', '2372', '79', '279', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '2', '1000', '0', '0', '1');
INSERT INTO `william_etcitem` VALUES ('5043', '死神邀请函(+3 声望 )', '$9673', '$9673', 'material', 'normal', 'vegetation', '8000', '2373', '79', '279', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '2', '1000', '0', '0', '1');
INSERT INTO `william_etcitem` VALUES ('5044', '死神邀请函(+5 声望 )', '$9672', '$9672', 'material', 'normal', 'vegetation', '8000', '2374', '79', '279', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '2', '1000', '0', '0', '1');
INSERT INTO `william_etcitem` VALUES ('5045', '死神邀请函(+10声望 )', '$9671', '$9671', 'material', 'normal', 'vegetation', '8000', '2375', '79', '279', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '2', '1000', '0', '0', '1');
INSERT INTO `william_etcitem` VALUES ('5046', '声望查询卡', '$9670', '$9670', 'material', 'normal', 'vegetation', '0', '3618', '79', '279', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '2', '1000', '0', '0', '1');
INSERT INTO `william_etcitem` VALUES ('5047', '会员证明【VIP1】', '$9669', '$9669', 'material', 'normal', 'vegetation', '0', '2372', '79', '279', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '2', '1000', '0', '0', '1');
INSERT INTO `william_etcitem` VALUES ('5048', '会员证明【VIP2】', '$9668', '$9668', 'material', 'normal', 'vegetation', '0', '2373', '79', '279', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '2', '1000', '0', '0', '1');
INSERT INTO `william_etcitem` VALUES ('5049', '会员证明【VIP3】', '$9667', '$9667', 'material', 'normal', 'vegetation', '0', '2374', '79', '279', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '2', '1000', '0', '0', '1');
INSERT INTO `william_etcitem` VALUES ('5050', '经验丹', '$9666', '$9666', 'other', 'normal', 'wood', '12000', '2639', '22', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '1', '0', '0', '0', '0', '0', '1');
INSERT INTO `william_etcitem` VALUES ('5051', '经验王', '$9665', '$9665', 'other', 'normal', 'wood', '12000', '3397', '22', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '1', '0', '0', '0', '0', '0', '1');
INSERT INTO `william_etcitem` VALUES ('5052', '高级经验王', '$9664', '$9664', 'other', 'normal', 'wood', '12000', '3393', '22', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '1', '0', '0', '0', '0', '0', '1');
INSERT INTO `william_etcitem` VALUES ('5053', '装备保护卷轴', '$5837', '$5837', 'scroll', 'dai', 'paper', '0', '2440', '2019', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '1', '0', '0', '0', '0', '0', '1');
INSERT INTO `william_etcitem` VALUES ('5054', '彩票', '$9663', '$9663', 'potion', 'normal', 'vegetation', '0', '3447', '2019', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '1', '0', '0', '0', '0', '0', '1');
INSERT INTO `william_etcitem` VALUES ('5055', '春哥证', '$9662', '$9662', 'scroll', 'res', 'paper', '630', '2016', '22', '28', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5056', '百分百武卷[防爆]', '$9661', '$9661', 'scroll', 'dai', 'wood', '0', '2971', '6936', '3270', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5057', '百分百防卷[防爆]', '$9660', '$9660', 'scroll', 'zel', 'wood', '0', '2969', '6936', '3270', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5058', '自解卡卷轴', '$9659', '$9659', 'scroll', 'normal', 'paper', '630', '3006', '22', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '30000', '0', '0', '1');
INSERT INTO `william_etcitem` VALUES ('5059', '变性药水', '$9658', '$9658', 'potion', 'normal', 'glass', '1000', '2242', '2983', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `william_etcitem` VALUES ('5060', '洗血药水', '$9657', '$9657', 'food', 'normal', 'vegetation', '630', '3263', '22', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `william_etcitem` VALUES ('5061', '画面更新卷轴', '$9656', '$9656', 'scroll', 'normal', 'paper', '630', '3073', '22', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '30000', '0', '0', '1');
INSERT INTO `william_etcitem` VALUES ('5062', 'BOSS雷达', '$9655', '$9655', 'other', 'normal', 'bone', '630', '3227', '22', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '30000', '0', '0', '1');
INSERT INTO `william_etcitem` VALUES ('5063', '万能药水箱', '$9654', '$9654', 'treasure_box', 'normal', 'gold', '0', '2011', '7203', '3584', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '0', '0', '1000', '0', '0', '1');
INSERT INTO `william_etcitem` VALUES ('5064', '65级奖励箱', '$9653', '$9653', 'treasure_box', 'normal', 'gold', '0', '2011', '7203', '3584', '1', '0', '0', '0', '65', '0', '0', '0', '0', '1', '1', '1', '0', '0', '1000', '0', '0', '1');
INSERT INTO `william_etcitem` VALUES ('5065', '75级奖励箱', '$9652', '$9652', 'treasure_box', 'normal', 'gold', '0', '2011', '7203', '3584', '1', '0', '0', '0', '75', '0', '0', '0', '0', '1', '1', '1', '0', '0', '1000', '0', '0', '1');
INSERT INTO `william_etcitem` VALUES ('5066', '82级奖励箱', '$9651', '$9651', 'treasure_box', 'normal', 'gold', '0', '2011', '7203', '3584', '1', '0', '0', '0', '82', '0', '0', '0', '0', '1', '1', '1', '0', '0', '1000', '0', '0', '1');
INSERT INTO `william_etcitem` VALUES ('5067', '85级奖励箱', '$9650', '$9650', 'treasure_box', 'normal', 'gold', '0', '2011', '7203', '3584', '1', '0', '0', '0', '85', '0', '0', '0', '0', '1', '1', '1', '0', '0', '1000', '0', '0', '1');
INSERT INTO `william_etcitem` VALUES ('5068', '88级奖励箱', '$9649', '$9649', 'treasure_box', 'normal', 'gold', '0', '2011', '7203', '3584', '1', '0', '0', '0', '88', '0', '0', '0', '0', '1', '1', '1', '0', '0', '1000', '0', '0', '1');
INSERT INTO `william_etcitem` VALUES ('5069', '90级奖励箱', '$9648', '$9648', 'treasure_box', 'normal', 'gold', '0', '2011', '7203', '3584', '1', '0', '0', '0', '90', '0', '0', '0', '0', '1', '1', '1', '0', '0', '1000', '0', '0', '1');
INSERT INTO `william_etcitem` VALUES ('5070', '92级奖励箱', '$9647', '$9647', 'treasure_box', 'normal', 'gold', '0', '2011', '7203', '3584', '1', '0', '0', '0', '92', '0', '0', '0', '0', '1', '1', '1', '0', '0', '1000', '0', '0', '1');
INSERT INTO `william_etcitem` VALUES ('5071', '副本钥匙', '$9646', '$9646', 'scroll', 'normal', 'glass', '0', '1025', '5055', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `william_etcitem` VALUES ('5074', '第三关钥匙', '第三关钥匙', '测试', 'scroll', 'normal', 'glass', '0', '1025', '22', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `william_etcitem` VALUES ('5072', '第一关钥匙', '第一关钥匙', '测试', 'scroll', 'normal', 'glass', '0', '1025', '22', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `william_etcitem` VALUES ('5073', '第二关钥匙', '第二关钥匙', '测试', 'scroll', 'normal', 'glass', '0', '1025', '22', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `william_etcitem` VALUES ('6000', '传送魔杖', '传送魔杖', '传送魔杖', 'wand', 'spell_long', 'wood', '7000', '116', '28', '20', '0', '15', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '6', '1000', '0', '0', '1');
INSERT INTO `william_etcitem` VALUES ('5075', '殷海薩的祝福(增加25%)', '殷海薩的祝福(增加25%)', '', 'other', 'normal', 'gemstone', '0', '3725', '3963', '4168', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5076', '殷海薩的祝福(增加50%)', '殷海薩的祝福(增加50%)', '', 'other', 'normal', 'gemstone', '0', '3727', '3963', '4167', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `william_etcitem` VALUES ('5077', '殷海薩的祝福(增加100%)', '殷海薩的祝福(增加100%)', '', 'other', 'normal', 'gemstone', '0', '3721', '3963', '4166', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
