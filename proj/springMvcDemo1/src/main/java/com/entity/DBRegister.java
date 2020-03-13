package com.entity;
import java.util.Date;

public class DBRegister {
	
	int registerId;
	int patientId;
	int doctorId;
	Date appointmentTime;
	int diagnosticState;
	public int getId() {
		return registerId;
	}
	public void setId(int id) {
		this.registerId = id;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public Date getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(Date appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	public int getDiagnosticState() {
		return diagnosticState;
	}
	public void setDiagnosticState(int diagnosticState) {
		this.diagnosticState = diagnosticState;
	}
	
}
