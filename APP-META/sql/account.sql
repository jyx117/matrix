/******************************************/
/*   DatabaseName = matrix   */
/*   TableName = account_provider   */
/******************************************/
CREATE TABLE `account_provider`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `uid`          varchar(64)  NOT NULL COMMENT '主账号uid',
    `name`         varchar(64)  NOT NULL COMMENT '主账号名',
    `cloud_source` varchar(8)   NOT NULL COMMENT '云来源',
    `tenant`       varchar(16)  NOT NULL COMMENT '租户',
    `gmt_create`   timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_uid_tenant` (`uid`, `tenant`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='主账号表'
;

/******************************************/
/*   DatabaseName = matrix   */
/*   TableName = account_provider_config   */
/******************************************/
CREATE TABLE `account_provider_config`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `provider_uid` varchar(64)    NOT NULL COMMENT '主账号uid',
    `tenant`       varchar(16)    NOT NULL COMMENT '租户',
    `k`            varchar(32)    NOT NULL COMMENT '配置key',
    `v`            varchar(128) NOT NULL COMMENT '配置value',
    `level`        tinyint(2) NOT NULL COMMENT '风险等级',
    `description`  varchar(128) DEFAULT NULL COMMENT '描述信息',
    `gmt_create`   timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_pu_k_tenant` (`provider_uid`, `tenant`, `k`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='主账号配置表'
;

/******************************************/
/*   DatabaseName = matrix   */
/*   TableName = account_ram_account   */
/******************************************/
CREATE TABLE `account_ram_account`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `uid`          varchar(32)  NOT NULL COMMENT '子账号uid',
    `name`         varchar(64)  NOT NULL COMMENT '账号登录名简写',
    `login_name`   varchar(128) NOT NULL COMMENT '账号登录名',
    `show_name`    varchar(128) NOT NULL COMMENT '账号展示名',
    `comment`      varchar(128) DEFAULT NULL COMMENT '备注',
    `create_time`  timestamp    DEFAULT NULL COMMENT '账号创建时间',
    `phone`        varchar(16)  DEFAULT NULL COMMENT '电话',
    `mail`         varchar(32)  DEFAULT NULL COMMENT '邮箱地址',
    `provider_uid` varchar(64)  NOT NULL COMMENT '主账号uid',
    `tenant`       varchar(16)  NOT NULL COMMENT '租户',
    `gmt_create`   timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_ln_pu_tenant` (`login_name`, `provider_uid`, `tenant`),
    UNIQUE KEY `uk_n_pu_tenant` (`name`, `provider_uid`, `tenant`),
    UNIQUE KEY `uk_uid_pu_tenant` (`uid`, `provider_uid`, `tenant`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='子账号表'
;

/******************************************/
/*   DatabaseName = matrix   */
/*   TableName = account_account_config   */
/******************************************/
CREATE TABLE `account_account_config`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `account_uid`  varchar(32)  NOT NULL COMMENT '子账号uid',
    `provider_uid` varchar(64)  NOT NULL COMMENT '主账号uid',
    `tenant`       varchar(16)  NOT NULL COMMENT '租户',
    `k`            varchar(32)  NOT NULL COMMENT '配置key',
    `v`            varchar(10240) NOT NULL COMMENT '配置value',
    `level`        tinyint(2) NOT NULL COMMENT '风险等级',
    `description`  varchar(128) DEFAULT NULL COMMENT '描述信息',
    `gmt_create`   timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_au_pu_tenant_k` (`account_uid`, `provider_uid`, `k`, `tenant`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='子账号配置表'
;

/******************************************/
/*   DatabaseName = matrix   */
/*   TableName = account_account_user   */
/******************************************/
CREATE TABLE `account_account_user`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `account_uid`  varchar(32) NOT NULL COMMENT '子账号uid',
    `provider_uid` varchar(64) NOT NULL COMMENT '主账号uid',
    `tenant`       varchar(16) NOT NULL COMMENT '租户',
    `user_id`      varchar(32) NOT NULL COMMENT '用户id',
    `type`         varchar(8)  NOT NULL COMMENT '类型',
    `gmt_create`   timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_au_pu_tenant_u` (`account_uid`, `provider_uid`, `user_id`, `tenant`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='子账号-用户映射表'
;

/******************************************/
/*   DatabaseName = matrix   */
/*   TableName = account_ram_group   */
/******************************************/
CREATE TABLE `account_ram_group`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`         varchar(64) NOT NULL COMMENT '用户组名',
    `show_name`    varchar(128) DEFAULT NULL COMMENT '用户组展示名',
    `comment`      varchar(128) DEFAULT NULL COMMENT '备注',
    `create_time`  timestamp    DEFAULT NULL COMMENT '账号创建时间',
    `provider_uid` varchar(64) NOT NULL COMMENT '主账号uid',
    `tenant`       varchar(16) NOT NULL COMMENT '租户',
    `gmt_create`   timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_name_pu_tenant` (`name`, `provider_uid`, `tenant`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='子账号用户组表'
;

/******************************************/
/*   DatabaseName = matrix   */
/*   TableName = account_account_group   */
/******************************************/
CREATE TABLE `account_account_group`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `account_uid`  varchar(32) NOT NULL COMMENT '子账号uid',
    `group_name`   varchar(64) DEFAULT NULL COMMENT '用户组名',
    `provider_uid` varchar(64) NOT NULL COMMENT '主账号uid',
    `tenant`       varchar(16) NOT NULL COMMENT '租户',
    `gmt_create`   timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_au_gn_pu_tenant` (`account_uid`, `group_name`, `provider_uid`, `tenant`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='子账号-用户组映射表'
;

/******************************************/
/*   DatabaseName = matrix   */
/*   TableName = account_ram_role   */
/******************************************/
CREATE TABLE `account_ram_role`
(
    `id`              bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`            varchar(64)  NOT NULL COMMENT '角色名',
    `arn`             varchar(128) NOT NULL COMMENT 'arn',
    `provider_uid`    varchar(64)  NOT NULL COMMENT '主账号uid',
    `session_timeout` int(5) unsigned NOT NULL DEFAULT 3600 COMMENT '角色登录超时时间',
    `comment`         varchar(1024) DEFAULT NULL COMMENT '备注',
    `create_time`     timestamp     DEFAULT NULL COMMENT '角色创建时间',
    `tenant`          varchar(16)  NOT NULL COMMENT '租户',
    `gmt_create`      timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified`    timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_name_pu_tenant` (`name`, `provider_uid`, `tenant`),
    UNIQUE KEY `uk_arn_pu_tenant` (`arn`, `provider_uid`, `tenant`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='子账号角色表'
;

/******************************************/
/*   DatabaseName = matrix   */
/*   TableName = account_policy   */
/******************************************/
CREATE TABLE `account_policy`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`         varchar(128) NOT NULL COMMENT '权限名',
    `type`         varchar(8)   NOT NULL COMMENT '权限类型',
    `provider_uid` varchar(64)  NOT NULL COMMENT '主账号uid',
    `comment`      varchar(1024) DEFAULT NULL COMMENT '备注',
    `document`     text         NOT NULL COMMENT '权限文本',
    `tenant`       varchar(16)  NOT NULL COMMENT '租户',
    `gmt_create`   timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_name_pu_tenant` (`name`, `provider_uid`, `tenant`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='子账号权限表'
;

/******************************************/
/*   DatabaseName = matrix   */
/*   TableName = account_account_policy   */
/******************************************/
CREATE TABLE `account_account_policy`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `account_uid`  varchar(32)  NOT NULL COMMENT '子账号uid',
    `policy_name`  varchar(128) NOT NULL COMMENT '权限名',
    `provider_uid` varchar(64)  NOT NULL COMMENT '主账号uid',
    `tenant`       varchar(16)  NOT NULL COMMENT '租户',
    `gmt_create`   timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_au_pu_tenant` (`account_uid`, `policy_name`, `provider_uid`, `tenant`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='子账号-权限映射表'
;

/******************************************/
/*   DatabaseName = matrix   */
/*   TableName = account_group_policy   */
/******************************************/
CREATE TABLE `account_group_policy`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `group_name`   varchar(64)  NOT NULL COMMENT '子账号组',
    `policy_name`  varchar(128) NOT NULL COMMENT '权限名',
    `provider_uid` varchar(64)  NOT NULL COMMENT '主账号uid',
    `tenant`       varchar(16)  NOT NULL COMMENT '租户',
    `gmt_create`   timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_gn_pu_tenant` (`group_name`, `policy_name`, `provider_uid`, `tenant`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='子账号组-权限映射表'
;

/******************************************/
/*   DatabaseName = matrix   */
/*   TableName = account_role_policy   */
/******************************************/
CREATE TABLE `account_role_policy`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `role_name`    varchar(64)  NOT NULL COMMENT '角色名',
    `policy_name`  varchar(128) NOT NULL COMMENT '权限名',
    `provider_uid` varchar(64)  NOT NULL COMMENT '主账号uid',
    `tenant`       varchar(16)  NOT NULL COMMENT '租户',
    `gmt_create`   timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_rn_pu_tenant` (`role_name`, `policy_name`, `provider_uid`, `tenant`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色-权限映射表'
;