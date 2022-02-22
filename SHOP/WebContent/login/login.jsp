<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<% 
		// 로그인 한 경우에 세션에 저장된 유저아이디를 가지고 옴
		String userID = null;
		if(session.getAttribute("userID") != null){
			userID = (String)session.getAttribute("userID");
		}
		if( userID != null){
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
				<li class="nav-item"><a class="nav-link" href="#">농산품</a></li>
				<li class="nav-item"><a class="nav-link" href="#">리뷰</a></li>
			</ul>
			<form class="d-flex mb-2 mb-auto">
				<input class="form-control" type="search" placeholder="Search"
					aria-label="Search">
				<button class="btn btn-secondary me-2" type="submit">Search</button>
			</form>
			<%
					if(userID == null){
			%>
			<ul class="navbar-nav mb-2 mb-lg-0">
			<li class="nav-item"><a class="btn btn-primary me-2" href="#" role="button">로그인</a></li>
			<li class="nav-item"><a class="btn btn-success me-2" href="<%=request.getContextPath()%>/join/join.jsp" role="button">회원가입</a></li>
			</ul>
			<%
					}else{
			%>
			<ul class="navbar-nav mb-2 mb-lg-0">
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-bs-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> 마이 페이지</a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
						<li><a class="dropdown-item" href="<%=request.getContextPath()%>/logout.jsp">로그아웃</a></li>
						<li><a class="dropdown-item" href="#">장바구니</a></li>
						<li><a class="dropdown-item" href="#">주문조회</a></li>
						<li><a class="dropdown-item" href="<%=request.getContextPath()%>/update/userPassword.jsp">고객정보수정</a></li>
					</ul>
				</li>
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
          <form action="<%=request.getContextPath()%>/userController?action=dologin" method="post">
          	<div class="form-group">
            <label for="username">아이디 :</label>
            <input type="text" class="form-control mb-3" name="userID" placeholder="아이디" value="${userID}" maxlength="20" required>
            </div>
            <div class="form-group">
            <label for="username">비밀번호 :</label>
            <input type="password" class="form-control mb-3" name="userPassword" placeholder="비밀번호" maxlength="20" required>
            </div>
            <div class="form-group mt-3">
              <button type="submit" class="btn btn-outline-danger">로그인</button>
			  			<a class="btn btn-outline-success me-2" href="login2.jsp" role="button"> 농민 로그인</a>
				 			<a id="custom-login-btn" href="javascript:loginWithKakao()"> 
			  			<img src="//k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg" width="180" alt="카카오 로그인 버튼" /></a></div>
          </form>
        </div>
      </div>
    </div>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.bundle.min.js"></script>


<script type="text/javascript">
  Kakao.init('e4ff133a3e61bfceedb984e0e50fc6e5');
  //console.log(Kakao.inInitialized());

  function loginWithKakao() {
    Kakao.Auth.login({
      success: function(authObj) {
        console.log(authObj); // access 토큰 값
				Kakao.Auth.setAccessToken(authObj.access_token); // access 토큰값 저장

				getInfo(); // 저장된 토큰값을 사용자 정보로 가져올 함수
      },
      fail: function(err) {
        console.log(err); // 로그인에 실패할 경우(?)
      },
    })
  }

	function getInfo(){
		 Kakao.API.request({
        url: '/v2/user/me',
        success: function(res) {
          var email = res.kakao_account.email;
					
		  sendID(email);
        },
        fail: function(error) {
          alert(
            '카카오 로그인에 실패했습니다' +
              JSON.stringify(error)
          );
        }
      });
	}

	const request = new XMLHttpRequest();

	function sendID(email){
	$.ajax({
		type: 'get',
		url: '<%=request.getContextPath()%>/KakaoController?val=' + email,
		success: function(data){
			console.log(data);
			if(data == "1"){
				location.href="../main.jsp";
			} else if(data == "0"){
				location.href="../update/userUpdate.jsp";
			} else if(data == "-1"){
				location.href="login.jsp";
			}
			
		},
		error: function(data){
			console.log('실패');
		}
	});	
	}
	
</script>
</body>
</html>