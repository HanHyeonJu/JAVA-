package todoApp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import todoApp.dao.LoginDao;
import todoApp.model.LoginBean;


@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private LoginDao loginDao; //로그인 체크 Dao객체
	
	@Override
	public void init() {
		loginDao = new LoginDao();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
		dispatcher.forward(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); //입력받을때 한글
		response.setContentType("text/html;charset=UTF-8"); //출력할때 한글
		// ID 패스워드를 파라메터로 입력받기
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		LoginBean loginBean = new LoginBean();
		loginBean.setUsername(username);
		loginBean.setPassword(password);
		
		if( loginDao.validate(loginBean) ) { //계정 있음 로그인 됨 => 할일 페이지로 forward
			System.out.println("로그인 성공!");
			HttpSession session = request.getSession();
			session.setAttribute("username", username); //로그인 한 유저네임을 세션에 저장
			RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-list.jsp");
			dispatcher.forward(request, response);			
		} 
		else { // 계정 없음 로그인 실패
			System.out.println("로그인 실패!");
			request.setAttribute("user", username);   //유저네임은 다시 보냄
			request.setAttribute("message", "로그인 실패!");
			// 로그인 실패 내용을 포워드로 다시 로그인 페이지에 보여주기
			RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
			dispatcher.forward(request, response);				
		}
		
	}

}
