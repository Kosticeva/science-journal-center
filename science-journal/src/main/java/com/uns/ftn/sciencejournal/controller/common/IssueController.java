package com.uns.ftn.sciencejournal.controller.common;

import com.uns.ftn.sciencejournal.dto.common.IssueDTO;
import com.uns.ftn.sciencejournal.service.common.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/issues")
public class IssueController {

    @Autowired
    IssueService issueService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IssueDTO>> getAllIssues() {
        return null;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IssueDTO> getIssueById(@PathVariable("id") Long id) {
        return null;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IssueDTO> createIssue(@RequestBody IssueDTO newIssue) {
        return null;
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IssueDTO> updateIssue(@PathVariable("id") Long id, @RequestBody IssueDTO newIssue) {
        return null;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteIssue(@PathVariable("id") Long id) {
        return null;
    }
}
