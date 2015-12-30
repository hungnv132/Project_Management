package com.hungnv132.core.support;

import java.io.IOException;
import java.lang.reflect.Type;

import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

@Component
public class Gson_JodaTime_Serializer implements JsonSerializer<LocalTime> {

	@Override
	public JsonElement serialize(LocalTime localTime, Type type, JsonSerializationContext context) {

		return new JsonPrimitive(localTime.toString("HH:mm"));
		
//		DateTimeFormatter hourFormatter = DateTimeFormat.forPattern("HH");
//		DateTimeFormatter minuteFormatter = DateTimeFormat.forPattern("mm");
//		String hour = localTime.toString(hourFormatter);
//		String minute = localTime.toString(minuteFormatter);
//		
//		} else {
//		if (minute.equals("00")) {
//			return new JsonPrimitive(hour + " giờ ");
//		} else {
//			return new JsonPrimitive(hour + " giờ " + minute + " phút");
//		}
	}

}
