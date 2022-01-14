package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 현재 어플리케이션의 context 객체		
		ServletContext context = getServletContext();
		
	    // context객체에 저장된 hit를 가져옴
		Integer hits = (Integer) context.getAttribute("hits");
		
		if(hits == null) hits = 0; // 제일 처음에는 hits값이 저장되지 않은 상태이기 때문에 null값이 되고 그럴 때 초기값을 0으로 설정해줌
		else hits++; // 처음 값(null값)이 저장되어 있고 그 다음 값이라면 +1씩 늘어나도록 설정해줌
		
		//	context객체에 hits라는 변수를 setAttribute를 이용해서 저장	
		context.setAttribute("hits", hits);
		
		PrintWriter out = response.getWriter();
		out.println("Hits : "+hits);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
