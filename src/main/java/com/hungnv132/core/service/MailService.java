package com.hungnv132.core.service;

import javax.inject.Inject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.hungnv132.web.controller.user.ForgetPasswordController;


@Service
public class MailService {

	final Logger logger = LogManager.getLogger(ForgetPasswordController.class);
	
	@Inject
	MailSender mailSender;
	
	public boolean sendMail(String from, String to, String subject, String msg) {
		try{
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setFrom(from);
			simpleMailMessage.setTo(to);
			simpleMailMessage.setSubject(subject);
			simpleMailMessage.setText(msg);
			mailSender.send(simpleMailMessage);	
			return true;
		}catch(Exception e){
			System.out.println(e);
			return false;
		}
	}
}
