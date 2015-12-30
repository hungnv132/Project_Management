package com.hungnv132.web.controller.evaluation;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hungnv132.core.dao.ProjectDAO;
import com.hungnv132.core.domain.Evaluation;
import com.hungnv132.core.domain.Project;
import com.hungnv132.core.domain.User;
import com.hungnv132.core.domain.User.ROLE;
import com.hungnv132.core.service.EvaluationService;
import com.hungnv132.core.service.ProjectService;
import com.hungnv132.core.service.UserService;
import com.hungnv132.core.support.AppUtils;

@Controller
public class DetailEvaluationController {

	final Logger logger = LogManager.getLogger(DetailEvaluationController.class);

	@Inject
	EvaluationService evaluationService;

	@Inject
	UserService userService;

	@Inject
	ProjectDAO  projectDAO;
	
	@Inject
	ProjectService projectService;

	@Inject
	MessageSource messageSource;



	@ModelAttribute(value = "evaluationForm")
	public CreateEvaluationForm evaluationForm() {
		return new CreateEvaluationForm();
	}

	

	@RequestMapping(value = "/manager/evaluation/{evaluationId}/view", method = RequestMethod.GET)
	public String viewEvaluation(@PathVariable int evaluationId, Model model) {
		Evaluation evaluation = evaluationService.findByIdWithUser(evaluationId, AppUtils.getJoinedUser());
		model.addAttribute("evaluation", evaluation);
		if (evaluation.getTrainingCourseAttended() != null) {
			model.addAttribute("trainingCourseAttended",
					AppUtils.separateStringToList(evaluation.getTrainingCourseAttended(), '#'));
		}
		if (evaluation.getCertificate() != null) {
			model.addAttribute("certificate", AppUtils.separateStringToList(evaluation.getCertificate(), '#'));
		}

		// model.addAttribute("languageScope",
		// AppUtils.separateStringToList(evaluation.getLanguageScope(), '#'));
		return "evaluation-view";
	}
	
	@RequestMapping(value = "/admin/evaluation/{evaluationId}/view", method = RequestMethod.GET)
	public String viewEvaluationForAdmin(@PathVariable int evaluationId, Model model) {
		Evaluation evaluation = evaluationService.findById(evaluationId);
		model.addAttribute("evaluation", evaluation);
		if (evaluation.getTrainingCourseAttended() != null) {
			model.addAttribute("trainingCourseAttended",
					AppUtils.separateStringToList(evaluation.getTrainingCourseAttended(), '#'));
		}
		if (evaluation.getCertificate() != null) {
			model.addAttribute("certificate", AppUtils.separateStringToList(evaluation.getCertificate(), '#'));
		}

		// model.addAttribute("languageScope",
		// AppUtils.separateStringToList(evaluation.getLanguageScope(), '#'));
		return "evaluation-view";
	}

}
