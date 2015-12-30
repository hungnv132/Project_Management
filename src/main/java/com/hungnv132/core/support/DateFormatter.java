package com.hungnv132.core.support;

import java.text.ParseException;
import java.util.Locale;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.format.Formatter;

import com.fasterxml.jackson.core.FormatSchema;

public class DateFormatter implements Formatter<LocalDateTime> {

	DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-yyyy");
	@Override
	public String print(LocalDateTime localDateTime, Locale locale) {
		return localDateTime.toString(formatter);		
	}

	@Override
	public LocalDateTime parse(String inputStringDate, Locale locale) throws ParseException {
		
		if (inputStringDate.length() < 10) {
			 throw new  ParseException("wrong format", 0);
		}
		return formatter.parseLocalDateTime(inputStringDate);
	}

	
}
