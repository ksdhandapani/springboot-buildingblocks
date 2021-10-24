package com.itsdhandapani.restservices.controllers;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itsdhandapani.restservices.dtos.UserDTOV1;
import com.itsdhandapani.restservices.dtos.UserDTOV2;
import com.itsdhandapani.restservices.entities.User;
import com.itsdhandapani.restservices.exceptions.UserNotFoundException;
import com.itsdhandapani.restservices.services.UserService;

@RestController
@RequestMapping(value = "/versioning/uri/users")
@Validated
public class UserVersioningByUriController {

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	// URI based versioning - V1
	@GetMapping(path = {"/v1.0/{id}","/v1.1/{id}"})
	public UserDTOV1 getUserByIdV1(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {

		Optional<User> userOptional = userService.getUserById(id);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("User with the userId " + id + " is not found");
		} else {
			User user = userOptional.get();
			UserDTOV1 userDTOV1 = modelMapper.map(user, UserDTOV1.class);
			return userDTOV1;
		}

	}
	
	// URI based versioning - V2
	@GetMapping(path = {"/v2.0/{id}"})
	public UserDTOV2 getUserByIdV2(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {

		Optional<User> userOptional = userService.getUserById(id);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("User with the userId " + id + " is not found");
		} else {
			User user = userOptional.get();
			UserDTOV2 userDTOV2 = modelMapper.map(user, UserDTOV2.class);
			return userDTOV2;
		}

	}
}
