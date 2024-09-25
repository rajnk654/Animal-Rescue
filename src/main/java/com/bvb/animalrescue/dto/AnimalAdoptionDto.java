package com.bvb.animalrescue.dto;

import com.bvb.animalrescue.model.Animal;
import com.bvb.animalrescue.model.User;

public class AnimalAdoptionDto {
	
	private Integer id;
	
	private User userDto;
	
	private Animal animalDto;

	private Double amount;

	private String orderStatus;

	private String razorPayOrderID;
	
	private Integer userId;
	
	private Integer animalId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUserDto() {
		return userDto;
	}

	public void setUserDto(User userDto) {
		this.userDto = userDto;
	}

	public Animal getAnimalDto() {
		return animalDto;
	}

	public void setAnimalDto(Animal animalDto) {
		this.animalDto = animalDto;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getRazorPayOrderID() {
		return razorPayOrderID;
	}

	public void setRazorPayOrderID(String razorPayOrderID) {
		this.razorPayOrderID = razorPayOrderID;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getAnimalId() {
		return animalId;
	}

	public void setAnimalId(Integer animalId) {
		this.animalId = animalId;
	}
	
	


}
