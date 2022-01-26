<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%
	String email = request.getParameter("email");

	if (email.contains("@") && email.contains(".")) { // 이메일은 @와 .이 있어야 한다(참)
		try {
			String url = "jdbc:mysql://localhost:3306/demo?useSSL=false";
			Connection con = DriverManager.getConnection(url, "root", "1234");

			PreparedStatement pstmt = con.prepareStatement("select * from members where email=?");
			pstmt.setString(1, email);

			ResultSet rs = pstmt.executeQuery(); // sql 실행 후 결과 rs에 저장
			
			if (rs.next()) { // 결과가 있다 = 이미 DB에 저장된 이메일이 있다
			out.print("이미 존재하는 이메일 입니다."); 
			}else{
				out.print("사용가능한 이메일 입니다.");
			}

			con.close();
		} catch (Exception e) {

		}
	} else {
		out.print("잘봇된 이메일 형식입니다.");
	}

%>