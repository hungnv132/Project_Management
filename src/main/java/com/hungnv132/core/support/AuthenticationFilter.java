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

import com.hungnv132.core.service.UserService;

public class AuthenticationFilter implements Filter {

	Logger logger = LogManager.getLogger(AuthenticationFilter.class);
	@Inject
	UserService UserService;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException,
			ServletException {
		logger.info("Authentication Filter ");

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();

		String path = httpRequest.getPathInfo();
		String path1 = httpRequest.getRequestURI();
		logger.info(path);
		logger.info(path1);
		if (path1 != null && path1.startsWith("/login") || path1.startsWith("/resources")) {
			logger.info("path hop le");
			filterChain.doFilter(request, response);
		} else if (session.getAttribute("joinedUser") != null) {
			logger.info("Dang dang nhap");
			filterChain.doFilter(request, response);
		} else {
			logger.info("Authentication false ");
			httpResponse.sendRedirect("/login");
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
