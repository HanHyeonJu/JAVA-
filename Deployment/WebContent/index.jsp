<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.Date" %> <!-- Date를 가져오기 위해 java.util을 import해줌 -->
<%@ page import = "gui.TextOutput" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>JSP 기본 페이지</h1>
	<%= new Date() %>
	<br>
	<!-- gui패키지에 있는 TextOutput클래스를 가져옴 -->
	<%= new TextOutput().getInfo() %>
</body>
</html>