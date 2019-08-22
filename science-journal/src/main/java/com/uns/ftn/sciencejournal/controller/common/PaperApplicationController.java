package com.uns.ftn.sciencejournal.controller.common;

import com.uns.ftn.sciencejournal.dto.common.PaperApplicationDTO;
import com.uns.ftn.sciencejournal.mapper.common.PaperApplicationMapper;
import com.uns.ftn.sciencejournal.model.common.PaperApplication;
import com.uns.ftn.sciencejournal.service.common.PaperApplicationService;
import com.uns.ftn.sciencejournal.service.users.EditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping(path = "/api/applications")
@CrossOrigin(origins = "http://localhost:4201")
public class PaperApplicationController {

    @Autowired
    PaperApplicationService paperApplicationService;

    @Autowired
    PaperApplicationMapper paperApplicationMapper;

    @Autowired
    EditorService editorService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PaperApplicationDTO>> getAllApplications() {
        return ResponseEntity.ok(paperApplicationMapper.mapManyToDTO(paperApplicationService.getAll()));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaperApplicationDTO> getApplicationById(@PathVariable("id") Long id) {
        if (!id.equals(null)) {
            return ResponseEntity.ok(paperApplicationMapper.mapToDTO(paperApplicationService.getById(id)));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaperApplicationDTO> createApplication(@RequestParam("title") String title,
                                                                 @RequestParam("abstract") String pAbstract,
                                                                 @RequestParam("keyterms") String keyterms,
                                                                 @RequestParam("author") String author,
                                                                 @RequestParam(value = "coauthors", required = false) Long[] coauthors,
                                                                 @RequestParam("magazine") String issn,
                                                                 @RequestParam("field") String field,
                                                                 @RequestParam("file") MultipartFile file) {
        PaperApplicationDTO newApplication = new PaperApplicationDTO();
        newApplication.setTitle(title);
        newApplication.setPaperAbstract(pAbstract);
        newApplication.setKeyTerms(keyterms);
        //newApplication.setAuthor(author);
        if (coauthors == null) {
            //
        } else if (coauthors.length == 0) {

        } else {
            newApplication.setCoauthors(new HashSet<>(Arrays.asList(coauthors)));
        }
        newApplication.setMagazine(issn);
        newApplication.setField(field);
        PaperApplication paperApplication = paperApplicationService.createApplication(paperApplicationMapper.mapFromDTO(newApplication), file);

        /*if (!(paperApplication == null)) {
            TaskDTO task = new TaskDTO();
            task.setApplication(paperApplication.getId());
            task.setDeadline(LocalDateTime.now().plusDays(3));
            task.setUser(editorService.getChiefEditorOfMagazine(paperApplication.getMagazine().getIssn()).getUser().getUsername());
            task.setSummary("Odabir recenzenata");
            task.setType(paperApplication.getState());
            task.setFinished(false);
            taskServicer.createTask(taskMapper.mapFromDTO(task));
            return ResponseEntity.ok(paperApplicationMapper.mapToDTO(paperApplication));
        }*/

        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaperApplicationDTO> updateApplication(@PathVariable("id") Long id,
                                                                 @RequestParam("metadata") PaperApplicationDTO newApplication,
                                                                 @RequestParam("file") MultipartFile file) {
        if (!newApplication.getPaperId().equals(null) && !id.equals(null)) {
            PaperApplication paperApplication = paperApplicationService.updateApplication(
                    paperApplicationMapper.mapFromDTO(newApplication), id, file);

            if (!paperApplication.equals(null)) {
                return ResponseEntity.ok(paperApplicationMapper.mapToDTO(paperApplication));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteApplication(@PathVariable("id") Long id) {
        if (!id.equals(null)) {
            paperApplicationService.deleteApplication(id);
            return ResponseEntity.ok(null);
        }

        return ResponseEntity.badRequest().build();
    }
}
