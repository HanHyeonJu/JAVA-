package todoApp.model;
// 로그인 체크를 할 때 사용할 자바 빈 객체
public class LoginBean {
	private String username;
	private String password;
	
	// 이미 있는 데이터를 받는 것이기 때문에 기본 생성자 필요없음
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
