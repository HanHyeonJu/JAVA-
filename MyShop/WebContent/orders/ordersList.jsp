<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/includes/header.jsp" />

<div class="container-fluid">
  <div class="font-weight-bold mt-3 shadow p-3 mb-4 bg-light rounded">주문 내역 관리</div>

  <table class="table table-hover">
    <thead>
      <tr>
        <th class="th-1">주문</th>
        <th>고객ID</th>
        <th>고객이름</th>
        <th class="th-2">고객 주소</th>
        <th class="th-2">고객 연락처</th>
        <th>총결제금액</th>
        <th>농민ID</th>
        <th class="th-2">농민 연락처</th>
        <th class="th-1">발주</th>
        <th class="th-2">송장 번호</th>
        <th>배송현황</th>
        <th class="th-1">수정</th>
        <th class="th-1">삭제</th>
      </tr>
    </thead>
    <tbody>
      <!-- controller 에서 저장한 ordersList(변수 : List<OrderList> ordersList)가 여기로 넘어옴 -->
      <c:forEach var="order" items="${ordersList}">
        <tr>
          <td class="th-1"><c:out value="${order.orderID}" /></td>
          <td><c:out value="${order.userID}" /></td>
          <td><c:out value="${order.userName}" /></td>
          <td class="th-2"><c:out value="${order.userAdd}" /></td>
          <td class="th-2"><c:out value="${order.userTel}" /></td>
          <td><c:out value="${order.totalPrice}" /></td>
          <td><c:out value="${order.farmID}" /></td>
          <td class="th-2"><c:out value="${order.farmTel}" /></td>
          <td class="farmCheck th-1"><c:out value="${order.farmCheck}" /></td>
          <td class="th-2"><c:out value="${order.trackNum}" /></td>
          <td><c:out value="${order.is_status}" /></td>
          <td class="th-1">
         	<a href="<%=request.getContextPath()%>/managerOrderlist?cmd=showEdit&orderID=<c:out value='${order.orderID}'/>" class="btn btn-info btn-sm">수정</a>
          </td>
          <td class="th-1">
          	 <a href="<%=request.getContextPath()%>/managerOrderlist?cmd=delete&orderID=<c:out value='${order.orderID}'/>"onclick="if(!confirm('삭제하겠습니까?')) return false" class="btn btn-danger btn-sm">삭제</a>
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</div>

<jsp:include page="/includes/footer.jsp" />

<script src="https://cdn.datatables.net/v/bs4/dt-1.10.24/datatables.min.js"></script>

<script>
  $('.nav-link').removeClass('active');
  $('#m-order').addClass('active');
  var path = '<%= request.getContextPath() %>';
</script>

<script src="assets/js/list.js"></script>
