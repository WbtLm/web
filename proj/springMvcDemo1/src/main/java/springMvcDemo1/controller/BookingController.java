package springMvcDemo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctrl.SessionCtrl;

@Controller
public class BookingController {
//	SessionCtrl sessionCtrl = SessionCtrl.;
	@RequestMapping(value = "booking/regist/sickbed",method=RequestMethod.GET)
	@ResponseBody
	public String bookingRegistSickbed(String sidStr,String departmentIDStr,String yearString,String monthString,String dayString) {
		int sdi = Integer.valueOf(sidStr);
		int dptID= Integer.valueOf(departmentIDStr);
		int year = Integer.valueOf(yearString);
		int month = Integer.valueOf(monthString);
		int day = Integer.valueOf(dayString);
		
		
		
		return "";
	}
}
