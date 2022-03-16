package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import beans.Farmer;
import beans.Order;
import beans.Product;
import beans.User;

public class OrderDao {
	private DataSource dataSource; // jdbc/shop 커넥션 풀 연결 객체
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public OrderDao(DataSource dataSource) {
		this.dataSource = dataSource; // 객체 생성시 커넥션 풀 daraSource를 입력
	}


	public List<Order> findAll() {
		List<Order> ordersList = new ArrayList<Order>(); // 빈 리스트 생성

		try {
			conn = dataSource.getConnection(); // DB연결
			pstmt = conn.prepareStatement("SELECT * FROM `order`"); // sql문
			rs = pstmt.executeQuery(); // 쿼리문 실행

			while (rs.next()) { // 반복문으로 orders 리스트 저장			
				Order order = new Order();
				
				order.setOrderID(rs.getInt("orderID"));
				order.setCartID(rs.getInt("cartID"));
				order.setUserID(rs.getString("userID"));
				order.setUserAdd(rs.getString("userAdd"));
				order.setUserTel(rs.getString("userTel"));
				order.setProdID(rs.getInt("prodID"));
				order.setProdName(rs.getString("prodName"));
				order.setProdPrice(rs.getInt("prodPrice"));
				order.setOrderQuantity(rs.getInt("orderQuantity"));
				order.setProdQuantity(rs.getInt("prodQuantity"));
				order.setTotalPrice(rs.getInt("totalPrice"));
				order.setFarmID(rs.getString("farmID"));
				order.setFarmTel(rs.getString("farmTel"));
				order.setFarmCheck(rs.getBoolean("farmCheck"));
				order.setTrackNum(rs.getInt("trackNum"));
				order.setIs_status( rs.getString("is_status"));
				
				ordersList.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("주문 내역 전체 출력 SQL에러");
		} finally { // 에러에 상관없이 무조건 실행
			closeAll(); // 여러 사람이 사용할 때를 대비하여 DB연결 객체들을 닫는 과정
		}

		System.out.println("전체 주문 내역 검색 완료");
		return ordersList;
	}
	
	
	// userID로 검색하기. 리퀘에 없는 나머지 정보들을.
	public User findByUserId(String userId) {
		User user = null;
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("select * from user where userId=?");
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				user = new User();
				user.setUserID(rs.getString("userID"));
				user.setUserPassword(rs.getString("userPassword"));
				user.setUserName(rs.getString("userName"));
				user.setUserAdd(rs.getString("userAdd"));
				user.setUserTel(rs.getString("userTel"));
			}
			
			}catch (SQLException e) {
				e.printStackTrace();
				System.out.println("userId select SQL 에러");
			} finally { //에러에 상관없이 무조건 실행
				//DB연결 객체들을 닫는 과정이 필요하다.
				closeAll();	
			}
			
			return user;
		
			
		}
	


	// farmID로 검색하기. 리퀘에 없는 나머지 정보들을.
	public Farmer findByFarmId(String farmId) {
		Farmer farmer = null;
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("select * from farmer where farmId=?");
			pstmt.setString(1, farmId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				farmer = new Farmer();
				farmer.setFarmID(rs.getString("farmID"));
				farmer.setFarmPassword(rs.getString("farmPassword"));
				farmer.setFarmName(rs.getString("farmName"));
				farmer.setFarmAdd(rs.getString("farmAdd"));
				farmer.setFarmTel(rs.getString("farmTel"));
			}
			
			}catch (SQLException e) {
				e.printStackTrace();
				System.out.println("farmerId select SQL 에러");
			} finally { //에러에 상관없이 무조건 실행
				//DB연결 객체들을 닫는 과정이 필요하다.
				closeAll();	
			}
			
			return farmer;
		
			
		}
	
	
	public boolean save(Order order) {
		boolean rowAffected = false;
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("insert into order (orderID, cartID, userID, userAdd, userTel, prodName, prodPrice, orderQuantity, prodQuantity, totalPrice, farmID, farmTel, farmCheck, trackNum, is_status"
					+ ") values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			pstmt.setInt(1, order.getOrderID());
			pstmt.setInt(2, order.getCartID());
			pstmt.setString(3, order.getUserID());
			pstmt.setString(4, order.getUserAdd());
			pstmt.setString(5, order.getUserTel());
			pstmt.setInt(6, order.getProdID());
			pstmt.setString(7, order.getProdName());
			pstmt.setInt(8, order.getProdPrice());
			pstmt.setInt(9, order.getOrderQuantity());
			pstmt.setInt(10, order.getProdQuantity());
			pstmt.setInt(11, order.getTotalPrice());
			pstmt.setString(12, order.getFarmID());
			pstmt.setString(13, order.getFarmTel());
			pstmt.setBoolean(14, order.getFarmCheck());
			pstmt.setInt(15, order.getTrackNum());
			pstmt.setString(16, order.getIs_status());

			rowAffected = pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("order sql 오류");
		} finally {
			closeAll();
		}

		System.out.println("order테이블 등록 완료");
		return rowAffected;
	}
	
	
	
	
	private void closeAll() {
		try {
			// 나중에 연 순서부터 닫음 rs => pstmt => conn(풀로 되돌아감)
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close(); // 실제로는 Connection Pool로 되돌아감
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB연결 닫기 에러");
		}
	}
}
