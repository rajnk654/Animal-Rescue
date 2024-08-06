package com.bvb.animalrescue.model;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Feedback {

	@Id
	@GeneratedValue
	@Column(name = "feedback_id")
	private Integer id;
	
	@Column(name = "feedback_description")
	private String feedbackDescription;
	
	@Column(name = "rating")
	private Integer rating;
	
	@Column(name = "created_at")
	private LocalDate createdAt;

	@ManyToOne
	@JoinColumn(name = "user_id",referencedColumnName = "id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "animal_id")
	private Animal animal;
	
	
	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	@Override
	public int hashCode() {
		return Objects.hash(createdAt, feedbackDescription, id, rating);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Feedback other = (Feedback) obj;
		return Objects.equals(createdAt, other.createdAt)
				&& Objects.equals(feedbackDescription, other.feedbackDescription) && Objects.equals(id, other.id)
				&& Objects.equals(rating, other.rating);
	}

	
	

}
