package com.itsdhandapani.restservices.controllers;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.itsdhandapani.restservices.dtos.UserMmDTO;
import com.itsdhandapani.restservices.entities.User;
import com.itsdhandapani.restservices.exceptions.UserNotFoundException;
import com.itsdhandapani.restservices.services.UserService;

@RestController
@RequestMapping(value = "/modelMapper/users")
@Validated
public class UserModelMapperController {

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(path = "/{id}")
	public UserMmDTO getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {

		Optional<User> userOptional = userService.getUserById(id);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("User with the userId " + id + " is not found");
		} else {
			User user = userOptional.get();
			UserMmDTO userMmDTO = modelMapper.map(user, UserMmDTO.class);
			return userMmDTO;
		}

	}
}
