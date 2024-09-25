package com.bvb.animalrescue.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="student_order")
public class BookRescuer {
	
	@Id
	@GeneratedValue
	private Integer orderId;

	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Rescuer rescuer;

	private Double amount;

	private String orderStatus;

	private String razorPayOrderID;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Rescuer getRescuer() {
		return rescuer;
	}

	public void setRescuer(Rescuer rescuer) {
		this.rescuer = rescuer;
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

	@Override
	public int hashCode() {
		return Objects.hash(amount, orderId, orderStatus, razorPayOrderID, rescuer, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookRescuer other = (BookRescuer) obj;
		return Objects.equals(amount, other.amount) && Objects.equals(orderId, other.orderId)
				&& Objects.equals(orderStatus, other.orderStatus)
				&& Objects.equals(razorPayOrderID, other.razorPayOrderID) && Objects.equals(rescuer, other.rescuer)
				&& Objects.equals(user, other.user);
	}

	

}
