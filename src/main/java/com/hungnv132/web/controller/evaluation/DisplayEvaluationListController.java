package com.hungnv132.web.controller.evaluation;

import java.util.Calendar;

import javax.inject.Inject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hungnv132.core.dao.EvaluationDAOImpl;
import com.hungnv132.core.domain.Evaluation;
import com.hungnv132.core.domain.User;
import com.hungnv132.core.service.EvaluationService;
import com.hungnv132.core.service.UserService;
import com.hungnv132.core.support.AppUtils;
import com.hungnv132.core.support.DatatableForm;
import com.hungnv132.core.support.Pagination;

@Controller
public class DisplayEvaluationListController {

	final Logger logger = LogManager.getLogger(DisplayEvaluationListController.class);
	
	@Inject
	EvaluationService  evaluationService;
	@Inject
	private UserService userService;
	
	
	@RequestMapping(value = "/manager/evaluations", method =  RequestMethod.GET) 
	public String displayEvaluationPage(){
		return "evaluation";
	}
	
	@RequestMapping(value  = "/manager/get-all-evaluations", method =  RequestMethod.POST)
	@ResponseBody
	public Pagination<Evaluation> getAllEvaluation(DatatableEvaluationForm form){
		form.setManagerId(AppUtils.getJoinedUser().getId());
		return evaluationService.getEvaluationPage(form);
	}
	
	@RequestMapping(value = "/admin/evaluations/{userId}", method = RequestMethod.GET)
	public String adminManageRequest(@PathVariable("userId") int userId, Model model) {
		Calendar cal = Calendar.getInstance();
		int currentMonth = cal.get(Calendar.MONTH) + 1;
		User user = userService.findById(userId);
		model.addAttribute("userId", userId);
		model.addAttribute("user", user);
//		model.addAttribute("currentMonth", currentMonth);
//		model.addAttribute("totalRequestsThisMonth", evaluationService.countRequestThisMonthOfUser(userId) );
//		model.addAttribute("totalRequestsApprovedThisMonth", evaluationService.countRequestApprovedThisMonthOfUser(userId) );
		return "admin-evaluation-list";
	}
	@RequestMapping(value  = "/admin/get-all-evaluations/{userId}", method =  RequestMethod.POST)
	@ResponseBody
	public Pagination<DisplayEvaluationForm> getAllEvaluationByUserId(DatatableEvaluationFormForAdmin form, @PathVariable("userId") int userId){
		form.setStaffId(userId);
		
		return evaluationService.getAllEvaluationByUserId(form);
	}
}
