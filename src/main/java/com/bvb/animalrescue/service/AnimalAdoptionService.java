package com.bvb.animalrescue.service;

import java.util.Map;

import com.bvb.animalrescue.dto.AnimalAdoptionDto;

public interface AnimalAdoptionService {
	public AnimalAdoptionDto createOrder(AnimalAdoptionDto animalAdoptionDto) throws Exception;

	public String updateOrder(Map<String, String> respPayload);

}
