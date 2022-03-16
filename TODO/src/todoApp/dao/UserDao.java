package todoApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import todoApp.model.Todo;
import todoApp.model.User;
import todoApp.utils.JDBCUtils;

// DAO 는 DB에 연결해 데이터를 조작하는 클래스
public class UserDao {
	
	//유저 입력 => DB에 유저데이터를 입력
	public int registerUser(User user) { //결과가 성공이면 1리턴 아니면 0이하
		String INSERT_USER_SQL = "INSERT INTO users(firstName,lastName,userName,password) "
				+ "VALUES (?,?,?,?)";
		
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JDBCUtils.getConnection();
			pstmt = conn.prepareStatement(INSERT_USER_SQL);
			pstmt.setString(1, user.getFirstName());
			pstmt.setString(2, user.getLastName());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getPassword()); // pstmt 준비 완료
			
			result = pstmt.executeUpdate(); //결과가 없는 업데이트,삭제,입력 등은 쿼리 업데이트 한 줄의 갯수가 리턴됨
			
		} catch (SQLException e) {
			System.out.println("SQL 입력 에러");
		} finally {
			JDBCUtils.close(conn, pstmt);
		}
		return result;
	} // registerUser
	
	
	public User getUserByUserName(String userName) {
		User user = null;
		
		String sql = "SELECT * FROM users WHERE userName = ? ";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtils.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				user = new User();
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setUserName(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn, pstmt, rs);
		}
		return user;
	} // getUserByUserName
	
	
	public List<User> getAllUsers() {
		List<User> userList = new ArrayList<User>();
		
		String sql = "SELECT * FROM users ORDER BY userName ASC ";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtils.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				User user = new User();
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setUserName(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
				
				userList.add(user);
			} // while
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn, pstmt, rs);
		}
		return userList;
	} // getAllUsers
	
	// 외부 조인해서 가져오기
	public User getUserAndTodos(String userName) {
		User user = null;
		List<Todo> todoList = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";
		sql += " SELECT u.userName, u.firstName, u.lastName, u.Password, ";
		sql += "        t.id, t.title, t.description, t.is_done, t.target_date ";
		sql += " from users u left outer join todos t ";
		sql += " ON u.userName = t.userName ";
		sql += " WHERE u.userName=? ";
		sql += " ORDER BY target_date DESC ";
		
		
		try {
			conn = JDBCUtils.getConnection();
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, userName);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Todo todo = new Todo();
				todo.setId(rs.getLong("id"));
				todo.setTitle(rs.getString("title"));
				todo.setDescription(rs.getString("description"));
				todo.setTargetDate(rs.getDate("target_date").toLocalDate());
				todo.setStatus(rs.getBoolean("is_done"));
				
				todoList.add(todo);
			}
			
			if(rs.last()) {
				user = new User();
				user.setUserName(rs.getString("userName"));
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setPassword(rs.getString("password"));
				
				user.setTodoList(todoList);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn, pstmt, rs);
		}
		
		return user;
	} // getUserAndTodos
	
	
	public void update(User user) {
		
		String sql = "";
		sql += " UPDATE users ";
		sql += " SET firstName = ?, lastName = ? ";
		sql += " WHERE userName = ? ";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = JDBCUtils.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getFirstName());
			pstmt.setString(2, user.getLastName());
			pstmt.setString(3, user.getUserName());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn, pstmt);
		}
	} // update
	
	
	
	public void delete(String userName) {
		
		String sql = "DELETE FROM users WHERE userName = ? ";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = JDBCUtils.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn, pstmt);
		}
	} // delete
	
	
	public static void main(String[] args) {
		UserDao userDao = new UserDao();
		User user = userDao.getUserAndTodos("hong");
		
		System.out.println(user);
	}


	public void updatePasswordById(String pwd, String id) {
		String sql = "";
		sql += " UPDATE users ";
		sql += " SET password = ? ";
		sql += " WHERE userName = ? ";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = JDBCUtils.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pwd);
			pstmt.setString(2, id);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn, pstmt);
		}
	}
	
} // class UserDao






