package test;

import java.time.LocalDate;
import java.util.List;

import todoApp.dao.TodoDaoImpl;
import todoApp.model.Todo;

public class Test {

	public static void main(String[] args) {
		
		TodoDaoImpl dao = new TodoDaoImpl();
//		Todo todo1 = new Todo(1L, "할일1", "drv98", "첫번째 할일", LocalDate.parse("2022-01-30"), false);
		
		//dao.insertTodo(todo1);
		
//		Todo t1 = dao.selectTodo(1);
//		System.out.println(t1.toString());
		
		//dao.deleteTodo(1);
//		Todo todo3 = new Todo(3L, "할일3수정", "son", "세번째 할일수정", LocalDate.parse("2052-02-20"), true);
//		dao.updateTodo(todo3);
		
		List<Todo> todos = dao.selectAllTodos();
		
		for( Todo todo : todos) {
			System.out.println(todo.toString());
		}
		
	}

}
