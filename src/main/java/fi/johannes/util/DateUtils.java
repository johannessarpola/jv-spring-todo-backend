package fi.johannes.util;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.data.util.Pair;

public class DateUtils {

	public static Pair<LocalDateTime, LocalDateTime> getCurrentWeek(){
		int now = LocalDateTime.now().getDayOfWeek().getValue();
		LocalDateTime weekstart = LocalDateTime.now().minus(now, ChronoUnit.DAYS);
		LocalDateTime weekend = LocalDateTime.now().plus(7, ChronoUnit.DAYS);
		return Pair.of(weekstart, weekend);
	}
 }
