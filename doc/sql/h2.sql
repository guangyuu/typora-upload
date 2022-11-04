CREATE TABLE tb_sys_user
(
    `id`           BIGINT PRIMARY KEY auto_increment,
    `create_time`  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `del_flag`     TINYINT ( 1 ) UNSIGNED NOT NULL DEFAULT '0',
    `username`     VARCHAR(100) NOT NULL,
    `access_token` VARCHAR(255) NOT NULL
);

CREATE TABLE `tb_sys_setting`
(
    `id`          BIGINT ( 20 ) PRIMARY KEY AUTO_INCREMENT,
    `create_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `del_flag`    TINYINT ( 1 ) UNSIGNED NOT NULL DEFAULT '0',
    `name`        VARCHAR(50) NOT NULL COMMENT '名称',
    `value`       VARCHAR(50) NOT NULL COMMENT '值',
    `desc`        VARCHAR(50)          DEFAULT NULL COMMENT '描述'
);
INSERT INTO tb_sys_setting(`name`, `value`, `desc`) values ('jwt.secret.key','c^3B1!4Y#8C$Dxy#','jwt签名密钥');
INSERT INTO tb_sys_setting(`name`, `value`, `desc`) values ('jwt.expire','360000','token过期时间');