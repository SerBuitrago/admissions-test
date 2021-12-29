package com.admissions.util.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class AdmissionsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private HttpStatus httpStatus;

	
	public AdmissionsException(String message) {
		this(HttpStatus.NOT_FOUND, message);
	}
	
	public AdmissionsException(HttpStatus httpStatus, String message) {
		super(message);
		this.httpStatus = httpStatus;
	}
	
}
