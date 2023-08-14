package com.bean;

public class FacilityManagerBean {
	
	
	/*
	* Author : R Ananthakrishna
	* Date: 19-07-2023
	* Version: Java JDK 8
	* Eclipse IDE Version: 2022-09
	* Purpose : Bean/Model class for Facility Manager
	*/
	
	private int fmId;
	private String fmName;
	private String fmDesignation;
	private String fmDuties;
	private long fmContact;
	
	
	
	public int getFmId() {
		return fmId;
	}
	public void setFmId(int fmId) {
		this.fmId = fmId;
	}
	public String getFmName() {
		return fmName;
	}
	public void setFmName(String fmName) {
		this.fmName = fmName;
	}
	public String getFmDesignation() {
		return fmDesignation;
	}
	public void setFmDesignation(String fmDesignation) {
		this.fmDesignation = fmDesignation;
	}
	public String getFmDuties() {
		return fmDuties;
	}
	public void setFmDuties(String fmDuties) {
		this.fmDuties = fmDuties;
	}
	public long getFmContact() {
		return fmContact;
	}
	public void setFmContact(long fmContact) {
		this.fmContact = fmContact;
	}
	

}
