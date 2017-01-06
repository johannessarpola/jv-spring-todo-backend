package fi.johannes.conf;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import fi.johannes.serialization.LocalDateTimeDeserializer;
import fi.johannes.serialization.LocalDateTimeSerializer;

@Configuration
public class TodoAppConfiguration {

    // For some reason serializer works but deserializer won't
	@Bean
	public Jackson2ObjectMapperBuilderCustomizer addCustomDateSerialization() {
		return jacksonObjectMapperBuilder -> {
            jacksonObjectMapperBuilder
                .serializerByType(LocalDateTime.class, new LocalDateTimeSerializer());
            jacksonObjectMapperBuilder
                .deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer());
        };
	}
}
