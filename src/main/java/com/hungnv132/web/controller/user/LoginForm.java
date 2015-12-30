package com.hungnv132.web.controller.user;


import org.hibernate.validator.constraints.NotEmpty;

import com.hungnv132.core.support.validator.CheckEmail;
import com.hungnv132.core.support.validator.CheckEmail.Action;;

public class LoginForm {
	
//	@Email(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
	@CheckEmail(actionFor=Action.LOGIN)
	private String email;
		
	@NotEmpty
	private String password;

	public LoginForm(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public LoginForm(){}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
