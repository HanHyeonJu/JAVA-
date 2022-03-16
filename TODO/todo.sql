# 데모 데이터베이스 생성하고 utf8mb4(모든문자,한글및 이모지포함)
CREATE DATABASE IF NOT EXISTS demo 
DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

use demo;

DROP TABLE IF EXISTS users; # 유저즈 테이블이 있으면 삭제
CREATE TABLE users (
	id INT NOT NULL auto_increment,  # 자동으로 1씩 증가 생성됨
    firstName VARCHAR(20) DEFAULT NULL, 
    lastName VARCHAR(20) DEFAULT NULL,
    userName VARCHAR(20) DEFAULT NULL,	# 유저 이름
	password VARCHAR(20) DEFAULT NULL,  # 비밀 번호
    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS todos; #  할일 테이블이 있으면 삭제
CREATE TABLE todos (
	id INT NOT NULL auto_increment, # 자동으로 1씩 증가 생성됨
    description VARCHAR(255) DEFAULT NULL, # 할일 설명
    is_done bit(1) NOT NULL,               # 완료 됨
    target_date datetime(6) DEFAULT NULL,  # 목표 날짜
	username VARCHAR(255) DEFAULT NULL,	   # 유저 이름
    title VARCHAR(255) DEFAULT NULL,	   # 할일 이름
    PRIMARY KEY(id)
);
