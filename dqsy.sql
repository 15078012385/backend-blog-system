/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : dqsy

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 03/07/2023 11:13:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_article
-- ----------------------------
DROP TABLE IF EXISTS `tb_article`;
CREATE TABLE `tb_article`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `publish_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发布用户',
  `article_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标题',
  `article_introduction` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '文章简介',
  `article_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '内容',
  `publish_time` datetime NULL DEFAULT NULL COMMENT '发布时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `views` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '文章浏览量',
  `first_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'https://up.enterdesk.com/edpic_source/b0/eb/8b/b0eb8bf2f1865c157454bc2c6bfd165c.jpg' COMMENT '首图',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发布者头像',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 685 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_article
-- ----------------------------

-- ----------------------------
-- Table structure for tb_file
-- ----------------------------
DROP TABLE IF EXISTS `tb_file`;
CREATE TABLE `tb_file`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '文件名称',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '文件类型',
  `size` bigint NULL DEFAULT NULL COMMENT '文件大小(kb)',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '下载链接',
  `md5` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '文件md5',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  `enable` tinyint(1) NULL DEFAULT 1 COMMENT '是否禁用链接',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 240 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_file
-- ----------------------------

-- ----------------------------
-- Table structure for tb_hot_search
-- ----------------------------
DROP TABLE IF EXISTS `tb_hot_search`;
CREATE TABLE `tb_hot_search`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `hot_search_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '内容',
  `search_count` bigint NULL DEFAULT NULL COMMENT '热搜指数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 126 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_hot_search
-- ----------------------------
INSERT INTO `tb_hot_search` VALUES (125, '你好呀', 1);

-- ----------------------------
-- Table structure for tb_img
-- ----------------------------
DROP TABLE IF EXISTS `tb_img`;
CREATE TABLE `tb_img`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片url',
  `upload_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '上传人',
  `upload_time` datetime NULL DEFAULT NULL COMMENT '上传时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 141 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_img
-- ----------------------------

-- ----------------------------
-- Table structure for tb_like
-- ----------------------------
DROP TABLE IF EXISTS `tb_like`;
CREATE TABLE `tb_like`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `article_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文章作者',
  `article_id` int NULL DEFAULT NULL COMMENT '文章ID',
  `article_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文章标题',
  `like_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '点赞人',
  `like_time` datetime NULL DEFAULT NULL COMMENT '点赞时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 141 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_like
-- ----------------------------

-- ----------------------------
-- Table structure for tb_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_log`;
CREATE TABLE `tb_log`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `access_time` datetime NULL DEFAULT NULL COMMENT '访问时间',
  `access_api` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '接口地址',
  `access_params` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '入参',
  `resp_data` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '出参',
  `access_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '访问IP',
  `time_consume` bigint NULL DEFAULT NULL COMMENT '耗时ms',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11166 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_log
-- ----------------------------

-- ----------------------------
-- Table structure for tb_notice
-- ----------------------------
DROP TABLE IF EXISTS `tb_notice`;
CREATE TABLE `tb_notice`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `notice_time` datetime NULL DEFAULT NULL COMMENT '发布日期',
  `notice_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '公告内容',
  `publish_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发布用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 116 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_notice
-- ----------------------------
INSERT INTO `tb_notice` VALUES (106, '2023-05-15 12:30:00', '需求分析：确定系统的功能需求和性能需求，确定所需的技术栈和开发环境。', '董芯彤');
INSERT INTO `tb_notice` VALUES (107, '2023-05-17 12:30:00', '系统设计：设计系统的架构和模块划分，确定数据库表结构和API接口设计。', '马宏宇');
INSERT INTO `tb_notice` VALUES (108, '2023-05-20 12:30:00', '前端开发：使用HTML、CSS和JavaScript等技术，实现用户界面的交互效果和功能。', '李增艳');
INSERT INTO `tb_notice` VALUES (110, '2023-05-25 12:30:00', '后端开发：使用Java或其他编程语言，编写服务器端程序，实现业务逻辑和数据处理。', '王海瑞');
INSERT INTO `tb_notice` VALUES (111, '2023-05-27 11:32:40', '测试与调试：对系统进行功能测试、性能测试和安全测试，发现并修复系统中的bug。', '田晴晴');
INSERT INTO `tb_notice` VALUES (112, '2023-06-03 12:30:00', '系统上线：将系统部署到生产环境中，进行全面测试和上线运维。', '韩沂桐');
INSERT INTO `tb_notice` VALUES (113, '2023-06-01 12:30:00', '系统维护：对系统进行日常维护和升级，修复系统中的漏洞和bug，保证系统的稳定性和安全性。', '董卓一');
INSERT INTO `tb_notice` VALUES (114, '2023-06-07 12:30:00', '后勤辅助：答辩ppt、摸鱼', '陈飞');

-- ----------------------------
-- Table structure for tb_recommend
-- ----------------------------
DROP TABLE IF EXISTS `tb_recommend`;
CREATE TABLE `tb_recommend`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `recommend_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '推荐内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 54 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_recommend
-- ----------------------------
INSERT INTO `tb_recommend` VALUES (51, '落霞与孤鹜齐飞，秋水共长天一色。——王之涣《登鹳雀楼》');

-- ----------------------------
-- Table structure for tb_rotation
-- ----------------------------
DROP TABLE IF EXISTS `tb_rotation`;
CREATE TABLE `tb_rotation`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `upload_time` datetime NULL DEFAULT NULL COMMENT '上传时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 119 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_rotation
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '头像',
  `wx_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'wxID',
  `wx_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'wx名',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `available_status` int NOT NULL COMMENT '是否可用，0-可用   1-封禁',
  `identity` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '身份，0-普通用户   1-管理员',
  `last_login` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 121 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (120, 'https://thirdwx.qlogo.cn/mmopen/vi_32/zEzJYYyj4pnbmXibgEytuAQ8jUEWuE9WDBMdRuibYsop25RICwdqthJiaEo1nuxznHCeVFLJxMicMl7fWrfzzFR7lA/132', 'f4d2a22e28656de339ba8a6857ed45c4', '钉钉', '2023-06-08 21:01:03', 0, '普通用户', '2023-06-08 21:04:27');

SET FOREIGN_KEY_CHECKS = 1;
