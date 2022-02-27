package com.antogian.boringapp.converters;

import com.antogian.boringapp.model.domain.Attribute;
import com.antogian.boringapp.model.entity.AttributeDefinition;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AttributeDefinitionConverter implements Converter<AttributeDefinition, Attribute> {

    @Override
    public Attribute convert(AttributeDefinition source) {
        return Attribute.builder()
                .category(source.getCategory())
                .description(source.getDescription())
                .build();
    }
}
