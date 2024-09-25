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

import com.bvb.animalrescue.dto.BookRescuerDto;
import com.bvb.animalrescue.service.BookRescuerService;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class BookRescuerController {

	@Autowired
	private BookRescuerService orderService;

//
//@GetMapping("/")
//public String home() {
//return "redirect:/index.html";
//

	@PostMapping("/create-order")
	public ResponseEntity<BookRescuerDto> createOrder(@RequestBody BookRescuerDto bookRescuerDto) throws Exception {
		BookRescuerDto createdOrder = orderService.createOrder(bookRescuerDto);
		return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
	}

	@PostMapping("/handle-payment-callback")
	public String handlePaymentCallback(@RequestParam Map<String, String> respPayload) {
		System.out.println("Received Payment Callback Payload: " + respPayload);
		orderService.updateOrder(respPayload);
		return "redirect:/success.html";
	}
}