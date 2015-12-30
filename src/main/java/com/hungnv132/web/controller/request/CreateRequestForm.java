package com.hungnv132.web.controller.request;

import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hungnv132.core.domain.Request;
import com.hungnv132.core.support.Json_JodaDate_Serializer;

public class CreateRequestForm {

	public enum REQUEST_TYPE{ABSENCE, LATE_WORKING, OVERTIME } 
	
	@NotEmpty
	private String pickedDate;
	
	private LocalTime overtimeStart;

	private LocalTime overtimeEnd;
	
	private LocalTime lateworkingStart; 
	
	@NotNull
	private Integer sendTo;

	@NotNull
	private REQUEST_TYPE requestType;

	@NotEmpty
	private String reason;

	
	public CreateRequestForm(){}


	public CreateRequestForm(String pickedDate, LocalTime overtimeStart, LocalTime overtimeEnd,
			LocalTime lateworkingStart, Integer sendTo, REQUEST_TYPE requestType, String reason) {
		this.pickedDate = pickedDate;
		this.overtimeStart = overtimeStart;
		this.overtimeEnd = overtimeEnd;
		this.lateworkingStart = lateworkingStart;
		this.sendTo = sendTo;
		this.requestType = requestType;
		this.reason = reason;
	}

	public Integer getSendTo() {
		return sendTo;
	}

	public void setSendTo(Integer sendTo) {
		this.sendTo = sendTo;
	}

	public REQUEST_TYPE getRequestType() {
		return requestType;
	}

	public void setRequestType(REQUEST_TYPE requestType) {
		this.requestType = requestType;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}


	public String getPickedDate() {
		return pickedDate;
	}


	public void setPickedDate(String pickedDate) {
		this.pickedDate = pickedDate;
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
