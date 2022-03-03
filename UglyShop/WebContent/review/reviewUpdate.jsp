<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import = "dao.ReviewDao"%>   
<%@ page import = "dao.UserDAO"%>   

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
<jsp:include page="../includes/headerUser3.jsp" />

	<h2>리뷰 수정 페이지 고객ver</h2>		
	<br><br><br>
	<div class="container" style="display: flex; flex-direction: column;text-align: center; width: 30rem;">
		
		<form action="<%=request.getContextPath() %>/reviewController2?cmd=update" method="post">
		
			<!-- 날짜는 수정이 아니라서 필요없음 -->
			<input type="hidden" name="userID" value="<c:out value='${review.userID}'/>" >고객Id : <c:out value="${review.userID}" ></c:out></input>
			<input type="hidden" name="prodID" value="${review.prodID}" ></input>
			<input type="hidden" name="reviewID" value="<c:out value='${review.reviewID}'/>"></input> <!-- 오류부분 1.리뷰리스트는 reviewID가 있어서 업데이트 가능 2. 리뷰 작성은 save에 reviewID가 없어서 불러오기 불가능-->
			
			<!-- 적는 칸 -->
			<table class="table table-striped" style="text-align: center; border:1px solid #dddddd; width: 30rem; ">
				<thead>
					<tr>
						<th colspan="2" style="background-color: #eeeeee; text-align: center;">리뷰 수정하기</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input type="text" class="form-control" placeholder="글 제목" name="title" maxlength="50" value="<c:out value='${review.reviewTitle}'/>"></td>
					</tr>
					<tr>
						<td><textarea class="form-control" placeholder="글 내용" name="content" maxlength="2048" style="height:350px" ><c:out value="${review.reviewContent}"/></textarea></td>
					</tr>
				</tbody>
			</table>
			
			<div  style="text-align:center; align-content: center; ">
				<button  onclick = "location.href = '<%=request.getContextPath()%>/reviewController2' " type="button" class="btn btn-primary">취소</button> <!-- 수정해야함! 리뷰 상세페이지로 이동하기  -->
				<input type="submit" class="btn btn-success" value="수정 등록">		
			</div>
			
		</form>
	</div>
	
	
	

<jsp:include page="../includes/footer.jsp" />
</body>
</html>
