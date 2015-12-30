package com.hungnv132.web.controller.admin;

import javax.management.relation.Role;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hungnv132.core.domain.Position;
import com.hungnv132.core.domain.User;
import com.hungnv132.core.domain.User.ROLE;
import com.hungnv132.core.support.Json_JodaDate_Serializer;
import com.hungnv132.core.support.validator.CheckEmail;
import com.hungnv132.core.support.validator.CheckEmail.Action;

public class CreateUserForm {

	// @NotNull(message="không được trống")
	// @StringTimeFormat(pattern="dd-mm-yyyy")
//	@CheckLocalDateTime(pattern = "dd-MM-yyyy")
//	@LocalDateTimeTimeFormat(pattern = "dd-MM-yyyy")
	@NotNull
	@JsonSerialize(using= Json_JodaDate_Serializer.class)
	private LocalDateTime dob;

	@NotEmpty(message="'Họ' không được trống ")
	private String firstName;

	private String midName;

	@NotEmpty(message="'Tên' không được trống ")
	private String lastName;

	@NotEmpty(message="'Giới tính' không được trống ")
	private String gender;

	@NotEmpty(message="'Địa chỉ' không được trống ")
	private String address;

	// @Email(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
	// message=" không đúng định dạng")
	@CheckEmail(actionFor=Action.CREATE)
	private String email;

	@NotNull
	@JsonSerialize(using= Json_JodaDate_Serializer.class)
	private LocalDateTime enrollmentDate;

	@NotNull(message="'Vị trí' không được trống ")
	private Integer positionId;
	
	@NotNull
	private ROLE role;

	public CreateUserForm(LocalDateTime dob, String firstName, String midName, String lastName, String address, String gender,
			String email, LocalDateTime enrollmentDate, Integer positionId) {
		this.dob = dob;
		this.firstName = firstName;
		this.midName = midName;
		this.lastName = lastName;
		this.gender = gender;
		this.address = address;
		this.email = email;
		this.enrollmentDate = enrollmentDate;
		this.positionId = positionId;
	}

	public CreateUserForm() {
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public User buildUser() {

		return  new User(this.dob, this.firstName, this.midName, this.lastName ,this.gender , this.address, this.email,
		this.enrollmentDate, new Position(this.getPositionId().intValue()), this.role);
	}
}
