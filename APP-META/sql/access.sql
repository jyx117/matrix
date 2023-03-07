/******************************************/
/*   DatabaseName = matrix   */
/*   TableName = tenant   */
/******************************************/
CREATE TABLE `tenant`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `code`         varchar(16) NOT NULL COMMENT 'user_id',
    `name`         varchar(32) NOT NULL COMMENT 'name',
    `gmt_create`   timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='tenant'
;

/******************************************/
/*   DatabaseName = matrix   */
/*   TableName = access_user   */
/******************************************/
CREATE TABLE `access_user`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id`      varchar(64) NOT NULL COMMENT 'user_id',
    `name`         varchar(32) NOT NULL COMMENT 'name',
    `avatar`       varchar(128)         DEFAULT NULL COMMENT '头像地址',
    `gmt_create`   timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='user'
;

/******************************************/
/*   DatabaseName = matrix   */
/*   TableName = access_user_auth   */
/******************************************/
CREATE TABLE `access_user_auth`
(
    `id`            bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id`       varchar(64)  NOT NULL COMMENT '用户id,和user表user_id字段对应',
    `identifier`    varchar(128) NOT NULL COMMENT '唯一标识码，如type是电话时则为电话号码',
    `identity_type` varchar(16)  NOT NULL COMMENT '唯一标识码类型：邮箱/手机号/微信/支付宝/钉钉等，支持第三方登录',
    `credential`    varchar(256) NOT NULL COMMENT '密码凭证（站内的保存密码，站外的不保存或保存token）',
    `gmt_create`    timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified`  timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_uid` (`user_id`),
    UNIQUE KEY `uk_id_idt` (`identifier`,`identity_type`)
) ENGINE=InnoDB AUTO_INCREMENT=385 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户鉴权表'
;

/******************************************/
/*   DatabaseName = matrix   */
/*   TableName = access_role   */
/******************************************/
CREATE TABLE `access_role`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `code`         varchar(32) NOT NULL COMMENT '角色code',
    `description`  varchar(64) NOT NULL COMMENT 'desc',
    `name`         varchar(32) NOT NULL COMMENT 'name',
    `tenant`       varchar(16) NOT NULL COMMENT 'tenant',
    `gmt_create`   timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_code_tenant` (`code`, `tenant`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='role'
;

/******************************************/
/*   DatabaseName = matrix   */
/*   TableName = access_user_role   */
/******************************************/
CREATE TABLE `access_user_role`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `role_code`    varchar(32) NOT NULL COMMENT '角色code',
    `user_id`      varchar(64) NOT NULL COMMENT 'uid',
    `tenant`       varchar(16) NOT NULL COMMENT 'tenant',
    `gmt_create`   timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_uid_code_tenant` (`user_id`, `role_code`, `tenant`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='user_role'
;

/******************************************/
/*   DatabaseName = matrix   */
/*   TableName = access_permission  */
/******************************************/
CREATE TABLE `access_permission`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `code`         varchar(64) NOT NULL COMMENT '权限code',
    `type`         varchar(8)  NOT NULL COMMENT '类型',
    `name`         varchar(32) NOT NULL COMMENT 'name',
    `gmt_create`   timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_code_type` (`code`, `type`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='permission'
;

/******************************************/
/*   DatabaseName = matrix   */
/*   TableName = access_role_permission  */
/******************************************/
CREATE TABLE `access_role_permission`
(
    `id`              bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `role_code`       varchar(32) NOT NULL COMMENT '角色code',
    `permission_code` varchar(64) NOT NULL COMMENT '权限code',
    `permission_type` varchar(8)  NOT NULL COMMENT '权限类型',
    `tenant`          varchar(16) NOT NULL COMMENT 'tenant',
    `gmt_create`      timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified`    timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_r_p_t` (`role_code`, `permission_code`, `permission_type`, `tenant`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='role_permission'
;