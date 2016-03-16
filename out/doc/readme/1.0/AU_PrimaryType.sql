/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50610
Source Host           : 127.0.0.1:3306
Source Database       : DW_MetaData

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2013-05-20 12:36:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `AU_PrimaryType`
-- ----------------------------
DROP TABLE IF EXISTS `AU_PrimaryType`;
CREATE TABLE `AU_PrimaryType` (
  `RecordID` bigint(20) NOT NULL AUTO_INCREMENT,
  `TypeName` varchar(45) DEFAULT NULL,
  `ScanOrder` int(9) DEFAULT '10000',
  `Enable` int(9) DEFAULT NULL,
  `CTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `MTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`RecordID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of AU_PrimaryType
-- ----------------------------
INSERT INTO `AU_PrimaryType` VALUES ('1', '秦旸', '10000', '0', '2013-04-28 15:57:01', '2013-05-20 09:34:29');
