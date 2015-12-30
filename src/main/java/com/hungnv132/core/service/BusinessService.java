package com.hungnv132.core.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hungnv132.core.dao.BusinessDAO;
import com.hungnv132.core.dao.ProjectDAO;
import com.hungnv132.core.dao.UserDAO;
import com.hungnv132.core.domain.Business;
import com.hungnv132.core.domain.Project;
import com.hungnv132.core.domain.User;
import com.hungnv132.web.controller.project.CreateBusinessForm;
import com.hungnv132.web.controller.project.UpdateBusinessForm;

@Service
@Transactional
public class BusinessService  {

	final Logger logger = LogManager.getLogger(BusinessService.class);
	@Inject
	BusinessDAO businessDAO;
	
	@Inject
	ProjectDAO projectDAO;
	
	@Inject
	UserDAO userDAO;
	
	public List<Business> getAllBusiness(){
		return businessDAO.findAll();
	}
	
	public Business findById(int businessId){
		return businessDAO.findById(businessId);
	}
	
	public void createNewBusiness(CreateBusinessForm form){
		
		Business business = new Business();
		List<User> listMember = new ArrayList<User>();
		for (Integer memberId : form.getMembers()) {
			listMember.add(userDAO.findById(memberId.intValue()));
		}
		Project project = projectDAO.findById(form.getProjectId());
		business.setDefaultData();
		business.setName(form.getBusinessName());
		business.setDescription(form.getBusinessDesc());
		business.setMembers(listMember);
		business.setProject(project);
		
		businessDAO.create(business);
	}
	
	public void updateBusiness(UpdateBusinessForm form){
		
		Business business = businessDAO.findById(form.getBusinessId());
		List<User> listMember = new ArrayList<User>();
		for (Integer memberId : form.getMembers()) {
			listMember.add(userDAO.findById(memberId.intValue()));
		}
		business.setDefaultData();
		business.setName(form.getBusinessName());
		business.setDescription(form.getBusinessDesc());
		business.setMembers(listMember);
		
		businessDAO.update(business);
	}
}
