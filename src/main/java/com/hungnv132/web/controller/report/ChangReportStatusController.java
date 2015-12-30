package com.hungnv132.web.controller.report;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hungnv132.core.dao.TaskDAO;
import com.hungnv132.core.domain.Report;
import com.hungnv132.core.domain.User;
import com.hungnv132.core.service.ReportService;
import com.hungnv132.core.service.UserService;
import com.hungnv132.core.support.AppUtils;
import com.hungnv132.core.support.Pagination;
import com.hungnv132.core.support.Response;
import com.hungnv132.core.support.Response.RES_STATUS;

@Controller
@RequestMapping(value = "manager")
public class ChangReportStatusController {

	final Logger logger = LogManager.getLogger(ChangReportStatusController.class);

	@Inject
	private ReportService reportService;

	@Inject
	private UserService userService;
	@Inject
	private TaskDAO taskDAO;
	@Inject
	MessageSource messageSource;

	

	@RequestMapping(value = "change-report-status", method = RequestMethod.POST)
	@ResponseBody
	public Response changeReportStatus(@Valid ChangeReportStatusForm form, BindingResult results, HttpSession session) {
		logger.info(" Change report status ");
		Response response = new Response();
		if (results.hasErrors()) {
			logger.info(" has error ");
			response.setStatus(RES_STATUS.FAILED);
		}
		response.setStatus(RES_STATUS.SUCCESS);
		reportService.changeReportStaus(form);
		return response;
	}
}
