<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/includes/header.jsp" />

<h2>review 리스트 페이지 농민ver</h2>
<div class="row mt-5">
  <div class="col-md-8 mx-auto">
    <table class="table table-striped table-hover">
      <thead>
        <tr>
          <th>글번호</th>
          <th>제목</th>
          <th>작성자id</th>
          <th>작성일자</th>
          <th>상품번호</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="reviews" items="${reviews}">
          <tr>
            <td><c:out value="${reviews.reviewID}" /></td>
            <td>
              <a href="<%= request.getContextPath() %>/reviewController?cmd=view&id=<c:out value='${reviews.reviewID}'/>"><c:out value="${reviews.reviewTitle}" /></a>
            </td>
            <td><c:out value="${reviews.userID}" /></td>
            <td><c:out value="${reviews.reviewDate}" /></td>
            <td><c:out value="${reviews.prodID}" /></td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>
</div>

<jsp:include page="/includes/footer.jsp" />

<script>
	$('.nav-link').removeClass('active');
	$('#m-review').addClass('active');
</script>
