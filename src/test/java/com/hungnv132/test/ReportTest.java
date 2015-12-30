package com.hungnv132.test;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.junit.Test;

import com.hungnv132.core.dao.ReportDAO;
import com.hungnv132.core.domain.DataContainer;
import com.hungnv132.core.domain.Report;
import com.hungnv132.core.domain.User.ROLE;
import com.hungnv132.core.support.DatatableForm;
import com.hungnv132.web.controller.report.DatatableReportForm;
import com.hungnv132.web.controller.report.DatatableReportFormForAdmin;
import com.hungnv132.web.controller.report.ReportListForManagerForm;

public class ReportTest extends MainTest{

	@Inject
	SessionFactory sessionFactory;

	@Inject
	ReportDAO reportDAO;
	
	@Test
	public void run(){
		printGson(findByPageForADMIN(4));
	}
	public List<Report> findByPageForSTAFF(int forWhoId){
		DatatableReportForm form =  new DatatableReportForm();
		form.setForWhoId(forWhoId);
		setDefaultDatatableForm(form);
		return reportDAO.findByPageFor(ROLE.STAFF, form);
	}
	public DataContainer<ReportListForManagerForm> findByPageForMANAGER(int forWhoId){
		DatatableReportForm form =  new DatatableReportForm();
		form.setForWhoId(forWhoId);
		setDefaultDatatableForm(form);
		return reportDAO.findByPageForMANAGER(form);
	}
	public DataContainer<ReportListForManagerForm> findByPageForADMIN(int staffId){
		DatatableReportFormForAdmin form =  new DatatableReportFormForAdmin();
		form.setStaffId(staffId);
		setDefaultDatatableForm(form);
		return reportDAO.findByPageForAdmin(form);
	}

}
