package br.com.csvreader.model;

public class School {
	
	private int ID;
	private String schoolCode;
	private String schoolName;
	private String address;
	private String city;
	private String stateCode;
	private String zipCode;
	private String province;
	private String country;
	private String postalCode;
	
	public int getID() {
		return ID;
	}
	
	public void setID(int iD) {
		ID = iD;
	}
	
	public String getSchoolCode() {
		return schoolCode;
	}
	
	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}
	
	public String getSchoolName() {
		return schoolName;
	}
	
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getStateCode() {
		return stateCode;
	}
	
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	
	public String getZipCode() {
		return zipCode;
	}
	
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	public String getProvince() {
		return province;
	}
	
	public void setProvince(String province) {
		this.province = province;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getPostalCode() {
		return postalCode;
	}
	
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	/*public String toJson() {
		String json = "";
		
		json += "{";
		json += "\"ID\":" + this.ID + ",";
		json += "\"schoolCode\":\"" + this.schoolCode + "\",";
		json += "\"schoolName\":\"" + this.schoolName + "\",";
		json += "\"address\":\"" + this.address + "\",";
		json += "\"city\":\"" + this.city + "\",";
		json += "\"stateCode\":\"" + this.stateCode + "\",";
		json += "\"zipCode\":\"" + this.zipCode + "\",";
		json += "\"province\":\"" + this.province + "\",";
		json += "\"country\":\"" + this.country + "\",";
		json += "\"postalCode\":\"" + this.postalCode + "\"";
		json += "}";
		
		return json;
	}*/
	public String toJson() {
		String json = "";
		
		json += "{";
		json += "ID:" + this.ID + ",";
		json += "schoolCode:" + this.schoolCode + ",";
		json += "schoolName:" + this.schoolName + ",";
		json += "address:" + this.address + ",";
		json += "city:" + this.city + ",";
		json += "stateCode:" + this.stateCode + ",";
		json += "zipCode:" + this.zipCode + ",";
		json += "province:" + this.province + ",";
		json += "country:" + this.country + ",";
		json += "postalCode:" + this.postalCode + "";
		json += "}";
		
		return json;
	}
}