package com.uns.ftn.sciencejournal.controller.common;

import com.uns.ftn.sciencejournal.dto.common.PaperIssueDTO;
import com.uns.ftn.sciencejournal.mapper.common.PaperIssueMapper;
import com.uns.ftn.sciencejournal.model.common.PaperIssue;
import com.uns.ftn.sciencejournal.service.common.PaperIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/issues")
@CrossOrigin(origins = "http://localhost:4201")
public class PaperIssueController {

    @Autowired
    PaperIssueService paperIssueService;

    @Autowired
    PaperIssueMapper paperIssueMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PaperIssueDTO>> getAllIssues() {
        return ResponseEntity.ok(paperIssueMapper.mapManyToDTO(paperIssueService.getAll()));
    }

    @GetMapping(value = "/{issn}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PaperIssueDTO>> getIssuesByMagazine(@PathVariable String issn) {
        return ResponseEntity.ok().body(paperIssueMapper.mapManyToDTO(paperIssueService.getByMagazine(issn)));
    }

    @GetMapping(value = "/{id}/{edition}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaperIssueDTO> getIssueById(@PathVariable("id") String issn,
                                                      @PathVariable("edition") String edition) {
        if (issn.equals(null) || edition.equals(null)) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(paperIssueMapper.mapToDTO(paperIssueService.getById(issn, edition)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaperIssueDTO> createIssue(@RequestBody PaperIssueDTO newIssue) {
        if (!newIssue.getMagazine().equals(null) && !newIssue.getEdition().equals(null)) {
            PaperIssue paperIssue = paperIssueService.createIssue(paperIssueMapper.mapFromDTO(newIssue));

            if (!paperIssue.equals(null)) {
                return ResponseEntity.ok(paperIssueMapper.mapToDTO(paperIssue));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/{id}/{edition}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaperIssueDTO> updateIssue(@PathVariable("id") String issn,
                                                     @PathVariable("edition") String edition,
                                                     @RequestBody PaperIssueDTO newIssue) {
        if (!newIssue.getMagazine().equals(null) && !issn.equals(null) && !edition.equals(null)) {
            PaperIssue paperIssue = paperIssueService.updateIssue(paperIssueMapper.mapFromDTO(newIssue), issn, edition);

            if (!paperIssue.equals(null)) {
                return ResponseEntity.ok(paperIssueMapper.mapToDTO(paperIssue));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/{id}/{edition}")
    public ResponseEntity deleteIssue(@PathVariable("id") String issn, @PathVariable("edition") String edition) {
        if (issn.equals(null) || edition.equals(null)) {
            ResponseEntity.badRequest().build();
        }

        paperIssueService.deleteIssue(issn, edition);
        return ResponseEntity.ok(null);
    }
}
