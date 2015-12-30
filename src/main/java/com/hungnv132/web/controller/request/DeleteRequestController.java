package com.hungnv132.web.controller.request;

import javax.inject.Inject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hungnv132.core.service.RequestService;

@Controller
public class DeleteRequestController {
	final Logger logger = LogManager.getLogger(CreateRequestController.class);

	@Inject
	private RequestService reportService;
	
	@RequestMapping(value="/staff/delete-request/{reportId}", method=RequestMethod.DELETE)
	@ResponseBody
	public String deleteRequest(@PathVariable int reportId){
		logger.info(reportId);
		if(reportService.deleteRequest(reportId)){
			return "success";
		}
		return "failed";
	}
}
