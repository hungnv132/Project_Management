package com.hungnv132.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.google.gson.annotations.Expose;

@Entity
@Table(name = "requests")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Request extends CommonObject {

	public enum REQUEST_STATUS {
		WAITING, APPROVED, REJECTED
	};
	@Expose
	@Column(name = "reason")
	private String reason;
	
	@Expose
	@Column(name = "manager_comment",  nullable= true)
	private String comment;

	
	@Column(name="datetime_start", nullable= true)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime dateTimeStart;

	@Expose
	@Column(name="datetime_end", nullable= true)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime dateTimeEnd;
	
	@Expose
	@Column(name="daysoff", nullable= true)
	private String daysoff;
	
	@Expose
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private REQUEST_STATUS status;

//	@Expose(serialize= false)
//	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "member_request")
	private User memberRequest;

	@Expose(serialize= false)
//	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "send_to")
	private User sendTo;

	
//	@JsonBackReference
//	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "request_type_id")
	private RequestType requestType;

	public Request() {
	}
	public Request(int id) {
		super(id);
	}
	public Request(String reason, String comment,  REQUEST_STATUS status, User memberRequest,
			User sendTo, RequestType requestType) {
		this.reason = reason;
		this.comment = comment;
		this.status = status;
		this.memberRequest = memberRequest;
		this.sendTo = sendTo;
		this.requestType = requestType;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public RequestType getRequestType() {
		return requestType;
	}

	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}


	public REQUEST_STATUS getStatus() {
		return status;
	}

	public void setStatus(REQUEST_STATUS status) {
		this.status = status;
	}

	public User getMemberRequest() {
		return memberRequest;
	}

	public void setMemberRequest(User memberRequest) {
		this.memberRequest = memberRequest;
	}

	public User getSendTo() {
		return sendTo;
	}

	public void setSendTo(User sendTo) {
		this.sendTo = sendTo;
	}

	public LocalDateTime getDateTimeStart() {
		return dateTimeStart;
	}

	public void setDateTimeStart(LocalDateTime dateTimeStart) {
		this.dateTimeStart = dateTimeStart;
	}

	public LocalDateTime getDateTimeEnd() {
		return dateTimeEnd;
	}

	public void setDateTimeEnd(LocalDateTime dateTimeEnd) {
		this.dateTimeEnd = dateTimeEnd;
	}

	public String getDaysoff() {
		return daysoff;
	}

	public void setDaysoff(String daysoff) {
		this.daysoff = daysoff;
	}

	@Override
	public void setDefaultData() {
		this.setStatus(REQUEST_STATUS.WAITING);
		this.setCreateAt(new LocalDateTime());
		this.setUpdateAt(new LocalDateTime());
	}
	
}
