package demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/Connect")
public class Connect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8"); // 한글설정(서블릿으로 한글을 출력하려면 설정해줘야 함)
		PrintWriter out = response.getWriter();
		
		Connection conn = null; // Connection 객체 만들어주기
		
		try {
			// 0. 드라이버 로딩(없어도 된다고 하는 추세, 자동으로 로딩이 되는 경우도 있지만 꼭 필요한 경우도 있음)
			Class.forName("com.mysql.jdbc.Driver");
			// 1. DB연결
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop?useSSL=false", "root", "1234");
		}catch(SQLException e){
			out.println("DB에 연결할 수 없습니다.");
			return;
		} catch (ClassNotFoundException e) {
			out.println("드라이버를 찾을 수 없습니다.");
			return;
		}
		
		out.println("DB 연결 테스트 완료!");
		
		try {
			conn.close();
		} catch (SQLException e) {
			out.println("DB 연결 종료 중 에러!");
		}
	}

}
