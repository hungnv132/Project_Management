package com.hungnv132.web.controller.request;

import javax.inject.Inject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hungnv132.core.domain.Request;
import com.hungnv132.core.domain.RequestType;
import com.hungnv132.core.service.RequestService;
import com.hungnv132.core.service.UserService;
import com.hungnv132.core.support.AppUtils;
import com.hungnv132.web.controller.request.DatatableRequestForm.DT_REQUEST_TYPE;


@Controller
public class DetailRequestController {

	final Logger logger = LogManager.getLogger(DetailRequestController.class);

	@Inject
	private RequestService requestService;

	@Inject
	private UserService userService;
	@Inject
	MessageSource messageSource;
	
	@RequestMapping(value = {"/staff/detail-request/{requestId}", "/manager/view-request-of-user/{requestId}"}, method = RequestMethod.GET)
	@ResponseBody
	public DetailRequestForm detailRequest( @PathVariable int requestId ) {
		DetailRequestForm form = new DetailRequestForm();
		Request request=  requestService.findById(requestId);
		BeanUtils.copyProperties(request, form);
		form.setPmApprove(request.getSendTo().getFullName());
		form.setUserRequest(request.getMemberRequest().getFullName());
		RequestType requestType = request.getRequestType();
		int requestTypeId = requestType.getId();
		form.setRequestType(DT_REQUEST_TYPE.values()[requestTypeId-1]);
		if (requestTypeId == 1) {
			form.setDaysoff(AppUtils.separateStringToList(request.getDaysoff().trim(),' '));
		}else if (requestTypeId == 2) {
			form.setDate(request.getDateTimeStart());
			form.setLateworkingStart(request.getDateTimeStart().toLocalTime());
		}else{
			form.setDate(request.getDateTimeStart());
			form.setOvertimeStart(request.getDateTimeStart().toLocalTime());
			form.setOvertimeEnd(request.getDateTimeEnd().toLocalTime());
		}
		return form;	
	}

}
