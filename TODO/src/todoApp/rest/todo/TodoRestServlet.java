package todoApp.rest.todo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import todoApp.dao.TodoDao;
import todoApp.dao.TodoDaoImpl;
import todoApp.model.Todo;
import todoApp.model.User;

//요청 공통 URL주소
//http://localhost:8090/TODO/api/todo/

@WebServlet(urlPatterns = "/api/todo/*", loadOnStartup = 1)
public class TodoRestServlet extends HttpServlet {
	
	private TodoDao todoDao = new TodoDaoImpl();
	private Gson gson = new Gson();
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("TodoRestServlet init() 호출됨");
	}
	
	
	private void sendResponse(String strJson, HttpServletResponse response) throws IOException {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(strJson);
		//out.flush();
		out.close();
	} // sendResponse
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("TodoRestServlet doGet() 호출됨");
		
		// 레코드 한개 조회
		// http://localhost:8090/TODO/api/todo?category=one&id=3
		
		// 레코드 특정 사용자 데이터만(여러개) 조회
		// http://localhost:8090/TODO/api/todo?category=me&username=hong
		
		// 레코드 전체(여러개) 조회 
		// http://localhost:8090/TODO/api/todo?category=all
		
		String category = request.getParameter("category");
		String strJson = "";
		
		if (category.equals("one")) {
			strJson = processGetOne(request);
		} else if (category.equals("me")) {
			strJson = processGetMe(request);
		} else if (category.equals("all")) {
			strJson = processGetAll(request);
		}
		
		sendResponse(strJson, response);
	} // doGet


	private String processGetOne(HttpServletRequest request) {
		
		String strId = request.getParameter("id");
		long id = Long.parseLong(strId); // "5" -> 5L
		
		Todo todo = todoDao.selectTodo(id);
		
		TodoOneResult todoOneResult = new TodoOneResult();
		if (todo != null) {
			todoOneResult.setHasResult(true);
			todoOneResult.setTodo(todo);
		} else { // todo == null
			todoOneResult.setHasResult(false);
		}
		
		String strJson = gson.toJson(todoOneResult);
		System.out.println("strJson : " + strJson);
		return strJson;
	} // processGetOne
	
	
	private String processGetMe(HttpServletRequest request) {
		
		String username = request.getParameter("username");
		
		User user = todoDao.getUserAndTodos(username);
		
		TodoListResult todoListResult = new TodoListResult();
		
		if (user != null) {
			todoListResult.setHasResult(true);
			todoListResult.setTotalCount(user.getTodoList().size());
			todoListResult.setUser(user);
		} else { // user == null
			todoListResult.setHasResult(false);
			todoListResult.setTotalCount(0);
		}
		
		String strJson = gson.toJson(todoListResult);
		System.out.println("strJson : " + strJson);
		return strJson;
	} // processGetMine
	
	
	private String processGetAll(HttpServletRequest request) {

		List<User> userList = todoDao.getAllUsersAndTodoCount();
		
		TodoListUsersResult result = new TodoListUsersResult();
		
		if (userList.size() > 0) {
			result.setHasResult(true);
			result.setTotalCount(userList.size());
			result.setUserList(userList);
		} else {
			result.setHasResult(false);
			result.setTotalCount(userList.size());
		}
		
		String strJson = gson.toJson(result);
		System.out.println("strJson : " + strJson);
		return strJson;
	} // processGetAll


	/*
	post 요청시 JSON 문자열 형식
	{
	    "title": "할일1",
	    "description": "할일1 입니다.",
	    "username": "hong",
	    "targetDate": {
	        "year": 2022,
	        "month": 3,
	        "day": 2
	    },
	    "status": false
	}
	*/
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("TodoRestServlet doPost() 호출됨");
		
		request.setCharacterEncoding("utf-8"); // 한글처리
		
		BufferedReader reader = request.getReader(); // 문자 입력스트림 가져오기

		Todo todo = gson.fromJson(reader, Todo.class); // JSON 문자열로부터 User 객체로 변환하기
		System.out.println(todo.toString());
		
		todoDao.insertTodo(todo); // 새 할일 글 한개 추가
		
		TodoResult todoResult = new TodoResult();
		todoResult.setSuccess(true);
		
		String strJson = gson.toJson(todoResult);
		
		sendResponse(strJson, response);
	} // doPost

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("TodoRestServlet doPut() 호출됨");
		
		request.setCharacterEncoding("utf-8"); // 한글처리
		
		BufferedReader reader = request.getReader(); // 문자 입력스트림 가져오기

		Todo todo = gson.fromJson(reader, Todo.class); // JSON 문자열로부터 User 객체로 변환하기
		System.out.println(todo.toString());
		
		todoDao.updateTodo(todo); // 할일 글 한개 수정
		
		TodoResult todoResult = new TodoResult();
		todoResult.setSuccess(true);
		
		String strJson = gson.toJson(todoResult);
		
		sendResponse(strJson, response);
	} // doPut

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("TodoRestServlet doDelete() 호출됨");
		
		// 레코드 한개 삭제
		// http://localhost:8090/TODO/api/todo?category=one&id=3
		
		// 레코드 특정 사용자 데이터만(여러개) 삭제
		// http://localhost:8090/TODO/api/todo?category=mine&username=hong
		
		String category = request.getParameter("category");
		String strJson = "";
		
		if (category.equals("one")) {
			strJson = processDeleteOne(request);
		} else if (category.equals("mine")) {
			strJson = processDeleteMine(request);
		}
		
		sendResponse(strJson, response);
	} // doDelete


	private String processDeleteOne(HttpServletRequest request) {
		String strId = request.getParameter("id"); // 삭제할 글번호 id
		long id = Long.parseLong(strId);
		
		todoDao.deleteTodo(id); // 글번호 id에 해당하는 글 한개 삭제
		
		TodoResult todoResult = new TodoResult();
		todoResult.setSuccess(true);
		
		String strJson = gson.toJson(todoResult);
		return strJson;
	} // processDeleteOne
	
	
	private String processDeleteMine(HttpServletRequest request) {
		String username = request.getParameter("username"); // 삭제할 username
		
		todoDao.deleteTodo(username); // username 에 해당하는 글 여러개 삭제
		
		TodoResult todoResult = new TodoResult();
		todoResult.setSuccess(true);
		
		String strJson = gson.toJson(todoResult);
		return strJson;
	} // processDeleteMine


	@Override
	public void destroy() {
		System.out.println("TodoRestServlet destroy() 호출됨");
	}
}
