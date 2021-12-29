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
}
