package com.ctrl;

import java.util.Date;

public class Utils {
	static public Date getDateByStr(String yearString,String monthString,String dayString) {
		int year = Integer.valueOf(yearString);
		int month = Integer.valueOf(monthString);
		int day = Integer.valueOf(dayString);
		return new Date(year-1900,month-1,day);
	}
}
