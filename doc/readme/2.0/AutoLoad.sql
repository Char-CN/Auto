/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50710
 Source Host           : 127.0.0.1
 Source Database       : DW_MetaData

 Target Server Type    : MySQL
 Target Server Version : 50710
 File Encoding         : utf-8

 Date: 02/01/2016 15:53:40 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `AL_DataSource`
-- ----------------------------
DROP TABLE IF EXISTS `AL_DataSource`;
CREATE TABLE `AL_DataSource` (
  `RecordID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `TargetSourceDBName` varchar(32) NOT NULL COMMENT '目标数据库的名称，必须填写mysql之类的数据库名称\r目标数据库的名称，必须填写mysql之类的数据库名称\r目标数据库的名称，必须填写mysql之类的数据库名称',
  `Name` varchar(32) NOT NULL COMMENT '名称，可随意填写',
  `Url` varchar(256) DEFAULT NULL COMMENT '连接字符串',
  `UserName` varchar(64) DEFAULT NULL COMMENT '用户名',
  `Password` varchar(64) DEFAULT NULL COMMENT '密码',
  `Enable` tinyint(1) DEFAULT NULL COMMENT '是否可用，0表示不可用，1表示可用',
  `MTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `CTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`RecordID`),
  UNIQUE KEY `IDX_NAME` (`TargetSourceDBName`,`Name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='说明：\n	目前仅支持mysql数据库，TargetSourceDBName一定要填mysql';

-- ----------------------------
--  Records of `AL_DataSource`
-- ----------------------------
BEGIN;
INSERT INTO `AL_DataSource` VALUES ('1', 'mysql', 'default', 'jdbc:mysql://127.0.0.1:3306/test', 'root', 'root', '1', '2016-01-15 17:05:35', '2016-01-29 15:51:53'), ('2', 'csv', 'csv', null, null, null, '1', '2016-01-26 11:55:42', '2016-01-29 15:51:53');
COMMIT;

-- ----------------------------
--  Table structure for `AL_InputFile`
-- ----------------------------
DROP TABLE IF EXISTS `AL_InputFile`;
CREATE TABLE `AL_InputFile` (
  `RecordID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `GroupID` bigint(20) DEFAULT NULL COMMENT '组ID，关联AL_InputGroup表',
  `DataSourceID` bigint(20) DEFAULT NULL COMMENT '数据源ID，关联AL_DataSource表',
  `FileName` varchar(45) DEFAULT NULL COMMENT '名称，可随意填写，此Name不是需要导入文件的Name',
  `FilePath` varchar(200) DEFAULT NULL COMMENT '导入的文件路径',
  `FileRegExp` varchar(200) DEFAULT NULL COMMENT '导入的文件名的正则表达式，匹配该正则的文件都会被扫描到',
  `FileSeparator` varchar(20) DEFAULT '	' COMMENT '文件分隔符',
  `InputSql` text COMMENT '配置INSERT语句，如：INSERT INTO HYY_TB(Name,#UP#=Age) Values({1},{2})；其中{1},{2}表示读取文件的第1个和第2个字段，如果字段中配置了#UP#=，那么会自动生成ON DUPLICATE KEY语句，对应update配置#UP#的字段',
  `InputMode` int(9) DEFAULT '1' COMMENT 'INSERT模式，0表示容错模式，1表示事务模式',
  `Sort` int(11) DEFAULT NULL COMMENT '扫描导入文件的顺序，从小到大',
  `Enable` int(9) DEFAULT '1' COMMENT '是否可用，0表示不可用，1表示可用',
  `MTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `CTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`RecordID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='说明：\n	这是最核心与最重要的一个配置文件，一条配置信息，通常是将一个路径下的一组被正则表达式匹配的csv文件导入到一个Mysql表中，你还可以配置文件的分隔符与扫描的顺序等等，文件名中常常包含周期与日期的固定维度，你可以在配置表AL_InputFileConstant中获取你得周期与日期并且定义任意个占位（如：{PeriodKey}），并填入到该配置的InputSql中。\n	有一种常见情况，就是你重新又生成了一次该csv，这种情况我们叫做重复导入，针对重要的情况，你可以根据文件名中体现出来的周期与日期维度将该数据删除，然后再导入mysql中，删除数据的那一步在配置表AL_InputFileBefore中配置，在导入每一个csv之前，都会执行一下delete sql。【当然，你对程序足够自信的话，也可以使用Mysql自带的特性ON DUPLICATE KEY，详情见InputSql的注释。】\n	当你的csv中包含维度信息的时候，你需要在AL_InputFileField配置表中配置，DimFlag必须设置成1（表示该维度会被导入到mysql），支持多重维度（维度中包含维度）配置，你配置维度后，通常会在AL_InputFileField表中的FieldPointName中自定义一个名称（如：{ProductID}），在InputSql中{ProductID}会自动被替换成该维度表的主键ID（配置在AL_InputFileField表中的DimValue）\n	当你的csv中字段需要做一些常规处理的时候，你需要在AL_InputFileField配置表中配置，在Dim开头的字段都不需要配置，设置成Null即可。\n	当程序处理的日志，通常会被记录到AL_InputFileLog表中。\n注意：\n	AL_InputFileConstant和AL_InputFileField配置表大同小异，前者大多用于抓取文件名中的内容，后者大多用于转换文件中的内容以及维度信息。';

-- ----------------------------
--  Records of `AL_InputFile`
-- ----------------------------
BEGIN;
INSERT INTO `AL_InputFile` VALUES ('1', '1', null, 'hyy', '/Users/hyy/Work/data', 'hyy.csv', '\\t', 'insert into hyy values({1},{2})', '1', '1', '0', '2016-01-28 15:21:39', null), ('2', '1', '2', 'hyy', '/Users/hyy/Work/data', 'hyy.csv', '\\t', '{1}\\t{2}\\t{3}', '1', '2', '0', '2016-01-28 15:21:43', null), ('10', '1', null, '案例一事实一', '/Users/hyy/Work/data', 'fact_sales_\\d*_\\d*.csv', '\\t', 'INSERT INTO Fact_Sales(PeriodKey,DayKey,ProductID,SalesmanID,SpotPriceTotal,SalePriceTotal) VALUES({PeriodKey},{DayKey},{ProductID},{SalesmanID},{4},{5})', '1', '3', '1', '2016-01-29 18:25:06', null);
COMMIT;

-- ----------------------------
--  Table structure for `AL_InputFileBefore`
-- ----------------------------
DROP TABLE IF EXISTS `AL_InputFileBefore`;
CREATE TABLE `AL_InputFileBefore` (
  `RecordID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `FileID` bigint(20) DEFAULT NULL COMMENT '关联表AL_InputFile',
  `DataSourceID` bigint(20) DEFAULT NULL COMMENT '关联表AL_DataSource',
  `BeforeSql` text COMMENT '在导入每个文件时，需要执行的SQL语句，可以配置成如：delete from table where PeriodKey={PeriodKey} and DayKey={DayKey}，这些占位字段需要在AL_InputFileConstant中配置',
  `Sort` int(9) DEFAULT NULL COMMENT '执行顺序，从小到大',
  `Enable` int(9) DEFAULT '1' COMMENT '是否可用，0表示不可用，1表示可用',
  `MTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `CTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`RecordID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='说明：\n	该表配置导入每个文件时，可以先执行的一条SQL语句，有一种常见情况，就是你重新又生成了一次该csv，这种情况我们叫做重复导入，针对重要的情况，你可以根据文件名中体现出来的周期与日期维度将该数据删除，然后再导入mysql中，删除数据的那一步在这里配置，在导入每一个csv之前，都会执行一下delete sql。当然，你也可以不这么做。';

-- ----------------------------
--  Records of `AL_InputFileBefore`
-- ----------------------------
BEGIN;
INSERT INTO `AL_InputFileBefore` VALUES ('1', '10', null, 'delete from Fact_Sales where PeriodKey={PeriodKey} and DayKey={DayKey}', '1', '1', '2016-01-29 18:24:13', '2016-01-29 18:17:08');
COMMIT;

-- ----------------------------
--  Table structure for `AL_InputFileConstant`
-- ----------------------------
DROP TABLE IF EXISTS `AL_InputFileConstant`;
CREATE TABLE `AL_InputFileConstant` (
  `RecordID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `FileID` bigint(20) DEFAULT NULL COMMENT '关联表AL_InputFile。',
  `FieldPointName` varchar(128) DEFAULT NULL COMMENT '对应的SQL占位符，格式：{PeriodKey}，{DayKey}',
  `DefaultValue` varchar(32) DEFAULT NULL COMMENT '当读取到该值为空字符串时的默认值，系统保留占位：#NULL_STR#表示null字符串，#EMPTY#表示空字符串，#NULL#表示数据库中的Null值',
  `ConvertMethod` varchar(64) DEFAULT NULL COMMENT '转换的方法，可以将值转成大写或小写，配置lower或者upper',
  `OldFormat` varchar(256) DEFAULT NULL COMMENT '正则表达式转换，如果配置fact_sales_(\\d*)_(\\d*).csv，需要注意的是，括号中的，是正则抓取的内容，在这里抓取到了一组值，这组值要被输出成什么格式，需要在NewFormat中配置。',
  `NewFormat` varchar(256) DEFAULT NULL COMMENT '通过OldFormat正则表达式抓取出的一组值，配置{n}表示获得第n个值，配置{.}回将所有抓出来的值都连接起来，你也可以在配置中加入任意字符串，如加入下划线：{1}_{2}_{3}',
  `LimitLength` varchar(16) DEFAULT NULL COMMENT '如果字符串太长，你可以通过此配置截断字符串，配置一：[1,100]即是截取1到100个；配置二：[50,]即是截取从第50个到最后；配置三：[,50]即是截取最后50个',
  `Sort` int(11) DEFAULT NULL COMMENT '转换顺序，从小到大',
  `MTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `CTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`RecordID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='说明：\n	AL_InputFileConstant和AL_InputFileField配置表大同小异，前者大多用于抓取文件名中的内容，后者大多用于转换文件中的内容以及维度信息。\n	系统占位#FILENAME#和#FILEPATH#可分别读取当前的路径与文件名，配置到DefaultValue中即可。然后自定义FieldPointName（如：{DayKey}），则可在配置表AL_InputFile或者AL_InputFileFields或者AL_InputFileBefor中填写{DayKey}';

-- ----------------------------
--  Records of `AL_InputFileConstant`
-- ----------------------------
BEGIN;
INSERT INTO `AL_InputFileConstant` VALUES ('1', '10', '{PeriodKey}', '#FILENAME#', null, 'fact_sales_(\\d*)_\\d*.csv', '{1}', null, null, '2016-01-28 16:36:40', '2016-01-29 15:50:16'), ('2', '10', '{DayKey}', '#FILENAME#', null, 'fact_sales_\\d*_(\\d*).csv', '{1}', null, null, '2016-01-28 16:42:45', '2016-01-29 15:50:16');
COMMIT;

-- ----------------------------
--  Table structure for `AL_InputFileField`
-- ----------------------------
DROP TABLE IF EXISTS `AL_InputFileField`;
CREATE TABLE `AL_InputFileField` (
  `RecordID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `FileID` bigint(20) DEFAULT NULL COMMENT '关联表AL_InputFile',
  `DataSourceID` bigint(20) DEFAULT NULL COMMENT '关联表AL_DataSource',
  `FieldPointName` varchar(20) DEFAULT NULL COMMENT '对应的SQL占位符，格式：{ProductID}，{SalesmanID}',
  `DimFlag` int(9) DEFAULT '0' COMMENT '设置成1，表示该维度会被导入到mysql',
  `DimTableName` varchar(100) DEFAULT NULL COMMENT '维度表名',
  `DimKey` varchar(256) DEFAULT NULL COMMENT '该Key并非维度表的Key，一般表示维度名称，因为维度名称不可能相同',
  `DimValue` varchar(256) DEFAULT NULL COMMENT '该Value表示维度表的主键ID',
  `DefaultValue` varchar(256) DEFAULT NULL COMMENT '当读取到该值为空字符串时的默认值，系统保留占位：#NULL_STR#表示null字符串，#EMPTY#表示空字符串，#NULL#表示数据库中的Null值',
  `ConvertMethod` varchar(256) DEFAULT NULL COMMENT '转换的方法，可以将值转成大写或小写，配置lower或者upper',
  `OldFormat` varchar(256) DEFAULT NULL COMMENT '正则表达式转换，如果配置fact_sales_(\\d*)_(\\d*).csv，需要注意的是，括号中的，是正则抓取的内容，在这里抓取到了一组值，这组值要被输出成什么格式，需要在NewFormat中配置。',
  `NewFormat` varchar(256) DEFAULT NULL COMMENT '通过OldFormat正则表达式抓取出的一组值，配置{n}表示获得第n个值，配置{.}回将所有抓出来的值都连接起来，你也可以在配置中加入任意字符串，如加入下划线：{1}_{2}_{3}',
  `LimitLength` varchar(20) DEFAULT NULL COMMENT '如果字符串太长，你可以通过此配置截断字符串，配置一：[1,100]即是截取1到100个；配置二：[50,]即是截取从第50个到最后；配置三：[,50]即是截取最后50个',
  `Sort` int(11) DEFAULT NULL COMMENT '转换顺序，从小到大',
  `MTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `CTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`RecordID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='说明：\n	AL_InputFileConstant和AL_InputFileField配置表大同小异，前者大多用于抓取文件名中的内容，后者大多用于转换文件中的内容以及维度信息。\n	当你的csv中包含维度信息的时候，你需要在AL_InputFileField配置表中配置，DimFlag必须设置成1（表示该维度会被导入到mysql），支持多重维度（维度中包含维度）配置，你配置维度后，通常会在AL_InputFileField表中的FieldPointName中自定义一个名称（如：{ProductID}），在AL_InputFile的InputSql中{ProductID}会自动被替换成该维度表的主键ID（配置在AL_InputFileField表中的DimValue）\n	当你的csv中字段需要做一些常规处理的时候，你需要在AL_InputFileField配置表中配置，在Dim开头的字段都不需要配置，设置成Null即可。';

-- ----------------------------
--  Records of `AL_InputFileField`
-- ----------------------------
BEGIN;
INSERT INTO `AL_InputFileField` VALUES ('1', '10', null, '{SalesmanID}', '1', 'Dim_Salesman', 'SalesmanName:{3}', 'RecordID', null, null, null, null, null, '1', '2016-01-28 16:45:29', '2016-01-28 15:18:38'), ('2', '10', null, '{ProductLevelID}', '1', 'Dim_Product_Level', 'ProductLevelName:{1}', 'RecordID', null, null, null, null, null, '2', '2016-01-28 16:45:42', '2016-01-28 15:06:24'), ('3', '10', null, '{ProductID}', '1', 'Dim_Product', 'ProductName:{2},ProductLevelID:{ProductLevelID}', 'RecordID', null, null, null, null, null, '3', '2016-01-28 16:46:04', '2016-01-28 15:15:17');
COMMIT;

-- ----------------------------
--  Table structure for `AL_InputFileLog`
-- ----------------------------
DROP TABLE IF EXISTS `AL_InputFileLog`;
CREATE TABLE `AL_InputFileLog` (
  `RecordID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `GroupID` bigint(20) DEFAULT NULL COMMENT '关联表AL_InputGroup',
  `FileID` bigint(20) DEFAULT NULL COMMENT '关联表AL_InputFile',
  `RealFilePath` varchar(200) DEFAULT NULL COMMENT '真实的文件路径',
  `RealFileName` varchar(100) DEFAULT NULL COMMENT '真实的文件名',
  `Result` varchar(16) DEFAULT NULL COMMENT '执行结果，0.fail | 1.success',
  `Detail` text COMMENT '日志详情，错误信息',
  `IsRead` tinyint(1) DEFAULT '0' COMMENT '是否已读，默认0是未读',
  `MTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `CTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`RecordID`),
  KEY `IDX_UL_SARUUC` (`CTime`) USING BTREE,
  KEY `IDX_GroupID` (`GroupID`),
  KEY `IDX_FileID` (`FileID`),
  KEY `IDX_RealFilePath` (`RealFilePath`),
  KEY `IDX_RealFileName` (`RealFileName`),
  KEY `IDX_Result` (`Result`),
  KEY `IDX_IsRead` (`IsRead`)
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=utf8 COMMENT='说明：\n	这是一个日志记录表，非常单纯。需要在/Auto/resource/sys-config.properties中配置database.log.success.flag=true和database.log.fail.flag=true，才会将记录插入到该表中，一般默认是开启的。';

-- ----------------------------
--  Records of `AL_InputFileLog`
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
--  Table structure for `AL_InputGroup`
-- ----------------------------
DROP TABLE IF EXISTS `AL_InputGroup`;
CREATE TABLE `AL_InputGroup` (
  `RecordID` bigint(20) NOT NULL COMMENT '记录ID',
  `DataSourceID` bigint(20) DEFAULT NULL COMMENT '关联表AL_DataSource',
  `GroupName` varchar(45) DEFAULT NULL COMMENT '名称，随意填写',
  `Enable` int(9) DEFAULT NULL COMMENT '是否可用，0表示不可用，1表示可用',
  `MTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `CTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`RecordID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='说明：\n	该表也很单纯，就是为了将AL_InputFile分组，需要引用该表RecordID，你懂得。';

-- ----------------------------
--  Records of `AL_InputGroup`
-- ----------------------------
BEGIN;
INSERT INTO `AL_InputGroup` VALUES ('1', '1', '何云洋', '1', '2016-01-15 17:04:26', null);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
