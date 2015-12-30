package com.hungnv132.web.controller.user;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hungnv132.core.domain.User;
import com.hungnv132.core.service.UserService;

@Controller
public class LoginController {

	final Logger logger = LogManager.getLogger(LoginController.class);
	@Inject
	private UserService userService;

	@ModelAttribute("form")
	public LoginForm form() {
		return new LoginForm();
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String loginForm() {
		return "login";
	}

//	@RequestMapping(value = "login", method = RequestMethod.POST)
//	public String login(@Valid @ModelAttribute("form") LoginForm form, BindingResult errors,
//			RedirectAttributes redirect, HttpSession session, Model model) {
//
//		String email = form.getEmail().trim();
//		String password = form.getPassword().trim();
//
//		logger.info("Email:" + email);
//		logger.info("Password:" + password);
//		if (errors.hasErrors()) {
//			for (FieldError field : errors.getFieldErrors()) {
//				logger.info(field.getCode() + " " + field.getDefaultMessage());
//			}
//			model.addAttribute("error", true);
//			return "/login";
//		}
//		// if (userService.findByEmail(email) == null) {
//		// errors.rejectValue("email", "IncorrectEmail");
//		// } else
//		User user = userService.findByEmailAndPassword(email, password);
//		if (user == null) {
//			errors.rejectValue("password", "IncorrectPassword");
//		}
//		if (errors.hasErrors()) {
//			model.addAttribute("error", true);
//			return "/login";
//		}
//		session.setAttribute("joinedUser", user);
//		return "redirect:/user/information";
//	}

//	@RequestMapping(value = "login", method = RequestMethod.POST)
//	public String login(RedirectAttributes redirect, HttpSession session) {
//
//		session.setAttribute("joinedUser", userService.findById(1));
//
//		return "redirect:/staff/information";
//	}
//	@RequestMapping(value = "login/staff", method = RequestMethod.GET)
//	public String login2(RedirectAttributes redirect, HttpSession session) {
//
//		session.setAttribute("joinedUser", userService.findById(2));
//
//		return "redirect:/staff/information";
//	}
//	
//	@RequestMapping(value = "login/pm", method = RequestMethod.GET)
//	public String login3(RedirectAttributes redirect, HttpSession session) {
//
//		session.setAttribute("joinedUser", userService.findById(3));
//
//		return "redirect:/staff/information";
//	}

}
