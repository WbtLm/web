package com.back.user;

import java.util.Date;
import java.util.List;

import org.springframework.jmx.export.naming.IdentityNamingStrategy;

import com.back.info.AdapterDB;
import com.back.info.DoctorInfo;
import com.back.info.PatientInfo;
import com.dao.CheckupDao;
import com.dao.HospitalDao;
import com.dao.PatientDao;
import com.dao.RegisterDao;
import com.entity.DBAppointCheckUp;
import com.entity.DBDoctor;
import com.entity.DBHospital;
import com.entity.DBPatient;
import com.entity.DBRegister;

public class Patient {
	PatientInfo info;
	static PatientDao dao;
	RegisterDao registerDao;
	HospitalDao hospitalDao;
	CheckupDao checkupDao;
	public void setUID(int backID) {
		info.setId(backID);
	}
	public Patient() {
		// TODO Auto-generated constructor stub
		info = new PatientInfo();
	}
	public static int getDBIDbyBackID(int patientBackID) {
		PatientInfo pInfo = new PatientInfo();
		pInfo.setId(patientBackID);
		DBPatient dbPatient = AdapterDB.patientInfoEchoBack2DB(pInfo);//echo (back id to db id)
		return dbPatient.getId();
	}
	public static int getBackIDbyDBID(int patientDBID) {
		DBPatient dbPatient = new DBPatient();
		dbPatient.setId(patientDBID);
		return AdapterDB.patientInfoEchoDB2Back(dbPatient).getId();
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
		if(dao.updatePatient(AdapterDB.patientInfoEchoBack2DB(this.info)) == 0){
			return false;
		}
		return true;
	}
	/**
	 * deptID暂时无效。
	 * @param deptID
	 * @return
	 */
	static public DoctorInfo[] getDoctorLstByDept(int deptID) {
		List<DBDoctor> docList = dao.getDoctorList();
		DoctorInfo[] ret = new DoctorInfo[docList.size()];

		int size=0;
		for(int i=0;i<docList.size();i++) {
			ret[i]=AdapterDB.doctorInfoEchoDB2Back(docList.get(i));
			if(ret[i].getDepartmentID() == deptID) {
				size++;
			}
		}
		DoctorInfo[] retInfos = new DoctorInfo[size];
		int j=0;
		for(int i=0;i<docList.size();i++) {
			if(ret[i].getDepartmentID()==deptID) {
				retInfos[j++] = ret[i];
			}
		}
		return retInfos;
	}
//	预约体检
	public boolean bookingHealthCheck(int type,int dateYear,int dateMonth,int dateDay) {
		if(info==null) {
			return false;
		}
//		Integer insertCheckup(DBAppointCheckUp checkup);
//		int id;
//		int patientId;
//		int typeId;
//		Date appointmentTime;
		DBAppointCheckUp dbAppointCheckUp = new DBAppointCheckUp();
		dbAppointCheckUp.setPatientId(this.getDBID());
		dbAppointCheckUp.setTypeId(type);
		dbAppointCheckUp.setAppointmentTime(new Date(dateYear-1900,dateMonth-1,dateDay));
		int ret = checkupDao.insertCheckup(dbAppointCheckUp);
		if(ret==0) {
			return false;
		}
		return true;
	}
//	预约住院
	public boolean bookingSickbed(int doctorID,int dateYear,int dateMonth,int dateDay) {
		if(info==null) {
			return false;
		}
//		Integer insertHospital(DBHospital hospital);
//		int inHospitalId;
//		int patientId;
//		int doctorId;
//		Date appointmentTime;
		DBHospital dbHospital = new DBHospital();
		dbHospital.setPatientId(this.getDBID());
		dbHospital.setDoctorId(Doctor.getDBIDbyBackID(doctorID));
		dbHospital.setAppointmentTime(new Date(dateYear-1900,dateMonth-1,dateDay));
		int ret = hospitalDao.insertHospital(dbHospital);
		if(ret==0) {
			return false;
		}
		return true;
	}
//	预约挂号
	public boolean bookingDoctor(int doctorId,int dateYear,int dateMonth,int dateDay,int state) {
		if(info == null) {
			return false;
		}
//		Integer insertRegister(DBRegister register);
//		int id;
//		int patientId;
//		int doctorId;
//		Date appointmentTime;
//		int diagnosticState;
		DBRegister dbRegister = new DBRegister();
		dbRegister.setPatientId(this.getDBID());
		dbRegister.setDoctorId(Doctor.getDBIDbyBackID(doctorId));
		dbRegister.setAppointmentTime(new Date(dateYear-1900,dateMonth-1,dateDay));
		// state unused
		dbRegister.setDiagnosticState(state);
		
		int ret = registerDao.insertRegister(dbRegister);
		if(ret==0) {
			return false;
		}
		return true;
	}
//  查询体检
	public DBAppointCheckUp queryHealthCheckInfo() {
		return checkupDao.selectCheckupById(Patient.getDBIDbyBackID(info.getId()));
	}
	
//  查询住院
	public DBHospital querySickBedInfo() {
		return hospitalDao.InselectHospitalById(Patient.getDBIDbyBackID(info.getId()));
	}
	
//  查询挂号
	public DBRegister queryDoctorInfo() {
		return registerDao.selectRegisterById(Patient.getDBIDbyBackID(info.getId()));
	}
	
//  查询结果
//	public HealthCheckResult
}
