<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- Step 1: HTML form 작성 -->
<form action="todo-demo.jsp">
	Add new item: <input type="text" name="theItem" />
	<input type="submit" value="입력" />
</form>
<!-- Step 1.5: 입력 받은 아이템을 테스트 출력 -->
<%-- <%= request.getParameter("theItem") %> 테스트 하고 나면 삭제 --%>
<!-- Step 2: To do 리스트에 새 아이템 추가 -->
<%
	// 세션에 저장된 todo 리스트를 items에 입력 ( ArrayList )
	List<String> items = (List<String>) session.getAttribute("myToDoList");

	// 만약에 myToDoList 가 존재하지 않는다면 새로 만들기
	if (items == null) {
		items = new ArrayList<String>();
		session.setAttribute("myToDoList", items);
	}
	
	// form 에 입력한 데이터를 리스트에 추가한다.(공백이 아닐경우만 추가할 수 있도록 조건 추가)
	String theItem = request.getParameter("theItem");
		if ((theItem != null)&&(!theItem.trim().equals(""))) {
			// 같은 데이터가 추가되지 않도록 함(trim() : 공백제거)
			if(!items.contains(theItem.trim())){
			items.add(theItem);
	        session.setAttribute("myToDoList", items);
			}
		}		
	%>
	
	<!-- Step 3: to do 리스트 출력하기 -->
	<hr>
	<b>To List Items:</b> <br/>
	
	<!-- ol : 순서대로 번호 달리는 리스트 -->
	<ol>
	<%
		/* for문을 이용하여 form에 입력된 데이터들을 출력 */
		for (String temp : items) {
			out.println("<li>" + temp + "</li>");
		}
	%>
	</ol>
</body>
</html>