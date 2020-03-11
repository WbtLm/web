package com.ctrl;

import java.util.*;
import com.dao.*;
import com.entity.*;

public class SessionCtrl {
	HashMap<Integer, String> map=new HashMap<Integer, String>();
	private DoctorDao dDao;
	private PatientDao pDao;
	private DoctorAccountDao dADao;
	private PatientAccountDao pADao;
	
	public String getSIDbyUID(int UID,int type) {
		if(map.containsKey(UID)) {
			return map.get(UID);
		}
		return encode(UID, type);
	}
	public int IsCorrect(String SID) {
		if(map.containsValue(SID)) {
			return 1;                       //是有效的SID 返回1
		} 
		return 0;
	}
	public int getUIDbySID(String SID) {
		return Integer.parseInt(SID.substring(20));
	}
	public int getTypebySID(String SID) {
		return (int)SID.indexOf(0);
	}
	public String getSIDbyLogin(int type,String account,String password) {
		int UID;
		if(password.isEmpty()) {
			return "Error";
		}
		if(type==1 && password.compareTo(pDao.getPatientPassword(account))!=0){
			return "账号或密码错误";
		}
		else if(type==2 && password.compareTo(dDao.getDoctorPassword(account))!=0) {
			return "账号或密码错误";
		}
		if(type==1) {
			UID=pADao.getIdByAccount(account);
		}
		else if(type==2) {
			UID=dADao.getIdByAccount(account);
		}
		else {
			return "身份不存在";
		}
		if(map.containsKey(UID)) {
			return map.get(UID);
		}
		return encode(UID, type);
	}
	public String getSIDbyRegist(int type,String account,String password) {
		if(type==1) {   //患者
			DBPatientAccount patientAccount=new DBPatientAccount();
			patientAccount.setAccount(account);
			patientAccount.setPassWord(password);
			pADao.insertPatientAccount(patientAccount);
		}
		else if(type==2) {  //医生
			DBDoctorAccount doctorAccount=new DBDoctorAccount();
			doctorAccount.setAccount(account);
			doctorAccount.setPassword(password);
			dADao.insertDoctorAccount(doctorAccount);
		}
		else {
			return "身份不存在";
		}
		return getSIDbyLogin(type, account, password);
	}
	private String encode(int UID,int type) {
		String code=new String();
		Random r=new Random();
		int a;
		code+=(char)type;
		for(int i=0;i<20;i++) {
			a=r.nextInt(126-48+1)+48;
			code+=(char)a;
		}
		code+=new Integer(UID).toString();
		map.put(UID, code);
		return code;
	}
}
