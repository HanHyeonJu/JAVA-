<%@page import="beans.Farmer"%>
<%@page import="javax.sql.DataSource"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
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
	if(farmID == null){
		out.println("<script>");
		out.println("alert('권한이 없습니다.')");
		out.println("location.href = '../login/login.jsp'");
		out.println("</script>");
	}
	%>

	 <nav class="navbar navbar-expand-lg navbar-dark" style="background-color: gray">
      <a class="navbar-brand" href="<%=request.getContextPath()%>/main.jsp">홈페이지</a>
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
          <li class="nav-item"><a class="nav-link active" aria-current="page" href="main.jsp">메인</a></li>
          <li class="nav-item"><a class="nav-link" href="#">농산품</a></li>
          <li class="nav-item"><a class="nav-link" href="#">리뷰</a></li>
        </ul>
        <form class="d-flex mb-2 mb-auto">
          <input class="form-control" type="search" placeholder="Search" aria-label="Search" />
          <button class="btn btn-secondary me-2" type="submit">Search</button>
        </form>
        <% if (farmID == null) { %>
        <ul class="navbar-nav mb-2 mb-lg-0">
          <li class="nav-item me-2"><a class="btn btn-primary" href="<%=request.getContextPath()%>/login/login2.jsp" role="button">로그인</a></li>
          <li class="nav-item me-2"><a class="btn btn-success" href="<%=request.getContextPath()%>/join/join2.jsp" role="button">회원가입</a></li>
        </ul>
        <% } else { %>
        <ul class="navbar-nav mb-2 mb-lg-0">
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-bs-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> 마이 페이지</a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
						<li><a class="dropdown-item" href="<%=request.getContextPath()%>/logout.jsp">로그아웃</a></li>
						<li><a class="dropdown-item" href="<%=request.getContextPath()%>/update/userPassword2.jsp">농민정보수정</a></li>
					</ul>
				</li>
			</ul>
        <% } %>
      </div>
    </nav>
    
	<%
			// JDBC 참조 변수 준비
			String url = "jdbc:mysql://localhost:3306/shop?useSSL=false";
			String user = "root", pw = "1234";

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			// 1) JDBC 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");

			// 2) DB연결(DB url, DB id, DB pw)
			con = DriverManager.getConnection(url, user, pw);

			// 3) SQL문 준비
			String sql = "select * from farmer where farmID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, farmID);
			
			// 4) 실행
			rs = pstmt.executeQuery();

			// 5) 결과를 테이블에 출력
			while (rs.next()) {
				String farmPassword = rs.getString("farmPassword");
				String farmName = rs.getString("farmName");
				String farmAdd = rs.getString("farmAdd");
				String farmTel = rs.getString("farmTel");
		%>
	
	<div class="container">
		<div class="row">
			<div class="col-lg-5 col-md-7 mx-auto">
				<div class="bg-light p-5 mt-5">
					<form method="post">
						<h6 class="text-center mb-3">${farmID }님의고객정보</h6>
						<input class="form-control mb-3" name="farmID"
							placeholder="아이디" value="${farmID}" maxlength="20" disabled /> <input
							type="password" class="form-control mb-3" name="farmPassword"
							placeholder="패스워드" value="<%=farmPassword %>" maxlength="20"
							disabled /> <input type="text" class="form-control mb-3"
							name="farmName" placeholder="이름" value="<%=farmName %>"
							maxlength="20" disabled /> <input type="text"
							class="form-control mb-3" name="farmAdd" placeholder="주소"
							value="<%=farmAdd %>" maxlength="50" disabled /> <input type="text"
							class="form-control mb-3 mt-3" name="farmTel" placeholder="전화번호"
							value="<%=farmTel %>" maxlength="20" disabled />
							<div class="form-group mt-3">
							<a class="btn btn-outline-success me-2" href="<%=request.getContextPath()%>/main2.jsp" role="button"> 확인</a>
							<a class="btn btn-outline-danger me-2" href="<%=request.getContextPath()%>/update/farmerUpdate.jsp" role="button"> 수정하기</a>
							</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<%
			}
	%>

	<%
		//JDBC 닫기
		rs.close();
		pstmt.close();
		con.close();
	%>
<script src="<%=request.getContextPath()%>/js/bootstrap.bundle.min.js"></script>
</body>
</html>