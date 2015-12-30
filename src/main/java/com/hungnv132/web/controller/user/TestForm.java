package com.hungnv132.web.controller.user;

import java.util.Date;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.hungnv132.core.domain.User;
import com.hungnv132.core.support.validator.CheckDate;

public class TestForm {

	//
	// @NotNull(message="không được trống")
	// @StringTimeFormat(pattern="dd-mm-yyyy")
	// @CheckDate(pattern="dd-MM-yyyy")
//	@CheckDate(pattern = "dd-MM-yyyy")
//	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDateTime createAt;

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	
	
	
}
