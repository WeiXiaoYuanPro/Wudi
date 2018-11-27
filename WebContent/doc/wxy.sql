/*
Navicat MySQL Data Transfer

Source Server         : 120.79.188.45_3306
Source Server Version : 50505
Source Host           : 120.79.188.45:3306
Source Database       : wxy

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2018-11-27 20:56:23
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
INSERT INTO `building` VALUES ('5bf0e527-6bde-4085-bbad-224ac13b953e', '二号教学楼', '2', '无', 'add4e444-d4d1-4802-8f98-e824965319df');
INSERT INTO `building` VALUES ('981ade07-0287-4d47-823a-a9e18a08b791', '一号教学楼', '1', '无', 'add4e444-d4d1-4802-8f98-e824965319df');

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
INSERT INTO `classroom` VALUES ('0db5775c-62bc-4288-a792-cf2c22671dcd', 'gf', 'dfg', '55', '235', '55', '54', '4536');
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
INSERT INTO `cmslogin_log` VALUES ('03dfa4b2-8ca5-4cb5-929c-bf450fa305ec', '杨正旺', null, '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `cmslogin_log` VALUES ('3f22338a-00a5-4697-9aba-0571701c2c82', '3', null, '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');
INSERT INTO `cmslogin_log` VALUES ('9c56b91c-b307-4f9f-8121-113063938c8b', 'js', null, '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '0', '0');

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
INSERT INTO `navs` VALUES ('4', '前端信息管理', '&#xe630;', '', '-1');
INSERT INTO `navs` VALUES ('5', '系統管理', '&#xe630;', '', '-1');
INSERT INTO `navs` VALUES ('6', '学生信息管理', 'icon-text', 'admin/students', '2');
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
INSERT INTO `school_zone` VALUES ('081f13a0-0f91-46f5-bebc-6455a64269e5', '凯里学院老校区', '洗马河', '无666', '001');
INSERT INTO `school_zone` VALUES ('add4e444-d4d1-4802-8f98-e824965319df', '凯里学院新校区', '开发区', '无', '001');

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
INSERT INTO `stucontatc` VALUES ('05', '1821354628', '135486295', '爱娃336', '阿斯蒂芬', '2017402218');
INSERT INTO `stucontatc` VALUES ('06', '4561235845', '135486292', '5203146528', '保罗', '2017402218');
INSERT INTO `stucontatc` VALUES ('1', '2', '3', '4', '56', '6');
INSERT INTO `stucontatc` VALUES ('110', '120', '23154685', '56214896512', 'u好啊 阿', '2018402005');
INSERT INTO `stucontatc` VALUES ('123', '110', '2945295366', '1234567895', '你好阿', '2017402216');
INSERT INTO `stucontatc` VALUES ('王驰', '110', '2945295399', '135486529', '詹姆斯', '2017402217');

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
INSERT INTO `stuinfo` VALUES ('11', '111', '0', '111', '111');
INSERT INTO `stuinfo` VALUES ('1111111', '111111111111111', '0', '111111111111111111', '1111111111111111111');
INSERT INTO `stuinfo` VALUES ('2017402120', '黄霜', '0', '1998', 'sss');
INSERT INTO `stuinfo` VALUES ('2017402128', '胡普艳', '0', '1999', 'ssssss');
INSERT INTO `stuinfo` VALUES ('2017402139', '孔倩', '0', '1997', 'qqq');
INSERT INTO `stuinfo` VALUES ('2017402155', 'haha ', '1', '111', '111');
INSERT INTO `stuinfo` VALUES ('2017402210', '王生', '1', '1989', 'ss4444');
INSERT INTO `stuinfo` VALUES ('2017402222', 'www', '0', '333', '222');

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
INSERT INTO `user_info` VALUES ('1000', '1', '1', '2018-11-18 19:34:36', 'default', '0');
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
