package com.hungnv132.core.support;

import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import com.fasterxml.jackson.databind.module.SimpleModule;

public class JodaDateTimeModule extends SimpleModule {

	public JodaDateTimeModule(){
		super();
		addSerializer(LocalDateTime.class, new Json_JodaDate_Serializer());
		addSerializer(LocalTime.class, new Json_JodaTime_Serializer());
	}
}
