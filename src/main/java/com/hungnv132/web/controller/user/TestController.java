package com.hungnv132.web.controller.user;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hungnv132.core.dao.TestDAO;
import com.hungnv132.core.support.ErrorEntity;
import com.hungnv132.core.support.ErrorField;
import com.hungnv132.core.support.Response.RES_STATUS;


public class TestController {

	final Logger logger = LogManager.getLogger(TestController.class);
	@Inject
	TestDAO testDAO;

/*	@RequestMapping(value = "test/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Test loginForm(@PathVariable int id) {
		Test test = testDAO.gettingForTest(id);
		return test;
	}
	
	@RequestMapping(value = "test/post", method = RequestMethod.POST)
	public void testPost(TestForm form, BindingResult errors) {
		logger.debug(form.getCreateAt());	
		
		
		if (errors.hasErrors()) {
			for (FieldError field : errors.getFieldErrors()) {
				logger.info("Code " + field.getCode());
				logger.info("Field " + field.getField());
				logger.info("Message " + field.getDefaultMessage());

				
			}
			
		}
		
		Test test = new Test();
		test.setLocalDateTime(form.getCreateAt());
		testDAO.creattingForTest(test);
	}*/
}
