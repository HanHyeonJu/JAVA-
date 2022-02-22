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

import beans.User;
import dao.UserDAO;

@WebServlet("/UpdateContoller")
public class UpdateController extends HttpServlet {
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
		response.setContentType("text/html; charset=UTF-8");
		String action = request.getParameter("action");

		if (action == null) {
			request.getRequestDispatcher("/main.jsp").forward(request, response);
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
			String userID = request.getParameter("userID");
			String userPassword = request.getParameter("userPassword");

			User user = new User();

			user.setUserID(userID);
			user.setUserPassword(userPassword);

			int result = userDao.login(userID, userPassword);

			if (result == 1) {
				request.getRequestDispatcher("update/userUpdate.jsp").forward(request, response);
			} else if (result == 0) {
				request.setAttribute("message", "0");

				request.getRequestDispatcher("update/userPassword.jsp").forward(request, response);
			}
		} else if (action.equals("doupdate")) {
			User user = new User();
			user.setUserID(request.getParameter("userID"));
			user.setUserPassword(request.getParameter("userPassword"));
			user.setUserName(request.getParameter("userName"));
			user.setUserAdd(request.getParameter("userAdd"));
			user.setUserTel(request.getParameter("userTel"));

			boolean update = userDao.userupdate(user);

			if (update) {
				request.getRequestDispatcher("update/updateSuccess.jsp").forward(request, response);
			}

		}
	}
}