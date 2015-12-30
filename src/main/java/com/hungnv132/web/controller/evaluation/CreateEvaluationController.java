package com.hungnv132.web.controller.evaluation;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hungnv132.core.dao.ProjectDAO;
import com.hungnv132.core.domain.Project;
import com.hungnv132.core.domain.User;
import com.hungnv132.core.domain.User.ROLE;
import com.hungnv132.core.service.EvaluationService;
import com.hungnv132.core.service.ProjectService;
import com.hungnv132.core.service.UserService;
import com.hungnv132.core.support.AppUtils;
import com.hungnv132.core.support.ErrorEntity;
import com.hungnv132.core.support.ErrorField;

@Controller
public class CreateEvaluationController {

	final Logger logger = LogManager.getLogger(CreateEvaluationController.class);
	
	@Inject
	EvaluationService  evaluationService;
	
	@Inject
	UserService  userService;
	
	@Inject
	ProjectService  projectService;
	

	@Inject
	ProjectDAO  projectDAO;
	
	@Inject
	MessageSource  messageSource;
	
	@ModelAttribute(value="staffList")
	public List<User> staffList(){
		return userService.getAllUserByRole(ROLE.STAFF);
	}
	
	@ModelAttribute(value="evaluationForm")
	public CreateEvaluationForm evaluationForm(){
		return new CreateEvaluationForm();
	}
	
	@ModelAttribute(value="projects")
	public List<Project> projectList(){
		return projectDAO.findAllProjectName();
	}
	
	
	@RequestMapping(value = "/manager/evaluation/create", method =  RequestMethod.GET) 
	public String displayEvaluationPage(){
		return "evaluation-create";
	}
	
	@RequestMapping(value = "/manager/evaluation/create", method =  RequestMethod.POST) 
	public String createEvaluation(@ModelAttribute(value="evaluationForm") @Valid CreateEvaluationForm form,BindingResult results, RedirectAttributes redirect){
		if (results.hasErrors()) {
			return "evaluation-create";
		}
		User staff =userService.findById(form.getStaffId().intValue());
		if (staff == null) {
			results.rejectValue("staffId", "WrongId");
			return "evaluation-create";
		}
		Project project  = projectService.findById(form.getProjectId());
		if (project == null) {
			results.rejectValue("projectId", "WrongId");
			return "evaluation-create";
		}
		
		form.setManagerId(AppUtils.getJoinedUser().getId());
		evaluationService.createNewEvaluation(form);
		
		return "redirect:/manager/evaluations";
	}
	
}
