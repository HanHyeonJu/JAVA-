package todoApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import todoApp.model.LoginBean;
import todoApp.utils.JDBCUtils;

//DB연결하여 로그인 체크하기 메소드 만들 클래스
public class LoginDao {
	// 리턴이 불린으로 계정이 있으면 true, 없으면 false
	public boolean validate(LoginBean loginBean) {
		boolean status = false; // 체크해서 없으면 false
		
		
		try {
			Connection conn = JDBCUtils.getConnection();
			
			String sql = "SELECT * from users where userName = ? and password = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginBean.getUsername());
			pstmt.setString(2, loginBean.getPassword()); //sql문 준비끝
			
			ResultSet rs = pstmt.executeQuery();
			
			status = rs.next(); //결과 줄이 있으면 트루 없으면 false
			
		} catch (SQLException e) {
			System.out.println("SQL 로그인 에러");
		}
		
		return status;
	}
}
