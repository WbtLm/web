package springMvcDemo1.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.jdbc.Null;
//import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ctrl.SessionCtrl;
import com.back.info.DoctorInfo;
import com.back.info.PatientInfo;
import com.back.user.*;

@Controller
public class UserController {

	/**
	 * 用户登录的处理函数
	 * @param userAccount 用于接收用户提交信息的参数  请求参数和函数参数名相同
	 * @return
	 */
	@RequestMapping( value = "/user/getInfo",method=RequestMethod.GET)
	@ResponseBody
	public String userLogin(String userAccount,String userPass,Model model) {
		System.out.println("user ctrl");
		model.addAttribute("name",userAccount);
		model.addAttribute("passwd",userPass);
		return "main";
	}
	
	SessionCtrl sessionCtrl = SessionCtrl.getInstance();
	@RequestMapping(value = "user/login",method=RequestMethod.POST)
	@ResponseBody
	public String userLogin1(@RequestBody String request) {	
		JSONObject jsonObj =JSONObject.parseObject(request);
		String account=jsonObj.getString("account");
		String password=jsonObj.getString("password");
		int type=jsonObj.getIntValue("type");
		
		JSONObject json = new JSONObject();
		int success=1;
		String errCode="";
		String sessionID=sessionCtrl.getSIDbyLogin(type, account, password);
		if(sessionCtrl.isCorrect(sessionID) == 0) {
			errCode=sessionID;
			success=0;
		}
		json.put("success", success);
		json.put("sessionID", sessionID);
		json.put("errCode", errCode);
		return JSON.toJSONString(json);
	}
	
	@RequestMapping(value = "user/regist/patient",method=RequestMethod.POST)
	@ResponseBody
	public String userRegistPatient(@RequestBody String regist) {
		JSONObject jsonObj =JSONObject.parseObject(regist);
		String name=jsonObj.getString("name");
		String age=jsonObj.getString("age");
		String phone=jsonObj.getString("phone");
		String password=jsonObj.getString("password");
		String idNumber=jsonObj.getString("idNumber");
		String history=jsonObj.getString("history");
		String insurance=jsonObj.getString("insurance");
		String sex=jsonObj.getString("sex");
		JSONObject json = new JSONObject();
		int success=1;
		String errCode="";
		String sessionID=sessionCtrl.getSIDbyRegist(1, phone, password);
		if(sessionCtrl.isCorrect(sessionID) == 0 ){
			errCode=sessionID;
			success=0;
		}
		else {
			Patient patient=new Patient();
			PatientInfo info=new PatientInfo();
			info.setAge(Integer.parseInt(age));
			info.setAllergy(history);
			if(sex.compareTo("man")==0)
				info.setBoy();
			else if(sex.compareTo("woman")==0)
				info.setGirl();
			info.setHealthCareType(insurance);
			info.setIDCard(idNumber);
			info.setName(name);
			info.setTel(phone);
			patient.setUID(Patient.getBackIDbyDBID(sessionCtrl.getUIDbySID(sessionID)));
			patient.setInfo(info);
			patient.updateInfo();
		}
		json.put("success", success);
		json.put("sessionID", sessionID);
		json.put("errCode", errCode);
		return JSON.toJSONString(json);
	}
	@RequestMapping(value = "user/regist/doctor",method=RequestMethod.POST)
	@ResponseBody
	public String userRegistDoctor(@RequestBody String regist){
		JSONObject jsonObj =JSONObject.parseObject(regist);
		String name=jsonObj.getString("name");
		String age=jsonObj.getString("age");
		String phone=jsonObj.getString("phone");
		String password=jsonObj.getString("password");
		String idNumber=jsonObj.getString("idNumber");
		String sex=jsonObj.getString("sex");
		String department=jsonObj.getString("department");
		String title=jsonObj.getString("title");
		JSONObject json = new JSONObject();
		int success=1;
		String errCode="";
		String sessionID=sessionCtrl.getSIDbyRegist(2, phone, password);
		if(sessionCtrl.isCorrect(sessionID) == 0 ){
			errCode=sessionID;
			success=0;
		}
		else {
			Doctor doctor=new Doctor();
			DoctorInfo info=new DoctorInfo();
			if(sex.compareTo("man")==0)
				info.setBoy();
			else if(sex.compareTo("woman")==0)
				info.setGirl();
			info.setIDCard(idNumber);
			info.setTel(phone);
			info.setTitle(title);
			info.setDepartmentID(Integer.parseInt(department));
			doctor.setUID(Doctor.getBackIDbyDBID(sessionCtrl.getUIDbySID(sessionID)));
			doctor.setInfo(info);
			doctor.updateInfo();
		}
		json.put("success", success);
		json.put("sessionID", sessionID);
		json.put("errCode", errCode);
		return JSON.toJSONString(json);
	}

}
