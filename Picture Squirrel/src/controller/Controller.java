package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/gallery")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Map<String, String> actionMap = new HashMap<>();
	
	public Controller() {
		// 컨트롤로 생성자 (시작할때 한번 실행)
		actionMap.put("home", "/home.jsp");
		actionMap.put("image", "/image.jsp");
		actionMap.put("rate", "/image.jsp"); //점수가 업데이트 된 화면
		
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		//만약 액션 파라메터가 없거나 actionMap에 없는 action이면 => home.jsp
		if(action == null || !actionMap.containsKey(action)) action = "home";
		
		request.getRequestDispatcher(actionMap.get(action)).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// method방식이 post인 rate가 Controller의 method에 왔을 때 액션이 rate면 image.jsp로 이동되기 위해서 dopost에 doget을 사용하여 액션이 rate면 actionMap의 image.jsp로 이동되게 함
		doGet(request, response);
	}

}
