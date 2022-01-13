<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- form에 입력한 내용을 셀프 검사해서 합격하면 Controller로 데이터 보냄 -->

<!-- 아래 폼에 입력한 내용으로 유저1 객체를 생성 -->
<jsp:useBean id="user1" class = "beans.User" scope = "session"/>
<jsp:setProperty property = "*" name = "user1"/>

<!-- 프로그램을 시작하면 바로 검사를 한 번 함 -->
<!-- 입력한 값을 아무것도 전송하지 않았는데 검사하지 않게 만들기 위해 action을 사용해서 formsubmit상태가 아니면 검사하지 않게 함  -->
<%
	String action = request.getParameter("action");
	
	if(action != null && action.equals("formsubmit")){
		/* 검사해서 합격하면 컨트롤러로 포워드 함 */
	if(user1.validate()){
		request.getRequestDispatcher("/Controller").forward(request, response);
		}
    }
%>

<h2><%=user1.getMessage() %></h2>

<!-- form에서 같은 페이지로 이동하게 함 -->
<form action = "/Forms/selfvalidatingform.jsp" method = "post">

<input type = "hidden" name = "action" value = "formsubmit">
<input type = "text" name = "email" placeholder = "email" value = "<%=user1.getEmail()%>"><br>
<input type = "text" name = "password" placeholder = "password" value = "<%=user1.getPassword()%>"><br>
<input type = "submit" value = "전송">

</form>
</body>
</html>