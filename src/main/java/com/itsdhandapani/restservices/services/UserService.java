package com.itsdhandapani.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.itsdhandapani.restservices.entities.User;
import com.itsdhandapani.restservices.exceptions.UserExistException;
import com.itsdhandapani.restservices.exceptions.UserNotFoundException;
import com.itsdhandapani.restservices.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}

	public User createUser(User user) throws UserExistException {
		User existingUserWithUsername = this.userRepository.findUserByUsername(user.getUsername());
		if (existingUserWithUsername != null) {
			throw new UserExistException(
					"User with the given username is already present, Try with the different username");
		}

		User existingUserWithEmail = this.userRepository.findUserByEmail(user.getEmail());
		if (existingUserWithEmail != null) {
			throw new UserExistException("User with the given email is already present, Try with the different email");
		}

		return this.userRepository.save(user);
	}

	public Optional<User> getUserById(Long id) throws UserNotFoundException {
		Optional<User> user = this.userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User with the Id : " + id + " not found");
		}
		return user;
	}

	public User updateUserById(Long id, User user) throws UserNotFoundException {
		Optional<User> optionalUser = this.userRepository.findById(id);
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("User with the Id : " + id + " not found");
		}
		user.setId(id);
		return this.userRepository.save(user);
	}

	public void deleteUserById(Long id) {
		Optional<User> optionalUser = this.userRepository.findById(id);
		if (!optionalUser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with the Id : " + id + " not found");
		}

		this.userRepository.deleteById(id);
	}

	public User getUserByUsername(String username) {
		return this.userRepository.findUserByUsername(username);

	}

}
