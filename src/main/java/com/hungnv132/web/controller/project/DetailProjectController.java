package com.hungnv132.web.controller.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hungnv132.core.dao.PositionDAO;
import com.hungnv132.core.domain.Position;
import com.hungnv132.core.domain.Project;
import com.hungnv132.core.service.ProjectService;
import com.hungnv132.core.service.UserService;
import com.hungnv132.core.support.AppUtils;
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
public class DetailProjectController {

	final Logger logger = LogManager.getLogger(DetailProjectController.class);
	@Inject
	private PositionDAO positionDAO;

	@Inject
	private UserService userService;

	@Inject
	private ProjectService projectService;
	@Inject
	MessageSource messageSource;

	@ModelAttribute("positions")
	public List<Position> getPositions(Locale locale) {
		// List<String> list =
		// AppUtils.separateStringToList(messageSource.getMessage("positions",
		// null, locale),',');
		return positionDAO.findAll();
	}

	@RequestMapping(value = "/manager/project/{projectId}", method = RequestMethod.GET)
	public String detailProject(@PathVariable int projectId, Model model) {
		Project project = projectService.findById(projectId);
		List<String> techList = AppUtils.separateStringToList(project.getTechnology(), '#');
		
		float totalDaysPlan = Days.daysBetween(project.getStartDate(), project.getEndDate()).getDays();
		float totalDaysCurrent = Days.daysBetween(project.getStartDate(), new LocalDateTime()).getDays();
		float percentDay = totalDaysCurrent/ totalDaysPlan*100;
		
		logger.info("+++++++ percentDay: "+ percentDay);
		
		model.addAttribute("project", project);
		model.addAttribute("percentDay", percentDay);
		model.addAttribute("techList", techList);
		
		
		return "project-detail";
	}
	
	
}
