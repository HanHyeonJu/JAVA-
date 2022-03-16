<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/headerUser3.jsp" />
    
      <%
		String message = (String) request.getAttribute("message");
   	
   		if (message == "p") {
			out.println("<script>");
			out.println("alert('비밀번호가 다르게 입력되었습니다')");
			out.println("</script>");
		} 
	%>
	
    <div class="container">
      <div class="row">
        <div class="col-lg-5 col-md-7 mx-auto">
          <div class="bg-light p-5 mt-5">
            <form action="<%=request.getContextPath()%>/UpdateUser?action=doupdate" method="post">
              <h3 class="text-center mb-3"> 고객정보수정</h3>
              <input type="text" class="form-control mb-3" name="userID" placeholder="아이디"  value="${userID}" maxlength="20" readonly />
              <input type="password" class="form-control mb-3" name="userPassword" placeholder="패스워드"  maxlength="20" required />
               <input type="password" class="form-control mb-3" name="userPassword2" placeholder="패스워드 확인" maxlength="20" required />
              <input type="text" class="form-control mb-3" name="userName" placeholder="이름"  maxlength="20" required />
              <input type="text" class="form-control mb-3" name="userAdd" placeholder="주소"  maxlength="50" required />
              <input type="text" class="form-control mb-3 mt-3" name="userTel" placeholder="전화번호" maxlength="20" required />
              <input type="submit" class="btn btn-dark form-control mb-3" value="수정하기" />
            </form>
          </div>
        </div>
      </div>
    </div>

    <script src="<%=request.getContextPath()%>/js/bootstrap.bundle.min.js"></script>
</body>
</html>