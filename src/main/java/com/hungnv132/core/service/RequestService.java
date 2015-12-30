package com.hungnv132.core.service;

import java.text.Normalizer.Form;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hungnv132.core.dao.RequestDAO;
import com.hungnv132.core.dao.UserDAO;
import com.hungnv132.core.domain.DataContainer;
import com.hungnv132.core.domain.Request;
import com.hungnv132.core.domain.RequestType;
import com.hungnv132.core.domain.User;
import com.hungnv132.core.domain.User.ROLE;
import com.hungnv132.core.support.AppUtils;
import com.hungnv132.core.support.Pagination;
import com.hungnv132.web.controller.request.ChangeRequestStatusForm;
import com.hungnv132.web.controller.request.CreateRequestForm;
import com.hungnv132.web.controller.request.CreateRequestForm.REQUEST_TYPE;
import com.hungnv132.web.controller.request.DatatableRequestForm;
import com.hungnv132.web.controller.request.DatatableRequestFormForAdmin;
import com.hungnv132.web.controller.request.Detail_Request_For_PM_Form;
import com.hungnv132.web.controller.request.UpdateRequestForm;

@Service
@Transactional
public class RequestService  {

	final Logger logger = LogManager.getLogger(RequestService.class);
	@Inject
	RequestDAO requestDAO;

	@Inject
	UserDAO userDAO;

	
	public List<Request> getAllRequest() {
		return requestDAO.findAll();
	}

	public Request findById(int id) {
		return requestDAO.findById(id);
	}

	public Pagination<Request> getPage(int draw, int start, int length) {
		return new Pagination<Request>(draw, start, length, requestDAO);
	}
	public Pagination<Request> getRequestPageForSTAFF(DatatableRequestForm form) {
//		List<Request>  list =  requestDAO.findByPageFor(ROLE.STAFF, form);
		DataContainer<Request>  dataContainer =  requestDAO.findRequestByPageFor(ROLE.STAFF, form);
		Pagination<Request> page = new Pagination<Request>();
		page.setDraw(form.getDraw());
		page.setData(dataContainer.getData());
		page.setRecordsTotal(dataContainer.getTotal());
		page.setRecordsFiltered(page.getRecordsTotal());
		return page;
//		return new Pagination<Request>(ROLE.STAFF, form ,requestDAO);
	}
	
	public Pagination<Request> getRequestPageForMANAGER(DatatableRequestForm form) {
		DataContainer<Request>  dataContainer =  requestDAO.findRequestByPageFor(ROLE.MANAGER, form);
		Pagination<Request> page = new Pagination<Request>();
		page.setDraw(form.getDraw());
		page.setData(dataContainer.getData());
		page.setRecordsTotal(dataContainer.getTotal());
		page.setRecordsFiltered(page.getRecordsTotal());
		return page;
	}
	
	public Pagination<Request> getRequestPageForAdmin(DatatableRequestFormForAdmin form) {
		DataContainer<Request>  dataContainer =  requestDAO.findRequestByPageForAdmin(form);
		Pagination<Request> page = new Pagination<Request>();
		page.setDraw(form.getDraw());
		page.setData(dataContainer.getData());
		page.setRecordsTotal(dataContainer.getTotal());
		page.setRecordsFiltered(page.getRecordsTotal());
		return page;
	}
	
	public Pagination<Detail_Request_For_PM_Form> getPageWithPM(int draw, int start, int length, int pmId) {
		List<Detail_Request_For_PM_Form>  list =  requestDAO.findByPageWithPM(start, length, pmId);
		Pagination<Detail_Request_For_PM_Form> page = new Pagination<Detail_Request_For_PM_Form>();
		page.setDraw(draw);
		page.setData(list);
		page.setRecordsTotal(requestDAO.countRequestForPMApprove(pmId));
		page.setRecordsFiltered(page.getRecordsTotal());
		return page;
	}
	public void createNewRequest(CreateRequestForm requestForm) {
		User manager = new User(requestForm.getSendTo());
		User userRequest = new User(AppUtils.getJoinedUser().getId());
		RequestType requestType= new RequestType(requestForm.getRequestType().ordinal()+1);
		
		Request request = new Request();		
		request.setDefaultData();
		request.setReason(requestForm.getReason());
		request.setSendTo(manager);
		request.setMemberRequest(userRequest);
		request.setRequestType(requestType);
		if (requestForm.getRequestType() == REQUEST_TYPE.ABSENCE) {
			request.setDaysoff(requestForm.getPickedDate());
			requestDAO.create(request);
		}
		if (requestForm.getRequestType() == REQUEST_TYPE.LATE_WORKING) {
			String lateWorkingTime = requestForm.getLateworkingStart().toString("HH:mm");
			String lateWorkingDate = requestForm.getPickedDate();
			String dateTime = lateWorkingDate.trim()+" "+ lateWorkingTime.trim();
			DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm");
			LocalDateTime start = formatter.parseLocalDateTime(dateTime); 
			request.setDateTimeStart(start);
			requestDAO.create(request);
		}
		if (requestForm.getRequestType() == REQUEST_TYPE.OVERTIME) {
			
			String timeStart = requestForm.getOvertimeStart().toString("HH:mm");
			String timeEnd = requestForm.getOvertimeEnd().toString("HH:mm");
			String overtimeDate = requestForm.getPickedDate();
			String dateTimeStart = overtimeDate.trim()+" "+ timeStart.trim();
			String dateTimeEnd= overtimeDate.trim()+" "+ timeEnd.trim();
			DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm");
			LocalDateTime start = formatter.parseLocalDateTime(dateTimeStart);
			LocalDateTime end = formatter.parseLocalDateTime(dateTimeEnd);
			request.setDateTimeStart(start);
			request.setDateTimeEnd(end);
			requestDAO.create(request);
		}
	
	
	}
	
	public void updateRequest(UpdateRequestForm requestForm) {
		User manager = new User(requestForm.getSendTo());
		RequestType requestType= new RequestType(requestForm.getRequestType().ordinal()+1);
		
		Request request = requestDAO.findById(requestForm.getId());
		request.setUpdateAt(new LocalDateTime());
		request.setReason(requestForm.getReason());
		request.setSendTo(manager);
		request.setRequestType(requestType);
		if (requestForm.getRequestType() == REQUEST_TYPE.ABSENCE) {
			request.setDaysoff(requestForm.getPickedDate());
			requestDAO.update(request);
		}
		if (requestForm.getRequestType() == REQUEST_TYPE.LATE_WORKING) {
			String lateWorkingTime = requestForm.getLateworkingStart().toString("HH:mm");
			String lateWorkingDate = requestForm.getPickedDate();
			String dateTime = lateWorkingDate.trim()+" "+ lateWorkingTime.trim();
			DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm");
			LocalDateTime start = formatter.parseLocalDateTime(dateTime); 
			request.setDateTimeStart(start);
			requestDAO.update(request);
		}
		if (requestForm.getRequestType() == REQUEST_TYPE.OVERTIME) {
			
			String timeStart = requestForm.getOvertimeStart().toString("HH:mm");
			String timeEnd = requestForm.getOvertimeEnd().toString("HH:mm");
			String overtimeDate = requestForm.getPickedDate();
			String dateTimeStart = overtimeDate.trim()+" "+ timeStart.trim();
			String dateTimeEnd= overtimeDate.trim()+" "+ timeEnd.trim();
			DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm");
			LocalDateTime start = formatter.parseLocalDateTime(dateTimeStart);
			LocalDateTime end = formatter.parseLocalDateTime(dateTimeEnd);
			request.setDateTimeStart(start);
			request.setDateTimeEnd(end);
			requestDAO.update(request);
		}
	
	
	}

	public void updateRequest(Request request) {
		requestDAO.update(request);
	}
	
	public int countRequestThisMonthOfUser(int userId) {
		return requestDAO.countRequestThisMonthOfUser(userId);
	}
	
	public int countRequestApprovedThisMonthOfUser(int userId) {
		return requestDAO.countRequestApprovedThisMonthOfUser(userId);
	}

	public boolean deleteRequest(int id){
		return requestDAO.delete(id);
	}
	
	public boolean changeRequestStaus(ChangeRequestStatusForm form){
		Request request = this.findById(form.getId());
		request.setStatus(form.getStatus());
		request.setComment(form.getComment());
		return requestDAO.update(request);
	}
	
	public RequestType findRequestTypeById(int requestTypeId){
		return requestDAO.findRequestTypeById(requestTypeId);
	}
	
}
