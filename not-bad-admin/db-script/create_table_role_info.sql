CREATE TABLE `role_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `roleName` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色名',
  `permission` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;