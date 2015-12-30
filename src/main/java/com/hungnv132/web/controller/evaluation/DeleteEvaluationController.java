package com.hungnv132.web.controller.evaluation;

import javax.inject.Inject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hungnv132.core.service.EvaluationService;
import com.hungnv132.web.controller.report.CreateReportController;

@Controller
public class DeleteEvaluationController {
	final Logger logger = LogManager.getLogger(DeleteEvaluationController.class);

	@Inject
	private EvaluationService evaluationService;
	
	@RequestMapping(value="/manager/evaluation/{id}/delete", method=RequestMethod.DELETE)
	@ResponseBody
	public String deleteEvaluation(@PathVariable int id){
		if(evaluationService.deleteEvaluation(id)){
			return "success";
		}
		return "failed";
	}
}
