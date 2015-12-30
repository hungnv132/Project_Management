package com.hungnv132.web.controller.project;

public class AvailableMemberForm {

	private int id;
	private String firstName;
	private String midName;
	private String lastName;
	private String position;
	
	public AvailableMemberForm(int id, String firstName, String midName, String lastName, String position) {
		this.id = id;
		this.firstName = firstName;
		this.midName = midName;
		this.lastName = lastName;
		this.position = position;
	}
	public AvailableMemberForm(){}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMidName() {
		return midName;
	}
	public void setMidName(String midName) {
		this.midName = midName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	
}
