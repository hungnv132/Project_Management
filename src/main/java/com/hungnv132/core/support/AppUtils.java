package com.hungnv132.core.support;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AppUtils {
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private  static Random rnd = new Random();

	/*public static String encrypt(String input) {
		String basedInput = "";
		try {
			byte[] standardInput = input.getBytes("UTF-8"); // standardize for
															// any systems

			 SecureRandom random = SecureRandom.getInstance(" SHA1PRNG"); 
			SecureRandom random = new SecureRandom();
			byte[] salt = new byte[10];
			random.nextBytes(salt);

			MessageDigest digest = MessageDigest.getInstance("SHA-256");

			byte[] saltAndStandardInput = new byte[salt.length + standardInput.length];
			System.arraycopy(salt, 0, saltAndStandardInput, 0, salt.length);
			System.arraycopy(standardInput, 0, saltAndStandardInput, salt.length, standardInput.length);

			byte[] encryptedInput = digest.digest(standardInput);
			basedInput = Base64.encodeBase64String(encryptedInput);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return basedInput;
	}*/

	public static String encodePassword(String input) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(input);
		return hashedPassword;
	}

	public static boolean matchPassword(String rawPassword, String encodedPassword) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.matches(rawPassword, encodedPassword);

	}

	public static JoinedUser getJoinedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		JoinedUser joinedUser = null;
		if (authentication != null && authentication.getPrincipal() instanceof JoinedUser) {
			joinedUser = (JoinedUser) authentication.getPrincipal();
		}
		return joinedUser;
	}

	public static boolean checkEmailFormat(String email) {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static Date convertStringToDate(String date) {
		// date = date + " 00:00:01";
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date parsedDate = null;
		try {
			parsedDate = formatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parsedDate;

	}

	public static List<String> separateStringToList(String str, char splitingChar) {
		str = str.trim();
		while (str.contains("  ")) {
			str = str.replace("  ", " ");
		}
		List<String> listStr = new ArrayList<String>();
		int start = 0;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c == splitingChar) {
				String subStr = str.substring(start, i);
				start = i + 1;
				listStr.add(subStr.trim());
			}
			if (i == str.length() - 1) {
				String subStr = str.substring(start, i + 1);
				if (subStr.trim().length() > 0) {
					listStr.add(subStr.trim());
				}
			}
		}
		return listStr;
	}

	public static String randomString(int len) {
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}

}
