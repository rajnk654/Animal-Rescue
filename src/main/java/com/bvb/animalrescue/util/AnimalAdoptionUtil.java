package com.bvb.animalrescue.util;

import org.springframework.beans.BeanUtils;

import com.bvb.animalrescue.dto.AnimalAdoptionDto;
import com.bvb.animalrescue.model.AnimalAdoption;

public class AnimalAdoptionUtil {
	public static AnimalAdoptionDto convertAnimalAdoptionEntityToDto(AnimalAdoption animalAdoption) {
		AnimalAdoptionDto dto = new AnimalAdoptionDto();

		// Copy simple properties
		BeanUtils.copyProperties(animalAdoption, dto);

		return dto;
	}

	// Convert BookRescuerDto to BookRescuer entity
	public static AnimalAdoption convertAnimalAdoptionDtoToEntity(AnimalAdoptionDto dto) {
		AnimalAdoption animalAdoption = new AnimalAdoption();

		BeanUtils.copyProperties(dto, animalAdoption);

		return animalAdoption;
	}

}
