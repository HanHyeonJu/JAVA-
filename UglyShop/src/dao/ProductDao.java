package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import beans.Product;


public class ProductDao {
	private DataSource dataSource;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public ProductDao(DataSource datasource) {
		this.dataSource = datasource;
	}
	
	// 모든 상품을 리스트로 리턴
	public List<Product> findAll(){
		List<Product> list = new ArrayList<>();
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("select * from product");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Product prod = new Product();
				prod.setProdID(rs.getInt("prodID"));
				prod.setFarmID(rs.getString("farmID"));
				prod.setProdName(rs.getString("prodName"));
				prod.setProdPrice(rs.getInt("prodPrice"));
				prod.setProdInven(rs.getInt("prodInven"));
				prod.setProdImg(rs.getString("prodImg"));
				prod.setProdInfo(rs.getString("prodInfo"));
				
				list.add(prod);
			}
		} catch (SQLException e) {
			System.out.println("SQL에러 - product findAll");
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return list;
	}
	
	public Product find(int id) {
		Product prod = null;
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("select * from product where prodID=?");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
					
			while(rs.next()) {
				prod = new Product();
				prod.setProdID(rs.getInt("prodID"));
				prod.setFarmID(rs.getString("farmID"));
				prod.setProdName(rs.getString("prodName"));
				prod.setProdPrice(rs.getInt("prodPrice"));
				prod.setProdInven(rs.getInt("prodInven"));
				prod.setProdImg(rs.getString("prodImg"));
				prod.setProdInfo(rs.getString("prodInfo"));
			}
			
		} catch (Exception e) {
			System.out.println("SQL에러 - product find");
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return prod;
	}
	
	// 모든 장바구니를 리스트로 리턴
	public Product findById(int prodId) {
			
		Product prod = null;
			
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("select * from product where prodID=?");
			pstmt.setInt(1, prodId);
			rs = pstmt.executeQuery();
			
			
		if(rs.next()) {
				prod = new Product();
				prod.setProdID(rs.getInt("prodID"));
				prod.setFarmID(rs.getString("farmID"));
				prod.setProdName(rs.getString("prodName"));
				prod.setProdPrice(rs.getInt("prodPrice"));
				prod.setProdInven(rs.getInt("prodInven"));
				prod.setProdImg(rs.getString("prodImg"));
				prod.setProdInfo(rs.getString("prodInfo"));
				
			}
			
		}catch (SQLException e) {
				e.printStackTrace();
				System.out.println("SQL 에러");
		} finally { //에러에 상관없이 무조건 실행
				//DB연결 객체들을 닫는 과정이 필요하다.
				closeAll();	
		}
			
		return prod;
		}
		
	public void closeAll() {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch (Exception e) {
			System.out.println("DB연결 닫는 과정에서 에러발생");
		}
	}
}
