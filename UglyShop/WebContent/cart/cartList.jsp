<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="beans.Cart"%>
 <jsp:include page="../includes/headerUser3.jsp" />

 <% if (session.getAttribute("cartList") != null) { HashMap<Integer, Cart>
    cartList = (HashMap<Integer, Cart>) session.getAttribute("cartList"); } %>
      <div class="container">
          <div class="font-weight-bold mt-3 shadow p-3 mb-4 bg-light rounded">
          장바구니
          </div>
        <table class="table table-striped table-hover">
          <thead>
            <tr>
              <th>상품명</th>
              <th>수량</th>
              <th>금액</th>
              <th width="5%">&nbsp;</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="carList" items="${cartList.values()}">
              <c:set var="prodPrice" value="${carList.prodPrice}" />
              <c:set var="orderQuantity" value="${carList.orderQuantity}" />
              <c:set var= "row_sum" value="${row_sum + prodPrice*orderQuantity}"/>
              <tr>
                <td><c:out value="${carList.prodName}" /></td>
                <td>
                  <c:out value="${carList.orderQuantity}" />
                <button type="button" class="down btn btn-secondary btn-sm" data-id="<c:out value='${carList.prodID}' />"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-down-short" viewBox="0 0 16 16">
                  <path fill-rule="evenodd" d="M8 4a.5.5 0 0 1 .5.5v5.793l2.146-2.147a.5.5 0 0 1 .708.708l-3 3a.5.5 0 0 1-.708 0l-3-3a.5.5 0 1 1 .708-.708L7.5 10.293V4.5A.5.5 0 0 1 8 4z"/>
                  </svg></button>
                  <button type="button" class="up btn btn-secondary btn-sm" data-id="<c:out value='${carList.prodID}' />">
                  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-up-short" viewBox="0 0 16 16">
                  <path fill-rule="evenodd" d="M8 12a.5.5 0 0 0 .5-.5V5.707l2.146 2.147a.5.5 0 0 0 .708-.708l-3-3a.5.5 0 0 0-.708 0l-3 3a.5.5 0 1 0 .708.708L7.5 5.707V11.5a.5.5 0 0 0 .5.5z"/>
                </svg>
                </button>
                </td>
                <td><c:out value="${prodPrice*orderQuantity}" /></td>
                <td>
                  <button
                    id="delete"
                    type="button"
                    class="btn btn-danger btn-sm btn-delete"
                    data-id="<c:out value='${carList.prodID}' />"
                  >
                    삭제
                  </button>
                </td>
              </tr>
            </c:forEach>
          </tbody>
           <tr>
              <td><b> 총 결제금액 : <c:out value="${row_sum}" /></b></td>
            </tr>
        </table>
        <div>
          <a href="<%=request.getContextPath()%>/payment.jsp" class="btn btn-success float-right">주문하기</a>
        </div>
      </div>

     
    	<jsp:include page="/includes/footer.jsp" />
      
      <script>
        $('.nav-link').removeClass('active');
        $('#m-cart').addClass('active');
      </script>
      
      <script src="<%=request.getContextPath()%>/assets/cartList.js"></script>
</body>
</html>