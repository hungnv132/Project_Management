package com.hungnv132.core.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hungnv132.core.dao.ReportDAO;
import com.hungnv132.core.dao.UserDAO;
import com.hungnv132.core.domain.DataContainer;
import com.hungnv132.core.domain.Report;
import com.hungnv132.core.domain.User;
import com.hungnv132.core.domain.User.ROLE;
import com.hungnv132.core.support.AppUtils;
import com.hungnv132.core.support.DatatableForm;
import com.hungnv132.core.support.JoinedUser;
import com.hungnv132.core.support.Pagination;
import com.hungnv132.web.controller.report.ChangeReportStatusForm;
import com.hungnv132.web.controller.report.DatatableReportForm;
import com.hungnv132.web.controller.report.DatatableReportFormForAdmin;
import com.hungnv132.web.controller.report.ReportListForManagerForm;

@Service
@Transactional
public class ReportService  {

	final Logger logger = LogManager.getLogger(ReportService.class);
	@Inject
	ReportDAO reportDAO;
	
	@Inject
	UserDAO userDAO;

	public List<Report> getAllReport() {
		return reportDAO.findAll();
	}

	public Report findById(int id) {
		return reportDAO.findById(id);
	}
	
	public Report findByIdWithUser(int id, JoinedUser user) {
		return reportDAO.findByIdWithUser(id, user.getId());
	}

	public Pagination<Report> getPage(int draw, int start, int length) {
		return new Pagination<Report>(draw, start, length, reportDAO);
	}
	
	public Pagination<Report> getReportPageForSTAFF(DatatableForm form) {
		return new Pagination<Report>(ROLE.STAFF,form, reportDAO);
	}
	public Pagination<Report> getPageWithStaff_Asc_AtField(int draw, int start, int length, int staffId, String order, String field) {
		List<Report>  list =  reportDAO.findByPageWithUser_AtFieldOrder(start, length, staffId, ROLE.STAFF, order, field);
		Pagination<Report> page = new Pagination<Report>();
		page.setDraw(draw);
		page.setData(list);
		page.setRecordsTotal(reportDAO.countReportApproved(staffId));
		page.setRecordsFiltered(page.getRecordsTotal());
		return page;
	}
	public Pagination<ReportListForManagerForm> getPageWithPM(int draw, int start, int length, int pmId) {
		List<ReportListForManagerForm>  list =  reportDAO.findByPageWithPM(start, length, pmId);
		Pagination<ReportListForManagerForm> page = new Pagination<ReportListForManagerForm>();
		page.setDraw(draw);
		page.setData(list);
		page.setRecordsTotal(reportDAO.countReportForPMApprove(pmId));
		page.setRecordsFiltered(page.getRecordsTotal());
		return page;
	}
	
	public Pagination<ReportListForManagerForm> getReportPageForMANAGER(DatatableReportForm form) {
		DataContainer<ReportListForManagerForm>  dataContainer =  reportDAO.findByPageForMANAGER(form);
		Pagination<ReportListForManagerForm> page = new Pagination<ReportListForManagerForm>();
		page.setDraw(form.getDraw());
		page.setData(dataContainer.getData());
		page.setRecordsTotal(dataContainer.getTotal());
		page.setRecordsFiltered(page.getRecordsTotal());
		return page;
	}
	public Pagination<ReportListForManagerForm> getReportPageForADMIN(DatatableReportFormForAdmin form) {
		DataContainer<ReportListForManagerForm>  dataContainer =  reportDAO.findByPageForAdmin(form);
		Pagination<ReportListForManagerForm> page = new Pagination<ReportListForManagerForm>();
		page.setDraw(form.getDraw());
		page.setData(dataContainer.getData());
		page.setRecordsTotal(dataContainer.getTotal());
		page.setRecordsFiltered(page.getRecordsTotal());
		return page;
	}
	public void createNewReport(Report report) {
		report.setDefaultData();
		reportDAO.create(report);

	}

	public void updateReport(Report report) {
		reportDAO.update(report);
	}
	
	public int countReportThisMonthOfUser(int userId) {
		return reportDAO.countReportThisMonthOfUser(userId);
	}
	
	public int countReportApprovedThisMonthOfUser(int userId) {
		return reportDAO.countReportApprovedThisMonthOfUser(userId);
	}

	public boolean deleteReport(int id){
		return reportDAO.delete(id);
	}
	
	
	public boolean changeReportStaus(ChangeReportStatusForm form){
		Report report = this.findById(form.getId());
		User  manager = userDAO.findById(AppUtils.getJoinedUser().getId());
		report.setStatus(form.getStatus());
		report.setComment(form.getComment());
		report.setConfirmBy(manager);
		return reportDAO.update(report);
	}
}
