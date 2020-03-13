package com.entity;
import java.util.Date;

public class DBAppointCheckUp {
	
	int checkupId;
	int patientId;
	int typeId;
	Date appointmentTime;
	public int getId() {
		return checkupId;
	}
	public void setId(int id) {
		this.checkupId = id;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public Date getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(Date appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	

}
