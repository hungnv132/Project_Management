package com.hungnv132.config;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hungnv132.config.component.MyLocalResolver;
import com.hungnv132.core.support.JodaDateTimeModule;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.hungnv132.web.controller" })
public class WebConfig extends WebMvcConfigurationSupport {

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		// ResourceBundleMessageSource messageSource = new
		// ResourceBundleMessageSource();
		messageSource.setBasenames(
				"classpath:messages/messages",
				"classpath:messages/validations",
				"classpath:positions");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setUseCodeAsDefaultMessage(false);
		return messageSource;
	}

	@Bean
	public MessageCodesResolver messageCodesResolver() {
		DefaultMessageCodesResolver resolver = new DefaultMessageCodesResolver();
		// resolver.setPrefix("validation.");
		return resolver;
	}

	@Bean
	public LocaleResolver localeResolver() {
		return new MyLocalResolver();
	}


	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	

}
