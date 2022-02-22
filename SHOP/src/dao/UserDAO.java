package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import beans.User;

public class UserDAO {
	private DataSource dataSource; // jdbc/demo 커넥션 풀 연결 객체
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// userDAO 객체를 이용할 때 connection pool인 datasource를 사용할 수 있도록 기본생성자 생성
	public UserDAO(DataSource dataSource) {
		this.dataSource = dataSource; 
	}
	
	public int login(String userID, String userPassword) {
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("select userPassword from user where userID=?");
			pstmt.setString(1, userID);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) { // 로그인 성공
					return 1;
				}
				else {
					return 0; // 결과는 나오지만 입력한 비밀번호가 틀린경우
				}
			}
			
			return -1; // 결과가 없는 경우 = 아이디가 없음
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 오류");
		}finally {
			closeAll();
		}
		
		return -2; // DB오류(DB연결 중에 오류가 생긴 경우)
	}
	
	public int existID(String userID) {
		int result = -1;  
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("select userID from user where userID=?");
			System.out.println(userID);
			
			pstmt.setString(1, userID);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				result=1; // 아이디가 존재하면 1
			}else {
				result=-1; // 아이디가 존재하지 않으면 -1
			}
			
		}catch(SQLException e) {
			System.out.println("SQL 에러  " +e.getMessage());
		}finally {
			closeAll();
		}
		return result;
	}
	
	public int join(User user) {
		int result = -1; // 회원가입 실패
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("insert into user values(?, ?, ?, ?, ?)");
			
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserAdd());
			pstmt.setString(5, user.getUserTel());
			
			
			result=pstmt.executeUpdate(); // 1이 return, 회원가입 성공
			
		}catch(SQLException e) {
			System.out.println("SQL 에러" + e.getMessage());
		}finally {
			closeAll();
		}
		
		return result;
		
		}
	
	public int KakaoSave(User user) {
		int result = -1;
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("insert into user(userID) values(?)");
			System.out.println(user.getUserID());
			
			pstmt.setString(1, user.getUserID());
		
			result=pstmt.executeUpdate(); // 1이 return, 회원가입 성공
			
			}catch(SQLException e) {
				System.out.println("SQL 에러" + e.getMessage());
			}finally {
				closeAll();
			}
			
			return result;
		}
	
	public boolean userupdate(User user) {
		
		boolean update = false;
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("update user set userPassword = ?, userName = ?, userAdd = ?, userTel = ? where userID = ?");
			
			pstmt.setString(1, user.getUserPassword());
			pstmt.setString(2, user.getUserName());
			pstmt.setString(3, user.getUserAdd());
			pstmt.setString(4, user.getUserTel());
			pstmt.setString(5, user.getUserID());
			
			update = pstmt.executeUpdate() > 0; // update가 0보다 크면 true
			
		} catch (SQLException e) {
			System.out.println("SQL 에러" + e.getMessage());
		}finally {
			closeAll();
		}
		
		return update;
		
	}
	
	private void closeAll() {
		// DB 연결 객체들을 닫는 과정은 필요함(용량문제로 인해) - 모든 메소드에 DB연결할 때마다 닫아줘야함
		try {
			// 나중에 생성한 순서부터 닫음 rs => pstmt => conn(풀로 되돌아감)
			// (!= null??) 아무 값이 없는데 닫으면 에러가 나기 때문
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			System.out.println("DB연결 닫을 때 에러발생");
		}
	}
	
}
