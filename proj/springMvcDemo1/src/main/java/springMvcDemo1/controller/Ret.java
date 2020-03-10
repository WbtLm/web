package springMvcDemo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


@Controller
public class Ret {
	@RequestMapping(value = "user/login",method=RequestMethod.POST)
	@ResponseBody
	public String userLogin1(String userAccount,String userPass) {
	//	{success:0/1(false/true),sessionID:""�����ɹ���,errCode:""}
		JSONObject json = new JSONObject();
		int success=0;
		int sessionID=0;
		String errCode="1";
		if(userAccount!=null && userAccount.equals(userPass)){
			success=1;
			sessionID=12121;
			errCode="0";
		}
		json.put("success", success);
		json.put("sessionID", sessionID);
		json.put("errCode", errCode);
		return JSON.toJSONString(json);
	}
	
}

