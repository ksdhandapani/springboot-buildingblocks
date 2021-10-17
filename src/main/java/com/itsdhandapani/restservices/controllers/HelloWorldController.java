package com.itsdhandapani.restservices.controllers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itsdhandapani.restservices.entities.UserDetail;

@RestController
public class HelloWorldController {

	@Autowired
	private ResourceBundleMessageSource messageSource;

	@RequestMapping(method = RequestMethod.GET, path = "/helloWorld")
	public String helloWorld() {
		return "Hello World!";
	}

	@GetMapping(path = "/helloWorld-bean")
	public UserDetail helloWorldBean() {
		return new UserDetail("Dhandapani", "Sudhakar", "Erode");
	}

	@GetMapping(path = "/hello-int")
	public String getMessageInI18NFormat(@RequestHeader(name = "Accept-Language", required = false) String locale) {
		return messageSource.getMessage("label.hello", null, new Locale(locale));
	}
	
	@GetMapping(path = "/hello-int2")
	public String getMessageInI18NFormat2() {
		return messageSource.getMessage("label.hello", null, LocaleContextHolder.getLocale());
	}
}
