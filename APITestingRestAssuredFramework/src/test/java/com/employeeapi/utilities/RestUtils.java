package com.employeeapi.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {

	
	public static String empName() {
		String generatedRandomString = RandomStringUtils.randomAlphabetic(3);
		return ("MdShar"+generatedRandomString);
	}
	
	public static String empSal() {
		String generatedRandomString=RandomStringUtils.randomNumeric(5);
		return (generatedRandomString);
	}
	
	public static String empAge() {
		String generatedRandomString= RandomStringUtils.randomNumeric(2);
		return (generatedRandomString);
	}
}
