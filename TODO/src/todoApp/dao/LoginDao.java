package todoApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import todoApp.JDBCUtils;
import todoApp.model.LoginBean;

// DB를 연결하여 로그인 체크하기 메소드를 만들 클래스
public class LoginDao {
	
	// DB에 있는 계정이면 true 아니면 false를 리턴받도록 하는 불린함수를 이용한 메소드
	public boolean validate(LoginBean loginBean) {
		boolean status = false; // DB에 체크해서 없으면 false
		
		Connection conn = JDBCUtils.getConnection(); // DB연결
		String sql = "select * from users where userName = ? and password = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginBean.getUsername());
			pstmt.setString(2, loginBean.getPassword()); // sql 준비 긑
			
			System.out.println(loginBean.getUsername());
			System.out.println(loginBean.getPassword());
			
			ResultSet rs = pstmt.executeQuery(); 
			
			status = rs.next(); // 결과가 있을 때, 없으면 false
			
			
		} catch (SQLException e) {
			System.out.println("SQL 로그인 에러");
		}
		
		return status;
	}
}
