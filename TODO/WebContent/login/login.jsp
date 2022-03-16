<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
    <title>로그인</title>
  </head>
  <body>
    <jsp:include page="../common/header.jsp" />
    <!-- 네브바 끝 -->
    <!-- 본문 -->
    <!-- 컨테이너 > 로우 > 컬럼 -->
    <div class="container">
      <div class="row mt-5">
        <div class="col-md-6 mx-auto">
          <h2>로그인</h2>
          <div class="alert alert-success center" role="alert">
            <p>${message}</p>
          </div>
          <form action="<%=request.getContextPath()%>/login" method="post">
            <div class="form-group">
              <label for="username">유저 이름 :</label>
              <input type="text" class="form-control" name="username" value="${user}" required />
            </div>
            <div class="form-group">
              <label for="password">비밀번호 :</label>
              <input type="text" class="form-control" name="password" required />
            </div>
            <div class="form-group mt-3">
              <button type="submit" class="btn btn-outline-danger">로그인</button>
            </div>
          </form>
        </div>
      </div>
    </div>
    <!-- 본문 끝 -->
    <jsp:include page="../common/footer.jsp" />

    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
