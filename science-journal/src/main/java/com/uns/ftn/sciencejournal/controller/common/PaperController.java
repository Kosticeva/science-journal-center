package com.uns.ftn.sciencejournal.controller.common;

import com.uns.ftn.sciencejournal.dto.common.PaperDTO;
import com.uns.ftn.sciencejournal.mapper.common.PaperMapper;
import com.uns.ftn.sciencejournal.model.common.Paper;
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

    @Autowired
    PaperMapper paperMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PaperDTO>> getAllPapers() {
        return ResponseEntity.ok(paperMapper.mapManyToDTO(paperService.getAll()));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaperDTO> getPaperById(@PathVariable("id") String id) {
        if (!id.equals(null)) {
            return ResponseEntity.ok(paperMapper.mapToDTO(paperService.getById(id)));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaperDTO> createPaper(@RequestBody PaperDTO newPaper) {
        if (!newPaper.getDoi().equals(null)) {
            Paper paper = paperService.createPaper(paperMapper.mapFromDTO(newPaper));

            if (!paper.equals(null)) {
                return ResponseEntity.ok(paperMapper.mapToDTO(paper));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaperDTO> updatePaper(@PathVariable("id") String id, @RequestBody PaperDTO newPaper) {
        if (!newPaper.getDoi().equals(null) && !id.equals(null)) {
            Paper paper = paperService.updatePaper(paperMapper.mapFromDTO(newPaper), id);

            if (!paper.equals(null)) {
                return ResponseEntity.ok(paperMapper.mapToDTO(paper));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deletePaper(@PathVariable("id") String id) {
        if (!id.equals(null)) {
            paperService.deletePaper(id);
            return ResponseEntity.ok(null);
        }

        return ResponseEntity.badRequest().build();
    }
}
