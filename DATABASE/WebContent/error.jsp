<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#message {
	position: relative;
	top: 100px;
	width: 300px;
	border: 1px solid gray;
	padding: 20px;
	background-color: #CCFFCC;
	text-align: center;
	font-weight: bold;
}
</style>
</head>
<body>
<div id="message">
<p> <%= request.getAttribute("message") %></p>
</div>
</body>
</html>