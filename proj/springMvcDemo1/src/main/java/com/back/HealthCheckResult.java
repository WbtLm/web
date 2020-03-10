package com.back;

public class HealthCheckResult {
	private int resultID;
	private int registeredID;
	private String prescription;
	private String theDiagnosis;
	public HealthCheckResult(int resultID,int registeredID,String prescription,String theDiagnosis) {
		this.resultID=resultID;
		this.registeredID=registeredID;
		this.prescription=prescription;
		this.theDiagnosis=theDiagnosis;
	}
	public int getResultID() {
		return resultID;
	}
	public void setResultID(int resultID) {
		this.resultID = resultID;
	}
	public int getRegisteredID() {
		return registeredID;
	}
	public void setRegisteredID(int registeredID) {
		this.registeredID = registeredID;
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
