package com.hellofresh.qa.automation.page.checkout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hellofresh.qa.automation.bot.Bot;
import com.hellofresh.qa.automation.page.Page;
/**
 * Class representing a checkout page . Please note that this page also has
 * functionality related to the catalogue section . This is OK for now. I dont
 * really want to create another page class
 * 
 * @author bharath
 *
 */
public class CheckoutPage extends Page<CheckoutPage>{
	private static final Logger LOGGER = LoggerFactory.getLogger(CheckoutPage.class);
	
	private final Bot bot;
	
	public enum PaymentOption{
		BANK_WIRE_PAYMENT, CHEQUE_PAYMENT
	}
	
	public CheckoutPage(final Bot bot){
		super(bot);
		this.bot = bot;
	}
	

	@Override
	public void load() {
		// Dont do anything , This page is loaded from the actions in the previous page 
		
	}


	@Override
	public boolean isLoaded() {
		return this.bot.isElementPresent(CheckoutPageElement.PRODUCT_CONTAINER);
	}
	
	/**
	 * Select a product and add the same to the cart , Note that we dont do any
	 * other validation here , Just add the product to the cart
	 * 
	 * @param productName
	 * 			The name of the product to select 
	 */
	public void selectProductAndAddtoCart(final String productName){
		LOGGER.debug("Secting the product with name {} ", productName);
		bot.clickOnElementContainingText(CheckoutPageElement.PRODUCT_CONTAINER, productName);
		LOGGER.debug("Selected the product ... Attempting to add to cart ");
		bot.click(CheckoutPageElement.ADD_TO_CART_BUTTON);
		LOGGER.debug("Added the product to the product cart ...");
	}
	
	
	
	private void selectPayment(PaymentOption option){
		switch(option){
		case BANK_WIRE_PAYMENT:
			bot.click(CheckoutPageElement.BANK_WIRE_TAB);
			break;
		case CHEQUE_PAYMENT:
			bot.click(CheckoutPageElement.CHEQUE_TAB);
			break;
		}
	}
	
	/**
	 * Purchase the product that is added to the cart .Note that we dont do any
	 * other validation , Just proceed to the checkout and attempt to purchase
	 * the product
	 * 
	 * @param paymentOption
	 * 			The payment method to use 
	 * 		
	 */
	public void purchaseProduct(PaymentOption paymentOption){
		LOGGER.debug("Attempting to purchase the product with payment option {} ", paymentOption.toString());
		//Click on the proceed to checkout displayed in the modal 
		bot.click(CheckoutPageElement.PROCEED_TO_CHECKOUT_BUTTON);
		//Click on the proceed to checkout in the summary section 
		bot.click(CheckoutPageElement.PROCEED_WITH_SUMMARY_BUTTON);
		//Now we are in the address section click the proceed to checkout 
		bot.click(CheckoutPageElement.PROCEED_WITH_ADDRESS_BUTTON);
		//Agree to terms 
		bot.click(CheckoutPageElement.AGREE_TERMS_CHECKBOX);
		//Click the proceed button
		bot.click(CheckoutPageElement.PROCEED_WITH_CARRIER_BUTTON);
		// Select the payment option.  
		this.selectPayment(paymentOption);
		bot.click(CheckoutPageElement.CONFIRM_MY_ORDER_BUTTON);
		LOGGER.debug("The checkout action is completed successfully !");
	}
	
	
	public String getOrderConfirmationMessage(){
		return bot.getText(CheckoutPageElement.ORDER_CONFIRMATION_HEADER);
	}
	
	public String getOrderConfirmationSectionMessage(){
		return bot.getText(CheckoutPageElement.ORDER_CONFIRMATION_MESSAGE_SECTION);
	}


	

}
