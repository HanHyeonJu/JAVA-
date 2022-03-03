package controller;

import java.io.IOException;
//import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.sql.DataSource;

import beans.User;
import dao.UserDAO;

@WebServlet("/JoinUser")
public class JoinController extends HttpServlet {
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("join/joinUser.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		User user = new User();
		user.setUserID(request.getParameter("userID"));
		user.setUserPassword(request.getParameter("userPassword"));
		user.setUserName(request.getParameter("userName"));
		user.setUserAdd(request.getParameter("userAdd"));
		user.setUserTel(request.getParameter("userTel"));
		
		//PrintWriter out = response.getWriter();
		int exist = userDao.existID(request.getParameter("userID")); // dao의 메소드를 이용해 userID가 중복인지 확인
		

		if (exist == 1) {
			request.setAttribute("message", "e1"); // 아이디 중복 메시지 출력

			request.getRequestDispatcher("join/joinUser.jsp").forward(request, response);
		} else {
			String password = request.getParameter("userPassword");
			String repassword = request.getParameter("userPassword2");

			if (!password.equals(repassword)) {
				request.setAttribute("message", "p");
				request.getRequestDispatcher("join/joinUser.jsp").forward(request, response);
			} else {
				int result = userDao.join(user);

				if (result == 1) {
					System.out.println("회원가입 성공"); // 회원가입 성공

					RequestDispatcher dispatcher = request.getRequestDispatcher("login/loginUser.jsp");
					dispatcher.forward(request, response);
				} else {
					request.setAttribute("message", "r-1"); // 회원가입 실패
					request.getRequestDispatcher("join/joinUser.jsp").forward(request, response);
				}
			}

		}
	}
}
