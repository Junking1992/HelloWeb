CREATE TABLE user_info (
	user_name VARCHAR(100) NOT NULL,
	user_pass VARCHAR(100) NOT NULL,
	user_id INT NOT NULL,
	real_name VARCHAR(100) NOT NULL,
	last_logintime DATE,
	PRIMARY KEY (user_name)
);

insert into user_info values ('wangjun','1234',0,'王俊','20160202');

select username, password 'aa' from user_info;

drop database demo;

drop table user_info;

show tables;

show databases;

CREATE DATABASE demo;