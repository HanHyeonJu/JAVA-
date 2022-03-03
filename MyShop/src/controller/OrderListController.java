package controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import beans.Order;
import dao.OrderListDao;

@WebServlet("/managerOrderlist")
public class OrderListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private OrderListDao orderListDao;
	
	@Resource(name = "jdbc/shop")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		orderListDao = new OrderListDao(dataSource);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF8");
		String action = request.getParameter("cmd");
		
		switch (action) {
		case "delete": // 삭제하기
			delete(request, response);
			break;
		case "showEdit": // 수정하기 창을 보여줌
			showEditForm(request, response);
			break;
		case "update": // 실제 수정하기
			update(request, response);
			break;
		case "list": // 주문 내역 전체 출력
			ordersList(request, response);
			break;
		default: // 요청 주소가 기본 또는 잘못 되었을 경우 ordersList로 이동
			ordersList(request, response);
			break;
		} // switch문 끝
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int orderID = Integer.parseInt(request.getParameter("orderID"));//orderID를 받음
		Order oneOrder = orderListDao.findOrderById(orderID); // 1개의 orderList를 가지고옴
		
		request.setAttribute("order", oneOrder);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("orders/showOrderEdit.jsp");
		dispatcher.forward(request, response); // 리퀘스트를 유지하면서 showOrderEdit.jsp페이지로 이동
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int orderID = Integer.parseInt(request.getParameter("orderID"));
		orderListDao.delete(orderID);
		
		response.sendRedirect("managerOrderlist?cmd=list");
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {	
		int orderID = Integer.parseInt(request.getParameter("orderID"));
		boolean farmCheck = Boolean.parseBoolean(request.getParameter("farmCheck"));
		int trackNum = Integer.parseInt(request.getParameter("trackNum"));
		String is_status = request.getParameter("is_status");
		
		System.out.println("orderID :" + orderID);
		System.out.println("farmCheck :" + farmCheck);
		System.out.println("trackNum :" + trackNum);
		System.out.println("is_status :" + is_status);
		
		orderListDao.update(orderID, farmCheck, trackNum, is_status);
		
		response.sendRedirect("managerOrderlist?cmd=list");
	}

	private void ordersList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Order> ordersList = orderListDao.findAll(); // DB의 모든 주문내역 가져오기
		
		request.setAttribute("ordersList", ordersList);
		
		RequestDispatcher rd = request.getRequestDispatcher("orders/ordersList.jsp");
		rd.forward(request, response); // 리퀘스트를 유지하면서 ordersList.jsp페이지로 이동
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
