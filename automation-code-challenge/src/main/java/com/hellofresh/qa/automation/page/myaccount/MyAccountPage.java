package com.hellofresh.qa.automation.page.myaccount;

import com.hellofresh.qa.automation.bot.Bot;
import com.hellofresh.qa.automation.page.Page;
import com.hellofresh.qa.automation.page.checkout.CheckoutPage;

public class MyAccountPage extends Page<MyAccountPage>{
	
	private final Bot bot;
	
	
	
	public MyAccountPage(final Bot bot){
		super(bot);
		this.bot = bot;
	}

	@Override
	public void load() {
		// This page is loaded by the previous flow , No need of this at this point
		
	}

	@Override
	public boolean isLoaded() {
		return bot.isElementPresent(MyAccountPageElement.ACCOUNT_INFO_SECTION);
	}
	
	/**
	 * Get the logged in user name 
	 * @return
	 * 		The user name of the logged in account 
	 */
	public String getLoggedInUserName(){
		return bot.getText(MyAccountPageElement.USER_NAME_TEXT);
	}
	
	/**
	 * Get the message displayed in the account info section
	 * 
	 * @return The welcome message that is displayed in the section
	 */
	public String getAccountInfoMessage(){
		return bot.getText(MyAccountPageElement.ACCOUNT_INFO_SECTION);
	}
	
	
	public String getHeaderMessage(){
		return bot.getText(MyAccountPageElement.HEADING_SECTION);
	}
	
	
	/**
	 * Navigate to the Womens section . Note that we load the checkout page
	 * directly, As I dont want to create a new page class for this at the
	 * moment
	 * 
	 * @return
	 * 		A handle to the checkout page 
	 */
	
	public CheckoutPage navigateToWomenSection(){
		this.bot.click(MyAccountPageElement.WOMENS_SECTION);
		return new CheckoutPage(this.bot).get();
	}
	
	

}
