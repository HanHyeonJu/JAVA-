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
	<%
		// 로그인 한 경우에 세션에 저장된 유저아이디를 가지고 옴
	String farmID = null;
	if (session.getAttribute("farmID") != null) {
		farmID = (String) session.getAttribute("farmID");
	}
	if( farmID != null){
		out.println("<script>");
		out.println("alert('이미 로그인이 되었습니다.')");
		out.println("location.href = '../main.jsp'");
		out.println("</script>");
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
				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="main.jsp">메인</a></li>
				<li class="nav-item"><a class="nav-link" href="#">농산품</a></li>
				<li class="nav-item"><a class="nav-link" href="#">리뷰</a></li>
			</ul>
			<form class="d-flex mb-2 mb-auto">
				<input class="form-control" type="search" placeholder="Search"
					aria-label="Search">
				<button class="btn btn-secondary me-2" type="submit">Search</button>
			</form>
			<%
				if (farmID == null) {
			%>
			<ul class="navbar-nav mb-2 mb-lg-0">
				<li class="nav-item"><a class="btn btn-primary me-2" href="#"
					role="button">로그인</a></li>
				<li class="nav-item"><a class="btn btn-success me-2" href="#"
					role="button">회원가입</a></li>
			</ul>
			<%
				} else {
			%>
			<ul class="navbar-nav mb-2 mb-lg-0">
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#"
					id="navbarDropdown" role="button" data-bs-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false"> 마이 페이지</a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
						<li><a class="dropdown-item" href="<%=request.getContextPath()%>/logout.jsp">로그아웃</a></li>
						<li><a class="dropdown-item" href="#">농민정보수정</a></li>
					</ul></li>
			</ul>
			<%
				}
			%>
		</div>
	</nav>

	<%
		String message = (String) request.getAttribute("message");

	if (message == "e1") {
		out.println("<script>");
		out.println("alert('존재하는 아이디입니다')");
		out.println("</script>");
	} else if (message == "p") {
		out.println("<script>");
		out.println("alert('비밀번호가 다르게 입력되었습니다')");
		out.println("</script>");
	} else if (message == "r-1") {
		out.println("<script>");
		out.println("alert('회원가입에 실패했습니다')");
		out.println("</script>");
	}
	%>

	<div class="container">
		<div class="row">
			<div class="col-lg-5 col-md-7 mx-auto">
				<div class="bg-light p-5 mt-5">
					<form action="<%=request.getContextPath()%>/JoinController2"
						method="post">
						<h3 class="text-center mb-3">회원가입</h3>
						<input type="text" class="form-control mb-3" name="farmID"
							placeholder="아이디" maxlength="20" required> <input
							type="password" class="form-control mb-3" name="farmPassword"
							placeholder="패스워드" maxlength="20" required> <input
							type="password" class="form-control mb-3" name="farmPassword2"
							placeholder="패스워드 확인" maxlength="20" required> <input
							type="text" class="form-control mb-3" name="farmName"
							placeholder="이름" maxlength="20" required> <input
							type="text" class="form-control mb-3" name="farmAdd"
							placeholder="주소" maxlength="50" required> <input
							type="text" class="form-control mb-3 mt-3" name="farmTel"
							placeholder="전화번호" maxlength="20" required> <input
							type="submit" class="btn btn-dark form-control mb-3" value="가입하기"
							onclick="test()">
					</form>
				</div>
			</div>
		</div>
	</div>

	<script src="<%=request.getContextPath()%>/js/bootstrap.bundle.min.js"></script>

	<script src="<%=request.getContextPath()%>/js/bootstrap.bundle.min.js"></script>
</body>
</html>