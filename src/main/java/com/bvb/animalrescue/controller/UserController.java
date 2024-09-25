package com.bvb.animalrescue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bvb.animalrescue.dto.GoogleUserDto;
import com.bvb.animalrescue.dto.UserDto;
import com.bvb.animalrescue.exception.AnimalRescueException;
import com.bvb.animalrescue.rest.AnimalRescueResponse;
import com.bvb.animalrescue.serviceImplementation.UserServiceImplementation;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "/user")
@CrossOrigin
public class UserController {

	@Autowired
	private UserServiceImplementation userService;

	@GetMapping("/{email}")
	public AnimalRescueResponse GetUsersByEmail(@PathVariable(name = "email") String email) {
		HttpStatus Status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			UserDto listOfDto = userService.getUsersByEmail(email);
			Status = HttpStatus.OK;
			return new AnimalRescueResponse(listOfDto, Status);
		} catch (AnimalRescueException exception) {
			message = "Failed to retrieve Users data ||" + exception.getLocalizedMessage();
		} catch (Exception exception) {
			message = "Internal Server Error !!" + exception.getLocalizedMessage();
		}

		return new AnimalRescueResponse(message, Status);
	}

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

	@PostMapping("/signUp")
//	@RequestMapping(path="/{signUp}")
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
	public AnimalRescueResponse updateUser(@PathVariable(name = "userId") Long userId, @RequestBody UserDto userDto) {

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
	public AnimalRescueResponse deleteUser(@PathVariable(name = "userId") Long userId) {

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

	@PostMapping("/signIn")
	public ResponseEntity<Object> signIn(HttpServletResponse response, @RequestBody UserDto userDto) {
		String statusMessage = "";
		String jwt = userService.signIn(userDto);

		if (jwt == null) {
			statusMessage = "Error while Signing In.";
			return ResponseEntity.badRequest().body("Invalid Credentials");
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("access_token", jwt);
		headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization, access_token");
		statusMessage = "User Signed In Successfully.";
		return ResponseEntity.ok().headers(headers).body(statusMessage);

	}

//	@GetMapping("/signInWithGoogle")
//	public AnimalRescueResponse signInWithGoogle(@RequestParam("code") String code, @RequestParam("scope") String scope,
//			@RequestParam("authuser") String authUser, @RequestParam("prompt") String prompt) {
//
//		String jwt = null;
//		try {
//			jwt = userService.signInWithGoogle(code);
//		} catch (AnimalRescueException e) {
//			return new AnimalRescueResponse("Error while signing in. " + e.getLocalizedMessage(),
//					HttpStatus.BAD_REQUEST);
//		}
//
//		return new AnimalRescueResponse(jwt, HttpStatus.OK);
//	}

	@PostMapping("/signInWithGoogle")
	public ResponseEntity<Object> signInWithGoogle(HttpServletResponse response, @RequestBody GoogleUserDto dto) {

		try {

			String statusMessage = "";
			String jwt = userService.signInWithGoogle(dto.getAccessToken());

			if (jwt == null) {
				statusMessage = "Error while Signing In.";
				return ResponseEntity.badRequest().body("Invalid Credentials");
			}

			HttpHeaders headers = new HttpHeaders();
			headers.add("access_token", jwt);
			headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization, access_token");
			statusMessage = "User Signed In Successfully.";
			return ResponseEntity.ok().headers(headers).body(statusMessage);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Internal server error " + e.getLocalizedMessage());
		}
	}
}
