<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../includes/headerFarm.jsp" />

<style>
.container{
  background-color: lightgray;
  display: flex;
}
.container2{
  margin-left: 40px;
}
.container3{
  /* flex-direction: column; */
  background-color: aquamarine;
  margin-top: 60px;
}

h3{
  margin-bottom: 30px;
}
.btn:first-child{
  margin-right: 60px;
}
</style>

<div class="col-md-8 mx-auto mt-5">
  <div class="container">
    <img src="<c:out value='${product.prodImg}' />" width="600" alt="상품사진" />
    <div class="container2">
      <h3>상품명: <c:out value="${product.prodName}" /></h3>
      <p>상품번호: <c:out value="${product.prodID}" /></p>
      <p>판매자ID: <c:out value="${product.farmID}" /></p>
      <p>가격: <c:out value="${product.prodPrice}" />원</p>
      <p>남은수량: <c:out value="${product.prodInven}" />개</p>
      <a class="btn btn-dark mt-auto" href="<%= request.getContextPath() %>/reviewController?cmd=find&prodID=<c:out value="${product.prodID}" />">리뷰보기</a>
    </div>  
  </div>

  <div class="container3">
    <p>상품설명</p>
    <p><c:out value="${product.prodInfo}" /></p>
  </div>
</div>
<jsp:include page="../includes/footer.jsp" />
<script>
  $('.nav-link').removeClass('active'); // 모든 메뉴의 액티브클래스를 삭제
  $('#m-proListFar').addClass('active'); // 네브바에서 메뉴중 m-home에 active 클래스를 주는 스크립트
</script>
