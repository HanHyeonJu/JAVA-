package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		String page = null;
		if(action == null) {
			page = "/error.jsp";
		}else if(action.equals("login")) {
			page = "/login.jsp";
		}else if(action.equals("about")) {
			page = "/about.jsp";
		}else{
			page = "/error.jsp";
		}
		
		// MVC2에서 변수page를 요청받아서 forward로 맞는 페이지로 이동시켜줌 
		getServletContext().getRequestDispatcher(page).forward(request, response);
	}

}
