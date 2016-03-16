/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50610
Source Host           : 127.0.0.1:3306
Source Database       : DW_MetaData

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2013-05-20 12:37:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `AU_CalcFormula`
-- ----------------------------
DROP TABLE IF EXISTS `AU_CalcFormula`;
CREATE TABLE `AU_CalcFormula` (
  `RecordID` bigint(20) NOT NULL AUTO_INCREMENT,
  `FormulaTitle` varchar(100) DEFAULT NULL,
  `FormulaInterface` varchar(255) DEFAULT NULL,
  `FormulaRemark` varchar(255) DEFAULT NULL,
  `CTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `MTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`RecordID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of AU_CalcFormula
-- ----------------------------
INSERT INTO `AU_CalcFormula` VALUES ('1', '加', 'com.pingan.jrkj.datacenter.dataupdate.handle.calcformula.impl.Add', 'add()', null, null);
INSERT INTO `AU_CalcFormula` VALUES ('2', '减', 'com.pingan.jrkj.datacenter.dataupdate.handle.calcformula.impl.Subtract', 'subtract()', null, null);
INSERT INTO `AU_CalcFormula` VALUES ('3', '乘', 'com.pingan.jrkj.datacenter.dataupdate.handle.calcformula.impl.Multiply', 'multiply()', null, null);
INSERT INTO `AU_CalcFormula` VALUES ('4', '除', 'com.pingan.jrkj.datacenter.dataupdate.handle.calcformula.impl.Divide', 'divide()', null, null);
