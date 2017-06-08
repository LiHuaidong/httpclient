package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import bean.Person;

@Controller
@RequestMapping("/hdli/test")
public class TestController {
	
	@RequestMapping(value="/get", method=RequestMethod.GET)
	@ResponseBody
	public Person getPerson(@RequestParam(name="id") String id) {
		return new Person("test", 28, 173);
	}

}
