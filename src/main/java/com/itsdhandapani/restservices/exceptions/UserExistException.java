package com.itsdhandapani.restservices.exceptions;

public class UserExistException extends Exception {

	private static final long serialVersionUID = -8334380551841655763L;

	public UserExistException(String message) {
		super(message);
	}

}
