package todoApp.rest.todo;

import java.util.List;

import todoApp.model.User;

public class TodoListUsersResult {

	private boolean hasResult;
	private int totalCount;
	
	private List<User> userList;
	
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
	
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	@Override
	public String toString() {
		return "TodoListUsersResult [hasResult=" + hasResult + ", totalCount=" + totalCount + ", userList=" + userList
				+ "]";
	}
}




