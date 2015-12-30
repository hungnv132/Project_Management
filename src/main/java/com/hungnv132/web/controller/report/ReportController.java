package com.hungnv132.web.controller.report;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

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
import com.hungnv132.core.support.AppUtils;
import com.hungnv132.core.support.Pagination;
import com.hungnv132.core.support.DatatableForm.ColumnKeys;
import com.hungnv132.core.support.DatatableForm.OrderKeys;
import com.hungnv132.core.support.DatatableForm.SearchKeys;


public class ReportController {

	final Logger logger = LogManager.getLogger(ReportController.class);
	
	@Inject
	private ReportService reportService;

	@Inject
	private UserService userService;
	
	@Inject
	MessageSource messageSource;
	
	@RequestMapping(value = "/admin/reports/{userId}", method = RequestMethod.GET)
	public String adminManageReport(@PathVariable("userId") int userId, Model model) {
		Calendar cal = Calendar.getInstance();
		int currentMonth = cal.get(Calendar.MONTH) + 1;
		User user = userService.findById(userId);
		model.addAttribute("userId", userId);
		model.addAttribute("user", user);
		model.addAttribute("currentMonth", currentMonth);
		model.addAttribute("totalReportsThisMonth", reportService.countReportThisMonthOfUser(userId) );
		model.addAttribute("totalReportsApprovedThisMonth", reportService.countReportApprovedThisMonthOfUser(userId) );
		return "admin-report-list";
	}

/*	@RequestMapping(value = "/report/get-reports-of-user/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public Pagination<Report> getAllReport(@RequestParam(required = false) int draw,
			@RequestParam(value = "start", defaultValue = "0", required = false) int start,
			@RequestParam(value = "length", defaultValue = "10", required = false) int length,
			@RequestParam(value = "order[0][dir]",defaultValue = "asc",required = false) String order,
			@PathVariable int userId) {
		logger.info(" draw " + draw);
		logger.info(" start " + start);
		logger.info(" length " + length);
		
		logger.info(" get-reports-of-user/ " + userId);
		
		Pagination<Report> pageReport = reportService.getPageWithStaff_Asc_AtField(draw, start, length,userId);
		return null;
	}*/
	
	@RequestMapping(value = "/staff/get-all-reports", method = RequestMethod.POST)
	@ResponseBody
	public Pagination<Report> getAllReport(DatatableReportForm form) {
		logger.info("++++++++ draw " + form.getDraw());
		logger.info("++++++++ start " + form.getStart());
		logger.info("++++++++ length " + form.getLength());
		logger.info("++++++++ search[regex] " + form.getSearch().get(SearchKeys.regex));
		logger.info("++++++++ search[value] " + form.getSearch().get(SearchKeys.value));
		logger.info("++++++++ order[0][dir]" + form.getOrder().get(0).get("dir"));
		 List<Map<OrderKeys, String>> fieldOrder = form.getOrder();
		 for (Map<OrderKeys, String> field : fieldOrder) {
					int number =Integer.parseInt(field.get(OrderKeys.column));
					String fieldName = form.getColumns().get(number).get(ColumnKeys.data);
					String order = field.get(OrderKeys.dir);
					logger.info("++++++++ fieldName " + fieldName);
					logger.info("++++++++ order " + order);
			}
		form.setForWhoId(AppUtils.getJoinedUser().getId());
		Pagination<Report> reportPage = reportService.getReportPageForSTAFF(form);
		return reportPage;
	}

	@RequestMapping(value = "/admin/get-all-report-test", method = RequestMethod.GET)
	@ResponseBody
	public Pagination<Report> getAllReport() {
		Pagination<Report> pageReport = reportService.getPage(2, 0, 20);
		return pageReport;
	}
	
}














