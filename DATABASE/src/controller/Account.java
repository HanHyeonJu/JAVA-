package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {
	private Connection conn;
	
	public Account(Connection conn) {
		this.conn = conn;
	}
	
	// 로그인(이메일, 패스워드) DB에서 같은 이메일, 패스워드 확인해서 false true 리턴
	public boolean login(String email, String password) throws SQLException {
		// 이메일과 패스워드가 같은 user가 있으면 갯수를 셀 수 있도록 SQL문을 만듬
		// email과 password를 ?로 해둔 이유는 email과 password를 입력해서 값을 넣어야하기 때문
		String sql = "select count(*) as count from users where email=? and password=?";
		// 프리페어드 sql문으로 준비(뭘 준비?)
		// sql문에 있는 ?에 값을 넣을 수 있도록 만들어줌
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		// 이메일과 패스워드는 문자열이라 setString으로 사용
		pstmt.setString(1,  email); // 1번째 ? 에 email 입력
		pstmt.setString(2,  password); // 2번째 ? 에 password 입력
		
		ResultSet rs = pstmt.executeQuery(); // SQL문 실행
		
		int count = 0;
		
		if(rs.next()) { // 실행시킨 SQL문에 결과가 잇으면
			count = rs.getInt("count"); // count열(count열이 뭐지?)의 값을 return(int형)
		}
		
		rs.close(); // rs.close의 위치가 여기인 이유는?? true false 구분하고 종료해야 하는 거 아닌가?
		
		if(count == 0) return false; // count의 값이 없으면 false 있으면 true를 return
		else return true;
	}
}
