package com.bvb.animalrescue.dto;

public class FosterCareDto {
	
	private Integer id;
	
	private String FosterCareName;
	
	private String location;
	
	private String city;
	
	private String state;
	
	private String PhoneNumber;
	
	private String status;
	


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFosterCareName() {
		return FosterCareName;
	}

	public void setFosterCareName(String FosterCareName) {
		this.FosterCareName = FosterCareName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
