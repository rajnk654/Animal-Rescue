package com.bvb.animalrescue.util;

import org.springframework.beans.BeanUtils;

import com.bvb.animalrescue.dto.AnimalDto;
import com.bvb.animalrescue.dto.RescuerDto;
import com.bvb.animalrescue.dto.UserDto;
import com.bvb.animalrescue.model.Animal;

public class AnimalUtil {
	public static AnimalDto convertAnimalsEntityToDto(Animal animal) {
		AnimalDto dto = new AnimalDto();
		BeanUtils.copyProperties(animal, dto);

		RescuerDto rescuerDto = RescuerUtil.convertRescuerEntityToDto(animal.getRescuer());
		dto.setRescuerDto(rescuerDto);
		if(animal.getUser()!=null) {
			dto.setAdopterId(animal.getUser().getId());
			UserDto userDto =  UserUtil.convertUsersEntityToDto(animal.getUser());
			dto.setUserDto(userDto);
		}
		dto.setRescuerId(animal.getRescuer().getId());
		dto.setFosterCareId(animal.getFosterCare().getId());
		return dto;

	}

	public static Animal convertAnimalsDtoToEntity(AnimalDto dto) {
		Animal animal = new Animal();
		BeanUtils.copyProperties(dto, animal);
		return animal;
	}

}
