package todoApp.dao;
//인터페이스 dao로 이것을 구현해서 기능을 완성시키게 함

import java.util.List;

import todoApp.model.Todo;
import todoApp.model.User;

public interface TodoDao {
	
	
	User getUserAndTodos(String userName);
	
	List<User> getAllUsersAndTodoCount();
	
	
	// Create 입력 => 할 일을 DB에 입력
	void insertTodo(Todo todo);
	// Read id로 할일을 검색
	Todo selectTodo(long todoId);
	
	// username 으로 할일을 검색
	List<Todo> selectTodoByUsername(String username);
	
	// Read 모든 할일을 검색
	List<Todo> selectAllTodos();
	// Delete 할일을 삭제(id로)
	boolean deleteTodo(long todoId);
	
	// Delete 할일을 삭제(username 으로)
	boolean deleteTodo(String username);
	
	// Update 할일을 업데이트
	boolean updateTodo(Todo todo);
}





