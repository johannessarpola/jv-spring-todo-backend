package fi.johannes.serialization;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import fi.johannes.util.DateUtils;
import org.springframework.stereotype.Component;

@Component
public class CustomDateSerializer extends StdSerializer<LocalDateTime> {

	public CustomDateSerializer() {
		super(LocalDateTime.class);
    }

	@Override
	public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
				String date = DateUtils.localDateTimeToString(value);
				gen.writeString(date);
	}
}