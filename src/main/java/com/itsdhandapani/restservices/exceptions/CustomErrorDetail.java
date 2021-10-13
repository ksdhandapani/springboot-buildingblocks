package com.itsdhandapani.restservices.exceptions;

import java.util.Date;

public class CustomErrorDetail {
	private Date timestamp;
	private String message;
	private String errorDetail;

	public CustomErrorDetail(Date timestamp, String message, String errorDetail) {
		this.timestamp = timestamp;
		this.message = message;
		this.errorDetail = errorDetail;
	}

	public CustomErrorDetail() {
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorDetail() {
		return errorDetail;
	}

	public void setErrorDetail(String errorDetail) {
		this.errorDetail = errorDetail;
	}

}
