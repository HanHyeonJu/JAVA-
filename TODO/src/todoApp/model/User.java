package todoApp.model;

import java.util.List;

public class User {
	
	private String firstName;
	private String lastName;
	private String userName;
	private String password;

	//private Todo todo; // 테이블 조인에서 1:1 관계
	private List<Todo> todoList; // 테이블 조인에서 1:N 관계
	
	private Integer todoCount;
	
	
	public User() {} //기본 생성자는 자바 빈용으로 필요
	// 유저 생성자 
	public User(String firstName, String lastName, String userName, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
	}
	// set/get 메소드를 자동완성
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Todo> getTodoList() {
		return todoList;
	}
	public void setTodoList(List<Todo> todoList) {
		this.todoList = todoList;
	}
	
	public Integer getTodoCount() {
		return todoCount;
	}
	public void setTodoCount(Integer todoCount) {
		this.todoCount = todoCount;
	}
	
	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName + ", password="
				+ password + ", todoList=" + todoList + ", todoCount=" + todoCount + "]";
	}
}




