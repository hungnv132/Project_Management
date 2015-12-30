package com.hungnv132.web.controller.report;

import com.hungnv132.core.support.DatatableForm;
import com.hungnv132.web.controller.report.DatatableReportForm.DT_REPORT_STATUS;

public class DatatableReportFormForAdmin extends DatatableForm {

	
	private DT_REPORT_STATUS reportStatus;
	
	private int staffId;

	public DT_REPORT_STATUS getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(DT_REPORT_STATUS reportStatus) {
		this.reportStatus = reportStatus;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	
	
}
