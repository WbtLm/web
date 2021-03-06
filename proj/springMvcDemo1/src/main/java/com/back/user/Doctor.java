package com.back.user;

import com.dao.DoctorAccountDao;
import com.dao.DoctorDao;
import com.dao.PatientAccountDao;
import com.dao.PatientDao;
import com.entity.DBDoctor;
import com.entity.DBPatient;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.sun.xml.internal.bind.v2.model.core.Adapter;

import java.util.List;

import javax.sound.midi.MidiDevice.Info;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSONObject;
import com.back.info.*;
import com.ctrl.Utils;
public class Doctor {
	DoctorInfo info;
	private DoctorDao dao;
	public void setUID(int uidBack) {
		this.info.setId(uidBack);
	}
	public static int getDBIDbyBackID(int doctorBackID) {
		DoctorInfo pInfo = new DoctorInfo();
		pInfo.setId(doctorBackID);
		DBDoctor dbDoctor = AdapterDB.doctorInfoEchoBack2DB(pInfo);//echo (back id to db id)
		return dbDoctor.getId();
	}
	public static int getBackIDbyDBID(int doctorDBID) {
		DBDoctor dbDoctor = new DBDoctor();
		dbDoctor.setId(doctorDBID);
		return AdapterDB.doctorInfoEchoDB2Back(dbDoctor).getId();
	}
	public int getDBID() {
		if(info!=null) {
			DBDoctor dbDoctor = AdapterDB.doctorInfoEchoBack2DB(this.info);//echo (back id to db id)
			return dbDoctor.getId();
		}
		return 0;
	}
	public Doctor() {
		// TODO Auto-generated constructor stub
		info = new DoctorInfo();
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring_context.xml");
		dao = (DoctorDao) ctx.getBean("doctorDao");
	}
	public DoctorInfo getInfo() {
		return info;
	}
	public void setInfo(DoctorInfo info) {
		this.info = info;
	}
	public boolean selectInfo() {
		if(info==null) {
			info = new DoctorInfo();
		}
		DBDoctor retDBDoctor = dao.selectDoctorById(this.getDBID());//select dbinfo of backinfo.id
		if(retDBDoctor == null) {
			Utils.log("dao.selectDoctorById failed DBID="+this.getDBID());
			return false;
		}
		info = AdapterDB.doctorInfoEchoDB2Back(retDBDoctor);//echo dbinfo to backinfo
		if(info==null) {
			return false;
		}
		else {
			return true;
		}
	}
	public boolean insertInfo() {
//		Integer insertPatient(DBPatient patient);
		if(info==null)
			return false;
		if(dao.insertDoctor(AdapterDB.doctorInfoEchoBack2DB(this.info))==0) {
			return false;
		}
		return true;
	}
	public boolean updateInfo() {
		if(info==null) {
			return false;
		}
//		JSONObject jsonObject= new JSONObject();
//		jsonObject.put("obj", this);
//		Utils.log(jsonObject.toJSONString());
		if(dao.updateDoctor(AdapterDB.doctorInfoEchoBack2DB(this.info)) == 0){
			return false;
		}
		return true;
	}
	public PatientInfo[] getPatientList() {
		List<DBPatient> retDbPatients = dao.getPatientList(getDBID());
		int size=retDbPatients.size();
		PatientInfo[] ret= new PatientInfo[size];
		for(int i=0;i<size;i++) {
			ret[i] = AdapterDB.patientInfoEchoDB2Back(retDbPatients.get(i));
		}
		return ret;
	}
	public boolean updateRegStatusByPatientID(int patientUID,int statusTo) {
		//Integer updateRegisterStatus(int d_id,int p_id,int i);
		
		if(dao.updateRegisterStatus(this.getDBID(), Patient.getDBIDbyBackID(patientUID), statusTo)==1) {
			return true;
		}
		return false;
	}
	
}
