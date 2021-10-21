package com.itsdhandapani.restservices.controllers;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.itsdhandapani.restservices.entities.User;
import com.itsdhandapani.restservices.exceptions.UserNotFoundException;
import com.itsdhandapani.restservices.services.UserService;

@RestController
@RequestMapping(value = "/jacksonfilters/users")
@Validated
public class UserMappingJacksonController {

	@Autowired
	private UserService userService;

	// Fields with HashSet
	@GetMapping(path = "/{id}")
	public MappingJacksonValue getUserById(@PathVariable("id") @Min(1) Long id) {
		try {
			Optional<User> userOptional = this.userService.getUserById(id);
			User user = userOptional.get();

			Set<String> fields = new HashSet<String>();
			fields.add("id");
			fields.add("username");
			fields.add("ssn");
			fields.add("orders");
			FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter",
					SimpleBeanPropertyFilter.filterOutAllExcept(fields));
			MappingJacksonValue mapper = new MappingJacksonValue(user);
			mapper.setFilters(filterProvider);
			return mapper;

		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}
	
	// Fields with @RequestParam
	@GetMapping(path = "/params/{id}")
	public MappingJacksonValue getUserById2(@PathVariable("id") @Min(1) Long id, @RequestParam("fields") Set<String> fields) {
		try {
			Optional<User> userOptional = this.userService.getUserById(id);
			User user = userOptional.get();
			FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter",
					SimpleBeanPropertyFilter.filterOutAllExcept(fields));
			MappingJacksonValue mapper = new MappingJacksonValue(user);
			mapper.setFilters(filterProvider);
			return mapper;

		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}
}
