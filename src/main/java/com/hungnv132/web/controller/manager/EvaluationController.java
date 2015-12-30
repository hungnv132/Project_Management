package com.hungnv132.web.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="user/pm")
public class EvaluationController {

	@RequestMapping(value="evaluation", method= RequestMethod.GET)
	public String home(){
		return "evaluation";
	}
}
