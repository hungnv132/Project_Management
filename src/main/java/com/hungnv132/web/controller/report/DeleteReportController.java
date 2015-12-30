package com.hungnv132.web.controller.report;

import javax.inject.Inject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hungnv132.core.service.ReportService;

@Controller
public class DeleteReportController {
	final Logger logger = LogManager.getLogger(CreateReportController.class);

	@Inject
	private ReportService reportService;
	
	@RequestMapping(value="/staff/delete-report/{reportId}", method=RequestMethod.DELETE)
	@ResponseBody
	public String deleteReport(@PathVariable int reportId){
		logger.info(reportId);
		if(reportService.deleteReport(reportId)){
			return "success";
		}
		return "failed";
	}
}
