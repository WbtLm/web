package com.back;

import java.util.Date;

public class BookingRegister {
	private int registeredID;
	private int patientID;
	private int doctorID;
	private Date appointmentTime;
	private int diagnosticState;
	public BookingRegister(int registeredID,int patientID,int doctorID,Date appointmentTime,int diagnosticState) {
		this.doctorID=registeredID;
		this.patientID=patientID;
		this.doctorID=doctorID;
		this.appointmentTime=appointmentTime;
		this.diagnosticState=diagnosticState;
	}
}
