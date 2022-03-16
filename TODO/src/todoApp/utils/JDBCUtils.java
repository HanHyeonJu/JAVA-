package todoApp.utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

// DB 연결을 도와주는 클래스
public class JDBCUtils {
    // demo 데이터베이스 , useSSL=false : SSL 인증을 사용하지 않음, 
    private static String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
    private static String jdbcUsername = "root";
    private static String jdbcPassword = "1234";

//    public static void main(String[] args) {
//		Connection conn = getConnection();
//	}
    
    public static Connection getConnection() throws SQLException {  	
    	Connection conn = null;
    	
    	try {
			Class.forName("com.mysql.jdbc.Driver"); //0
			conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword); //1
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 클래스 못찾음");
			return null;
		}
//    	catch (SQLException e) {
//			System.out.println("SQL 에러");
//			return null;
//		}
    	//DB연결 성공 
    	System.out.println("연결성공!");
    	return conn; //DB에 연결하여 커넥션을 받아옴
    } // getConnection
    
    
    public static void close(Connection conn, PreparedStatement pstmt) {
    	close(conn, pstmt, null);
    } // close
    
    
    public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
    	if (rs != null) {
    		try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    	if (pstmt != null) {
    		try {
    			pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    	if (conn != null) {
    		try {
    			conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    } // close
    
    
    //자바 날짜를 SQL 날짜로 변경
    public static Date getSQLDate(LocalDate date) {
    	return java.sql.Date.valueOf(date);
    }
    //SQL 날짜를 자바 날짜로 변경
    public static LocalDate getUtilDate(Date sqlDate) {
    	return sqlDate.toLocalDate();
    }
}






