package controller;

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

import beans.User;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/webshop")
	private DataSource ds; // 데이터소스 ds로 DB연결
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		
		if(action == null) {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		else if(action.equals("login")) {
			request.setAttribute("email", "");
			request.setAttribute("password", "");
			request.setAttribute("message", "");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		else if(action.equals("createaccount")) {
			request.setAttribute("email", "");
			request.setAttribute("password", "");
			request.setAttribute("message", "");
			request.getRequestDispatcher("/createaccount.jsp").forward(request, response);
		}else {
			out.println("없는 액션입니다.");
			return;
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String action = request.getParameter("action");
		
		if(action == null) {
			out.println("알수 없는 요청입니다.");
			return;
		}
		
		// Connection 객체가 없으면 DB를 사용할 수 없음
		Connection conn = null;
		
		try {
			conn = ds.getConnection(); // DB연결
		}catch(SQLException e){
			out.println("DB에 연결할 수 없습니다.");
			return;
		} 
	
		Account account = new Account(conn); // 어카운트 클래스 생성
		
		if(action.equals("dologin")) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			request.setAttribute("email", email);
			request.setAttribute("password", ""); // 보안의 이유로 password는 공백처리
			
			try {
				if(account.login(email, password)) {
					request.getRequestDispatcher("/loginsuccess.jsp").forward(request, response);
				}else {
					request.setAttribute("message", "아이디 또는 패스워드가 틀립니다.");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace(); // 에러가 왜 발생했는지 console창에 이유를 출력해주게 함
				request.setAttribute("message", "DB 에러 발생");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		}
		else if(action.equals("createaccount")) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String repeatPassword = request.getParameter("repeatpassword");
			request.setAttribute("email", email); // 이메일 정보를 request에 저장(?)
			
			//확인패스워드가 입력패스워드와 같지 않을경우와 입력한 아이디와 패스워드가 유효성 검사에 불합격했을 경우
			if(!password.equals(repeatPassword)) {
				request.setAttribute("message", "패스워드가 틀립니다.");
				request.getRequestDispatcher("/createaccount.jsp").forward(request, response);
			}
			else {
				// 유효성검사를 위해 User클래스를 객체로 불러옴				
				User user = new User(email, password);
				if(!user.validate()) {
					// 이메일 또는 패스워드가 validate()형식에 맞지 않음(유효성검사 탈락)
					request.setAttribute("message", user.getMessage());
					request.getRequestDispatcher("/createaccount.jsp").forward(request, response);
				}
				else { // 유효성 검사 통과 => 이메일 중복확인 => 새 계정 만들기
					try {
						if(account.exists(email)) {
							//같은 이메일이 DB에 있을경우
							request.setAttribute("message", "이미 가입된 이메일이 있습니다.");
							request.getRequestDispatcher("/createaccount.jsp").forward(request, response);
						}
						else {
							// 새계정을 만든다
							account.create(email, password);
							request.getRequestDispatcher("/createsuccess.jsp").forward(request, response);
						}
					} catch (SQLException e) { // SQL 에러가 발생했을 경우
						request.setAttribute("message", "SQL 에러 발생");
						request.getRequestDispatcher("/error.jsp").forward(request, response);
					}
				}
			}
		}
		try {
			conn.close(); // 실제로는 conn을 닫는 것 대신에 커넥션을 커넥션 풀로 보냄, 다른 DB요청이 왔을 때 다른 DB를 꺼낼 수 있도록 하기 위함
		} catch (SQLException e) {
			out.println("DB 연결 종료 중 에러!");
		}
	}
	
}
