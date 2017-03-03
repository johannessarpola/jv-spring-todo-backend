package fi.johannes.serialization;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import fi.johannes.conf.TodoAppConfiguration;
import fi.johannes.util.DateUtils;


@RunWith(SpringRunner.class)
@JsonTest
@ContextConfiguration(classes = {TodoAppConfiguration.class})
public class LocalDateTimeSerializerTest {

	@Autowired
    private JacksonTester<TestDate> json;
	
	@Test
	public void serializationTest() throws IOException {
		LocalDateTime now = LocalDateTime.now();
		String astext = DateUtils.localDateTimeToString(now);
	    Writer jsonWriter = new StringWriter();
	    JsonGenerator jsonGenerator = new JsonFactory().createGenerator(jsonWriter);
	    SerializerProvider serializerProvider = new ObjectMapper().getSerializerProvider();
		CustomDateSerializer ldts = new CustomDateSerializer();
		ldts.serialize(now, jsonGenerator, serializerProvider);
		jsonGenerator.flush();
		assertEquals("\""+astext+"\"", jsonWriter.toString());
	}
	
	@Test
	public void serializationTest2() throws IOException {		
		LocalDateTime ab = LocalDateTime.of(1999, 1, 1, 0, 0);
		TestDate td = new TestDate();
		td.setTime(ab);
		td.setId("Abc");
		
        // Assert against a `.json` file in the same package as the test
        assertThat(this.json.write(td)).isEqualToJson("testDate1.json");
        // Or use JSON path based assertions
        assertThat(this.json.write(td)).hasJsonPathStringValue("@.time");
        assertThat(this.json.write(td)).extractingJsonPathStringValue("@.time")
                .isEqualTo(DateUtils.localDateTimeToString(ab));
	}
	
	private static class TestDate{
		LocalDateTime time;
		String id;
		public TestDate() {
		}
		public void setTime(LocalDateTime time) {
			this.time = time;
		}
		public LocalDateTime getTime() {
			return time;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getId() {
			return id;
		}
	}
}
