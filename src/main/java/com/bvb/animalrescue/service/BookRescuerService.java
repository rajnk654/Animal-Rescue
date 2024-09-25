package com.bvb.animalrescue.service;

import java.util.Map;

import com.bvb.animalrescue.dto.BookRescuerDto;
import com.bvb.animalrescue.model.BookRescuer;

public interface BookRescuerService {

	public BookRescuerDto createOrder(BookRescuerDto bookRescuerDto) throws Exception;
	public BookRescuer updateOrder(Map<String,String> responsePayLoad);
}
