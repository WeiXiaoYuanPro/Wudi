/*
Navicat MySQL Data Transfer

Source Server         : 120.79.188.45_3306
Source Server Version : 50505
Source Host           : 120.79.188.45:3306
Source Database       : wxy

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2019-03-22 12:52:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `building`
-- ----------------------------
DROP TABLE IF EXISTS `building`;
CREATE TABLE `building` (
  `id` varchar(100) NOT NULL COMMENT 'id',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `addr` varchar(100) DEFAULT NULL COMMENT '经度纬度',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `school_id` varchar(100) DEFAULT NULL COMMENT '分校id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of building
-- ----------------------------
INSERT INTO `building` VALUES ('1553225054784', '三号教学楼', '10.00', '大数据', '001');

-- ----------------------------
-- Table structure for `cms_user`
-- ----------------------------
DROP TABLE IF EXISTS `cms_user`;
CREATE TABLE `cms_user` (
  `id` varchar(100) NOT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `create_time` varchar(100) DEFAULT NULL,
  `img` varchar(100) DEFAULT NULL,
  `type` int(1) DEFAULT NULL COMMENT '0超级管理员，1普通管理员',
  `status` int(1) DEFAULT NULL COMMENT '0:正常，1异常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_user
-- ----------------------------

-- ----------------------------
-- Table structure for `department`
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` varchar(100) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `no` varchar(100) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('001', '凯里学院', '01001', 'klxy');

-- ----------------------------
-- Table structure for `depman`
-- ----------------------------
DROP TABLE IF EXISTS `depman`;
CREATE TABLE `depman` (
  `id` varchar(255) NOT NULL,
  `depname` varchar(255) DEFAULT NULL COMMENT '用户名',
  `status` tinyint(4) DEFAULT '0' COMMENT '0工作，1离职',
  `create_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of depman
-- ----------------------------
INSERT INTO `depman` VALUES ('1544148462109', '王驰', '0', '2019-03-22');
INSERT INTO `depman` VALUES ('1544148462110', '卢英剑', '0', '2019-03-22');
INSERT INTO `depman` VALUES ('1544148462131q', '陈糠杰', '0', '2019-03-22');
INSERT INTO `depman` VALUES ('1544148462132', '龙明涛', '0', '2019-03-22');
INSERT INTO `depman` VALUES ('1544148462134', '蒋霜', '0', '2019-03-22');
INSERT INTO `depman` VALUES ('1544148462135', '陈凤朝', '0', '2019-03-22');
INSERT INTO `depman` VALUES ('1544148462136', '肖老师', '0', '2019-03-22');
INSERT INTO `depman` VALUES ('1544148462137', '梁老师', '0', '2019-03-22');
INSERT INTO `depman` VALUES ('1544148462138', '李老师', '0', '2019-03-22');
INSERT INTO `depman` VALUES ('1544148462139', '李颖鹏', '0', '2019-03-22');
INSERT INTO `depman` VALUES ('1544148462178', '李金鹏', '0', '2019-03-22');
INSERT INTO `depman` VALUES ('1544148462188', '李洋', '0', '2019-03-22');
INSERT INTO `depman` VALUES ('1544148462195', '杨正旺', '1', '2019-03-22');
INSERT INTO `depman` VALUES ('1553185306063', '刘德华', '1', '2019-03-22');

-- ----------------------------
-- Table structure for `login_log`
-- ----------------------------
DROP TABLE IF EXISTS `login_log`;
CREATE TABLE `login_log` (
  `id` varchar(100) NOT NULL,
  `username` varchar(100) DEFAULT NULL,
  `login_time` varchar(100) DEFAULT NULL,
  `ip` varchar(100) DEFAULT NULL,
  `addr` varchar(100) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT 'status：0成功，1失败',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of login_log
-- ----------------------------
INSERT INTO `login_log` VALUES ('079a6285-ad32-44e9-95f3-62c03d9cfcf5', '3', '2018-11-28 07:05:28', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1543567291333', '3', '2018-11-30 04:41:31', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1543567943934', '3', '2018-11-30 04:52:23', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1543670038975', '3', '2018-12-01 09:13:58', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1543971649126', '3', '2018-12-05 09:00:47', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1543972544575', '3', '2018-12-05 09:15:44', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1544195183869', '3', '2018-12-07 11:06:23', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1544195217329', '3', '2018-12-07 11:06:57', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1544195315926', '3', '2018-12-07 11:08:35', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1544195992311', '3', '2018-12-07 11:19:52', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1544196001886', '3', '2018-12-07 11:20:01', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1544196001926', '3', '2018-12-07 11:20:01', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1544267831709', '3', '2018-12-08 07:17:11', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1544268159142', '3', '2018-12-08 07:22:39', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1544268487308', '3', '2018-12-08 07:26:42', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1544536987115', '3', '2018-12-11 10:03:06', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1544869820307', '3', '2018-12-15 06:30:19', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1544870160697', '3', '2018-12-15 06:36:00', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1545636710016', '3', '2018-12-24 03:31:49', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1551417643129', '3', '2019-03-01 01:20:43', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1551417888417', '3', '2019-03-01 01:24:48', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1551425404997', '3', '2019-03-01 03:30:04', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1551426612092', '3', '2019-03-01 03:50:11', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1551426827077', '3', '2019-03-01 03:53:03', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1551427185164', '3', '2019-03-01 03:59:44', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1551428380514', '3', '2019-03-01 04:19:40', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1551428437878', '3', '2019-03-01 04:20:37', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1551429203821', '3', '2019-03-01 04:33:23', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1551429243552', '3', '2019-03-01 04:34:03', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1551429392000', '3', '2019-03-01 04:36:31', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1551429596897', '3', '2019-03-01 04:39:56', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1551429801641', '3', '2019-03-01 04:43:21', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1551447830356', '3', '2019-03-01 09:43:50', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1551447940897', '3', '2019-03-01 09:45:40', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1551448218652', '3', '2019-03-01 09:50:18', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1551448453262', '3', '2019-03-01 09:54:13', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1551947676574', '3', '2019-03-07 04:34:36', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1552821766822', '3', '2019-03-17 07:22:46', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1552824292209', '3', '2019-03-17 08:04:51', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1552829338281', '杨正旺', '2019-03-17 09:28:57', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1552837153977', '3', '2019-03-17 11:39:13', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1552837172005', '3', '2019-03-17 11:39:31', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('1552837182295', '杨正旺', '2019-03-17 11:39:42', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('22106df7-08db-43e4-988d-c594c26c46f0', '3', '2018-11-28 07:51:53', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('314e4f7c-db8d-4f11-8bbe-14dacd6a8338', 'js', '2018-11-28 10:19:59', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('47c7bd7d-b094-40db-bf04-0a3413aafe83', '3', '2018-11-28 :06:08:02', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('4ad1f8e2-9c93-412b-8dff-27b79449f0b0', '3', '2018-11-28 19:57:31', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', 'ok', '0');
INSERT INTO `login_log` VALUES ('81c43bc1-27e5-4559-b9d3-5db4e8e9de6f', 'read', '2018-11-28 :06:07:10', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('84ae051d-06ea-4e6e-8b2d-e830d14f8b6d', 'read', '2018-11-28 07:19:03', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('98cc0a53-b05e-48e4-990c-33dde3d8b2b8', '3', '2018-11-28 09:46:41', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `login_log` VALUES ('df874fdd-b23d-47fd-8fb0-0ee1967d5029', 'js', '2018-11-28 07:18:32', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');

-- ----------------------------
-- Table structure for `major`
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major` (
  `id` varchar(100) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `dep_no` varchar(100) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of major
-- ----------------------------
INSERT INTO `major` VALUES ('001', '凯里学院', '01001', 'klxy');

-- ----------------------------
-- Table structure for `role_info`
-- ----------------------------
DROP TABLE IF EXISTS `role_info`;
CREATE TABLE `role_info` (
  `id` varchar(100) NOT NULL,
  `rolename` varchar(100) DEFAULT NULL,
  `level` int(1) DEFAULT NULL,
  `control` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `user_id` varchar(100) DEFAULT NULL COMMENT '0：学生 1：教师 2：主管 3：校工 4：管理员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_info
-- ----------------------------

-- ----------------------------
-- Table structure for `room`
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `id` varchar(100) NOT NULL COMMENT '房间编号',
  `name` varchar(100) DEFAULT NULL COMMENT '教室名',
  `building_id` varchar(100) DEFAULT NULL COMMENT '教学楼id',
  `capacity` int(3) DEFAULT NULL COMMENT '容量',
  `type` int(2) DEFAULT NULL COMMENT '种类',
  `status` int(2) DEFAULT NULL COMMENT '状态',
  `latitude` decimal(10,0) DEFAULT NULL COMMENT '纬度',
  `longitude` decimal(10,0) DEFAULT NULL COMMENT '经度',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES ('1543837464466', '3', '1544092795707', '3', '2', '3', '2', '3');
INSERT INTO `room` VALUES ('1543837482634', '3', '981ade07-0287-4d47-823a-a9e18a08b791', '333', '333', '43', '33', '4');
INSERT INTO `room` VALUES ('1543837494999', '222', '1544075999362', '2222', '2222', '2222', '2222', '2222');
INSERT INTO `room` VALUES ('1543837507154', '555', '1544092795707', '555', '555', '555', '555', '555');
INSERT INTO `room` VALUES ('1543837521801', '666', '1544092814922', '6666', '666', '66', '666', '6666');
INSERT INTO `room` VALUES ('1543837532669', '888', '888', '888', '888', '888', '888', '8888');
INSERT INTO `room` VALUES ('1544011183758', '2', '3', '4', '5', '6', '7', '8');
INSERT INTO `room` VALUES ('1544015647747', 'D', 'SF', '2', '12', '2', '3', '13');
INSERT INTO `room` VALUES ('1544019822645', '222', '222', '22', '22', '1', '3', '5');
INSERT INTO `room` VALUES ('1544019885381', '1', '1', '1', '1', '11', '1', '1');
INSERT INTO `room` VALUES ('1544022905517', '65', '1544020131028', '4', '5', '8', '9', '8');
INSERT INTO `room` VALUES ('1544088029167', '32205', '246e1c81-e060-435a-9031-da6159f35b18', '55', '1', '4', '7', '7');
INSERT INTO `room` VALUES ('76dccdae-db28-488e-b79a-ef81b9c0dd88', '2015', '1544092795707', '12123', '5', '6', '7', '8');

-- ----------------------------
-- Table structure for `school`
-- ----------------------------
DROP TABLE IF EXISTS `school`;
CREATE TABLE `school` (
  `id` varchar(100) NOT NULL,
  `schoolname` varchar(100) DEFAULT NULL,
  `no` varchar(100) DEFAULT NULL,
  `addr` varchar(100) DEFAULT NULL,
  `img` varchar(100) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of school
-- ----------------------------
INSERT INTO `school` VALUES ('001', '凯里学院', '01001', '贵州省黔东南州凯里市开发区开元大道3号', '@#￥%&*', 'klxy');

-- ----------------------------
-- Table structure for `stu_family_info`
-- ----------------------------
DROP TABLE IF EXISTS `stu_family_info`;
CREATE TABLE `stu_family_info` (
  `id` varchar(100) NOT NULL,
  `addr` varchar(100) DEFAULT NULL,
  `tel` varchar(100) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `stu_no` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stu_family_info
-- ----------------------------
INSERT INTO `stu_family_info` VALUES ('0001', '雷山', '2222222', '测试2', '2018002');
INSERT INTO `stu_family_info` VALUES ('0002', '十多个', '发到', '大概', '是');
INSERT INTO `stu_family_info` VALUES ('100', '000', '45345', '4', '213');
INSERT INTO `stu_family_info` VALUES ('1544009069121', '贵州省', '3223980', '111', '2017402137');
INSERT INTO `stu_family_info` VALUES ('545', 'f ', '45345', '4563', '564');
INSERT INTO `stu_family_info` VALUES ('93423dba-caae-40ea-bbee-99f70770da99', '32136', '231654', '32165', '2222');

-- ----------------------------
-- Table structure for `stucontatc`
-- ----------------------------
DROP TABLE IF EXISTS `stucontatc`;
CREATE TABLE `stucontatc` (
  `id` varchar(100) NOT NULL,
  `tel` varchar(100) DEFAULT NULL,
  `qq` varchar(100) DEFAULT NULL,
  `weixin` varchar(100) DEFAULT NULL,
  `other` varchar(100) DEFAULT NULL,
  `stu_no` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stucontatc
-- ----------------------------
INSERT INTO `stucontatc` VALUES ('1543835570787', '1111', '11111', '11111', '11111', '1111');
INSERT INTO `stucontatc` VALUES ('1543835580168', 'qqq', 'qq', 'aaa', 'sxs', 'dxcc');
INSERT INTO `stucontatc` VALUES ('1543835592707', '999', 'qq', 'qqqq', 'qqq', 'swsw');
INSERT INTO `stucontatc` VALUES ('1543835600975', 'www', 'www', 'www', 'wwws', 'sssss');
INSERT INTO `stucontatc` VALUES ('1543835618147', 'ededed', 'edede', 'dede', 'dde', 'ded');
INSERT INTO `stucontatc` VALUES ('1543835632602', '1888909898', '167582332', 'wqssas', '123', 'swdc');
INSERT INTO `stucontatc` VALUES ('1543835644950', '009090909', 'e34324', 'ewfrw', 'wdwd', 'wwwwwwwww');
INSERT INTO `stucontatc` VALUES ('1543835952509', '222222', 'wsss', 'cdv', 'vdf v', 'fvdc');
INSERT INTO `stucontatc` VALUES ('1544009517889', '111', 'qqqq', 'qqq', 'qqq', 'qqq');
INSERT INTO `stucontatc` VALUES ('1544251816604', '1123', '11', '11', '11', '321');
INSERT INTO `stucontatc` VALUES ('1544252642483', '18286566256', '863065523', '2233', '123', '2017402120');
INSERT INTO `stucontatc` VALUES ('1544252699665', '123654', '55', '1', 'sad', 'undefined');
INSERT INTO `stucontatc` VALUES ('1544252818938', '12', '12', '12', '21', 'undefined');
INSERT INTO `stucontatc` VALUES ('1544253051135', '213', '132', '132', '123', 'undefined');
INSERT INTO `stucontatc` VALUES ('1544253712615', '010', '010', '010', '101', 'undefined');
INSERT INTO `stucontatc` VALUES ('1547041758703', '16415464541', '561465165', '56165', '51561', '165');
INSERT INTO `stucontatc` VALUES ('2', '1821354628', '2945295399', '爱娃336', '阿斯蒂芬', '2017402218');
INSERT INTO `stucontatc` VALUES ('8ef94c0f-33a4-4fda-989c-8bf3c4096def', '13885540814', '124471982', '12123', '12312', '12313');

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` varchar(255) CHARACTER SET utf8 NOT NULL,
  `no` varchar(255) CHARACTER SET utf8 DEFAULT '',
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `cls` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of student
-- ----------------------------

-- ----------------------------
-- Table structure for `task`
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` varchar(255) NOT NULL COMMENT '任务编号',
  `title` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '0' COMMENT '0未完成，1完成',
  `create_time` datetime DEFAULT NULL,
  `finish_time` datetime DEFAULT NULL,
  `executor` varchar(255) DEFAULT NULL COMMENT '执行人',
  `deadline` datetime DEFAULT NULL COMMENT '截止时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES ('1553187551450', '测试', null, '1', '2019-03-22 00:59:11', '2019-03-22 01:16:35', '1544148462136', '2019-03-22 00:00:00');

-- ----------------------------
-- Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT 'id',
  `username` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '家庭住址',
  `password` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `img` varchar(100) DEFAULT NULL,
  `status` int(1) DEFAULT '1' COMMENT '状态',
  `sex` int(1) DEFAULT '1',
  `birth` date DEFAULT NULL,
  `remark` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('001', 'xiao', '123456', '2019-03-21 18:49:12', null, '0', '1', null, null);
INSERT INTO `user_info` VALUES ('002', 'cc', '654321', '2019-03-21 19:50:49', null, '0', '1', null, null);
INSERT INTO `user_info` VALUES ('111', '战锤2000', '皇帝万岁', '2018-11-16 09:07:44', 'null', '1', '1', null, null);
INSERT INTO `user_info` VALUES ('1543924712930', '66666', 'yyyy', '2018-12-04 19:58:32', '222', '1', '1', null, null);
INSERT INTO `user_info` VALUES ('1547181782185', '周杰伦', '123321', '2019-01-11 12:43:02', 'default', '0', '1', null, null);
INSERT INTO `user_info` VALUES ('1547188014480', '123', '321', '2019-01-11 14:26:54', 'default', '0', '1', null, null);
INSERT INTO `user_info` VALUES ('1547188168186', '10', '111', '2019-01-11 14:29:28', 'default', '0', '1', null, null);
INSERT INTO `user_info` VALUES ('1547212717659', '123456', '000000', '2019-01-11 21:18:37', 'default', '0', '1', null, null);
INSERT INTO `user_info` VALUES ('1547212752867', '888889999', '99898989', '2019-01-11 21:19:12', 'default', '0', '1', null, null);
INSERT INTO `user_info` VALUES ('1547212805195', 'qqqq', 'qqqq', '2019-01-11 21:20:05', 'default', '0', '1', null, null);
INSERT INTO `user_info` VALUES ('1547213129147', 'eewwe', 'rwerwe', '2019-01-11 21:25:29', 'default', '0', '1', null, null);
INSERT INTO `user_info` VALUES ('1551425071312', '1', '1', '2019-03-01 15:24:31', 'default', '0', '1', null, null);
INSERT INTO `user_info` VALUES ('33333', '李颖鹏', 'lyp', '2019-03-20 00:00:00', null, null, '1', null, null);
INSERT INTO `user_info` VALUES ('admin', '超级管理员', '123456', '2019-03-22 09:12:21', null, '0', '1', null, null);

-- ----------------------------
-- View structure for `dt`
-- ----------------------------
DROP VIEW IF EXISTS `dt`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `dt` AS select `a`.`id` AS `id`,`a`.`depname` AS `depname`,`a`.`status` AS `status`,`b`.`status` AS `taskstatus` from (`depman` `a` left join `task` `b` on((`a`.`id` = `b`.`executor`))) order by `b`.`id` ;
