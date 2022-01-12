<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 주석 : ctrl + shift + / -->
<!-- 정적 include : 미리 컴파일해둠, 자주 바꾸지 않을 때 사용 -->
<%@ include file = "copyright.txt" %>
<br>
<!-- 동적 include : 볼때마다 새로고침, 자주 수정할 때 사용  -->
<jsp:include page="update.txt" />

<!-- 자바 변수등을 넣으려면 정적 include를 사용  -->
<%@ include file = "variable.jsp" %>
<!-- 이 name은 variable.jsp에 저장한 변수의 값 -->
<%=name %>

<!-- 실행시(runtime)에 두 개의 html중에 id가 있을경우 login.html 없을경우 fail.html(html은 정적(?))동적도 글자깨짐 -->
<% String id = request.getParameter("id"); %>

<% if(id==null) { %>
<jsp:include page="fail.html" />
<% } else { %>
<jsp:include page="login.html" />
<% } %>
</body>
</html>