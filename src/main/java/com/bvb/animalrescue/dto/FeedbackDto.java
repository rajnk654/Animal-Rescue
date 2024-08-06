package com.bvb.animalrescue.dto;

import java.time.LocalDate;

public class FeedbackDto {
	private Integer id;
	private Integer userId;
	private String feedbackDescription;
	private Integer rating;
	private LocalDate createdAt;

	public Integer id() {
		return id;
	}

	public void setFeedbackId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFeedbackDescription() {
		return feedbackDescription;
	}

	public void setFeedbackDescription(String feedbackDescription) {
		this.feedbackDescription = feedbackDescription;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

}
