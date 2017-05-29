CREATE TABLE `user_permission` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `account` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '账号',
  `password` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '密码',
  `ctime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `utime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;