package com.bvb.animalrescue.service;

import java.util.List;

import com.bvb.animalrescue.dto.FeedbackDto;
import com.bvb.animalrescue.exception.AnimalRescueException;
import com.bvb.animalrescue.model.Feedback;

public interface FeedbackService {
	
	public String createFeedback(Feedback feedback, Integer userId,Integer animalId)throws AnimalRescueException;
	public List<FeedbackDto> getAllFeedbacks() throws AnimalRescueException;
	public Feedback getFeedbackById(Integer id) throws AnimalRescueException;
	public String deleteFeedback(Integer id) throws AnimalRescueException;
}
