package com.bvb.animalrescue.util;

import org.springframework.beans.BeanUtils;

import com.bvb.animalrescue.dto.AnimalDto;
import com.bvb.animalrescue.dto.RescuerDto;
import com.bvb.animalrescue.model.Animal;

public class AnimalUtil {
	public static AnimalDto convertAnimalsEntityToDto(Animal animal) {
		AnimalDto dto = new AnimalDto();
		BeanUtils.copyProperties(animal, dto);
		
		RescuerDto rescuerDto = RescuerUtil.convertRescuerEntityToDto(animal.getRescuer());
		dto.setRescuerDto(rescuerDto);
		return dto;
	}

	public static Animal convertAnimalsDtoToEntity(AnimalDto dto) {
		Animal animal = new Animal();
		BeanUtils.copyProperties(dto, animal);
		return animal;
	}

}
