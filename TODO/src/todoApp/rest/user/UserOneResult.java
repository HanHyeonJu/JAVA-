package todoApp.rest.user;

import todoApp.model.User;

public class UserOneResult {

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
