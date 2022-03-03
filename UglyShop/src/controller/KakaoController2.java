package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import beans.Farmer;
import dao.FarmerDAO;


@WebServlet("/KakaoController2")
public class KakaoController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private FarmerDAO farmerDao;

	@Resource(name = "jdbc/shop")
	private DataSource dataSource;

	public void init() throws ServletException {
		super.init();
		farmerDao = new FarmerDAO(dataSource);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String farmID = request.getParameter("val");
		
		int exist = farmerDao.existID(farmID); // 결과가 존재하지 않는다면 -1, 존재한다면 1이 exist로 return
		
		if(exist == 1) {
			
			HttpSession session = request.getSession();
			session.setAttribute("farmID", farmID);
			
			out.print("1");
		} else {
			
			Farmer farmer = new Farmer();
			farmer.setFarmID(farmID);
			
			int save = farmerDao.KakaoSave(farmer);
			
			if(save == 1) {
			
			HttpSession session = request.getSession(); 
			session.setAttribute("farmID",farmID); 
			
			out.print("0");
			//response.sendRedirect("update/userUpdate.jsp");
			} else {
				System.out.println("카카오 로그인 실패");
				out.print("-1");
			}
			
		}
		
		
		
	}

}
