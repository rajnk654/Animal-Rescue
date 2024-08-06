package com.bvb.animalrescue.serviceImplementation;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bvb.animalrescue.dao.UserRepository;
import com.bvb.animalrescue.dto.UserDto;
import com.bvb.animalrescue.exception.AnimalRescueException;
import com.bvb.animalrescue.model.User;
import com.bvb.animalrescue.service.UserService;
import com.bvb.animalrescue.util.UserUtil;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImplementation implements UserService{

	private final UserRepository userRepository;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class);

	public UserServiceImplementation(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
//Retrieve The User Data
	public List<UserDto> getUsers() throws AnimalRescueException {
		try {
			List<UserDto> listOfDtos = userRepository.findAll().stream().map(UserUtil::convertUsersEntityToDto)
					.collect(Collectors.toList());

			return listOfDtos;
		} catch (Exception exception) {
			throw new AnimalRescueException(exception.getMessage());
		}
	}
	
//Adding New User Data
	public String addNewUsers(UserDto dto) throws AnimalRescueException {
		try {
			User user = UserUtil.convertUsersDtoToEntity(dto);

			if (user == null) {
				throw new Exception("Empty Data IS Not Allowed");
			}
			userRepository.save(user);

			dto = UserUtil.convertUsersEntityToDto(user);
			logger.info("User has found successfully");
			return "User' " + dto.getEmail() + "' has Successfully inserted.";

		} catch (Exception exception) {
			logger.error("Error In Creating User");
			throw new AnimalRescueException("Internal Server Error" + exception.getMessage());
		}
	}

	public String deleteUsers(Integer userId) throws AnimalRescueException {
		Optional<User> user = userRepository.findById(userId);
		try {
			if (user.isPresent()) {
				userRepository.deleteById(userId);
				String name = user.get().getFirstName();
				logger.info("User With "+name+"has been deleted Successfully");
				return "User " + name + "Has deleted Successfully.";
			} else {
				throw new AnimalRescueException("User doesn't exist");
			}
		} catch (Exception exception) {
			throw new AnimalRescueException("User Doesn't exist." + exception.getLocalizedMessage());
		}

	}

	@Transactional
	public String updateUser(Integer userId, UserDto dto) throws AnimalRescueException {
		User user = UserUtil.convertUsersDtoToEntity(dto);
		User existingUser = userRepository.findById(userId).get();

		if (existingUser == null) {
			throw new AnimalRescueException("User not Found");
		}
		// Update Email
		if (user.getEmail() != null && user.getEmail().length() > 0
				&& !Objects.equals(user.getEmail(), existingUser.getEmail())) {
			existingUser.setEmail(user.getEmail());
		}

		// Update PhoneNumber
		if (user.getPhoneNumber() != null && user.getPhoneNumber().length() > 0
				&& !Objects.equals(user.getPhoneNumber(), existingUser.getPhoneNumber())) {
			existingUser.setPhoneNumber(user.getPhoneNumber());
		}

		// Update User FirstName
		if (user.getFirstName() != null && user.getFirstName().length() > 0
				&& !Objects.equals(user.getFirstName(), existingUser.getFirstName())) {
			existingUser.setFirstName(user.getFirstName());
		}

		//Update User LastName
		if (user.getLastName() != null && user.getLastName().length() > 0
				&& !Objects.equals(user.getLastName(), existingUser.getLastName())) {
			existingUser.setLastName(user.getLastName());
		}
		return "User updated Successfully";
	}
// Deleting The Data Of The User
	public String deleteUser(Integer userId) throws AnimalRescueException {
		Optional<User> user = userRepository.findById(userId);
		try {
			if (user.isPresent()) {
				userRepository.deleteById(userId);
				String name = user.get().getFirstName();
				return "User " + name + " Has deleted successfully.";
			} else {
				throw new AnimalRescueException("User doesn't exist.");
			}
		} catch (Exception e) {
			throw new AnimalRescueException("User doesn't exist." + e.getLocalizedMessage());
		}
	}
}
