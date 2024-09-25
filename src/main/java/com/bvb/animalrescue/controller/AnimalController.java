
package com.bvb.animalrescue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bvb.animalrescue.dto.AnimalDto;
import com.bvb.animalrescue.exception.AnimalRescueException;
import com.bvb.animalrescue.model.Animal;
import com.bvb.animalrescue.rest.AnimalRescueResponse;
import com.bvb.animalrescue.serviceImplementation.AnimalServiceImplementation;

@RestController
@RequestMapping(path = "/animal")
public class AnimalController {

	@Autowired
	private AnimalServiceImplementation animalService;

	@PostMapping("rescuer-Id/{rescuerId}/fosterCare-Id/{fosterCareId}")
	public AnimalRescueResponse registerAnimal(@PathVariable(name = "rescuerId") Long rescuerId,
			@PathVariable(name = "fosterCareId") Integer fosterCareId, @RequestBody AnimalDto dto) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			AnimalDto animalDto = animalService.registerAnimal(dto, rescuerId, fosterCareId);
			if (animalDto != null) {
				status = HttpStatus.OK;
				return new AnimalRescueResponse(animalDto, status);
			}
		}

		catch (AnimalRescueException exception) {
			message = "Failed to register Animal !!" + exception.getLocalizedMessage();

		} catch (Exception exception) {
			message = "Internal Server Error !!" + exception.getLocalizedMessage();
		}
		return new AnimalRescueResponse(message, status);
	}

	//
	@PostMapping("/animalId/{animalId}/adopterId/{adopterId}")
	public AnimalRescueResponse adoptAnimal(@PathVariable(name = "adopterId")Long adopterId,
			@PathVariable(name = "animalId")Integer animalId) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			// Assuming addAnimal returns a success message.
			message = animalService.adoptAnimal(adopterId, animalId);
			status = HttpStatus.OK;
		} catch (AnimalRescueException exception) {
			message = "Failed To Add Animal!! " + exception.getMessage();
		} catch (Exception exception) {
			message = "Internal Server Error: " + exception.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR; // Update status on a general exception
		}
		return new AnimalRescueResponse(message, status);
	}

	@GetMapping
	public AnimalRescueResponse getAllAnimals() {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			List<AnimalDto> listOfDto = animalService.getAllAnimals();
			status = HttpStatus.OK;
			return new AnimalRescueResponse(listOfDto, status);
		} catch (AnimalRescueException exception) {
			message = "Failed to retrieve Animals data !!" + exception.getLocalizedMessage();
		} catch (Exception exception) {
			message = "Internal Server Error !!" + exception.getLocalizedMessage();
		}
		return new AnimalRescueResponse(message, status);

	}

	@GetMapping("/{id}")
	public AnimalRescueResponse getAnimalById(Integer id) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			AnimalDto animal = animalService.getAnimalById(id);
			status = HttpStatus.OK;
			if (animal == null) {
				throw new AnimalRescueException("Empty Animal Data");
			}
			return new AnimalRescueResponse(animal, status);
		} catch (AnimalRescueException exception) {
			message = "Failed to retrieve Animal id !!" + exception.getLocalizedMessage();
		}
		return new AnimalRescueResponse(message, status);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Animal> updateAnimal(@PathVariable Integer id, @RequestBody Animal animal) {
		Animal updatedAnimal = animalService.updateAnimal(id, animal);
		return ResponseEntity.ok(updatedAnimal);
	}

	@DeleteMapping("/{id}")
	public AnimalRescueResponse deleteAnimal(@PathVariable Integer id) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			message = animalService.deleteAnimal(id);

		} catch (AnimalRescueException exception) {
			message = "Failed To Delete the Animal Data!!" + exception.getMessage();
		} catch (Exception exception) {
			message = "Internal Server Error!!" + exception.getMessage();
		}
		return new AnimalRescueResponse(message, status);
	}

}
