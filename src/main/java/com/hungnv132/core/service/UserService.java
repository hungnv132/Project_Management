package com.hungnv132.core.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hungnv132.core.dao.PositionDAO;
import com.hungnv132.core.dao.UserDAO;
import com.hungnv132.core.domain.DataContainer;
import com.hungnv132.core.domain.Position;
import com.hungnv132.core.domain.User;
import com.hungnv132.core.domain.User.ROLE;
import com.hungnv132.core.support.AppUtils;
import com.hungnv132.core.support.Pagination;
import com.hungnv132.web.controller.admin.AdminUpdateUserForm;
import com.hungnv132.web.controller.project.AvailableMemberForm;
import com.hungnv132.web.controller.project.DatatableAvailableMemberForm;
import com.hungnv132.web.controller.user.DatatableUserForm;
import com.hungnv132.web.controller.user.UpdateUserForm;

@Service
@Transactional
public class UserService {

	final Logger logger = LogManager.getLogger(UserService.class);
	
	@Inject
	UserDAO userDAO;
	
	@Inject
	PositionDAO positionDAO;
	
	public Pagination<User> getUserByPage(DatatableUserForm form){
		List<User>  list =  userDAO.findByPage(form);
		Pagination<User> page = new Pagination<User>();
		page.setDraw(form.getDraw());
		page.setData(list);
		page.setRecordsTotal(userDAO.count());
		page.setRecordsFiltered(page.getRecordsTotal());
		return page;
	}
	
	
	
	public List<User> getAllUser(){
		return userDAO.findAll();
	}
	
	public List<User> getAllUserByRole(ROLE role){
		return userDAO.findAllByRole(role);
	}
	
	public List<String> getAllRole(){
		List<String> list = new ArrayList<String>();
		for (ROLE role : ROLE.values()) {
			list.add(role.name());
		}
		return list;
	}
	
	public void updateUserInfo(UpdateUserForm form){
		User user = userDAO.findById(form.getId());
		if (user != null) {
			logger.info("updating user id  "+ form.getId());
			user.setFirstName(form.getFirstName());
			user.setMidName(form.getMidName());
			user.setLastName(form.getLastName());
			user.setGender(form.getGender());
			user.setDob(form.getDob());
			user.setEmail(form.getEmail());
			user.setAddress(form.getAddress());
			user.setEnrollmentDate(form.getEnrollmentDate());
			user.setUpdateAt(new LocalDateTime());
			userDAO.update(user);
		}
	}

	public void adminUpdateUserInfo(AdminUpdateUserForm form){
		User user = userDAO.findById(form.getId());
		if (user != null) {
			logger.info("updating user id  "+ form.getId());
			user.setFirstName(form.getFirstName());
			user.setMidName(form.getMidName());
			user.setLastName(form.getLastName());
			user.setGender(form.getGender());
			user.setDob(form.getDob());
			user.setEmail(form.getEmail());
			user.setAddress(form.getAddress());
			user.setEnrollmentDate(form.getEnrollmentDate());
			Position position = positionDAO.findById(form.getPositionId());
			user.setPosition(position);
			user.setRole(form.getRole());
			user.setUpdateAt(new LocalDateTime());
			userDAO.update(user);
		}
	}
	public User findById(int id){
		return userDAO.findById(id);
	}
	public User findByEmail(String email){
		return userDAO.findByEmail(email);
	}
	public Pagination<User> getPage(int draw, int start, int length){
		return new Pagination<User>(draw, start, length, userDAO);
	}


	

	public Pagination<AvailableMemberForm> getAvailableMembers(DatatableAvailableMemberForm form){
		DataContainer<AvailableMemberForm>   container =  userDAO.findAvailableMember(form);
		Pagination<AvailableMemberForm> page = new Pagination<AvailableMemberForm>();
		page.setDraw(form.getDraw());
		page.setData(container.getData());
		page.setRecordsTotal(container.getTotal());
		page.setRecordsFiltered(page.getRecordsTotal());
		return page;
	}
	
	public void createNewStaff(User user){
		user.setDefaultData();
		userDAO.create(user);
		
	}
	public boolean checkExistedId(int id){
		User user = userDAO.findById(id);
		if (user == null) {
			return false;
		}
		return true;
	}
	public boolean checkExistedEmail(String email){
		User user = userDAO.findByEmail(email);
		if (user == null) {
			return false;
		}
		return true;
	}
	public boolean checkLogin(String email, String password){
		String encodedPassword = AppUtils.encodePassword(password);
		User user = userDAO.findByEmailAndPassword(email, encodedPassword);		
		if (user == null) {
			return false;
		}
		return true;
	}
	
	
	public void update(User user){
		 userDAO.update(user);		
	}
	
	public User findByEmailAndPassword(String email, String password){
		String encodedPassword = AppUtils.encodePassword(password);
		return userDAO.findByEmailAndPassword(email, encodedPassword);		
	}
	
	public User findByEmailAndNotId(String email, int id){
		return userDAO.findByEmailButNotSelf(email, id);
	}
	
	public List<User> findByRole(ROLE role){
		return userDAO.findByRole(role);
	}
	
	
	

	
}
