package com.uns.ftn.sciencejournal.controller.users;

import com.uns.ftn.sciencejournal.dto.users.EditorDTO;
import com.uns.ftn.sciencejournal.mapper.users.EditorMapper;
import com.uns.ftn.sciencejournal.model.users.Editor;
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

    @Autowired
    EditorMapper editorMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EditorDTO>> getAllEditors() {
        return ResponseEntity.ok(editorMapper.mapManyToDTO(editorService.getAll()));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EditorDTO> getEditorById(@PathVariable("id") Integer id) {
        if (!id.equals(null)) {
            return ResponseEntity.ok(editorMapper.mapToDTO(editorService.getById(id)));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EditorDTO> createEditor(@RequestBody EditorDTO newEditor) {
        if (newEditor.getId().equals(null)) {
            Editor editor = editorService.createEditor(editorMapper.mapFromDTO(newEditor));

            if (!editor.equals(null)) {
                return ResponseEntity.ok(editorMapper.mapToDTO(editor));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EditorDTO> updateEditor(@PathVariable("id") Integer id, @RequestBody EditorDTO newEditor) {
        if (!newEditor.getId().equals(null) && !id.equals(null)) {
            Editor editor = editorService.updateEditor(editorMapper.mapFromDTO(newEditor), id);

            if (!editor.equals(null)) {
                return ResponseEntity.ok(editorMapper.mapToDTO(editor));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteEditor(@PathVariable("id") Integer id) {
        if (!id.equals(null)) {
            editorService.deleteEditor(id);
            return ResponseEntity.ok(null);
        }

        return ResponseEntity.badRequest().build();
    }
}
