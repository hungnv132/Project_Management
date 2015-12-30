package com.hungnv132.core.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.gson.annotations.Expose;
import com.hungnv132.core.support.AppUtils;

@Entity
@Table(name = "users")
// @SamePassword
@JsonAutoDetect
public class User extends CommonObject {
	public enum ROLE {
		ADMIN, MANAGER, STAFF
	};
	
	@Transient
	@Expose(serialize= false)
	private final String DEFAULT_PASSWORD= "13245678";

	@Expose(serialize= false)
	@Column(name = "password")
	private String password;

	@Transient
	private String confirmPassword;

	@Column(name = "date_of_birth")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime dob;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "mid_name")
	private String midName;

	@Column(name = "last_name")
	private String lastName;

	@Transient
	private String fullName;

	@Column(name = "gender")
	private String gender;

	@Column(name = "email", unique = true)
	private String email;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "enrollment_date")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
//	@JsonSerialize(using= Json_JodaDate_Serializer.class)
	private LocalDateTime enrollmentDate;

	
	@Expose(serialize= false)
	@JsonManagedReference
	@OneToMany(mappedBy = "memberReport", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Report> reports;
	
	@Expose(serialize= false)
	@JsonManagedReference
	@OneToMany(mappedBy = "confirmBy", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Report> reportsForManager;

	@Expose(serialize= false)
	@JsonManagedReference
	@OneToMany(mappedBy = "memberRequest", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Request> requests;
	
	@Expose(serialize= false)
	@JsonManagedReference
	@OneToMany(mappedBy = "sendTo", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Request> requestsForManager;
	
	
	@Expose(serialize= false)
	@OneToMany(mappedBy = "staff", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Evaluation> evaluationForMember;
	
	@Expose(serialize= false)
	@OneToMany(mappedBy = "evaluatedBy", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Evaluation> evaluationForManager;
	
	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private ROLE role;
	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="position_id")
	private Position position;
	
	@Expose(serialize= false)
	@ManyToMany(fetch=FetchType.LAZY, cascade= CascadeType.ALL,mappedBy="members")
	private List<Business> business;
	
	
	public User(String password, LocalDateTime dob, String firstName, String midName, String lastName, String address,
			String gender, String email, LocalDateTime enrollmentDate) {

		this.password = password;
		this.dob = dob;
		this.firstName = firstName;
		this.midName = midName;
		this.lastName = lastName;
		this.gender = gender;
		this.address = address;
		this.email = email;
		this.enrollmentDate = enrollmentDate;
		
	}

	public User(LocalDateTime dob, String firstName, String midName, String lastName, String gender, String address,
			String email, LocalDateTime enrollmentDate, Position position, ROLE role) {
		
		this.dob = dob;
		this.firstName = firstName;
		this.midName = midName;
		this.lastName = lastName;
		this.address = address;
		this.gender = gender;
		this.email = email;
		this.enrollmentDate = enrollmentDate;
		this.position= position;
		this.role = role;
	}

	public User() {
	}
	
	public User(int id) {
		super(id);
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String comfirmPassword) {
		this.confirmPassword = comfirmPassword;
	}

	public ROLE getRole() {
		return role;
	}

	public void setRole(ROLE role) {
		this.role = role;
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

	public String getFullName() {
		return firstName + " " + midName + " " + lastName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public LocalDateTime getEnrollmentDate() {
		return enrollmentDate;
	}

	public void setEnrollmentDate(LocalDateTime enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	
	@JsonIgnore
	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}
	@JsonIgnore
	public List<Report> getReportsForManager() {
		return reportsForManager;
	}
	
	public void setReportsForManager(List<Report> reportsForManager) {
		this.reportsForManager = reportsForManager;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

	public List<Request> getRequestsForManager() {
		return requestsForManager;
	}

	public void setRequestsForManager(List<Request> requestsForManager) {
		this.requestsForManager = requestsForManager;
	}

	
	
	public List<Business> getBusiness() {
		return business;
	}

	public void setBusiness(List<Business> business) {
		this.business = business;
	}

	public Collection<GrantedAuthority> getCollectionAuthorities(){
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_"+ this.getRole()));
		return authorities;
	}
	
	@Override
	public void setDefaultData() {
		super.setDefaultData();
		this.setPassword(AppUtils.encodePassword(DEFAULT_PASSWORD.toString()));
	}
}
