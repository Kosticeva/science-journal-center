package com.uns.ftn.sciencejournal.controller.users;

import com.uns.ftn.sciencejournal.dto.users.CredentialsDTO;
import com.uns.ftn.sciencejournal.service.users.CredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/credentials")
public class CredentialsController {

    @Autowired
    CredentialsService credentialsService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CredentialsDTO>> getAllCredentialss() {
        return null;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CredentialsDTO> getCredentialsById(@PathVariable("id") String id) {
        return null;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CredentialsDTO> createCredentials(@RequestBody CredentialsDTO newCredentials) {
        return null;
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CredentialsDTO> updateCredentials(@PathVariable("id") String id, @RequestBody CredentialsDTO newCredentials) {
        return null;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteCredentials(@PathVariable("id") String id) {
        return null;
    }
}
