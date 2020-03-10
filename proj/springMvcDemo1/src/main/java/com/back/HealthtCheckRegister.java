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
}
