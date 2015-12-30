package com.hungnv132.web.controller.user;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hungnv132.core.domain.User;
import com.hungnv132.core.service.UserService;
import com.hungnv132.core.support.AppUtils;
import com.hungnv132.core.support.JoinedUser;

@Controller
@RequestMapping(value="staff")
public class UpdateUserController {

	final Logger logger = LogManager.getLogger(UpdateUserController.class);
	@Inject
	private UserService userService;

	@ModelAttribute("user")
	public User user() {
		logger.info("+++++++++++ pass: "+ AppUtils.getJoinedUser().getPassword());
		User refreshedUser = userService.findByEmail(AppUtils.getJoinedUser().getEmail().trim());
		return refreshedUser;
	}

	@RequestMapping(value = "update", method = RequestMethod.GET)
	public String updateUser(@ModelAttribute("user") User user, Model model) {
		logger.info("Name;  " + user.getEmail());
		UpdateUserForm form = UpdateUserForm.copyFromUser(user);
		// form.setFirstName("new new ");
		model.addAttribute("form", form);
		return "user-update";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String updateUser(@Valid @ModelAttribute("form") UpdateUserForm form, BindingResult errors,
			HttpSession session, RedirectAttributes redirect, Model model) {
		logger.info(" Update usser!!!");
		logger.info("First Name " + form.getFirstName());
		if (errors.hasErrors()) {
			logger.info("Has an error");
			for (FieldError field : errors.getFieldErrors()) {
				logger.info("Code " + field.getCode());
				logger.info("Field " + field.getField());
				logger.info("Message " + field.getDefaultMessage());
			}
			return "user-update";

		} 
		if(userService.findByEmailAndNotId(form.getEmail(), form.getId()) != null) {
			errors.rejectValue("email", "UsedEmail");
			return "user-update";
		}
		userService.updateUserInfo(form);
		redirect.addFlashAttribute("updateSuccessfully", "success");
		User user = userService.findByEmail(form.getEmail().trim());
		logger.info("++++++++++++ rolw: " + user.getCollectionAuthorities().iterator().next().getAuthority());
		JoinedUser joinedUser = new JoinedUser(user);
		UsernamePasswordAuthenticationToken  authentication = new UsernamePasswordAuthenticationToken(joinedUser, user.getPassword(), user.getCollectionAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
//		session.setAttribute("joinedUser", userService.findById(form.getId()));
		return "redirect:/staff/information";
	}
}
