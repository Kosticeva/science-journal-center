package com.uns.ftn.sciencejournal.controller;

import com.uns.ftn.sciencejournal.dto.common.PaperDTO;
import com.uns.ftn.sciencejournal.mapper.common.PaperMapper;
import com.uns.ftn.sciencejournal.model.SearchQuery;
import com.uns.ftn.sciencejournal.service.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    SearchService searchService;

    @Autowired
    PaperMapper paperMapper;

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PaperDTO>> getPapers(@RequestBody SearchQuery query) {
        return ResponseEntity.ok(paperMapper.mapManyToDTO(searchService.searchPapers(query)));
    }
}
