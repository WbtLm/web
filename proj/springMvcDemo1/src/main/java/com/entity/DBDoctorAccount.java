package com.entity;

public class DBDoctorAccount {
	int doctorId;
	String account;
	String password;
	public int getId() {
		return doctorId;
	}
	public void setId(int id) {
		this.doctorId = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
