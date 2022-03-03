<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/includes/header.jsp" />

<div class="container-fluid">
	<div class="font-weight-bold mt-3 shadow p-3 mb-4 bg-light rounded">고객 및 농민 관리</div>

	<div class="container">
		<div class="dropdown">
			<button class="btn btn-primaty dropdown-toggle" data-toggle="dropdown">
			고객/농민
			</button>
			<div class="dropdown-menu">
				<a class="dropdown-item" href="<%=request.getContextPath()%>/member?cmd=userList">고객</a>
				<a class="dropdown-item" href="<%=request.getContextPath()%>/member?cmd=farmerList">농민</a>
			</div>
		</div>
	</div>

	<%
		String cmd = request.getParameter("cmd");
	%>

	<%
		if (cmd == null) {
	%>
	<div class="container">
		<div class="jumbotron mt-5">
			<h1 class="display-4">DropDown에서</h1>
			<br>
			<h1 class="display-4">고객 혹은 농민을 선택해주세요</h1>
		</div>
	</div>
	<%
		} else if (cmd.equals("userList")) {
	%>
	<div class="container">
	<table class="table table-hover">
		<thead>
			<tr>
				<th>고객 ID</th>
				<th>고객 이름</th>
				<th>고객 주소</th>
				<th>고객 전화번호</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${userList}">
				<tr>
					<td><c:out value="${user.userID}" /></td>
					<td><c:out value="${user.userName}" /></td>
					<td><c:out value="${user.userAdd}" /></td>
					<td><c:out value="${user.userTel}" /></td>
					<td class="th-1"><a
						href="<%=request.getContextPath()%>/member?cmd=deleteUser&userID=<c:out value='${user.userID}'/>"
						onclick="if(!confirm('삭제하겠습니까?')) return false"
						class="btn btn-danger btn-sm">삭제</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	<%
		} else if (cmd.equals("farmerList")) {
	%>
	<div class="container">
	<table class="table table-hover">
		<thead>
			<tr>
				<th>농민 ID</th>
				<th>농민 이름</th>
				<th>농민 주소</th>
				<th>농민 전화번호</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="farmer" items="${farmerList}">
				<tr>
					<td><c:out value="${farmer.farmID}" /></td>
					<td><c:out value="${farmer.farmName}" /></td>
					<td><c:out value="${farmer.farmAdd}" /></td>
					<td><c:out value="${farmer.farmTel}" /></td>
					<td class="th-1"><a
						href="<%=request.getContextPath()%>/member?cmd=deleteFarmer&farmID=<c:out value='${farmer.farmID}'/>"
						onclick="if(!confirm('삭제하겠습니까?')) return false"
						class="btn btn-danger btn-sm">삭제</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	<%
		}
	%>
</div>

<jsp:include page="/includes/footer.jsp" />

<script
	src="https://cdn.datatables.net/v/bs4/dt-1.10.24/datatables.min.js"></script>

<script>
  $('.nav-link').removeClass('active');
  $('#m-member').addClass('active');
  var path = '<%=request.getContextPath()%> ';
</script>

<script src="assets/js/list.js"></script>