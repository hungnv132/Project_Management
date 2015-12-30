package com.hungnv132.web.controller.user;

import javax.inject.Inject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hungnv132.core.domain.User;
import com.hungnv132.core.service.MailService;
import com.hungnv132.core.service.UserService;
import com.hungnv132.core.support.AppUtils;
import com.hungnv132.core.support.Response;
import com.hungnv132.core.support.Response.RES_STATUS;


@Controller
@RequestMapping(value="staff")
public class ForgetPasswordController {
	
	final Logger logger = LogManager.getLogger(ForgetPasswordController.class);
	@Inject
	private MailService mailService;
	
	@Inject
	private UserService userService;
	
	@Inject
	Environment env;
	
	@RequestMapping(value="reset-password", method=RequestMethod.POST)
	@ResponseBody
	public Response sendEmail(String email){
		Response response = new Response();
		User user = userService.findByEmail(email);
		if (user == null) {
			response.setStatus(RES_STATUS.FAILED).setMessage("Email không tồn tại");
			logger.info("++++++++ emaill nulll");
		}else{
			String newPassword = AppUtils.randomString(12);
			String message =  "New Password is: "+ newPassword;
			boolean success = mailService.sendMail(env.getProperty("mail.from"), email, env.getProperty("mail.title"), message);
			if (success) {
				logger.info("++++++++ send successfullt");
				 user.setPassword(AppUtils.encodePassword(newPassword));
				 userService.update(user);
				 response.setStatus(RES_STATUS.SUCCESS);
			}
		}
		
		return response;
	}

}
