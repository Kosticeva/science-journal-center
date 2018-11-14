package com.uns.ftn.sciencejournal.controller.common;

import com.uns.ftn.sciencejournal.dto.common.ScienceFieldDTO;
import com.uns.ftn.sciencejournal.service.common.ScienceFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/scienceFields")
public class ScienceFieldController {

    @Autowired
    ScienceFieldService scienceFieldService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ScienceFieldDTO>> getAllScienceFields() {
        return null;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScienceFieldDTO> getScienceFieldById(@PathVariable("id") String id) {
        return null;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScienceFieldDTO> createScienceField(@RequestBody ScienceFieldDTO newScienceField) {
        return null;
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScienceFieldDTO> updateScienceField(@PathVariable("id") String id, @RequestBody ScienceFieldDTO newScienceField) {
        return null;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteScienceField(@PathVariable("id") String id) {
        return null;
    }
}
