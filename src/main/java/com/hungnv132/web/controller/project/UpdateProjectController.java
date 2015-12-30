
package com.hungnv132.web.controller.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.joda.time.Days;
import org.joda.time.LocalDateTime;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hungnv132.core.dao.ProjectDAO;
import com.hungnv132.core.domain.Project;
import com.hungnv132.core.service.BusinessService;
import com.hungnv132.core.service.ProjectService;
import com.hungnv132.core.service.UserService;
import com.hungnv132.core.support.AppUtils;
import com.hungnv132.core.support.ErrorEntity;
import com.hungnv132.core.support.ErrorField;
import com.hungnv132.core.support.Response;
import com.hungnv132.core.support.Response.RES_STATUS;
import com.hungnv132.core.support.ReturnEntity;

@Controller
public class UpdateProjectController {

	final Logger logger = LogManager.getLogger(UpdateProjectController.class);

	@Inject
	private UserService userService;

	@Inject
	private ProjectService projectService;
	
	@Inject
	private ProjectDAO projectDAO;
	
	@Inject
	private BusinessService businessService;
	
	@Inject
	MessageSource messageSource;
	
	@RequestMapping(value = "/manager/project/business/update", method = RequestMethod.POST)
	@ResponseBody
	public Response updateBusiness(@Valid UpdateBusinessForm form, BindingResult errors, RedirectAttributes redirect,
			Locale locale) {
		logger.info("++++++++++++++ create new project");
		if (errors.hasErrors()) {
			ErrorEntity errorResponse = new ErrorEntity();
			List<ErrorField> listError = new ArrayList<ErrorField>();
			logger.info("Has an error");
			for (FieldError field : errors.getFieldErrors()) {
				String code = field.getCode() + "." + field.getField();
				ErrorField e = new ErrorField();
				e.setField(field.getField());
				e.setMessage(messageSource.getMessage(code, null, locale));
				listError.add(e);
			}
			errorResponse.setStatus(RES_STATUS.FAILED);
			errorResponse.setErrorList(listError);
			return errorResponse;

		} else {
			businessService.updateBusiness(form);
			ReturnEntity successResponse = new ReturnEntity();
			successResponse.setStatus(RES_STATUS.SUCCESS);
			return successResponse;
		}
	}
	
	
	@RequestMapping(value = "/manager/project/edit/{projectId}", method = RequestMethod.POST)
	@ResponseBody
	public Project getProjectInfo(@PathVariable int projectId) {
		Project project = projectService.findById(projectId);
		return project;
	}
	
	@RequestMapping(value = "/manager/project/update", method = RequestMethod.POST)
	@ResponseBody
	public Response updateProject(@Valid UpdateProjectForm form, BindingResult errors, RedirectAttributes redirect,
			Locale locale) {
		logger.info("++++++++++++++ update project");
		if (errors.hasErrors()) {
			ErrorEntity errorResponse = new ErrorEntity();
			List<ErrorField> listError = new ArrayList<ErrorField>();
			logger.info("Has an error");
			for (FieldError field : errors.getFieldErrors()) {
				String code = field.getCode() + "." + field.getField();
				ErrorField e = new ErrorField();
				e.setField(field.getField());
				e.setMessage(messageSource.getMessage(code, null, locale));
				listError.add(e);
			}
			errorResponse.setStatus(RES_STATUS.FAILED);
			errorResponse.setErrorList(listError);
			return errorResponse;

		} else {
			projectService.updateProject(form);
			ReturnEntity successResponse = new ReturnEntity();
			successResponse.setStatus(RES_STATUS.SUCCESS);
			return successResponse;
		}
	}
}














