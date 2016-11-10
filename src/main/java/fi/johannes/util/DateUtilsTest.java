package fi.johannes.util;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;
import org.springframework.data.util.Pair;

public class DateUtilsTest {

	@Test
	public void test() {
		LocalDateTime now = LocalDateTime.now();
		Pair<LocalDateTime, LocalDateTime> pair = DateUtils.getCurrentWeek();
		assertEquals(now.getYear(), pair.getFirst().getYear());
		assertEquals(now.getYear(), pair.getSecond().getYear());
		assertTrue(now.getDayOfYear() <= pair.getSecond().getDayOfYear());
		assertTrue(now.getDayOfYear() >= pair.getFirst().getDayOfYear());
		
	}

}
