package fi.johannes.randombeans;

import static org.junit.Assert.*;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

import fi.johannes.entity.Todo;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;

public class MockTodo {
	static EnhancedRandom ench = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
			   .minCollectionSize(5)
			   .maxCollectionSize(10)
			   .minStringLength(10)
			   .maxStringLength(20)
			   .maxObjectPoolSize(100)
			   .charset(StandardCharsets.UTF_8)
			   .dateRange(LocalDate.now(), LocalDate.now().plus(30, ChronoUnit.DAYS))
			   .timeRange(LocalTime.of(8, 0), LocalTime.of(17, 20))
			   .scanClasspathForConcreteTypes(true)
			   .seed(123L)
			   .build();
	
	public static Todo giveOne(){
		return ench.nextObject(Todo.class);
	}
}
