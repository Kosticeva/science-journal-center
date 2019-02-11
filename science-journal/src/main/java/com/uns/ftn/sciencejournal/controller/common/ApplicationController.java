package com.uns.ftn.sciencejournal.controller.common;

import com.uns.ftn.sciencejournal.dto.common.ApplicationDTO;
import com.uns.ftn.sciencejournal.dto.common.TaskDTO;
import com.uns.ftn.sciencejournal.mapper.common.ApplicationMapper;
import com.uns.ftn.sciencejournal.mapper.common.TaskMapper;
import com.uns.ftn.sciencejournal.model.common.Application;
import com.uns.ftn.sciencejournal.service.common.ApplicationService;
import com.uns.ftn.sciencejournal.service.common.TaskServicer;
import com.uns.ftn.sciencejournal.service.users.EditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping(path = "/api/applications")
@CrossOrigin(origins = "http://localhost:4201")
public class ApplicationController {

    @Autowired
    ApplicationService applicationService;

    @Autowired
    ApplicationMapper applicationMapper;

    @Autowired
    TaskServicer taskServicer;

    @Autowired
    TaskMapper taskMapper;

    @Autowired
    EditorService editorService;

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

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApplicationDTO> createApplication(@RequestParam("title") String title,
                                                            @RequestParam("abstract") String pAbstract,
                                                            @RequestParam("keyterms") String keyterms,
                                                            @RequestParam("author") String author,
                                                            @RequestParam(value = "coauthors", required = false) Long[] coauthors,
                                                            @RequestParam("magazine") String issn,
                                                            @RequestParam("field") String field,
                                                            @RequestParam("file") MultipartFile file) {
        ApplicationDTO newApplication = new ApplicationDTO();
        newApplication.setTitle(title);
        newApplication.setPaperAbstract(pAbstract);
        newApplication.setKeyTerms(keyterms);
        newApplication.setAuthor(author);
        if(coauthors == null) {
            //
        }else if(coauthors.length == 0) {

        }else{
            newApplication.setCoauthors(new HashSet<>(Arrays.asList(coauthors)));
        }
        newApplication.setMagazine(issn);
        newApplication.setField(field);
        Application application = applicationService.createApplication(applicationMapper.mapFromDTO(newApplication), file);

        if (!(application == null)) {
            TaskDTO task = new TaskDTO();
            task.setApplication(application.getId());
            task.setDeadline(LocalDateTime.now().plusDays(3));
            task.setUser(editorService.getChiefEditorOfMagazine(application.getMagazine().getIssn()).getUser().getUsername());
            task.setSummary("Odabir recenzenata");
            task.setType(application.getState());
            task.setFinished(false);
            taskServicer.createTask(taskMapper.mapFromDTO(task));
            return ResponseEntity.ok(applicationMapper.mapToDTO(application));
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApplicationDTO> updateApplication(@PathVariable("id") Long id,
                                                            @RequestParam("metadata") ApplicationDTO newApplication,
                                                            @RequestParam("file") MultipartFile file) {
        if (!newApplication.getPaperId().equals(null) && !id.equals(null)) {
            Application application = applicationService.updateApplication(
                    applicationMapper.mapFromDTO(newApplication), id, file);

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
