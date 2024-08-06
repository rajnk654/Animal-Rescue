package com.bvb.animalrescue.serviceImplementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bvb.animalrescue.dao.AnimalRepository;
import com.bvb.animalrescue.dao.RescuerRepository;
import com.bvb.animalrescue.dto.AnimalDto;
import com.bvb.animalrescue.exception.AnimalRescueException;
import com.bvb.animalrescue.model.Animal;
import com.bvb.animalrescue.model.Rescuer;
import com.bvb.animalrescue.service.AnimalService;
import com.bvb.animalrescue.util.AnimalUtil;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AnimalServiceImplementation implements AnimalService {

	@Autowired
	private AnimalRepository animalRepository;

	@Autowired
	private RescuerRepository rescuerRepository;

	private Logger logger = LoggerFactory.getLogger(AnimalServiceImplementation.class);

	// POST: Create a new animal
	public AnimalDto registerAnimal(AnimalDto animalDto, Integer rescuerId) throws AnimalRescueException {
		Animal animal = AnimalUtil.convertAnimalsDtoToEntity(animalDto);
		try {
			Rescuer rescuer = rescuerRepository.findById(rescuerId).get();
			animal.setRescueDate(LocalDate.now());
			animal.setRescuer(rescuer);
			animalRepository.save(animal);
			return animalDto;

		} catch (DataIntegrityViolationException exception) {
			logger.warn("Animal Data Already Exists");
			throw new AnimalRescueException("Animal Data Already Exists" + exception.getMessage());

		}

	}

	// GET: Retrieve all animals
	public List<AnimalDto> getAllAnimals() throws AnimalRescueException {
		try {
			List<AnimalDto> listOfDtos = animalRepository.findAll().stream().map(AnimalUtil::convertAnimalsEntityToDto)
					.collect(Collectors.toList());
			return listOfDtos;
		} catch (Exception exception) {
			throw new AnimalRescueException(exception.getLocalizedMessage());
		}
	}

	// GET: Retrieve a specific animal by ID
	public Animal getAnimalById(Integer id) throws AnimalRescueException {
		try {
			return animalRepository.findById(id).orElse(null);
		} catch (Exception exception) {
			throw new AnimalRescueException(exception.getMessage());
		}
	}

	// PUT: Update an existing animal
	public Animal updateAnimal(Integer animalId, Animal updatedAnimal) {
		// Retrieve the existing animal
		Optional<Animal> existingAnimalOpt = animalRepository.findById(animalId);

		if (existingAnimalOpt.isPresent()) {
			Animal existingAnimal = existingAnimalOpt.get();

			// Update fields
			existingAnimal.setAnimalType(updatedAnimal.getAnimalType());
			existingAnimal.setGender(updatedAnimal.getGender());
			existingAnimal.setRescueDate(updatedAnimal.getRescueDate());
			existingAnimal.setAdoptable(updatedAnimal.getAdoptable());

			// Save updated animal
			return animalRepository.save(existingAnimal);
		} else {
			throw new EntityNotFoundException("Animal not found with ID: " + animalId);
		}
	}

	// DELETE: Remove an animal by ID
	public String deleteAnimal(Integer id) throws AnimalRescueException {
		Optional<Animal> animal = animalRepository.findById(id);
		try {
			if (animal.isPresent()) {
				animalRepository.deleteById(id);
				logger.info("Animal Data Has Deleted Successfully");
				return "Animal Data Has deleted successfully.";

			} else {
				throw new AnimalRescueException("Rescuer Doesn't Exists");
			}
		} catch (Exception exception) {
			throw new AnimalRescueException("Animal Data doesn't exist." + exception.getLocalizedMessage());
		}
	}
}
