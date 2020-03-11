package springMvcDemo1.controller;

import com.dao.PatientAccountDao;
import com.entity.DBPatientAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/test")
@Controller
public class TestController {
	@Autowired
    private PatientAccountDao patientAccountDao;

	@ResponseBody
	@RequestMapping(value = "/json", method = RequestMethod.POST)
	public int testJosn(@RequestBody DBPatientAccount patientAccount) {
		int result = patientAccountDao.insertPatientAccount(patientAccount);
		return 1;
	}

}


