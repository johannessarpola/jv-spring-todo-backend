package fi.johannes.conf;

import java.time.LocalDateTime;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fi.johannes.serialization.CustomDateDeserializer;
import fi.johannes.serialization.CustomDateSerializer;

@Configuration
public class TodoAppConfiguration {

    // For some reason serializer works but deserializer won't
	@Bean
	public Jackson2ObjectMapperBuilderCustomizer addCustomDateSerialization() {
		return jacksonObjectMapperBuilder -> {
            jacksonObjectMapperBuilder
                .serializerByType(LocalDateTime.class, new CustomDateSerializer());
            jacksonObjectMapperBuilder
                .deserializerByType(LocalDateTime.class, new CustomDateDeserializer());
        };
	}
}
