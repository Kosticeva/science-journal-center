package com.uns.ftn.sciencejournal.controller.common;

import com.uns.ftn.sciencejournal.dto.common.PaperDTO;
import com.uns.ftn.sciencejournal.service.common.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/papers")
public class PaperController {

    @Autowired
    PaperService paperService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PaperDTO>> getAllPapers() {
        return null;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaperDTO> getPaperById(@PathVariable("id") String id) {
        return null;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaperDTO> createPaper(@RequestBody PaperDTO newPaper) {
        return null;
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaperDTO> updatePaper(@PathVariable("id") String id, @RequestBody PaperDTO newPaper) {
        return null;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deletePaper(@PathVariable("id") String id) {
        return null;
    }
}
