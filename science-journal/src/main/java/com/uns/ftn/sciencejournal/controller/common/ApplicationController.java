package com.uns.ftn.sciencejournal.controller.common;

import com.uns.ftn.sciencejournal.dto.common.ApplicationDTO;
import com.uns.ftn.sciencejournal.mapper.common.ApplicationMapper;
import com.uns.ftn.sciencejournal.model.common.Application;
import com.uns.ftn.sciencejournal.service.common.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/applications")
public class ApplicationController {

    @Autowired
    ApplicationService applicationService;

    @Autowired
    ApplicationMapper applicationMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ApplicationDTO>> getAllApplications() {
        return ResponseEntity.ok(applicationMapper.mapManyToDTO(applicationService.getAll()));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApplicationDTO> getApplicationById(@PathVariable("id") Long id) {
        if (!id.equals(null)) {
            return ResponseEntity.ok(applicationMapper.mapToDTO(applicationService.getById(id)));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApplicationDTO> createApplication(@RequestBody ApplicationDTO newApplication) {
        if (newApplication.getPaperId().equals(null)) {
            Application application = applicationService.createApplication(applicationMapper.mapFromDTO(newApplication));

            if (!application.equals(null)) {
                return ResponseEntity.ok(applicationMapper.mapToDTO(application));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApplicationDTO> updateApplication(@PathVariable("id") Long id, @RequestBody ApplicationDTO newApplication) {
        if (!newApplication.getPaperId().equals(null) && !id.equals(null)) {
            Application application = applicationService.updateApplication(applicationMapper.mapFromDTO(newApplication), id);

            if (!application.equals(null)) {
                return ResponseEntity.ok(applicationMapper.mapToDTO(application));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteApplication(@PathVariable("id") Long id) {
        if (!id.equals(null)) {
            applicationService.deleteApplication(id);
            return ResponseEntity.ok(null);
        }

        return ResponseEntity.badRequest().build();
    }
}
