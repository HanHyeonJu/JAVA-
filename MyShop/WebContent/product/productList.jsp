<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/includes/header.jsp" />

<style>
  .card-img-top {
    width: 450;
    object-fit: cover;
  }
</style>

<h2>상품 리스트 페이지</h2>

<!-- Section-->
<section class="py-5">
  <div class="container px-4 px-lg-5 mt-5">
    <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
      <c:forEach var="products" items="${prodList}">
        <div class="col mb-5">
          <div class="card h-100">
            <!-- Product image-->
            <img class="card-img-top" src="<%= request.getContextPath() %>/assets/img/${products.prodImg}" alt="..." />
            <!-- Product details-->
            <div class="card-body p-4">
              <div class="text-center">
                <!-- Product name-->
                <h5 class="fw-bolder"><c:out value="${products.prodName}" /></h5>
                <p>판매자: <c:out value="${products.farmID}" /></p>
                <!-- Product price-->
                <p><c:out value="${products.prodPrice}" />원</p>
              </div>
            </div>
            <!-- Product actions-->
            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
              <div class="text-center"><a class="btn btn-secondary mt-auto" href="<%= request.getContextPath() %>/managerProduct?cmd=find&prodID=${products.prodID}">자세히 보기</a></div>
            </div>
          </div>
        </div>
      </c:forEach>
    </div>
  </div>
</section>

<style>
  .container {
    background-color: darkseagreen;
  }
  .container2 {
    margin-top: 40px;
  }
  /* .card {
    margin-right: 30px;
    margin-bottom: 30px;
  } */
</style>

<jsp:include page="/includes/footer.jsp" />

<script>
	$('.nav-link').removeClass('active');
	$('#m-product').addClass('active');
</script>
