package com.ctrl;

import java.util.Date;

import com.back.info.UserBasicInfo;
import com.back.user.Doctor;
import com.back.user.Patient;

public class Utils {
	static public Date getDateByStr(String yearString,String monthString,String dayString) {
		int year = Integer.valueOf(yearString);
		int month = Integer.valueOf(monthString);
		int day = Integer.valueOf(dayString);
		return new Date(year-1900,month-1,day);
	}
	static public int getBackUIDbySID(String sidStr) {
		SessionCtrl sessionCtrl = SessionCtrl.getInstance();
		int uidDB = sessionCtrl.getUIDbySID(sidStr);
		int type = sessionCtrl.getTypebySID(sidStr);
		
		if(UserBasicInfo.isDoctorCapacity(type)) {
			return Doctor.getBackIDbyDBID(uidDB);
		}
		else if(UserBasicInfo.isPatientCapacity(type)){
			return Patient.getBackIDbyDBID(uidDB);
		}
		else {
			return 0;
		}
	}
}
