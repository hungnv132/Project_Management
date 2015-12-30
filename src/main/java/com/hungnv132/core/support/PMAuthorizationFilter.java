package com.hungnv132.core.support;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.hungnv132.core.domain.User;
import com.hungnv132.core.domain.User.ROLE;
import com.hungnv132.core.service.UserService;

public class PMAuthorizationFilter implements Filter {

	Logger logger = LogManager.getLogger(PMAuthorizationFilter.class);
	@Inject
	UserService UserService;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		logger.info("Authorization Filter ");
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		HttpSession session = httpRequest.getSession();
		Object authority = session.getAttribute("joinedUser");
		
		if (authority != null &&((User)authority).getRole() != ROLE.STAFF) {
			logger.info("role"+ authority);
			logger.info("duoc quyn truy cap");
			filterChain.doFilter(request, response);		
		}else{			
			logger.info("ko co quyen truy cap ");
			httpResponse.sendRedirect("/login");
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
