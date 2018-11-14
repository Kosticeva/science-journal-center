package com.uns.ftn.sciencejournal.controller.users;

import com.uns.ftn.sciencejournal.dto.users.EditorDTO;
import com.uns.ftn.sciencejournal.service.users.EditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/editors")
public class EditorController {

    @Autowired
    EditorService editorService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EditorDTO>> getAllEditors() {
        return null;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EditorDTO> getEditorById(@PathVariable("id") Integer id) {
        return null;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EditorDTO> createEditor(@RequestBody EditorDTO newEditor) {
        return null;
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EditorDTO> updateEditor(@PathVariable("id") Integer id, @RequestBody EditorDTO newEditor) {
        return null;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteEditor(@PathVariable("id") Integer id) {
        return null;
    }
}
