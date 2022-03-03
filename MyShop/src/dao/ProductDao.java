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
	private DataSource datasource;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public ProductDao(DataSource datasource) {
		this.datasource = datasource;
	}

	// 모든 상품을 리스트로 리턴
	public List<Product> findAll() {
		List<Product> prodList = new ArrayList<>();

		try {
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement("select * from product");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Product product = new Product();
				product.setProdID(rs.getInt("prodID"));
				product.setFarmID(rs.getString("farmID"));
				product.setProdName(rs.getString("prodName"));
				product.setProdPrice(rs.getInt("prodPrice"));
				product.setProdInven(rs.getInt("prodInven"));
				product.setProdImg(rs.getString("prodImg"));
				product.setProdInfo(rs.getString("prodInfo"));

				prodList.add(product);
			}
			
		} catch (SQLException e) {
			System.out.println("농산품 전체 출력 SQL에러");
			e.printStackTrace();
		} finally {
			closeAll();
		}
		
		System.out.println("농산품 전체 출력 완료");
		return prodList;
	}

	public Product find(int prodId) {
		Product product = null;

		try {
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement("select * from product where prodID=?");
			pstmt.setInt(1, prodId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				product = new Product();
				product.setProdID(rs.getInt("prodID"));
				product.setFarmID(rs.getString("farmID"));
				product.setProdName(rs.getString("prodName"));
				product.setProdPrice(rs.getInt("prodPrice"));
				product.setProdInven(rs.getInt("prodInven"));
				product.setProdImg(rs.getString("prodImg"));
				product.setProdInfo(rs.getString("prodInfo"));
			}

		} catch (Exception e) {
			System.out.println("특정 농산품 출력 SQL에러");
			e.printStackTrace();
		} finally {
			closeAll();
		}
		
		System.out.println("특정 농산품 출력 완료");
		return product;
	}

	public void closeAll() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			System.out.println("DB연결 닫는 과정에서 에러발생");
		}
	}
}
