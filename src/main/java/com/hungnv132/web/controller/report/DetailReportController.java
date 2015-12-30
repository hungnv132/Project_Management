package com.hungnv132.web.controller.report;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hungnv132.core.domain.Report;
import com.hungnv132.core.service.ReportService;
import com.hungnv132.core.service.UserService;


@Controller
public class DetailReportController {

	final Logger logger = LogManager.getLogger(DetailReportController.class);

	@Inject
	private ReportService reportService;

	@Inject
	private UserService userService;
	@Inject
	MessageSource messageSource;
	
	@RequestMapping(value = {"/staff/detail-report/{reportId}", "/manager/view-report/{reportId}"}, method = RequestMethod.GET)
	@ResponseBody
	public DetailReportForm detailReport( @PathVariable int reportId ,Model model, HttpSession session) {
		DetailReportForm form = new DetailReportForm();
		Report report=  reportService.findById(reportId);
		BeanUtils.copyProperties(report, form);
		if (report.getConfirmBy() == null) {
			form.setConfirmBy("Chưa được duyệt");
		}else{
			form.setConfirmBy(report.getConfirmBy().getFullName());
		}
		form.setProjectName(report.getProject().geName());
		form.setUserReport(report.getMemberReport().getFullName());
		return form;	
	}

}
