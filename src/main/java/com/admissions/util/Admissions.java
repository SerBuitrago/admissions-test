package com.admissions.util;

import java.util.List;

public class Admissions {

	public static boolean isString(String string) {
		return string != null && string.trim().length() > 0;
	}

	public static boolean isLong(Long value) {
		return value != null && value > 0;
	}

	public static <T> boolean isList(List<T> list) {
		return list != null && !list.isEmpty();
	}
	
	public static String message(int type, boolean plural) {
		String message = null;
		switch (type) {
		case 1:
			message = "registrado";
			break;
		case 2:
			message = "actualizado";
			break;
		case 3:
			message = "actualizado el estado";
			break;
		case 4:
			message = "actualizando cantidad";
			break;
		default:
			message = "n/a";
			break;
		}
		return message;
	}
}
