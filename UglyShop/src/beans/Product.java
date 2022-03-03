package beans;

public class Product {
	private int prodID;
	private String farmID;
	private String prodName;
	private int prodPrice;
	private int prodInven;
	private String prodImg;
	private String prodInfo;
	
	public Product() {
	}


	public int getProdID() {
		return prodID;
	}

	public void setProdID(int prodID) {
		this.prodID = prodID;
	}

	public String getFarmID() {
		return farmID;
	}
	
	public void setFarmID(String farmID) {
		this.farmID = farmID;
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

	public int getProdInven() {
		return prodInven;
	}

	public void setProdInven(int prodInven) {
		this.prodInven = prodInven;
	}

	public String getProdImg() {
		return prodImg;
	}

	public void setProdImg(String prodImg) {
		this.prodImg = prodImg;
	}

	public String getProdInfo() {
		return prodInfo;
	}

	public void setProdInfo(String prodInfo) {
		this.prodInfo = prodInfo;
	}

	@Override
	public String toString() {
		return "Product [prodID=" + prodID + ", farmID=" + farmID + ", prodName=" + prodName + ", prodPrice=" + prodPrice + ", prodInven="
				+ prodInven + ", prodImg=" + prodImg + ", prodInfo=" + prodInfo + "]";
	}

}
