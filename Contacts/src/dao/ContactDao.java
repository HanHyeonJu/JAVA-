package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.Contact;

public class ContactDao {
// DB에 있는 contacts 테이블을 CRUD하는 클래스
	// DB 연결에 필요한 객체들
	private DataSource dataSource; // jdbc/demo 커넥션 풀 연결 객체
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	// ContactDao 객체 생성 시 Connection Pool인 dataSource를 사용하기 위해 dataSource 매개변수를
	// 가진기본생성자를 만들어 줌
	// ConnactDao 객체를 사용할 때는 dataSource가 들어가도록 해줌
	public ContactDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	// 모든 연락처를 리스트로 return
	public List<Contact> findAll() {
		List<Contact> list = new ArrayList<Contact>(); // Contact 타입의 빈 리스트 생성

		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("select * from contacts");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// DB에서 contact 객체로 결과를 가져와서 저장시킴
				// 지금처럼 자바빈 클래스에서 생성자를 따로 만들지 않고 set데이터들을 저장해도 되고 todo처럼 생성자를 만들어놓고 그 안에 저장해도 상관없음 편한대로 하면 됨
				Contact contact = new Contact();
				contact.setId(rs.getInt("id"));
				contact.setName(rs.getString("name"));
				contact.setEmail(rs.getString("email"));
				contact.setPhone(rs.getString("phone"));

				// contact 객체에 저장한 데이터들을 리스트에 추가
				list.add(contact);
			} // Controller 서블릿에서 잘 만들었는지 test 해보기

		} catch (SQLException e) {
			System.out.println("SQL 에러");
		} finally { // 에러에 상관없이 무조건 실행됨
			closeAll();
		}
		return list;
	}
	
	// 특정 id에 해당하는 데이터 return
	public Contact find(int id) {
		Contact contact = null;
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("select * from contacts where id=?");
			// DB에 입력할 값
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				contact = new Contact();
				contact.setId(rs.getInt("id"));
				contact.setName(rs.getString("name"));
				contact.setEmail(rs.getString("email"));
				contact.setPhone(rs.getString("phone"));
			}
		} catch (SQLException e) {
			System.out.println("시스템 에러");
		} finally {
			closeAll();
		}
		
		return contact;
	}
	
	// 입력한 contact 객체를 DB에 저장(새로운 연락처 저장)
	public boolean save(Contact contact) {
		boolean rowAffected = false;
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("insert into contacts (name, email, phone) values (?, ?, ?)");
			pstmt.setString(1, contact.getName());
			pstmt.setString(2, contact.getEmail());
			pstmt.setString(3, contact.getPhone());

			rowAffected = pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("시스템 에러");
			return false;
		} finally {
			closeAll();
		}
		
		return rowAffected;
	}
	
	// 수정한 contact 객체를 DB에 업데이트(전체 수정이 아니고 id별로 수정)
	public boolean update(Contact contact) {
		boolean rowUpdate = false;
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("update contacts set name=?, email=?, phone=? where id=?");
			pstmt.setString(1, contact.getName());
			pstmt.setString(2, contact.getEmail());
			pstmt.setString(3, contact.getPhone());
			pstmt.setInt(4, contact.getId());
			
			rowUpdate = pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("시스템 에러");
			return false;
		} finally {
			closeAll();
		}
		
		return rowUpdate;
	}
	
	// 입력된 id에 해당하는 데이터를 테이블에서 삭제
	public boolean delete(int id) {
		boolean rowDelete = false;
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("delete from contacts where id=?");
			// DB에 입력할 값
			pstmt.setInt(1, id);
			
			rowDelete = pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("시스템 에러");
			return false;
		} finally {
			closeAll();
		}
		
		return rowDelete;
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
