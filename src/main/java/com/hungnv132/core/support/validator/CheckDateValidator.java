package com.hungnv132.core.support.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class CheckDateValidator implements ConstraintValidator<CheckDate, Date> {

	Logger logger = LogManager.getLogger(CheckDateValidator.class);

	String pattern = "";
	
	@Override
	public void initialize(CheckDate checkDate) {
		this.pattern = checkDate.pattern();
	}

	@Override
	public boolean isValid(Date inputDate, ConstraintValidatorContext constraintValidatorContext) {
		logger.info("Check Date time");

		boolean isValid = true;
		int flag = 0;
		if( inputDate == null){
			 flag =1;
		}else{
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			try {
				formatter.format(inputDate);
			} catch (Exception e) {
				flag = 2;
			}
		}
			
		if (flag == 1) {
			constraintValidatorContext.disableDefaultConstraintViolation();			
			constraintValidatorContext.buildConstraintViolationWithTemplate(" không được trống").addConstraintViolation();
			isValid = false;
		}else if (flag == 2) {
			
			constraintValidatorContext.disableDefaultConstraintViolation();
			constraintValidatorContext.buildConstraintViolationWithTemplate(" không đúng  định dạng").addConstraintViolation();
			isValid = false;
		}
	
		return isValid;
	}

}
