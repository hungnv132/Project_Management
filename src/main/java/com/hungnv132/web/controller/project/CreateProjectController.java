package com.hungnv132.web.controller.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hungnv132.core.dao.PositionDAO;
import com.hungnv132.core.domain.Position;
import com.hungnv132.core.service.BusinessService;
import com.hungnv132.core.service.ProjectService;
import com.hungnv132.core.service.UserService;
import com.hungnv132.core.support.DatatableForm.ColumnKeys;
import com.hungnv132.core.support.DatatableForm.OrderKeys;
import com.hungnv132.core.support.DatatableForm.SearchKeys;
import com.hungnv132.core.support.ErrorEntity;
import com.hungnv132.core.support.ErrorField;
import com.hungnv132.core.support.Pagination;
import com.hungnv132.core.support.Response;
import com.hungnv132.core.support.Response.RES_STATUS;
import com.hungnv132.core.support.ReturnEntity;

@Controller
public class CreateProjectController {

	final Logger logger = LogManager.getLogger(CreateProjectController.class);
	@Inject
	private PositionDAO positionDAO;

	@Inject
	private UserService userService;

	@Inject
	private ProjectService projectService;
	
	@Inject
	private BusinessService businessService;
	
	@Inject
	MessageSource messageSource;

	@ModelAttribute("positions")
	public List<Position> getPositions(Locale locale) {
		// List<String> list =
		// AppUtils.separateStringToList(messageSource.getMessage("positions",
		// null, locale),',');
		return positionDAO.findAll();
	}

	@RequestMapping(value = "/manager/create-project", method = RequestMethod.GET)
	public String listProject() {

		return "project-create";
	}

	@RequestMapping(value = "/manager/get-all-available-members", method = RequestMethod.POST)
	@ResponseBody
	public Pagination<AvailableMemberForm> getAllUser(DatatableAvailableMemberForm form) {
		logger.info("++++++++ draw " + form.getDraw());
		logger.info("++++++++ start " + form.getStart());
		logger.info("++++++++ length " + form.getLength());
		logger.info("++++++++ search[regex] " + form.getSearch().get(SearchKeys.regex));
		logger.info("++++++++ search[value] " + form.getSearch().get(SearchKeys.value));
		logger.info("++++++++ order[0][dir]" + form.getOrder().get(0).get("dir"));
		List<Map<OrderKeys, String>> fieldOrder = form.getOrder();
		for (Map<OrderKeys, String> field : fieldOrder) {
			int number = Integer.parseInt(field.get(OrderKeys.column));
			String fieldName = form.getColumns().get(number).get(ColumnKeys.data);
			String order = field.get(OrderKeys.dir);
			logger.info("++++++++ fieldName " + fieldName);
			logger.info("++++++++ order " + order);
		}
		Pagination<AvailableMemberForm> pageAvailableMember = userService.getAvailableMembers(form);
		return pageAvailableMember;
	}

	@RequestMapping(value = "/manager/create-new-project", method = RequestMethod.POST)
	@ResponseBody
	public Response createNewProject(@Valid CreateProjectForm form, BindingResult errors, RedirectAttributes redirect,
			Locale locale) {
		logger.info("++++++++++++++ create new project");
		if (form.getBusiness() == null) {
			errors.rejectValue("business", "NotEmpty");
		}
		if (errors.hasErrors()) {
			ErrorEntity errorResponse = new ErrorEntity();
			List<ErrorField> listError = new ArrayList<ErrorField>();
			logger.info("Has an error");
			for (FieldError field : errors.getFieldErrors()) {
				String code = field.getCode() + "." + field.getField();
				ErrorField e = new ErrorField();
				e.setField(field.getField());
				
				if (field.getField().contains("businessName")) {
					e.setMessage(messageSource.getMessage("NotEmpty.businessName", null, locale));
				}else if (field.getField().contains("businessDesc")) {
					e.setMessage(messageSource.getMessage("NotEmpty.businessDesc", null, locale));
				}else if (field.getField().contains("member")) {
					e.setMessage(messageSource.getMessage("NotEmpty.members", null, locale));
				}else{
					e.setMessage(messageSource.getMessage(code, null, locale));
				}
				listError.add(e);
				logger.info("+++++++++++ "+ field.getDefaultMessage());
			}
			errorResponse.setStatus(RES_STATUS.FAILED);
			errorResponse.setErrorList(listError);
			return errorResponse;

		} else {
			logger.info("++++++++++++++ member: " + form.getBusiness().get(0).getMembers().length);
			projectService.createNewProject(form);
			ReturnEntity successResponse = new ReturnEntity();
			successResponse.setStatus(RES_STATUS.SUCCESS);
			return successResponse;
		}
	}
	@RequestMapping(value = "/manager/project/business/create", method = RequestMethod.POST)
	@ResponseBody
	public Response createNewBusiness(@Valid CreateBusinessForm form, BindingResult errors, RedirectAttributes redirect,
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
				/*	if (field.getField().contains("businessName")) {
					e.setMessage(messageSource.getMessage("NotEmpty.businessName", null, locale));
				}else if (field.getField().contains("businessDesc")) {
					e.setMessage(messageSource.getMessage("NotEmpty.businessDesc", null, locale));
				}else if (field.getField().contains("member")) {
					e.setMessage(messageSource.getMessage("NotEmpty.members", null, locale));
				}else{
					e.setMessage(messageSource.getMessage(code, null, locale));
				}*/
				listError.add(e);
			}
			errorResponse.setStatus(RES_STATUS.FAILED);
			errorResponse.setErrorList(listError);
			return errorResponse;

		} else {
			businessService.createNewBusiness(form);
			ReturnEntity successResponse = new ReturnEntity();
			successResponse.setStatus(RES_STATUS.SUCCESS);
			return successResponse;
		}
	}

}
