/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50610
Source Host           : 127.0.0.1:3306
Source Database       : DW_MetaData

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2013-05-20 12:36:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `AU_Primary`
-- ----------------------------
DROP TABLE IF EXISTS `AU_Primary`;
CREATE TABLE `AU_Primary` (
  `RecordID` bigint(20) NOT NULL AUTO_INCREMENT,
  `TypeID` bigint(20) DEFAULT NULL,
  `UpdateTable` varchar(100) DEFAULT NULL,
  `UpdateKey` varchar(255) DEFAULT NULL,
  `UpdateWhere` varchar(255) DEFAULT NULL,
  `UpdateField` varchar(255) DEFAULT NULL,
  `UpdateVar` varchar(255) DEFAULT NULL,
  `UpdateOrder` int(9) DEFAULT NULL,
  `Enable` int(9) DEFAULT NULL,
  `CTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `MTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`RecordID`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of AU_Primary
-- ----------------------------
INSERT INTO `AU_Primary` VALUES ('1', '1', 'test', 'id', 'where MTime<=now()', 'number=$B$', '$ID$ = id ###   $NUMBER$ = number', null, '0', null, '2013-04-28 15:58:45');
INSERT INTO `AU_Primary` VALUES ('2', '1', 'BigTest', 'RecordID', 'where RecordID <= 2', 'IncrementSUM = $A$,Percent = $B$', '$RecordID$ = RecordID', null, '0', null, '2013-04-28 16:15:07');
INSERT INTO `AU_Primary` VALUES ('3', '1', 'DW_Warehouse.DW_USER_REG_FACTS', 'VISITOR_TYPE_KEY###APP_KEY###REG_SOURCE_KEY###REG_CHANNEL_KEY###PERIOD_KEY###DAY_KEY', 'where PERIOD_KEY=3002', 'USER_COUNT_CUMULATED = $A$ ### Percent = $B$', '$DAYKEY$ = DAY_KEY ### $VISITORTYPEKEY$=VISITOR_TYPE_KEY', null, '0', null, '2013-04-28 15:58:46');
INSERT INTO `AU_Primary` VALUES ('100', '1', 'DW_Warehouse.DW_APP_ACTION_FACTS', 'APP_VERSION_KEY###PUBLISH_CHANNEL_KEY###PERIOD_KEY###DAY_KEY', 'where PERIOD_KEY=102', 'NEW_USER_CUMULATE = $A$', '$APP_VERSION_KEY$=APP_VERSION_KEY###$PUBLISH_CHANNEL_KEY$=PUBLISH_CHANNEL_KEY###$PERIOD_KEY$=PERIOD_KEY###$DAY_KEY$=DAY_KEY', '1', '1', '2013-04-27 16:30:51', '2013-05-02 09:58:17');
INSERT INTO `AU_Primary` VALUES ('101', '1', 'DW_Warehouse.DW_APP_ACTION_FACTS', 'APP_VERSION_KEY###PUBLISH_CHANNEL_KEY###PERIOD_KEY###DAY_KEY', 'where PERIOD_KEY=102', 'ACTIVE_RATIO=$RATIO$', '$APP_VERSION_KEY$=APP_VERSION_KEY###$PUBLISH_CHANNEL_KEY$=PUBLISH_CHANNEL_KEY###$PERIOD_KEY$=PERIOD_KEY###$DAY_KEY$=DAY_KEY', '2', '1', '2013-04-28 09:11:10', '2013-05-02 09:58:19');
