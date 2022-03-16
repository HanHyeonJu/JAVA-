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
import dao.OrderDAO;
import dao.ProductDAO;
import beans.Cart;
import beans.Product;
import utills.Json;

@WebServlet("/order")
public class OrderController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private OrderDAO orderDao;

	@Resource(name = "jdbc/shop")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		orderDao = new OrderDAO(dataSource);
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

		RequestDispatcher rd = req.getRequestDispatcher("orders/ordersList.jsp"); // !!!jsp이름 수정하기!!!
		rd.forward(req, resp); // 리퀘스트를 유지하면서 ordersList.jsp페이지로 이동

	}

	private void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8"); // 입력받을 때 한글
		resp.setContentType("text/html;charset=UTF-8"); // 출력할 때 한글
		

		HashMap<Integer, Cart> cartList = null;
		cartList = new HashMap<>();

		HttpSession session = req.getSession();
		cartList = (HashMap<Integer, Cart>) session.getAttribute("cartList"); // 카트리스트 세션 받아옴
		Order order = null;
		
	
		for (Cart cart : cartList.values()) {
			//System.out.println(cart); 
			order = new Order();
			order.setUserID(cart.getUserID());
			order.setProdID(cart.getProdID());
			order.setProdName(cart.getProdName());
			order.setProdPrice(cart.getProdPrice());
			order.setOrderQuantity(cart.getOrderQuantity());
			order.setFarmID(cart.getFarmID());

			boolean isSaved = orderDao.save(order); // cart객체로부터 받은 cartList의 정보들을 받음
			boolean userSaved = orderDao.userSave(order); // 배송지 정보 전달을 위해 유저의 정보들을 order에 저장시킴
			
			order = orderDao.findByUserId(cart.getUserID()); // 한 명의 유저의 정보를 찾음
			
			session.removeAttribute("cartList"); // 구매완료된 cartList세션 삭제
		} // for문 끝
		
		req.setAttribute("order", order);
		RequestDispatcher rd = req.getRequestDispatcher("order/orderCheck.jsp"); // 오더체크.jsp에서 구매를 한 고객에게 배송지 정보를 확인시켜줌 
		rd.include(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
