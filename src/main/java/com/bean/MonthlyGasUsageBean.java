package com.bean;

public class MonthlyGasUsageBean {
	
	/*
	* Author : R Ananthakrishna
	* Date: 19-07-2023
	* Version: Java JDK 8
	* Eclipse IDE Version: 2022-09
	* Purpose : Bean/Model class for Monthly Gas Usage
	*/
	
	private int mgcId;
	private String flatNo;
	private String flatTower;
	private double gasUnit;
	
	
	
	public int getMgcId() {
		return mgcId;
	}
	public void setMgcId(int mgcId) {
		this.mgcId = mgcId;
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
	public double getGasUnit() {
		return gasUnit;
	}
	public void setGasUnit(double gasUnit) {
		this.gasUnit = gasUnit;
	}
	
	
	
	

}
