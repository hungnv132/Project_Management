package com.hungnv132.test;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.junit.Test;

import com.hungnv132.core.dao.RequestDAO;
import com.hungnv132.core.domain.DataContainer;
import com.hungnv132.core.domain.Request;
import com.hungnv132.core.domain.RequestType;
import com.hungnv132.web.controller.request.DatatableRequestFormForAdmin;

public class RequestTest extends MainTest{

	@Inject
	SessionFactory sessionFactory;

	@Inject
	RequestDAO requestDAO;
	
	@Test
	public void run(){
		printGson(findByPageForADMIN(3));
	}
	public List<RequestType> findAllRequestType(){
		return requestDAO.findAllRequestType();
	}
	public DataContainer<Request> findByPageForADMIN(int staffId){
		DatatableRequestFormForAdmin form =  new DatatableRequestFormForAdmin();
		form.setStaffId(staffId);
		setDefaultDatatableForm(form);
		return requestDAO.findRequestByPageForAdmin(form);
	}

}
