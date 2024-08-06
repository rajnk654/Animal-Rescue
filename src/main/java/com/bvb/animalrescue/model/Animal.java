package com.bvb.animalrescue.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import com.bvb.animalrescue.enumerated.AnimalStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "animal")
public class Animal {

	@Id
	@GeneratedValue
	@Column(name = "animal_id")
	private Integer id;

	@Column(name = "animal_type")
	private String animalType;

	@Column(name = "gender")
	private String gender;

	@Column(name = "rescue_date")
	private LocalDate rescueDate;

	@Column(name = "adoptable")
	private Boolean adoptable;

	@Column(name = "status", nullable=false)
	@Enumerated(EnumType.STRING)
	public AnimalStatus status;

	@ManyToOne
	@JoinColumn(name = "rescuer_id")
	private Rescuer rescuer;

	@OneToMany(mappedBy = "animal")
	private List<Feedback> feedback;
	
//	@ManyToOne
//	@JoinColumn(name="foster_id",referencedColumnName = "id",nullable = false)
//	private User user;
//	
//	
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

	public List<Feedback> getFeedback() {
		return feedback;
	}

	public void setFeedback(List<Feedback> feedback) {
		this.feedback = feedback;
	}

	public Integer getid() {
		return id;
	}

	public void setid(Integer id) {
		this.id = id;
	}

	public String getAnimalType() {
		return animalType;
	}

	public void setAnimalType(String animalType) {
		this.animalType = animalType;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getRescueDate() {
		return rescueDate;
	}

	public void setRescueDate(LocalDate rescueDate) {
		this.rescueDate = rescueDate;
	}

	public Boolean getAdoptable() {
		return adoptable;
	}

	public void setAdoptable(Boolean adoptable) {
		this.adoptable = adoptable;
	}

	public AnimalStatus getStatus() {
		return status;
	}

	public void setStatus(AnimalStatus status) {
		this.status = status;
	}

	public Rescuer getRescuer() {
		return rescuer;
	}

	public void setRescuer(Rescuer rescuer) {
		this.rescuer = rescuer;
	}

	@Override
	public int hashCode() {
		return Objects.hash(adoptable, animalType, gender, id, rescueDate, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		return Objects.equals(adoptable, other.adoptable) && Objects.equals(animalType, other.animalType)
				&& Objects.equals(gender, other.gender) && Objects.equals(id, other.id)
				&& Objects.equals(rescueDate, other.rescueDate) && status == other.status;
	}

}
