package utills;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Contact;
//contactJson 객체를 out.print로 화편 페이지에 제인슨으로 전송하기 위한 클래스
public class Json {
	
	private ContactJson contactJson; // contactJson 객체
	private Gson gson; // gson 라이브러리 객체
	private PrintWriter out; // 출력객체
	private HttpServletResponse response; // 응답객체
	
	public Json(HttpServletResponse response) { // 생성자(response) => 응답 타입을 제이슨으로 출력하기 위함
		contactJson = new ContactJson(); // 새로운 contactJson 객체 생성
		gson = new Gson(); // 새로운 gson 객체 생성
		
		this.response = response;
		this.response.setContentType("application/json; charset=utf-8"); //응답 타입을 제이슨으로 설정
		
		try {
			out = response.getWriter(); // out객체를 입력받은 것을 응답해주는 객체로 생성
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	//동작 메서드
	// 1. 입력된 연락처를 json형태로 jsp로 보낼 때(수정 요청 시 그 수정 연락처의 내용을 응답으로 보냄)
	public void sendContact(Contact contact) {
		contactJson.setStatus(true); 
		contactJson.setContact(contact);
		
		sendResponse(gson.toJson(contactJson)); // 입력된 상태와 연락처를 모두 제이슨으로 변환해서 보냄
	}
	
	// 2. 상태와 메시지를 제이슨형태로 전송할 때(입력, 업데이트, 삭제 등은 메시지로 성공여부를 보낸다.)
	public void sendMessage(boolean status, String message) {
		contactJson.setStatus(status); // 상태 입력
		contactJson.setMessage(message); // 메시지 입력
		
		sendResponse(gson.toJson(contactJson)); // 상태, 메시지 제이슨으로 변환해서 보냄
	}
	// 3.  공통으로 출력할 때
	private void sendResponse(String jsonData) { // 변수명은 아무거나 해도 상관없음
		out.print(jsonData); // 제이슨으로 변환된 데이터들을 출력
		out.flush(); // (혹시 남아있는 데이터가 있을 경우록 대비) 남아있는 데이터 모두 출력
	}

}
