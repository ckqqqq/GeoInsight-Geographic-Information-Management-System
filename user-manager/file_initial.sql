
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE DATABASE IF NOT EXISTS project_database DEFAULT CHARACTER SET utf8;
# SHOW DATABASE '';
USE project_database;
CREATE TABLE `file` (
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `file_short_name` varchar(200) CHARACTER SET utf8mb4  DEFAULT NULL COMMENT '文件名',
                        `file_full_name` varchar(200) CHARACTER SET utf8mb4  DEFAULT NULL COMMENT '存储文件名',
                        `file_type` varchar(200) CHARACTER SET utf8mb4  DEFAULT NULL COMMENT '文件类型',
                        `file_uri` varchar(300) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '文件URI',
                        `file_size` bigint DEFAULT NULL COMMENT '文件大小',
                        `publish_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='课程文件表';