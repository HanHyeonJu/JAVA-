<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- jsp에 자바 빈 객체 만들기 : id는 객체의 이름 scope는 범위 -->

<jsp:useBean id="user" class = "beans.User" scope = "session" />

<!-- 자바 반에 값을 입력하기 -->
<jsp:setProperty property = "email" name ="user" value = "drv98@naver.com"/>
<jsp:setProperty property = "password" name ="user" value = "letmein"/>
</body>
</html>