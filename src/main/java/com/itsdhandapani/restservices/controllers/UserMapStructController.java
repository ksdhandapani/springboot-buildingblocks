package com.itsdhandapani.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itsdhandapani.restservices.dtos.UserMsDTO;
import com.itsdhandapani.restservices.entities.User;
import com.itsdhandapani.restservices.exceptions.UserNotFoundException;
import com.itsdhandapani.restservices.mappers.UserMapper;
import com.itsdhandapani.restservices.services.UserService;

@RestController
@RequestMapping(value = "/mapStruct/users")
public class UserMapStructController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserMapper userMapper;
	
	@GetMapping
	List<UserMsDTO> getAllUsers() {
		List<User> users = this.userService.getAllUsers();
		List<UserMsDTO> userMsDTOs = this.userMapper.usesToUserMsDTOs(users);
		return userMsDTOs;
	}
	
	@GetMapping(path="/{userId}")
	UserMsDTO getUserById(@PathVariable("userId")Long userId) throws UserNotFoundException
	{
		Optional<User> userOptional = this.userService.getUserById(userId);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("Use with the id "+userId+"not found");
		} else {
			User user = userOptional.get();
			UserMsDTO userMsDTO = this.userMapper.userToUserMsDTO(user);
			return userMsDTO;
		}
	}

}
