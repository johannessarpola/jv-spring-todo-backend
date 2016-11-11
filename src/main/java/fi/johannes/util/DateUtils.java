package fi.johannes.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.springframework.data.util.Pair;

public class DateUtils {
	//TODO Move to app prop and change to datetimeformatter
	private static String DATE_PATTERN = "dd.MM.yyyy HH:mm:ss";
	
	public static Pair<LocalDateTime, LocalDateTime> getCurrentWeek(){
		int now = LocalDateTime.now().getDayOfWeek().getValue();
		LocalDateTime weekstart = LocalDateTime.now().minus(now, ChronoUnit.DAYS);
		LocalDateTime weekend = LocalDateTime.now().plus(7, ChronoUnit.DAYS);
		return Pair.of(weekstart, weekend);
	}
	// TODO Test
	public static String localDateTimeToString(LocalDateTime ldt){
		DateTimeFormatter df = DateTimeFormatter.ofPattern(DATE_PATTERN);
		return df.format(ldt);
	}
	// TODO Test
	public static LocalDateTime stringToLocalDateTime(String str){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
		return LocalDateTime.parse(str, formatter);
	}
 }
