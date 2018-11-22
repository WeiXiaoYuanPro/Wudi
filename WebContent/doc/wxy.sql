/*
Navicat MySQL Data Transfer

Source Server         : 120.79.188.45_3306
Source Server Version : 50505
Source Host           : 120.79.188.45:3306
Source Database       : wxy

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2018-11-22 17:42:30
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
  `school_id` varchar(100) DEFAULT NULL COMMENT '学校id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of building
-- ----------------------------
INSERT INTO `building` VALUES ('0323d1cd-b1c1-4b42-b2cc-62ee6051ac02', '33', '333', '3333', '33333');
INSERT INTO `building` VALUES ('2aa764f1-8cd2-4a45-800c-b79f617b2f83', '9', '8', '7', '6');
INSERT INTO `building` VALUES ('352bbab6-7eeb-4259-bb29-ed116538f826', '3', '8', '7', '5');
INSERT INTO `building` VALUES ('ba0d825b-185a-42e7-a05b-1f9ea4ca57e4', '6', '5', '4', '3');

-- ----------------------------
-- Table structure for `classroom`
-- ----------------------------
DROP TABLE IF EXISTS `classroom`;
CREATE TABLE `classroom` (
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
-- Records of classroom
-- ----------------------------
INSERT INTO `classroom` VALUES ('005bc058-2df1-4072-9a2b-402f8f183614', '3', '3', '3', '5', '8', '9', '7');
INSERT INTO `classroom` VALUES ('76dccdae-db28-488e-b79a-ef81b9c0dd88', '2015', '7栋', '12123', '5', '6', '7', '8');

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
INSERT INTO `cms_user` VALUES ('11fae6c8-2ac4-4ce9-9268-78a26508976b', '徐益', '222', '2018-11-19 19:21:04', '222', '222', '222');
INSERT INTO `cms_user` VALUES ('5ec64f2f-d804-4114-a061-19173426f69d', '123', '123', '2018-11-19 20:38:36', '123', '1', '1');
INSERT INTO `cms_user` VALUES ('aa794989-4d40-42be-980e-2a82d4be6511', '杨正旺', '8023', '2018-11-19 16:25:42', '1', '0', '0');
INSERT INTO `cms_user` VALUES ('f4dbf9fd-42d6-4d02-aec1-2325c4a7dce8', 'eeee', 'dddd', '2018-11-21 20:36:03', '', '1', '0');

-- ----------------------------
-- Table structure for `cmslogin_log`
-- ----------------------------
DROP TABLE IF EXISTS `cmslogin_log`;
CREATE TABLE `cmslogin_log` (
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
-- Records of cmslogin_log
-- ----------------------------
INSERT INTO `cmslogin_log` VALUES ('2', '2', null, null, null, null, null);
INSERT INTO `cmslogin_log` VALUES ('3', '2', null, null, null, null, null);
INSERT INTO `cmslogin_log` VALUES ('4', '4', null, null, null, null, null);
INSERT INTO `cmslogin_log` VALUES ('45', '4', null, null, null, null, null);

-- ----------------------------
-- Table structure for `dormitory`
-- ----------------------------
DROP TABLE IF EXISTS `dormitory`;
CREATE TABLE `dormitory` (
  `id` varchar(100) NOT NULL COMMENT 'Id',
  `name` varchar(255) DEFAULT NULL COMMENT '宿舍名称',
  `building_id` varchar(255) DEFAULT NULL COMMENT '楼号 id',
  `capacity` int(3) DEFAULT NULL COMMENT '容量',
  `type` int(2) DEFAULT NULL COMMENT '种类',
  `status` int(2) DEFAULT NULL COMMENT '状态',
  `latitude` decimal(10,0) DEFAULT NULL COMMENT '纬度',
  `longitude` decimal(10,0) DEFAULT NULL COMMENT '经度',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dormitory
-- ----------------------------
INSERT INTO `dormitory` VALUES ('2', '204', '7栋', '4', '1', '5', '1010', '8');
INSERT INTO `dormitory` VALUES ('3', '208', '7栋', '4', '1', '6', '9', '7');
INSERT INTO `dormitory` VALUES ('b13bc79e-e9fc-4cfd-9c0f-cea5ffa16a84', '211', '7栋', '5', '5', '4', '8', '9');

-- ----------------------------
-- Table structure for `navs`
-- ----------------------------
DROP TABLE IF EXISTS `navs`;
CREATE TABLE `navs` (
  `id` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL COMMENT '显示的标题',
  `icon` varchar(255) DEFAULT '&#xe630;' COMMENT '图标',
  `href` varchar(255) DEFAULT '' COMMENT '访问路径',
  `fid` varchar(255) DEFAULT NULL COMMENT '父id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of navs
-- ----------------------------
INSERT INTO `navs` VALUES ('1', '后台首页', '&#xe630;', 'page/main.html', '-1');
INSERT INTO `navs` VALUES ('10', '学校楼信息', '&#xe630;', 'admin/building', '2');
INSERT INTO `navs` VALUES ('11', '学生信息', '&#xe630;', 'admin/stuinfos', '2');
INSERT INTO `navs` VALUES ('12', '教室信息', '&#xe630;', 'admin/classroom', '2');
INSERT INTO `navs` VALUES ('13', '学生联系', '&#xe630;', 'admin/stucontatc', '2');
INSERT INTO `navs` VALUES ('2', '基础信息管理', '&#xe630;', '', '-1');
INSERT INTO `navs` VALUES ('3', '系統管理', '&#xe630;', '', '-1');
INSERT INTO `navs` VALUES ('4', '学生信息管理', 'icon-text', 'admin/students', '2');
INSERT INTO `navs` VALUES ('4fca5fb2-8101-4f61-bf1d-405307908d4e', '西瓜刀的博客qw', '&#xe630;', '/', '-1');
INSERT INTO `navs` VALUES ('5', '404页面', '&#xe61c;', 'page/404.html', '3');
INSERT INTO `navs` VALUES ('54d59bf9-21c6-4d25-92a0-60338f1572b2', '用户信息', '&#xe630;', 'admin/userInfo', '2');
INSERT INTO `navs` VALUES ('5908b6fc-2e11-45ea-a79a-220dd684474a', '测试', '&#xe630;', 'admin/navsinfo', '2');
INSERT INTO `navs` VALUES ('6', '菜单管理', 'icon-text', 'admin/navsinfo', '3');
INSERT INTO `navs` VALUES ('6eaa2397-45b1-4dd4-82b8-542a7eb6bf4d', '角色信息', '&#xe630;', 'admin/role', '3');
INSERT INTO `navs` VALUES ('7', '学生家庭信息', '&#xe61c;', 'admin/stu_families', '2');
INSERT INTO `navs` VALUES ('8', '宿舍管理', '&#xe61c;', 'admin/dormitory', '2');
INSERT INTO `navs` VALUES ('83fc2229-6ddb-4f40-b4e7-fe1fb245c1cd', '登录日志记录管理', '&#xe630;', 'admin/cmslogin_log', '2');
INSERT INTO `navs` VALUES ('9a236434-97a8-4aed-baa4-8af162655d79', '用户信息管理', '&#xe630;', 'admin/cms_user', '2');
INSERT INTO `navs` VALUES ('c3d61e00-a7d7-4a06-8609-63829590b16a', '11)	学校楼房信息', '&#xe630;', 'admin/schoolzone', '2');
INSERT INTO `navs` VALUES ('db7b8dca-1f85-4c59-acbb-d45951e1eee7', 'cc', '&#xe630;', 'aaaa', '1');
INSERT INTO `navs` VALUES ('f0473551-04c6-48b6-bb2c-0e2e5d215ca4', '学校信息管理', '&#xe630;', 'admin/schools', '2');

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
INSERT INTO `role_info` VALUES ('02', '3', '4', '5', '6', '7');
INSERT INTO `role_info` VALUES ('03', 'erw', '4', 'dssa', 'xzc', 'fd');
INSERT INTO `role_info` VALUES ('1', '2', '3', '4', '5', '6');
INSERT INTO `role_info` VALUES ('7', '8', '9', '4', '5', '6');

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
INSERT INTO `school` VALUES ('003', '贵州大学', '04', 'guiyang', '@', 'h');
INSERT INTO `school` VALUES ('333', '333', '333', '3333', '333', '333');

-- ----------------------------
-- Table structure for `school_zone`
-- ----------------------------
DROP TABLE IF EXISTS `school_zone`;
CREATE TABLE `school_zone` (
  `id` varchar(100) NOT NULL COMMENT 'id',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `addr` varchar(100) DEFAULT NULL COMMENT '经度纬度',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `school_id` varchar(100) DEFAULT NULL COMMENT '学校id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of school_zone
-- ----------------------------
INSERT INTO `school_zone` VALUES ('1', '11', '111', '111', '1111');
INSERT INTO `school_zone` VALUES ('36340319-8bfb-4afd-b3a3-69a6c2454e33', '1', '1', '1', '1');
INSERT INTO `school_zone` VALUES ('b1c0d59c-dd25-45c1-9138-c7cef1890ffd', '3', '4', '5', '6');

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
INSERT INTO `stu_family_info` VALUES ('545', 'f ', '45345', '4563', '564');

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
INSERT INTO `stucontatc` VALUES ('110', '120', '23154685', '56214896512', 'u好啊 阿', '2018402005');
INSERT INTO `stucontatc` VALUES ('123', '110', '2945295366', '1234567895', '你好阿', '2017402216');

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
INSERT INTO `student` VALUES ('1', '1', '1', '1', '2');
INSERT INTO `student` VALUES ('222', '222', '胜多负少sd实打实地方', '333', '1');
INSERT INTO `student` VALUES ('2222', '2222', 'www', '333', '1');
INSERT INTO `student` VALUES ('44', '44', '梁老', '宿舍', '1');
INSERT INTO `student` VALUES ('444', '444', '44', '44', '1');

-- ----------------------------
-- Table structure for `stuinfo`
-- ----------------------------
DROP TABLE IF EXISTS `stuinfo`;
CREATE TABLE `stuinfo` (
  `no` varchar(100) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `sex` int(1) DEFAULT NULL,
  `birth` varchar(100) DEFAULT NULL,
  `img` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stuinfo
-- ----------------------------
INSERT INTO `stuinfo` VALUES ('2017402120', '黄霜', '0', '1998', 'sss');
INSERT INTO `stuinfo` VALUES ('2017402128', '胡普艳', '0', '1999', 'ssssss');
INSERT INTO `stuinfo` VALUES ('2017402139', '孔倩', '0', '1997', 'qqq');
INSERT INTO `stuinfo` VALUES ('2017402210', '王生', '1', '1989', 'ss4444');

-- ----------------------------
-- Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT 'id',
  `username` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '家庭住址',
  `password` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `create_time` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `img` varchar(100) DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('38141a48-6bba-4245-9f2e-ea625f3611ec', '战锤2000', '皇帝万岁', '2018-11-16 09:07:44', 'null', '1');
INSERT INTO `user_info` VALUES ('7b4c35ee-fb1e-476f-ae96-073db0580083', '荒野大镖客', '一人一狗', '2018-11-16 09:05:32', 'null', '0');
INSERT INTO `user_info` VALUES ('b856e712-ffb2-4569-ba29-ff678b558887', '3', '3', '2018-11-18 19:34:42', 'default', '0');
INSERT INTO `user_info` VALUES ('c4ba1f7a-1a1f-4677-ad05-a5b8150d2ffa', '2', '2', '2018-11-18 19:34:39', '564', '0');
INSERT INTO `user_info` VALUES ('c95e0299-029b-4d0b-b672-899a5a0feab6', '1', '1', '2018-11-18 19:34:36', 'default', '0');

-- ----------------------------
-- Table structure for `userloginlog`
-- ----------------------------
DROP TABLE IF EXISTS `userloginlog`;
CREATE TABLE `userloginlog` (
  `id` varchar(100) NOT NULL,
  `username` varchar(100) DEFAULT NULL,
  `login_time` varchar(100) DEFAULT NULL,
  `ip` varchar(100) DEFAULT NULL,
  `addr` varchar(100) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userloginlog
-- ----------------------------
