package com.itsdhandapani.restservices.exceptions;

public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 6935700673272773596L;

	public UserNotFoundException(String message) {
		super(message);
	}

}
