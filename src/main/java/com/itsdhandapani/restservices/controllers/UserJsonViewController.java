package com.itsdhandapani.restservices.controllers;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;
import com.itsdhandapani.restservices.entities.User;
import com.itsdhandapani.restservices.entities.Views;
import com.itsdhandapani.restservices.exceptions.UserNotFoundException;
import com.itsdhandapani.restservices.services.UserService;

@RestController
@Validated
@RequestMapping(value="/jsonview/users")
public class UserJsonViewController {

	@Autowired
	private UserService userService;
	
	@JsonView(Views.External.class)
	@GetMapping(path = "/external/{id}")
	public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id) {
		try {
			return this.userService.getUserById(id);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}
	@JsonView(Views.Internal.class)
	@GetMapping(path = "/internal/{id}")
	public Optional<User> getUserById2(@PathVariable("id") @Min(1) Long id) {
		try {
			return this.userService.getUserById(id);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}
}
