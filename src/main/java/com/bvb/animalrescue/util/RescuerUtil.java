package com.bvb.animalrescue.util;

import org.springframework.beans.BeanUtils;

import com.bvb.animalrescue.dto.RescuerDto;
import com.bvb.animalrescue.model.Rescuer;

public class RescuerUtil {
	public static RescuerDto convertRescuerEntityToDto(Rescuer rescuer) {
		RescuerDto dto = new RescuerDto();
		BeanUtils.copyProperties(rescuer, dto);
		return dto;
	}

	public static Rescuer convertRescuerDtoToEntity(RescuerDto dto) {
		Rescuer rescuer = new Rescuer();
		BeanUtils.copyProperties(dto, rescuer);
		return rescuer;
	}

}
