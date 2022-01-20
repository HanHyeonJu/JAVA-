package todoApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import todoApp.JDBCUtils;
import todoApp.model.User;

// DAO는 DB에 연결해 데이터를 조작하는 클래스
public class UserDao {
	// 유저 입력 => DB에 유저데이터를 입력
	public int registerUser(User user) { // 결과가 true면 1 return 아니면 0 이하
		String INSERT_USER_SQL = "INSERT INTO users(firstName, lastName, userName, password)"
				+ "values(?,?,?,?);";
		
		int result = 0;
		
		try {
			Connection conn = JDBCUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(INSERT_USER_SQL);
			pstmt.setString(1, user.getFirstName());
			pstmt.setString(2, user.getLastName());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getPassword()); // pstmt 준비 완료
			
			result = pstmt.executeUpdate(); // 결과가 없는 업데이트, 삭제, 입력, 등은 쿼리 업데이트
			// 입력하는 것은 결과가 나오지는 않고 한 줄이 입력되었다고 표시만 되기 때문에 결과가 없다고 봄(= 그렇게 입력된 줄의 갯수가 리턴됨)
			
		} catch (SQLException e) {
			System.out.println("SQL 입력 에러");
		}
		
		// 정상적으로 update가 됬으면 result가 영향을 받았으니 1이 되었을거니까 1이면 true이다.
		return result;
	}
}
