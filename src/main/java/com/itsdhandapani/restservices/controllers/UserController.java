package com.itsdhandapani.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.itsdhandapani.restservices.entities.User;
import com.itsdhandapani.restservices.exceptions.UserExistException;
import com.itsdhandapani.restservices.exceptions.UserNotFoundException;
import com.itsdhandapani.restservices.services.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(path = "/users")
	public List<User> getAllUsers() {
		return this.userService.getAllUsers();
	}

	@PostMapping(path = "/users")
	public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder builder) {
		try {
			this.userService.createUser(user);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} catch (UserExistException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping(path = "/users/{id}")
	public Optional<User> getUserById(@PathVariable("id") Long id) {
		try {
			return this.userService.getUserById(id);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}

	@PutMapping(path = "/users/{id}")
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
		try {
			return this.userService.updateUserById(id, user);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@DeleteMapping(path = "/users/{id}")
	public void deleteById(@PathVariable("id") Long id) {
		this.userService.deleteUserById(id);
	}

	@GetMapping(path = "/users/username/{username}")
	public User getUserByUsername(@PathVariable("username") String username) {
		return this.userService.getUserByUsername(username);
	}
}