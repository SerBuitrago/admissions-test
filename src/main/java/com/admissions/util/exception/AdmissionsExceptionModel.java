package com.admissions.util.exception;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdmissionsExceptionModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String message;
	private String exception;
	private String path;
	private int statu;
	
	public static AdmissionsExceptionModel builder(Exception e, int code) {
		return new AdmissionsExceptionModel(e.getMessage(), e.getClass().getSimpleName(), e.getLocalizedMessage(), code);
	}
}
