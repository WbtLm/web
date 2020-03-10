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
}
