package com.hungnv132.test;



import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.junit.Test;

import com.hungnv132.core.service.MailService;
import com.hungnv132.core.support.AppUtils;


public class MailServiceTest extends MainTest{

	@Inject
	SessionFactory sessionFactory;

	@Inject
	MailService mailService;
	
	@Test
	public void run(){
		print(AppUtils.randomString(10));
	}
}
