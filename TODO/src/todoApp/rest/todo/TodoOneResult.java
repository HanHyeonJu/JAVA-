package todoApp.rest.todo;

import todoApp.model.Todo;

public class TodoOneResult {

	private Todo todo;
	private boolean hasResult;
	
	public Todo getTodo() {
		return todo;
	}
	public void setTodo(Todo todo) {
		this.todo = todo;
	}
	public boolean isHasResult() {
		return hasResult;
	}
	public void setHasResult(boolean hasResult) {
		this.hasResult = hasResult;
	}
	
	@Override
	public String toString() {
		return "TodoOneResult [todo=" + todo + ", hasResult=" + hasResult + "]";
	}
}
