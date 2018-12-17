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

    @GetMapping(value = "/{id}/{edition}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IssueDTO> getIssueById(@PathVariable("id") String issn,
                                                 @PathVariable("edition") String edition) {
        if (issn.equals(null) || edition.equals(null)) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(issueMapper.mapToDTO(issueService.getById(issn, edition)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IssueDTO> createIssue(@RequestBody IssueDTO newIssue) {
        if (!newIssue.getMagazine().equals(null) && !newIssue.getEdition().equals(null)) {
            Issue issue = issueService.createIssue(issueMapper.mapFromDTO(newIssue));

            if (!issue.equals(null)) {
                return ResponseEntity.ok(issueMapper.mapToDTO(issue));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/{id}/{edition}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IssueDTO> updateIssue(@PathVariable("id") String issn,
                                                @PathVariable("edition") String edition,
                                                @RequestBody IssueDTO newIssue) {
        if (!newIssue.getMagazine().equals(null) && !issn.equals(null) && !edition.equals(null)) {
            Issue issue = issueService.updateIssue(issueMapper.mapFromDTO(newIssue), issn, edition);

            if (!issue.equals(null)) {
                return ResponseEntity.ok(issueMapper.mapToDTO(issue));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/{id}/{edition}")
    public ResponseEntity deleteIssue(@PathVariable("id") String issn, @PathVariable("edition") String edition) {
        if (issn.equals(null) || edition.equals(null)) {
            ResponseEntity.badRequest().build();
        }

        issueService.deleteIssue(issn, edition);
        return ResponseEntity.ok(null);
    }
}
