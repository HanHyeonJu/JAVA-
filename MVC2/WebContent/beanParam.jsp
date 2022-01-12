<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="user2" class = "beans.User" scope = "page" />

<!-- 파라메터를 통해 값을 입력 -->
<!-- 파라메터로 값을 입력받을 거라서 property = "*(전체)"로 해준다 -->
<jsp:setProperty property = "*" name = "user2"/>

이메일 : <%=user2.getEmail() %>
이메일 : <%=user2.getPassword() %>
</body>
</html>