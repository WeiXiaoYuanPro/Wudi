/*
Navicat MySQL Data Transfer

Source Server         : 120.79.188.45_3306
Source Server Version : 50505
Source Host           : 120.79.188.45:3306
Source Database       : wxy

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2018-11-30 16:50:28
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
  `schoolzone_id` varchar(100) DEFAULT NULL COMMENT '分校id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of building
-- ----------------------------
INSERT INTO `building` VALUES ('0cf0f24f-0dea-421b-b56a-816917575676', 'sss', 'qq1', '111', 'add4e444-d4d1-4802-8f98-e824965319df');
INSERT INTO `building` VALUES ('246e1c81-e060-435a-9031-da6159f35b18', '3栋', '120', '220', 'add4e444-d4d1-4802-8f98-e824965319df');
INSERT INTO `building` VALUES ('55cc6c32-d5e5-41b9-8d0c-facaf944d719', '111', '111', '111', '081f13a0-0f91-46f5-bebc-6455a64269e5');
INSERT INTO `building` VALUES ('5bf0e527-6bde-4085-bbad-224ac13b953e', '二号教学楼', '2', '无', 'add4e444-d4d1-4802-8f98-e824965319df');
INSERT INTO `building` VALUES ('5df4332d-5e17-458d-869f-78e24a05df2a', '看', null, null, null);
INSERT INTO `building` VALUES ('5ea8859d-01e6-4e70-8305-5b6da080e6e2', '贵州师范大学', '凯里', '22222', null);
INSERT INTO `building` VALUES ('607647aa-ca4d-4bf4-b989-161532f1ec44', '111', null, null, null);
INSERT INTO `building` VALUES ('6e46eaf7-0ff3-4eac-ba17-2f78b5d5f587', null, null, null, null);
INSERT INTO `building` VALUES ('8602f175-3c83-4984-b734-3eab827cb00d', '222', '222', '111', 'add4e444-d4d1-4802-8f98-e824965319df');
INSERT INTO `building` VALUES ('981ade07-0287-4d47-823a-a9e18a08b791', '一号教学楼', '1', '无', 'add4e444-d4d1-4802-8f98-e824965319df');
INSERT INTO `building` VALUES ('9f64e48e-cbaf-4dd5-866c-65c059f7377c', null, null, null, null);
INSERT INTO `building` VALUES ('d6d38298-95aa-4856-9d8c-958c37021c90', 'i', null, null, null);
INSERT INTO `building` VALUES ('e237692e-512b-4818-b891-4b328215358f', '111', '111111', '1', 'add4e444-d4d1-4802-8f98-e824965319df');

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
INSERT INTO `classroom` VALUES ('0db5775c-62bc-4288-a792-cf2c22671dcd', 'gf', 'dfg', '55', '235', '55', '54', '4536');
INSERT INTO `classroom` VALUES ('149424d8-c720-449b-8eb3-cdbaeabdf499', '1', '2', '3', '4', '5', '6', '7');
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
INSERT INTO `cms_user` VALUES ('44e25748-d3bd-4198-b85f-75cb49d6a1ea', 'js', '111', '2018-11-22 21:00:42', '', '1', '0');
INSERT INTO `cms_user` VALUES ('913d0112-eb03-4ecd-aaea-b01063a7a719', 'read', 'read', '2018-11-28 18:06:34', '', '0', '0');
INSERT INTO `cms_user` VALUES ('aa794989-4d40-42be-980e-2a82d4be6511', '杨正旺', '8023', '2018-11-19 16:25:42', '1', '0', '0');
INSERT INTO `cms_user` VALUES ('b07c4ba7-d26e-44f3-a00a-af22f5e550b5', '3', '3', '2018-11-22 21:02:15', '', '1', '0');

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
INSERT INTO `cmslogin_log` VALUES ('03401e66-cd67-4c64-a316-8667fbebee38', 'js', '2018-11-28 :06:00:55', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `cmslogin_log` VALUES ('079a6285-ad32-44e9-95f3-62c03d9cfcf5', '3', '2018-11-28 07:05:28', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `cmslogin_log` VALUES ('1543567291333', '3', '2018-11-30 04:41:31', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `cmslogin_log` VALUES ('194f7107-fec9-4bfd-9ed7-3e63b6b759c2', '杨正旺', '2018-11-28 19:57:59', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', 'ok', '0');
INSERT INTO `cmslogin_log` VALUES ('22106df7-08db-43e4-988d-c594c26c46f0', '3', '2018-11-28 07:51:53', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `cmslogin_log` VALUES ('314e4f7c-db8d-4f11-8bbe-14dacd6a8338', 'js', '2018-11-28 10:19:59', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `cmslogin_log` VALUES ('47c7bd7d-b094-40db-bf04-0a3413aafe83', '3', '2018-11-28 :06:08:02', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `cmslogin_log` VALUES ('4ad1f8e2-9c93-412b-8dff-27b79449f0b0', '3', '2018-11-28 19:57:31', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', 'ok', '0');
INSERT INTO `cmslogin_log` VALUES ('81c43bc1-27e5-4559-b9d3-5db4e8e9de6f', 'read', '2018-11-28 :06:07:10', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `cmslogin_log` VALUES ('84ae051d-06ea-4e6e-8b2d-e830d14f8b6d', 'read', '2018-11-28 07:19:03', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `cmslogin_log` VALUES ('98cc0a53-b05e-48e4-990c-33dde3d8b2b8', '3', '2018-11-28 09:46:41', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `cmslogin_log` VALUES ('b5ab9635-cf64-46d7-a4d1-556bc61bbc55', '杨正旺', '2018-11-28 :06:03:07', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `cmslogin_log` VALUES ('df874fdd-b23d-47fd-8fb0-0ee1967d5029', 'js', '2018-11-28 07:18:32', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');

-- ----------------------------
-- Table structure for `depman`
-- ----------------------------
DROP TABLE IF EXISTS `depman`;
CREATE TABLE `depman` (
  `id` varchar(255) NOT NULL,
  `depname` varchar(255) DEFAULT NULL COMMENT '用户名',
  `status` tinyint(4) DEFAULT '0' COMMENT '0工作，1离职',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of depman
-- ----------------------------
INSERT INTO `depman` VALUES ('10', '吴海', '1');
INSERT INTO `depman` VALUES ('11', '唐国继', '1');
INSERT INTO `depman` VALUES ('12', '陈糠杰', '1');
INSERT INTO `depman` VALUES ('13', '龙明涛', '1');
INSERT INTO `depman` VALUES ('14', '蒋霜', '1');
INSERT INTO `depman` VALUES ('22', '肖老师', '1');
INSERT INTO `depman` VALUES ('23', '梁老师', '1');
INSERT INTO `depman` VALUES ('24', '李老师', '1');
INSERT INTO `depman` VALUES ('4', '李颖鹏', '1');
INSERT INTO `depman` VALUES ('5', '卢英剑', '1');
INSERT INTO `depman` VALUES ('6', '李金鹏', '1');
INSERT INTO `depman` VALUES ('7', '王驰', '1');
INSERT INTO `depman` VALUES ('8', '李洋', '1');
INSERT INTO `depman` VALUES ('9', '杨正旺', '1');

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
INSERT INTO `dormitory` VALUES ('11c5b664-f311-4db1-8ea0-1c6206ba8bed', '20', '20', '0', '5', '5', '5', '5');
INSERT INTO `dormitory` VALUES ('3dda6897-54d3-4f17-bc77-57dc1505c344', '7', '7', '7', '7', '7', '7', '7');
INSERT INTO `dormitory` VALUES ('b13bc79e-e9fc-4cfd-9c0f-cea5ffa16a84', '211', '7栋', '5', '5', '4', '8', '9');
INSERT INTO `dormitory` VALUES ('c9a9d258-3054-47cf-9880-b806658f3291', '9', '9', '9', '9', '9', '9', '9');

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
INSERT INTO `navs` VALUES ('10', '学生信息', '&#xe630;', 'admin/stuinfos', '2');
INSERT INTO `navs` VALUES ('11', '学校楼信息', '&#xe630;', 'admin/building', '3');
INSERT INTO `navs` VALUES ('12', '学生联系', '&#xe630;', 'admin/stu_contatc', '2');
INSERT INTO `navs` VALUES ('13', '用户信息', '&#xe630;', 'admin/userInfo', '4');
INSERT INTO `navs` VALUES ('15', '登录日志记录管理', '&#xe630;', 'admin/cmslogin_log', '5');
INSERT INTO `navs` VALUES ('16', '用户信息管理', '&#xe630;', 'admin/cms_user', '5');
INSERT INTO `navs` VALUES ('17', '客户端登录日志管理', '&#xe630;', 'admin/userloginlog', '4');
INSERT INTO `navs` VALUES ('19', '宿舍管理', '&#xe61c;', 'admin/dormitory', '3');
INSERT INTO `navs` VALUES ('2', '学生基础信息管理', '&#xe630;', '', '-1');
INSERT INTO `navs` VALUES ('20', '404页面', '&#xe61c;', 'page/404.html', '5');
INSERT INTO `navs` VALUES ('21', '菜单管理', 'icon-text', 'admin/navsinfo', '5');
INSERT INTO `navs` VALUES ('22', '角色信息', '&#xe630;', 'admin/role', '5');
INSERT INTO `navs` VALUES ('3', '学校基本信息管理', '&#xe630;', '', '-1');
INSERT INTO `navs` VALUES ('3f596ea9-a2a4-4e8a-9f27-3028bb8defcf', '任务列表', '&#xe630;', 'admin/openTaskList', '5');
INSERT INTO `navs` VALUES ('4', '前端信息管理', '&#xe630;', '', '-1');
INSERT INTO `navs` VALUES ('5', '系統管理', '&#xe630;', '', '-1');
INSERT INTO `navs` VALUES ('7', '学生家庭信息', '&#xe61c;', 'admin/stu_families', '2');
INSERT INTO `navs` VALUES ('8', '学校信息管理', '&#xe630;', 'admin/schools', '3');
INSERT INTO `navs` VALUES ('9', '分校区信息管理', '&#xe630;', 'admin/schoolzone', '3');
INSERT INTO `navs` VALUES ('92', '教室信息', '&#xe630;', 'admin/classroom', '3');

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
INSERT INTO `role_info` VALUES ('01', '1', '5', '1', '1', '1');
INSERT INTO `role_info` VALUES ('02', '3', '4', '5', '6', '7');
INSERT INTO `role_info` VALUES ('03', 'erw', '4', 'dssa', 'xzc', '10');
INSERT INTO `role_info` VALUES ('100', '2', '3', '4', '5', '10');

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
INSERT INTO `school_zone` VALUES ('00474873-b45d-4c2d-af45-1488dbc19564', '222', '222', '22222', '003');
INSERT INTO `school_zone` VALUES ('081f13a0-0f91-46f5-bebc-6455a64269e5', '凯里学院老校区', '洗马河', '无666', '001');
INSERT INTO `school_zone` VALUES ('09719a3d-e64a-4e13-aaf4-5e6b7cfde2f7', '123123', '213', '2131', '-1');
INSERT INTO `school_zone` VALUES ('0d2165c8-7f79-4087-9c66-a2b928c1a01a', '31', '2', '', '-1');
INSERT INTO `school_zone` VALUES ('1abbf040-4b48-4fd1-b052-bb0ed71e8cac', '23', '12', '', '-1');
INSERT INTO `school_zone` VALUES ('266b4d29-05ee-4462-b256-375ae360d590', '111', '1111', '1111', '001');
INSERT INTO `school_zone` VALUES ('2e8ab6b4-8f53-4f7d-8682-6410149975b3', '3', '1', '', '-1');
INSERT INTO `school_zone` VALUES ('76c34f0c-35f1-4b9a-804f-cb3f9361a86a', '12', '3', '', '-1');
INSERT INTO `school_zone` VALUES ('790f928f-60a3-40cd-a036-6c3605c25e76', '31231', '12312', '31231', '-1');
INSERT INTO `school_zone` VALUES ('87abc9ca-6c6a-446f-b9c7-07a75a845a00', '2222', '222', '222', '001');
INSERT INTO `school_zone` VALUES ('9af2fbca-340c-408e-80ab-fb0bb3cec237', '3', '2', '', '-1');
INSERT INTO `school_zone` VALUES ('9d7eb250-d2bc-4a13-a769-839a7dbb73f6', '3123', '213', '2131', '-1');
INSERT INTO `school_zone` VALUES ('a02d754b-1f96-4248-9b7b-78548a03a483', '3', '12', '', '-1');
INSERT INTO `school_zone` VALUES ('a39ff134-8176-4e99-a136-1581c1b1df77', '1', '3123', '', '-1');
INSERT INTO `school_zone` VALUES ('add4e444-d4d1-4802-8f98-e824965319df', '凯里学院新校区', '开发区', '无', '001');
INSERT INTO `school_zone` VALUES ('b94e3f4d-40ac-4964-a648-08f77ed9815b', '凯里学院1', '经济开发区3号', '1232', '001');
INSERT INTO `school_zone` VALUES ('bf8223d1-cbb9-4197-a431-0ec0ef06a02d', '3', '12', '', '-1');
INSERT INTO `school_zone` VALUES ('c448fa2f-5d8a-497b-81ea-d0fa620284de', '3123', '1312', '12312', '-1');
INSERT INTO `school_zone` VALUES ('d7921b78-3d6d-4af6-a005-4841724426a0', '12', '3', '', '-1');
INSERT INTO `school_zone` VALUES ('f74be904-a027-4b7e-87b1-c2c13f0753be', '2222', '222', '22222', '001');

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
INSERT INTO `stuinfo` VALUES ('2017402155', '吴吉吉', '1', '2018-10-28', '恩恩额');
INSERT INTO `stuinfo` VALUES ('2017402210', '王生', '1', '1989', 'ss4444');

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
INSERT INTO `task` VALUES ('1543484956768', '测试3', null, '1', '2018-11-29 17:49:16', '2018-11-29 18:52:12', '22', '2018-11-30 00:00:00');
INSERT INTO `task` VALUES ('1543485309133', '测试4', null, '0', '2018-11-29 17:55:09', null, '23', '2018-11-29 00:00:00');
INSERT INTO `task` VALUES ('1543485750280', '测试5', null, '0', '2018-11-29 18:02:30', null, '24', '2018-11-29 18:02:25');
INSERT INTO `task` VALUES ('1543550355025', '测试6', null, '0', '2018-11-30 11:59:15', null, '22', '2018-11-30 11:59:11');
INSERT INTO `task` VALUES ('1543550416984', '444', null, '0', '2018-11-30 12:00:16', null, '23', '2018-11-30 12:00:08');
INSERT INTO `task` VALUES ('1543565832662', '任务列表点击功能', null, '0', '2018-11-30 16:17:12', null, '22', '2018-11-30 16:16:48');
INSERT INTO `task` VALUES ('1543565941465', '图片上传功能研究', null, '0', '2018-11-30 16:19:01', null, '5', '2018-11-30 16:18:40');
INSERT INTO `task` VALUES ('1543565941469', '测试一下', null, '0', '2018-11-29 11:05:27', null, '22', '2018-11-29 00:00:00');
INSERT INTO `task` VALUES ('1543566249739', '客户端登陆接口调试', null, '0', '2018-11-30 16:24:09', null, '6', '2018-11-30 16:24:05');
INSERT INTO `task` VALUES ('1543566281034', '客户端退出接口调试', null, '0', '2018-11-30 16:24:41', null, '6', '2018-12-06 00:00:00');
INSERT INTO `task` VALUES ('1543566319665', '后台管理系统登录功能', null, '1', '2018-11-30 16:25:19', '2018-11-30 16:43:39', '4', '2018-12-07 16:25:08');
INSERT INTO `task` VALUES ('1543566356713', '后台管理系统登录日志', null, '0', '2018-11-30 16:25:56', null, '8', '2018-12-07 00:00:00');
INSERT INTO `task` VALUES ('1543566400962', '测试任务', null, '0', '2018-11-30 16:26:40', null, '14', '2018-12-07 00:00:00');
INSERT INTO `task` VALUES ('1543566440872', '测试任务，功能完善', null, '0', '2018-11-30 16:27:20', null, '9', '2018-12-07 00:00:00');
INSERT INTO `task` VALUES ('1543566668131', '主页图表显示', null, '0', '2018-11-30 16:31:08', null, '7', '2018-12-03 00:00:00');

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
INSERT INTO `user_info` VALUES ('111', '战锤2000', '皇帝万岁', '2018-11-16 09:07:44', 'null', '1');
INSERT INTO `user_info` VALUES ('7b4c35ee-fb1e-476f-ae96-073db0580083', '荒野大镖客', '一人一狗', '2018-11-16 09:05:32', 'null', '0');
INSERT INTO `user_info` VALUES ('b856e712-ffb2-4569-ba29-ff678b558887', '3', '3', '2018-11-18 19:34:42', 'default', '0');
INSERT INTO `user_info` VALUES ('c4ba1f7a-1a1f-4677-ad05-a5b8150d2ffa', '2', '2', '2018-11-18 19:34:39', '564', '0');

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

-- ----------------------------
-- View structure for `dt`
-- ----------------------------
DROP VIEW IF EXISTS `dt`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `dt` AS select `a`.`id` AS `id`,`a`.`depname` AS `depname`,`a`.`status` AS `status`,`b`.`status` AS `taskstatus` from (`depman` `a` left join `task` `b` on((`a`.`id` = `b`.`executor`))) order by `b`.`id` ;
