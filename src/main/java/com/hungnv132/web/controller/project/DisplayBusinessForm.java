package com.hungnv132.web.controller.project;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class DisplayBusinessForm {
	
	@NotEmpty
	private String businessName;

	@NotEmpty
	private String businessDesc;
	

	public DisplayBusinessForm(){}
	
	public DisplayBusinessForm(String businessName, String businessDesc) {
		this.businessName = businessName;
		this.businessDesc = businessDesc;
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

}
