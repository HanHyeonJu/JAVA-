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
import beans.User;

public class OrderDAO {
	private DataSource dataSource; // jdbc/shop 커넥션 풀 연결 객체
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public OrderDAO(DataSource dataSource) {
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
				order.setFarmID(rs.getString("farmID"));
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
	public Order findByUserId(String userID) {
		Order order = null;
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("select userName,userAdd,userTel from orders where userID=?");
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				order = new Order();
				order.setUserName(rs.getString("userName"));
				order.setUserAdd(rs.getString("userAdd"));
				order.setUserTel(rs.getString("userTel"));
			}
			
			}catch (SQLException e) {
				e.printStackTrace();
				System.out.println("userId select SQL 에러");
			} finally { //에러에 상관없이 무조건 실행
				//DB연결 객체들을 닫는 과정이 필요하다.
				closeAll();	
			}
			
			return order;
			
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
			pstmt = conn.prepareStatement("insert into orders (userID, prodID, prodName, prodPrice, orderQuantity, farmID, is_status) values (?,?,?,?,?,?,?)");
			
			pstmt.setString(1, order.getUserID());
			pstmt.setInt(2, order.getProdID());
			pstmt.setString(3, order.getProdName());
			pstmt.setInt(4, order.getProdPrice());
			pstmt.setInt(5, order.getOrderQuantity());
			pstmt.setString(6, order.getFarmID());
			pstmt.setString(7, "배송요청");

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
	
	
	public boolean userSave(Order order) {
		boolean update = false;
		
		try {
			conn = dataSource.getConnection();
				pstmt = conn.prepareStatement("set sql_safe_updates=0");
				pstmt.executeQuery();
				pstmt.close();
				
				conn = dataSource.getConnection();
				pstmt = 
				conn.prepareStatement
				("update orders o inner join user u on u.userID = o.userID set o.userName = u.userName, o.userAdd = u.userAdd, o.userTel = u.userTel where o.userID = ?");
				pstmt.setString(1, order.getUserID());
				
				update = pstmt.executeUpdate() > 0; // update가 0보다 크면 true
			
		} catch (SQLException e) {
			System.out.println("SQL 에러" + e.getMessage());
		}finally {
			closeAll();
		}
		
		return update;
		
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
