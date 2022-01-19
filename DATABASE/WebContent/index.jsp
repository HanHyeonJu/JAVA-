<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p><a href = "<%=request.getContextPath() %>/Controller?action=login">클릭해서 로그인</a>
<p><a href = "<%=request.getContextPath() %>/Controller?action=createaccount">새 계정 만들기</a>
</body>
</html>