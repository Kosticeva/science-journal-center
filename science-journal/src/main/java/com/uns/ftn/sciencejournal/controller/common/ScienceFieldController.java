package com.uns.ftn.sciencejournal.controller.common;

import com.uns.ftn.sciencejournal.dto.common.ScienceFieldDTO;
import com.uns.ftn.sciencejournal.mapper.common.ScienceFieldMapper;
import com.uns.ftn.sciencejournal.model.common.ScienceField;
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

    @Autowired
    ScienceFieldMapper scienceFieldMapper;
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ScienceFieldDTO>> getAllScienceFields() {
        return ResponseEntity.ok(scienceFieldMapper.mapManyToDTO(scienceFieldService.getAll()));

    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScienceFieldDTO> getScienceFieldById(@PathVariable("id") String id) {
        if (!id.equals(null)) {
            return ResponseEntity.ok(scienceFieldMapper.mapToDTO(scienceFieldService.getById(id)));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScienceFieldDTO> createScienceField(@RequestBody ScienceFieldDTO newScienceField) {
        if (newScienceField.getCode().equals(null)) {
            ScienceField scienceField = scienceFieldService.createScienceField(scienceFieldMapper.mapFromDTO(newScienceField));

            if (!scienceField.equals(null)) {
                return ResponseEntity.ok(scienceFieldMapper.mapToDTO(scienceField));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScienceFieldDTO> updateScienceField(@PathVariable("id") String id, @RequestBody ScienceFieldDTO newScienceField) {
        if (!newScienceField.getCode().equals(null) && !id.equals(null)) {
            ScienceField scienceField = scienceFieldService.updateScienceField(scienceFieldMapper.mapFromDTO(newScienceField), id);

            if (!scienceField.equals(null)) {
                return ResponseEntity.ok(scienceFieldMapper.mapToDTO(scienceField));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteScienceField(@PathVariable("id") String id) {
        if (!id.equals(null)) {
            scienceFieldService.deleteScienceField(id);
            return ResponseEntity.ok(null);
        }

        return ResponseEntity.badRequest().build();
    }
}
