package com.hungnv132.web.controller.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hungnv132.core.domain.Request;
import com.hungnv132.core.domain.Request.REQUEST_STATUS;
import com.hungnv132.core.domain.RequestType;
import com.hungnv132.core.domain.User;
import com.hungnv132.core.service.RequestService;
import com.hungnv132.core.service.UserService;
import com.hungnv132.core.support.AppUtils;
import com.hungnv132.core.support.ErrorEntity;
import com.hungnv132.core.support.ErrorField;
import com.hungnv132.core.support.Response;
import com.hungnv132.core.support.Response.RES_STATUS;
import com.hungnv132.core.support.ReturnEntity;
import com.hungnv132.web.controller.request.CreateRequestForm.REQUEST_TYPE;
import com.hungnv132.web.controller.request.DatatableRequestForm.DT_REQUEST_TYPE;

@Controller
public class UpdateRequestController {

	final Logger logger = LogManager.getLogger(UpdateRequestController.class);

	@Inject
	private RequestService requestService;

	@Inject
	private UserService userService;
	@Inject
	MessageSource messageSource;

	@RequestMapping(value = "/staff/edit-request/{requestId}", method = RequestMethod.GET)
	@ResponseBody
	public Response editRequestForm(@PathVariable int requestId, Locale locale) {
		Response response = new Response();
		Request request = requestService.findById(requestId);
		if(request == null || request.getStatus() != REQUEST_STATUS.WAITING){
			response.setStatus(RES_STATUS.FAILED);
			response.setMessage(messageSource.getMessage("CanNotModify", null, locale));
			return response;
		}
		EditRequestForm form = new EditRequestForm();
		BeanUtils.copyProperties(request, form);
		form.setSendTo(request.getSendTo().getId());
		RequestType requestType = request.getRequestType();
		int requestTypeId = requestType.getId();
		form.setRequestType(DT_REQUEST_TYPE.values()[requestTypeId - 1]);
		if (requestTypeId == 1) {
			form.setDaysoff(AppUtils.separateStringToList(request.getDaysoff().trim(), ' '));
		} else if (requestTypeId == 2) {
			form.setDate(request.getDateTimeStart());
			form.setLateworkingStart(request.getDateTimeStart().toLocalTime().toString("HH:mm"));
		} else {
			form.setDate(request.getDateTimeStart());
			form.setOvertimeStart(request.getDateTimeStart().toLocalTime().toString("HH:mm"));
			form.setOvertimeEnd(request.getDateTimeEnd().toLocalTime().toString("HH:mm"));
		}
		response.setStatus(RES_STATUS.SUCCESS);
		response.setReturnedData(form);
		return response;
	}

	@RequestMapping(value = "/staff/update-request/{requestId}", method = RequestMethod.POST)
	@ResponseBody
	public Response request(@PathVariable("requestId") int requestId, @Valid UpdateRequestForm form,
			BindingResult errors, RedirectAttributes redirect, Locale locale, HttpSession session) {
		logger.info(" updating request");
		User pmApprove = userService.findById(form.getSendTo().intValue());
		REQUEST_TYPE requestType = form.getRequestType();
		if (pmApprove == null) {
			errors.rejectValue("pmId", "Incorrect");
		}
		if (requestType != null && requestType == REQUEST_TYPE.LATE_WORKING && form.getLateworkingStart() == null) {
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
		form.setId(requestId);
		requestService.updateRequest(form);
		ReturnEntity successResponse = new ReturnEntity();
		successResponse.setStatus(RES_STATUS.SUCCESS);
		return successResponse;
	}

}
