package com.hellofresh.qa.automation.page.auth;

import com.hellofresh.qa.automation.bot.PageElement;

public enum AuthPageElement implements PageElement{
	EMAIL_TEXTBOX("id;email_create"),
	CREATE_ACCOUNT_BUTTON("id;SubmitCreate"),
	FIRST_NAME_TEXTBOX("id;customer_firstname"),
	LAST_NAME_TEXTBOX("id;customer_lastname"),
	PASSWORD_TEXTBOX("id;passwd"),
	AGE_DAY_DROPDOWN("id;days"),
	AGE_MONTH_DROPDOWN("id;months"),
	AGE_YEAR_DROPDOWN("id;years"),
	COMPANY_TEXTBOX("id;company"),
	ADDRESS1_TEXTBOX("id;address1"),
	ADDRESS2_TEXTBOX("id;address2"),
	CITY_TEXTBOX("id;city"),
	STATE_DROPDOWN("id;id_state"),
	POSTCODE_TEXTBOX("id;postcode"),
	OTHER_TEXTBOX("id;other"),
	PHONE_TEXTBOX("id;phone"),
	PHONE_MOBILE_TEXTBOX("id;phone_mobile"),
	ALIAS_TEXTBOX("id;alias"),
	SUBMIT_BUTTON("id;submitAccount"),
	
	LOGIN_EMAIL_TEXTBOX("id;email"),
	LOGIN_PASSWORD_TEXTBOX("id;passwd"),
	SIGN_IN_BUTTON("id;SubmitLogin")
	
	;
	
	private final String elementLocator;
	
	private AuthPageElement(final String elementLocator){
		this.elementLocator = elementLocator;
	}
	
	public String getElementLocator() {
		return this.elementLocator;
	}

}
