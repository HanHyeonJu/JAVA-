package beans;

import java.time.LocalDate;

public class Review {
	private int reviewID;
	private String userID;
	private LocalDate reviewDate;
	private String reviewTitle;
	private String reviewContent;
	private int prodID;
	
	public Review() { }
	
	public Review(String userID, LocalDate reviewDate, String reviewTitle, String reviewContent, int prodID) {
		super();
		this.userID = userID;
		this.reviewDate = reviewDate;
		this.reviewTitle = reviewTitle;
		this.reviewContent = reviewContent;
		this.prodID = prodID;
	}
	
	public int getReviewID() {
		return reviewID;
	}
	public void setReviewID(int reviewID) {
		this.reviewID = reviewID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public LocalDate getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(LocalDate reviewDate) {
		this.reviewDate = reviewDate;
	}
	public String getReviewTitle() {
		return reviewTitle;
	}
	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	public int getProdID() {
		return prodID;
	}
	public void setProdID(int prodID) {
		this.prodID = prodID;
	}

	@Override
	public String toString() {
		return "Review [ reviewID=" + reviewID + ", userID=" + userID + ", reviewDate=" + reviewDate + ", reviewTitle="
				+ reviewTitle + ", reviewContent=" + reviewContent + ", prodID=" + prodID + " ]";
	}
	
	
}

