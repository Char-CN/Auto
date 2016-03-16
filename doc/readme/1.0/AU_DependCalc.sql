/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50610
Source Host           : 127.0.0.1:3306
Source Database       : DW_MetaData

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2013-05-20 12:36:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `AU_DependCalc`
-- ----------------------------
DROP TABLE IF EXISTS `AU_DependCalc`;
CREATE TABLE `AU_DependCalc` (
  `RecordID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DependID` bigint(20) DEFAULT NULL,
  `CalcFormulaID` bigint(20) DEFAULT NULL,
  `Placeholder` varchar(100) DEFAULT NULL,
  `Param` varchar(100) DEFAULT NULL,
  `CalcOrder` int(9) DEFAULT NULL,
  `Remark` varchar(255) DEFAULT NULL,
  `CTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `MTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`RecordID`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of AU_DependCalc
-- ----------------------------
INSERT INTO `AU_DependCalc` VALUES ('1', '1', '1', '$B$', '8', '1', '加2000', null, null);
INSERT INTO `AU_DependCalc` VALUES ('2', '1', '3', '$B$', '0.001', '2', '乘以3', null, null);
INSERT INTO `AU_DependCalc` VALUES ('3', '2', '1', '$A$', '100', '1', null, null, null);
INSERT INTO `AU_DependCalc` VALUES ('4', '2', '3', '$A$', '$B$', '2', null, null, '2013-04-27 13:00:04');
INSERT INTO `AU_DependCalc` VALUES ('5', '4', '1', '$A$', '100000', '1', '加十万', null, null);
INSERT INTO `AU_DependCalc` VALUES ('6', '-1', '4', '$B$', '$A$', '1', 'b除以a', null, null);
INSERT INTO `AU_DependCalc` VALUES ('100', '100', '1', '$A$', '$B$', '1', '加$B$', '2013-04-27 17:17:24', '2013-04-27 18:12:43');
