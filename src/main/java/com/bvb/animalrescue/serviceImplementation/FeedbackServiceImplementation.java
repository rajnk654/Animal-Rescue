package com.bvb.animalrescue.serviceImplementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bvb.animalrescue.dao.AnimalRepository;
import com.bvb.animalrescue.dao.FeedbackRepository;
import com.bvb.animalrescue.dao.UserRepository;
import com.bvb.animalrescue.dto.FeedbackDto;
import com.bvb.animalrescue.exception.AnimalRescueException;
import com.bvb.animalrescue.model.Animal;
import com.bvb.animalrescue.model.Feedback;
import com.bvb.animalrescue.model.User;
import com.bvb.animalrescue.service.FeedbackService;
import com.bvb.animalrescue.util.FeedbackUtil;

@Service
public class FeedbackServiceImplementation implements FeedbackService {

	@Autowired
	private FeedbackRepository feedbackRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AnimalRepository animalRepository;

	private Logger logger = LoggerFactory.getLogger(FeedbackServiceImplementation.class);

	public String createFeedback(Feedback feedback, Integer userId, Integer animalId) throws AnimalRescueException {
		try {
			User user = userRepository.findById(userId).orElse(null);
			Animal animal = animalRepository.findById(animalId).orElse(null);
			if (user == null || feedback == null) {
				throw new AnimalRescueException("User Doesn't Exists");
			}
			feedback.setUser(user);
			feedback.setAnimal(animal);
			feedbackRepository.save(feedback);
			logger.info("Feedback Created Successfully");
			return "Feedback created Successfully";
		} catch (Exception exception) {
			throw new AnimalRescueException(exception.getMessage());
		}
	}

// Retrieve all feedbacks
	public List<FeedbackDto> getAllFeedbacks() throws AnimalRescueException {
		try {
			List<FeedbackDto> listOfDtos = feedbackRepository.findAll().stream()
					.map(FeedbackUtil::convertFeedbackEntityToDto).collect(Collectors.toList());
			return listOfDtos;
		} catch (Exception exception) {
			throw new AnimalRescueException(exception.getMessage());
		}
	}

// Retrieve a specific feedback by ID
	public Feedback getFeedbackById(Integer id) throws AnimalRescueException {
		try {
			return feedbackRepository.findById(id).orElse(null);
		} catch (Exception exception) {
			throw new AnimalRescueException(exception.getMessage());
		}
	}

	// Update an existing feedback
//	public Feedback updateFeedback(Integer id, Feedback updatedFeedback) {
//		Feedback existingFeedback = getFeedbackById(Integerid);
//		if (existingFeedback != null) {
//			existingFeedback.setUser(updatedFeedback.getUser());
//			existingFeedback.setFeedbackDescription(updatedFeedback.getFeedbackDescription());
//			existingFeedback.setRating(updatedFeedback.getRating());
//			existingFeedback.setCreatedAt(updatedFeedback.getCreatedAt());
//			return feedbackRepository.save(existingFeedback);
//		}
//		return null;
//	}

	// Delete a feedback by ID
	public String deleteFeedback(Integer id) throws AnimalRescueException {
		Optional<Feedback> feedback = feedbackRepository.findById(id);
		try {
			if (feedback.isPresent()) {
				feedbackRepository.deleteById(id);
				logger.info("Feedback Data Has Been Deleted Successfuly");
				return "Feedback  Has deleted successfully.";
			} else {
				throw new AnimalRescueException("Feedback Doesn't Exists");
			}
		} catch (Exception exception) {
			throw new AnimalRescueException("Feedback doesn't exist." + exception.getMessage());
		}

	}
}
