package controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;


@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 각각의 객체들을 다른 scope로 전달
		User user1 = new User("Bob",1);
		User user2 = new User("Mike",2);
		User user3 = new User("Sue",3);
		
		// user1객체는 request scope로 전달
		request.setAttribute("user1", user1);
		
		// user2객체는 session scope로 전달
		HttpSession session = request.getSession();
		session.setAttribute("user2", user2);
		
		// user3는 Context로 전달(Context는 프로그램 자체의 객체이기 때문에 프로그램이 실행되는 동안은 유지된다)
		ServletContext context = getServletContext();
		context.setAttribute("user3", user3);
		
		// user1, user2, user3 객체를 각각 scope에 맞게 forward방식으로 receiveObjects.jsp로 데이터들을 요청
		request.getRequestDispatcher("/receiveObjects.jsp").forward(request, response);
	}
}
