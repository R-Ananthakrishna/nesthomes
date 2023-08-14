package com.bean;

public class DailyHelpBean {
	
	/*
	* Author : R Ananthakrishna
	* Date: 19-07-2023
	* Version: Java JDK 8
	* Eclipse IDE Version: 2022-09
	* Purpose : Bean/Model class for Daily Help
	*/
	
	private int dhId;
	private String dhName;
	private String dhCategory;
	private String dhAddress;
	private long dhContact;
	
	
	
	public int getDhId() {
		return dhId;
	}
	public void setDhId(int dhId) {
		this.dhId = dhId;
	}
	public String getDhName() {
		return dhName;
	}
	public void setDhName(String dhName) {
		this.dhName = dhName;
	}
	public String getDhCategory() {
		return dhCategory;
	}
	public void setDhCategory(String dhCategory) {
		this.dhCategory = dhCategory;
	}
	public String getDhAddress() {
		return dhAddress;
	}
	public void setDhAddress(String dhAddress) {
		this.dhAddress = dhAddress;
	}
	public long getDhContact() {
		return dhContact;
	}
	public void setDhContact(long dhContact) {
		this.dhContact = dhContact;
	}
	
	

}
