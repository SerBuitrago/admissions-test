package com.admissions.entity.validate;

import com.admissions.entity.Question;
import com.admissions.util.Admissions;
import com.admissions.util.exception.AdmissionsException;

public class QuestionValidate {
	public static void validate(Question question) {
		if(question == null)
			throw new AdmissionsException("No se ha podido validar la pregunta.");
		if(!Admissions.isLong(question.getIdTest()))
			throw new AdmissionsException("No se ha podido validar el id del test de la pregunta.");
		if(!Admissions.isString(question.getDescription()))
			throw new AdmissionsException("No se ha podido validar la descripcion de la pregunta.");
		CategoryValidate.validateById(question.getCategory());
	}
	
	public static void validateById(Question question) {
		if(question == null)
			throw new AdmissionsException("No se ha podido validar la pregunta.");
		if(!Admissions.isLong(question.getId()))
			throw new AdmissionsException("No se ha podido validar el id de la pregunta.");
	}
}
