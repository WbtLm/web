package springMvcDemo1.controller;

import org.apache.ibatis.jdbc.Null;
//import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ctrl.SessionCtrl;


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
	public String userLogin1(String account,String password,int type) {
		JSONObject json = new JSONObject();
		int success=1;
		String errCode="";
		String sessionID=sessionCtrl.getSIDbyLogin(type, account, password);
		if(sessionCtrl.isCorrect(sessionID) != 0) {
			errCode=sessionID;
			success=0;
		}
		json.put("success", success);
		json.put("sessionID", sessionID);
		json.put("errCode", errCode);
		return JSON.toJSONString(json);
	}
	
}
