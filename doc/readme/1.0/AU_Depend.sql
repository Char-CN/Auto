/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50610
Source Host           : 127.0.0.1:3306
Source Database       : DW_MetaData

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2013-05-20 12:36:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `AU_Depend`
-- ----------------------------
DROP TABLE IF EXISTS `AU_Depend`;
CREATE TABLE `AU_Depend` (
  `RecordID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PrimaryID` bigint(20) DEFAULT NULL,
  `SelectTable` varchar(100) DEFAULT NULL,
  `SelectField` varchar(255) DEFAULT NULL,
  `SelectWhere` varchar(255) DEFAULT NULL,
  `CTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `MTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`RecordID`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of AU_Depend
-- ----------------------------
INSERT INTO `AU_Depend` VALUES ('1', '1', 'test as t', '$B$ = (10 + sum(t.age) * count(t.age))', 'where t.id <= $ID$', null, null);
INSERT INTO `AU_Depend` VALUES ('2', '2', 'BigTest', '$A$ = (sum(IncrementPV) + sum(IncrementUV))###$ID$ = RecordID', 'where RecordID <= $RecordID$', null, null);
INSERT INTO `AU_Depend` VALUES ('3', '2', 'BigTest', '$B$ = (sum(IncrementPV) / sum(IncrementUV))', 'where RecordID <= $RecordID$', null, null);
INSERT INTO `AU_Depend` VALUES ('4', '3', 'DW_Warehouse.DW_USER_REG_FACTS', '$A$ = (sum(USER_COUNT_INCREMENT)) ### $B$ = (sum(USER_COUNT_INCREMENT))/(USER_COUNT_INCREMENT)', 'where PERIOD_KEY=3002 and DAY_KEY <= $DAYKEY$ and VISITOR_TYPE_KEY=$VISITORTYPEKEY$', null, null);
INSERT INTO `AU_Depend` VALUES ('100', '100', 'DW_Warehouse.DW_APP_ACTION_FACTS', '$A$ = NEW_USER_INCREMENT', 'where PERIOD_KEY=102 and DAY_KEY=$DAY_KEY$ and APP_VERSION_KEY = $APP_VERSION_KEY$ and PUBLISH_CHANNEL_KEY = $PUBLISH_CHANNEL_KEY$ order by day_key desc limit 0,1', '2013-04-27 16:56:06', '2013-04-28 09:22:44');
INSERT INTO `AU_Depend` VALUES ('101', '100', 'DW_Warehouse.DW_APP_ACTION_FACTS', '$B$ = NEW_USER_CUMULATE', 'where PERIOD_KEY=102 and DAY_KEY<$DAY_KEY$ and APP_VERSION_KEY = $APP_VERSION_KEY$ and PUBLISH_CHANNEL_KEY = $PUBLISH_CHANNEL_KEY$ order by day_key desc limit 0,1', '2013-04-27 18:11:16', '2013-04-28 07:40:23');
INSERT INTO `AU_Depend` VALUES ('102', '101', 'DW_Warehouse.DW_APP_ACTION_FACTS', '$RATIO$ = IFNULL(ACTIVE_USER / NEW_USER_CUMULATE, 0)', 'where PERIOD_KEY=102 and DAY_KEY=$DAY_KEY$ and APP_VERSION_KEY = $APP_VERSION_KEY$ and PUBLISH_CHANNEL_KEY = $PUBLISH_CHANNEL_KEY$ order by day_key desc limit 0,1', '2013-04-28 09:14:25', '2013-04-28 09:24:21');
