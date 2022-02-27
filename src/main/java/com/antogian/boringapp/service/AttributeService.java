package com.antogian.boringapp.service;

import com.antogian.boringapp.controller.request.AttributeCreationRequest;
import com.antogian.boringapp.converters.AttributeDefinitionConverter;
import com.antogian.boringapp.model.domain.Attribute;
import com.antogian.boringapp.model.entity.AttributeDefinition;
import com.antogian.boringapp.repository.AttributeDefinitionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class AttributeService {
    private AttributeDefinitionRepository repository;
    private AttributeDefinitionConverter converter;

    public List<Attribute> getAttributes() {
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(repository.findAll().iterator(), Spliterator.ORDERED), false)
                .map(def -> converter.convert(def)).collect(Collectors.toList());
    }

    public void saveAttribute(AttributeCreationRequest request) {
        AttributeDefinition definition = AttributeDefinition.builder()
                .category(request.getCategory())
                .description(request.getDescription())
                .build();
        repository.save(definition);
    }

    public List<Attribute> getAttributesByCategory(String category) {
        return StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(repository.findAllByCategory(category).iterator(), Spliterator.ORDERED), false)
                .map(def -> converter.convert(def)).collect(Collectors.toList());
    }
}
