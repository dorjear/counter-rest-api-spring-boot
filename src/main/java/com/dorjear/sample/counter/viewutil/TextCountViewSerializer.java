package com.dorjear.sample.counter.viewutil;

import java.io.IOException;

import com.dorjear.sample.counter.viewmodel.TextCountView;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class TextCountViewSerializer extends JsonSerializer<TextCountView> {

	@Override
	public void serialize(TextCountView value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		jgen.writeStartObject();
		jgen.writeNumberField(value.getText(), value.getCount());
		jgen.writeEndObject();

	}

}
