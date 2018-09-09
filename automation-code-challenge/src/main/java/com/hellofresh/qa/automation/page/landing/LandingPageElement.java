package com.hellofresh.qa.automation.page.landing;

import com.hellofresh.qa.automation.bot.PageElement;

public enum LandingPageElement implements PageElement{
	
	SIGN_IN_LINK("class;login");
	
	private final String elementLocator;
	
	private LandingPageElement(final String elementLocator){
		this.elementLocator = elementLocator;
	}

	public String getElementLocator() {
		return this.elementLocator;
	}

}
