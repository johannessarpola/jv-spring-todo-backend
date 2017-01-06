package fi.johannes.serialization;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import fi.johannes.util.DateUtils;
import org.springframework.stereotype.Component;

@Component
public class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {

	public LocalDateTimeDeserializer() {
		super(LocalDateTime.class);
	}

	// TODO For some reason this won't be called, could try wrapping LocalDateTime to custom date to see would it have effect
	@Override
	public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException {
		String text = jp.getText();
		return DateUtils.stringToLocalDateTime(text);
	}
}
