package com.bvb.animalrescue.service;

import java.util.List;

import com.bvb.animalrescue.dto.RescuerDto;
import com.bvb.animalrescue.dto.UserDto;
import com.bvb.animalrescue.exception.AnimalRescueException;
import com.bvb.animalrescue.model.Rescuer;

public interface RescuerService {
	public String addNewRescuer(RescuerDto rescuerDto) throws AnimalRescueException;
	public List<RescuerDto> getAllRescuers() throws AnimalRescueException;
	public Rescuer getRescuerById(Long id) throws AnimalRescueException;
	public String updateRescuer(Long id, UserDto userDto) throws AnimalRescueException;
	public String deleteRescuer(Long id) throws AnimalRescueException ;
	
	
	

}
