package springMvcDemo1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.back.info.DoctorInfo;
import com.back.info.PatientInfo;
import com.back.info.UserBasicInfo;
import com.back.user.Doctor;
import com.back.user.Patient;
import com.ctrl.SessionCtrl;
import com.ctrl.Utils;

public class WorkController {
	SessionCtrl sessionCtrl = SessionCtrl.getInstance();
	@RequestMapping(value = "work/patient/getDoctorLst",method=RequestMethod.GET)
	@ResponseBody
	public String workPatientGetDoctorLst(String departmentIDstr) {
		int success=0;
		JSONObject json = new JSONObject();
		if(departmentIDstr==null) {
			json.put("success","0");
			json.put("errCode", "arg==null");
			return json.toJSONString();
		}
		int deptID = Integer.valueOf(departmentIDstr);
		DoctorInfo[] doctorInfo = Patient.getDoctorLstByDept(deptID);
		json.put("success","1");
		json.put("docInfoLst", doctorInfo);
		json.put("errCode", "");
		return json.toJSONString();
	}
	
	@RequestMapping(value = "work/doctor/getPatientLst",method=RequestMethod.GET)
	@ResponseBody
	public String workDoctorGetPatientLst(String sIDString) {
		
		JSONObject json = new JSONObject();
		if(sIDString==null) {
			json.put("success","0");
			json.put("errCode", "arg==null");
			return json.toJSONString();
		}
		int uid = Utils.getBackUIDbySID(sIDString);
		boolean isDoctor = UserBasicInfo.isDoctorCapacity(sessionCtrl.getTypebySID(sIDString));
		if (uid==0) {
			json.put("success","0");
			json.put("errCode", "user unlogin");
			return json.toJSONString();
		}
		if(isDoctor==false) {
			json.put("success", "0");
			json.put("errCode","is not doctor");
			return json.toJSONString();
		}
		Doctor doctor = new Doctor();
		doctor.setUID(uid);
		PatientInfo[] patientInfoLst  = doctor.getPatientList();
		json.put("success", 1);
		json.put("patientInfoLst",	patientInfoLst);
		json.put("errCode","");
		return json.toJSONString();
	}
	
//	updateRegStatusByPatientID
	
	@RequestMapping(value = "work/doctor/setPatientCond",method=RequestMethod.GET)
	@ResponseBody
	public String workDoctorSetPatientCond(String sIDString,String patientIDString) {
		JSONObject json = new JSONObject();
		int uid = Utils.getBackUIDbySID(sIDString);
		int patientID = Integer.valueOf(patientIDString);
		boolean isDoctor = UserBasicInfo.isDoctorCapacity(sessionCtrl.getTypebySID(sIDString));
		if (uid==0) {
			json.put("success","0");
			json.put("errCode", "user unlogin");
			return json.toJSONString();
		}
		if(isDoctor==false) {
			json.put("success", "0");
			json.put("errCode","is not doctor");
			return json.toJSONString();
		}
		Doctor doctor = new Doctor();
		doctor.setUID(uid);
		boolean ret  = doctor.updateRegStatusByPatientID(patientID,0);
		if(ret==false) {
			json.put("success", 0);
			json.put("errCode","doctor.updateRegStatusByPatientID fail");
			return json.toJSONString();
		}
		json.put("success", 1);
		json.put("errCode","");
		return json.toJSONString();
	}
}
