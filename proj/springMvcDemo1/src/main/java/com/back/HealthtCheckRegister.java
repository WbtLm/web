package com.back;

import java.util.Date;

public class HealthtCheckRegister {
	private int checkUpID;
	private int patientID;
	private int typeID;
	private Date appointmentTime;
	public HealthtCheckRegister(int checkUpID,int patientID,int typeID,Date appointmentTime){
		this.checkUpID=checkUpID;
		this.patientID=patientID;
		this.typeID=typeID;
		this.appointmentTime=appointmentTime;
	}
	public int getCheckUpID() {
		return checkUpID;
	}
	public void setCheckUpID(int checkUpID) {
		this.checkUpID = checkUpID;
	}
	public int getPatientID() {
		return patientID;
	}
	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}
	public int getTypeID() {
		return typeID;
	}
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
	public Date getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(Date appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	
}
