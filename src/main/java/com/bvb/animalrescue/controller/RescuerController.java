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

import com.bvb.animalrescue.dto.RescuerDto;
import com.bvb.animalrescue.dto.UserDto;
import com.bvb.animalrescue.exception.AnimalRescueException;
import com.bvb.animalrescue.model.Rescuer;
import com.bvb.animalrescue.rest.AnimalRescueResponse;
import com.bvb.animalrescue.serviceImplementation.RescuerServiceImplementation;

@RestController
@RequestMapping(path = "/Rescuer")
public class RescuerController {

	@Autowired
	private RescuerServiceImplementation rescuerService;

//	@Autowired
//	private UserServiceImplementation userServiceImplementation;

	@GetMapping
	public AnimalRescueResponse getAllRescuers() {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			List<RescuerDto> listOfDto = rescuerService.getAllRescuers();
			status = HttpStatus.OK;
			return new AnimalRescueResponse(listOfDto, status);
		} catch (AnimalRescueException exception) {
			message = "Failed to retrieve Users data !!" + exception.getLocalizedMessage();
		} catch (Exception exception) {
			message = "Internal Server Error !!" + exception.getLocalizedMessage();
		}
		return new AnimalRescueResponse(message, status);

	}

	@GetMapping("/{id}")
	public AnimalRescueResponse getRescuerById(Integer id) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			Rescuer rescuer = rescuerService.getRescuerById(id);
			status = HttpStatus.OK;
			if (rescuer == null) {
				throw new AnimalRescueException("Empty Rescuer Data");
			}
			return new AnimalRescueResponse(rescuer, status);
		} catch (AnimalRescueException exception) {
			message = "Failed to retrieve rescuer id !!" + exception.getLocalizedMessage();
		}
		return new AnimalRescueResponse(message, status);
	}

	@PostMapping
	public AnimalRescueResponse createRescuer(@RequestBody UserDto dto) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			String newDto = rescuerService.addNewRescuer(dto);
			if (newDto != null) {
				status = HttpStatus.OK;

				return new AnimalRescueResponse(newDto, status);
			}
		}

		catch (AnimalRescueException exception) {
			message = "Failed to add User !!" + exception.getLocalizedMessage();
		} catch (Exception exception) {
			message = "Internal Server Error !!" + exception.getLocalizedMessage();
		}
		return new AnimalRescueResponse(message, status);

	}

	@PutMapping("/{id}")
	public AnimalRescueResponse updateRescuer(@PathVariable Integer id, @RequestBody UserDto rescuerDto) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			message = rescuerService.updateRescuer(id, rescuerDto);
			status = HttpStatus.OK;
		} catch (AnimalRescueException exception) {
			message = "Failed to Update Rescuer !!" + exception.getLocalizedMessage();
		} catch (Exception exception) {
			message = "Internal Server Error !!" + exception.getLocalizedMessage();
		}
		return new AnimalRescueResponse(message, status);

	}

	// DELETE: Remove a rescuer by ID
	@DeleteMapping("/{id}")
	public AnimalRescueResponse deleteRescuer(@PathVariable(name = "id") Integer id) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			message = rescuerService.deleteRescuer(id);

		} catch (AnimalRescueException exception) {
			message = "Failed to Delete Rescuer !!" + exception.getLocalizedMessage();
		} catch (Exception exception) {
			message = "Internal Server Error!!" + exception.getLocalizedMessage();
		}
		return new AnimalRescueResponse(message, status);

	}

}
