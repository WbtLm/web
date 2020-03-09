package com.back;

import com.entity.DBDoctor;
import com.entity.DBPatient;

public class AdapterDB {
	static DoctorInfo doctorInfoEchoDB2Back(DBDoctor db) { 
		DoctorInfo retDoctorInfo = new DoctorInfo();
		retDoctorInfo.setId(db.getId());
		retDoctorInfo.setUserName(db.getName());
		retDoctorInfo.setAge(db.getAge());
		retDoctorInfo.setName(db.getName());
		retDoctorInfo.setDepartmentID(db.getDepartmentId());
		retDoctorInfo.setTitle(db.getTitle());
		if(db.getSex().equals("男")) {
			retDoctorInfo.setBoy();
		}
		else {
			retDoctorInfo.setGirl();
		}
		
		retDoctorInfo.deleteIDCard();
		retDoctorInfo.deleteTel();
		return retDoctorInfo;
	}
	static DBDoctor doctorInfoEchoBack2DB(DoctorInfo info) {
		DBDoctor ret = new DBDoctor();
		ret.setAge(info.getAge());
		ret.setDepartmentId(info.getDepartmentID());
		ret.setId(info.getId());
		ret.setName(info.getName());
		if(info.isBoy()) {
			ret.setSex("男");
		}
		else {
			ret.setSex("女");
		}
		ret.setTitle(info.getTitle());
		return ret;
	}
	static PatientInfo patientInfoEchoDB2Back(DBPatient db) { 
		PatientInfo retPatientInfo = new PatientInfo();
		retPatientInfo.setId(db.getId());
		retPatientInfo.setUserName(db.getName());
		retPatientInfo.setAge(db.getAge());
		retPatientInfo.setName(db.getName());
		retPatientInfo.setIDCard(db.getIdNumber());
		retPatientInfo.deleteTel();
		if(db.getSex().equals("男")) {
			retPatientInfo.setBoy();
		}
		else {
			retPatientInfo.setGirl();
		}
//		private String healthCareType;
//		private String allergy;
		retPatientInfo.setHealthCareType(db.getHealthCareType());
		retPatientInfo.setAllergy(db.getAllergy());
		return retPatientInfo;
	}
	static DBPatient patientInfoEchoBack2DB(PatientInfo info) {
		DBPatient ret = new DBPatient();
		ret.setAge(info.getAge());
		ret.setAllergy(info.getAllergy());
		ret.setHealthCareType(info.getHealthCareType());
		ret.setId(info.getId());
		ret.setIdNumber(info.getIDCard());
		ret.setName(info.getName());
		if(info.isBoy()) {
			ret.setSex("男");
		}
		else {
			ret.setSex("女");
		}
		return ret;
	}
}
