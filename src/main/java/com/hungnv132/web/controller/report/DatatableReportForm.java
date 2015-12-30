package com.hungnv132.web.controller.report;

import com.hungnv132.core.support.DatatableForm;

public class DatatableReportForm extends DatatableForm {

	public enum DT_REPORT_STATUS {
		APPROVED, REJECTED, WAITING, ALL
	}

	private DT_REPORT_STATUS reportStatus;
	
	private int forWhoId;

	public DT_REPORT_STATUS getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(DT_REPORT_STATUS reportStatus) {
		this.reportStatus = reportStatus;
	}

	public int getForWhoId() {
		return forWhoId;
	}

	public void setForWhoId(int forWhoId) {
		this.forWhoId = forWhoId;
	}
	
	
}
