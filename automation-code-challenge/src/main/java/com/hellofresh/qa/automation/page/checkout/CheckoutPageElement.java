package com.hellofresh.qa.automation.page.checkout;

import com.hellofresh.qa.automation.bot.PageElement;

public enum CheckoutPageElement implements PageElement{
	PRODUCT_CONTAINER("css;.product-container .right-block .product-name"),
	ADD_TO_CART_BUTTON("name;Submit"),
	PROCEED_TO_CHECKOUT_BUTTON("xpath;//*[@id='layer_cart']//a[@class and @title='Proceed to checkout']"),
	PROCEED_WITH_SUMMARY_BUTTON("xpath;//*[contains(@class,'cart_navigation')]/a[@title='Proceed to checkout']"),
	PROCEED_WITH_ADDRESS_BUTTON("name;processAddress"),
	AGREE_TERMS_CHECKBOX("id;uniform-cgv"),
	PROCEED_WITH_CARRIER_BUTTON("name;processCarrier"),
	BANK_WIRE_TAB("class;bankwire"),
	CHEQUE_TAB("class;cheque"),
	CONFIRM_MY_ORDER_BUTTON("xpath;//*[@id='cart_navigation']/button"),
	
	ORDER_CONFIRMATION_HEADER("css;h1"),
	PAYMENT_SECTION("xpath;//li[@id='step_end' and @class='step_current last']"),
	SHIPPING_SECTION("xpath;//li[@class='step_done step_done_last four']"),
	ORDER_CONFIRMATION_MESSAGE_SECTION("xpath;//*[@class='cheque-indent']/strong")
	
	;
	
private final String elementLocator;
	
	private CheckoutPageElement(final String elementLocator){
		this.elementLocator = elementLocator;
	}
	
	public String getElementLocator() {
		return this.elementLocator;
	}

}
