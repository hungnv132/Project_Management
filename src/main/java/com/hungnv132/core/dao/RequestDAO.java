package com.hungnv132.core.dao;

import java.util.List;

import com.hungnv132.core.domain.DataContainer;
import com.hungnv132.core.domain.Request;
import com.hungnv132.core.domain.Request.REQUEST_STATUS;
import com.hungnv132.core.domain.RequestType;
import com.hungnv132.core.domain.User.ROLE;
import com.hungnv132.core.support.DatatableForm;
import com.hungnv132.web.controller.request.Detail_Request_For_PM_Form;

public interface RequestDAO extends CommonDAO<Request>{

	 List<Request> findByStatus(REQUEST_STATUS status);
	 public List<Detail_Request_For_PM_Form> findByPageWithPM(int start, int length, int pmId);
	 int countRequestApproved(int userId); //count  reports  belong to UserID that approved
	 
	 int countRequestThisMonthOfUser(int userId); //count  reports  belong to UserID in this month
	 int countRequestApprovedThisMonthOfUser(int userId); //count  approved reports  belong to UserID in this month
	
//	 public void updateRequestWithTask(UpdateRequestForm form);
	 public int countRequestForPMApprove(int pmId);
	
	 RequestType findRequestTypeById(int requestTypeId);
	 
	 List<RequestType> findAllRequestType();
	 
	 public DataContainer<Request> findRequestByPageFor(ROLE forWho, DatatableForm form);
	 
	public DataContainer<Request> findRequestByPageForAdmin( DatatableForm form);
}
