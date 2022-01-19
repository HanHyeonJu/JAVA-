<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 원래는 getAttribute를 사용해서 request된 데이터들을 받아야 하지만 JSTL을 이용했기 때문에 getAttribute없이 변수만으로 데이터를 받을 수 있다 -->
<p><c:out value="${user1.name}"/>
<p><c:out value="${user2.name}"/>
<p><c:out value="${user3.name}"/>
</body>
</html>