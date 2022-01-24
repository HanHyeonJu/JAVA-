<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      rel="stylesheet"
      href="<%=request.getContextPath() %>/css/bootstrap.min.css"
    />
    <link
      rel="stylesheet"
      href="<%=request.getContextPath() %>/css/style.css"
    />
    <title>로그인</title>
  </head>
  <body>
    <jsp:include page="../common/header.jsp" />
    <!-- 네브바 끝 -->
    <!-- 본문 -->
    <!-- 컨테이너 => 로우 => 컬럼 -->
    <div class="container">
			<!-- 5만큼 위쪽으로 마진을 줌 -->
			<div class="row mt-5">
			<!-- 로우 안에 컬럼 12개까지 분할 가능 -->
			<!-- 가운데로 표시될 수 있도록 수정함(mx-auto)-->
      <div class="col-md-6 mx-auto">
				<h2>로그인</h2>
        <div class="alert alert-success center" role="alert">
          <p>${message}</p>
        </div>
        <form action="<%=request.getContextPath() %>/login" method="post">
          <div class="form-gruop">
            <label for="username">아이디 :</label>
            <input
              type="text"
              class="form-control"
              name="username"
              value="${user}"
              required
            />
          </div>
          <div class="form-gruop">
            <label for="password">비밀번호 :</label>
            <input
              type="password"
              class="form-control"
              name="password"
              required
            />
          </div>
          <div class="form-gruop mt-3">
            <button type="submit" class="btn btn-outline-dark">로그인</button>
          </div>
				</div>
        </form>
      </div>
    </div>
    <!-- 본문 끝 -->
    <jsp:include page="../common/footer.jsp" />
    <script src="<%=request.getContextPath() %>/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
