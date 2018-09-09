package com.hellofresh.qa.automation.page.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hellofresh.qa.automation.bot.Bot;
import com.hellofresh.qa.automation.model.RegistrationInfo;
import com.hellofresh.qa.automation.page.Page;
import com.hellofresh.qa.automation.page.myaccount.MyAccountPage;

/**
 * A page class representing the authentication page . For the challenge this
 * also includes the registration page also . Should be ok for now !
 * 
 * @author bharath
 *
 */
public class AuthPage extends Page<AuthPage>{
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthPage.class);
	private final Bot bot;
	private static final int REGISTRATION_TIMEOUT_IN_SECONDS = 5;
	private static final String REGISTRATION_FORM_NOT_LOADED = "The registration form is not loaded! Cannot proceed";
	
	public AuthPage(final Bot bot){
		super(bot);
		this.bot = bot;
	}
	
	// Dont do anything , This page is loaded from the previous page for the flow
	@Override
	public void load() {
		// Nothing here 
		
	}



	@Override
	public boolean isLoaded() {
		return this.bot.isElementPresent(AuthPageElement.EMAIL_TEXTBOX);
	}
	
	
	/**
	 * Fill in the registration form . Note This method just fills in the form .
	 * 
	 * @param registrationInfo
	 *            The registration info needed to fill in the form
	 * 
	 */
	public void fillInRegistrationForm(final RegistrationInfo registrationInfo){
		LOGGER.debug("Filling in registration info .....");
		this.bot.type(AuthPageElement.EMAIL_TEXTBOX, registrationInfo.getEmail());
		this.bot.click(AuthPageElement.CREATE_ACCOUNT_BUTTON);
		// Check to see if the regitration form is loaded , if not throw an error message
		if(this.bot.isElementPresent(AuthPageElement.FIRST_NAME_TEXTBOX, REGISTRATION_TIMEOUT_IN_SECONDS) == false){
			throw new RuntimeException(REGISTRATION_FORM_NOT_LOADED);
		}
		// Start filling in the form 
		this.bot.type(AuthPageElement.FIRST_NAME_TEXTBOX, registrationInfo.getFirstName());
		this.bot.type(AuthPageElement.LAST_NAME_TEXTBOX, registrationInfo.getLastName());
		this.bot.type(AuthPageElement.PASSWORD_TEXTBOX, registrationInfo.getPassword());
		// Birthday 
		this.bot.selectDropDownByValue(AuthPageElement.AGE_DAY_DROPDOWN, registrationInfo.getBirthDay());
		this.bot.selectDropDownByValue(AuthPageElement.AGE_MONTH_DROPDOWN, registrationInfo.getBirthMonth());
		this.bot.selectDropDownByValue(AuthPageElement.AGE_YEAR_DROPDOWN, registrationInfo.getBirthYear());
		// Company , Address and other info 
		this.bot.type(AuthPageElement.COMPANY_TEXTBOX, registrationInfo.getCompany());
		this.bot.type(AuthPageElement.ADDRESS1_TEXTBOX, registrationInfo.getAddress1());
		this.bot.type(AuthPageElement.ADDRESS2_TEXTBOX, registrationInfo.getAddress2());
		this.bot.type(AuthPageElement.CITY_TEXTBOX, registrationInfo.getCity());
		this.bot.selectDropDownByVisibleText(AuthPageElement.STATE_DROPDOWN, registrationInfo.getState());
		this.bot.type(AuthPageElement.POSTCODE_TEXTBOX, registrationInfo.getPostcode());
		this.bot.type(AuthPageElement.OTHER_TEXTBOX, registrationInfo.getOther());
		//Phone and alias 
		this.bot.type(AuthPageElement.PHONE_TEXTBOX, registrationInfo.getPhone());
		this.bot.type(AuthPageElement.PHONE_MOBILE_TEXTBOX, registrationInfo.getMobilePhone());
		this.bot.type(AuthPageElement.ALIAS_TEXTBOX, registrationInfo.getAlias());
	}
	
	/**
	 * Fill in the registration form and register the user data by clicking the
	 * submit button
	 * 
	 * @param registrationInfo
	 * 			The registration info needed to fill in the form
	 * @return
	 * 		An instance of my account page 
	 */
	
	public MyAccountPage regsiter(final RegistrationInfo registrationInfo){
		LOGGER.debug("Registering the user data");
		this.fillInRegistrationForm(registrationInfo);
		LOGGER.debug("Submitting the form for the user");
		this.bot.click(AuthPageElement.SUBMIT_BUTTON);
		return new MyAccountPage(bot).get();
	}
	
	
	public MyAccountPage Login(final String username, final String password){
		LOGGER.debug("Logging in with the username and password {}, {}", username, password);
		bot.type(AuthPageElement.LOGIN_EMAIL_TEXTBOX, username);
		bot.type(AuthPageElement.LOGIN_PASSWORD_TEXTBOX, password);
		bot.click(AuthPageElement.SIGN_IN_BUTTON);
		return new MyAccountPage(this.bot).get();
	}


	
	

}
