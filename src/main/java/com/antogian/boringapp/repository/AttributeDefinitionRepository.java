package com.antogian.boringapp.repository;

import com.antogian.boringapp.model.entity.AttributeDefinition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttributeDefinitionRepository extends CrudRepository<AttributeDefinition, Long> {
    List<AttributeDefinition> findAllByCategory(String category);
}
