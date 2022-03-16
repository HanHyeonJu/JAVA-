package com.example.todo.domain;


public class UserOneResult {

	// JSON문자열의 name 속성과 멤버변수명이 일치하지 않을경우에는
	// @SerializedName 값으로 일치시키면 해당값이 매핑됨.
	private User user;
	
	private boolean hasResult;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public boolean isHasResult() {
		return hasResult;
	}
	public void setHasResult(boolean hasResult) {
		this.hasResult = hasResult;
	}
	
	@Override
	public String toString() {
		return "UserOneResult [user=" + user + ", hasResult=" + hasResult + "]";
	}
}
