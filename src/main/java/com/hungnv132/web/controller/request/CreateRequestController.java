package com.hungnv132.web.controller.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hungnv132.core.domain.Request;
import com.hungnv132.core.domain.RequestType;
import com.hungnv132.core.domain.Task;
import com.hungnv132.core.domain.User;
import com.hungnv132.core.domain.User.ROLE;
import com.hungnv132.core.service.RequestService;
import com.hungnv132.core.service.UserService;
import com.hungnv132.core.support.ErrorEntity;
import com.hungnv132.core.support.ErrorField;
import com.hungnv132.core.support.Response;
import com.hungnv132.core.support.ReturnEntity;
import com.hungnv132.core.support.Response.RES_STATUS;
import com.hungnv132.web.controller.request.CreateRequestForm.REQUEST_TYPE;

@Controller
public class CreateRequestController {

	final Logger logger = LogManager.getLogger(CreateRequestController.class);

	@Inject
	private RequestService requestService;

	@Inject
	private UserService userService;
	@Inject
	MessageSource messageSource;

	// @ModelAttribute("form")
	// public CreateRequestForm form() {
	// return new CreateRequestForm();
	// }
	//
//	 @ModelAttribute("managers")
//	 public List<User> getPM() {
//		 return userService.findByRole(ROLE.MANAGER);
//	 }

	@RequestMapping(value = "/staff/create-request", method = RequestMethod.POST)
	@ResponseBody
	public Response createRequest(@Valid CreateRequestForm form, BindingResult errors, Locale locale,
			HttpSession session) {
		logger.info(" requesting");
		User manager = userService.findById(form.getSendTo().intValue());
		REQUEST_TYPE requestType = form.getRequestType();
		if (manager == null) {
			errors.rejectValue("pmId", "Incorrect");
		}
		if (requestType != null && requestType == REQUEST_TYPE.LATE_WORKING && form.getLateworkingStart() == null) {
			logger.info("++++++++++ ngày tron ");
			errors.rejectValue("lateworkingStart", "NotEmpty");
		}
		if (requestType != null && requestType == REQUEST_TYPE.OVERTIME && form.getOvertimeStart() == null) {
			errors.rejectValue("overtimeStart", "NotEmpty");
		}
		if (requestType != null && requestType == REQUEST_TYPE.OVERTIME && form.getOvertimeEnd() == null) {
			errors.rejectValue("overtimeEnd", "NotEmpty");
		}
		if (errors.hasErrors()) {
			logger.info("Has an error");
			ErrorEntity errorResponse= new ErrorEntity();
			List<ErrorField> listError = new ArrayList<ErrorField>();
			for (FieldError field : errors.getFieldErrors()) {
				logger.info("Code " + field.getCode());
				logger.info("Field " + field.getField());
				logger.info("Message " + field.getDefaultMessage());

				String code = field.getCode() + "." + field.getField();
				ErrorField e = new ErrorField();
				e.setField(field.getField());
				e.setMessage(messageSource.getMessage(code, null, locale));
				listError.add(e);
			}
			errorResponse.setStatus(RES_STATUS.FAILED);
			errorResponse.setErrorList(listError);
			return errorResponse;
		}
		requestService.createNewRequest(form);

		ReturnEntity successResponse= new ReturnEntity();
		successResponse.setStatus(RES_STATUS.SUCCESS);
		return successResponse;

	}
}
