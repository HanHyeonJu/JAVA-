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

import beans.Manager;
import dao.ManagerDAO;


@WebServlet("/managerController")
public class LoginController3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ManagerDAO managerDao;

	@Resource(name = "jdbc/shop")
	private DataSource dataSource;

	public void init() throws ServletException {
		super.init();
		managerDao = new ManagerDAO(dataSource);
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
			request.setAttribute("manID", "");
			request.setAttribute("manPassword", "");
			request.setAttribute("message", "");
			request.getRequestDispatcher("/login3.jsp").forward(request, response);
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
			String manID = request.getParameter("manID");
			String manPassword = request.getParameter("manPassword");
			
			Manager manager = new Manager();
			
			manager.setManID(manID);
			manager.setManPassword(manPassword);

			int result = managerDao.login(manID, manPassword);

			if (result == 1) {
				HttpSession session = request.getSession();
				session.setAttribute("manID", manID);

				RequestDispatcher dispatcher = request.getRequestDispatcher("main3.jsp");
				dispatcher.forward(request, response);
			} else if (result == 0) {
				request.setAttribute("manID", manID); // 패스워드만 틀린 경우이기 때문에 userID는 보이도록 남겨줌
				request.setAttribute("message", "0");
				
				request.getRequestDispatcher("login/login3.jsp").forward(request, response);
			} else if (result == -1) {
				request.setAttribute("manID", manID);
				request.setAttribute("message", "-1");
				
				request.getRequestDispatcher("login/login3.jsp").forward(request, response);
			}

		}
		

	}

}
