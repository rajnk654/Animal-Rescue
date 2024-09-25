package com.bvb.animalrescue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bvb.animalrescue.dto.AnimalDto;
import com.bvb.animalrescue.dto.FosterCareDto;
import com.bvb.animalrescue.exception.AnimalRescueException;
import com.bvb.animalrescue.rest.AnimalRescueResponse;
import com.bvb.animalrescue.serviceImplementation.FosterCareServiceImplementation;

@RestController
@RequestMapping(path = "/fosterCare")
public class FosterCareController {

	@Autowired
	private FosterCareServiceImplementation fosterCareService;

	@PostMapping
	public AnimalRescueResponse createNewFosterCare(@RequestBody FosterCareDto fosterCareDto) {
		HttpStatus Status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			Status = HttpStatus.OK;
			message = fosterCareService.addNewFosterCare(fosterCareDto);
			return new AnimalRescueResponse(message, Status);
		} catch (Exception exception) {
			message = "Internal Server Error !!" + exception.getLocalizedMessage();
		}
		return new AnimalRescueResponse(message, Status);
	}

	@GetMapping
	public AnimalRescueResponse getFosterCare() {
		HttpStatus Status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			List<FosterCareDto> listOfDto = fosterCareService.getFosterCare();
			Status = HttpStatus.OK;
			return new AnimalRescueResponse(listOfDto, Status);

		} catch (AnimalRescueException exception) {
			message = "Failed to retrieve Users data ||" + exception.getLocalizedMessage();
		}
		return new AnimalRescueResponse(message, Status);
	}

	@PutMapping("/id/{fosterCareId}")
	public AnimalRescueResponse updateFosterCare(@PathVariable(name = "fosterCareId") Integer fosterCareId,
			@RequestBody FosterCareDto fosterCareDto) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			String dto = fosterCareService.updateFosterCare(fosterCareId, fosterCareDto);
			status = HttpStatus.OK;
			return new AnimalRescueResponse(dto, status);
		} catch (AnimalRescueException exception) {
			message = "Failed to Update User !!" + exception.getLocalizedMessage();
		} catch (Exception exception) {
			message = "Internal Server Error !!" + exception.getLocalizedMessage();
		}
		return new AnimalRescueResponse(message, status);
	}
	
	// Get animals from fostercare
	@GetMapping("/{fosterCareId}/animals")
	public AnimalRescueResponse getAnimalsInFosterCare(@PathVariable Integer fosterCareId) {
	    HttpStatus status = HttpStatus.BAD_REQUEST;
	    String message = "";
	    try {
	        List<AnimalDto> animals = fosterCareService.getAnimalsInFosterCare(fosterCareId);
	        if (animals != null && !animals.isEmpty()) {
	            status = HttpStatus.OK;
	            return new AnimalRescueResponse(animals, status);
	        } else {
	            throw new AnimalRescueException("No animals found in the foster care with ID: " + fosterCareId);
	        }
	    } catch (AnimalRescueException exception) {
	        message = "Failed to retrieve animals from foster care!! " + exception.getLocalizedMessage();
	    } catch (Exception exception) {
	        message = "Internal Server Error!! " + exception.getLocalizedMessage();
	        status = HttpStatus.INTERNAL_SERVER_ERROR;
	    }
	    return new AnimalRescueResponse(message, status);
	}
	

	@DeleteMapping("/id/{fosterCareId}")
	public AnimalRescueResponse deleteFosterCare(@PathVariable(name = "fosterCareId") Integer fosterCareId) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			message = fosterCareService.deleteFosterCare(fosterCareId);
			status = HttpStatus.OK;
		} catch (AnimalRescueException exception) {
			message = "Failed To Delete FosterCare!!" + exception.getMessage();

		} catch (Exception exception) {
			message = "Internal Server Error !!" + exception.getMessage();
		}
		return new AnimalRescueResponse(message, status);
	}
}
