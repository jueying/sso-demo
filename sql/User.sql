-- 创建数据库
CREATE DATABASE IF NOT EXISTS ssodemo;
use ssodemo;

-- 创建用户表
CREATE TABLE user(
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `username` varchar(120) NOT NULL COMMENT '用户名',
    `password` varchar(120) NOT NULL COMMENT '用户密码',
    PRIMARY KEY (id),
    KEY (username)
)   ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='用户表'

insert into user(username, password) values('richard', '123456');
insert into user(username, password) values('scott', '123456');