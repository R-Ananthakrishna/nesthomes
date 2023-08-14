package com.bean;

public class RentalBean {
	
	/*
	* Author : R Ananthakrishna
	* Date: 19-07-2023
	* Version: Java JDK 8
	* Eclipse IDE Version: 2022-09
	* Purpose : Bean/Model class for Rental
	*/
	
	
	private int rId;
	private String tName;
	private String flatNo;
	private String flatTower;
	
	
	public int getrId() {
		return rId;
	}
	public void setrId(int rId) {
		this.rId = rId;
	}
	public String getTName() {
		return tName;
	}
	public void setTName(String tName) {
		this.tName = tName;
	}
	public String getFlatNo() {
		return flatNo;
	}
	public void setFlatNo(String flatNo) {
		this.flatNo = flatNo;
	}
	public String getFlatTower() {
		return flatTower;
	}
	public void setFlatTower(String flatTower) {
		this.flatTower = flatTower;
	}
	
	

}
