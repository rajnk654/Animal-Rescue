package com.bvb.animalrescue.util;

import org.springframework.beans.BeanUtils;

import com.bvb.animalrescue.dto.UserDto;
import com.bvb.animalrescue.model.User;

public class UserUtil {

	public static UserDto convertUsersEntityToDto(User user) {
		UserDto dto = new UserDto();
		BeanUtils.copyProperties(user, dto);
		return dto;
	}

	public static User convertUsersDtoToEntity(UserDto dto) {
		User user = new User();
		BeanUtils.copyProperties(dto, user);
		return user;
	}

}
