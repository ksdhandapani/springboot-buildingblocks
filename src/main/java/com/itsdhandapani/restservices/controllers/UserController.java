package com.itsdhandapani.restservices.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.itsdhandapani.restservices.entities.User;
import com.itsdhandapani.restservices.exceptions.UserExistException;
import com.itsdhandapani.restservices.exceptions.UserNotFoundException;
import com.itsdhandapani.restservices.exceptions.UsernameNotFoundException;
import com.itsdhandapani.restservices.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "User Management RESTful Services", value="UserController", description = "Controller for User Management Service" )
@RestController
@Validated
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "Retrieve List of Users")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllUsers() {
		return this.userService.getAllUsers();
	}
	
	@ApiOperation(value = "Create a new User")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createUser(@ApiParam("User information for a new User to be created") @Valid @RequestBody User user, UriComponentsBuilder builder) {
		try {
			this.userService.createUser(user);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} catch (UserExistException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@ApiOperation(value = "Retrieve an User by Id")
	@GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public User getUserById(@PathVariable("id") @Min(1) Long id) {
		try {
			return this.userService.getUserById(id).get();
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}
	
	@ApiOperation(value = "Update the User by Id")
	@PutMapping(path = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
		try {
			return this.userService.updateUserById(id, user);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@ApiOperation(value = "Delete an User by Id")
	@DeleteMapping(path = "/{id}")
	public void deleteById(@PathVariable("id") Long id) {
		this.userService.deleteUserById(id);
	}

	@ApiOperation(value = "Retrieve an User by Username")
	@GetMapping(path = "/username/{username}",produces = MediaType.APPLICATION_JSON_VALUE)
	public User getUserByUsername(@PathVariable("username") String username) throws UsernameNotFoundException {
		User user = this.userService.getUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User with the username '" + username + "' is not found");
		} else {
			return user;
		}
	}
}
