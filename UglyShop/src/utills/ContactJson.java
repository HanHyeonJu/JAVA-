package utills;

import beans.Cart;
// 제이슨 형태로 변환해서 보낼 클래스
public class ContactJson { 
	
	private boolean status; //상태 (성공/실패)
	private String message; //메세지 입력
	private Cart cart; //연락처 객체를 하나 입력
	
	// 겟, 셋 메소드
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
	
	public Cart getCart() {
		return cart;
	}
	
	public void setCart(Cart cart) {
		this.cart = cart;
	}

}
