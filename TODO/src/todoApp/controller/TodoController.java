package todoApp.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import todoApp.dao.TodoDao;
import todoApp.dao.TodoDaoImpl;
import todoApp.model.Todo;

@WebServlet("/todos")
public class TodoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TodoDao todoDAO;
	
	public void init() {
		todoDAO = new TodoDaoImpl(); //실제 객체는 todoDao를 구현한 TodoDaoImple로 생성
	}
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response); //post로 요청하더라도 get으로 처리
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청주소가 localhost:8090/TODO/new => "/new" 가 action의 값
		String action = request.getParameter("action");
		System.out.println(action);
		switch(action) {
		case "new":
			showNewForm(request, response);
			break;
		case "post":
			insertTodo(request, response);
			break;	
		case "delete":
			deleteTodo(request, response);
			break;
		case "edit": // 수정 폼을 보여줌
			showEditForm(request, response);
			break;
		case "update":
			updateTodo(request, response);
			break;
		case "list": //localhost:8090/TODO/todos/list
			listTodo(request, response);
			break;
		default:	//요청주소가 기본 또는 잘못되었을 경우 로그인 페이지로 이동
			HttpSession session = request.getSession();
			session.invalidate(); //로그인 정보를 모두 삭제
			RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
			dispatcher.forward(request, response);		
		} //스위치 문 끝
		
	}

	private void updateTodo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		// 업데이트시에는 id도 입력됨
		Long id = Long.parseLong(request.getParameter("id"));	
		String title = request.getParameter("title");
		String username = (String)session.getAttribute("username");
		String description = request.getParameter("description");
		LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"));		
		boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
		
		Todo todo = new Todo(id, title, username, description, targetDate, isDone);		
		todoDAO.updateTodo(todo);
		
		response.sendRedirect("todos?action=list");	// 할일을 업데이트한 후 리스트 페이지로 이동		
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id")); //id를 받음
		Todo theTodo = todoDAO.selectTodo(id);
		// 수정할 todo객체를 같이 보냄
		request.setAttribute("todo", theTodo);
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-form.jsp");
		dispatcher.forward(request, response);	
		
	}

	private void deleteTodo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Long id = Long.parseLong(request.getParameter("id")); //id를 받음
		todoDAO.deleteTodo(id);
		
		response.sendRedirect("todos?action=list");	// 삭제 후 리스트 페이지로 이동
	}

	private void insertTodo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
			
		String title = request.getParameter("title");
		String username = (String)session.getAttribute("username");
		String description = request.getParameter("description");
		LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"));		
		boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
		
		Todo newTodo = new Todo(title, username, description, targetDate, isDone);		
		todoDAO.insertTodo(newTodo);
		
		response.sendRedirect("todos?action=list");	// 새 할일을 저장후에 리스트 페이지로 이동	
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-form.jsp");
		dispatcher.forward(request, response);			
	}

	private void listTodo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Todo> listTodo = todoDAO.selectAllTodos(); //DB에서 할일들을 가져와 리스트에 저장
		request.setAttribute("listTodo", listTodo);	    //리스트를 리퀘스트에 저장
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-list.jsp");
		dispatcher.forward(request, response);		
	}

}
