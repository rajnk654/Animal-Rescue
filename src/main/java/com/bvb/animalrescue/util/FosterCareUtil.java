package com.bvb.animalrescue.util;

import org.springframework.beans.BeanUtils;

import com.bvb.animalrescue.dto.FosterCareDto;
import com.bvb.animalrescue.model.FosterCare;



public class FosterCareUtil {
	public static FosterCareDto convertFosterCareEntityToDto(FosterCare fosterCare) {
		FosterCareDto dto = new FosterCareDto();
		BeanUtils.copyProperties(fosterCare, dto);
		return dto;
	}

	public static FosterCare convertFosterCareDtoToEntity(FosterCareDto dto) {
		FosterCare fostercare = new FosterCare();
		BeanUtils.copyProperties(dto, fostercare);
		return fostercare;
	}

}
