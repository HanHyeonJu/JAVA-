<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
    <link rel="shortcut icon" href="https://cdn-icons-png.flaticon.com/512/765/765544.png" type="image/x-icon">
    <title>못난이 농산품(농민)</title>
  </head>
  <body>
    <header>
		<% 
		// 로그인 한 경우에 세션에 저장된 유저아이디를 가지고 옴
		String farmID = null;
		if(session.getAttribute("farmID") != null){
			farmID = (String)session.getAttribute("farmID");
		}
		if(farmID == null){
			out.println("<script>");
			out.println("alert('권한이 없습니다.')");
			out.println("location.href = login/loginFarm.jsp");
			out.println("</script>");
		}
	
	%>
	

	 <nav class="navbar navbar-expand-lg navbar-dark" style="background-color: gray">
      <a class="navbar-brand" href="<%=request.getContextPath()%>/main/mainFarm.jsp">홈페이지</a>
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/ProdController?cmd=list">농산품</a></li>
          <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/reviewController?cmd=list">리뷰</a></li>
        </ul>
        <form class="d-flex mb-2 mb-auto">
          <input class="form-control" type="search" placeholder="Search" aria-label="Search" />
          <button class="btn btn-secondary me-2" type="submit">Search</button>
        </form>
        <% if (farmID == null) { %>
        <ul class="navbar-nav mb-2 mb-lg-0">
          <li class="nav-item me-2"><a class="btn btn-primary" href="<%=request.getContextPath()%>/login/loginFarm.jsp" role="button">로그인</a></li>
          <li class="nav-item me-2"><a class="btn btn-success" href="<%=request.getContextPath()%>/join/joinFarm.jsp" role="button">회원가입</a></li>
        </ul>
        <% } else { %>
        <ul class="navbar-nav mb-2 mb-lg-0">
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-bs-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> 마이 페이지</a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
						<li><a class="dropdown-item" href="<%=request.getContextPath()%>/login/logout.jsp">로그아웃</a></li>
						<li><a class="dropdown-item" href="<%=request.getContextPath()%>/update/farmerPassword.jsp">농민정보수정</a></li>
					</ul>
				</li>
			</ul>
        <% } %>
      </div>
    </nav>
    </header>
