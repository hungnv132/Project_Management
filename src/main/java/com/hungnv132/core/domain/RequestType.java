package com.hungnv132.core.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.google.gson.annotations.Expose;

@Entity
@Table(name = "request_types")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class RequestType extends CommonObject {
	
	@Column(name="type")
	private String type;
	
//	@JsonManagedReference
//	@JsonBackReference
	
	@Expose(serialize= false)
	@OneToMany(mappedBy="requestType", fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<Request> requests;
	
	public RequestType(){}
	
	public RequestType(int id) {
		super(id);
	}
	public RequestType(String type, Set<Request> requests) {
		this.type = type;
		this.requests = requests;
	}
//	@JsonIgnore
	public Set<Request> getRequests() {
		return requests;
	}

	public void setRequests(Set<Request> requests) {
		this.requests = requests;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
