<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String user = request.getParameter("user");
	String id = request.getParameter("id");
	
	//jsp에는 이미 out객체가 사용가능 서블릿은 out객체를 만들어줘야 출력가능
	out.println("유지 파라메터 : " + user);
	out.println("아이디 파라메터 : " + id);
	%>
</body>
</html>