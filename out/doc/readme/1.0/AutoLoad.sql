/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50615
Source Host           : localhost:3306
Source Database       : DW_MetaData

Target Server Type    : MYSQL
Target Server Version : 50615
File Encoding         : 65001

Date: 2014-06-13 13:59:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `AL_InputFile`
-- ----------------------------
DROP TABLE IF EXISTS `AL_InputFile`;
CREATE TABLE `AL_InputFile` (
  `RecordID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `TypeID` bigint(20) DEFAULT NULL,
  `FileName` varchar(45) DEFAULT NULL COMMENT '文件显示名称',
  `FilePath` varchar(200) DEFAULT NULL,
  `FileRegExp` varchar(200) DEFAULT NULL COMMENT '文件名的正则表达式',
  `InputSql` text,
  `SplitSign` varchar(20) DEFAULT NULL,
  `Sort` tinyint(4) DEFAULT NULL,
  `Enable` int(9) DEFAULT NULL COMMENT '0为禁止;1为可用',
  `MTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`RecordID`)
) ENGINE=InnoDB AUTO_INCREMENT=146 DEFAULT CHARSET=utf8 COMMENT='asdasdasd';

-- ----------------------------
-- Records of AL_InputFile
-- ----------------------------
INSERT INTO `AL_InputFile` VALUES ('1', '2', '新一账通-cube（日）', '/home/www/data/dw/upload/user_regNew', 'toa_cube_d_.\\d{8}.csv', 'INSERT INTO DW_Warehouse.TOA_DM_USER_REG_FACTS(REG_SOURCE_KEY,USER_TYPE_KEY,REG_VERIFY_STATE_KEY,PERIOD_KEY,DAY_KEY,#UP#=USER_COUNT_INCREMENT) VALUES(#B#,#C#,#D#,102,#A#,#E#);', '	', null, '1', '2013-05-16 19:24:45', null);
INSERT INTO `AL_InputFile` VALUES ('2', '2', '新一账通-cube（月）', '/home/www/data/dw/upload/user_regNew', 'toa_cube_m_.\\d{6}.csv', 'INSERT INTO DW_Warehouse.TOA_DM_USER_REG_FACTS(REG_SOURCE_KEY,USER_TYPE_KEY,REG_VERIFY_STATE_KEY,PERIOD_KEY,DAY_KEY,#UP#=USER_COUNT_INCREMENT) VALUES(#B#,#C#,#D#,3002,#A#,#E#);', '	', null, '1', '2013-05-16 19:25:48', null);
INSERT INTO `AL_InputFile` VALUES ('3', '2', '新万里通-cube（日）', '/home/www/data/dw/upload/user_regNew', 'wlt_cube_d_.\\d{8}.csv', 'INSERT INTO DW_Warehouse.WLT_DM_USER_REG_FACTS(REG_SOURCE_KEY,PERIOD_KEY,DAY_KEY,#UP#=USER_COUNT_INCREMENT) VALUES(#B#,102,#A#,#C#);', '	', null, '1', '2013-05-16 18:59:28', null);
INSERT INTO `AL_InputFile` VALUES ('4', '2', '新万里通-cube（月）', '/home/www/data/dw/upload/user_regNew', 'wlt_cube_m_.\\d{6}.csv', 'INSERT INTO DW_Warehouse.WLT_DM_USER_REG_FACTS(REG_SOURCE_KEY,PERIOD_KEY,DAY_KEY,#UP#=USER_COUNT_INCREMENT) VALUES(#B#,3002,#A#,#C#);', '	', null, '1', '2013-05-16 19:09:56', null);
INSERT INTO `AL_InputFile` VALUES ('15', '2', '新一账通-仅一账通（日）', '/home/www/data/dw/upload/user_regNew', 'toa_t_d_.\\d{8}.csv', 'INSERT INTO DW_Warehouse.DW_USER_REG_FACTS(VISITOR_TYPE_KEY,APP_KEY,PERIOD_KEY,DAY_KEY,#UP#=USER_COUNT_INCREMENT) VALUES(4,0,102,#A#,#B#);', '	', null, '1', '2013-05-16 17:35:39', null);
INSERT INTO `AL_InputFile` VALUES ('16', '2', '新一账通-且万里通（日）', '/home/www/data/dw/upload/user_regNew', 'toa_t-w_d_.\\d{8}.csv', 'INSERT INTO DW_Warehouse.DW_USER_REG_FACTS(VISITOR_TYPE_KEY,APP_KEY,PERIOD_KEY,DAY_KEY,#UP#=USER_COUNT_INCREMENT) VALUES(12,0,102,#A#,#B#);', '	', null, '1', '2013-05-16 17:35:39', null);
INSERT INTO `AL_InputFile` VALUES ('17', '2', '新万里通-仅万里通（日）', '/home/www/data/dw/upload/user_regNew', 'wlt_w_d_.\\d{8}.csv', 'INSERT INTO DW_Warehouse.DW_USER_REG_FACTS(VISITOR_TYPE_KEY,APP_KEY,PERIOD_KEY,DAY_KEY,#UP#=USER_COUNT_INCREMENT) VALUES(8,1,102,#A#,#B#);', '	', null, '1', '2013-05-16 17:36:26', null);
INSERT INTO `AL_InputFile` VALUES ('18', '2', '新万里通-且一账通（日）', '/home/www/data/dw/upload/user_regNew', 'wlt_w-t_d_.\\d{8}.csv', 'INSERT INTO DW_Warehouse.DW_USER_REG_FACTS(VISITOR_TYPE_KEY,APP_KEY,PERIOD_KEY,DAY_KEY,#UP#=USER_COUNT_INCREMENT) VALUES(12,1,102,#A#,#B#);', '	', null, '1', '2013-05-16 17:36:26', null);
INSERT INTO `AL_InputFile` VALUES ('21', '2', '新一账通-仅一账通（月）', '/home/www/data/dw/upload/user_regNew', 'toa_t_m_.\\d{6}.csv', 'INSERT INTO DW_Warehouse.DW_USER_REG_FACTS(VISITOR_TYPE_KEY,APP_KEY,PERIOD_KEY,DAY_KEY,#UP#=USER_COUNT_INCREMENT) VALUES(4,0,3002,#A#,#B#);', '	', null, '1', '2013-05-16 17:35:39', null);
INSERT INTO `AL_InputFile` VALUES ('22', '2', '新一账通-且万里通（月）', '/home/www/data/dw/upload/user_regNew', 'toa_t-w_m_.\\d{6}.csv', 'INSERT INTO DW_Warehouse.DW_USER_REG_FACTS(VISITOR_TYPE_KEY,APP_KEY,PERIOD_KEY,DAY_KEY,#UP#=USER_COUNT_INCREMENT) VALUES(12,0,3002,#A#,#B#);', '	', null, '1', '2013-05-16 17:35:39', null);
INSERT INTO `AL_InputFile` VALUES ('23', '2', '新万里通-仅万里通（月）', '/home/www/data/dw/upload/user_regNew', 'wlt_w_m_.\\d{6}.csv', 'INSERT INTO DW_Warehouse.DW_USER_REG_FACTS(VISITOR_TYPE_KEY,APP_KEY,PERIOD_KEY,DAY_KEY,#UP#=USER_COUNT_INCREMENT) VALUES(8,1,3002,#A#,#B#);', '	', null, '1', '2013-05-16 17:36:26', null);
INSERT INTO `AL_InputFile` VALUES ('24', '2', '新万里通-先一账通（月）', '/home/www/data/dw/upload/user_regNew', 'wlt_t2w_m_.\\d{6}.csv', 'INSERT INTO DW_Warehouse.DW_USER_REG_FACTS(VISITOR_TYPE_KEY,APP_KEY,PERIOD_KEY,DAY_KEY,#UP#=USER_COUNT_INCREMENT) VALUES(1012,1,3002,#A#,#B#);', '	', null, '1', '2013-05-16 17:36:26', null);
INSERT INTO `AL_InputFile` VALUES ('25', '2', '新万里通-后一账通（月）', '/home/www/data/dw/upload/user_regNew', 'wlt_w2t_m_.\\d{6}.csv', 'INSERT INTO DW_Warehouse.DW_USER_REG_FACTS(VISITOR_TYPE_KEY,APP_KEY,PERIOD_KEY,DAY_KEY,#UP#=USER_COUNT_INCREMENT) VALUES(2012,1,3002,#A#,#B#);', '	', null, '1', '2013-05-16 17:36:26', null);
INSERT INTO `AL_InputFile` VALUES ('26', '2', '新一账通-先万里通（月）', '/home/www/data/dw/upload/user_regNew', 'toa_w2t_m_.\\d{6}.csv', 'INSERT INTO DW_Warehouse.DW_USER_REG_FACTS(VISITOR_TYPE_KEY,APP_KEY,PERIOD_KEY,DAY_KEY,#UP#=USER_COUNT_INCREMENT) VALUES(2012,0,3002,#A#,#B#);', '	', null, '1', '2013-05-16 17:35:39', null);
INSERT INTO `AL_InputFile` VALUES ('27', '2', '新一账通&万里通（月）', '/home/www/data/dw/upload/user_regNew', 'toa_wlt_m_.\\d{6}.csv', 'INSERT INTO DW_Warehouse.DW_USER_REG_FACTS(VISITOR_TYPE_KEY,APP_KEY,PERIOD_KEY,DAY_KEY,#UP#=USER_COUNT_INCREMENT) VALUES(0,-1,3002,#A#,#B#);', '	', null, '1', '2013-05-16 19:10:59', null);
INSERT INTO `AL_InputFile` VALUES ('30', '2', '活跃-仅一账通登陆-一（日）', '/home/www/data/dw/upload/user_interaction', 't_d_.\\d{8}.csv', 'INSERT INTO DW_Warehouse.DW_VISITOR_INTERACTION_FACTS(VISITOR_BRIDGE_KEY,APP_BRIDGE_KEY,ACTION_KEY,PERIOD_KEY,DAY_KEY,#UP#=USER_COUNT_INCREMENT) VALUES(5,1,1,102,#A#,#B#) ', '	', null, '1', '2013-05-12 14:47:19', null);
INSERT INTO `AL_InputFile` VALUES ('31', '2', '活跃-一登 & 万活-一&万（日） ', '/home/www/data/dw/upload/user_interaction', 'tAw_d_.\\d{8}.csv', 'INSERT INTO DW_Warehouse.DW_VISITOR_INTERACTION_FACTS(VISITOR_BRIDGE_KEY,APP_BRIDGE_KEY,ACTION_KEY,PERIOD_KEY,DAY_KEY,#UP#=USER_COUNT_INCREMENT) VALUES(2,1,1,102,#A#,#B#) ', '	', null, '1', '2013-05-12 14:47:19', null);
INSERT INTO `AL_InputFile` VALUES ('32', '2', '活跃-万活 非 一登-一&万（日）', '/home/www/data/dw/upload/user_interaction', 'w-t_d_.\\d{8}.csv', 'INSERT INTO DW_Warehouse.DW_VISITOR_INTERACTION_FACTS(VISITOR_BRIDGE_KEY,APP_BRIDGE_KEY,ACTION_KEY,PERIOD_KEY,DAY_KEY,#UP#=USER_COUNT_INCREMENT) VALUES(2,4,1,102,#A#,#B#) ', '	', null, '1', '2013-05-12 14:47:19', null);
INSERT INTO `AL_InputFile` VALUES ('33', '2', '活跃-万活-仅万（日）', '/home/www/data/dw/upload/user_interaction', 'w_d_.\\d{8}.csv', 'INSERT INTO DW_Warehouse.DW_VISITOR_INTERACTION_FACTS(VISITOR_BRIDGE_KEY,APP_BRIDGE_KEY,ACTION_KEY,PERIOD_KEY,DAY_KEY,#UP#=USER_COUNT_INCREMENT) VALUES(6,4,2,102,#A#,#B#) ', '	', null, '1', '2013-05-12 14:47:19', null);
INSERT INTO `AL_InputFile` VALUES ('34', '2', '活跃-万活-非一-13年之前注册（日）', '/home/www/data/dw/upload/user_interaction', 'w_nt_b13_d_.\\d{8}.csv', 'INSERT INTO DW_Warehouse.DW_VISITOR_INTERACTION_FACTS(VISITOR_BRIDGE_KEY,APP_BRIDGE_KEY,ACTION_KEY,PERIOD_KEY,DAY_KEY,#UP#=USER_COUNT_INCREMENT) VALUES(11,4,2,102,#A#,#B#) ', '	', null, '1', '2013-05-12 14:47:19', null);
INSERT INTO `AL_InputFile` VALUES ('35', '2', '活跃-万活-非一-13年新注册（日）', '/home/www/data/dw/upload/user_interaction', 'w_nt_a13_d_.\\d{8}.csv', 'INSERT INTO DW_Warehouse.DW_VISITOR_INTERACTION_FACTS(VISITOR_BRIDGE_KEY,APP_BRIDGE_KEY,ACTION_KEY,PERIOD_KEY,DAY_KEY,#UP#=USER_COUNT_INCREMENT) VALUES(7,4,2,102,#A#,#B#) ', '	', null, '1', '2013-05-12 14:47:19', null);
INSERT INTO `AL_InputFile` VALUES ('36', '2', '活跃-万活-非一-未注册（日）', '/home/www/data/dw/upload/user_interaction', 'w_nt_n_d_.\\d{8}.csv', 'INSERT INTO DW_Warehouse.DW_VISITOR_INTERACTION_FACTS(VISITOR_BRIDGE_KEY,APP_BRIDGE_KEY,ACTION_KEY,PERIOD_KEY,DAY_KEY,#UP#=USER_COUNT_INCREMENT) VALUES(10,4,2,102,#A#,#B#) ', '	', null, '1', '2013-05-12 14:47:19', null);
INSERT INTO `AL_InputFile` VALUES ('37', '2', '活跃-万活-一-13年之前注册（日）', '/home/www/data/dw/upload/user_interaction', 'w_t_b13_d_.\\d{8}.csv', 'INSERT INTO DW_Warehouse.DW_VISITOR_INTERACTION_FACTS(VISITOR_BRIDGE_KEY,APP_BRIDGE_KEY,ACTION_KEY,PERIOD_KEY,DAY_KEY,#UP#=USER_COUNT_INCREMENT) VALUES(12,4,2,102,#A#,#B#)', '	', null, '1', '2013-05-12 14:47:19', null);
INSERT INTO `AL_InputFile` VALUES ('38', '2', '活跃-万活-一-13年新注册（日）', '/home/www/data/dw/upload/user_interaction', 'w_t_a13_d_.\\d{8}.csv', 'INSERT INTO DW_Warehouse.DW_VISITOR_INTERACTION_FACTS(VISITOR_BRIDGE_KEY,APP_BRIDGE_KEY,ACTION_KEY,PERIOD_KEY,DAY_KEY,#UP#=USER_COUNT_INCREMENT) VALUES(3,4,2,102,#A#,#B#)', '	', null, '1', '2013-05-12 14:47:19', null);
INSERT INTO `AL_InputFile` VALUES ('39', '2', '活跃-万活-一-未注册（日）', '/home/www/data/dw/upload/user_interaction', 'w_t_n_d_.\\d{8}.csv', 'INSERT INTO DW_Warehouse.DW_VISITOR_INTERACTION_FACTS(VISITOR_BRIDGE_KEY,APP_BRIDGE_KEY,ACTION_KEY,PERIOD_KEY,DAY_KEY,#UP#=USER_COUNT_INCREMENT) VALUES(13,4,2,102,#A#,#B#)', '	', null, '1', '2013-05-12 14:47:19', null);
INSERT INTO `AL_InputFile` VALUES ('40', '2', '活跃-仅一账通登陆-一（月）', '/home/www/data/dw/upload/user_interaction', 't_m_.\\d{6}.csv', 'INSERT INTO DW_Warehouse.DW_VISITOR_INTERACTION_FACTS(VISITOR_BRIDGE_KEY,APP_BRIDGE_KEY,ACTION_KEY,PERIOD_KEY,DAY_KEY,#UP#=USER_COUNT_INCREMENT) VALUES(5,1,1,3002,#A#,#B#) ', '	', null, '1', '2013-05-12 14:47:19', null);
INSERT INTO `AL_InputFile` VALUES ('41', '2', '活跃-一登 & 万活-一&万（月） ', '/home/www/data/dw/upload/user_interaction', 'tAw_m_.\\d{6}.csv', 'INSERT INTO DW_Warehouse.DW_VISITOR_INTERACTION_FACTS(VISITOR_BRIDGE_KEY,APP_BRIDGE_KEY,ACTION_KEY,PERIOD_KEY,DAY_KEY,#UP#=USER_COUNT_INCREMENT) VALUES(2,1,1,3002,#A#,#B#) ', '	', null, '1', '2013-05-12 14:47:19', null);
INSERT INTO `AL_InputFile` VALUES ('42', '2', '活跃-万活 非 一登-一&万（月）', '/home/www/data/dw/upload/user_interaction', 'w-t_m_.\\d{6}.csv', 'INSERT INTO DW_Warehouse.DW_VISITOR_INTERACTION_FACTS(VISITOR_BRIDGE_KEY,APP_BRIDGE_KEY,ACTION_KEY,PERIOD_KEY,DAY_KEY,#UP#=USER_COUNT_INCREMENT) VALUES(2,4,1,3002,#A#,#B#) ', '	', null, '1', '2013-05-12 14:47:19', null);
INSERT INTO `AL_InputFile` VALUES ('43', '2', '活跃-万活-仅万（月）', '/home/www/data/dw/upload/user_interaction', 'w_m_.\\d{6}.csv', 'INSERT INTO DW_Warehouse.DW_VISITOR_INTERACTION_FACTS(VISITOR_BRIDGE_KEY,APP_BRIDGE_KEY,ACTION_KEY,PERIOD_KEY,DAY_KEY,#UP#=USER_COUNT_INCREMENT) VALUES(6,4,2,3002,#A#,#B#) ', '	', null, '1', '2013-05-12 14:47:19', null);
INSERT INTO `AL_InputFile` VALUES ('44', '2', '活跃-万活-非一-13年之前注册（月）', '/home/www/data/dw/upload/user_interaction', 'w_nt_b13_m_.\\d{6}.csv', 'INSERT INTO DW_Warehouse.DW_VISITOR_INTERACTION_FACTS(VISITOR_BRIDGE_KEY,APP_BRIDGE_KEY,ACTION_KEY,PERIOD_KEY,DAY_KEY,#UP#=USER_COUNT_INCREMENT) VALUES(11,4,2,3002,#A#,#B#) ', '	', null, '1', '2013-05-12 14:47:19', null);
INSERT INTO `AL_InputFile` VALUES ('45', '2', '活跃-万活-非一-13年新注册（月）', '/home/www/data/dw/upload/user_interaction', 'w_nt_a13_m_.\\d{6}.csv', 'INSERT INTO DW_Warehouse.DW_VISITOR_INTERACTION_FACTS(VISITOR_BRIDGE_KEY,APP_BRIDGE_KEY,ACTION_KEY,PERIOD_KEY,DAY_KEY,#UP#=USER_COUNT_INCREMENT) VALUES(7,4,2,3002,#A#,#B#) ', '	', null, '1', '2013-05-12 14:47:19', null);
INSERT INTO `AL_InputFile` VALUES ('46', '2', '活跃-万活-非一-未注册（月）', '/home/www/data/dw/upload/user_interaction', 'w_nt_n_m_.\\d{6}.csv', 'INSERT INTO DW_Warehouse.DW_VISITOR_INTERACTION_FACTS(VISITOR_BRIDGE_KEY,APP_BRIDGE_KEY,ACTION_KEY,PERIOD_KEY,DAY_KEY,#UP#=USER_COUNT_INCREMENT) VALUES(10,4,2,3002,#A#,#B#) ', '	', null, '1', '2013-05-12 14:47:19', null);
INSERT INTO `AL_InputFile` VALUES ('47', '2', '活跃-万活-一-13年之前注册（月）', '/home/www/data/dw/upload/user_interaction', 'w_t_b13_m_.\\d{6}.csv', 'INSERT INTO DW_Warehouse.DW_VISITOR_INTERACTION_FACTS(VISITOR_BRIDGE_KEY,APP_BRIDGE_KEY,ACTION_KEY,PERIOD_KEY,DAY_KEY,#UP#=USER_COUNT_INCREMENT) VALUES(12,4,2,3002,#A#,#B#)', '	', null, '1', '2013-05-12 14:47:19', null);
INSERT INTO `AL_InputFile` VALUES ('48', '2', '活跃-万活-一-13年新注册（月）', '/home/www/data/dw/upload/user_interaction', 'w_t_a13_m_.\\d{6}.csv', 'INSERT INTO DW_Warehouse.DW_VISITOR_INTERACTION_FACTS(VISITOR_BRIDGE_KEY,APP_BRIDGE_KEY,ACTION_KEY,PERIOD_KEY,DAY_KEY,#UP#=USER_COUNT_INCREMENT) VALUES(3,4,2,3002,#A#,#B#)', '	', null, '1', '2013-05-12 14:47:19', null);
INSERT INTO `AL_InputFile` VALUES ('49', '2', '活跃-万活-一-未注册（月）', '/home/www/data/dw/upload/user_interaction', 'w_t_n_m_.\\d{6}.csv', 'INSERT INTO DW_Warehouse.DW_VISITOR_INTERACTION_FACTS(VISITOR_BRIDGE_KEY,APP_BRIDGE_KEY,ACTION_KEY,PERIOD_KEY,DAY_KEY,#UP#=USER_COUNT_INCREMENT) VALUES(13,4,2,3002,#A#,#B#)', '	', null, '1', '2013-05-12 14:47:19', null);
INSERT INTO `AL_InputFile` VALUES ('59', '2', '淘宝客-订单－预估（日）', '/home/www/data/dw/upload/wltTaobaoke', 'order_all_d_.\\d{8}.csv', 'INSERT INTO DW_Warehouse.WLT_DM_REBATE_ORDER_FACTS(PARTNER_KEY,ORDER_STEP_KEY,PERIOD_KEY,DAY_KEY,#UP#=ORDER_COUNT,#UP#=ORDER_TOTAL) values (1,0,102,#A#,#B#,CONVERT(#C#,DECIMAL(20,3))*1000);', '	', null, '1', '2013-05-12 14:46:41', null);
INSERT INTO `AL_InputFile` VALUES ('60', '2', '淘宝客-订单－处理成功（日）', '/home/www/data/dw/upload/wltTaobaoke', 'done_sucess_d_.\\d{8}.csv', 'INSERT INTO DW_Warehouse.WLT_DM_REBATE_ORDER_FACTS(PARTNER_KEY,ORDER_STEP_KEY,PERIOD_KEY,DAY_KEY,#UP#=ORDER_COUNT,#UP#=ORDER_TOTAL,#UP#=ORDER_PAYMENT,#UP#=REBATE_TOTAL,#UP#=USER_COUNT) values (1,21,102,#A#,#B#,CONVERT(#C#,DECIMAL(20,3))*1000,CONVERT(#D#,DECIMAL(20,3))*1000,#E#,#F#);', '	', null, '1', '2013-05-12 14:46:41', null);
INSERT INTO `AL_InputFile` VALUES ('61', '2', '淘宝客-订单－处理异常（日）', '/home/www/data/dw/upload/wltTaobaoke', 'done_fail_d_.\\d{8}.csv', 'INSERT INTO DW_Warehouse.WLT_DM_REBATE_ORDER_FACTS(PARTNER_KEY,ORDER_STEP_KEY,PERIOD_KEY,DAY_KEY,#UP#=ORDER_COUNT,#UP#=ORDER_TOTAL,#UP#=ORDER_PAYMENT,#UP#=REBATE_TOTAL,#UP#=USER_COUNT) values (1,22,102,#A#,#B#,CONVERT(#C#,DECIMAL(20,3))*1000,CONVERT(#D#,DECIMAL(20,3))*1000,#E#,#F#);', '	', null, '1', '2013-05-12 14:46:41', null);
INSERT INTO `AL_InputFile` VALUES ('62', '2', '淘宝客-订单－在途（日）', '/home/www/data/dw/upload/wltTaobaoke', 'process_d_.\\d{8}.csv', 'INSERT INTO DW_Warehouse.WLT_DM_REBATE_ORDER_FACTS(PARTNER_KEY,ORDER_STEP_KEY,PERIOD_KEY,DAY_KEY,#UP#=ORDER_COUNT,#UP#=ORDER_TOTAL) values (1,10,102,#A#,#B#,CONVERT(#C#,DECIMAL(20,3))*1000);', '	', null, '1', '2013-05-12 14:46:41', null);
INSERT INTO `AL_InputFile` VALUES ('63', '2', '淘宝客-订单－完成（日）', '/home/www/data/dw/upload/wltTaobaoke', 'done_all_d_.\\d{8}.csv', 'INSERT INTO DW_Warehouse.WLT_DM_REBATE_ORDER_FACTS(PARTNER_KEY,ORDER_STEP_KEY,PERIOD_KEY,DAY_KEY,#UP#=USER_COUNT,#UP#=ORDER_COUNT,#UP#=ORDER_PAYMENT,#UP#=REBATE_TOTAL) values (1,20,102,#A#,#B#,#C#,CONVERT(#D#,DECIMAL(20,3))*1000,#E#);', '	', null, '1', '2013-05-12 14:46:41', null);
INSERT INTO `AL_InputFile` VALUES ('70', '2', '淘宝客-订单－处理成功（月）', '/home/www/data/dw/upload/wltTaobaoke', 'done_sucess_m_.\\d{6}.csv', 'INSERT INTO DW_Warehouse.WLT_DM_REBATE_ORDER_FACTS(PARTNER_KEY,ORDER_STEP_KEY,PERIOD_KEY,DAY_KEY,#UP#=ORDER_COUNT,#UP#=ORDER_TOTAL,#UP#=ORDER_PAYMENT,#UP#=REBATE_TOTAL,#UP#=USER_COUNT) values (1,21,3002,#A#,#B#,CONVERT(#C#,DECIMAL(20,3))*1000,CONVERT(#D#,DECIMAL(20,3))*1000,#E#,#F#);', '	', null, '1', '2013-05-12 14:46:41', null);
INSERT INTO `AL_InputFile` VALUES ('71', '2', '淘宝客-订单－处理异常（月）', '/home/www/data/dw/upload/wltTaobaoke', 'done_fail_m_.\\d{6}.csv', 'INSERT INTO DW_Warehouse.WLT_DM_REBATE_ORDER_FACTS(PARTNER_KEY,ORDER_STEP_KEY,PERIOD_KEY,DAY_KEY,#UP#=ORDER_COUNT,#UP#=ORDER_TOTAL,#UP#=ORDER_PAYMENT,#UP#=REBATE_TOTAL,#UP#=USER_COUNT) values (1,22,3002,#A#,#B#,CONVERT(#C#,DECIMAL(20,3))*1000,CONVERT(#D#,DECIMAL(20,3))*1000,#E#,#F#);', '	', null, '1', '2013-05-12 14:46:41', null);
INSERT INTO `AL_InputFile` VALUES ('72', '2', '淘宝客-订单－在途（月）', '/home/www/data/dw/upload/wltTaobaoke', 'process_m_.\\d{6}.csv', 'INSERT INTO DW_Warehouse.WLT_DM_REBATE_ORDER_FACTS(PARTNER_KEY,ORDER_STEP_KEY,PERIOD_KEY,DAY_KEY,#UP#=ORDER_COUNT,#UP#=ORDER_TOTAL) values (1,10,3002,#A#,#B#,CONVERT(#C#,DECIMAL(20,3))*1000);', '	', null, '1', '2013-05-12 14:46:41', null);
INSERT INTO `AL_InputFile` VALUES ('73', '2', '淘宝客-订单－完成（月）', '/home/www/data/dw/upload/wltTaobaoke', 'done_all_m_.\\d{6}.csv', 'INSERT INTO DW_Warehouse.WLT_DM_REBATE_ORDER_FACTS(PARTNER_KEY,ORDER_STEP_KEY,PERIOD_KEY,DAY_KEY,#UP#=USER_COUNT,#UP#=ORDER_COUNT,#UP#=ORDER_PAYMENT,#UP#=REBATE_TOTAL) values (1,20,3002,#A#,#B#,#C#,CONVERT(#D#,DECIMAL(20,3))*1000,#E#);', '	', null, '1', '2013-05-12 14:46:41', null);
INSERT INTO `AL_InputFile` VALUES ('80', '4', '移动APP-统计（日）', '/home/www/data/dw/upload/talkingData', 'daydata_\\d{4}_\\d{2}_\\d{2}.csv', 'INSERT INTO DW_Warehouse.`DW_APP_ACTION_FACTS`(APP_VERSION_KEY,PUBLISH_CHANNEL_KEY,PERIOD_KEY,DAY_KEY,#UP#=NEW_USER_INCREMENT,#UP#=NEW_USER_CUMULATE,#UP#=ACTIVE_USER,#UP#=ACTIVE_RATIO,#UP#=APP_OPEN_COUNT,#UP#=APP_USETIME,#UP#=APP_USETIME_PER_OPEN) VALUES(#A#,#D#,102,#E#,#F#,#G#,#H#,#I#,#J#,#K#,#L#);', '	', null, '1', '2013-05-18 15:31:58', null);
INSERT INTO `AL_InputFile` VALUES ('81', '4', '移动APP-统计（月）', '/home/www/data/dw/upload/talkingData', 'sum_month_daydata_\\d{4}_\\d{2}_\\d{2}.csv', 'INSERT INTO DW_Warehouse.`DW_APP_ACTION_FACTS`(APP_VERSION_KEY,PUBLISH_CHANNEL_KEY,PERIOD_KEY,DAY_KEY,#UP#=NEW_USER_INCREMENT,#UP#=NEW_USER_CUMULATE,#UP#=ACTIVE_USER,#UP#=ACTIVE_RATIO,#UP#=APP_OPEN_COUNT,#UP#=APP_USETIME,#UP#=APP_USETIME_PER_OPEN) VALUES(#A#,#D#,3002,#E#,#F#,#G#,#H#,#I#,#J#,#K#,#L#);', '	', null, '1', '2013-05-18 15:31:59', null);
INSERT INTO `AL_InputFile` VALUES ('100', '3', 'pv_uv_daily', '/home/www/data/reposys/upload/general', '\\w*.pv_uv.\\d{8}.csv', 'delete from BI_Data.pv_uv_daily where (date=#A# and domain=#B#);insert into BI_Data.pv_uv_daily(date,domain,pv,uv,ip,averageStayTime,jumpPercent,session,session_time) values(#A#,#B#,#C#,#D#,#E#,#F#,#G#,#H#,#I#);', '	', null, '1', '2013-05-13 11:16:53', null);
INSERT INTO `AL_InputFile` VALUES ('101', '3', 'pv_uv_hourly', '/home/www/data/reposys/upload/general', '\\w*.pv_uv_hourly.\\d*.\\d{8}.csv', 'delete from BI_Data.pv_uv_hourly where (date=#A# and domain=#B# and hour=#C#);insert into BI_Data.pv_uv_hourly(date,domain,hour,pv,uv,ip,session) values(#A#,#B#,#C#,#D#,#E#,#F#,#G#);', '	', null, '1', '2013-05-07 13:36:49', null);
INSERT INTO `AL_InputFile` VALUES ('102', '3', 'url_setting_detail', '/home/www/data/reposys/upload/general', '\\d*.\\d{8}.csv', 'delete from BI_Data.url_setting_detail where (date=#A# and setting_id=#B#);insert into BI_Data.url_setting_detail(date,setting_id,pv,uv,ip) values(#A#,#B#,#C#,#D#,#E#);', '	', null, '1', null, null);
INSERT INTO `AL_InputFile` VALUES ('103', '3', 'visit_depth', '/home/www/data/reposys/upload/general', '\\w*.visit_depth.\\d{8}.csv', 'delete from BI_Data.visit_depth where (vis_date=#A# and domain=#B# and vis_depth=#C#);insert into BI_Data.visit_depth(vis_date,domain,vis_depth,visit_count) values(#A#,#B#,#C#,#D#);', '	', null, '1', null, null);
INSERT INTO `AL_InputFile` VALUES ('104', '3', 'visit_page', '/home/www/data/reposys/upload/general', '\\w*.visit_pages.\\d{8}.csv', 'delete from BI_Data.visit_page where (vis_date=#A# and domain=#B# and page_count_enum=#C#);insert into BI_Data.visit_page(vis_date,domain,page_count_enum,visit_count) values(#A#,#B#,#C#,#D#);', '	', null, '1', null, null);
INSERT INTO `AL_InputFile` VALUES ('105', '3', 'visit_times', '/home/www/data/reposys/upload/general', '\\w*.visit_time_length.\\d{8}.csv', 'delete from BI_Data.visit_times where (vis_date=#A# and domain=#B# and vis_time=#C#);insert into BI_Data.visit_times(vis_date,domain,vis_time,vis_count) values(#A#,#B#,#C#,#D#);', '	', null, '1', null, null);
INSERT INTO `AL_InputFile` VALUES ('106', '3', 'visit_frequency', '/home/www/data/reposys/upload/general', '\\w*.visit_frequence.\\d{8}.csv', 'delete from BI_Data.visit_frequency where (vis_date=#A# and domain=#B# and vis_frequency=#C#);insert into BI_Data.visit_frequency(vis_date,domain,vis_frequency,vis_guest) values(#A#,#B#,#C#,#D#);', '	', null, '1', null, null);
INSERT INTO `AL_InputFile` VALUES ('111', '3', 'BI_Data.url_setting_detail', '/home/www/data/reposys/upload/24money', '\\d*.\\d{8}.csv', 'delete from BI_Data.url_setting_detail where (date=#A# and setting_id=#B#);insert into BI_Data.url_setting_detail(setting_id,date,pv,uv,ip) values(#B#,#A#,#C#,#D#,#E#);', '	', null, '0', null, null);
INSERT INTO `AL_InputFile` VALUES ('112', '3', 'BI_Data.pv_uv_daily', '/home/www/data/reposys/upload/24money', 'pv_uv.\\d{8}.csv', 'delete from BI_Data.pv_uv_daily where (date=#A# and domain=#B#);insert into BI_Data.pv_uv_daily(date,domain,pv,uv,ip,averageStayTime) values(#A#,#B#,#C#,#D#,#E#,#F#);', '	', null, '0', null, null);
INSERT INTO `AL_InputFile` VALUES ('113', '3', 'BI_Data.pv_uv_hourly', '/home/www/data/reposys/upload/24money', 'pv_uv_hourly.\\d*.\\d{8}.csv', 'delete from BI_Data.pv_uv_hourly where (date=#A# and hour=#B# and domain=#C#);insert into BI_Data.pv_uv_hourly(date,hour,domain,pv,uv,ip) values(#A#,#B#,#C#,#D#,#E#,#F#);', '	', null, '0', null, null);
INSERT INTO `AL_InputFile` VALUES ('114', '3', 'BI_Data.visit_depth', '/home/www/data/reposys/upload/24money', 'visit_depth.\\d{8}.csv', 'delete from BI_Data.visit_depth where (vis_date=#A# and domain=#B# and vis_depth=#C#); insert into BI_Data.visit_depth(vis_date,domain,vis_depth,visit_count) values(#A#,#B#,#C#,#D#);', '	', null, '0', null, null);
INSERT INTO `AL_InputFile` VALUES ('115', '3', 'BI_Data.visit_frequency', '/home/www/data/reposys/upload/24money', 'visit_frequence.\\d{8}.csv', 'delete from BI_Data.visit_frequency where (vis_date=#A# and domain=#B# and vis_frequency=#C#); insert into BI_Data.visit_frequency(vis_date,domain,vis_frequency,vis_guest) values(#A#,#B#,#C#,#D#);', '	', null, '0', null, null);
INSERT INTO `AL_InputFile` VALUES ('116', '3', 'BI_Data.visit_page', '/home/www/data/reposys/upload/24money', 'visit_pages.\\d{8}.csv', 'delete from BI_Data.visit_page where (vis_date=#A# and domain=#B# and page_count_enum=#C#); insert into BI_Data.visit_page(vis_date,domain,page_count_enum,visit_count) values(#A#,#B#,#C#,#D#);', '	', null, '0', null, null);
INSERT INTO `AL_InputFile` VALUES ('117', '3', 'BI_Data.visit_times', '/home/www/data/reposys/upload/24money', 'visit_time_length.\\d{8}.csv', 'delete from BI_Data.visit_times where (vis_date=#A# and domain=#B# and vis_time=#C#); insert into BI_Data.visit_times(vis_date,domain,vis_time,vis_count) values(#A#,#B#,#C#,#D#);', '	', null, '0', null, null);
INSERT INTO `AL_InputFile` VALUES ('118', '3', 'BI_Data.pv_uv_daily', '/home/www/data/reposys/upload/pahaoche/', 'pv_uv.\\d{8}.csv', 'delete from BI_Data.pv_uv_daily where (date=#A# and domain=#B#); insert into BI_Data.pv_uv_daily(date,domain,pv,uv,ip) values(#A#,#B#,#C#,#D#,#E#);', '	', null, '0', null, null);
INSERT INTO `AL_InputFile` VALUES ('119', '3', '24Money_PV_UV', '/home/www/data/dw/upload/24money', 'pv_uv.\\d{8}.csv', 'INSERT INTO DW_Warehouse.DW_VISITOR_PAGE_FACTS(#PK#=VISITOR_BRIDGE_KEY,#PK#=APP_KEY,#PK#=VISIT_PATH_STEP_KEY,#PK#=URL_PAGE_KEY,#PK#=URL_QUERY,#PK#=PERIOD_KEY,#PK#=DAY_KEY,#UP#=ACTION_COUNT_INCREMENT,#UP#=USER_COUNT_INCREMENT,#UP#=TERMINAL_COUNT_INCREMENT) VALUES(1,2,0,0,\'\',102,#A#,#B#,#C#,#D#);', '	', null, '1', '2013-05-12 14:46:41', null);
INSERT INTO `AL_InputFile` VALUES ('120', '3', '24Money_New_UV', '/home/www/data/dw/upload/24money', 'newCookieUser.\\d{8}.csv', 'INSERT INTO DW_Warehouse.DW_VISITOR_PAGE_FACTS(#PK#=VISITOR_BRIDGE_KEY,#PK#=APP_KEY,#PK#=VISIT_PATH_STEP_KEY,#PK#=URL_PAGE_KEY,#PK#=URL_QUERY,#PK#=PERIOD_KEY,#PK#=DAY_KEY,#UP#=USER_COUNT_INCREMENT) VALUES(8,2,0,0,\'\',102,#A#,#B#);', '	', null, '1', '2013-05-12 14:46:41', null);
INSERT INTO `AL_InputFile` VALUES ('121', '3', '24Money_Old_UV', '/home/www/data/dw/upload/24money', 'oldCookieUser.\\d{8}.csv', 'INSERT INTO DW_Warehouse.DW_VISITOR_PAGE_FACTS(#PK#=VISITOR_BRIDGE_KEY,#PK#=APP_KEY,#PK#=VISIT_PATH_STEP_KEY,#PK#=URL_PAGE_KEY,#PK#=URL_QUERY,#PK#=PERIOD_KEY,#PK#=DAY_KEY,#UP#=USER_COUNT_INCREMENT) VALUES(9,2,0,0,\'\',102,#A#,#B#);', '	', null, '1', '2013-05-12 14:46:41', null);
INSERT INTO `AL_InputFile` VALUES ('122', '3', '24Money_Refer_PV', '/home/www/data/dw/upload/24money', '\\d*.refer.\\d{8}.csv', 'INSERT INTO DW_Warehouse.DW_VISITOR_PAGE_FACTS(#PK#=VISITOR_BRIDGE_KEY,#PK#=APP_KEY,#PK#=VISIT_PATH_STEP_KEY,#PK#=URL_PAGE_KEY,#PK#=URL_QUERY,#PK#=PERIOD_KEY,#PK#=DAY_KEY,#UP#=ACTION_COUNT_INCREMENT,#UP#=USER_COUNT_INCREMENT,#UP#=TERMINAL_COUNT_INCREMENT) VALUES(1,2,1,#B#,#D#,102,#A#,#E#,#F#,#G#);', '	', null, '1', '2013-05-12 14:46:41', null);
INSERT INTO `AL_InputFile` VALUES ('123', '3', '24Money_URL_PV', '/home/www/data/dw/upload/24money', '\\d*.url.\\d{8}.csv', 'INSERT INTO DW_Warehouse.DW_VISITOR_PAGE_FACTS(#PK#=VISITOR_BRIDGE_KEY,#PK#=APP_KEY,#PK#=VISIT_PATH_STEP_KEY,#PK#=URL_PAGE_KEY,#PK#=URL_QUERY,#PK#=PERIOD_KEY,#PK#=DAY_KEY,#UP#=ACTION_COUNT_INCREMENT,#UP#=USER_COUNT_INCREMENT,#UP#=TERMINAL_COUNT_INCREMENT) VALUES(1,2,0,#B#,#D#,102,#A#,#E#,#F#,#G#);', '	', null, '1', '2013-05-12 14:46:41', null);
INSERT INTO `AL_InputFile` VALUES ('124', '3', 'newuser', '/home/www/data/reposys/upload/general', '\\w*.newuser.\\d{8}.csv', 'delete from BI_Data.newold_visitor where (date=#A# and visitor=#B# and domain=#C#);insert into BI_Data.newold_visitor(date,visitor,domain,pv,visitor_count,stay_time,visit_count,visit_page,dap_rate) values(#A#,#B#,#C#,#D#,#E#,#F#,#G#,#H#,#I#);', '	', null, '1', null, null);
INSERT INTO `AL_InputFile` VALUES ('125', '3', 'olduser', '/home/www/data/reposys/upload/general', '\\w*.olduser.\\d{8}.csv', 'delete from BI_Data.newold_visitor where (date=#A# and visitor=#B# and domain=#C#);insert into BI_Data.newold_visitor(date,visitor,domain,pv,visitor_count,stay_time,visit_count,visit_page,dap_rate) values(#A#,#B#,#C#,#D#,#E#,#F#,#G#,#H#,#I#);', '	', null, '1', null, null);
INSERT INTO `AL_InputFile` VALUES ('126', '3', 'new_cookie_user_domain', '/home/www/data/reposys/upload/general', '\\w*.new_cookie_user_domain.\\d{8}.csv', 'delete from BI_Data.newold_website where (date=#A# and visitor=#B# and domain=#C# and source_websit=#D#);insert into BI_Data.newold_website(date,visitor,domain,source_websit,pv) values(#A#,#B#,#C#,#D#,#E#);', '	', null, '1', null, null);
INSERT INTO `AL_InputFile` VALUES ('127', '3', 'old_cookie_user_domain', '/home/www/data/reposys/upload/general', '\\w*.old_cookie_user_domain.\\d{8}.csv', 'delete from BI_Data.newold_website where (date=#A# and visitor=#B# and domain=#C# and source_websit=#D#);insert into BI_Data.newold_website(date,visitor,domain,source_websit,pv) values(#A#,#B#,#C#,#D#,#E#);', '	', null, '1', null, null);
INSERT INTO `AL_InputFile` VALUES ('128', '3', 'new_cookie_user_entrance', '/home/www/data/reposys/upload/general', '\\w*.new_cookie_user_entrance.\\d{8}.csv', 'delete from BI_Data.newold_entrance where (date=#A# and visitor=#B# and domain=#C# and entrance_url=#D#);insert into BI_Data.newold_entrance(date,visitor,domain,entrance_url,visit_count) values(#A#,#B#,#C#,#D#,#E#);', '	', null, '1', null, null);
INSERT INTO `AL_InputFile` VALUES ('129', '3', 'old_cookie_user_entrance', '/home/www/data/reposys/upload/general', '\\w*.old_cookie_user_entrance.\\d{8}.csv', 'delete from BI_Data.newold_entrance where (date=#A# and visitor=#B# and domain=#C# and entrance_url=#D#);insert into BI_Data.newold_entrance(date,visitor,domain,entrance_url,visit_count) values(#A#,#B#,#C#,#D#,#E#);', '	', null, '1', null, null);
INSERT INTO `AL_InputFile` VALUES ('135', '3', 'net_screen', '/home/www/data/reposys/upload/general', '\\w*.net_screen.\\d{8}.csv', 'delete from BI_Data.net_screen where (date=#A# and domain=#B# and scr_resolution=#C#); insert into BI_Data.net_screen(date,domain,scr_resolution,pv,uv,ip) values(#A#,#B#,#C#,#D#,#E#,#F#);', '	', null, '1', null, null);
INSERT INTO `AL_InputFile` VALUES ('136', '3', 'net_provider', '/home/www/data/reposys/upload/general', '\\w*.net_provider.\\d{8}.csv', 'delete from BI_Data.net_provider where (date=#A# and domain=#B# and province=#C# and city=#D#); insert into BI_Data.net_provider(date,domain,province,city,pv,uv,ip,net_provid) values(#A#,#B#,#C#,#D#,#E#,#F#,#G#,0);', '	', null, '1', null, null);
INSERT INTO `AL_InputFile` VALUES ('137', '3', 'net_device', '/home/www/data/reposys/upload/general', '\\w*.net_device.\\d{8}.csv', 'delete from BI_Data.net_device where (date=#A# and domain=#B# and device_name=#C# and system_name=#D#); insert into BI_Data.net_device(date,domain,device_name,system_name,pv,uv,ip) values(#A#,#B#,#C#,#D#,#E#,#F#,#G#);', '	', null, '1', null, null);
INSERT INTO `AL_InputFile` VALUES ('138', '3', 'stat_proj_result', '/home/www/data/reposys/upload/loanpages', '\\d*.\\d*.\\d{8}.csv', 'delete from BI_Data.stat_proj_result where (date=#A# and proj_id=#B# and page_id=#D# and channel_id=#C#); insert into BI_Data.stat_proj_result(date,proj_id,page_id,channel_id,pv,uv) values(#A#,#B#,#D#,#C#,#E#,#F#);', '	', null, '1', null, null);
INSERT INTO `AL_InputFile` VALUES ('139', '3', 'tb_browser', '/home/www/data/reposys/upload/general', '\\w*.net_browser.\\d{8}.csv', 'delete from BI_Data.tb_browser where (date=#A# and domain=#B# and browser_name=#D# and port_name=#C#); insert into BI_Data.tb_browser(date,domain,port_name,browser_name,pv,uv,ip) values(#A#,#B#,#C#,#D#,#E#,#F#,#G#);', '	', null, '1', null, null);
INSERT INTO `AL_InputFile` VALUES ('141', '6', 'BI_Data.pv_uv_daily', '/home/www/data/dw/upload/webtrends', '\\w*_\\d{8}.csv', 'delete from BI_Data.pv_uv_daily where (date=#A# and domain=#B#);insert into BI_Data.pv_uv_daily(date,domain,pv,uv,ip,averageStayTime,jumpPercent,session,session_time) values(#A#,#B#,#C#,#D#,#E#,#F#,#G#,#H#,#I#);', '	', null, '1', '2013-05-17 10:21:11', null);
INSERT INTO `AL_InputFile` VALUES ('142', '6', 'BI_Data.pv_uv_hourly', '/home/www/data/dw/upload/webtrends', '\\w*_\\d{8}_hourly.csv', 'delete from BI_Data.pv_uv_hourly where (date=#A# and hour=#C# and domain=#B#);insert into BI_Data.pv_uv_hourly(date,hour,domain,pv,uv,ip) values(#A#,#C#,#B#,#D#,#E#,#F#);', '	', null, '1', '2013-05-17 13:36:08', null);
INSERT INTO `AL_InputFile` VALUES ('143', '7', '外部商圈', '/data/coreKPI/upload/dcds', '\\d+_wlt_e_m_.day.\\d{8}.csv', 'insert into DW_Warehouse.WLT_DM_PARTNER_TRANS_FACTS(PARTNER_SERVICE_KEY,PARTNER_SHOP_KEY,PARTNER_CHANNEL_KEY,PARTNER_GOODS_KEY,PAYMENT_TYPE_KEY,USER_TYPE_BRIDGE_KEY,PERIOD_KEY,DAY_KEY,#UP#=USER_COUNT,#UP#=TRANS_COUNT,#UP#=PAYMENT_AMOUNT,#UP#=PAYMENT_CASH_AMOUNT,#UP#=PAYMENT_POINT_AMOUNT,#UP#=COMMISSION_AMOUNT,#UP#=REBATE_POINT_AMOUNT)values(#A#,#B#,#C#,#D#,#E#,0,102,#F#,#G#,#H#,#I#,#J#,#K#,#L#,#M#);', '	', null, '1', '2013-05-22 17:14:27', null);
INSERT INTO `AL_InputFile` VALUES ('144', '7', '24Money', 'D:/data/', '24Money_\\w*.csv', 'insert into test(name,lastperiod,age,remark) values(#B#,#A#,#C#,#FILE_NAME#);', '\\t', '1', '1', '2014-05-14 17:09:34', null);
INSERT INTO `AL_InputFile` VALUES ('145', '20', '12', 'D:/data/', 'hbase_\\d*.csv', '#KEY#:#VAL#', '\\t', '2', '1', '2014-04-15 20:21:36', '2014-04-15 17:35:27');

-- ----------------------------
-- Table structure for `AL_InputFileLog`
-- ----------------------------
DROP TABLE IF EXISTS `AL_InputFileLog`;
CREATE TABLE `AL_InputFileLog` (
  `RecordID` bigint(20) NOT NULL AUTO_INCREMENT,
  `TypeID` bigint(20) DEFAULT NULL,
  `FileID` bigint(20) DEFAULT NULL,
  `RealFilePath` varchar(256) DEFAULT NULL,
  `RealFileName` varchar(128) DEFAULT NULL,
  `Result` varchar(16) DEFAULT NULL COMMENT '0.fail | 1.success',
  `Detail` text COMMENT '日志详情',
  `CTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`RecordID`),
  KEY `IDX_UL_SARUUC` (`CTime`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of AL_InputFileLog
-- ----------------------------
INSERT INTO `AL_InputFileLog` VALUES ('1', '7', '144', null, null, 'fail', 'com.pingan.jrkj.datacenter.dataload.exception.DirectoryNotFoundException: D:/data/1\\ is not directory', '2014-05-14 16:54:38');
INSERT INTO `AL_InputFileLog` VALUES ('2', '7', '144', null, null, 'fail', 'com.pingan.jrkj.datacenter.dataload.exception.DirectoryNotFoundException: D:/data/1\\ is not directory', '2014-05-14 16:57:41');
INSERT INTO `AL_InputFileLog` VALUES ('3', '7', '144', null, null, 'fail', 'com.pingan.jrkj.datacenter.dataload.exception.DirectoryNotFoundException: D:/data/1\\ is not directory', '2014-05-14 16:58:15');
INSERT INTO `AL_InputFileLog` VALUES ('4', '7', '144', 'D:/data/\\', '24Money_B.csv', 'fail', 'java.lang.RuntimeException: java.lang.RuntimeException: org.springframework.jdbc.BadSqlGrammarException: StatementCallback; bad SQL grammar [insert into test(name,lastperiod,age,remark) values(\'\'哈\\\'哈\\\'\',\'102\',\'11\',\'B\');]; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'å“ˆ\\\'å“ˆ\\\'\',\'102\',\'11\',\'B\')\' at line 1\r\n at com.pingan.jrkj.datacenter.dataload.service.ALService.insertInputFile(ALService.java:65)\r\n at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.handle(ALHandle.java:181)\r\n at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.main(ALHandle.java:298)', '2014-05-14 17:07:31');
INSERT INTO `AL_InputFileLog` VALUES ('5', '7', '144', 'D:/data/\\', '24Money_L.csv', 'fail', 'com.pingan.jrkj.datacenter.dataload.exception.FileEmptyException: the file is empty\r\n at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.handle(ALHandle.java:151)\r\n at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.main(ALHandle.java:298)', '2014-05-14 17:07:31');
INSERT INTO `AL_InputFileLog` VALUES ('6', '7', '144', 'D:/data/\\', '24Money_B.csv', 'success', '', '2014-05-14 17:10:02');
INSERT INTO `AL_InputFileLog` VALUES ('7', '7', '144', 'D:/data/\\', '24Money_L.csv', 'fail', 'com.pingan.jrkj.datacenter.dataload.exception.FileEmptyException: the file is empty\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.handle(ALHandle.java:151)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.main(ALHandle.java:298)', '2014-05-14 17:10:02');
INSERT INTO `AL_InputFileLog` VALUES ('8', '7', '144', 'D:/data/\\', '24Money_B.csv', 'success', '', '2014-05-14 19:14:13');
INSERT INTO `AL_InputFileLog` VALUES ('9', '7', '144', 'D:/data/\\', '24Money_L.csv', 'fail', 'com.pingan.jrkj.datacenter.dataload.exception.FileEmptyException: the file is empty\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.handle(ALHandle.java:151)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.main(ALHandle.java:316)', '2014-05-14 19:14:13');
INSERT INTO `AL_InputFileLog` VALUES ('10', '7', '144', 'D:/data/\\', '24Money_B.csv', 'fail', 'java.lang.RuntimeException: java.lang.RuntimeException: org.springframework.jdbc.UncategorizedSQLException: StatementCallback; uncategorized SQLException for SQL [insert into test(name,lastperiod,age,remark) values(\'哈hyyhyy哈test2\',\'702\',\'\',\'B\');]; SQL state [HY000]; error code [1366]; Incorrect integer value: \'\' for column \'age\' at row 1; nested exception is java.sql.SQLException: Incorrect integer value: \'\' for column \'age\' at row 1\r\n    at com.pingan.jrkj.datacenter.dataload.service.ALService.insertInputFile(ALService.java:65)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.handle(ALHandle.java:181)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.main(ALHandle.java:316)', '2014-05-14 19:16:09');
INSERT INTO `AL_InputFileLog` VALUES ('11', '7', '144', 'D:/data/\\', '24Money_L.csv', 'fail', 'com.pingan.jrkj.datacenter.dataload.exception.FileEmptyException: the file is empty\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.handle(ALHandle.java:151)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.main(ALHandle.java:316)', '2014-05-14 19:16:09');
INSERT INTO `AL_InputFileLog` VALUES ('12', '7', '144', 'D:/data/\\', '24Money_B.csv', 'fail', 'java.lang.RuntimeException: java.lang.RuntimeException: org.springframework.jdbc.UncategorizedSQLException: StatementCallback; uncategorized SQLException for SQL [insert into test(name,lastperiod,age,remark) values(\'哈hyyhyy哈test2\',\'702\',\'\',\'B\');]; SQL state [HY000]; error code [1366]; Incorrect integer value: \'\' for column \'age\' at row 1; nested exception is java.sql.SQLException: Incorrect integer value: \'\' for column \'age\' at row 1\r\n    at com.pingan.jrkj.datacenter.dataload.service.ALService.insertInputFile(ALService.java:65)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.handle(ALHandle.java:181)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.main(ALHandle.java:316)', '2014-05-14 19:16:24');
INSERT INTO `AL_InputFileLog` VALUES ('13', '7', '144', 'D:/data/\\', '24Money_L.csv', 'fail', 'com.pingan.jrkj.datacenter.dataload.exception.FileEmptyException: the file is empty\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.handle(ALHandle.java:151)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.main(ALHandle.java:316)', '2014-05-14 19:16:24');
INSERT INTO `AL_InputFileLog` VALUES ('14', '7', '144', 'D:/data/\\', '24Money_B.csv', 'fail', 'java.lang.RuntimeException: java.lang.RuntimeException: org.springframework.jdbc.UncategorizedSQLException: StatementCallback; uncategorized SQLException for SQL [insert into test(name,lastperiod,age,remark) values(\'哈hyyhyy哈test2\',\'702\',\'\',\'B\');]; SQL state [HY000]; error code [1366]; Incorrect integer value: \'\' for column \'age\' at row 1; nested exception is java.sql.SQLException: Incorrect integer value: \'\' for column \'age\' at row 1\r\n    at com.pingan.jrkj.datacenter.dataload.service.ALService.insertInputFile(ALService.java:65)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.handle(ALHandle.java:181)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.main(ALHandle.java:316)', '2014-05-14 19:16:33');
INSERT INTO `AL_InputFileLog` VALUES ('15', '7', '144', 'D:/data/\\', '24Money_L.csv', 'fail', 'com.pingan.jrkj.datacenter.dataload.exception.FileEmptyException: the file is empty\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.handle(ALHandle.java:151)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.main(ALHandle.java:316)', '2014-05-14 19:16:33');
INSERT INTO `AL_InputFileLog` VALUES ('16', '7', '144', 'D:/data/\\', '24Money_B.csv', 'fail', 'java.lang.RuntimeException: java.lang.RuntimeException: org.springframework.jdbc.UncategorizedSQLException: StatementCallback; uncategorized SQLException for SQL [insert into test(name,lastperiod,age,remark) values(\'哈hyyhyy哈test2\',\'702\',\'\',\'B\');]; SQL state [HY000]; error code [1366]; Incorrect integer value: \'\' for column \'age\' at row 1; nested exception is java.sql.SQLException: Incorrect integer value: \'\' for column \'age\' at row 1\r\n    at com.pingan.jrkj.datacenter.dataload.service.ALService.insertInputFile(ALService.java:65)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.handle(ALHandle.java:181)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.main(ALHandle.java:316)', '2014-05-14 19:30:05');
INSERT INTO `AL_InputFileLog` VALUES ('17', '7', '144', 'D:/data/\\', '24Money_L.csv', 'fail', 'com.pingan.jrkj.datacenter.dataload.exception.FileEmptyException: the file is empty\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.handle(ALHandle.java:151)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.main(ALHandle.java:316)', '2014-05-14 19:30:05');
INSERT INTO `AL_InputFileLog` VALUES ('18', '7', '144', 'D:/data/\\', '24Money_B.csv', 'fail', 'java.lang.RuntimeException: java.lang.RuntimeException: org.springframework.jdbc.UncategorizedSQLException: StatementCallback; uncategorized SQLException for SQL [insert into test(name,lastperiod,age,remark) values(\'哈hyyhyy哈test2\',\'702\',\'\',\'B\');]; SQL state [HY000]; error code [1366]; Incorrect integer value: \'\' for column \'age\' at row 1; nested exception is java.sql.SQLException: Incorrect integer value: \'\' for column \'age\' at row 1\r\n    at com.pingan.jrkj.datacenter.dataload.service.ALService.insertInputFile(ALService.java:65)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.handle(ALHandle.java:181)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.main(ALHandle.java:316)', '2014-05-14 19:30:45');
INSERT INTO `AL_InputFileLog` VALUES ('19', '7', '144', 'D:/data/\\', '24Money_L.csv', 'fail', 'com.pingan.jrkj.datacenter.dataload.exception.FileEmptyException: the file is empty\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.handle(ALHandle.java:151)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.main(ALHandle.java:316)', '2014-05-14 19:30:45');
INSERT INTO `AL_InputFileLog` VALUES ('20', '7', '144', 'D:/data/\\', '24Money_B.csv', 'fail', 'java.lang.RuntimeException: java.lang.RuntimeException: org.springframework.jdbc.UncategorizedSQLException: StatementCallback; uncategorized SQLException for SQL [insert into test(name,lastperiod,age,remark) values(\'哈hyyhyy哈test2\',\'702\',\'\',\'B\');]; SQL state [HY000]; error code [1366]; Incorrect integer value: \'\' for column \'age\' at row 1; nested exception is java.sql.SQLException: Incorrect integer value: \'\' for column \'age\' at row 1\r\n    at com.pingan.jrkj.datacenter.dataload.service.ALService.insertInputFile(ALService.java:65)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.handle(ALHandle.java:181)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.main(ALHandle.java:316)', '2014-05-14 19:32:58');
INSERT INTO `AL_InputFileLog` VALUES ('21', '7', '144', 'D:/data/\\', '24Money_L.csv', 'fail', 'com.pingan.jrkj.datacenter.dataload.exception.FileEmptyException: the file is empty\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.handle(ALHandle.java:151)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.main(ALHandle.java:316)', '2014-05-14 19:32:58');
INSERT INTO `AL_InputFileLog` VALUES ('22', '7', '144', 'D:/data/\\', '24Money_B.csv', 'fail', 'java.lang.RuntimeException: java.lang.RuntimeException: org.springframework.jdbc.UncategorizedSQLException: StatementCallback; uncategorized SQLException for SQL [insert into test(name,lastperiod,age,remark) values(\'哈hyyhyy哈test2\',\'702\',\'null\',\'B\');]; SQL state [HY000]; error code [1366]; Incorrect integer value: \'null\' for column \'age\' at row 1; nested exception is java.sql.SQLException: Incorrect integer value: \'null\' for column \'age\' at row 1\r\n    at com.pingan.jrkj.datacenter.dataload.service.ALService.insertInputFile(ALService.java:65)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.handle(ALHandle.java:181)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.main(ALHandle.java:316)', '2014-05-14 19:34:41');
INSERT INTO `AL_InputFileLog` VALUES ('23', '7', '144', 'D:/data/\\', '24Money_L.csv', 'fail', 'com.pingan.jrkj.datacenter.dataload.exception.FileEmptyException: the file is empty\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.handle(ALHandle.java:151)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.main(ALHandle.java:316)', '2014-05-14 19:34:41');
INSERT INTO `AL_InputFileLog` VALUES ('24', '7', '144', 'D:/data/\\', '24Money_B.csv', 'success', '', '2014-05-14 19:35:20');
INSERT INTO `AL_InputFileLog` VALUES ('25', '7', '144', 'D:/data/\\', '24Money_L.csv', 'fail', 'com.pingan.jrkj.datacenter.dataload.exception.FileEmptyException: the file is empty\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.handle(ALHandle.java:151)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.main(ALHandle.java:316)', '2014-05-14 19:35:20');
INSERT INTO `AL_InputFileLog` VALUES ('26', '7', '144', 'D:/data/\\', '24Money_B.csv', 'success', '', '2014-05-14 19:36:19');
INSERT INTO `AL_InputFileLog` VALUES ('27', '7', '144', 'D:/data/\\', '24Money_L.csv', 'fail', 'com.pingan.jrkj.datacenter.dataload.exception.FileEmptyException: the file is empty\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.handle(ALHandle.java:151)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.main(ALHandle.java:316)', '2014-05-14 19:36:19');
INSERT INTO `AL_InputFileLog` VALUES ('28', '7', '144', 'D:/data/\\', '24Money_L.csv', 'success', '', '2014-05-14 19:38:35');
INSERT INTO `AL_InputFileLog` VALUES ('29', '7', '144', 'D:/data/\\', '24Money_L.csv', 'success', '', '2014-05-14 19:39:05');
INSERT INTO `AL_InputFileLog` VALUES ('30', '7', '144', 'D:/data/\\', '24Money_L.csv', 'success', '', '2014-05-14 19:39:26');
INSERT INTO `AL_InputFileLog` VALUES ('31', '7', '144', 'D:/data/\\', '24Money_L.csv', 'success', '', '2014-05-14 19:39:37');
INSERT INTO `AL_InputFileLog` VALUES ('32', '7', '144', 'D:/data/\\', '24Money_L.csv', 'fail', 'java.lang.NullPointerException\r\n    at com.pingan.jrkj.datacenter.dataload.service.ALService.setConvertDimsSqls(ALService.java:419)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.handle(ALHandle.java:164)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.main(ALHandle.java:320)', '2014-05-14 21:03:10');
INSERT INTO `AL_InputFileLog` VALUES ('33', '7', '144', 'D:/data/\\', '24Money_L.csv', 'fail', 'java.lang.NullPointerException\r\n    at com.pingan.jrkj.datacenter.dataload.service.ALService.setConvertDimsSqls(ALService.java:419)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.handle(ALHandle.java:164)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.main(ALHandle.java:320)', '2014-05-14 21:03:26');
INSERT INTO `AL_InputFileLog` VALUES ('34', '7', '144', 'D:/data/\\', '24Money_L.csv', 'fail', 'java.lang.NullPointerException\r\n    at com.pingan.jrkj.datacenter.dataload.service.ALService.setConvertDimsSqls(ALService.java:419)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.handle(ALHandle.java:164)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.main(ALHandle.java:320)', '2014-05-14 21:03:52');
INSERT INTO `AL_InputFileLog` VALUES ('35', '7', '144', 'D:/data/\\', '24Money_L.csv', 'fail', 'java.lang.NullPointerException\r\n    at java.lang.String.replace(Unknown Source)\r\n    at com.pingan.jrkj.datacenter.dataload.service.ALService.setConvertDimsSqls(ALService.java:424)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.handle(ALHandle.java:164)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.main(ALHandle.java:320)', '2014-05-14 21:04:54');
INSERT INTO `AL_InputFileLog` VALUES ('36', '7', '144', 'D:/data/\\', '24Money_L.csv', 'fail', 'java.lang.NullPointerException\r\n    at java.lang.String.replace(Unknown Source)\r\n    at com.pingan.jrkj.datacenter.dataload.service.ALService.setConvertDimsSqls(ALService.java:424)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.handle(ALHandle.java:164)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.main(ALHandle.java:320)', '2014-05-14 21:05:27');
INSERT INTO `AL_InputFileLog` VALUES ('37', '7', '144', 'D:/data/\\', '24Money_L.csv', 'fail', 'java.lang.NullPointerException\r\n    at java.lang.String.replace(Unknown Source)\r\n    at com.pingan.jrkj.datacenter.dataload.service.ALService.setConvertDimsSqls(ALService.java:424)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.handle(ALHandle.java:164)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.main(ALHandle.java:320)', '2014-05-14 21:06:13');
INSERT INTO `AL_InputFileLog` VALUES ('38', '7', '144', 'D:/data/\\', '24Money_L.csv', 'fail', 'java.lang.NullPointerException\r\n    at java.lang.String.replace(Unknown Source)\r\n    at com.pingan.jrkj.datacenter.dataload.service.ALService.setConvertDimsSqls(ALService.java:427)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.handle(ALHandle.java:164)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.main(ALHandle.java:320)', '2014-05-14 21:06:41');
INSERT INTO `AL_InputFileLog` VALUES ('39', '7', '144', 'D:/data/\\', '24Money_L.csv', 'fail', 'java.lang.NullPointerException\r\n    at java.lang.String.replace(Unknown Source)\r\n    at com.pingan.jrkj.datacenter.dataload.service.ALService.setConvertDimsSqls(ALService.java:427)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.handle(ALHandle.java:164)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.main(ALHandle.java:320)', '2014-05-14 21:07:01');
INSERT INTO `AL_InputFileLog` VALUES ('40', '7', '144', 'D:/data/\\', '24Money_L.csv', 'fail', 'java.lang.NullPointerException\r\n    at java.lang.String.replace(Unknown Source)\r\n    at com.pingan.jrkj.datacenter.dataload.service.ALService.setConvertDimsSqls(ALService.java:427)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.handle(ALHandle.java:164)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.main(ALHandle.java:320)', '2014-05-14 21:08:06');
INSERT INTO `AL_InputFileLog` VALUES ('41', '7', '144', 'D:/data/\\', '24Money_L.csv', 'fail', 'java.lang.RuntimeException: java.lang.RuntimeException: org.springframework.jdbc.BadSqlGrammarException: StatementCallback; bad SQL grammar [INSERT INTO DW_MetaData.TTT(,,) VALUES(,,)]; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \',) VALUES(,,)\' at line 1\r\n    at com.pingan.jrkj.datacenter.dataload.service.ALService.insertDim(ALService.java:108)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.handle(ALHandle.java:168)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.main(ALHandle.java:320)', '2014-05-14 21:08:22');
INSERT INTO `AL_InputFileLog` VALUES ('42', '7', '144', 'D:/data/\\', '24Money_L.csv', 'fail', 'java.lang.RuntimeException: java.lang.RuntimeException: org.springframework.jdbc.BadSqlGrammarException: StatementCallback; bad SQL grammar [INSERT INTO DW_MetaData.TTT(A1,A2,A3) VALUES(#A#,#E#,#D#)]; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'\' at line 1\r\n    at com.pingan.jrkj.datacenter.dataload.service.ALService.insertDim(ALService.java:108)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.handle(ALHandle.java:168)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.main(ALHandle.java:320)', '2014-05-14 21:09:43');
INSERT INTO `AL_InputFileLog` VALUES ('43', '7', '144', 'D:/data/\\', '24Money_L.csv', 'success', '', '2014-05-14 21:11:00');
INSERT INTO `AL_InputFileLog` VALUES ('44', '7', '144', 'D:/data/\\', '24Money_L.csv', 'success', '', '2014-05-14 21:11:48');
INSERT INTO `AL_InputFileLog` VALUES ('45', '7', '144', 'D:/data/\\', '24Money_L.csv', 'success', '', '2014-05-14 21:12:10');
INSERT INTO `AL_InputFileLog` VALUES ('46', '7', '144', 'D:/data/\\', '24Money_L.csv', 'success', '', '2014-05-14 21:17:59');
INSERT INTO `AL_InputFileLog` VALUES ('47', '7', '144', 'D:/data/\\', '24Money_L.csv', 'success', '', '2014-05-14 21:18:56');
INSERT INTO `AL_InputFileLog` VALUES ('48', '7', '144', 'D:/data/\\', '24Money_L.csv', 'success', '', '2014-05-15 10:25:02');
INSERT INTO `AL_InputFileLog` VALUES ('49', '7', '144', 'D:/data/\\', '24Money_L.csv', 'fail', 'java.lang.NullPointerException\r\n    at java.lang.String.replace(Unknown Source)\r\n    at com.pingan.jrkj.datacenter.dataload.service.ALService.setConvertDimsSqls(ALService.java:439)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.handle(ALHandle.java:164)\r\n    at com.pingan.jrkj.datacenter.dataload.handle.ALHandle.main(ALHandle.java:320)', '2014-05-15 10:25:26');
INSERT INTO `AL_InputFileLog` VALUES ('50', '7', '144', 'D:/data/\\', '24Money_L.csv', 'success', '', '2014-05-15 10:26:53');
INSERT INTO `AL_InputFileLog` VALUES ('51', '7', '144', 'D:/data/\\', '24Money_L.csv', 'success', '', '2014-05-15 10:27:03');
INSERT INTO `AL_InputFileLog` VALUES ('52', '7', '144', 'D:/data/\\', '24Money_L.csv', 'success', '', '2014-05-15 10:29:06');
INSERT INTO `AL_InputFileLog` VALUES ('53', '7', '144', 'D:/data/\\', '24Money_L.csv', 'success', '', '2014-05-15 15:05:21');
INSERT INTO `AL_InputFileLog` VALUES ('54', '7', '144', 'D:/data/\\', '24Money_L.csv', 'success', '', '2014-05-15 15:05:39');

-- ----------------------------
-- Table structure for `AL_InputSqlField`
-- ----------------------------
DROP TABLE IF EXISTS `AL_InputSqlField`;
CREATE TABLE `AL_InputSqlField` (
  `RecordID` bigint(20) NOT NULL AUTO_INCREMENT,
  `FileID` bigint(20) DEFAULT NULL,
  `Synch` int(9) DEFAULT NULL,
  `FieldPointName` varchar(20) DEFAULT NULL COMMENT '对应的SQL占位符;格式:#A#',
  `SqlKey` varchar(100) DEFAULT NULL COMMENT 'Sql中关联的key',
  `SqlValue` varchar(100) DEFAULT NULL COMMENT 'Sql中对应的Value',
  `TableName` varchar(100) DEFAULT NULL COMMENT '对应的表名',
  `DefaultValue` varchar(20) DEFAULT NULL COMMENT '该列为空字符串是默认值',
  `OldFormat` varchar(256) DEFAULT NULL,
  `NewFormat` varchar(256) DEFAULT NULL,
  `LimitLength` varchar(20) DEFAULT NULL,
  `Sort` tinyint(4) DEFAULT NULL,
  `MTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`RecordID`)
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of AL_InputSqlField
-- ----------------------------
INSERT INTO `AL_InputSqlField` VALUES ('8', '122', '1', '#B#', 'URL_HOST:#B#,URL_PATH:#C#', 'URL_PAGE_KEY', 'DW_Warehouse.DW_URL_PAGE', null, null, null, null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('9', '122', '0', '#D#', null, null, null, '#EMPTY#', null, null, ',321', null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('10', '122', '0', '#C#', null, null, null, '#EMPTY#', null, null, null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('11', '123', '1', '#B#', 'URL_HOST:#B#,URL_PATH:#C#', 'URL_PAGE_KEY', 'DW_Warehouse.DW_URL_PAGE', null, null, null, null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('12', '123', '0', '#D#', null, null, null, '#EMPTY#', null, null, ',321', null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('13', '123', '0', '#C#', null, null, null, '#EMPTY#', null, null, null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('14', '15', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('15', '16', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('16', '17', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('17', '18', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('19', '3', '1', '#B#', 'REG_SOURCE_ID', 'REG_SOURCE_KEY', 'DW_Warehouse.WLT_DM_USER_REG_SOURCE', '0000000000000000', null, null, null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('20', '3', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('21', '21', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('22', '22', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('23', '23', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('24', '24', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('25', '25', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('26', '26', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('27', '27', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('31', '30', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('32', '31', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('33', '32', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('34', '33', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('35', '34', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('36', '35', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('37', '36', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('38', '37', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('39', '38', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('40', '39', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('41', '40', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('42', '41', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('43', '42', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('44', '43', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('45', '44', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('46', '45', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('47', '46', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('48', '47', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('49', '48', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('50', '49', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('56', '60', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('57', '61', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('58', '62', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('59', '63', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('60', '59', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('61', '70', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('62', '71', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('63', '72', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('64', '73', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('65', '80', '1', '#A#', 'APP_PLATFORM_CODE:#A#,PLATFORM_CODE:#B#,APP_VERSION:#C#', 'APP_VERSION_KEY', 'DW_Warehouse.DW_APP_VERSION', null, null, null, null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('66', '80', '1', '#D#', 'CHANNEL_CODE', 'PUBLISH_CHANNEL_KEY', 'DW_Warehouse.DW_PUBLISH_CHANNEL', '-1', null, null, null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('67', '80', '0', '#E#', null, null, null, null, 'yyMMddHH', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('68', '80', '0', '#C#', null, null, null, '#NULL_STR#', null, null, null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('69', '81', '1', '#A#', 'APP_PLATFORM_CODE:#A#,PLATFORM_CODE:#B#,APP_VERSION:#C#', 'APP_VERSION_KEY', 'DW_Warehouse.DW_APP_VERSION', null, null, null, null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('70', '81', '1', '#D#', 'CHANNEL_CODE', 'PUBLISH_CHANNEL_KEY', 'DW_Warehouse.DW_PUBLISH_CHANNEL', null, null, null, null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('71', '81', '0', '#E#', null, null, null, '-1', 'yyyyMMdd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('72', '81', '0', '#C#', null, null, null, '#NULL_STR#', null, null, null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('101', '2', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('103', '1', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('104', '1', '1', '#C#', 'USER_TYPE_ID', 'USER_TYPE_KEY', 'DW_Warehouse.TOA_DM_USER_TYPE', null, null, null, null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('105', '2', '1', '#C#', 'USER_TYPE_ID', 'USER_TYPE_KEY', 'DW_Warehouse.TOA_DM_USER_TYPE', null, null, null, null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('108', '1', '1', '#B#', 'REG_SOURCE_ID', 'REG_SOURCE_KEY', 'DW_Warehouse.TOA_DM_USER_REG_SOURCE', null, null, null, null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('109', '2', '1', '#B#', 'REG_SOURCE_ID', 'REG_SOURCE_KEY', 'DW_Warehouse.TOA_DM_USER_REG_SOURCE', null, null, null, null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('110', '1', '1', '#D#', 'REG_VERIFY_STATE_ID', 'REG_VERIFY_STATE_KEY', 'DW_Warehouse.TOA_DM_USER_VERIFY_STATE', null, null, null, null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('112', '2', '1', '#D#', 'REG_VERIFY_STATE_ID', 'REG_VERIFY_STATE_KEY', 'DW_Warehouse.TOA_DM_USER_VERIFY_STATE', null, null, null, null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('113', '4', '0', '#A#', null, null, null, null, 'yyyy-MM-dd', 'yyyyMMdd', null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('114', '4', '1', '#B#', 'REG_SOURCE_ID', 'REG_SOURCE_KEY', 'DW_Warehouse.WLT_DM_USER_REG_SOURCE', null, null, null, null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('115', '143', '0', '#A#', 'SERVICE_NAME', 'SERVICE_KEY', 'DW_Warehouse.WLT_DM_PARTNER_SERVICE', '0', null, null, null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('116', '143', '0', '#B#', 'SHOP_NAME', 'SHOP_KEY', 'DW_Warehouse.WLT_DM_PARTNER_SHOP', '0', null, null, null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('117', '143', '0', '#C#', 'CHANNEL_NAME', 'CHANNEL_KEY', 'DW_Warehouse.WLT_DM_PARTNER_CHANNEL', '0', null, null, null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('118', '143', '0', '#D#', 'GOODS_NAME', 'GOODS_KEY', 'DW_Warehouse.WLT_DM_PARTNER_GOODS', '0', null, null, null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('119', '143', '0', '#E#', 'PAYMENT_TYPE_NAME', 'PAYMENT_TYPE_KEY', 'DW_Warehouse.WLT_DM_PAYMENT_TYPE', '0', null, null, null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('120', '143', '0', '#F#', null, null, null, '0', null, null, null, null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('121', '144', '0', '#A#', null, null, null, null, null, null, '1,10', null, null, null);
INSERT INTO `AL_InputSqlField` VALUES ('122', '144', '0', '#D#', null, null, null, null, '(\\d{4})(\\d{2})(\\d{2})', '{0}-{1}-{2}', '', null, '2014-04-11 14:22:52', null);
INSERT INTO `AL_InputSqlField` VALUES ('123', '144', '1', '#TEST#', 'A1:#A#,A2:#E#,A3:#D#', 'RecordID', 'DW_MetaData.TTT', null, null, null, null, null, '2014-05-14 19:38:25', '2014-04-11 16:56:34');
INSERT INTO `AL_InputSqlField` VALUES ('124', '145', '1', '#CHANNEL#', 'ChannelName:#A#', 'ChannelKey', 'DW_MetaData.Channel', null, null, null, null, '1', '2014-04-16 12:09:33', '2014-04-15 19:56:20');
INSERT INTO `AL_InputSqlField` VALUES ('125', '145', '1', '#GOODS#', 'GoodsName:#B#', 'GoodsKey', 'DW_MetaData.Goods', null, null, null, null, '2', '2014-04-16 12:09:36', '2014-04-15 19:56:37');
INSERT INTO `AL_InputSqlField` VALUES ('126', '145', '0', '#KEY#', null, null, null, '#EMPTY#', '', '100_#C#_#D#_#GOODS#_#CHANNEL#', null, '4', '2014-04-16 11:16:04', '2014-04-15 19:59:12');
INSERT INTO `AL_InputSqlField` VALUES ('127', '145', '0', '#VAL#', null, null, null, '#EMPTY#', '', '#E#_#F#_#G#', null, '5', '2014-04-15 20:54:50', '2014-04-15 19:59:47');
INSERT INTO `AL_InputSqlField` VALUES ('128', '145', '0', '#C#', null, null, null, null, '(\\d{4})-(\\d{2})-(\\d{2})', '{0}{1}{2}', null, '3', '2014-04-16 16:01:46', '2014-04-15 20:54:37');
INSERT INTO `AL_InputSqlField` VALUES ('129', '144', '0', '#FILE_NAME#', null, null, null, '#FILENAME#', '24Money_(\\w*).csv', '{.}', null, null, '2014-05-13 19:38:05', null);
INSERT INTO `AL_InputSqlField` VALUES ('130', '144', '0', '#FILE_PATH#', null, null, null, '#FILEPATH#', null, null, null, null, '2014-05-13 18:57:44', null);

-- ----------------------------
-- Table structure for `AL_InputSqlFieldConstant`
-- ----------------------------
DROP TABLE IF EXISTS `AL_InputSqlFieldConstant`;
CREATE TABLE `AL_InputSqlFieldConstant` (
  `RecordID` bigint(20) NOT NULL AUTO_INCREMENT,
  `FileID` bigint(20) DEFAULT NULL,
  `Constant` int(9) DEFAULT NULL,
  `ExcelPointName` varchar(20) DEFAULT NULL COMMENT '对应的Excel列占位符;格式:#A#为第A列的值',
  `FieldPointName` varchar(20) DEFAULT NULL COMMENT '对应的SQL占位符;格式:#A#',
  `SqlKey` varchar(20) DEFAULT NULL COMMENT 'Sql中关联的key',
  `SqlValue` varchar(20) DEFAULT NULL COMMENT 'Sql中对应的Value',
  `TableName` varchar(50) DEFAULT NULL COMMENT '对应的表名',
  `OldFormat` varchar(20) DEFAULT NULL COMMENT '老格式',
  `NewFormat` varchar(20) DEFAULT NULL COMMENT '新格式',
  `CTime` timestamp NULL DEFAULT NULL,
  `MTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`RecordID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of AL_InputSqlFieldConstant
-- ----------------------------

-- ----------------------------
-- Table structure for `AL_InputSqlFileName`
-- ----------------------------
DROP TABLE IF EXISTS `AL_InputSqlFileName`;
CREATE TABLE `AL_InputSqlFileName` (
  `RecordID` bigint(20) NOT NULL DEFAULT '0',
  `FileID` bigint(20) DEFAULT NULL,
  `FeildPointName` varchar(20) DEFAULT NULL,
  `FileNameRegExp` varchar(45) DEFAULT NULL,
  `MTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`RecordID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of AL_InputSqlFileName
-- ----------------------------
INSERT INTO `AL_InputSqlFileName` VALUES ('1', '143', '#PPP#', '(\\\\d*)_wlt_e_m.day.\\\\d*.csv', '2014-04-16 10:54:14');

-- ----------------------------
-- Table structure for `AL_InputType`
-- ----------------------------
DROP TABLE IF EXISTS `AL_InputType`;
CREATE TABLE `AL_InputType` (
  `RecordID` bigint(20) NOT NULL,
  `TypeName` varchar(45) DEFAULT NULL,
  `TypeExt` varchar(20) DEFAULT NULL,
  `JobName` varchar(100) DEFAULT NULL,
  `TargetJNDIName` varchar(256) DEFAULT NULL,
  `TargetSource` varchar(16) DEFAULT 'MYSQL' COMMENT 'MYSQL | HBASE | ORACLE',
  `Enable` int(9) DEFAULT NULL,
  `MTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `CTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`RecordID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of AL_InputType
-- ----------------------------
INSERT INTO `AL_InputType` VALUES ('1', 'Excel2007', 'xlsx', 'ExcelHandle/ExcelJob.kjb', null, null, '0', null, null);
INSERT INTO `AL_InputType` VALUES ('2', '刘泉Csv', 'csv', 'CsvHandle/CsvJob.kjb', null, null, '0', null, null);
INSERT INTO `AL_InputType` VALUES ('3', '徐旭Csv', 'csv', 'CsvHandle/CsvJob.kjb', null, null, '0', null, null);
INSERT INTO `AL_InputType` VALUES ('4', '秦YangCsv', 'csv', 'CsvHandle/CsvJob.kjb', null, null, '0', null, null);
INSERT INTO `AL_InputType` VALUES ('5', '秦yangCsv2', 'csv', 'CsvHandle_null2strnull/CsvJob.kjb', null, null, '0', null, null);
INSERT INTO `AL_InputType` VALUES ('6', '周国平Csv', 'csv', 'CsvHandle/CsvJob.kjb', null, null, '0', null, null);
INSERT INTO `AL_InputType` VALUES ('7', '李娜', 'csv', 'CsvHandle/CsvJob.kjb', null, null, '1', null, null);
INSERT INTO `AL_InputType` VALUES ('20', 'gg', 'csv', null, null, null, '20', null, null);
