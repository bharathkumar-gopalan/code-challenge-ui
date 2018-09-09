package com.hellofresh.qa.automation.page;

import com.hellofresh.qa.automation.bot.Bot;

/**
 * An interface representing a page . All the page classes should extend this
 * class
 * 
 * @author bharath
 *
 * @param <T>
 *            The page type
 */
public abstract class Page<T> {

	private static final String PAGE_NOT_LOADED_MESSAGE = "The page %s is not loaded correctly, The automation cannot proceed!";
	private final Bot bot;
	
	
	public Page(final Bot bot){
		this.bot = bot;
	}

	/**
	 * Load the given page . This method should describe the mechanisam to load
	 * the page
	 */
	public abstract void load();

	/**
	 * Check if the given page is loaded
	 * 
	 * @return boolean representing if a given page is loaded or not
	 */
	public abstract boolean isLoaded();

	/**
	 * Get the page . First call the load method , Then check if the page is
	 * loaded . If the page is not loaded , throw an exception
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T get() {
		this.load();
		if (this.isLoaded() == false) {
			throw new RuntimeException(String.format(PAGE_NOT_LOADED_MESSAGE, this.getClass().getSimpleName()));
		}
		return (T) this;
	}
	
	
	public String getCurrentUrl(){
		return this.bot.getPageUrl();
	}
	
	
	public Bot getBotInstance(){
		return this.bot;
	}

}
