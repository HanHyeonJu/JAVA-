<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%
	String strId = request.getParameter("id");

	if (strId == null || strId.trim().equals("")) { // 입력받은 아이디가 널값이거나 공백인 경우
	out.print("아이디를 입력해주세요.");
	} else {
	int id = Integer.parseInt(strId);
	try {
		String url = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		Connection con = DriverManager.getConnection(url, "root", "1234");

		PreparedStatement pstmt = con.prepareStatement("select * from emp where id=?");
		pstmt.setInt(1, id);

		ResultSet rs = pstmt.executeQuery(); // sql 실행 후 결과 rs에 저장
		if (rs.next()) {
		out.print(rs.getInt(1) + " " + rs.getString(2)); // 1과 2는 첫번째열 두번째열 의미 (열의 이름 적어도 괜찮음(id, name))
		}else{
			out.print("테이블에 해당 id가 없습니다.");
		}

		con.close();
	} catch (Exception e) {

	}
}
%>