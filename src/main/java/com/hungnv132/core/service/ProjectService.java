package com.hungnv132.core.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.joda.time.LocalDateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hungnv132.core.dao.ProjectDAO;
import com.hungnv132.core.dao.UserDAO;
import com.hungnv132.core.domain.Business;
import com.hungnv132.core.domain.Project;
import com.hungnv132.core.domain.User;
import com.hungnv132.web.controller.project.BusinessForm;
import com.hungnv132.web.controller.project.CreateProjectForm;
import com.hungnv132.web.controller.project.UpdateProjectForm;

@Service
@Transactional
public class ProjectService  {

	final Logger logger = LogManager.getLogger(ProjectService.class);
	@Inject
	ProjectDAO projectDAO;
	
	@Inject
	UserDAO userDAO;
	
	public List<Project> getAllProject(){
		return projectDAO.findAll();
	}
	
	public Project findById(int projectId){
		return projectDAO.findById(projectId);
	}
	
	public void createNewProject(CreateProjectForm form){
		
		Project project = new Project();
		project.setDefaultData();
		BeanUtils.copyProperties(form, project);
		project.setName(form.getProjectName());
		project.setDescription(form.getProjectDesc());
		
		List<Business> listBusiness = new ArrayList<Business>();
		for (BusinessForm businessForm : form.getBusiness()) {
			Business b = new Business();
			b.setDefaultData();
			b.setName(businessForm.getBusinessName());
			b.setDescription(businessForm.getBusinessDesc());
			b.setProject(project);
			List<User> listMember = new ArrayList<User>();
			BeanUtils.copyProperties(businessForm, b);
			for (Integer memberId : businessForm.getMembers()) {
				listMember.add(userDAO.findById(memberId.intValue()));
			}
			b.setMembers(listMember);
			listBusiness.add(b);
		}
		project.setBusiness(listBusiness);
		
		projectDAO.create(project);
	}
	public void updateProject(UpdateProjectForm form){
		Project project = projectDAO.findById(form.getId());
		BeanUtils.copyProperties(form, project);
		project.setUpdateAt(new LocalDateTime());
		projectDAO.update(project);
	}
}
