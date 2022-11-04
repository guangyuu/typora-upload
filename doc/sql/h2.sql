CREATE TABLE tb_sys_user
(
    `id`           BIGINT PRIMARY KEY auto_increment,
    `create_time`  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `del_flag`     TINYINT ( 1 ) UNSIGNED NOT NULL DEFAULT '0',
    `username`     VARCHAR(100) NOT NULL,
    `access_token` VARCHAR(255) NOT NULL
)