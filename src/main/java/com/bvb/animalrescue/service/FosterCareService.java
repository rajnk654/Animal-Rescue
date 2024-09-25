package com.bvb.animalrescue.service;

import java.util.List;

import com.bvb.animalrescue.dto.AnimalDto;
import com.bvb.animalrescue.dto.FosterCareDto;
import com.bvb.animalrescue.exception.AnimalRescueException;

public interface FosterCareService {

	public String addNewFosterCare(FosterCareDto fosterCareDto) throws AnimalRescueException;

	public List<FosterCareDto> getFosterCare() throws AnimalRescueException;

	public String updateFosterCare(Integer fosterCareId, FosterCareDto dto) throws AnimalRescueException;

	public String deleteFosterCare(Integer fosterCareId) throws AnimalRescueException;
	
	 public List<AnimalDto> getAnimalsInFosterCare(Integer fosterCareId) throws AnimalRescueException;
}
