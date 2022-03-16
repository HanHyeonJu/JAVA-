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

public class TodoDaoImpl implements TodoDao {
//DB연결하고 각 기능에 맞게 작업한다. 데이터베이스 todos테이블에 CRUD 작업

	
	
	// 외부 조인해서 가져오기 메소드
	public User getUserAndTodos(String userName) {
		User user = null;
		List<Todo> todoList = new ArrayList<Todo>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";
		sql += " SELECT u.id AS user_id, u.userName, u.firstName, u.lastName, u.password, ";
		sql += "        t.id AS todo_id, t.title, t.description, t.is_done, t.target_date ";
		sql += " FROM users u LEFT OUTER JOIN todos t ";
		sql += " ON u.userName = t.username ";
		sql += " WHERE u.userName = ? ";
		sql += " ORDER BY target_date DESC ";
		
		try {
			conn = JDBCUtils.getConnection();
			
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, userName);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Todo todo = new Todo();
				todo.setId(rs.getLong("todo_id"));
				todo.setTitle(rs.getString("title"));
				todo.setDescription(rs.getString("description"));
				todo.setStatus(rs.getBoolean("is_done"));
				todo.setTargetDate(rs.getDate("target_date").toLocalDate());
				
				todoList.add(todo); // 리스트에 추가
			} // while
			
			if (rs.last()) { // 마지막 데이터 행으로 커서 위치를 이동시키기
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
	
	
	
	@Override
	public List<User> getAllUsersAndTodoCount() {
		List<User> userList = new ArrayList<User>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";
		sql += " SELECT u.id AS user_id, u.userName, u.firstName, u.lastName, ";
		sql += "        COUNT(t.username) AS todo_count ";
		sql += " FROM users u LEFT OUTER JOIN todos t ";
		sql += " ON u.userName = t.username ";
		sql += " GROUP BY u.username ";
		sql += " HAVING COUNT(t.username) > 0 ";
		sql += " ORDER BY userName ASC, target_date DESC ";
		
		try {
			conn = JDBCUtils.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				User user = new User();
				user.setUserName(rs.getString("userName"));
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setTodoCount(rs.getInt("todo_count"));
				
				userList.add(user); // 리스트에 추가
			} // while
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn, pstmt, rs);
		}
		
		return userList;
	} // getAllUsersAndTodoCount
	
	
	
	
	@Override
	public void insertTodo(Todo todo) {
		
		String INSERT_TODO_SQL = "INSERT INTO todos(title,username,description,target_date,is_done) "
				+ "VALUE (?,?,?,?,?)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JDBCUtils.getConnection();
			
			pstmt = conn.prepareStatement(INSERT_TODO_SQL);
			pstmt.setString(1, todo.getTitle());
			pstmt.setString(2, todo.getUsername());
			pstmt.setString(3, todo.getDescription());
			pstmt.setDate(4, JDBCUtils.getSQLDate(todo.getTargetDate())); 
			pstmt.setBoolean(5, todo.isStatus());
			
			pstmt.executeUpdate(); //결과가 없는 업데이트,삭제,입력 등은 쿼리 업데이트 한 줄의 갯수가 리턴됨
			
		} catch (SQLException e) {
			System.out.println("SQL 입력 에러");
			return;
		} finally {
			JDBCUtils.close(conn, pstmt);
		}
		System.out.println("입력 완료!");
	} // insertTodo

	@Override
	public Todo selectTodo(long todoId) {
		
		Todo todo = null;
		
		String SELECT_TODO_BY_ID = "SELECT id,title,username,description,target_date,is_done FROM todos WHERE id = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			
			pstmt = conn.prepareStatement(SELECT_TODO_BY_ID);
			pstmt.setLong(1, todoId);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) { //결과가 있을 경우에 값을 저장한다. (없는데 저장하면 에러발생)
				long id = rs.getLong("id");
				String title = rs.getString("title");
				String username = rs.getString("username");
				String description = rs.getString("description");
				LocalDate targetDate = rs.getDate("target_date").toLocalDate();
				Boolean status = rs.getBoolean("is_done");
				todo = new Todo(id, title, username, description, targetDate, status);
			}
		} catch (SQLException e) {
			System.out.println("SQL todo 검색 에러");
			return null;
		} finally {
			JDBCUtils.close(conn, pstmt, rs);
		}
		System.out.println("아이디로 할일 검색완료!");
		return todo;
	} // selectTodo
	
	
	@Override
	public List<Todo> selectTodoByUsername(String username) {

		List<Todo> todos = new ArrayList<>(); //빈 리스트를 생성

		String SELECT_ALL_TODOS = "SELECT * FROM todos WHERE username = ? "; //todos테이블 전체 검색
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			
			pstmt = conn.prepareStatement(SELECT_ALL_TODOS);
			pstmt.setString(1, username);
			
			rs = pstmt.executeQuery(); // 쿼리 실행후 결과 저장
			//결과가 여러줄일경우 while()사용하여 처리, 1줄일때 if()
			while(rs.next()) { //결과가 있을 경우에 값을 저장한다. (없는데 저장하면 에러발생)
				Todo todo = new Todo();
				todo.setId(rs.getLong("id"));
				todo.setTitle(rs.getString("title"));
				todo.setUsername(rs.getString("username"));
				todo.setDescription(rs.getString("description"));
				todo.setTargetDate(rs.getDate("target_date").toLocalDate());
				todo.setStatus(rs.getBoolean("is_done"));
				
				//리스트에 담기 ( todo객체로 입력)
				todos.add(todo);
			} // while
		} catch (SQLException e) {
			System.out.println("SQL todo ALL검색 에러");
			return null;
		} finally {
			JDBCUtils.close(conn, pstmt, rs);
		}
		System.out.println("todo 리스트 검색완료!");
		return todos;
	} // selectTodoByUsername
	

	@Override
	public List<Todo> selectAllTodos() {
		
		List<Todo> todos = new ArrayList<>(); //빈 리스트를 생성

		String SELECT_ALL_TODOS = "SELECT * FROM todos"; //todos테이블 전체 검색
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			
			pstmt = conn.prepareStatement(SELECT_ALL_TODOS);
			
			rs = pstmt.executeQuery(); // 쿼리 실행후 결과 저장
			//결과가 여러줄일경우 while()사용하여 처리, 1줄일때 if()
			while (rs.next()) { //결과가 있을 경우에 값을 저장한다. (없는데 저장하면 에러발생)
				long id = rs.getLong("id");
				String title = rs.getString("title");
				String username = rs.getString("username");
				String description = rs.getString("description");
				LocalDate targetDate = rs.getDate("target_date").toLocalDate();
				Boolean status = rs.getBoolean("is_done");
				//리스트에 담기 ( todo객체로 입력)
				todos.add(new Todo(id, title, username, description, targetDate, status));
			} // while
		} catch (SQLException e) {
			System.out.println("SQL todo ALL검색 에러");
			return null;
		} finally {
			JDBCUtils.close(conn, pstmt, rs);
		}
		System.out.println("todo 리스트 검색완료!");
		return todos;
	} // selectAllTodos

	@Override
	public boolean deleteTodo(long todoId) {
		
		String DELETE_TODO_SQL = "DELETE FROM todos WHERE id = ?";
		boolean rowDeleted = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JDBCUtils.getConnection();
			pstmt = conn.prepareStatement(DELETE_TODO_SQL);
			pstmt.setLong(1, todoId);		
			
			rowDeleted = pstmt.executeUpdate() > 0; // 한줄이상 삭제가 되면 true
			
		} catch (SQLException e) {
			System.out.println("SQL 삭제 에러");
			return false;
		} finally {
			JDBCUtils.close(conn, pstmt);
		}
		System.out.println("할일 삭제!");
		return rowDeleted;
	} // deleteTodo
	
	
	@Override
	public boolean deleteTodo(String username) {
		String DELETE_TODO_SQL = "DELETE FROM todos WHERE username = ?";
		boolean rowDeleted = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JDBCUtils.getConnection();
			pstmt = conn.prepareStatement(DELETE_TODO_SQL);
			pstmt.setString(1, username);		
			
			rowDeleted = pstmt.executeUpdate() > 0; // 한줄이상 삭제가 되면 true
			
		} catch (SQLException e) {
			System.out.println("SQL 삭제 에러");
			return false;
		} finally {
			JDBCUtils.close(conn, pstmt);
		}
		System.out.println("할일 삭제!");
		return rowDeleted;
	} // deleteTodo

	
	@Override
	public boolean updateTodo(Todo todo) {
		
		String UPDATE_TODO = 
				"UPDATE todos set title=?, username=?, description=?, target_date=?, is_done=? where id=?";
		boolean rowUpdated = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JDBCUtils.getConnection();
			pstmt = conn.prepareStatement(UPDATE_TODO); 
			
			pstmt.setString(1, todo.getTitle());
			pstmt.setString(2, todo.getUsername());
			pstmt.setString(3, todo.getDescription());
			pstmt.setDate(4, JDBCUtils.getSQLDate(todo.getTargetDate()));
			pstmt.setBoolean(5, todo.isStatus());
			pstmt.setLong(6, todo.getId());
			
			rowUpdated = pstmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
			System.out.println("SQL 업데이트 todo 에러");
			return false;
		} finally {
			JDBCUtils.close(conn, pstmt);
		}
		System.out.println("업데이트 완료!");
		return rowUpdated;
	} // updateTodo
	
}



