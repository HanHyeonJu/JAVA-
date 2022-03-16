package com.example.todo.domain;

import com.google.gson.annotations.SerializedName;

public class User {
	
	// JSON문자열의 name 속성과 멤버변수명이 일치하지 않을경우에는
	// @SerializedName 값으로 일치시키면 해당값이 매핑됨.
	@SerializedName("firstName")
	private String firstName;
	
	private String lastName;
	private String userName;
	private String password;
	
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
	
	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName + ", password="
				+ password + "]";
	}
}




