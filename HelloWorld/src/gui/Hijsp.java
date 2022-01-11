package gui;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// HttpServlet�� ����� Ŭ���� => ����
@WebServlet("/hi")
public class Hijsp extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get�� url�ּ� ��û��
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<b>Hi!!!</b>");
		out.println("</html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// form��û�� ex) �α���(�ּ�â�� ǥ�õ��� ����)
		super.doPost(req, resp);
	}
	
	

}
