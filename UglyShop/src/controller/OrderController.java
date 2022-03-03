package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

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
import beans.Order;
import beans.User;
import dao.OrderDao;
import dao.ProductDao;
import beans.Cart;
import beans.Product;
import utills.Json;

@WebServlet("/order")
public class OrderController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private OrderDao orderDao;

	@Resource(name = "jdbc/shop")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		orderDao = new OrderDao(dataSource);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("cmd");
		
		switch (action) {
		case "save": // order테이블에 주문 저장하기
			save(req, resp);
			break;
		case "list": // 주문 내역 전체 출력
			ordersList(req, resp);
			break;
		default: // 요청 주소가 기본 또는 잘못 되었을 경우 ordersList로 이동
			ordersList(req, resp);
			break;
		}
		
	
	}
	
	
	private void ordersList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Order> orders = orderDao.findAll(); // DB의 모든 주문내역 가져오기
		
		req.setAttribute("orders", orders);
		
		RequestDispatcher rd = req.getRequestDispatcher("orders/ordersList.jsp"); //!!!jsp이름 수정하기!!!
		rd.forward(req, resp); // 리퀘스트를 유지하면서 ordersList.jsp페이지로 이동
		
	}

	private void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Order order = new Order();
		User user = orderDao.findByUserId(req.getParameter("userId"));
		Farmer farmer = orderDao.findByFarmId(req.getParameter("farmID"));
		
		order.setOrderID(Integer.parseInt(req.getParameter("orderId")));
		order.setCartID(Integer.parseInt(req.getParameter("cartId"))); 
		order.setUserID(req.getParameter("userId")); 
		order.setUserAdd(user.getUserAdd());
		order.setUserTel(user.getUserTel());
		order.setProdID(Integer.parseInt(req.getParameter("prodId")));
		order.setProdName(req.getParameter("prodName"));
		order.setProdPrice(Integer.parseInt(req.getParameter("prodPrice")));
		order.setOrderQuantity(Integer.parseInt(req.getParameter("orderQuantity")));
		
//		prodQuantity 재고, orderQuantity 주문수량. Update 메소드로 하기!!!!
//		order.setProdQuantity(Integer.parseInt(req.getParameter("prodQuantity")));
		
		order.setTotalPrice(Integer.parseInt(req.getParameter("totalPrice")));
		order.setFarmID(req.getParameter("farmID"));
		order.setFarmTel(farmer.getFarmTel());
		order.setFarmCheck(Boolean.parseBoolean(req.getParameter("farmCheck")));
		order.setTrackNum(Integer.parseInt(req.getParameter("trackNum")));
		order.setIs_status(req.getParameter("is_status"));
		
		boolean isSaved = orderDao.save(order); // 참이면 저장완료

		if (isSaved) {
			System.out.println("order테이블에 등록 완료!");
			
			req.setAttribute("order", order);
			RequestDispatcher rd = req.getRequestDispatcher("order?cmd=list");
			rd.forward(req, resp);
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
