package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import beans.Farmer;
import dao.FarmerDAO;



@WebServlet("/UpdateContoller2")
public class UpdateController2 extends HttpServlet {
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
		response.setContentType("text/html; charset=UTF-8");
		String action = request.getParameter("action");

		if (action == null) {
			request.getRequestDispatcher("/main2.jsp").forward(request, response);
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

		if (action.equals("docheck")) {
			String farmID = request.getParameter("farmID");
			String farmPassword = request.getParameter("farmPassword");

			Farmer farmer = new Farmer();

			farmer.setFarmID(farmID);
			farmer.setFarmPassword(farmPassword);

			int result = farmerDao.login(farmID, farmPassword);

			if (result == 1) {
				request.getRequestDispatcher("update/farmerUpdate.jsp").forward(request, response);
			} else if (result == 0) {
				request.setAttribute("message", "0");

				request.getRequestDispatcher("update/farmerPassword.jsp").forward(request, response);
			}
		} else if (action.equals("doupdate")) {
			Farmer farmer = new Farmer();
			farmer.setFarmID(request.getParameter("farmID"));
			farmer.setFarmPassword(request.getParameter("farmPassword"));
			farmer.setFarmName(request.getParameter("farmName"));
			farmer.setFarmAdd(request.getParameter("farmAdd"));
			farmer.setFarmTel(request.getParameter("farmTel"));

			boolean update = farmerDao.farmerupdate(farmer);

			if (update) {
				request.getRequestDispatcher("update/farmerSuccess.jsp").forward(request, response);
			}

		}
	}
}