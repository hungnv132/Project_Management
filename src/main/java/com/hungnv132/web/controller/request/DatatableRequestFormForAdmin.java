package com.hungnv132.web.controller.request;

import com.hungnv132.core.support.DatatableForm;
import com.hungnv132.web.controller.request.DatatableRequestForm.DT_REQUEST_STATUS;
import com.hungnv132.web.controller.request.DatatableRequestForm.DT_REQUEST_TYPE;

public class DatatableRequestFormForAdmin extends DatatableForm {


	
	private DT_REQUEST_TYPE requestType;
	
	private DT_REQUEST_STATUS requestStatus;
	
	private int staffId;

	public DT_REQUEST_STATUS getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(DT_REQUEST_STATUS requestStatus) {
		this.requestStatus = requestStatus;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public DT_REQUEST_TYPE getRequestType() {
		return requestType;
	}

	public void setRequestType(DT_REQUEST_TYPE requestType) {
		this.requestType = requestType;
	}
	
	
	
}
