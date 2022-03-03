<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/includes/header.jsp" />

<div class="container-fluid">
	<div class="font-weight-bold mt-3 shadow p-3 mb-4 bg-light rounded">주문 내역 수정</div>
		
		<form action="<%=request.getContextPath()%>/managerOrderlist?cmd=update" method="post">
			<input type="hidden" name="orderID" value="<c:out value="${order.orderID}" />" />
			<table class="table table-hover">
				<thead>
					<tr>
						<th class="th-1">주문</th>
						<th class="th-1">cart</th>
						<th>고객ID</th>
						<th>고객이름</th>
						<th class="th-2">고객 주소</th>
						<th class="th-2">고객 연락처</th>
						<th>농민ID</th>
						<th class="th-2">농민 연락처</th>
						<th class="th-1">발주</th>
						<th class="th-2">송장 번호</th>
						<th>배송현황</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="th-1"><c:out value="${order.orderID}" /></td>
						<td class="th-1"><c:out value="${order.cartID}" /></td>
						<td><c:out value="${order.userID}" /></td>
						<td><c:out value="${order.userName}" /></td>
						<td class="th-2"><c:out value="${order.userAdd}" /></td>
						<td class="th-2"><c:out value="${order.userTel}" /></td>
						<td><c:out value="${order.farmID}" /></td>
						<td class="th-2"><c:out value="${order.farmTel}" /></td>
						<td class="farmCheckBox th-1"><input type="checkbox"
							class="form-control" name="farmCheck"
							value="<c:out value="${order.farmCheck}" />" required></td>
						<td><input type="text" class="form-control" name="trackNum"
							value="<c:out value='${order.trackNum}' />" required></td>
						<td><select class="form-control" name="is_status">
								<option value="주문중">주문중</option>
								<option value="배송준비">배송준비</option>
								<option value="배송중">배송중</option>
								<option value="배송완료">배송완료</option>
						</select></td>
					</tr>
				</tbody>
			</table>

			<table class="table table-hover">
				<thead>
					<tr>
						<th>상품ID</th>
						<th>상품이름</th>
						<th>상품재고</th>
						<th>상품가격</th>
						<th>총결제금액</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><c:out value="${order.prodID}" /></td>
						<td><c:out value="${order.prodName}" /></td>
						<td><c:out value="${order.prodQuantity}" /></td>
						<td><c:out value="${order.prodPrice}" /></td>
						<td><c:out value="${order.totalPrice}" /></td>
					</tr>
				</tbody>
			</table>

	<div class="form-group mt-3">
		<button type="submit" class="btn btn-success">수정</button>
	</div>
	</form>
</div>

<jsp:include page="/includes/footer.jsp" />

<script
	src="https://cdn.datatables.net/v/bs4/dt-1.10.24/datatables.min.js"></script>

<script>
  $('.nav-link').removeClass('active');
  $('#m-order').addClass('active');
  var path = '<%= request.getContextPath() %>';
</script>

<script src="assets/js/list.js"></script>