package com.hungnv132.core.support;

import java.io.IOException;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@Component
public class Json_JodaDate_Serializer  extends JsonSerializer<LocalDateTime> {

	@Override
	public void serialize(LocalDateTime date, JsonGenerator gen, SerializerProvider provider) throws IOException,
			JsonProcessingException {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-yyyy");
		
		gen.writeString(date.toString(formatter));
	}
	

}
