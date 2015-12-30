package com.hungnv132.web.controller.request;

import java.util.List;

import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hungnv132.core.support.Json_JodaDate_Serializer;
import com.hungnv132.web.controller.request.DatatableRequestForm.DT_REQUEST_TYPE;

public class EditRequestForm {

	private Integer id;

	@JsonSerialize(using= Json_JodaDate_Serializer.class)
	private LocalDateTime createAt;

	@JsonSerialize(using= Json_JodaDate_Serializer.class)
	private LocalDateTime updateAt;

	private Integer sendTo;

	private String reason;

	private DT_REQUEST_TYPE requestType;


	private LocalDateTime date;
	
	private String overtimeStart;

	private String overtimeEnd;
	
	private String lateworkingStart; 
	
	private List<String> daysoff;
	
	public EditRequestForm() {

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

	public Integer getSendTo() {
		return sendTo;
	}

	public void setSendTo(Integer sendTo) {
		this.sendTo = sendTo;
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

	public String getOvertimeStart() {
		return overtimeStart;
	}

	public void setOvertimeStart(String overtimeStart) {
		this.overtimeStart = overtimeStart;
	}

	public String getOvertimeEnd() {
		return overtimeEnd;
	}

	public void setOvertimeEnd(String overtimeEnd) {
		this.overtimeEnd = overtimeEnd;
	}

	public String getLateworkingStart() {
		return lateworkingStart;
	}

	public void setLateworkingStart(String lateworkingStart) {
		this.lateworkingStart = lateworkingStart;
	}

	
}
