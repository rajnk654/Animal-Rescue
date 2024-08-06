package com.bvb.animalrescue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bvb.animalrescue.dto.FeedbackDto;
import com.bvb.animalrescue.exception.AnimalRescueException;
import com.bvb.animalrescue.model.Feedback;
import com.bvb.animalrescue.rest.AnimalRescueResponse;
import com.bvb.animalrescue.service.FeedbackService;

@RestController
@RequestMapping(path = "/feedback")
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackService;

	@GetMapping
	public AnimalRescueResponse getAllFeedbacks() {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			List<FeedbackDto> listOfDto = feedbackService.getAllFeedbacks();
			status = HttpStatus.OK;
			return new AnimalRescueResponse(listOfDto, status);
		} catch (AnimalRescueException exception) {
			message = "Failed to retrieve Feedback data !!" + exception.getLocalizedMessage();
		} catch (Exception exception) {
			message = "Internal Server Error !!" + exception.getLocalizedMessage();
		}
		return new AnimalRescueResponse(message, status);
	}

	@PostMapping("{userId}/{animalId}")
	public AnimalRescueResponse createFeedback(@PathVariable(name = "userId") Integer userId,
			@PathVariable(name = "animalId") Integer animalId, @RequestBody Feedback feedback) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			String dto = feedbackService.createFeedback(feedback, userId, animalId);
			if (dto != null) {
				status = HttpStatus.OK;
				return new AnimalRescueResponse(dto, status);
			}
		} catch (AnimalRescueException exception) {
			message = "Failed to register Animal !!" + exception.getLocalizedMessage();

		} catch (Exception exception) {
			message = "Internal Server Error !!" + exception.getLocalizedMessage();
		}
		return new AnimalRescueResponse(message, status);
	}

	@GetMapping("/{id}")
	public AnimalRescueResponse getFeedbackById(Integer id) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			Feedback feedback = feedbackService.getFeedbackById(id);
			status = HttpStatus.OK;
			if (feedback == null) {
				throw new AnimalRescueException("Empty Feedback Data");
			}
			return new AnimalRescueResponse(feedback, status);
		} catch (AnimalRescueException exception) {
			message = "Failed to retrieve feedback id !!" + exception.getLocalizedMessage();
		}
		return new AnimalRescueResponse(message, status);

	}

	// PUT: Update an existing feedback
//	@PutMapping("/{id}")
//	public ResponseEntity<Feedback> updateFeedback(@PathVariable Integer id, @RequestBody Feedback feedback) {
//		Feedback updatedFeedback = feedbackServiceImplementation.updateFeedback(id, feedback);
//		if (updatedFeedback != null) {
//			return ResponseEntity.ok(updatedFeedback); // HTTP 200 OK
//		} else {
//			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
//		}
//	}

	// DELETE: Remove a feedback by ID
	@DeleteMapping("/{id}")
	public AnimalRescueResponse deleteFeedback(@PathVariable Integer id) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			message = feedbackService.deleteFeedback(id);
		} catch (AnimalRescueException exception) {
			message = "Failed to Delete Feedback !!" + exception.getMessage();
		} catch (Exception exception) {
			message = "Internal Server Error!!" + exception.getMessage();
		}
		return new AnimalRescueResponse(message, status);
	}
}
