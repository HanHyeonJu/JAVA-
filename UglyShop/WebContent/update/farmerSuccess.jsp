<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../includes/headerFarm3.jsp" />


	
	<div class="container">
		<div class="row">
			<div class="col-lg-5 col-md-7 mx-auto">
				<div class="bg-light p-5 mt-5">
					<form method="post">
						<h6 class="text-center mb-3">${farmID }님의고객정보</h6>
						<input class="form-control mb-3" name="farmID"
							placeholder="아이디" value="${farmID}" maxlength="20" disabled /> <input
							type="password" class="form-control mb-3" name="farmPassword"
							placeholder="패스워드" value= <c:out value="${farmer.farmPassword}" /> maxlength="20"
							disabled /> <input type="text" class="form-control mb-3"
							name="farmName" placeholder="이름" value= <c:out value="${farmer.farmName}" />
							maxlength="20" disabled /> <input type="text"
							class="form-control mb-3" name="farmAdd" placeholder="주소"
							value= '<c:out value="${farmer.farmAdd}" />' maxlength="50" disabled /> <input type="text"
							class="form-control mb-3 mt-3" name="farmTel" placeholder="전화번호"
							value= <c:out value="${farmer.farmTel}" /> maxlength="20" disabled />
							<div class="form-group mt-3">
							<a class="btn btn-outline-success me-2" href="<%=request.getContextPath()%>/main/mainFarm.jsp" role="button"> 확인</a>
							<a class="btn btn-outline-danger me-2" href="<%=request.getContextPath()%>/update/farmerUpdate.jsp" role="button"> 수정하기</a>
							</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	>
<script src="<%=request.getContextPath()%>/js/bootstrap.bundle.min.js"></script>
</body>
</html>