package com.uns.ftn.sciencejournal.controller.common;

import com.uns.ftn.sciencejournal.dto.common.IssueDTO;
import com.uns.ftn.sciencejournal.mapper.common.IssueMapper;
import com.uns.ftn.sciencejournal.model.common.Issue;
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

    @Autowired
    IssueMapper issueMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IssueDTO>> getAllIssues() {
        return ResponseEntity.ok(issueMapper.mapManyToDTO(issueService.getAll()));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IssueDTO> getIssueById(@PathVariable("id") Long id) {
        if (!id.equals(null)) {
            return ResponseEntity.ok(issueMapper.mapToDTO(issueService.getById(id)));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IssueDTO> createIssue(@RequestBody IssueDTO newIssue) {
        if (newIssue.getId().equals(null)) {
            Issue issue = issueService.createIssue(issueMapper.mapFromDTO(newIssue));

            if (!issue.equals(null)) {
                return ResponseEntity.ok(issueMapper.mapToDTO(issue));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IssueDTO> updateIssue(@PathVariable("id") Long id, @RequestBody IssueDTO newIssue) {
        if (!newIssue.getId().equals(null) && !id.equals(null)) {
            Issue issue = issueService.updateIssue(issueMapper.mapFromDTO(newIssue), id);

            if (!issue.equals(null)) {
                return ResponseEntity.ok(issueMapper.mapToDTO(issue));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteIssue(@PathVariable("id") Long id) {
        if (!id.equals(null)) {
            issueService.deleteIssue(id);
            return ResponseEntity.ok(null);
        }

        return ResponseEntity.badRequest().build();
    }
}
