package com.itsdhandapani.restservices.exceptions;

import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice // It is globally applicable to all the controllers
public class CustomGlobalControllerAdviceExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomErrorDetail customErrorDetail = new CustomErrorDetail(new Date(),
				"From handleMethodArgumentNotValid in CustomGlobalExceptionHandler", ex.getMessage());
		return new ResponseEntity<Object>(customErrorDetail, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomErrorDetail customErrorDetail = new CustomErrorDetail(new Date(),
				"From handleHttpRequestMethodNotSupported in CustomGlobalExceptionHandler", ex.getMessage());
		return new ResponseEntity<Object>(customErrorDetail, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public final ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException ex,
			WebRequest request) {
		CustomErrorDetail customErrorDetail = new CustomErrorDetail(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(customErrorDetail, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,
			WebRequest request) {
		CustomErrorDetail customErrorDetail = new CustomErrorDetail(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(customErrorDetail, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
		CustomErrorDetail customErrorDetail = new CustomErrorDetail(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(customErrorDetail, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(OrderNotFoundException.class)
	public final ResponseEntity<Object> handleOrderNotFoundException(OrderNotFoundException ex, WebRequest request) {
		CustomErrorDetail customErrorDetail = new CustomErrorDetail(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(customErrorDetail, HttpStatus.NOT_FOUND);
	}

}
