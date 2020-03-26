/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : schoolshop

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 26/03/2020 10:54:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_area
-- ----------------------------
DROP TABLE IF EXISTS `tb_area`;
CREATE TABLE `tb_area` (
  `area_id` int(2) NOT NULL AUTO_INCREMENT,
  `area_name` varchar(200) NOT NULL,
  `priority` int(2) NOT NULL DEFAULT '0',
  `createTime` datetime DEFAULT NULL,
  `lastEditTime` datetime DEFAULT NULL,
  PRIMARY KEY (`area_id`),
  UNIQUE KEY `UK_AREA` (`area_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_area
-- ----------------------------
BEGIN;
INSERT INTO `tb_area` VALUES (1, '南门', 1, NULL, NULL);
INSERT INTO `tb_area` VALUES (2, '西门', 2, NULL, NULL);
INSERT INTO `tb_area` VALUES (3, '北门', 3, NULL, NULL);
INSERT INTO `tb_area` VALUES (4, '东门', 4, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for tb_head_line
-- ----------------------------
DROP TABLE IF EXISTS `tb_head_line`;
CREATE TABLE `tb_head_line` (
  `line_id` int(10) NOT NULL AUTO_INCREMENT,
  `line_name` varchar(1024) DEFAULT NULL,
  `line_link` varchar(1600) NOT NULL,
  `line_img` varchar(1600) NOT NULL,
  `priority` int(2) NOT NULL DEFAULT '0',
  `enable_status` int(2) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`line_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_head_line
-- ----------------------------
BEGIN;
INSERT INTO `tb_head_line` VALUES (3, '轮播1', '1', '/item/lunbo/wallhaven-13p6o1.jpg', 10, 1, '2020-02-18 11:31:51', '2020-02-18 11:31:40');
INSERT INTO `tb_head_line` VALUES (4, '轮播2', '2', '/item/lunbo/wallhaven-39rq53.jpg', 9, 1, '2020-02-18 11:32:31', '2020-02-18 11:32:32');
INSERT INTO `tb_head_line` VALUES (5, '轮播3', '3', '/item/lunbo/wallhaven-73w51y.jpg', 8, 1, '2020-02-18 11:32:56', '2020-02-18 11:32:59');
INSERT INTO `tb_head_line` VALUES (6, '轮播4', '4', '/item/lunbo/wallhaven-lq6rmy.png', 7, 1, '2020-02-18 11:33:27', '2020-02-18 11:33:29');
INSERT INTO `tb_head_line` VALUES (7, '轮播5', '5', '/item/lunbo/wallhaven-zmmkkv.png', 6, 1, '2020-02-18 11:33:58', '2020-02-18 11:34:03');
COMMIT;

-- ----------------------------
-- Table structure for tb_local_auth
-- ----------------------------
DROP TABLE IF EXISTS `tb_local_auth`;
CREATE TABLE `tb_local_auth` (
  `local_auth_id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `user_id` int(10) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`local_auth_id`),
  UNIQUE KEY `uk_local_profile` (`username`),
  KEY `fk_localauth_profile` (`user_id`),
  CONSTRAINT `fk_localauth_profile` FOREIGN KEY (`user_id`) REFERENCES `tb_person_info` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_local_auth
-- ----------------------------
BEGIN;
INSERT INTO `tb_local_auth` VALUES (3, 'maotentai', '123123', 1, NULL, NULL);
INSERT INTO `tb_local_auth` VALUES (5, 'mm', '123', 9, '2020-02-23 00:44:05', '2020-02-23 00:44:05');
COMMIT;

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `order_id` int(10) NOT NULL AUTO_INCREMENT,
  `shop_id` int(10) NOT NULL,
  `product_id` int(11) NOT NULL,
  `buyer_id` int(10) NOT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '1',
  `create_time` date DEFAULT NULL,
  `last_edit_time` date DEFAULT NULL,
  `seller_id` int(10) DEFAULT NULL,
  `promotion_price` varchar(100) DEFAULT NULL,
  `normal_price` varchar(100) DEFAULT NULL,
  `integral` int(100) DEFAULT NULL,
  `orderuuid` int(100) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_order
-- ----------------------------
BEGIN;
INSERT INTO `tb_order` VALUES (11, 18, 43, 9, 2, '2020-02-23', '2020-02-24', NULL, '18', '20', 2, 34233);
INSERT INTO `tb_order` VALUES (12, 18, 62, 9, 2, '2020-02-23', '2020-02-23', NULL, '35', '40', 2, 89940);
INSERT INTO `tb_order` VALUES (13, 18, 63, 9, 2, '2020-02-23', '2020-02-23', NULL, '18', '20', 4, 15887);
INSERT INTO `tb_order` VALUES (14, 25, 59, 9, 2, '2020-02-23', '2020-02-24', NULL, '380', '400', 2, 51725);
INSERT INTO `tb_order` VALUES (15, 19, 44, 9, 2, '2020-02-23', '2020-02-24', NULL, '13', '15', 2, 21618);
INSERT INTO `tb_order` VALUES (16, 19, 45, 9, 2, '2020-02-23', '2020-02-24', NULL, '18', '20', 2, 26591);
INSERT INTO `tb_order` VALUES (17, 19, 46, 9, 2, '2020-02-23', '2020-02-25', NULL, '15', '20', 2, 11372);
INSERT INTO `tb_order` VALUES (18, 22, 53, 9, 2, '2020-02-25', '2020-02-25', NULL, '40', '50', 2, 55041);
INSERT INTO `tb_order` VALUES (19, 22, 53, 9, 2, '2020-02-25', '2020-02-25', NULL, '40', '50', 2, 47925);
INSERT INTO `tb_order` VALUES (20, 22, 53, 9, 2, '2020-02-25', '2020-02-25', NULL, '40', '50', 2, 92281);
INSERT INTO `tb_order` VALUES (21, 21, 49, 9, 2, '2020-02-25', '2020-02-25', NULL, '18', '20', 2, 41586);
INSERT INTO `tb_order` VALUES (22, 26, 60, 9, 1, '2020-02-25', '2020-02-25', NULL, '180', '200', 2, NULL);
COMMIT;

-- ----------------------------
-- Table structure for tb_order_summary
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_summary`;
CREATE TABLE `tb_order_summary` (
  `order_summary_id` int(100) NOT NULL AUTO_INCREMENT,
  `shop_id` int(100) DEFAULT NULL,
  `product_id` int(100) DEFAULT NULL,
  `order_num` int(100) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`order_summary_id`),
  KEY `order_summary_key_shop_id` (`shop_id`),
  KEY `order_summary_key_product_id` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=129 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_order_summary
-- ----------------------------
BEGIN;
INSERT INTO `tb_order_summary` VALUES (24, 24, 56, 10, '2020-02-24 00:00:00');
INSERT INTO `tb_order_summary` VALUES (25, 19, 46, 11, '2020-02-24 00:00:00');
INSERT INTO `tb_order_summary` VALUES (27, 18, 43, 10, '2020-02-24 00:00:00');
INSERT INTO `tb_order_summary` VALUES (29, 21, 50, 10, '2020-02-24 00:00:00');
INSERT INTO `tb_order_summary` VALUES (30, 23, 55, 17, '2020-02-24 00:00:00');
INSERT INTO `tb_order_summary` VALUES (33, 21, 49, 10, '2020-02-24 00:00:00');
INSERT INTO `tb_order_summary` VALUES (34, 19, 44, 21, '2020-02-24 00:00:00');
INSERT INTO `tb_order_summary` VALUES (35, 23, 54, 31, '2020-02-24 00:00:00');
INSERT INTO `tb_order_summary` VALUES (36, 20, 48, 23, '2020-02-24 00:00:00');
INSERT INTO `tb_order_summary` VALUES (42, 18, 62, 11, '2020-02-23 00:00:00');
INSERT INTO `tb_order_summary` VALUES (43, 22, 52, 31, '2020-02-24 00:00:00');
INSERT INTO `tb_order_summary` VALUES (47, 26, 61, 43, '2020-02-24 00:00:00');
INSERT INTO `tb_order_summary` VALUES (52, 19, 45, 23, '2020-02-24 00:00:00');
INSERT INTO `tb_order_summary` VALUES (53, 25, 59, 51, '2020-02-24 00:00:00');
INSERT INTO `tb_order_summary` VALUES (58, 25, 58, 41, '2020-02-24 00:00:00');
INSERT INTO `tb_order_summary` VALUES (59, 18, 63, 32, '2020-02-24 00:00:00');
INSERT INTO `tb_order_summary` VALUES (61, 20, 47, 23, '2020-02-24 00:00:00');
INSERT INTO `tb_order_summary` VALUES (62, 24, 57, 43, '2020-02-24 00:00:00');
INSERT INTO `tb_order_summary` VALUES (63, 18, 62, 51, '2020-02-24 00:00:00');
INSERT INTO `tb_order_summary` VALUES (66, 24, 56, 9, '2020-02-23 00:00:00');
INSERT INTO `tb_order_summary` VALUES (67, 19, 46, 12, '2020-02-23 00:00:00');
INSERT INTO `tb_order_summary` VALUES (68, 26, 61, 32, '2020-02-23 00:00:00');
INSERT INTO `tb_order_summary` VALUES (69, 18, 43, 23, '2020-02-23 00:00:00');
INSERT INTO `tb_order_summary` VALUES (70, 26, 60, 12, '2020-02-24 00:00:00');
INSERT INTO `tb_order_summary` VALUES (71, 21, 50, 12, '2020-02-23 00:00:00');
INSERT INTO `tb_order_summary` VALUES (73, 19, 45, 12, '2020-02-23 00:00:00');
INSERT INTO `tb_order_summary` VALUES (74, 25, 59, 13, '2020-02-23 00:00:00');
INSERT INTO `tb_order_summary` VALUES (75, 21, 49, 12, '2020-02-23 00:00:00');
INSERT INTO `tb_order_summary` VALUES (76, 19, 44, 13, '2020-02-23 00:00:00');
INSERT INTO `tb_order_summary` VALUES (79, 25, 58, 12, '2020-02-23 00:00:00');
INSERT INTO `tb_order_summary` VALUES (80, 18, 63, 31, '2020-02-23 00:00:00');
INSERT INTO `tb_order_summary` VALUES (81, 22, 53, 21, '2020-02-23 00:00:00');
INSERT INTO `tb_order_summary` VALUES (83, 24, 57, 12, '2020-02-23 00:00:00');
INSERT INTO `tb_order_summary` VALUES (86, 21, 51, 31, '2020-02-23 00:00:00');
INSERT INTO `tb_order_summary` VALUES (91, 26, 60, 12, '2020-02-23 00:00:00');
INSERT INTO `tb_order_summary` VALUES (93, 23, 55, 12, '2020-02-23 00:00:00');
INSERT INTO `tb_order_summary` VALUES (98, 23, 54, 31, '2020-02-23 00:00:00');
INSERT INTO `tb_order_summary` VALUES (99, 20, 48, 21, '2020-02-23 00:00:00');
INSERT INTO `tb_order_summary` VALUES (102, 22, 53, 13, '2020-02-24 00:00:00');
INSERT INTO `tb_order_summary` VALUES (103, 20, 47, 13, '2020-02-23 00:00:00');
INSERT INTO `tb_order_summary` VALUES (106, 22, 52, 14, '2020-02-23 00:00:00');
INSERT INTO `tb_order_summary` VALUES (107, 21, 51, 15, '2020-02-24 00:00:00');
INSERT INTO `tb_order_summary` VALUES (108, 24, 56, 0, '2020-02-27 00:00:00');
INSERT INTO `tb_order_summary` VALUES (109, 19, 46, 0, '2020-02-27 00:00:00');
INSERT INTO `tb_order_summary` VALUES (110, 26, 61, 0, '2020-02-27 00:00:00');
INSERT INTO `tb_order_summary` VALUES (111, 18, 43, 0, '2020-02-27 00:00:00');
INSERT INTO `tb_order_summary` VALUES (112, 21, 50, 0, '2020-02-27 00:00:00');
INSERT INTO `tb_order_summary` VALUES (113, 26, 60, 0, '2020-02-27 00:00:00');
INSERT INTO `tb_order_summary` VALUES (114, 19, 45, 0, '2020-02-27 00:00:00');
INSERT INTO `tb_order_summary` VALUES (115, 23, 55, 0, '2020-02-27 00:00:00');
INSERT INTO `tb_order_summary` VALUES (116, 21, 49, 0, '2020-02-27 00:00:00');
INSERT INTO `tb_order_summary` VALUES (117, 25, 59, 0, '2020-02-27 00:00:00');
INSERT INTO `tb_order_summary` VALUES (118, 19, 44, 0, '2020-02-27 00:00:00');
INSERT INTO `tb_order_summary` VALUES (119, 23, 54, 0, '2020-02-27 00:00:00');
INSERT INTO `tb_order_summary` VALUES (120, 20, 48, 0, '2020-02-27 00:00:00');
INSERT INTO `tb_order_summary` VALUES (121, 25, 58, 0, '2020-02-27 00:00:00');
INSERT INTO `tb_order_summary` VALUES (122, 18, 63, 0, '2020-02-27 00:00:00');
INSERT INTO `tb_order_summary` VALUES (123, 22, 53, 0, '2020-02-27 00:00:00');
INSERT INTO `tb_order_summary` VALUES (124, 20, 47, 0, '2020-02-27 00:00:00');
INSERT INTO `tb_order_summary` VALUES (125, 24, 57, 0, '2020-02-27 00:00:00');
INSERT INTO `tb_order_summary` VALUES (126, 22, 52, 0, '2020-02-27 00:00:00');
INSERT INTO `tb_order_summary` VALUES (127, 18, 62, 0, '2020-02-27 00:00:00');
INSERT INTO `tb_order_summary` VALUES (128, 21, 51, 0, '2020-02-27 00:00:00');
COMMIT;

-- ----------------------------
-- Table structure for tb_person_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_person_info`;
CREATE TABLE `tb_person_info` (
  `user_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(200) DEFAULT NULL,
  `profile_Img` varchar(1024) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `gender` varchar(2) DEFAULT NULL,
  `enable_Status` int(2) NOT NULL DEFAULT '0' COMMENT '0:代表账号被静止使用，1:代表账号可以正常使用',
  `user_type` int(2) NOT NULL DEFAULT '0' COMMENT '0:顾客，1:商家，2:管理员',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `integral` int(100) DEFAULT '0',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_person_info
-- ----------------------------
BEGIN;
INSERT INTO `tb_person_info` VALUES (1, '嘻嘻', '/item/head/2020021914232652015.jpg', '111', '1', 1, 2, NULL, NULL, 0);
INSERT INTO `tb_person_info` VALUES (9, 'tutu', '/item/head/2020022300440452651.jpg', '123123@qq.com', '2', 1, 1, '2020-02-23 00:44:05', '2020-02-23 00:44:05', 12);
COMMIT;

-- ----------------------------
-- Table structure for tb_product
-- ----------------------------
DROP TABLE IF EXISTS `tb_product`;
CREATE TABLE `tb_product` (
  `product_id` int(100) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(100) NOT NULL,
  `product_desc` varchar(2000) DEFAULT NULL,
  `img_addr` varchar(2000) DEFAULT '',
  `normal_price` varchar(100) DEFAULT NULL,
  `promotion_price` varchar(100) DEFAULT NULL,
  `priority` int(2) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '0',
  `product_category_id` int(11) DEFAULT NULL,
  `shop_id` int(20) NOT NULL DEFAULT '0',
  `integral` int(100) DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `fk_product_procate` (`product_category_id`),
  KEY `fk_product_shop` (`shop_id`),
  CONSTRAINT `fk_product_procate` FOREIGN KEY (`product_category_id`) REFERENCES `tb_product_category` (`product_category_id`),
  CONSTRAINT `fk_product_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_product
-- ----------------------------
BEGIN;
INSERT INTO `tb_product` VALUES (43, '烤五花一把', '一把20串', '/item/shop18/2020021816331793059.jpg', '20', '18', 10, '2020-02-18 16:33:18', '2020-02-18 16:33:30', 1, 25, 18, 2);
INSERT INTO `tb_product` VALUES (44, '烧仙草', '好喝，料多', '/item/shop19/2020021914284892678.png', '15', '13', 10, '2020-02-19 14:28:49', '2020-02-19 14:28:49', 1, 30, 19, 2);
INSERT INTO `tb_product` VALUES (45, '乌龙茶', '好喝，正宗', '/item/shop19/2020021914295547318.jpg', '20', '18', 10, '2020-02-19 14:29:55', '2020-02-19 14:29:55', 1, 31, 19, 2);
INSERT INTO `tb_product` VALUES (46, '橙汁', '新鲜，纯榨', '/item/shop19/2020021914305621784.jpg', '20', '15', 10, '2020-02-19 14:30:57', '2020-02-19 14:30:57', 1, 32, 19, 2);
INSERT INTO `tb_product` VALUES (47, '阿斯顿马丁', '好开，快', '/item/shop20/2020021914334024562.png', '2000', '1800', 10, '2020-02-19 14:33:40', '2020-02-19 14:33:40', 1, 33, 20, 2);
INSERT INTO `tb_product` VALUES (48, '高尔夫保姆车', '空间大，带孩子方便。', '/item/shop20/2020021914342430151.jpg', '800', '700', 10, '2020-02-19 14:34:25', '2020-02-19 14:34:25', 1, 34, 20, 2);
INSERT INTO `tb_product` VALUES (49, '阿婆全集', '阿婆全集，有收藏意义', '/item/shop21/2020021914360467672.jpg', '20', '18', 10, '2020-02-19 14:36:05', '2020-02-19 14:36:05', 1, 35, 21, 2);
INSERT INTO `tb_product` VALUES (50, '言情全集', '洒泪全集', '/item/shop21/2020021914371321862.jpg', '20', '18', 10, '2020-02-19 14:37:13', '2020-02-19 14:37:13', 1, 36, 21, 2);
INSERT INTO `tb_product` VALUES (51, '5年高考3年模拟', '奥力给，加油', '/item/shop21/2020021914380398116.jpg', '10', '9', 9, '2020-02-19 14:38:03', '2020-02-19 14:38:03', 1, 37, 21, 2);
INSERT INTO `tb_product` VALUES (52, '皇上衣服', '威武霸气', '/item/shop22/2020021914393116722.jpg', '200', '180', 10, '2020-02-19 14:39:32', '2020-02-19 14:39:32', 1, 38, 22, 2);
INSERT INTO `tb_product` VALUES (53, '道具刀', '不能用来杀人', '/item/shop22/2020021914405475426.jpg', '50', '40', 10, '2020-02-19 14:40:54', '2020-02-19 14:40:54', 1, 39, 22, 2);
INSERT INTO `tb_product` VALUES (54, '生化危机', '真实还原生化危机', '/item/shop23/2020022009435289721.png', '600', '500', 10, '2020-02-20 09:43:53', '2020-02-20 09:43:53', 1, 40, 23, 2);
INSERT INTO `tb_product` VALUES (55, '东方快车杀人案', '真实还原东方快车杀人案', '/item/shop23/2020022009443946151.png', '600', '500', 10, '2020-02-20 09:44:40', '2020-02-20 09:44:40', 1, 41, 23, 2);
INSERT INTO `tb_product` VALUES (56, '布加迪威龙', '123123', '/item/shop24/2020022009465237660.jpg', '10000000', '8000000', 10, '2020-02-20 09:46:53', '2020-02-20 09:46:53', 1, 42, 24, 2);
INSERT INTO `tb_product` VALUES (57, '五菱宏光', '1234231', '/item/shop24/2020022009472183950.jpg', '100000', '80000', 10, '2020-02-20 09:47:22', '2020-02-20 09:47:22', 1, 43, 24, 2);
INSERT INTO `tb_product` VALUES (58, '全身按摩', 'qweqwe', '/item/shop25/2020022009482395087.png', '300', '260', 10, '2020-02-20 09:48:23', '2020-02-20 09:48:23', 1, 45, 25, 2);
INSERT INTO `tb_product` VALUES (59, '全身焗油', '撒大王大王', '/item/shop25/2020022009485685274.png', '400', '380', 10, '2020-02-20 09:48:56', '2020-02-20 09:48:56', 1, 46, 25, 2);
INSERT INTO `tb_product` VALUES (60, '高级洗发膏', '请问请问请问', '/item/shop26/2020022009500438683.jpg', '200', '180', 10, '2020-02-20 09:50:04', '2020-02-20 09:50:04', 1, 47, 26, 2);
INSERT INTO `tb_product` VALUES (61, '全套', '请问请问请问', '/item/shop26/2020022009503637180.png', '200', '150', 10, '2020-02-20 09:50:36', '2020-02-20 09:50:36', 1, 48, 26, 2);
INSERT INTO `tb_product` VALUES (62, '烤猪蹄', '好吃的烤猪蹄', '/item/shop18/2020022020110186340.png', '40', '35', 10, '2020-02-20 20:10:59', '2020-02-20 20:13:32', 1, 25, 18, 2);
INSERT INTO `tb_product` VALUES (63, '烤鱿鱼', '好吃的鱿鱼', '/item/shop18/2020022220595196017.jpg', '20', '18', 10, '2020-02-22 20:59:52', '2020-02-22 21:53:36', 1, 25, 18, 4);
COMMIT;

-- ----------------------------
-- Table structure for tb_product_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_category`;
CREATE TABLE `tb_product_category` (
  `product_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_category_name` varchar(100) NOT NULL,
  `priority` int(2) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `shop_id` int(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`product_category_id`),
  KEY `fk_procate_shop` (`shop_id`),
  CONSTRAINT `fk_procate_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_product_category
-- ----------------------------
BEGIN;
INSERT INTO `tb_product_category` VALUES (25, '烧烤小串', 20, '2020-02-18 16:30:14', 18);
INSERT INTO `tb_product_category` VALUES (26, '肉食天堂', 19, '2020-02-18 16:30:14', 18);
INSERT INTO `tb_product_category` VALUES (27, '素菜', 18, '2020-02-18 16:30:14', 18);
INSERT INTO `tb_product_category` VALUES (28, '酒水', 17, '2020-02-18 16:30:14', 18);
INSERT INTO `tb_product_category` VALUES (29, '其他', 16, '2020-02-18 16:30:14', 18);
INSERT INTO `tb_product_category` VALUES (30, '奶茶', 10, '2020-02-19 14:27:27', 19);
INSERT INTO `tb_product_category` VALUES (31, '茶', 9, '2020-02-19 14:27:27', 19);
INSERT INTO `tb_product_category` VALUES (32, '鲜榨果汁', 8, '2020-02-19 14:27:27', 19);
INSERT INTO `tb_product_category` VALUES (33, '跑车', 10, '2020-02-19 14:32:26', 20);
INSERT INTO `tb_product_category` VALUES (34, '保姆车', 9, '2020-02-19 14:32:26', 20);
INSERT INTO `tb_product_category` VALUES (35, '推理小说', 10, '2020-02-19 14:35:05', 21);
INSERT INTO `tb_product_category` VALUES (36, '言情小说', 9, '2020-02-19 14:35:05', 21);
INSERT INTO `tb_product_category` VALUES (37, '工具书', 8, '2020-02-19 14:35:05', 21);
INSERT INTO `tb_product_category` VALUES (38, '演出服', 10, '2020-02-19 14:38:46', 22);
INSERT INTO `tb_product_category` VALUES (39, '演出道具', 9, '2020-02-19 14:38:46', 22);
INSERT INTO `tb_product_category` VALUES (40, '恐怖', 10, '2020-02-20 09:43:03', 23);
INSERT INTO `tb_product_category` VALUES (41, '推理', 9, '2020-02-20 09:43:03', 23);
INSERT INTO `tb_product_category` VALUES (42, '经典豪车', 10, '2020-02-20 09:45:34', 24);
INSERT INTO `tb_product_category` VALUES (43, '家用', 9, '2020-02-20 09:45:34', 24);
INSERT INTO `tb_product_category` VALUES (45, '按摩', 10, '2020-02-20 09:47:52', 25);
INSERT INTO `tb_product_category` VALUES (46, '焗油', 9, '2020-02-20 09:47:52', 25);
INSERT INTO `tb_product_category` VALUES (47, '洗头', 10, '2020-02-20 09:49:34', 26);
INSERT INTO `tb_product_category` VALUES (48, '洗剪吹', 10, '2020-02-20 09:49:34', 26);
INSERT INTO `tb_product_category` VALUES (49, 'spa', 9, '2020-02-22 11:27:02', 25);
COMMIT;

-- ----------------------------
-- Table structure for tb_product_img
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_img`;
CREATE TABLE `tb_product_img` (
  `product_img_id` int(20) NOT NULL AUTO_INCREMENT,
  `img_addr` varchar(2000) NOT NULL,
  `img_desc` varchar(2000) DEFAULT NULL,
  `priority` int(2) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `product_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`product_img_id`),
  KEY `fk_proimg_product` (`product_id`),
  CONSTRAINT `fk_proimg_product` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_product_img
-- ----------------------------
BEGIN;
INSERT INTO `tb_product_img` VALUES (64, '/item/shop18/2020021816331885866.jpg', NULL, 1, '2020-02-18 16:33:18', 43);
INSERT INTO `tb_product_img` VALUES (65, '/item/shop18/2020021816331868356.jpeg', NULL, 1, '2020-02-18 16:33:18', 43);
INSERT INTO `tb_product_img` VALUES (66, '/item/shop18/2020021816331862034.jpg', NULL, 1, '2020-02-18 16:33:18', 43);
INSERT INTO `tb_product_img` VALUES (67, '/item/shop19/2020021914284921958.jpg', NULL, 1, '2020-02-19 14:28:49', 44);
INSERT INTO `tb_product_img` VALUES (68, '/item/shop19/2020021914284922376.png', NULL, 1, '2020-02-19 14:28:49', 44);
INSERT INTO `tb_product_img` VALUES (69, '/item/shop19/2020021914295540469.jpg', NULL, 1, '2020-02-19 14:29:56', 45);
INSERT INTO `tb_product_img` VALUES (70, '/item/shop19/2020021914295574176.png', NULL, 1, '2020-02-19 14:29:56', 45);
INSERT INTO `tb_product_img` VALUES (71, '/item/shop19/2020021914305649323.jpg', NULL, 1, '2020-02-19 14:30:57', 46);
INSERT INTO `tb_product_img` VALUES (72, '/item/shop19/2020021914305786928.png', NULL, 1, '2020-02-19 14:30:58', 46);
INSERT INTO `tb_product_img` VALUES (73, '/item/shop20/2020021914334076745.jpg', NULL, 1, '2020-02-19 14:33:41', 47);
INSERT INTO `tb_product_img` VALUES (74, '/item/shop20/2020021914334036806.jpg', NULL, 1, '2020-02-19 14:33:41', 47);
INSERT INTO `tb_product_img` VALUES (75, '/item/shop20/2020021914342560908.png', NULL, 1, '2020-02-19 14:34:25', 48);
INSERT INTO `tb_product_img` VALUES (76, '/item/shop20/2020021914342555339.jpg', NULL, 1, '2020-02-19 14:34:26', 48);
INSERT INTO `tb_product_img` VALUES (77, '/item/shop21/2020021914360510185.png', NULL, 1, '2020-02-19 14:36:05', 49);
INSERT INTO `tb_product_img` VALUES (78, '/item/shop21/2020021914360519035.jpg', NULL, 1, '2020-02-19 14:36:06', 49);
INSERT INTO `tb_product_img` VALUES (79, '/item/shop21/2020021914371323687.png', NULL, 1, '2020-02-19 14:37:14', 50);
INSERT INTO `tb_product_img` VALUES (80, '/item/shop21/2020021914371333500.jpg', NULL, 1, '2020-02-19 14:37:14', 50);
INSERT INTO `tb_product_img` VALUES (81, '/item/shop21/2020021914380318983.png', NULL, 1, '2020-02-19 14:38:04', 51);
INSERT INTO `tb_product_img` VALUES (82, '/item/shop21/2020021914380324627.jpg', NULL, 1, '2020-02-19 14:38:04', 51);
INSERT INTO `tb_product_img` VALUES (83, '/item/shop21/2020021914380412173.png', NULL, 1, '2020-02-19 14:38:05', 51);
INSERT INTO `tb_product_img` VALUES (84, '/item/shop22/2020021914393210326.png', NULL, 1, '2020-02-19 14:39:33', 52);
INSERT INTO `tb_product_img` VALUES (85, '/item/shop22/2020021914393298211.jpg', NULL, 1, '2020-02-19 14:39:33', 52);
INSERT INTO `tb_product_img` VALUES (86, '/item/shop22/2020021914393264103.png', NULL, 1, '2020-02-19 14:39:33', 52);
INSERT INTO `tb_product_img` VALUES (87, '/item/shop22/2020021914393311866.jpg', NULL, 1, '2020-02-19 14:39:33', 52);
INSERT INTO `tb_product_img` VALUES (88, '/item/shop22/2020021914405476822.png', NULL, 1, '2020-02-19 14:40:55', 53);
INSERT INTO `tb_product_img` VALUES (89, '/item/shop22/2020021914405498136.png', NULL, 1, '2020-02-19 14:40:55', 53);
INSERT INTO `tb_product_img` VALUES (90, '/item/shop22/2020021914405551205.jpg', NULL, 1, '2020-02-19 14:40:55', 53);
INSERT INTO `tb_product_img` VALUES (91, '/item/shop22/2020021914405522678.jpg', NULL, 1, '2020-02-19 14:40:55', 53);
INSERT INTO `tb_product_img` VALUES (92, '/item/shop22/2020021914405530543.png', NULL, 1, '2020-02-19 14:40:56', 53);
INSERT INTO `tb_product_img` VALUES (93, '/item/shop22/2020021914405585179.jpg', NULL, 1, '2020-02-19 14:40:56', 53);
INSERT INTO `tb_product_img` VALUES (94, '/item/shop23/2020022009435391509.jpg', NULL, 1, '2020-02-20 09:43:54', 54);
INSERT INTO `tb_product_img` VALUES (95, '/item/shop23/2020022009435342384.jpg', NULL, 1, '2020-02-20 09:43:54', 54);
INSERT INTO `tb_product_img` VALUES (96, '/item/shop23/2020022009435483876.png', NULL, 1, '2020-02-20 09:43:55', 54);
INSERT INTO `tb_product_img` VALUES (97, '/item/shop23/2020022009444057453.jpg', NULL, 1, '2020-02-20 09:44:40', 55);
INSERT INTO `tb_product_img` VALUES (98, '/item/shop23/2020022009444036701.png', NULL, 1, '2020-02-20 09:44:41', 55);
INSERT INTO `tb_product_img` VALUES (99, '/item/shop23/2020022009444073383.jpg', NULL, 1, '2020-02-20 09:44:41', 55);
INSERT INTO `tb_product_img` VALUES (100, '/item/shop24/2020022009465321929.png', NULL, 1, '2020-02-20 09:46:53', 56);
INSERT INTO `tb_product_img` VALUES (101, '/item/shop24/2020022009472277946.png', NULL, 1, '2020-02-20 09:47:22', 57);
INSERT INTO `tb_product_img` VALUES (102, '/item/shop25/2020022009482335002.jpg', NULL, 1, '2020-02-20 09:48:24', 58);
INSERT INTO `tb_product_img` VALUES (103, '/item/shop25/2020022009485681232.png', NULL, 1, '2020-02-20 09:48:57', 59);
INSERT INTO `tb_product_img` VALUES (104, '/item/shop25/2020022009485634566.jpg', NULL, 1, '2020-02-20 09:48:57', 59);
INSERT INTO `tb_product_img` VALUES (105, '/item/shop26/2020022009500487691.png', NULL, 1, '2020-02-20 09:50:05', 60);
INSERT INTO `tb_product_img` VALUES (106, '/item/shop26/2020022009503620374.jpg', NULL, 1, '2020-02-20 09:50:37', 61);
INSERT INTO `tb_product_img` VALUES (107, '/item/shop18/2020022020111146476.jpg', NULL, 1, '2020-02-20 20:11:32', 62);
INSERT INTO `tb_product_img` VALUES (108, '/item/shop18/2020022020113445570.png', NULL, 1, '2020-02-20 20:11:50', 62);
INSERT INTO `tb_product_img` VALUES (109, '/item/shop18/2020022020115078035.jpg', NULL, 1, '2020-02-20 20:11:53', 62);
INSERT INTO `tb_product_img` VALUES (110, '/item/shop18/2020022220595273351.jpeg', NULL, 1, '2020-02-22 20:59:52', 63);
INSERT INTO `tb_product_img` VALUES (111, '/item/shop18/2020022220595223947.jpg', NULL, 1, '2020-02-22 20:59:52', 63);
COMMIT;

-- ----------------------------
-- Table structure for tb_shop
-- ----------------------------
DROP TABLE IF EXISTS `tb_shop`;
CREATE TABLE `tb_shop` (
  `shop_id` int(10) NOT NULL AUTO_INCREMENT,
  `owner_id` int(10) NOT NULL COMMENT '店铺创建人',
  `area_id` int(5) DEFAULT NULL,
  `shop_category_id` int(11) DEFAULT NULL,
  `shop_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `shop_desc` varchar(1024) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `shop_addr` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `shop_img` varchar(1024) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `priority` int(3) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '0',
  `advice` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`shop_id`),
  KEY `fk_shop_profile` (`owner_id`),
  KEY `fk_shop_area` (`area_id`),
  KEY `fk_shop_shopcate` (`shop_category_id`),
  CONSTRAINT `fk_shop_area` FOREIGN KEY (`area_id`) REFERENCES `tb_area` (`area_id`),
  CONSTRAINT `fk_shop_profile` FOREIGN KEY (`owner_id`) REFERENCES `tb_person_info` (`user_id`),
  CONSTRAINT `fk_shop_shopcate` FOREIGN KEY (`shop_category_id`) REFERENCES `tb_shop_category` (`shop_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_shop
-- ----------------------------
BEGIN;
INSERT INTO `tb_shop` VALUES (18, 1, 3, 20, '小花烧烤', '好吃好喝好玩', '北门外100m', '188888888', '/item/shop18/2020021816281096656.jpg', NULL, '2020-02-18 16:28:10', '2020-02-18 16:28:10', 1, NULL);
INSERT INTO `tb_shop` VALUES (19, 1, 2, 22, '东东奶茶', '好喝', '西门外100米', '188888888', '/item/shop19/2020021914232652015.jpg', NULL, '2020-02-19 14:23:27', '2020-02-19 14:23:27', 1, NULL);
INSERT INTO `tb_shop` VALUES (20, 1, 3, 32, '小花租车', '好开', '北门外100米', '1888888', '/item/shop20/2020021914243143530.png', NULL, '2020-02-19 14:24:31', '2020-02-19 14:24:31', 1, NULL);
INSERT INTO `tb_shop` VALUES (21, 1, 4, 15, '小花二手书', '好看', '东门外1000米', '1888888888', '/item/shop21/2020021914253811687.png', NULL, '2020-02-19 14:25:38', '2020-02-19 14:25:38', 1, NULL);
INSERT INTO `tb_shop` VALUES (22, 1, 4, 31, '小花演出', '好穿好用', '东门外100米', '188888888', '/item/shop22/2020021914262879111.jpg', NULL, '2020-02-19 14:26:29', '2020-02-19 14:26:29', 1, NULL);
INSERT INTO `tb_shop` VALUES (23, 1, 3, 24, '小花密室逃生', '好玩，恐怖', '北门外100米', '188888', '/item/shop23/2020022009383121710.png', NULL, '2020-02-20 09:38:32', '2020-02-20 09:38:32', 1, NULL);
INSERT INTO `tb_shop` VALUES (24, 1, 4, 14, '小花二手车', '经典好货', '东门外100米', '199999999', '/item/shop24/2020022009391871070.jpg', NULL, '2020-02-20 09:39:19', '2020-02-20 09:39:19', 1, NULL);
INSERT INTO `tb_shop` VALUES (25, 1, 4, 17, '小花护理', '舒适，安逸', '东门外100米', '18888888', '/item/shop25/2020022009401187765.png', NULL, '2020-02-20 09:40:12', '2020-02-22 11:15:20', 1, NULL);
INSERT INTO `tb_shop` VALUES (26, 1, 1, 18, '小花理发', '有设计感，有性价比', '南门外100米', '188888888', '/item/shop26/2020022009412036047.png', NULL, '2020-02-20 09:41:20', '2020-02-20 09:41:20', 1, NULL);
COMMIT;

-- ----------------------------
-- Table structure for tb_shop_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_shop_category`;
CREATE TABLE `tb_shop_category` (
  `shop_category_id` int(10) NOT NULL AUTO_INCREMENT,
  `shop_category_name` varchar(100) NOT NULL DEFAULT '',
  `shop_category_desc` varchar(1024) DEFAULT '',
  `shop_category_img` varchar(2000) DEFAULT NULL,
  `priority` int(2) NOT NULL DEFAULT '0',
  `parent_id` int(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`shop_category_id`),
  KEY `fk_shop_category_self` (`parent_id`),
  CONSTRAINT `fk_shop_category_self` FOREIGN KEY (`parent_id`) REFERENCES `tb_shop_category` (`shop_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_shop_category
-- ----------------------------
BEGIN;
INSERT INTO `tb_shop_category` VALUES (10, '二手市场', '二手商品交易', '/item/fenlei/ershoushebei.png', 100, NULL, NULL, NULL);
INSERT INTO `tb_shop_category` VALUES (11, '美容美发', '美容美发', '/item/fenlei/40.png', 99, NULL, NULL, NULL);
INSERT INTO `tb_shop_category` VALUES (12, '美食饮品', '美食饮品', '/item/fenlei/dibudaohanglan-.png', 98, NULL, NULL, NULL);
INSERT INTO `tb_shop_category` VALUES (13, '休闲娱乐', '休闲娱乐', '/item/fenlei/xiuxianyule.png', 97, NULL, NULL, NULL);
INSERT INTO `tb_shop_category` VALUES (14, '旧车', '旧车', '', 80, 10, NULL, NULL);
INSERT INTO `tb_shop_category` VALUES (15, '二手书籍', '二手书籍', '', 79, 10, NULL, NULL);
INSERT INTO `tb_shop_category` VALUES (17, '护理', '护理', '', 76, 11, NULL, NULL);
INSERT INTO `tb_shop_category` VALUES (18, '理发', '理发', '', 74, 11, NULL, NULL);
INSERT INTO `tb_shop_category` VALUES (20, '大排档', '大排档', '', 59, 12, NULL, NULL);
INSERT INTO `tb_shop_category` VALUES (22, '奶茶店', '奶茶店', '', 58, 12, NULL, NULL);
INSERT INTO `tb_shop_category` VALUES (24, '密室逃生', '密室逃生', '', 56, 13, NULL, NULL);
INSERT INTO `tb_shop_category` VALUES (25, 'KTV', 'KTV', '', 57, 13, NULL, NULL);
INSERT INTO `tb_shop_category` VALUES (27, '培训教育', '培训教育', '/item/fenlei/jiaoyu.png', 96, NULL, NULL, NULL);
INSERT INTO `tb_shop_category` VALUES (28, '租赁市场', '租赁市场', '/item/fenlei/xunizulinhetong.png', 95, NULL, NULL, NULL);
INSERT INTO `tb_shop_category` VALUES (29, '程序设计', '程序设计', '', 50, 27, NULL, NULL);
INSERT INTO `tb_shop_category` VALUES (30, '声乐舞蹈', '声乐舞蹈', '', 49, 27, NULL, NULL);
INSERT INTO `tb_shop_category` VALUES (31, '演出道具', '演出道具', '', 45, 28, NULL, NULL);
INSERT INTO `tb_shop_category` VALUES (32, '交通工具', '交通工具', '', 44, 28, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for tb_wechat_auth
-- ----------------------------
DROP TABLE IF EXISTS `tb_wechat_auth`;
CREATE TABLE `tb_wechat_auth` (
  `wechat_auth_id` int(10) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(1024) NOT NULL,
  `user_id` int(10) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`wechat_auth_id`),
  UNIQUE KEY `open_id` (`open_id`),
  KEY `fk_wechatauth_profile` (`user_id`),
  CONSTRAINT `fk_wechatauth_profile` FOREIGN KEY (`user_id`) REFERENCES `tb_person_info` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
