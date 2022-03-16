<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<header>
  <nav
    class="navbar navbar-expand-lg navbar-dark"
    style="background-color: coral"
  >
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
        <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
          <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/login">로그인</a></li>
          <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/register/register.jsp">가입하기</a></li>
        </ul>
      </div>
    </div>
  </nav>
</header>
