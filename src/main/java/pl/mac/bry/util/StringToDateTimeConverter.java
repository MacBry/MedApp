package pl.mac.bry.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringToDateTimeConverter {
	
	public static LocalDateTime convert (String date) {
		date = date.replace('T', ' ');
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return LocalDateTime.parse(date, formatter);
	}

}
