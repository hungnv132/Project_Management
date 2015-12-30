package com.hungnv132.web.controller.request;

import com.hungnv132.core.support.DatatableForm;

public class DatatableRequestForm extends DatatableForm {

	public enum DT_REQUEST_STATUS {
		APPROVED, REJECTED, WAITING, ALL
	}

	public enum DT_REQUEST_TYPE {
		ABSENCE, LATE_WORKING, OVERTIME, ALL
	}

	
	private DT_REQUEST_TYPE requestType;
	
	private DT_REQUEST_STATUS requestStatus;
	
	private int forWhoId;

	public DT_REQUEST_STATUS getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(DT_REQUEST_STATUS requestStatus) {
		this.requestStatus = requestStatus;
	}

	public int getForWhoId() {
		return forWhoId;
	}

	public void setForWhoId(int forWhoId) {
		this.forWhoId = forWhoId;
	}

	public DT_REQUEST_TYPE getRequestType() {
		return requestType;
	}

	public void setRequestType(DT_REQUEST_TYPE requestType) {
		this.requestType = requestType;
	}
	
	
	
}
