package com.hungnv132.web.controller.admin;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hungnv132.core.dao.PositionDAO;
import com.hungnv132.core.dao.UserDAO;
import com.hungnv132.core.domain.Position;
import com.hungnv132.core.domain.User;
import com.hungnv132.core.domain.User.ROLE;
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
import com.hungnv132.web.controller.user.DatatableUserForm;

@Controller
@RequestMapping(value = "admin")
public class AdminManageUserController {

	final Logger logger = LogManager.getLogger(AdminManageUserController.class);
	@Inject
	private UserService userService;

	@Inject
	private UserDAO userDAO;
	

	@Inject
	private PositionDAO positionDAO;
	
	@Inject
	MessageSource messageSource;

	@ModelAttribute("form")
	public CreateUserForm createUserFrom() {
		return new CreateUserForm();
	}
	
	@ModelAttribute("positions")
	public List<Position> getPositions(Locale locale) {
//		List<String>  list = AppUtils.separateStringToList(messageSource.getMessage("positions", null, locale),',');
		return positionDAO.findAll();
	}
	@ModelAttribute("roles")
	public List<String> getRoles() {
		return userService.getAllRole();
	}
	
	
	@RequestMapping(value = "manage-user", method = RequestMethod.GET)
	public String manageUser(Locale locale) {
//		logger.info(messageSource.getMessage("NotEmpty", null, locale));
		return "admin-manage-user";
	}

/*	@RequestMapping(value = "get-all-user", method = RequestMethod.GET)
	@ResponseBody
	public Pagination<User> getAllUser(@RequestParam(required = false) int draw,
			@RequestParam(value = "start", defaultValue = "0", required = false) int start,
			@RequestParam(value = "length", defaultValue = "10", required = false) int length) {
		logger.info(" draw " + draw);
		logger.info(" start " + start);
		logger.info(" length " + length);
		Pagination<User> pageUser = userService.getPage(draw, start, length);
		return pageUser;
	}*/

	@RequestMapping(value = "get-all-user", method = RequestMethod.POST)
	@ResponseBody
	public Pagination<User> getAllUser(DatatableUserForm form) {
		logger.info("++++++++ draw " + form.getDraw());
		logger.info("++++++++ start " + form.getStart());
		logger.info("++++++++ length " + form.getLength());
		logger.info("++++++++ search[regex] " + form.getSearch().get(SearchKeys.regex));
		logger.info("++++++++ search[value] " + form.getSearch().get(SearchKeys.value));
		logger.info("++++++++ order[0][dir]" + form.getOrder().get(0).get("dir"));
//		logger.info("++++++++ column.data " + form.getColumns().get(0).getData());
		 List<Map<OrderKeys, String>> fieldOrder = form.getOrder();
		 for (Map<OrderKeys, String> field : fieldOrder) {
					int number =Integer.parseInt(field.get(OrderKeys.column));
					String fieldName = form.getColumns().get(number).get(ColumnKeys.data);
					String order = field.get(OrderKeys.dir);
					logger.info("++++++++ fieldName " + fieldName);
					logger.info("++++++++ order " + order);
			}
		Pagination<User> pageUser = userService.getUserByPage(form);
		return pageUser;
	}
	
	
	@RequestMapping(value = "get-all-user-test", method = RequestMethod.GET)
	@ResponseBody
	public Pagination<User> getAllUser() {
		Pagination<User> pageUser = userService.getPage(2, 0, 20);
		return pageUser;
	}

	@RequestMapping(value = "create-new-staff", method = RequestMethod.POST)
	@ResponseBody
	public Response createNewStaff(@Valid CreateUserForm form, BindingResult errors, RedirectAttributes redirect,
			Locale locale) {
		logger.info(" register a new account!!!");
		logger.info("dob " + form.getDob());
		logger.info("enroldate " + form.getEnrollmentDate());		
		
		if (errors.hasErrors()) {
			ErrorEntity errorResponse= new ErrorEntity();
			List<ErrorField> listError = new ArrayList<ErrorField>();
			logger.info("Has an error");
			for (FieldError field : errors.getFieldErrors()) {
				String code = field.getCode() + "." + field.getField();
				ErrorField e = new ErrorField();
				e.setField(field.getField());
				if (field.getField().equals("email")) {
					e.setMessage(field.getDefaultMessage());
				} else {
					e.setMessage(messageSource.getMessage(code, null, locale));
				}

				logger.info("Message " + field.getDefaultMessage());
				logger.info("Field " + field.getField());
				logger.info("Code " + field.getCode());
				listError.add(e);
			}
			errorResponse.setStatus(RES_STATUS.FAILED);
			errorResponse.setErrorList(listError);
			return errorResponse;

		} else {
			userService.createNewStaff(form.buildUser());
			ReturnEntity successResponse= new ReturnEntity();
			successResponse.setStatus(RES_STATUS.SUCCESS);
			return successResponse;
		}
	}
	
	@RequestMapping(value = "detail-user/{id}", method = RequestMethod.GET)
	@ResponseBody
	public User geUser(@PathVariable("id") int id) {
		logger.info("id "+ id);
		return userService.findById(id);
	}

}
