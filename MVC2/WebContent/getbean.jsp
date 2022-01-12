<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- jsp에 자바 빈 객체 만들기 : id는 객체의 이름 scope는 범위(session은 같은 브라우저를 의미) -->
<jsp:useBean id="user" class = "beans.User" scope = "session" />

<!-- 자바 반에 값을 가져오기 -->
이메일 : <%= user.getEmail() %>
비밀번호 : <%= user.getPassword() %>
</body>
</html>