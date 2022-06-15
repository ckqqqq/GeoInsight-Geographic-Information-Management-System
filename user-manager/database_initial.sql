#在运行整个项目前使用该mysql文件创建数据库
/*
 Navicat Premium Data Transfer

 Source Server         : Yyds_3_15_Second_hand_book_platform
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : root:3306
 Source Schema         : project_database

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 15/05/2022 16:15:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE DATABASE IF NOT EXISTS project_database DEFAULT CHARACTER SET utf8;
# SHOW DATABASE '';
USE project_database;
-- ----------------------------
-- Table structure for item
-- ----------------------------

# DROP TABLE IF EXISTS `item`;
# CREATE TABLE `item`  (
#                          `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
#                          `itemname` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
#                          `typename` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
#                          `longitude` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
#                          `latitude` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
#                          `finalOption` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
#                          `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
#                          `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
#                          PRIMARY KEY (`id`) USING BTREE
# ) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
# CREATE DATABASE IF NOT EXISTS item_l character set utf8;
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item`  (
                         `id` varchar(64) NOT NULL COMMENT '编号',
                         `itemname` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
                         `typename` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
                         `longitude` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
                         `latitude` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
                         `final_option` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
                         `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
                         `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                         PRIMARY KEY (`id`)
) ENGINE = InnoDB  CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of item
-- ----------------------------
-- #数据库是生成的，数据是我随机生成的，这个后端mybatisok 的，然后下一周的任务就是把咱们的后端对接上，咱们的后端又改了一版
INSERT INTO `item` VALUES ('110108000000202105220204001000101001', '皂君东里社区居委', '震情信息', 'E 117.87349561998104', 'S 79.17248239986428', '1', '发放人道主义物资','2022-05-26 00:25:20');
INSERT INTO `item` VALUES ('110105000000202007120203001402303005', '朝阳区', '交通事故', 'E 73.11757465406474', 'S 73.4384393765733', '0', '发放人道主义物资','2022-05-26 00:25:20');
INSERT INTO `item` VALUES ('110102000000201103220114001804401003', '西城区', '舆情信息', 'W 113.52524175543662', 'S 75.69006945656373', '1', '发放人道主义物资','2022-05-26 00:25:20');
INSERT INTO `item` VALUES ('110101002008202712432014002003405002', '景山东街社区居委会', '舆情网感知', 'E 83.86624427158884', 'N 41.41549234179481', '1', '需要联系当地有关部门','2022-05-26 00:25:20');
INSERT INTO `item` VALUES ('130181101216201709292104001212403004', '北张村委会', '滑坡', 'W 119.33815733861722', 'S 81.95738502165271', '1', '继续观察','2022-05-26 00:25:20');
INSERT INTO `item` VALUES ('130433203208202206222249001411406002', '前时玉村委会', '震情信息', 'E 33.07577536857024', 'S 71.1085338298315', '1', '继续观察','2022-05-26 00:25:20');
INSERT INTO `item` VALUES ('220322205204199914532214002023501004', '旱河村委会', '爆炸', 'E 99.58941942302056', 'S 33.236845482156', '0', '急需处理','2022-05-26 00:25:20');
INSERT INTO `item` VALUES ('231083103206201406232359002032304002', '长胜村委会', '塌方', 'E 83.33240150468492', 'S 73.41244755091702', '1', '发放人道主义物资','2022-05-26 00:25:20');
INSERT INTO `item` VALUES ('210203013003201616272319001812403004', '云峰社区居委会', '舆情信息', 'W 108.70279185920054', 'N 77.89960395220642', '0', '继续观察','2022-05-26 00:25:20');
INSERT INTO `item` VALUES ('211005001000202105220204001013304002', '苏家街道办事处', '滑坡', 'W 5.732749660961469', 'S 84.77994907944013', '1', '表示关切','2022-05-26 00:25:20');
INSERT INTO `item` VALUES ('220281101300201807230405001803402005', '永胜村委会（天岗石材开发区）（省）', '塌方', 'E 66.76810586579244', 'S 78.60184441818606', '0', '发放人道主义物资','2022-05-26 00:25:20');
INSERT INTO `item` VALUES ('230882106206197704270255001404502001', '巨福村委会', '雷暴', 'E 161.46974925286452', 'N 45.10029449988048', '0', '继续观察','2022-05-26 00:25:20');
# INSERT INTO `item` VALUES (, '罢工影响哈蒙德公司生产', '其他', 'E 77.85982203312946', 'S 73.47727893763326', '1', '表示关切','2022-05-26 00:25:20');
# INSERT INTO `item` VALUES (13, '祖安爆发空气污染，PM2.5严重超标', '自然灾害', 'E 150.3940248793576', 'N 87.34920079929881', '0', '继续观察','2022-05-26 00:25:20');
# INSERT INTO `item` VALUES (14, '艾欧尼亚遭遇还笑袭击', '其他', 'W 178.2268193137609', 'S 72.40016477904868', '0', '继续观察','2022-05-26 00:25:20');
# INSERT INTO `item` VALUES (15, '诺克萨斯遭遇叛乱，叛军攻占首都', '突发事件', 'E 116.44898537652381', 'S 55.18753681874344', '0', '表示关切','2022-05-26 00:25:20');
SET FOREIGN_KEY_CHECKS = 1;


