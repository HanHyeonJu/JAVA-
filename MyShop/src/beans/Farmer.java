package beans;

public class Farmer {
	private String farmID;
	private String farmPassword;
	private String farmName;
	private String farmAdd;
	private String farmTel;
	
	
	public Farmer(String farmID, String farmPassword, String farmName, String farmAdd, String farmTel) {
		this.farmID = farmID;
		this.farmPassword = farmPassword;
		this.farmName = farmName;
		this.farmAdd = farmAdd;
		this.farmTel = farmTel;
	}
	
	public String getFarmID() {
		return farmID;
	}
	public void setFarmID(String farmID) {
		this.farmID = farmID;
	}
	public String getFarmPassword() {
		return farmPassword;
	}
	public void setFarmPassword(String farmPassword) {
		this.farmPassword = farmPassword;
	}
	public String getFarmName() {
		return farmName;
	}
	public void setFarmName(String farmName) {
		this.farmName = farmName;
	}
	public String getFarmAdd() {
		return farmAdd;
	}
	public void setFarmAdd(String farmAdd) {
		this.farmAdd = farmAdd;
	}
	public String getFarmTel() {
		return farmTel;
	}
	public void setFarmTel(String farmTel) {
		this.farmTel = farmTel;
	}
		
}
