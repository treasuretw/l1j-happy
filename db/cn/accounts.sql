/*
MySQL Data Transfer
Source Host: localhost
Source Database: l1jdb_cn
Target Host: localhost
Target Database: l1jdb_cn
Date: 2011-6-25 ÏÂÎç 12:44:15
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for accounts
-- ----------------------------
CREATE TABLE `accounts` (
  `login` varchar(50) NOT NULL DEFAULT '',
  `password` varchar(50) DEFAULT NULL,
  `lastactive` datetime DEFAULT NULL,
  `access_level` int(11) DEFAULT NULL,
  `ip` varchar(20) NOT NULL DEFAULT '',
  `host` varchar(255) NOT NULL DEFAULT '',
  `online` int(11) NOT NULL DEFAULT '0',
  `banned` int(11) unsigned NOT NULL DEFAULT '0',
  `character_slot` int(2) unsigned NOT NULL DEFAULT '0',
  `warepassword` int(6) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`login`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `accounts` VALUES ('1230123', 'LFBJfo4Ky3xpTvTNfqhYSeGPP0s=', '2011-06-25 02:45:12', '100', '127.0.0.1', '127.0.0.1', '0', '0', '0', '0');
INSERT INTO `accounts` VALUES ('123123', 'YB8YiWZ++uuzO4wSVyg12j8Cf3g=', '2011-06-09 13:45:22', '0', '127.0.0.1', '127.0.0.1', '0', '0', '0', '0');
INSERT INTO `accounts` VALUES ('asda', 'jlReHDH5H3d8iUs70sLn1wRMyd0=', '2011-06-03 19:10:18', '0', '113.243.31.155', '113.243.63.177', '0', '0', '0', '0');
INSERT INTO `accounts` VALUES ('1231023', 'LFBJfo4Ky3xpTvTNfqhYSeGPP0s=', '2011-06-01 16:41:25', '0', '127.0.0.1', '127.0.0.1', '0', '0', '0', '0');
INSERT INTO `accounts` VALUES ('favl', '3S7bh+qet6Mv1AVydtOh+rhhwdU=', '2011-06-03 19:10:09', '0', '113.243.31.155', '113.243.31.155', '0', '0', '0', '0');
INSERT INTO `accounts` VALUES ('123456', 'fEqNCco3Yq9h5ZUglD3CZJT4lBs=', '2011-06-15 22:38:39', '0', '127.0.0.1', '127.0.0.1', '0', '0', '0', '0');
