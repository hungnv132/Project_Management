package com.hungnv132.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.joda.time.Days;
import org.joda.time.LocalDateTime;
import org.junit.Test;

import com.hungnv132.core.dao.BusinessDAO;
import com.hungnv132.core.dao.ProjectDAO;
import com.hungnv132.core.dao.UserDAO;
import com.hungnv132.core.domain.Business;
import com.hungnv132.core.domain.Project;
import com.hungnv132.core.domain.Project.PROJECT_STATUS;
import com.hungnv132.core.domain.User;
import com.hungnv132.core.service.UserService;
import com.hungnv132.web.controller.project.DatatableAvailableMemberForm;

public class ProjectTest extends MainTest{

	@Inject
	SessionFactory sessionFactory;
	
	@Inject
	ProjectDAO projectDAO;

	@Inject
	BusinessDAO businessDAO;	
	
	@Inject
	UserDAO userDAO;
	
	@Inject
	UserService userService;
	
	@Test
	public void main(){
		findbyId(8);
//		deleteBusiness(16);
		printGson(findAllProjectNameByMemberId(4));
	}
	public List<Project> findAllProjectNameByMemberId(int memberId) {
		return projectDAO.findAllProjectNameByMemberId(memberId);
	}
	public void findbyId(int id){
		printGson(projectDAO.findById(id));
	}
	
	public void deleteBusiness(int id){
		print(businessDAO.delete(id));
	}

	public void findAllProjectName(){
		printGson(projectDAO.findAllProjectName());
	}

	public void createNewProject() {
//------------------------------------------------------------------- Member  setting
		User member1 = userDAO.findById(2);
		User member2 = userDAO.findById(3);
		List<User> memberList = new ArrayList<User>();
		memberList.add(member1);
		memberList.add(member2);
		
//------------------------------------------------------------------ Business setting
		
		Business b1 = new Business();
		b1.setDefaultData();
		b1.setName("Phan tich thiet ke");
		b1.setDescription("thi phan tich thiet ke");
		b1.setMembers(memberList);
		
		Business b2 = new Business();
		b2.setDefaultData();
		b2.setName("Code thoi");
		b2.setDescription("Code thi code");
		b2.setMembers(memberList);
		
		List<Business> businessList = new ArrayList<Business>();
		businessList.add(b1);
		businessList.add(b2);
//------------------------------------------------------------------ Project setting
		Project project = new Project();
		project.setDefaultData();
		project.setStartDate(new LocalDateTime());
		project.setEndDate(new LocalDateTime());
		project.setDescription("du an cuc lon");
		project.setName("Thuowng mai dien tu");
		project.setNumOfMem(20);
		project.setBudget(BigDecimal.valueOf(1000000));
		project.setStatus(PROJECT_STATUS.RUNNING);
		project.setTechnology("PHP, HTML,");
		
//-----------------------------------------------------
		b1.setProject(project);
		b2.setProject(project);
		project.setBusiness(businessList);
		
		projectDAO.create(project);
		
	}
	public void findAvailableMembers(){
		
		DatatableAvailableMemberForm form = new DatatableAvailableMemberForm();
		form.setPosition(-1); // -1: get with any positions
		setDefaultDatatableForm(form);
		
		printGson(userService.getAvailableMembers(form));
		
	}
	public void calPercentDay(){
		Project project = projectDAO .findById(4);
		float totalDaysPlan = Days.daysBetween(project.getStartDate(), project.getEndDate()).getDays();
		float totalDaysCurrent = Days.daysBetween(project.getStartDate(), new LocalDateTime()).getDays();
		float percentDay = totalDaysCurrent/ totalDaysPlan*100;
		print(percentDay);
	}
	
	

}
