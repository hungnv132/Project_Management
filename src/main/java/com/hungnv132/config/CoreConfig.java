package com.hungnv132.config;

import java.util.List;
import java.util.Properties;

import javax.inject.Inject;
import javax.xml.ws.Response;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.hungnv132.core.support.DateFormatter;
import com.hungnv132.core.support.Gson_JodaDate_Serializer;
import com.hungnv132.core.support.Gson_JodaTime_Serializer;
import com.hungnv132.core.support.JodaDateTimeModule;
import com.hungnv132.core.support.TimeFormatter;

@Configuration
@EnableTransactionManagement
@ImportResource(value="/WEB-INF/config-security/security.xml")
@PropertySources({
		@PropertySource("classpath:/config/connection.properties"),
		@PropertySource("classpath:/positions.properties"),
		@PropertySource("classpath:/config/hibernate.properties"),
		@PropertySource("classpath:/config/mail.properties") })
@ComponentScan(basePackages = { "com.hungnv132.core" })
public class CoreConfig extends WebMvcConfigurerAdapter {
	
	Logger logger = LogManager.getLogger(CoreConfig.class);
	@Inject
	Environment env;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addFormatter(dateFormatter());
		registry.addFormatter(timeFormatter());
	}

	
	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		exceptionResolvers.add(simpleMappingExceptionResolver());
	}

	@Bean
	public SimpleMappingExceptionResolver simpleMappingExceptionResolver(){
		SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
		exceptionResolver.addStatusCode("errors/404", 404);
		
		Properties mappings = new Properties();
//		mappings.put(key, value)
		exceptionResolver.setExceptionMappings(mappings);
		return exceptionResolver;
	}
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);
//		converters.add(createJackson2HttpMessageConverter());
		converters.add(createGsonHttpMessageConverter());
	}


/*	@Bean
	public MappingJackson2HttpMessageConverter createJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JodaDateTimeModule());
		converter.setObjectMapper(objectMapper);
		return converter;
	}*/
	
	
	

	@Bean
	public MailSender mailSender(){
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(env.getProperty("mail.host"));
		mailSender.setPort(Integer.parseInt(env.getProperty("mail.port")));
		mailSender.setUsername(env.getProperty("mail.username"));
		mailSender.setPassword(env.getProperty("mail.password"));
		Properties pro = new Properties();
		pro.setProperty("mail.smtp.auth", env.getProperty("mail.smtp.auth"));
		pro.setProperty("mail.smtp.starttls.enable", env.getProperty("mail.smtp.starttls.enable"));
		/*pro.setProperty("mail.smtp.ssl.trust", env.getProperty("mail.smtp.ssl.trust"));*/
		mailSender.setJavaMailProperties(pro);
		return mailSender;
	}
	
	@Bean
	public GsonHttpMessageConverter createGsonHttpMessageConverter() {
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(LocalDateTime.class, new Gson_JodaDate_Serializer());
		gsonBuilder.registerTypeAdapter(LocalTime.class, new Gson_JodaTime_Serializer());
		Gson gson = gsonBuilder
			 .addSerializationExclusionStrategy(new ExclusionStrategy() {
		            @Override
		            public boolean shouldSkipField(FieldAttributes fieldAttributes) {
		                final Expose expose = fieldAttributes.getAnnotation(Expose.class);
		                return expose != null && !expose.serialize();
		            }
	
		            @Override
		            public boolean shouldSkipClass(Class<?> aClass) {
		                return false;
		            }
		        })
		        .addDeserializationExclusionStrategy(new ExclusionStrategy() {
		            @Override
		            public boolean shouldSkipField(FieldAttributes fieldAttributes) {
		                final Expose expose = fieldAttributes.getAnnotation(Expose.class);
		                return expose != null && !expose.deserialize();
		            }
	
		            @Override
		            public boolean shouldSkipClass(Class<?> aClass) {
		                return false;
		            }
		        })
		        .create();
		
		GsonHttpMessageConverter gsonConverter = new GsonHttpMessageConverter();
		gsonConverter.setGson(gson);
		return gsonConverter;
	}
	
	@Bean 
	public Formatter<LocalDateTime> dateFormatter(){
		return new DateFormatter();
	}
	@Bean 
	public Formatter<LocalTime> timeFormatter(){
		return new TimeFormatter();
	}
	
	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dmds = new DriverManagerDataSource();
		dmds.setDriverClassName(env.getProperty("jdbc.driverClassName").toString());
		dmds.setUrl(env.getProperty("jdbc.url").toString());
		dmds.setUsername(env.getProperty("jdbc.username").toString());
		dmds.setPassword(env.getProperty("jdbc.password").toString());
		return dmds;
	}

	@Bean
	public HibernateTransactionManager transactionManger() {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory().getObject());
		return txManager;
	}
	@Bean
	public LocalSessionFactoryBean sessionFactory() {

		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] { "com.hungnv132.core.domain" });

		Properties property = new Properties();
		property.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		property.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		// property.setProperty("hibernate.current_session_context_class",
		// env.getProperty("hibernate.current_session_context_class"));
		property.setProperty("javax.persistence.validation.mode", env.getProperty("javax.persistence.validation.mode"));
		logger.info("sessionfactory");
		sessionFactory.setHibernateProperties(property);
		return sessionFactory;
	}
}
