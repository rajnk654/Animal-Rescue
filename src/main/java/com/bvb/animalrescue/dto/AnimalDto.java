package com.bvb.animalrescue.dto;

import java.time.LocalDate;

import com.bvb.animalrescue.enumerated.AnimalStatus;

public class AnimalDto {
	private Integer animalId;
	private String animalType;
	private String gender;
	private LocalDate rescueDate;
	private Boolean adoptable;
	private AnimalStatus status;
	private RescuerDto rescuerDto;

	public Integer getAnimalId() {
		return animalId;
	}

	public void setAnimalId(Integer animalId) {
		this.animalId = animalId;
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
