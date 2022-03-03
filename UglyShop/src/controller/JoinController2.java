package controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.sql.DataSource;

import beans.Farmer;
import dao.FarmerDAO;


@WebServlet("/joinFarmer")
public class JoinController2 extends HttpServlet {
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("join/join2.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Farmer farmer = new Farmer();
		farmer.setFarmID(request.getParameter("farmID"));
		farmer.setFarmPassword(request.getParameter("farmPassword"));
		farmer.setFarmName(request.getParameter("farmName"));
		farmer.setFarmAdd(request.getParameter("farmAdd"));
		farmer.setFarmTel(request.getParameter("farmTel"));

		int exist = farmerDao.existID(request.getParameter("farmID")); // dao의 메소드를 이용해 userID가 중복인지 확인

		if (exist == 1) {
			request.setAttribute("message", "e1"); // 아이디 중복 메시지 출력

			request.getRequestDispatcher("join/joinFarm.jsp").forward(request, response);
		} else {
			String password = request.getParameter("farmPassword");
			String repassword = request.getParameter("farmPassword2");

			if (!password.equals(repassword)) {
				request.setAttribute("message", "p");
				request.getRequestDispatcher("join/joinFarm.jsp").forward(request, response);
			} else {
				int result = farmerDao.join(farmer);

				if (result == 1) {
					System.out.println("회원가입 성공"); // 회원가입 성공

					RequestDispatcher dispatcher = request.getRequestDispatcher("login/loginFarm.jsp");
					dispatcher.forward(request, response);
				} else {
					request.setAttribute("message", "r-1"); // 회원가입 실패
					request.getRequestDispatcher("join/joinFarm.jsp").forward(request, response);
				}
			}

		}
	}
}
