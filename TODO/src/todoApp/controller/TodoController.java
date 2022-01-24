package todoApp.controller;

import java.io.IOException;
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


// 서블릿이 기본"/"주소이면 다른 서블릿 "/register , /login" 등을 제외한 모든 요청이 여기에서 처리 
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
		case "edit":
			showEditForm(request, response);
			break;
		case "update":
			updateTodo(request, response);
			break;
		case "list": //localhost:8090/TODO/list
			listTodo(request, response);
			break;
		default:	//요청주소가 기본 또는 잘못되었을 경우 로그인 페이지로 이동
			RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
			dispatcher.forward(request, response);
			break;			
		} //스위치 문 끝
		
	}

	private void updateTodo(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// todo의 id별로 수정해야 하기 때문에 id가 필요함
		Long id = Long.parseLong(request.getParameter("id")); //id를 받음
		Todo theTodo = todoDAO.selectTodo(id);
		// todoDAO에서 이미 만들어뒀던 아이디를 가지고 그 아이디에 관한 todo를 검색하여 결과를 받는 메소드가 있는 selectTodo를 사용해서 해당 id에 맞는 todo를 가져옴
		
		// 수정할 객체인 theTodo를 todo 이름으로 저장해서 보냄
		request.setAttribute("todo", theTodo);
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-form.jsp");	// todo가 null값이 아니기 때문에 할일 추가 폼이 아닌 수정 폼이 열리면서 수정가능
		dispatcher.forward(request, response);	
	}

	private void deleteTodo(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void insertTodo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 입력값을 받아서 DB(todos table)에 저장
		request.setCharacterEncoding("UTF-8"); // 한글로 입력받을 수 있도록 설정
		
		HttpSession session =request.getSession();
		session.getAttribute("username"); // 유저네임을 세션으로 받음
		
		String title = request.getParameter("title");
		String username = (String)session.getAttribute("username");
		String description = request.getParameter("description");
		LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"));
		boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
		
		Todo newTodo = new Todo(title, username, description, targetDate, isDone);
		
		// 새 할 일을 저장 후에 리스트 페이지로 이동
		todoDAO.insertTodo(newTodo);
		response.sendRedirect("todos?action=list"); // 이미 DB에 저장되어 있는 상태일 때는 redirect로 페이지를 이동해도 서버에서가 아니라 DB에서 가져오기 때문에 상관없음
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// action이 new인 경우 = > 할일 추가 페이지 보여주기
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-form.jsp");
		dispatcher.forward(request, response);	
	}

	private void listTodo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// action이 list인 경우
		List<Todo> listTodo = todoDAO.selectAllTodos(); //DB에서 할일들을 가져와 리스트에 저장
		request.setAttribute("listTodo", listTodo);	    //리스트를 리퀘스트에 저장
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-list.jsp");
		dispatcher.forward(request, response);		
	}

}
