package com.uns.ftn.sciencejournal.controller;

import com.uns.ftn.sciencejournal.dto.PaperResultDTO;
import com.uns.ftn.sciencejournal.dto.common.ApplicationDTO;
import com.uns.ftn.sciencejournal.dto.common.PaperDTO;
import com.uns.ftn.sciencejournal.dto.users.CredentialsDTO;
import com.uns.ftn.sciencejournal.dto.users.ReviewerDTO;
import com.uns.ftn.sciencejournal.dto.users.UserDTO;
import com.uns.ftn.sciencejournal.mapper.common.ApplicationMapper;
import com.uns.ftn.sciencejournal.mapper.common.PaperMapper;
import com.uns.ftn.sciencejournal.mapper.users.CredentialsMapper;
import com.uns.ftn.sciencejournal.mapper.users.UserMapper;
import com.uns.ftn.sciencejournal.model.SearchFieldQuery;
import com.uns.ftn.sciencejournal.model.SearchQuery;
import com.uns.ftn.sciencejournal.service.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
@CrossOrigin(origins = "http://localhost:4201")
public class SearchController {

    @Autowired
    SearchService searchService;

    @Autowired
    CredentialsMapper userMapper;

    @Autowired
    ApplicationMapper applicationMapper;

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PaperResultDTO>> getPapers(@RequestBody SearchQuery query) {
        return ResponseEntity.ok(searchService.searchPapers(query));
    }

    @PutMapping(value = "/query", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PaperResultDTO>> getPapers(@RequestBody String query) {
        return ResponseEntity.ok(searchService.searchPapersQuery(query));
    }

    @PutMapping(value = "/reviewers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CredentialsDTO>> getReviewers(@RequestBody ApplicationDTO applicationDTO) {
        return ResponseEntity.ok().body(userMapper.mapManyToDTO(searchService.searchReviewers(applicationMapper.mapFromDTO(applicationDTO))));
    }
}
