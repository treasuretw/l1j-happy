/*
MySQL Data Transfer
Source Host: localhost
Source Database: l1jdb_cn
Target Host: localhost
Target Database: l1jdb_cn
Date: 2011-6-25 ���� 12:51:07
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for character_skills
-- ----------------------------
CREATE TABLE `character_skills` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `char_obj_id` int(10) NOT NULL DEFAULT '0',
  `skill_id` int(10) unsigned NOT NULL DEFAULT '0',
  `skill_name` varchar(45) NOT NULL DEFAULT '',
  `is_active` int(10) DEFAULT NULL,
  `activetimeleft` int(10) DEFAULT NULL,
  PRIMARY KEY (`char_obj_id`,`skill_id`),
  KEY `key_id` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=162 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `character_skills` VALUES ('1', '268471291', '1', '初级治愈术', '0', '0');
INSERT INTO `character_skills` VALUES ('2', '268471291', '2', '日光术', '0', '0');
INSERT INTO `character_skills` VALUES ('3', '268471291', '3', '保护罩', '0', '0');
INSERT INTO `character_skills` VALUES ('4', '268471291', '4', '光箭', '0', '0');
INSERT INTO `character_skills` VALUES ('5', '268471291', '5', '指定传送', '0', '0');
INSERT INTO `character_skills` VALUES ('6', '268471291', '6', '冰箭', '0', '0');
INSERT INTO `character_skills` VALUES ('7', '268471291', '7', '风刃', '0', '0');
INSERT INTO `character_skills` VALUES ('8', '268471291', '8', '神圣武器', '0', '0');
INSERT INTO `character_skills` VALUES ('9', '268471291', '87', '冲击之晕', '0', '0');
INSERT INTO `character_skills` VALUES ('10', '268471291', '88', '增幅防御', '0', '0');
INSERT INTO `character_skills` VALUES ('11', '268471291', '89', '尖刺盔甲', '0', '0');
INSERT INTO `character_skills` VALUES ('12', '268471291', '90', '坚固防护', '0', '0');
INSERT INTO `character_skills` VALUES ('13', '268471291', '91', '反击屏障', '0', '0');
INSERT INTO `character_skills` VALUES ('14', '268471291', '70', '火风暴', '0', '0');
INSERT INTO `character_skills` VALUES ('15', '268471291', '59', '冰雪暴', '0', '0');
INSERT INTO `character_skills` VALUES ('16', '268471291', '64', '魔法封印', '0', '0');
INSERT INTO `character_skills` VALUES ('17', '268471291', '44', '魔法相消术', '0', '0');
INSERT INTO `character_skills` VALUES ('18', '268471291', '49', '体力回复术', '0', '0');
INSERT INTO `character_skills` VALUES ('19', '268471291', '35', '高级治愈术', '0', '0');
INSERT INTO `character_skills` VALUES ('20', '268471291', '68', '圣结界', '0', '0');
INSERT INTO `character_skills` VALUES ('21', '269890414', '1', '初级治愈术', '0', '0');
INSERT INTO `character_skills` VALUES ('22', '269890414', '2', '日光术', '0', '0');
INSERT INTO `character_skills` VALUES ('23', '269890414', '3', '保护罩', '0', '0');
INSERT INTO `character_skills` VALUES ('24', '269890414', '4', '光箭', '0', '0');
INSERT INTO `character_skills` VALUES ('25', '269890414', '5', '指定传送', '0', '0');
INSERT INTO `character_skills` VALUES ('26', '269890414', '6', '冰箭', '0', '0');
INSERT INTO `character_skills` VALUES ('27', '269890414', '7', '风刃', '0', '0');
INSERT INTO `character_skills` VALUES ('28', '269890414', '8', '神圣武器', '0', '0');
INSERT INTO `character_skills` VALUES ('29', '269890414', '9', '解毒术', '0', '0');
INSERT INTO `character_skills` VALUES ('30', '269890414', '10', '寒冷战栗', '0', '0');
INSERT INTO `character_skills` VALUES ('31', '269890414', '11', '毒咒', '0', '0');
INSERT INTO `character_skills` VALUES ('32', '269890414', '12', '拟似魔法武器', '0', '0');
INSERT INTO `character_skills` VALUES ('33', '269890414', '13', '无所遁形术', '0', '0');
INSERT INTO `character_skills` VALUES ('34', '269890414', '14', '负重强化', '0', '0');
INSERT INTO `character_skills` VALUES ('35', '269890414', '15', '火箭', '0', '0');
INSERT INTO `character_skills` VALUES ('36', '269890414', '16', '地狱之牙', '0', '0');
INSERT INTO `character_skills` VALUES ('37', '269890414', '17', '极光雷电', '0', '0');
INSERT INTO `character_skills` VALUES ('38', '269890414', '18', '起死回生术', '0', '0');
INSERT INTO `character_skills` VALUES ('39', '269890414', '19', '中级治愈术', '0', '0');
INSERT INTO `character_skills` VALUES ('40', '269890414', '20', '闇盲咒术', '0', '0');
INSERT INTO `character_skills` VALUES ('41', '269890414', '21', '铠甲护持', '0', '0');
INSERT INTO `character_skills` VALUES ('42', '269890414', '22', '寒冰气息', '0', '0');
INSERT INTO `character_skills` VALUES ('43', '269890414', '23', '能量感测', '0', '0');
INSERT INTO `character_skills` VALUES ('44', '269890414', '24', 'none', '0', '0');
INSERT INTO `character_skills` VALUES ('45', '269890414', '25', '燃烧的火球', '0', '0');
INSERT INTO `character_skills` VALUES ('46', '269890414', '26', '通畅气脉术', '0', '0');
INSERT INTO `character_skills` VALUES ('47', '269890414', '27', '坏物术', '0', '0');
INSERT INTO `character_skills` VALUES ('48', '269890414', '28', '吸血鬼之吻', '0', '0');
INSERT INTO `character_skills` VALUES ('49', '269890414', '29', '缓速术', '0', '0');
INSERT INTO `character_skills` VALUES ('50', '269890414', '30', '岩牢', '0', '0');
INSERT INTO `character_skills` VALUES ('51', '269890414', '31', '魔法屏障', '0', '0');
INSERT INTO `character_skills` VALUES ('52', '269890414', '32', '冥想术', '0', '0');
INSERT INTO `character_skills` VALUES ('53', '269890414', '33', '木乃伊的诅咒', '0', '0');
INSERT INTO `character_skills` VALUES ('54', '269890414', '34', '极道落雷', '0', '0');
INSERT INTO `character_skills` VALUES ('55', '269890414', '35', '高级治愈术', '0', '0');
INSERT INTO `character_skills` VALUES ('56', '269890414', '36', '迷魅术', '0', '0');
INSERT INTO `character_skills` VALUES ('57', '269890414', '37', '圣洁之光', '0', '0');
INSERT INTO `character_skills` VALUES ('58', '269890414', '38', '冰锥', '0', '0');
INSERT INTO `character_skills` VALUES ('59', '269890414', '39', '魔力夺取', '0', '0');
INSERT INTO `character_skills` VALUES ('60', '269890414', '40', '黑闇之影', '0', '0');
INSERT INTO `character_skills` VALUES ('61', '269890414', '41', '造尸术', '0', '0');
INSERT INTO `character_skills` VALUES ('62', '269890414', '42', '体魄强健术', '0', '0');
INSERT INTO `character_skills` VALUES ('63', '269890414', '43', '加速术', '0', '0');
INSERT INTO `character_skills` VALUES ('64', '269890414', '44', '魔法相消术', '0', '0');
INSERT INTO `character_skills` VALUES ('65', '269890414', '45', '地裂术', '0', '0');
INSERT INTO `character_skills` VALUES ('66', '269890414', '46', '烈炎术', '0', '0');
INSERT INTO `character_skills` VALUES ('67', '269890414', '47', '弱化术', '0', '0');
INSERT INTO `character_skills` VALUES ('68', '269890414', '48', '祝福魔法武器', '0', '0');
INSERT INTO `character_skills` VALUES ('69', '269890414', '129', '魔法防御', '0', '0');
INSERT INTO `character_skills` VALUES ('70', '269890414', '130', '心灵转换', '0', '0');
INSERT INTO `character_skills` VALUES ('71', '269890414', '131', '世界树的呼唤', '0', '0');
INSERT INTO `character_skills` VALUES ('72', '269890414', '132', '三重矢', '0', '0');
INSERT INTO `character_skills` VALUES ('73', '269890414', '133', '弱化属性', '0', '0');
INSERT INTO `character_skills` VALUES ('74', '269890414', '134', '镜反射', '0', '0');
INSERT INTO `character_skills` VALUES ('75', '269890414', '135', 'none', '0', '0');
INSERT INTO `character_skills` VALUES ('76', '269890414', '136', 'none', '0', '0');
INSERT INTO `character_skills` VALUES ('77', '269890414', '137', '净化精神', '0', '0');
INSERT INTO `character_skills` VALUES ('78', '269890414', '138', '属性防御', '0', '0');
INSERT INTO `character_skills` VALUES ('79', '269890414', '139', 'none', '0', '0');
INSERT INTO `character_skills` VALUES ('80', '269890414', '140', 'none', '0', '0');
INSERT INTO `character_skills` VALUES ('81', '269890414', '141', 'none', '0', '0');
INSERT INTO `character_skills` VALUES ('82', '269890414', '142', 'none', '0', '0');
INSERT INTO `character_skills` VALUES ('83', '269890414', '143', 'none', '0', '0');
INSERT INTO `character_skills` VALUES ('84', '269890414', '144', 'none', '0', '0');
INSERT INTO `character_skills` VALUES ('85', '269890414', '145', '释放元素', '0', '0');
INSERT INTO `character_skills` VALUES ('86', '269890414', '146', '魂体转换', '0', '0');
INSERT INTO `character_skills` VALUES ('87', '269890414', '147', '单属性防御', '0', '0');
INSERT INTO `character_skills` VALUES ('88', '269890414', '148', '火焰武器', '0', '0');
INSERT INTO `character_skills` VALUES ('89', '269890414', '149', '风之神射', '0', '0');
INSERT INTO `character_skills` VALUES ('90', '269890414', '150', '风之疾走', '0', '0');
INSERT INTO `character_skills` VALUES ('91', '269890414', '151', '大地防护', '0', '0');
INSERT INTO `character_skills` VALUES ('92', '269890414', '152', '地面障碍', '0', '0');
INSERT INTO `character_skills` VALUES ('93', '269890414', '153', '魔法消除', '0', '0');
INSERT INTO `character_skills` VALUES ('94', '269890414', '154', '召唤属性精灵', '0', '0');
INSERT INTO `character_skills` VALUES ('95', '269890414', '155', '烈炎气息', '0', '0');
INSERT INTO `character_skills` VALUES ('96', '269890414', '156', '暴风之眼', '0', '0');
INSERT INTO `character_skills` VALUES ('97', '269890414', '157', '大地屏障', '0', '0');
INSERT INTO `character_skills` VALUES ('98', '269890414', '158', '生命之泉', '0', '0');
INSERT INTO `character_skills` VALUES ('99', '269890414', '159', '大地的祝福', '0', '0');
INSERT INTO `character_skills` VALUES ('100', '269890414', '160', '水之防护', '0', '0');
INSERT INTO `character_skills` VALUES ('101', '269890414', '161', '封印禁地', '0', '0');
INSERT INTO `character_skills` VALUES ('102', '269890414', '162', '召唤强力属性精灵', '0', '0');
INSERT INTO `character_skills` VALUES ('103', '269890414', '163', '烈炎武器', '0', '0');
INSERT INTO `character_skills` VALUES ('104', '269890414', '164', '生命的祝福', '0', '0');
INSERT INTO `character_skills` VALUES ('105', '269890414', '165', '生命呼唤', '0', '0');
INSERT INTO `character_skills` VALUES ('106', '269890414', '166', '暴风神射', '0', '0');
INSERT INTO `character_skills` VALUES ('107', '269890414', '167', '风之枷锁', '0', '0');
INSERT INTO `character_skills` VALUES ('108', '269890414', '168', '钢铁防护', '0', '0');
INSERT INTO `character_skills` VALUES ('109', '269890414', '169', '体能激发', '0', '0');
INSERT INTO `character_skills` VALUES ('110', '269890414', '170', '水之元气', '0', '0');
INSERT INTO `character_skills` VALUES ('111', '269890414', '171', '属性之火', '0', '0');
INSERT INTO `character_skills` VALUES ('112', '269890414', '172', '暴风疾走', '0', '0');
INSERT INTO `character_skills` VALUES ('113', '269890414', '173', '污浊之水', '0', '0');
INSERT INTO `character_skills` VALUES ('114', '269890414', '174', '精准射击', '0', '0');
INSERT INTO `character_skills` VALUES ('115', '269890414', '175', '烈焰之魂', '0', '0');
INSERT INTO `character_skills` VALUES ('116', '269890414', '176', '能量激发', '0', '0');
INSERT INTO `character_skills` VALUES ('117', '269927405', '129', '魔法防御', '0', '0');
INSERT INTO `character_skills` VALUES ('118', '269927405', '130', '心灵转换', '0', '0');
INSERT INTO `character_skills` VALUES ('119', '269927405', '131', '世界树的呼唤', '0', '0');
INSERT INTO `character_skills` VALUES ('120', '269927405', '137', '净化精神', '0', '0');
INSERT INTO `character_skills` VALUES ('121', '269927405', '138', '属性防御', '0', '0');
INSERT INTO `character_skills` VALUES ('122', '269927405', '145', '释放元素', '0', '0');
INSERT INTO `character_skills` VALUES ('123', '269927405', '146', '魂体转换', '0', '0');
INSERT INTO `character_skills` VALUES ('124', '269927405', '147', '单属性防御', '0', '0');
INSERT INTO `character_skills` VALUES ('125', '269927405', '132', '三重矢', '0', '0');
INSERT INTO `character_skills` VALUES ('126', '269927405', '133', '弱化属性', '0', '0');
INSERT INTO `character_skills` VALUES ('127', '269927405', '153', '魔法消除', '0', '0');
INSERT INTO `character_skills` VALUES ('128', '269927405', '154', '召唤属性精灵', '0', '0');
INSERT INTO `character_skills` VALUES ('129', '269927405', '161', '封印禁地', '0', '0');
INSERT INTO `character_skills` VALUES ('130', '269927405', '162', '召唤强力属性精灵', '0', '0');
INSERT INTO `character_skills` VALUES ('131', '269927405', '134', '镜反射', '0', '0');
INSERT INTO `character_skills` VALUES ('151', '269927405', '156', '暴风之眼', '0', '0');
INSERT INTO `character_skills` VALUES ('150', '269927405', '150', '风之疾走', '0', '0');
INSERT INTO `character_skills` VALUES ('149', '269927405', '149', '风之神射', '0', '0');
INSERT INTO `character_skills` VALUES ('148', '269927405', '169', '体能激发', '0', '0');
INSERT INTO `character_skills` VALUES ('147', '269927405', '168', '钢铁防护', '0', '0');
INSERT INTO `character_skills` VALUES ('143', '269927405', '151', '大地防护', '0', '0');
INSERT INTO `character_skills` VALUES ('144', '269927405', '152', '地面障碍', '0', '0');
INSERT INTO `character_skills` VALUES ('145', '269927405', '157', '大地屏障', '0', '0');
INSERT INTO `character_skills` VALUES ('146', '269927405', '159', '大地的祝福', '0', '0');
INSERT INTO `character_skills` VALUES ('152', '269927405', '166', '暴风神射', '0', '0');
INSERT INTO `character_skills` VALUES ('153', '269927405', '167', '风之枷锁', '0', '0');
INSERT INTO `character_skills` VALUES ('154', '269927405', '174', '精准射击', '0', '0');
INSERT INTO `character_skills` VALUES ('155', '268471291', '40', '黑闇之影', '0', '0');
INSERT INTO `character_skills` VALUES ('156', '268471291', '56', '疾病术', '0', '0');
INSERT INTO `character_skills` VALUES ('157', '268471291', '36', '迷魅术', '0', '0');
INSERT INTO `character_skills` VALUES ('158', '268471291', '107', '暗影之牙', '0', '0');
INSERT INTO `character_skills` VALUES ('159', '268471291', '58', '火牢', '0', '0');
INSERT INTO `character_skills` VALUES ('160', '268471291', '108', '会心一击', '0', '0');
INSERT INTO `character_skills` VALUES ('161', '268471291', '25', '燃烧的火球', '0', '0');
