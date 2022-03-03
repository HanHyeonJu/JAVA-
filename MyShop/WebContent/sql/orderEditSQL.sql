SELECT * FROM shop.order;
update `order` set farmCheck = b'1', traorderckNum = 1, is_status ='주문중' where orderid = 1;
INSERT INTO `shop`.`order` (`cartID`, `userID`, `userName`, `userAdd`, `userTel`, `prodID`, `prodName`, `prodPrice`, `prodQuantity`, `totalPrice`, `farmID`, `farmTel`, `farmCheck`, `trackNum`, `is_status`) VALUES ('2', 'user1', '고객1', '부산시', '010-0000-0000', '1', '상품1', '1000', '1', '1000', 'farm1', '010-1111-1111', b'1', '0', '주문중');
select * from `order` where orderID = 1;