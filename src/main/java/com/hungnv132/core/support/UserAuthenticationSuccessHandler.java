package com.hungnv132.core.support;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse,
			Authentication authentication) throws IOException, ServletException {
		httpservletresponse.getWriter().write("Thanh cong");
		
	}

}
