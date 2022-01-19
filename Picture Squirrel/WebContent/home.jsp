<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<c:import url="header.jsp">
	<c:param name="title" value="홈페이지"></c:param>
</c:import>

<!-- jstl에 들어가는 데이터소스와 context.xml에 들어간 데이터소스는 같아야함 -->
<sql:setDataSource var="ds" dataSource="jdbc/webshop" />

<!--전체 이미지를 검색할 수 있는 sql문 사용(아이디 순서대로 나오도록 함)  -->
<!-- sql문으로 검색한 결과는 변수 results에 저장 -->
<sql:query var="results" dataSource="${ds}" sql="select * from images order by id" />

<table class="images">
<c:set var="tablewidth" value="8"/>

<!-- results에는 쿼리의 실행결과이고 이것을 results.rows로 받아 한줄씩 row로 반복 results를 꺼내기 위해 items사용 -->
<!-- iamge.stem = 이미지의 이름 + image.image_extension = 이미지의 확장자 = 이미지의 주소(?) -->
<c:forEach var="image" items="${results.rows}" varStatus="row">
	<!-- 행의 갯수와 테이블의 넓이를 나눴을 때 나머지가 0이 되면 tr생성(??) -->
	<c:if test="${row.index % tablewidth == 0}"><tr></c:if>
	<!-- 반복문으로 받은 image를 이미지의이름.확장자로 가져와서 이미지를 출력할 수 있게 함 -->
	<c:set var="picName" scope="page" value="${image.stem}.${image.image_extension}"/>
	
	<td>
	<!-- 클릭한 image의 id가 parameter로 넘어갈 수 있도록 함 -->
	<a href = "<c:url value = "/gallery?action=image&image=${image.id}"/>">
	<img src="${pageContext.request.contextPath}/pics/${picName}">
	</a>
	</td>
	<!-- 그리고 다시 나누었을 때 0이 되면 한 열을 닫고 다음 열로 넘어감  -->
	<c:if test="${row.index+1 % tablewidth == 0}"></tr></c:if>
	
</c:forEach> 
</table>

<c:import url="footer.jsp"></c:import>


