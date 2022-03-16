package todoApp.rest.todo;

public class TodoResult {

	private boolean isSuccess;

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	@Override
	public String toString() {
		return "TodoResult [isSuccess=" + isSuccess + "]";
	}
}
