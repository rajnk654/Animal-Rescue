package com.bvb.animalrescue.rest;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class AnimalRescueResponse extends ResponseEntity<Object>{

	public AnimalRescueResponse(Object body, HttpStatusCode status) {
		super(body, status);
		
	}

}
