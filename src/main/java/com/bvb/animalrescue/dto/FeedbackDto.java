package com.bvb.animalrescue.dto;

import java.time.LocalDate;

public class FeedbackDto {
	private Long id;
	private Long userId;
	private String feedbackDescription;
	private Integer rating;
	private LocalDate createdAt;

	public Long id() {
		return id;
	}

	public void setFeedbackId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
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
