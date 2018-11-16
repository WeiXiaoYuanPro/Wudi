/*
Navicat MySQL Data Transfer

Source Server         : 阿里云120.79.188.45
Source Server Version : 50505
Source Host           : 120.79.188.45:3306
Source Database       : wxy

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2018-11-16 16:36:01
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
INSERT INTO `building` VALUES ('1', '3栋', '1', '12', '大数据工程学院');
INSERT INTO `building` VALUES ('2', '4栋', '1', '32', '外国语学院');

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
INSERT INTO `classroom` VALUES ('1', '2', '3', '4', '5', '6', '7', '8');
INSERT INTO `classroom` VALUES ('2', '3', '4', '5', '6', '7', '8', '9');
INSERT INTO `classroom` VALUES ('3', '4', '5', '6', '7', '8', '9', '0');
INSERT INTO `classroom` VALUES ('4', 'fg', '放到', '56', '56', '45', '866', '65653');

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
INSERT INTO `dormitory` VALUES ('1', '2', '3', '4', '5', '6', '7', '8');
INSERT INTO `dormitory` VALUES ('2', '沙发上', '沙发', '4', '1', '4', '600', '50');

-- ----------------------------
-- Table structure for `navs`
-- ----------------------------
DROP TABLE IF EXISTS `navs`;
CREATE TABLE `navs` (
  `id` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL COMMENT '显示的标题',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `href` varchar(255) DEFAULT '' COMMENT '访问路径',
  `fid` varchar(255) DEFAULT NULL COMMENT '父id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of navs
-- ----------------------------
INSERT INTO `navs` VALUES ('1', '后台首页', 'icon-computer', 'page/main.html', '-1');
INSERT INTO `navs` VALUES ('2', '基础数据管理', '&#xe630;', '', '-1');
INSERT INTO `navs` VALUES ('3', '系統管理', '&#xe630;', '', '-1');
INSERT INTO `navs` VALUES ('4', '学生信息管理', 'icon-text', 'admin/students', '2');
INSERT INTO `navs` VALUES ('5', '404页面', '&#xe61c;', 'page/404.html', '3');
INSERT INTO `navs` VALUES ('6', '菜单管理', 'icon-text', 'admin/navsinfo', '3');

-- ----------------------------
-- Table structure for `stu_contact`
-- ----------------------------
DROP TABLE IF EXISTS `stu_contact`;
CREATE TABLE `stu_contact` (
  `id` varchar(100) NOT NULL,
  `tel` varchar(100) DEFAULT NULL,
  `qq` varchar(100) DEFAULT NULL,
  `weixin` varchar(100) DEFAULT NULL,
  `other` varchar(100) DEFAULT NULL,
  `stu_no` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stu_contact
-- ----------------------------
INSERT INTO `stu_contact` VALUES ('110', '120', '23154685', '56214896512', 'u好啊 阿', '2018402005');
INSERT INTO `stu_contact` VALUES ('123', '110', '2945295366', '1234567895', '你好阿', '2017402216');

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
INSERT INTO `student` VALUES ('222', '222', '胜多负少sd实打实地方', '是的发发发', '1');
INSERT INTO `student` VALUES ('2222', '2222', '222', '333', '1');
INSERT INTO `student` VALUES ('44', '44', '梁老师嗯嗯', '宿舍', '1');
INSERT INTO `student` VALUES ('444', '444', '44', '44', '1');
