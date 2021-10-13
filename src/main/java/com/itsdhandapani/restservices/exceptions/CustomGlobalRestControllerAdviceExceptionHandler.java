package com.itsdhandapani.restservices.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
public class CustomGlobalRestControllerAdviceExceptionHandler {

	@ExceptionHandler(UsernameNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public CustomErrorDetail UsernameNotFound(UsernameNotFoundException ex) {
		return new CustomErrorDetail(new Date(),
				"From UsernameNotFound in CustomGlobalRestControllerAdviceExceptionHandler - NOT FOUND",
				ex.getMessage());
	}
}
