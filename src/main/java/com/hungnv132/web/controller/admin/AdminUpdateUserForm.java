package com.hungnv132.web.controller.admin;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDateTime;

import com.hungnv132.core.domain.User.ROLE;

public class AdminUpdateUserForm {

	@NotNull
	private Integer id;
	
	@NotNull
	private LocalDateTime dob;

	@NotEmpty
	private String firstName;

	private String midName;

	@NotEmpty
	private String lastName;

	@NotEmpty
	private String gender;

	@NotEmpty
	private String address;

	@Email(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})")
	private String email;

	@NotNull
	private LocalDateTime enrollmentDate;

	@NotNull
	private Integer positionId;
	
	@NotNull
	private ROLE role;

	
	public AdminUpdateUserForm(){}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDob() {
		return dob;
	}

	public void setDob(LocalDateTime dob) {
		this.dob = dob;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getEnrollmentDate() {
		return enrollmentDate;
	}

	public void setEnrollmentDate(LocalDateTime enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}

	public Integer getPositionId() {
		return positionId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	public ROLE getRole() {
		return role;
	}

	public void setRole(ROLE role) {
		this.role = role;
	}
	
	
}
