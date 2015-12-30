package com.hungnv132.web.controller.user;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hungnv132.core.domain.User;
import com.hungnv132.core.service.UserService;
import com.hungnv132.core.support.AppUtils;


@Controller
@RequestMapping(value="staff")
public class ChangePasswordController {
	
	final Logger logger = LogManager.getLogger(UpdateUserController.class);
	@Inject
	private UserService userService;
	
	@ModelAttribute("form")
	public ChangePasswordForm form(){
		return new ChangePasswordForm();
	}
	
	@RequestMapping(value="change-password", method=RequestMethod.GET)
	public String changePasswordForm(){	
		return "user-changepassword";
	}
	
	@RequestMapping(value="change-password", method=RequestMethod.POST)
	public String changePassword(@Valid @ModelAttribute("form") ChangePasswordForm form, BindingResult results, 
			RedirectAttributes redirect){
		logger.info("changing  password ");
		
		String joinedUserPassword = AppUtils.getJoinedUser().getPassword();
		if (!form.getOldPassword().equals("") && AppUtils.matchPassword(form.getOldPassword(), joinedUserPassword) == false) {
			results.rejectValue("oldPassword","oldPassword", "mật khẩu cũ không đúng");
			logger.info("mk cu ko dung");
		}
		if (!form.getNewPassword().equals(form.getReNewPassword()) ) {
			results.rejectValue("reNewPassword","reNewPassword", "mật khẩu không trùng ");
		}
		if (results.hasErrors()) {
			logger.info("change password failed");
			return "user-changepassword";
		}
		User user =  userService.findByEmail(AppUtils.getJoinedUser().getEmail());
		String newPass = AppUtils.encodePassword(form.getNewPassword());
		user.setPassword(newPass);
		userService.update(user);
		AppUtils.getJoinedUser().setPassword(newPass);
		logger.info("changing  password OK ");
		redirect.addFlashAttribute("changePassSuccessfully", "success");
		return "redirect:/staff/information";
	}
}
