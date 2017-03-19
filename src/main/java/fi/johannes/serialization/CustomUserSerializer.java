package fi.johannes.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import fi.johannes.models.User;
import fi.johannes.util.DateUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * johanness on 19/03/2017.
 */
@Component
public class CustomUserSerializer extends StdSerializer<User> {


    public CustomUserSerializer() {
        super(User.class);
    }

    @Override
    public void serialize(User value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException, JsonProcessingException {
        gen.writeStartObject();
        gen.writeNumberField("user_id", value.getId());
        gen.writeStringField("login", value.getLogin());
        gen.writeEndObject();
    }
}