package com.itsdhandapani.restservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itsdhandapani.restservices.entities.User;

@Repository(value = "userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

	public User findUserByUsername(String username);
}
