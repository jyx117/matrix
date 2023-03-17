# ************************************************************
# Sequel Pro SQL dump
# Version 5446
#
# https://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 8.0.27)
# Database: matrix
# Generation Time: 2023-03-17 08:52:06 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table access_permission
# ------------------------------------------------------------

DROP TABLE IF EXISTS `access_permission`;

CREATE TABLE `access_permission` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(64) NOT NULL COMMENT '权限code',
  `type` varchar(8) NOT NULL COMMENT '类型',
  `name` varchar(32) NOT NULL COMMENT 'name',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_code_type` (`code`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='permission';



# Dump of table access_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `access_role`;

CREATE TABLE `access_role` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(32) NOT NULL COMMENT '角色code',
  `description` varchar(64) NOT NULL COMMENT 'desc',
  `name` varchar(32) NOT NULL COMMENT 'name',
  `tenant` varchar(16) NOT NULL COMMENT 'tenant',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_code_tenant` (`code`,`tenant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='role';



# Dump of table access_role_permission
# ------------------------------------------------------------

DROP TABLE IF EXISTS `access_role_permission`;

CREATE TABLE `access_role_permission` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_code` varchar(32) NOT NULL COMMENT '角色code',
  `permission_code` varchar(64) NOT NULL COMMENT '权限code',
  `permission_type` varchar(8) NOT NULL COMMENT '权限类型',
  `tenant` varchar(16) NOT NULL COMMENT 'tenant',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_r_p_t` (`role_code`,`permission_code`,`permission_type`,`tenant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='role_permission';



# Dump of table access_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `access_user`;

CREATE TABLE `access_user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` varchar(64) NOT NULL COMMENT 'user_id',
  `name` varchar(32) NOT NULL COMMENT 'name',
  `avatar` varchar(128) DEFAULT NULL COMMENT '头像地址',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='user';

LOCK TABLES `access_user` WRITE;
/*!40000 ALTER TABLE `access_user` DISABLE KEYS */;

INSERT INTO `access_user` (`id`, `user_id`, `name`, `avatar`, `gmt_create`, `gmt_modified`)
VALUES
	(3,'uid1','测试用户',NULL,'2023-03-07 16:00:30','2023-03-07 16:00:30'),
	(4,'USER_RGsPOK2x_202303071720430','name1',NULL,'2023-03-07 17:20:43','2023-03-07 17:20:43');

/*!40000 ALTER TABLE `access_user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table access_user_auth
# ------------------------------------------------------------

DROP TABLE IF EXISTS `access_user_auth`;

CREATE TABLE `access_user_auth` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` varchar(64) NOT NULL COMMENT '用户id,和user表user_id字段对应',
  `identifier` varchar(128) NOT NULL COMMENT '唯一标识码，如type是电话时则为电话号码',
  `identity_type` varchar(16) NOT NULL COMMENT '唯一标识码类型：邮箱/手机号/微信/支付宝/钉钉等，支持第三方登录',
  `credential` varchar(256) DEFAULT NULL,
  `gmt_create` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_uid` (`user_id`),
  UNIQUE KEY `uk_id_idt` (`identifier`,`identity_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户鉴权表';

LOCK TABLES `access_user_auth` WRITE;
/*!40000 ALTER TABLE `access_user_auth` DISABLE KEYS */;

INSERT INTO `access_user_auth` (`id`, `user_id`, `identifier`, `identity_type`, `credential`, `gmt_create`, `gmt_modified`)
VALUES
	(385,'uid1','','PASSWORD','111','2023-03-07 16:03:59','2023-03-07 16:03:59'),
	(386,'USER_RGsPOK2x_202303071720430','name1','PASSWORD','$2a$10$12g5Hd1tfPsgqkLwdByVa.S/NbP8z3mRsVTydH8prAta0AH33FzQq','2023-03-07 17:20:43','2023-03-07 17:20:43');

/*!40000 ALTER TABLE `access_user_auth` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table access_user_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `access_user_role`;

CREATE TABLE `access_user_role` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_code` varchar(32) NOT NULL COMMENT '角色code',
  `user_id` varchar(64) NOT NULL COMMENT 'uid',
  `tenant` varchar(16) NOT NULL COMMENT 'tenant',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_uid_code_tenant` (`user_id`,`role_code`,`tenant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='user_role';

LOCK TABLES `access_user_role` WRITE;
/*!40000 ALTER TABLE `access_user_role` DISABLE KEYS */;

INSERT INTO `access_user_role` (`id`, `role_code`, `user_id`, `tenant`, `gmt_create`, `gmt_modified`)
VALUES
	(1,'admin','USER_RGsPOK2x_202303071720430','default','2023-03-08 16:08:12','2023-03-08 16:08:12'),
	(2,'admin','USER_RGsPOK2x_202303071720430','test','2023-03-09 08:54:43','2023-03-09 08:54:43'),
	(3,'common','USER_RGsPOK2x_202303071720430','test','2023-03-09 09:02:32','2023-03-09 09:02:32');

/*!40000 ALTER TABLE `access_user_role` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table account_account_config
# ------------------------------------------------------------

DROP TABLE IF EXISTS `account_account_config`;

CREATE TABLE `account_account_config` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account_uid` varchar(32) NOT NULL COMMENT '子账号uid',
  `provider_uid` varchar(64) NOT NULL COMMENT '主账号uid',
  `tenant` varchar(16) NOT NULL COMMENT '租户',
  `k` varchar(32) NOT NULL COMMENT '配置key',
  `v` varchar(10240) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '配置value',
  `level` tinyint NOT NULL COMMENT '风险等级',
  `description` varchar(128) DEFAULT NULL COMMENT '描述信息',
  `gmt_create` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_au_pu_tenant_k` (`account_uid`,`provider_uid`,`k`,`tenant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='子账号配置表';

LOCK TABLES `account_account_config` WRITE;
/*!40000 ALTER TABLE `account_account_config` DISABLE KEYS */;

INSERT INTO `account_account_config` (`id`, `account_uid`, `provider_uid`, `tenant`, `k`, `v`, `level`, `description`, `gmt_create`, `gmt_modified`)
VALUES
	(1,'261698279016849473','1564266258685674','default','LOGIN_TYPE','SSO',1,'登录方式','2023-03-17 16:19:13','2023-03-17 16:19:13'),
	(2,'261698279016849473','1564266258685674','default','SSO_SAML_DOCUMENT','<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<EntityDescriptor  xmlns=\"urn:oasis:names:tc:SAML:2.0:metadata\" xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:shibmd=\"urn:mace:shibboleth:metadata:1.0\" xmlns:xml=\"http://www.w3.org/XML/1998/namespace\" xmlns:mdui=\"urn:oasis:names:tc:SAML:metadata:ui\" entityID=\"https://cloudmatrix.alibaba-inc.com/accounts/idp/sso\">\n    <IDPSSODescriptor protocolSupportEnumeration=\"urn:oasis:names:tc:SAML:2.0:protocol\">\n        <KeyDescriptor use=\"signing\">\n            <ds:KeyInfo>\n                <ds:X509Data>\n                    <ds:X509Certificate>\n                        MIIDcjCCAloCCQCETzeMecnwKDANBgkqhkiG9w0BAQsFADB7MQswCQYDVQQGEwJj\nbjELMAkGA1UECAwCaHoxDDAKBgNVBAcMA2FsaTEMMAoGA1UECgwDYWxpMRAwDgYD\nVQQLDAdhbGliYWJhMQwwCgYDVQQDDANndHMxIzAhBgkqhkiG9w0BCQEWFHl1eGlu\nLmp5eEBhbnRmaW4uY29tMB4XDTIxMDkyNDEwMDQzOVoXDTMxMDkyMjEwMDQzOVow\nezELMAkGA1UEBhMCY24xCzAJBgNVBAgMAmh6MQwwCgYDVQQHDANhbGkxDDAKBgNV\nBAoMA2FsaTEQMA4GA1UECwwHYWxpYmFiYTEMMAoGA1UEAwwDZ3RzMSMwIQYJKoZI\nhvcNAQkBFhR5dXhpbi5qeXhAYW50ZmluLmNvbTCCASIwDQYJKoZIhvcNAQEBBQAD\nggEPADCCAQoCggEBANIdVwg1KPIwARB9yo2G+/6kT5NKIr6LiJmnaNVpNFZNH6F2\np+VgWEr3RR8rKdGJ7Sp5BolwR+mnuc3BuV6rXveMQuZsPGH/r0sZLS9RVENMvH1V\ntYttMc34T8WLNLyXyJcKM6FKraYCP/G9MsrMC160vF0U0S5AMqlz8/zqT4zkPUsE\nsZ4fI1BQumwHbdq6mY5ZUXWVz31vXNowovBVa6CwSblNEFwgDRSLSOdjnprelQ1L\nNwqJXw9zAWTxtXDV03uRzdD8/XqwNnWD13/4ta4u8X5r3lzg6K3/rPnvWQoROl4o\nLNX4Jonx8ulBImZ/84QcNjkeRqXoGWiiiB1koWMCAwEAATANBgkqhkiG9w0BAQsF\nAAOCAQEAFy/Zlr7E2cJeC6pMQvV22Kfh/3RpwhMOXDjig0fKFMjPeChFrNjQzJDS\npyyFykWPEa2PNXLc9ySycbq76WWELQ5w1Obr6zbf+7EKGeMGFZ0W/Z54oEHlxyqG\nY0nx75XG+ReBIoBTiNh4032MUNdwa5uW6kB4/2xTldYZhg9Tih7KwzT5LreU4Vch\nZE9EfZAnBxJql2xKmKRAn7Cq1G+0lKrRJIiUb808ytNZlnbV1niEcUn8QAvwVrRZ\nB2Dysu5m1Q4S2GbZi/6JR+wXpbDTOYGVi0UGp7eKzkoGgmnaez+65qr0kAHiiJhA\nmy8881ijns07HxM6ES77bvybe9Bbug==\n                    </ds:X509Certificate>\n                </ds:X509Data>\n            </ds:KeyInfo>\n        </KeyDescriptor>\n        <KeyDescriptor use=\"signing\">\n            <ds:KeyInfo>\n                <ds:X509Data>\n                    <ds:X509Certificate>\n                        MIIDcjCCAloCCQCETzeMecnwKDANBgkqhkiG9w0BAQsFADB7MQswCQYDVQQGEwJj\nbjELMAkGA1UECAwCaHoxDDAKBgNVBAcMA2FsaTEMMAoGA1UECgwDYWxpMRAwDgYD\nVQQLDAdhbGliYWJhMQwwCgYDVQQDDANndHMxIzAhBgkqhkiG9w0BCQEWFHl1eGlu\nLmp5eEBhbnRmaW4uY29tMB4XDTIxMDkyNDEwMDQzOVoXDTMxMDkyMjEwMDQzOVow\nezELMAkGA1UEBhMCY24xCzAJBgNVBAgMAmh6MQwwCgYDVQQHDANhbGkxDDAKBgNV\nBAoMA2FsaTEQMA4GA1UECwwHYWxpYmFiYTEMMAoGA1UEAwwDZ3RzMSMwIQYJKoZI\nhvcNAQkBFhR5dXhpbi5qeXhAYW50ZmluLmNvbTCCASIwDQYJKoZIhvcNAQEBBQAD\nggEPADCCAQoCggEBANIdVwg1KPIwARB9yo2G+/6kT5NKIr6LiJmnaNVpNFZNH6F2\np+VgWEr3RR8rKdGJ7Sp5BolwR+mnuc3BuV6rXveMQuZsPGH/r0sZLS9RVENMvH1V\ntYttMc34T8WLNLyXyJcKM6FKraYCP/G9MsrMC160vF0U0S5AMqlz8/zqT4zkPUsE\nsZ4fI1BQumwHbdq6mY5ZUXWVz31vXNowovBVa6CwSblNEFwgDRSLSOdjnprelQ1L\nNwqJXw9zAWTxtXDV03uRzdD8/XqwNnWD13/4ta4u8X5r3lzg6K3/rPnvWQoROl4o\nLNX4Jonx8ulBImZ/84QcNjkeRqXoGWiiiB1koWMCAwEAATANBgkqhkiG9w0BAQsF\nAAOCAQEAFy/Zlr7E2cJeC6pMQvV22Kfh/3RpwhMOXDjig0fKFMjPeChFrNjQzJDS\npyyFykWPEa2PNXLc9ySycbq76WWELQ5w1Obr6zbf+7EKGeMGFZ0W/Z54oEHlxyqG\nY0nx75XG+ReBIoBTiNh4032MUNdwa5uW6kB4/2xTldYZhg9Tih7KwzT5LreU4Vch\nZE9EfZAnBxJql2xKmKRAn7Cq1G+0lKrRJIiUb808ytNZlnbV1niEcUn8QAvwVrRZ\nB2Dysu5m1Q4S2GbZi/6JR+wXpbDTOYGVi0UGp7eKzkoGgmnaez+65qr0kAHiiJhA\nmy8881ijns07HxM6ES77bvybe9Bbug==\n                    </ds:X509Certificate>\n                </ds:X509Data>\n            </ds:KeyInfo>\n        </KeyDescriptor>\n        <KeyDescriptor use=\"encryption\">\n            <ds:KeyInfo>\n                <ds:X509Data>\n                    <ds:X509Certificate>\n                        MIIDcjCCAloCCQCETzeMecnwKDANBgkqhkiG9w0BAQsFADB7MQswCQYDVQQGEwJj\nbjELMAkGA1UECAwCaHoxDDAKBgNVBAcMA2FsaTEMMAoGA1UECgwDYWxpMRAwDgYD\nVQQLDAdhbGliYWJhMQwwCgYDVQQDDANndHMxIzAhBgkqhkiG9w0BCQEWFHl1eGlu\nLmp5eEBhbnRmaW4uY29tMB4XDTIxMDkyNDEwMDQzOVoXDTMxMDkyMjEwMDQzOVow\nezELMAkGA1UEBhMCY24xCzAJBgNVBAgMAmh6MQwwCgYDVQQHDANhbGkxDDAKBgNV\nBAoMA2FsaTEQMA4GA1UECwwHYWxpYmFiYTEMMAoGA1UEAwwDZ3RzMSMwIQYJKoZI\nhvcNAQkBFhR5dXhpbi5qeXhAYW50ZmluLmNvbTCCASIwDQYJKoZIhvcNAQEBBQAD\nggEPADCCAQoCggEBANIdVwg1KPIwARB9yo2G+/6kT5NKIr6LiJmnaNVpNFZNH6F2\np+VgWEr3RR8rKdGJ7Sp5BolwR+mnuc3BuV6rXveMQuZsPGH/r0sZLS9RVENMvH1V\ntYttMc34T8WLNLyXyJcKM6FKraYCP/G9MsrMC160vF0U0S5AMqlz8/zqT4zkPUsE\nsZ4fI1BQumwHbdq6mY5ZUXWVz31vXNowovBVa6CwSblNEFwgDRSLSOdjnprelQ1L\nNwqJXw9zAWTxtXDV03uRzdD8/XqwNnWD13/4ta4u8X5r3lzg6K3/rPnvWQoROl4o\nLNX4Jonx8ulBImZ/84QcNjkeRqXoGWiiiB1koWMCAwEAATANBgkqhkiG9w0BAQsF\nAAOCAQEAFy/Zlr7E2cJeC6pMQvV22Kfh/3RpwhMOXDjig0fKFMjPeChFrNjQzJDS\npyyFykWPEa2PNXLc9ySycbq76WWELQ5w1Obr6zbf+7EKGeMGFZ0W/Z54oEHlxyqG\nY0nx75XG+ReBIoBTiNh4032MUNdwa5uW6kB4/2xTldYZhg9Tih7KwzT5LreU4Vch\nZE9EfZAnBxJql2xKmKRAn7Cq1G+0lKrRJIiUb808ytNZlnbV1niEcUn8QAvwVrRZ\nB2Dysu5m1Q4S2GbZi/6JR+wXpbDTOYGVi0UGp7eKzkoGgmnaez+65qr0kAHiiJhA\nmy8881ijns07HxM6ES77bvybe9Bbug==\n                    </ds:X509Certificate>\n                </ds:X509Data>\n            </ds:KeyInfo>\n        </KeyDescriptor>\n        <SingleSignOnService Binding=\"urn:oasis:names:tc:SAML:2.0:bindings:HTTP-Redirect\" Location=\"https://cloudmatrix.alibaba-inc.com/account/index\"/>\n    </IDPSSODescriptor>\n</EntityDescriptor>',1,'登录方式','2023-03-17 16:19:38','2023-03-17 16:27:10'),
	(3,'261698279016849473','1564266258685674','default','SSO_SAML_PUBLIC_KEY','MIIDcjCCAloCCQCETzeMecnwKDANBgkqhkiG9w0BAQsFADB7MQswCQYDVQQGEwJj\nbjELMAkGA1UECAwCaHoxDDAKBgNVBAcMA2FsaTEMMAoGA1UECgwDYWxpMRAwDgYD\nVQQLDAdhbGliYWJhMQwwCgYDVQQDDANndHMxIzAhBgkqhkiG9w0BCQEWFHl1eGlu\nLmp5eEBhbnRmaW4uY29tMB4XDTIxMDkyNDEwMDQzOVoXDTMxMDkyMjEwMDQzOVow\nezELMAkGA1UEBhMCY24xCzAJBgNVBAgMAmh6MQwwCgYDVQQHDANhbGkxDDAKBgNV\nBAoMA2FsaTEQMA4GA1UECwwHYWxpYmFiYTEMMAoGA1UEAwwDZ3RzMSMwIQYJKoZI\nhvcNAQkBFhR5dXhpbi5qeXhAYW50ZmluLmNvbTCCASIwDQYJKoZIhvcNAQEBBQAD\nggEPADCCAQoCggEBANIdVwg1KPIwARB9yo2G+/6kT5NKIr6LiJmnaNVpNFZNH6F2\np+VgWEr3RR8rKdGJ7Sp5BolwR+mnuc3BuV6rXveMQuZsPGH/r0sZLS9RVENMvH1V\ntYttMc34T8WLNLyXyJcKM6FKraYCP/G9MsrMC160vF0U0S5AMqlz8/zqT4zkPUsE\nsZ4fI1BQumwHbdq6mY5ZUXWVz31vXNowovBVa6CwSblNEFwgDRSLSOdjnprelQ1L\nNwqJXw9zAWTxtXDV03uRzdD8/XqwNnWD13/4ta4u8X5r3lzg6K3/rPnvWQoROl4o\nLNX4Jonx8ulBImZ/84QcNjkeRqXoGWiiiB1koWMCAwEAATANBgkqhkiG9w0BAQsF\nAAOCAQEAFy/Zlr7E2cJeC6pMQvV22Kfh/3RpwhMOXDjig0fKFMjPeChFrNjQzJDS\npyyFykWPEa2PNXLc9ySycbq76WWELQ5w1Obr6zbf+7EKGeMGFZ0W/Z54oEHlxyqG\nY0nx75XG+ReBIoBTiNh4032MUNdwa5uW6kB4/2xTldYZhg9Tih7KwzT5LreU4Vch\nZE9EfZAnBxJql2xKmKRAn7Cq1G+0lKrRJIiUb808ytNZlnbV1niEcUn8QAvwVrRZ\nB2Dysu5m1Q4S2GbZi/6JR+wXpbDTOYGVi0UGp7eKzkoGgmnaez+65qr0kAHiiJhA\nmy8881ijns07HxM6ES77bvybe9Bbug==',1,'sso登录公钥','2023-03-17 16:27:17','2023-03-17 16:28:13'),
	(4,'261698279016849473','1564266258685674','default','SSO_SAML_PRIVATE_KEY','-----BEGIN RSA PRIVATE KEY-----\nMIIEpAIBAAKCAQEA0h1XCDUo8jABEH3KjYb7/qRPk0oivouImado1Wk0Vk0foXan\n5WBYSvdFHysp0YntKnkGiXBH6ae5zcG5Xqte94xC5mw8Yf+vSxktL1FUQ0y8fVW1\ni20xzfhPxYs0vJfIlwozoUqtpgI/8b0yyswLXrS8XRTRLkAyqXPz/OpPjOQ9SwSx\nnh8jUFC6bAdt2rqZjllRdZXPfW9c2jCi8FVroLBJuU0QXCANFItI52Oemt6VDUs3\nColfD3MBZPG1cNXTe5HN0Pz9erA2dYPXf/i1ri7xfmveXODorf+s+e9ZChE6Xigs\n1fgmifHy6UEiZn/zhBw2OR5GpegZaKKIHWShYwIDAQABAoIBAF9cUZO4DM1eXB1P\n+g4hpVz81eDTVNGGIokaIco55TcF6cUuRSUlhO0BLK3ouSIQp0MPipf2Da1OIlXw\nBmGhBYj0b6iElyMjGDvNQWSoVMX2ndWEgNC2zlPcztepRlYRzUg/qQ8bBZTCkKL2\na4b+9GoXPSZBvG5xVIzzw1iWP2SBn4YOogX5VCYdaHvZE9iR10qG38Ie9k21g3ol\njYoz0/Sd5o8SzyGDOkg60mmOSDyGa7yoHrWRcw6/l7bdMiS+craSjNX7dvalprcd\npK3NjoQ696pKfgFz3UGxl9Qi3D91u3YEj15H+/jvsI9qCho2J887ditIb+Ov57Qv\nHXmXv4ECgYEA6tOuOX3RqPTX71aR5acnTbsP+PIV+vi9fUfgjkycSLr442fKAE3d\nbSwPbMuIvykscA7WkjbapQipBmJ6LjSVgA0B5sJT6Ji+pf3JVKSDf82VrzBhwYp0\nDVrVLVq2oEczguYIxWZxbSCzzmkB+X7ZlARNzsF0kQtgmaEZ3tobzE0CgYEA5Q8+\n/UAnPt8+1IGL+yyA2C53HTi8Eu3NZ6FiDeTY9nuG98O6tl7MpQrd9E7NFii40jRn\ndrbmbKH2rhzdWuBlB6YmqfqfDh3gPFsMNV3ZN+12Lb2z1e/g9MDKtY3qgAFeYUzh\nzVDBBj6XKICRkLNRhvmmbBdqrjNjLwwNz5vJPG8CgYEAo2IQkFOoMmPYcd/Ltvtr\n7zs9y7mz7WBvhuEhriBDhJ8CWEZ4V2nVrAXB3bJnPInLL1RDmour6LNz1sthsxBH\nDEcAkauXcfgEfb1r/QZRr/Q8Nx30hhJs2HXgaKP6+N1RS7v9UksKlCDyuaR6CTtm\n6wNrl3UmQ1ylI37hXxplyOkCgYEAtzFwUR7DxHbLSy2ohGNdUIgo34gGcqUZh+37\n+9yw4MB+ex1g8IX06XI9fSygvT4oQoWC0eEJfIw0O5/+MLYSVwG9HmjAWIvwUJUI\nuI2sMhyKTZ7QeyN0KIsgE//CMknl76+LT6dsGNBqGAxrXuzrISX8waogud2DT5c2\nXHndgaUCgYB4R9MPQAUEGjSeiH9w0scRFyaKQy6+JNRX1uP/Z78KFTJwLZEikvI/\nTFD87NTq4TJ1imxmvgdSqyrPJUQOm1U93CxU+M30ndAOXckwOPsqufeP2qciLmrk\nDzrlH1ReizgVvrqTSz3gxHhT09bShSpVXfE8akiL3UA1mKkCbFMv8Q==\n-----END RSA PRIVATE KEY-----',1,'sso登录私钥','2023-03-17 16:27:17','2023-03-17 16:28:58');

/*!40000 ALTER TABLE `account_account_config` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table account_account_group
# ------------------------------------------------------------

DROP TABLE IF EXISTS `account_account_group`;

CREATE TABLE `account_account_group` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account_uid` varchar(32) NOT NULL COMMENT '子账号uid',
  `group_name` varchar(64) DEFAULT NULL COMMENT '用户组名',
  `provider_uid` varchar(64) NOT NULL COMMENT '主账号uid',
  `tenant` varchar(16) NOT NULL COMMENT '租户',
  `gmt_create` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_au_gn_pu_tenant` (`account_uid`,`group_name`,`provider_uid`,`tenant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='子账号-用户组映射表';



# Dump of table account_account_policy
# ------------------------------------------------------------

DROP TABLE IF EXISTS `account_account_policy`;

CREATE TABLE `account_account_policy` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account_uid` varchar(32) NOT NULL COMMENT '子账号uid',
  `policy_name` varchar(128) NOT NULL COMMENT '权限名',
  `provider_uid` varchar(64) NOT NULL COMMENT '主账号uid',
  `tenant` varchar(16) NOT NULL COMMENT '租户',
  `gmt_create` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_au_pu_tenant` (`account_uid`,`policy_name`,`provider_uid`,`tenant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='子账号-权限映射表';



# Dump of table account_account_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `account_account_user`;

CREATE TABLE `account_account_user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account_uid` varchar(32) NOT NULL COMMENT '子账号uid',
  `provider_uid` varchar(64) NOT NULL COMMENT '主账号uid',
  `tenant` varchar(16) NOT NULL COMMENT '租户',
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `type` varchar(8) NOT NULL COMMENT '类型',
  `gmt_create` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_au_pu_tenant_u` (`account_uid`,`provider_uid`,`user_id`,`tenant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='子账号-用户映射表';

LOCK TABLES `account_account_user` WRITE;
/*!40000 ALTER TABLE `account_account_user` DISABLE KEYS */;

INSERT INTO `account_account_user` (`id`, `account_uid`, `provider_uid`, `tenant`, `user_id`, `type`, `gmt_create`, `gmt_modified`)
VALUES
	(1,'261698279016849473','1564266258685674','default','USER_RGsPOK2x_202303071720430','admin','2023-03-17 16:34:43','2023-03-17 16:34:53');

/*!40000 ALTER TABLE `account_account_user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table account_group_policy
# ------------------------------------------------------------

DROP TABLE IF EXISTS `account_group_policy`;

CREATE TABLE `account_group_policy` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `group_name` varchar(64) NOT NULL COMMENT '子账号组',
  `policy_name` varchar(128) NOT NULL COMMENT '权限名',
  `provider_uid` varchar(64) NOT NULL COMMENT '主账号uid',
  `tenant` varchar(16) NOT NULL COMMENT '租户',
  `gmt_create` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_gn_pu_tenant` (`group_name`,`policy_name`,`provider_uid`,`tenant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='子账号组-权限映射表';



# Dump of table account_policy
# ------------------------------------------------------------

DROP TABLE IF EXISTS `account_policy`;

CREATE TABLE `account_policy` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(128) NOT NULL COMMENT '权限名',
  `type` varchar(8) NOT NULL COMMENT '权限类型',
  `provider_uid` varchar(64) NOT NULL COMMENT '主账号uid',
  `comment` varchar(1024) DEFAULT NULL COMMENT '备注',
  `document` text NOT NULL COMMENT '权限文本',
  `tenant` varchar(16) NOT NULL COMMENT '租户',
  `gmt_create` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name_pu_tenant` (`name`,`provider_uid`,`tenant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='子账号权限表';



# Dump of table account_provider
# ------------------------------------------------------------

DROP TABLE IF EXISTS `account_provider`;

CREATE TABLE `account_provider` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` varchar(64) NOT NULL COMMENT '主账号uid',
  `name` varchar(64) NOT NULL COMMENT '主账号名',
  `cloud_source` varchar(8) NOT NULL COMMENT '云来源',
  `tenant` varchar(16) NOT NULL COMMENT '租户',
  `gmt_create` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_uid_cs_tenant` (`uid`,`cloud_source`,`tenant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='主账号表';

LOCK TABLES `account_provider` WRITE;
/*!40000 ALTER TABLE `account_provider` DISABLE KEYS */;

INSERT INTO `account_provider` (`id`, `uid`, `name`, `cloud_source`, `tenant`, `gmt_create`, `gmt_modified`)
VALUES
	(1,'1564266258685674','1564266258685674','ALIBABA','default','2023-03-17 16:11:33','2023-03-17 16:11:33');

/*!40000 ALTER TABLE `account_provider` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table account_provider_config
# ------------------------------------------------------------

DROP TABLE IF EXISTS `account_provider_config`;

CREATE TABLE `account_provider_config` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `provider_uid` varchar(64) NOT NULL COMMENT '主账号uid',
  `tenant` varchar(16) NOT NULL COMMENT '租户',
  `k` varchar(32) NOT NULL COMMENT '配置key',
  `v` varchar(10240) NOT NULL COMMENT '配置value',
  `level` tinyint NOT NULL COMMENT '风险等级',
  `description` varchar(128) DEFAULT NULL COMMENT '描述信息',
  `gmt_create` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_pu_k_tenant` (`provider_uid`,`tenant`,`k`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='主账号配置表';

LOCK TABLES `account_provider_config` WRITE;
/*!40000 ALTER TABLE `account_provider_config` DISABLE KEYS */;

INSERT INTO `account_provider_config` (`id`, `provider_uid`, `tenant`, `k`, `v`, `level`, `description`, `gmt_create`, `gmt_modified`)
VALUES
	(1,'1564266258685674','default','SITE','CHINA',2,'阿里云账号所属站点','2023-03-17 16:13:41','2023-03-17 16:13:41');

/*!40000 ALTER TABLE `account_provider_config` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table account_ram_account
# ------------------------------------------------------------

DROP TABLE IF EXISTS `account_ram_account`;

CREATE TABLE `account_ram_account` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` varchar(32) NOT NULL COMMENT '子账号uid',
  `name` varchar(64) NOT NULL COMMENT '账号登录名简写',
  `login_name` varchar(128) NOT NULL COMMENT '账号登录名',
  `show_name` varchar(128) NOT NULL COMMENT '账号展示名',
  `comment` varchar(128) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '账号创建时间',
  `phone` varchar(16) DEFAULT NULL COMMENT '电话',
  `mail` varchar(32) DEFAULT NULL COMMENT '邮箱地址',
  `provider_uid` varchar(64) NOT NULL COMMENT '主账号uid',
  `tenant` varchar(16) NOT NULL COMMENT '租户',
  `gmt_create` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_ln_pu_tenant` (`login_name`,`provider_uid`,`tenant`),
  UNIQUE KEY `uk_n_pu_tenant` (`name`,`provider_uid`,`tenant`),
  UNIQUE KEY `uk_uid_pu_tenant` (`uid`,`provider_uid`,`tenant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='子账号表';

LOCK TABLES `account_ram_account` WRITE;
/*!40000 ALTER TABLE `account_ram_account` DISABLE KEYS */;

INSERT INTO `account_ram_account` (`id`, `uid`, `name`, `login_name`, `show_name`, `comment`, `create_time`, `phone`, `mail`, `provider_uid`, `tenant`, `gmt_create`, `gmt_modified`)
VALUES
	(1,'261698279016849473','test111','test111@1564266258685674.onaliyun.com','test111','','2023-03-17 00:00:00','xxx','ccc','1564266258685674','default','2023-03-17 16:17:22','2023-03-17 16:17:22');

/*!40000 ALTER TABLE `account_ram_account` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table account_ram_group
# ------------------------------------------------------------

DROP TABLE IF EXISTS `account_ram_group`;

CREATE TABLE `account_ram_group` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) NOT NULL COMMENT '用户组名',
  `show_name` varchar(128) DEFAULT NULL COMMENT '用户组展示名',
  `comment` varchar(128) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '账号创建时间',
  `provider_uid` varchar(64) NOT NULL COMMENT '主账号uid',
  `tenant` varchar(16) NOT NULL COMMENT '租户',
  `gmt_create` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name_pu_tenant` (`name`,`provider_uid`,`tenant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='子账号用户组表';



# Dump of table account_ram_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `account_ram_role`;

CREATE TABLE `account_ram_role` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) NOT NULL COMMENT '角色名',
  `arn` varchar(128) NOT NULL COMMENT 'arn',
  `provider_uid` varchar(64) NOT NULL COMMENT '主账号uid',
  `session_timeout` int unsigned NOT NULL DEFAULT '3600' COMMENT '角色登录超时时间',
  `comment` varchar(1024) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '角色创建时间',
  `tenant` varchar(16) NOT NULL COMMENT '租户',
  `gmt_create` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name_pu_tenant` (`name`,`provider_uid`,`tenant`),
  UNIQUE KEY `uk_arn_pu_tenant` (`arn`,`provider_uid`,`tenant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='子账号角色表';



# Dump of table account_role_policy
# ------------------------------------------------------------

DROP TABLE IF EXISTS `account_role_policy`;

CREATE TABLE `account_role_policy` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(64) NOT NULL COMMENT '角色名',
  `policy_name` varchar(128) NOT NULL COMMENT '权限名',
  `provider_uid` varchar(64) NOT NULL COMMENT '主账号uid',
  `tenant` varchar(16) NOT NULL COMMENT '租户',
  `gmt_create` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_rn_pu_tenant` (`role_name`,`policy_name`,`provider_uid`,`tenant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色-权限映射表';



# Dump of table tenant
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tenant`;

CREATE TABLE `tenant` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(16) NOT NULL COMMENT 'user_id',
  `name` varchar(32) NOT NULL COMMENT 'name',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='tenant';

LOCK TABLES `tenant` WRITE;
/*!40000 ALTER TABLE `tenant` DISABLE KEYS */;

INSERT INTO `tenant` (`id`, `code`, `name`, `gmt_create`, `gmt_modified`)
VALUES
	(1,'test','测试租户','2023-03-02 14:49:08','2023-03-02 14:49:08');

/*!40000 ALTER TABLE `tenant` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
