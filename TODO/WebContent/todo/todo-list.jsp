<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css" />
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
    <title>Todo리스트</title>
  </head>
  <body>
    <header>
      <nav class="navbar navbar-expand-lg navbar-dark" style="background-color: coral">
        <div class="container-fluid">
          <a class="navbar-brand" href="<%=request.getContextPath()%>">TODO APP</a>
          <button
            class="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
              <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/todos?action=list">ToDo 리스트</a>
              </li>
            </ul>
            <ul class="navbar-nav mb-2">
              <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/todos?action=logout">로그아웃</a>
              </li>
            </ul>
          </div>
        </div>
      </nav>
    </header>

    <!-- 네브바 끝 -->
    <!-- 본문 -->
    <div class="container mt-5">
      <h3 class="text-center">할일 목록들</h3>
      <hr />
      <div class="container text-left">
        <a href="<%=request.getContextPath()%>/todos?action=new" class="btn btn-success">할일 추가</a>
      </div>
      <br />
      <table class="table table-bordered">
        <thead>
          <tr>
            <th>제목</th>
            <th>마감 일자</th>
            <th>현재 상태</th>
            <th>액션</th>
          </tr>
        </thead>
        <tbody>
          <!-- 할일 데이터를 테이블로 -->
          <c:forEach var="todo" items="${listTodo}">
            <tr>
              <td><c:out value="${todo.title}" /></td>
              <td><c:out value="${todo.targetDate}" /></td>
              <td class="status"><c:out value="${todo.status}" /></td>

              <td>
                <a href="<%=request.getContextPath()%>/todos?action=edit&id=<c:out value='${todo.id}'/>" class="btn btn-info btn-sm">수정</a>
                <a
                  href="<%=request.getContextPath()%>/todos?action=delete&id=<c:out value='${todo.id}'/>"
                  onclick="if(!confirm('정말로 삭제하겠습니까?')) return false"
                  class="btn btn-danger btn-sm"
                  >삭제</a
                >
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
      <div class="row mt-5">
        <div class="col-4 mx-auto">
          <canvas id="myChart"></canvas>
        </div>
      </div>
    </div>
    <!-- 본문 끝 -->
    <jsp:include page="../common/footer.jsp" />

    <script src="<%=request.getContextPath() %>/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="<%=request.getContextPath() %>/js/todo.js"></script>
  </body>
</html>
