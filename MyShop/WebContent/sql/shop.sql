CREATE DATABASE IF NOT EXISTS shop
DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

use shop;

-- 고객 TBL
DROP TABLE IF EXISTS user; 
CREATE TABLE user( 
	userID  VARCHAR(20) default '' NOT NULL, 
    userPassword VARCHAR(20) default '' NOT NULL,
    userName VARCHAR(20)default '' NOT NULL,
    userAdd VARCHAR(50),
    userTel VARCHAR(20) DEFAULT '' NOT NULL,
    primary key(userID)
);

-- 농민 TBL
DROP TABLE IF EXISTS farmer; 
CREATE TABLE farmer( 
	farmID  VARCHAR(20) DEFAULT '' NOT NULL, 
    farmPassword VARCHAR(20) default '' NOT NULL,
    farmName VARCHAR(20) default '' NOT NULL,
    farmAdd VARCHAR(50),
    farmTel VARCHAR(20) default '' NOT NULL,
    primary key(farmID)
);

-- 관리자 TBL
DROP TABLE IF EXISTS manager; 
CREATE TABLE manager(
	manID  VARCHAR(20) default '' NOT NULL, 
    manPassword VARCHAR(20) default '' NOT NULL,
    primary key(manID)
);

-- 상품 TBL
DROP TABLE IF EXISTS product; 
CREATE TABLE product(
	prodID INT NOT NULL auto_increment, 
    farmID VARCHAR(20) default '' NOT NULL,
    prodName VARCHAR(20) default '' NOT NULL,
    prodPrice int,
    prodInven int,
    prodImg varchar(20),
    prodInfo varchar(50),
    primary key(prodID),
    foreign key(farmID) references farmer(farmID) on update cascade on delete cascade
);

-- 장바구니 TBL
DROP TABLE IF EXISTS cart; 
CREATE TABLE cart(
	cartID INT NOT NULL auto_increment, 
    prodID INT,
    prodPrice int,
    farmID VARCHAR(20),
	userID VARCHAR(20),
    prodQuantity INT,
    totalPrice INT,
    primary key(cartID),
	foreign key(userID) references user(userID)  on update cascade on delete cascade,
	foreign key(farmID) references farmer(farmID)  on update cascade on delete cascade,
	foreign key(prodID) references product(prodID)  on update cascade on delete cascade
);

-- 주문 TBL
DROP TABLE IF EXISTS `order`; 
CREATE TABLE `order`(
	orderID INT NOT NULL auto_increment, 
    cartID INT,
	userID VARCHAR(20),
    userName VARCHAR(20) default '' NOT NULL,
    userAdd VARCHAR(50),
    userTel VARCHAR(20) default '' NOT NULL,
    prodID INT,
    prodPrice INT,
    prodName VARCHAR(20) default '' NOT NULL,
	prodQuantity INT,
    totalPrice INT,
	farmID  VARCHAR(20) default '' NOT NULL, 
    farmTel VARCHAR(20) default '' NOT NULL,
    farmCheck BIT(1) NOT NULL,
    TrackNum int,
    is_status VARCHAR(20) default '' NOT NULL,
    primary key(orderID),
    foreign key(cartID) references cart(cartID)  on update cascade on delete no action,
	foreign key(userID) references cart(userID)  on update cascade on delete no action,
	foreign key(farmID) references cart(farmID)  on update cascade on delete no action,
	foreign key(prodID) references cart(prodID)  on update cascade on delete no action
);

-- 리뷰 TBL
DROP TABLE IF EXISTS review; 
CREATE TABLE review(
	reviewID INT NOT NULL auto_increment, 
	userID VARCHAR(20) default '' NOT NULL,
    reviewDate datetime,
    reviewTitle varchar(50),
    reviewContent varchar(2048),
    prodID INT,
    primary key(reviewID),
    foreign key(prodID) references product(prodID)  on update cascade on delete cascade
);

-- 답글게시판 TBL
DROP TABLE IF EXISTS reply; 
CREATE TABLE reply(
	replyID INT NOT NULL auto_increment, 
	farmID VARCHAR(20) default '' NOT NULL,
    replyContent varchar(2048),
    reviewID INT,
    primary key(replyID),
	foreign key(reviewID) references review(reviewID)  on update cascade on delete cascade
);


