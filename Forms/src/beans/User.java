package beans;

//자바 빈으로 만들 클래스(필드변수, 기본생성자(생략가능), get/set 메소드 필요)
public class User {
	private String email = ""; // 초기값을 공백으로 줘야 null값이 나오지 않음
	private String password = "";
	
	private String message = ""; // 유효성 검사에 불합격 했을 때 메세지 그래서 get 메소드만 주어짐
	
	public User() {
		//기본 생성자	
	}
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	// 입력된 필드변수들의 값이 유효한지 유효성 검사
	public boolean validate() {
//		if(email == null) { // 초기값을 공백으로 설정해줬기 때문에 null값은 필요하지 않음(어차피 안 들어가짐(?))
//			message = "Invalid email";
//			return false;
//		}
//		if(password == null) {
//			message = "Invalid password";
//			return false;
//		}                 // 정규 표현식으로 자바문자열은 역슬래시를 두 번 적어야한다.\w = 모든문자(숫자포함)
		if(!email.matches("\\w+@\\w+\\.\\w+")){
			message = "Invalid email address";
			return false;
		}
		if(password.length() < 8) {
			message = "패스워드는 8자 이상";
			return false;
		}
		else if(password.matches("\\w*\\s+\\w*")){
			message = "패스워드에 스페이스가 포함되면 안됩니다.";
			return false;
		}
		
		//위의 검사를 다 함격하면 유효성 메소드 true로 리턴
		return true;
	}
}
	
	
