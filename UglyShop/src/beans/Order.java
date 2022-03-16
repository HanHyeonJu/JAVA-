package beans;

import java.time.LocalDate;

public class Order {
	private int orderID;
	private int cartID;
	private String userID;
	private String userName;
	private String userAdd;
	private String farmID;
	private String userTel;
	private int prodID;
	private int prodPrice;
	private String prodName;
	private int orderQuantity;
	private String is_status;
	private LocalDate orderDate;
	
	
	
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public int getCartID() {
		return cartID;
	}
	public void setCartID(int cartID) {
		this.cartID = cartID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserAdd() {
		return userAdd;
	}
	public void setUserAdd(String userAdd) {
		this.userAdd = userAdd;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	public int getProdID() {
		return prodID;
	}
	public void setProdID(int prodID) {
		this.prodID = prodID;
	}
	public int getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(int prodPrice) {
		this.prodPrice = prodPrice;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	
	public int getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public String getFarmID() {
		return farmID;
	}
	public void setFarmID(String farmID) {
		this.farmID = farmID;
	}

	public String getIs_status() {
		return is_status;
	}
	public void setIs_status(String is_status) {
		this.is_status = is_status;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	

	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", cartID=" + cartID + ", userID=" + userID + ", userName=" + userName
				+ ", userAdd=" + userAdd + ", userTel=" + userTel + ", prodID=" + prodID + ", prodPrice=" + prodPrice
				+ ", prodName=" + prodName + ", orderQuantity=" + orderQuantity
				+ ", farmID=" + farmID + ", is_status=" + is_status + "]";
	}
}
