package springMvcDemo1.controller;

import org.apache.catalina.ant.JKStatusUpdateTask;
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
import com.fasterxml.jackson.annotation.JsonTypeInfo.None;
import com.entity.*;

import ch.qos.logback.classic.pattern.Util;

@Controller
public class BookingController {
	SessionCtrl sessionCtrl = SessionCtrl.getInstance();
	@RequestMapping(value = "booking/regist/sickbed",method=RequestMethod.POST)
	@ResponseBody
	public String bookingRegistSickbed(@RequestBody String request) {
		JSONObject jsonObj =JSONObject.parseObject(request);
		
		String sidStr=jsonObj.getString("sidStr");
		String docUIDstr=jsonObj.getString("docUIDstr");
		String yearString=jsonObj.getString("yearString");
		String monthString=jsonObj.getString("monthString");
		String dayString=jsonObj.getString("dayString");
		int success=0;
		String bedID="";
		String errCodeString = "";
		JSONObject json = new JSONObject();
		if(sidStr==null || docUIDstr ==null || yearString == null || monthString == null || dayString == null) {
			json.put("success","0");
			json.put("errCode", "arg == null");
			return json.toJSONString();
		}
		int year = Integer.valueOf(yearString);
		int month = Integer.valueOf(monthString);
		int day = Integer.valueOf(dayString);

		int uid = Utils.getBackUIDbySID(sidStr);
		int docUID = Integer.valueOf(docUIDstr);
		
		boolean isPatient = UserBasicInfo.isPatientCapacity(sessionCtrl.getTypebySID(sidStr));
		if (uid==0) {
			json.put("success","0");
			json.put("errCode", "user unlogin");
			return json.toJSONString();
		}
		if(isPatient == false) {
			json.put("success","0");
			json.put("errCode", "is not patient");
			return json.toJSONString();
		}
		//get patient info
		Patient patient = new Patient();
		patient.setUID(uid);
		boolean ret = patient.bookingSickbed(docUID, year, month, day);
		if(ret==false) {
			json.put("success","0");
			json.put("errCode", "patient.bookingSickbed failed");
			return json.toJSONString();
		}
		
		
		json.put("success","1");
		json.put("errCode", "");
		return json.toJSONString();
	}
	
	@RequestMapping(value = "booking/regist/doctor",method=RequestMethod.GET)
	@ResponseBody
	public String bookingRegistDoctor(@RequestBody String request) {
		JSONObject jsonObj =JSONObject.parseObject(request);
		String sidStr=jsonObj.getString("sidStr");
		String docUIDstr=jsonObj.getString("docUIDstr");
		String yearString=jsonObj.getString("yearString");
		String monthString=jsonObj.getString("monthString");
		String dayString=jsonObj.getString("dayString");
		int success=0;
		String bedID="";
		String errCodeString = "";
		JSONObject json = new JSONObject();
		if(sidStr==null || docUIDstr ==null || yearString == null || monthString == null || dayString == null) {
			json.put("success","0");
			json.put("errCode", "arg == null");
			return json.toJSONString();
		}
		int year = Integer.valueOf(yearString);
		int month = Integer.valueOf(monthString);
		int day = Integer.valueOf(dayString);

		int uid = Utils.getBackUIDbySID(sidStr);
		int docUID = Integer.valueOf(docUIDstr);
		if (uid==0) {
			json.put("success","0");
			json.put("errCode", "user unlogin");
			return json.toJSONString();
		}
		boolean isPatient = UserBasicInfo.isPatientCapacity(sessionCtrl.getTypebySID(sidStr));
		
		if(isPatient == false) {
			json.put("success","0");
			json.put("errCode", "is not patient");
			return json.toJSONString();
		}
		//get patient info
		Patient patient = new Patient();
		patient.setUID(uid);
		boolean ret = patient.bookingDoctor(docUID, year, month, day,0); // 0 is unused
		if(ret==false) {
			json.put("success","0");
			json.put("errCode", "patient.bookingSickbed failed");
			return json.toJSONString();
		}
		
		
		json.put("success","1");
		json.put("errCode", "");
		return json.toJSONString();
	}
	
	@RequestMapping(value = "booking/regist/healthCheck",method=RequestMethod.GET)
	@ResponseBody
	public String bookingRegistHealthCheck(@RequestBody String request) {
		JSONObject jsonObj =JSONObject.parseObject(request);
		String sidStr=jsonObj.getString("sidStr");
		String checkTypeStr=jsonObj.getString("checkTypeStr");
		String yearString=jsonObj.getString("yearString");
		String monthString=jsonObj.getString("monthString");
		String dayString=jsonObj.getString("dayString");
		int success=0;
		String bedID="";
		String errCodeString = "";
		JSONObject json = new JSONObject();
		if(sidStr==null || checkTypeStr ==null || yearString == null || monthString == null || dayString == null) {
			json.put("success","0");
			json.put("errCode", "arg == null");
			return json.toJSONString();
		}
		int year = Integer.valueOf(yearString);
		int month = Integer.valueOf(monthString);
		int day = Integer.valueOf(dayString);

		int uid = Utils.getBackUIDbySID(sidStr);
		int checkType = Integer.valueOf(checkTypeStr);
		if (uid==0) {
			json.put("success","0");
			json.put("errCode", "user unlogin");
			return json.toJSONString();
		}
		boolean isPatient = UserBasicInfo.isPatientCapacity(sessionCtrl.getTypebySID(sidStr));
		
		if(isPatient == false) {
			json.put("success","0");
			json.put("errCode", "is not patient");
			return json.toJSONString();
		}
		//get patient info
		Patient patient = new Patient();
		patient.setUID(uid);
		boolean ret = patient.bookingHealthCheck(checkType, year, month, day);
		if(ret==false) {
			json.put("success","0");
			json.put("errCode", "patient.bookingHealthCheck failed");
			return json.toJSONString();
		}
		
		
		json.put("success","1");
		json.put("errCode", "");
		return json.toJSONString();
	}
	
	@RequestMapping(value = "booking/query/sickbedInfo",method=RequestMethod.GET)
	@ResponseBody
	public String bookingQuerySickbedInfo(@RequestBody String request) {
		JSONObject jsonObj =JSONObject.parseObject(request);
		
		String sessionID=jsonObj.getString("sessionID");
		int success=1;
		String errCode="";
		JSONObject json = new JSONObject();
		if(sessionCtrl.isCorrect(sessionID) ==0) {
			json.put("success","0");
			json.put("errCode", "SessionID无效");
			return json.toJSONString();
			
		}
		if(sessionCtrl.getTypebySID(sessionID)==1) {
			Patient patient=new Patient();
			patient.setUID(Patient.getBackIDbyDBID(sessionCtrl.getUIDbySID(sessionID)));
			DBHospital sickBedInfo=patient.querySickBedInfo();
			if(sickBedInfo==null) {
				json.put("success", "0");
				json.put("errCode","查询失败");
				return json.toJSONString();
			}
			int year=sickBedInfo.getAppointmentTime().getYear()+1900;
			int month=sickBedInfo.getAppointmentTime().getMonth()+1;
			int day=sickBedInfo.getAppointmentTime().getDay();
			json.put("success",success);
			json.put("year",year);
			json.put("month",month);
			json.put("day",day);
			json.put("errCode",errCode);
			return json.toJSONString();
		}
		else {
			json.put("success","0");
			json.put("errCode", "该身份无权查询");
			return json.toJSONString();
		}
	}
	@RequestMapping(value = "booking/query/doctorInfo",method=RequestMethod.GET)
	@ResponseBody
	public String bookingQueryDcotorInfo(@RequestBody String request) {
		JSONObject jsonObj =JSONObject.parseObject(request);
		String sessionID=jsonObj.getString("sessionID");
		int success=1;
		String errCode="";
		JSONObject json = new JSONObject();
		if(sessionCtrl.isCorrect(sessionID) ==0) {
			json.put("success","0");
			json.put("errCode", "SessionID无效");
			return json.toJSONString();
			
		}
		if(sessionCtrl.getTypebySID(sessionID)==1) {
			Patient patient=new Patient();
			patient.setUID(Patient.getBackIDbyDBID(sessionCtrl.getUIDbySID(sessionID)));
			DBRegister doctorInfo=patient.queryDoctorInfo();
			if(doctorInfo==null) {
				json.put("success", "0");
				json.put("errCode","查询失败");
				return json.toJSONString();
			}
			int year=doctorInfo.getAppointmentTime().getYear()+1900;
			int month=doctorInfo.getAppointmentTime().getMonth()+1;
			int day=doctorInfo.getAppointmentTime().getDay();
			Doctor doctor=new Doctor();
			doctor.setUID(Doctor.getBackIDbyDBID(sessionCtrl.getUIDbySID(sessionID)));
			doctor.selectInfo();
			json.put("success",success);
			json.put("registerID", doctorInfo.getId());
			json.put("doctorName", doctor.getInfo().getUserName());
			json.put("year",year);
			json.put("month",month);
			json.put("day",day);
			json.put("department",doctor.getInfo().getDepartmentID());
			json.put("errCode",errCode);
			return json.toJSONString();
		}
		else {
			json.put("success","0");
			json.put("errCode", "该身份无权查询");
			return json.toJSONString();
		}
	}
	@RequestMapping(value = "booking/query/healthCheckInfo",method=RequestMethod.GET)
	@ResponseBody
	public String bookingQueryHealthCheckInfo(@RequestBody String request) {
		JSONObject jsonObj =JSONObject.parseObject(request);
		String sessionID=jsonObj.getString("sessionID");
		int success=1;
		String errCode="";
		JSONObject json = new JSONObject();
		if(sessionCtrl.isCorrect(sessionID) ==0) {
			json.put("success","0");
			json.put("errCode", "SessionID无效");
			return json.toJSONString();
			
		}
		if(sessionCtrl.getTypebySID(sessionID)==1) {
			Patient patient=new Patient();
			patient.setUID(Patient.getBackIDbyDBID(sessionCtrl.getUIDbySID(sessionID)));
			DBAppointCheckUp appointCheckUp=patient.queryHealthCheckInfo();
			if(appointCheckUp==null) {
				json.put("success", "0");
				json.put("errCode","查询失败");
				return json.toJSONString();
			}
			int year=appointCheckUp.getAppointmentTime().getYear()+1900;
			int month=appointCheckUp.getAppointmentTime().getMonth()+1;
			int day=appointCheckUp.getAppointmentTime().getDay();
			json.put("success",success);
			json.put("type",appointCheckUp.getTypeId());
			json.put("year",year);
			json.put("month",month);
			json.put("day",day);
			json.put("errCode",errCode);
			return json.toJSONString();
		}
		else {
			json.put("success","0");
			json.put("errCode", "该身份无权查询");
			return json.toJSONString();
		}
	}
}
