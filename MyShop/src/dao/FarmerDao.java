package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import beans.Farmer;

public class FarmerDao {
	private DataSource dataSource; // jdbc/demo 커넥션 풀 연결 객체
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// userDAO 객체를 이용할 때 connection pool인 datasource를 사용할 수 있도록 기본생성자 생성
	public FarmerDao(DataSource dataSource) {
		this.dataSource = dataSource; 
	}
	
	public int login(String farmID, String farmPassword) {
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("select farmPassword from farmer where farmID=?");
			pstmt.setString(1, farmID);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(1).equals(farmPassword)) { // 로그인 성공
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
	
	public List<Farmer> findAllFarmer() throws SQLException{
		List<Farmer> farmerList = new ArrayList<Farmer>();
		
		try {
		conn = dataSource.getConnection(); // DB연결
		pstmt = conn.prepareStatement("SELECT * FROM farmer"); // sql문
		rs = pstmt.executeQuery();
		
		while (rs.next()) { // 반복문으로 orders 리스트 저장
			String farmID = rs.getString("farmID");
			String farmPassword = rs.getString("farmPassword");
			String farmName = rs.getString("farmName");
			String farmAdd = rs.getString("farmAdd");
			String farmTel = rs.getString("farmTel");
			
			farmerList.add(new Farmer(farmID, farmPassword, farmName, farmAdd, farmTel));
		}
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("농민 리스트 전체 출력 SQL에러");
		}
		
		System.out.println("전체 농민 리스트 출력 성공");
		return farmerList;
	}
	
	public boolean deleteFarmer(String farmID) {
		boolean rowDeleted = false;

		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("delete from farmer where farmID = ?");
			pstmt.setString(1, farmID);

			rowDeleted = pstmt.executeUpdate() > 0; // 실제 쿼리를 실행 -> 삭제되면 true

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}

		System.out.println("농민 삭제 성공");
		return rowDeleted;
	}
	
}
