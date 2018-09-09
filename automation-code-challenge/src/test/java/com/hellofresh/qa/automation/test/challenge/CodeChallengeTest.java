package com.hellofresh.qa.automation.test.challenge;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.hellofresh.qa.automation.bot.Bot;
import com.hellofresh.qa.automation.model.RegistrationInfo;
import com.hellofresh.qa.automation.page.auth.AuthPage;
import com.hellofresh.qa.automation.page.checkout.CheckoutPage;
import com.hellofresh.qa.automation.page.checkout.CheckoutPage.PaymentOption;
import com.hellofresh.qa.automation.page.checkout.CheckoutPageElement;
import com.hellofresh.qa.automation.page.landing.LandingPage;
import com.hellofresh.qa.automation.page.myaccount.MyAccountPage;
import com.hellofresh.qa.automation.page.myaccount.MyAccountPageElement;
import com.hellofresh.qa.automation.test.base.BaseTest;
import com.hellofresh.qa.automation.util.FileUtil;
import com.hellofresh.qa.automation.util.RandomUtil;

/**
 * 
 * The test suite for the challenge . The test methods in the suite are
 * independent of each other.
 * 
 * NOTE: THIS CLASS DOES NOT SUPPORT PARALLEL EXECUTION ON A METHOD LEVEL   
 * 
 * @author bharath
 *
 */
public class CodeChallengeTest extends BaseTest{
	private static final String REGITSRTAION_DATA_JSON_FILE = "registration/test-data/RegistrationData.json";
	private static final String CHECKOUT_DATA_CSV_FILE = "registration/test-data/CheckoutData.csv";
	private static final Logger LOGGER = LoggerFactory.getLogger(CodeChallengeTest.class);

	// The full username format , We use this to construct the full username
	// from the registration data
	private static final String FULL_NAME_FORMAT = "%s %s";

	// expected text messages
	private static final String MY_ACCOUNT_MESSAGE_EXPECTED = "MY ACCOUNT";
	private static final String WELCOME_MESSAGE_EXPECTED = "Welcome to your account";
	private static final String ORDER_CONFIRMATION_MESSAGE_EXPECTED_1 = "ORDER CONFIRMATION";
	private static final String ORDER_CONFIRMATION_MESSAGE_EXPECTED_2 = "Your order on My Store is complete.";
	private static final String EXPECTED_URL_MY_ACCOUNT_TEXT = "controller=my-account";
	private static final String EXCEPCTED_URL_ORD_CONFIRM_TEXT = "controller=order-confirmation";

	// Existing user info
	private static final String EXISTING_EMAIL = "hf_challenge_278c73d4-e7e9-4f6f-873c-f2319b9bdfef@hf.com";
	private static final String EXISTING_PASSWORD = "Password1";
	private static final String EXISTING_USERNAME = "John hellofresh";

	private static final String INVALID_PAYMENT_OPTION_MESSAGE = "Invalid paymemt option %s , only BANK_WIRE_PAYMENT and CHEQUE_PAYMENT allowed";

	// Assertion failure messages
	private static final String SIGNOUT_LINK_NOT_DISPLAYED_MESSAGE = "The Signout link is displayed in the my account page --";
	private static final String MYACCT_URL_MISMATCH_MESSAGE = "The My Account page URL contains "
			+ EXPECTED_URL_MY_ACCOUNT_TEXT;
	private static final String ORD_CONF_URL_MISMATCH_MESSAGE = "The Order confirmation page URL contains "
			+ EXCEPCTED_URL_ORD_CONFIRM_TEXT;
	private static final String NAME_MISMATCH_MESSAGE = "There is a mismatch in the registered user name --";
	private static final String MY_ACCOUNT_HEADER_MISMATCH_MESSAGE = "There is a mismatch in the my account header message --";
	private static final String WELCOME_MESSAGE_MISMATCH = "The account Info is expected to contain 'Welcome to your account', but does not have it ,"
			+ " The actual message displayed is %s";
	private static final String ORDER_CONFIRMATION_MESSAGE_MISMATCH = "There is a mismatch in order confirmation message--";
	private static final String SHIPPING_SECTION_MISSING_MESSAGE = "The Shipping section is displayed --";
	private static final String PAYMENT_SECTION_MISSING_MESSAGE = "The payment section is displayed--";

	private Bot bot;
	private String applicationBaseUrl;


	/**
	 * Get the registration data from the JSON file also populate the random
	 * email and password
	 * 
	 * @return The RegistrationInfo object
	 */
	private RegistrationInfo getRegistrationData() {
		// Read the regstration info from the file
		final String jsonData = FileUtil.getFileDataAsString(REGITSRTAION_DATA_JSON_FILE);
		final RegistrationInfo userRegistrationInfo = new Gson().fromJson(jsonData, RegistrationInfo.class);
		// Set the email and password (with a random email and password)
		userRegistrationInfo.setEmail(RandomUtil.createRandomEmailId());
		userRegistrationInfo.setPassword(RandomUtil.createRandomPassword());
		return userRegistrationInfo;
	}

	/**
	 * Validate the account info page using assertions .The validations in test
	 * case #1 and #2 are very similar . This method is used in validation of
	 * the both
	 * 
	 * @param myAccountPage
	 *            A handle to the my account Page
	 * @param username
	 *            The username to cross check the page against
	 */
	private void validateMyAccountPage(final MyAccountPage myAccountPage, final String username) {
		// Get the logged in username
		final String loggedInUserName = myAccountPage.getLoggedInUserName();
		// Get the welcome message
		final String welcomeMessage = myAccountPage.getAccountInfoMessage();
		//Get the header message 
		final String headerMessage = myAccountPage.getHeaderMessage();

		// Check if the URL contains the expected part
		Assert.assertTrue(myAccountPage.getCurrentUrl().contains(EXPECTED_URL_MY_ACCOUNT_TEXT), MYACCT_URL_MISMATCH_MESSAGE);

		// Check if the Account info Section contains a welcome message
		Assert.assertTrue(welcomeMessage.contains(WELCOME_MESSAGE_EXPECTED), String.format(WELCOME_MESSAGE_MISMATCH, welcomeMessage));

		// Check if the user name displayed matched with the one that was registered
		Assert.assertEquals(username, loggedInUserName, NAME_MISMATCH_MESSAGE);

		// Check if the my account page contains the MY ACCOUNT header
		Assert.assertEquals(headerMessage, MY_ACCOUNT_MESSAGE_EXPECTED, MY_ACCOUNT_HEADER_MISMATCH_MESSAGE);

		// Check if the signout link is displayed ,
		// TODO deviation from page object , Can be corrected later !
		Assert.assertTrue(bot.isElementDisplayed(MyAccountPageElement.SIGNOUT_LINK), SIGNOUT_LINK_NOT_DISPLAYED_MESSAGE);

	}

	private PaymentOption getPaymentOptionFromString(final String paymentOption) {
		try {
			return PaymentOption.valueOf(paymentOption);
		} catch (Exception err) {
			throw new RuntimeException(String.format(INVALID_PAYMENT_OPTION_MESSAGE, paymentOption));
		}
	}
	
	/**
	 * Get the created bot instance , usually this has to done once per method
	 * 
	 */
	@BeforeMethod
	public void setupTestSuite(){
		this.bot = getBotInstance();
		this.applicationBaseUrl = getApplicationBaseUrl();
	}
	
	

	@Test(description = "Verify if the user is able to regsiter a new account successfully")
	public void signUpTest() {
		LOGGER.debug("Verifying the user registration process...");
		final RegistrationInfo userRegistrationInfo = this.getRegistrationData();
		final String username = String.format(FULL_NAME_FORMAT, userRegistrationInfo.getFirstName(),
				userRegistrationInfo.getLastName());
		// Load the landing page
		final LandingPage landingPage = new LandingPage(this.bot, this.applicationBaseUrl).get();
		// Navigate to the auth page
		final AuthPage authPage = landingPage.navigateToAuthPage();
		// Fill in the user registration info and register the user,
		// intrinsically check if the page is loaded
		final MyAccountPage myAccountPage = authPage.regsiter(userRegistrationInfo);
		// Validate the my account page
		this.validateMyAccountPage(myAccountPage, username);
	}

	@Test(description = "Verify if an existing user is able to sign in successfully ")
	public void logInTest() {
		LOGGER.debug("Checking to see if the user is able to Sign in ....");
		// Load the application landing page
		final LandingPage landingPage = new LandingPage(this.bot, this.applicationBaseUrl).get();
		// Navigate to the auth page and Sign in with the username and password
		final AuthPage authPage = landingPage.navigateToAuthPage();
		final MyAccountPage myAccountPage = authPage.Login(EXISTING_EMAIL, EXISTING_PASSWORD);
		// Once the user logged in My account page will be displayed , Validate
		// it !
		this.validateMyAccountPage(myAccountPage, EXISTING_USERNAME);
	}

	@Test(description = "Verify if the user is able to purchase a product successfully", dataProvider = "purchaseDataProvider")
	public void checkoutTest(final String productToPurchase, final String paymentOption) {
		LOGGER.debug("Checking to see if the user is able to purchase a product successfully");
		final PaymentOption option = this.getPaymentOptionFromString(paymentOption.toUpperCase().trim());
		final LandingPage landingPage = new LandingPage(this.bot, this.applicationBaseUrl).get();
		// Navigate to the auth page and Sign in with the username and password
		final AuthPage authPage = landingPage.navigateToAuthPage();
		final MyAccountPage myAccountPage = authPage.Login(EXISTING_EMAIL, EXISTING_PASSWORD);
		// Navigate to Women's section .
		// TODO I should have ideally added a new page class for this , I dont
		// want to do it at the moment
		final CheckoutPage checkoutPage = myAccountPage.navigateToWomenSection();
		// Select the desired product
		checkoutPage.selectProductAndAddtoCart(productToPurchase);
		checkoutPage.purchaseProduct(option);

		// Check if the Order confirmation message is displayed
		Assert.assertEquals(ORDER_CONFIRMATION_MESSAGE_EXPECTED_1, checkoutPage.getOrderConfirmationMessage(),
				ORDER_CONFIRMATION_MESSAGE_MISMATCH);
		// Check if the shipping section element is displayed
		Assert.assertTrue(bot.isElementDisplayed(CheckoutPageElement.SHIPPING_SECTION),
				SHIPPING_SECTION_MISSING_MESSAGE);
		// Check if the payment section is displayed
		Assert.assertTrue(bot.isElementDisplayed(CheckoutPageElement.PAYMENT_SECTION), PAYMENT_SECTION_MISSING_MESSAGE);
		// Check if the second order confirmaton message is displayed
		Assert.assertEquals(ORDER_CONFIRMATION_MESSAGE_EXPECTED_2, checkoutPage.getOrderConfirmationSectionMessage(),
				ORDER_CONFIRMATION_MESSAGE_MISMATCH);
		// Check if the URL matches
		Assert.assertTrue(myAccountPage.getCurrentUrl().contains(EXCEPCTED_URL_ORD_CONFIRM_TEXT),
				ORD_CONF_URL_MISMATCH_MESSAGE);
	}

	@DataProvider(name = "purchaseDataProvider")
	public Iterator<Object[]> purcaseDataProvider() {
		return FileUtil.fetchCSVFileData(CHECKOUT_DATA_CSV_FILE);
	}

}
