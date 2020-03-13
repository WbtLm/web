package com.back.info;

import com.entity.DBDoctor;
import com.entity.DBPatient;

public class AdapterDB {
	public static DoctorInfo doctorInfoEchoDB2Back(DBDoctor db) { 
		DoctorInfo retDoctorInfo = new DoctorInfo();
		retDoctorInfo.setId(db.getId()*10+1);
		retDoctorInfo.setUserName(db.getName());
		retDoctorInfo.setAge(db.getAge());
		retDoctorInfo.setName(db.getName());
		retDoctorInfo.setDepartmentID(db.getDepartmentId());
		retDoctorInfo.setTitle(db.getTitle());
		if(db.getSex()!=null && db.getSex().equals("男")) {
			retDoctorInfo.setBoy();
		}
		else if(db.getSex()!=null && db.getSex().equals("女")){
			retDoctorInfo.setGirl();
		}
		else {
			retDoctorInfo.deleteSex();
		}
		retDoctorInfo.setIDCard(db.getIdNumber());
		retDoctorInfo.deleteTel();
		
		
		return retDoctorInfo;
	}
	public static DBDoctor doctorInfoEchoBack2DB(DoctorInfo info) {
		DBDoctor ret = new DBDoctor();
		ret.setAge(info.getAge());
		ret.setDepartmentId(info.getDepartmentID());
		ret.setId(info.getId()/10);
		ret.setName(info.getName());
		ret.setIdNumber(info.getIDCard());
		if(info.isBoy()) {
			ret.setSex("男");
		}
		else if(info.isGirl()){
			ret.setSex("女");
		}
		else {
			ret.setSex("null");
		}
		ret.setTitle(info.getTitle());
		return ret;
	}
	public static PatientInfo patientInfoEchoDB2Back(DBPatient db) { 
		PatientInfo retPatientInfo = new PatientInfo();
		retPatientInfo.setId(db.getId()*10+2);
		retPatientInfo.setUserName(db.getName());
		retPatientInfo.setAge(db.getAge());
		retPatientInfo.setName(db.getName());
		retPatientInfo.setIDCard(db.getIdNumber());
		retPatientInfo.deleteTel();
		if(db.getSex()!=null && db.getSex().equals("男")) {
			retPatientInfo.setBoy();
		}
		else if(db.getSex()!=null && db.getSex().equals("女")){
			retPatientInfo.setGirl();
		}
		else {
			retPatientInfo.deleteSex();
		}
//		private String healthCareType;
//		private String allergy;
		retPatientInfo.setHealthCareType(db.getHealthCareType());
		retPatientInfo.setAllergy(db.getAllergy());
		return retPatientInfo;
	}
	public static DBPatient patientInfoEchoBack2DB(PatientInfo info) {
		DBPatient ret = new DBPatient();
		ret.setAge(info.getAge());
		ret.setAllergy(info.getAllergy());
		ret.setHealthCareType(info.getHealthCareType());
		ret.setId(info.getId()/10);
		ret.setIdNumber(info.getIDCard());
		ret.setName(info.getName());
		if(info.isBoy()) {
			ret.setSex("男");
		}
		else if(info.isGirl()) {
			ret.setSex("女");
		}
		else {
			ret.setSex("null");
		}
		return ret;
	}
}
