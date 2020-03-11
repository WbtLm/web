package springMvcDemo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.back.info.PatientInfo;
import com.back.info.UserBasicInfo;
import com.back.user.Doctor;
import com.back.user.Patient;
import com.ctrl.SessionCtrl;
import com.ctrl.Utils;

import ch.qos.logback.classic.pattern.Util;

@Controller
public class BookingController {
	SessionCtrl sessionCtrl = SessionCtrl.getInstance();
	@RequestMapping(value = "booking/regist/sickbed",method=RequestMethod.GET)
	@ResponseBody
	public String bookingRegistSickbed(String sidStr,String docUIDstr,String yearString,String monthString,String dayString) {
		int success=0;
		String bedID="";
		String errCodeString = "";
		JSONObject json = new JSONObject();
		
		int year = Integer.valueOf(yearString);
		int month = Integer.valueOf(monthString);
		int day = Integer.valueOf(dayString);

		int uid = Utils.getBackUIDbySID(sidStr);
		int docUID = Integer.valueOf(docUIDstr);
		
		boolean isPatient = UserBasicInfo.isPatientCapacity(sessionCtrl.getTypebySID(sidStr));
		
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
			json.put("errCode", "is not patient");
			return json.toJSONString();
		}
		
		
		json.put("success","1");
		json.put("errCode", "");
		return json.toJSONString();
	}
}
