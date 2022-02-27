package com.antogian.boringapp.controller;

import com.antogian.boringapp.controller.request.AttributeCreationRequest;
import com.antogian.boringapp.model.domain.Attribute;
import com.antogian.boringapp.service.AttributeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api")
@AllArgsConstructor
public class AttributeController {

    private AttributeService service;

    @GetMapping(path = "/v1/attributes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Attribute> getAttributes() {
        return service.getAttributes();
    }

    @PutMapping(path = "/v1/attributes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveAttribute (@RequestBody final AttributeCreationRequest request) {
        service.saveAttribute(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/v1/attributes/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Attribute> getAttributesByCategory(@RequestParam String category) {
        return service.getAttributesByCategory(category);
    }

}
