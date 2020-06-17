/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50173
Source Host           : localhost:3306
Source Database       : wcsy

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2020-06-17 15:54:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for task_plan_t
-- ----------------------------
DROP TABLE IF EXISTS `task_plan_t`;
CREATE TABLE `task_plan_t` (
  `TASK_PLAN_ID` varchar(64) NOT NULL COMMENT '任务计划表id',
  `TASK_ID` varchar(64) DEFAULT NULL COMMENT '任务ID',
  `USER_CODE` varchar(64) DEFAULT NULL COMMENT '作业员用户账号',
  `JCY_NAME` varchar(64) DEFAULT NULL COMMENT '检查员中文名',
  `DLFQ` varchar(128) DEFAULT NULL COMMENT '地理分区',
  `CJ` datetime DEFAULT NULL COMMENT '采集',
  `HC` datetime DEFAULT NULL COMMENT '核查',
  `BJ` datetime DEFAULT NULL COMMENT '编辑',
  `ZJ` datetime DEFAULT NULL COMMENT '质检',
  `EC` datetime DEFAULT NULL COMMENT '二查',
  `HK` datetime DEFAULT NULL COMMENT '合库',
  `SJ` datetime DEFAULT NULL COMMENT '上交',
  `WCBJ` char(1) DEFAULT NULL COMMENT '任务完成标记（0：未完成；1：已经完成）',
  PRIMARY KEY (`TASK_PLAN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务计划表';

-- ----------------------------
-- Records of task_plan_t
-- ----------------------------
INSERT INTO `task_plan_t` VALUES ('08c0bc0de8bd4032a3ca8efa2d177e9f', '1', 'liuyl', '李云龙', '济宁任城区', '2020-06-17 15:52:35', '2020-06-17 15:52:35', '2020-06-17 15:52:35', '2020-06-17 15:52:35', '2020-06-17 15:52:35', '2020-06-17 15:52:35', '2020-06-17 15:52:35', '0');
INSERT INTO `task_plan_t` VALUES ('1', '1', 'liuyl', '高阳', '山东', '2020-06-10 19:17:21', '2020-06-10 19:17:28', '2020-06-10 19:17:32', '2020-06-10 19:17:36', '2020-06-10 19:17:38', '2020-06-10 19:17:41', '2020-06-10 19:17:44', '1');
INSERT INTO `task_plan_t` VALUES ('2', null, 'test1', null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `task_plan_t` VALUES ('3', null, '3', null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `task_plan_t` VALUES ('4', null, '3', null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `task_plan_t` VALUES ('5', null, '3', null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `task_plan_t` VALUES ('6', null, '3', null, null, null, null, null, null, null, null, null, '0');

-- ----------------------------
-- Table structure for task_schedule_t
-- ----------------------------
DROP TABLE IF EXISTS `task_schedule_t`;
CREATE TABLE `task_schedule_t` (
  `TASK_SCHEDULE_ID` varchar(64) NOT NULL COMMENT '任务进度表id',
  `TASK_ID` varchar(64) DEFAULT NULL COMMENT '任务ID',
  `USER_CODE` varchar(64) DEFAULT NULL COMMENT '作业员用户账号',
  `JCY_NAME` varchar(64) DEFAULT NULL COMMENT '检查员中文名',
  `DLFQ` varchar(128) DEFAULT NULL COMMENT '地理分区',
  `CJ` varchar(64) DEFAULT NULL COMMENT '采集',
  `HC` varchar(64) DEFAULT NULL COMMENT '核查',
  `BJ` varchar(64) DEFAULT NULL COMMENT '编辑',
  `ZJ` varchar(64) DEFAULT NULL COMMENT '质检',
  `EC` varchar(64) DEFAULT NULL COMMENT '二查',
  `HK` varchar(64) DEFAULT NULL COMMENT '合库',
  `SJ` varchar(64) DEFAULT NULL COMMENT '上交',
  `WCBJ` char(1) DEFAULT NULL COMMENT '任务完成标记（0：未完成；1：已经完成）',
  PRIMARY KEY (`TASK_SCHEDULE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务进度表';

-- ----------------------------
-- Records of task_schedule_t
-- ----------------------------
INSERT INTO `task_schedule_t` VALUES ('1', '3', 'liuyl', '秦始皇', '山东', '100', '100', '80', '0', '0', '0', '0', '0');
INSERT INTO `task_schedule_t` VALUES ('2', '2', 'liuyl', '秦始皇', '山东', '100', '100', '80', '0', '0', '0', '0', '0');
INSERT INTO `task_schedule_t` VALUES ('3', '1', 'langren', '狼人', '山东', '100', '100', '100', '100', '100', '100', '80', '0');
INSERT INTO `task_schedule_t` VALUES ('4', '2', 'mayun', '码云', '山东', '100', '100', '100', '100', '100', '100', '90', '0');
INSERT INTO `task_schedule_t` VALUES ('5', '1', 'mahuateng', '麻花疼', '山东', '100', '100', '100', '100', '100', '100', '100', '0');
INSERT INTO `task_schedule_t` VALUES ('efecdbb0c0974665a4eaf5e1a5ce4a41', '1', 'liuyl', '马云', '山东', '100', '100', '100', '100', '100', '100', '100', '1');

-- ----------------------------
-- Table structure for task_t
-- ----------------------------
DROP TABLE IF EXISTS `task_t`;
CREATE TABLE `task_t` (
  `TASK_ID` varchar(64) NOT NULL COMMENT '任务ID',
  `TASK_YEAR` varchar(20) DEFAULT NULL COMMENT '年份',
  `TASK_NAME` varchar(256) DEFAULT NULL COMMENT '任务名称',
  `WCBJ` char(1) DEFAULT NULL,
  PRIMARY KEY (`TASK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务信息表';

-- ----------------------------
-- Records of task_t
-- ----------------------------
INSERT INTO `task_t` VALUES ('1', '2020', '马鞍山市地理国情监测', '1');
INSERT INTO `task_t` VALUES ('2', '2019', '马鞍山市地理国情监测', '0');
INSERT INTO `task_t` VALUES ('3', '2018', '马鞍山市地理国情监测', '0');
INSERT INTO `task_t` VALUES ('513647c7df0c42fc88d4854b0d9d25cc', '2018', '山东省济宁市地理国情监测', '0');
INSERT INTO `task_t` VALUES ('6400c6d5367a49249719bd8a6597fa8b', '2020', '山东省济宁市地理国情监测', '0');
INSERT INTO `task_t` VALUES ('e04e8d3227af41eb9e987be1c88ed1bf', '2019', '山东省济宁市地理国情监测', '0');

-- ----------------------------
-- Table structure for user_t
-- ----------------------------
DROP TABLE IF EXISTS `user_t`;
CREATE TABLE `user_t` (
  `USER_ID` varchar(64) NOT NULL COMMENT '用户id',
  `USER_CODE` varchar(64) DEFAULT NULL COMMENT '用户账号',
  `USER_NAME` varchar(64) DEFAULT NULL COMMENT '真实姓名',
  `PASSWORD` varchar(64) DEFAULT NULL COMMENT '用户密码',
  `USER_ROLE` varchar(64) DEFAULT NULL COMMENT '用户角色',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色信息表';

-- ----------------------------
-- Records of user_t
-- ----------------------------
INSERT INTO `user_t` VALUES ('1', 'admin', '超级管理员', 'admin', '管理员');
INSERT INTO `user_t` VALUES ('2', 'liuyl', 'liuyl', 'liuyl', '作业员');
INSERT INTO `user_t` VALUES ('3', 'langren', '狼人', 'langren', '作业员');
INSERT INTO `user_t` VALUES ('4', 'mahuateng', '麻花疼', 'mahuateng', '作业员');
INSERT INTO `user_t` VALUES ('5', 'mayun', '马云', 'mayun', '作业员');
