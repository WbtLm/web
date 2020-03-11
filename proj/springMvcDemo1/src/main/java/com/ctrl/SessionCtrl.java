package com.ctrl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

import com.dao.*;
import com.entity.*;

public class SessionCtrl {
	HashMap<Integer, String> map=new HashMap<Integer, String>();
	private static SessionCtrl instance = new SessionCtrl();
	@Autowired
	private DoctorDao dDao;
	@Autowired
	private PatientDao pDao;
	@Autowired
	private DoctorAccountDao dADao;
	@Autowired
	private PatientAccountDao pADao;
	
	private SessionCtrl() {}
	public static SessionCtrl getInstance() {
		return instance;
	}
	public String getSIDbyUID(int UID,int type) {
		if(map.containsKey(UID)) {
			return map.get(UID);
		}
		return encode(UID, type);
	}
	public int isCorrect(String SID) {
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
		int UID=0;
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
			if(pADao.insertPatientAccount(patientAccount)==0) {
				return "注册失败";
			}
		}
		else if(type==2) {  //医生
			DBDoctorAccount doctorAccount=new DBDoctorAccount();
			doctorAccount.setAccount(account);
			doctorAccount.setPassword(password);
			if(dADao.insertDoctorAccount(doctorAccount)==0) {
				return "注册失败";
			}
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
		code+=new Integer(type).toString();
		for(int i=0;i<20;i++) {
			a=r.nextInt(126-48+1)+48;
			code+=(char)a;
		}
		code+=new Integer(UID).toString();
		map.put(UID, code);
		return code;
	}
}
