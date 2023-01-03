/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : ageiport

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 03/01/2023 19:49:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for agei_main_task_instance
-- ----------------------------
DROP TABLE IF EXISTS `agei_main_task_instance`;
CREATE TABLE `agei_main_task_instance`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `app` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `biz_key` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `biz_query` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `biz_task_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `biz_user_feature` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `biz_user_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `biz_user_key` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `biz_user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `biz_user_org` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `biz_user_tenant` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `data_failed_count` int(11) NULL DEFAULT NULL,
  `data_processed_count` int(11) NULL DEFAULT NULL,
  `data_success_count` int(11) NULL DEFAULT NULL,
  `data_total_count` int(11) NULL DEFAULT NULL,
  `env` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `execute_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `feature` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `flow_order` int(11) NULL DEFAULT NULL,
  `flow_task_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `gmt_create` datetime(0) NULL DEFAULT NULL,
  `gmt_dispatch` datetime(0) NULL DEFAULT NULL,
  `gmt_execute` datetime(0) NULL DEFAULT NULL,
  `gmt_expired` datetime(0) NULL DEFAULT NULL,
  `gmt_finished` datetime(0) NULL DEFAULT NULL,
  `gmt_modified` datetime(0) NULL DEFAULT NULL,
  `gmt_start` datetime(0) NULL DEFAULT NULL,
  `host` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `log` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `main_task_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `namespace` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `result_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `result_message` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `retry_times` int(11) NULL DEFAULT NULL,
  `row_status` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `row_version` int(11) NULL DEFAULT NULL,
  `runtime_param` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `status` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `sub_failed_count` int(11) NULL DEFAULT NULL,
  `sub_finished_count` int(11) NULL DEFAULT NULL,
  `sub_success_count` int(11) NULL DEFAULT NULL,
  `sub_total_count` int(11) NULL DEFAULT NULL,
  `tenant` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trace_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_mainTaskId`(`main_task_id`) USING BTREE,
  INDEX `idx_main_task`(`tenant`, `namespace`, `app`, `env`, `code`) USING BTREE,
  INDEX `idx_bizUser`(`biz_user_tenant`, `biz_user_org`, `biz_user_id`, `biz_user_key`) USING BTREE,
  INDEX `idx_gmt_create`(`gmt_create`) USING BTREE,
  INDEX `idx_flowTaskId`(`flow_task_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of agei_main_task_instance
-- ----------------------------
INSERT INTO `agei_main_task_instance` VALUES (1, 'app', 'Eh6VEF', '{}', 'HzloC5G', 'owhAFMy3M', 'uCX', '6sjYvt', 'WJ9msi', 'vrasg', 'kiNN9zEPr', 'TaskCode', NULL, NULL, NULL, NULL, 'PRODUCTION', 'SHARDING', NULL, NULL, NULL, '2022-06-16 17:00:08', NULL, NULL, NULL, NULL, '2022-06-16 17:00:08', NULL, '127.0.0.1', NULL, '20220616170008-f253', 'TaskName', 'com.alibaba', NULL, NULL, 0, 'VALID', 1, '{}', 'NEW', NULL, NULL, NULL, NULL, 'TenantTest', 'edea3bab-75b2-4c86-b379-61763426c326', 'EXPORT');

-- ----------------------------
-- Table structure for agei_sub_task_instance
-- ----------------------------
DROP TABLE IF EXISTS `agei_sub_task_instance`;
CREATE TABLE `agei_sub_task_instance`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `app` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `biz_key` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `biz_query` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `biz_task_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `biz_user_feature` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `biz_user_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `biz_user_key` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `biz_user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `biz_user_org` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `biz_user_tenant` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `data_failed_count` int(11) NULL DEFAULT NULL,
  `data_processed_count` int(11) NULL DEFAULT NULL,
  `data_success_count` int(11) NULL DEFAULT NULL,
  `data_total_count` int(11) NULL DEFAULT NULL,
  `env` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `execute_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `feature` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `flow_order` int(11) NULL DEFAULT NULL,
  `flow_task_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `gmt_create` datetime(0) NULL DEFAULT NULL,
  `gmt_dispatch` datetime(0) NULL DEFAULT NULL,
  `gmt_execute` datetime(0) NULL DEFAULT NULL,
  `gmt_expired` datetime(0) NULL DEFAULT NULL,
  `gmt_finished` datetime(0) NULL DEFAULT NULL,
  `gmt_modified` datetime(0) NULL DEFAULT NULL,
  `gmt_start` datetime(0) NULL DEFAULT NULL,
  `host` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `log` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `main_task_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `namespace` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `result_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `result_message` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `retry_times` int(11) NULL DEFAULT NULL,
  `row_status` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `row_version` int(11) NULL DEFAULT NULL,
  `runtime_param` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `status` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `sub_task_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sub_task_no` int(11) NULL DEFAULT NULL,
  `tenant` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `trace_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_mainTaskId`(`main_task_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of agei_sub_task_instance
-- ----------------------------

-- ----------------------------
-- Table structure for agei_task_specification
-- ----------------------------
DROP TABLE IF EXISTS `agei_task_specification`;
CREATE TABLE `agei_task_specification`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `app` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `creator_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `creator_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `env` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `feature` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  `modifier_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `modifier_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `namespace` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `owner_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `owner_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `row_status` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `row_version` int(11) NOT NULL,
  `status` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `task_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `task_desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `task_execute_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `task_handler` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `task_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `task_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `tenant` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `tenant_namespace_app_env_task_code`(`tenant`, `namespace`, `app`, `env`, `task_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of agei_task_specification
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
