package net.aigarcia.rest.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

	private static final String EMAIL_REGEX = "\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b";
	private static final String EMAIL_REGEX_RFC = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
//	private static final String regex = "^(.+)@(.+)$";
	private static final Pattern pattern = Pattern.compile(EMAIL_REGEX_RFC);

	private static final String REGEX_PASSWORD = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[0-9]).{1,}$";
//	private static final String REGEX_PASSWORD = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$"; //con caracteres especiales, 8 caracteres minimos
	private static final Pattern patternPassword = Pattern.compile(REGEX_PASSWORD);
	
	public static boolean validateEmail(String email) {
		if (email == null || email.isEmpty())
			return false;

		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	public static boolean validatePassword(String password) {
		if (password == null || password.isEmpty())
			return false;

		Matcher matcher = patternPassword.matcher(password);
		return matcher.matches();
	}
}
