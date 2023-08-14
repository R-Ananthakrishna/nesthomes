package com.bean;

public class TenantDetailsBean {
	
	/*
	* Author : R Ananthakrishna
	* Date: 19-07-2023
	* Version: Java JDK 8
	* Eclipse IDE Version: 2022-09
	* Purpose : Bean/Model class for Tenant
	*/
	
	private int tId;
	private String tName;
	private String tAddress;
	private long tContact;

	
	public int getTId() {
		return tId;
	}
	public void setTId(int tId) {
		this.tId = tId;
	}
	public String getTName() {
		return tName;
	}
	public void setTName(String tName) {
		this.tName = tName;
	}
	public String getTAddress() {
		return tAddress;
	}
	public void setTAddress(String tAddress) {
		this.tAddress = tAddress;
	}
	public long getTContact() {
		return tContact;
	}
	public void setTContact(long tContact) {
		this.tContact = tContact;
	}
	
	

}
