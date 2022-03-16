package todoApp.rest.todo;

import todoApp.model.User;

public class TodoListResult {

	private boolean hasResult;
	private int totalCount;
	
	private User user; // 조인 결과로 user가 todoList 포함함
	
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
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "TodoListResult [hasResult=" + hasResult + ", totalCount=" + totalCount + ", user=" + user + "]";
	}
}




