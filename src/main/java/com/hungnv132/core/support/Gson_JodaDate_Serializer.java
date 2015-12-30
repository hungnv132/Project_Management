package com.hungnv132.core.support;

import java.io.IOException;
import java.lang.reflect.Type;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;



@Component
public class Gson_JodaDate_Serializer  implements JsonSerializer<LocalDateTime> {

	@Override
	public JsonElement serialize(LocalDateTime localDateTime, Type type, JsonSerializationContext context) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-yyyy");
		
		return new JsonPrimitive(localDateTime.toString(formatter));
	}


	

}
