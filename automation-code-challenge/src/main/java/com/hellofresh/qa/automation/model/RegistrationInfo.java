package com.hellofresh.qa.automation.model;

import com.google.gson.Gson;

/**
 * A class representing registration info . 
 * 
 * 
 * @author bharath
 *
 */
public class RegistrationInfo {
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private String birthDay;
	private String birthMonth;
	private String birthYear;
	private String company;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String postcode;
	private String other;
	private String phone;
	private String mobilePhone;
	private String alias;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	public String getBirthMonth() {
		return birthMonth;
	}
	public void setBirthMonth(String birthMonth) {
		this.birthMonth = birthMonth;
	}
	public String getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	public static void main(String args[]){
		RegistrationInfo info = new RegistrationInfo();
		info.setFirstName("hf_firstname");
		info.setLastName("hf_lastname");
		info.setBirthDay("1");
		info.setBirthMonth("1");
		info.setBirthYear("2000");
		info.setCompany("Hello Fresh");
		info.setAddress1("7600 E caley Avenue");
		info.setAddress2("Englewood");
		info.setCity("Centennial");
		info.setState("Colorado");
		info.setPostcode("80111");
		info.setOther("Other info");
		info.setPhone("1234567890");
		info.setMobilePhone("4444444444");
		info.setAlias("hf");
		System.out.println(new Gson().toJson(info));
		
	}
	
	
	
	

}
