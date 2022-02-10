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

import beans.Farmer;
import dao.FarmerDAO;


@WebServlet("/farmerController")
public class LoginController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private FarmerDAO farmerDao;

	@Resource(name = "jdbc/shop")
	private DataSource dataSource;

	public void init() throws ServletException {
		super.init();
		farmerDao = new FarmerDAO(dataSource);
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
			request.setAttribute("farmID", "");
			request.setAttribute("farmPassword", "");
			request.setAttribute("message", "");
			request.getRequestDispatcher("/login2.jsp").forward(request, response);
		}
		// else if(action.equals("createaccount")) {
		// request.setAttribute("email", "");
		// request.setAttribute("password", "");
		// request.setAttribute("message", "");
		// request.getRequestDispatcher("/createaccount.jsp").forward(request,
		// response);
		// }
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
			String farmID = request.getParameter("farmID");
			String farmPassword = request.getParameter("farmPassword");
			
			Farmer farmer = new Farmer();
			
			farmer.setFarmID(farmID);
			farmer.setFarmPassword(farmPassword);

			int result = farmerDao.login(farmID, farmPassword);

			if (result == 1) {
				HttpSession session = request.getSession();
				session.setAttribute("farmID", farmID);

				RequestDispatcher dispatcher = request.getRequestDispatcher("main2.jsp");
				dispatcher.forward(request, response);
			} else if (result == 0) {
				request.setAttribute("farmID", farmID); // 패스워드만 틀린 경우이기 때문에 userID는 보이도록 남겨줌
				request.setAttribute("message", "0");
				
				request.getRequestDispatcher("login/login3.jsp").forward(request, response);
			} else if (result == -1) {
				request.setAttribute("farmID", farmID);
				request.setAttribute("message", "-1");
				
				request.getRequestDispatcher("login/login3.jsp").forward(request, response);
			}

		}
		

	}

}
