package gui;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloWorld
 */
@WebServlet("/hello") // <- �ְ� �ּ��ΰ���
public class HelloWorld extends HttpServlet { // http����� ���ִ� Ŭ������ HttpServlet
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloWorld() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��û�� �ּҰ� doget���� ����?? ��û�� �ּҰ� ����?
		// ���ؽ�Ʈ�н� = ������Ʈ��		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html;charset=UTF-8"); // �ѱۼ���(�� ���ָ� ������ ���� ???�̷� ������)
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<b>Hello World!!!</b><br>");
		out.println("<b>�ȳ��ϼ���!!!</b>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
