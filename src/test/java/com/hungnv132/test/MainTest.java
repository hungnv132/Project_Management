package com.hungnv132.test;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.LocalDateTime;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.hungnv132.core.support.DatatableForm;
import com.hungnv132.core.support.Gson_JodaDate_Serializer;
import com.hungnv132.core.support.DatatableForm.OrderKeys;

//import com.fasterxml.jackson.datatype.joda.JodaModule;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { com.hungnv132.config.CoreConfig.class })
@WebAppConfiguration
public class MainTest {

	protected void setDefaultDatatableForm(DatatableForm form) {
		form.setDraw(1);
		form.setStart(0);
		form.setLength(10);
		
		
	}

	protected void printGson(Object object) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(LocalDateTime.class, new Gson_JodaDate_Serializer());
		Gson gson = gsonBuilder.setExclusionStrategies(new ExclusionStrategy() {
			@Override
			public boolean shouldSkipField(FieldAttributes fieldAttributes) {
				final Expose expose = fieldAttributes.getAnnotation(Expose.class);
				return expose != null && !expose.serialize();
			}

			@Override
			public boolean shouldSkipClass(Class<?> aClass) {
				return false;
			}
		}).addDeserializationExclusionStrategy(new ExclusionStrategy() {
			@Override
			public boolean shouldSkipField(FieldAttributes fieldAttributes) {
				final Expose expose = fieldAttributes.getAnnotation(Expose.class);
				return expose != null && !expose.deserialize();
			}

			@Override
			public boolean shouldSkipClass(Class<?> aClass) {
				return false;
			}
		}).create();
		print(gson.toJson(object));
	}

	protected void print(Object o) {
		System.out.println(o);
	}

};
