package com.entity;
import java.util.Date;

public class DBHospital {
	
	int inHospitalId;
	int patientId;
	int doctorId;
	Date appointmentTime;
	public int getInHospitalId() {
		return inHospitalId;
	}
	public void setInHospitalId(int inHospitalId) {
		this.inHospitalId = inHospitalId;
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

}
