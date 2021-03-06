/*
MySQL Data Transfer
Source Host: localhost
Source Database: l1jdb_cn
Target Host: localhost
Target Database: l1jdb_cn
Date: 2011-6-25 ÏÂÎç 12:57:02
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for shop_sell_price
-- ----------------------------
CREATE TABLE `shop_sell_price` (
  `item_id` int(10) unsigned NOT NULL,
  `name` char(64) DEFAULT NULL,
  `sell_price` int(10) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `shop_sell_price` VALUES ('52', 'åæå', '1800');
INSERT INTO `shop_sell_price` VALUES ('100', 'è¦ä¸å¥¥éåé²æ ¹çè§', '96000');
INSERT INTO `shop_sell_price` VALUES ('101', 'ææ¯å¡å·´å¾·ç', '1');
INSERT INTO `shop_sell_price` VALUES ('102', 'é²è¥¿é¤', '100');
INSERT INTO `shop_sell_price` VALUES ('103', 'æ', '100');
INSERT INTO `shop_sell_price` VALUES ('104', 'æ³ä¸', '4000');
INSERT INTO `shop_sell_price` VALUES ('105', 'è±¡çå¡é¿ç', '1');
INSERT INTO `shop_sell_price` VALUES ('106', 'è´å¡åé', '5000');
INSERT INTO `shop_sell_price` VALUES ('107', 'æ·±çº¢é¿ç', '25000');
INSERT INTO `shop_sell_price` VALUES ('108', 'å¤±å»é­åçæ¶é­é°å', '10');
INSERT INTO `shop_sell_price` VALUES ('109', 'å¤±å»é­åçå·´é£ç¹é­æ', '10');
INSERT INTO `shop_sell_price` VALUES ('110', 'å¤±å»é­åçå·´åæ¯é­æ', '10');
INSERT INTO `shop_sell_price` VALUES ('111', 'å¤±å»é­åçå°ä¹å¥³çé­æ', '10');
INSERT INTO `shop_sell_price` VALUES ('115', 'æ°´æ¶é­æ', '200');
INSERT INTO `shop_sell_price` VALUES ('116', 'é»æ³å¸ä¹æ', '200');
INSERT INTO `shop_sell_price` VALUES ('117', 'è¾éé­æ', '4950');
INSERT INTO `shop_sell_price` VALUES ('118', 'æ¼é»æ°´æ¶ç', '11000');
INSERT INTO `shop_sell_price` VALUES ('119', 'æ¶é­é°å', '10000');
INSERT INTO `shop_sell_price` VALUES ('120', 'è±¡çå¡é­æ', '14351');
INSERT INTO `shop_sell_price` VALUES ('121', 'å°ä¹å¥³çé­æ', '10000');
INSERT INTO `shop_sell_price` VALUES ('122', 'ææ¯å¡å·´å¾·é­æ', '14351');
INSERT INTO `shop_sell_price` VALUES ('123', 'å·´åæ¯é­æ', '1');
INSERT INTO `shop_sell_price` VALUES ('124', 'å·´é£ç¹é­æ', '22222');
INSERT INTO `shop_sell_price` VALUES ('125', 'å·«æ¯é­æ³æ', '2500');
INSERT INTO `shop_sell_price` VALUES ('126', 'çé£é­æ', '22222');
INSERT INTO `shop_sell_price` VALUES ('127', 'é¢éçé£é­æ', '22222');
INSERT INTO `shop_sell_price` VALUES ('128', 'æ©¡æ¨é­æ³æ', '100');
INSERT INTO `shop_sell_price` VALUES ('129', 'ç¾åºé­æ³æ', '500');
INSERT INTO `shop_sell_price` VALUES ('130', 'çº¢æ°´æ¶é­æ', '2500');
INSERT INTO `shop_sell_price` VALUES ('131', 'åéé­æ³æ', '330');
INSERT INTO `shop_sell_price` VALUES ('132', 'ç¥å®é­æ', '2750');
INSERT INTO `shop_sell_price` VALUES ('133', 'å¤ä»£äººçæºæ§', '15000');
INSERT INTO `shop_sell_price` VALUES ('134', 'å£æ¶é­æ', '15000');
INSERT INTO `shop_sell_price` VALUES ('135', 'ä¸ä¸ºäººç¥çæ§', '3000');
INSERT INTO `shop_sell_price` VALUES ('136', 'æ§', '1');
INSERT INTO `shop_sell_price` VALUES ('137', 'äºè¿', '1');
INSERT INTO `shop_sell_price` VALUES ('138', 'æ¨æ£', '1');
INSERT INTO `shop_sell_price` VALUES ('139', 'å¼è±å°', '22');
INSERT INTO `shop_sell_price` VALUES ('140', 'éé¤', '17');
INSERT INTO `shop_sell_price` VALUES ('141', 'æé¤', '33');
INSERT INTO `shop_sell_price` VALUES ('142', 'é¶æ§', '44');
INSERT INTO `shop_sell_price` VALUES ('143', 'ææ§', '99');
INSERT INTO `shop_sell_price` VALUES ('144', 'ä¾åéæ§', '500');
INSERT INTO `shop_sell_price` VALUES ('145', 'çæå£«æ§', '500');
INSERT INTO `shop_sell_price` VALUES ('146', 'æµæé¤', '770');
INSERT INTO `shop_sell_price` VALUES ('147', 'è±¡çå¡æ§å¤´', '500');
INSERT INTO `shop_sell_price` VALUES ('148', 'å·¨æ§', '5000');
INSERT INTO `shop_sell_price` VALUES ('149', 'çäººæ§å¤´', '3850');
INSERT INTO `shop_sell_price` VALUES ('150', 'å¤©ç¶ä¹æ', '1');
INSERT INTO `shop_sell_price` VALUES ('151', 'æ¶é­æ§å¤´', '14300');
INSERT INTO `shop_sell_price` VALUES ('152', 'ééé¢çª', '100');
INSERT INTO `shop_sell_price` VALUES ('153', 'é¢éé¢çª', '1470');
INSERT INTO `shop_sell_price` VALUES ('154', 'æå½±é¢çª', '10000');
INSERT INTO `shop_sell_price` VALUES ('155', 'é­å½åçä¹çª', '1000');
INSERT INTO `shop_sell_price` VALUES ('156', 'è±¡çå¡é¢çª', '2');
INSERT INTO `shop_sell_price` VALUES ('157', 'é¶åé¢çª', '2500');
INSERT INTO `shop_sell_price` VALUES ('158', 'é»æé¢çª', '15000');
INSERT INTO `shop_sell_price` VALUES ('159', 'ç­é¢çª', '1');
INSERT INTO `shop_sell_price` VALUES ('160', 'å½çé¢çª', '3000');
INSERT INTO `shop_sell_price` VALUES ('161', 'å¤§é©¬å£«é©é¢çª', '1000');
INSERT INTO `shop_sell_price` VALUES ('162', 'å¹½æé¢çª', '1500');
INSERT INTO `shop_sell_price` VALUES ('163', 'å·´å°å¡é¢çª', '1500');
INSERT INTO `shop_sell_price` VALUES ('164', 'æé»é¢çª', '1500');
INSERT INTO `shop_sell_price` VALUES ('165', 'æ¶é­é¢çª', '1500');
INSERT INTO `shop_sell_price` VALUES ('166', 'æ¨ä¹é¢çª', '2500');
INSERT INTO `shop_sell_price` VALUES ('167', 'åå°å° è¢«éå¿çå¼©æª', '120000');
INSERT INTO `shop_sell_price` VALUES ('168', 'é»æåå­å¼', '2000');
INSERT INTO `shop_sell_price` VALUES ('169', 'çäººä¹å¼', '2500');
INSERT INTO `shop_sell_price` VALUES ('170', 'ç²¾çµå¼', '1');
INSERT INTO `shop_sell_price` VALUES ('171', 'æ¬§è¥¿æ¯å¼', '1');
INSERT INTO `shop_sell_price` VALUES ('172', 'å¼', '1');
INSERT INTO `shop_sell_price` VALUES ('173', 'ç­å¼', '1');
INSERT INTO `shop_sell_price` VALUES ('174', 'è±¡çå¡ç³å¼', '1');
INSERT INTO `shop_sell_price` VALUES ('175', 'è±¡çå¡é¿å¼', '1');
INSERT INTO `shop_sell_price` VALUES ('176', 'ææ¯å¡å·´å¾·å¼', '1');
INSERT INTO `shop_sell_price` VALUES ('177', 'å¹½æåå­å¼', '1');
INSERT INTO `shop_sell_price` VALUES ('178', 'å¯éåå­å¼', '1');
INSERT INTO `shop_sell_price` VALUES ('179', 'å¤ä»£å¦ç²¾å¼©æª', '9500');
INSERT INTO `shop_sell_price` VALUES ('180', 'åå­å¼', '2500');
INSERT INTO `shop_sell_price` VALUES ('181', 'å°¤ç±³å¼', '3000');
INSERT INTO `shop_sell_price` VALUES ('182', 'å¤èçå¼©æª', '10000');
INSERT INTO `shop_sell_price` VALUES ('183', 'å¹»è±¡ä¹å¼', '1');
INSERT INTO `shop_sell_price` VALUES ('184', 'èµ¤ç°ä¹å¼', '3000');
INSERT INTO `shop_sell_price` VALUES ('185', 'æ¶é­åå­å¼', '3500');
INSERT INTO `shop_sell_price` VALUES ('186', 'çäºå¾åå­å¼', '2500');
INSERT INTO `shop_sell_price` VALUES ('187', 'ææ¯å¡å·´å¾·åå­å¼', '10000');
INSERT INTO `shop_sell_price` VALUES ('188', 'ææ¯å¡å·´å¾·éåå­å¼', '10000');
INSERT INTO `shop_sell_price` VALUES ('189', 'æé»åå­å¼', '10000');
INSERT INTO `shop_sell_price` VALUES ('190', 'æ²åä¹å¼', '1');
INSERT INTO `shop_sell_price` VALUES ('191', 'æ°´ä¹ç«ç´', '1');
INSERT INTO `shop_sell_price` VALUES ('192', 'æ°´ç²¾çµä¹å¼', '1');
INSERT INTO `shop_sell_price` VALUES ('193', 'éæç²', '1');
INSERT INTO `shop_sell_price` VALUES ('194', 'çéæç²', '1');
INSERT INTO `shop_sell_price` VALUES ('206', 'é»æç²¾çµä¹å', '1');
INSERT INTO `shop_sell_price` VALUES ('208', 'é­æ³ç²¾çµç­å', '1');
INSERT INTO `shop_sell_price` VALUES ('209', 'ä½è´¨ç²¾çµç­å', '1000');
INSERT INTO `shop_sell_price` VALUES ('211', 'ç¬è§å½ä¹è§', '2');
INSERT INTO `shop_sell_price` VALUES ('214', 'IDï¼å¦ç²¾å¼', '2');
INSERT INTO `shop_sell_price` VALUES ('215', 'ææ·ç²¾çµå¼', '2');
INSERT INTO `shop_sell_price` VALUES ('216', 'å¦ç²¾ä¹å¼', '2');
INSERT INTO `shop_sell_price` VALUES ('219', 'é©¬æ®åçæ©ç½', '2');
INSERT INTO `shop_sell_price` VALUES ('220', 'æ³å¸ä¹æ', '2');
INSERT INTO `shop_sell_price` VALUES ('223', 'ç¥ç§é­æ', '2');
INSERT INTO `shop_sell_price` VALUES ('224', 'è±¡çå¡é­æ', '2');
INSERT INTO `shop_sell_price` VALUES ('225', 'çæä¹å', '2');
INSERT INTO `shop_sell_price` VALUES ('226', 'éªå£«ä¹å', '2');
INSERT INTO `shop_sell_price` VALUES ('228', 'å£æå£«ä¹å', '0');
INSERT INTO `shop_sell_price` VALUES ('230', 'å± æçè¡è²å·¨å', '0');
INSERT INTO `shop_sell_price` VALUES ('20003', 'é¢éå¤´ç', '2500');
INSERT INTO `shop_sell_price` VALUES ('20006', 'éªå£«é¢ç²', '2500');
INSERT INTO `shop_sell_price` VALUES ('20011', 'æé­æ³å¤´ç', '1500');
INSERT INTO `shop_sell_price` VALUES ('20013', 'ææ·é­æ³å¤´ç', '11000');
INSERT INTO `shop_sell_price` VALUES ('20014', 'æ²»æé­æ³å¤´ç', '7500');
INSERT INTO `shop_sell_price` VALUES ('20015', 'åéé­æ³å¤´ç', '9000');
INSERT INTO `shop_sell_price` VALUES ('20018', 'é©¬åºå°ä¹å¸½', '5000');
INSERT INTO `shop_sell_price` VALUES ('20019', 'çå ', '1000');
INSERT INTO `shop_sell_price` VALUES ('20020', 'æ­¦å®å¤´ç', '15000');
INSERT INTO `shop_sell_price` VALUES ('20021', 'ç²¾çµææ·å¤´ç', '1');
INSERT INTO `shop_sell_price` VALUES ('20022', 'å·´å°å¡å¤´ç', '2500');
INSERT INTO `shop_sell_price` VALUES ('20023', 'é£ä¹å¤´ç', '2500');
INSERT INTO `shop_sell_price` VALUES ('20024', 'åçå¤´ç', '2500');
INSERT INTO `shop_sell_price` VALUES ('20025', 'å·´åçä¹å¸½', '2500');
INSERT INTO `shop_sell_price` VALUES ('20026', 'å¤ä¹è§é', '110');
INSERT INTO `shop_sell_price` VALUES ('20027', 'çº¢éªå£«å¤´å·¾', '2500');
INSERT INTO `shop_sell_price` VALUES ('20028', 'è±¡çå¡ç®å¤´ç', '0');
INSERT INTO `shop_sell_price` VALUES ('20029', 'è¥¿çä¹å¸½', '2500');
INSERT INTO `shop_sell_price` VALUES ('20030', 'ç¥å®å¤´é¥°', '3000');
INSERT INTO `shop_sell_price` VALUES ('20031', 'ç«ç°ä¹å½±å¤´ç', '2500');
INSERT INTO `shop_sell_price` VALUES ('20032', 'é»æå¤´é¥°', '2500');
INSERT INTO `shop_sell_price` VALUES ('20033', 'è¾å°ç©çç¥ç¦', '5000');
INSERT INTO `shop_sell_price` VALUES ('20034', 'æ¬§è¥¿æ¯å¤´ç', '10');
INSERT INTO `shop_sell_price` VALUES ('20035', 'ç²¾çµç®ç', '3000');
INSERT INTO `shop_sell_price` VALUES ('20036', 'å¤è§å¤´ç', '6700');
INSERT INTO `shop_sell_price` VALUES ('20037', 'çå®çé¢å·', '30');
INSERT INTO `shop_sell_price` VALUES ('20038', 'é¶éç®å¸½', '0');
INSERT INTO `shop_sell_price` VALUES ('20039', 'ç²¾çµä½è´¨å¤´ç', '30');
INSERT INTO `shop_sell_price` VALUES ('20040', 'å¡å£«ä¼¯ä¹å¸½', '2500');
INSERT INTO `shop_sell_price` VALUES ('20041', 'åç¹å¤´ç', '15000');
INSERT INTO `shop_sell_price` VALUES ('20042', 'èµå°¼æ¯å¤´ç®', '15000');
INSERT INTO `shop_sell_price` VALUES ('20043', 'é¢ç', '100');
INSERT INTO `shop_sell_price` VALUES ('20044', 'èæµ·è´¼å¤´å·¾', '15000');
INSERT INTO `shop_sell_price` VALUES ('20045', 'éª·é«å¤´ç', '0');
INSERT INTO `shop_sell_price` VALUES ('20046', 'åçå¸½', '0');
INSERT INTO `shop_sell_price` VALUES ('20047', 'åçå¤´å¥', '0');
INSERT INTO `shop_sell_price` VALUES ('20048', 'æ··æ²å¤´ç', '1000');
INSERT INTO `shop_sell_price` VALUES ('20049', 'å·¨èå¥³ççéç¿è', '1000');
INSERT INTO `shop_sell_price` VALUES ('20050', 'å·¨èå¥³ççé¶ç¿è', '1000');
INSERT INTO `shop_sell_price` VALUES ('20051', 'åä¸»çå¨ä¸¥', '2500');
INSERT INTO `shop_sell_price` VALUES ('20052', 'ä¾åæç¯·', '0');
INSERT INTO `shop_sell_price` VALUES ('20053', 'ç¼ç®æç¯·', '0');
INSERT INTO `shop_sell_price` VALUES ('20054', 'å°å±æ§æç¯·', '25000');
INSERT INTO `shop_sell_price` VALUES ('20055', 'çé£æç¯·', '2500');
INSERT INTO `shop_sell_price` VALUES ('20056', 'æé­æ³æç¯·', '200');
INSERT INTO `shop_sell_price` VALUES ('20057', 'å¥æ³åçæç¯·', '25000');
INSERT INTO `shop_sell_price` VALUES ('20058', 'æ­¦å®æç¯·', '25000');
INSERT INTO `shop_sell_price` VALUES ('20059', 'æ°´å±æ§æç¯·', '25000');
INSERT INTO `shop_sell_price` VALUES ('20060', 'èæµ·è´¼æç¯·', '15000');
INSERT INTO `shop_sell_price` VALUES ('20061', 'é£å±æ§æç¯·', '15000');
INSERT INTO `shop_sell_price` VALUES ('20062', 'çé­çè¡åæç¯·', '35000');
INSERT INTO `shop_sell_price` VALUES ('20063', 'ä¿æ¤èæç¯·', '15000');
INSERT INTO `shop_sell_price` VALUES ('20064', 'çº¢éªå£«ä¹æç¯·', '2000');
INSERT INTO `shop_sell_price` VALUES ('20065', 'çº¢è²æç¯·', '1000');
INSERT INTO `shop_sell_price` VALUES ('20066', 'é»èç®æç¯·', '1000');
INSERT INTO `shop_sell_price` VALUES ('20067', 'ç¥å®æç¯·', '150000');
INSERT INTO `shop_sell_price` VALUES ('20068', 'äºä¸éªå£«å¢æ«è©', '15000');
INSERT INTO `shop_sell_price` VALUES ('20069', 'ç«ç°ä¹å½±æç¯·', '10000');
INSERT INTO `shop_sell_price` VALUES ('20070', 'é»ææç¯·', '20000');
INSERT INTO `shop_sell_price` VALUES ('20071', 'ç«å±æ§æç¯·', '15000');
INSERT INTO `shop_sell_price` VALUES ('20072', 'æ¬§è¥¿æ¯æç¯·', '10');
INSERT INTO `shop_sell_price` VALUES ('20073', 'ç²¾çµæç¯·', '500');
INSERT INTO `shop_sell_price` VALUES ('20074', 'é¶åæç¯·', '0');
INSERT INTO `shop_sell_price` VALUES ('20075', 'æ­»äº¡æç¯·', '0');
INSERT INTO `shop_sell_price` VALUES ('20076', 'å è½æç¯·', '25000');
INSERT INTO `shop_sell_price` VALUES ('20077', 'éèº«æç¯·', '15000');
INSERT INTO `shop_sell_price` VALUES ('20078', 'æ··æ²æç¯·', '25000');
INSERT INTO `shop_sell_price` VALUES ('20079', 'å¸è¡é¬¼æç¯·', '25000');
INSERT INTO `shop_sell_price` VALUES ('20080', 'ä½ç§»æç¯·', '5000');
INSERT INTO `shop_sell_price` VALUES ('20081', 'æ²¹å¸æç¯·', '400');
INSERT INTO `shop_sell_price` VALUES ('20082', 'è±¡çå¡Tæ¤', '0');
INSERT INTO `shop_sell_price` VALUES ('20083', 'ç«ç°ä¹å½±è¡¬è¡«', '1000');
INSERT INTO `shop_sell_price` VALUES ('20084', 'ç²¾çµTæ¤', '1300');
INSERT INTO `shop_sell_price` VALUES ('20085', 'Tæ¤', '500');
INSERT INTO `shop_sell_price` VALUES ('20086', 'æºåTæ¤', '50000');
INSERT INTO `shop_sell_price` VALUES ('20087', 'ææ·Tæ¤', '50000');
INSERT INTO `shop_sell_price` VALUES ('20088', 'åéTæ¤', '50000');
INSERT INTO `shop_sell_price` VALUES ('20089', 'å°è¤ç²', '100');
INSERT INTO `shop_sell_price` VALUES ('20090', 'ç®èå¿', '1');
INSERT INTO `shop_sell_price` VALUES ('20091', 'é¢ééå±çç²', '35000');
INSERT INTO `shop_sell_price` VALUES ('20092', 'å¤èçç®çç²', '15000');
INSERT INTO `shop_sell_price` VALUES ('20093', 'å¤èçé¿è¢', '15000');
INSERT INTO `shop_sell_price` VALUES ('20094', 'å¤èçé³ç²', '15000');
INSERT INTO `shop_sell_price` VALUES ('20095', 'å¤èçéå±çç²', '15000');
INSERT INTO `shop_sell_price` VALUES ('20096', 'ç¯ç²', '100');
INSERT INTO `shop_sell_price` VALUES ('20097', 'æ¨ç²', '600');
INSERT INTO `shop_sell_price` VALUES ('20098', 'é»ææ æèçç²', '1000');
INSERT INTO `shop_sell_price` VALUES ('20099', 'æ¶é­çç²', '2500');
INSERT INTO `shop_sell_price` VALUES ('20100', 'æ­»äº¡éªå£«çç²', '5000');
INSERT INTO `shop_sell_price` VALUES ('20101', 'ç®ç²', '6250');
INSERT INTO `shop_sell_price` VALUES ('20102', 'ææ¯å¡å·´å¾·ç®çç²', '750');
INSERT INTO `shop_sell_price` VALUES ('20103', 'ææ¯å¡å·´å¾·é¿è¢', '750');
INSERT INTO `shop_sell_price` VALUES ('20104', 'ææ¯å¡å·´å¾·é¶éç®çç²', '500');
INSERT INTO `shop_sell_price` VALUES ('20105', 'ææ¯å¡å·´å¾·é¾ç²', '2500');
INSERT INTO `shop_sell_price` VALUES ('20106', 'è¾éé¿è¢', '1');
INSERT INTO `shop_sell_price` VALUES ('20107', 'å·«å¦æç¯·', '6000');
INSERT INTO `shop_sell_price` VALUES ('20108', 'å¤ä»£é£é¾é³çç²', '10000');
INSERT INTO `shop_sell_price` VALUES ('20109', 'æ³ä»¤åçé¿è¢', '10000');
INSERT INTO `shop_sell_price` VALUES ('20110', 'æé­æ³é¾ç²', '4000');
INSERT INTO `shop_sell_price` VALUES ('20111', 'æ³å¸é¿è¢', '2500');
INSERT INTO `shop_sell_price` VALUES ('20112', 'æ¼æ³¢å¤å¥', '15000');
INSERT INTO `shop_sell_price` VALUES ('20113', 'æ­¦å®æ¤é ', '15000');
INSERT INTO `shop_sell_price` VALUES ('20114', 'ç»µè´¨é¿è¢', '500');
INSERT INTO `shop_sell_price` VALUES ('20115', 'è¤ç²', '1000');
INSERT INTO `shop_sell_price` VALUES ('20116', 'å·´å°å¡çç²', '25000');
INSERT INTO `shop_sell_price` VALUES ('20117', 'å·´é£ç¹çç²', '25000');
INSERT INTO `shop_sell_price` VALUES ('20118', 'åççç²', '25000');
INSERT INTO `shop_sell_price` VALUES ('20119', 'å¤ä»£ç«é¾é³çç²', '25000');
INSERT INTO `shop_sell_price` VALUES ('20120', 'ç®çç²', '1000');
INSERT INTO `shop_sell_price` VALUES ('20121', 'é»æ³å¸é¿è¢', '0');
INSERT INTO `shop_sell_price` VALUES ('20122', 'é³ç²', '1000');
INSERT INTO `shop_sell_price` VALUES ('20123', 'å¤å½å¸é¿è¢', '1000');
INSERT INTO `shop_sell_price` VALUES ('20124', 'éª·é«çç²', '0');
INSERT INTO `shop_sell_price` VALUES ('20125', 'é¾ç²', '3000');
INSERT INTO `shop_sell_price` VALUES ('20126', 'è±¡çå¡ç®çç²', '0');
INSERT INTO `shop_sell_price` VALUES ('20127', 'æ°´é¾é³çç²', '15000');
INSERT INTO `shop_sell_price` VALUES ('20128', 'æ°´æ¶çç²', '3500');
INSERT INTO `shop_sell_price` VALUES ('20129', 'ç¥å®æ³è¢', '15000');
INSERT INTO `shop_sell_price` VALUES ('20130', 'å¤ä»£å°é¾é³çç²', '15000');
INSERT INTO `shop_sell_price` VALUES ('20131', 'ç«ç°ä¹å½±çç²', '15000');
INSERT INTO `shop_sell_price` VALUES ('20132', 'é»ææ«è©', '450');
INSERT INTO `shop_sell_price` VALUES ('20133', 'é»ææ§è¡èéå±çç²', '5000');
INSERT INTO `shop_sell_price` VALUES ('20134', 'å°ä¹å¥³çé­åç¤¼æ', '5000');
INSERT INTO `shop_sell_price` VALUES ('20135', 'æ¬§è¥¿æ¯ç¯ç²', '100');
INSERT INTO `shop_sell_price` VALUES ('20136', 'æ¬§è¥¿æ¯é¾ç²', '400');
INSERT INTO `shop_sell_price` VALUES ('20137', 'ç²¾çµé¾ç²', '3000');
INSERT INTO `shop_sell_price` VALUES ('20138', 'ç²¾çµéå±çç²', '9000');
INSERT INTO `shop_sell_price` VALUES ('20139', 'ç²¾çµæ¤è¸éå±æ¿', '1040');
INSERT INTO `shop_sell_price` VALUES ('20140', 'è¢«éå¿çç®çç²', '0');
INSERT INTO `shop_sell_price` VALUES ('20141', 'è¢«éå¿çé¿è¢', '0');
INSERT INTO `shop_sell_price` VALUES ('20142', 'è¢«éå¿çé³ç²', '0');
INSERT INTO `shop_sell_price` VALUES ('20143', 'è¢«éå¿çéå±çç²', '0');
INSERT INTO `shop_sell_price` VALUES ('20144', 'æ­»äº¡çç²', '5000');
INSERT INTO `shop_sell_price` VALUES ('20145', 'ç¡¬ç®èå¿', '1');
INSERT INTO `shop_sell_price` VALUES ('20146', 'å°é¾é³çç²', '15000');
INSERT INTO `shop_sell_price` VALUES ('20147', 'é¶éç®ç²', '150');
INSERT INTO `shop_sell_price` VALUES ('20148', 'é¶éç®èå¿', '1');
INSERT INTO `shop_sell_price` VALUES ('20149', 'ééçç²', '8000');
INSERT INTO `shop_sell_price` VALUES ('20150', 'åç¹çç²', '15000');
INSERT INTO `shop_sell_price` VALUES ('20151', 'èµå°¼æ¯æç¯·', '15000');
INSERT INTO `shop_sell_price` VALUES ('20152', 'å è½é¿è¢', '15000');
INSERT INTO `shop_sell_price` VALUES ('20153', 'å¤ä»£æ°´é¾é³çç²', '15000');
INSERT INTO `shop_sell_price` VALUES ('20154', 'éå±çç²', '18500');
INSERT INTO `shop_sell_price` VALUES ('20155', 'èæµ·è´¼ç®çç²', '1500');
INSERT INTO `shop_sell_price` VALUES ('20156', 'é£é¾é³çç²', '15000');
INSERT INTO `shop_sell_price` VALUES ('20157', 'éå±èè£ç®çç²', '5000');
INSERT INTO `shop_sell_price` VALUES ('20158', 'æ··æ²æ³è¢', '15000');
INSERT INTO `shop_sell_price` VALUES ('20159', 'ç«é¾é³çç²', '15000');
INSERT INTO `shop_sell_price` VALUES ('20160', 'é»é¿èé¿è¢', '15000');
INSERT INTO `shop_sell_price` VALUES ('20161', 'å¹»è±¡çç²', '15000');
INSERT INTO `shop_sell_price` VALUES ('20162', 'ç®æå¥', '200');
INSERT INTO `shop_sell_price` VALUES ('20163', 'é¢éæå¥', '25000');
INSERT INTO `shop_sell_price` VALUES ('20164', 'å½±å­æå¥', '2500');
INSERT INTO `shop_sell_price` VALUES ('20165', 'æ¶é­æå¥', '2555');
INSERT INTO `shop_sell_price` VALUES ('20166', 'æ­»äº¡éªå£«æå¥', '5000');
INSERT INTO `shop_sell_price` VALUES ('20167', 'è¥è´çæå¥', '2500');
INSERT INTO `shop_sell_price` VALUES ('20168', 'æ­¦å®æå¥', '15000');
INSERT INTO `shop_sell_price` VALUES ('20169', 'å·´å°å¡æå¥', '5000');
INSERT INTO `shop_sell_price` VALUES ('20170', 'åçæå¥', '15000');
INSERT INTO `shop_sell_price` VALUES ('20171', 'ä¿æ¤èæå¥', '4500');
INSERT INTO `shop_sell_price` VALUES ('20172', 'æ°´çµæå¥', '2500');
INSERT INTO `shop_sell_price` VALUES ('20173', 'è±¡çå¡ç®æå¥', '0');
INSERT INTO `shop_sell_price` VALUES ('20174', 'éªäººæå¥', '5000');
INSERT INTO `shop_sell_price` VALUES ('20175', 'æ°´æ¶æå¥', '2500');
INSERT INTO `shop_sell_price` VALUES ('20176', 'ç¥å®æå¥', '15000');
INSERT INTO `shop_sell_price` VALUES ('20177', 'å°çµæå¥', '15000');
INSERT INTO `shop_sell_price` VALUES ('20178', 'ææåçæå¥', '10000');
INSERT INTO `shop_sell_price` VALUES ('20179', 'ç«ç°ä¹å½±æå¥', '10000');
INSERT INTO `shop_sell_price` VALUES ('20180', 'é»ææå¥', '10000');
INSERT INTO `shop_sell_price` VALUES ('20181', 'ç«çµæå¥', '10000');
INSERT INTO `shop_sell_price` VALUES ('20182', 'æå¥', '1500');
INSERT INTO `shop_sell_price` VALUES ('20183', 'æ­»äº¡æå¥', '10000');
INSERT INTO `shop_sell_price` VALUES ('20184', 'åç¹æå¥', '15000');
INSERT INTO `shop_sell_price` VALUES ('20185', 'èµå°¼æ¯æå¥', '15000');
INSERT INTO `shop_sell_price` VALUES ('20186', 'å è½æå¥', '15000');
INSERT INTO `shop_sell_price` VALUES ('20187', 'æåçé¿æå¥', '15000');
INSERT INTO `shop_sell_price` VALUES ('20188', 'èæµ·è´¼æå¥', '1500');
INSERT INTO `shop_sell_price` VALUES ('20189', 'é£çµæå¥', '2500');
INSERT INTO `shop_sell_price` VALUES ('20190', 'æ··æ²æå¥', '2351');
INSERT INTO `shop_sell_price` VALUES ('20191', 'èç²', '3500');
INSERT INTO `shop_sell_price` VALUES ('20192', 'ç®é¿é´', '1000');
INSERT INTO `shop_sell_price` VALUES ('20193', 'ç®åé', '200');
INSERT INTO `shop_sell_price` VALUES ('20194', 'é¢éé¿é´', '6800');
INSERT INTO `shop_sell_price` VALUES ('20195', 'å½±å­é¿é´', '2500');
INSERT INTO `shop_sell_price` VALUES ('20196', 'é»ææ æèé¿é´', '10000');
INSERT INTO `shop_sell_price` VALUES ('20197', 'æ¶é­é¿é´', '10000');
INSERT INTO `shop_sell_price` VALUES ('20198', 'æ­»äº¡éªå£«é¿é´', '10000');
INSERT INTO `shop_sell_price` VALUES ('20199', 'ææ¯å¡å·´å¾·é¿é´', '1000');
INSERT INTO `shop_sell_price` VALUES ('20200', 'é­å½åçé¿é´', '10000');
INSERT INTO `shop_sell_price` VALUES ('20201', 'æ­¦å®é¿é´', '10000');
INSERT INTO `shop_sell_price` VALUES ('20202', 'å·´å°å¡é¿é´', '10000');
INSERT INTO `shop_sell_price` VALUES ('20203', 'åçé¿é´', '10000');
INSERT INTO `shop_sell_price` VALUES ('20204', 'å·´åæ¯é¿é´', '10000');
INSERT INTO `shop_sell_price` VALUES ('20205', 'é¿é´', '1250');
INSERT INTO `shop_sell_price` VALUES ('20206', 'è±¡çå¡ç®åé', '0');
INSERT INTO `shop_sell_price` VALUES ('20207', 'æ·±æ°´é¿é´', '2500');
INSERT INTO `shop_sell_price` VALUES ('20208', 'ç¥å®é¿é´', '10000');
INSERT INTO `shop_sell_price` VALUES ('20209', 'ç«ç°ä¹å½±é¿é´', '10000');
INSERT INTO `shop_sell_price` VALUES ('20210', 'é»æé¿é´', '2500');
INSERT INTO `shop_sell_price` VALUES ('20211', 'å°ä¹å¥³çé­ååé', '10000');
INSERT INTO `shop_sell_price` VALUES ('20212', 'é¶éç®åé', '452');
INSERT INTO `shop_sell_price` VALUES ('20213', 'ç­ç»é´', '150');
INSERT INTO `shop_sell_price` VALUES ('20214', 'åç¹é¿é´', '15000');
INSERT INTO `shop_sell_price` VALUES ('20215', 'èµå°¼æ¯é¿é´', '15000');
INSERT INTO `shop_sell_price` VALUES ('20216', 'å è½é¿é´', '15000');
INSERT INTO `shop_sell_price` VALUES ('20217', 'èæµ·è´¼é¿é´', '2500');
INSERT INTO `shop_sell_price` VALUES ('20218', 'é»é¿èåé', '3500');
INSERT INTO `shop_sell_price` VALUES ('20219', 'ç®ç¾ç', '1');
INSERT INTO `shop_sell_price` VALUES ('20220', 'é¢éç¾ç', '25000');
INSERT INTO `shop_sell_price` VALUES ('20221', 'éª·é«ç¾ç', '1000');
INSERT INTO `shop_sell_price` VALUES ('20222', 'æ¨ç¾', '30');
INSERT INTO `shop_sell_price` VALUES ('20223', 'ä¾ååç¾', '100');
INSERT INTO `shop_sell_price` VALUES ('20224', 'ææ¯å¡å·´å¾·åç¾', '500');
INSERT INTO `shop_sell_price` VALUES ('20225', 'çé£æ°´æ¶ç', '5647');
INSERT INTO `shop_sell_price` VALUES ('20226', 'é­æ³è½éä¹ä¹¦', '5647');
INSERT INTO `shop_sell_price` VALUES ('20227', 'æ¢æèç¾ç', '10000');
INSERT INTO `shop_sell_price` VALUES ('20228', 'æ­¦å®ä¹ç¾', '10000');
INSERT INTO `shop_sell_price` VALUES ('20229', 'åå°ä¹ç¾', '5000');
INSERT INTO `shop_sell_price` VALUES ('20230', 'çº¢éªå£«ç¾ç', '5000');
INSERT INTO `shop_sell_price` VALUES ('20231', 'å¡ç¾', '3500');
INSERT INTO `shop_sell_price` VALUES ('20232', 'è±¡çå¡ç®ç¾ç', '0');
INSERT INTO `shop_sell_price` VALUES ('20233', 'ç¥å®é­æ³ä¹¦', '10000');
INSERT INTO `shop_sell_price` VALUES ('20234', 'ä¿¡å¿µä¹ç¾', '2500');
INSERT INTO `shop_sell_price` VALUES ('20235', 'ä¼å¨ä¹ç¾', '2500');
INSERT INTO `shop_sell_price` VALUES ('20236', 'ç²¾çµç¾ç', '2500');
INSERT INTO `shop_sell_price` VALUES ('20237', 'é¿åæµ·ç¾ç', '60');
INSERT INTO `shop_sell_price` VALUES ('20238', 'é¶éªå£«ä¹ç¾', '15000');
INSERT INTO `shop_sell_price` VALUES ('20239', 'å°ç¾ç', '30');
INSERT INTO `shop_sell_price` VALUES ('20240', 'æ­»äº¡ä¹ç¾', '10000');
INSERT INTO `shop_sell_price` VALUES ('20241', 'é¶éç®ç¾', '1');
INSERT INTO `shop_sell_price` VALUES ('20242', 'å¤§ç¾ç', '600');
INSERT INTO `shop_sell_price` VALUES ('20243', 'éèä¹è°·é¡¹é¾', '0');
INSERT INTO `shop_sell_price` VALUES ('20244', 'å°åé­åé¡¹é¾', '10000');
INSERT INTO `shop_sell_price` VALUES ('20245', 'å°åææ·é¡¹é¾', '10000');
INSERT INTO `shop_sell_price` VALUES ('20246', 'å°ååéé¡¹é¾', '10000');
INSERT INTO `shop_sell_price` VALUES ('20247', 'å°åæºåé¡¹é¾', '10000');
INSERT INTO `shop_sell_price` VALUES ('20248', 'å°åç²¾ç¥é¡¹é¾', '10000');
INSERT INTO `shop_sell_price` VALUES ('20249', 'å°åä½è´¨é¡¹é¾', '10000');
INSERT INTO `shop_sell_price` VALUES ('20250', 'åå½¢æªé¦é¢é¡¹é¾', '10000');
INSERT INTO `shop_sell_price` VALUES ('20251', 'é½ä½©æ°è¯ºçé¡¹é¾', '10000');
INSERT INTO `shop_sell_price` VALUES ('20252', 'è¾éé¡¹é¾', '10000');
INSERT INTO `shop_sell_price` VALUES ('20253', 'æ³ä»¤åçä¹ç¼', '10000');
INSERT INTO `shop_sell_price` VALUES ('20254', 'é­åé¡¹é¾', '10000');
INSERT INTO `shop_sell_price` VALUES ('20255', 'å¥æ³åçä¹æ', '10000');
INSERT INTO `shop_sell_price` VALUES ('20256', 'ææ·é¡¹é¾', '10000');
INSERT INTO `shop_sell_price` VALUES ('20257', 'é»æ³å¸é¡¹é¾', '10000');
INSERT INTO `shop_sell_price` VALUES ('20258', 'å¤å½å¸é¡¹é¾', '10000');
INSERT INTO `shop_sell_price` VALUES ('20259', 'æ­å±ä¹å²é¡¹é¾', '50');
INSERT INTO `shop_sell_price` VALUES ('20260', 'è¾èä¸é¡¹é¾', '10000');
INSERT INTO `shop_sell_price` VALUES ('20261', 'ç«ç°ä¹å½±é¡¹é¾', '10000');
INSERT INTO `shop_sell_price` VALUES ('20262', 'è¥å»æ»¡åéé¡¹é¾', '10000');
INSERT INTO `shop_sell_price` VALUES ('20263', 'å¦é­æå£«æ¤èº«ç¬¦', '2500');
INSERT INTO `shop_sell_price` VALUES ('20264', 'åéé¡¹é¾', '10000');
INSERT INTO `shop_sell_price` VALUES ('20265', 'çµé­çå°è®°', '2500');
INSERT INTO `shop_sell_price` VALUES ('20266', 'æºåé¡¹é¾', '10000');
INSERT INTO `shop_sell_price` VALUES ('20267', 'ç²¾ç¥é¡¹é¾', '10000');
INSERT INTO `shop_sell_price` VALUES ('20268', 'ä½è´¨é¡¹é¾', '10000');
INSERT INTO `shop_sell_price` VALUES ('20269', 'éª·é«é¡¹é¾', '10000');
INSERT INTO `shop_sell_price` VALUES ('20270', 'é½ä½©æ°è¯ºçé¡¹é¾1', '10000');
INSERT INTO `shop_sell_price` VALUES ('20277', 'åå½¢æªé¦é¢ä¹æ(å³)', '10000');
INSERT INTO `shop_sell_price` VALUES ('20278', 'åå½¢æªé¦é¢ä¹æ(å·¦)', '10000');
INSERT INTO `shop_sell_price` VALUES ('20279', 'è¾éææ', '10000');
INSERT INTO `shop_sell_price` VALUES ('20280', 'ç­é­ææ', '5000');
INSERT INTO `shop_sell_price` VALUES ('20281', 'åå½¢æ§å¶ææ', '10000');
INSERT INTO `shop_sell_price` VALUES ('20282', 'è±¡çå¡ææ', '1');
INSERT INTO `shop_sell_price` VALUES ('20284', 'å¬å¤æ§å¶ææ', '10000');
INSERT INTO `shop_sell_price` VALUES ('20285', 'æ°´çµææ', '10000');
INSERT INTO `shop_sell_price` VALUES ('20286', 'å®æ¤å¢ææ', '10000');
INSERT INTO `shop_sell_price` VALUES ('20287', 'å®æ¤èçææ', '10000');
INSERT INTO `shop_sell_price` VALUES ('20288', 'ä¼ éæ§å¶ææ', '10000');
INSERT INTO `shop_sell_price` VALUES ('20289', 'æ·±æ¸ææ', '10000');
INSERT INTO `shop_sell_price` VALUES ('20290', 'ç«ç°ä¹å½±ææ', '10000');
INSERT INTO `shop_sell_price` VALUES ('20291', 'è¥å»æ»¡åéææ', '10000');
INSERT INTO `shop_sell_price` VALUES ('20293', 'åè¯åçé»ç³ææ', '2500');
INSERT INTO `shop_sell_price` VALUES ('20294', 'åè¯åççº¢å®ç³ææ', '2500');
INSERT INTO `shop_sell_price` VALUES ('20295', 'åè¯åçèå®ç³ææ', '2500');
INSERT INTO `shop_sell_price` VALUES ('20296', 'åè¯åçç»¿å®ç³ææ', '2500');
INSERT INTO `shop_sell_price` VALUES ('20297', 'ç²¾ç¥çå°è®°', '2500');
INSERT INTO `shop_sell_price` VALUES ('20298', 'æ´å°¼æ¯ææ', '10000');
INSERT INTO `shop_sell_price` VALUES ('20299', 'æ­»äº¡çèªçº¦', '2500');
INSERT INTO `shop_sell_price` VALUES ('20300', 'å°çµææ', '10000');
INSERT INTO `shop_sell_price` VALUES ('20301', 'èº«ä½çå°è®°', '2500');
INSERT INTO `shop_sell_price` VALUES ('20302', 'é£çµææ', '10000');
INSERT INTO `shop_sell_price` VALUES ('20303', 'æé­ææ', '2500');
INSERT INTO `shop_sell_price` VALUES ('20304', 'ç«çµææ', '2500');
INSERT INTO `shop_sell_price` VALUES ('20305', 'æäººææ', '2500');
INSERT INTO `shop_sell_price` VALUES ('20306', 'å°åèº«ä½è°å¸¦', '5000');
INSERT INTO `shop_sell_price` VALUES ('20307', 'å°åçµé­è°å¸¦', '5000');
INSERT INTO `shop_sell_price` VALUES ('20308', 'å°åç²¾ç¥è°å¸¦', '5000');
INSERT INTO `shop_sell_price` VALUES ('20309', 'åæèº«ä½è°å¸¦', '10000');
INSERT INTO `shop_sell_price` VALUES ('20310', 'åæçµé­è°å¸¦', '10000');
INSERT INTO `shop_sell_price` VALUES ('20311', 'åæç²¾ç¥è°å¸¦', '10000');
INSERT INTO `shop_sell_price` VALUES ('20312', 'èº«ä½è°å¸¦', '10000');
INSERT INTO `shop_sell_price` VALUES ('20313', 'é»æè°å¸¦', '1000');
INSERT INTO `shop_sell_price` VALUES ('20314', 'å¤ä»£å·¨äººææ', '10000');
INSERT INTO `shop_sell_price` VALUES ('20315', 'è¥å»æ»¡åéè°å¸¦', '10000');
INSERT INTO `shop_sell_price` VALUES ('20316', 'çµé­è°å¸¦', '10000');
INSERT INTO `shop_sell_price` VALUES ('20317', 'æ¬§åç®å¸¦', '10000');
INSERT INTO `shop_sell_price` VALUES ('20318', 'åæ¢ç®å¸¦', '10000');
INSERT INTO `shop_sell_price` VALUES ('20319', 'ç²¾ç¥è°å¸¦', '10000');
INSERT INTO `shop_sell_price` VALUES ('20320', 'æ³°å¦ç®å¸¦', '10000');
INSERT INTO `shop_sell_price` VALUES ('20321', 'å¤ç½ç®å¸¦', '10000');
INSERT INTO `shop_sell_price` VALUES ('20322', 'ç®å¤¹å', '10');
INSERT INTO `shop_sell_price` VALUES ('20342', 'æ­»ç¥æ«è©', '0');
INSERT INTO `shop_sell_price` VALUES ('20343', 'æ¼æ³¢åå¸½', '0');
INSERT INTO `shop_sell_price` VALUES ('20344', 'æ¼æ³¢åå¸½', '0');
INSERT INTO `shop_sell_price` VALUES ('20345', 'æ¯å©çé¡¹é¾', '0');
INSERT INTO `shop_sell_price` VALUES ('20346', 'æµ£ççé¡¹é¾', '0');
INSERT INTO `shop_sell_price` VALUES ('20347', 'é©æ', '0');
INSERT INTO `shop_sell_price` VALUES ('20348', 'åæ', '0');
INSERT INTO `shop_sell_price` VALUES ('20349', 'çç¬é¡¹é¾', '0');
INSERT INTO `shop_sell_price` VALUES ('20350', 'éªäººçé¡¹é¾', '0');
INSERT INTO `shop_sell_price` VALUES ('20351', 'éªäººçè¡èå', '0');
INSERT INTO `shop_sell_price` VALUES ('20352', 'éªäººçé´å­', '0');
INSERT INTO `shop_sell_price` VALUES ('20353', 'å£æå£«çç²', '0');
INSERT INTO `shop_sell_price` VALUES ('20354', 'å£æå£«é¿é´', '0');
INSERT INTO `shop_sell_price` VALUES ('20355', 'å£æå£«æå¥', '0');
INSERT INTO `shop_sell_price` VALUES ('20356', 'å£æå£«å¤´ç', '0');
INSERT INTO `shop_sell_price` VALUES ('20357', 'å£æå£«ç¾ç', '0');
INSERT INTO `shop_sell_price` VALUES ('30001', 'è¯åçæ­»äº¡éªå£«å¤´ç', '10000');
INSERT INTO `shop_sell_price` VALUES ('30002', 'è¯åçæ­»äº¡éªå£«çç²', '10000');
INSERT INTO `shop_sell_price` VALUES ('30003', 'è¯åçæ­»äº¡éªå£«æå¥', '10000');
INSERT INTO `shop_sell_price` VALUES ('30004', 'è¯åçæ­»äº¡éªå£«é¿é´', '10000');
INSERT INTO `shop_sell_price` VALUES ('30005', 'èç¥éªå£«å¤´ç', '0');
INSERT INTO `shop_sell_price` VALUES ('30006', 'èç¥éªå£«çç²', '0');
INSERT INTO `shop_sell_price` VALUES ('30007', 'èç¥éªå£«æå¥', '0');
INSERT INTO `shop_sell_price` VALUES ('30008', 'èç¥éªå£«é¿é´', '0');
INSERT INTO `shop_sell_price` VALUES ('40001', 'ç¯', '5');
INSERT INTO `shop_sell_price` VALUES ('40002', 'ç¯ç¬¼', '50');
INSERT INTO `shop_sell_price` VALUES ('40003', 'ç¯æ²¹', '15');
INSERT INTO `shop_sell_price` VALUES ('40006', 'åé æªç©é­æ', '1000');
INSERT INTO `shop_sell_price` VALUES ('40007', 'éªçµé­æ', '1000');
INSERT INTO `shop_sell_price` VALUES ('40008', 'åå½¢é­æ', '1000');
INSERT INTO `shop_sell_price` VALUES ('40009', 'é©±éé­æ', '150');
INSERT INTO `shop_sell_price` VALUES ('40010', 'æ²»æè¯æ°´', '18');
INSERT INTO `shop_sell_price` VALUES ('40011', 'å¼ºåæ²»æè¯æ°´', '100');
INSERT INTO `shop_sell_price` VALUES ('40012', 'ç»ææ²»æè¯æ°´', '300');
INSERT INTO `shop_sell_price` VALUES ('40013', 'èªæå éè¯æ°´', '100');
INSERT INTO `shop_sell_price` VALUES ('40014', 'åæ¢è¯æ°´', '400');
INSERT INTO `shop_sell_price` VALUES ('40015', 'å éé­ååå¤è¯æ°´', '700');
INSERT INTO `shop_sell_price` VALUES ('40016', 'æéè¯æ°´', '300');
INSERT INTO `shop_sell_price` VALUES ('40017', 'è§£æ¯è¯æ°´', '35');
INSERT INTO `shop_sell_price` VALUES ('40018', 'å¼ºå ç»¿è²è¯æ°´', '750');
INSERT INTO `shop_sell_price` VALUES ('40019', 'æµç¼©ä½åæ¢å¤å', '27');
INSERT INTO `shop_sell_price` VALUES ('40020', 'æµç¼©å¼ºåä½åæ¢å¤å', '150');
INSERT INTO `shop_sell_price` VALUES ('40021', 'æµç¼©ç»æä½åæ¢å¤å', '450');
INSERT INTO `shop_sell_price` VALUES ('40022', 'å¤ä»£ä½åæ¢å¤å', '31');
INSERT INTO `shop_sell_price` VALUES ('40023', 'å¤ä»£å¼ºåä½åæ¢å¤å', '187');
INSERT INTO `shop_sell_price` VALUES ('40024', 'å¤ä»£ç»æä½åæ¢å¤å', '495');
INSERT INTO `shop_sell_price` VALUES ('40025', 'å¤±æè¯æ°´', '1');
INSERT INTO `shop_sell_price` VALUES ('40032', 'ä¼å¨çç¥ç¦', '150');
INSERT INTO `shop_sell_price` VALUES ('40044', 'é»ç³', '500');
INSERT INTO `shop_sell_price` VALUES ('40045', 'çº¢å®ç³', '500');
INSERT INTO `shop_sell_price` VALUES ('40046', 'èå®ç³', '500');
INSERT INTO `shop_sell_price` VALUES ('40047', 'ç»¿å®ç³', '500');
INSERT INTO `shop_sell_price` VALUES ('40048', 'åè´¨é»ç³', '1000');
INSERT INTO `shop_sell_price` VALUES ('40049', 'åè´¨çº¢å®ç³', '1000');
INSERT INTO `shop_sell_price` VALUES ('40050', 'åè´¨èå®ç³', '1000');
INSERT INTO `shop_sell_price` VALUES ('40051', 'åè´¨ç»¿å®ç³', '1000');
INSERT INTO `shop_sell_price` VALUES ('40052', 'é«åè´¨é»ç³', '5000');
INSERT INTO `shop_sell_price` VALUES ('40053', 'é«åè´¨çº¢å®ç³', '5000');
INSERT INTO `shop_sell_price` VALUES ('40054', 'é«åè´¨èå®ç³', '5000');
INSERT INTO `shop_sell_price` VALUES ('40055', 'é«åè´¨ç»¿å®ç³', '5000');
INSERT INTO `shop_sell_price` VALUES ('40057', 'æ¼æµ®ä¹ç¼è', '100');
INSERT INTO `shop_sell_price` VALUES ('40059', null, '2');
INSERT INTO `shop_sell_price` VALUES ('40060', null, '3');
INSERT INTO `shop_sell_price` VALUES ('40061', null, '3');
INSERT INTO `shop_sell_price` VALUES ('40062', null, '3');
INSERT INTO `shop_sell_price` VALUES ('40064', null, '3');
INSERT INTO `shop_sell_price` VALUES ('40065', null, '2');
INSERT INTO `shop_sell_price` VALUES ('40069', null, '3');
INSERT INTO `shop_sell_price` VALUES ('40072', null, '10');
INSERT INTO `shop_sell_price` VALUES ('40074', null, '15500');
INSERT INTO `shop_sell_price` VALUES ('40079', null, '60');
INSERT INTO `shop_sell_price` VALUES ('40087', null, '37500');
INSERT INTO `shop_sell_price` VALUES ('40088', null, '650');
INSERT INTO `shop_sell_price` VALUES ('40089', null, '500');
INSERT INTO `shop_sell_price` VALUES ('40100', 'ç¬é´ç§»å¨å·è½´', '35');
INSERT INTO `shop_sell_price` VALUES ('40101', 'æå®ä¼ éå·è½´(éèä¹è°·)', '500');
INSERT INTO `shop_sell_price` VALUES ('40102', 'äºä¸æåºæå®ä¼ éå·è½´', '500');
INSERT INTO `shop_sell_price` VALUES ('40103', 'æ¬§çæåºæå®ä¼ éå·è½´', '500');
INSERT INTO `shop_sell_price` VALUES ('40104', 'å²æ¢ä¹å¡ç§»å¨å·è½´(11F)', '500');
INSERT INTO `shop_sell_price` VALUES ('40105', 'å²æ¢ä¹å¡ç§»å¨å·è½´(21F)', '500');
INSERT INTO `shop_sell_price` VALUES ('40106', 'å²æ¢ä¹å¡ç§»å¨å·è½´(31F)', '500');
INSERT INTO `shop_sell_price` VALUES ('40107', 'å²æ¢ä¹å¡ç§»å¨å·è½´(41F)', '500');
INSERT INTO `shop_sell_price` VALUES ('40108', 'å²æ¢ä¹å¡ç§»å¨å·è½´(51F)', '500');
INSERT INTO `shop_sell_price` VALUES ('40109', 'å²æ¢ä¹å¡ç§»å¨å·è½´(61F)', '500');
INSERT INTO `shop_sell_price` VALUES ('40110', 'å²æ¢ä¹å¡ç§»å¨å·è½´(71F)', '500');
INSERT INTO `shop_sell_price` VALUES ('40111', 'å²æ¢ä¹å¡ç§»å¨å·è½´(81F)', '500');
INSERT INTO `shop_sell_price` VALUES ('40112', 'å²æ¢ä¹å¡ç§»å¨å·è½´(91F)', '500');
INSERT INTO `shop_sell_price` VALUES ('40113', 'å²æ¢ä¹å¡ç§»å¨å·è½´(100F)', '500');
INSERT INTO `shop_sell_price` VALUES ('40114', 'å¦æ£®æå®ä¼ éå·è½´', '500');
INSERT INTO `shop_sell_price` VALUES ('40115', 'é£æ¨æåºæå®ä¼ éå·è½´', '500');
INSERT INTO `shop_sell_price` VALUES ('40116', 'å¨é¡¿æåºæå®ä¼ éå·è½´', '500');
INSERT INTO `shop_sell_price` VALUES ('40117', 'é¶éªå£«æåºæå®ä¼ éå·è½´', '500');
INSERT INTO `shop_sell_price` VALUES ('40118', 'ééèæåºæå®ä¼ éå·è½´', '500');
INSERT INTO `shop_sell_price` VALUES ('40119', 'è§£é¤ååçå·è½´', '50');
INSERT INTO `shop_sell_price` VALUES ('40120', 'æµæåæåºæå®ä¼ éå·è½´', '500');
INSERT INTO `shop_sell_price` VALUES ('40121', 'æå®ä¼ é(ç¿ç©æ´ç©´)', '500');
INSERT INTO `shop_sell_price` VALUES ('40122', 'è¯ç¹æåºæå®ä¼ éå·è½´', '500');
INSERT INTO `shop_sell_price` VALUES ('40123', 'æµ·é³æåºæå®ä¼ éå·è½´', '500');
INSERT INTO `shop_sell_price` VALUES ('40124', 'è¡çä¼ éå·è½´', '60');
INSERT INTO `shop_sell_price` VALUES ('40125', 'çæ³æåºæå®ä¼ éå·è½´', '500');
INSERT INTO `shop_sell_price` VALUES ('40126', 'é´å®å·è½´', '25');
INSERT INTO `shop_sell_price` VALUES ('40127', 'å¯¹çç²æ½æ³çå¹»è±¡å·è½´', '0');
INSERT INTO `shop_sell_price` VALUES ('40128', 'å¯¹æ­¦å¨æ½æ³çå¹»è±¡å·è½´', '0');
INSERT INTO `shop_sell_price` VALUES ('40129', 'å¥å®çå·è½´', '0');
INSERT INTO `shop_sell_price` VALUES ('40130', 'éä¾çå·è½´', '0');
INSERT INTO `shop_sell_price` VALUES ('40131', 'çå°å¾è¾', '0');
INSERT INTO `shop_sell_price` VALUES ('40132', 'é£é²å å¾è¾', '0');
INSERT INTO `shop_sell_price` VALUES ('40133', 'é½è¾¾çæå¾è¾', '0');
INSERT INTO `shop_sell_price` VALUES ('40134', 'ç½å­å¾è¾', '0');
INSERT INTO `shop_sell_price` VALUES ('40135', 'é¿åå·´å¾è¾', '0');
INSERT INTO `shop_sell_price` VALUES ('40136', '3è¿åçç«', '460');
INSERT INTO `shop_sell_price` VALUES ('40137', '6è¿åçç«', '2180');
INSERT INTO `shop_sell_price` VALUES ('40138', 'é«çº§6è¿åçç«', '1050');
INSERT INTO `shop_sell_price` VALUES ('40139', 'èè²2æ®µçç«', '350');
INSERT INTO `shop_sell_price` VALUES ('40140', 'èè²ä»å¥³æ£', '40');
INSERT INTO `shop_sell_price` VALUES ('40141', 'èè²çç«', '87');
INSERT INTO `shop_sell_price` VALUES ('40142', 'èè²å¿åçç«', '175');
INSERT INTO `shop_sell_price` VALUES ('40143', 'çº¢è²2æ®µçç«', '350');
INSERT INTO `shop_sell_price` VALUES ('40144', 'çº¢è²ä»å¥³æ£', '42');
INSERT INTO `shop_sell_price` VALUES ('40145', 'çº¢è²çç«', '87');
INSERT INTO `shop_sell_price` VALUES ('40146', 'çº¢è²å¿åçç«', '145');
INSERT INTO `shop_sell_price` VALUES ('40147', 'ç»¿è²2æ®µåå½¢çç«', '350');
INSERT INTO `shop_sell_price` VALUES ('40148', 'ç»¿è²2æ®µçç«', '350');
INSERT INTO `shop_sell_price` VALUES ('40149', 'èè²åå½¢çç«', '175');
INSERT INTO `shop_sell_price` VALUES ('40150', 'ç»¿è²ä»å¥³æ£', '43');
INSERT INTO `shop_sell_price` VALUES ('40151', 'æ·¡ç»¿è²çç«', '82');
INSERT INTO `shop_sell_price` VALUES ('40152', 'ç»¿è²çç«', '82');
INSERT INTO `shop_sell_price` VALUES ('40153', 'ç»¿è²å¿åçç«', '175');
INSERT INTO `shop_sell_price` VALUES ('40154', 'å£è¯çç«', '500');
INSERT INTO `shop_sell_price` VALUES ('40155', 'é»è²2æ®µåå½¢çç«', '350');
INSERT INTO `shop_sell_price` VALUES ('40156', 'é»è²2æ®µçç«', '350');
INSERT INTO `shop_sell_price` VALUES ('40157', 'é»è²åå½¢çç«', '350');
INSERT INTO `shop_sell_price` VALUES ('40158', 'é»è²ä»å¥³æ£', '43');
INSERT INTO `shop_sell_price` VALUES ('40159', 'é»è²çç«', '87');
INSERT INTO `shop_sell_price` VALUES ('40160', 'æ·¡é»è²çç«', '87');
INSERT INTO `shop_sell_price` VALUES ('40161', 'é»è²å¿åçç«', '175');
INSERT INTO `shop_sell_price` VALUES ('40162', 'é«ä»ä¹å¿', '0');
INSERT INTO `shop_sell_price` VALUES ('40163', 'é»éé¥å', '0');
INSERT INTO `shop_sell_price` VALUES ('40164', 'ææ¯ä¹¦(å²å»ä¹æ)', '20000');
INSERT INTO `shop_sell_price` VALUES ('40165', 'ææ¯ä¹¦(å¢å¹é²å¾¡)', '20000');
INSERT INTO `shop_sell_price` VALUES ('40166', 'ææ¯ä¹¦(å°åºçç²)', '20000');
INSERT INTO `shop_sell_price` VALUES ('40167', 'å¤èç®è¢', '0');
INSERT INTO `shop_sell_price` VALUES ('40168', 'å¤èä¸è¢', '0');
INSERT INTO `shop_sell_price` VALUES ('40169', 'é£é¾ä¹å¿', '0');
INSERT INTO `shop_sell_price` VALUES ('40170', 'é­æ³ä¹¦(çç§çç«ç)', '1650');
INSERT INTO `shop_sell_price` VALUES ('40171', 'é­æ³ä¹¦(éçæ°èæ¯)', '1650');
INSERT INTO `shop_sell_price` VALUES ('40172', 'é­æ³ä¹¦(åç©æ¯)', '1650');
INSERT INTO `shop_sell_price` VALUES ('40173', 'é­æ³ä¹¦(å¸è¡é¬¼ä¹å»)', '1650');
INSERT INTO `shop_sell_price` VALUES ('40174', 'é­æ³ä¹¦(ç¼éæ¯)', '1650');
INSERT INTO `shop_sell_price` VALUES ('40175', 'é­æ³ä¹¦(é­æ³å±é)', '1650');
INSERT INTO `shop_sell_price` VALUES ('40176', 'é­æ³ä¹¦(å¥æ³æ¯)', '1650');
INSERT INTO `shop_sell_price` VALUES ('40177', 'é­æ³ä¹¦(å²©ç¢)', '1650');
INSERT INTO `shop_sell_price` VALUES ('40178', 'é­æ³ä¹¦(æ¨ä¹ä¼çè¯å)', '1650');
INSERT INTO `shop_sell_price` VALUES ('40179', 'é­æ³ä¹¦(æéè½é·)', '4125');
INSERT INTO `shop_sell_price` VALUES ('40180', 'é­æ³ä¹¦(é«çº§æ²»ææ¯)', '4125');
INSERT INTO `shop_sell_price` VALUES ('40181', 'é­æ³ä¹¦(è¿·é­æ¯)', '4125');
INSERT INTO `shop_sell_price` VALUES ('40182', 'é­æ³ä¹¦(å£æ´ä¹å)', '4125');
INSERT INTO `shop_sell_price` VALUES ('40183', 'é­æ³ä¹¦(å°é¥)', '4125');
INSERT INTO `shop_sell_price` VALUES ('40184', 'é­æ³ä¹¦(é­åå¤ºå)', '4125');
INSERT INTO `shop_sell_price` VALUES ('40185', 'é­æ³ä¹¦(é»?ä¹å½±)', '4125');
INSERT INTO `shop_sell_price` VALUES ('40186', 'é­æ³ä¹¦(é å°¸æ¯)', '4125');
INSERT INTO `shop_sell_price` VALUES ('40187', 'é­æ³ä¹¦(ä½é­å¼ºå¥æ¯)', '2000');
INSERT INTO `shop_sell_price` VALUES ('40188', 'é­æ³ä¹¦(å éæ¯)', '2000');
INSERT INTO `shop_sell_price` VALUES ('40189', 'é­æ³ä¹¦(é­æ³ç¸æ¶æ¯)', '4125');
INSERT INTO `shop_sell_price` VALUES ('40190', 'é­æ³ä¹¦(å°è£æ¯)', '2000');
INSERT INTO `shop_sell_price` VALUES ('40191', 'é­æ³ä¹¦(ççæ¯)', '2000');
INSERT INTO `shop_sell_price` VALUES ('40192', 'é­æ³ä¹¦(å¼±åæ¯)', '2000');
INSERT INTO `shop_sell_price` VALUES ('40193', 'é­æ³ä¹¦(ç¥ç¦é­æ³æ­¦å¨)', '2000');
INSERT INTO `shop_sell_price` VALUES ('40194', 'é­æ³ä¹¦(ä½ååå¤æ¯)', '14850');
INSERT INTO `shop_sell_price` VALUES ('40195', 'é­æ³ä¹¦(å°çå´ç¯±)', '14850');
INSERT INTO `shop_sell_price` VALUES ('40196', 'é­æ³ä¹¦(å¬å¤æ¯)', '14850');
INSERT INTO `shop_sell_price` VALUES ('40197', 'é­æ³ä¹¦(ç¥å£ç¾èµ°)', '14850');
INSERT INTO `shop_sell_price` VALUES ('40198', 'é­æ³ä¹¦(é¾å·é£)', '14850');
INSERT INTO `shop_sell_price` VALUES ('40199', 'é­æ³ä¹¦(å¼ºåå éæ¯)', '14850');
INSERT INTO `shop_sell_price` VALUES ('40200', 'é­æ³ä¹¦(çæ´æ¯)', '14850');
INSERT INTO `shop_sell_price` VALUES ('40201', 'é­æ³ä¹¦(ç¾çæ¯)', '14850');
INSERT INTO `shop_sell_price` VALUES ('40202', 'é­æ³ä¹¦(å¨é¨æ²»ææ¯)', '14850');
INSERT INTO `shop_sell_price` VALUES ('40203', 'é­æ³ä¹¦(ç«ç¢)', '14850');
INSERT INTO `shop_sell_price` VALUES ('40204', 'é­æ³ä¹¦(å°éªæ´)', '14850');
INSERT INTO `shop_sell_price` VALUES ('40205', 'é­æ³ä¹¦(éèº«æ¯)', '14850');
INSERT INTO `shop_sell_price` VALUES ('40206', 'é­æ³ä¹¦(è¿çæ¯)', '14850');
INSERT INTO `shop_sell_price` VALUES ('40207', 'é­æ³ä¹¦(éè£æ¯)', '14850');
INSERT INTO `shop_sell_price` VALUES ('40208', 'é­æ³ä¹¦(æ²»æè½éé£æ´)', '14850');
INSERT INTO `shop_sell_price` VALUES ('40209', 'é­æ³ä¹¦(é­æ³å°å°)', '14850');
INSERT INTO `shop_sell_price` VALUES ('40210', 'é­æ³ä¹¦(é·éé£æ´)', '14850');
INSERT INTO `shop_sell_price` VALUES ('40211', 'é­æ³ä¹¦(æ²ç¡ä¹é¾)', '14850');
INSERT INTO `shop_sell_price` VALUES ('40212', 'é­æ³ä¹¦(åå½¢æ¯)', '14850');
INSERT INTO `shop_sell_price` VALUES ('40213', 'é­æ³ä¹¦(å£ç»ç)', '14850');
INSERT INTO `shop_sell_price` VALUES ('40214', 'é­æ³ä¹¦(éä½ä¼ éæ¯)', '14850');
INSERT INTO `shop_sell_price` VALUES ('40215', 'é­æ³ä¹¦(ç«é£æ´)', '14850');
INSERT INTO `shop_sell_price` VALUES ('40216', 'é­æ³ä¹¦(è¯æ°´éåæ¯)', '14850');
INSERT INTO `shop_sell_price` VALUES ('40217', 'é­æ³ä¹¦(å¼ºåæ æéå½¢æ¯)', '14850');
INSERT INTO `shop_sell_price` VALUES ('40218', 'é­æ³ä¹¦(åé é­æ³æ­¦å¨)', '14850');
INSERT INTO `shop_sell_price` VALUES ('40219', 'é­æ³ä¹¦(æµæé¨)', '14850');
INSERT INTO `shop_sell_price` VALUES ('40220', 'é­æ³ä¹¦(ç»æè¿çæ¯)', '14850');
INSERT INTO `shop_sell_price` VALUES ('40221', 'é­æ³ä¹¦(éä½ç¼éæ¯)', '14850');
INSERT INTO `shop_sell_price` VALUES ('40222', 'é­æ³ä¹¦(ç©¶æåè£æ¯)', '14850');
INSERT INTO `shop_sell_price` VALUES ('40223', 'é­æ³ä¹¦(ç»å¯¹å±é)', '14850');
INSERT INTO `shop_sell_price` VALUES ('40224', 'é­æ³ä¹¦(çµé­åå)', '14850');
INSERT INTO `shop_sell_price` VALUES ('40225', 'é­æ³ä¹¦(å°éªé£é£)', '14850');
INSERT INTO `shop_sell_price` VALUES ('40226', 'é­æ³ä¹¦(ç²¾åç®æ )', '14850');
INSERT INTO `shop_sell_price` VALUES ('40227', 'é­æ³ä¹¦(æ¿å±å£«æ°)', '14850');
INSERT INTO `shop_sell_price` VALUES ('40228', 'é­æ³ä¹¦(å¼å¤çå)', '14850');
INSERT INTO `shop_sell_price` VALUES ('40229', 'é­æ³ä¹¦(é¢éå£«æ°)', '14850');
INSERT INTO `shop_sell_price` VALUES ('40230', 'é­æ³ä¹¦(å²å»å£«æ°)', '14850');
INSERT INTO `shop_sell_price` VALUES ('40231', 'é­æ³ä¹¦(æ´æ¤çå)', '14850');
INSERT INTO `shop_sell_price` VALUES ('40232', 'ç²¾çµæ°´æ¶(é­æ³é²å¾¡)', '1650');
INSERT INTO `shop_sell_price` VALUES ('40233', 'ç²¾çµæ°´æ¶(å¿çµè½¬æ¢)', '1650');
INSERT INTO `shop_sell_price` VALUES ('40234', 'ç²¾çµæ°´æ¶(ä¸çæ çå¼å¤)', '1650');
INSERT INTO `shop_sell_price` VALUES ('40235', 'ç²¾çµæ°´æ¶(ååç²¾ç¥)', '4125');
INSERT INTO `shop_sell_price` VALUES ('40236', 'ç²¾çµæ°´æ¶(å±æ§é²å¾¡)', '4125');
INSERT INTO `shop_sell_price` VALUES ('40237', 'ç²¾çµæ°´æ¶(éæ¾åç´ )', '4125');
INSERT INTO `shop_sell_price` VALUES ('40238', 'ç²¾çµæ°´æ¶(é­ä½è½¬æ¢)', '2000');
INSERT INTO `shop_sell_price` VALUES ('40239', 'ç²¾çµæ°´æ¶(åå±æ§é²å¾¡)', '2000');
INSERT INTO `shop_sell_price` VALUES ('40240', 'ç²¾çµæ°´æ¶(ä¸éç¢)', '2000');
INSERT INTO `shop_sell_price` VALUES ('40241', 'ç²¾çµæ°´æ¶(å¼±åå±æ§)', '2000');
INSERT INTO `shop_sell_price` VALUES ('40242', 'ç²¾çµæ°´æ¶(é­æ³æ¶é¤)', '2000');
INSERT INTO `shop_sell_price` VALUES ('40243', 'ç²¾çµæ°´æ¶(å¬å¤å±æ§ç²¾çµ)', '2000');
INSERT INTO `shop_sell_price` VALUES ('40244', 'ç²¾çµæ°´æ¶(å°å°ç¦å°)', '2000');
INSERT INTO `shop_sell_price` VALUES ('40245', 'ç²¾çµæ°´æ¶(å¬å¤å¼ºåå±æ§ç²¾çµ)', '2000');
INSERT INTO `shop_sell_price` VALUES ('40246', 'ç²¾çµæ°´æ¶(éåå°)', '2000');
INSERT INTO `shop_sell_price` VALUES ('40247', 'ç²¾çµæ°´æ¶(å¤§å°é²æ¤)', '2000');
INSERT INTO `shop_sell_price` VALUES ('40248', 'ç²¾çµæ°´æ¶(å°é¢éç¢)', '2000');
INSERT INTO `shop_sell_price` VALUES ('40249', 'ç²¾çµæ°´æ¶(å¤§å°å±é)', '2000');
INSERT INTO `shop_sell_price` VALUES ('40250', 'ç²¾çµæ°´æ¶(å¤§å°çç¥ç¦)', '2000');
INSERT INTO `shop_sell_price` VALUES ('40251', 'ç²¾çµæ°´æ¶(é¢éé²æ¤)', '2000');
INSERT INTO `shop_sell_price` VALUES ('40252', 'ç²¾çµæ°´æ¶(ä½è½æ¿å)', '2000');
INSERT INTO `shop_sell_price` VALUES ('40253', 'ç²¾çµæ°´æ¶(æ°´ä¹åæ°)', '2000');
INSERT INTO `shop_sell_price` VALUES ('40254', 'ç²¾çµæ°´æ¶(çå½ä¹æ³)', '2000');
INSERT INTO `shop_sell_price` VALUES ('40255', 'ç²¾çµæ°´æ¶(çå½çç¥ç¦)', '2000');
INSERT INTO `shop_sell_price` VALUES ('40256', 'ç²¾çµæ°´æ¶(ç«ç°æ­¦å¨)', '2000');
INSERT INTO `shop_sell_price` VALUES ('40257', 'ç²¾çµæ°´æ¶(ççæ°æ¯)', '2000');
INSERT INTO `shop_sell_price` VALUES ('40258', 'ç²¾çµæ°´æ¶(ççæ­¦å¨)', '2000');
INSERT INTO `shop_sell_price` VALUES ('40259', 'ç²¾çµæ°´æ¶(å±æ§ä¹ç«)', '2000');
INSERT INTO `shop_sell_price` VALUES ('40260', 'ç²¾çµæ°´æ¶(é£ä¹ç¥å°)', '2000');
INSERT INTO `shop_sell_price` VALUES ('40261', 'ç²¾çµæ°´æ¶(é£ä¹ç¾èµ°)', '2000');
INSERT INTO `shop_sell_price` VALUES ('40262', 'ç²¾çµæ°´æ¶(æ´é£ä¹ç¼)', '2000');
INSERT INTO `shop_sell_price` VALUES ('40263', 'ç²¾çµæ°´æ¶(æ´é£ç¥å°)', '2000');
INSERT INTO `shop_sell_price` VALUES ('40264', 'ç²¾çµæ°´æ¶(é£ä¹æ·é)', '2000');
INSERT INTO `shop_sell_price` VALUES ('40265', 'é»æç²¾çµæ°´æ¶(æéæ¯)', '5000');
INSERT INTO `shop_sell_price` VALUES ('40266', 'é»æç²¾çµæ°´æ¶(éå å§æ¯)', '5000');
INSERT INTO `shop_sell_price` VALUES ('40267', 'é»æç²¾çµæ°´æ¶(å½±ä¹é²æ¤)', '5000');
INSERT INTO `shop_sell_price` VALUES ('40268', 'é»æç²¾çµæ°´æ¶(æç¼é­ç³)', '5000');
INSERT INTO `shop_sell_price` VALUES ('40269', 'é»æç²¾çµæ°´æ¶(åéæå)', '5000');
INSERT INTO `shop_sell_price` VALUES ('40270', 'é»æç²¾çµæ°´æ¶(è¡èµ°å é)', '5000');
INSERT INTO `shop_sell_price` VALUES ('40271', 'é»æç²¾çµæ°´æ¶(çç§æå¿)', '5000');
INSERT INTO `shop_sell_price` VALUES ('40272', 'é»æç²¾çµæ°´æ¶(æé»ç²å)', '5000');
INSERT INTO `shop_sell_price` VALUES ('40273', 'é»æç²¾çµæ°´æ¶(æ¯æ§æµæ)', '5000');
INSERT INTO `shop_sell_price` VALUES ('40274', 'é»æç²¾çµæ°´æ¶(ææ·æå)', '5000');
INSERT INTO `shop_sell_price` VALUES ('40275', 'é»æç²¾çµæ°´æ¶(åéç ´å)', '5000');
INSERT INTO `shop_sell_price` VALUES ('40276', 'é»æç²¾çµæ°´æ¶(æå½±éªé¿)', '5000');
INSERT INTO `shop_sell_price` VALUES ('40277', 'é»æç²¾çµæ°´æ¶(æå½±ä¹ç)', '5000');
INSERT INTO `shop_sell_price` VALUES ('40278', 'é»æç²¾çµæ°´æ¶(ä¼å¿ä¸å»)', '5000');
INSERT INTO `shop_sell_price` VALUES ('40279', 'é»æç²¾çµæ°´æ¶(éªé¿æå)', '5000');
INSERT INTO `shop_sell_price` VALUES ('40280', 'å°å°çå²æ¢ä¹å¡ä¼ éç¬¦(11F)', '500');
INSERT INTO `shop_sell_price` VALUES ('40281', 'å°å°çå²æ¢ä¹å¡ä¼ éç¬¦(21F)', '500');
INSERT INTO `shop_sell_price` VALUES ('40282', 'å°å°çå²æ¢ä¹å¡ä¼ éç¬¦(31F)', '500');
INSERT INTO `shop_sell_price` VALUES ('40283', 'å°å°çå²æ¢ä¹å¡ä¼ éç¬¦(41F)', '500');
INSERT INTO `shop_sell_price` VALUES ('40284', 'å°å°çå²æ¢ä¹å¡ä¼ éç¬¦(51F)', '500');
INSERT INTO `shop_sell_price` VALUES ('40285', 'å°å°çå²æ¢ä¹å¡ä¼ éç¬¦(61F)', '500');
INSERT INTO `shop_sell_price` VALUES ('40286', 'å°å°çå²æ¢ä¹å¡ä¼ éç¬¦(71F)', '500');
INSERT INTO `shop_sell_price` VALUES ('40287', 'å°å°çå²æ¢ä¹å¡ä¼ éç¬¦(81F)', '500');
INSERT INTO `shop_sell_price` VALUES ('40288', 'å°å°çå²æ¢ä¹å¡ä¼ éç¬¦(91F)', '500');
INSERT INTO `shop_sell_price` VALUES ('40289', 'å²æ¢ä¹å¡ä¼ éç¬¦(11F)', '500');
INSERT INTO `shop_sell_price` VALUES ('40290', 'å²æ¢ä¹å¡ä¼ éç¬¦(21F)', '500');
INSERT INTO `shop_sell_price` VALUES ('40291', 'å²æ¢ä¹å¡ä¼ éç¬¦(31F)', '500');
INSERT INTO `shop_sell_price` VALUES ('40292', 'å²æ¢ä¹å¡ä¼ éç¬¦(41F)', '500');
INSERT INTO `shop_sell_price` VALUES ('40293', 'å²æ¢ä¹å¡ä¼ éç¬¦(51F)', '500');
INSERT INTO `shop_sell_price` VALUES ('40294', 'å²æ¢ä¹å¡ä¼ éç¬¦(61F)', '500');
INSERT INTO `shop_sell_price` VALUES ('40295', 'å²æ¢ä¹å¡ä¼ éç¬¦(71F)', '500');
INSERT INTO `shop_sell_price` VALUES ('40296', 'å²æ¢ä¹å¡ä¼ éç¬¦(81F)', '500');
INSERT INTO `shop_sell_price` VALUES ('40297', 'å²æ¢ä¹å¡ä¼ éç¬¦(91F)', '500');
INSERT INTO `shop_sell_price` VALUES ('40298', 'å¾è¯´è¯ä¹å²çè¹ç¥¨', '150');
INSERT INTO `shop_sell_price` VALUES ('40299', 'å¾å¤é²ä¸çè¹ç¥¨', '150');
INSERT INTO `shop_sell_price` VALUES ('40300', 'éå¿ä¹å²è¹ç¥¨', '150');
INSERT INTO `shop_sell_price` VALUES ('40301', 'æµ·é³æ¸¯å£è¹ç¥¨', '150');
INSERT INTO `shop_sell_price` VALUES ('40302', 'æµ·è´¼å²è¹ç¥¨', '345');
INSERT INTO `shop_sell_price` VALUES ('40303', 'éèæ¸¯å£è¹ç¥¨', '345');
INSERT INTO `shop_sell_price` VALUES ('40304', 'é©¬æ®åä¹ç³', '525');
INSERT INTO `shop_sell_price` VALUES ('40305', 'å¸æ ¼éå¥¥ä¹ç³', '525');
INSERT INTO `shop_sell_price` VALUES ('40306', 'ä¼å¨ä¹ç³', '525');
INSERT INTO `shop_sell_price` VALUES ('40307', 'æ²åä¹ç³', '525');
INSERT INTO `shop_sell_price` VALUES ('40308', 'éå¸', '0');
INSERT INTO `shop_sell_price` VALUES ('40309', 'é£äººå¦ç²¾ç«èµç¥¨', '0');
INSERT INTO `shop_sell_price` VALUES ('40310', 'ä¿¡çº¸', '17');
INSERT INTO `shop_sell_price` VALUES ('40311', 'è¡ççä¿¡çº¸', '40');
INSERT INTO `shop_sell_price` VALUES ('40312', 'æé¦é¥å', '0');
INSERT INTO `shop_sell_price` VALUES ('40313', 'é¶é¥å', '0');
INSERT INTO `shop_sell_price` VALUES ('40314', 'é¡¹å', '0');
INSERT INTO `shop_sell_price` VALUES ('40315', 'å¨å­', '250');
INSERT INTO `shop_sell_price` VALUES ('40316', 'é«ç­å® ç©é¡¹å', '0');
INSERT INTO `shop_sell_price` VALUES ('40317', 'ç£¨åç³', '15');
INSERT INTO `shop_sell_price` VALUES ('40318', 'é­æ³å®ç³', '100');
INSERT INTO `shop_sell_price` VALUES ('40319', 'ç²¾çµç', '100');
INSERT INTO `shop_sell_price` VALUES ('40320', 'ä¸çº§é»é­ç³', '100');
INSERT INTO `shop_sell_price` VALUES ('40321', 'äºçº§é»é­ç³', '200');
INSERT INTO `shop_sell_price` VALUES ('40322', 'ä¸çº§é»é­ç³', '400');
INSERT INTO `shop_sell_price` VALUES ('40323', 'åçº§é»é­ç³', '800');
INSERT INTO `shop_sell_price` VALUES ('40324', 'äºçº§é»é­ç³', '1600');
INSERT INTO `shop_sell_price` VALUES ('40325', 'äºæ®µå¼é­æ³éª°å­', '5000');
INSERT INTO `shop_sell_price` VALUES ('40326', 'ä¸æ®µå¼é­æ³éª°å­', '5000');
INSERT INTO `shop_sell_price` VALUES ('40327', 'åæ®µå¼é­æ³éª°å­', '5000');
INSERT INTO `shop_sell_price` VALUES ('40328', 'å­æ®µå¼é­æ³éª°å­', '5000');
INSERT INTO `shop_sell_price` VALUES ('40329', 'åä½æ°å¾è¾', '0');
INSERT INTO `shop_sell_price` VALUES ('40330', 'æ éç®­ç­', '5000');
INSERT INTO `shop_sell_price` VALUES ('40331', 'è¯ç¹åå£«ä¹å', '0');
INSERT INTO `shop_sell_price` VALUES ('40332', 'è¯ç¹å°æä¹å¼', '0');
INSERT INTO `shop_sell_price` VALUES ('40333', 'è¯ç¹æ³å¸é­æ', '0');
INSERT INTO `shop_sell_price` VALUES ('40334', 'è¯ç¹åºå®¢åå', '0');
INSERT INTO `shop_sell_price` VALUES ('40335', 'è¯ç¹æå£«æ§å¤´', '0');
INSERT INTO `shop_sell_price` VALUES ('40336', 'è¯ç¹å¾½ç« é¿é´', '0');
INSERT INTO `shop_sell_price` VALUES ('40337', 'è¯ç¹å¾½ç« çç²', '0');
INSERT INTO `shop_sell_price` VALUES ('40338', 'è¯ç¹å¾½ç« æå¥', '0');
INSERT INTO `shop_sell_price` VALUES ('40339', 'è¯ç¹å¾½ç« ç¾ç', '0');
INSERT INTO `shop_sell_price` VALUES ('40340', 'è¯ç¹å¾½ç« å¤´ç', '0');
INSERT INTO `shop_sell_price` VALUES ('40341', 'å®å¡çæ¯ä¹é³', '0');
INSERT INTO `shop_sell_price` VALUES ('40342', 'å®å¡çæ¯ä¹çª', '0');
INSERT INTO `shop_sell_price` VALUES ('40343', 'å®å¡çæ¯ä¹ç¼', '0');
INSERT INTO `shop_sell_price` VALUES ('40344', 'å®å¡çæ¯ä¹è¡', '0');
INSERT INTO `shop_sell_price` VALUES ('40345', 'å®å¡çæ¯ä¹è', '0');
INSERT INTO `shop_sell_price` VALUES ('40346', 'å®å¡çæ¯ä¹å¿', '0');
INSERT INTO `shop_sell_price` VALUES ('40347', 'å®å¡çæ¯ä¹éª¨', '0');
INSERT INTO `shop_sell_price` VALUES ('40348', 'å®å¡çæ¯ä¹ç', '0');
INSERT INTO `shop_sell_price` VALUES ('40349', 'å·´æå¡æ¯ä¹é³', '0');
INSERT INTO `shop_sell_price` VALUES ('40350', 'å·´æå¡æ¯ä¹çª', '0');
INSERT INTO `shop_sell_price` VALUES ('40351', 'å·´æå¡æ¯ä¹ç¼', '0');
INSERT INTO `shop_sell_price` VALUES ('40352', 'å·´æå¡æ¯ä¹è¡', '0');
INSERT INTO `shop_sell_price` VALUES ('40353', 'å·´æå¡æ¯ä¹è', '0');
INSERT INTO `shop_sell_price` VALUES ('40354', 'å·´æå¡æ¯ä¹å¿', '0');
INSERT INTO `shop_sell_price` VALUES ('40355', 'å·´æå¡æ¯ä¹éª¨', '0');
INSERT INTO `shop_sell_price` VALUES ('40356', 'å·´æå¡æ¯ä¹ç', '0');
INSERT INTO `shop_sell_price` VALUES ('40357', 'æ³å©æä¹é³', '0');
INSERT INTO `shop_sell_price` VALUES ('40358', 'æ³å©æä¹çª', '0');
INSERT INTO `shop_sell_price` VALUES ('40359', 'æ³å©æä¹ç¼', '0');
INSERT INTO `shop_sell_price` VALUES ('40360', 'æ³å©æä¹è¡', '0');
INSERT INTO `shop_sell_price` VALUES ('40361', 'æ³å©æä¹è', '0');
INSERT INTO `shop_sell_price` VALUES ('40362', 'æ³å©æä¹å¿', '0');
INSERT INTO `shop_sell_price` VALUES ('40363', 'æ³å©æä¹éª¨', '0');
INSERT INTO `shop_sell_price` VALUES ('40364', 'æ³å©æä¹ç', '0');
INSERT INTO `shop_sell_price` VALUES ('40365', 'æå¾·æå°ä¹é³', '0');
INSERT INTO `shop_sell_price` VALUES ('40366', 'æå¾·æå°ä¹çª', '0');
INSERT INTO `shop_sell_price` VALUES ('40367', 'æå¾·æå°ä¹ç¼', '0');
INSERT INTO `shop_sell_price` VALUES ('40368', 'æå¾·æå°ä¹è¡', '0');
INSERT INTO `shop_sell_price` VALUES ('40369', 'æå¾·æå°ä¹è', '0');
INSERT INTO `shop_sell_price` VALUES ('40370', 'æå¾·æå°ä¹å¿', '0');
INSERT INTO `shop_sell_price` VALUES ('40371', 'æå¾·æå°ä¹éª¨', '0');
INSERT INTO `shop_sell_price` VALUES ('40372', 'æå¾·æå°ä¹ç', '0');
INSERT INTO `shop_sell_price` VALUES ('40373', 'å°å¾:å¤§éå¨å¾', '500');
INSERT INTO `shop_sell_price` VALUES ('40374', 'å°å¾:è¯´è¯ä¹å²', '30');
INSERT INTO `shop_sell_price` VALUES ('40375', 'å°å¾:å¤é²ä¸', '43');
INSERT INTO `shop_sell_price` VALUES ('40376', 'å°å¾:è¯ç¹å', '43');
INSERT INTO `shop_sell_price` VALUES ('40377', 'å°å¾:å¦é­åå ¡', '33');
INSERT INTO `shop_sell_price` VALUES ('40378', 'å°å¾:å¦ç²¾æ£®æ', '33');
INSERT INTO `shop_sell_price` VALUES ('40379', 'å°å¾:é£æ¨ä¹å', '33');
INSERT INTO `shop_sell_price` VALUES ('40380', 'å°å¾:é¶éªå£«æåº', '31');
INSERT INTO `shop_sell_price` VALUES ('40381', 'å°å¾:é¾ä¹è°·', '43');
INSERT INTO `shop_sell_price` VALUES ('40382', 'å°å¾:å¥å²©', '41');
INSERT INTO `shop_sell_price` VALUES ('40383', 'å°å¾:æ­å±ä¹å²', '38');
INSERT INTO `shop_sell_price` VALUES ('40384', 'å°å¾:éèä¹è°·', '36');
INSERT INTO `shop_sell_price` VALUES ('40385', 'å°å¾:æµ·é³', '33');
INSERT INTO `shop_sell_price` VALUES ('40386', 'å°å¾:ç«é¾çª', '31');
INSERT INTO `shop_sell_price` VALUES ('40387', 'å°å¾:æ¬§ç', '43');
INSERT INTO `shop_sell_price` VALUES ('40388', 'å°å¾:äºä¸', '41');
INSERT INTO `shop_sell_price` VALUES ('40389', 'å°å¾:æ²é»æ´ç©´', '39');
INSERT INTO `shop_sell_price` VALUES ('40390', 'å°å¾:æµ·è´¼å²', '25');
INSERT INTO `shop_sell_price` VALUES ('40391', 'è®¡ç®å¨', '35');
INSERT INTO `shop_sell_price` VALUES ('40392', 'è¶è¯æ ', '33');
INSERT INTO `shop_sell_price` VALUES ('40393', 'ç«é¾é³', '50');
INSERT INTO `shop_sell_price` VALUES ('40394', 'é£é¾é³', '100');
INSERT INTO `shop_sell_price` VALUES ('40395', 'æ°´é¾é³', '100');
INSERT INTO `shop_sell_price` VALUES ('40396', 'å°é¾é³', '100');
INSERT INTO `shop_sell_price` VALUES ('40397', 'å¥ç¾æä¹ç®(é¾)', '100');
INSERT INTO `shop_sell_price` VALUES ('40398', 'å¥ç¾æä¹ç®(å±±ç¾)', '100');
INSERT INTO `shop_sell_price` VALUES ('40399', 'å¥ç¾æä¹ç®(ç®å­)', '100');
INSERT INTO `shop_sell_price` VALUES ('40400', 'å¥ç¾æä¹ç®(è)', '100');
INSERT INTO `shop_sell_price` VALUES ('40401', 'è¯åçç®é©(ç«)', '100');
INSERT INTO `shop_sell_price` VALUES ('40402', 'è¯åçç®é©(æ°´)', '100');
INSERT INTO `shop_sell_price` VALUES ('40403', 'è¯åçç®é©(é£)', '100');
INSERT INTO `shop_sell_price` VALUES ('40404', 'è¯åçç®é©(å°)', '100');
INSERT INTO `shop_sell_price` VALUES ('40405', 'ç®é©', '2');
INSERT INTO `shop_sell_price` VALUES ('40406', 'é«çº§ç®é©', '20');
INSERT INTO `shop_sell_price` VALUES ('40407', 'éª¨å¤´ç¢ç', '10');
INSERT INTO `shop_sell_price` VALUES ('40408', 'éå±å', '20');
INSERT INTO `shop_sell_price` VALUES ('40409', 'ä¸æ­»é¸ä¹å¿', '980');
INSERT INTO `shop_sell_price` VALUES ('40410', 'é»æå®ç¹çæ ç®', '45');
INSERT INTO `shop_sell_price` VALUES ('40411', 'é»æå®ç¹çæ°´æ', '123');
INSERT INTO `shop_sell_price` VALUES ('40412', 'é»æå®ç¹çæ æ', '2');
INSERT INTO `shop_sell_price` VALUES ('40413', 'å°ä¹å¥³çä¹å¿', '341');
INSERT INTO `shop_sell_price` VALUES ('40414', 'ç¼éæ¯ä¹ç³', '231');
INSERT INTO `shop_sell_price` VALUES ('40415', 'éç©è¢å­', '546');
INSERT INTO `shop_sell_price` VALUES ('40416', 'è¯åä¹è¡', '552');
INSERT INTO `shop_sell_price` VALUES ('40417', 'ç²¾çµç»æ¶', '465');
INSERT INTO `shop_sell_price` VALUES ('40418', 'å è½çè´¢ç©', '451');
INSERT INTO `shop_sell_price` VALUES ('40419', 'å·¨å¤§è«å¦®äºèèä¸', '451');
INSERT INTO `shop_sell_price` VALUES ('40420', 'å¤ä»£äººçåæ¯ä¹¦1å', '50');
INSERT INTO `shop_sell_price` VALUES ('40421', 'å¤ä»£äººçåæ¯ä¹¦2å', '50');
INSERT INTO `shop_sell_price` VALUES ('40422', 'å¤ä»£äººçåæ¯ä¹¦3å', '50');
INSERT INTO `shop_sell_price` VALUES ('40423', 'å¤ä»£äººçåæ¯ä¹¦4å', '50');
INSERT INTO `shop_sell_price` VALUES ('40424', 'ç¼ç®', '341');
INSERT INTO `shop_sell_price` VALUES ('40425', 'é»ææ æèè¯æ°´', '345');
INSERT INTO `shop_sell_price` VALUES ('40426', 'é»ææ æèææ', '1410');
INSERT INTO `shop_sell_price` VALUES ('40427', 'é»æå¦ç²¾è¢å­', '576');
INSERT INTO `shop_sell_price` VALUES ('40428', 'æåä¹æ³ª', '341');
INSERT INTO `shop_sell_price` VALUES ('40429', 'å¤§æ´ç©´å·è½´ç¢ç', '50');
INSERT INTO `shop_sell_price` VALUES ('40430', 'å¤§æ´ç©´æ°´æ¶', '45');
INSERT INTO `shop_sell_price` VALUES ('40431', 'é¼¹é¼ çç®', '98');
INSERT INTO `shop_sell_price` VALUES ('40432', 'å¤§æ´ç©´å·è½´ç¢ç', '50');
INSERT INTO `shop_sell_price` VALUES ('40433', 'ç°ç³ä¹çª', '98');
INSERT INTO `shop_sell_price` VALUES ('40434', 'ç°ç³çå°¾å·´', '45');
INSERT INTO `shop_sell_price` VALUES ('40435', 'æ·±æ¸ä¹è±çè±è', '213');
INSERT INTO `shop_sell_price` VALUES ('40436', 'æ·±æ¸ä¹è±çæ ¹', '241');
INSERT INTO `shop_sell_price` VALUES ('40437', 'æ·±æ¸è±ææ¡', '23');
INSERT INTO `shop_sell_price` VALUES ('40438', 'èè ä¹ç', '212');
INSERT INTO `shop_sell_price` VALUES ('40439', 'ç½ééå±æ¿', '231');
INSERT INTO `shop_sell_price` VALUES ('40440', 'ç½é', '456');
INSERT INTO `shop_sell_price` VALUES ('40441', 'ç½éåç³', '25');
INSERT INTO `shop_sell_price` VALUES ('40442', 'å¸æä¼¯çèæ¶²', '235');
INSERT INTO `shop_sell_price` VALUES ('40443', 'é»è²ç±³ç´¢è', '451');
INSERT INTO `shop_sell_price` VALUES ('40444', 'é»è²ç±³ç´¢èåç³', '546');
INSERT INTO `shop_sell_price` VALUES ('40445', 'é»è²ç±³ç´¢èéå±æ¿', '235');
INSERT INTO `shop_sell_price` VALUES ('40446', 'é»æ³å¸ææ', '1456');
INSERT INTO `shop_sell_price` VALUES ('40447', 'é»èçç®', '351');
INSERT INTO `shop_sell_price` VALUES ('40448', 'é»èççª', '124');
INSERT INTO `shop_sell_price` VALUES ('40449', 'é»èçç', '341');
INSERT INTO `shop_sell_price` VALUES ('40450', 'é»æå®ç¹çæ æ', '341');
INSERT INTO `shop_sell_price` VALUES ('40451', 'é»èä¹å¿', '560');
INSERT INTO `shop_sell_price` VALUES ('40452', 'å¤å½å¸ææ', '1657');
INSERT INTO `shop_sell_price` VALUES ('40453', 'å¤å½å¸é¿é­', '1345');
INSERT INTO `shop_sell_price` VALUES ('40454', 'é©¯å½å¸ææ', '1653');
INSERT INTO `shop_sell_price` VALUES ('40455', 'èè²å¸æ', '100');
INSERT INTO `shop_sell_price` VALUES ('40456', 'çº¢è²å¸æ', '100');
INSERT INTO `shop_sell_price` VALUES ('40457', 'ç½è²å¸æ', '100');
INSERT INTO `shop_sell_price` VALUES ('40458', 'åæçé³ç', '54');
INSERT INTO `shop_sell_price` VALUES ('40459', 'æ¯èä¹ç®', '87');
INSERT INTO `shop_sell_price` VALUES ('40460', 'é¿è¥¿å¡åºå¥¥çç°ç¬', '165');
INSERT INTO `shop_sell_price` VALUES ('40461', 'æ¶é­çé»è²èé£', '12');
INSERT INTO `shop_sell_price` VALUES ('40462', 'æ¶é­ççº¢è²èé£', '34');
INSERT INTO `shop_sell_price` VALUES ('40463', 'æ¶é­çèè²èé£', '54');
INSERT INTO `shop_sell_price` VALUES ('40464', 'æ¶é­çç½è²èé£', '43');
INSERT INTO `shop_sell_price` VALUES ('40465', 'ç²¾çµä½¿ææ', '451');
INSERT INTO `shop_sell_price` VALUES ('40466', 'é¾ä¹å¿', '4526');
INSERT INTO `shop_sell_price` VALUES ('40467', 'é¶', '341');
INSERT INTO `shop_sell_price` VALUES ('40468', 'é¶åç³', '23');
INSERT INTO `shop_sell_price` VALUES ('40469', 'é¶éå±æ¿', '65');
INSERT INTO `shop_sell_price` VALUES ('40470', 'åç³ç¢ç', '32');
INSERT INTO `shop_sell_price` VALUES ('40471', 'ç²¾çµç¢ç', '54');
INSERT INTO `shop_sell_price` VALUES ('40472', 'å°ç±ç¬ä¹ç®', '65');
INSERT INTO `shop_sell_price` VALUES ('40473', 'å è½é°å', '774');
INSERT INTO `shop_sell_price` VALUES ('40474', 'å è½ä¹æ¯', '768');
INSERT INTO `shop_sell_price` VALUES ('40475', 'å è½é¦çº§', '0');
INSERT INTO `shop_sell_price` VALUES ('40476', 'å è½ä¹æ', '546');
INSERT INTO `shop_sell_price` VALUES ('40477', 'å è½çæ¶é­ä¹¦1å', '50');
INSERT INTO `shop_sell_price` VALUES ('40478', 'å è½çæ¶é­ä¹¦2å', '50');
INSERT INTO `shop_sell_price` VALUES ('40479', 'å è½çæ¶é­ä¹¦3å', '50');
INSERT INTO `shop_sell_price` VALUES ('40480', 'å è½çæ¶é­ä¹¦4å', '50');
INSERT INTO `shop_sell_price` VALUES ('40481', 'å è½ä¹ç', '546');
INSERT INTO `shop_sell_price` VALUES ('40482', 'å è½ä¹è', '541');
INSERT INTO `shop_sell_price` VALUES ('40483', 'éå±èè£çç®', '234');
INSERT INTO `shop_sell_price` VALUES ('40484', 'éå±èè£çæ¯æ¶²', '464');
INSERT INTO `shop_sell_price` VALUES ('40485', 'éå±èè£çç', '341');
INSERT INTO `shop_sell_price` VALUES ('40486', 'ç«å±±ç°', '351');
INSERT INTO `shop_sell_price` VALUES ('40487', 'é»ééå±æ¿', '682');
INSERT INTO `shop_sell_price` VALUES ('40488', 'é»é', '264');
INSERT INTO `shop_sell_price` VALUES ('40489', 'é»éåç³', '543');
INSERT INTO `shop_sell_price` VALUES ('40490', 'é»æåç´ ç³', '874');
INSERT INTO `shop_sell_price` VALUES ('40491', 'æ ¼å©è¬ç¾½æ¯', '674');
INSERT INTO `shop_sell_price` VALUES ('40492', 'ç»¿æ°´æ¶', '24');
INSERT INTO `shop_sell_price` VALUES ('40493', 'é­æ³ç¬å­', '657');
INSERT INTO `shop_sell_price` VALUES ('40494', 'çº¯ç²¹çç±³ç´¢èå', '231');
INSERT INTO `shop_sell_price` VALUES ('40495', 'ç±³ç´¢èçº¿', '541');
INSERT INTO `shop_sell_price` VALUES ('40496', 'ç²ç³çç±³ç´¢èå', '879');
INSERT INTO `shop_sell_price` VALUES ('40497', 'ç±³ç´¢èéå±æ¿', '546');
INSERT INTO `shop_sell_price` VALUES ('40498', 'é£ä¹æ³ª', '431');
INSERT INTO `shop_sell_price` VALUES ('40499', 'èèæ±', '76');
INSERT INTO `shop_sell_price` VALUES ('40500', 'ç´«æ°´æ¶', '0');
INSERT INTO `shop_sell_price` VALUES ('40501', 'çº¢æ°´æ¶', '0');
INSERT INTO `shop_sell_price` VALUES ('40502', 'çº¿', '25');
INSERT INTO `shop_sell_price` VALUES ('40503', 'è®åå¦®çç½', '314');
INSERT INTO `shop_sell_price` VALUES ('40504', 'è®åå¦®çèç®', '652');
INSERT INTO `shop_sell_price` VALUES ('40505', 'å®ç¹ä¹æ ç®', '431');
INSERT INTO `shop_sell_price` VALUES ('40506', 'å®ç¹çæ°´æ', '765');
INSERT INTO `shop_sell_price` VALUES ('40507', 'å®ç¹çæ æ', '1');
INSERT INTO `shop_sell_price` VALUES ('40508', 'å¥¥éåé²æ ¹', '454');
INSERT INTO `shop_sell_price` VALUES ('40509', 'å¥¥éåé²æ ¹éå±æ¿', '985');
INSERT INTO `shop_sell_price` VALUES ('40510', 'æ±¡æµå®ç¹çæ ç®', '0');
INSERT INTO `shop_sell_price` VALUES ('40511', 'æ±¡æµå®ç¹çæ°´æ', '0');
INSERT INTO `shop_sell_price` VALUES ('40512', 'æ±¡æµå®ç¹çæ æ', '0');
INSERT INTO `shop_sell_price` VALUES ('40513', 'é£äººå·¨é­çè¡', '643');
INSERT INTO `shop_sell_price` VALUES ('40514', 'ç²¾çµä¹æ³ª', '1000');
INSERT INTO `shop_sell_price` VALUES ('40515', 'åç´ ç³', '431');
INSERT INTO `shop_sell_price` VALUES ('40516', 'è´¨éç»¿æ°´æ¶', '0');
INSERT INTO `shop_sell_price` VALUES ('40517', 'è´¨éçº¢æ°´æ¶', '0');
INSERT INTO `shop_sell_price` VALUES ('40518', 'è´¨éèæ°´æ¶', '0');
INSERT INTO `shop_sell_price` VALUES ('40519', 'æ½çé¬æ¯', '0');
INSERT INTO `shop_sell_price` VALUES ('40520', 'ç²¾çµç²æ«', '0');
INSERT INTO `shop_sell_price` VALUES ('40521', 'ç²¾çµç¾½ç¿¼', '0');
INSERT INTO `shop_sell_price` VALUES ('40522', 'èæ°´æ¶', '0');
INSERT INTO `shop_sell_price` VALUES ('40523', 'ç½æ°´æ¶', '0');
INSERT INTO `shop_sell_price` VALUES ('40524', 'é»è²è¡ç', '786');
INSERT INTO `shop_sell_price` VALUES ('40525', 'æ ¼å°è¯ä¹æ³ª', '0');
INSERT INTO `shop_sell_price` VALUES ('40526', 'èéå±æ¿', '0');
INSERT INTO `shop_sell_price` VALUES ('40527', 'éå¤´', '0');
INSERT INTO `shop_sell_price` VALUES ('40528', 'å®æ¤ç¥ä¹è¢', '0');
INSERT INTO `shop_sell_price` VALUES ('40529', 'æè°¢ä¿¡', '50');
INSERT INTO `shop_sell_price` VALUES ('40530', 'å¤ä»£çæçé¥å', '0');
INSERT INTO `shop_sell_price` VALUES ('40531', 'å¤ä»£éªå£«çé¥å', '0');
INSERT INTO `shop_sell_price` VALUES ('40532', 'å¤ä»£æ³å¸çé¥å', '0');
INSERT INTO `shop_sell_price` VALUES ('40533', 'å¤ä»£é¥å(ä¸åé¨)', '0');
INSERT INTO `shop_sell_price` VALUES ('40534', 'å¤ä»£é¥å(ä¸åé¨)', '0');
INSERT INTO `shop_sell_price` VALUES ('40535', 'å¤ä»£å¦ç²¾çé¥å', '0');
INSERT INTO `shop_sell_price` VALUES ('40536', 'å¤ä»£æ¶é­çè®°è½½', '50');
INSERT INTO `shop_sell_price` VALUES ('40537', 'å¤ä»£çéç©', '0');
INSERT INTO `shop_sell_price` VALUES ('40538', 'é£å°¸é¬¼çæç²', '341');
INSERT INTO `shop_sell_price` VALUES ('40539', 'é£å°¸é¬¼ççé½¿', '214');
INSERT INTO `shop_sell_price` VALUES ('40540', 'å¤èçäº¤ææä»¶', '50');
INSERT INTO `shop_sell_price` VALUES ('40541', 'é»æä¹æ', '0');
INSERT INTO `shop_sell_price` VALUES ('40542', 'åå½¢æªçè¡', '0');
INSERT INTO `shop_sell_price` VALUES ('40543', 'èå¥³æ¿é´é¥å', '0');
INSERT INTO `shop_sell_price` VALUES ('40544', 'èå¥³ä¹é³', '452');
INSERT INTO `shop_sell_price` VALUES ('40545', 'ä¼¦å¾ä¹è¢', '50');
INSERT INTO `shop_sell_price` VALUES ('40546', 'é©¬æ²ä¹è¢', '0');
INSERT INTO `shop_sell_price` VALUES ('40547', 'ææ°çéç©', '0');
INSERT INTO `shop_sell_price` VALUES ('40548', 'å¤ä»£äº¡çµä¹è¢', '0');
INSERT INTO `shop_sell_price` VALUES ('40549', 'çé­ä¹å', '0');
INSERT INTO `shop_sell_price` VALUES ('40550', 'çé­ä¹ç¼', '0');
INSERT INTO `shop_sell_price` VALUES ('40551', 'çé­ä¹çª', '0');
INSERT INTO `shop_sell_price` VALUES ('40552', 'çé­ä¹å¿', '0');
INSERT INTO `shop_sell_price` VALUES ('40553', 'å¸é²è¿ªå¡ä¹è¢', '50');
INSERT INTO `shop_sell_price` VALUES ('40554', 'ç§å¯åå', '50');
INSERT INTO `shop_sell_price` VALUES ('40555', 'å¯å®¤é¥å', '0');
INSERT INTO `shop_sell_price` VALUES ('40556', 'ææååä¹è¢', '50');
INSERT INTO `shop_sell_price` VALUES ('40557', 'ææåå(å¤é²ä¸æ)', '50');
INSERT INTO `shop_sell_price` VALUES ('40558', 'ææåå(å¥å²©æ)', '50');
INSERT INTO `shop_sell_price` VALUES ('40559', 'ææåå(äºä¸åé)', '50');
INSERT INTO `shop_sell_price` VALUES ('40560', 'ææåå(é£æ¨æ)', '50');
INSERT INTO `shop_sell_price` VALUES ('40561', 'ææåå(è¯ç¹æ)', '50');
INSERT INTO `shop_sell_price` VALUES ('40562', 'ææåå(æµ·é³æ)', '50');
INSERT INTO `shop_sell_price` VALUES ('40563', 'ææåå(çæ³æ)', '50');
INSERT INTO `shop_sell_price` VALUES ('40564', 'çå½çå·è½´', '50');
INSERT INTO `shop_sell_price` VALUES ('40565', 'æç´¢ç¶', '50');
INSERT INTO `shop_sell_price` VALUES ('40566', 'ç¥ç§è´å£³', '0');
INSERT INTO `shop_sell_price` VALUES ('40567', 'ç¥ç§æ°´æ¶ç', '0');
INSERT INTO `shop_sell_price` VALUES ('40568', 'ç¥ç§çè¢å­', '0');
INSERT INTO `shop_sell_price` VALUES ('40569', 'ç¥ç§é­æ', '0');
INSERT INTO `shop_sell_price` VALUES ('40570', 'è¾èäºçåæ¥', '0');
INSERT INTO `shop_sell_price` VALUES ('40571', 'åºå®¢é¦é¢çç®±å­', '0');
INSERT INTO `shop_sell_price` VALUES ('40572', 'åºå®¢ä¹è¯', '0');
INSERT INTO `shop_sell_price` VALUES ('40573', 'çµé­ä¹è¯', '50');
INSERT INTO `shop_sell_price` VALUES ('40574', 'çµé­ä¹è¯', '50');
INSERT INTO `shop_sell_price` VALUES ('40575', 'çµé­ä¹è¯', '50');
INSERT INTO `shop_sell_price` VALUES ('40576', 'çµé­æ°´æ¶', '0');
INSERT INTO `shop_sell_price` VALUES ('40577', 'çµé­æ°´æ¶', '0');
INSERT INTO `shop_sell_price` VALUES ('40578', 'çµé­æ°´æ¶', '0');
INSERT INTO `shop_sell_price` VALUES ('40579', 'ä¸æ­»æçéª¨å¤´', '0');
INSERT INTO `shop_sell_price` VALUES ('40580', 'ä¸æ­»æçéª¨å¤´ç¢ç', '0');
INSERT INTO `shop_sell_price` VALUES ('40581', 'ä¸æ­»æçé¥å', '0');
INSERT INTO `shop_sell_price` VALUES ('40582', 'å®è¿ªäºä¹è¢', '0');
INSERT INTO `shop_sell_price` VALUES ('40583', 'å®è¿ªäºä¹ä¿¡', '50');
INSERT INTO `shop_sell_price` VALUES ('40584', 'éªæªé¦çº§', '0');
INSERT INTO `shop_sell_price` VALUES ('40585', 'å¦é­é¿èé¦çº§', '0');
INSERT INTO `shop_sell_price` VALUES ('40586', 'çæå¾½ç« çç¢ç', '0');
INSERT INTO `shop_sell_price` VALUES ('40587', 'çæå¾½ç« çç¢ç', '0');
INSERT INTO `shop_sell_price` VALUES ('40588', 'å¦ç²¾æå®ç©', '0');
INSERT INTO `shop_sell_price` VALUES ('40589', 'ææçé¥å', '0');
INSERT INTO `shop_sell_price` VALUES ('40590', 'æ¨æéæ©ä¹çª', '760');
INSERT INTO `shop_sell_price` VALUES ('40591', 'åè¯åçé­æ³ä¹¦', '50');
INSERT INTO `shop_sell_price` VALUES ('40592', 'åè¯åçç²¾çµä¹¦', '50');
INSERT INTO `shop_sell_price` VALUES ('40593', 'è°æ¥ç°¿çç¼ºé¡µ', '50');
INSERT INTO `shop_sell_price` VALUES ('40594', 'åµå°¸é¥å', '0');
INSERT INTO `shop_sell_price` VALUES ('40595', 'æ­»äº¡ä¹è¯', '0');
INSERT INTO `shop_sell_price` VALUES ('40596', 'æ­»äº¡èªçº¦', '50');
INSERT INTO `shop_sell_price` VALUES ('40597', 'ç ´æçè°æ¥ç°¿', '50');
INSERT INTO `shop_sell_price` VALUES ('40598', 'åº·ä¹è¢', '50');
INSERT INTO `shop_sell_price` VALUES ('40599', 'å¡ææ¯çé­æ³è¢', '0');
INSERT INTO `shop_sell_price` VALUES ('40600', 'å è½é¥å', '0');
INSERT INTO `shop_sell_price` VALUES ('40601', 'é¾é¾ç²', '451');
INSERT INTO `shop_sell_price` VALUES ('40602', 'èè²é¿ç¬', '0');
INSERT INTO `shop_sell_price` VALUES ('40603', 'èèæ¯æ¶²', '50');
INSERT INTO `shop_sell_price` VALUES ('40604', 'éª·é«é¥å', '210');
INSERT INTO `shop_sell_price` VALUES ('40605', 'éª·é«å¤´', '760');
INSERT INTO `shop_sell_price` VALUES ('40606', 'æ··æ²é¥å', '0');
INSERT INTO `shop_sell_price` VALUES ('40607', 'è¿çè¯æ°´', '0');
INSERT INTO `shop_sell_price` VALUES ('40608', 'é»éªå£«çèªçº¦', '100');
INSERT INTO `shop_sell_price` VALUES ('40609', 'çå°å¦é­é­æ³ä¹¦', '100');
INSERT INTO `shop_sell_price` VALUES ('40610', 'é£é²å å¦é­é­æ³ä¹¦', '100');
INSERT INTO `shop_sell_price` VALUES ('40611', 'é½è¾¾çæå¦é­é­æ³ä¹¦', '100');
INSERT INTO `shop_sell_price` VALUES ('40612', 'é¿åå·´å¦é­é­æ³ä¹¦', '100');
INSERT INTO `shop_sell_price` VALUES ('40613', 'é»é¥å', '0');
INSERT INTO `shop_sell_price` VALUES ('40614', 'ç¿ç©æ¶éæä»¶', '50');
INSERT INTO `shop_sell_price` VALUES ('40615', 'æå½±ç¥æ®¿2æ¥¼é¥å', '50');
INSERT INTO `shop_sell_price` VALUES ('40616', 'æå½±ç¥æ®¿3æ¥¼é¥å', '50');
INSERT INTO `shop_sell_price` VALUES ('40617', 'æ°´æ¶ç', '0');
INSERT INTO `shop_sell_price` VALUES ('40618', 'åä¹æ°æ¯', '0');
INSERT INTO `shop_sell_price` VALUES ('40619', 'ä¸æ¹çç±é¥å', '0');
INSERT INTO `shop_sell_price` VALUES ('40620', 'ç¬¬äºè¿·å®«é¥å', '0');
INSERT INTO `shop_sell_price` VALUES ('40621', 'å¾·é·åé¥å', '0');
INSERT INTO `shop_sell_price` VALUES ('40622', 'é£é¾ççªå­', '0');
INSERT INTO `shop_sell_price` VALUES ('40623', 'å¤é²å1ä¸ä¼ å®¶ä¹å®', '0');
INSERT INTO `shop_sell_price` VALUES ('40624', 'å¤é²å2ä¸ä¼ å®¶ä¹å®', '0');
INSERT INTO `shop_sell_price` VALUES ('40625', 'å¤é²å3ä¸ä¼ å®¶ä¹å®', '0');
INSERT INTO `shop_sell_price` VALUES ('40626', 'å¤é²å4ä¸ä¼ å®¶ä¹å®', '0');
INSERT INTO `shop_sell_price` VALUES ('40627', 'å¤é²å5ä¸ä¼ å®¶ä¹å®', '0');
INSERT INTO `shop_sell_price` VALUES ('40628', 'å¤é²å6ä¸ä¼ å®¶ä¹å®', '0');
INSERT INTO `shop_sell_price` VALUES ('40629', 'å¤é²å7ä¸ä¼ å®¶ä¹å®', '0');
INSERT INTO `shop_sell_price` VALUES ('40630', 'è¿ªå¥çæ§æ¥è®°', '50');
INSERT INTO `shop_sell_price` VALUES ('40631', 'è±æ¯å¡çææ', '0');
INSERT INTO `shop_sell_price` VALUES ('40632', 'é·å¥¥çº³çè¢å­', '0');
INSERT INTO `shop_sell_price` VALUES ('40633', 'è¥è´äººçæ¥å', '50');
INSERT INTO `shop_sell_price` VALUES ('40634', 'è¥è´äººçå®ç©', '0');
INSERT INTO `shop_sell_price` VALUES ('40635', 'æ³ä»¤åå¢å°è®°', '0');
INSERT INTO `shop_sell_price` VALUES ('40636', 'æ³ä»¤åçå°è®°ç', '0');
INSERT INTO `shop_sell_price` VALUES ('40637', 'çåå·´çä¿¡', '50');
INSERT INTO `shop_sell_price` VALUES ('40638', 'é­å½åå¢å°è®°', '0');
INSERT INTO `shop_sell_price` VALUES ('40639', 'é­å½åçå°è®°ç', '0');
INSERT INTO `shop_sell_price` VALUES ('40640', 'å¥æ³åçå°è®°ç', '0');
INSERT INTO `shop_sell_price` VALUES ('40641', 'è¯´è¯å·è½´', '50');
INSERT INTO `shop_sell_price` VALUES ('40642', 'å¥æ³åå¢å°è®°', '0');
INSERT INTO `shop_sell_price` VALUES ('40643', 'æ°´ä¹æ°æ¯', '0');
INSERT INTO `shop_sell_price` VALUES ('40644', 'è¿·å®«æé å¾', '50');
INSERT INTO `shop_sell_price` VALUES ('40645', 'é£ä¹æ°æ¯', '0');
INSERT INTO `shop_sell_price` VALUES ('40646', 'è¥è´çè§', '0');
INSERT INTO `shop_sell_price` VALUES ('40647', 'èå®å¾ç¢ç', '50');
INSERT INTO `shop_sell_price` VALUES ('40648', 'çéçåºå®¢ä¹å', '0');
INSERT INTO `shop_sell_price` VALUES ('40649', 'ä¸åæ¹çç±é¥å', '0');
INSERT INTO `shop_sell_price` VALUES ('40650', 'åæ¹çç±é¥å', '0');
INSERT INTO `shop_sell_price` VALUES ('40651', 'ç«ä¹æ°æ¯', '0');
INSERT INTO `shop_sell_price` VALUES ('40652', 'çç§çç®', '0');
INSERT INTO `shop_sell_price` VALUES ('40653', 'çº¢é¥å', '0');
INSERT INTO `shop_sell_price` VALUES ('40654', 'ç¬¬ä¸è¿·å®«é¥å', '0');
INSERT INTO `shop_sell_price` VALUES ('40655', 'æ°´æ¶ä¹ç', '0');
INSERT INTO `shop_sell_price` VALUES ('40656', 'è¯ç¼ä¹åA', '0');
INSERT INTO `shop_sell_price` VALUES ('40657', 'è¯ç¼ä¹åB', '0');
INSERT INTO `shop_sell_price` VALUES ('40658', 'è¯ç¼ä¹åC', '0');
INSERT INTO `shop_sell_price` VALUES ('40659', 'è¯ç¼ä¹åD', '0');
INSERT INTO `shop_sell_price` VALUES ('40660', 'è¯ç¼å·è½´', '50');
INSERT INTO `shop_sell_price` VALUES ('40661', 'å¿å­çééª¸', '0');
INSERT INTO `shop_sell_price` VALUES ('40662', 'å¿å­çèåç»', '50');
INSERT INTO `shop_sell_price` VALUES ('40663', 'å¿å­çä¿¡', '50');
INSERT INTO `shop_sell_price` VALUES ('40664', 'é¿ææ¯çæ¤èº«ç¬¦', '50');
INSERT INTO `shop_sell_price` VALUES ('40665', 'é¿ææ¯çä¿¡', '50');
INSERT INTO `shop_sell_price` VALUES ('40666', 'æ æ³å¾ç¥çä¼ å®¶ä¹å®', '0');
INSERT INTO `shop_sell_price` VALUES ('40667', 'ææåå¢å°è®°', '0');
INSERT INTO `shop_sell_price` VALUES ('40668', 'ææåçå°è®°ç', '0');
INSERT INTO `shop_sell_price` VALUES ('40669', 'ç«ç°ä¹å½±èéª¨', '0');
INSERT INTO `shop_sell_price` VALUES ('40670', 'ç«ç°ä¹å½±å°¾å·´', '0');
INSERT INTO `shop_sell_price` VALUES ('40671', 'ç«ç°ä¹å½±éª¨ç¿¼', '0');
INSERT INTO `shop_sell_price` VALUES ('40672', 'ç«ç°ä¹å½±èæ¤', '0');
INSERT INTO `shop_sell_price` VALUES ('40673', 'ç«ç°ä¹å½±é¦çº§', '0');
INSERT INTO `shop_sell_price` VALUES ('40674', 'ç«ç°ä¹å½±æç²', '0');
INSERT INTO `shop_sell_price` VALUES ('40675', 'é»æç¿ç³', '0');
INSERT INTO `shop_sell_price` VALUES ('40676', '?ä¹æ°æ¯', '0');
INSERT INTO `shop_sell_price` VALUES ('40677', 'é»æç¿ç³é¸å', '0');
INSERT INTO `shop_sell_price` VALUES ('40678', 'çµé­ç³ç¢ç', '0');
INSERT INTO `shop_sell_price` VALUES ('40679', 'æ±¡æµçéç²', '0');
INSERT INTO `shop_sell_price` VALUES ('40680', 'æ±¡æµæç¯·', '0');
INSERT INTO `shop_sell_price` VALUES ('40681', 'æ±¡æµçé¢é´', '0');
INSERT INTO `shop_sell_price` VALUES ('40682', 'æ±¡æµçèç²', '0');
INSERT INTO `shop_sell_price` VALUES ('40683', 'æ±¡æµçå¤´ç', '0');
INSERT INTO `shop_sell_price` VALUES ('40684', 'æ±¡æµçå¼', '0');
INSERT INTO `shop_sell_price` VALUES ('40685', 'æªç£¨åçéå', '0');
INSERT INTO `shop_sell_price` VALUES ('40686', 'å®æåçéå', '0');
INSERT INTO `shop_sell_price` VALUES ('40687', 'å¥¥å§çè¢å­', '0');
INSERT INTO `shop_sell_price` VALUES ('40688', 'æªä¸æ¼çéå', '0');
INSERT INTO `shop_sell_price` VALUES ('40689', 'æªç²¾éçéå', '0');
INSERT INTO `shop_sell_price` VALUES ('40690', 'æªä¿®è¡¥çéå', '0');
INSERT INTO `shop_sell_price` VALUES ('40691', 'åæåçéå', '0');
INSERT INTO `shop_sell_price` VALUES ('40692', 'å®æçèå®å¾', '50');
INSERT INTO `shop_sell_price` VALUES ('40693', 'è¿å¾ééç²', '0');
INSERT INTO `shop_sell_price` VALUES ('40694', 'è¿å¾éæç¯·', '0');
INSERT INTO `shop_sell_price` VALUES ('40695', 'è¿å¾éé¢é´', '0');
INSERT INTO `shop_sell_price` VALUES ('40696', 'è¿å¾éçéç©', '0');
INSERT INTO `shop_sell_price` VALUES ('40697', 'è¿å¾éèç²', '0');
INSERT INTO `shop_sell_price` VALUES ('40698', 'è¿å¾éå¤´ç', '0');
INSERT INTO `shop_sell_price` VALUES ('40699', 'è¿å¾éå¼', '0');
INSERT INTO `shop_sell_price` VALUES ('40700', 'é¶ç¬', '0');
INSERT INTO `shop_sell_price` VALUES ('40701', 'å°èå®å¾', '50');
INSERT INTO `shop_sell_price` VALUES ('40702', 'å°è¢å­', '0');
INSERT INTO `shop_sell_price` VALUES ('40703', 'å¿çµæ¯éç³', '0');
INSERT INTO `shop_sell_price` VALUES ('40704', 'æ­»äº¡å°¾éª¨', '0');
INSERT INTO `shop_sell_price` VALUES ('40705', 'æ­»äº¡å·¨æ§', '0');
INSERT INTO `shop_sell_price` VALUES ('40706', 'æ­»äº¡æé¤', '0');
INSERT INTO `shop_sell_price` VALUES ('40707', 'æ­»äº¡é¦çº§', '0');
INSERT INTO `shop_sell_price` VALUES ('40708', 'æ­»äº¡é¿ç', '0');
INSERT INTO `shop_sell_price` VALUES ('40709', 'æ­»äº¡ä¹å', '0');
INSERT INTO `shop_sell_price` VALUES ('40710', 'æåçè¢å­', '0');
INSERT INTO `shop_sell_price` VALUES ('40711', 'å¡å¾ç©æ¯é¡¹é¾', '0');
INSERT INTO `shop_sell_price` VALUES ('40712', 'å¡ç«æ®çé«çº§è¢å­', '0');
INSERT INTO `shop_sell_price` VALUES ('40713', 'å¡ç«æ®çè¢å­', '0');
INSERT INTO `shop_sell_price` VALUES ('40714', 'èå°¾è¥è´ä¹ç®', '0');
INSERT INTO `shop_sell_price` VALUES ('40715', 'ç®å°æ¯çç¤¼ç©', '0');
INSERT INTO `shop_sell_price` VALUES ('40716', 'ç·ç·çå®ç©', '1240');
INSERT INTO `shop_sell_price` VALUES ('40717', 'å¼ç»çæä¹¦', '50');
INSERT INTO `shop_sell_price` VALUES ('40718', 'è¡ç³ç¢ç', '0');
INSERT INTO `shop_sell_price` VALUES ('40719', 'æ··æ²é¦çº§', '0');
INSERT INTO `shop_sell_price` VALUES ('40720', 'é»æä¹ç¿¼', '0');
INSERT INTO `shop_sell_price` VALUES ('40721', 'å·¨å¤§åçç§å­', '0');
INSERT INTO `shop_sell_price` VALUES ('40722', 'éåç', '990');
INSERT INTO `shop_sell_price` VALUES ('40723', 'é¶åç', '1000');
INSERT INTO `shop_sell_price` VALUES ('40724', 'éåç', '1010');
INSERT INTO `shop_sell_price` VALUES ('40725', 'åçç³æ', '1020');
INSERT INTO `shop_sell_price` VALUES ('40726', 'åçç§å­', '1030');
INSERT INTO `shop_sell_price` VALUES ('40727', 'ç»¿ç­è¢', '1040');
INSERT INTO `shop_sell_price` VALUES ('40728', 'çº¢ç­è¢', '1050');
INSERT INTO `shop_sell_price` VALUES ('40729', 'éè¢å­', '1060');
INSERT INTO `shop_sell_price` VALUES ('40730', 'å£è¯å¡ç', '1070');
INSERT INTO `shop_sell_price` VALUES ('40731', 'æäººèå¡ç', '1080');
INSERT INTO `shop_sell_price` VALUES ('40732', 'ç½è²æäººèå¡ç', '1090');
INSERT INTO `shop_sell_price` VALUES ('40733', 'åèªè´§å¸', '1100');
INSERT INTO `shop_sell_price` VALUES ('40734', 'ä¿¡èµè´§å¸', '1110');
INSERT INTO `shop_sell_price` VALUES ('40735', 'åæ°è´§å¸', '1120');
INSERT INTO `shop_sell_price` VALUES ('40736', 'æºæ§è´§å¸', '1130');
INSERT INTO `shop_sell_price` VALUES ('40737', 'èå®ç®±', '50');
INSERT INTO `shop_sell_price` VALUES ('40738', 'é¶é£å', '0');
INSERT INTO `shop_sell_price` VALUES ('40739', 'é£å', '0');
INSERT INTO `shop_sell_price` VALUES ('40740', 'éé£å', '0');
INSERT INTO `shop_sell_price` VALUES ('40741', 'å¥¥éåé²æ ¹éééª¨ç®­', '0');
INSERT INTO `shop_sell_price` VALUES ('40742', 'å¤ä»£ä¹ç®­', '0');
INSERT INTO `shop_sell_price` VALUES ('40743', 'ç®­', '0');
INSERT INTO `shop_sell_price` VALUES ('40744', 'é¶ç®­', '0');
INSERT INTO `shop_sell_price` VALUES ('40745', 'é»éç®­', '0');
INSERT INTO `shop_sell_price` VALUES ('40746', 'ç±³ç´¢èç®­', '0');
INSERT INTO `shop_sell_price` VALUES ('40747', 'é»è²ç±³ç´¢èç®­', '0');
INSERT INTO `shop_sell_price` VALUES ('40748', 'å¥¥éåé²æ ¹ç®­', '0');
INSERT INTO `shop_sell_price` VALUES ('40749', 'çç¬ä¹ç', '25000');
INSERT INTO `shop_sell_price` VALUES ('40750', 'ç ´ç­ä¹ç', '25000');
INSERT INTO `shop_sell_price` VALUES ('40751', 'æç¬ä¹ç', '25000');
INSERT INTO `shop_sell_price` VALUES ('40752', 'é»éä¹ç', '25000');
INSERT INTO `shop_sell_price` VALUES ('40753', 'çç¬ä¹ç', '25000');
INSERT INTO `shop_sell_price` VALUES ('40754', 'çç¬ä¹ç', '25000');
INSERT INTO `shop_sell_price` VALUES ('40755', 'çç¬ä¹ç', '25000');
INSERT INTO `shop_sell_price` VALUES ('40756', 'ç¥ä¹ç', '25000');
INSERT INTO `shop_sell_price` VALUES ('40757', 'é¢éä¹ç', '45000');
INSERT INTO `shop_sell_price` VALUES ('40758', 'èå©ä¹ç', '45000');
INSERT INTO `shop_sell_price` VALUES ('40759', 'çç¬ä¹ç', '45000');
INSERT INTO `shop_sell_price` VALUES ('40760', 'çç¬ä¹ç', '45000');
INSERT INTO `shop_sell_price` VALUES ('40761', 'å® ç©ç®çç²', '7500');
INSERT INTO `shop_sell_price` VALUES ('40762', 'å® ç©éª·é«çç²', '7500');
INSERT INTO `shop_sell_price` VALUES ('40763', 'å® ç©é¢éçç²', '25000');
INSERT INTO `shop_sell_price` VALUES ('40764', 'å® ç©ç±³ç´¢èçç²', '25000');
INSERT INTO `shop_sell_price` VALUES ('40765', 'å® ç©åå­çç²', '25000');
INSERT INTO `shop_sell_price` VALUES ('40766', 'å® ç©é¾ç²', '25000');
INSERT INTO `shop_sell_price` VALUES ('40778', 'ç®å¸¦', '1');
INSERT INTO `shop_sell_price` VALUES ('40779', 'é¢éå', '1');
INSERT INTO `shop_sell_price` VALUES ('40801', 'æå®ä¼ é(é»æå±±è)', '500');
INSERT INTO `shop_sell_price` VALUES ('40802', 'æå®ä¼ é(å¥å²©ç«æåº)', '500');
INSERT INTO `shop_sell_price` VALUES ('40803', 'æå®ä¼ é(éå­æ£®æ)', '500');
INSERT INTO `shop_sell_price` VALUES ('40804', 'æå®ä¼ é(å·´æå¡æ¯æ æ¯å°)', '500');
INSERT INTO `shop_sell_price` VALUES ('40805', 'æå®ä¼ é(æ³å©ææ æ¯å°)', '500');
INSERT INTO `shop_sell_price` VALUES ('40806', 'æå®ä¼ é(æå¾·æå°æ æ¯å°)', '500');
INSERT INTO `shop_sell_price` VALUES ('40807', 'æå®ä¼ é(æµ·é³æ´ç©´3F)', '200');
INSERT INTO `shop_sell_price` VALUES ('40808', 'æå®ä¼ é(æµ·é³æ´ç©´4F)', '200');
INSERT INTO `shop_sell_price` VALUES ('40809', 'æå®ä¼ é(ç«çª)', '200');
INSERT INTO `shop_sell_price` VALUES ('40810', 'æå®ä¼ é(é¾ä¹è°·å¥å£)', '500');
INSERT INTO `shop_sell_price` VALUES ('40811', 'æå®ä¼ é(æ²æ¼ )', '200');
INSERT INTO `shop_sell_price` VALUES ('40812', 'æå®ä¼ é(æ¬§ç)', '200');
INSERT INTO `shop_sell_price` VALUES ('40813', 'æå®ä¼ é(è¿å¤æåº)', '200');
INSERT INTO `shop_sell_price` VALUES ('40814', 'æå®ä¼ é(é£å°¸å°)', '200');
INSERT INTO `shop_sell_price` VALUES ('40815', 'æå®ä¼ é(é£æ¨å°ç1F)', '200');
INSERT INTO `shop_sell_price` VALUES ('40816', 'æå®ä¼ é(é£æ¨å°ç2F)', '200');
INSERT INTO `shop_sell_price` VALUES ('40817', 'æå®ä¼ é(å·¨èæ´ç©´)', '200');
INSERT INTO `shop_sell_price` VALUES ('40818', 'æå®ä¼ é(å·¨èæ´ç©´)', '200');
INSERT INTO `shop_sell_price` VALUES ('40819', 'æå®ä¼ é(å·¨èå¥³çæ æ¯å°)', '200');
INSERT INTO `shop_sell_price` VALUES ('40820', 'æå®ä¼ é(è±¡çå¡5F)', '200');
INSERT INTO `shop_sell_price` VALUES ('40821', 'æå®ä¼ é(è±¡çå¡6F)', '200');
INSERT INTO `shop_sell_price` VALUES ('40822', 'æå®ä¼ é(è±¡çå¡7F)', '200');
INSERT INTO `shop_sell_price` VALUES ('40823', 'æå®ä¼ é(è±¡çå¡8F)', '200');
INSERT INTO `shop_sell_price` VALUES ('40824', 'æå®ä¼ é(éªå£«æ´ç©´2F)', '200');
INSERT INTO `shop_sell_price` VALUES ('40825', 'æå®ä¼ é(éªå£«æ´ç©´3F)', '200');
INSERT INTO `shop_sell_price` VALUES ('40826', 'æå®ä¼ é(éªå£«æ´ç©´4F)', '200');
INSERT INTO `shop_sell_price` VALUES ('40827', 'æå®ä¼ é(å¥å²©å°ç2F)', '200');
INSERT INTO `shop_sell_price` VALUES ('40828', 'æå®ä¼ é(å¥å²©å°ç3F)', '200');
INSERT INTO `shop_sell_price` VALUES ('40829', 'æå®ä¼ é(å¥å²©å°ç4F)', '200');
INSERT INTO `shop_sell_price` VALUES ('40830', 'æå®ä¼ é(å¤é²ä¸å°ç3F)', '200');
INSERT INTO `shop_sell_price` VALUES ('40831', 'æå®ä¼ é(å¤é²ä¸å°ç4F)', '200');
INSERT INTO `shop_sell_price` VALUES ('40832', 'æå®ä¼ é(å¤é²ä¸å°ç5F)', '200');
INSERT INTO `shop_sell_price` VALUES ('40833', 'æå®ä¼ é(å¤é²ä¸å°ç6F)', '200');
INSERT INTO `shop_sell_price` VALUES ('40834', 'æå®ä¼ é(å¤é²ä¸å°ç7F)', '200');
INSERT INTO `shop_sell_price` VALUES ('40835', 'æå®ä¼ é(é¾ä¹è°·å°ç1F)', '200');
INSERT INTO `shop_sell_price` VALUES ('40836', 'æå®ä¼ é(é¾ä¹è°·å°ç2F)', '200');
INSERT INTO `shop_sell_price` VALUES ('40837', 'æå®ä¼ é(é¾ä¹è°·å°ç3F)', '200');
INSERT INTO `shop_sell_price` VALUES ('40838', 'æå®ä¼ é(é¾ä¹è°·å°ç4F)', '200');
INSERT INTO `shop_sell_price` VALUES ('40839', 'æå®ä¼ é(é¾ä¹è°·å°ç5F)', '200');
INSERT INTO `shop_sell_price` VALUES ('40840', 'æå®ä¼ é(é¾ä¹è°·å°ç6F)', '200');
INSERT INTO `shop_sell_price` VALUES ('40841', 'æå®ä¼ é(å®å¡çæ¯æ æ¯å°)', '200');
INSERT INTO `shop_sell_price` VALUES ('40842', 'æå®ä¼ é(é£æ¨å)', '200');
INSERT INTO `shop_sell_price` VALUES ('40843', 'æå®ä¼ é(é£æ¨æ²æ¼ )', '200');
INSERT INTO `shop_sell_price` VALUES ('40844', 'æå®ä¼ é(å¸é²è¿ªå¡æ´)', '200');
INSERT INTO `shop_sell_price` VALUES ('40845', 'æå®ä¼ é(æ²é»æ´ç©´)', '200');
INSERT INTO `shop_sell_price` VALUES ('40846', 'æå®ä¼ é(ææ¯å¡å·´å¾·å°ä¸æ´ç©´1F)', '200');
INSERT INTO `shop_sell_price` VALUES ('40847', 'æå®ä¼ é(ææ¯å¡å·´å¾·å°ä¸æ´ç©´2F)', '200');
INSERT INTO `shop_sell_price` VALUES ('40848', 'æå®ä¼ é(ææ¯å¡å·´å¾·å°ä¸æ´ç©´3F)', '200');
INSERT INTO `shop_sell_price` VALUES ('40849', 'æå®ä¼ é(å¤ä»£äººç©ºé´1F)', '200');
INSERT INTO `shop_sell_price` VALUES ('40850', 'æå®ä¼ é(å¤ä»£äººç©ºé´2F)', '200');
INSERT INTO `shop_sell_price` VALUES ('40851', 'æå®ä¼ é(å¤ä»£äººç©ºé´4F)', '200');
INSERT INTO `shop_sell_price` VALUES ('40852', 'æå®ä¼ é(å¥¥å§å°ç)', '200');
INSERT INTO `shop_sell_price` VALUES ('40853', 'æå®ä¼ é(å¤§æ´ç©´æµæåå°åº)', '200');
INSERT INTO `shop_sell_price` VALUES ('40854', 'æå®ä¼ é(é­æç¥æ®¿)', '200');
INSERT INTO `shop_sell_price` VALUES ('40855', 'æå®ä¼ é(ç²¾çµå¢ç©´)', '200');
INSERT INTO `shop_sell_price` VALUES ('40856', 'æå®ä¼ é(æµ·è´¼å²)', '200');
INSERT INTO `shop_sell_price` VALUES ('40857', 'æå®ä¼ é(ææ¯å¡å·´å¾·æ­£é¨)', '200');
INSERT INTO `shop_sell_price` VALUES ('40858', 'äººå¤´é©¬ç½å°å°XO', '200');
INSERT INTO `shop_sell_price` VALUES ('40859', 'é­æ³å·è½´(åçº§æ²»ææ¯)', '20');
INSERT INTO `shop_sell_price` VALUES ('40860', 'é­æ³å·è½´(æ¥åæ¯)', '20');
INSERT INTO `shop_sell_price` VALUES ('40861', 'é­æ³å·è½´(ä¿æ¤ç½©)', '20');
INSERT INTO `shop_sell_price` VALUES ('40862', 'é­æ³å·è½´(åç®­)', '20');
INSERT INTO `shop_sell_price` VALUES ('40863', 'é­æ³å·è½´(æå®ä¼ é)', '20');
INSERT INTO `shop_sell_price` VALUES ('40864', 'é­æ³å·è½´(å°ç®­)', '20');
INSERT INTO `shop_sell_price` VALUES ('40865', 'é­æ³å·è½´(é£å)', '20');
INSERT INTO `shop_sell_price` VALUES ('40866', 'é­æ³å·è½´(ç¥å£æ­¦å¨)', '20');
INSERT INTO `shop_sell_price` VALUES ('40867', 'é­æ³å·è½´(è§£æ¯æ¯)', '40');
INSERT INTO `shop_sell_price` VALUES ('40868', 'é­æ³å·è½´(å¯å·ææ )', '40');
INSERT INTO `shop_sell_price` VALUES ('40869', 'é­æ³å·è½´(æ¯å)', '40');
INSERT INTO `shop_sell_price` VALUES ('40870', 'é­æ³å·è½´(æä¼¼é­æ³æ­¦å¨)', '40');
INSERT INTO `shop_sell_price` VALUES ('40871', 'é­æ³å·è½´(æ æéå½¢æ¯)', '40');
INSERT INTO `shop_sell_price` VALUES ('40872', 'é­æ³å·è½´(è´éå¼ºå)', '40');
INSERT INTO `shop_sell_price` VALUES ('40873', 'é­æ³å·è½´(ç«ç®­)', '40');
INSERT INTO `shop_sell_price` VALUES ('40874', 'é­æ³å·è½´(å°ç±ä¹ç)', '40');
INSERT INTO `shop_sell_price` VALUES ('40875', 'é­æ³å·è½´(æåé·çµ)', '80');
INSERT INTO `shop_sell_price` VALUES ('40876', 'é­æ³å·è½´(èµ·æ­»åçæ¯)', '80');
INSERT INTO `shop_sell_price` VALUES ('40877', 'é­æ³å·è½´(ä¸­çº§æ²»ææ¯)', '80');
INSERT INTO `shop_sell_price` VALUES ('40878', 'é­æ³å·è½´(?ç²åæ¯)', '80');
INSERT INTO `shop_sell_price` VALUES ('40879', 'é­æ³å·è½´(é ç²æ¤æ)', '80');
INSERT INTO `shop_sell_price` VALUES ('40880', 'é­æ³å·è½´(å¯å°æ°æ¯)', '80');
INSERT INTO `shop_sell_price` VALUES ('40881', 'é­æ³å·è½´(è½éææµ)', '80');
INSERT INTO `shop_sell_price` VALUES ('40883', 'é­æ³å·è½´(çç§çç«ç)', '160');
INSERT INTO `shop_sell_price` VALUES ('40884', 'é­æ³å·è½´(éçæ°èæ¯)', '160');
INSERT INTO `shop_sell_price` VALUES ('40885', 'é­æ³å·è½´(åç©æ¯)', '160');
INSERT INTO `shop_sell_price` VALUES ('40886', 'é­æ³å·è½´(å¸è¡é¬¼ä¹å»)', '160');
INSERT INTO `shop_sell_price` VALUES ('40887', 'é­æ³å·è½´(ç¼éæ¯)', '160');
INSERT INTO `shop_sell_price` VALUES ('40888', 'é­æ³å·è½´(å²©ç¢)', '160');
INSERT INTO `shop_sell_price` VALUES ('40889', 'é­æ³å·è½´(é­æ³å±é)', '160');
INSERT INTO `shop_sell_price` VALUES ('40890', 'é­æ³å·è½´(å¥æ³æ¯)', '160');
INSERT INTO `shop_sell_price` VALUES ('40891', 'é­æ³å·è½´(æ¨ä¹ä¼çè¯å)', '320');
INSERT INTO `shop_sell_price` VALUES ('40892', 'é­æ³å·è½´(æéè½é·)', '320');
INSERT INTO `shop_sell_price` VALUES ('40893', 'é­æ³å·è½´(é«çº§æ²»ææ¯)', '320');
INSERT INTO `shop_sell_price` VALUES ('40894', 'é­æ³å·è½´(è¿·é­æ¯)', '320');
INSERT INTO `shop_sell_price` VALUES ('40895', 'é­æ³å·è½´(å£æ´ä¹å)', '3000');
INSERT INTO `shop_sell_price` VALUES ('40896', 'é­æ³å·è½´(å°é¥)', '3000');
INSERT INTO `shop_sell_price` VALUES ('40897', 'é­æ³å·è½´(é­åå¤ºå)', '320');
INSERT INTO `shop_sell_price` VALUES ('40898', 'é­æ³å·è½´(é»?ä¹å½±)', '320');
INSERT INTO `shop_sell_price` VALUES ('40899', 'é¢éåç³', '0');
INSERT INTO `shop_sell_price` VALUES ('40901', 'ç»å©ææ(é¶)', '2500');
INSERT INTO `shop_sell_price` VALUES ('40902', 'ç»å©ææ(é)', '5000');
INSERT INTO `shop_sell_price` VALUES ('40903', 'ç»å©ææ(èå®ç³)', '2500');
INSERT INTO `shop_sell_price` VALUES ('40904', 'ç»å©ææ(ç»¿å®ç³)', '2500');
INSERT INTO `shop_sell_price` VALUES ('40905', 'ç»å©ææ(çº¢å®ç³)', '2500');
INSERT INTO `shop_sell_price` VALUES ('40906', 'ç»å©ææ(é»ç³)', '2500');
INSERT INTO `shop_sell_price` VALUES ('40907', 'è¥¿çææ', '0');
INSERT INTO `shop_sell_price` VALUES ('40908', 'æ¬§æææ', '0');
INSERT INTO `shop_sell_price` VALUES ('40929', 'åé¶ç¥ç§è¯æ°´', '0');
INSERT INTO `shop_sell_price` VALUES ('40930', 'ç¤è', '0');
INSERT INTO `shop_sell_price` VALUES ('40931', 'ç²¾å·¥çèå®ç³', '0');
INSERT INTO `shop_sell_price` VALUES ('40932', 'ç²¾å·¥çåè´¨èå®ç³', '0');
INSERT INTO `shop_sell_price` VALUES ('40933', 'ç²¾å·¥çé«åè´¨èå®ç³', '0');
INSERT INTO `shop_sell_price` VALUES ('40934', 'ç²¾å·¥çæåèå®ç³', '0');
INSERT INTO `shop_sell_price` VALUES ('40935', 'ç²¾å·¥çç»¿å®ç³', '0');
INSERT INTO `shop_sell_price` VALUES ('40936', 'ç²¾å·¥çåè´¨ç»¿å®ç³', '0');
INSERT INTO `shop_sell_price` VALUES ('40937', 'ç²¾å·¥çé«åè´¨ç»¿å®ç³', '0');
INSERT INTO `shop_sell_price` VALUES ('40938', 'ç²¾å·¥çæåç»¿å®ç³', '0');
INSERT INTO `shop_sell_price` VALUES ('40939', 'ç²¾å·¥ççº¢å®ç³', '0');
INSERT INTO `shop_sell_price` VALUES ('40940', 'ç²¾å·¥çåè´¨çº¢å®ç³', '0');
INSERT INTO `shop_sell_price` VALUES ('40941', 'ç²¾å·¥çé«åè´¨çº¢å®ç³', '0');
INSERT INTO `shop_sell_price` VALUES ('40942', 'ç²¾å·¥çæåçº¢å®ç³', '0');
INSERT INTO `shop_sell_price` VALUES ('40943', 'ç²¾å·¥çåä¹é»', '0');
INSERT INTO `shop_sell_price` VALUES ('40944', 'ç²¾å·¥çåè´¨åä¹é»', '0');
INSERT INTO `shop_sell_price` VALUES ('40945', 'ç²¾å·¥çé«åè´¨åä¹é»', '0');
INSERT INTO `shop_sell_price` VALUES ('40946', 'ç²¾å·¥çæååä¹é»', '0');
INSERT INTO `shop_sell_price` VALUES ('40947', 'ç²¾å·¥çæ°´ä¹é»', '0');
INSERT INTO `shop_sell_price` VALUES ('40948', 'ç²¾å·¥çåè´¨æ°´ä¹é»', '0');
INSERT INTO `shop_sell_price` VALUES ('40949', 'ç²¾å·¥çé«åè´¨æ°´ä¹é»', '0');
INSERT INTO `shop_sell_price` VALUES ('40950', 'ç²¾å·¥çæåæ°´ä¹é»', '0');
INSERT INTO `shop_sell_price` VALUES ('40951', 'ç²¾å·¥çç«ä¹é»', '0');
INSERT INTO `shop_sell_price` VALUES ('40952', 'ç²¾å·¥çåè´¨ç«ä¹é»', '0');
INSERT INTO `shop_sell_price` VALUES ('40953', 'ç²¾å·¥çé«åè´¨ç«ä¹é»', '0');
INSERT INTO `shop_sell_price` VALUES ('40954', 'ç²¾å·¥çæåç«ä¹é»', '0');
INSERT INTO `shop_sell_price` VALUES ('40955', 'ç²¾å·¥çé£ä¹é»', '0');
INSERT INTO `shop_sell_price` VALUES ('40956', 'ç²¾å·¥çåè´¨é£ä¹é»', '0');
INSERT INTO `shop_sell_price` VALUES ('40957', 'ç²¾å·¥çé«åè´¨é£ä¹é»', '0');
INSERT INTO `shop_sell_price` VALUES ('40958', 'ç²¾å·¥çæåé£ä¹é»', '0');
INSERT INTO `shop_sell_price` VALUES ('40959', 'å¥æ³åçå¾½å°', '0');
INSERT INTO `shop_sell_price` VALUES ('40960', 'æ³ä»¤åçå¾½å°', '0');
INSERT INTO `shop_sell_price` VALUES ('40961', 'é­å½åçå¾½å°', '0');
INSERT INTO `shop_sell_price` VALUES ('40962', 'ææåçå¾½å°', '0');
INSERT INTO `shop_sell_price` VALUES ('40964', 'é»é­æ³ç²', '0');
INSERT INTO `shop_sell_price` VALUES ('40965', 'ææ¯å¡å·´å¾·å¶ä½æ­¦å¨ç§ç¬', '0');
INSERT INTO `shop_sell_price` VALUES ('40966', 'çï¼å¥çå¶ä½é²å·ç§ç¬', '0');
INSERT INTO `shop_sell_price` VALUES ('40967', 'å£å°éç©', '0');
INSERT INTO `shop_sell_price` VALUES ('40968', 'ä¿®è¡èç»å¸', '0');
INSERT INTO `shop_sell_price` VALUES ('40969', 'é»æå¦ç²¾ççµé­æ°´æ¶', '0');
INSERT INTO `shop_sell_price` VALUES ('40970', 'å®å æ¯çå°¾å·´', '0');
INSERT INTO `shop_sell_price` VALUES ('40971', 'å®å æ¯ä¹ç', '0');
INSERT INTO `shop_sell_price` VALUES ('40972', 'å·´è¨æ¯çæ°æ¯', '0');
INSERT INTO `shop_sell_price` VALUES ('40973', 'å·´è¨æ¯çç¿è', '0');
INSERT INTO `shop_sell_price` VALUES ('40974', 'çé«çè¡', '0');
INSERT INTO `shop_sell_price` VALUES ('40975', 'çé«çé³', '0');
INSERT INTO `shop_sell_price` VALUES ('40976', 'æ²', '0');
INSERT INTO `shop_sell_price` VALUES ('40977', 'æè¡çæ²', '0');
INSERT INTO `shop_sell_price` VALUES ('40978', 'å°ä¹å®æ¤èçå°¾å·´', '0');
INSERT INTO `shop_sell_price` VALUES ('40979', 'æ°´ä¹å®æ¤èçå°¾å·´', '0');
INSERT INTO `shop_sell_price` VALUES ('40980', 'ç«ä¹å®æ¤èçå°¾å·´', '0');
INSERT INTO `shop_sell_price` VALUES ('40981', 'é£ä¹å®æ¤èçå°¾å·´', '0');
INSERT INTO `shop_sell_price` VALUES ('40982', 'å°ä¹å®æ¤èçç®', '0');
INSERT INTO `shop_sell_price` VALUES ('40983', 'æ°´ä¹å®æ¤èçç®', '0');
INSERT INTO `shop_sell_price` VALUES ('40984', 'ç«ä¹å®æ¤èçç®', '0');
INSERT INTO `shop_sell_price` VALUES ('40985', 'é£ä¹å®æ¤èçç®', '0');
INSERT INTO `shop_sell_price` VALUES ('40986', 'å®æ¤èä¹ç', '0');
INSERT INTO `shop_sell_price` VALUES ('40987', 'åè¯åçé»è²è³ç¯', '0');
INSERT INTO `shop_sell_price` VALUES ('40988', 'åè¯åçé»è²è³ç¯', '0');
INSERT INTO `shop_sell_price` VALUES ('40989', 'åè¯åçé»è²è³ç¯', '0');
INSERT INTO `shop_sell_price` VALUES ('40990', 'çé­çç¿è', '0');
INSERT INTO `shop_sell_price` VALUES ('40991', 'çé­åæå', '0');
INSERT INTO `shop_sell_price` VALUES ('40992', 'çé­çå¤´', '0');
INSERT INTO `shop_sell_price` VALUES ('40993', 'çé­çè§', '0');
INSERT INTO `shop_sell_price` VALUES ('40994', 'çé­ä¹è', '0');
INSERT INTO `shop_sell_price` VALUES ('40995', 'çé­ä¹æ', '0');
INSERT INTO `shop_sell_price` VALUES ('40996', 'çé­çå¿è', '0');
INSERT INTO `shop_sell_price` VALUES ('40997', 'çé­ä¹ç', '0');
INSERT INTO `shop_sell_price` VALUES ('40998', 'çé­ä¹èº', '0');
INSERT INTO `shop_sell_price` VALUES ('40999', 'é»æå¦ç²¾å£«åµå¾½ç« ', '0');
INSERT INTO `shop_sell_price` VALUES ('41000', 'é»æå¦ç²¾å°åå¾½ç« ', '0');
INSERT INTO `shop_sell_price` VALUES ('41001', 'æ¥å¿é', '0');
INSERT INTO `shop_sell_price` VALUES ('41002', 'ç¿ç©è¢å­', '0');
INSERT INTO `shop_sell_price` VALUES ('41003', 'ç½ä¼çè¢å­', '0');
INSERT INTO `shop_sell_price` VALUES ('41004', 'æå¸ç½çè¢å­', '0');
INSERT INTO `shop_sell_price` VALUES ('41005', 'å¤æ´»ä¸æ°¸çä¹èªçº¦ä¹¦', '0');
INSERT INTO `shop_sell_price` VALUES ('41006', 'æä¼¯åçè¢å­', '0');
INSERT INTO `shop_sell_price` VALUES ('41007', 'ä¼èä¸çå½ä»¤ä¹¦ï¼çµé­ä¹å®æ¯', '0');
INSERT INTO `shop_sell_price` VALUES ('41008', 'ä¼èä¸çè¢å­', '0');
INSERT INTO `shop_sell_price` VALUES ('41009', 'ä¼èä¸çå½ä»¤ä¹¦ï¼åçä¹æå¿', '0');
INSERT INTO `shop_sell_price` VALUES ('41010', 'ä¼èä¸çæ¨èå½', '0');
INSERT INTO `shop_sell_price` VALUES ('41011', 'å°å°çåå²ä¹¦ç¬¬1é¡µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41012', 'å°å°çåå²ä¹¦ç¬¬2é¡µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41013', 'å°å°çåå²ä¹¦ç¬¬3é¡µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41014', 'å°å°çåå²ä¹¦ç¬¬4é¡µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41015', 'å°å°çåå²ä¹¦ç¬¬5é¡µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41016', 'å°å°çåå²ä¹¦ç¬¬6é¡µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41017', 'å°å°çåå²ä¹¦ç¬¬7é¡µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41018', 'å°å°çåå²ä¹¦ç¬¬8é¡µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41019', 'ææ¯å¡å·´å¾·åå²ä¹¦ç¬¬1é¡µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41020', 'ææ¯å¡å·´å¾·åå²ä¹¦ç¬¬2é¡µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41021', 'ææ¯å¡å·´å¾·åå²ä¹¦ç¬¬3é¡µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41022', 'ææ¯å¡å·´å¾·åå²ä¹¦ç¬¬4é¡µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41023', 'ææ¯å¡å·´å¾·åå²ä¹¦ç¬¬5é¡µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41024', 'ææ¯å¡å·´å¾·åå²ä¹¦ç¬¬6é¡µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41025', 'ææ¯å¡å·´å¾·åå²ä¹¦ç¬¬7é¡µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41026', 'ææ¯å¡å·´å¾·åå²ä¹¦ç¬¬8é¡µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41027', 'å®æ´çææ¯å¡å·´å¾·åå²ä¹¦', '0');
INSERT INTO `shop_sell_price` VALUES ('41028', 'æ­»äº¡éªå£«ä¹ä¹¦', '0');
INSERT INTO `shop_sell_price` VALUES ('41029', 'å¬å¤çä¹æ ¸', '0');
INSERT INTO `shop_sell_price` VALUES ('41030', 'å¬å¤çç¢ç', '0');
INSERT INTO `shop_sell_price` VALUES ('41031', 'ä¸é¶æ®µå¬å¤ç', '0');
INSERT INTO `shop_sell_price` VALUES ('41032', 'äºé¶æ®µå¬å¤ç', '0');
INSERT INTO `shop_sell_price` VALUES ('41033', 'ä¸é¶æ®µå¬å¤ç', '0');
INSERT INTO `shop_sell_price` VALUES ('41034', 'åé¶æ®µå¬å¤ç', '0');
INSERT INTO `shop_sell_price` VALUES ('41035', 'å®æ´çå¬å¤ç', '0');
INSERT INTO `shop_sell_price` VALUES ('41036', 'è¶æ°´', '0');
INSERT INTO `shop_sell_price` VALUES ('41037', 'ä¸å®æ´çèªæµ·æ¥å¿', '0');
INSERT INTO `shop_sell_price` VALUES ('41038', 'èªæµ·æ¥å¿ç¬¬1é¡µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41039', 'èªæµ·æ¥å¿ç¬¬2é¡µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41040', 'èªæµ·æ¥å¿ç¬¬3é¡µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41041', 'èªæµ·æ¥å¿ç¬¬4é¡µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41042', 'èªæµ·æ¥å¿ç¬¬5é¡µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41043', 'èªæµ·æ¥å¿ç¬¬6é¡µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41044', 'èªæµ·æ¥å¿ç¬¬7é¡µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41045', 'èªæµ·æ¥å¿ç¬¬8é¡µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41046', 'èªæµ·æ¥å¿ç¬¬9é¡µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41047', 'èªæµ·æ¥å¿ç¬¬10é¡µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41048', 'æ¶çè¶æ°´çèªæµ·æ¥å¿ç¬¬1é¡µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41049', 'æ¶çè¶æ°´çèªæµ·æ¥å¿ç¬¬2é¡µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41050', 'æ¶çè¶æ°´çèªæµ·æ¥å¿ç¬¬3é¡µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41051', 'æ¶çè¶æ°´çèªæµ·æ¥å¿ç¬¬4é¡µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41052', 'æ¶çè¶æ°´çèªæµ·æ¥å¿ç¬¬5é¡µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41053', 'æ¶çè¶æ°´çèªæµ·æ¥å¿ç¬¬6é¡µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41054', 'æ¶çè¶æ°´çèªæµ·æ¥å¿ç¬¬7é¡µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41055', 'æ¶çè¶æ°´çèªæµ·æ¥å¿ç¬¬8é¡µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41056', 'æ¶çè¶æ°´çèªæµ·æ¥å¿ç¬¬9é¡µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41057', 'æ¶çè¶æ°´çèªæµ·æ¥å¿ç¬¬10é¡µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41058', 'å®æ´çèªæµ·æ¥å¿', '0');
INSERT INTO `shop_sell_price` VALUES ('41059', 'èªæµ·å£«çè¢å­', '0');
INSERT INTO `shop_sell_price` VALUES ('41060', 'è¯ºæ¼é¿åå·´çä¿¡', '0');
INSERT INTO `shop_sell_price` VALUES ('41061', 'å¦ç²¾è°æ¥ä¹¦ï¼å¡éº¦é½è¾¾çæ', '0');
INSERT INTO `shop_sell_price` VALUES ('41062', 'äººç±»è°æ¥ä¹¦ï¼å·´åºæ©é£é²å ', '0');
INSERT INTO `shop_sell_price` VALUES ('41063', 'ç²¾çµè°æ¥ä¹¦ï¼å¯æ®é½è¾¾çæ', '0');
INSERT INTO `shop_sell_price` VALUES ('41064', 'å¦é­è°æ¥ä¹¦ï¼å¼§é¬çé£é²å ', '0');
INSERT INTO `shop_sell_price` VALUES ('41065', 'æ­»äº¡ä¹æ è°æ¥ä¹¦ï¼è¯ºäºé¿åå·´', '0');
INSERT INTO `shop_sell_price` VALUES ('41066', 'æ±¡æµçæ ¹', '0');
INSERT INTO `shop_sell_price` VALUES ('41067', 'æ±¡æµçæ æ', '0');
INSERT INTO `shop_sell_price` VALUES ('41068', 'æ±¡æµçç®', '0');
INSERT INTO `shop_sell_price` VALUES ('41069', 'æ±¡æµçé¬æ¯', '0');
INSERT INTO `shop_sell_price` VALUES ('41070', 'æ±¡æµçç²¾çµç¾½ç¿¼', '0');
INSERT INTO `shop_sell_price` VALUES ('41071', 'é¶ç', '0');
INSERT INTO `shop_sell_price` VALUES ('41072', 'é¶çå°', '0');
INSERT INTO `shop_sell_price` VALUES ('41073', 'å¼ºçé¥å', '0');
INSERT INTO `shop_sell_price` VALUES ('41074', 'å¼ºççè¢å­', '0');
INSERT INTO `shop_sell_price` VALUES ('41075', 'æ±¡æµçå¤´å', '0');
INSERT INTO `shop_sell_price` VALUES ('41076', 'åæ ¸æ¶', '0');
INSERT INTO `shop_sell_price` VALUES ('41077', 'æ°´æ ¸æ¶', '0');
INSERT INTO `shop_sell_price` VALUES ('41078', 'ç«æ ¸æ¶', '0');
INSERT INTO `shop_sell_price` VALUES ('41079', 'é£æ ¸æ¶', '0');
INSERT INTO `shop_sell_price` VALUES ('41080', 'ç²¾çµæ ¸æ¶', '0');
INSERT INTO `shop_sell_price` VALUES ('41081', 'å¦é­å°ç', '0');
INSERT INTO `shop_sell_price` VALUES ('41082', 'å¦é­å°çé¡¹é¾', '0');
INSERT INTO `shop_sell_price` VALUES ('41083', 'åæ¯ç²', '0');
INSERT INTO `shop_sell_price` VALUES ('41084', 'å¹»è§ä¹ç²', '0');
INSERT INTO `shop_sell_price` VALUES ('41085', 'é¢è¨å®¶çç ', '0');
INSERT INTO `shop_sell_price` VALUES ('41086', 'æ ç²¾çæ ¹', '0');
INSERT INTO `shop_sell_price` VALUES ('41087', 'æ ç²¾çæ ç®', '0');
INSERT INTO `shop_sell_price` VALUES ('41088', 'æ ç²¾çå¶å­', '0');
INSERT INTO `shop_sell_price` VALUES ('41089', 'æ ç²¾çæ æ', '0');
INSERT INTO `shop_sell_price` VALUES ('41090', 'é£é²å å¾è¾', '0');
INSERT INTO `shop_sell_price` VALUES ('41091', 'é½è¾¾çæå¾è¾', '0');
INSERT INTO `shop_sell_price` VALUES ('41092', 'é¿åå·´å¾è¾', '0');
INSERT INTO `shop_sell_price` VALUES ('41093', 'æ¢¦å¹»ççå¨å¨', '0');
INSERT INTO `shop_sell_price` VALUES ('41094', 'è¯±æçé¦æ°´', '0');
INSERT INTO `shop_sell_price` VALUES ('41095', 'æ¼äº®çæ´è£', '0');
INSERT INTO `shop_sell_price` VALUES ('41096', 'åä¸½çææ', '0');
INSERT INTO `shop_sell_price` VALUES ('41097', 'ç±çä¼çå¿', '0');
INSERT INTO `shop_sell_price` VALUES ('41098', 'è±éä¼ è®°', '0');
INSERT INTO `shop_sell_price` VALUES ('41099', 'æ¶é«¦çå¸½å­', '0');
INSERT INTO `shop_sell_price` VALUES ('41100', 'é«çº§çº¢é', '0');
INSERT INTO `shop_sell_price` VALUES ('41101', 'ç¥ç§çé¥å', '0');
INSERT INTO `shop_sell_price` VALUES ('41102', 'ä¼æ£®ä¹å¿', '0');
INSERT INTO `shop_sell_price` VALUES ('41103', 'ç³å¤´å', '250');
INSERT INTO `shop_sell_price` VALUES ('41104', 'éç¿ç³', '500');
INSERT INTO `shop_sell_price` VALUES ('41105', 'ç«å±±å²©', '500');
INSERT INTO `shop_sell_price` VALUES ('41106', 'çä¾å¥´çå°¾å·´æ¯', '250');
INSERT INTO `shop_sell_price` VALUES ('41107', 'çç²ç', '250');
INSERT INTO `shop_sell_price` VALUES ('41108', 'é»ç³åç³', '250');
INSERT INTO `shop_sell_price` VALUES ('41109', 'çä¾å¥´å¤é¨çå°¾å·´æ¯', '250');
INSERT INTO `shop_sell_price` VALUES ('41110', 'éç©è¢', '0');
INSERT INTO `shop_sell_price` VALUES ('41111', 'ç ´æ§çéç©è¢', '0');
INSERT INTO `shop_sell_price` VALUES ('41112', 'æ§éç©è¢', '0');
INSERT INTO `shop_sell_price` VALUES ('41113', 'è¤ªè²ææ', '0');
INSERT INTO `shop_sell_price` VALUES ('41114', 'æè¡çæå¸', '0');
INSERT INTO `shop_sell_price` VALUES ('41115', 'æè¡çæä»¶', '0');
INSERT INTO `shop_sell_price` VALUES ('41116', 'è¤ªè²é¡¹é¾', '0');
INSERT INTO `shop_sell_price` VALUES ('41117', 'ç ´æ§çé±å', '0');
INSERT INTO `shop_sell_price` VALUES ('41118', 'æè¡çåé¦', '0');
INSERT INTO `shop_sell_price` VALUES ('41119', 'éå¤±çé¥å', '0');
INSERT INTO `shop_sell_price` VALUES ('41120', 'çéçé­æ', '0');
INSERT INTO `shop_sell_price` VALUES ('41121', 'ç«ç°ä¹å½±çå¥çº¦ä¹¦', '0');
INSERT INTO `shop_sell_price` VALUES ('41122', 'ç«ç°ä¹å½±çå¥çº¦', '0');
INSERT INTO `shop_sell_price` VALUES ('41123', 'ç«ç°ä¹å½±çå è½ç²', '0');
INSERT INTO `shop_sell_price` VALUES ('41124', 'ç«ç°ä¹å½±çæ åç²', '0');
INSERT INTO `shop_sell_price` VALUES ('41125', 'ç«ç°ä¹å½±çæ§çç²', '0');
INSERT INTO `shop_sell_price` VALUES ('41126', 'çé­çå è½äºæ°´', '0');
INSERT INTO `shop_sell_price` VALUES ('41127', 'çé­çæ åäºæ°´', '0');
INSERT INTO `shop_sell_price` VALUES ('41128', 'çé­çæ§çäºæ°´', '0');
INSERT INTO `shop_sell_price` VALUES ('41129', 'çé­çäºæ°´', '0');
INSERT INTO `shop_sell_price` VALUES ('41130', 'çé­çå¥çº¦ä¹¦', '0');
INSERT INTO `shop_sell_price` VALUES ('41131', 'çé­å¥çå¥çº¦', '0');
INSERT INTO `shop_sell_price` VALUES ('41132', 'çé­çå è½ç²', '0');
INSERT INTO `shop_sell_price` VALUES ('41133', 'çé­çæ åç²', '0');
INSERT INTO `shop_sell_price` VALUES ('41134', 'çé­çæ§çç²', '0');
INSERT INTO `shop_sell_price` VALUES ('41135', 'ç«ç°ä¹å½±çå è½äºæ°´', '0');
INSERT INTO `shop_sell_price` VALUES ('41136', 'ç«ç°ä¹å½±çæ åäºæ°´', '0');
INSERT INTO `shop_sell_price` VALUES ('41137', 'ç«ç°ä¹å½±çæ§çäºæ°´', '0');
INSERT INTO `shop_sell_price` VALUES ('41138', 'ç«ç°ä¹å½±çäºæ°´', '0');
INSERT INTO `shop_sell_price` VALUES ('41139', 'ä¸èµ·ç¼çå¤èé¡¹é¾', '0');
INSERT INTO `shop_sell_price` VALUES ('41140', 'å¤åçå¤èé¡¹é¾', '0');
INSERT INTO `shop_sell_price` VALUES ('41141', 'ç¥ç§çä½åè¯æ°´', '0');
INSERT INTO `shop_sell_price` VALUES ('41142', 'ç¥ç§çé­åè¯æ°´', '0');
INSERT INTO `shop_sell_price` VALUES ('41143', 'æµ·è´¼éª·é«é¦é¢åèº«è¯æ°´', '0');
INSERT INTO `shop_sell_price` VALUES ('41144', 'æµ·è´¼éª·é«å£«åµåèº«è¯æ°´', '0');
INSERT INTO `shop_sell_price` VALUES ('41145', 'æµ·è´¼éª·é«åæåèº«è¯æ°´', '0');
INSERT INTO `shop_sell_price` VALUES ('41146', '$10001', '0');
INSERT INTO `shop_sell_price` VALUES ('41147', 'ææ¯ä¹¦(ååºé²æ¤)', '0');
INSERT INTO `shop_sell_price` VALUES ('41148', 'ææ¯ä¹¦(åå»å±é)', '0');
INSERT INTO `shop_sell_price` VALUES ('41149', 'ç²¾çµæ°´æ¶(çç°ä¹é­)', '0');
INSERT INTO `shop_sell_price` VALUES ('41150', 'ç²¾çµæ°´æ¶(è½éæ¿å)', '0');
INSERT INTO `shop_sell_price` VALUES ('41151', 'ç²¾çµæ°´æ¶(æ°´ä¹é²æ¤)', '0');
INSERT INTO `shop_sell_price` VALUES ('41152', 'ç²¾çµæ°´æ¶(æ±¡æµä¹æ°´)', '0');
INSERT INTO `shop_sell_price` VALUES ('41153', 'ç²¾çµæ°´æ¶(ç²¾åå°å»)', '0');
INSERT INTO `shop_sell_price` VALUES ('41154', 'æä¹é³', '0');
INSERT INTO `shop_sell_price` VALUES ('41155', 'ç«ä¹é³', '0');
INSERT INTO `shop_sell_price` VALUES ('41156', 'åä¹é³', '0');
INSERT INTO `shop_sell_price` VALUES ('41157', 'æ¨ä¹é³', '0');
INSERT INTO `shop_sell_price` VALUES ('41158', 'çéçæ°´æ¶ç', '0');
INSERT INTO `shop_sell_price` VALUES ('41159', 'ç¥ç§çç¾½æ¯', '0');
INSERT INTO `shop_sell_price` VALUES ('41160', 'å® ç©å¬å¤ç¬', '0');
INSERT INTO `shop_sell_price` VALUES ('41161', 'é»è²è³ç¯', '0');
INSERT INTO `shop_sell_price` VALUES ('41162', 'é»è²è³ç¯', '0');
INSERT INTO `shop_sell_price` VALUES ('41163', 'é»è²è³ç¯', '0');
INSERT INTO `shop_sell_price` VALUES ('41164', 'ç¥ç§çé»è²è³ç¯', '0');
INSERT INTO `shop_sell_price` VALUES ('41165', 'ç¥ç§çé»è²è³ç¯', '0');
INSERT INTO `shop_sell_price` VALUES ('41166', 'ç¥ç§çé»è²è³ç¯', '0');
INSERT INTO `shop_sell_price` VALUES ('41167', 'æå£«è³ç¯', '0');
INSERT INTO `shop_sell_price` VALUES ('41168', 'ç¥ç§çæå£«è³ç¯', '0');
INSERT INTO `shop_sell_price` VALUES ('41169', 'ç°è²æå£«è³ç¯', '0');
INSERT INTO `shop_sell_price` VALUES ('41170', 'ç¥ç§çç°è²æå£«è³ç¯', '0');
INSERT INTO `shop_sell_price` VALUES ('41171', 'ç½è²æå£«è³ç¯', '0');
INSERT INTO `shop_sell_price` VALUES ('41172', 'ç¥ç§çç½è²æå£«è³ç¯', '0');
INSERT INTO `shop_sell_price` VALUES ('41173', 'éªå£«è³ç¯', '0');
INSERT INTO `shop_sell_price` VALUES ('41174', 'ç¥ç§çéªå£«è³ç¯', '0');
INSERT INTO `shop_sell_price` VALUES ('41175', 'ç°è²éªå£«è³ç¯', '0');
INSERT INTO `shop_sell_price` VALUES ('41176', 'ç¥ç§çç°è²éªå£«è³ç¯', '0');
INSERT INTO `shop_sell_price` VALUES ('41177', 'ç½è²éªå£«è³ç¯', '0');
INSERT INTO `shop_sell_price` VALUES ('41178', 'ç¥ç§çç½è²éªå£«è³ç¯', '0');
INSERT INTO `shop_sell_price` VALUES ('41179', 'æ³å¸è³ç¯', '0');
INSERT INTO `shop_sell_price` VALUES ('41180', 'ç¥ç§çæ³å¸è³ç¯', '0');
INSERT INTO `shop_sell_price` VALUES ('41181', 'ç°è²æ³å¸è³ç¯', '0');
INSERT INTO `shop_sell_price` VALUES ('41182', 'ç¥ç§çç°è²æ³å¸è³ç¯', '0');
INSERT INTO `shop_sell_price` VALUES ('41183', 'ç½è²æ³å¸è³ç¯', '0');
INSERT INTO `shop_sell_price` VALUES ('41184', 'ç¥ç§çç½è²æ³å¸è³ç¯', '0');
INSERT INTO `shop_sell_price` VALUES ('41185', 'ç²¾è´çé£çµææ(ç·çµ)', '0');
INSERT INTO `shop_sell_price` VALUES ('41186', 'ç²¾è´çé£çµææ(å¬çµ)', '0');
INSERT INTO `shop_sell_price` VALUES ('41187', 'ç²¾è´çé£çµææ(ä¼¯çµ)', '0');
INSERT INTO `shop_sell_price` VALUES ('41188', 'ç²¾è´çé£çµææ(è±é)', '0');
INSERT INTO `shop_sell_price` VALUES ('41189', 'ç²¾è´çå°çµææ(ç·çµ)', '0');
INSERT INTO `shop_sell_price` VALUES ('41190', 'ç²¾è´çå°çµææ(å¬çµ)', '0');
INSERT INTO `shop_sell_price` VALUES ('41191', 'ç²¾è´çå°çµææ(ä¼¯çµ)', '0');
INSERT INTO `shop_sell_price` VALUES ('41192', 'ç²¾è´çå°çµææ(è±é)', '0');
INSERT INTO `shop_sell_price` VALUES ('41193', 'ç²¾è´çç«çµææ(ç·çµ)', '0');
INSERT INTO `shop_sell_price` VALUES ('41194', 'ç²¾è´çç«çµææ(å¬çµ)', '0');
INSERT INTO `shop_sell_price` VALUES ('41195', 'ç²¾è´çç«çµææ(ä¼¯çµ)', '0');
INSERT INTO `shop_sell_price` VALUES ('41196', 'ç²¾è´çç«çµææ(è±é)', '0');
INSERT INTO `shop_sell_price` VALUES ('41197', 'ç²¾è´çæ°´çµææ(ç·çµ)', '0');
INSERT INTO `shop_sell_price` VALUES ('41198', 'ç²¾è´çæ°´çµææ(å¬çµ)', '0');
INSERT INTO `shop_sell_price` VALUES ('41199', 'ç²¾è´çæ°´çµææ(ä¼¯çµ)', '0');
INSERT INTO `shop_sell_price` VALUES ('41200', 'ç²¾è´çæ°´çµææ(è±é)', '0');
INSERT INTO `shop_sell_price` VALUES ('41201', 'éªå£«ä¹é­', '0');
INSERT INTO `shop_sell_price` VALUES ('41202', 'å¦ç²¾ä¹é­', '0');
INSERT INTO `shop_sell_price` VALUES ('41203', 'çæä¹é­', '0');
INSERT INTO `shop_sell_price` VALUES ('41204', 'é»å¦ä¹é­', '0');
INSERT INTO `shop_sell_price` VALUES ('41205', 'æ³å¸ä¹é­', '0');
INSERT INTO `shop_sell_price` VALUES ('41206', 'å°äºååçæ­¦å¨', '250');
INSERT INTO `shop_sell_price` VALUES ('41207', 'è¹åéä½', '0');
INSERT INTO `shop_sell_price` VALUES ('41208', 'å¾®å¼±ççµé­', '0');
INSERT INTO `shop_sell_price` VALUES ('41209', '$10002', '0');
INSERT INTO `shop_sell_price` VALUES ('41210', '$10003', '0');
INSERT INTO `shop_sell_price` VALUES ('41211', 'é¦è', '0');
INSERT INTO `shop_sell_price` VALUES ('41212', '$10005', '0');
INSERT INTO `shop_sell_price` VALUES ('41213', '$10006', '0');
INSERT INTO `shop_sell_price` VALUES ('41214', '$10012', '0');
INSERT INTO `shop_sell_price` VALUES ('41215', '$10010', '0');
INSERT INTO `shop_sell_price` VALUES ('41216', '$10011', '0');
INSERT INTO `shop_sell_price` VALUES ('41217', '$10028', '0');
INSERT INTO `shop_sell_price` VALUES ('41218', '$10029', '0');
INSERT INTO `shop_sell_price` VALUES ('41219', '$10030', '0');
INSERT INTO `shop_sell_price` VALUES ('41220', '$10031', '0');
INSERT INTO `shop_sell_price` VALUES ('41221', 'é»æå¦ç²¾è¢å­', '0');
INSERT INTO `shop_sell_price` VALUES ('41222', '$10008', '0');
INSERT INTO `shop_sell_price` VALUES ('41223', '$10007', '0');
INSERT INTO `shop_sell_price` VALUES ('41224', '$10009', '0');
INSERT INTO `shop_sell_price` VALUES ('41225', '$10013', '0');
INSERT INTO `shop_sell_price` VALUES ('41226', '$10014', '0');
INSERT INTO `shop_sell_price` VALUES ('41227', '$10033', '0');
INSERT INTO `shop_sell_price` VALUES ('41228', '$10034', '0');
INSERT INTO `shop_sell_price` VALUES ('41229', '$10025', '0');
INSERT INTO `shop_sell_price` VALUES ('41230', '$10020', '0');
INSERT INTO `shop_sell_price` VALUES ('41231', '$10021', '0');
INSERT INTO `shop_sell_price` VALUES ('41232', '$10016', '0');
INSERT INTO `shop_sell_price` VALUES ('41233', '$10017', '0');
INSERT INTO `shop_sell_price` VALUES ('41234', '$10023', '0');
INSERT INTO `shop_sell_price` VALUES ('41235', '$10024', '0');
INSERT INTO `shop_sell_price` VALUES ('41236', '$10026', '0');
INSERT INTO `shop_sell_price` VALUES ('41237', '$10027', '0');
INSERT INTO `shop_sell_price` VALUES ('41238', '$10017', '0');
INSERT INTO `shop_sell_price` VALUES ('41239', '$10018', '0');
INSERT INTO `shop_sell_price` VALUES ('41240', '$10022', '0');
INSERT INTO `shop_sell_price` VALUES ('41241', '$10015', '0');
INSERT INTO `shop_sell_price` VALUES ('41242', 'å¦é­å®ç©è¢', '0');
INSERT INTO `shop_sell_price` VALUES ('41243', 'ææ¯å¡å·´å¾·è¡¥ç»è¢', '0');
INSERT INTO `shop_sell_price` VALUES ('41244', 'ææ¯å¡å·´å¾·è¡¥ç»ç®±', '0');
INSERT INTO `shop_sell_price` VALUES ('41245', 'æº¶è§£å', '0');
INSERT INTO `shop_sell_price` VALUES ('41246', 'é­æ³ç»æ¶ä½', '0');
INSERT INTO `shop_sell_price` VALUES ('41247', 'é­æ³å¨å¨çè¢å­', '0');
INSERT INTO `shop_sell_price` VALUES ('41248', 'é­æ³å¨å¨ï¼è¥è¥', '0');
INSERT INTO `shop_sell_price` VALUES ('41249', 'é­æ³å¨å¨ï¼å°æåå·´', '0');
INSERT INTO `shop_sell_price` VALUES ('41250', 'é­æ³å¨å¨ï¼éç¼å®å®', '0');
INSERT INTO `shop_sell_price` VALUES ('41251', 'éª·é«å£æ¯', '0');
INSERT INTO `shop_sell_price` VALUES ('41252', 'çå¥çä¹é¾', '0');
INSERT INTO `shop_sell_price` VALUES ('41253', 'çå®«æçå¸çè°å³æ', '0');
INSERT INTO `shop_sell_price` VALUES ('41254', 'èå©çå¾½ç« ', '0');
INSERT INTO `shop_sell_price` VALUES ('41255', 'æçä¹¦ï¼1é¶æ®µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41256', 'æçä¹¦ï¼2é¶æ®µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41257', 'æçä¹¦ï¼3é¶æ®µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41258', 'æçä¹¦ï¼4é¶æ®µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41259', 'æçä¹¦ï¼5é¶æ®µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41260', 'æ´ç«', '150');
INSERT INTO `shop_sell_price` VALUES ('41261', 'é¥­å¢', '0');
INSERT INTO `shop_sell_price` VALUES ('41262', 'é¸¡èä¸²ç§', '0');
INSERT INTO `shop_sell_price` VALUES ('41263', 'å¤ªé³è±ç±½', '0');
INSERT INTO `shop_sell_price` VALUES ('41264', 'é¢ç²', '0');
INSERT INTO `shop_sell_price` VALUES ('41265', 'èè', '0');
INSERT INTO `shop_sell_price` VALUES ('41266', 'èè', '0');
INSERT INTO `shop_sell_price` VALUES ('41267', 'èµ·å£«', '0');
INSERT INTO `shop_sell_price` VALUES ('41268', 'å°æ¯è¨', '0');
INSERT INTO `shop_sell_price` VALUES ('41269', 'ç¤çç±³', '0');
INSERT INTO `shop_sell_price` VALUES ('41271', 'çç±³è±', '0');
INSERT INTO `shop_sell_price` VALUES ('41272', 'çä¸è¾£', '0');
INSERT INTO `shop_sell_price` VALUES ('41273', 'æ¾é¥¼', '0');
INSERT INTO `shop_sell_price` VALUES ('41274', 'èèè¿', '0');
INSERT INTO `shop_sell_price` VALUES ('41275', 'çè', '0');
INSERT INTO `shop_sell_price` VALUES ('41276', 'å±±çªè', '0');
INSERT INTO `shop_sell_price` VALUES ('41277', 'æ¼æµ®ä¹ç¼èæ', '0');
INSERT INTO `shop_sell_price` VALUES ('41278', 'ç¤çè', '0');
INSERT INTO `shop_sell_price` VALUES ('41279', 'çé¥¼', '0');
INSERT INTO `shop_sell_price` VALUES ('41280', 'ç¤èèè¿èµ·å¸', '0');
INSERT INTO `shop_sell_price` VALUES ('41281', 'æ°´ææ²æ', '0');
INSERT INTO `shop_sell_price` VALUES ('41282', 'æ°´æç³éè', '0');
INSERT INTO `shop_sell_price` VALUES ('41283', 'ç¤å±±çªèä¸²', '0');
INSERT INTO `shop_sell_price` VALUES ('41284', 'èèæ±¤', '0');
INSERT INTO `shop_sell_price` VALUES ('41285', 'ç¹å«çæ¼æµ®ä¹ç¼èæ', '0');
INSERT INTO `shop_sell_price` VALUES ('41286', 'ç¹å«çç¤çè', '0');
INSERT INTO `shop_sell_price` VALUES ('41287', 'ç¹å«ççé¥¼', '0');
INSERT INTO `shop_sell_price` VALUES ('41288', 'ç¹å«çç¤èèè¿èµ·å¸', '0');
INSERT INTO `shop_sell_price` VALUES ('41289', 'ç¹å«çæ°´ææ²æ', '0');
INSERT INTO `shop_sell_price` VALUES ('41290', 'ç¹å«çæ°´æç³éè', '0');
INSERT INTO `shop_sell_price` VALUES ('41291', 'ç¹å«çç¤å±±çªèä¸²', '0');
INSERT INTO `shop_sell_price` VALUES ('41292', 'ç¹å«çèèæ±¤', '0');
INSERT INTO `shop_sell_price` VALUES ('41293', 'é¿éç«¿', '0');
INSERT INTO `shop_sell_price` VALUES ('41294', 'ç­éç«¿', '0');
INSERT INTO `shop_sell_price` VALUES ('41295', 'é¥µ', '0');
INSERT INTO `shop_sell_price` VALUES ('41296', 'é²·é±¼', '0');
INSERT INTO `shop_sell_price` VALUES ('41297', 'é²é±¼', '0');
INSERT INTO `shop_sell_price` VALUES ('41298', 'é³é±¼', '0');
INSERT INTO `shop_sell_price` VALUES ('41299', 'èç­å¸¦é±¼', '0');
INSERT INTO `shop_sell_price` VALUES ('41300', 'é²é±¼', '0');
INSERT INTO `shop_sell_price` VALUES ('41301', 'åçº¢åçé±¼', '0');
INSERT INTO `shop_sell_price` VALUES ('41302', 'åç»¿åçé±¼', '0');
INSERT INTO `shop_sell_price` VALUES ('41303', 'åèåçé±¼', '0');
INSERT INTO `shop_sell_price` VALUES ('41304', 'åç½åçé±¼', '0');
INSERT INTO `shop_sell_price` VALUES ('41305', 'ç ´ç¢çè³ç¯', '0');
INSERT INTO `shop_sell_price` VALUES ('41306', 'ç ´ç¢çææ', '0');
INSERT INTO `shop_sell_price` VALUES ('41307', 'ç ´ç¢çé¡¹é¾', '0');
INSERT INTO `shop_sell_price` VALUES ('41308', 'åèçåçè¢å­', '0');
INSERT INTO `shop_sell_price` VALUES ('41309', 'å® ç©æéç', '0');
INSERT INTO `shop_sell_price` VALUES ('41310', 'èå©æå®', '0');
INSERT INTO `shop_sell_price` VALUES ('41311', 'æåç®±', '0');
INSERT INTO `shop_sell_price` VALUES ('41312', 'å ææ¯å¸çç®', '0');
INSERT INTO `shop_sell_price` VALUES ('41313', 'å ææ¯å¸ççµé­ç', '0');
INSERT INTO `shop_sell_price` VALUES ('41314', 'å ææ¯å¸çç¬¦å', '0');
INSERT INTO `shop_sell_price` VALUES ('41315', 'å£æ°´', '0');
INSERT INTO `shop_sell_price` VALUES ('41316', 'ç¥å£çç±³ç´¢èç²', '0');
INSERT INTO `shop_sell_price` VALUES ('41317', 'æç½æ£®çæ¨èä¹¦', '0');
INSERT INTO `shop_sell_price` VALUES ('41318', 'å¯æ©çä¾¿æ¡çº¸', '0');
INSERT INTO `shop_sell_price` VALUES ('41319', 'èè±è±æ', '0');
INSERT INTO `shop_sell_price` VALUES ('41320', 'é»è¥¿è±æ', '0');
INSERT INTO `shop_sell_price` VALUES ('41321', 'ç«ç°è±æ', '0');
INSERT INTO `shop_sell_price` VALUES ('41322', 'å¡æè±æ', '0');
INSERT INTO `shop_sell_price` VALUES ('41323', 'å¤ªé³è±è±æ', '0');
INSERT INTO `shop_sell_price` VALUES ('41324', 'å°èå°è±æ', '0');
INSERT INTO `shop_sell_price` VALUES ('41325', 'åå£«ä¹è¯', '0');
INSERT INTO `shop_sell_price` VALUES ('41326', 'åå£«ä¹è¯', '0');
INSERT INTO `shop_sell_price` VALUES ('41327', 'å¹½çµä¹æ°æ¯', '0');
INSERT INTO `shop_sell_price` VALUES ('41328', 'åèçæ°æ¯', '0');
INSERT INTO `shop_sell_price` VALUES ('41329', 'æ æ¬å¶ä½å§æä¹¦', '0');
INSERT INTO `shop_sell_price` VALUES ('41330', 'ç©çèèä¹è¯', '0');
INSERT INTO `shop_sell_price` VALUES ('41331', 'ç©ççä¹è¯', '0');
INSERT INTO `shop_sell_price` VALUES ('42001', 'æå®ä¼ é(GMæå¾å®¤)', '200');
INSERT INTO `shop_sell_price` VALUES ('42002', 'æå®ä¼ é(æ°´æ¶æ´ç©´1F)', '200');
INSERT INTO `shop_sell_price` VALUES ('42003', 'æå®ä¼ é(æ°´æ¶æ´ç©´2F)', '200');
INSERT INTO `shop_sell_price` VALUES ('42004', 'æå®ä¼ é(æ°´æ¶æ´ç©´3F)', '200');
INSERT INTO `shop_sell_price` VALUES ('42005', 'æå®ä¼ é(è±¡çå¡1F)', '200');
INSERT INTO `shop_sell_price` VALUES ('42006', 'æå®ä¼ é(è¯ç¹å°ç1F)', '200');
INSERT INTO `shop_sell_price` VALUES ('42007', 'æå®ä¼ é(éå¿ä¹å²)', '200');
INSERT INTO `shop_sell_price` VALUES ('42008', 'æå®ä¼ é(è¯ç¹å°ç2F)', '200');
INSERT INTO `shop_sell_price` VALUES ('42009', 'æå®ä¼ é(è¯ç¹å°ç3F)', '200');
INSERT INTO `shop_sell_price` VALUES ('42010', 'æå®ä¼ é(è¯ç¹å°ç4F)', '200');
INSERT INTO `shop_sell_price` VALUES ('42011', 'æå®ä¼ é(éä¼åº1F)', '200');
INSERT INTO `shop_sell_price` VALUES ('42012', 'æå®ä¼ é(çªå»éè®­ç»åº1F)', '200');
INSERT INTO `shop_sell_price` VALUES ('42013', 'æå®ä¼ é(é­å½åçåå¬å®¤1F)', '200');
INSERT INTO `shop_sell_price` VALUES ('42014', 'æå®ä¼ é(éå½æç»å®¤1F)', '200');
INSERT INTO `shop_sell_price` VALUES ('42016', 'æå®ä¼ é(é­å½è®­ç»åº1F)', '200');
INSERT INTO `shop_sell_price` VALUES ('42017', 'æå®ä¼ é(æ¢¦å¹»ä¹å²)', '200');
INSERT INTO `shop_sell_price` VALUES ('42018', 'æå®ä¼ é(é­å½å¬å¤å®¤1F)', '200');
INSERT INTO `shop_sell_price` VALUES ('42019', 'æå®ä¼ é(é»æçç»ç1F)', '200');
INSERT INTO `shop_sell_price` VALUES ('42020', 'æå®ä¼ é(é»é­æ³ä¿®ç¼åº2F)', '200');
INSERT INTO `shop_sell_price` VALUES ('42021', 'æå®ä¼ é(å¤ä»£å·¨äººä¹å¢)', '200');
INSERT INTO `shop_sell_price` VALUES ('42022', 'æå®ä¼ é(äºä¸åå)', '200');
INSERT INTO `shop_sell_price` VALUES ('42023', 'æå®ä¼ é(GMæ¿é´)', '200');
INSERT INTO `shop_sell_price` VALUES ('42024', 'æå®ä¼ é(æ­£ä¹ç¥æ®¿)', '200');
INSERT INTO `shop_sell_price` VALUES ('42025', 'æå®ä¼ é(éªæ¶ç¥æ®¿)', '200');
INSERT INTO `shop_sell_price` VALUES ('42026', 'æå®ä¼ é(åçè¯æ©æ æ¯å°)', '200');
INSERT INTO `shop_sell_price` VALUES ('42027', 'æå®ä¼ é(é¶éªå£«æåº)', '60');
INSERT INTO `shop_sell_price` VALUES ('42028', 'æå®ä¼ é(å¤é²ä¸å°ç7F)', '200');
INSERT INTO `shop_sell_price` VALUES ('42029', 'å²æ¢ä¹å¡ç§»å¨å·è½´(100F)', '200');
INSERT INTO `shop_sell_price` VALUES ('42030', 'æå®ä¼ é(å²æ¢ä¹å¡90F)', '200');
INSERT INTO `shop_sell_price` VALUES ('42031', 'æå®ä¼ é(å²æ¢ä¹å¡80F)', '200');
INSERT INTO `shop_sell_price` VALUES ('42032', 'æå®ä¼ é(å²æ¢ä¹å¡70F)', '200');
INSERT INTO `shop_sell_price` VALUES ('42033', 'æå®ä¼ é(å²æ¢ä¹å¡60F)', '200');
INSERT INTO `shop_sell_price` VALUES ('42035', 'æå®ä¼ é(å²æ¢ä¹å¡50F)', '200');
INSERT INTO `shop_sell_price` VALUES ('42036', 'æå®ä¼ é(å²æ¢ä¹å¡40F)', '200');
INSERT INTO `shop_sell_price` VALUES ('42037', 'æå®ä¼ é(å²æ¢ä¹å¡30F)', '200');
INSERT INTO `shop_sell_price` VALUES ('42038', 'æå®ä¼ é(å²æ¢ä¹å¡20F)', '200');
INSERT INTO `shop_sell_price` VALUES ('42039', 'æå®ä¼ é(å²æ¢ä¹å¡10F)', '200');
INSERT INTO `shop_sell_price` VALUES ('42040', 'æå®ä¼ é(æµ·è´¼å²å°ç1F)', '200');
INSERT INTO `shop_sell_price` VALUES ('42041', 'æå®ä¼ é(æµ·è´¼å²å°ç2F)', '200');
INSERT INTO `shop_sell_price` VALUES ('42042', 'æå®ä¼ é(æµ·è´¼å²å°ç3F)', '200');
INSERT INTO `shop_sell_price` VALUES ('42043', 'æå®ä¼ é(å°åºæ¹)', '200');
INSERT INTO `shop_sell_price` VALUES ('42044', 'æå®ä¼ é(è¯´è¯å²ç»ååº)', '200');
INSERT INTO `shop_sell_price` VALUES ('42045', 'æå®ä¼ é(è¡çå°å±)', '200');
INSERT INTO `shop_sell_price` VALUES ('42046', 'æå®ä¼ é(è¡çå°å±)', '200');
INSERT INTO `shop_sell_price` VALUES ('42047', 'æå®ä¼ é(å¤ä»£äººç©ºé´3F)', '200');
INSERT INTO `shop_sell_price` VALUES ('42048', 'æå®ä¼ é(å¥¥å§å°ç)', '200');
INSERT INTO `shop_sell_price` VALUES ('42049', 'æå®ä¼ é(éå¿ä¹å²)', '200');
INSERT INTO `shop_sell_price` VALUES ('42050', 'å°ç±å¥åºå·', '200');
INSERT INTO `shop_sell_price` VALUES ('42051', 'æå®ä¼ é(å½±ä¹ç¥æ®¿å¤é¨)', '200');
INSERT INTO `shop_sell_price` VALUES ('42052', 'æå®ä¼ é(å½±ä¹ç¥æ®¿1F)', '200');
INSERT INTO `shop_sell_price` VALUES ('43000', 'è¿çè¯æ°´', '0');
INSERT INTO `shop_sell_price` VALUES ('45000', 'é­æ³ä¹¦(åçº§æ²»ææ¯)', '50');
INSERT INTO `shop_sell_price` VALUES ('45001', 'é­æ³ä¹¦(æ¥åæ¯)', '50');
INSERT INTO `shop_sell_price` VALUES ('45002', 'é­æ³ä¹¦(ä¿æ¤ç½©)', '50');
INSERT INTO `shop_sell_price` VALUES ('45003', 'é­æ³ä¹¦(åç®­)', '50');
INSERT INTO `shop_sell_price` VALUES ('45004', 'é­æ³ä¹¦(æå®ä¼ é)', '50');
INSERT INTO `shop_sell_price` VALUES ('45005', 'é­æ³ä¹¦(å°ç®­)', '50');
INSERT INTO `shop_sell_price` VALUES ('45006', 'é­æ³ä¹¦(é£å)', '50');
INSERT INTO `shop_sell_price` VALUES ('45007', 'é­æ³ä¹¦(ç¥å£æ­¦å¨)', '50');
INSERT INTO `shop_sell_price` VALUES ('45008', 'é­æ³ä¹¦(è§£æ¯æ¯)', '50');
INSERT INTO `shop_sell_price` VALUES ('45009', 'é­æ³ä¹¦(å¯å·ææ )', '50');
INSERT INTO `shop_sell_price` VALUES ('45010', 'é­æ³ä¹¦(æ¯å)', '50');
INSERT INTO `shop_sell_price` VALUES ('45011', 'é­æ³ä¹¦(æä¼¼é­æ³æ­¦å¨)', '50');
INSERT INTO `shop_sell_price` VALUES ('45012', 'é­æ³ä¹¦(æ æéå½¢æ¯)', '50');
INSERT INTO `shop_sell_price` VALUES ('45013', 'é­æ³ä¹¦(è´éå¼ºå)', '50');
INSERT INTO `shop_sell_price` VALUES ('45014', 'é­æ³ä¹¦(å°ç±ä¹ç)', '50');
INSERT INTO `shop_sell_price` VALUES ('45015', 'é­æ³ä¹¦(ç«ç®­)', '50');
INSERT INTO `shop_sell_price` VALUES ('45016', 'é­æ³ä¹¦(æåé·çµ)', '50');
INSERT INTO `shop_sell_price` VALUES ('45017', 'é­æ³ä¹¦(å¯å°æ°æ¯)', '50');
INSERT INTO `shop_sell_price` VALUES ('45018', 'é­æ³ä¹¦(ä¸­çº§æ²»ææ¯)', '50');
INSERT INTO `shop_sell_price` VALUES ('45019', 'é­æ³ä¹¦(?ç²åæ¯)', '50');
INSERT INTO `shop_sell_price` VALUES ('45020', 'é­æ³ä¹¦(é ç²æ¤æ)', '50');
INSERT INTO `shop_sell_price` VALUES ('45021', 'é­æ³ä¹¦(èµ·æ­»åçæ¯)', '50');
INSERT INTO `shop_sell_price` VALUES ('45022', 'é­æ³ä¹¦(è½éææµ)', '50');
INSERT INTO `shop_sell_price` VALUES ('49005', 'å¡ç«æ®çè¢å­', '0');
INSERT INTO `shop_sell_price` VALUES ('49006', 'å¡ç«æ®çè¢å­', '0');
INSERT INTO `shop_sell_price` VALUES ('49007', 'å¡ç«æ®çè¢å­', '0');
INSERT INTO `shop_sell_price` VALUES ('49008', 'å¡ç«æ®çè¢å­', '0');
INSERT INTO `shop_sell_price` VALUES ('49009', 'å¡ç«æ®çé«çº§è¢å­', '0');
INSERT INTO `shop_sell_price` VALUES ('49010', 'å¡ç«æ®çé«çº§è¢å­', '0');
INSERT INTO `shop_sell_price` VALUES ('49011', 'å¡ç«æ®çé«çº§è¢å­', '0');
INSERT INTO `shop_sell_price` VALUES ('49012', 'å¡ç«æ®çé«çº§è¢å­', '0');
INSERT INTO `shop_sell_price` VALUES ('49013', 'é­æçå·è½´', '50000');
INSERT INTO `shop_sell_price` VALUES ('49014', 'çµé­ä¹ç', '0');
INSERT INTO `shop_sell_price` VALUES ('49015', 'é»è²ç±³ç´¢èæº¶æ¶²', '0');
INSERT INTO `shop_sell_price` VALUES ('49103', null, '1000');
INSERT INTO `shop_sell_price` VALUES ('49104', null, '1000');
INSERT INTO `shop_sell_price` VALUES ('49108', null, '4500');
INSERT INTO `shop_sell_price` VALUES ('49109', null, '4500');
INSERT INTO `shop_sell_price` VALUES ('49117', null, '750');
INSERT INTO `shop_sell_price` VALUES ('49118', null, '750');
INSERT INTO `shop_sell_price` VALUES ('49119', null, '750');
INSERT INTO `shop_sell_price` VALUES ('49120', null, '750');
INSERT INTO `shop_sell_price` VALUES ('49122', null, '2000');
INSERT INTO `shop_sell_price` VALUES ('49124', null, '2000');
INSERT INTO `shop_sell_price` VALUES ('49125', null, '2000');
INSERT INTO `shop_sell_price` VALUES ('49127', null, '4500');
INSERT INTO `shop_sell_price` VALUES ('49129', null, '4500');
INSERT INTO `shop_sell_price` VALUES ('49156', null, '150');
INSERT INTO `shop_sell_price` VALUES ('49157', null, '250');
INSERT INTO `shop_sell_price` VALUES ('49158', null, '250');
INSERT INTO `shop_sell_price` VALUES ('60003', 'é«çº§ç¥­å¸å¬å¤ç', '0');
INSERT INTO `shop_sell_price` VALUES ('60101', 'é¥µ (10)', '0');
INSERT INTO `shop_sell_price` VALUES ('60102', 'é¥µ (100)', '0');
INSERT INTO `shop_sell_price` VALUES ('60103', 'æº¶è§£å (10)', '0');
INSERT INTO `shop_sell_price` VALUES ('60104', 'æº¶è§£å (100)', '0');
INSERT INTO `shop_sell_price` VALUES ('60105', 'é¡¹å[Lv.10 ç§ç¾ç¬]', '0');
INSERT INTO `shop_sell_price` VALUES ('60106', 'é¡¹å[Lv.10 ç«]', '0');
INSERT INTO `shop_sell_price` VALUES ('60107', 'é¡¹å[Lv.10 ç]', '0');
INSERT INTO `shop_sell_price` VALUES ('60108', 'é¡¹å[Lv.10 æå®¾ç]', '0');
INSERT INTO `shop_sell_price` VALUES ('60109', 'é¡¹å[Lv.10 ç¼]', '0');
INSERT INTO `shop_sell_price` VALUES ('60110', 'é¡¹å[Lv.10 æµ£ç]', '0');
INSERT INTO `shop_sell_price` VALUES ('60111', 'é¡¹å[Lv.10 å°çç¬]', '0');
INSERT INTO `shop_sell_price` VALUES ('60112', 'é¡¹å[Lv.10 å£ä¼¯çº³ç¬]', '0');
INSERT INTO `shop_sell_price` VALUES ('60113', 'é¡¹å[Lv.10 çç¸]', '0');
INSERT INTO `shop_sell_price` VALUES ('60114', 'é¡¹å[Lv.10 æ´èµ°å]', '0');
INSERT INTO `shop_sell_price` VALUES ('60115', 'é¡¹å[Lv.10 åå£«å¥]', '0');
INSERT INTO `shop_sell_price` VALUES ('60116', 'é¡¹å[Lv.10 æ¯å©]', '0');
INSERT INTO `shop_sell_price` VALUES ('60201', 'ç¬é´ç§»å¨ææ', '0');
INSERT INTO `shop_sell_price` VALUES ('60202', 'å® ç©æç¯', '0');
INSERT INTO `shop_sell_price` VALUES ('60203', 'é­æ³ç¬¦è¢', '0');
INSERT INTO `shop_sell_price` VALUES ('60204', 'é­æ³å¨å¨ï¼é¿è', '0');
INSERT INTO `shop_sell_price` VALUES ('60205', 'é­æ³å¨å¨ï¼å¥æ¯å¦ä¿®', '0');
INSERT INTO `shop_sell_price` VALUES ('60206', 'é­æ³å¨å¨ï¼ç³å¤´é«ä»', '0');
INSERT INTO `shop_sell_price` VALUES ('60207', 'è£å¤é´å®å·è½´', '0');
INSERT INTO `shop_sell_price` VALUES ('60208', 'è£å¤å¼ºåæ°´æ¶', '0');
INSERT INTO `shop_sell_price` VALUES ('60209', 'åå¿è¡ç', '0');
INSERT INTO `shop_sell_price` VALUES ('60210', 'å·çª¥å¡', '0');
INSERT INTO `shop_sell_price` VALUES ('60211', 'åå·çª¥å¡', '0');
INSERT INTO `shop_sell_price` VALUES ('60212', 'ç¬é´ç§»å¨ææ', '0');
INSERT INTO `shop_sell_price` VALUES ('60213', 'éçè¯æ°´', '0');
INSERT INTO `shop_sell_price` VALUES ('60301', 'å® ç©æ´»åè¯æ°´', '0');
INSERT INTO `shop_sell_price` VALUES ('60302', 'å® ç©é­åè¯æ°´', '0');
INSERT INTO `shop_sell_price` VALUES ('60401', 'é­æ³å¾½ç« ', '0');
INSERT INTO `shop_sell_price` VALUES ('62001', 'ç²¾çµçç¥ç¦å¾¡å®', '0');
INSERT INTO `shop_sell_price` VALUES ('62002', 'ç²¾çµçç¥ç¦ç­¾è¯', '0');
INSERT INTO `shop_sell_price` VALUES ('62003', 'åå²å¤ä¹¦(ä¸å)', '0');
INSERT INTO `shop_sell_price` VALUES ('62004', 'åå²å¤ä¹¦(ä¸å)', '0');
INSERT INTO `shop_sell_price` VALUES ('62005', 'åå²å¤ä¹¦(å¨)', '0');
INSERT INTO `shop_sell_price` VALUES ('62006', 'å¤ä»£çç¾½æ¯ç¬', '0');
INSERT INTO `shop_sell_price` VALUES ('62007', 'åç¥ç¦çæ³æ°´', '0');
INSERT INTO `shop_sell_price` VALUES ('62008', 'å¤ä»£æ ¼å©è¬çç¾½æ¯', '0');
INSERT INTO `shop_sell_price` VALUES ('70000', 'è§£å¡ç¹æ»å¨æ¡(é¶éªå£«æ)', '250');
INSERT INTO `shop_sell_price` VALUES ('75000', 'é±å', '0');
INSERT INTO `shop_sell_price` VALUES ('100004', 'åé¦', '8');
INSERT INTO `shop_sell_price` VALUES ('100008', 'ç±³ç´¢èç­å', '1');
INSERT INTO `shop_sell_price` VALUES ('100009', 'å¥¥éåé²æ ¹ç­å', '1');
INSERT INTO `shop_sell_price` VALUES ('100025', 'é¶å', '750');
INSERT INTO `shop_sell_price` VALUES ('100027', 'å¼¯å', '650');
INSERT INTO `shop_sell_price` VALUES ('100029', 'é¶é¿å', '950');
INSERT INTO `shop_sell_price` VALUES ('100037', 'å¤§é©¬å£«é©å', '5500');
INSERT INTO `shop_sell_price` VALUES ('100041', 'æ­¦å£«å', '5500');
INSERT INTO `shop_sell_price` VALUES ('100042', 'ç»å', '1');
INSERT INTO `shop_sell_price` VALUES ('100049', 'æ­¦å®ä¹å', '1');
INSERT INTO `shop_sell_price` VALUES ('100052', 'åæå', '7800');
INSERT INTO `shop_sell_price` VALUES ('100057', 'çé²åºä¹å', '8100');
INSERT INTO `shop_sell_price` VALUES ('100062', 'æ­¦å®åæå', '1');
INSERT INTO `shop_sell_price` VALUES ('100064', 'å·¨å', '1');
INSERT INTO `shop_sell_price` VALUES ('100074', 'é¶ååå', '1');
INSERT INTO `shop_sell_price` VALUES ('100084', 'æé»åå', '1');
INSERT INTO `shop_sell_price` VALUES ('100095', 'ç', '66');
INSERT INTO `shop_sell_price` VALUES ('100098', 'éç', '385');
INSERT INTO `shop_sell_price` VALUES ('100099', 'ç²¾çµä¹ç', '1');
INSERT INTO `shop_sell_price` VALUES ('100102', 'é²è¥¿é¤', '650');
INSERT INTO `shop_sell_price` VALUES ('100103', 'æ', '650');
INSERT INTO `shop_sell_price` VALUES ('100107', 'æ·±çº¢é¿ç', '1');
INSERT INTO `shop_sell_price` VALUES ('100132', 'ç¥å®é­æ', '1');
INSERT INTO `shop_sell_price` VALUES ('100143', 'ææ§', '770');
INSERT INTO `shop_sell_price` VALUES ('100151', 'æ¶é­æ§å¤´', '1');
INSERT INTO `shop_sell_price` VALUES ('100164', 'æé»é¢çª', '1');
INSERT INTO `shop_sell_price` VALUES ('100169', 'çäººä¹å¼', '1');
INSERT INTO `shop_sell_price` VALUES ('100172', 'å¼', '55');
INSERT INTO `shop_sell_price` VALUES ('100189', 'æé»åå­å¼', '1');
INSERT INTO `shop_sell_price` VALUES ('120011', 'æé­æ³å¤´ç', '250');
INSERT INTO `shop_sell_price` VALUES ('120016', 'æ¼æ³¢å¸½å­', '0');
INSERT INTO `shop_sell_price` VALUES ('120043', 'é¢ç', '200');
INSERT INTO `shop_sell_price` VALUES ('120056', 'æé­æ³æç¯·', '100');
INSERT INTO `shop_sell_price` VALUES ('120074', 'é¶åæç¯·', '0');
INSERT INTO `shop_sell_price` VALUES ('120077', 'éèº«æç¯·', '0');
INSERT INTO `shop_sell_price` VALUES ('120085', 'Tæ¤', '0');
INSERT INTO `shop_sell_price` VALUES ('120101', 'ç®ç²', '5500');
INSERT INTO `shop_sell_price` VALUES ('120112', 'æ¼æ³¢å¤å¥', '0');
INSERT INTO `shop_sell_price` VALUES ('120128', 'æ°´æ¶çç²', '0');
INSERT INTO `shop_sell_price` VALUES ('120137', 'ç²¾çµé¾ç²', '0');
INSERT INTO `shop_sell_price` VALUES ('120149', 'ééçç²', '5500');
INSERT INTO `shop_sell_price` VALUES ('120154', 'éå±çç²', '5500');
INSERT INTO `shop_sell_price` VALUES ('120182', 'æå¥', '0');
INSERT INTO `shop_sell_price` VALUES ('120242', 'å¤§ç¾ç', '1200');
INSERT INTO `shop_sell_price` VALUES ('120244', 'å°åé­åé¡¹é¾', '0');
INSERT INTO `shop_sell_price` VALUES ('120245', 'å°åææ·é¡¹é¾', '0');
INSERT INTO `shop_sell_price` VALUES ('120246', 'å°ååéé¡¹é¾', '0');
INSERT INTO `shop_sell_price` VALUES ('120247', 'å°åæºåé¡¹é¾', '0');
INSERT INTO `shop_sell_price` VALUES ('120248', 'å°åç²¾ç¥é¡¹é¾', '0');
INSERT INTO `shop_sell_price` VALUES ('120249', 'å°åä½è´¨é¡¹é¾', '0');
INSERT INTO `shop_sell_price` VALUES ('120254', 'é­åé¡¹é¾', '0');
INSERT INTO `shop_sell_price` VALUES ('120256', 'ææ·é¡¹é¾', '0');
INSERT INTO `shop_sell_price` VALUES ('120264', 'åéé¡¹é¾', '0');
INSERT INTO `shop_sell_price` VALUES ('120266', 'æºåé¡¹é¾', '0');
INSERT INTO `shop_sell_price` VALUES ('120267', 'ç²¾ç¥é¡¹é¾', '0');
INSERT INTO `shop_sell_price` VALUES ('120268', 'ä½è´¨é¡¹é¾', '0');
INSERT INTO `shop_sell_price` VALUES ('120280', 'ç­é­ææ', '0');
INSERT INTO `shop_sell_price` VALUES ('120285', 'æ°´çµææ', '0');
INSERT INTO `shop_sell_price` VALUES ('120289', 'æ·±æ¸ææ', '0');
INSERT INTO `shop_sell_price` VALUES ('120300', 'å°çµææ', '0');
INSERT INTO `shop_sell_price` VALUES ('120302', 'é£çµææ', '0');
INSERT INTO `shop_sell_price` VALUES ('120304', 'ç«çµææ', '0');
INSERT INTO `shop_sell_price` VALUES ('120306', 'å°åèº«ä½è°å¸¦', '0');
INSERT INTO `shop_sell_price` VALUES ('120307', 'å°åçµé­è°å¸¦', '0');
INSERT INTO `shop_sell_price` VALUES ('120308', 'å°åç²¾ç¥è°å¸¦', '0');
INSERT INTO `shop_sell_price` VALUES ('120309', 'åæèº«ä½è°å¸¦', '0');
INSERT INTO `shop_sell_price` VALUES ('120310', 'åæçµé­è°å¸¦', '0');
INSERT INTO `shop_sell_price` VALUES ('120311', 'åæç²¾ç¥è°å¸¦', '0');
INSERT INTO `shop_sell_price` VALUES ('120312', 'èº«ä½è°å¸¦', '0');
INSERT INTO `shop_sell_price` VALUES ('120316', 'çµé­è°å¸¦', '0');
INSERT INTO `shop_sell_price` VALUES ('120317', 'æ¬§åç®å¸¦', '0');
INSERT INTO `shop_sell_price` VALUES ('120319', 'ç²¾ç¥è°å¸¦', '0');
INSERT INTO `shop_sell_price` VALUES ('120320', 'æ³°å¦ç®å¸¦', '0');
INSERT INTO `shop_sell_price` VALUES ('120321', 'å¤ç½ç®å¸¦', '0');
INSERT INTO `shop_sell_price` VALUES ('140006', 'åé æªç©é­æ', '650');
INSERT INTO `shop_sell_price` VALUES ('140008', 'åèº«é­æ', '650');
INSERT INTO `shop_sell_price` VALUES ('140010', 'æ²»æè¯æ°´', '64');
INSERT INTO `shop_sell_price` VALUES ('140011', 'å¼ºåæ²»æè¯æ°´', '212');
INSERT INTO `shop_sell_price` VALUES ('140012', 'ç»ææ²»æè¯æ°´', '990');
INSERT INTO `shop_sell_price` VALUES ('140013', 'èªæå éè¯æ°´', '412');
INSERT INTO `shop_sell_price` VALUES ('140014', 'åæ¢è¯æ°´', '920');
INSERT INTO `shop_sell_price` VALUES ('140015', 'å éé­åæ¢å¤è¯æ°´', '777');
INSERT INTO `shop_sell_price` VALUES ('140016', 'æéè¯æ°´', '990');
INSERT INTO `shop_sell_price` VALUES ('140018', 'å¼ºåèªæå éè¯æ°´', '2722');
INSERT INTO `shop_sell_price` VALUES ('140061', 'æ æª¬', '9');
INSERT INTO `shop_sell_price` VALUES ('140062', 'é¦è', '9');
INSERT INTO `shop_sell_price` VALUES ('140065', 'ç³æ', '6');
INSERT INTO `shop_sell_price` VALUES ('140068', 'ç²¾çµé¥¼å¹²', '1250');
INSERT INTO `shop_sell_price` VALUES ('140069', 'æ©å­', '9');
INSERT INTO `shop_sell_price` VALUES ('140072', 'ç¤èé¥¼', '33');
INSERT INTO `shop_sell_price` VALUES ('140074', 'å¯¹çç²æ½æ³çå·è½´', '80000');
INSERT INTO `shop_sell_price` VALUES ('140087', 'å¯¹æ­¦å¨æ½æ³çå·è½´', '40000');
INSERT INTO `shop_sell_price` VALUES ('140088', 'åå½¢å·è½´', '2422');
INSERT INTO `shop_sell_price` VALUES ('140089', 'å¤æ´»å·è½´', '2134');
INSERT INTO `shop_sell_price` VALUES ('140100', 'ç¬é´ç§»å¨å·è½´', '50');
INSERT INTO `shop_sell_price` VALUES ('140119', 'è§£é¤ååçå·è½´', '100');
INSERT INTO `shop_sell_price` VALUES ('140506', 'å®ç¹çæ°´æ', '0');
INSERT INTO `shop_sell_price` VALUES ('200001', 'æ¬§è¥¿æ¯åé¦', '32');
INSERT INTO `shop_sell_price` VALUES ('200002', 'éª°å­åé¦', '240');
INSERT INTO `shop_sell_price` VALUES ('200027', 'å¼¯å', '1320');
INSERT INTO `shop_sell_price` VALUES ('200032', 'ä¾µç¥èä¹å', '2750');
INSERT INTO `shop_sell_price` VALUES ('200041', 'æ­¦å£«å', '22000');
INSERT INTO `shop_sell_price` VALUES ('200052', 'åæå', '19800');
INSERT INTO `shop_sell_price` VALUES ('200171', 'æ¬§è¥¿æ¯å¼', '100');
INSERT INTO `shop_sell_price` VALUES ('220034', 'æ¬§è¥¿æ¯å¤´ç', '150');
INSERT INTO `shop_sell_price` VALUES ('220043', 'é¢ç', '200');
INSERT INTO `shop_sell_price` VALUES ('220056', 'æé­æ³æç¯·', '100');
INSERT INTO `shop_sell_price` VALUES ('220101', 'ç®ç²', '1000');
INSERT INTO `shop_sell_price` VALUES ('220115', 'è¤ç²', '1000');
INSERT INTO `shop_sell_price` VALUES ('220122', 'é³ç²', '2000');
INSERT INTO `shop_sell_price` VALUES ('220125', 'é¾ç²', '6000');
INSERT INTO `shop_sell_price` VALUES ('220135', 'æ¬§è¥¿æ¯ç¯ç²', '200');
INSERT INTO `shop_sell_price` VALUES ('220136', 'æ¬§è¥¿æ¯é¾ç²', '800');
INSERT INTO `shop_sell_price` VALUES ('220147', 'é¶éç®ç²', '300');
INSERT INTO `shop_sell_price` VALUES ('220154', 'éå±çç²', '37000');
INSERT INTO `shop_sell_price` VALUES ('220213', 'ç­ç»é´', '300');
INSERT INTO `shop_sell_price` VALUES ('220237', 'é¿åæµ·ç¾ç', '90');
INSERT INTO `shop_sell_price` VALUES ('240010', 'æ²»æè¯æ°´', '64');
INSERT INTO `shop_sell_price` VALUES ('240074', 'å¯¹çç²æ½æ³çå·è½´', '34510');
INSERT INTO `shop_sell_price` VALUES ('240087', 'å¯¹æ­¦å¨æ½æ³çå·è½´', '75220');
