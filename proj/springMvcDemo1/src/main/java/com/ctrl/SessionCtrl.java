package com.ctrl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.back.info.DoctorInfo;
import com.back.info.PatientInfo;
import com.back.user.Doctor;
import com.back.user.Patient;
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
	
	private SessionCtrl() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring_context.xml");
		pDao = (PatientDao) ctx.getBean("patientDao");
		dDao = (DoctorDao) ctx.getBean("doctorDao");
		dADao = (DoctorAccountDao) ctx.getBean("doctorAccountDao");
		pADao = (PatientAccountDao) ctx.getBean("patientAccountDao");

	}
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
		System.out.println("getTypebySID.input SID = "+SID);
		return Integer.parseInt(SID.substring(21));
	}
	public int getTypebySID(String SID) {
		System.out.println("getTypebySID.input SID = "+SID);
		int ret=(int)SID.charAt(0) - '0';
		Utils.log(""+ret);
		return ret;
	}
	public String getSIDbyLogin(int type,String account,String password) {
		int UID=0;
		
		System.out.println("is null");
		if(password.isEmpty() || password==null) {
			return "Error";
		}
		System.out.println("passwd");
		if(type==1 && !password.equals(pDao.getPatientPassword(account))){
			return "username or passwd wrong";
		}
		else if(type==2 && !password.equals(dDao.getDoctorPassword(account))) {
			return "username or passwd wrong";
		}
		System.out.println("type");
		if(type==1) {
			UID=pADao.getIdByAccount(account);
			PatientInfo pInfo = new PatientInfo();
			UID=Patient.getBackIDbyDBID(UID);
		}
		else if(type==2) {
			UID=dADao.getIdByAccount(account);
			DoctorInfo dInfo = new DoctorInfo();
			UID=Doctor.getBackIDbyDBID(UID);
		}
		else {
			return "neither doctor or patient.";
		}
		System.out.println("map");
		if(map.containsKey(UID)) {
			return map.get(UID);
		}
	System.out.println("UID"+UID);
		return encode(UID, type);
	}
	public String getSIDbyRegist(int type,String account,String password) {
		if(type==1) {   //患者
			DBPatientAccount patientAccount=new DBPatientAccount();
			patientAccount.setAccount(account);
			patientAccount.setPassWord(password);
			if(pDao.getPatientPassword(account)!=null) {
				return "注册失败，重复注册";
			}
			if(pADao.insertPatientAccount(patientAccount)==0) {
				return "注册失败：新建账户失败";
			}
			
			int UID=pADao.getIdByAccount(account);
			if(UID==0) {
				Utils.log("error:pADao.getIDByAccount() UID==0");
				return "error:pADao.getIDByAccount() UID==0";
			}
			PatientInfo pInfo = new PatientInfo();
			UID=Patient.getBackIDbyDBID(UID);
			pInfo.setId(UID);
			Patient patient = new Patient();
			patient.setInfo(pInfo);
			boolean ret = patient.insertInfo();
			if(ret == false) {
				Utils.log("error:account establish success but: info failed");
				return "error:account establish success but: info failed";
			}
		}
		else if(type==2) {  //医生
			DBDoctorAccount doctorAccount=new DBDoctorAccount();
			doctorAccount.setAccount(account);
			doctorAccount.setPassword(password);
			if(dDao.getDoctorPassword(account)!=null) {
				return "注册失败，重复注册";
			}
			if(dADao.insertDoctorAccount(doctorAccount)==0) {
				return "注册失败：新建账户失败";
			}
			int UID=dADao.getIdByAccount(account);
			DoctorInfo dInfo = new DoctorInfo();
			UID=Doctor.getBackIDbyDBID(UID);
			dInfo.setId(UID);
			if(UID==0) {
				Utils.log("error:pADao.getIDByAccount() UID==0");
				return "error:pADao.getIDByAccount() UID==0";
			}
			Doctor doctor = new Doctor();
			doctor.setInfo(dInfo);
			boolean ret = doctor.insertInfo();
			if(ret == false) {
				Utils.log("error:account establish success but: info failed");
				return "error:account establish success but: info failed";
			}
		}
		else {
			return "身份不存在";
		}
		return getSIDbyLogin(type, account, password);
	}
	private String encode(int UID,int type) {
		if(UID==0 || type!=1 && type!=2) {
			return "用户信息错误";
		}
		String code=new String();
		Random r=new Random();
		int a;
		code+=new Integer(type).toString();
		for(int i=0;i<20;i++) {
			a=r.nextInt(126-48+1)+48;
			code+=(char)a;
		}
		code+=new Integer(UID).toString();
		System.out.println("encode.return = "+code);
		map.put(UID, code);
		return code;
	}
}
