package com.bvb.animalrescue.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "rescuer")
public class Rescuer {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@Column(name="location")
	private String location;
	
	@Column(name="city")
	private String city;
	
	@Column(name="state")
	private String state;
	
	@Column(name="availability")
	private Boolean availability;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@OneToMany(mappedBy = "rescuer")
	private List<Animal> animal;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public Boolean getAvailability() {
		return availability;
	}

	public void setAvailability(Boolean availability) {
		this.availability = availability;
	}

	@Override
	public String toString() {
		return "Rescuer [id=" + id + ", location=" + location + ", city=" + city + ", state=" + state
				+ ", availability=" + availability + ", user=" + user + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(availability, city, id, location, state);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rescuer other = (Rescuer) obj;
		return Objects.equals(availability, other.availability) && Objects.equals(city, other.city)
				&& Objects.equals(id, other.id) && Objects.equals(location, other.location)
				&& Objects.equals(state, other.state);
	}

	

}
