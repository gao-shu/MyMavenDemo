USE test;

CREATE TABLE `mybatis` (
                           `id` int(20) NOT NULL AUTO_INCREMENT,
                           `name` varchar(20) DEFAULT NULL,
                           `pwd` varchar(20) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO USER ( id, name, pwd )
VALUES
( NULL, "张三", '123456' ), ( NULL, "李四", '123456' ),
( NULL, "王五", '123456' );