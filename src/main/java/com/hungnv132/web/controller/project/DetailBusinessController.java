package com.hungnv132.web.controller.project;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.joda.time.Days;
import org.joda.time.LocalDateTime;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hungnv132.core.dao.BusinessDAO;
import com.hungnv132.core.dao.PositionDAO;
import com.hungnv132.core.domain.Business;
import com.hungnv132.core.domain.Position;
import com.hungnv132.core.service.ProjectService;
import com.hungnv132.core.support.AppUtils;

@Controller
public class DetailBusinessController {

	final Logger logger = LogManager.getLogger(DetailBusinessController.class);
	@Inject
	private PositionDAO positionDAO;


	@Inject
	private BusinessDAO businessDAO;
	
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

	@RequestMapping(value = "/manager/project/business/{businessId}", method = RequestMethod.GET)
	@ResponseBody
	public Business detailProject(@PathVariable int businessId, Model model) {
		Business business = businessDAO.findById(businessId);
		
		return business;
	}
}
