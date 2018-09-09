package com.hellofresh.qa.automation.util;

import java.util.UUID;

/**
 * 
 * A simple utility class to create random email and password
 * 
 * @author bharath
 *
 */
public class RandomUtil {
	private static final String EMAIL_ID_FORMAT = "hf_challenge_%s@hf.com";

	// Fixed and hard coded password
	private static final String PASSWORD_STRING = "Password1";

	/**
	 * Create a random email ID . It is not a good idea to create random ids
	 * based on millis, Because during parallel runs there could be a chance of
	 * collision. This uses UUID to create random email ids
	 * 
	 * @return A random email id
	 */
	public static String createRandomEmailId() {
		return String.format(EMAIL_ID_FORMAT, UUID.randomUUID().toString());
	}

	public static String createRandomPassword() {
		return PASSWORD_STRING;
	}

}
