package com.bvb.animalrescue.serviceImplementation;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bvb.animalrescue.dao.RoleRepository;
import com.bvb.animalrescue.dao.UserRepository;
import com.bvb.animalrescue.dto.UserDto;
import com.bvb.animalrescue.exception.AnimalRescueException;
import com.bvb.animalrescue.model.Role;
import com.bvb.animalrescue.model.User;
import com.bvb.animalrescue.security.SecurityConstants;
import com.bvb.animalrescue.service.UserService;
import com.bvb.animalrescue.util.GoogleOAuthUtil;
import com.bvb.animalrescue.util.UserUtil;
//import com.fasterxml.jackson.core.JsonParser;
//import com.nimbusds.jose.shaded.gson.JsonObject;
import com.nimbusds.jose.shaded.gson.JsonObject;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private RoleRepository roleRepository;

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

	// Retrieve The User Data

	public UserDto getUsersByEmail(String email) throws AnimalRescueException {
		try {
			Optional<User> user = userRepository.findByEmail(email);

			if (user.isPresent()) {
				UserDto userDto = UserUtil.convertUsersEntityToDto(user.get());
				return userDto;
			} else {
				throw new AnimalRescueException("User not found for the given email.");
			}

		} catch (Exception exception) {
			throw new AnimalRescueException("Error while retrieving user data: " + exception.getMessage());
		}
	}

//Adding New User Data
	public String addNewUsers(UserDto dto) throws AnimalRescueException {
		try {
			User user = UserUtil.convertUsersDtoToEntity(dto);

			if (user == null) {
				throw new Exception("Empty Data IS Not Allowed");
			}
			user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
			user.setCreatedAt(LocalDateTime.now());
			user.setLastModifiedAt(LocalDateTime.now());
			user.setIsEmailVarified(Boolean.FALSE);
			user.setIsPhoneNumberVarified(Boolean.FALSE);
			user.setEnabled(Boolean.TRUE);

			Role userRole = roleRepository.findByName("USER");
			user.getRoles().add(userRole);

			user = userRepository.save(user);
			userRepository.save(user);

			dto = UserUtil.convertUsersEntityToDto(user);
			logger.info("User has Inserted successfully");
			return "User' " + dto.getEmail() + "' has Successfully inserted.";

		} catch (Exception exception) {
			logger.error("Error In Creating User" + exception.getMessage());
			throw new AnimalRescueException("Internal Server Error" + exception.getMessage());
		}
	}

	public String deleteUsers(Long userId) throws AnimalRescueException {
		Optional<User> user = userRepository.findById(userId);
		try {
			if (user.isPresent()) {
				userRepository.deleteById(userId);
				String name = user.get().getFirstName();
				logger.info("User With " + name + "has been deleted Successfully");
				return "User " + name + "Has deleted Successfully.";
			} else {
				throw new AnimalRescueException("User doesn't exist");
			}
		} catch (Exception exception) {
			throw new AnimalRescueException("User Doesn't exist." + exception.getLocalizedMessage());
		}

	}

	@Transactional
	public String updateUser(Long userId, UserDto dto) throws AnimalRescueException {
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

		// Update Profile pic
		if (user.getProfilePic() != null && user.getProfilePic().length() > 0
				&& !Objects.equals(user.getProfilePic(), existingUser.getProfilePic())) {
			existingUser.setProfilePic(user.getProfilePic());
		}

		// Update User LastName
		if (user.getLastName() != null && user.getLastName().length() > 0
				&& !Objects.equals(user.getLastName(), existingUser.getLastName())) {
			existingUser.setLastName(user.getLastName());
		}
		return "User updated Successfully";
	}

	@Override
	public String signIn(UserDto userDto) {
		String jwt = null;
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
		if (null != authentication) {
			User user = userRepository.findByEmail(userDto.getEmail()).get();
			SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
			jwt = Jwts.builder().setIssuer("AGRO_GENIUS").setSubject("JWT Token").claim("userId", user.getId())
					.claim("username", authentication.getName()).claim("email", userDto.getEmail())
					.claim("authorities", populateAuthorities(authentication.getAuthorities())).setIssuedAt(new Date())
					.setExpiration(new Date((new Date()).getTime() + 30000000)).signWith(key).compact();
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return jwt;
	}

	private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
		Set<String> authoritiesSet = new HashSet<>();
		for (GrantedAuthority authority : collection) {
			authoritiesSet.add(authority.getAuthority());
		}
		return String.join(",", authoritiesSet);
	}

	public String signInWithGoogle(String accessToken) throws AnimalRescueException {
		UserDto userDto = null;
		try {
			userDto = getUserDtoFromGoogleAccessToken(accessToken);
			Optional<User> user = userRepository.findByEmail(userDto.getEmail());
			if (user.isEmpty()) {

				this.addNewUsers(userDto);

			}
		} catch (Exception e) {
			throw new AnimalRescueException("Error while signing in. " + e.getLocalizedMessage());
		}
		return signIn(userDto);

	}

	private UserDto getUserDtoFromGoogleAccessToken(String accessToken) {
		UserDto usertDto = new UserDto();
		JsonObject jsonObject = GoogleOAuthUtil.getProfileDetailsGoogle(accessToken);
		return populateUserDetailsFromGoogleResPonse(usertDto, jsonObject);

	}

	private UserDto populateUserDetailsFromGoogleResPonse(UserDto userDto, JsonObject jsonObject) {
		userDto.setFirstName(jsonObject.get("given_name").toString().replaceAll("\"", ""));
		userDto.setLastName(jsonObject.get("family_name").toString().replaceAll("\"", ""));
		Double phone = Math.random();
		userDto.setPhoneNumber(phone.toString().substring(2, 12) + "_RANDOM");
		userDto.setEmail(jsonObject.get("email").toString().replaceAll("\"", ""));
		userDto.setPassword(userDto.getEmail());
		userDto.setIsGoogleUser(Boolean.TRUE);
		userDto.setProfilePic(jsonObject.get("picture").toString().replaceAll("\"", ""));
		// "picture" to get profile picture
		return userDto;

	}

	@Override
	public String deleteUsers(Integer userId) throws AnimalRescueException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteUser(Long userId) throws AnimalRescueException {
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
