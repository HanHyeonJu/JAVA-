<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>홈페이지</h1>
	
	<!-- request.getContextPath를 써주는게 context가 바뀌어도 실행할 수 있게 해주기 때문에 그냥 context를 써는 것 보다 좋음  -->
	<p><a href = "<%=request.getContextPath() %>/Controller?action=login">로그인</a>
	<p><a href = "<%=request.getContextPath() %>/Controller?action=about">어바웃</a>
</body>
</html>