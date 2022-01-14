package demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/DatasourceDemo")
public class DatasourceDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Resource(name = "jdbc/webshop")
	private DataSource ds; // 데이터소스 ds로 DB연결
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		Connection conn = null;
		
		try {
			conn = ds.getConnection(); // DB연결
		}catch(SQLException e){
			out.println("DB에 연결할 수 없습니다.");
			return;
		} 
		
		out.println("DB 연결 테스트 완료!");
		
		try {
			conn.close(); // 실제로는 conn을 닫는 것 대신에 커넥션을 커넥션 풀로 보냄, 다른 DB요청이 왔을 때 다른 DB를 꺼낼 수 있도록 하기 위함
		} catch (SQLException e) {
			out.println("DB 연결 종료 중 에러!");
		}
	}

}
