package com.hellofresh.qa.automation.bot;

/**
 * An interface representing an automation bot . Bot can be thought as an
 * automation agent that actually drives the browser. Using such a pattern, An
 * user can abstract the lower level task of handling UI elements (For example
 * waiting for an element , checking for the various element state etc ) . With
 * this approach, the resulting test code is more cleaner (Rather than being
 * cluttered with selenium API calls)
 * 
 * @author bharath
 *
 */
// TODO - Please understand that this bot is incomplete ! I have created
// interaction methods that are needed for the demo test case
public interface Bot {

	/**
	 * Navigate the browser to a specific URL
	 * 
	 * @param url
	 *            The url to navigate to
	 */
	public void goToUrl(final String url);

	/**
	 * Click on a specific element
	 * 
	 * @param element
	 *            The element to click
	 */
	public void click(final PageElement element);
	
	/**
	 * Get the current page URL 
	 * 
	 * @return
	 * 		The current displayed page URL 
	 */
	public String getPageUrl();

	/**
	 * Send a text to a particular page element
	 * 
	 * @param element
	 *            The element to type
	 * @param text
	 *            The text to type
	 */
	public void type(final PageElement element, final String text);

	/**
	 * Get the text displayed on a web element
	 * 
	 * @param element
	 *            The element to interact with
	 * @return The string representing the element text
	 */
	public String getText(final PageElement element);

	/**
	 * Check to see if an element is present
	 * 
	 * @param element
	 *            The element to check the presence for
	 * 
	 * @return A boolean value indicating if the element is present or not
	 * 
	 */
	public boolean isElementPresent(final PageElement element);

	/**
	 * Click on an element which has the give text . This is typically useful
	 * when there are multiple elements with the same locator and we need to
	 * select one. An example will be a product search result
	 * 
	 * @param element
	 *            The Page element to search for
	 * @param elementText
	 *            The element text that must be matched
	 */
	public void clickOnElementContainingText(final PageElement element, final String elementText);

	/**
	 * Wait for the presence of the element until a specified timeout
	 * 
	 * @param element
	 *            The element to wait for
	 * @param timeoutInSeconds
	 *            The element timeout
	 * @return A boolean value indicating if the element is present or not
	 * 
	 */
	public boolean isElementPresent(final PageElement element, final int timeoutInSeconds);
	
	/**
	 * Select a dropdown by value 
	 * 
	 * @param element
	 * 			The dropdown element 
	 * @param value
	 * 			The value to select 
	 */
	public void selectDropDownByValue(final PageElement element, final String value);
	
	/**
	 * Select a dropdown by visible text
	 * 
	 * @param element
	 * 			The dropdown element 
	 * @param visibleText
	 * 			The visible text 
	 */
	public void selectDropDownByVisibleText(final PageElement element, final String visibleText);
	
	/**
	 * Check to see if a webelement is displayed(present and visible)  
	 * 
	 * @param element
	 * 			The element to check for 
	 * @return
	 * 		A boolean value indicating that the element is both present and visible 
	 */
	public boolean isElementDisplayed(final PageElement element);

	/**
	 * Quit the current browser instance
	 */
	public void quit();

}
