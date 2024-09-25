package com.bvb.animalrescue.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bvb.animalrescue.dto.AnimalAdoptionDto;
import com.bvb.animalrescue.service.AnimalAdoptionService;

@RestController
@RequestMapping("/adopt")
@CrossOrigin
public class AnimalAdoptionController {

	@Autowired
	private AnimalAdoptionService animalAdoptionService;

	@PostMapping("/adopt-animal")
	public ResponseEntity<AnimalAdoptionDto> createOrder(@RequestBody AnimalAdoptionDto animalAdoptionDto)
			throws Exception {
		AnimalAdoptionDto createdOrder = animalAdoptionService.createOrder(animalAdoptionDto);
		return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
	}

	@PostMapping("/handle-payment-callback")
	public String handlePaymentCallback(@RequestParam Map<String, String> respPayload) {
		System.out.println("Received Payment Callback Payload: " + respPayload);
		animalAdoptionService.updateOrder(respPayload);
		return "redirect:/success.html";
	}
}