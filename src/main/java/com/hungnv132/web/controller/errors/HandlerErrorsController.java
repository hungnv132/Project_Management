package com.hungnv132.web.controller.errors;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class HandlerErrorsController {

	final Logger logger = LogManager.getLogger(HandlerErrorsController.class);
	
	@ExceptionHandler(value= Throwable.class)
	@ResponseStatus( value =HttpStatus.NOT_FOUND)
	public String handle404(){
		return "errors/404";
	}
	
	@ExceptionHandler(value= Throwable.class)
	@ResponseStatus( value =HttpStatus.HTTP_VERSION_NOT_SUPPORTED)
	public String handle505(){
		return "errors/404";
	}
	
}
