<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/headerFarm3.jsp" />
<%
	String message = (String)request.getAttribute("message"); // 메시지의 값을 받아서 값에 맞는 alert창 출력
	

	if(message == "0"){
		out.println("<script>");	
		out.println("alert('비밀번호가 틀렸습니다')");		
		out.println("</script>");
	}
		
%>

<div class="container">
      <div class="row mt-5">
        <div class="col-md-6 mx-auto">
          <form action="<%=request.getContextPath()%>/UpdateFarm?action=docheck" method="post">
          	<div class="form-group">
            <label for="username">아이디 :</label>
            <input type="text" class="form-control mb-3" name="farmID" placeholder="아이디" value="${farmID}" maxlength="20" required>
            </div>
            <div class="form-group">
            <label for="username">비밀번호 :</label>
            <input type="password" class="form-control mb-3" name="farmPassword" placeholder="비밀번호" maxlength="20" required>
            </div>
            <div class="form-group mt-3">
              <button type="submit" class="btn btn-outline-danger">수정하기</button>
            </div>
          </form>
        </div>
      </div>
    </div>

<script src="<%=request.getContextPath()%>/js/bootstrap.bundle.min.js"></script>
</body>
</html>