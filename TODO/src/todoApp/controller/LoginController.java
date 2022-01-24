package todoApp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import todoApp.dao.LoginDao;
import todoApp.model.LoginBean;


@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private LoginDao loginDao; // 로그인 체크 Dao 객체
    
    @Override
    public void init() {
    	loginDao = new LoginDao(); 
    }
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// post로 모든 내용을 처리하긴 하지만 get으로 넘어오는 경우가 생길 수도 있으니까 약간 대비용 느낌으로 해둔 듯함(별 내용 없다고 함)
		RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
		dispatcher.forward(request, response);	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 입력받을 때 한글
		response.setContentType("text/html;charset=UTF-8"); // 출력할 때 한글
		// ID, 패스워드를 파레터로 입력받기
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		LoginBean loginBean = new LoginBean();
		
		loginBean.setUsername(username);
		loginBean.setPassword(password);
		
		if(loginDao.validate(loginBean)) { // DB에 계정이 있는 경우 => 로그인 성공 => 할 일 페이지로 forward
			System.out.println("로그인 성공!");
			
			HttpSession session = request.getSession();
			session.setAttribute("username", username); // 로그인 한 유저네임을 세션에 저장(패스워드는?)
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-list.jsp");
			dispatcher.forward(request, response);
		}
		else {// 계정 없음, 로그인 실패
			System.out.println("로그인 실패!"); // 콘솔창에 출력됨
			request.setAttribute("user", username); // 유저네임은 다시 보내서 로그인에 실패해서 다시 로그인.jsp로 들어가도 유저네임은 확인가능
			request.setAttribute("message", "로그인 실패!");
			// request는 요청이 끝나면 사라지기 때문에 다시 로그인 페이지로 들어가면 적혀있던 유저이름과 메시지는 보이지 않음
			// 로그인 실패 내용을 포워드로 다시 로그인 페이지에 보내주기
			RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
			dispatcher.forward(request, response);	 
		}
	}

}
