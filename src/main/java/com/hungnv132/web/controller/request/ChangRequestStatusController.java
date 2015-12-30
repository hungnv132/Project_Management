package com.hungnv132.web.controller.request;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hungnv132.core.service.RequestService;
import com.hungnv132.core.service.UserService;
import com.hungnv132.core.support.Response;
import com.hungnv132.core.support.Response.RES_STATUS;

@Controller
@RequestMapping(value = "manager")
public class ChangRequestStatusController {

	final Logger logger = LogManager.getLogger(ChangRequestStatusController.class);

	@Inject
	private RequestService requestService;

	@Inject
	private UserService userService;
	@Inject
	MessageSource messageSource;

//	@RequestMapping(value = "confirm-request", method = RequestMethod.GET)
//	public String requestList(Model model, HttpSession session) {
//
//		return "pm-request-list";
//	}
//
//	@RequestMapping(value = "confirm-request-list", method = RequestMethod.GET)
//	@ResponseBody
//	public Pagination<ConfirmRequestInfoForm> getAllRequest(@RequestParam(required = false) int draw,
//			@RequestParam(value = "start", defaultValue = "0", required = false) int start,
//			@RequestParam(value = "length", defaultValue = "10", required = false) int length, HttpSession session) {
//		logger.info(" draw " + draw);
//		logger.info(" start " + start);
//		logger.info(" length " + length);
//
//		User pm = (User) session.getAttribute("joinedUser");
//
//		Pagination<ConfirmRequestInfoForm> pageRequest = requestService.getPageWithPM(draw, start, length, pm.getId());
//		return pageRequest;
//	}

	@RequestMapping(value = "change-request-status", method = RequestMethod.POST)
	@ResponseBody
	public Response changeRequestStatus(@Valid ChangeRequestStatusForm form, BindingResult results, HttpSession session) {
		logger.info(" Change request status ");
		Response response = new Response();
		if (results.hasErrors()) {
			logger.info(" has error ");
			response.setStatus(RES_STATUS.FAILED);
		}
		response.setStatus(RES_STATUS.SUCCESS);
		requestService.changeRequestStaus(form);
		return response;
	}

}
