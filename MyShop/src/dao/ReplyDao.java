package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import beans.Reply;
// 실제 CRUD기능을 하는 클래스
public class ReplyDao {
	private DataSource datasource;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
    public  ReplyDao(DataSource datasource) {
        this.datasource = datasource;
        // 객체 생성시 커넥션 풀 datasource를 입력
    }
    
    // 클릭한 reviewID에 해당하는 하나의 덧글을 리턴 => 우선 테스트삼아 replyID로 찾게 만들었으니 나중에 sql문 수정할것
    public Reply find(int id) {
    	Reply reply = null;
    	
    	try {
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement("select * from reply where reviewID=?");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();	// 리턴받은 데이터를 rs객체에 저장
			
			while(rs.next()) {
				// 리턴받은 데이터를 Reply객체에 각각 저장
				reply = new Reply();
				reply.setReplyID(rs.getInt("replyID"));
				reply.setFarmID(rs.getString("farmID"));
				reply.setReplyContent(rs.getString("replyContent"));
				reply.setReviewID(rs.getInt("reviewID"));
				
		    	System.out.println("덧글 찾기 성공");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL에러 - find");
		} finally {
			closeAll();
		}
		return reply;	
    }
    
    public Reply prodfind(int id) {
    	Reply reply = null;
    	
    	try {
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement("select * from reply where prodID=?");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();	// 리턴받은 데이터를 rs객체에 저장
			
			while(rs.next()) {
				// 리턴받은 데이터를 Reply객체에 각각 저장
				reply = new Reply();
				reply.setReplyID(rs.getInt("replyID"));
				reply.setFarmID(rs.getString("farmID"));
				reply.setReplyContent(rs.getString("replyContent"));
				reply.setReviewID(rs.getInt("reviewID"));
				
		    	System.out.println("덧글 찾기 성공");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL에러 - find");
		} finally {
			closeAll();
		}
		return reply;	
    }
    
    
    public boolean save(Reply reply) {
    	boolean rowsaved = false;
    	
    	try {
			conn = datasource.getConnection();
	    	pstmt = conn.prepareStatement("insert into reply(farmID,replyContent,reviewID) values (?,?,?)");
	    	pstmt.setString(1, reply.getFarmID());
	    	pstmt.setString(2, reply.getReplyContent());
	    	pstmt.setInt(3, reply.getReviewID());
	    	rowsaved = pstmt.executeUpdate() > 0;	// 저장된 행이 1이상이면 true
	    	
	    	System.out.println("덧글 작성 성공");
	    	
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL에러 - save");
		} finally {
			closeAll();
		}
		return rowsaved;
    }
    
    
    public boolean delete(int id) {
    	Boolean deleteId = false;
    	try {
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement("delete from reply where replyID=?");
			pstmt.setInt(1, id);
			deleteId = pstmt.executeUpdate() > 0;	// 반환값이 1이면 삭제, 0이면 에러

	    	System.out.println("덧글 삭제 성공");			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL에러 - delete");
		} finally {
			closeAll();
		}
		return deleteId;
    }
    
    public void closeAll() {
		try {
			if(rs != null) rs.close();
		    if(pstmt != null) pstmt.close();
		    if(conn != null) conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB연결 닫는작업에서 에러발생!");
		}
    }
    
}
