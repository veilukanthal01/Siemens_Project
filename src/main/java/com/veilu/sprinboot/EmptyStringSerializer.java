package com.veilu.sprinboot;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class EmptyStringSerializer extends JsonSerializer<Object> {

	public static final JsonSerializer<Object> INSTANCE = new EmptyStringSerializer();

	private EmptyStringSerializer() {
	}

	@Override
	public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
			throws IOException, JsonProcessingException {

		jsonGenerator.writeString("");
	}
}
