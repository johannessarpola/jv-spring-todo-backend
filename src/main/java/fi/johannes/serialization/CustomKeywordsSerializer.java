package fi.johannes.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import fi.johannes.models.Keywords;

import java.io.IOException;

/**
 * johanness on 19/03/2017.
 */
public class CustomKeywordsSerializer extends StdSerializer<Keywords> {

    public CustomKeywordsSerializer() {
        super(Keywords.class);
    }

    @Override
    public void serialize(Keywords value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartArray();
        value.getWords().forEach(word -> {
            try {
                gen.writeString(word.getWordStr());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        gen.writeEndArray();

    }
}
