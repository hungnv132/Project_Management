package com.hungnv132.web.controller.request;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hungnv132.core.dao.RequestDAO;
import com.hungnv132.core.domain.Request;
import com.hungnv132.core.domain.RequestType;
import com.hungnv132.core.domain.User;
import com.hungnv132.core.domain.User.ROLE;
import com.hungnv132.core.service.RequestService;
import com.hungnv132.core.service.UserService;
import com.hungnv132.core.support.AppUtils;
import com.hungnv132.core.support.DatatableForm.ColumnKeys;
import com.hungnv132.core.support.DatatableForm.OrderKeys;
import com.hungnv132.core.support.DatatableForm.SearchKeys;
import com.hungnv132.core.support.Pagination;

@Controller
public class DisplayRequestListController {

final Logger logger = LogManager.getLogger(DisplayRequestListController.class);
	
	@Inject
	private RequestService requestService;

	@Inject
	private RequestDAO requestDAO;
	
	@Inject
	private UserService userService;
	@Inject
	MessageSource messageSource;
	
	@ModelAttribute("managers")
	public List<User> getPM() {
		logger.info(" get PM");
		return userService.findByRole(ROLE.MANAGER);
	}
	
	@RequestMapping(value = "/admin/requests/{userId}", method = RequestMethod.GET)
	public String adminManageRequest(@PathVariable("userId") int userId, Model model) {
		Calendar cal = Calendar.getInstance();
		int currentMonth = cal.get(Calendar.MONTH) + 1;
		User user = userService.findById(userId);
		model.addAttribute("userId", userId);
		model.addAttribute("user", user);
		model.addAttribute("currentMonth", currentMonth);
		model.addAttribute("totalRequestsThisMonth", requestService.countRequestThisMonthOfUser(userId) );
		model.addAttribute("totalRequestsApprovedThisMonth", requestService.countRequestApprovedThisMonthOfUser(userId) );
		return "admin-request-list";
	}
	
	@RequestMapping(value = "/staff/requests", method = RequestMethod.GET)
	public String requestPage(Model model, HttpSession session) {
//		User user = (User) session.getAttribute("joinedUser");
		model.addAttribute("userId", AppUtils.getJoinedUser().getId());
		return "user-request-list";
	}
	
//	@RequestMapping(value = "/admin/get-requests-of-user/{userId}", method = RequestMethod.GET)
//	@ResponseBody
//	public Pagination<Request> getAllRequest(@RequestParam(required = false) int draw,
//			@RequestParam(value = "start", defaultValue = "0", required = false) int start,
//			@RequestParam(value = "length", defaultValue = "10", required = false) int length,
//			@PathVariable int userId) {
//		logger.info(" draw " + draw);
//		logger.info(" start " + start);
//		logger.info(" length " + length);
//		logger.info(" get-requests-of-user/ " + userId);
//		
//		Pagination<Request> pageRequest = requestService.getPageWithUser(draw, start, length,userId);
//		return pageRequest;
//	}
	
	@RequestMapping(value = "/admin/staff/get-all-requests", method = RequestMethod.GET)
	@ResponseBody
	public Pagination<Request> getAllRequestForAdmin(DatatableRequestFormForAdmin form) {
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
		Pagination<Request> pageRequest = requestService.getRequestPageForAdmin(form);
		return pageRequest;
	}
	
	@RequestMapping(value = "/staff/get-all-requests", method = RequestMethod.POST)
	@ResponseBody
	public Pagination<Request> getAllRequest(DatatableRequestForm form) {
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
		Pagination<Request> pageRequest = requestService.getRequestPageForSTAFF(form);
		return pageRequest;
	}

	
	// display page that show all requests of staff that PM manage them
	
	@RequestMapping(value = "/manager/requests", method = RequestMethod.GET)
	public String requestList(Model model, HttpSession session) {
		return "pm-request-list";
	}

	@RequestMapping(value = "/manager/get-all-requests", method = RequestMethod.GET)
	@ResponseBody
	public Pagination<Request> getAllRequestForManager(DatatableRequestForm form) {
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
		Pagination<Request> pageRequest = requestService.getRequestPageForMANAGER(form);
		return pageRequest;
	}
	
}
