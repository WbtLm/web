package com.entity;

public class DBPatientAccount {
    int patientId;
    String account;
    String passWord;
	public int getId() {
		return patientId;
	}
	public void setId(int id) {
		this.patientId = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
    
	
}
