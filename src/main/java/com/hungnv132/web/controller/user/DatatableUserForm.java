package com.hungnv132.web.controller.user;


import com.hungnv132.core.support.DatatableForm;

public class DatatableUserForm extends DatatableForm {
	public enum DT_ROLE {
		ALL, ADMIN, MANAGER, STAFF
	};
	
	
	private DT_ROLE role;
	
	private int position;

	public DT_ROLE getRole() {
		return role;
	}

	public void setRole(DT_ROLE role) {
		this.role = role;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	
	
	
}
