<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<c:import url="header.jsp">
	<c:param name="title" value="단독이미지"></c:param>
</c:import>

<!-- jstl에 들어가는 데이터소스와 context.xml에 들어간 데이터소스는 같아야함 -->
<sql:setDataSource var="ds" dataSource="jdbc/webshop" />

<!-- 아이디별로 사진 검색  -->
<!-- results를 배열이라고 생각 -->
<sql:query var="results" dataSource="${ds}" sql="select * from images where id=?" >
	<sql:param>${param.image}</sql:param>
</sql:query>


<!-- 여러 사진이 보이는게 아니라 사진이 하나라서 열이 한 행만 있다는 뜻  -->
<!-- 배열에 값이 하나 있다는 뜻 그래서 아이디당 사진 하나만 보여질 수 있게 한다...(?) -->
<c:set var="image" scope="page" value="${results.rows[0]}"/>
<c:set var="picName" scope="page" value="${image.stem}.${image.image_extension}"/>
<c:set var="average_ranking" scope="page" value="${image.average_ranking}"/>

<!-- action이 "rate"이면 rankings와 average_ranking을 업데이트 한다. (= action이 rate일 때만 동작) -->
<!-- 기존에 있던 average_ranking변수에 값에 newRating의 값을 덮어쓰게 함 -->
<!-- 평균과 rankings 아이디 파라메터형식으로 받아서 업데이트 -->
	<!-- sql:param은 ? 순서대로 적어줌 -->
	<!-- 평균평점 -->
	<!-- rankings(+1한걸 받아서 업데이트 하는 이유는 점수를 주면 rankings가 하나 올라가기 때문 -->
	<!-- 이미지의 아이디 -->
<c:if test='${param.action == "rate"}'>
	<c:set scope="page" var="newRating"
		value="${(image.average_ranking * image.rankings + param.rating)/(image.rankings + 1)}" />

	<c:set scope="page" var="average_ranking" value="${newRating}" />

	<sql:update dataSource="${ds}" sql="update images set average_ranking=?, rankings=? where id=?" >
	<sql:param>${newRating}</sql:param>
	<sql:param>${image.rankings+1}</sql:param>
	<sql:param>${param.image}</sql:param>
	</sql:update>
</c:if>

 <div class="container">
   <div class="heading">
     <h1><c:out value="${fn:toUpperCase(fn:substring(image.stem,0,1))}${fn:toLowerCase(fn:substring(image.stem, 1, -1))}"/></h1>
     <!-- fmt를 이용해서 소수점 한자리까지 숫자가 나올 수 있게 해주도록 설정 -->
     <div class="rating">Rated: <fmt:formatNumber value="${average_ranking}" maxFractionDigits="1"/></div>
   </div>
   <div class="flex-box">
     <div class="picture">
       <div class="imageby">Image by <a href="#">${image.attribution_name}</a></div>
       <img
          src="${pageContext.request.contextPath}/pics/${picName}"
          alt="">
     </div>
     <div class="rating-radio">
     <form action='<c:url value="/gallery"/>' method="post">
     	<input type="hidden" name="action" value="rate"/>
     	<input type="hidden" name="image" value="${image.id}"/>
        <h3>점수를 선택하기</h3>
        <!-- 폼 안에 있기 때문에 라디오 타입이 서브밋 버튼을 누르면 폼의 액션방식으로 데이터가 파라메터 형식으로 넘어감 -->
        <div><input type="radio" name="rating" value="5" />5 - 최고! </div>
        <div><input type="radio" name="rating" value="4" />4 - 좋은작품! </div>
        <div><input type="radio" name="rating" value="3" />3 - 괜찮음 </div>
        <div><input type="radio" name="rating" value="2" />2 - 그럭저럭 </div>
        <div><input type="radio" name="rating" value="1" />1 - 지뢰작 </div>
        <p>
        <input type="submit" name="submit" value="OK">
        <button><a href='<c:url value="/gallery?action=home"/>'>돌아가기</a></button>
        </p>
     </form>
     </div>
   </div>
 </div>


<c:import url="footer.jsp"></c:import>
