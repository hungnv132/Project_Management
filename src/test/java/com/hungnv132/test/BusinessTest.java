package com.hungnv132.test;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.junit.Test;

import com.hungnv132.core.dao.BusinessDAO;
import com.hungnv132.core.dao.UserDAO;
import com.hungnv132.core.service.UserService;

public class BusinessTest extends MainTest{

	@Inject
	SessionFactory sessionFactory;
	
	@Inject
	BusinessDAO businessDAO;
	
	@Inject
	UserDAO userDAO;
	
	@Inject
	UserService userService;
	
	@Test
	public void main(){
		findbyId(5);
	}
	
	public void findbyId(int id){
		printGson(businessDAO.findById(id));
	}
	

}
