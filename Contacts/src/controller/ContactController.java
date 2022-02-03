package controller;

import java.io.IOException;
//import java.util.List;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import dao.ContactDao;
import model.Contact;

@WebServlet("/contact")
public class ContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ContactDao contactDao;

	@Resource(name = "jdbc/demo")
	private DataSource dataSource;

	public void init() throws ServletException {
		super.init();
		contactDao = new ContactDao(dataSource);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		// 파라메터가 cmd값을 읽어서 액션으로 저장하는데 만약 값이 null이면 "list"로 대처(cmd는 action 파라메터 변수명)
		// 삼항연산자 사용 : null이 아니면 참 ? 거짓이면 list 출력
		String action = req.getParameter("cmd") != null ? req.getParameter("cmd") : "list";

		switch (action) {
		case "post": // 새로 입력 저장
			save(req, res);
			break;
		case "edit": // 수정하기 창을 보여줌
			edit(req, res);
			break;
		case "update": // 실제 수정하기
			update(req, res);
			break;
		case "del": // 삭제하기
			delete(req, res);
			break;
		default: // 전체 연락처를 화면에 테이블로 표시
			list(req, res);
			break;
		}

	}

	private void list(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<Contact> contacts = contactDao.findAll(); // DB모든 연락처 가져오기
		req.setAttribute("contacts", contacts);
		
		RequestDispatcher rd= req.getRequestDispatcher("contact/list.jsp");
		rd.forward(req, res); // request를 유지하면서 list.jsp로 이동
	}

	private void delete(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub

	}

	private void update(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub

	}

	private void edit(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub

	}

	private void save(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Contact contact = new Contact();
		contact.setName(req.getParameter("name"));
		contact.setEmail(req.getParameter("email"));
		contact.setPhone(req.getParameter("phone"));
		
		boolean isSaved = contactDao.save(contact); // 결과가 참이면 DB에 저장
		
		if(isSaved) {
			System.out.println("입력완료!");
		}
		
		list(req, res); // 다시 리스트 화면 출력
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
