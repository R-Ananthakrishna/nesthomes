package com.bean;

public class OwnerDetailsBean {
	
	/*
	* Author : R Ananthakrishna
	* Date: 19-07-2023
	* Version: Java JDK 8
	* Eclipse IDE Version: 2022-09
	* Purpose : Bean/Model class for Owner
	*/
	
	
	private int owId;
	private String owName;
	private String owAddress;
	private long owContact;
	
	
	public int getOwId() {
		return owId;
	}
	public void setOwId(int owId) {
		this.owId = owId;
	}
	public String getOwName() {
		return owName;
	}
	public void setOwName(String owName) {
		this.owName = owName;
	}
	public String getOwAddress() {
		return owAddress;
	}
	public void setOwAddress(String owAddress) {
		this.owAddress = owAddress;
	}
	public long getOwContact() {
		return owContact;
	}
	public void setOwContact(long owContact) {
		this.owContact = owContact;
	}
	

}
