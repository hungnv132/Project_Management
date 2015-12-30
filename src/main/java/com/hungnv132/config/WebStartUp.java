package com.hungnv132.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import com.hungnv132.core.support.AdminAuthorizationFilter;
import com.hungnv132.core.support.AuthenticationFilter;
import com.hungnv132.core.support.PMAuthorizationFilter;

public class WebStartUp implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) throws ServletException {
		registerCharacterEncodingFilter(container);
		registerRootWebApplicationContext(container);
		registerWebDispatcherServlet(container);
		registerDelegatingFilterProxy(container);
//		registerAuthenticationFilter(container);
//		registerAdminAuthorizationFilter(container);
//		registerPMAuthorizationFilter(container);
		
	}

	private void registerRootWebApplicationContext(ServletContext container) {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(com.hungnv132.config.CoreConfig.class);
		container.addListener(new ContextLoaderListener(rootContext));
	}

	private void registerWebDispatcherServlet(ServletContext container) {
		AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
		webContext.register(com.hungnv132.config.WebConfig.class);
		DispatcherServlet dispatcherServlet = new DispatcherServlet(webContext);
//		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
		
		ServletRegistration.Dynamic webDispatcher = container.addServlet("web", dispatcherServlet);
		webDispatcher.setLoadOnStartup(1);
		
		webDispatcher.addMapping("/");
	}

	/*private void registerAuthenticationFilter(ServletContext container) {
		AuthenticationFilter f1 = new AuthenticationFilter();
		FilterRegistration.Dynamic authenFilter = container.addFilter("authenticateFilter", f1);
		authenFilter.addMappingForUrlPatterns(null, false, "/*");
		
	}

	private void registerAdminAuthorizationFilter(ServletContext container) {
		AdminAuthorizationFilter f2 = new AdminAuthorizationFilter();
		FilterRegistration.Dynamic authenFilter = container.addFilter("adminAuthorizeFilter", f2);
		authenFilter.addMappingForUrlPatterns(null, false, "/admin/*");
	}

	private void registerPMAuthorizationFilter(ServletContext container) {
		PMAuthorizationFilter f3 = new PMAuthorizationFilter();
		FilterRegistration.Dynamic authenFilter = container.addFilter("pmAuthorizeFilter", f3);
		authenFilter.addMappingForUrlPatterns(null, false, "/manager/*");
	}*/

	private void registerCharacterEncodingFilter(ServletContext container) {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		FilterRegistration.Dynamic charEncodingFilter = container.addFilter("characterEncodingFilter(",
				characterEncodingFilter);
		charEncodingFilter.addMappingForUrlPatterns(null, false, "/*");
	}
	
	private void registerDelegatingFilterProxy(ServletContext container) {
		DelegatingFilterProxy springSecurityFilterChain = new DelegatingFilterProxy();
		FilterRegistration.Dynamic authenFilter = container.addFilter("springSecurityFilterChain", springSecurityFilterChain);
		authenFilter.addMappingForUrlPatterns(null, false, "/*");
	}

	

}
