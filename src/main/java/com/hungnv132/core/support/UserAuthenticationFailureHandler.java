package com.hungnv132.core.support;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class UserAuthenticationFailureHandler implements AuthenticationFailureHandler {

	final Logger logger = LogManager.getLogger(UserAuthenticationFailureHandler.class);
//	public UserAuthenticationFailureHandler(String url) {
//		this.setUrl(url);
//	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse,
			AuthenticationException authenticationexception) throws IOException, ServletException {
		logger.debug("  -------   -------login failed -------");
		httpservletrequest.setAttribute("email", httpservletrequest.getParameter("email"));
		httpservletrequest.setAttribute("SPRING_SECURITY_LAST_EXCEPTION", authenticationexception);
//		httpservletrequest.getRequestDispatcher("/login?error=true").forward(httpservletrequest, httpservletresponse);
		httpservletresponse.sendRedirect("/login?error=true");
	}

}
