/*
MySQL Data Transfer
Source Host: localhost
Source Database: l1jdb_cn
Target Host: localhost
Target Database: l1jdb_cn
Date: 2011-6-25 ���� 01:00:01
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for william_reward
-- ----------------------------
CREATE TABLE `william_reward` (
  `id` int(10) NOT NULL,
  `註解` varchar(45) DEFAULT NULL,
  `level` int(10) NOT NULL,
  `give_royal` int(10) NOT NULL,
  `give_knight` int(10) NOT NULL,
  `give_mage` int(10) NOT NULL,
  `give_elf` int(10) NOT NULL,
  `give_darkelf` int(10) NOT NULL,
  `give_DragonKnight` int(10) NOT NULL,
  `give_Illusionist` int(10) NOT NULL,
  `getItem` varchar(45) NOT NULL,
  `count` varchar(45) NOT NULL,
  `enchantlvl` varchar(45) NOT NULL,
  `quest_id` int(10) NOT NULL,
  `quest_step` int(10) NOT NULL,
  `message` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='InnoDB free:17408 KB';

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `william_reward` VALUES ('1', '黑妖奖励：名誉货币50个、+7死亡之指', '50', '0', '0', '0', '0', '1', '0', '0', '40733,13', '50,1', '0,7', '20001', '255', '恭喜你升到５０级，给予你奖励物品！');
INSERT INTO `william_reward` VALUES ('2', '妖精奖励：名誉货币50个、+7赤焰之剑、+7赤焰之弓', '50', '0', '0', '0', '1', '0', '0', '0', '40733,50,184', '50,1,1', '0,7,7', '20001', '255', '恭喜你升到５０级，给予你奖励物品！');
INSERT INTO `william_reward` VALUES ('3', '法师奖励：名誉货币50个、+7玛那水晶球', '50', '0', '0', '1', '0', '0', '0', '0', '40733,20225', '50,1', '0,7', '20001', '255', '恭喜你升到５０级，给予你奖励物品！');
INSERT INTO `william_reward` VALUES ('4', '骑士奖励：名誉货币50个、+7黑焰之剑', '50', '0', '1', '0', '0', '0', '0', '0', '40733,56', '50,1', '0,7', '20001', '255', '恭喜你升到５０级，给予你奖励物品！');
INSERT INTO `william_reward` VALUES ('5', '王族奖励：名誉货币50个、+7黃金权杖', '50', '1', '0', '0', '0', '0', '0', '0', '40733,51', '50,1', '0,7', '20001', '255', '恭喜你升到５０级，给予你奖励物品！');
INSERT INTO `william_reward` VALUES ('6', '龙骑士奖励：名誉货币50个、+7龙骑士的双手剑', '50', '0', '0', '0', '0', '0', '1', '0', '40733,275', '50,1', '0,7', '20001', '255', '恭喜你升到５０级，给予你奖励物品！');
INSERT INTO `william_reward` VALUES ('7', '幻术師奖励：名誉货币50个、+7幻术士魔杖', '50', '0', '0', '0', '0', '0', '0', '1', '40733,269', '50,1', '0,7', '20001', '255', '恭喜你升到５０级，给予你奖励物品！');
INSERT INTO `william_reward` VALUES ('8', '名誉货币100个', '60', '1', '1', '1', '1', '1', '1', '1', '40733', '100', '0', '20002', '255', '恭喜你升到６０级，给予你奖励物品！');
