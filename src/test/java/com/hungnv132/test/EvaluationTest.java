package com.hungnv132.test;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.junit.Test;

import com.hungnv132.core.dao.EvaluationDAO;
import com.hungnv132.core.domain.RequestType;
import com.hungnv132.web.controller.evaluation.DatatableEvaluationFormForAdmin;
import com.hungnv132.web.controller.request.DatatableRequestFormForAdmin;

public class EvaluationTest extends MainTest{

	@Inject
	SessionFactory sessionFactory;

	@Inject
	EvaluationDAO evaluationDAO;
	
	@Test
	public void run(){
		findAllByUserId(4);
	}
	public void findAllByUserId(int userId){
		DatatableEvaluationFormForAdmin form =  new DatatableEvaluationFormForAdmin();
		form.setStaffId(userId);
		setDefaultDatatableForm(form);
		printGson(evaluationDAO.findAllByUserId(form));
	}

}
