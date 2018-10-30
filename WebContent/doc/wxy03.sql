/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : stu

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2018-10-30 09:59:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `navs`
-- ----------------------------
DROP TABLE IF EXISTS `navs`;
CREATE TABLE `navs` (
  `id` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL COMMENT '显示的标题',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `href` varchar(255) DEFAULT '' COMMENT '访问路径',
  `spread` tinyint(4) NOT NULL DEFAULT '0' COMMENT '页面开始的时候，是否要展开,0：false，1true',
  `node` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0：父节点，1子节点',
  `fid` varchar(255) DEFAULT NULL COMMENT '父id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of navs
-- ----------------------------
INSERT INTO `navs` VALUES ('1', '后台首页', 'icon-computer', 'page/main.html', '0', '0', null);
INSERT INTO `navs` VALUES ('4', '基础数据管理', '&#xe630;', '', '1', '0', null);
INSERT INTO `navs` VALUES ('5', '学生信息管理', 'icon-text', 'admin/students', '0', '1', '4');
INSERT INTO `navs` VALUES ('6', '其他页面', '&#xe630;', '', '1', '0', '');
INSERT INTO `navs` VALUES ('7', '404页面', '&#xe61c;', 'page/404.html', '0', '1', '6');

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
