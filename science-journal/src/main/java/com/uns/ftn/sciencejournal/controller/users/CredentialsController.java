package com.uns.ftn.sciencejournal.controller.users;

import com.uns.ftn.sciencejournal.dto.users.CredentialsDTO;
import com.uns.ftn.sciencejournal.mapper.users.CredentialsMapper;
import com.uns.ftn.sciencejournal.model.users.Credentials;
import com.uns.ftn.sciencejournal.service.users.CredentialsService;
import com.uns.ftn.sciencejournal.service.utils.FormDTO;
/*import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

@RestController
@CrossOrigin(origins = "http://localhost:4201")
@RequestMapping(path = "/api/credentials")
public class CredentialsController {

    @Autowired
    CredentialsService credentialsService;

    @Autowired
    CredentialsMapper credentialsMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CredentialsDTO>> getAllCredentials() {
        return ResponseEntity.ok(credentialsMapper.mapManyToDTO(credentialsService.getAll()));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CredentialsDTO> getCredentialsById(@PathVariable("id") String id) {
        if (!id.equals(null)) {
            return ResponseEntity.ok(credentialsMapper.mapToDTO(credentialsService.getById(id)));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CredentialsDTO> createCredentials(@RequestBody CredentialsDTO newCredentials) {
        if (!newCredentials.getUsername().equals(null)) {
            Credentials credentials = credentialsService.createCredentials(credentialsMapper.mapFromDTO(newCredentials));

            if (!credentials.equals(null)) {
                return ResponseEntity.ok(credentialsMapper.mapToDTO(credentials));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CredentialsDTO> updateCredentials(@PathVariable("id") String id,
                                                            @RequestBody CredentialsDTO newCredentials) {
        if (!newCredentials.getUsername().equals(null) && !id.equals(null)) {
            Credentials credentials = credentialsService.updateCredentials(
                    credentialsMapper.mapFromDTO(newCredentials), id);

            if (!credentials.equals(null)) {
                return ResponseEntity.ok(credentialsMapper.mapToDTO(credentials));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteCredentials(@PathVariable("id") String id) {
        if (!id.equals(null)) {
            credentialsService.deleteCredentials(id);
            return ResponseEntity.ok(null);
        }

        return ResponseEntity.badRequest().build();
    }
}
