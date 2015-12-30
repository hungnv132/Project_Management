package com.hungnv132.web.controller.report;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.joda.time.LocalDateTime;
import org.springframework.beans.BeanUtils;
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
import com.hungnv132.core.dao.ReportDAO;
import com.hungnv132.core.dao.TaskDAO;
import com.hungnv132.core.domain.Project;
import com.hungnv132.core.domain.Report;
import com.hungnv132.core.domain.Report.REPORT_STATUS;
import com.hungnv132.core.domain.Task;
import com.hungnv132.core.service.ReportService;
import com.hungnv132.core.service.UserService;
import com.hungnv132.core.support.AppUtils;
import com.hungnv132.core.support.ErrorEntity;
import com.hungnv132.core.support.ErrorField;
import com.hungnv132.core.support.Response;
import com.hungnv132.core.support.Response.RES_STATUS;

@Controller
public class UpdateReportController {

	final Logger logger = LogManager.getLogger(UpdateReportController.class);

	@Inject
	private ReportService reportService;
	
	@Inject
	private ReportDAO reportDAO;
	
	@Inject
	private TaskDAO taskDAO;
	@Inject
	private ProjectDAO projectDAO;

	@Inject
	private UserService userService;
	@Inject
	MessageSource messageSource;

	@RequestMapping(value = "/staff/edit-report/{reportId}", method = RequestMethod.GET)
	@ResponseBody
	public Response editReportForm(@PathVariable int reportId, Model model, HttpSession session, Locale locale) {
		Response response = new Response();
		Report report = reportService.findByIdWithUser(reportId, AppUtils.getJoinedUser());
		if(report == null || report.getStatus() != REPORT_STATUS.WAITING){
			response.setStatus(RES_STATUS.FAILED);
			response.setMessage(messageSource.getMessage("CanNotModify", null, locale));
			return response;
		}
		UpdateReportForm form = new UpdateReportForm();
		BeanUtils.copyProperties(report, form);
//		form.setPmApprove(report.getConfirmBy().getId());
		form.setProjectId(report.getProject().getId());
		response.setStatus(RES_STATUS.SUCCESS);
		response.setReturnedData(form);
		return response;
	}
/*
	@RequestMapping(value = "/staff/update-report/{reportId}/edit", method = RequestMethod.POST)
	@ResponseBody
	public UpdateReportForm udpateReport(@PathVariable int reportId, Model model, HttpSession session) {
		UpdateReportForm form = new UpdateReportForm();
		Report report = reportService.findById(reportId);
		BeanUtils.copyProperties(report, form);
		form.setPmApprove(report.getManagerApprove().getId());
		return form;
	}
*/
	@RequestMapping(value = "/staff/update-report/{reportId}", method = RequestMethod.POST)
	@ResponseBody
	public Response updateReport(@PathVariable("reportId") int reportId, @Valid UpdateReportForm form, BindingResult errors,
			RedirectAttributes redirect, Locale locale) {
		logger.info(" reporting");
		logger.info(" ++++++++++++++++++++ size task " + form.getTasks().size());
		
		Response response = new Response();
		Report report = reportService.findByIdWithUser(reportId, AppUtils.getJoinedUser());
		if(report == null || report.getStatus() != REPORT_STATUS.WAITING){
			response.setStatus(RES_STATUS.FAILED);
			response.setMessage(messageSource.getMessage("CanNotModify", null, locale));
			return response;
		}
		if (errors.hasErrors()) {
			logger.info("Has an error");
			ErrorEntity errorResponse= new ErrorEntity();
			List<ErrorField> listError = new ArrayList<ErrorField>();
			boolean repeatedContentError = false;
			boolean repeatedTimeEndError = false;
			boolean repeatedTimeStartError = false;
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
						code = "NotNull.task.content";
						e.setMessage(messageSource.getMessage(code, null, locale));
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
				logger.info("field: " + e.getField() + "  message: " + e.getMessage());
				listError.add(e);
			}

			errorResponse.setStatus(RES_STATUS.FAILED);
			errorResponse.setErrorList(listError);
			return errorResponse;
		}	
		report.getTasks().clear();
		Iterator<TaskForm> iteratorTaskForm = form.getTasks().iterator();
		while (iteratorTaskForm.hasNext()) {
			Task task = iteratorTaskForm.next().buildTask();
			task.setReport(report);
			report.getTasks().add(task);
		}
		report.setCreateAt(form.getCreateAt());
		report.setUpdateAt(new LocalDateTime());
		Project project = projectDAO.findById(form.getProjectId());
		report.setProject(project);
		reportDAO.update(report);
		Response successResponse= new Response();
		successResponse.setStatus(RES_STATUS.SUCCESS);
		return successResponse;

	}

}
