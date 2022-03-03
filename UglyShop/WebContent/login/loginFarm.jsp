<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<jsp:include page="../includes/headerFarm2.jsp" />
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
          <h2>농민 로그인</h2>
          <form action="<%=request.getContextPath()%>/farmerLogin?action=dologin" method="post">
          	<div class="form-group">
            <label for="username">아이디 :</label>
            <input type="text" class="form-control mb-3" name="farmID" placeholder="아이디" value="${farmID}" maxlength="20" required>
            </div>
            <div class="form-group">
            <label for="username">비밀번호 :</label>
            <input type="password" class="form-control mb-3" name="farmPassword" placeholder="비밀번호" maxlength="20" required>
            </div>
            <div class="form-group mt-3">
              <button type="submit" class="btn btn-outline-danger">로그인</button>
              <a class="btn btn-outline-primary me-2" href="loginUser.jsp" role="button"> 고객 로그인</a>
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
		url: '<%=request.getContextPath()%>/KakaoController2?val=' + email,
		success: function(data){
			console.log(data);
			if(data == "1"){
				location.href="../main/mainFarm.jsp";
			} else if(data == "0"){
				location.href="../update/farmerUpdate.jsp";
			} else if(data == "-1"){
				location.href="loginFarm.jsp";
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