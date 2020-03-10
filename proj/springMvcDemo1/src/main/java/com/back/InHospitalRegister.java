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
}
