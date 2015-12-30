package com.hungnv132.core.dao;

import java.util.List;

import com.hungnv132.core.domain.DataContainer;
import com.hungnv132.core.domain.Report;
import com.hungnv132.core.domain.Report.REPORT_STATUS;
import com.hungnv132.core.domain.User.ROLE;
import com.hungnv132.core.support.DatatableForm;
import com.hungnv132.web.controller.report.DatatableReportForm.DT_REPORT_STATUS;
import com.hungnv132.web.controller.report.DatatableReportFormForAdmin;
import com.hungnv132.web.controller.report.ReportListForManagerForm;
import com.hungnv132.web.controller.report.UpdateReportForm;

public interface ReportDAO extends CommonDAO<Report>{

	 List<Report> findByStatus(REPORT_STATUS status);
	 public List<ReportListForManagerForm>  findByPageWithPM(int start, int length, int pmId);
	 int countReportApproved(int userId); //count  reports  belong to UserID that approved
	 
	 int countReportThisMonthOfUser(int userId); //count  reports  belong to UserID in this month
	 int countReportApprovedThisMonthOfUser(int userId); //count  approved reports  belong to UserID in this month
	
	 public void updateReportWithTask(UpdateReportForm form);
	 public int countReportForPMApprove(int pmId);
	 
	 public List<Report> findByPageWithUser_AtFieldOrder(int start, int length, int userId, ROLE forGetting, String order, String field);
	 public List<Report> test(ROLE forWho,  int start, int length, int userId, DT_REPORT_STATUS S);
	 
	 public DataContainer<ReportListForManagerForm> findByPageForMANAGER(DatatableForm form);
	 
	 public DataContainer<ReportListForManagerForm> findByPageForAdmin(DatatableForm form);
}
