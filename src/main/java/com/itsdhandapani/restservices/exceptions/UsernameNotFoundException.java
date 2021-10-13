package com.itsdhandapani.restservices.exceptions;

public class UsernameNotFoundException extends Exception {

	private static final long serialVersionUID = -5886073846185537472L;
	
	public UsernameNotFoundException(String message)
	{
		super(message);
	}
}
