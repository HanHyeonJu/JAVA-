<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/includes/header.jsp" />

<h2>review detail 페이지 농민ver</h2>
<br /><br />

<%
	String prodID = request.getParameter("prodID");
	if(prodID != null){
%>			
<div style="background-color: gray">
 <c:forEach var="review" items="${reviews}"> 
  <p>리뷰글번호: <c:out value="${review.reviewID}" /></p>
  <p>제목: <c:out value="${review.reviewTitle}" /></p>
  <p><c:out value="${review.reviewContent}" /></p>
  <p>작성자: <c:out value="${review.userID}" /></p>
  <p>작성일자: <c:out value="${review.reviewDate}" /></p>
  <p>상품번호: <c:out value="${review.prodID}" /></p>
 </c:forEach>
</div>
<hr />
<div style="background-color: bisque">
  <p>덧글번호: <c:out value="${reply.replyID}" /></p>
  <p>판매자: <c:out value="${reply.farmID}" /></p>
  <p><c:out value="${reply.replyContent}" /></p>
  <p>리뷰글번호: <c:out value="${reply.reviewID}" /></p>
  <a href="<%=request.getContextPath()%>/replyController?cmd=delete&replyID=<c:out value='${reply.replyID}'/>" onclick="if(!confirm('정말로 삭제하시겠습니까?')) return false" class="btn btn-success btn-action">답글삭제</a>
</div>
 <a href="<%=request.getContextPath()%>/reviewController?cmd=delete&reviewID=<c:out value='${review.reviewID}'/>" onclick="if(!confirm('정말로 삭제하시겠습니까?')) return false" class="btn btn-success btn-action mt-3">전체삭제</a>
<br></br>
<%} else { %>
<div style="background-color: gray">
  <p>리뷰글번호: <c:out value="${review.reviewID}" /></p>
  <p>제목: <c:out value="${review.reviewTitle}" /></p>
  <p><c:out value="${review.reviewContent}" /></p>
  <p>작성자: <c:out value="${review.userID}" /></p>
  <p>작성일자: <c:out value="${review.reviewDate}" /></p>
  <p>상품번호: <c:out value="${review.prodID}" /></p>
</div>
<hr />
<div style="background-color: bisque">
  <p>덧글번호: <c:out value="${reply.replyID}" /></p>
  <p>판매자: <c:out value="${reply.farmID}" /></p>
  <p><c:out value="${reply.replyContent}" /></p>
  <p>리뷰글번호: <c:out value="${reply.reviewID}" /></p>
  <a href="<%=request.getContextPath()%>/replyController?cmd=delete&replyID=<c:out value='${reply.replyID}'/>" onclick="if(!confirm('정말로 삭제하시겠습니까?')) return false" class="btn btn-success btn-action">답글삭제</a>
</div>
 <a href="<%=request.getContextPath()%>/reviewController?cmd=delete&reviewID=<c:out value='${review.reviewID}'/>" onclick="if(!confirm('정말로 삭제하시겠습니까?')) return false" class="btn btn-success btn-action mt-3">전체삭제</a>
<br></br>
<% } %>
<jsp:include page="/includes/footer.jsp" />

<script>
	$('.nav-link').removeClass('active');
	$('#m-review').addClass('active');
</script>
