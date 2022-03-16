package todoApp.model;

import java.time.LocalDate;

// 할일을 저장할 자바 빈 객체
public class Todo {
	private Long id; //int보다 큰 숫자까지 표현
	private String title;	//할일 제목
	private String username;//유저 이름
	private String description;// 내용
	private LocalDate targetDate; //목표 일자 예) 2007-12-03
	private boolean status; //현재 상태(할일을 진행 또는 완료)
	
	public Todo() {}

	public Todo(String title, String username, String description, LocalDate targetDate, boolean status) {
		this.title = title;
		this.username = username;
		this.description = description;
		this.targetDate = targetDate;
		this.status = status;
	}
	
	public Todo(Long id, String title, String username, String description, LocalDate targetDate, boolean status) {
		super();
		this.id = id;
		this.title = title;
		this.username = username;
		this.description = description;
		this.targetDate = targetDate;
		this.status = status;
	}

	//get set 메소드 자동완성

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	//해시코드와 equals를 id로 자동완성 => 객체를 id로 구분
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Todo other = (Todo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", title=" + title + ", username=" + username + ", description=" + description
				+ ", targetDate=" + targetDate + ", status=" + status + "]";
	}

}
