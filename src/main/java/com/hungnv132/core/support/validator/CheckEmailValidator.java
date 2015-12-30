package com.hungnv132.core.support.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.hungnv132.core.service.UserService;
import com.hungnv132.core.support.validator.CheckEmail.Action;

public class CheckEmailValidator implements ConstraintValidator<CheckEmail, String> {

	Logger logger = LogManager.getLogger(CheckEmailValidator.class);

	private final String EXISTED_EMAIL_MESSAGE = "Email đã được sử dụng";
	private final String INCORRECT_EMAIL_MESSAGE = "Email không chính xác";	
	private final String INCORRECT_FORMAT_MESSAGE = "Email không đúng định dạng";
	@Inject
	UserService userService;

	private String pattern;

	private Action actionFor;
	private boolean isValid = true;

	@Override
	public void initialize(CheckEmail checkEmail) {
		this.pattern = checkEmail.pattern();
		this.actionFor = checkEmail.actionFor();
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
		logger.info("Check Email");

		boolean isValid = true;
		constraintValidatorContext.disableDefaultConstraintViolation();
		Pattern pattern = Pattern.compile(this.pattern);
		Matcher matcher = pattern.matcher(email);
		constraintValidatorContext.disableDefaultConstraintViolation();
		if (!matcher.matches()) {
			logger.info("Email khong dung dinh dang");
			constraintValidatorContext.buildConstraintViolationWithTemplate(this.INCORRECT_FORMAT_MESSAGE)
					.addConstraintViolation();
			isValid = false;
			logger.info(this.isValid);
		} else {
			logger.info("Email  ko cmn trong");
			switch (this.actionFor) {
			case CREATE:
				if (userService.checkExistedEmail(email)) {
					constraintValidatorContext.buildConstraintViolationWithTemplate(this.EXISTED_EMAIL_MESSAGE)
							.addConstraintViolation();
					isValid = false;
				}
				break;
//			case UPDATE:
//				 if (userService.findByEmailAndNotId(email, id)) {
//				 
//				 }
//				isValid = false;
//				break;
			case LOGIN:
				if (!userService.checkExistedEmail(email)) {
					logger.info("check email login");
					constraintValidatorContext.buildConstraintViolationWithTemplate(this.INCORRECT_EMAIL_MESSAGE)
							.addConstraintViolation();
					isValid = false;
				}
				break;
			default:
				break;
			}
		}
		logger.info("end " + this.isValid);
		return isValid;
	}

}
