package com.bvb.animalrescue.service;

import java.util.List;

import com.bvb.animalrescue.dto.AnimalDto;
import com.bvb.animalrescue.exception.AnimalRescueException;
import com.bvb.animalrescue.model.Animal;

public interface AnimalService {
	public AnimalDto registerAnimal(AnimalDto animalDto, Integer rescuerId) throws AnimalRescueException;
	public List<AnimalDto> getAllAnimals() throws AnimalRescueException;
	public Animal getAnimalById(Integer id) throws AnimalRescueException;
	public Animal updateAnimal(Integer animalId, Animal updatedAnimal);
	public String deleteAnimal(Integer id) throws AnimalRescueException;

}
