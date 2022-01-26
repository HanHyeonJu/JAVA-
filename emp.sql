use demo;

DROP TABLE IF EXISTS EMP; 
CREATE TABLE EMP(
	id INT NOT NULL auto_increment, # 자동으로 1씩 증가
	name VARCHAR(50),
    primary key(id)
);

select * from EMP;

drop table if EXISTS members;

CREATE TABLE `members` (
  `id` int(8) unsigned NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `phone` varchar(100) default NULL,
  `email` varchar(255) default NULL,
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=1;



select * from members;

