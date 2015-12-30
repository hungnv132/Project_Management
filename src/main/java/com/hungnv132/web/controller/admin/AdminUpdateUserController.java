package com.hungnv132.web.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hungnv132.core.domain.User;
import com.hungnv132.core.service.UserService;
import com.hungnv132.core.support.ErrorEntity;
import com.hungnv132.core.support.ErrorField;
import com.hungnv132.core.support.JoinedUser;
import com.hungnv132.core.support.Response;
import com.hungnv132.core.support.ReturnEntity;
import com.hungnv132.core.support.Response.RES_STATUS;
import com.hungnv132.web.controller.report.UpdateReportForm;
import com.hungnv132.web.controller.user.UpdateUserForm;

@Controller
@RequestMapping(value="admin")
public class AdminUpdateUserController {
	final Logger logger = LogManager.getLogger(AdminManageUserController.class);
	@Inject
	private UserService userService;

	@Inject
	MessageSource messageSource;

	@ModelAttribute("form")
	public CreateUserForm createUserFrom() {
		return new CreateUserForm();
	}
	
	@RequestMapping(value = "get-user/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public User getUser(@PathVariable int userId) {
		return userService.findById(userId);
	}

	@RequestMapping(value = "update-user", method = RequestMethod.POST)
	@ResponseBody
	public Response adminUpdateUser(@Valid AdminUpdateUserForm form, BindingResult errors, RedirectAttributes redirect,
			Locale locale) {
		logger.info(" register a new account!!!");
		logger.info("dob " + form.getDob());
		logger.info("enroldate " + form.getEnrollmentDate());		
		
		if (errors.hasErrors()) {
			ErrorEntity errorResponse= new ErrorEntity();
			List<ErrorField> listError = new ArrayList<ErrorField>();
			logger.info("Has an error");
			for (FieldError field : errors.getFieldErrors()) {
				String code = field.getCode() + "." + field.getField();
				ErrorField e = new ErrorField();
				e.setField(field.getField());
				if (field.getField().equals("email")) {
					e.setMessage(field.getDefaultMessage());
				} else {
					e.setMessage(messageSource.getMessage(code, null, locale));
				}

				logger.info("Message " + field.getDefaultMessage());
				logger.info("Field " + field.getField());
				listError.add(e);
			}
			
			errorResponse.setStatus(RES_STATUS.FAILED);
			errorResponse.setErrorList(listError);
			return errorResponse;

		} else {
			userService.adminUpdateUserInfo(form);
			ReturnEntity successResponse= new ReturnEntity();
			successResponse.setStatus(RES_STATUS.SUCCESS);
			return successResponse;
		}
	}
}
