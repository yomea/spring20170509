package com.test.fomatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;

public class MyDateFormatter implements Formatter<Date> {
	
	private String dateStrPattern;
	
	public MyDateFormatter(String dateStrPattern) {
		this.dateStrPattern = dateStrPattern;
		
	}
	
	
	
	
	@Override
	public String print(Date object, Locale locale) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String dateStr = sdf.format(object);
		
		return dateStr;
	}

	@Override
	public Date parse(String text, Locale locale) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat(dateStrPattern);
		
		Date date = sdf.parse(text);
		
		return date;
	}
	
	
	
	

}
