/*
 Navicat Premium Data Transfer

 Source Server         : 本机MySQL
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : chatbot

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 07/10/2022 19:39:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_friend
-- ----------------------------
DROP TABLE IF EXISTS `t_friend`;
CREATE TABLE `t_friend`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '好友关系id',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户id',
  `friend_id` int(0) NULL DEFAULT NULL COMMENT '朋友id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `is_delete` tinyint(0) NOT NULL DEFAULT 0 COMMENT '是否删除，待通过0-未删除1-删除2',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_friend_message
-- ----------------------------
DROP TABLE IF EXISTS `t_friend_message`;
CREATE TABLE `t_friend_message`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '消息id',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `type` tinyint(0) NULL DEFAULT 0 COMMENT '内容类型，文本0-图片1-视频2',
  `sender_id` int(0) NOT NULL COMMENT '发送者id',
  `receiver_id` int(0) NOT NULL COMMENT '接收者id',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `is_delete` tinyint(0) NOT NULL DEFAULT 0 COMMENT '消息是否删除/撤回，未撤回0-撤回1',
  `is_read` tinyint(0) NULL DEFAULT 0 COMMENT '消息是否已读，未读0-已读1',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_group
-- ----------------------------
DROP TABLE IF EXISTS `t_group`;
CREATE TABLE `t_group`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '群聊信息id',
  `group_ower_id` int(0) NULL DEFAULT NULL COMMENT '群主id',
  `avatar` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '群头像url',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '群备注',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '群名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `is_delete` tinyint(0) NOT NULL DEFAULT 0 COMMENT '是否删除，未删除0-删除1',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_group_member
-- ----------------------------
DROP TABLE IF EXISTS `t_group_member`;
CREATE TABLE `t_group_member`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '群成员id',
  `group_id` int(0) NULL DEFAULT NULL COMMENT '群聊id',
  `member_id` int(0) NULL DEFAULT NULL COMMENT '成员id',
  `is_quit` tinyint(0) NULL DEFAULT 0 COMMENT '是否退群，未进群0-未退群1-退群2',
  `invite_time` datetime(0) NULL DEFAULT NULL COMMENT '邀请时间',
  `agree_time` datetime(0) NULL DEFAULT NULL COMMENT '加群时间',
  `quit_time` datetime(0) NULL DEFAULT NULL COMMENT '退群时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_group_message
-- ----------------------------
DROP TABLE IF EXISTS `t_group_message`;
CREATE TABLE `t_group_message`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '群聊消息id',
  `group_id` int(0) NULL DEFAULT NULL COMMENT '群id',
  `sender_id` int(0) NULL DEFAULT NULL COMMENT '发送者id',
  `content` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `type` tinyint(0) NULL DEFAULT 0 COMMENT '内容类型，文本0-图片1-视频2',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `is_delete` tinyint(0) NOT NULL DEFAULT 0 COMMENT '是否删除，未删除0-删除1',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_session_list
-- ----------------------------
DROP TABLE IF EXISTS `t_session_list`;
CREATE TABLE `t_session_list`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '会话列表id',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户id',
  `target_id` int(0) NULL DEFAULT NULL COMMENT '会话目标id，群会话则是群id，否则是好友id',
  `is_group` tinyint(0) NULL DEFAULT NULL COMMENT '是否是群会话，是0，否1',
  `last_user_id` int(0) NULL DEFAULT NULL COMMENT '最后用户id',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后内容',
  `last_time` datetime(0) NULL DEFAULT NULL COMMENT '最后时间',
  `is_delete` tinyint(0) NOT NULL DEFAULT 0 COMMENT '是否删除，未删除0-删除1',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `gender` tinyint(0) NULL DEFAULT NULL COMMENT '性别男0-女1',
  `phone` char(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号',
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `signature` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个性签名',
  `avatar` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像url',
  `register_time` datetime(0) NULL DEFAULT NULL COMMENT '注册时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
