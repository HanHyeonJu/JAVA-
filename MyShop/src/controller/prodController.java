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

import beans.Product;
import dao.ProductDao;
//import dao.ReplyDao;
//import dao.ReviewDao;

@WebServlet("/managerProduct")
public class prodController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductDao prodDao;

	@Resource(name = "jdbc/shop")
	private DataSource datasource;

	@Override
	public void init() throws ServletException {
		prodDao = new ProductDao(datasource);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		List<Product> prod = prodDao.findAll();
//		prod.forEach(product -> System.out.println(product.toString()));	// 전체출력 테스트용
//		System.out.println(prodDao.find(1));

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("cmd");

		try {
			switch (action) {
			case "find": // 상품 하나의 상세정보 출력
				find(request, response);
				break;
			case "del": // 삭제
				delete(request, response);
				break;
			case "list": // 전체 상품을 화면에 테이블로 표시
				list(request, response);
				break;
			case "save": // 전체 상품을 화면에 테이블로 표시
				save(request, response);
				break;
			case "update": // 전체 상품을 화면에 테이블로 표시
				update(request, response);
				break;
			default:
				list(request, response);
				break;
			}
		} finally {
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void save(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한 상품의 상세정보를 출력
		int prodId = Integer.parseInt(request.getParameter("prodID"));

		Product product = prodDao.find(prodId);

		if (product != null) {
			request.setAttribute("product", product);
			RequestDispatcher rd = request.getRequestDispatcher("product/productDetail.jsp");
			rd.forward(request, response);
		}

	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 상품 전체출력
		List<Product> prodList = prodDao.findAll();
		request.setAttribute("prodList", prodList);
		RequestDispatcher rd = request.getRequestDispatcher("product/productList.jsp");
		rd.forward(request, response);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		// 상품 삭제

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
