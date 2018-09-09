package com.hellofresh.qa.automation.page.myaccount;

import com.hellofresh.qa.automation.bot.PageElement;

public enum MyAccountPageElement implements PageElement{
	ACCOUNT_INFO_SECTION("class;info-account"),
	SIGNOUT_LINK("class;logout"),
	USER_NAME_TEXT("class;account"),
	WOMENS_SECTION("linkText;WOMEN"),
	HEADING_SECTION("css;h1")
	;
	
	private final String elementLocator;
	
	private MyAccountPageElement(final String elementLocator){
		this.elementLocator = elementLocator;
	}

	public String getElementLocator() {
		return this.elementLocator;
	}


}
