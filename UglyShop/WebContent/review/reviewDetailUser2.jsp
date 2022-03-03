<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../includes/headerUser.jsp" />
<style>
  .review {
    background-color: lightblue;
  }
  .reply {
    background-color: bisque;
  }
  .replyEdit {
    background-color: rgb(154, 205, 255);
  }
  .content {
    margin-top: 30px;
  }
  #replySubmit {
    margin-top: 20px;
  }
</style>
<h2>review detail 페이지 고객ver</h2>
<br /><br />


<!-- 복붙시작 -->

<%
String prodID = request.getParameter("prodID");
if(prodID != null){
%>
<div class="container-fluid">
  <div class="font-weight-bold mt-3 shadow p-3 mb-4 bg-light rounded">리뷰 페이지</div>		
	<c:forEach var="review" items="${reviews}"> 	
	<div style="background-color: coral">
  		<p>리뷰글번호: <c:out value="${review.reviewID}" /></p>
 		<p>작성자: <c:out value="${review.userID}" /></p>
 		<p>작성일자: <c:out value="${review.reviewDate}" /></p>
 		<p>상품번호: <c:out value="${review.prodID}" /></p>
 		<p>제목: <c:out value="${review.reviewTitle}" /></p>
		<p><c:out value="${review.reviewContent}" /></p>
 		<a href="<%=request.getContextPath()%>/reviewController2?cmd=view&id=<c:out value='${review.reviewID}'/>" class="btn btn-success btn-action mt-3">덧글보기</a>
 	</div>
 	<br></br>	
 	</c:forEach>
 	</div>

<%} %>

<!-- 복붙끝 -->
<jsp:include page="../includes/footer.jsp" />
<script>
  $('.nav-link').removeClass('active'); // 모든 메뉴의 액티브클래스를 삭제
  $('#m-reviewFar').addClass('active'); // 네브바에서 메뉴중 m-home에 active 클래스를 주는 스크립트
</script>
