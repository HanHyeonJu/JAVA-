package todoApp.rest.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import todoApp.dao.UserDao;
import todoApp.model.User;

// REST API 서버 요청방식 - 개별 CRUD 작업과 매칭해서 사용함 
// - GET : 조회(Read. DB의 select문)
// - POST : 생성(Create. DB의 insert문)
// - PUT : 수정(Update. DB의 update문)
// - DELETE : 삭제(Delete. DB의 delete문)

// 요청 공통 URL주소
// http://localhost:8090/TODO/api/user/

@WebServlet(urlPatterns = "/api/user/*", loadOnStartup = 1)
public class UserRestServlet extends HttpServlet {
	
	private ServletContext application;
	private UserDao userDao = new UserDao();
	private Gson gson = new Gson();

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init() 호출됨");
		
		// application : 애플리케이션당 한개 유지되는 영역객체
		application = config.getServletContext();
		
		// 요청횟수 totalCount
		application.setAttribute("totalCount", 0);
	}
	
	private void addCount() {
		int totalCount = (Integer) application.getAttribute("totalCount");
		totalCount++;
		application.setAttribute("totalCount", totalCount);
		
		System.out.println("\"/api/user/*\" 요청횟수: " + totalCount);
	}
	
	@Override
	public void destroy() {
		System.out.println("destroy() 호출됨");
		// 정리 작업..
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() 호출됨");
		addCount();
		
		// 레코드 한개 조회
		// http://localhost:8090/TODO/api/user?category=one&userName=hong
		
		// 레코드 전체(여러개) 조회 
		// http://localhost:8090/TODO/api/user?category=list
		
		String category = request.getParameter("category");
		
		String strJson = "";
		if (category.equals("one")) {
			strJson = processGetOne(request);
		} else if (category.equals("list")) {
			strJson = processGetList(request);
		}
		
		sendResponse(strJson, response);
	} // doGet
	

	private void sendResponse(String strJson, HttpServletResponse response) throws IOException {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(strJson);
		//out.flush();
		out.close();
	} // sendResponse
	
	
	
	private String processGetOne(HttpServletRequest request) {
		
		String userName = request.getParameter("userName");
		
		User user = userDao.getUserByUserName(userName);
		// XML 또는 JSON 문자열로 응답을 줌
		
		UserOneResult userOneResult = new UserOneResult();
		if (user != null) {
			userOneResult.setHasResult(true);
			userOneResult.setUser(user);
		} else {
			userOneResult.setHasResult(false);
		}
		
		// Gson을 이용해서 userOneResult 자바객체를 JSON 형식의 문자열로 변환하기
		String strJson = gson.toJson(userOneResult);
		System.out.println("strJson : " + strJson);
		return strJson;
	} // processGetOne
	
	
	private String processGetList(HttpServletRequest request) {
		
		List<User> userList = userDao.getAllUsers();
		
		UserListResult userListResult = new UserListResult();
		
		if (userList.size() > 0) {
			userListResult.setHasResult(true);
			userListResult.setUserList(userList);
			userListResult.setTotalCount(userList.size());
		} else { // userList.size() == 0
			userListResult.setHasResult(false);
			userListResult.setTotalCount(userList.size());
		}
		
		String strJson = gson.toJson(userListResult);
		System.out.println("strJson : " + strJson);
		return strJson;
	} // processGetList
	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost() 호출됨");
		addCount();
		
		request.setCharacterEncoding("utf-8"); // 한글처리
		
		BufferedReader reader = request.getReader(); // 문자 입력스트림 가져오기

		User user = gson.fromJson(reader, User.class); // JSON 문자열로부터 User 객체로 변환하기
		System.out.println(user.toString());
		
		userDao.registerUser(user); // 신규회원 추가
		
		UserResult userResult = new UserResult();
		userResult.setSuccess(true);
		
		String strJson = gson.toJson(userResult);
		
		sendResponse(strJson, response);
	} // doPost
	

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPut() 호출됨");
		addCount();
		
		request.setCharacterEncoding("utf-8"); // 한글처리
		
		String category = request.getParameter("category");
		System.out.println("category : " + category);
		
		if (category.equals("modify")) {
			updateUser(request, response);
			
		} else if (category.equals("password")) {
			updateUserPassword(request, response);
		}
	} // doPut
	
	
	private void updateUserPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		userDao.updatePasswordById(pwd, id); // 회원 아이디에 해당하는 비밀번호 수정
		
		UserResult userResult = new UserResult();
		userResult.setSuccess(true);
		
		String strJson = gson.toJson(userResult);
		
		sendResponse(strJson, response);
	} // updateUserPassword
	

	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader reader = request.getReader(); // 문자 입력스트림 가져오기

		User user = gson.fromJson(reader, User.class); // JSON 문자열로부터 User 객체로 변환하기
		System.out.println(user.toString());
		
		userDao.update(user); // 회원정보 수정
		
		UserResult userResult = new UserResult();
		userResult.setSuccess(true);
		
		String strJson = gson.toJson(userResult);
		
		sendResponse(strJson, response);
	} // updateUser
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doDelete() 호출됨");
		addCount();
		
		request.setCharacterEncoding("utf-8"); // 한글처리
		
		String userName = request.getParameter("userName");
		
		userDao.delete(userName); // userName에 해당하는 유저 삭제하기
		
		UserResult userResult = new UserResult();
		userResult.setSuccess(true);
		
		String strJson = gson.toJson(userResult);
		
		sendResponse(strJson, response);
	} // doDelete
	
} // class UserRestServlet 







