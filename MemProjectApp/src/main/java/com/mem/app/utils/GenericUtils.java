package com.mem.app.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GenericUtils {
	
	public static  Date getDate(String dateAsString) {
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
		try {
			return ft.parse(dateAsString);
			
		} catch (ParseException e) {
		}
		return null;
	}

}
