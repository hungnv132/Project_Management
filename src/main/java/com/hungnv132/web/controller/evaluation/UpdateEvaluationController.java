package com.hungnv132.web.controller.evaluation;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hungnv132.core.dao.ProjectDAO;
import com.hungnv132.core.domain.Evaluation;
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
public class UpdateEvaluationController {

	final Logger logger = LogManager.getLogger(UpdateEvaluationController.class);

	@Inject
	EvaluationService evaluationService;

	@Inject
	UserService userService;

	@Inject
	ProjectDAO projectDAO;

	@Inject
	ProjectService projectService;

	@Inject
	MessageSource messageSource;

	@ModelAttribute(value = "staffList")
	public List<User> staffList() {
		return userService.getAllUserByRole(ROLE.STAFF);
	}

	/*
	 * @ModelAttribute(value="evaluationForm") public CreateEvaluationForm
	 * evaluationForm(){ return new CreateEvaluationForm(); }
	 */

	@ModelAttribute(value = "projects")
	public List<Project> projectList() {
		return projectDAO.findAllProjectName();
	}

	@RequestMapping(value = {"/manager/evaluation/{evaluationId}/edit"}, method = RequestMethod.GET)
	public String viewEvaluation(@PathVariable int evaluationId, Model model) {
		Evaluation evaluation ;
		if(AppUtils.getJoinedUser().getRole() == ROLE.ADMIN){
			 evaluation = evaluationService.findById(evaluationId);
		}else{
			 evaluation = evaluationService.findByIdWithUser(evaluationId, AppUtils.getJoinedUser());
		}
		UpdateEvaluationForm form = new UpdateEvaluationForm();
		BeanUtils.copyProperties(evaluation, form);
		form.setStaffId(evaluation.getStaff().getId());
		form.setManagerId(evaluation.getEvaluatedBy().getId());
		form.setProjectId(evaluation.getProject().getId());
		model.addAttribute("evaluationForm", form);
		if (evaluation.getTrainingCourseAttended() != null) {
			model.addAttribute("trainingCourseAttended",
					AppUtils.separateStringToList(evaluation.getTrainingCourseAttended(), '#'));
		}
		if (evaluation.getCertificate() != null) {
			model.addAttribute("certificate", AppUtils.separateStringToList(evaluation.getCertificate(), '#'));
		}	
		return "evaluation-edit";
	}

	@RequestMapping(value = "/manager/evaluation/update", method = RequestMethod.POST)
	public String updateEvaluation(@ModelAttribute(value = "evaluationForm") @Valid UpdateEvaluationForm form,
			BindingResult results, RedirectAttributes redirect, Model model) {
		if (form.getTrainingCourseAttended() != null) {
			model.addAttribute("trainingCourseAttended",
					AppUtils.separateStringToList(form.getTrainingCourseAttended(), '#'));
		}
		if (form.getCertificate() != null) {
			model.addAttribute("certificate", AppUtils.separateStringToList(form.getCertificate(), '#'));
		}
		if (results.hasErrors()) {
			ErrorEntity errorResponse = new ErrorEntity();
			List<ErrorField> listError = new ArrayList<ErrorField>();
			logger.info("Has an error");
			for (FieldError field : results.getFieldErrors()) {
				logger.info("Code " + field.getCode());
				logger.info("Field " + field.getField());
				logger.info("Message " + field.getDefaultMessage());
			}
			return "evaluation-edit";
		}
		User staff = userService.findById(form.getStaffId().intValue());
		if (staff == null) {
			results.rejectValue("staffId", "WrongId");
			return "evaluation-edit";
		}
		Project project = projectService.findById(form.getProjectId());
		if (project == null) {
			results.rejectValue("projectId", "WrongId");
			return "evaluation-edit";
		}

		form.setManagerId(AppUtils.getJoinedUser().getId());
		evaluationService.updateEvaluation(form);

		return "redirect:/manager/evaluations";
	}

}
