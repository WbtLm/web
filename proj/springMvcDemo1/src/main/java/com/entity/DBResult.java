package com.entity;


public class DBResult {
	
	int resultsId;
	int registeredId;
	String prescription;
	String theDiagnosis;
	public int getResultsId() {
		return resultsId;
	}
	public void setResultsId(int resultsId) {
		this.resultsId = resultsId;
	}
	public int getRegisteredId() {
		return registeredId;
	}
	public void setRegisteredId(int registeredId) {
		this.registeredId = registeredId;
	}
	public String getPrescription() {
		return prescription;
	}
	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}
	public String getTheDiagnosis() {
		return theDiagnosis;
	}
	public void setTheDiagnosis(String theDiagnosis) {
		this.theDiagnosis = theDiagnosis;
	}
   
}
