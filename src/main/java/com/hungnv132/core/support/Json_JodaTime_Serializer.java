package com.hungnv132.core.support;

import java.io.IOException;

import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@Component
public class Json_JodaTime_Serializer extends JsonSerializer<LocalTime> {

	@Override
	public void serialize(LocalTime time, JsonGenerator gen, SerializerProvider provider) throws IOException,
			JsonProcessingException {
		DateTimeFormatter hourFormatter = DateTimeFormat.forPattern("HH");
		DateTimeFormatter minuteFormatter = DateTimeFormat.forPattern("mm");
		String hour = time.toString(hourFormatter);
		String minute = time.toString(minuteFormatter);
		if (minute.equals("00")) {
			gen.writeString(hour + " giờ ");
		} else {
			gen.writeString(hour + " giờ " + minute + " phút");
		}
	}

}
