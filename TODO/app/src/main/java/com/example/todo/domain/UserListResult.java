package com.example.todo.domain;

import java.util.List;


public class UserListResult {

	private List<User> userList;
	private boolean hasResult;
	private int totalCount;
	
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public boolean isHasResult() {
		return hasResult;
	}
	public void setHasResult(boolean hasResult) {
		this.hasResult = hasResult;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	@Override
	public String toString() {
		return "UserListResult [userList=" + userList + ", hasResult=" + hasResult + ", totalCount=" + totalCount + "]";
	}
}
