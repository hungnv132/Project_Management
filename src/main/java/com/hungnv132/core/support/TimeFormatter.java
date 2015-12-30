package com.hungnv132.core.support;

import java.text.ParseException;
import java.util.Locale;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.format.Formatter;

import com.fasterxml.jackson.core.FormatSchema;
import com.hungnv132.web.controller.report.CreateReportController;

public class TimeFormatter implements Formatter<LocalTime> {

	final Logger logger = LogManager.getLogger(TimeFormatter.class);

	DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm");
	@Override
	public String print(LocalTime localDateTime, Locale locale) {
		return localDateTime.toString(formatter);		
	}

	@Override
	public LocalTime parse(String inputStringTime, Locale locale) throws ParseException {
		return formatter.parseLocalTime(inputStringTime);
	}

}
