package gui;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// HttpServlet을 상속한 클래스 => 서블릿
@WebServlet("/hi")
public class Hijsp extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get은 url주소 요청시
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<b>Hi!!!</b>");
		out.println("</html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// form요청시 ex) 로그인(주소창에 표시되지 않음)
		super.doPost(req, resp);
	}
	
	

}
