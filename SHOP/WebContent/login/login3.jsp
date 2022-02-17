<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
<title>Insert title here</title>
</head>
<body>

	<% // 로그인 한 경우에 세션에 저장된 유저아이디를 가지고 옴
		String farmID = null;
		if(session.getAttribute("farmID") != null){
			farmID = (String)session.getAttribute("farmID");
		}
	%>

	<nav class="navbar navbar-expand-lg navbar-dark"
		style="background-color: gray">
		<a class="navbar-brand" href="<%=request.getContextPath()%>/main.jsp">홈페이지</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link" href="#">농산품</a></li>
				<li class="nav-item"><a class="nav-link" href="#">리뷰</a></li>
				<li class="nav-item"><a class="nav-link" href="#">농민·고객 관리</a></li>
				<li class="nav-item"><a class="nav-link" href="#">주문내역관리</a></li>
			</ul>
			<form class="d-flex mb-2 mb-auto">
				<input class="form-control me-2" type="search" placeholder="Search"
					aria-label="Search">
				<button class="btn btn-secondary" type="submit">Search</button>
			</form>
			<%
					if(farmID == null){
			%>
			<ul class="navbar-nav mb-2 mb-lg-0">
			<li class="nav-item"><a class="btn btn-primary" href="#" role="button">로그인</a></li>
			</ul>
			<%
					}else{
			%>
			<ul class="navbar-nav mb-2 mb-lg-0">
			<li class="nav-item"><a class="btn btn-primary" href="logout.jsp" role="button">로그아웃</a></li>
			</ul>
			<%
					}
			%>
		</div>
	</nav>
<%
	String message = (String)request.getAttribute("message"); // 메시지의 값을 받아서 값에 맞는 alert창 출력

	if(message == "0"){
		out.println("<script>");	
		out.println("alert('비밀번호가 틀렸습니다')");		
		out.println("</script>");	
	} else if(message == "-1"){
		out.println("<script>");	
		out.println("alert('존재하지 않는 아이디입니다')");		
		out.println("</script>");
	}
		
%>

<div class="container">
      <div class="row mt-5">
        <div class="col-md-6 mx-auto">
          <h2>로그인</h2>
          <form action="<%=request.getContextPath()%>/managerController?action=dologin" method="post">
          	<div class="form-group">
            <label for="username">아이디 :</label>
            <input type="text" class="form-control mb-3" name="manID" placeholder="아이디" value="${manID}" maxlength="20" required>
            </div>
            <div class="form-group">
            <label for="username">비밀번호 :</label>
            <input type="password" class="form-control mb-3" name="manPassword" placeholder="비밀번호" maxlength="20" required>
            </div>
            <div class="form-group mt-3">
              <button type="submit" class="btn btn-outline-danger">로그인</button>
            </div>
          </form>
        </div>
      </div>
    </div>
<script src="<%=request.getContextPath()%>/js/bootstrap.bundle.min.js"></script>
</body>
</html>