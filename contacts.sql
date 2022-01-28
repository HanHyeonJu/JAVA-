use demo;

drop table if EXISTS contacts;

create table contacts(
id int UNSIGNED not null AUTO_INCREMENT,
name varchar(100) not null,
email varchar(100) not null,
phone varchar(15) not null,
primary key(id)
);

select * from contacts;
