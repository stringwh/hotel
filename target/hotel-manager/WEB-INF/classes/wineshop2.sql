/*
 Navicat Premium Data Transfer

 Source Server         : MySql
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : wineshop2

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 21/06/2019 17:41:05
*/
CREATE DATABASE /*!32312 IF NOT EXISTS*/`wineshop2` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `wineshop2`;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for anothorpay
-- ----------------------------
DROP TABLE IF EXISTS `anothorpay`;
CREATE TABLE `anothorpay`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `desc` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '支付描述',
  `money` float NOT NULL COMMENT '支付金额',
  `roomnumber` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '订单房间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for bill
-- ----------------------------
DROP TABLE IF EXISTS `bill`;
CREATE TABLE `bill`  (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自编号',
  `roomNumber` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '房间号',
  `roomType` int(11) NULL DEFAULT NULL COMMENT '房间类型',
  `guestName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '顾客姓名',
  `idCard` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份证',
  `generationTime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '生成时间',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '电话',
  `payAmount` float NULL DEFAULT NULL COMMENT '实付金额',
  `checkintime` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `checkouttime` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `billstatus` int(5) NOT NULL DEFAULT 0,
  `roomTypeStr` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '房间类型字符',
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `orId`(`Id`) USING BTREE,
  INDEX `ck_order_roomNumber`(`roomNumber`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 111 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for check
-- ----------------------------
DROP TABLE IF EXISTS `check`;
CREATE TABLE `check`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `roomNumber` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '房间号',
  `vipid` int(20) NULL DEFAULT NULL COMMENT '会员卡号',
  `userName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户姓名',
  `sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别',
  `idCard` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份证',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话',
  `checkInTime` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '入住时间',
  `checkouttime` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '入住时间',
  `status` int(5) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `ck_check_vipid`(`vipid`) USING BTREE,
  INDEX `ck_check_roomNumber`(`roomNumber`) USING BTREE,
  CONSTRAINT `ck_check_roomNumber` FOREIGN KEY (`roomNumber`) REFERENCES `room` (`roomNumber`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ck_check_vipid` FOREIGN KEY (`vipid`) REFERENCES `vip` (`vipId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for emplyee
-- ----------------------------
DROP TABLE IF EXISTS `emplyee`;
CREATE TABLE `emplyee`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自编号',
  `account` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `realName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '真实姓名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登陆密码',
  `sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别',
  `birthDate` date NOT NULL COMMENT '出生日期',
  `idCard` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份证',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地址',
  `type` int(11) NOT NULL COMMENT '用户类型 1.管理员 2.普通用户',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 71 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of emplyee
-- ----------------------------
INSERT INTO `emplyee` VALUES (3, 'sa', '黄晓乐1', 'sa1', '男', '1999-11-25', '421181199911288888', '15572858888', '荆州', 2);
INSERT INTO `emplyee` VALUES (14, '老王1', '张三111', '789456', '女', '1989-10-19', '340822198910199087', '15870593333', '仙桃', 2);
INSERT INTO `emplyee` VALUES (15, '王五', '李四', '1234567', '男', '1989-10-19', '340822198910195835', '15870593664', '武汉', 2);
INSERT INTO `emplyee` VALUES (66, 'sasa', 'sasa', '123456', '男', '2013-12-12', '340822198910199081', '12312312312', '12', 2);
INSERT INTO `emplyee` VALUES (70, 'admin', 'admin', '123456', '男', '2019-06-21', '420154199802031212', '15612351463', '湖北', 1);

-- ----------------------------
-- Table structure for ly
-- ----------------------------
DROP TABLE IF EXISTS `ly`;
CREATE TABLE `ly`  (
  `lid` int(20) NOT NULL AUTO_INCREMENT,
  `uid` int(20) NOT NULL,
  `message` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ldate` date NOT NULL,
  PRIMARY KEY (`lid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ly
-- ----------------------------
INSERT INTO `ly` VALUES (1, 2, '啊 ', '2017-03-21');
INSERT INTO `ly` VALUES (2, 3, '飒飒大苏打', '2017-03-24');
INSERT INTO `ly` VALUES (3, 4, 'faasfafas', '2017-03-25');
INSERT INTO `ly` VALUES (4, 5, 'gfgdfg', '2017-03-25');
INSERT INTO `ly` VALUES (5, 6, 'asdasdasd', '2017-03-26');
INSERT INTO `ly` VALUES (6, 7, 'dasdasda', '2017-03-27');
INSERT INTO `ly` VALUES (7, 8, 'aasdasd', '2017-03-28');
INSERT INTO `ly` VALUES (8, 9, 'tryrty', '2017-03-28');
INSERT INTO `ly` VALUES (9, 10, 'trhrthr', '2017-03-28');
INSERT INTO `ly` VALUES (10, 11, 'trr', '2017-03-29');
INSERT INTO `ly` VALUES (11, 12, 'hfghfg', '2017-03-29');

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `roomNumber` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '房间号',
  `price` float NOT NULL COMMENT '价格',
  `roomType` int(10) NOT NULL COMMENT '房间类型',
  `deposit` float NULL DEFAULT NULL COMMENT '押金',
  `status` int(10) NOT NULL COMMENT '客房状态 1 可入住 2 已入住 3 待清理 4 停用',
  PRIMARY KEY (`roomNumber`) USING BTREE,
  INDEX `ck_room_roomType`(`roomType`) USING BTREE,
  CONSTRAINT `ck_room_roomType` FOREIGN KEY (`roomType`) REFERENCES `roomtype` (`rid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES ('1111', 360, 1, 100, 1);
INSERT INTO `room` VALUES ('1112', 390, 1, 120, 1);
INSERT INTO `room` VALUES ('1113', 350, 1, 100, 1);
INSERT INTO `room` VALUES ('1114', 400, 2, 150, 1);
INSERT INTO `room` VALUES ('1116', 480, 3, 160, 1);
INSERT INTO `room` VALUES ('1117', 560, 4, 200, 1);
INSERT INTO `room` VALUES ('1118', 580, 4, 200, 1);
INSERT INTO `room` VALUES ('1119', 560, 4, 200, 1);
INSERT INTO `room` VALUES ('2000', 600, 5, 250, 1);
INSERT INTO `room` VALUES ('2002', 650, 5, 200, 1);
INSERT INTO `room` VALUES ('2003', 700, 6, 280, 1);
INSERT INTO `room` VALUES ('2004', 700, 6, 280, 1);
INSERT INTO `room` VALUES ('2006', 1200, 9, 350, 3);
INSERT INTO `room` VALUES ('2007', 8888, 7, 2000, 1);
INSERT INTO `room` VALUES ('2008', 1600, 8, 500, 3);
INSERT INTO `room` VALUES ('2009', 1600, 8, 500, 1);
INSERT INTO `room` VALUES ('3003', 1200, 9, 350, 1);
INSERT INTO `room` VALUES ('3004', 1200, 9, 350, 1);
INSERT INTO `room` VALUES ('3005', 350, 3, 120, 3);
INSERT INTO `room` VALUES ('3006', 1800, 10, 680, 1);
INSERT INTO `room` VALUES ('3007', 1680, 10, 600, 4);
INSERT INTO `room` VALUES ('3008', 1680, 10, 680, 3);
INSERT INTO `room` VALUES ('3009', 1800, 11, 700, 1);
INSERT INTO `room` VALUES ('4001', 1800, 11, 700, 3);
INSERT INTO `room` VALUES ('4002', 2500, 11, 800, 1);
INSERT INTO `room` VALUES ('4003', 2500, 10, 800, 3);
INSERT INTO `room` VALUES ('4004', 260, 7, 100, 3);
INSERT INTO `room` VALUES ('4005', 260, 7, 100, 3);
INSERT INTO `room` VALUES ('4007', 500, 7, 150, 3);
INSERT INTO `room` VALUES ('4009', 600, 7, 350, 3);
INSERT INTO `room` VALUES ('5001', 700, 7, 250, 3);
INSERT INTO `room` VALUES ('5002', 700, 7, 350, 3);
INSERT INTO `room` VALUES ('5003', 8888, 8, 2500, 3);
INSERT INTO `room` VALUES ('5004', 700, 11, 350, 3);

-- ----------------------------
-- Table structure for roomtype
-- ----------------------------
DROP TABLE IF EXISTS `roomtype`;
CREATE TABLE `roomtype`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自编号',
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '房间类型',
  `rid` int(11) NOT NULL COMMENT '房间类型编号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `rid`(`rid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roomtype
-- ----------------------------
INSERT INTO `roomtype` VALUES (12, '单人间', 1);
INSERT INTO `roomtype` VALUES (13, '双人间', 2);
INSERT INTO `roomtype` VALUES (14, '标准间', 3);
INSERT INTO `roomtype` VALUES (15, '大床房', 4);
INSERT INTO `roomtype` VALUES (16, '商务套房', 5);
INSERT INTO `roomtype` VALUES (17, '总统套房', 6);
INSERT INTO `roomtype` VALUES (18, '泰坦尼克号', 7);
INSERT INTO `roomtype` VALUES (19, '情侣套房', 8);
INSERT INTO `roomtype` VALUES (20, '维也纳套房', 9);
INSERT INTO `roomtype` VALUES (21, '爱琴海套房', 10);
INSERT INTO `roomtype` VALUES (22, '罗马假日', 11);

-- ----------------------------
-- Table structure for vip
-- ----------------------------
DROP TABLE IF EXISTS `vip`;
CREATE TABLE `vip`  (
  `vipId` int(20) NOT NULL AUTO_INCREMENT COMMENT '会员卡号',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别',
  `idCard` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份证',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话',
  `level` int(11) NULL DEFAULT 1 COMMENT '会员等级',
  `balance` float NULL DEFAULT 0 COMMENT '余额',
  `addMonetary` float NULL DEFAULT 0 COMMENT '累计消费金额',
  PRIMARY KEY (`vipId`) USING BTREE,
  INDEX `ck_vip_level`(`level`) USING BTREE,
  CONSTRAINT `ck_vip_level` FOREIGN KEY (`level`) REFERENCES `vipdiscount` (`lever`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vip
-- ----------------------------
INSERT INTO `vip` VALUES (31, '史伟伟', '男', '422202198711224739', '15527467522', 1, 700, 0);
INSERT INTO `vip` VALUES (32, '张慧', '女', '420821199203270014', '15527467521', 6, 0, 10559);
INSERT INTO `vip` VALUES (33, '聂鹏飞', '男', '433302198808244733', '18764322242', 1, 0, 0);
INSERT INTO `vip` VALUES (35, '看看', '男', '422202198711224729', '15527467521', 1, 0, 0);
INSERT INTO `vip` VALUES (36, '是', '男', '422202198711224731', '18764322242', 1, 0, 0);
INSERT INTO `vip` VALUES (42, '聂鹏飞', '男', '422202198711224723', '15527467522', 1, 0, 0);
INSERT INTO `vip` VALUES (43, '王五', '男', '422202198711224738', '18764322242', 1, 0, 0);
INSERT INTO `vip` VALUES (44, '陈龙', '男', '420116199407032078', '18108644415', 1, 0, 0);
INSERT INTO `vip` VALUES (45, '当', '男', '483489199309110987', '16718020987', 1, 0, 0);

-- ----------------------------
-- Table structure for vipdiscount
-- ----------------------------
DROP TABLE IF EXISTS `vipdiscount`;
CREATE TABLE `vipdiscount`  (
  `lever` int(11) NOT NULL AUTO_INCREMENT COMMENT '会员等级',
  `leverName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '等级名称',
  `discountRate` float NOT NULL COMMENT '折扣率',
  PRIMARY KEY (`lever`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vipdiscount
-- ----------------------------
INSERT INTO `vipdiscount` VALUES (1, '普通用户', 1);
INSERT INTO `vipdiscount` VALUES (2, '白银会员', 0.9);
INSERT INTO `vipdiscount` VALUES (3, '黄金会员', 0.8);
INSERT INTO `vipdiscount` VALUES (4, '白金会员', 0.7);
INSERT INTO `vipdiscount` VALUES (5, '钻石会员', 0.6);
INSERT INTO `vipdiscount` VALUES (6, '至尊会员', 0.5);

SET FOREIGN_KEY_CHECKS = 1;
