package com.back.user;

import java.util.List;

import com.back.info.AdapterDB;
import com.back.info.DoctorInfo;
import com.back.info.PatientInfo;
import com.dao.PatientDao;
import com.entity.DBDoctor;
import com.entity.DBPatient;

public class Patient {
	PatientInfo info;
	PatientDao dao;
	public Patient() {
		// TODO Auto-generated constructor stub
		info = new PatientInfo();
	}
	public int getDBID() {
		if(info!=null) {
			DBPatient dbPatient = AdapterDB.patientInfoEchoBack2DB(this.info);//echo (back id to db id)
			return dbPatient.getId();
		}
		return 0;
	}
	public PatientInfo getInfo() {
		return info;
	}
	public void setInfo(PatientInfo info) {
		this.info = info;
	}
	
	public boolean selectInfo() {
		if(info==null) {
			info = new PatientInfo();
		}
		DBPatient retDBPatient = dao.selectPatientById(this.getDBID());//select dbinfo of backinfo.id
		info = AdapterDB.patientInfoEchoDB2Back(retDBPatient);//echo dbinfo to backinfo
		if(info==null) {
			return false;
		}
		else {
			return true;
		}
	}
	public boolean updateInfo() {
		if(info==null) {
			return false;
		}
		if(dao.updatePatient(dao.selectPatientById(this.getDBID())) == 0){
			return false;
		}
		return true;
	}
	/**
	 * deptID暂时无效。
	 * @param deptID
	 * @return
	 */
	public DoctorInfo[] getDoctorLstByDept(int deptID) {
		List<DBDoctor> docList = dao.getDoctorList();
		DoctorInfo[] ret = new DoctorInfo[docList.size()];
		for(int i=0;i<docList.size();i++) {
			ret[i]=AdapterDB.doctorInfoEchoDB2Back(docList.get(i));
		}
		return ret;
	}
//	预约住院
//	预约挂号
	
}
