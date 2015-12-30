package com.hungnv132.web.controller.user;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDateTime;
import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hungnv132.core.domain.User;
import com.hungnv132.core.support.Json_JodaDate_Serializer;

public class UpdateUserForm {

	private int id;
	//
	// @NotNull(message="không được trống")
	// @StringTimeFormat(pattern="dd-mm-yyyy")
	// @CheckLocalDateTime(pattern="dd-MM-yyyy")
//	@CheckLocalDateTime(pattern = "dd-MM-yyyy")
//	@LocalDateTimeTimeFormat(pattern = "dd-MM-yyyy")
//	@JsonSerialize(using= Json_JodaDate_Serializer.class)
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

	@Email(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = " không đúng định dạng")
	// @CheckEmail(checkExisted = true)
	private String email;

	// @NotNull(message="không được trống")
	// @StringTimeFormat(pattern="dd-mm-yyyy")
//	@CheckLocalDateTime(pattern = "dd-MM-yyyy")
	// @NotEmpty
//	@LocalDateTimeTimeFormat(pattern = "dd-MM-yyyy")
//	@JsonSerialize(using= Json_JodaDate_Serializer.class)
	private LocalDateTime enrollmentDate;

	/*@NotNull
	private Integer position;*/

	public UpdateUserForm(LocalDateTime dob, String firstName, String midName, String lastName, String gender, String address,
			String email, LocalDateTime enrollmentDate) {
		this.dob = dob;
		this.firstName = firstName;
		this.midName = midName;
		this.lastName = lastName;
		this.gender = gender;
		this.address = address;
		this.email = email;
		this.enrollmentDate = enrollmentDate;
//		this.position = position;
	}

	public UpdateUserForm() {
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

//	public Integer getPosition() {
//		return position;
//	}
//
//	public void setPosition(Integer position) {
//		this.position = position;
//	}

	// public User buildUser() {
	//
	// return new User(this.dob, this.firstName, this.midName, this.lastName,
	// this.gender, this.email,
	// this.enrollmentDate, this.position);
	// }

	public static UpdateUserForm copyFromUser(User user) {
		UpdateUserForm form = new UpdateUserForm();
		BeanUtils.copyProperties(user, form);
		return form;
	}
}
