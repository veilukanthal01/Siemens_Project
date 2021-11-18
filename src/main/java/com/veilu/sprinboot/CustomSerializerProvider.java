package com.veilu.sprinboot;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.SerializerFactory;

public class CustomSerializerProvider extends DefaultSerializerProvider {

	private static final long serialVersionUID = -2679313928236200861L;

	public CustomSerializerProvider() {
		super();
	}

	public CustomSerializerProvider(CustomSerializerProvider provider, SerializationConfig config,
			SerializerFactory jsf) {
		super(provider, config, jsf);
	}

	@Override
	public CustomSerializerProvider createInstance(SerializationConfig config, SerializerFactory jsf) {
		return new CustomSerializerProvider(this, config, jsf);
	}

	@Override
	public JsonSerializer<Object> findNullValueSerializer(BeanProperty property) throws JsonMappingException {
		
		if (property.getType().getRawClass().equals(String.class)) {
			return EmptyStringSerializer.INSTANCE;
		} else {
			return super.findNullValueSerializer(property);
		}
	}
	
}
