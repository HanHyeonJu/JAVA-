<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- jstl 디렉티브 태그 추가 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 문자열 출력 -->
<c:out value="JSTL 안녕!"></c:out>

<!-- JSTL로 빈 사용 TestBean 클래스의 변수 getinfo를 가져와서 출력함 -->
<jsp:useBean id="test" class="beans.TestBean"/>
<!-- jstl로 Bean객체 사용 -->
<p> getInfo 메소드 : <c:out value="${test.info}"/><p>

<!-- JSTL로 Parameter불러오기 -->
파라메타 : <c:out value="${param.name}"/>

<!-- br태그 등과 같이 구분해주는 태그를 넣어주지 않으면 파라메터와 이프문이 이어져서 출력 될 수 있으니 주의!  -->
<br><br>
<!-- JSTL로 IF문 만들기 -->
<!-- 따옴표 두 개가 필요할 때는 각각 다른 따옴표를 써줘야 오류가 안 남 -->
<c:if test='${param.name == "Bob"}'>
Hello Bob
</c:if>
<!-- JSTL IF문은 따로 else문이 없어서 다른 조건의 IF문이 필요하면 IF문을 한 번 더 작성해주어야 함 -->
<c:if test='${param.name != "Bob"}'>
No Bob
</c:if>
<br><br>

<!-- JSTL Switch문(JSTL Choose) -->
<!-- choose : switch, when : case, otherwise : default -->
<c:choose>
	<c:when test="${param.id == 1}">
	<b>아이디는 1이다.</b>
	</c:when>
	<c:when test="${param.id == 2}">
	<b>아이디는 2이다.</b>
	</c:when>
	<c:otherwise>
		<b>아이디는 1 또는 2가 아니다.</b>
	</c:otherwise>
</c:choose>
<br><br>

<!-- JSTL 반복문(forEach문) -->
<c:forEach var="i" begin="0" end="10" step="2" varStatus="status">
	i의 값 : <c:out value="${i}"/><br>
	
	<c:if test="${status.first}">
	첫번째 값
	</c:if>
	
	<c:if test="${status.last}">
	마지막 값
	</c:if>
	<br/>
</c:forEach>

</body>
</html>