/*
 Navicat Premium Data Transfer

 Source Server         : school
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : student

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 11/02/2023 14:48:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for c
-- ----------------------------
DROP TABLE IF EXISTS `c`;
CREATE TABLE `c`  (
  `cno` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cname` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `credit` int NOT NULL,
  `cdept` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `tname` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`cno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c
-- ----------------------------
INSERT INTO `c` VALUES ('C1', '高级语言程序设计', 4, '计算机应用', '王晓名');
INSERT INTO `c` VALUES ('C2', '数据结构', 4, '计算机应用', '刘红');
INSERT INTO `c` VALUES ('C3', '离散数学', 4, '计算机应用', '李严劲');
INSERT INTO `c` VALUES ('C4', '计算机原理', 6, '计算机软件', '王晓名');
INSERT INTO `c` VALUES ('C5', '数据库原理', 4, '计算机应用', '吴志钢');
INSERT INTO `c` VALUES ('C6', 'WINDOW技术', 4, '计算机软件', '吴志钢');
INSERT INTO `c` VALUES ('C8', '编译原理', 4, '计算机软件', '蒋莹岳');
INSERT INTO `c` VALUES ('C9', '系统结构', 6, '计算机应用', '蒋莹岳');

-- ----------------------------
-- Table structure for s
-- ----------------------------
DROP TABLE IF EXISTS `s`;
CREATE TABLE `s`  (
  `sno` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sname` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sex` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `age` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sdept` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `logn` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `pswd` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`sno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s
-- ----------------------------
INSERT INTO `s` VALUES ('S1', '李铭', '男', '19', '计算机软件', 'S1', 'S1');
INSERT INTO `s` VALUES ('S2', '刘晓鸣', '男', '20', '计算机应用', 'S2', 'S2');
INSERT INTO `s` VALUES ('S3', '李明', '男', '22', '计算机应用', 'S3', 'S3');
INSERT INTO `s` VALUES ('S4', '张鹰', '女', '21', '计算机软件', 'S4', 'S4');
INSERT INTO `s` VALUES ('S5', '刘竟晓', '女', '22', '计算机软件', 'S5', 'S5');
INSERT INTO `s` VALUES ('S6', '刘成刚', '男', '21', '计算机软件', 'S6', 'S6');

-- ----------------------------
-- Table structure for sc
-- ----------------------------
DROP TABLE IF EXISTS `sc`;
CREATE TABLE `sc`  (
  `sno` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cno` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `grade` int NULL DEFAULT NULL,
  PRIMARY KEY (`sno`, `cno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sc
-- ----------------------------
INSERT INTO `sc` VALUES ('S1', 'C2', 66);
INSERT INTO `sc` VALUES ('S1', 'C3', 88);
INSERT INTO `sc` VALUES ('S1', 'C4', 60);
INSERT INTO `sc` VALUES ('S2', 'C6', NULL);
INSERT INTO `sc` VALUES ('S2', 'C9', 90);
INSERT INTO `sc` VALUES ('S3', 'C1', 88);
INSERT INTO `sc` VALUES ('S4', 'C1', 67);
INSERT INTO `sc` VALUES ('S4', 'C2', 76);
INSERT INTO `sc` VALUES ('S4', 'C3', 67);
INSERT INTO `sc` VALUES ('S5', 'C1', 67);
INSERT INTO `sc` VALUES ('S6', 'C1', 78);

SET FOREIGN_KEY_CHECKS = 1;
