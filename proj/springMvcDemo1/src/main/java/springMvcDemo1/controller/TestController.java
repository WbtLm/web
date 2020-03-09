package springMvcDemo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	@RequestMapping("/user/log")	//����
	public String userlogin(String userAccount,String userPass,Model model) {
			return "none";
	}
}


