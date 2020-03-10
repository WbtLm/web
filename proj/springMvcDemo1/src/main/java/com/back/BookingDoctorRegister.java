package com.back;

import java.util.Date;

public class BookingDoctorRegister {
	private int registeredID;
	private int patientID;
	private int doctorID;
	private Date appointmentTime;
	private int diagnosticState;
	public BookingDoctorRegister(int registeredID,int patientID,int doctorID,Date appointmentTime,int diagnosticState) {
		this.doctorID=registeredID;
		this.patientID=patientID;
		this.doctorID=doctorID;
		this.appointmentTime=appointmentTime;
		this.diagnosticState=diagnosticState;
	}
	public int getRegisteredID() {
		return registeredID;
	}
	public void setRegisteredID(int registeredID) {
		this.registeredID = registeredID;
	}
	public int getPatientID() {
		return patientID;
	}
	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}
	public int getDoctorID() {
		return doctorID;
	}
	public void setDoctorID(int doctorID) {
		this.doctorID = doctorID;
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
