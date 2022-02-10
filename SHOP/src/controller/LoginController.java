package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import beans.User;
import dao.UserDAO;

@WebServlet("/userController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDAO userDao;

	@Resource(name = "jdbc/shop")
	private DataSource dataSource;

	public void init() throws ServletException {
		super.init();
		userDao = new UserDAO(dataSource);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 로그인, 회원가입 폼을 작성하지 않을 때 그냥 페이지 넘기는 용
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");

		if (action == null) {
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		} else if (action.equals("login")) {
			request.setAttribute("userID", "");
			request.setAttribute("userPassword", "");
			request.setAttribute("message", "");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		else {
			out.println("없는 액션입니다.");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 입력받을 때 한글
		response.setContentType("text/html;charset=UTF-8"); // 출력할 때 한글
		PrintWriter out = response.getWriter();

		String action = request.getParameter("action");

		if (action == null) {
			out.println("알수 없는 요청입니다.");
			return;
		}

		if (action.equals("dologin")) {
			String userID = request.getParameter("userID");
			String userPassword = request.getParameter("userPassword");
			
			User user = new User();
			
			user.setUserID(userID);
			user.setUserPassword(userPassword);

			int result = userDao.login(userID, userPassword);

			if (result == 1) {
				HttpSession session = request.getSession();
				session.setAttribute("userID", userID);

				RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
				dispatcher.forward(request, response);
			} else if (result == 0) {
				request.setAttribute("userID", userID); // 패스워드만 틀린 경우이기 때문에 userID는 보이도록 남겨줌
				request.setAttribute("message", "0");
				
				request.getRequestDispatcher("login/login.jsp").forward(request, response);
			} else if (result == -1) {
				request.setAttribute("userID", userID);
				request.setAttribute("message", "-1");
				
				request.getRequestDispatcher("login/login.jsp").forward(request, response);
			}

		}
		

	}

}
