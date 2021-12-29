package com.admissions.entity.validate;

import com.admissions.entity.Test;
import com.admissions.util.Admissions;
import com.admissions.util.exception.AdmissionsException;

public class TestValidate {
	public static void validate(Test test) {
		if(test == null)
			throw new AdmissionsException("No se ha podido validar la prueba.");
		if(!Admissions.isString(test.getName()))
			throw new AdmissionsException("No se ha podido validar el nombre de la prueba.");
	}
}
