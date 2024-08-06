package com.bvb.animalrescue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bvb.animalrescue.dto.UserDto;
import com.bvb.animalrescue.exception.AnimalRescueException;
import com.bvb.animalrescue.model.User;
import com.bvb.animalrescue.rest.AnimalRescueResponse;
import com.bvb.animalrescue.serviceImplementation.UserServiceImplementation;

@RestController
@RequestMapping(path = "api/v1/users")
@CrossOrigin
public class UserController {

	@Autowired
	private UserServiceImplementation userService;

	@GetMapping
	public AnimalRescueResponse getUsers() {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			List<UserDto> listOfDto = userService.getUsers();
			status = HttpStatus.OK;
			return new AnimalRescueResponse(listOfDto, status);
		} catch (AnimalRescueException exception) {
			message = "Failed to retrieve Users data ||" + exception.getLocalizedMessage();
		} catch (Exception exception) {
			message = "Internal Server Error !!" + exception.getLocalizedMessage();
		}

		return new AnimalRescueResponse(message, status);
	}

	@PostMapping("/demo")
	public User demo(@RequestBody User user) {
		System.out.println("Ok");
		return user;
	}
	@PostMapping
	public AnimalRescueResponse registerNewUsers(@RequestBody UserDto dto) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {

			status = HttpStatus.OK;
			message = userService.addNewUsers(dto);
			return new AnimalRescueResponse(message, status);
		} catch (AnimalRescueException exception) {
			message = "Failed to retrieve Users data!!" + exception.getLocalizedMessage();

		} catch (Exception exception) {
			message = "Internal Server Error !!" + exception.getLocalizedMessage();
		}
		return new AnimalRescueResponse(message, status);
	}

	@DeleteMapping(path = "/{userId}")
	public AnimalRescueResponse deleteUsers(@PathVariable(name = "userId") Integer userId) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			message = userService.deleteUsers(userId);
			status = HttpStatus.OK;
		} catch (AnimalRescueException exception) {
			message = "Failed to Delete User!" + exception.getLocalizedMessage();

		} catch (Exception exception) {
			message = "Internal Server Error!!" + exception.getLocalizedMessage();
		}
		return new AnimalRescueResponse(message, status);

	}

	@PutMapping(path = "/id/{userId}")
	public AnimalRescueResponse updateUser(@PathVariable(name = "userId") Integer userId,
			@RequestBody UserDto userDto) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			String dto = userService.updateUser(userId, userDto);
			status = HttpStatus.OK;
			return new AnimalRescueResponse(dto, status);
		} catch (AnimalRescueException exception) {
			message = "Failed to Update User !!" + exception.getLocalizedMessage();
		} catch (Exception exception) {
			message = "Internal Server Error !!" + exception.getLocalizedMessage();
		}
		return new AnimalRescueResponse(message, status);
	}

	@DeleteMapping("/id/{userId}")
	public AnimalRescueResponse deleteUser(@PathVariable(name = "userId") Integer userId) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			message = userService.deleteUser(userId);
			status = HttpStatus.OK;
		} catch (AnimalRescueException exception) {
			message = "Failed to add User !!" + exception.getLocalizedMessage();
		} catch (Exception exception) {
			message = "Internal Server Error !!" + exception.getLocalizedMessage();
		}
		return new AnimalRescueResponse(message, status);

	}

}
