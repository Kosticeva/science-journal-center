package com.uns.ftn.sciencejournal.controller.common;

import com.uns.ftn.sciencejournal.dto.common.PaperDTO;
import com.uns.ftn.sciencejournal.mapper.common.PaperMapper;
import com.uns.ftn.sciencejournal.model.common.Paper;
import com.uns.ftn.sciencejournal.service.common.PaperService;
import com.uns.ftn.sciencejournal.service.storage.MagazineStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/api/papers")
@CrossOrigin(origins = "http://localhost:4201")
public class PaperController {

    @Autowired
    PaperService paperService;

    @Autowired
    PaperMapper paperMapper;

    @Autowired
    MagazineStorageService magazineStorageService;

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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PaperDTO> createPaper(@RequestBody PaperDTO newPaper) {
        if (!(newPaper.getDoi() == null)) {
            Paper paper = paperService.createPaper(paperMapper.mapFromDTO(newPaper));

            if (paper != null) {
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

    @GetMapping("/download/{doi}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String doi, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = magazineStorageService.downloadPaper(paperService.getById(doi));

        if (resource == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
