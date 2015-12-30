
package com.hungnv132.web.controller.project;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hungnv132.core.dao.BusinessDAO;
import com.hungnv132.core.dao.ProjectDAO;
import com.hungnv132.core.domain.Project;
import com.hungnv132.core.service.BusinessService;
import com.hungnv132.core.service.ProjectService;
import com.hungnv132.core.service.UserService;

@Controller
public class DeleteBusinessController {

	final Logger logger = LogManager.getLogger(DeleteBusinessController.class);

	@Inject
	private UserService userService;

	@Inject
	private ProjectService projectService;
	
	@Inject
	private BusinessDAO businessDAO;
	
	@Inject
	private BusinessService businessService;
	
	
	@Inject
	MessageSource messageSource;
	
	
	@RequestMapping(value = "/manager/project/business/delete/{businessId}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteBusiness(@PathVariable int businessId) {
		logger.info("+++++++++delete");
		if(businessDAO.delete(businessId)){
			return "success";
		}
		return "failed";
	}
}














