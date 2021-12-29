package com.admissions.entity.validate;

import com.admissions.entity.Option;
import com.admissions.util.Admissions;
import com.admissions.util.exception.AdmissionsException;

public class OptionValidate {
	
	public static void validate(Option option) {
		if(option == null)
			throw new AdmissionsException("No se ha podido validar la opcion.");
		if(!Admissions.isString(option.getDescription()))
			throw new AdmissionsException("No se ha podido validar la descripcion de la opcion.");
		QuestionValidate.validateById(option.getQuestion());
	}
}
