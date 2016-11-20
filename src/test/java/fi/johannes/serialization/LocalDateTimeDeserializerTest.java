package fi.johannes.serialization;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;

import fi.johannes.util.DateUtils;

public class LocalDateTimeDeserializerTest {

    private ObjectMapper mapper;
    private LocalDateTimeDeserializer deserializer;

    @Before
    public void setup() {
        mapper = new ObjectMapper();
        deserializer = new LocalDateTimeDeserializer();
    }
	
	@Test
	public void deserializer_test() throws JsonParseException, IOException {
		LocalDateTime ldt = LocalDateTime.now();
        String json = String.format("{\"time\":%s}", "\""+DateUtils.localDateTimeToString(ldt)+"\"");
        LocalDateTime deserialisedLDT = deserializeLDT(json);
        assertThat(deserialisedLDT, instanceOf(LocalDateTime.class));
        assertThat(deserialisedLDT.getHour(), is(equalTo(ldt.getHour())));
        assertThat(deserialisedLDT.getDayOfMonth(), is(equalTo(ldt.getDayOfMonth())));
        assertThat(deserialisedLDT.getYear(), is(equalTo(ldt.getYear())));
        assertThat(deserialisedLDT.getMinute(), is(equalTo(ldt.getMinute())));
        assertThat(deserialisedLDT.getSecond(), is(equalTo(ldt.getSecond())));
	}
    private LocalDateTime deserializeLDT(String json) throws JsonParseException, IOException {
        InputStream stream = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
        JsonParser parser = mapper.getFactory().createParser(stream);
        DeserializationContext ctxt = mapper.getDeserializationContext();
        parser.nextToken();
        parser.nextToken();
        parser.nextToken();
        return deserializer.deserialize(parser, ctxt);
    }

}
