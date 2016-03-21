package net.lelyak.edu.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class LocalDateTimeConverter {
	private static final String dateFormat = "dd/MM/yyyy";
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);;

	public static LocalDateTime convert(String source) {
		if (source == null || source.isEmpty()) {
			return null;
		}

		return LocalDateTime.parse(source.trim(), formatter);
	}
}