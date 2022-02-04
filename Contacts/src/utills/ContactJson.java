package utills;

import model.Contact;
// 제이슨 형태로 보낼 클래스
public class ContactJson { 
	
	private boolean status; // 상태(정상이면 true 아니면 false return 받음)
	private String message; // 메시지
	private Contact contact; // 특정 id로 DB에 있는 연락처 하나를 읽어올 때 이 객체에 입력
	
	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Contact getContact() {
		return contact;
	}
	
	public void setContact(Contact contact) {
		this.contact = contact;
	}

}
