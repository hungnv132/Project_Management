
package com.hungnv132.web.controller.project;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hungnv132.core.dao.ProjectDAO;
import com.hungnv132.core.domain.Project;
import com.hungnv132.core.service.ProjectService;
import com.hungnv132.core.service.UserService;

@Controller
public class DisplayProjectListController {

	final Logger logger = LogManager.getLogger(DisplayProjectListController.class);

	@Inject
	private UserService userService;

	@Inject
	private ProjectService projectService;
	
	@Inject
	private ProjectDAO projectDAO;
	
	@Inject
	MessageSource messageSource;
	
	@RequestMapping(value = "/manager/projects", method = RequestMethod.GET)
	public String listProject() {
		
		return "project-list";
	}
	@RequestMapping(value = "/manager/get-all-projects", method = RequestMethod.POST)
	@ResponseBody
	public List<Project> listProjects() {
		 
		return projectDAO.findAllProjectName();
	}
}














