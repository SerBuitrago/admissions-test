package com.admissions.util.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdmissionsExceptionHandler {

	private static final Map<String, Integer> STATUS = new HashMap<>();
	
	@ExceptionHandler(AdmissionsException.class)
	public final ResponseEntity<AdmissionsExceptionModel> AllExceptions(HttpServletRequest request, Exception exception) {
		ResponseEntity<AdmissionsExceptionModel> result;
		Integer code = getStatus(exception);
		code = (code == null) ? HttpStatus.INTERNAL_SERVER_ERROR.value() : code;
		AdmissionsExceptionModel error = new AdmissionsExceptionModel(exception.getMessage(),
				exception.getClass().getSimpleName(), request.getRequestURI(), code);
		result = new ResponseEntity<>(error, HttpStatus.valueOf(code));
		exception.printStackTrace();
		return result;
	}

	private Integer getStatus(Exception e) {
		if (e instanceof AdmissionsException) {
			AdmissionsException ex = (AdmissionsException) e;
			if (ex.getHttpStatus() != null) {
				return ex.getHttpStatus().value();
			}
		}
		return STATUS.get(e.getClass().getSimpleName());
	}
}
