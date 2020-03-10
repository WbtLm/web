package com.back;

import java.util.Date;

public class InHospitalRegister {
	private int inHospitalID;
	private int patientID;
	private int doctorID;
	private Date AppointmentTime;
	
	public InHospitalRegister(int inHospitalID,int patientID,int doctorID,Date AppointmentTime) {
		this.inHospitalID=inHospitalID;
		this.patientID=patientID;
		this.doctorID=doctorID;
		this.AppointmentTime=AppointmentTime;
	}

	public int getInHospitalID() {
		return inHospitalID;
	}

	public void setInHospitalID(int inHospitalID) {
		this.inHospitalID = inHospitalID;
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
		return AppointmentTime;
	}

	public void setAppointmentTime(Date appointmentTime) {
		AppointmentTime = appointmentTime;
	}
	
}
