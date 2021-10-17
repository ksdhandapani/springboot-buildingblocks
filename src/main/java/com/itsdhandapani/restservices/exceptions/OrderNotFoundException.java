package com.itsdhandapani.restservices.exceptions;

public class OrderNotFoundException extends Exception {

	private static final long serialVersionUID = -6632376898249175314L;

	public OrderNotFoundException(String message) {
		super(message);
	}

}
