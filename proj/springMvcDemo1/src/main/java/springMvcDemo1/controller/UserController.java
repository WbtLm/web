package springMvcDemo1.controller;

import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class UserController {
	
	/**
	 * 用户登录的处理函数
	 * @param userAccount 用于接收用户提交信息的参数  请求参数和函数参数名相同
	 * @return
	 */
	@RequestMapping( value = "log",method=RequestMethod.POST)
	@ResponseBody
	public String userLogin(String userAccount,String userPass,Model model) {
		System.out.println("user ctrl");
		model.addAttribute("name",userAccount);
		model.addAttribute("passwd",userPass);
		return "main";
	}
	@RequestMapping( value = "log1",method=RequestMethod.GET)
	@ResponseBody
	public String userLogin1(String userAccount,String userPass,Model model) {
		System.out.println("user ctrl");
		model.addAttribute("name",userAccount);
		model.addAttribute("passwd",userPass);
		Ret ret = new Ret();
		ret.setName("abcd");
		
		return "json  datas23332332323";
	}
}
class Main{
	UserController us;
	public void main() {
		UserController ctrl;
	}
}