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
		int deptID = Integer.valueOf(departmentIDstr);
		DoctorInfo[] doctorInfo = Patient.getDoctorLstByDept(deptID);
		json.put("success","1");
		json.put("docInfoLst", doctorInfo);
		json.put("errCode", "");
		return json.toJSONString();
	}
	
	@RequestMapping(value = "work/doctor/getPatientInfo",method=RequestMethod.GET)
	@ResponseBody
	public String workDoctorGetPatientInfo(String sIDString,String patientIDString) {
		int success=0;
		JSONObject json = new JSONObject();
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
}
