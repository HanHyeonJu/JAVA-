package demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Cookies")
public class Cookies extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		
		//브라우저에서 쿠키들을 가져옴
		Cookie[] cookies = request.getCookies();
		
		//쿠키가 있으면 전체 출력
		if(cookies == null) {
			out.println("No cookies found<br/>");
		}else {
			for(Cookie c : cookies) {
				String name = c.getName();
				String value = c.getValue();
				
				out.println(name + "=" + value + "<br/>");
			}
					
		}
		
		Cookie cookie = new Cookie("user", "Vicky"); // 쿠키 생성
		
		cookie.setMaxAge(300); // cookie의 생존기간(300초로 설정)
		
		response.addCookie(cookie);
		
		out.println("Cookie set.<br/>"); 
		
		out.println("</html>");
		
		// 맨 처음에 출력할 때는 쿠키가 없다고 뜸
	}
}
