package com.bvb.animalrescue.dto;

import java.time.LocalDate;

import com.bvb.animalrescue.enumerated.AnimalStatus;

public class AnimalDto {
	private Long id;
	private String animalType;
	private String gender;
	private LocalDate rescueDate;
	private Boolean adoptable;
	private AnimalStatus status;
	private RescuerDto rescuerDto;
	
	private Long adopterId;
	private Long rescuerId;
	private Integer fosterCareId;
	
	private UserDto userDto; 

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

	public Long getAdopterId() {
		return adopterId;
	}

	public void setAdopterId(Long adopterId) {
		this.adopterId = adopterId;
	}

	public Long getRescuerId() {
		return rescuerId;
	}

	public void setRescuerId(Long rescuerId) {
		this.rescuerId = rescuerId;
	}

	public Integer getFosterCareId() {
		return fosterCareId;
	}

	public void setFosterCareId(Integer fosterCareId) {
		this.fosterCareId = fosterCareId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	

	public RescuerDto getRescuerDto() {
		return rescuerDto;
	}

	public void setRescuerDto(RescuerDto rescuerDto) {
		this.rescuerDto = rescuerDto;
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

	

	

}
