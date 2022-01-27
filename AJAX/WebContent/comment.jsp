<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%
	String comment = request.getParameter("comment");
String email = request.getParameter("email");

if (comment.trim().equals("") || email.trim().equals("")) { // 댓글과 이메일 둘 다 공백을 제외하고 아무것도 없으면 안 된다
	out.print("<p>댓글 내용과 이메일을 적어 주세요</p>");
} else {
	try { // DB에 댓글과 이메일을 저장
		String url = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		Connection con = DriverManager.getConnection(url, "root", "1234");

		PreparedStatement pstmt = con.prepareStatement("insert into comment(comment, email) values(?, ?)");
		pstmt.setString(1, comment);
		pstmt.setString(2, email);

		int i = pstmt.executeUpdate(); // 결과는 숫자로 받음(입력된 줄의 갯수) = 1이 정상
		
		// 저장한 순서대로 comment가 보여지게 함
		pstmt = con.prepareStatement("select * from comment ORDER BY id DESC"); // 내림차순 정렬
		ResultSet rs = pstmt.executeQuery(); // sql 실행 후 결과를 rs에 저장

		out.print("<hr>");
		out.print("<h2>Cooments : </h2>");
		while (rs.next()) { // 저장된 결과들을 하나씩 보여줄 수 있도록 while문 사용
			out.print("<div class='box'>");
			out.print("<p>" + rs.getString("comment") + "</p>");
			out.print("<p><strong>글쓴이 : " + rs.getString("email") + "</strong></p>"); // 2? 3?
			out.print("</div>");
		}

		con.close();
	} catch (Exception e) {
		out.print(e);
	}
}
%>