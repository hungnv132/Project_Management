package com.hungnv132.web.controller.request;

import java.util.List;

import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hungnv132.core.domain.Request.REQUEST_STATUS;
import com.hungnv132.core.support.Json_JodaDate_Serializer;
import com.hungnv132.web.controller.request.DatatableRequestForm.DT_REQUEST_TYPE;

public class DetailRequestForm {

	private Integer id;

	@JsonSerialize(using= Json_JodaDate_Serializer.class)
	private LocalDateTime createAt;

	@JsonSerialize(using= Json_JodaDate_Serializer.class)
	private LocalDateTime updateAt;

	private String pmApprove;

	private String userRequest;

	private REQUEST_STATUS status;

	private String comment;

	private String reason;

	private String time;

	private DT_REQUEST_TYPE requestType;


	private LocalDateTime date;
	
	private LocalTime overtimeStart;

	private LocalTime overtimeEnd;
	
	private LocalTime lateworkingStart; 
	
	private List<String> daysoff;
	
	public DetailRequestForm() {

	}

	public DT_REQUEST_TYPE getRequestType() {
		return requestType;
	}

	public void setRequestType(DT_REQUEST_TYPE requestType) {
		this.requestType = requestType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}

	public String getPmApprove() {
		return pmApprove;
	}

	public void setPmApprove(String pmApprove) {
		this.pmApprove = pmApprove;
	}

	public REQUEST_STATUS getStatus() {
		return status;
	}

	public void setStatus(REQUEST_STATUS status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUserRequest() {
		return userRequest;
	}

	public void setUserRequest(String userRequest) {
		this.userRequest = userRequest;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}


	public List<String> getDaysoff() {
		return daysoff;
	}

	public void setDaysoff(List<String> daysoff) {
		this.daysoff = daysoff;
	}

	public LocalTime getOvertimeStart() {
		return overtimeStart;
	}

	public void setOvertimeStart(LocalTime overtimeStart) {
		this.overtimeStart = overtimeStart;
	}

	public LocalTime getOvertimeEnd() {
		return overtimeEnd;
	}

	public void setOvertimeEnd(LocalTime overtimeEnd) {
		this.overtimeEnd = overtimeEnd;
	}

	public LocalTime getLateworkingStart() {
		return lateworkingStart;
	}

	public void setLateworkingStart(LocalTime lateworkingStart) {
		this.lateworkingStart = lateworkingStart;
	}

	
}
