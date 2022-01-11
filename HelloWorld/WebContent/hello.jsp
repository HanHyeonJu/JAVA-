<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
  </head>
  <body>
    <h1>JSP 페이지</h1> 

    <% 
     String text = "오늘의 날짜는 : ";
     out.println(text); 
    %>

    
    <%= new java.util.Date() %>
  </body>
</html>
