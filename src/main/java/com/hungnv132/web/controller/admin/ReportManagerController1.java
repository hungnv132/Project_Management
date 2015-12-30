package com.hungnv132.web.controller.admin;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hungnv132.core.domain.Report;
import com.hungnv132.core.domain.User;
import com.hungnv132.core.service.ReportService;
import com.hungnv132.core.service.UserService;
import com.hungnv132.core.support.Pagination;

//@Controller
//@RequestMapping(value = "admin")
public class ReportManagerController1 {

	final Logger logger = LogManager.getLogger(ReportManagerController1.class);
	
	@Inject
	private ReportService reportService;

	@Inject
	private UserService userService;
	@Inject
	MessageSource messageSource;
	
	@RequestMapping(value = "reports/{userId}", method = RequestMethod.GET)
	public String manageReport(@PathVariable("userId") int userId, Model model) {
		Calendar cal = Calendar.getInstance();
		int currentMonth = cal.get(Calendar.MONTH) + 1;
		User user = userService.findById(userId);
		model.addAttribute("userId", userId);
		model.addAttribute("user", user);
		model.addAttribute("currentMonth", currentMonth);
		model.addAttribute("totalReportsThisMonth", reportService.countReportThisMonthOfUser(userId) );
		model.addAttribute("totalReportsApprovedThisMonth", reportService.countReportApprovedThisMonthOfUser(userId) );
		return "report-list";
	}

	@RequestMapping(value = "get-reports-of-user/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public Pagination<Report> getAllReport(@RequestParam(required = false) int draw,
			@RequestParam(value = "start", defaultValue = "0", required = false) int start,
			@RequestParam(value = "length", defaultValue = "10", required = false) int length,
			@PathVariable int userId) {
		logger.info(" draw " + draw);
		logger.info(" start " + start);
		logger.info(" length " + length);
		logger.info(" get-reports-of-user/ " + userId);
		
//		Pagination<Report> pageReport = reportService.getPageWithUser(draw, start, length,userId);
		return null;
	}

	@RequestMapping(value = "get-all-report-test", method = RequestMethod.GET)
	@ResponseBody
	public Pagination<Report> getAllReport() {
		Pagination<Report> pageReport = reportService.getPage(2, 0, 20);
		return pageReport;
	}
}
