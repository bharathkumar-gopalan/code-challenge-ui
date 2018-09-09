package com.hellofresh.qa.automation.page.landing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hellofresh.qa.automation.bot.Bot;
import com.hellofresh.qa.automation.page.Page;
import com.hellofresh.qa.automation.page.auth.AuthPage;

/**
 * 
 * Class representing a landing page instance 
 * 
 * @author bharath
 *
 */
public class LandingPage extends Page<LandingPage>{
	private static final Logger LOGGER = LoggerFactory.getLogger(LandingPage.class);
	private final Bot bot;
	private final String baseAppUrl;
	private static final String AUTH_PAGE_NOT_LOADED_MESSAGE = "The auth page failed to load , Maybe the registration email already exists??";
	
	
	public LandingPage(final Bot bot, final String baseAppUrl){
		super(bot);
		this.bot = bot;
		this.baseAppUrl = baseAppUrl;
	}


	@Override
	public void load() {
		LOGGER.debug("Loading the base page at {}", this.baseAppUrl);
		this.bot.goToUrl(baseAppUrl);
	}


	@Override
	public boolean isLoaded() {
		return bot.isElementPresent(LandingPageElement.SIGN_IN_LINK);
	}
	
	/**
	 * Navigate to the auth page by clicking in the sign in button.
	 * 
	 * @return An Auth page instance
	 */
	public AuthPage navigateToAuthPage(){
		LOGGER.debug("Navigating to the auth page....");
		bot.click(LandingPageElement.SIGN_IN_LINK);
		final AuthPage authPage = new AuthPage(this.bot);
		if(authPage.isLoaded() == false){
			throw new RuntimeException(AUTH_PAGE_NOT_LOADED_MESSAGE);
		}
		return authPage;
	}

}
