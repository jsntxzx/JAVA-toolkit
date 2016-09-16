/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : shishuocms

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2016-09-16 22:39:51
*/
CREATE DATABASE IF NOT EXISTS `bloger` ;
USE `bloger` ;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `adminId` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
  `name` varchar(50) DEFAULT NULL COMMENT '管理员名称',
  `password` varchar(32) DEFAULT NULL COMMENT '密码 MD5加密',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`adminId`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='管理员';

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'bloger', '96e79218965eb72c92a549dd5a330112', '2012-08-08 00:00:00');
INSERT INTO `admin` VALUES ('2', 'top_admin', '96e79218965eb72c92a549dd5a330112', '2016-09-15 22:03:39');
INSERT INTO `admin` VALUES ('3', 'admin', '96e79218965eb72c92a549dd5a330112', '2016-09-16 22:33:32');

-- ----------------------------
-- Table structure for `admin_folder`
-- ----------------------------
DROP TABLE IF EXISTS `admin_folder`;
CREATE TABLE `admin_folder` (
  `adminId` bigint(20) DEFAULT NULL,
  `folderId` bigint(20) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of admin_folder
-- ----------------------------
INSERT INTO `admin_folder` VALUES ('1', '1', '2014-10-29 20:48:42');
INSERT INTO `admin_folder` VALUES ('1', '3', '2014-10-29 20:48:43');
INSERT INTO `admin_folder` VALUES ('1', '4', '2014-10-29 20:48:44');
INSERT INTO `admin_folder` VALUES ('1', '5', '2014-10-29 20:48:45');
INSERT INTO `admin_folder` VALUES ('1', '2', '2014-10-29 20:48:46');
INSERT INTO `admin_folder` VALUES ('3', '1', '2016-09-16 22:33:45');
INSERT INTO `admin_folder` VALUES ('3', '2', '2016-09-16 22:33:47');

-- ----------------------------
-- Table structure for `article`
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `articleId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文件ID',
  `folderId` bigint(20) DEFAULT NULL,
  `path` varchar(200) DEFAULT NULL,
  `adminId` bigint(20) DEFAULT '0' COMMENT '管理员ID',
  `picture` varchar(60) DEFAULT NULL,
  `title` varchar(200) DEFAULT '' COMMENT '文件名称',
  `summary` varchar(2000) DEFAULT NULL,
  `content` mediumtext COMMENT '文件内容',
  `viewCount` int(11) DEFAULT '0' COMMENT '浏览数',
  `commentCount` int(11) DEFAULT '0' COMMENT '评论数',
  `status` varchar(20) DEFAULT 'init' COMMENT '状态：0 隐藏 1 显示',
  `check` enum('yes','no','init') DEFAULT NULL,
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`articleId`),
  KEY `idx_folder` (`status`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='文件';

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('1', '1', '1', '1', '', 'Hello World', 'Hello World!!', '<p>Hello World!!</p><div id=\"xunlei_com_thunder_helper_plugin_d462f475-c18e-46be-bd10-327458d045bd\"></div>', '0', '0', 'display', 'yes', '2014-10-29 00:00:00', '2014-10-29 20:49:54');

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `commentId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `userId` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `fatherId` bigint(20) DEFAULT NULL COMMENT '父评论ID',
  `kindId` bigint(20) DEFAULT NULL,
  `kind` varchar(45) DEFAULT NULL COMMENT '文件ID',
  `name` varchar(45) DEFAULT NULL COMMENT '评论者',
  `email` varchar(45) DEFAULT NULL COMMENT '评论者邮件地址',
  `url` varchar(200) DEFAULT NULL COMMENT '评论者网址',
  `phone` bigint(20) DEFAULT NULL,
  `content` text COMMENT '内容',
  `ip` varchar(45) DEFAULT NULL COMMENT 'Ip',
  `status` varchar(20) DEFAULT NULL COMMENT '状态',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`commentId`),
  KEY `idx_status` (`status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='评论';

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for `config`
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
  `key` varchar(45) NOT NULL COMMENT 'Key',
  `value` varchar(45) DEFAULT NULL COMMENT '值',
  `description` text COMMENT '描述',
  `createTime` datetime DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='网站配置';

-- ----------------------------
-- Records of config
-- ----------------------------
INSERT INTO `config` VALUES ('blog_headline_image_height', '420', '首页头图的高（px）', '2012-08-08 00:00:00');
INSERT INTO `config` VALUES ('blog_headline_image_width', '858', '首页头图的宽（px）', '2012-08-08 00:00:00');
INSERT INTO `config` VALUES ('blog_seo_headline', '师说CMS是用Java开发的内容管理系统', '网站口号', '2012-08-08 00:00:00');
INSERT INTO `config` VALUES ('blog_seo_title', '师说CMS', '网站名称', '2012-08-08 00:00:00');
INSERT INTO `config` VALUES ('blog_static', 'false', '是否启用全站静态化', '2012-08-08 00:00:00');
INSERT INTO `config` VALUES ('blog_template', 'blog', '模板', '2012-08-08 00:00:00');

-- ----------------------------
-- Table structure for `folder`
-- ----------------------------
DROP TABLE IF EXISTS `folder`;
CREATE TABLE `folder` (
  `folderId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '目录ID',
  `fatherId` bigint(20) NOT NULL DEFAULT '0' COMMENT '父亲Id，用于构建目录树',
  `ename` varchar(45) NOT NULL COMMENT '英文名',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '中文名',
  `path` varchar(200) NOT NULL DEFAULT '' COMMENT '路径',
  `content` text,
  `level` tinyint(4) DEFAULT '1' COMMENT '层级',
  `sort` tinyint(4) DEFAULT '0' COMMENT '排序',
  `width` int(11) DEFAULT '0',
  `height` int(11) DEFAULT '0',
  `count` int(11) DEFAULT '0' COMMENT '文件数',
  `status` varchar(20) DEFAULT 'hidden' COMMENT '状态：0 隐藏 1 现实',
  `check` enum('yes','no') DEFAULT 'no',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`folderId`),
  UNIQUE KEY `idx_ename` (`ename`) USING BTREE,
  KEY `idx_status` (`fatherId`,`status`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='目录';

-- ----------------------------
-- Records of folder
-- ----------------------------
INSERT INTO `folder` VALUES ('1', '0', 'blog', '博客', '1', '', '1', '1', '0', '0', '0', 'display', 'no', '2014-10-29 18:37:39', null);
INSERT INTO `folder` VALUES ('2', '0', 'about', '关于博主', '2', '<p>朕就是这样一汉子。<br/></p><div id=\"xunlei_com_thunder_helper_plugin_d462f475-c18e-46be-bd10-327458d045bd\"></div>', '1', '1', '0', '0', '0', 'display', 'no', '2014-10-29 18:38:02', null);
INSERT INTO `folder` VALUES ('3', '1', 'life', '生活', '1#3', '', '2', '1', '0', '0', '0', 'display', 'no', '2014-10-29 20:16:53', null);
INSERT INTO `folder` VALUES ('4', '1', 'work', '工作', '1#4', '', '2', '1', '0', '0', '0', 'display', 'no', '2014-10-29 20:17:06', null);
INSERT INTO `folder` VALUES ('5', '1', 'travel', '旅游', '1#5', '', '2', '1', '0', '0', '0', 'display', 'no', '2014-10-29 20:17:39', null);

-- ----------------------------
-- Table structure for `guestbook`
-- ----------------------------
DROP TABLE IF EXISTS `guestbook`;
CREATE TABLE `guestbook` (
  `guestbookId` bigint(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `website` varchar(100) DEFAULT NULL,
  `title` varchar(200) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `reply` varchar(2000) DEFAULT NULL,
  `status` enum('display','hidden','init') DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `replyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`guestbookId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of guestbook
-- ----------------------------

-- ----------------------------
-- Table structure for `headline`
-- ----------------------------
DROP TABLE IF EXISTS `headline`;
CREATE TABLE `headline` (
  `headlineId` bigint(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `picture` varchar(100) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `sort` tinyint(4) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`headlineId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of headline
-- ----------------------------

-- ----------------------------
-- Table structure for `media`
-- ----------------------------
DROP TABLE IF EXISTS `media`;
CREATE TABLE `media` (
  `mediaId` bigint(20) NOT NULL AUTO_INCREMENT,
  `kindId` bigint(20) DEFAULT '0',
  `name` varchar(200) DEFAULT NULL,
  `path` varchar(200) DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `kind` varchar(20) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`mediaId`),
  KEY `idx_kind` (`kind`,`kindId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=ucs2 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of media
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `openId` bigint(20) DEFAULT NULL COMMENT '公共用户ID，只有是师说，QQ，微博等其它网站登录时才有。',
  `type` varchar(20) DEFAULT NULL COMMENT '帐号类型：0 本站 1 师说 2 QQ 3 微博',
  `name` varchar(45) DEFAULT NULL COMMENT '用户名',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户';

-- ----------------------------
-- Records of user
-- ----------------------------
