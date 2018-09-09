package com.hellofresh.qa.automation.test.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.hellofresh.qa.automation.bot.Bot;
import com.hellofresh.qa.automation.bot.WebDriverBot;
import com.hellofresh.qa.automation.environment.ConfigurationHelper;
import com.hellofresh.qa.automation.environment.Environment;
import com.hellofresh.qa.automation.environment.EnvironmentHandler;

/**
 * 
 * The base test class for the automation . All the Test classes must extend
 * this !
 * 
 * A NOTE ABOUT PARALLEL EXECUTION : The base test creates a new bot instance
 * and assigns to the same bot variable . So if parallel runs are done on a
 * method level , it will fail. Parallel executions are possible at a test suite
 * level. They are possible on a class(Suite) level
 * 
 * @author Bharath
 *
 */
public abstract class BaseTest {

	private Bot bot;

	/**
	 * Returns the application base url
	 * 
	 * @return The base URL of the application
	 */
	public String getApplicationBaseUrl() {
		final Environment environment = ConfigurationHelper.INSTANCE.getEnvironment();
		return EnvironmentHandler.INSTANCE.getWebUrlFor(environment);
	}

	@BeforeMethod
	public final void setUpTestSuite() {
		this.bot = WebDriverBot.newInstance();
	}

	/**
	 * Get the bot instance initialized
	 * 
	 * @return The bot instance created
	 */
	public Bot getBotInstance() {
		return this.bot;
	}

	/**
	 * Quit the browser once the test method execution completes
	 * 
	 */
	@AfterMethod(alwaysRun = true)
	public final void tearDown() {
		if (this.bot != null) {
			this.bot.quit();
		}
	}

}
