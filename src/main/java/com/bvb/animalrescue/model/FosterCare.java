package com.bvb.animalrescue.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class FosterCare {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;

	@Column(name = "foster_care_name")
	private String fosterCareName;

	@Column(name = "location")
	private String location;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "phone_number")
	private String phoneNumber;

//	@Column(name = "status")
//	private String status;
	
	

	@OneToMany(mappedBy = "fosterCare")
	private List<Animal> animal;

	public List<Animal> getAnimal() {
		return animal;
	}

	public void setAnimal(List<Animal> animal) {
		this.animal = animal;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFosterCareName() {
		return fosterCareName;
	}

	public void setFosterCareName(String FosterCareName) {
		this.fosterCareName = FosterCareName;
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
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	

	@Override
	public String toString() {
		return "FostCare [id=" + id + ", FosterCareName=" + fosterCareName + ", location=" + location + ", city=" + city
				+ ", state=" + state + ", phoneNumber=" + phoneNumber +  "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(fosterCareName, city, id, location, phoneNumber, state);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FosterCare other = (FosterCare) obj;
		return Objects.equals(fosterCareName, other.fosterCareName) && Objects.equals(city, other.city)
				&& Objects.equals(id, other.id) && Objects.equals(location, other.location)
				&& Objects.equals(phoneNumber, other.phoneNumber) && Objects.equals(state, other.state);
				
	}
}
