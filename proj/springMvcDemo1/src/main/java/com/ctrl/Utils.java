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
		return sessionCtrl.getUIDbySID(sidStr);
	}
	static public void log(String log) {
		if(log==null) {
			log= "log.null";
		}
		System.out.println(log);
	}
}
