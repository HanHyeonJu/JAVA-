<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- ${pageContext.request.contextPath} : 서버 앞에 주소가 바뀌더라도 사용가능할 수 있도록 함(jstl방식) -->
<link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/css/style.css">
<title>${param.title}</title>
</head>
<body>
<div class="headerWrapper">
	<div class="header">
	<img src="images/logo.png"/>
	<!-- ?? -->
	<span id="title"></span>
	</div>
</div>

<div class = "content">