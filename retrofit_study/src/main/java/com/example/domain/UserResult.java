package com.example.domain;

public class UserResult {

	private boolean isSuccess;

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	@Override
	public String toString() {
		return "UserResult [isSuccess=" + isSuccess + "]";
	}
}
