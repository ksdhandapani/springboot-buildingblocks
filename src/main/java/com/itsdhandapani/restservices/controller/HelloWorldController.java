package com.itsdhandapani.restservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itsdhandapani.restservices.entity.UserDetail;

@RestController
public class HelloWorldController {

	@RequestMapping(method = RequestMethod.GET, path = "/helloWorld")
	public String helloWorld() {
		return "Hello World!";
	}

	@GetMapping(path = "/helloWorld-bean")
	public UserDetail helloWorldBean() {
		return new UserDetail("Dhandapani", "Sudhakar", "Erode");
	}
}
