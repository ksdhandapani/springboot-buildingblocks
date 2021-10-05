package com.itsdhandapani.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsdhandapani.restservices.entities.User;
import com.itsdhandapani.restservices.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}

	public User createUser(User user) {
		return this.userRepository.save(user);
	}

	public Optional<User> getUserById(Long id) {
		Optional<User> user = this.userRepository.findById(id);
		return user;
	}

	public User updateUserById(Long id, User user) {
		user.setId(id);
		return this.userRepository.save(user);
	}
	
	public void deleteUserById(Long id)
	{
		if(this.userRepository.findById(id).isPresent())
		{
			this.userRepository.deleteById(id);
		}
	}
	
	public User getUserByUsername(String username)
	{
		return this.userRepository.findUserByUsername(username);
		
	}

}
