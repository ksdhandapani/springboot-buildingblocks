package com.itsdhandapani.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itsdhandapani.restservices.entities.User;
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
	public User createUser(@RequestBody User user) {
		return this.userService.createUser(user);
	}

	@GetMapping(path = "/users/{id}")
	public Optional<User> getUserById(@PathVariable("id") Long id) {
		return this.userService.getUserById(id);
	}

	@PutMapping(path = "/users/{id}")
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
		return this.userService.updateUserById(id, user);
	}
	
	@DeleteMapping(path="/users/{id}")
	public void deleteById(@PathVariable("id") Long id)
	{
		this.userService.deleteUserById(id);
	}
	
	@GetMapping(path="/users/username/{username}")
	public User getUserByUsername(@PathVariable("username") String username)
	{
		return this.userService.getUserByUsername(username);
	}
}
