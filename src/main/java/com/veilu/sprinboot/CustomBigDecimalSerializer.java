package com.veilu.sprinboot;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustomBigDecimalSerializer extends JsonSerializer<Long> {
	
	public CustomBigDecimalSerializer() {
	}
	
    @Override
    public void serialize(Long value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonGenerationException {
        if (null == value) {
            jgen.writeNull();
        } else {
        	jgen.writeNumber(((Long) value));
        	System.out.println("hi");
        }
    }
}