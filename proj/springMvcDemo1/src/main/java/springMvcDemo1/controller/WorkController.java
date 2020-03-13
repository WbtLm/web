package springMvcDemo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
@Controller
public class WorkController {
	SessionCtrl sessionCtrl = SessionCtrl.getInstance();
	//					     work/patient/getDoctorLst
	
	@RequestMapping(value = "work/patient/getDoctorLst",method=RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String workPatientGetDoctorLst(@RequestBody String request) {
		JSONObject jsonObj =JSONObject.parseObject(request);
		
		String departmentIDstr=jsonObj.getString("departmentIDstr");
		
		JSONObject json = new JSONObject();
		if(departmentIDstr==null) {
			json.put("success",0);
			json.put("errCode", "arg==null");
			return json.toJSONString();
		}
		int deptID = Integer.valueOf(departmentIDstr);
		Patient patient =new Patient();
		DoctorInfo[] doctorInfo = patient.getDoctorLstByDept(deptID);
		json.put("success",1);
		json.put("docInfoLst", doctorInfo);
		json.put("errCode", "");
		Utils.log(json.toJSONString());
		return json.toJSONString();
	}
	@RequestMapping(value = "work/doctor/getPatientLst",method=RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String workDoctorGetPatientLst(@RequestBody String request) {
		JSONObject jsonObj =JSONObject.parseObject(request);
		String sIDString=jsonObj.getString("sIDString");
		Utils.log("workDoctorGetPatientLst:doctor.sid="+sIDString);
		JSONObject json = new JSONObject();
		if(sIDString==null) {
			json.put("success",0);
			json.put("errCode", "arg==null");
			return json.toJSONString();
		}
		int uid = Utils.getBackUIDbySID(sIDString);

		boolean isDoctor = UserBasicInfo.isDoctorCapacity(sessionCtrl.getTypebySID(sIDString));
		if (uid==0) {
			json.put("success",0);
			json.put("errCode", "user unlogin");
			return json.toJSONString();
		}
		if(isDoctor==false) {
			json.put("success", 0);
			json.put("errCode","is not doctor");
			return json.toJSONString();
		}
		Doctor doctor = new Doctor();
		doctor.setUID(uid);
		Utils.log("workDoctorGetPatientLst:doctor.uid="+uid+"sid="+sIDString);
		PatientInfo[] patientInfoLst  = doctor.getPatientList();
		json.put("success", 1);
		json.put("patientInfoLst",	patientInfoLst);
		json.put("errCode","");
		Utils.log(json.toJSONString());
		return json.toJSONString();
	}
	
//	updateRegStatusByPatientID
	
	@RequestMapping(value = "work/doctor/setPatientCond",method=RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String workDoctorSetPatientCond(@RequestBody String request) {
		JSONObject jsonObj =JSONObject.parseObject(request);
		String sIDString=jsonObj.getString("sIDString");
		String patientIDString=jsonObj.getString("patientIDString");
		JSONObject json = new JSONObject();
		int uid = Utils.getBackUIDbySID(sIDString);
		int patientID = Integer.valueOf(patientIDString);
		boolean isDoctor = UserBasicInfo.isDoctorCapacity(sessionCtrl.getTypebySID(sIDString));
		if (uid==0) {
			json.put("success",0);
			json.put("errCode", "user unlogin");
			return json.toJSONString();
		}
		if(isDoctor==false) {
			json.put("success", 0);
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
