<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- <%
	String manID =  (String)session.getAttribute("manID");
	if(manID == null){
		response.sendRedirect("../login/managerLogin.jsp");
	}  
%> --%>

	
<jsp:include page="/includes/header.jsp" />

<div class="container">
	<div class="jumbotron mt-5">
		<h1 class="display-4">못난이 농산품</h1>
		<p class="lead mt-3">
			JSP, Servlet, JDBC, MySQL, DataTables, Modal <small
				class="text-muted">(plugin jQuery)</small>.
		</p>
		<hr class="my-4">
		<p>못난이 농산품 판매 사이트입니다.</p>
	</div>
</div>

<jsp:include page="/includes/footer.jsp" />

<script>
	$('.nav-link').removeClass('active');
	$('#m-home').addClass('active');
</script>
