package com.hungnv132.web.controller.user;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hungnv132.core.domain.User;
import com.hungnv132.core.service.UserService;
import com.hungnv132.core.support.AppUtils;
import com.hungnv132.core.support.JoinedUser;

@Controller
@RequestMapping(value = "staff")
public class UserController {

	final Logger logger = LogManager.getLogger(UpdateUserController.class);
	@Inject
	private UserService userService;
	
	@ModelAttribute("user")
	public User user() {
		logger.info("++++++++++++  @ModelAttributeu Information");
		return AppUtils.getJoinedUser();
		
	}
	
	@RequestMapping(value = "information", method = RequestMethod.GET)
	public String home() {
		logger.info("++++++++++++ user Information");
		return "user-information";
	}
	
	@RequestMapping(value = "logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
}
