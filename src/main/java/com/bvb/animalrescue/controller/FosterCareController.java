//package com.bvb.animalrescue.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Repository;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.bvb.animalrescue.exception.AnimalRescueException;
//import com.bvb.animalrescue.model.Feedback;
//import com.bvb.animalrescue.model.FosterCare;
//import com.bvb.animalrescue.rest.AnimalRescueResponse;
//import com.bvb.animalrescue.serviceImplementation.FeedbackServiceImplementation;
//import com.bvb.animalrescue.serviceImplementation.FosterCareServiceImplementation;
//
//@RestController
//@RequestMapping(path="/feedback")
//public class FosterCareController {
//	
//	@Autowired
//	private FosterCareServiceImplementation fosterCareService;
//	
//	@PostMapping("{userId}/{animalId}")
//	public AnimalRescueResponse createFeedback(@PathVariable(name = "userId") Integer userId,
//			@PathVariable(name = "animalId") Integer animalId, @RequestBody FosterCare fosterCare) {
//		HttpStatus status = HttpStatus.BAD_REQUEST;
//		String message = "";
//		try {
//			String dto = fosterCareService.createFeedback(fosterCare, userId, animalId);
//			if (dto != null) {
//				status = HttpStatus.OK;
//				return new AnimalRescueResponse(dto, status);
//			}
//		} catch (AnimalRescueException exception) {
//			message = "Failed to register Animal !!" + exception.getLocalizedMessage();
//
//		} catch (Exception exception) {
//			message = "Internal Server Error !!" + exception.getLocalizedMessage();
//		}
//		return new AnimalRescueResponse(message, status);
//	}


