<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import = "dao.OrderDAO"%>   
<jsp:include page="../includes/headerUser3.jsp" />
<body>
	<div class="container">
      <div class="row">
        <div class="col-lg-5 col-md-7 mx-auto">
          <div class="bg-light p-5 mt-5">
            <form action="<%=request.getContextPath()%>/order?cmd=userSave&id=<c:out value='${order.orderID}'/>" method="post">
              <h3 class="text-center mb-3"> 배송지정보</h3>
              <input type="text" class="form-control mb-3" name="userID" placeholder="아이디"  value="${userID}" maxlength="20" readonly />
              <input type="text" class="form-control mb-3" name="userName" placeholder="받으시는 분" value=<c:out value="${order.userName}" /> maxlength="20" required />
              <input type="text" class="form-control mb-3" name="userAdd" placeholder="배송받을 주소" value='<c:out value="${order.userAdd}" />' maxlength="50" required />
              <input type="text" class="form-control mb-3 mt-3" name="userTel" placeholder="전화번호" value=<c:out value="${order.userTel}" /> maxlength="20" required />
              <input type="submit" class="btn btn-dark form-control mb-3 mt-3" value="확인" />
            </form>
          </div>
        </div>
      </div>
    </div>
    <jsp:include page="../includes/footer.jsp" />
</body>
</html>