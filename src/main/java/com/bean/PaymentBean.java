package com.bean;

public class PaymentBean {
	
	/*
	* Author : R Ananthakrishna
	* Date: 19-07-2023
	* Version: Java JDK 8
	* Eclipse IDE Version: 2022-09
	* Purpose : Bean/Model class for Payment
	*/
	
	
	private int soId;
	private String acctType;
	private String flatNo;
	private String duesType;
	private String date;
	private double total;
	private double gasDues;
	private double paid;
	private String uName;

	
	
	public int getSoId() {
		return soId;
	}
	public void setSoId(int soId) {
		this.soId = soId;
	}
	public String getAcctType() {
		return acctType;
	}
	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}
	public String getFlatNo() {
		return flatNo;
	}
	public void setFlatNo(String flatNo) {
		this.flatNo = flatNo;
	}
	public String getDuesType() {
		return duesType;
	}
	public void setDuesType(String duesType) {
		this.duesType = duesType;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getGasDues() {
		return gasDues;
	}
	public void setGasDues(double gasDues) {
		this.gasDues = gasDues;
	}
	public double getPaid() {
		return paid;
	}
	public void setPaid(double paid) {
		this.paid = paid;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	
	
	
	
	

}
