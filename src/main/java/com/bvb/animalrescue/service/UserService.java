package com.bvb.animalrescue.service;

import java.util.List;

import com.bvb.animalrescue.dto.UserDto;
import com.bvb.animalrescue.exception.AnimalRescueException;

public interface UserService {
	public List<UserDto> getUsers() throws AnimalRescueException;
	public String addNewUsers(UserDto dto) throws AnimalRescueException;
	public String deleteUsers(Integer userId) throws AnimalRescueException;
	public String updateUser(Long userId, UserDto dto) throws AnimalRescueException;
	public String deleteUser(Long userId) throws AnimalRescueException;
	public UserDto getUsersByEmail(String email) throws AnimalRescueException;
	public String signIn(UserDto userDto) ;

}
