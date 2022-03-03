package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import beans.Farmer;
import beans.User;
import dao.FarmerDao;
import dao.UserDao;

@WebServlet("/member")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private FarmerDao farmerDao;
	private UserDao userDao;
	
	@Resource(name = "jdbc/shop")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		farmerDao = new FarmerDao(dataSource);
		userDao = new UserDao(dataSource);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF8");
		String action = request.getParameter("cmd");
		
		switch (action) {
		case "deleteFarmer": // 농민 삭제하기
			deleteFarmer(request, response);
			break;
		case "deleteUser": // 고객 삭제하기
			deleteUser(request, response);
			break;
		case "farmerList": // 농민 목록 출력
			try {
				farmerList(request, response);
			} catch (SQLException | ServletException | IOException e) {
				e.printStackTrace();
				System.out.println("controller : 농민 목록 출력 에러");
			}
			break;
		case "userList": // 고객 목록 출력
			try {
				userList(request, response);
			} catch (SQLException | ServletException | IOException e) {
				e.printStackTrace();
				System.out.println("controller : 고객 목록 출력 에러");
			}
			break;
		case "memberList": // 고객 삭제하기
			memberList(request, response);
			break;
		default: // 요청 주소가 기본 또는 잘못 되었을 경우 memberList로 이동
			memberList(request, response);
			break;
		} // switch문 끝
	}

	private void memberList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("member/memberList.jsp");
		
	}

	private void userList(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		List<User> userList = userDao.findAllUser();
		
		request.setAttribute("userList", userList);
		
		RequestDispatcher rd = request.getRequestDispatcher("member/memberList.jsp");
		rd.forward(request, response);
	}

	private void farmerList(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		List<Farmer> farmerList = farmerDao.findAllFarmer();
		
		request.setAttribute("farmerList", farmerList);
		
		RequestDispatcher rd = request.getRequestDispatcher("member/memberList.jsp");
		rd.forward(request, response);
		
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userID = (String)(request.getParameter("userID"));
		userDao.deleteUser(userID);
		
		response.sendRedirect("member?cmd=userList");
		
	}

	private void deleteFarmer(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String farmID = (String)(request.getParameter("farmID"));
		farmerDao.deleteFarmer(farmID);
		
		response.sendRedirect("member?cmd=farmerList");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
