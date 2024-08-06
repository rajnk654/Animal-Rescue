package com.bvb.animalrescue.serviceImplementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bvb.animalrescue.dao.RescuerRepository;
import com.bvb.animalrescue.dao.UserRepository;
import com.bvb.animalrescue.dto.RescuerDto;
import com.bvb.animalrescue.dto.UserDto;
import com.bvb.animalrescue.exception.AnimalRescueException;
import com.bvb.animalrescue.model.Rescuer;
import com.bvb.animalrescue.model.User;
import com.bvb.animalrescue.service.RescuerService;
import com.bvb.animalrescue.util.RescuerUtil;
import com.bvb.animalrescue.util.UserUtil;

import jakarta.transaction.Transactional;

@Service
public class RescuerServiceImplementation implements RescuerService {

	@Autowired
	private RescuerRepository rescuerRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserServiceImplementation userService;
	
	private Logger logger = LoggerFactory.getLogger(RescuerServiceImplementation.class);

	public String addNewRescuer(UserDto userDto) throws AnimalRescueException {
		User user = UserUtil.convertUsersDtoToEntity(userDto);
		try {
			userRepository.save(user);
			Rescuer rescuer = RescuerUtil.convertRescuerDtoToEntity(userDto.getRescuerDto());
			rescuer.setUser(user);
			rescuerRepository.save(rescuer);
			logger.info("Rescuered Registered Successfully");
			return "Rescuer Registered Successfully";
		} catch (DataIntegrityViolationException exception) {
			throw new AnimalRescueException("Rescuer data already Exsists.." + exception.getLocalizedMessage());
		}

	}

	public List<RescuerDto> getAllRescuers() throws AnimalRescueException {
		try {
			List<RescuerDto> listOfDtos = rescuerRepository.findAll().stream()
					.map(RescuerUtil::convertRescuerEntityToDto).collect(Collectors.toList());
			return listOfDtos;
		} catch (Exception exception) {
			throw new AnimalRescueException(exception.getLocalizedMessage());
		}
	}

	public Rescuer getRescuerById(Integer id) throws AnimalRescueException {
		try {
			return rescuerRepository.findById(id).orElse(null);
		} catch (Exception exception) {
			throw new AnimalRescueException(exception.getMessage());
		}
	}

	@Transactional
	public String updateRescuer(Integer id, UserDto userDto) throws AnimalRescueException {
		Rescuer existingRescuer = rescuerRepository.findById(id).get();
		if (existingRescuer == null) {
			throw new AnimalRescueException("Rescuer is not found");
		}
		Integer userId = existingRescuer.getUser().getId();
		String message = userService.updateUser(userId, userDto);
		return message;

	}

	// Delete a rescuer by ID
	public String deleteRescuer(Integer id) throws AnimalRescueException {
		Optional<Rescuer> rescuer = rescuerRepository.findById(id);
		try {
			if (rescuer.isPresent()) {
				rescuerRepository.deleteById(id);
				String name = rescuer.get().getUser().getFirstName();
				logger.info("Rescuer with"+name+"has deleted Successfully");
				return "Rescuer " + name + " Has deleted successfully.";
			} else {
				throw new AnimalRescueException("Rescuer Doesn't Exists");
			}
		} catch (Exception exception) {
			throw new AnimalRescueException("Rescuer doesn't exist." + exception.getLocalizedMessage());
		}
	}
}
