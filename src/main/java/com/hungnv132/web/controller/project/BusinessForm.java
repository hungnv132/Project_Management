package com.hungnv132.web.controller.project;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class BusinessForm {
	
	@NotEmpty
	private String businessName;

	@NotEmpty
	private String businessDesc;
	
	@NotNull
	private Integer[] members;

	public BusinessForm(){}
	
	public BusinessForm(String businessName, String businessDesc, Integer[] members) {
		this.businessName = businessName;
		this.businessDesc = businessDesc;
		this.members = members;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getBusinessDesc() {
		return businessDesc;
	}

	public void setBusinessDesc(String businessDesc) {
		this.businessDesc = businessDesc;
	}

	public Integer[] getMembers() {
		return members;
	}

	public void setMembers(Integer[] members) {
		this.members = members;
	}

	
}
