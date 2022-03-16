package beans;

import java.io.Serializable;

public class Cart {
	private static int num;
	private int cartID;
	private int prodID;
	private String prodName; //상품명
	private int prodPrice;
	private int orderQuantity;
	private String userID;
	private String farmID;
	
	public Cart(int prodID, String prodName, int prodPrice, int orderQuantity, String userID,
			String farmID) {
		super();
		this.prodID = prodID;
		this.prodName = prodName;
		this.prodPrice = prodPrice;
		this.orderQuantity = orderQuantity;
		this.setUserID(userID);
		this.setFarmID(farmID);
	}
	
	public int getCartID() {
		return cartID;
	}

	public void setCartID(int cartID) {
		this.cartID = cartID;
	}

	public int getProdID() {
		return prodID;
	}

	public void setProdId(int prodID) {
		this.prodID = prodID;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public int getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(int prodPrice) {
		this.prodPrice = prodPrice;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public String getFarmID() {
		return farmID;
	}

	public void setFarmID(String farmID) {
		this.farmID = farmID;
	}
	
	@Override
	public String toString() {
		return "Cart [prodID=" + prodID + ", prodName=" + prodName + ", prodPrice="
				+ prodPrice + ", orderQuantity=" + orderQuantity + ", userID=" + userID + ", farmID=" + farmID
				+ "]";
	} 
}
