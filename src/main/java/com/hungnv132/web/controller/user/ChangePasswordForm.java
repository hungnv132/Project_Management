package com.hungnv132.web.controller.user;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class ChangePasswordForm {

	@NotEmpty(message="không được trống")
	private String oldPassword;
	
	@NotEmpty(message="không được trống")
	private String newPassword;
	
	@NotEmpty(message="không được trống")
	private String reNewPassword;

	@AssertTrue(message="mật khẩu không trùng nhau")
	public boolean checkSamePassword(){
		if (this.newPassword.equals(this.reNewPassword)) {
			return true;
		}
		return false;
	}
	public ChangePasswordForm(String oldPassword, String newPassword, String reNewPassword) {
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.reNewPassword = reNewPassword;
	}
	
	public ChangePasswordForm(){}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getReNewPassword() {
		return reNewPassword;
	}

	public void setReNewPassword(String reNewPassword) {
		this.reNewPassword = reNewPassword;
	}
	
	
}
