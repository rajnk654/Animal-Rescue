
package com.bvb.animalrescue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bvb.animalrescue.dto.ContactUsDto;
import com.bvb.animalrescue.rest.AnimalRescueResponse;
import com.bvb.animalrescue.service.ContactService;
import com.bvb.animalrescue.service.GoogleCaptchaVerificationService;

@RestController
@RequestMapping("/contact")
public class ContactController {

	@Autowired
	private GoogleCaptchaVerificationService googleCaptchaVerificationService;

	@Autowired
	private ContactService contactService;

	@PostMapping
	public AnimalRescueResponse addContactForm(@RequestBody ContactUsDto contactUsDto) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			if (googleCaptchaVerificationService.isvalidCaptcha(contactUsDto.getToken())) {
				contactService.saveContactDetails(contactUsDto);
			}
			status = HttpStatus.OK;
		} catch (Exception exception) {
			message = "Failed to send contact data !!" + exception.getLocalizedMessage();
		}
		return new AnimalRescueResponse(message, status);
	}
}
