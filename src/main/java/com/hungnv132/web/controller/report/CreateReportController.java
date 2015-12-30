package com.hungnv132.web.controller.report;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hungnv132.core.dao.ProjectDAO;
import com.hungnv132.core.domain.Project;
import com.hungnv132.core.domain.Report;
import com.hungnv132.core.domain.Task;
import com.hungnv132.core.domain.User;
import com.hungnv132.core.domain.User.ROLE;
import com.hungnv132.core.service.ReportService;
import com.hungnv132.core.service.UserService;
import com.hungnv132.core.support.AppUtils;
import com.hungnv132.core.support.ErrorEntity;
import com.hungnv132.core.support.ErrorField;
import com.hungnv132.core.support.Response;
import com.hungnv132.core.support.Response.RES_STATUS;
import com.hungnv132.core.support.ReturnEntity;

@Controller
public class CreateReportController {

	final Logger logger = LogManager.getLogger(CreateReportController.class);

	@Inject
	private ReportService reportService;

	@Inject
	private UserService userService;
	
	@Inject
	private ProjectDAO projectDAO;
	
	@Inject
	MessageSource messageSource;

	@ModelAttribute("form")
	public CreateReportForm form() {
		return new CreateReportForm();
	}

	/*@ModelAttribute("manager")
	public List<User> getPM() {
		logger.info(" get PM");
		return userService.findByRole(ROLE.MANAGER);
	}
*/
	@ModelAttribute("projects")
	public List<Project> getProjects() {
		logger.info("++++++++++ get project");
		return projectDAO.findAllProjectNameByMemberId(AppUtils.getJoinedUser().getId());
	}
	
	@RequestMapping(value = "/staff/report", method = RequestMethod.GET)
	public String reportForm(Model model, HttpSession session) {
//		User user = (User) session.getAttribute("joinedUser");
		model.addAttribute("userId", AppUtils.getJoinedUser().getId());
		return "report-list";
	}

	@RequestMapping(value = "/staff/create-report", method = RequestMethod.POST)
	@ResponseBody
	public Response createReport(@Valid CreateReportForm form, BindingResult errors, RedirectAttributes redirect,
			Locale locale, HttpSession session) {
		logger.info(" reporting");
		logger.info(" size task " + form.getTasks().size());
		if (errors.hasErrors()) {
			ErrorEntity errorResponse= new ErrorEntity();
			List<ErrorField> listError = new ArrayList<ErrorField>();
			logger.info("Has an error");
			boolean repeatedContentError = false;
			boolean repeatedTimeStartError = false;
			boolean repeatedTimeEndError = false;
			for (FieldError field : errors.getFieldErrors()) {
				logger.info("Code " + field.getCode());
				logger.info("Field " + field.getField());
				logger.info("Message " + field.getDefaultMessage());

				String code = field.getCode() + "." + field.getField();
				ErrorField e = new ErrorField();
				String extractNumber = field.getField().replaceAll("\\D+", "");
				if (field.getField().contains("content")) {
					e.setField("content-" + extractNumber);
					if (repeatedContentError == false) {
						e.setMessage(messageSource.getMessage("NotNull.task.content", null, locale));
						repeatedContentError = true;
					} else {
						e.setMessage("existedMessage");
					}

				} else if (field.getField().contains("timeStart")) {
					e.setField("timeStart-" + extractNumber);
					if (repeatedTimeStartError == false) {
						e.setMessage(messageSource.getMessage("typeMismatch.task.timeStart", null, locale));
						repeatedTimeStartError = true;
					} else {
						e.setMessage("existedMessage");
					}

				}else if (field.getField().contains("timeEnd")) {
					e.setField("timeEnd-" + extractNumber);
					if (repeatedTimeEndError == false) {
						e.setMessage(messageSource.getMessage("typeMismatch.task.timeEnd", null, locale));
						repeatedTimeEndError = true;
					} else {
						e.setMessage("existedMessage");
					}

				} else {
					e.setField(field.getField());
					e.setMessage(messageSource.getMessage(code, null, locale));
				}
				listError.add(e);
			}

			errorResponse.setStatus(RES_STATUS.FAILED);
			errorResponse.setErrorList(listError);
			return errorResponse;

		}
		redirect.addFlashAttribute("reportSuccess", "success");
		
		Report report = new Report();
		
		List<Task> listTask = new ArrayList<Task>();
		for (TaskForm t : form.getTasks()) {
			Task task = new Task(t.getContent(), t.getTimeStart(), t.getTimeEnd());
			task.setReport(report);
			listTask.add(task);
		}
		
		Project project  = new Project(form.getProjectId().intValue());
		User memberReport= new User(AppUtils.getJoinedUser().getId());
		
		report.setCreateAt(form.getCreateAt());
		report.setTasks(listTask);
		report.setProject(project);
		report.setMemberReport(memberReport);
		report.setDefaultData();
		
		reportService.createNewReport(report);
		ReturnEntity successResponse= new ReturnEntity();
		successResponse.setStatus(RES_STATUS.SUCCESS);
		return successResponse;

	}
}
