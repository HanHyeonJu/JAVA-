<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/headerFarm3.jsp" />
    
    <div class="container">
      <div class="row">
        <div class="col-lg-5 col-md-7 mx-auto">
          <div class="bg-light p-5 mt-5">
            <form action="<%=request.getContextPath()%>/farmerUpdate?action=doupdate" method="post">
              <h3 class="text-center mb-3"> 농민정보수정</h3>
              <input type="text" class="form-control mb-3" name="farmID" placeholder="아이디"  value="${farmID}" maxlength="20" disabled />
              <input type="password" class="form-control mb-3" name="farmPassword" placeholder="패스워드"  maxlength="20" required />
              <input type="text" class="form-control mb-3" name="farmName" placeholder="이름"  maxlength="20" required />
              <input type="text" class="form-control mb-3" name="farmAdd" placeholder="주소"  maxlength="50" required />
              <input type="text" class="form-control mb-3 mt-3" name="farmTel" placeholder="전화번호" maxlength="20" required />
              <input type="submit" class="btn btn-dark form-control mb-3" value="수정하기" />
            </form>
          </div>
        </div>
      </div>
    </div>

    <script src="<%=request.getContextPath()%>/js/bootstrap.bundle.min.js"></script>
</body>
</html>