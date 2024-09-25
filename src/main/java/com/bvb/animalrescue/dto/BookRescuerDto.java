package com.bvb.animalrescue.dto;

public class BookRescuerDto {

	private Integer orderId;

	private Long userId;

	private Long rescuerId;

	private UserDto userDto;

	private RescuerDto rescuerDto;

	private Double amount;

	private String orderStatus;

	private String razorPayOrderID;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRescuerId() {
		return rescuerId;
	}

	public void setRescuerId(Long rescuerId) {
		this.rescuerId = rescuerId;
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

	public RescuerDto getRescuerDto() {
		return rescuerDto;
	}

	public void setRescuerDto(RescuerDto rescuerDto) {
		this.rescuerDto = rescuerDto;
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

}
