package com.admissions.entity.validate;

import com.admissions.entity.Category;
import com.admissions.util.Admissions;
import com.admissions.util.exception.AdmissionsException;

public class CategoryValidate {

	public static void validate(Category category) {
		if(category == null)
			throw new AdmissionsException("No se ha podido validar la categoria.");
		if(!Admissions.isString(category.getName()))
			throw new AdmissionsException("No se ha podido validar el nombre de la categoria.");
	}
	
	public static void validateById(Category category) {
		if(category == null)
			throw new AdmissionsException("No se ha podido validar la categoria.");
		if(!Admissions.isLong(category.getId()))
			throw new AdmissionsException("No se ha podido validar el id de la categoria.");
	}
}
