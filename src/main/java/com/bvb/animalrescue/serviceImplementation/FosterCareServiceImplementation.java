//package com.bvb.animalrescue.serviceImplementation;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.bvb.animalrescue.dao.AnimalRepository;
//import com.bvb.animalrescue.dao.FosterCareRepository;
//import com.bvb.animalrescue.dao.UserRepository;
//import com.bvb.animalrescue.dto.FosterCareDto;
//import com.bvb.animalrescue.exception.AnimalRescueException;
//import com.bvb.animalrescue.model.Animal;
//import com.bvb.animalrescue.model.Feedback;
//import com.bvb.animalrescue.model.FosterCare;
//import com.bvb.animalrescue.model.User;
//
//@Service
//public class FosterCareServiceImplementation {
//	
//	@Autowired
//	private FosterCareRepository fosterCareRepository;
//	
//	@Autowired
//	private UserRepository userRepository;
//	
//	@Autowired
//	private AnimalRepository animalRepository;
//	
//	public String createFosterCAre(FosterCare fosterCare, Integer userId, Integer animalId) throws AnimalRescueException {
//		try {
//			User user = userRepository.findById(userId).orElse(null);
//			Animal animal = animalRepository.findById(animalId).orElse(null);
//			if (user == null || animal == null) {
//				throw new AnimalRescueException(" Doesn't Exists");
//			}
//			fosterCare.setUser(user);
//			fosterCare.setAnimal(animal);
//			fosterCare.
//			logger.info("Feedback Created Successfully");
//			return "Feedback created Successfully";
//		} catch (Exception exception) {
//			throw new AnimalRescueException(exception.getMessage());
//		}
//	}
//	
//	
//	
//
//}
