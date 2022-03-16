<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../includes/headerUser.jsp" />

<style>
.container {
	background-color: lightgray;
	display: flex;
}

.container2 {
	margin-left: 40px;
}

.container3 {
	/* flex-direction: column; */
	background-color: aquamarine;
	margin-top: 60px;
}

h3 {
	margin-bottom: 30px;
}

.btn:first-child {
	margin-right: 60px;
}
</style>

<div class="col-md-8 mx-auto mt-5">
	<div class="container">
		<img class="ProdImg" src="<%= request.getContextPath() %>/assets/img/<c:out value='${product.prodImg}' />.jpg" alt="상품사진">
		<div class="container2">
			<h3>
				상품명:
				<c:out value="${product.prodName}" />
			</h3>
			<p>
				상품번호:
				<c:out value="${product.prodID}" />
			</p>
			<p id="pID" style="display: none">
				<c:out value="${product.prodID}" />
			</p>
			<p>
				판매자ID:
				<c:out value="${product.farmID}" />
			</p>
			<p id="fID" style="display: none">
				<c:out value="${product.farmID}" />
			</p> 
			<p>
				가격:
				<c:out value="${product.prodPrice}" />
				원
			</p>
			<a class="btn btn-dark mt-auto"
				href="<%= request.getContextPath() %>/reviewController2?cmd=find&prodID=<c:out value="${product.prodID}" />">리뷰보기</a>
			<%
				String userID = null;
			if (session.getAttribute("userID") != null) {
				userID = (String) session.getAttribute("userID");
			%>
			<button id="cart" type="button" class="btn btn-primary">
				장바구니</button>
			<%
				} else {
			%>
			<!-- 로그인 하지 않은 상태에서 장바구니 버튼을 클릭한 경우 -->
			<a class="btn btn-primary" onclick="if(!confirm('권한이 없습니다')) return false" href="login/loginUser.jsp" role="button"> 장바구니</a> 
			<%
				}
			%>
		</div>
	</div>

	<div class="container3">
		<p>상품설명</p>
		<p>
			<c:out value="${product.prodInfo}" />
		</p>
	</div>
</div>
<jsp:include page="../includes/footer.jsp" />
<script>
	$('.nav-link').removeClass('active'); // 모든 메뉴의 액티브클래스를 삭제
	$('#m-proListFar').addClass('active'); // 네브바에서 메뉴중 m-home에 active 클래스를 주는 스크립트

	$('#cart').click(function() {
		const id = document.querySelector('#pID').textContent;
		const id2 = document.querySelector('#fID').textContent; 
		const url = "cart?id=" + id + "&farmID=" + id2;
		$.get(url, function(res) {
			console.log(res);
			alert("장바구니에 상품이 추가되었습니다");
		});
	})
</script>


