package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import beans.Review;

public class ReviewDAO {
	private DataSource datasource;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
    public ReviewDAO(DataSource datasource) {
        this.datasource = datasource;
        // 객체 생성시 커넥션 풀 datasource를 입력
    }
    
    // 모든 리뷰를 리스트로 리턴
    public List<Review> findAll(){
    	List<Review> list = new ArrayList<>();
    	
    	try {
    		conn = datasource.getConnection();
			pstmt = conn.prepareStatement("select * from review");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Review review = new Review();
				review.setReviewID(rs.getInt("reviewID"));
				review.setUserID(rs.getString("userID"));
				review.setReviewTitle(rs.getString("reviewTitle"));
				review.setReviewDate(rs.getDate("reviewDate").toLocalDate());
				review.setReviewContent(rs.getString("reviewContent"));
				review.setProdID(rs.getInt("prodID"));
				
				list.add(review);
			}
		} catch (SQLException e) {
			System.out.println("SQL에러 - review findAll");
			e.printStackTrace();
		} finally {
			closeAll();
		}
    	
		return list;
    }
    
    public Review find(int id) {
    	// 받아온 reviewID로 같은 값을 가진 행을 찾아 출력
    	Review review = null;
    	
    	try {
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement("select * from review where reviewID=?");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				// rs에 리턴받은 데이터를 속성별로 review객체에 저장
				review = new Review();	// 여기서 ""내부는 DB의 속성이름과 같아야함
				review.setReviewID(rs.getInt("reviewID"));
				review.setReviewTitle(rs.getString("reviewTitle"));;
				review.setUserID(rs.getString("userID"));
				review.setReviewDate(rs.getDate("reviewDate").toLocalDate());
				review.setReviewContent(rs.getString("reviewContent"));
				review.setProdID(rs.getInt("prodID"));
				review.setFarmID(rs.getString("farmID"));
			}
    		
		} catch (SQLException e) {
			System.out.println("SQL에러 - review find");
			e.printStackTrace();
		} finally {
			closeAll();
		}
    	return review;
    }
    
    public List<Review> findProd(int id) {
    	// 받아온 prodID로 같은 값을 가진 리뷰들을 모두 출력
    	List<Review> list = new ArrayList<>();
    	
    	try {
    		conn = datasource.getConnection();
			pstmt = conn.prepareStatement("select * from review where prodID=?");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Review review = new Review();
				review.setReviewID(rs.getInt("reviewID"));
				review.setUserID(rs.getString("userID"));
				review.setReviewTitle(rs.getString("reviewTitle"));
				review.setReviewDate(rs.getDate("reviewDate").toLocalDate());
				review.setReviewContent(rs.getString("reviewContent"));
				review.setProdID(rs.getInt("prodID"));
				
				list.add(review);
			}
		} catch (SQLException e) {
			System.out.println("SQL에러 - review findProd");
			e.printStackTrace();
		} finally {
			closeAll();
		}
    	
		return list;
    }
    
	public boolean delete(int reviewID) {
		
			boolean rowDeleted = false;
					
			try {
				conn = datasource.getConnection();
				pstmt = conn.prepareStatement("DELETE FROM review WHERE reviewid = ?");
				pstmt.setInt(1, reviewID);
				rowDeleted = pstmt.executeUpdate() > 0;
				
			} catch (SQLException e) {
				System.out.println("SQL 삭제 에러");
				return false;
			}finally {
				closeAll();
			}
			System.out.println("리뷰 삭제!");
			
			return rowDeleted;
	}
	
	public boolean update(Review review) {
		boolean rowAffected = false;
		 // userID,prodID는 업뎃을 할필요가?
		try {
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement("update review set userID =? , reviewDate = now() , reviewTitle =? , reviewContent =?, prodID =? where reviewID =?");
			pstmt.setString(1, review.getUserID());
			//pstmt.setDate(2, Date.valueOf(review.getReviewDate()));
			pstmt.setString(2, review.getReviewTitle());
			pstmt.setString(3, review.getReviewContent());
			pstmt.setInt(4, review.getProdID());
			pstmt.setInt(5, review.getReviewID());
			
			rowAffected = pstmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL에러 - update");
		} finally {
			closeAll();
		}
		return rowAffected;
	}
    
	public void closeAll() {
		try {
			// 생성한 순서의 역순으로 닫아줌. rs > pstmt > conn (pool로 되돌아감)
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch (Exception e2) {
			System.out.println("DB연결 닫는 작업에서 에러발생");
		}
	}

	
}
